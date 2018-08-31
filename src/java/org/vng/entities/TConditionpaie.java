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
@Table(name = "t_conditionpaie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TConditionpaie.findAll", query = "SELECT t FROM TConditionpaie t")
    , @NamedQuery(name = "TConditionpaie.findByCopCode", query = "SELECT t FROM TConditionpaie t WHERE t.copCode = :copCode")
    , @NamedQuery(name = "TConditionpaie.findByCopDesig", query = "SELECT t FROM TConditionpaie t WHERE t.copDesig = :copDesig")})
public class TConditionpaie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "cop_code")
    private String copCode;
    @Size(max = 100)
    @Column(name = "cop_desig")
    private String copDesig;
    @OneToMany(mappedBy = "opvCopCode")
    private List<TOperationParcel> tOperationParcelList;
    @OneToMany(mappedBy = "invCopCode")
    private List<TIntervenir> tIntervenirList;

    public TConditionpaie() {
    }

    public TConditionpaie(String copCode) {
        this.copCode = copCode;
    }

    public String getCopCode() {
        return copCode;
    }

    public void setCopCode(String copCode) {
        this.copCode = copCode;
    }

    public String getCopDesig() {
        return copDesig;
    }

    public void setCopDesig(String copDesig) {
        this.copDesig = copDesig;
    }

    @XmlTransient
    public List<TOperationParcel> getTOperationParcelList() {
        return tOperationParcelList;
    }

    public void setTOperationParcelList(List<TOperationParcel> tOperationParcelList) {
        this.tOperationParcelList = tOperationParcelList;
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
        hash += (copCode != null ? copCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TConditionpaie)) {
            return false;
        }
        TConditionpaie other = (TConditionpaie) object;
        if ((this.copCode == null && other.copCode != null) || (this.copCode != null && !this.copCode.equals(other.copCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TConditionpaie[ copCode=" + copCode + " ]";
    }
    
}
