/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bit.proyecto.dto;

import java.util.Collection;

/**
 * @author maseicha
 */
public class ShoeDTO {
    private String code;
    private String name;
    private String model;
    private String gender;
    private String color;
    private String size;
    private Integer stock;
    private Double price;
    private String image;
    public ShoeDTO() {
    }

    public ShoeDTO(String code) {
        this.code = code;
    }

    public ShoeDTO(String code, String name, String model, String gender, String color, String size, Integer stock, Double price) {
        this.code = code;
        this.name = name;
        this.model = model;
        this.gender = gender;
        this.color = color;
        this.size = size;
        this.stock = stock;
        this.price = price;
    }

    public ShoeDTO(String code, String name, String model, String gender, String color, String size, Integer stock, Double price, String image) {
        this.code = code;
        this.name = name;
        this.model = model;
        this.gender = gender;
        this.color = color;
        this.size = size;
        this.stock = stock;
        this.price = price;
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
