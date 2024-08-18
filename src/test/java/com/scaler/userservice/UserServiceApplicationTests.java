package com.scaler.userservice;

import com.scaler.userservice.security.repositories.JpaRegisteredClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.UUID;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private JpaRegisteredClientRepository registeredClientRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void addSampleRegisteredClient() {
		RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
				.clientId("oidc-client")
				.clientSecret("$2a$12$a68OqYZ.WxZ6qkUR4qu1xOqlq/AJ7r5N/EnYZaCMgiU86K4Jn4eGy")
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				.redirectUri("https://oauth.pstmn.io/v1/callback")
				.postLogoutRedirectUri("https://oauth.pstmn.io/v1/callback")
				.scope(OidcScopes.OPENID)
				.scope(OidcScopes.PROFILE)
				.scope("ADMIN")
				.clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
				.build();

		registeredClientRepository.save(oidcClient);
	}

}
