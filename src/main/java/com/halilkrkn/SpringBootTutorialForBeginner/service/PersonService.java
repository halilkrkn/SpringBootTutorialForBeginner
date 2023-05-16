package com.halilkrkn.SpringBootTutorialForBeginner.service;

import com.halilkrkn.SpringBootTutorialForBeginner.dao.PersonDao;
import com.halilkrkn.SpringBootTutorialForBeginner.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDao personDao;


    // Burada @Qualifier("fakeDao") ile hangi dao kullanılacaksa onu belirtiyoruz.
    // @Qualifier("fakeDao") ile @Repository("fakeDao") ile belirttiğimiz dao kullanılıyor.
    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    // PersonDao interface'indeki addPerson() methodu çağrılıyor.
    public int addPerson(Person person){
        return personDao.addPerson(person);
    }

    // PersonDao interface'indeki selectAllPeople() methodu çağrılıyor.
    public List<Person> getAllPeople(){
        return personDao.getAllPeople();
    }

    // PersonDao interface'indeki selectPersonById() methodu çağrılıyor.
    public Optional<Person> getPersonById(UUID id){
        return personDao.selectPersonById(id);
    }

    // PersonDao interface'indeki deletePersonById() methodu çağrılıyor.
    public int deletePerson(UUID id){
        return personDao.deletePersonById(id);
    }

    // personDao interface'indeki updatePersonById() methodu çağrılıyor.
    public int updatePerson(UUID id, Person newPerson){
        return personDao.updatePersonById(id, newPerson);
    }
}
