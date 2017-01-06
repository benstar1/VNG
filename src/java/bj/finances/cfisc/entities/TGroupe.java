/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "T_GROUPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TGroupe.findAll", query = "SELECT t FROM TGroupe t")
    , @NamedQuery(name = "TGroupe.findByGroupId", query = "SELECT t FROM TGroupe t WHERE t.groupId = :groupId")
    , @NamedQuery(name = "TGroupe.findByGroupName", query = "SELECT t FROM TGroupe t WHERE t.groupName = :groupName")})
public class TGroupe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "GROUP_ID")
    private String groupId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "GROUP_NAME")
    private String groupName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupe")
    private List<TUtilisateur> tUtilisateurList;

    public TGroupe() {
    }

    public TGroupe(String groupId) {
        this.groupId = groupId;
    }

    public TGroupe(String groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @XmlTransient
    public List<TUtilisateur> getTUtilisateurList() {
        return tUtilisateurList;
    }

    public void setTUtilisateurList(List<TUtilisateur> tUtilisateurList) {
        this.tUtilisateurList = tUtilisateurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupId != null ? groupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TGroupe)) {
            return false;
        }
        TGroupe other = (TGroupe) object;
        if ((this.groupId == null && other.groupId != null) || (this.groupId != null && !this.groupId.equals(other.groupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.mef.cfisc.entite.TGroupe[ groupId=" + groupId + " ]";
    }
    
}
