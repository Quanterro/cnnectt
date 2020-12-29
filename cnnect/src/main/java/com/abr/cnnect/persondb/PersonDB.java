package com.abr.cnnect.persondb;

import com.abr.cnnect.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDB {
    boolean addPerson(Person person);
    List<Person> getAll();
    Optional<Person> selectPerson(UUID id);
    boolean deletePerson(UUID id);
    boolean updatePersonEmail(UUID id, Person person);
}
