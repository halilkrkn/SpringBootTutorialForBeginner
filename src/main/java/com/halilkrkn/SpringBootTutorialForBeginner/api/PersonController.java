package com.halilkrkn.SpringBootTutorialForBeginner.api;

import com.halilkrkn.SpringBootTutorialForBeginner.model.Person;
import com.halilkrkn.SpringBootTutorialForBeginner.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class PersonController {

    // PersonService class'ı çağrılıyor.
    private final PersonService personService;

    // Burada @Autowired ile hangi service kullanılacaksa onu belirtiyoruz.
    // @Autowired ile @Service ile belirttiğimiz service kullanılıyor.
    // PersonService class'ı @Service ile belirtildiği için burada @Autowired ile çağrılıyor.
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    // EKLEME İŞLEMİ
    // PersonService class'ındaki addPerson() methodu çağrılıyor.
    // @RequestBody ile json formatında veri alınıyor.
    // @PostMapping("/addPerson") ile ekleme yapılıyor.
    // Örnek url: http://localhost:8080/api/v1/addPerson
    // Örnek json: {"name":"Halil"}
    @PostMapping("/addPerson")
    public int addPerson(@Valid @NonNull @RequestBody Person person) {
        return personService.addPerson(person);
    }

    // LİSTELEME İŞLEMİ
    // PersonService class'ındaki getAllPeople() methodu çağrılıyor.
    // @GetMapping("/getAllPeople") ile liteleme yapılıyor.
    // Örnek url: http://localhost:8080/api/v1/getAllPerson
    @GetMapping("/getAllPerson")
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    // SEÇME İŞLEMİ
    // PersonService class'ındaki getPersonById() methodu çağrılıyor.
    // @PathVariable("id") ile id parametresi alınıyor.
    // @GetMapping("/getPersonById/{id}") ile url'de id parametresi alınıyor ve ilgi id'deki kişi seçiliyor.
    // Örnek url: http://localhost:8080/api/v1/getPersonById/1
    @GetMapping("/getPersonById/{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id)
                .orElse(null);
    }

    // SİLME İŞLEMİ
    // PersonService class'ındaki deletePerson() methodu çağrılıyor.
    // @PathVariable("id") ile id parametresi alınıyor.
    // @DeleteMapping("/deletePerson/{id}") ile silme yapılıyor.
    // Örnek url: http://localhost:8080/api/v1/deletePerson/1
    @DeleteMapping("/deletePerson/{id}")
    public int deletePerson(@PathVariable("id") UUID id){
        return personService.deletePerson(id);
    }

    // GÜNCELLEME İŞLEMİ
    // PersonService class'ındaki updatePerson() methodu çağrılıyor.
    // @PathVariable("id") ile id parametresi alınıyor.
    // @RequestBody ile json formatında veri alınıyor.
    // @PutMapping("/updatePerson/{id}") ile güncelleme yapılıyor.
    // Örnek url: http://localhost:8080/api/v1/updatePerson/1
    @PutMapping("/updatePerson/{id}")
    public int updatePerson(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person personToUpdate){
        return personService.updatePerson(id, personToUpdate);
    }


}
