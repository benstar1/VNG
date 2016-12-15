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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "T_DECLARATION_FISCALE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TDeclarationFiscale.findAll", query = "SELECT t FROM TDeclarationFiscale t"),
    @NamedQuery(name = "TDeclarationFiscale.findByDeclarNum", query = "SELECT t FROM TDeclarationFiscale t WHERE t.declarNum = :declarNum"),
    @NamedQuery(name = "TDeclarationFiscale.findAllByContribuable", query = "SELECT t FROM TDeclarationFiscale t WHERE t.declarNum = :cmpCod"),
    @NamedQuery(name = "TDeclarationFiscale.findByDeclarReference", query = "SELECT t FROM TDeclarationFiscale t WHERE t.declarReference = :declarReference"),
    @NamedQuery(name = "TDeclarationFiscale.findByDeclarDate", query = "SELECT t FROM TDeclarationFiscale t WHERE t.declarDate = :declarDate"),
    @NamedQuery(name = "TDeclarationFiscale.findByDeclarBureau", query = "SELECT t FROM TDeclarationFiscale t WHERE t.declarBureau = :declarBureau"),
    @NamedQuery(name = "TDeclarationFiscale.findByNumEntDecl", query = "SELECT t FROM TDeclarationFiscale t WHERE t.declarEntDecNum = :entdeclar"),
    @NamedQuery(name = "TDeclarationFiscale.findByDeclarAnnee", query = "SELECT t FROM TDeclarationFiscale t WHERE t.declarAnnee = :declarAnnee")})

@SequenceGenerator(name = "generateur_declfisc",sequenceName = "SEQ_DECLFISC", allocationSize=1)
public class TDeclarationFiscale implements Serializable {
    @Column(name = "TOTAL_DD")
    private Long totalDd;
    @Column(name = "TOTAL_TVA")
    private Long totalTva;
    @Column(name = "TOTAL_PC")
    private Long totalPc;
    @Column(name = "TOTAL_PCS")
    private Long totalPcs;
    @Column(name = "TOTAL_RS")
    private Long totalRs;
    @Column(name = "TOTAL_AIB")
    private Long totalAib;
    @Column(name = "TOTAL_BASE")
    private Long totalBase;
    @Column(name = "TOTAL_VCAF")
    private Long totalVcaf;
    @Column(name = "TOTAL_PRIXTAXABLE")
    private Long totalPrixtaxable;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generateur_declfisc")
    @Basic(optional = false)
    @NotNull
    @Column(name = "DECLAR_NUM")
    private Long declarNum;
    @Size(max = 10)
    @Column(name = "DECLAR_REFERENCE")
    private String declarReference;
    @Column(name = "DECLAR_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date declarDate;
    @Size(max = 10)
    @Column(name = "DECLAR_BUREAU")
    private String declarBureau;
    @Size(max = 10)
    @Column(name = "DECLAR_ANNEE")
    private String declarAnnee;
    @JoinColumn(name = "DECLAR_ENT_DEC_NUM", referencedColumnName = "ENT_DEC_NUM")
    @ManyToOne
    private TEntDeclaration declarEntDecNum;
    @OneToMany(mappedBy = "taxDeclarNum")
    private List<TTaxeDeclaration> tTaxeDeclarationList;

    public TDeclarationFiscale() {
    }

    public TDeclarationFiscale(Long declarNum) {
        this.declarNum = declarNum;
    }

    public Long getDeclarNum() {
        return declarNum;
    }

    public void setDeclarNum(Long declarNum) {
        this.declarNum = declarNum;
    }

    public String getDeclarReference() {
        return declarReference;
    }

    public void setDeclarReference(String declarReference) {
        this.declarReference = declarReference;
    }

    public Date getDeclarDate() {
        return declarDate;
    }

    public void setDeclarDate(Date declarDate) {
        this.declarDate = declarDate;
    }

    public String getDeclarBureau() {
        return declarBureau;
    }

    public void setDeclarBureau(String declarBureau) {
        this.declarBureau = declarBureau;
    }

    public String getDeclarAnnee() {
        return declarAnnee;
    }

    public void setDeclarAnnee(String declarAnnee) {
        this.declarAnnee = declarAnnee;
    }

    public TEntDeclaration getDeclarEntDecNum() {
        return declarEntDecNum;
    }

    public void setDeclarEntDecNum(TEntDeclaration declarEntDecNum) {
        this.declarEntDecNum = declarEntDecNum;
    }

    @XmlTransient
    public List<TTaxeDeclaration> getTTaxeDeclarationList() {
        return tTaxeDeclarationList;
    }

    public void setTTaxeDeclarationList(List<TTaxeDeclaration> tTaxeDeclarationList) {
        this.tTaxeDeclarationList = tTaxeDeclarationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (declarNum != null ? declarNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TDeclarationFiscale)) {
            return false;
        }
        TDeclarationFiscale other = (TDeclarationFiscale) object;
        if ((this.declarNum == null && other.declarNum != null) || (this.declarNum != null && !this.declarNum.equals(other.declarNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TDeclarationFiscale[ declarNum=" + declarNum + " ]";
    }

    public Long getTotalDd() {
        return totalDd;
    }

    public void setTotalDd(Long totalDd) {
        this.totalDd = totalDd;
    }

    public Long getTotalTva() {
        return totalTva;
    }

    public void setTotalTva(Long totalTva) {
        this.totalTva = totalTva;
    }

    public Long getTotalPc() {
        return totalPc;
    }

    public void setTotalPc(Long totalPc) {
        this.totalPc = totalPc;
    }

    public Long getTotalPcs() {
        return totalPcs;
    }

    public void setTotalPcs(Long totalPcs) {
        this.totalPcs = totalPcs;
    }

    public Long getTotalRs() {
        return totalRs;
    }

    public void setTotalRs(Long totalRs) {
        this.totalRs = totalRs;
    }

    public Long getTotalAib() {
        return totalAib;
    }

    public void setTotalAib(Long totalAib) {
        this.totalAib = totalAib;
    }

    public Long getTotalBase() {
        return totalBase;
    }

    public void setTotalBase(Long totalBase) {
        this.totalBase = totalBase;
    }

    public Long getTotalVcaf() {
        return totalVcaf;
    }

    public void setTotalVcaf(Long totalVcaf) {
        this.totalVcaf = totalVcaf;
    }

    public Long getTotalPrixtaxable() {
        return totalPrixtaxable;
    }

    public void setTotalPrixtaxable(Long totalPrixtaxable) {
        this.totalPrixtaxable = totalPrixtaxable;
    }
    
}
