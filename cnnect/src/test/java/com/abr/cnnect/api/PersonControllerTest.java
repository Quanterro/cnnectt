package com.abr.cnnect.api;

import com.abr.cnnect.model.Person;
import com.abr.cnnect.persondb.PersonDB;
import com.abr.cnnect.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonControllerTest {

    private PersonController personController;
    private Person person;
    private Person person1;
    private Person person2;
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
		when(personDB.addPerson(person)).thenReturn(true);
        when(personDB.addPerson(person1)).thenReturn(true);
        when(personDB.addPerson(person2)).thenReturn(true);
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
        when(personDB.updatePersonEmail(person.getId(),person)).thenReturn(true);
        when(personDB.updatePersonEmail(person1.getId(),person1)).thenReturn(true);
        when(personDB.updatePersonEmail(person2.getId(),person2)).thenReturn(true);
        assertTrue(personController.updatePersonEmail(person.getId(),person));
        assertTrue(personController.updatePersonEmail(person1.getId(),person1));
        assertFalse(personController.updatePersonEmail(person2.getId(),person2));
    }

    @Test
    void deletePerson() {
        when(personDB.deletePerson(person.getId())).thenReturn(true);
        when(personDB.deletePerson(person1.getId())).thenReturn(false);
        assertTrue(personController.deletePerson(person.getId()));
        assertFalse(personController.deletePerson(person1.getId()));
    }

    @Test
    void updatePersonEmail() {
        when(personDB.updatePersonEmail(person.getId(),person)).thenReturn(true);
        when(personDB.updatePersonEmail(person1.getId(),person1)).thenReturn(true);
        when(personDB.updatePersonEmail(person2.getId(),person2)).thenReturn(true);
        assertTrue(personController.updatePersonEmail(person.getId(),person));
        assertTrue(personController.updatePersonEmail(person1.getId(),person1));
        assertFalse(personController.updatePersonEmail(person2.getId(),person2));
    }
}