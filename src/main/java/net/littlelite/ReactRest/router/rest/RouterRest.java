/*
 * Project ReactREST
 * Copyright (c) Alessio Saltarin 2022
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.ReactRest.router.rest;

import net.littlelite.ReactRest.handler.PersonHandler;
import org.jetbrains.annotations.NotNull;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Component
public class RouterRest
{
    @Bean
    @RouterOperations({ @RouterOperation(path = "/api/persons/{id}", beanClass = PersonHandler.class, beanMethod = "getPersonById"),
            @RouterOperation(path = "/api/persons/age/{age}", beanClass = PersonHandler.class, beanMethod = "getPersonsByAge"),
            @RouterOperation(path = "/api/persons", beanClass = PersonHandler.class, beanMethod = "getAllPersons") })
    public RouterFunction<ServerResponse> root(@NotNull PersonHandler personHandler)
    {
        return RouterFunctions
                .route(GET("/api/persons/{id}").and(accept(MediaType.APPLICATION_JSON)), personHandler::getPersonById)
                .andRoute(GET("/api/persons/age/{age}").and(accept(MediaType.APPLICATION_JSON)), personHandler::getPersonsByAge)
                .andRoute(GET("/api/persons").and(accept(MediaType.APPLICATION_JSON)), personHandler::getAllPersons);

    }

}