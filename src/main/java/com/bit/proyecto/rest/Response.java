package com.bit.proyecto.rest;

import java.util.Date;

public class Response {
    private String message;
    private String code;
    private Boolean status;
    private Date dateTime;
    private Object data;
    public Response(){}
    public Response(String message, String code, Boolean status, Date dateTime) {
        this.message = message;
        this.code = code;
        this.status = status;
        this.dateTime = dateTime;
    }

    public Response(String message, String code, Boolean status, Object data) {
        this.message = message;
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public Response(String message, String code, Boolean status, Date dateTime, Object data) {
        this.message = message;
        this.code = code;
        this.status = status;
        this.dateTime = dateTime;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
