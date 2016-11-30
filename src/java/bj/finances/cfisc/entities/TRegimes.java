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
@Table(name = "T_REGIMES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRegimes.findAll", query = "SELECT t FROM TRegimes t"),
    @NamedQuery(name = "TRegimes.findByCp4Cod", query = "SELECT t FROM TRegimes t WHERE t.tRegimesPK.cp4Cod = :cp4Cod"),
    @NamedQuery(name = "TRegimes.findByValidFrom", query = "SELECT t FROM TRegimes t WHERE t.tRegimesPK.validFrom = :validFrom"),
    @NamedQuery(name = "TRegimes.findByValidTo", query = "SELECT t FROM TRegimes t WHERE t.validTo = :validTo"),
    @NamedQuery(name = "TRegimes.findByCp4Dsc", query = "SELECT t FROM TRegimes t WHERE t.cp4Dsc = :cp4Dsc"),
    @NamedQuery(name = "TRegimes.findByCp4Dsc2", query = "SELECT t FROM TRegimes t WHERE t.cp4Dsc2 = :cp4Dsc2"),
    @NamedQuery(name = "TRegimes.findByCp4Dsc3", query = "SELECT t FROM TRegimes t WHERE t.cp4Dsc3 = :cp4Dsc3"),
    @NamedQuery(name = "TRegimes.findByCprCod", query = "SELECT t FROM TRegimes t WHERE t.cprCod = :cprCod"),
    @NamedQuery(name = "TRegimes.findByCppCod", query = "SELECT t FROM TRegimes t WHERE t.cppCod = :cppCod"),
    @NamedQuery(name = "TRegimes.findBySpeTra", query = "SELECT t FROM TRegimes t WHERE t.speTra = :speTra"),
    @NamedQuery(name = "TRegimes.findByGenTra", query = "SELECT t FROM TRegimes t WHERE t.genTra = :genTra"),
    @NamedQuery(name = "TRegimes.findByModifyTime", query = "SELECT t FROM TRegimes t WHERE t.modifyTime = :modifyTime"),
    @NamedQuery(name = "TRegimes.findByFlgRem", query = "SELECT t FROM TRegimes t WHERE t.flgRem = :flgRem")})
public class TRegimes implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TRegimesPK tRegimesPK;
    @Column(name = "VALID_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validTo;
    @Size(max = 70)
    @Column(name = "CP4_DSC")
    private String cp4Dsc;
    @Size(max = 70)
    @Column(name = "CP4_DSC2")
    private String cp4Dsc2;
    @Size(max = 70)
    @Column(name = "CP4_DSC3")
    private String cp4Dsc3;
    @Size(max = 2)
    @Column(name = "CPR_COD")
    private String cprCod;
    @Size(max = 2)
    @Column(name = "CPP_COD")
    private String cppCod;
    @Size(max = 1)
    @Column(name = "SPE_TRA")
    private String speTra;
    @Size(max = 1)
    @Column(name = "GEN_TRA")
    private String genTra;
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;
    @Column(name = "FLG_REM")
    private Short flgRem;

    public TRegimes() {
    }

    public TRegimes(TRegimesPK tRegimesPK) {
        this.tRegimesPK = tRegimesPK;
    }

    public TRegimes(String cp4Cod, Date validFrom) {
        this.tRegimesPK = new TRegimesPK(cp4Cod, validFrom);
    }

    public TRegimesPK getTRegimesPK() {
        return tRegimesPK;
    }

    public void setTRegimesPK(TRegimesPK tRegimesPK) {
        this.tRegimesPK = tRegimesPK;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public String getCp4Dsc() {
        return cp4Dsc;
    }

    public void setCp4Dsc(String cp4Dsc) {
        this.cp4Dsc = cp4Dsc;
    }

    public String getCp4Dsc2() {
        return cp4Dsc2;
    }

    public void setCp4Dsc2(String cp4Dsc2) {
        this.cp4Dsc2 = cp4Dsc2;
    }

    public String getCp4Dsc3() {
        return cp4Dsc3;
    }

    public void setCp4Dsc3(String cp4Dsc3) {
        this.cp4Dsc3 = cp4Dsc3;
    }

    public String getCprCod() {
        return cprCod;
    }

    public void setCprCod(String cprCod) {
        this.cprCod = cprCod;
    }

    public String getCppCod() {
        return cppCod;
    }

    public void setCppCod(String cppCod) {
        this.cppCod = cppCod;
    }

    public String getSpeTra() {
        return speTra;
    }

    public void setSpeTra(String speTra) {
        this.speTra = speTra;
    }

    public String getGenTra() {
        return genTra;
    }

    public void setGenTra(String genTra) {
        this.genTra = genTra;
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
        hash += (tRegimesPK != null ? tRegimesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRegimes)) {
            return false;
        }
        TRegimes other = (TRegimes) object;
        if ((this.tRegimesPK == null && other.tRegimesPK != null) || (this.tRegimesPK != null && !this.tRegimesPK.equals(other.tRegimesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TRegimes[ tRegimesPK=" + tRegimesPK + " ]";
    }
    
}
