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
import javax.persistence.ManyToOne;
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
@Table(name = "t_village")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TVillage.findAll", query = "SELECT t FROM TVillage t")
    , @NamedQuery(name = "TVillage.findByVilaCode", query = "SELECT t FROM TVillage t WHERE t.vilaCode = :vilaCode")
    , @NamedQuery(name = "TVillage.findByVilaArro", query = "SELECT t FROM TVillage t WHERE t.vilaArrCode.arrCode = :arroCode")
    , @NamedQuery(name = "TVillage.findByVlaDesig", query = "SELECT t FROM TVillage t WHERE t.vlaDesig = :vlaDesig")})
public class TVillage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "vila_code")
    private String vilaCode;
    @Size(max = 100)
    @Column(name = "vla_desig")
    private String vlaDesig;
    @OneToMany(mappedBy = "pbaVilaCode")
    private List<TParcelleBafon> tParcelleBafonList;
    @OneToMany(mappedBy = "intVilaCode")
    private List<TIntervenant> tIntervenantList;
    @JoinColumn(name = "vila_arr_code", referencedColumnName = "arr_code")
    @ManyToOne(optional = false)
    private TArrondissement vilaArrCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "affVilaCode")
    private List<TAffecter> tAffecterList;

    public TVillage() {
    }

    public TVillage(String vilaCode) {
        this.vilaCode = vilaCode;
    }

    public String getVilaCode() {
        return vilaCode;
    }

    public void setVilaCode(String vilaCode) {
        this.vilaCode = vilaCode;
    }

    public String getVlaDesig() {
        return vlaDesig;
    }

    public void setVlaDesig(String vlaDesig) {
        this.vlaDesig = vlaDesig;
    }

    @XmlTransient
    public List<TParcelleBafon> getTParcelleBafonList() {
        return tParcelleBafonList;
    }

    public void setTParcelleBafonList(List<TParcelleBafon> tParcelleBafonList) {
        this.tParcelleBafonList = tParcelleBafonList;
    }

    @XmlTransient
    public List<TIntervenant> getTIntervenantList() {
        return tIntervenantList;
    }

    public void setTIntervenantList(List<TIntervenant> tIntervenantList) {
        this.tIntervenantList = tIntervenantList;
    }

    public TArrondissement getVilaArrCode() {
        return vilaArrCode;
    }

    public void setVilaArrCode(TArrondissement vilaArrCode) {
        this.vilaArrCode = vilaArrCode;
    }

    @XmlTransient
    public List<TAffecter> getTAffecterList() {
        return tAffecterList;
    }

    public void setTAffecterList(List<TAffecter> tAffecterList) {
        this.tAffecterList = tAffecterList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vilaCode != null ? vilaCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TVillage)) {
            return false;
        }
        TVillage other = (TVillage) object;
        if ((this.vilaCode == null && other.vilaCode != null) || (this.vilaCode != null && !this.vilaCode.equals(other.vilaCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TVillage[ vilaCode=" + vilaCode + " ]";
    }
    
}
