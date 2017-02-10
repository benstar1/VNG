/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author JOHNSON
 */
@Entity
@Table(name = "T_TAXE_DOUANE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTaxeDouane.findAll", query = "SELECT t FROM TTaxeDouane t"),
    @NamedQuery(name = "TTaxeDouane.findByTaxCod", query = "SELECT t FROM TTaxeDouane t WHERE t.taxCod = :taxCod"),
    @NamedQuery(name = "TTaxeDouane.findByValidFrom", query = "SELECT t FROM TTaxeDouane t WHERE t.validFrom = :validFrom"),
    @NamedQuery(name = "TTaxeDouane.findByValidTo", query = "SELECT t FROM TTaxeDouane t WHERE t.validTo = :validTo"),
    @NamedQuery(name = "TTaxeDouane.findByTaxDsc", query = "SELECT t FROM TTaxeDouane t WHERE t.taxDsc = :taxDsc"),
    @NamedQuery(name = "TTaxeDouane.findByTaxDsc2", query = "SELECT t FROM TTaxeDouane t WHERE t.taxDsc2 = :taxDsc2"),
    @NamedQuery(name = "TTaxeDouane.findByTaxDsc3", query = "SELECT t FROM TTaxeDouane t WHERE t.taxDsc3 = :taxDsc3"),
    @NamedQuery(name = "TTaxeDouane.findByBudCod", query = "SELECT t FROM TTaxeDouane t WHERE t.budCod = :budCod"),
    @NamedQuery(name = "TTaxeDouane.findByTaxLin", query = "SELECT t FROM TTaxeDouane t WHERE t.taxLin = :taxLin"),
    @NamedQuery(name = "TTaxeDouane.findByModifyTime", query = "SELECT t FROM TTaxeDouane t WHERE t.modifyTime = :modifyTime"),
    @NamedQuery(name = "TTaxeDouane.findByFlgRem", query = "SELECT t FROM TTaxeDouane t WHERE t.flgRem = :flgRem")})
public class TTaxeDouane implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "TAX_COD")
    private String taxCod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALID_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validFrom;
    @Column(name = "VALID_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validTo;
    @Size(max = 35)
    @Column(name = "TAX_DSC")
    private String taxDsc;
    @Size(max = 35)
    @Column(name = "TAX_DSC2")
    private String taxDsc2;
    @Size(max = 35)
    @Column(name = "TAX_DSC3")
    private String taxDsc3;
    @Size(max = 17)
    @Column(name = "BUD_COD")
    private String budCod;
    @Size(max = 17)
    @Column(name = "TAX_LIN")
    private String taxLin;
    @Column(name = "MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;
    @Column(name = "FLG_REM")
    private Short flgRem;
    @OneToMany(mappedBy = "taxLinCod")
    private List<TTaxeDeclDou> tTaxeDeclDouList;

    public TTaxeDouane() {
    }

    public TTaxeDouane(String taxCod) {
        this.taxCod = taxCod;
    }

    public TTaxeDouane(String taxCod, Date validFrom) {
        this.taxCod = taxCod;
        this.validFrom = validFrom;
    }

    public String getTaxCod() {
        return taxCod;
    }

    public void setTaxCod(String taxCod) {
        this.taxCod = taxCod;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public String getTaxDsc() {
        return taxDsc;
    }

    public void setTaxDsc(String taxDsc) {
        this.taxDsc = taxDsc;
    }

    public String getTaxDsc2() {
        return taxDsc2;
    }

    public void setTaxDsc2(String taxDsc2) {
        this.taxDsc2 = taxDsc2;
    }

    public String getTaxDsc3() {
        return taxDsc3;
    }

    public void setTaxDsc3(String taxDsc3) {
        this.taxDsc3 = taxDsc3;
    }

    public String getBudCod() {
        return budCod;
    }

    public void setBudCod(String budCod) {
        this.budCod = budCod;
    }

    public String getTaxLin() {
        return taxLin;
    }

    public void setTaxLin(String taxLin) {
        this.taxLin = taxLin;
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

    @XmlTransient
    @JsonIgnore
    public List<TTaxeDeclDou> getTTaxeDeclDouList() {
        return tTaxeDeclDouList;
    }

    public void setTTaxeDeclDouList(List<TTaxeDeclDou> tTaxeDeclDouList) {
        this.tTaxeDeclDouList = tTaxeDeclDouList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taxCod != null ? taxCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTaxeDouane)) {
            return false;
        }
        TTaxeDouane other = (TTaxeDouane) object;
        if ((this.taxCod == null && other.taxCod != null) || (this.taxCod != null && !this.taxCod.equals(other.taxCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TTaxeDouane[ taxCod=" + taxCod + " ]";
    }
    
}
