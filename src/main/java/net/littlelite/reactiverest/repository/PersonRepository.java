/*
 * Project ReactREST
 * Copyright (c) Alessio Saltarin 2022
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.reactiverest.repository;

import net.littlelite.reactiverest.model.Person;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonRepository extends ReactiveCrudRepository<Person, Long>
{
    Flux<Person> findByName(String name);
    Flux<Person> findBySurname(String surname);

    @Modifying
    @Query("UPDATE person SET name = :name where surname = :surname")
    Mono<Integer> setFixedFirstnameFor(String name, String surname);

    @Query("SELECT * FROM person WHERE surname = :#{[0]}")
    Flux<Person> findByQueryWithExpression(String surname);

    @Query("SELECT * FROM person WHERE age >= :#{[0]}")
    Flux<Person> findPersonsWithAge(int age);

}
