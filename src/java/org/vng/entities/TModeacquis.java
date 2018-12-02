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
@Table(name = "t_modeacquis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TModeacquis.findAll", query = "SELECT t FROM TModeacquis t")
    , @NamedQuery(name = "TModeacquis.findByMacCode", query = "SELECT t FROM TModeacquis t WHERE t.macCode = :macCode")
    , @NamedQuery(name = "TModeacquis.findByHeritage", query = "SELECT t FROM TModeacquis t WHERE t.macCode ='MD009' OR t.macCode ='MD002' ")
    , @NamedQuery(name = "TModeacquis.findByOperationnel", query = "SELECT t FROM TModeacquis t WHERE t.macCat ='OP' ")
    , @NamedQuery(name = "TModeacquis.findByMacDesig", query = "SELECT t FROM TModeacquis t WHERE t.macDesig = :macDesig")
    , @NamedQuery(name = "TModeacquis.findByMacCat", query = "SELECT t FROM TModeacquis t WHERE t.macCat = :macCat")})
public class TModeacquis implements Serializable {

    @OneToMany(mappedBy = "adcMacCode")
    private List<TAdc> tAdcList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "mac_code")
    private String macCode;
    @Size(max = 100)
    @Column(name = "mac_desig")
    private String macDesig;
    @Size(max = 5)
    @Column(name = "mac_cat")
    private String macCat;
    @OneToMany(mappedBy = "opvMacCode")
    private List<TOperationParcel> tOperationParcelList;
    @OneToMany(mappedBy = "invMacCode")
    private List<TIntervenir> tIntervenirList;

    public TModeacquis() {
    }

    public TModeacquis(String macCode) {
        this.macCode = macCode;
    }

    public String getMacCode() {
        return macCode;
    }

    public void setMacCode(String macCode) {
        this.macCode = macCode;
    }

    public String getMacDesig() {
        return macDesig;
    }

    public void setMacDesig(String macDesig) {
        this.macDesig = macDesig;
    }

    public String getMacCat() {
        return macCat;
    }

    public void setMacCat(String macCat) {
        this.macCat = macCat;
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
        hash += (macCode != null ? macCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TModeacquis)) {
            return false;
        }
        TModeacquis other = (TModeacquis) object;
        if ((this.macCode == null && other.macCode != null) || (this.macCode != null && !this.macCode.equals(other.macCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TModeacquis[ macCode=" + macCode + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public List<TAdc> getTAdcList() {
        return tAdcList;
    }

    public void setTAdcList(List<TAdc> tAdcList) {
        this.tAdcList = tAdcList;
    }
    
}
