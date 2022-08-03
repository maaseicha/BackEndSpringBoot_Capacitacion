/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bit.proyecto.dto;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author james
 */
public class PersonDTO {
    private Integer code;
    private String cedula;
    private String name;
    private String lastName;
    private String address;
    private String gender;
    private String email;
    private String password;
    private Object orders;

    public PersonDTO() {
    }

    public PersonDTO(Integer code) {
        this.code = code;
    }

    public PersonDTO(Integer code, String cedula, String name) {
        this.code = code;
        this.cedula = cedula;
        this.name = name;
    }

    public PersonDTO(Integer code, String cedula, String name, String lastName, String address, String gender, String email, Object orders) {
        this.code = code;
        this.cedula = cedula;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.email = email;
        this.orders = orders;
    }

    public PersonDTO(Integer code, String cedula, String name, String lastName, String address, String gender, String email, String password) {
        this.code = code;
        this.cedula = cedula;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.email = email;
        this.password = password;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getOrders() {
        return orders;
    }

    public void setOrders(Object orders) {
        this.orders = orders;
    }
}
