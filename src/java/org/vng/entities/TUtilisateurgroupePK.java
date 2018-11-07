/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author AAKAKPO
 */
@Embeddable
public class TUtilisateurgroupePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ugr_groupe")
    private String ugrGroupe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ugr_login")
    private String ugrLogin;

    public TUtilisateurgroupePK() {
    }

    public TUtilisateurgroupePK(String ugrGroupe, String ugrLogin) {
        this.ugrGroupe = ugrGroupe;
        this.ugrLogin = ugrLogin;
    }

    public String getUgrGroupe() {
        return ugrGroupe;
    }

    public void setUgrGroupe(String ugrGroupe) {
        this.ugrGroupe = ugrGroupe;
    }

    public String getUgrLogin() {
        return ugrLogin;
    }

    public void setUgrLogin(String ugrLogin) {
        this.ugrLogin = ugrLogin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ugrGroupe != null ? ugrGroupe.hashCode() : 0);
        hash += (ugrLogin != null ? ugrLogin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUtilisateurgroupePK)) {
            return false;
        }
        TUtilisateurgroupePK other = (TUtilisateurgroupePK) object;
        if ((this.ugrGroupe == null && other.ugrGroupe != null) || (this.ugrGroupe != null && !this.ugrGroupe.equals(other.ugrGroupe))) {
            return false;
        }
        if ((this.ugrLogin == null && other.ugrLogin != null) || (this.ugrLogin != null && !this.ugrLogin.equals(other.ugrLogin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TUtilisateurgroupePK[ ugrGroupe=" + ugrGroupe + ", ugrLogin=" + ugrLogin + " ]";
    }
    
}
