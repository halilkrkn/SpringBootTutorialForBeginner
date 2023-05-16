package com.halilkrkn.SpringBootTutorialForBeginner.dao;

import com.halilkrkn.SpringBootTutorialForBeginner.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    // Ekleme İşlemi
    // Burada UUID id parametresi ile id'yi dışarıdan alıyoruz.
    // Person person parametresi ile de Person objesini dışarıdan alıyoruz.
    // Default olarak addPerson() methodu çağrıldığında UUID id = UUID.randomUUID(); ile
    // rastgele bir id oluşturuluyor.
    // Bu methodu çağıran yerde addPerson() methodu çağrılırken UUID id parametresi verilmezse
    // default olarak UUID id = UUID.randomUUID(); ile rastgele bir id oluşturuluyor.
    // Burada DB.add(new Person(id, person.getName())); ile DB listesine yeni bir Person objesi ekleniyor.
    // Burada DB listesine eklenen Person objesinin id'si UUID id ile oluşturulan id oluyor.
    // Burada DB listesine eklenen Person objesinin name'i ise dışarıdan alınan person.getName() oluyor.
    int addPerson(UUID id, Person person);

    default int addPerson(Person person){
        UUID id = UUID.randomUUID();
        return addPerson(id, person);
    }

    // Listeleme İşlemi
    // Burada DB listesini dönüyoruz.
    List<Person> getAllPeople();

    // Seçme İşlemi
    // Burada DB listesindeki her bir elemanı tek tek dolaşıyoruz ve id verilen kişiyi seçiyoruz.
    Optional<Person> selectPersonById(UUID id);

    // Silme İşlemi
    // Burada DB listesindeki her bir elemanı tek tek dolaşıyoruz ve id'i verileen kişiyi siliyoruz.
    int deletePersonById(UUID id);


    // Güncelleme İşlemi
    // Burada DB listesindeki her bir elemanı tek tek dolaşıyoruz ve id'i verilen kişiyi güncelliyoruz.
    int updatePersonById(UUID id, Person person);

}
