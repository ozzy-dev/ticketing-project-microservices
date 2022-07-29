package com.cydeo.controller;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

  private static final String AUTHORIZATION_HEADER="Authorization";
  private static final String TOKEN_TYPE = "Bearer";
  Logger logger = LoggerFactory.getLogger(FeignClientInterceptor.class);

  @Override
  public void apply(RequestTemplate requestTemplate) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    SimpleKeycloakAccount details = (SimpleKeycloakAccount) authentication.getDetails();
    requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", TOKEN_TYPE, details.getKeycloakSecurityContext().getTokenString()));
  logger.info(String.format("%s %s", TOKEN_TYPE, details.getKeycloakSecurityContext().getTokenString()));
  }
}