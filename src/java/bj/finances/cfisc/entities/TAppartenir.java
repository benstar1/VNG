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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "T_APPARTENIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TAppartenir.findAll", query = "SELECT t FROM TAppartenir t"),
    @NamedQuery(name = "TAppartenir.findByAppartenirCentrImpCode", query = "SELECT t FROM TAppartenir t WHERE t.tAppartenirPK.appartenirCentrImpCode = :appartenirCentrImpCode"),
    @NamedQuery(name = "TAppartenir.findByAppartenirUtilLogin", query = "SELECT t FROM TAppartenir t WHERE t.tAppartenirPK.appartenirUtilLogin = :appartenirUtilLogin"),
    @NamedQuery(name = "TAppartenir.findByAppartenirDatedebut", query = "SELECT t FROM TAppartenir t WHERE t.tAppartenirPK.appartenirDatedebut = :appartenirDatedebut"),
    @NamedQuery(name = "TAppartenir.findByDatefin", query = "SELECT t FROM TAppartenir t WHERE t.datefin = :datefin")})
public class TAppartenir implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TAppartenirPK tAppartenirPK;
    @Column(name = "DATEFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datefin;
    @JoinColumn(name = "APPARTENIR_UTIL_LOGIN", referencedColumnName = "UTIL_LOGIN", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TUtilisateur tUtilisateur;
    @JoinColumn(name = "APPARTENIR_CENTR_IMP_CODE", referencedColumnName = "CENTR_IMP_CODE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TCentreImpot tCentreImpot;

    public TAppartenir() {
    }

    public TAppartenir(TAppartenirPK tAppartenirPK) {
        this.tAppartenirPK = tAppartenirPK;
    }

    public TAppartenir(String appartenirCentrImpCode, String appartenirUtilLogin, Date appartenirDatedebut) {
        this.tAppartenirPK = new TAppartenirPK(appartenirCentrImpCode, appartenirUtilLogin, appartenirDatedebut);
    }

    public TAppartenirPK getTAppartenirPK() {
        return tAppartenirPK;
    }

    public void setTAppartenirPK(TAppartenirPK tAppartenirPK) {
        this.tAppartenirPK = tAppartenirPK;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public TUtilisateur getTUtilisateur() {
        return tUtilisateur;
    }

    public void setTUtilisateur(TUtilisateur tUtilisateur) {
        this.tUtilisateur = tUtilisateur;
    }

    public TCentreImpot getTCentreImpot() {
        return tCentreImpot;
    }

    public void setTCentreImpot(TCentreImpot tCentreImpot) {
        this.tCentreImpot = tCentreImpot;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tAppartenirPK != null ? tAppartenirPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TAppartenir)) {
            return false;
        }
        TAppartenir other = (TAppartenir) object;
        if ((this.tAppartenirPK == null && other.tAppartenirPK != null) || (this.tAppartenirPK != null && !this.tAppartenirPK.equals(other.tAppartenirPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TAppartenir[ tAppartenirPK=" + tAppartenirPK + " ]";
    }
    
}
