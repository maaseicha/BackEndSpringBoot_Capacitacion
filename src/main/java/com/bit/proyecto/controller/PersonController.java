package com.bit.proyecto.controller;

import com.bit.proyecto.model.Person;
import com.bit.proyecto.rest.Response;
import com.bit.proyecto.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class PersonController {

    @Autowired
    PersonService personService;

//    @PostMapping("people")
//    public ResponseEntity<Response> savePerson(@RequestHeader(value = "token", defaultValue = "none") String token, @RequestBody Person person){
//        return ResponseEntity.status(HttpStatus.CREATED).body(personService.savePerson(token,person));
//    }
    @PostMapping("people")
    public ResponseEntity<Response> savePerson(@RequestBody Person person){
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.savePerson(person));
    }
    @GetMapping("people")
    public ResponseEntity<Response> getPeople(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(personService.getPeople(pageable));
    }

    @GetMapping("person")
    public ResponseEntity<Response> getPersonOrders(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(personService.getPersonById(id));
    }
}
