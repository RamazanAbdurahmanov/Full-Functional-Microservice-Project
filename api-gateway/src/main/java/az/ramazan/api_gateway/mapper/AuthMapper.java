package az.ramazan.api_gateway.mapper;

import az.ramazan.api_gateway.model.AuthResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import static az.ramazan.api_gateway.mapper.DateMapper.DATE_MAPPER;

public enum AuthMapper {
    AUTH_MAPPER;

    public AuthResponse buildAuthresponse(OidcUser oidcUser,
                                          OAuth2AuthorizedClient client) {
        return AuthResponse.builder()
                .userId(oidcUser.getEmail())
                .accessToken(client.getAccessToken().getTokenValue())
                .refreshToken(client.getRefreshToken().getTokenValue())
                .expiresAt(DATE_MAPPER.toLocalDateTime(client.getAccessToken().getExpiresAt()))
                .authorities(oidcUser.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList()
                )
                .build();
    }
}
