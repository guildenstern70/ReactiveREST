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
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration
public class PersonRepositoryTest
{
    private final Logger logger = LoggerFactory.getLogger(PersonRepositoryTest.class);

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
    void addNewPersonAndThenDeleteIt()
    {
        var person = new Person("Elena", "Zambrelli", 20);
        var countBefore = this.personRepository.count().block();
        assertThat(countBefore).isNotNull();
        logger.info("Before add => " + countBefore + " items.");
        var savedPerson = this.personRepository.save(person).block();
        assertThat(savedPerson).isNotNull();
        var countAfter = this.personRepository.count().block();
        assertThat(countAfter).isEqualTo(countBefore + 1);
        logger.info("Person saved with ID = " + savedPerson.getId());
        this.personRepository.deleteById(savedPerson.getId()).block();
        var countAfterDelete = this.personRepository.count().block();
        assertThat(countAfterDelete).isEqualTo(countBefore);
    }
}
