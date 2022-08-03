/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bit.proyecto.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author james
 */
@Entity
@Table(name = "ENTREGA")
public class Deliver implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ENT_CODIGO")
    private Integer entCode;
    @Column(name = "ENT_DESCRIPCION")
    private String entDescription;
    @Column(name = "ENT_RECIBE")
    private String entReceive;
    @Column(name = "ENT_OBSERVACION")
    private String entComment;
    @Column(name = "ENT_FECHA_ENVIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entSendDate;
    @Column(name = "ENT_FECHA_RECIBE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entReceiveDate;
    @Basic(optional = false)
    @Column(name = "ENT_ESTADO")
    private String entStatus;
    @JoinColumn(name = "PED_CODIGO", referencedColumnName = "PED_CODIGO")
    @ManyToOne(optional = false)
    private Order pedCode;

    public Deliver() {
    }

    public Deliver(Integer entCode) {
        this.entCode = entCode;
    }

    public Deliver(String entDescription, String entReceive, String entComment, Date entSendDate, Date entReceiveDate, String entStatus, Order pedCode) {
        this.entDescription = entDescription;
        this.entReceive = entReceive;
        this.entComment = entComment;
        this.entSendDate = entSendDate;
        this.entReceiveDate = entReceiveDate;
        this.entStatus = entStatus;
        this.pedCode = pedCode;
    }

    public Integer getEntCode() {
        return entCode;
    }

    public void setEntCode(Integer entCode) {
        this.entCode = entCode;
    }

    public String getEntDescription() {
        return entDescription;
    }

    public void setEntDescription(String entDescription) {
        this.entDescription = entDescription;
    }

    public String getEntReceive() {
        return entReceive;
    }

    public void setEntReceive(String entReceive) {
        this.entReceive = entReceive;
    }

    public String getEntComment() {
        return entComment;
    }

    public void setEntComment(String entComment) {
        this.entComment = entComment;
    }

    public Date getEntSendDate() {
        return entSendDate;
    }

    public void setEntSendDate(Date entSendDate) {
        this.entSendDate = entSendDate;
    }

    public Date getEntReceiveDate() {
        return entReceiveDate;
    }

    public void setEntReceiveDate(Date entReceiveDate) {
        this.entReceiveDate = entReceiveDate;
    }

    public String getEntStatus() {
        return entStatus;
    }

    public void setEntStatus(String entStatus) {
        this.entStatus = entStatus;
    }

    public Order getPedCode() {
        return pedCode;
    }

    public void setPedCode(Order pedCode) {
        this.pedCode = pedCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entCode != null ? entCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deliver)) {
            return false;
        }
        Deliver other = (Deliver) object;
        if ((this.entCode == null && other.entCode != null) || (this.entCode != null && !this.entCode.equals(other.entCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bit.proyecto.modelo.Entrega[ entCode=" + entCode + " ]";
    }
    
}
