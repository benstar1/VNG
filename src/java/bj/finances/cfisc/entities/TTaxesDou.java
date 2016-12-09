/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "T_TAXES_DOU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTaxesDou.findAll", query = "SELECT t FROM TTaxesDou t"),
    @NamedQuery(name = "TTaxesDou.findByRnk", query = "SELECT t FROM TTaxesDou t WHERE t.rnk = :rnk"),
    @NamedQuery(name = "TTaxesDou.findByTaxCod", query = "SELECT t FROM TTaxesDou t WHERE t.taxCod = :taxCod"),
    @NamedQuery(name = "TTaxesDou.findByTaxAmt", query = "SELECT t FROM TTaxesDou t WHERE t.taxAmt = :taxAmt")})
public class TTaxesDou implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RNK")
    private Long rnk;
    @Size(max = 3)
    @Column(name = "TAX_COD")
    private String taxCod;
    @Column(name = "TAX_AMT")
    private BigInteger taxAmt;

    public TTaxesDou() {
    }

    public TTaxesDou(Long rnk) {
        this.rnk = rnk;
    }

    public Long getRnk() {
        return rnk;
    }

    public void setRnk(Long rnk) {
        this.rnk = rnk;
    }

    public String getTaxCod() {
        return taxCod;
    }

    public void setTaxCod(String taxCod) {
        this.taxCod = taxCod;
    }

    public BigInteger getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(BigInteger taxAmt) {
        this.taxAmt = taxAmt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rnk != null ? rnk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTaxesDou)) {
            return false;
        }
        TTaxesDou other = (TTaxesDou) object;
        if ((this.rnk == null && other.rnk != null) || (this.rnk != null && !this.rnk.equals(other.rnk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TTaxesDou[ rnk=" + rnk + " ]";
    }
    
}
