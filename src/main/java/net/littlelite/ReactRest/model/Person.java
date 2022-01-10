/*
 * Project ReactREST
 * Copyright (c) Alessio Saltarin 2022
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.ReactRest.model;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public record Person(@Id Long id, String name, String surname, int age)
{
    boolean hasId()
    {
        return id != null;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Person person))
        {
            return false;
        }
        return Objects.equals(name, person.name) && Objects.equals(surname, person.surname);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, surname);
    }
}