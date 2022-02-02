/*
 * Project ReactREST
 * Copyright (c) Alessio Saltarin 2022
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.reactiverest.service;

import net.littlelite.reactiverest.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.stereotype.Service;
import reactor.test.StepVerifier;

import static org.springframework.data.relational.core.query.Query.query;

@Service
public class DbInitializer implements ApplicationRunner
{
    protected static final Logger logger = LoggerFactory.getLogger(DbInitializer.class);

    private final R2dbcEntityTemplate template;

    @Autowired
    public DbInitializer(R2dbcEntityTemplate template)
    {
        this.template = template;
    }

    @Override
    public void run(ApplicationArguments args)
    {
        logger.info("Populating Database");
        this.template.getDatabaseClient().sql("CREATE TABLE IF NOT EXISTS person" +
                        "(id bigint auto_increment PRIMARY KEY," +
                        "name VARCHAR(255)," +
                        "surname VARCHAR(255)," +
                        "age INT)")
                .fetch()
                .rowsUpdated()
                .block();

        var personTableRows =
                this.template.count(query(Criteria.empty()),Person.class)
                        .block();

        if (personTableRows == null || personTableRows == 0)
        {
            logger.info("Person Table is empty. Populating it now.");

            this.template.insert(Person.class)
                    .using(new Person("Joe", "Doe", 34))
                    .block();

            this.template.select(Person.class)
                    .first()
                    .doOnNext(it -> logger.info(it.toString()))
                    .as(StepVerifier::create)
                    .expectNextCount(1)
                    .verifyComplete();
        }
        else
        {
            logger.info("Person Table already has " + personTableRows + " rows.");
        }

    }
}
