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
@Table(name = "t_type_operation_parcel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTypeOperationParcel.findAll", query = "SELECT t FROM TTypeOperationParcel t")
    , @NamedQuery(name = "TTypeOperationParcel.findByTyovCode", query = "SELECT t FROM TTypeOperationParcel t WHERE t.tyovCode = :tyovCode")
    , @NamedQuery(name = "TTypeOperationParcel.findByTyovLib", query = "SELECT t FROM TTypeOperationParcel t WHERE t.tyovLib = :tyovLib")})
public class TTypeOperationParcel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "tyov_code")
    private String tyovCode;
    @Size(max = 100)
    @Column(name = "tyov_lib")
    private String tyovLib;
    @ManyToMany(mappedBy = "tTypeOperationParcelList")
    private List<TRolepart> tRolepartList;
    @OneToMany(mappedBy = "opvTyovCode")
    private List<TOperationParcel> tOperationParcelList;

    public TTypeOperationParcel() {
    }

    public TTypeOperationParcel(String tyovCode) {
        this.tyovCode = tyovCode;
    }

    public String getTyovCode() {
        return tyovCode;
    }

    public void setTyovCode(String tyovCode) {
        this.tyovCode = tyovCode;
    }

    public String getTyovLib() {
        return tyovLib;
    }

    public void setTyovLib(String tyovLib) {
        this.tyovLib = tyovLib;
    }

    @XmlTransient
    public List<TRolepart> getTRolepartList() {
        return tRolepartList;
    }

    public void setTRolepartList(List<TRolepart> tRolepartList) {
        this.tRolepartList = tRolepartList;
    }

    @XmlTransient
    public List<TOperationParcel> getTOperationParcelList() {
        return tOperationParcelList;
    }

    public void setTOperationParcelList(List<TOperationParcel> tOperationParcelList) {
        this.tOperationParcelList = tOperationParcelList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tyovCode != null ? tyovCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTypeOperationParcel)) {
            return false;
        }
        TTypeOperationParcel other = (TTypeOperationParcel) object;
        if ((this.tyovCode == null && other.tyovCode != null) || (this.tyovCode != null && !this.tyovCode.equals(other.tyovCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TTypeOperationParcel[ tyovCode=" + tyovCode + " ]";
    }
    
}
