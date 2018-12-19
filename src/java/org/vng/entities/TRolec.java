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
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Ben
 */
@Entity
@Table(name = "t_rolec")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRolec.findAll", query = "SELECT t FROM TRolec t")
    , @NamedQuery(name = "TRolec.findByRocCode", query = "SELECT t FROM TRolec t WHERE t.rocCode = :rocCode")
    , @NamedQuery(name = "TRolec.findByRocDesig", query = "SELECT t FROM TRolec t WHERE t.rocDesig = :rocDesig")})
public class TRolec implements Serializable {

    @OneToMany(mappedBy = "invRocCode")
    private List<TIntervenir> tIntervenirList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "roc_code")
    private String rocCode;
    @Size(max = 100)
    @Column(name = "roc_desig")
    private String rocDesig;
    @OneToMany(mappedBy = "divRocCode")
    private List<TDivergence> tDivergenceList;

    public TRolec() {
    }

    public TRolec(String rocCode) {
        this.rocCode = rocCode;
    }

    public String getRocCode() {
        return rocCode;
    }

    public void setRocCode(String rocCode) {
        this.rocCode = rocCode;
    }

    public String getRocDesig() {
        return rocDesig;
    }

    public void setRocDesig(String rocDesig) {
        this.rocDesig = rocDesig;
    }

    @XmlTransient
    public List<TDivergence> getTDivergenceList() {
        return tDivergenceList;
    }

    public void setTDivergenceList(List<TDivergence> tDivergenceList) {
        this.tDivergenceList = tDivergenceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rocCode != null ? rocCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRolec)) {
            return false;
        }
        TRolec other = (TRolec) object;
        if ((this.rocCode == null && other.rocCode != null) || (this.rocCode != null && !this.rocCode.equals(other.rocCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TRolec[ rocCode=" + rocCode + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public List<TIntervenir> getTIntervenirList() {
        return tIntervenirList;
    }

    public void setTIntervenirList(List<TIntervenir> tIntervenirList) {
        this.tIntervenirList = tIntervenirList;
    }
    
}
