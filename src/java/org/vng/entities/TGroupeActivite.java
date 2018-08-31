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
@Table(name = "t_groupe_activite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TGroupeActivite.findAll", query = "SELECT t FROM TGroupeActivite t")
    , @NamedQuery(name = "TGroupeActivite.findByGacCode", query = "SELECT t FROM TGroupeActivite t WHERE t.gacCode = :gacCode")
    , @NamedQuery(name = "TGroupeActivite.findByGacDesig", query = "SELECT t FROM TGroupeActivite t WHERE t.gacDesig = :gacDesig")})
public class TGroupeActivite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "gac_code")
    private String gacCode;
    @Size(max = 200)
    @Column(name = "gac_desig")
    private String gacDesig;
    @OneToMany(mappedBy = "actGacCode")
    private List<TActivite> tActiviteList;

    public TGroupeActivite() {
    }

    public TGroupeActivite(String gacCode) {
        this.gacCode = gacCode;
    }

    public String getGacCode() {
        return gacCode;
    }

    public void setGacCode(String gacCode) {
        this.gacCode = gacCode;
    }

    public String getGacDesig() {
        return gacDesig;
    }

    public void setGacDesig(String gacDesig) {
        this.gacDesig = gacDesig;
    }

    @XmlTransient
    public List<TActivite> getTActiviteList() {
        return tActiviteList;
    }

    public void setTActiviteList(List<TActivite> tActiviteList) {
        this.tActiviteList = tActiviteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gacCode != null ? gacCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TGroupeActivite)) {
            return false;
        }
        TGroupeActivite other = (TGroupeActivite) object;
        if ((this.gacCode == null && other.gacCode != null) || (this.gacCode != null && !this.gacCode.equals(other.gacCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TGroupeActivite[ gacCode=" + gacCode + " ]";
    }
    
}
