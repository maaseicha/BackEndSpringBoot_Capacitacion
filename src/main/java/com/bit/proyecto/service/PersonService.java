package com.bit.proyecto.service;

import com.bit.proyecto.configuration.TokenLogin;
import com.bit.proyecto.configuration.exception.BadRequestException;
import com.bit.proyecto.configuration.exception.NotFoundException;
import com.bit.proyecto.configuration.exception.ServerErrorException;
import com.bit.proyecto.configuration.exception.UnauthorizedException;
import com.bit.proyecto.dao.OrderRepository;
import com.bit.proyecto.dao.PersonRepository;
import com.bit.proyecto.model.Order;
import com.bit.proyecto.model.Person;
import com.bit.proyecto.rest.Response;
import com.bit.proyecto.dto.OrderDTO;
import com.bit.proyecto.dto.PersonDTO;
import com.bit.proyecto.service.agreement.PersonServiceAgreement;
import com.bit.proyecto.service.logical.DataLogical;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService implements PersonServiceAgreement {
    private static final Logger logger = LogManager.getLogger(PersonService.class);
    private Response response;
    private final ObjectMapper maper = new ObjectMapper();
    @Autowired
    private DataLogical dataLogical;
    @Autowired
    PersonRepository repository;
    @Autowired
    TokenLogin tokens;
    @Autowired
    OrderRepository orderRepository;
    @Override
    public Response getPeople(Pageable pageable) {
        List<PersonDTO> personDTOS = new ArrayList<>();
        List<OrderDTO> orderDTOS = new ArrayList<>();
        try {
            for (Person person: repository.findAll(pageable).getContent()) {
                if (orderRepository.existByPerCodigo(person.getPerCode())>0)
                    for (Order order: orderRepository.findByPerCodigo(person.getPerCode())) {
                        orderDTOS.add(new OrderDTO(order.getPedCode()));
                    }
                personDTOS.add(new PersonDTO(person.getPerCode(),person.getPerCedula(),person.getPerName(),person.getPerLastName(),person.getPerAddress(),person.getPerGender(),person.getPerEmail(),orderDTOS));
                orderDTOS = new ArrayList<>();
            }
            response = new Response("Data found successfully", "200", true, new Date(),personDTOS);
            String json = maper.writeValueAsString(response);
            logger.info("Respuesta {}", () -> json);
            return response;
        } catch (ServerErrorException e) {
            throw new ServerErrorException("Error interno");
        } catch (JsonProcessingException e) {
            throw new ServerErrorException("Error al procesar el json");
        }
    }

    @Override
    public Response getPersonById(Integer id) {
        PersonDTO personDTO;
        Map map = new LinkedHashMap();
        List<OrderDTO> orderDTOS = new ArrayList<>();
        try {
            if (repository.existsById(id)) {
                Person person = repository.findById(id).get();
                map.put("code",person.getPerCode());
                map.put("cedula",person.getPerCedula());
                map.put("name",person.getPerName());
                map.put("last_name",person.getPerLastName());
                map.put("email",person.getPerEmail());
                for (Order order: orderRepository.findByPerCodigo(id)) {
                    orderDTOS.add(new OrderDTO(order.getPedCode(),order.getPedDate(),order.getPedComment(),order.getPedStatus(),order.getPedTotal(),order.getPedInvoice()));
                }
                map.put("orders",orderDTOS);
                response = new Response("Data found successfully", "200", true, new Date(),map);
                String json = maper.writeValueAsString(response);
                logger.info("Respuesta {}", () -> json);
                return response;
            }
            throw new NotFoundException("The person with the "+id+" does not exist.");
        } catch (ServerErrorException e) {
            throw new ServerErrorException("Error interno");
        } catch (JsonProcessingException e) {
            throw new ServerErrorException("Error al procesar el json");
        }
    }

    @Override
    public Response savePerson(Person person) {
        try {
//            if (tokens.exist(token)) {
                String validate = dataLogical.validateDataPerson(person);
                if (validate.equals("true")) {
                    if (person.getPerCedula() != null && person.getPerEmail() != null) {
                        if (repository.existByPerCedula(person.getPerCedula()) == 0){
                            if (repository.existByPerEmail(person.getPerEmail()) == 0) {
                                person.setPerCode(null);
                                repository.save(person);
                                response = new Response("Data saved successfully", "200", true, new Date(), person);
                                String json = maper.writeValueAsString(response);
                                logger.info("Respuesta {}", () -> json);
                                return response;
                            }
                            throw new BadRequestException("The email ("+person.getPerEmail()+") is already registered.");
                        }
                        throw new BadRequestException("The cedula ("+person.getPerCedula()+") is already registered.");
                    }
                    throw new BadRequestException("Please cedula and email is required.");
                }
                throw new BadRequestException(validate);
//            }
//            throw new UnauthorizedException("Please send a valid token in the Key(token) header");
        } catch (ServerErrorException e) {
            throw new ServerErrorException("Error interno");
        } catch (JsonProcessingException e) {
            throw new ServerErrorException("Error al procesar el json");
        }
    }

    @Override
    public Response deletePerson(Integer id) {
        return null;
    }

    @Override
    public Response updatePerson(Integer id, Person person) {
        return null;
    }
}
