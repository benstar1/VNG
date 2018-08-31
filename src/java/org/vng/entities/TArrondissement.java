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
@Table(name = "t_arrondissement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TArrondissement.findAll", query = "SELECT t FROM TArrondissement t")
    , @NamedQuery(name = "TArrondissement.findByArrCode", query = "SELECT t FROM TArrondissement t WHERE t.arrCode = :arrCode")
    , @NamedQuery(name = "TArrondissement.findByArrDesig", query = "SELECT t FROM TArrondissement t WHERE t.arrDesig = :arrDesig")})
public class TArrondissement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "arr_code")
    private String arrCode;
    @Size(max = 100)
    @Column(name = "arr_desig")
    private String arrDesig;
    @JoinColumn(name = "arr_com_code", referencedColumnName = "com_code")
    @ManyToOne
    private TCommune arrComCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vilaArrCode")
    private List<TVillage> tVillageList;

    public TArrondissement() {
    }

    public TArrondissement(String arrCode) {
        this.arrCode = arrCode;
    }

    public String getArrCode() {
        return arrCode;
    }

    public void setArrCode(String arrCode) {
        this.arrCode = arrCode;
    }

    public String getArrDesig() {
        return arrDesig;
    }

    public void setArrDesig(String arrDesig) {
        this.arrDesig = arrDesig;
    }

    public TCommune getArrComCode() {
        return arrComCode;
    }

    public void setArrComCode(TCommune arrComCode) {
        this.arrComCode = arrComCode;
    }

    @XmlTransient
    public List<TVillage> getTVillageList() {
        return tVillageList;
    }

    public void setTVillageList(List<TVillage> tVillageList) {
        this.tVillageList = tVillageList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (arrCode != null ? arrCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TArrondissement)) {
            return false;
        }
        TArrondissement other = (TArrondissement) object;
        if ((this.arrCode == null && other.arrCode != null) || (this.arrCode != null && !this.arrCode.equals(other.arrCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TArrondissement[ arrCode=" + arrCode + " ]";
    }
    
}
