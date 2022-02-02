package ee.bcs.bank.service;


import ee.bcs.bank.dto.PersonDto;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    public PersonDto createPerson(String firstName, String lastName) {
        PersonDto person = new PersonDto();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        return person;

    }

}
