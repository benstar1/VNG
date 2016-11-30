/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

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
 * @author HP
 */
@Entity
@Table(name = "T_DEPARTEMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TDepartement.findAll", query = "SELECT t FROM TDepartement t"),
    @NamedQuery(name = "TDepartement.findByDepCode", query = "SELECT t FROM TDepartement t WHERE t.depCode = :depCode"),
    @NamedQuery(name = "TDepartement.findByDepLibelle", query = "SELECT t FROM TDepartement t WHERE t.depLibelle = :depLibelle")})
public class TDepartement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "DEP_CODE")
    private String depCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "DEP_LIBELLE")
    private String depLibelle;
    @OneToMany(mappedBy = "centrDepCode")
    private List<TCentreImpot> tCentreImpotList;

    public TDepartement() {
    }

    public TDepartement(String depCode) {
        this.depCode = depCode;
    }

    public TDepartement(String depCode, String depLibelle) {
        this.depCode = depCode;
        this.depLibelle = depLibelle;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getDepLibelle() {
        return depLibelle;
    }

    public void setDepLibelle(String depLibelle) {
        this.depLibelle = depLibelle;
    }

    @XmlTransient
    public List<TCentreImpot> getTCentreImpotList() {
        return tCentreImpotList;
    }

    public void setTCentreImpotList(List<TCentreImpot> tCentreImpotList) {
        this.tCentreImpotList = tCentreImpotList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (depCode != null ? depCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TDepartement)) {
            return false;
        }
        TDepartement other = (TDepartement) object;
        if ((this.depCode == null && other.depCode != null) || (this.depCode != null && !this.depCode.equals(other.depCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TDepartement[ depCode=" + depCode + " ]";
    }
    
}
