package org.example;

import org.example.model.Person;

import java.util.List;
import java.util.stream.Collectors;

public class PeopleCleaner {

    public List<Person> getPeopleWithBooksOnly(List<Person> people) {
        return people.stream()
                .filter(person -> !person.getBooks().isEmpty())
                .collect(Collectors.toList());
    }
}