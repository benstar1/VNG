/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

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
 * @author HP
 */
@Entity
@Table(name = "T_CENTRE_IMPOT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCentreImpot.findAll", query = "SELECT t FROM TCentreImpot t"),
    @NamedQuery(name = "TCentreImpot.findByCentrImpCode", query = "SELECT t FROM TCentreImpot t WHERE t.centrImpCode = :centrImpCode"),
    @NamedQuery(name = "TCentreImpot.findByCentrImpLibelle", query = "SELECT t FROM TCentreImpot t WHERE t.centrImpLibelle = :centrImpLibelle"),
    @NamedQuery(name = "TCentreImpot.findByCentrDepCode", query = "SELECT t FROM TCentreImpot t WHERE t.centrDepCode = :centrDepCode")})
public class TCentreImpot implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CENTR_IMP_CODE")
    private String centrImpCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CENTR_IMP_LIBELLE")
    private String centrImpLibelle;
    @OneToMany(mappedBy = "contCentrImpCode")
    private List<TRepUnique> tRepUniqueList;
    @JoinColumn(name = "CENTR_DEP_CODE", referencedColumnName = "DEP_CODE")
    @ManyToOne
    private TDepartement centrDepCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tCentreImpot")
    private List<TAppartenir> tAppartenirList;

    public TCentreImpot() {
    }

    public TCentreImpot(String centrImpCode) {
        this.centrImpCode = centrImpCode;
    }

    public TCentreImpot(String centrImpCode, String centrImpLibelle) {
        this.centrImpCode = centrImpCode;
        this.centrImpLibelle = centrImpLibelle;
    }

    public String getCentrImpCode() {
        return centrImpCode;
    }

    public void setCentrImpCode(String centrImpCode) {
        this.centrImpCode = centrImpCode;
    }

    public String getCentrImpLibelle() {
        return centrImpLibelle;
    }

    public void setCentrImpLibelle(String centrImpLibelle) {
        this.centrImpLibelle = centrImpLibelle;
    }

    @XmlTransient
    public List<TRepUnique> getTRepUniqueList() {
        return tRepUniqueList;
    }

    public void setTRepUniqueList(List<TRepUnique> tRepUniqueList) {
        this.tRepUniqueList = tRepUniqueList;
    }

    public TDepartement getCentrDepCode() {
        return centrDepCode;
    }

    public void setCentrDepCode(TDepartement centrDepCode) {
        this.centrDepCode = centrDepCode;
    }

    @XmlTransient
    public List<TAppartenir> getTAppartenirList() {
        return tAppartenirList;
    }

    public void setTAppartenirList(List<TAppartenir> tAppartenirList) {
        this.tAppartenirList = tAppartenirList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (centrImpCode != null ? centrImpCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCentreImpot)) {
            return false;
        }
        TCentreImpot other = (TCentreImpot) object;
        if ((this.centrImpCode == null && other.centrImpCode != null) || (this.centrImpCode != null && !this.centrImpCode.equals(other.centrImpCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TCentreImpot[ centrImpCode=" + centrImpCode + " ]";
    }
    
}
