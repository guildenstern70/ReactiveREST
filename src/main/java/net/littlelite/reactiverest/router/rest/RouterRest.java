/*
 * Project ReactREST
 * Copyright (c) Alessio Saltarin 2022
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.reactiverest.router.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import net.littlelite.reactiverest.handler.CommonHandler;
import net.littlelite.reactiverest.handler.PersonHandler;
import net.littlelite.reactiverest.model.Person;
import org.jetbrains.annotations.NotNull;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Component
public class RouterRest
{
    @Bean
    @RouterOperations({
            @RouterOperation(
                    method = RequestMethod.GET,
                    path = "/api/persons/{id}",
                    beanClass = PersonHandler.class,
                    beanMethod = "getPersonById",
                    operation = @Operation(
                            operationId = "getPersonById",
                            responses =
                            {
                                    @ApiResponse(responseCode = "200", description = "Person Found", content = @Content(schema = @Schema(implementation = Person.class))),
                                    @ApiResponse(responseCode = "404", description = "Person not found.")
                            },
                            parameters = @Parameter(
                                    name = "id",
                                    in = ParameterIn.PATH,
                                    style = ParameterStyle.SIMPLE,
                                    required = true,
                                    description = "ID of person to be found"
                            )
                    )),
            @RouterOperation(
                    method = RequestMethod.GET,
                    path = "/api/persons/age/{age}",
                    beanClass = PersonHandler.class,
                    beanMethod = "getPersonsByAge",
                    operation = @Operation(
                            operationId = "getPersonsByAge",
                            responses =
                                    {
                                            @ApiResponse(responseCode = "200", description = "Persons Found", content = @Content(schema = @Schema(implementation = Person.class)))
                                    },
                            parameters = @Parameter(
                                    name = "age",
                                    in = ParameterIn.PATH,
                                    style = ParameterStyle.SIMPLE,
                                    required = true,
                                    description = "Find all persons with age greater or equal than"
                            )
                    )),
            @RouterOperation(
                    method = RequestMethod.GET,
                    path = "/api/persons",
                    beanClass = PersonHandler.class,
                    beanMethod = "getAllPersons"),
            @RouterOperation(
                    method = RequestMethod.POST,
                    path = "/api/persons",
                    beanClass = PersonHandler.class,
                    beanMethod = "createPerson",
                    operation = @Operation(
                            operationId = "insertPerson",
                            responses = {
                                @ApiResponse(responseCode = "201", description = "Person Inserted", content = @Content(schema = @Schema(implementation = Person.class))),
                                @ApiResponse(responseCode = "400", description = "Invalid Person details supplied")
                            },
                            requestBody =
                                @RequestBody(content = @Content(schema = @Schema(implementation = Person.class)))
                    )),
            @RouterOperation(
                    method = RequestMethod.DELETE,
                    path = "/api/persons",
                    beanClass = PersonHandler.class,
                    beanMethod = "deletePerson"),
            @RouterOperation(
                    method = RequestMethod.GET,
                    path = "/api/common/version",
                    beanClass = CommonHandler.class,
                    beanMethod = "getVersion"),
    })
    public RouterFunction<ServerResponse> root(
            @NotNull PersonHandler personHandler,
            @NotNull CommonHandler commonHandler)
    {
        return RouterFunctions
                .route(GET(      "/api/persons/{id}").and(accept(MediaType.APPLICATION_JSON)), personHandler::getPersonById)
                .andRoute(GET(   "/api/persons/age/{age}").and(accept(MediaType.APPLICATION_JSON)), personHandler::getPersonsByAge)
                .andRoute(GET(   "/api/persons").and(accept(MediaType.APPLICATION_JSON)), personHandler::getAllPersons)
                .andRoute(POST(  "/api/persons").and(accept(MediaType.APPLICATION_JSON)).and(contentType(MediaType.APPLICATION_JSON)), personHandler::createPerson)
                .andRoute(DELETE("/api/persons/{id}"), personHandler::deletePerson)
                .andRoute(GET(   "/api/common/version"), commonHandler::getVersion);

    }
}
