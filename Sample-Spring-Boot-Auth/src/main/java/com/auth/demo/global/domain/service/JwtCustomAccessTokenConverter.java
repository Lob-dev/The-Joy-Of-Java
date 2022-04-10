package com.auth.demo.global.domain.service;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtCustomAccessTokenConverter extends JwtAccessTokenConverter {

	@Override
	public OAuth2Authentication extractAuthentication(Map<String, ?> claim) {
		final OAuth2Authentication authentication = super.extractAuthentication(claim);
		authentication.setDetails(claim);
		return authentication;
	}
}