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
@Table(name = "t_mode_partage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TModePartage.findAll", query = "SELECT t FROM TModePartage t")
    , @NamedQuery(name = "TModePartage.findByMopCode", query = "SELECT t FROM TModePartage t WHERE t.mopCode = :mopCode")
    , @NamedQuery(name = "TModePartage.findByMopLib", query = "SELECT t FROM TModePartage t WHERE t.mopLib = :mopLib")})
public class TModePartage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "mop_code")
    private String mopCode;
    @Size(max = 100)
    @Column(name = "mop_lib")
    private String mopLib;
    @OneToMany(mappedBy = "opvMopCode")
    private List<TOperationParcel> tOperationParcelList;

    public TModePartage() {
    }

    public TModePartage(String mopCode) {
        this.mopCode = mopCode;
    }

    public String getMopCode() {
        return mopCode;
    }

    public void setMopCode(String mopCode) {
        this.mopCode = mopCode;
    }

    public String getMopLib() {
        return mopLib;
    }

    public void setMopLib(String mopLib) {
        this.mopLib = mopLib;
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
        hash += (mopCode != null ? mopCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TModePartage)) {
            return false;
        }
        TModePartage other = (TModePartage) object;
        if ((this.mopCode == null && other.mopCode != null) || (this.mopCode != null && !this.mopCode.equals(other.mopCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TModePartage[ mopCode=" + mopCode + " ]";
    }
    
}
