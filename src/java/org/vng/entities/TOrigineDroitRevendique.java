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
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Ben
 */
@Entity
@Table(name = "t_origine_droit_revendique")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TOrigineDroitRevendique.findAll", query = "SELECT t FROM TOrigineDroitRevendique t")
    , @NamedQuery(name = "TOrigineDroitRevendique.findByOriCode", query = "SELECT t FROM TOrigineDroitRevendique t WHERE t.oriCode = :oriCode")
    , @NamedQuery(name = "TOrigineDroitRevendique.findByOriLib", query = "SELECT t FROM TOrigineDroitRevendique t WHERE t.oriLib = :oriLib")})
public class TOrigineDroitRevendique implements Serializable {

    @OneToMany(mappedBy = "divOriginDroit")
    private List<TDivergence> tDivergenceList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "ori_code")
    private String oriCode;
    @Size(max = 200)
    @Column(name = "ori_lib")
    private String oriLib;

    public TOrigineDroitRevendique() {
    }

    public TOrigineDroitRevendique(String oriCode) {
        this.oriCode = oriCode;
    }

    public String getOriCode() {
        return oriCode;
    }

    public void setOriCode(String oriCode) {
        this.oriCode = oriCode;
    }

    public String getOriLib() {
        return oriLib;
    }

    public void setOriLib(String oriLib) {
        this.oriLib = oriLib;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oriCode != null ? oriCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TOrigineDroitRevendique)) {
            return false;
        }
        TOrigineDroitRevendique other = (TOrigineDroitRevendique) object;
        if ((this.oriCode == null && other.oriCode != null) || (this.oriCode != null && !this.oriCode.equals(other.oriCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TOrigineDroitRevendique[ oriCode=" + oriCode + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public List<TDivergence> getTDivergenceList() {
        return tDivergenceList;
    }

    public void setTDivergenceList(List<TDivergence> tDivergenceList) {
        this.tDivergenceList = tDivergenceList;
    }
    
}
