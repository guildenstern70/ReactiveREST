/*
 * Project ReactREST
 * Copyright (c) Alessio Saltarin 2022
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.ReactRest.router.rest;

import net.littlelite.ReactRest.handler.PersonHandler;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Component
public class RouterRest
{
    @Bean
    public RouterFunction<ServerResponse> root(@NotNull PersonHandler personHandler)
    {
        return RouterFunctions.route()
                .GET("/api/persons/{id}", accept(MediaType.TEXT_PLAIN), personHandler::getPersonById)
                .GET("/api/persons/age/{age}", accept(MediaType.TEXT_PLAIN), personHandler::getPersonsByAge)
                .GET("/api/persons", accept(MediaType.TEXT_PLAIN), personHandler::getAllPersons)
                .build();
    }
}