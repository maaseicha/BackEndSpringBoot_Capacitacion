package com.bit.proyecto.service.logical;

import com.bit.proyecto.helper.Helpers;
import com.bit.proyecto.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLogical {
    @Autowired
    private Helpers helpers;
    public String validateDataPerson(Person person){
        if (person.getPerCedula() == null)
            return "La cedula es requerida";
        if (person.getPerName() == null)
            return "El nombre es requerido";
        if (person.getPerLastName() == null)
            return "El apellido es requerido";
        if (!helpers.validarCedula(person.getPerCedula()))
            return "La cedula es incorrecta";
        return "true";
    }
}
