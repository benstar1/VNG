/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ben
 */
@Entity
@Table(name = "t_affecter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TAffecter.findAll", query = "SELECT t FROM TAffecter t")
    , @NamedQuery(name = "TAffecter.findByAffCode", query = "SELECT t FROM TAffecter t WHERE t.affCode = :affCode")
    , @NamedQuery(name = "TAffecter.findByAffDateDebut", query = "SELECT t FROM TAffecter t WHERE t.affDateDebut = :affDateDebut")
    , @NamedQuery(name = "TAffecter.findByAffDateFin", query = "SELECT t FROM TAffecter t WHERE t.affDateFin = :affDateFin")
    , @NamedQuery(name = "TAffecter.findByAffDateChargement", query = "SELECT t FROM TAffecter t WHERE t.affDateChargement = :affDateChargement")})
public class TAffecter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "aff_code")
    private String affCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aff_date_debut")
    @Temporal(TemporalType.DATE)
    private Date affDateDebut;
    @Column(name = "aff_date_fin")
    @Temporal(TemporalType.DATE)
    private Date affDateFin;
    @Column(name = "aff_date_chargement")
    @Temporal(TemporalType.DATE)
    private Date affDateChargement;
    @JoinColumn(name = "aff_enq_code", referencedColumnName = "enq_code")
    @ManyToOne(optional = false)
    private TEnquete affEnqCode;
    @JoinColumn(name = "aff_uti_code", referencedColumnName = "uti_code")
    @ManyToOne(optional = false)
    private TUtilisateur affUtiCode;
    @JoinColumn(name = "aff_vila_code", referencedColumnName = "vila_code")
    @ManyToOne(optional = false)
    private TVillage affVilaCode;

    public TAffecter() {
    }

    public TAffecter(String affCode) {
        this.affCode = affCode;
    }

    public TAffecter(String affCode, Date affDateDebut) {
        this.affCode = affCode;
        this.affDateDebut = affDateDebut;
    }

    public String getAffCode() {
        return affCode;
    }

    public void setAffCode(String affCode) {
        this.affCode = affCode;
    }

    public Date getAffDateDebut() {
        return affDateDebut;
    }

    public void setAffDateDebut(Date affDateDebut) {
        this.affDateDebut = affDateDebut;
    }

    public Date getAffDateFin() {
        return affDateFin;
    }

    public void setAffDateFin(Date affDateFin) {
        this.affDateFin = affDateFin;
    }

    public Date getAffDateChargement() {
        return affDateChargement;
    }

    public void setAffDateChargement(Date affDateChargement) {
        this.affDateChargement = affDateChargement;
    }

    public TEnquete getAffEnqCode() {
        return affEnqCode;
    }

    public void setAffEnqCode(TEnquete affEnqCode) {
        this.affEnqCode = affEnqCode;
    }

    public TUtilisateur getAffUtiCode() {
        return affUtiCode;
    }

    public void setAffUtiCode(TUtilisateur affUtiCode) {
        this.affUtiCode = affUtiCode;
    }

    public TVillage getAffVilaCode() {
        return affVilaCode;
    }

    public void setAffVilaCode(TVillage affVilaCode) {
        this.affVilaCode = affVilaCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (affCode != null ? affCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TAffecter)) {
            return false;
        }
        TAffecter other = (TAffecter) object;
        if ((this.affCode == null && other.affCode != null) || (this.affCode != null && !this.affCode.equals(other.affCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TAffecter[ affCode=" + affCode + " ]";
    }
    
}
