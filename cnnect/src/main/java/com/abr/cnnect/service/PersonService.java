package com.abr.cnnect.service;

import com.abr.cnnect.model.Person;
import com.abr.cnnect.persondb.PersonDB;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDB personDB;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDB personDB) {
        this.personDB = personDB;
    }

    public int addPerson(Person person){
        return personDB.insertPerson(person);
    }

    public List<Person> getAll() {
        return personDB.getAll();
    }

    public Optional<Person> selectPerson(UUID id){
        return personDB.selectPerson(id);
    }

    public boolean deletePerson(UUID id){
        return personDB.deletePerson(id);
    }

    public boolean updatePersonEmail(UUID id, Person person){
        return personDB.updatePersonEmail(id,person);
    }
}
