/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "T_MONNAIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMonnaie.findAll", query = "SELECT t FROM TMonnaie t"),
    @NamedQuery(name = "TMonnaie.findByCurRat", query = "SELECT t FROM TMonnaie t WHERE t.curRat = :curRat"),
    @NamedQuery(name = "TMonnaie.findByValidFrom", query = "SELECT t FROM TMonnaie t WHERE t.tMonnaiePK.validFrom = :validFrom"),
    @NamedQuery(name = "TMonnaie.findByValidTo", query = "SELECT t FROM TMonnaie t WHERE t.validTo = :validTo"),
    @NamedQuery(name = "TMonnaie.findByCurCod", query = "SELECT t FROM TMonnaie t WHERE t.tMonnaiePK.curCod = :curCod"),
    @NamedQuery(name = "TMonnaie.findByCurDsc", query = "SELECT t FROM TMonnaie t WHERE t.curDsc = :curDsc"),
    @NamedQuery(name = "TMonnaie.findByCurDsc2", query = "SELECT t FROM TMonnaie t WHERE t.curDsc2 = :curDsc2"),
    @NamedQuery(name = "TMonnaie.findByCurDsc3", query = "SELECT t FROM TMonnaie t WHERE t.curDsc3 = :curDsc3"),
    @NamedQuery(name = "TMonnaie.findByModifyTime", query = "SELECT t FROM TMonnaie t WHERE t.modifyTime = :modifyTime"),
    @NamedQuery(name = "TMonnaie.findByFlgRem", query = "SELECT t FROM TMonnaie t WHERE t.flgRem = :flgRem")})
public class TMonnaie implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TMonnaiePK tMonnaiePK;
    @Column(name = "CUR_RAT")
    private BigInteger curRat;
    @Column(name = "VALID_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validTo;
    @Size(max = 35)
    @Column(name = "CUR_DSC")
    private String curDsc;
    @Size(max = 35)
    @Column(name = "CUR_DSC2")
    private String curDsc2;
    @Size(max = 35)
    @Column(name = "CUR_DSC3")
    private String curDsc3;
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;
    @Column(name = "FLG_REM")
    private Short flgRem;

    public TMonnaie() {
    }

    public TMonnaie(TMonnaiePK tMonnaiePK) {
        this.tMonnaiePK = tMonnaiePK;
    }

    public TMonnaie(Date validFrom, String curCod) {
        this.tMonnaiePK = new TMonnaiePK(validFrom, curCod);
    }

    public TMonnaiePK getTMonnaiePK() {
        return tMonnaiePK;
    }

    public void setTMonnaiePK(TMonnaiePK tMonnaiePK) {
        this.tMonnaiePK = tMonnaiePK;
    }

    public BigInteger getCurRat() {
        return curRat;
    }

    public void setCurRat(BigInteger curRat) {
        this.curRat = curRat;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public String getCurDsc() {
        return curDsc;
    }

    public void setCurDsc(String curDsc) {
        this.curDsc = curDsc;
    }

    public String getCurDsc2() {
        return curDsc2;
    }

    public void setCurDsc2(String curDsc2) {
        this.curDsc2 = curDsc2;
    }

    public String getCurDsc3() {
        return curDsc3;
    }

    public void setCurDsc3(String curDsc3) {
        this.curDsc3 = curDsc3;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Short getFlgRem() {
        return flgRem;
    }

    public void setFlgRem(Short flgRem) {
        this.flgRem = flgRem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tMonnaiePK != null ? tMonnaiePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TMonnaie)) {
            return false;
        }
        TMonnaie other = (TMonnaie) object;
        if ((this.tMonnaiePK == null && other.tMonnaiePK != null) || (this.tMonnaiePK != null && !this.tMonnaiePK.equals(other.tMonnaiePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TMonnaie[ tMonnaiePK=" + tMonnaiePK + " ]";
    }
    
}
