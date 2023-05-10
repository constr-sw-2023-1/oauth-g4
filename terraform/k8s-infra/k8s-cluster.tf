provider "kubernetes" {
  config_path = "~/.kube/config"
}
provider "oci" {
  region = var.region
}



resource "kubernetes_namespace" "free_namespace" {
  metadata {
    name = "cs-ns"
  }
}

resource "kubernetes_secret" "ghcr-secret" {
  metadata {
    name = "ghcr-secret"
    namespace = kubernetes_namespace.free_namespace.id
  }

  type = "kubernetes.io/dockerconfigjson"

  data = {
    ".dockerconfigjson" = jsonencode({
      auths = {
        "https://ghcr.io" = {
          "username" = var.github_username
          "password" = var.github_pat
          "email"    = var.github_email
          "auth"     = base64encode("${var.github_username}:${var.github_pat}")
        }
      }
    })
  }
}

data "oci_containerengine_node_pool" "free_k8s_np" {
  node_pool_id = var.node_pool_id
}
locals {
  active_nodes = [for node in data.oci_containerengine_node_pool.free_k8s_np.nodes : node if node.state == "ACTIVE"]
}

resource "oci_network_load_balancer_network_load_balancer" "free_nlb" {
  compartment_id                 = var.compartment_id
  display_name                   = "cs-nlb"
  subnet_id                      = var.public_subnet_id
  is_private                     = false
  is_preserve_source_destination = false
}

resource "oci_network_load_balancer_backend_set" "free_nlb_backend_set1" {
  health_checker {
    protocol = "TCP"
    port     = 10256
  }
  name                     = "cs-backend-set1"
  network_load_balancer_id = oci_network_load_balancer_network_load_balancer.free_nlb.id
  policy                   = "FIVE_TUPLE"
  is_preserve_source       = false
}

resource "oci_network_load_balancer_backend_set" "free_nlb_backend_set2" {
  health_checker {
    protocol = "TCP"
    port     = 10256
  }
  name                     = "cs-backend-set2"
  network_load_balancer_id = oci_network_load_balancer_network_load_balancer.free_nlb.id
  policy                   = "FIVE_TUPLE"
  is_preserve_source       = false
}

resource "oci_network_load_balancer_backend" "free_nlb_backend_1" {
  count                    = length(local.active_nodes)
  backend_set_name         = oci_network_load_balancer_backend_set.free_nlb_backend_set1.name
  network_load_balancer_id = oci_network_load_balancer_network_load_balancer.free_nlb.id
  port                     = 31600
  target_id                = local.active_nodes[count.index].id
}
resource "oci_network_load_balancer_backend" "free_nlb_backend_2" {
  count                    = length(local.active_nodes)
  backend_set_name         = oci_network_load_balancer_backend_set.free_nlb_backend_set2.name
  network_load_balancer_id = oci_network_load_balancer_network_load_balancer.free_nlb.id
  port                     = 31601
  target_id                = local.active_nodes[count.index].id
}

resource "oci_network_load_balancer_listener" "free_nlb_listener1" {
  default_backend_set_name = oci_network_load_balancer_backend_set.free_nlb_backend_set1.name
  name                     = "cs-nlb-listener1"
  network_load_balancer_id = oci_network_load_balancer_network_load_balancer.free_nlb.id
  port                     = "8090"
  protocol                 = "TCP"
}
resource "oci_network_load_balancer_listener" "free_nlb_listener2" {
  default_backend_set_name = oci_network_load_balancer_backend_set.free_nlb_backend_set2.name
  name                     = "cs-nlb-listener2"
  network_load_balancer_id = oci_network_load_balancer_network_load_balancer.free_nlb.id
  port                     = "8080"
  protocol                 = "TCP"
}

resource "kubernetes_deployment" "deployment_keycloak" {
  metadata {
    labels = {
      "io.kompose.service" = "keycloak"
    }
    name      = "keycloak"
    namespace = kubernetes_namespace.free_namespace.id
  }
  spec {
    replicas = 1
    selector {
      match_labels = {
        "io.kompose.service" = "keycloak"
      }
    }
    strategy {
      type = "Recreate"
    }
    template {
      metadata {
        labels = {
          "io.kompose.service"         = "keycloak"
        }
      }
      spec {
        container {
          args = [
            "start-dev",
            "--import-realm",
          ]
          env {
            name  = "KEYCLOAK_ADMIN"
            value = "admin"
          }
          env {
            name  = "KEYCLOAK_ADMIN_PASSWORD"
            value = "a12345678"
          }
          env {
            name  = "KEYCLOAK_FRONTEND_URL"
            value = "http://${kubernetes_service.keycloak_service.spec[0].cluster_ip}:8080"
          }
          image = "quay.io/keycloak/keycloak:21.0.2"
          name  = "keycloak"
          port {
            container_port = 8080
          }
        }
        restart_policy = "Always"
      }
    }
  }
}

resource "kubernetes_deployment" "deployment_oauth" {
  metadata {
    labels = {
      "io.kompose.service" = "oauth"
    }
    name      = "oauth"
    namespace = kubernetes_namespace.free_namespace.id
  }
  spec {
    replicas = 1
    selector {
      match_labels = {
        "io.kompose.service" = "oauth"
      }
    }
    template {
      metadata {
        labels = {
          "io.kompose.service"         = "oauth"
        }
      }
      spec {
        container {
          env {
            name  = "KEYCLOAK_CLIENT_ID"
            value = "oauth"
          }
          env {
            name  = "KEYCLOAK_CLIENT_SECRET"
            value = "04bfUatIDO6ipwg1TF2mTzHrX8UZD02Z"
          }
          env {
            name  = "KEYCLOAK_REALM"
            value = "construcao-sw"
          }
          env {
            name  = "KEYCLOAK_REALM_URL"
            value = "http://${kubernetes_service.keycloak_service.spec[0].cluster_ip}:8090"
          }
          image = "ghcr.io/constr-sw-2023-1/oauth-g4:latest"
          name  = "oauth"
          port {
            container_port = 8080
          }
        }
        image_pull_secrets {
          name = "ghcr-secret"
        }
        restart_policy = "Always"
      }
    }
  }
}


resource "kubernetes_service" "keycloak_service" {
  metadata {
    labels = {
      "io.kompose.service" = "keycloak-service"
    }
    name      = "keycloak-service"
    namespace = kubernetes_namespace.free_namespace.id
  }
  spec {
    port {
      port        = 8090
      target_port = 8080
      node_port   = 31600
    }
    selector = {
      "io.kompose.service" = "keycloak"
    }
    type = "NodePort"
  }
}

resource "kubernetes_service" "oauth_service" {
  metadata {
    labels = {
      "io.kompose.service" = "oauth-service"
    }
    name      = "oauth-service"
    namespace = kubernetes_namespace.free_namespace.id
  }
  spec {
    port {
      port        = 8080
      target_port = 8080
      node_port   = 31601
    }
    selector = {
      "io.kompose.service" = "oauth"
    }
    type = "NodePort"
  }
}

