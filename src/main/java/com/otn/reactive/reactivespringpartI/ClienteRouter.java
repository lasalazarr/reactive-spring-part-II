package com.otn.reactive.reactivespringpartI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFlux
@CrossOrigin
public class ClienteRouter {

    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(ClienteHandler clienteHandler){
        return route(GET("/reactive/v1/cliente").and(accept(APPLICATION_JSON)), clienteHandler::getAll)
                .andRoute(POST("/reactive/v1/cliente").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), clienteHandler::save)
                .andRoute(PUT("/reactive/v1/cliente/{id}").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), clienteHandler::update)
                .andRoute(DELETE("/reactive/v1/cliente/{id}"), clienteHandler::delete);
    }
}
