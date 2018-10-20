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
@Table(name = "t_type_domaine_parcel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTypeDomaineParcel.findAll", query = "SELECT t FROM TTypeDomaineParcel t")
    , @NamedQuery(name = "TTypeDomaineParcel.findByTydoCode", query = "SELECT t FROM TTypeDomaineParcel t WHERE t.tydoCode = :tydoCode")
    , @NamedQuery(name = "TTypeDomaineParcel.findByTydoLib", query = "SELECT t FROM TTypeDomaineParcel t WHERE t.tydoLib = :tydoLib")})
public class TTypeDomaineParcel implements Serializable {

    @OneToMany(mappedBy = "staTydoCode")
    private List<TStatut> tStatutList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "tydo_code")
    private String tydoCode;
    @Size(max = 100)
    @Column(name = "tydo_lib")
    private String tydoLib;

    public TTypeDomaineParcel() {
    }

    public TTypeDomaineParcel(String tydoCode) {
        this.tydoCode = tydoCode;
    }

    public String getTydoCode() {
        return tydoCode;
    }

    public void setTydoCode(String tydoCode) {
        this.tydoCode = tydoCode;
    }

    public String getTydoLib() {
        return tydoLib;
    }

    public void setTydoLib(String tydoLib) {
        this.tydoLib = tydoLib;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tydoCode != null ? tydoCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTypeDomaineParcel)) {
            return false;
        }
        TTypeDomaineParcel other = (TTypeDomaineParcel) object;
        if ((this.tydoCode == null && other.tydoCode != null) || (this.tydoCode != null && !this.tydoCode.equals(other.tydoCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TTypeDomaineParcel[ tydoCode=" + tydoCode + " ]";
    }

    @XmlTransient
    public List<TStatut> getTStatutList() {
        return tStatutList;
    }

    public void setTStatutList(List<TStatut> tStatutList) {
        this.tStatutList = tStatutList;
    }

}
