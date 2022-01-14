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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.noContent;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;


@SuppressWarnings("ClassCanBeRecord")
@Component
@RequiredArgsConstructor
public class PersonHandler
{
    private final PersonRepository personRepository;
    protected static final Logger logger = LoggerFactory.getLogger(PersonHandler.class);

    public @NotNull Mono<ServerResponse> getPersonById(@NotNull ServerRequest request)
    {
        var id = Long.parseLong(request.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(this.personRepository.findById(id), Person.class)
                .switchIfEmpty(notFound().build());
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

    public @NotNull Mono<ServerResponse> createPerson(@NotNull ServerRequest request)
    {
        return request.bodyToMono(Person.class)
                .flatMap(this.personRepository::save)
                .doOnSuccess( person -> logger.info("Person saved with id: " + person.getId()))
                .doOnError(e -> logger.error("Error in save person method", e))
                .map( savedPerson -> UriComponentsBuilder
                        .fromPath(("/api/persons/{id}"))
                        .buildAndExpand(savedPerson.getId())
                        .toUri())
                .flatMap( uri -> ServerResponse.created(uri).build());
    }

    public @NotNull Mono<ServerResponse> deletePerson(@NotNull ServerRequest request)
    {
        final long id = Long.parseLong(request.pathVariable("id"));
        return this.personRepository
                .findById(id)
                .flatMap(p -> noContent().build(this.personRepository.delete(p)))
                .switchIfEmpty(notFound().build());
    }
}
