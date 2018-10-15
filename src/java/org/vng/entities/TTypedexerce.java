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
@Table(name = "t_typedexerce")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTypedexerce.findAll", query = "SELECT t FROM TTypedexerce t")
    , @NamedQuery(name = "TTypedexerce.findByTdeCode", query = "SELECT t FROM TTypedexerce t WHERE t.tdeCode = :tdeCode")
    , @NamedQuery(name = "TTypedexerce.findByTdeDesig", query = "SELECT t FROM TTypedexerce t WHERE t.tdeDesig = :tdeDesig")
    , @NamedQuery(name = "TTypedexerce.findByTdeCat", query = "SELECT t FROM TTypedexerce t WHERE t.tdeCat LIKE :tdeCat")
    , @NamedQuery(name = "TTypedexerce.findByTdeCodeParent", query = "SELECT t FROM TTypedexerce t WHERE t.tdeCodeParent = :tdeCodeParent")})
public class TTypedexerce implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "tde_code")
    private String tdeCode;
    @Size(max = 500)
    @Column(name = "tde_desig")
    private String tdeDesig;
    @Size(max = 10)
    @Column(name = "tde_cat")
    private String tdeCat;
    @Size(max = 5)
    @Column(name = "tde_code_parent")
    private String tdeCodeParent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dreTdeCode")
    private List<TDroitExerce> tDroitExerceList;

    public TTypedexerce() {
    }

    public TTypedexerce(String tdeCode) {
        this.tdeCode = tdeCode;
    }

    public String getTdeCode() {
        return tdeCode;
    }

    public void setTdeCode(String tdeCode) {
        this.tdeCode = tdeCode;
    }

    public String getTdeDesig() {
        return tdeDesig;
    }

    public void setTdeDesig(String tdeDesig) {
        this.tdeDesig = tdeDesig;
    }

    public String getTdeCat() {
        return tdeCat;
    }

    public void setTdeCat(String tdeCat) {
        this.tdeCat = tdeCat;
    }

    public String getTdeCodeParent() {
        return tdeCodeParent;
    }

    public void setTdeCodeParent(String tdeCodeParent) {
        this.tdeCodeParent = tdeCodeParent;
    }

    @XmlTransient
    public List<TDroitExerce> getTDroitExerceList() {
        return tDroitExerceList;
    }

    public void setTDroitExerceList(List<TDroitExerce> tDroitExerceList) {
        this.tDroitExerceList = tDroitExerceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tdeCode != null ? tdeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTypedexerce)) {
            return false;
        }
        TTypedexerce other = (TTypedexerce) object;
        if ((this.tdeCode == null && other.tdeCode != null) || (this.tdeCode != null && !this.tdeCode.equals(other.tdeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TTypedexerce[ tdeCode=" + tdeCode + " ]";
    }
    
}
