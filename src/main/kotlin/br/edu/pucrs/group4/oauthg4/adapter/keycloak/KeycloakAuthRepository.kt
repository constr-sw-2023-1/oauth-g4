package br.edu.pucrs.group4.oauthg4.adapter.keycloak

import br.edu.pucrs.group4.oauthg4.domain.dto.JwtTokenDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "keycloak-auth", url = "\${spring.security.oauth2.keycloak.realm.url}")
interface KeycloakAuthRepository {

    @PostMapping("/protocol/openid-connect/token")
    fun login(
        @RequestParam clientId: String,
        @RequestParam clientSecret: String,
        @RequestParam username: String,
        @RequestParam password: String,
        @RequestParam grantType: String
    ): JwtTokenDTO

}
