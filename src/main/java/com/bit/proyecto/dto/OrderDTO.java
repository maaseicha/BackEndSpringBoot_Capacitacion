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

public class OrderDTO {

    private Integer code;
    private Date date;
    private String comment;
    private String status;
    private Double total;
    private String invoice;

    public OrderDTO() {
    }

    public OrderDTO(Integer code) {
        this.code = code;
    }

    public OrderDTO(Integer code, Date date, String comment, String status, Double total, String invoice) {
        this.code = code;
        this.date = date;
        this.comment = comment;
        this.status = status;
        this.total = total;
        this.invoice = invoice;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }
}
