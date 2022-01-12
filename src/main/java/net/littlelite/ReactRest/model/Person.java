/*
 * Project ReactREST
 * Copyright (c) Alessio Saltarin 2022
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.ReactRest.model;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person
{
    @Id
    Long id;
    @NonNull
    String name;
    @NonNull
    String surname;
    int age;

    public Person(@NotNull String name, @NotNull String surname, int age)
    {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
}


