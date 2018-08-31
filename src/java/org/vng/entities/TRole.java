/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.entities;

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
 * @author Ben
 */
@Entity
@Table(name = "t_role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRole.findAll", query = "SELECT t FROM TRole t")
    , @NamedQuery(name = "TRole.findByRolCode", query = "SELECT t FROM TRole t WHERE t.rolCode = :rolCode")
    , @NamedQuery(name = "TRole.findByRolDesig", query = "SELECT t FROM TRole t WHERE t.rolDesig = :rolDesig")
    , @NamedQuery(name = "TRole.findByRolCat", query = "SELECT t FROM TRole t WHERE t.rolCat = :rolCat")})
public class TRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "rol_code")
    private String rolCode;
    @Size(max = 100)
    @Column(name = "rol_desig")
    private String rolDesig;
    @Size(max = 10)
    @Column(name = "rol_cat")
    private String rolCat;
    @OneToMany(mappedBy = "opvRolCode")
    private List<TOperationParcel> tOperationParcelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dreRolCode")
    private List<TDroitExerce> tDroitExerceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invRolCode")
    private List<TIntervenir> tIntervenirList;

    public TRole() {
    }

    public TRole(String rolCode) {
        this.rolCode = rolCode;
    }

    public String getRolCode() {
        return rolCode;
    }

    public void setRolCode(String rolCode) {
        this.rolCode = rolCode;
    }

    public String getRolDesig() {
        return rolDesig;
    }

    public void setRolDesig(String rolDesig) {
        this.rolDesig = rolDesig;
    }

    public String getRolCat() {
        return rolCat;
    }

    public void setRolCat(String rolCat) {
        this.rolCat = rolCat;
    }

    @XmlTransient
    public List<TOperationParcel> getTOperationParcelList() {
        return tOperationParcelList;
    }

    public void setTOperationParcelList(List<TOperationParcel> tOperationParcelList) {
        this.tOperationParcelList = tOperationParcelList;
    }

    @XmlTransient
    public List<TDroitExerce> getTDroitExerceList() {
        return tDroitExerceList;
    }

    public void setTDroitExerceList(List<TDroitExerce> tDroitExerceList) {
        this.tDroitExerceList = tDroitExerceList;
    }

    @XmlTransient
    public List<TIntervenir> getTIntervenirList() {
        return tIntervenirList;
    }

    public void setTIntervenirList(List<TIntervenir> tIntervenirList) {
        this.tIntervenirList = tIntervenirList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolCode != null ? rolCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRole)) {
            return false;
        }
        TRole other = (TRole) object;
        if ((this.rolCode == null && other.rolCode != null) || (this.rolCode != null && !this.rolCode.equals(other.rolCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TRole[ rolCode=" + rolCode + " ]";
    }
    
}
