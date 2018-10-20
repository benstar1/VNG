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
@Table(name = "t_statut")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TStatut.findAll", query = "SELECT t FROM TStatut t")
    , @NamedQuery(name = "TStatut.findByStaCode", query = "SELECT t FROM TStatut t WHERE t.staCode = :staCode")
    , @NamedQuery(name = "TStatut.findByStaDesig", query = "SELECT t FROM TStatut t WHERE t.staDesig = :staDesig")
    , @NamedQuery(name = "TStatut.findByStaTydoCode", query = "SELECT t FROM TStatut t WHERE t.staTydoCode = :staTydoCode")})
public class TStatut implements Serializable {

    @JoinColumn(name = "sta_tydo_code", referencedColumnName = "tydo_code")
    @ManyToOne
    private TTypeDomaineParcel staTydoCode;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "sta_code")
    private String staCode;
    @Size(max = 100)
    @Column(name = "sta_desig")
    private String staDesig;
    //@Size(max = 5)
   // @Column(name = "sta_tydo_code")
    //private String staTydoCode;
    @OneToMany(mappedBy = "pbaStaCode")
    private List<TParcelleBafon> tParcelleBafonList;

    public TStatut() {
    }

    public TStatut(String staCode) {
        this.staCode = staCode;
    }

    public String getStaCode() {
        return staCode;
    }

    public void setStaCode(String staCode) {
        this.staCode = staCode;
    }

    public String getStaDesig() {
        return staDesig;
    }

    public void setStaDesig(String staDesig) {
        this.staDesig = staDesig;
    }
/*
    public String getStaTydoCode() {
        return staTydoCode;
    }

    public void setStaTydoCode(String staTydoCode) {
        this.staTydoCode = staTydoCode;
    }
*/
    @XmlTransient
    public List<TParcelleBafon> getTParcelleBafonList() {
        return tParcelleBafonList;
    }

    public void setTParcelleBafonList(List<TParcelleBafon> tParcelleBafonList) {
        this.tParcelleBafonList = tParcelleBafonList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (staCode != null ? staCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TStatut)) {
            return false;
        }
        TStatut other = (TStatut) object;
        if ((this.staCode == null && other.staCode != null) || (this.staCode != null && !this.staCode.equals(other.staCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TStatut[ staCode=" + staCode + " ]";
    }

    public TTypeDomaineParcel getStaTydoCode() {
        return staTydoCode;
    }

    public void setStaTydoCode(TTypeDomaineParcel staTydoCode) {
        this.staTydoCode = staTydoCode;
    }
    
}
