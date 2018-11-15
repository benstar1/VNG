/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AAKAKPO
 */
@Entity
@Table(name = "t_utilisateurgroupe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUtilisateurgroupe.findAll", query = "SELECT t FROM TUtilisateurgroupe t")
    , @NamedQuery(name = "TUtilisateurgroupe.findByUgrGroupe", query = "SELECT t FROM TUtilisateurgroupe t WHERE t.tUtilisateurgroupePK.ugrGroupe = :ugrGroupe")
    , @NamedQuery(name = "TUtilisateurgroupe.findByUgrLogin", query = "SELECT t FROM TUtilisateurgroupe t WHERE t.tUtilisateurgroupePK.ugrLogin = :ugrLogin")})
public class TUtilisateurgroupe implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TUtilisateurgroupePK tUtilisateurgroupePK;

    public TUtilisateurgroupe() {
    }

    public TUtilisateurgroupe(TUtilisateurgroupePK tUtilisateurgroupePK) {
        this.tUtilisateurgroupePK = tUtilisateurgroupePK;
    }

    public TUtilisateurgroupe(String ugrGroupe, String ugrLogin) {
        this.tUtilisateurgroupePK = new TUtilisateurgroupePK(ugrGroupe, ugrLogin);
    }

    public TUtilisateurgroupePK getTUtilisateurgroupePK() {
        return tUtilisateurgroupePK;
    }

    public void setTUtilisateurgroupePK(TUtilisateurgroupePK tUtilisateurgroupePK) {
        this.tUtilisateurgroupePK = tUtilisateurgroupePK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tUtilisateurgroupePK != null ? tUtilisateurgroupePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUtilisateurgroupe)) {
            return false;
        }
        TUtilisateurgroupe other = (TUtilisateurgroupe) object;
        if ((this.tUtilisateurgroupePK == null && other.tUtilisateurgroupePK != null) || (this.tUtilisateurgroupePK != null && !this.tUtilisateurgroupePK.equals(other.tUtilisateurgroupePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TUtilisateurgroupe[ tUtilisateurgroupePK=" + tUtilisateurgroupePK + " ]";
    }
    
}
