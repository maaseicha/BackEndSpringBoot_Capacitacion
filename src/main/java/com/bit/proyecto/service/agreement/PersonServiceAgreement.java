package com.bit.proyecto.service.agreement;

import com.bit.proyecto.model.Person;
import com.bit.proyecto.rest.Response;
import org.springframework.data.domain.Pageable;

public interface PersonServiceAgreement {
    public Response getPeople(Pageable pageable);
    public Response getPersonById(Integer id);
    public Response savePerson(Person person);
    public Response deletePerson(Integer id);
    public Response updatePerson(Integer id, Person person);
}
