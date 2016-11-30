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
@Table(name = "T_BUREAU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TBureau.findAll", query = "SELECT t FROM TBureau t"),
    @NamedQuery(name = "TBureau.findByCuoCod", query = "SELECT t FROM TBureau t WHERE t.tBureauPK.cuoCod = :cuoCod"),
    @NamedQuery(name = "TBureau.findByValidFrom", query = "SELECT t FROM TBureau t WHERE t.tBureauPK.validFrom = :validFrom"),
    @NamedQuery(name = "TBureau.findByValidTo", query = "SELECT t FROM TBureau t WHERE t.validTo = :validTo"),
    @NamedQuery(name = "TBureau.findByCuoNam", query = "SELECT t FROM TBureau t WHERE t.cuoNam = :cuoNam"),
    @NamedQuery(name = "TBureau.findByCuoNam2", query = "SELECT t FROM TBureau t WHERE t.cuoNam2 = :cuoNam2"),
    @NamedQuery(name = "TBureau.findByCuoNam3", query = "SELECT t FROM TBureau t WHERE t.cuoNam3 = :cuoNam3"),
    @NamedQuery(name = "TBureau.findByCuoAdr", query = "SELECT t FROM TBureau t WHERE t.cuoAdr = :cuoAdr"),
    @NamedQuery(name = "TBureau.findByCuoAd2", query = "SELECT t FROM TBureau t WHERE t.cuoAd2 = :cuoAd2"),
    @NamedQuery(name = "TBureau.findByCuoAd3", query = "SELECT t FROM TBureau t WHERE t.cuoAd3 = :cuoAd3"),
    @NamedQuery(name = "TBureau.findByCuoAd4", query = "SELECT t FROM TBureau t WHERE t.cuoAd4 = :cuoAd4"),
    @NamedQuery(name = "TBureau.findByCuoTel", query = "SELECT t FROM TBureau t WHERE t.cuoTel = :cuoTel"),
    @NamedQuery(name = "TBureau.findByCuoFax", query = "SELECT t FROM TBureau t WHERE t.cuoFax = :cuoFax"),
    @NamedQuery(name = "TBureau.findByCuoTlx", query = "SELECT t FROM TBureau t WHERE t.cuoTlx = :cuoTlx"),
    @NamedQuery(name = "TBureau.findByCuoClr", query = "SELECT t FROM TBureau t WHERE t.cuoClr = :cuoClr"),
    @NamedQuery(name = "TBureau.findByCuoBrd", query = "SELECT t FROM TBureau t WHERE t.cuoBrd = :cuoBrd"),
    @NamedQuery(name = "TBureau.findByCuoAcc", query = "SELECT t FROM TBureau t WHERE t.cuoAcc = :cuoAcc"),
    @NamedQuery(name = "TBureau.findByBnkCod", query = "SELECT t FROM TBureau t WHERE t.bnkCod = :bnkCod"),
    @NamedQuery(name = "TBureau.findByBraCod", query = "SELECT t FROM TBureau t WHERE t.braCod = :braCod"),
    @NamedQuery(name = "TBureau.findByCuoCap", query = "SELECT t FROM TBureau t WHERE t.cuoCap = :cuoCap"),
    @NamedQuery(name = "TBureau.findByModifyTime", query = "SELECT t FROM TBureau t WHERE t.modifyTime = :modifyTime"),
    @NamedQuery(name = "TBureau.findByFlgRem", query = "SELECT t FROM TBureau t WHERE t.flgRem = :flgRem")})
