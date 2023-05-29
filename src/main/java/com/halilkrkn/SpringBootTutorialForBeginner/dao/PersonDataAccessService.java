package com.halilkrkn.SpringBootTutorialForBeginner.dao;

import com.halilkrkn.SpringBootTutorialForBeginner.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// @Repository("postgresDao") ile hangi dao kullanılacaksa onu belirtiyoruz.
@Repository("postgresDao")
public class PersonDataAccessService implements PersonDao {

    @Override
    public int addPerson(UUID id, Person person) {
        return 0;
    }

    @Override
    public List<Person> getAllPeople() {
        return List.of(new Person(UUID.randomUUID(), "FROM POSTGRES DB"));
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return Optional.empty();
    }

    @Override
    public int deletePersonById(UUID id) {
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return 0;
    }
}
