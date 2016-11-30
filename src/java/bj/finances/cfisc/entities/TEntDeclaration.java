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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "T_ENT_DECLARATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TEntDeclaration.findAll", query = "SELECT t FROM TEntDeclaration t"),
    @NamedQuery(name = "TEntDeclaration.findByEntDecNum", query = "SELECT t FROM TEntDeclaration t WHERE t.entDecNum = :entDecNum"),
    @NamedQuery(name = "TEntDeclaration.findByEntDecValidation", query = "SELECT t FROM TEntDeclaration t WHERE t.entDecValidation = :entDecValidation"),
    @NamedQuery(name = "TEntDeclaration.findByEntDecDate", query = "SELECT t FROM TEntDeclaration t WHERE t.entDecDate = :entDecDate"),
    @NamedQuery(name = "TEntDeclaration.findByEntDecDatefin", query = "SELECT t FROM TEntDeclaration t WHERE t.entDecDatefin = :entDecDatefin"),
    @NamedQuery(name = "TEntDeclaration.findByEntDecDatedebut", query = "SELECT t FROM TEntDeclaration t WHERE t.entDecDatedebut = :entDecDatedebut")})
public class TEntDeclaration implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENT_DEC_NUM")
    private Long entDecNum;
    @Size(max = 10)
    @Column(name = "ENT_DEC_VALIDATION")
    private String entDecValidation;
    @Column(name = "ENT_DEC_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entDecDate;
    @Column(name = "ENT_DEC_DATEFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entDecDatefin;
    @Column(name = "ENT_DEC_DATEDEBUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entDecDatedebut;
    @JoinColumn(name = "ENT_DEC_CONT_IMMATR", referencedColumnName = "CONT_IMMATR")
    @OneToOne
    private TRepUnique entDecContImmatr;
    @OneToMany(mappedBy = "declarEntDecNum")
    private List<TDeclarationFiscale> tDeclarationFiscaleList;

    public TEntDeclaration() {
    }

    public TEntDeclaration(Long entDecNum) {
        this.entDecNum = entDecNum;
    }

    public Long getEntDecNum() {
        return entDecNum;
    }

    public void setEntDecNum(Long entDecNum) {
        this.entDecNum = entDecNum;
    }

    public String getEntDecValidation() {
        return entDecValidation;
    }

    public void setEntDecValidation(String entDecValidation) {
        this.entDecValidation = entDecValidation;
    }

    public Date getEntDecDate() {
        return entDecDate;
    }

    public void setEntDecDate(Date entDecDate) {
        this.entDecDate = entDecDate;
    }

    public Date getEntDecDatefin() {
        return entDecDatefin;
    }

    public void setEntDecDatefin(Date entDecDatefin) {
        this.entDecDatefin = entDecDatefin;
    }

    public Date getEntDecDatedebut() {
        return entDecDatedebut;
    }

    public void setEntDecDatedebut(Date entDecDatedebut) {
        this.entDecDatedebut = entDecDatedebut;
    }

    public TRepUnique getEntDecContImmatr() {
        return entDecContImmatr;
    }

    public void setEntDecContImmatr(TRepUnique entDecContImmatr) {
        this.entDecContImmatr = entDecContImmatr;
    }

    @XmlTransient
    public List<TDeclarationFiscale> getTDeclarationFiscaleList() {
        return tDeclarationFiscaleList;
    }

    public void setTDeclarationFiscaleList(List<TDeclarationFiscale> tDeclarationFiscaleList) {
        this.tDeclarationFiscaleList = tDeclarationFiscaleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entDecNum != null ? entDecNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TEntDeclaration)) {
            return false;
        }
        TEntDeclaration other = (TEntDeclaration) object;
        if ((this.entDecNum == null && other.entDecNum != null) || (this.entDecNum != null && !this.entDecNum.equals(other.entDecNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TEntDeclaration[ entDecNum=" + entDecNum + " ]";
    }
    
}
