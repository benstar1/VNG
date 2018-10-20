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
@Table(name = "t_typebf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTypebf.findAll", query = "SELECT t FROM TTypebf t")
    , @NamedQuery(name = "TTypebf.findByTbfCode", query = "SELECT t FROM TTypebf t WHERE t.tbfCode = :tbfCode")
    , @NamedQuery(name = "TTypebf.findByTbfDesig", query = "SELECT t FROM TTypebf t WHERE t.tbfDesig = :tbfDesig")})
public class TTypebf implements Serializable {

    @OneToMany(mappedBy = "patyTbfCode")
    private List<TParcelleTypeBf> tParcelleTypeBfList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "tbf_code")
    private String tbfCode;
    @Size(max = 100)
    @Column(name = "tbf_desig")
    private String tbfDesig;
    @OneToMany(mappedBy = "pbaTbfCode")
    private List<TParcelleBafon> tParcelleBafonList;

    public TTypebf() {
    }

    public TTypebf(String tbfCode) {
        this.tbfCode = tbfCode;
    }

    public String getTbfCode() {
        return tbfCode;
    }

    public void setTbfCode(String tbfCode) {
        this.tbfCode = tbfCode;
    }

    public String getTbfDesig() {
        return tbfDesig;
    }

    public void setTbfDesig(String tbfDesig) {
        this.tbfDesig = tbfDesig;
    }

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
        hash += (tbfCode != null ? tbfCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTypebf)) {
            return false;
        }
        TTypebf other = (TTypebf) object;
        if ((this.tbfCode == null && other.tbfCode != null) || (this.tbfCode != null && !this.tbfCode.equals(other.tbfCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TTypebf[ tbfCode=" + tbfCode + " ]";
    }

    @XmlTransient
    public List<TParcelleTypeBf> getTParcelleTypeBfList() {
        return tParcelleTypeBfList;
    }

    public void setTParcelleTypeBfList(List<TParcelleTypeBf> tParcelleTypeBfList) {
        this.tParcelleTypeBfList = tParcelleTypeBfList;
    }
    
}
