package com.bit.proyecto.configuration;

import com.bit.proyecto.configuration.exception.BadRequestException;
import com.bit.proyecto.configuration.exception.NotFoundException;
import com.bit.proyecto.configuration.exception.ServerErrorException;
import com.bit.proyecto.configuration.exception.UnauthorizedException;
import com.bit.proyecto.rest.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ExceptionConfig {
    private Response res = new Response();
    private static final Logger logger = LogManager.getLogger(ExceptionConfig.class);
    private final ObjectMapper maper = new ObjectMapper();

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException nfe) throws JsonProcessingException {
        res = new Response(nfe.getMessage(),"404",false,new Date());
        String json = maper.writeValueAsString(res);
        logger.error("Response: {}", () -> json);
        return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequestException(BadRequestException bre) throws JsonProcessingException {
        res = new Response(bre.getMessage(),"400",false,new Date());
        String json = maper.writeValueAsString(res);
        logger.error("Response: {}", () -> json);
        return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<?> serverErrorException(ServerErrorException see) throws JsonProcessingException {
        res = new Response(see.getMessage(),"500",false,new Date());
        String json = maper.writeValueAsString(res);
        logger.error("Response: {}", () -> json);
        return new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> unauthorizedException(UnauthorizedException ue) throws JsonProcessingException {
        res = new Response(ue.getMessage(),"401",false,new Date());
        String json = maper.writeValueAsString(res);
        logger.error("Response: {}", () -> json);
        return new ResponseEntity<>(res,HttpStatus.UNAUTHORIZED);
    }
}
