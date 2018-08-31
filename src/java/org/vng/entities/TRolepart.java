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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "t_rolepart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRolepart.findAll", query = "SELECT t FROM TRolepart t")
    , @NamedQuery(name = "TRolepart.findByRovCode", query = "SELECT t FROM TRolepart t WHERE t.rovCode = :rovCode")
    , @NamedQuery(name = "TRolepart.findByRovDesig", query = "SELECT t FROM TRolepart t WHERE t.rovDesig = :rovDesig")})
public class TRolepart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "rov_code")
    private String rovCode;
    @Size(max = 100)
    @Column(name = "rov_desig")
    private String rovDesig;
    @JoinTable(name = "t_role_type_operation", joinColumns = {
        @JoinColumn(name = "rt_rov_code", referencedColumnName = "rov_code")}, inverseJoinColumns = {
        @JoinColumn(name = "rt_tyov_code", referencedColumnName = "tyov_code")})
    @ManyToMany
    private List<TTypeOperationParcel> tTypeOperationParcelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tRolepart")
    private List<TParticiper> tParticiperList;

    public TRolepart() {
    }

    public TRolepart(String rovCode) {
        this.rovCode = rovCode;
    }

    public String getRovCode() {
        return rovCode;
    }

    public void setRovCode(String rovCode) {
        this.rovCode = rovCode;
    }

    public String getRovDesig() {
        return rovDesig;
    }

    public void setRovDesig(String rovDesig) {
        this.rovDesig = rovDesig;
    }

    @XmlTransient
    public List<TTypeOperationParcel> getTTypeOperationParcelList() {
        return tTypeOperationParcelList;
    }

    public void setTTypeOperationParcelList(List<TTypeOperationParcel> tTypeOperationParcelList) {
        this.tTypeOperationParcelList = tTypeOperationParcelList;
    }

    @XmlTransient
    public List<TParticiper> getTParticiperList() {
        return tParticiperList;
    }

    public void setTParticiperList(List<TParticiper> tParticiperList) {
        this.tParticiperList = tParticiperList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rovCode != null ? rovCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRolepart)) {
            return false;
        }
        TRolepart other = (TRolepart) object;
        if ((this.rovCode == null && other.rovCode != null) || (this.rovCode != null && !this.rovCode.equals(other.rovCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TRolepart[ rovCode=" + rovCode + " ]";
    }
    
}
