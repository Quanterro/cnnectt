package com.abr.cnnect.api;

import com.abr.cnnect.model.Person;
import com.abr.cnnect.persondb.PersonDB;
import com.abr.cnnect.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.util.Optional.ofNullable;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonControllerTest {

    private PersonController personController;
    private Person person;
    private Person person1;
    private Person person2;
    private Person person3;
    private PersonDB personDB = mock(PersonDB.class);

    private List<Person> personList;

    @BeforeEach
    public void init()
    {
        personController = new PersonController(new PersonService(personDB));
        person = new Person ("Jan", "Kowalski", "kow@gmail.com");
        person1 = new Person ("Szymon", "Abramski", "abr@gmail.com");
        person2 = new Person ("Jan", "Kowalski", "kow @gmail.com");
        personList = Arrays.asList(person,person1);
    }

    @Test
    void addPerson() {
        when(personDB.addPerson(any(Person.class))).thenReturn(true);
        assertTrue(personController.addPerson(person));
        assertTrue(personController.addPerson(person1));
        assertFalse(personController.addPerson(person2));
    }

    @Test
    void getAll() {
        when(personDB.getAll()).thenReturn(personList);
        assertEquals(personController.getAll().get(0).getName(),"Szymon");
        assertEquals(personController.getAll().get(1).getName(),"Jan");
    }

    @Test
    void selectPerson() {
        when(personDB.selectPerson(person.getId())).thenReturn(ofNullable(person));
        when(personDB.selectPerson(person2.getId())).thenReturn(ofNullable(null));
        assertEquals(personController.selectPerson(person.getId()).getName(),person.getName());
        assertNull(personController.selectPerson(person2.getId()));

    }

    @Test
    void deletePerson() {
        when(personDB.deletePerson(any(UUID.class))).thenReturn(true);
        assertTrue(personController.deletePerson(person.getId()));
        assertTrue(personController.deletePerson(person1.getId()));
    }

    @Test
    void updatePersonEmail() {
        when(personDB.updatePersonEmail(any(UUID.class),any(Person.class))).thenReturn(true);
        assertTrue(personController.updatePersonEmail(person.getId(),person));
        assertTrue(personController.updatePersonEmail(person1.getId(),person1));
        assertFalse(personController.updatePersonEmail(person2.getId(),person2));
    }
}