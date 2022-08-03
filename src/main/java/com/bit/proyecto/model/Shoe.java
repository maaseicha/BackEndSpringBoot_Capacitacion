/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bit.proyecto.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author maseicha
 */
@Entity
@Table(name = "ZAPATO")
public class Shoe implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ZAP_CODIGO")
    private String zapCode;
    @Basic(optional = false)
    @Column(name = "ZAP_NOMBRE")
    private String zapName;
    @Basic(optional = false)
    @Column(name = "ZAP_MODELO")
    private String zapModel;
    @Basic(optional = false)
    @Column(name = "ZAP_GENERO")
    private String zapGender;
    @Basic(optional = false)
    @Column(name = "ZAP_COLOR")
    private String zapColor;
    @Basic(optional = false)
    @Column(name = "ZAP_TALLA")
    private String zapSize;
    @Basic(optional = false)
    @Column(name = "ZAP_STOCK")
    private Integer zapStock;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ZAP_PRECIO", scale = 2)
    private Double zapPrice;
    @Column(name = "ZAP_IMAGE")
    private String zapImage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zapCode")
    private Collection<DetailOrder> detailOrderCollection;

    public Shoe() {
    }

    public Shoe(String zapCode, String zapName, String zapModel, String zapGender, String zapColor, String zapSize, Integer zapStock, Double zapPrice) {
        this.zapCode = zapCode;
        this.zapName = zapName;
        this.zapModel = zapModel;
        this.zapGender = zapGender;
        this.zapColor = zapColor;
        this.zapSize = zapSize;
        this.zapStock = zapStock;
        this.zapPrice = zapPrice;
    }

    public Shoe(String zapCode, String zapName, String zapModel, String zapGender, String zapColor, String zapSize, Integer zapStock, Double zapPrice, String zapImage) {
        this.zapCode = zapCode;
        this.zapName = zapName;
        this.zapModel = zapModel;
        this.zapGender = zapGender;
        this.zapColor = zapColor;
        this.zapSize = zapSize;
        this.zapStock = zapStock;
        this.zapPrice = zapPrice;
        this.zapImage = zapImage;
    }

    public String getZapCode() {
        return zapCode;
    }

    public void setZapCode(String zapCode) {
        this.zapCode = zapCode;
    }

    public String getZapName() {
        return zapName;
    }

    public void setZapName(String zapName) {
        this.zapName = zapName;
    }

    public String getZapModel() {
        return zapModel;
    }

    public void setZapModel(String zapModel) {
        this.zapModel = zapModel;
    }

    public String getZapGender() {
        return zapGender;
    }

    public void setZapGender(String zapGender) {
        this.zapGender = zapGender;
    }

    public String getZapColor() {
        return zapColor;
    }

    public void setZapColor(String zapColor) {
        this.zapColor = zapColor;
    }

    public String getZapSize() {
        return zapSize;
    }

    public void setZapSize(String zapSize) {
        this.zapSize = zapSize;
    }

    public Integer getZapStock() {
        return zapStock;
    }

    public void setZapStock(Integer zapStock) {
        this.zapStock = zapStock;
    }

    public Double getZapPrice() {
        return zapPrice;
    }

    public void setZapPrice(Double zapPrice) {
        this.zapPrice = zapPrice;
    }

    public String getZapImage() {
        return zapImage;
    }

    public void setZapImage(String zapImage) {
        this.zapImage = zapImage;
    }

    public Collection<DetailOrder> getDetailOrderCollection() {
        return detailOrderCollection;
    }

    public void setDetailOrderCollection(Collection<DetailOrder> detailOrderCollection) {
        this.detailOrderCollection = detailOrderCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zapCode != null ? zapCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shoe)) {
            return false;
        }
        Shoe other = (Shoe) object;
        if ((this.zapCode == null && other.zapCode != null) || (this.zapCode != null && !this.zapCode.equals(other.zapCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bit.proyecto.modelo.Zapato[ zapCode=" + zapCode + " ]";
    }
}
