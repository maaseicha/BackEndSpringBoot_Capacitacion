package com.bit.proyecto;

import com.bit.proyecto.model.Person;
import com.bit.proyecto.rest.Response;
import com.bit.proyecto.service.PersonService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestPersonTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private PersonService personService;

    @Before
    public void init() {
        Map map = new LinkedHashMap();
        map.put("code",1);
        map.put("cedula","1805004908");
        map.put("name","Marco");
        map.put("last_name","Aseicha");
        map.put("email","maseicha@pichincha.com");
        map.put("orders",new ArrayList<>());
        Response response = new Response("Data found successfully","200",true,new Date(),map);
        Mockito.when(personService.getPersonById(1)).thenReturn(response);

        Person person = new Person("1805004908","Marco","Asicha","Pelileo","1.70",new Date(),"M","maseicha@pichincha.com");
        response = new Response("The cedula ("+person.getPerCedula()+") is already registered.","404",false,new Date());
        Mockito.when(personService.savePerson(person)).thenReturn(response);

        List<Person> personList = new ArrayList<>();
        personList.add(person);
        personList.add(person);
        response = new Response("The cedula ("+person.getPerCedula()+") is already registered.","404",false,new Date(),personList);
        Mockito.when(personService.getPeople(Pageable.ofSize(1))).thenReturn(response);
    }

    @Test
    public void getOrderByPerson() throws Exception {
        ResponseEntity<Response> response = restTemplate.getForEntity("/person?id=1", Response.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(response.getBody().getCode(),"200");
        Assert.assertTrue(response.getBody().getStatus());
    }

    @Test
    public void saveNewPerson() throws Exception{
        Person person = new Person("1805004908","Marco","Asicha","Pelileo","1.70",new Date(),"M","maseicha@pichincha.com");
        ResponseEntity<Response> response = restTemplate.withBasicAuth("user", "password").postForEntity("/people",person,Response.class);
        System.out.println(response);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void getPeople() throws Exception{
        ResponseEntity<Response> response = restTemplate.getForEntity("/people?page=0&size=2", Response.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
