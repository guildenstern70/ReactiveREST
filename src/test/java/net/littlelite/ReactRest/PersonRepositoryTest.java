/*
 * Project ReactREST
 * Copyright (c) Alessio Saltarin 2022
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.ReactRest;

import net.littlelite.ReactRest.model.Person;
import net.littlelite.ReactRest.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PersonRepositoryTest
{
    private static final Duration TIMEOUT = Duration.ofSeconds(4);

    @Autowired
    private PersonRepository personRepository;

    @Test
    void findByFirstNameTest()
    {
        var person = this.personRepository.findByName("Joe")
                .blockFirst();
        assertThat(person).isNotNull();
        assertThat(person.getSurname()).isEqualTo("Doe");
    }

    @Test
    void findBySurnameTest()
    {
        var person = this.personRepository.findBySurname("Doe")
                .blockFirst();
        assertThat(person).isNotNull();
        assertThat(person.getName()).isEqualTo("Joe");
    }

    @Test
    void addNewPerson()
    {
        var person = new Person("Elena", "Zambrelli", 20);
        var savedPerson = this.personRepository.save(person).block();
        this.personRepository.findById(791279L).doOnNext(
                foundPerson -> {
                    assertThat(foundPerson).isNotNull();
                    assertThat(foundPerson.getSurname()).isEqualTo("Zambrelli");
                }).block(TIMEOUT);

    }
}
