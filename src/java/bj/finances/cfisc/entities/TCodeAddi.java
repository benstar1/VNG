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
@Table(name = "T_CODE_ADDI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCodeAddi.findAll", query = "SELECT t FROM TCodeAddi t"),
    @NamedQuery(name = "TCodeAddi.findByCp3Cod", query = "SELECT t FROM TCodeAddi t WHERE t.tCodeAddiPK.cp3Cod = :cp3Cod"),
    @NamedQuery(name = "TCodeAddi.findByValidFrom", query = "SELECT t FROM TCodeAddi t WHERE t.tCodeAddiPK.validFrom = :validFrom"),
    @NamedQuery(name = "TCodeAddi.findByValidTo", query = "SELECT t FROM TCodeAddi t WHERE t.validTo = :validTo"),
    @NamedQuery(name = "TCodeAddi.findByCp3Dsc", query = "SELECT t FROM TCodeAddi t WHERE t.cp3Dsc = :cp3Dsc"),
    @NamedQuery(name = "TCodeAddi.findByRulCod", query = "SELECT t FROM TCodeAddi t WHERE t.rulCod = :rulCod"),
    @NamedQuery(name = "TCodeAddi.findByCp3Agr", query = "SELECT t FROM TCodeAddi t WHERE t.cp3Agr = :cp3Agr"),
    @NamedQuery(name = "TCodeAddi.findByCprVe2", query = "SELECT t FROM TCodeAddi t WHERE t.cprVe2 = :cprVe2"),
    @NamedQuery(name = "TCodeAddi.findByCprDs2", query = "SELECT t FROM TCodeAddi t WHERE t.cprDs2 = :cprDs2"),
    @NamedQuery(name = "TCodeAddi.findByModifyTime", query = "SELECT t FROM TCodeAddi t WHERE t.modifyTime = :modifyTime"),
    @NamedQuery(name = "TCodeAddi.findByFlgRem", query = "SELECT t FROM TCodeAddi t WHERE t.flgRem = :flgRem")})
public class TCodeAddi implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TCodeAddiPK tCodeAddiPK;
    @Column(name = "VALID_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validTo;
    @Size(max = 70)
    @Column(name = "CP3_DSC")
    private String cp3Dsc;
    @Size(max = 17)
    @Column(name = "RUL_COD")
    private String rulCod;
    @Size(max = 1)
    @Column(name = "CP3_AGR")
    private String cp3Agr;
    @Size(max = 4)
    @Column(name = "CPR_VE2")
    private String cprVe2;
    @Size(max = 70)
    @Column(name = "CPR_DS2")
    private String cprDs2;
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;
    @Column(name = "FLG_REM")
    private Short flgRem;

    public TCodeAddi() {
    }

    public TCodeAddi(TCodeAddiPK tCodeAddiPK) {
        this.tCodeAddiPK = tCodeAddiPK;
    }

    public TCodeAddi(String cp3Cod, Date validFrom) {
        this.tCodeAddiPK = new TCodeAddiPK(cp3Cod, validFrom);
    }

    public TCodeAddiPK getTCodeAddiPK() {
        return tCodeAddiPK;
    }

    public void setTCodeAddiPK(TCodeAddiPK tCodeAddiPK) {
        this.tCodeAddiPK = tCodeAddiPK;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public String getCp3Dsc() {
        return cp3Dsc;
    }

    public void setCp3Dsc(String cp3Dsc) {
        this.cp3Dsc = cp3Dsc;
    }

    public String getRulCod() {
        return rulCod;
    }

    public void setRulCod(String rulCod) {
        this.rulCod = rulCod;
    }

    public String getCp3Agr() {
        return cp3Agr;
    }

    public void setCp3Agr(String cp3Agr) {
        this.cp3Agr = cp3Agr;
    }

    public String getCprVe2() {
        return cprVe2;
    }

    public void setCprVe2(String cprVe2) {
        this.cprVe2 = cprVe2;
    }

    public String getCprDs2() {
        return cprDs2;
    }

    public void setCprDs2(String cprDs2) {
        this.cprDs2 = cprDs2;
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
        hash += (tCodeAddiPK != null ? tCodeAddiPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCodeAddi)) {
            return false;
        }
        TCodeAddi other = (TCodeAddi) object;
        if ((this.tCodeAddiPK == null && other.tCodeAddiPK != null) || (this.tCodeAddiPK != null && !this.tCodeAddiPK.equals(other.tCodeAddiPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TCodeAddi[ tCodeAddiPK=" + tCodeAddiPK + " ]";
    }
    
}
