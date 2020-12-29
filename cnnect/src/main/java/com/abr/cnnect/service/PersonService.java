package com.abr.cnnect.service;

import com.abr.cnnect.model.Person;
import com.abr.cnnect.persondb.PersonDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonDB personDB;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDB personDB) {
        this.personDB = personDB;
    }

    public int addPerson(Person person){
        if(!person.getEmail().contains(" "))
            return personDB.addPerson(person);
        else
            return 1;
    }

    public List<Person> getAll() {
        return personDB.getAll().stream()
                .sorted(Comparator.comparing(Person::getLastName))
                .collect(Collectors.toList());
    }

    public Optional<Person> selectPerson(UUID id){
        return personDB.selectPerson(id);
    }

    public boolean deletePerson(UUID id){
        return personDB.deletePerson(id);
    }

    public boolean updatePersonEmail(UUID id, Person person){
        if(!person.getEmail().contains(" "))
            return personDB.updatePersonEmail(id,person);
        else
            return false;

    }
}
