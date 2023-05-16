package com.halilkrkn.SpringBootTutorialForBeginner.dao;

import com.halilkrkn.SpringBootTutorialForBeginner.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> DB = new ArrayList<>();


    // Ekleme
    @Override
    public int addPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }


    // Listeleme
    // Burada DB listesini dönüyoruz.
    // DB listesindeki her bir eleman Person tipinde olduğu için List<Person> tipinde bir dönüş değeri dönüyoruz.
    @Override
    public List<Person> getAllPeople() {
        return DB;
    }

    // Seçme
    // Burada DB.stream() ile DB listesindeki her bir elemanı tek tek dolaşıyoruz.
    // person -> person.getId().equals(id) ile DB listesindeki her bir elemanın id'si parametre olarak gelen id'ye eşit mi diye kontrol ediyoruz.
    // Eğer eşit ise .findFirst(); ile ilk bulduğumuz elemanı dönüyoruz.
    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id))
                .findFirst();
    }

    // Silme
    // Burada Optional<Person> personMaybe = selectPersonById(id); ile
    // selectPersonById() methodu çağrılıyor ve bu methodun dönüş değeri Optional<Person> tipinde bir değişkene atanıyor.
    // Eğer bu değişkenin değeri boş ise if (personMaybe.isEmpty()) ile kontrol ediliyor.
    // Eğer bu değişkenin değeri boş değil ise else bloğu çalışıyor ve DB.remove(personMaybe.get()); ile DB listesinden bu kişi siliniyor.
    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if (personMaybe.isEmpty()) {
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;
    }

    // Güncelleme
    // Burada selectPersonById(id) methodu çağrılıyor ve bu methodun dönüş değeri Optional<Person> tipinde bir değişkene atanıyor.
    // Eğer bu değişkenin değeri boş değil ise .map(personUpdate -> { ile DB listesindeki her bir elemanı tek tek dolaşıyoruz.
    // int indexOfPersonToUpdate = DB.indexOf(personUpdate); ile DB listesindeki her bir elemanın index'ini alıyoruz.
    // if (indexOfPersonToUpdate >= 0) ile eğer index 0'dan büyük veya eşit ise if bloğu çalışıyor.
    // DB.set(indexOfPersonToUpdate, new Person(id, person.getName())); ile DB listesindeki bu kişiyi güncelliyoruz.
    @Override
    public int updatePersonById(UUID id, Person person) {

        return selectPersonById(id).map(personUpdate -> {

            int indexOfPersonToUpdate = DB.indexOf(personUpdate);

            if (indexOfPersonToUpdate >= 0) {
                DB.set(indexOfPersonToUpdate, new Person(id, person.getName()));
                return 1;
            }
            return 0;
        }).orElse(-1);
    }
}
