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
@Table(name = "t_ethnie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TEthnie.findAll", query = "SELECT t FROM TEthnie t")
    , @NamedQuery(name = "TEthnie.findByEthCode", query = "SELECT t FROM TEthnie t WHERE t.ethCode = :ethCode")
    , @NamedQuery(name = "TEthnie.findByEthDesig", query = "SELECT t FROM TEthnie t WHERE t.ethDesig = :ethDesig")})
public class TEthnie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "eth_code")
    private String ethCode;
    @Size(max = 100)
    @Column(name = "eth_desig")
    private String ethDesig;
    @OneToMany(mappedBy = "intEthCode")
    private List<TIntervenant> tIntervenantList;

    public TEthnie() {
    }

    public TEthnie(String ethCode) {
        this.ethCode = ethCode;
    }

    public String getEthCode() {
        return ethCode;
    }

    public void setEthCode(String ethCode) {
        this.ethCode = ethCode;
    }

    public String getEthDesig() {
        return ethDesig;
    }

    public void setEthDesig(String ethDesig) {
        this.ethDesig = ethDesig;
    }

    @XmlTransient
    public List<TIntervenant> getTIntervenantList() {
        return tIntervenantList;
    }

    public void setTIntervenantList(List<TIntervenant> tIntervenantList) {
        this.tIntervenantList = tIntervenantList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ethCode != null ? ethCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TEthnie)) {
            return false;
        }
        TEthnie other = (TEthnie) object;
        if ((this.ethCode == null && other.ethCode != null) || (this.ethCode != null && !this.ethCode.equals(other.ethCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TEthnie[ ethCode=" + ethCode + " ]";
    }
    
}
