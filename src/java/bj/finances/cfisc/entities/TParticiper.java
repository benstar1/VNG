/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "T_PARTICIPER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TParticiper.findAll", query = "SELECT t FROM TParticiper t"),
    @NamedQuery(name = "TParticiper.findByPartNum", query = "SELECT t FROM TParticiper t WHERE t.partNum = :partNum"),
    @NamedQuery(name = "TParticiper.findByPartContImmatr", query = "SELECT t FROM TParticiper t WHERE t.partContImmatr = :partContImmatr"),
    @NamedQuery(name = "TParticiper.findByPartContImmatrCont", query = "SELECT t FROM TParticiper t WHERE t.partContImmatrCont = :partContImmatrCont"),
    @NamedQuery(name = "TParticiper.findByPartPourcentage", query = "SELECT t FROM TParticiper t WHERE t.partPourcentage = :partPourcentage"),
    @NamedQuery(name = "TParticiper.findByPartMontant", query = "SELECT t FROM TParticiper t WHERE t.partMontant = :partMontant")})
public class TParticiper implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PART_NUM")
    private Long partNum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "PART_CONT_IMMATR")
    private String partContImmatr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "PART_CONT_IMMATR_CONT")
    private String partContImmatrCont;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PART_POURCENTAGE")
    private BigDecimal partPourcentage;
    @Column(name = "PART_MONTANT")
    private Long partMontant;
    @JoinColumn(name = "PART_TYP_PART_CODE", referencedColumnName = "TYP_PART_CODE")
    @ManyToOne(optional = false)
    private TTypeParticipation partTypPartCode;

    public TParticiper() {
    }

    public TParticiper(Long partNum) {
        this.partNum = partNum;
    }

    public TParticiper(Long partNum, String partContImmatr, String partContImmatrCont) {
        this.partNum = partNum;
        this.partContImmatr = partContImmatr;
        this.partContImmatrCont = partContImmatrCont;
    }

    public Long getPartNum() {
        return partNum;
    }

    public void setPartNum(Long partNum) {
        this.partNum = partNum;
    }

    public String getPartContImmatr() {
        return partContImmatr;
    }

    public void setPartContImmatr(String partContImmatr) {
        this.partContImmatr = partContImmatr;
    }

    public String getPartContImmatrCont() {
        return partContImmatrCont;
    }

    public void setPartContImmatrCont(String partContImmatrCont) {
        this.partContImmatrCont = partContImmatrCont;
    }

    public BigDecimal getPartPourcentage() {
        return partPourcentage;
    }

    public void setPartPourcentage(BigDecimal partPourcentage) {
        this.partPourcentage = partPourcentage;
    }

    public Long getPartMontant() {
        return partMontant;
    }

    public void setPartMontant(Long partMontant) {
        this.partMontant = partMontant;
    }

    public TTypeParticipation getPartTypPartCode() {
        return partTypPartCode;
    }

    public void setPartTypPartCode(TTypeParticipation partTypPartCode) {
        this.partTypPartCode = partTypPartCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (partNum != null ? partNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TParticiper)) {
            return false;
        }
        TParticiper other = (TParticiper) object;
        if ((this.partNum == null && other.partNum != null) || (this.partNum != null && !this.partNum.equals(other.partNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TParticiper[ partNum=" + partNum + " ]";
    }
    
}
