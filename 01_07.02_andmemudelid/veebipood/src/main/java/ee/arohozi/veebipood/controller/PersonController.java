package ee.arohozi.veebipood.controller;

import ee.arohozi.veebipood.entity.Person;
import ee.arohozi.veebipood.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @PostMapping("login")
    public boolean login(@RequestBody Person person) {
        if (person.getId() == null) {
            throw new RuntimeException("err_user_id_null");
        }
        if (person.getPassword() == null || person.getPassword().isBlank()) {
            throw new RuntimeException("err_password_missing");
        }
        Person dbPerson = personRepository.findById(person.getId()).orElseThrow();
        if (dbPerson.getPassword().equals(person.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("signup")
    public List<Person> signup(@RequestBody Person person) {
        if (person.getEmail() == null || person.getEmail().isBlank()) {
            throw new RuntimeException("err_email_missing");
        }
        if (person.getPassword() == null || person.getPassword().isBlank()) {
            throw new RuntimeException("err_password_missing");
        }
        personRepository.save(person);
        return personRepository.findAll();
    }
}