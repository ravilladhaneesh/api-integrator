package com.example.integration.auth;

import org.springframework.stereotype.Component;

import com.example.integration.model.AuthType;

@Component
public class AuthHandlerFactory {

    private final BearerTokenAuthHandler bearerHandler;
    private final OAuthAuthHandler oauthHandler;

    public AuthHandlerFactory(BearerTokenAuthHandler bearerHandler,
                              OAuthAuthHandler oauthHandler) {
        this.bearerHandler = bearerHandler;
        this.oauthHandler = oauthHandler;
    }

    public AuthHandler getHandler(AuthType authType) {
        return switch (authType) {
            case BEARER_TOKEN -> bearerHandler;
            case OAUTH2 -> oauthHandler;
        };
    }
}

