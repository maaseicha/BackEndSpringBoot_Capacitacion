package com.bit.proyecto.controller;

import com.bit.proyecto.configuration.TokenLogin;
import com.bit.proyecto.rest.Login;
import com.bit.proyecto.rest.Response;
import com.bit.proyecto.service.SessionService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    SessionService sessionService;

    @PostMapping("login")
    public ResponseEntity<Response> getLogin(@RequestBody Login login){
        return ResponseEntity.status(HttpStatus.OK).body(sessionService.validationLogin(login));
    }

}
