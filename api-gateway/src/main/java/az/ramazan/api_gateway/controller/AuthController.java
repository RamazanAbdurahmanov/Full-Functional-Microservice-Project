package az.ramazan.api_gateway.controller;

import az.ramazan.api_gateway.mapper.AuthMapper;
import az.ramazan.api_gateway.model.AuthResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    @GetMapping
    public AuthResponse login(
            Model model,
            @AuthenticationPrincipal OidcUser oidcUser,
            @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient
    ) {
        return AuthMapper.AUTH_MAPPER.buildAuthresponse(oidcUser, authorizedClient);
    }
}
