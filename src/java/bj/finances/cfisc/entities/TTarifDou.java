/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
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
@Table(name = "T_TARIF_DOU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTarifDou.findAll", query = "SELECT t FROM TTarifDou t"),
    @NamedQuery(name = "TTarifDou.findByHs6Cod", query = "SELECT t FROM TTarifDou t WHERE t.tTarifDouPK.hs6Cod = :hs6Cod"),
    @NamedQuery(name = "TTarifDou.findByValidFrom", query = "SELECT t FROM TTarifDou t WHERE t.tTarifDouPK.validFrom = :validFrom"),
    @NamedQuery(name = "TTarifDou.findByValidTo", query = "SELECT t FROM TTarifDou t WHERE t.validTo = :validTo"),
    @NamedQuery(name = "TTarifDou.findByHs6Dsc", query = "SELECT t FROM TTarifDou t WHERE t.hs6Dsc = :hs6Dsc"),
    @NamedQuery(name = "TTarifDou.findByHs4Cod", query = "SELECT t FROM TTarifDou t WHERE t.hs4Cod = :hs4Cod"),
    @NamedQuery(name = "TTarifDou.findByHs5Cod", query = "SELECT t FROM TTarifDou t WHERE t.hs5Cod = :hs5Cod"),
    @NamedQuery(name = "TTarifDou.findByModifyTime", query = "SELECT t FROM TTarifDou t WHERE t.modifyTime = :modifyTime"),
    @NamedQuery(name = "TTarifDou.findByFlgRem", query = "SELECT t FROM TTarifDou t WHERE t.flgRem = :flgRem")})
public class TTarifDou implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TTarifDouPK tTarifDouPK;
    @Column(name = "VALID_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validTo;
    @Size(max = 1000)
    @Column(name = "HS6_DSC")
    private String hs6Dsc;
    @Size(max = 4)
    @Column(name = "HS4_COD")
    private String hs4Cod;
    @Size(max = 5)
    @Column(name = "HS5_COD")
    private String hs5Cod;
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;
    @Column(name = "FLG_REM")
    private Short flgRem;

    public TTarifDou() {
    }

    public TTarifDou(TTarifDouPK tTarifDouPK) {
        this.tTarifDouPK = tTarifDouPK;
    }

    public TTarifDou(String hs6Cod, Date validFrom) {
        this.tTarifDouPK = new TTarifDouPK(hs6Cod, validFrom);
    }

    public TTarifDouPK getTTarifDouPK() {
        return tTarifDouPK;
    }

    public void setTTarifDouPK(TTarifDouPK tTarifDouPK) {
        this.tTarifDouPK = tTarifDouPK;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public String getHs6Dsc() {
        return hs6Dsc;
    }

    public void setHs6Dsc(String hs6Dsc) {
        this.hs6Dsc = hs6Dsc;
    }

    public String getHs4Cod() {
        return hs4Cod;
    }

    public void setHs4Cod(String hs4Cod) {
        this.hs4Cod = hs4Cod;
    }

    public String getHs5Cod() {
        return hs5Cod;
    }

    public void setHs5Cod(String hs5Cod) {
        this.hs5Cod = hs5Cod;
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
        hash += (tTarifDouPK != null ? tTarifDouPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTarifDou)) {
            return false;
        }
        TTarifDou other = (TTarifDou) object;
        if ((this.tTarifDouPK == null && other.tTarifDouPK != null) || (this.tTarifDouPK != null && !this.tTarifDouPK.equals(other.tTarifDouPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TTarifDou[ tTarifDouPK=" + tTarifDouPK + " ]";
    }
    
}
