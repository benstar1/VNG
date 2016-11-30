/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author HP
 */
@Embeddable
public class TAppartenirPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "APPARTENIR_CENTR_IMP_CODE")
    private String appartenirCentrImpCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "APPARTENIR_UTIL_LOGIN")
    private String appartenirUtilLogin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "APPARTENIR_DATEDEBUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appartenirDatedebut;

    public TAppartenirPK() {
    }

    public TAppartenirPK(String appartenirCentrImpCode, String appartenirUtilLogin, Date appartenirDatedebut) {
        this.appartenirCentrImpCode = appartenirCentrImpCode;
        this.appartenirUtilLogin = appartenirUtilLogin;
        this.appartenirDatedebut = appartenirDatedebut;
    }

    public String getAppartenirCentrImpCode() {
        return appartenirCentrImpCode;
    }

    public void setAppartenirCentrImpCode(String appartenirCentrImpCode) {
        this.appartenirCentrImpCode = appartenirCentrImpCode;
    }

    public String getAppartenirUtilLogin() {
        return appartenirUtilLogin;
    }

    public void setAppartenirUtilLogin(String appartenirUtilLogin) {
        this.appartenirUtilLogin = appartenirUtilLogin;
    }

    public Date getAppartenirDatedebut() {
        return appartenirDatedebut;
    }

    public void setAppartenirDatedebut(Date appartenirDatedebut) {
        this.appartenirDatedebut = appartenirDatedebut;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appartenirCentrImpCode != null ? appartenirCentrImpCode.hashCode() : 0);
        hash += (appartenirUtilLogin != null ? appartenirUtilLogin.hashCode() : 0);
        hash += (appartenirDatedebut != null ? appartenirDatedebut.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TAppartenirPK)) {
            return false;
        }
        TAppartenirPK other = (TAppartenirPK) object;
        if ((this.appartenirCentrImpCode == null && other.appartenirCentrImpCode != null) || (this.appartenirCentrImpCode != null && !this.appartenirCentrImpCode.equals(other.appartenirCentrImpCode))) {
            return false;
        }
        if ((this.appartenirUtilLogin == null && other.appartenirUtilLogin != null) || (this.appartenirUtilLogin != null && !this.appartenirUtilLogin.equals(other.appartenirUtilLogin))) {
            return false;
        }
        if ((this.appartenirDatedebut == null && other.appartenirDatedebut != null) || (this.appartenirDatedebut != null && !this.appartenirDatedebut.equals(other.appartenirDatedebut))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TAppartenirPK[ appartenirCentrImpCode=" + appartenirCentrImpCode + ", appartenirUtilLogin=" + appartenirUtilLogin + ", appartenirDatedebut=" + appartenirDatedebut + " ]";
    }
    
}
