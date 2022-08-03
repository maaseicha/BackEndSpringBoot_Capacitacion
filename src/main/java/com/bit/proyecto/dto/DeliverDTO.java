/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bit.proyecto.dto;

import java.util.Date;

/**
 *
 * @author james
 */
public class DeliverDTO {
    private Integer code;
    private String description;
    private String receive;
    private String comment;
    private Date sendDate;
    private Date receiveDate;
    private String status;

    public DeliverDTO() {
    }

    public DeliverDTO(Integer code) {
        this.code = code;
    }

    public DeliverDTO(Integer code, String status) {
        this.code = code;
        this.status = status;
    }

    public DeliverDTO(Integer code, String description, String receive, String comment, Date sendDate, Date receiveDate, String status) {
        this.code = code;
        this.description = description;
        this.receive = receive;
        this.comment = comment;
        this.sendDate = sendDate;
        this.receiveDate = receiveDate;
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
