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
@Table(name = "t_naturepapier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TNaturepapier.findAll", query = "SELECT t FROM TNaturepapier t")
    , @NamedQuery(name = "TNaturepapier.findByNapCode", query = "SELECT t FROM TNaturepapier t WHERE t.napCode = :napCode")
    , @NamedQuery(name = "TNaturepapier.findByNapDesig", query = "SELECT t FROM TNaturepapier t WHERE t.napDesig = :napDesig")})
public class TNaturepapier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "nap_code")
    private String napCode;
    @Size(max = 100)
    @Column(name = "nap_desig")
    private String napDesig;
    @OneToMany(mappedBy = "invNatPapier")
    private List<TIntervenir> tIntervenirList;

    public TNaturepapier() {
    }

    public TNaturepapier(String napCode) {
        this.napCode = napCode;
    }

    public String getNapCode() {
        return napCode;
    }

    public void setNapCode(String napCode) {
        this.napCode = napCode;
    }

    public String getNapDesig() {
        return napDesig;
    }

    public void setNapDesig(String napDesig) {
        this.napDesig = napDesig;
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
        hash += (napCode != null ? napCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TNaturepapier)) {
            return false;
        }
        TNaturepapier other = (TNaturepapier) object;
        if ((this.napCode == null && other.napCode != null) || (this.napCode != null && !this.napCode.equals(other.napCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TNaturepapier[ napCode=" + napCode + " ]";
    }
    
}
