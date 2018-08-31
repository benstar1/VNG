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
@Table(name = "t_periodicite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TPeriodicite.findAll", query = "SELECT t FROM TPeriodicite t")
    , @NamedQuery(name = "TPeriodicite.findByPerCode", query = "SELECT t FROM TPeriodicite t WHERE t.perCode = :perCode")
    , @NamedQuery(name = "TPeriodicite.findByPerDesig", query = "SELECT t FROM TPeriodicite t WHERE t.perDesig = :perDesig")})
public class TPeriodicite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "per_code")
    private String perCode;
    @Size(max = 100)
    @Column(name = "per_desig")
    private String perDesig;
    @OneToMany(mappedBy = "opvPerCode")
    private List<TOperationParcel> tOperationParcelList;

    public TPeriodicite() {
    }

    public TPeriodicite(String perCode) {
        this.perCode = perCode;
    }

    public String getPerCode() {
        return perCode;
    }

    public void setPerCode(String perCode) {
        this.perCode = perCode;
    }

    public String getPerDesig() {
        return perDesig;
    }

    public void setPerDesig(String perDesig) {
        this.perDesig = perDesig;
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
        hash += (perCode != null ? perCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TPeriodicite)) {
            return false;
        }
        TPeriodicite other = (TPeriodicite) object;
        if ((this.perCode == null && other.perCode != null) || (this.perCode != null && !this.perCode.equals(other.perCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TPeriodicite[ perCode=" + perCode + " ]";
    }
    
}
