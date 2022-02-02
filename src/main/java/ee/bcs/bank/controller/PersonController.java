package ee.bcs.bank.controller;

import ee.bcs.bank.dto.PersonDto;
import ee.bcs.bank.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PersonController {

    @Resource
    private PersonService personService;

    @GetMapping("/person")
    public PersonDto controllerCreatePerson(@RequestParam String firstName, @RequestParam String lastName) {
        PersonDto person = personService.createPerson(firstName, lastName);
        return person;
    }
}
