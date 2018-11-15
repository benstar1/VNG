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
@Table(name = "t_nature_origine_contestation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TNatureOrigineContestation.findAll", query = "SELECT t FROM TNatureOrigineContestation t")
    , @NamedQuery(name = "TNatureOrigineContestation.findByNaoCode", query = "SELECT t FROM TNatureOrigineContestation t WHERE t.naoCode = :naoCode")
    , @NamedQuery(name = "TNatureOrigineContestation.findByNaoLib", query = "SELECT t FROM TNatureOrigineContestation t WHERE t.naoLib = :naoLib")})
public class TNatureOrigineContestation implements Serializable {

    @OneToMany(mappedBy = "divNatureContest")
    private List<TDivergence> tDivergenceList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "nao_code")
    private String naoCode;
    @Size(max = 200)
    @Column(name = "nao_lib")
    private String naoLib;

    public TNatureOrigineContestation() {
    }

    public TNatureOrigineContestation(String naoCode) {
        this.naoCode = naoCode;
    }

    public String getNaoCode() {
        return naoCode;
    }

    public void setNaoCode(String naoCode) {
        this.naoCode = naoCode;
    }

    public String getNaoLib() {
        return naoLib;
    }

    public void setNaoLib(String naoLib) {
        this.naoLib = naoLib;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (naoCode != null ? naoCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TNatureOrigineContestation)) {
            return false;
        }
        TNatureOrigineContestation other = (TNatureOrigineContestation) object;
        if ((this.naoCode == null && other.naoCode != null) || (this.naoCode != null && !this.naoCode.equals(other.naoCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TNatureOrigineContestation[ naoCode=" + naoCode + " ]";
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
