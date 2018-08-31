/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
 * @author Ben
 */
@Entity
@Table(name = "t_typeutil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTypeutil.findAll", query = "SELECT t FROM TTypeutil t")
    , @NamedQuery(name = "TTypeutil.findByTyuCode", query = "SELECT t FROM TTypeutil t WHERE t.tyuCode = :tyuCode")
    , @NamedQuery(name = "TTypeutil.findByTyuDesig", query = "SELECT t FROM TTypeutil t WHERE t.tyuDesig = :tyuDesig")})
public class TTypeutil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "tyu_code")
    private String tyuCode;
    @Size(max = 100)
    @Column(name = "tyu_desig")
    private String tyuDesig;
    @OneToMany(mappedBy = "utiTyuCode")
    private List<TUtilisateur> tUtilisateurList;

    public TTypeutil() {
    }

    public TTypeutil(String tyuCode) {
        this.tyuCode = tyuCode;
    }

    public String getTyuCode() {
        return tyuCode;
    }

    public void setTyuCode(String tyuCode) {
        this.tyuCode = tyuCode;
    }

    public String getTyuDesig() {
        return tyuDesig;
    }

    public void setTyuDesig(String tyuDesig) {
        this.tyuDesig = tyuDesig;
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
        hash += (tyuCode != null ? tyuCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTypeutil)) {
            return false;
        }
        TTypeutil other = (TTypeutil) object;
        if ((this.tyuCode == null && other.tyuCode != null) || (this.tyuCode != null && !this.tyuCode.equals(other.tyuCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TTypeutil[ tyuCode=" + tyuCode + " ]";
    }
    
}
