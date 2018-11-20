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
@Table(name = "t_commune")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCommune.findAll", query = "SELECT t FROM TCommune t")
    , @NamedQuery(name = "TCommune.findByComCode", query = "SELECT t FROM TCommune t WHERE t.comCode = :comCode")
    , @NamedQuery(name = "TCommune.findByComEncours", query = "SELECT t FROM TCommune t WHERE t.comEncours = true")
    , @NamedQuery(name = "TCommune.findByDepartement", query = "SELECT t FROM TCommune t WHERE t.comDepCode.depCode = :departCode")
    , @NamedQuery(name = "TCommune.findByComDesig", query = "SELECT t FROM TCommune t WHERE t.comDesig = :comDesig")})
public class TCommune implements Serializable {

    @Column(name = "com_encours")
    private Boolean comEncours;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "com_code")
    private String comCode;
    @Size(max = 100)
    @Column(name = "com_desig")
    private String comDesig;
    @OneToMany(mappedBy = "arrComCode")
    private List<TArrondissement> tArrondissementList;
    @JoinColumn(name = "com_dep_code", referencedColumnName = "dep_code")
    @ManyToOne
    private TDepartement comDepCode;

    public TCommune() {
    }

    public TCommune(String comCode) {
        this.comCode = comCode;
    }

    public String getComCode() {
        return comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode;
    }

    public String getComDesig() {
        return comDesig;
    }

    public void setComDesig(String comDesig) {
        this.comDesig = comDesig;
    }

    @XmlTransient
    public List<TArrondissement> getTArrondissementList() {
        return tArrondissementList;
    }

    public void setTArrondissementList(List<TArrondissement> tArrondissementList) {
        this.tArrondissementList = tArrondissementList;
    }

    public TDepartement getComDepCode() {
        return comDepCode;
    }

    public void setComDepCode(TDepartement comDepCode) {
        this.comDepCode = comDepCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comCode != null ? comCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCommune)) {
            return false;
        }
        TCommune other = (TCommune) object;
        if ((this.comCode == null && other.comCode != null) || (this.comCode != null && !this.comCode.equals(other.comCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TCommune[ comCode=" + comCode + " ]";
    }

    public Boolean getComEncours() {
        return comEncours;
    }

    public void setComEncours(Boolean comEncours) {
        this.comEncours = comEncours;
    }
    
}
