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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "T_UTILISATEUR_GROUPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUtilisateurGroupe.findAll", query = "SELECT t FROM TUtilisateurGroupe t")
    , @NamedQuery(name = "TUtilisateurGroupe.findByLoginName", query = "SELECT t FROM TUtilisateurGroupe t WHERE t.loginName = :loginName")
    , @NamedQuery(name = "TUtilisateurGroupe.findByGroupeName", query = "SELECT t FROM TUtilisateurGroupe t WHERE t.groupeName = :groupeName")})
public class TUtilisateurGroupe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "LOGIN_NAME")
    private String loginName;
    @Size(max = 50)
    @Column(name = "GROUPE_NAME")
    private String groupeName;

    public TUtilisateurGroupe() {
    }

    public TUtilisateurGroupe(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getGroupeName() {
        return groupeName;
    }

    public void setGroupeName(String groupeName) {
        this.groupeName = groupeName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loginName != null ? loginName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUtilisateurGroupe)) {
            return false;
        }
        TUtilisateurGroupe other = (TUtilisateurGroupe) object;
        if ((this.loginName == null && other.loginName != null) || (this.loginName != null && !this.loginName.equals(other.loginName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.mef.cfisc.entite.TUtilisateurGroupe[ loginName=" + loginName + " ]";
    }
    
}
