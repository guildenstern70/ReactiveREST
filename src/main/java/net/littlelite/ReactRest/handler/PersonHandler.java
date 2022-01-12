/*
 * Project ReactREST
 * Copyright (c) Alessio Saltarin 2022
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.ReactRest.handler;

import lombok.RequiredArgsConstructor;
import net.littlelite.ReactRest.model.Person;
import net.littlelite.ReactRest.repository.PersonRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PersonHandler
{
    private final PersonRepository personRepository;

    public @NotNull Mono<ServerResponse> getPersonById(@NotNull ServerRequest request)
    {
        var id = Long.parseLong(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(this.personRepository.findById(id), Person.class);
    }

    public @NotNull Mono<ServerResponse> getPersonsByAge(@NotNull ServerRequest request)
    {
        var age = Integer.parseInt(request.pathVariable("age"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(this.personRepository.findPersonsWithAge(age), Person.class);
    }

    public @NotNull Mono<ServerResponse> getAllPersons(@NotNull ServerRequest request)
    {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(this.personRepository.findAll(), Person.class);
    }
}
