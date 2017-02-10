/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "T_TAXE_DECL_DOU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTaxeDeclDou.findAll", query = "SELECT t FROM TTaxeDeclDou t"),
    @NamedQuery(name = "TTaxeDeclDou.findSumTax2", query = "SELECT SUM(t.taxLinAmt) FROM TTaxeDeclDou t WHERE t.taxLinCod =:codTaxe AND t.tTaxeDeclDouPK.instanceid=:instanceId "),
        @NamedQuery(name = "TTaxeDeclDou.findSumTax", query = "SELECT t.tTaxeDeclDouPK.instanceid as instanceId, t.taxLinCod as taxLinCod, SUM(t.taxLinAmt) FROM TTaxeDeclDou t WHERE t.tTaxeDeclDouPK.instanceid IN :listeInstanceId GROUP BY instanceId,taxLinCod  "),
    @NamedQuery(name = "TTaxeDeclDou.findAllByArticle", query = "SELECT t FROM TTaxeDeclDou t WHERE t.tArticle =:tArticle"),
    @NamedQuery(name = "TTaxeDeclDou.findByInstanceid", query = "SELECT t FROM TTaxeDeclDou t WHERE t.tTaxeDeclDouPK.instanceid = :instanceid"),
    @NamedQuery(name = "TTaxeDeclDou.findByKeyItmNbr", query = "SELECT t FROM TTaxeDeclDou t WHERE t.tTaxeDeclDouPK.keyItmNbr = :keyItmNbr"),
    @NamedQuery(name = "TTaxeDeclDou.findByKeyTaxRnk", query = "SELECT t FROM TTaxeDeclDou t WHERE t.tTaxeDeclDouPK.keyTaxRnk = :keyTaxRnk"),
    @NamedQuery(name = "TTaxeDeclDou.findByTaxLinCod", query = "SELECT t FROM TTaxeDeclDou t WHERE t.taxLinCod = :taxLinCod"),
    @NamedQuery(name = "TTaxeDeclDou.findByTaxLinBse", query = "SELECT t FROM TTaxeDeclDou t WHERE t.taxLinBse = :taxLinBse"),
    @NamedQuery(name = "TTaxeDeclDou.findByTaxLinRat", query = "SELECT t FROM TTaxeDeclDou t WHERE t.taxLinRat = :taxLinRat"),
    @NamedQuery(name = "TTaxeDeclDou.findByTaxLinAmt", query = "SELECT t FROM TTaxeDeclDou t WHERE t.taxLinAmt = :taxLinAmt"),
    @NamedQuery(name = "TTaxeDeclDou.findByTaxLinMop", query = "SELECT t FROM TTaxeDeclDou t WHERE t.taxLinMop = :taxLinMop"),
    @NamedQuery(name = "TTaxeDeclDou.findByTaxLinTyp", query = "SELECT t FROM TTaxeDeclDou t WHERE t.taxLinTyp = :taxLinTyp")})
public class TTaxeDeclDou implements Serializable {
    @Column(name = "TAX_LIN_BSE")
    private BigDecimal taxLinBse;
    @Column(name = "TAX_LIN_RAT")
    private BigDecimal taxLinRat;
    @Column(name = "TAX_LIN_AMT")
    private BigDecimal taxLinAmt;
    @JoinColumn(name = "TAX_LIN_COD", referencedColumnName = "TAX_COD")
    @ManyToOne
    private TTaxeDouane taxLinCod;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TTaxeDeclDouPK tTaxeDeclDouPK;
   
    @Size(max = 1)
    @Column(name = "TAX_LIN_MOP")
    private String taxLinMop;
    @Size(max = 1)
    @Column(name = "TAX_LIN_TYP")
    private String taxLinTyp;
    @JoinColumns({
        @JoinColumn(name = "INSTANCEID", referencedColumnName = "INSTANCEID", insertable = false, updatable = false),
        @JoinColumn(name = "KEY_ITM_NBR", referencedColumnName = "KEY_ITM_NBR", insertable = false, updatable = false)})
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private TArticle tArticle;

    public TTaxeDeclDou() {
    }

    public TTaxeDeclDou(TTaxeDeclDouPK tTaxeDeclDouPK) {
        this.tTaxeDeclDouPK = tTaxeDeclDouPK;
    }

    public TTaxeDeclDou(long instanceid, long keyItmNbr, long keyTaxRnk) {
        this.tTaxeDeclDouPK = new TTaxeDeclDouPK(instanceid, keyItmNbr, keyTaxRnk);
    }

    public TTaxeDeclDouPK getTTaxeDeclDouPK() {
        return tTaxeDeclDouPK;
    }

    public void setTTaxeDeclDouPK(TTaxeDeclDouPK tTaxeDeclDouPK) {
        this.tTaxeDeclDouPK = tTaxeDeclDouPK;
    }

    

    public BigDecimal getTaxLinBse() {
        return taxLinBse;
    }

    public void setTaxLinBse(BigDecimal taxLinBse) {
        this.taxLinBse = taxLinBse;
    }

    public BigDecimal getTaxLinRat() {
        return taxLinRat;
    }

    public void setTaxLinRat(BigDecimal taxLinRat) {
        this.taxLinRat = taxLinRat;
    }

    public BigDecimal getTaxLinAmt() {
        return taxLinAmt;
    }

    public void setTaxLinAmt(BigDecimal taxLinAmt) {
        this.taxLinAmt = taxLinAmt;
    }

    public String getTaxLinMop() {
        return taxLinMop;
    }

    public void setTaxLinMop(String taxLinMop) {
        this.taxLinMop = taxLinMop;
    }

    public String getTaxLinTyp() {
        return taxLinTyp;
    }

    public void setTaxLinTyp(String taxLinTyp) {
        this.taxLinTyp = taxLinTyp;
    }

    public TArticle getTArticle() {
        return tArticle;
    }

    public void setTArticle(TArticle tArticle) {
        this.tArticle = tArticle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tTaxeDeclDouPK != null ? tTaxeDeclDouPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTaxeDeclDou)) {
            return false;
        }
        TTaxeDeclDou other = (TTaxeDeclDou) object;
        if ((this.tTaxeDeclDouPK == null && other.tTaxeDeclDouPK != null) || (this.tTaxeDeclDouPK != null && !this.tTaxeDeclDouPK.equals(other.tTaxeDeclDouPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TTaxeDeclDou[ tTaxeDeclDouPK=" + tTaxeDeclDouPK + " ]";
    }


    public TTaxeDouane getTaxLinCod() {
        return taxLinCod;
    }

    public void setTaxLinCod(TTaxeDouane taxLinCod) {
        this.taxLinCod = taxLinCod;
    }
    
}
