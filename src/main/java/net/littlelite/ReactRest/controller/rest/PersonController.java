/*
 * Project ReactREST
 * Copyright (c) Alessio Saltarin 2022
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.ReactRest.controller.rest;

import net.littlelite.ReactRest.model.Person;
import net.littlelite.ReactRest.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository)
    {
        this.personRepository = personRepository;
    }

    @GetMapping("/{id}")
    private Mono<ResponseEntity<Person>> getPersonById(@PathVariable Long id)
    {
        Mono<Person> person = this.personRepository.findById(id);
        return person.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @GetMapping("/age/{age}")
    public Flux<Person> getPersonsByAge(@PathVariable int age)
    {
        return this.personRepository.findPersonsWithAge(age);
    }

    @GetMapping
    private ResponseEntity<Flux<Person>> getAllPersons()
    {
        return ResponseEntity.ok(this.personRepository.findAll());
    }
}
 **/