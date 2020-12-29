package com.abr.cnnect.api;

import com.abr.cnnect.model.Person;
import com.abr.cnnect.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/clients")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public int addPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAll() {
        return personService.getAll();
    }
    @GetMapping(path = "/{id}")
    public Person selectPerson(@PathVariable("id") UUID id){
        return personService.selectPerson(id)
                .orElse(null);
    }

    @GetMapping(path = "/d/{id}")
    public boolean deletePerson(@PathVariable("id") UUID id) {
        return personService.deletePerson(id);
    }

    @PutMapping(path = "/{id}")
    public boolean updatePersonEmail(@PathVariable("id") UUID id,@RequestBody Person person){
        return personService.updatePersonEmail(id,person);
    }
}
