/*
 * Project ReactREST
 * Copyright (c) Alessio Saltarin 2022
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.ReactRest;

import net.littlelite.ReactRest.model.Person;
import net.littlelite.ReactRest.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class WebClientTest
{
    private final Logger logger = LoggerFactory.getLogger(WebClientTest.class);
    private static final Duration TIMEOUT = Duration.ofSeconds(4);

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void getPersonByIdTest()
    {
        webTestClient
                .get().uri("/api/persons/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Person.class).value(person ->
                {
                    assertThat(person.getSurname()).isEqualTo("Doe");
                });
    }

    @Test
    public void getAllPersonsTest()
    {
        var personsOnDbCount = this.personRepository.count().block();
        assertThat(personsOnDbCount).isNotNull();
        webTestClient
                .get().uri("/api/persons")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Person.class)
                .hasSize(personsOnDbCount.intValue());
    }

    @Test
    public void getPersonsByAge()
    {
        var initialPersonCount = this.personRepository.count().block();

        var persons = Arrays.asList(
                new Person("Jack", "Bauer", 20),
                new Person("Chloe", "O'Brian", 18),
                new Person("Kim", "Rossi", 30),
                new Person("David", "Palmer", 45),
                new Person("Michelle", "Dessler", 56));

        this.personRepository.saveAll(persons).blockLast(TIMEOUT);

        final var personsWithAge45 =
                this.personRepository.findPersonsWithAge(45).collectList().block();
        assertThat(personsWithAge45).isNotNull();
        webTestClient
                .get().uri("/api/persons/age/45")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Person.class)
                .hasSize(personsWithAge45.size());

        // Clean up
        persons.stream()
                .map( person ->
                        this.personRepository.findBySurname( person.getSurname()) )
                .map( pFlux ->
                        pFlux.flatMap( person -> this.personRepository.deleteById( person.getId() )) )
                .forEach(Flux::subscribe);

        var finalPersonCount = this.personRepository.count().block();

        assertThat(finalPersonCount).isEqualTo(initialPersonCount);

    }
}
