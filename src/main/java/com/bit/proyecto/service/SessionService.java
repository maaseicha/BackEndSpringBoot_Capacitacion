package com.bit.proyecto.service;

import com.bit.proyecto.configuration.JwtTokenUtil;
import com.bit.proyecto.configuration.exception.NotFoundException;
import com.bit.proyecto.configuration.exception.ServerErrorException;
import com.bit.proyecto.configuration.exception.UnauthorizedException;
import com.bit.proyecto.dao.PersonRepository;
import com.bit.proyecto.dto.LoginDTO;
import com.bit.proyecto.dto.PersonDTO;
import com.bit.proyecto.model.Person;
import com.bit.proyecto.rest.Login;
import com.bit.proyecto.rest.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class SessionService {
    private static final Logger logger = LogManager.getLogger(PersonService.class);
    private Response response;
    private final ObjectMapper maper = new ObjectMapper();

    @Autowired
    private PersonRepository personRepository;

    public Response validationLogin(Login login) {
        PersonDTO personDTO = new PersonDTO();
        try{
            String email = login.getUsername();
            String password = login.getPass();
            if (personRepository.existByLogin(email,password) > 0) {
                Person person = personRepository.findByPerEmail(email);
                personDTO = new PersonDTO(person.getPerCode(),person.getPerCedula(),person.getPerName(),person.getPerLastName(),person.getPerAddress(),person.getPerGender(),person.getPerEmail(),person.getPerPassword());
                response = new Response("Login successfully!", "200", true, new Date(),getSessionToExpose(personDTO));
                String json = maper.writeValueAsString(response);
                logger.info("Respuesta {}", () -> json);
                return response;
            }
            throw new UnauthorizedException("Usted no cuenta con los permisos necesarios");
        } catch (ServerErrorException e) {
            throw new ServerErrorException("Error interno");
        } catch (JsonProcessingException e) {
            throw new ServerErrorException("Error al procesar el json");
        }
    }

    public LoginDTO getSessionToExpose(PersonDTO personDTO) {
        JwtTokenUtil getToken = new JwtTokenUtil();
        String token = getToken.getJWTToken(personDTO.getEmail());

        LoginDTO session = new LoginDTO(personDTO.getCode(), token, personDTO.getEmail());
        return session;
    }
}
