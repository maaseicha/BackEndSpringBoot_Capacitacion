/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bit.proyecto.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;

/**
 *
 * @author james
 */
@Entity
@Table(name = "DETALLE_PEDIDO")
public class DetailOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DEP_CODIGO")
    private Integer depCode;
    @Basic(optional = false)
    @Column(name = "DEP_CANTIDAD")
    private Integer depAmount;
    @Basic(optional = false)
    @Column(name = "DEP_PRECIO")
    private Double depPrice;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DEP_DESCUENTO")
    private Double depDiscount;
    @Column(name = "DEP_IVA")
    private Double depIva;
    @Column(name = "DEP_SUBTOTAL")
    private Double depSubtotal;
    @Column(name = "DEP_OBSERVACION")
    private String depComment;
    @JoinColumn(name = "ZAP_CODIGO", referencedColumnName = "ZAP_CODIGO")
    @ManyToOne(optional = false)
    private Shoe zapCode;
    @JoinColumn(name = "PED_CODIGO", referencedColumnName = "PED_CODIGO")
    @ManyToOne(optional = false)
    private Order pedCode;

    public DetailOrder() {
    }

    public DetailOrder(Integer depCode) {
        this.depCode = depCode;
    }

    public DetailOrder(Integer depAmount, Double depPrice, Double depDiscount, Double depIva, Double depSubtotal, String depComment, Shoe zapCode, Order pedCode) {
        this.depAmount = depAmount;
        this.depPrice = depPrice;
        this.depDiscount = depDiscount;
        this.depIva = depIva;
        this.depSubtotal = depSubtotal;
        this.depComment = depComment;
        this.zapCode = zapCode;
        this.pedCode = pedCode;
    }

    public Integer getDepCode() {
        return depCode;
    }

    public void setDepCode(Integer depCode) {
        this.depCode = depCode;
    }

    public Integer getDepAmount() {
        return depAmount;
    }

    public void setDepAmount(Integer depAmount) {
        this.depAmount = depAmount;
    }

    public Double getDepPrice() {
        return depPrice;
    }

    public void setDepPrice(Double depPrice) {
        this.depPrice = depPrice;
    }

    public Double getDepDiscount() {
        return depDiscount;
    }

    public void setDepDiscount(Double depDiscount) {
        this.depDiscount = depDiscount;
    }

    public Double getDepIva() {
        return depIva;
    }

    public void setDepIva(Double depIva) {
        this.depIva = depIva;
    }

    public Double getDepSubtotal() {
        return depSubtotal;
    }

    public void setDepSubtotal(Double depSubtotal) {
        this.depSubtotal = depSubtotal;
    }

    public String getDepComment() {
        return depComment;
    }

    public void setDepComment(String depComment) {
        this.depComment = depComment;
    }

    public Shoe getZapCode() {
        return zapCode;
    }

    public void setZapCode(Shoe zapCode) {
        this.zapCode = zapCode;
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
        hash += (depCode != null ? depCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailOrder)) {
            return false;
        }
        DetailOrder other = (DetailOrder) object;
        if ((this.depCode == null && other.depCode != null) || (this.depCode != null && !this.depCode.equals(other.depCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bit.proyecto.modelo.DetallePedido[ depCode=" + depCode + " ]";
    }
    
}
