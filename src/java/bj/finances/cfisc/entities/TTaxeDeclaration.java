/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "T_TAXE_DECLARATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTaxeDeclaration.findAll", query = "SELECT t FROM TTaxeDeclaration t"),
    @NamedQuery(name = "TTaxeDeclaration.findByTaxDecNum", query = "SELECT t FROM TTaxeDeclaration t WHERE t.taxDecNum = :taxDecNum"),
    @NamedQuery(name = "TTaxeDeclaration.findByTaxDecTarifCode", query = "SELECT t FROM TTaxeDeclaration t WHERE t.taxDecTarifCode = :taxDecTarifCode"),
    @NamedQuery(name = "TTaxeDeclaration.findByTaxDecValCaf", query = "SELECT t FROM TTaxeDeclaration t WHERE t.taxDecValCaf = :taxDecValCaf"),
    @NamedQuery(name = "TTaxeDeclaration.findByTaxDecBaseTaxable", query = "SELECT t FROM TTaxeDeclaration t WHERE t.taxDecBaseTaxable = :taxDecBaseTaxable"),
    @NamedQuery(name = "TTaxeDeclaration.findByTaxDecPrixTaxable", query = "SELECT t FROM TTaxeDeclaration t WHERE t.taxDecPrixTaxable = :taxDecPrixTaxable"),
    @NamedQuery(name = "TTaxeDeclaration.findByTaxDecDd", query = "SELECT t FROM TTaxeDeclaration t WHERE t.taxDecDd = :taxDecDd"),
    @NamedQuery(name = "TTaxeDeclaration.findByTaxDecPc", query = "SELECT t FROM TTaxeDeclaration t WHERE t.taxDecPc = :taxDecPc"),
    @NamedQuery(name = "TTaxeDeclaration.findByTaxDecPcs", query = "SELECT t FROM TTaxeDeclaration t WHERE t.taxDecPcs = :taxDecPcs"),
    @NamedQuery(name = "TTaxeDeclaration.findByTaxDecRs", query = "SELECT t FROM TTaxeDeclaration t WHERE t.taxDecRs = :taxDecRs"),
    @NamedQuery(name = "TTaxeDeclaration.findByTaxDecAib", query = "SELECT t FROM TTaxeDeclaration t WHERE t.taxDecAib = :taxDecAib"),
    @NamedQuery(name = "TTaxeDeclaration.findByTaxDecTva", query = "SELECT t FROM TTaxeDeclaration t WHERE t.taxDecTva = :taxDecTva"),
    @NamedQuery(name = "TTaxeDeclaration.findByTaxDecTotal", query = "SELECT t FROM TTaxeDeclaration t WHERE t.taxDecTotal = :taxDecTotal")})
public class TTaxeDeclaration implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TAX_DEC_NUM")
    private Long taxDecNum;
    @Size(max = 10)
    @Column(name = "TAX_DEC_TARIF_CODE")
    private String taxDecTarifCode;
    @Column(name = "TAX_DEC_VAL_CAF")
    private Long taxDecValCaf;
    @Column(name = "TAX_DEC_BASE_TAXABLE")
    private Long taxDecBaseTaxable;
    @Column(name = "TAX_DEC_PRIX_TAXABLE")
    private Long taxDecPrixTaxable;
    @Column(name = "TAX_DEC_DD")
    private Long taxDecDd;
    @Column(name = "TAX_DEC_PC")
    private Long taxDecPc;
    @Column(name = "TAX_DEC_PCS")
    private Long taxDecPcs;
    @Column(name = "TAX_DEC_RS")
    private Long taxDecRs;
    @Column(name = "TAX_DEC_AIB")
    private Long taxDecAib;
    @Column(name = "TAX_DEC_TVA")
    private Long taxDecTva;
    @Column(name = "TAX_DEC_TOTAL")
    private Long taxDecTotal;
    @JoinColumn(name = "TAX_DECLAR_NUM", referencedColumnName = "DECLAR_NUM")
    @ManyToOne
    private TDeclarationFiscale taxDeclarNum;

    public TTaxeDeclaration() {
    }

    public TTaxeDeclaration(Long taxDecNum) {
        this.taxDecNum = taxDecNum;
    }

    public Long getTaxDecNum() {
        return taxDecNum;
    }

    public void setTaxDecNum(Long taxDecNum) {
        this.taxDecNum = taxDecNum;
    }

    public String getTaxDecTarifCode() {
        return taxDecTarifCode;
    }

    public void setTaxDecTarifCode(String taxDecTarifCode) {
        this.taxDecTarifCode = taxDecTarifCode;
    }

    public Long getTaxDecValCaf() {
        return taxDecValCaf;
    }

    public void setTaxDecValCaf(Long taxDecValCaf) {
        this.taxDecValCaf = taxDecValCaf;
    }

    public Long getTaxDecBaseTaxable() {
        return taxDecBaseTaxable;
    }

    public void setTaxDecBaseTaxable(Long taxDecBaseTaxable) {
        this.taxDecBaseTaxable = taxDecBaseTaxable;
    }

    public Long getTaxDecPrixTaxable() {
        return taxDecPrixTaxable;
    }

    public void setTaxDecPrixTaxable(Long taxDecPrixTaxable) {
        this.taxDecPrixTaxable = taxDecPrixTaxable;
    }

    public Long getTaxDecDd() {
        return taxDecDd;
    }

    public void setTaxDecDd(Long taxDecDd) {
        this.taxDecDd = taxDecDd;
    }

    public Long getTaxDecPc() {
        return taxDecPc;
    }

    public void setTaxDecPc(Long taxDecPc) {
        this.taxDecPc = taxDecPc;
    }

    public Long getTaxDecPcs() {
        return taxDecPcs;
    }

    public void setTaxDecPcs(Long taxDecPcs) {
        this.taxDecPcs = taxDecPcs;
    }

    public Long getTaxDecRs() {
        return taxDecRs;
    }

    public void setTaxDecRs(Long taxDecRs) {
        this.taxDecRs = taxDecRs;
    }

    public Long getTaxDecAib() {
        return taxDecAib;
    }

    public void setTaxDecAib(Long taxDecAib) {
        this.taxDecAib = taxDecAib;
    }

    public Long getTaxDecTva() {
        return taxDecTva;
    }

    public void setTaxDecTva(Long taxDecTva) {
        this.taxDecTva = taxDecTva;
    }

    public Long getTaxDecTotal() {
        return taxDecTotal;
    }

    public void setTaxDecTotal(Long taxDecTotal) {
        this.taxDecTotal = taxDecTotal;
    }

    public TDeclarationFiscale getTaxDeclarNum() {
        return taxDeclarNum;
    }

    public void setTaxDeclarNum(TDeclarationFiscale taxDeclarNum) {
        this.taxDeclarNum = taxDeclarNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taxDecNum != null ? taxDecNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTaxeDeclaration)) {
            return false;
        }
        TTaxeDeclaration other = (TTaxeDeclaration) object;
        if ((this.taxDecNum == null && other.taxDecNum != null) || (this.taxDecNum != null && !this.taxDecNum.equals(other.taxDecNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TTaxeDeclaration[ taxDecNum=" + taxDecNum + " ]";
    }
    
}
