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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author james
 */
@Entity
@Table(name = "PEDIDO")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PED_CODIGO")
    private Integer pedCode;
    @Basic(optional = false)
    @Column(name = "PED_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pedDate;
    @Column(name = "PED_OBSERVACION")
    private String pedComment;
    @Column(name = "PED_ESTADO")
    private String pedStatus;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PED_TOTAL")
    private Double pedTotal;
    @Basic(optional = false)
    @Column(name = "PED_FACTURA")
    private String pedInvoice;
    @JoinColumn(name = "PER_CODIGO", referencedColumnName = "PER_CODIGO")
    @ManyToOne(optional = false)
    private Person perCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedCode")
    private Collection<DetailOrder> detailOrderCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedCode")
    private Collection<Deliver> deliverCollection;

    public Order() {
    }

    public Order(Integer pedCode) {
        this.pedCode = pedCode;
    }

    public Order(Date pedDate, String pedComment, String pedStatus, Double pedTotal, String pedInvoice, Person perCode) {
        this.pedDate = pedDate;
        this.pedComment = pedComment;
        this.pedStatus = pedStatus;
        this.pedTotal = pedTotal;
        this.pedInvoice = pedInvoice;
        this.perCode = perCode;
    }

    public Integer getPedCode() {
        return pedCode;
    }

    public void setPedCode(Integer pedCode) {
        this.pedCode = pedCode;
    }

    public Date getPedDate() {
        return pedDate;
    }

    public void setPedDate(Date pedDate) {
        this.pedDate = pedDate;
    }

    public String getPedComment() {
        return pedComment;
    }

    public void setPedComment(String pedComment) {
        this.pedComment = pedComment;
    }

    public String getPedStatus() {
        return pedStatus;
    }

    public void setPedStatus(String pedStatus) {
        this.pedStatus = pedStatus;
    }

    public Double getPedTotal() {
        return pedTotal;
    }

    public void setPedTotal(Double pedTotal) {
        this.pedTotal = pedTotal;
    }

    public String getPedInvoice() {
        return pedInvoice;
    }

    public void setPedInvoice(String pedInvoice) {
        this.pedInvoice = pedInvoice;
    }

    public Person getPerCode() {
        return perCode;
    }

    public void setPerCode(Person perCode) {
        this.perCode = perCode;
    }

    public Collection<DetailOrder> getDetailOrderCollection() {
        return detailOrderCollection;
    }

    public void setDetailOrderCollection(Collection<DetailOrder> detailOrderCollection) {
        this.detailOrderCollection = detailOrderCollection;
    }

    public Collection<Deliver> getDeliverCollection() {
        return deliverCollection;
    }

    public void setDeliverCollection(Collection<Deliver> deliverCollection) {
        this.deliverCollection = deliverCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedCode != null ? pedCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order)) {
            return false;
        }
        Order other = (Order) object;
        if ((this.pedCode == null && other.pedCode != null) || (this.pedCode != null && !this.pedCode.equals(other.pedCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bit.proyecto.modelo.Pedido[ pedCode=" + pedCode + " ]";
    }
    
}
