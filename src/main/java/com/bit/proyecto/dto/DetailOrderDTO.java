/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bit.proyecto.dto;

/**
 *
 * @author james
 */

public class DetailOrderDTO {

    private Integer code;
    private Integer amount;
    private Double price;
    private Double discount;
    private Double iva;
    private Double subtotal;
    private String comment;

    public DetailOrderDTO() {
    }

    public DetailOrderDTO(Integer code, Integer amount, Double price, Double discount, Double iva, Double subtotal, String comment) {
        this.code = code;
        this.amount = amount;
        this.price = price;
        this.discount = discount;
        this.iva = iva;
        this.subtotal = subtotal;
        this.comment = comment;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
