package com.pongchi.glimelight.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        ErrorPage errorPage401 = new ErrorPage(HttpStatus.UNAUTHORIZED, "/exception/authentication-fail");
        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/exception/not-found");
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/exception/interval-server-error");
        ErrorPage errorPageEx = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/exception/interval-server-error");

        factory.addErrorPages(errorPage401, errorPage404, errorPage500, errorPageEx);
    }
}
