/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bit.proyecto.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author maseicha
 */
@Entity
@Table(name = "PERSONA")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PER_CODIGO")
    private Integer perCode;
    @Basic(optional = false)
    @Column(name = "PER_IDENTIFICACION")
    private String perCedula;
    @Basic(optional = false)
    @Column(name = "PER_NOMBRE")
    private String perName;
    @Basic(optional = false)
    @Column(name = "PER_APELLIDO")
    private String perLastName;
    @Column(name = "PER_DIRECCION")
    private String perAddress;
    @Column(name = "PER_TALLA")
    private String perSize;
    @Column(name = "PER_FECHA_NACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date perBirthDate;
    @Column(name = "PER_GENERO")
    private String perGender;
    @Column(name = "PER_EMAIL")
    private String perEmail;
    @Column(name = "PER_PASSWORD")
    private String perPassword;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perCode")
    private Collection<Order> orderCollection;

    public Person() {
    }

    public Person(Integer perCode) {
        this.perCode = perCode;
    }

    public Person(Integer perCode, String perCedula, String perName, String perLastName, String perAddress, String perSize, Date perBirthDate, String perGender, String perEmail) {
        this.perCode = perCode;
        this.perCedula = perCedula;
        this.perName = perName;
        this.perLastName = perLastName;
        this.perAddress = perAddress;
        this.perSize = perSize;
        this.perBirthDate = perBirthDate;
        this.perGender = perGender;
        this.perEmail = perEmail;
    }

    public Person(String perCedula, String perName, String perLastName, String perAddress, String perSize, Date perBirthDate, String perGender, String perEmail) {
        this.perCedula = perCedula;
        this.perName = perName;
        this.perLastName = perLastName;
        this.perAddress = perAddress;
        this.perSize = perSize;
        this.perBirthDate = perBirthDate;
        this.perGender = perGender;
        this.perEmail = perEmail;
    }

    public Person(String perCedula, String perName, String perLastName, String perAddress, String perSize, Date perBirthDate, String perGender, String perEmail, String perPassword) {
        this.perCedula = perCedula;
        this.perName = perName;
        this.perLastName = perLastName;
        this.perAddress = perAddress;
        this.perSize = perSize;
        this.perBirthDate = perBirthDate;
        this.perGender = perGender;
        this.perEmail = perEmail;
        this.perPassword = perPassword;
    }

    public Integer getPerCode() {
        return perCode;
    }

    public void setPerCode(Integer perCode) {
        this.perCode = perCode;
    }

    public String getPerCedula() {
        return perCedula;
    }

    public void setPerCedula(String perCedula) {
        this.perCedula = perCedula;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }

    public String getPerLastName() {
        return perLastName;
    }

    public void setPerLastName(String perLastName) {
        this.perLastName = perLastName;
    }

    public String getPerAddress() {
        return perAddress;
    }

    public void setPerAddress(String perAddress) {
        this.perAddress = perAddress;
    }

    public String getPerSize() {
        return perSize;
    }

    public void setPerSize(String perSize) {
        this.perSize = perSize;
    }

    public Date getPerBirthDate() {
        return perBirthDate;
    }

    public void setPerBirthDate(Date perBirthDate) {
        this.perBirthDate = perBirthDate;
    }

    public String getPerGender() {
        return perGender;
    }

    public void setPerGender(String perGender) {
        this.perGender = perGender;
    }

    public String getPerEmail() {
        return perEmail;
    }

    public void setPerEmail(String perEmail) {
        this.perEmail = perEmail;
    }

    public String getPerPassword() {
        return perPassword;
    }

    public void setPerPassword(String perPassword) {
        this.perPassword = perPassword;
    }

    public Collection<Order> getOrderCollection() {
        return orderCollection;
    }

    public void setOrderCollection(Collection<Order> orderCollection) {
        this.orderCollection = orderCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perCode != null ? perCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.perCode == null && other.perCode != null) || (this.perCode != null && !this.perCode.equals(other.perCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bit.proyecto.modelo.Persona[ perCode=" + perCode + " ]";
    }
    
}
