@Configuration
class KeycloakConfig {

    @Value("${security.oauth.client_secret}")
    private lateinit var clientSecret

    @Value("${security.oauth.keycloak_url}")
    private lateinit var keycloakUrl

}