public class TBureau implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TBureauPK tBureauPK;
    @Column(name = "VALID_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validTo;
    @Size(max = 35)
    @Column(name = "CUO_NAM")
    private String cuoNam;
    @Size(max = 35)
    @Column(name = "CUO_NAM2")
    private String cuoNam2;
    @Size(max = 35)
    @Column(name = "CUO_NAM3")
    private String cuoNam3;
    @Size(max = 35)
    @Column(name = "CUO_ADR")
    private String cuoAdr;
    @Size(max = 35)
    @Column(name = "CUO_AD2")
    private String cuoAd2;
    @Size(max = 35)
    @Column(name = "CUO_AD3")
    private String cuoAd3;
    @Size(max = 35)
    @Column(name = "CUO_AD4")
    private String cuoAd4;
    @Size(max = 15)
    @Column(name = "CUO_TEL")
    private String cuoTel;
    @Size(max = 15)
    @Column(name = "CUO_FAX")
    private String cuoFax;
    @Size(max = 15)
    @Column(name = "CUO_TLX")
    private String cuoTlx;
    @Size(max = 1)
    @Column(name = "CUO_CLR")
    private String cuoClr;
    @Size(max = 1)
    @Column(name = "CUO_BRD")
    private String cuoBrd;
    @Size(max = 17)
    @Column(name = "CUO_ACC")
    private String cuoAcc;
    @Size(max = 17)
    @Column(name = "BNK_COD")
    private String bnkCod;
    @Size(max = 17)
    @Column(name = "BRA_COD")
    private String braCod;
    @Size(max = 1)
    @Column(name = "CUO_CAP")
    private String cuoCap;
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;
    @Column(name = "FLG_REM")
    private Short flgRem;

    public TBureau() {
    }

    public TBureau(TBureauPK tBureauPK) {
        this.tBureauPK = tBureauPK;
    }

    public TBureau(String cuoCod, Date validFrom) {
        this.tBureauPK = new TBureauPK(cuoCod, validFrom);
    }

    public TBureauPK getTBureauPK() {
        return tBureauPK;
    }

    public void setTBureauPK(TBureauPK tBureauPK) {
        this.tBureauPK = tBureauPK;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public String getCuoNam() {
        return cuoNam;
    }

    public void setCuoNam(String cuoNam) {
        this.cuoNam = cuoNam;
    }

    public String getCuoNam2() {
        return cuoNam2;
    }

    public void setCuoNam2(String cuoNam2) {
        this.cuoNam2 = cuoNam2;
    }

    public String getCuoNam3() {
        return cuoNam3;
    }

    public void setCuoNam3(String cuoNam3) {
        this.cuoNam3 = cuoNam3;
    }

    public String getCuoAdr() {
        return cuoAdr;
    }

    public void setCuoAdr(String cuoAdr) {
        this.cuoAdr = cuoAdr;
    }

    public String getCuoAd2() {
        return cuoAd2;
    }

    public void setCuoAd2(String cuoAd2) {
        this.cuoAd2 = cuoAd2;
    }

    public String getCuoAd3() {
        return cuoAd3;
    }

    public void setCuoAd3(String cuoAd3) {
        this.cuoAd3 = cuoAd3;
    }

    public String getCuoAd4() {
        return cuoAd4;
    }

    public void setCuoAd4(String cuoAd4) {
        this.cuoAd4 = cuoAd4;
    }

    public String getCuoTel() {
        return cuoTel;
    }

    public void setCuoTel(String cuoTel) {
        this.cuoTel = cuoTel;
    }

    public String getCuoFax() {
        return cuoFax;
    }

    public void setCuoFax(String cuoFax) {
        this.cuoFax = cuoFax;
    }

    public String getCuoTlx() {
        return cuoTlx;
    }

    public void setCuoTlx(String cuoTlx) {
        this.cuoTlx = cuoTlx;
    }

    public String getCuoClr() {
        return cuoClr;
    }

    public void setCuoClr(String cuoClr) {
        this.cuoClr = cuoClr;
    }

    public String getCuoBrd() {
        return cuoBrd;
    }

    public void setCuoBrd(String cuoBrd) {
        this.cuoBrd = cuoBrd;
    }

    public String getCuoAcc() {
        return cuoAcc;
    }

    public void setCuoAcc(String cuoAcc) {
        this.cuoAcc = cuoAcc;
    }

    public String getBnkCod() {
        return bnkCod;
    }

    public void setBnkCod(String bnkCod) {
        this.bnkCod = bnkCod;
    }

    public String getBraCod() {
        return braCod;
    }

    public void setBraCod(String braCod) {
        this.braCod = braCod;
    }

    public String getCuoCap() {
        return cuoCap;
    }

    public void setCuoCap(String cuoCap) {
        this.cuoCap = cuoCap;
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
        hash += (tBureauPK != null ? tBureauPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TBureau)) {
            return false;
        }
        TBureau other = (TBureau) object;
        if ((this.tBureauPK == null && other.tBureauPK != null) || (this.tBureauPK != null && !this.tBureauPK.equals(other.tBureauPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TBureau[ tBureauPK=" + tBureauPK + " ]";
    }
    
}
