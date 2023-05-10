variable "compartment_id" {
  type        = string
  description = "The compartment to create the resources in"
}
variable "region" {
  type        = string
  description = "The region to provision the resources in"
}
variable "public_subnet_id" {
  type = string
  description = "The public subnet's OCID"
}
variable "node_pool_id" {
  type = string
  description = "The OCID of the Node Pool where the compute instances reside"
}
variable "github_username" {
  type = string
  description = "Github Username"
}
variable "github_pat" {
  type = string
  description = "Github Personal Access Token"
}
variable "github_email" {
  type = string
  description = "Github Email"
}
