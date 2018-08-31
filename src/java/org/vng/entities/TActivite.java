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
@Table(name = "t_activite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TActivite.findAll", query = "SELECT t FROM TActivite t")
    , @NamedQuery(name = "TActivite.findByActCode", query = "SELECT t FROM TActivite t WHERE t.actCode = :actCode")
    , @NamedQuery(name = "TActivite.findByActDesig", query = "SELECT t FROM TActivite t WHERE t.actDesig = :actDesig")})
public class TActivite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "act_code")
    private String actCode;
    @Size(max = 300)
    @Column(name = "act_desig")
    private String actDesig;
    @OneToMany(mappedBy = "intActCode")
    private List<TIntervenant> tIntervenantList;
    @JoinColumn(name = "act_gac_code", referencedColumnName = "gac_code")
    @ManyToOne
    private TGroupeActivite actGacCode;

    public TActivite() {
    }

    public TActivite(String actCode) {
        this.actCode = actCode;
    }

    public String getActCode() {
        return actCode;
    }

    public void setActCode(String actCode) {
        this.actCode = actCode;
    }

    public String getActDesig() {
        return actDesig;
    }

    public void setActDesig(String actDesig) {
        this.actDesig = actDesig;
    }

    @XmlTransient
    public List<TIntervenant> getTIntervenantList() {
        return tIntervenantList;
    }

    public void setTIntervenantList(List<TIntervenant> tIntervenantList) {
        this.tIntervenantList = tIntervenantList;
    }

    public TGroupeActivite getActGacCode() {
        return actGacCode;
    }

    public void setActGacCode(TGroupeActivite actGacCode) {
        this.actGacCode = actGacCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actCode != null ? actCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TActivite)) {
            return false;
        }
        TActivite other = (TActivite) object;
        if ((this.actCode == null && other.actCode != null) || (this.actCode != null && !this.actCode.equals(other.actCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TActivite[ actCode=" + actCode + " ]";
    }
    
}
