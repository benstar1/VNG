/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ben
 */
@Entity
@Table(name = "t_enqueter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TEnqueter.findAll", query = "SELECT t FROM TEnqueter t")
    , @NamedQuery(name = "TEnqueter.findByEqEnqCode", query = "SELECT t FROM TEnqueter t WHERE t.tEnqueterPK.eqEnqCode = :eqEnqCode")
    , @NamedQuery(name = "TEnqueter.findByEqUtiCode", query = "SELECT t FROM TEnqueter t WHERE t.tEnqueterPK.eqUtiCode = :eqUtiCode")
    , @NamedQuery(name = "TEnqueter.findByEqDateDebut", query = "SELECT t FROM TEnqueter t WHERE t.tEnqueterPK.eqDateDebut = :eqDateDebut")
    , @NamedQuery(name = "TEnqueter.findByEqDateFin", query = "SELECT t FROM TEnqueter t WHERE t.eqDateFin = :eqDateFin")
    , @NamedQuery(name = "TEnqueter.findByEqConCode", query = "SELECT t FROM TEnqueter t WHERE t.eqConCode = :eqConCode")
    , @NamedQuery(name = "TEnqueter.findByEqDateChargement", query = "SELECT t FROM TEnqueter t WHERE t.eqDateChargement = :eqDateChargement")})
public class TEnqueter implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TEnqueterPK tEnqueterPK;
    @Column(name = "eq_date_fin")
    @Temporal(TemporalType.DATE)
    private Date eqDateFin;
    @Size(max = 50)
    @Column(name = "eq_con_code")
    private String eqConCode;
    @Column(name = "eq_date_chargement")
    @Temporal(TemporalType.DATE)
    private Date eqDateChargement;
    @JoinColumn(name = "eq_enq_code", referencedColumnName = "enq_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TEnquete tEnquete;
    @JoinColumn(name = "eq_uti_code", referencedColumnName = "uti_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TUtilisateur tUtilisateur;

    public TEnqueter() {
    }

    public TEnqueter(TEnqueterPK tEnqueterPK) {
        this.tEnqueterPK = tEnqueterPK;
    }

    public TEnqueter(String eqEnqCode, String eqUtiCode, Date eqDateDebut) {
        this.tEnqueterPK = new TEnqueterPK(eqEnqCode, eqUtiCode, eqDateDebut);
    }

    public TEnqueterPK getTEnqueterPK() {
        return tEnqueterPK;
    }

    public void setTEnqueterPK(TEnqueterPK tEnqueterPK) {
        this.tEnqueterPK = tEnqueterPK;
    }

    public Date getEqDateFin() {
        return eqDateFin;
    }

    public void setEqDateFin(Date eqDateFin) {
        this.eqDateFin = eqDateFin;
    }

    public String getEqConCode() {
        return eqConCode;
    }

    public void setEqConCode(String eqConCode) {
        this.eqConCode = eqConCode;
    }

    public Date getEqDateChargement() {
        return eqDateChargement;
    }

    public void setEqDateChargement(Date eqDateChargement) {
        this.eqDateChargement = eqDateChargement;
    }

    public TEnquete getTEnquete() {
        return tEnquete;
    }

    public void setTEnquete(TEnquete tEnquete) {
        this.tEnquete = tEnquete;
    }

    public TUtilisateur getTUtilisateur() {
        return tUtilisateur;
    }

    public void setTUtilisateur(TUtilisateur tUtilisateur) {
        this.tUtilisateur = tUtilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tEnqueterPK != null ? tEnqueterPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TEnqueter)) {
            return false;
        }
        TEnqueter other = (TEnqueter) object;
        if ((this.tEnqueterPK == null && other.tEnqueterPK != null) || (this.tEnqueterPK != null && !this.tEnqueterPK.equals(other.tEnqueterPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TEnqueter[ tEnqueterPK=" + tEnqueterPK + " ]";
    }
    
}
