package com.abr.cnnect;

import com.abr.cnnect.api.PersonController;
import com.abr.cnnect.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class CnnectApplicationTests {

	@Autowired
	PersonController personController;

	@Test
	void contextLoads() {
		List<Person> personList;
		Person person;

		assertTrue(personController.addPerson(new Person("Szymon", "Abramski", "abr@gmail.com")));

		assertFalse(personController.addPerson(new Person("Jan", "Kowalski", "kow@ gmail.com")));

		assertTrue(personController.addPerson(new Person("Jan", "Kowalski", "kow@gmail.com")));

		personList = personController.getAll();

		assertEquals(personList.size(),2);
		assertEquals(personList.get(0).getName(),"Szymon");
		assertEquals(personList.get(1).getName(),"Jan");

		assertTrue(personController.addPerson(new Person("Man", "Dingo", "man@gmail.com")));

		personList = personController.getAll();

		assertEquals(personList.size(),3);
		assertEquals(personList.get(0).getName(),"Szymon");
		assertEquals(personList.get(1).getName(),"Man");
		assertEquals(personList.get(2).getName(),"Jan");

		assertTrue(personController.addPerson(new Person("Karol", "Abakanowicz", "aba@gmail.com")));

		personList = personController.getAll();

		assertEquals(personList.size(),4);
		assertEquals(personList.get(0).getName(),"Karol");

		assertEquals(personController.selectPerson(personList.get(0).getId()).getName(), "Karol");

		assertFalse(personController.updatePersonEmail(personList.get(0).getId(), new Person("Karol", "Abakanowicz", "kabak @gma il.com")));
		assertTrue(personController.updatePersonEmail(personList.get(0).getId(), new Person("Karol", "Abakanowicz", "kabak@gmail.com")));

		personList = personController.getAll();

		assertEquals(personController.selectPerson(personList.get(0).getId()).getEmail(), "kabak@gmail.com");

		assertFalse(personController.updatePersonEmail(UUID.randomUUID(), new Person("Karol", "Abakanowicz", "kabakaka@gmail.com")));

		assertTrue(personController.deletePerson(personList.get(0).getId()));
		personList = personController.getAll();
		assertEquals(personList.get(0).getName(),"Szymon");

		assertFalse(personController.deletePerson(UUID.randomUUID()));

		assertEquals(personController.selectPerson(personList.get(0).getId()).getName(), "Szymon");

	}

}
