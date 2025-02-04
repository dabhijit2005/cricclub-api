package com.cricclub.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.util.Utils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.Collections.singletonList;

@Configuration
public class GoogleConfig {
	
	private final GoogleProps googleProps;
	
	public GoogleConfig(GoogleProps googleProps) {
		this.googleProps = googleProps;
	}
	
	@Bean
	public GoogleIdTokenVerifier googleIdTokenVerifier() {
		return new GoogleIdTokenVerifier
						.Builder(Utils.getDefaultTransport(), Utils.getDefaultJsonFactory())
						.setAudience(singletonList(googleProps.getClientId()))
						.build();
	}
}
