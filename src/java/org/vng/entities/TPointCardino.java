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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "t_point_cardino")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TPointCardino.findAll", query = "SELECT t FROM TPointCardino t")
    , @NamedQuery(name = "TPointCardino.findByPocaCode", query = "SELECT t FROM TPointCardino t WHERE t.pocaCode = :pocaCode")
    , @NamedQuery(name = "TPointCardino.findByPocaDesig", query = "SELECT t FROM TPointCardino t WHERE t.pocaDesig = :pocaDesig")})
public class TPointCardino implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "poca_code")
    private String pocaCode;
    @Size(max = 100)
    @Column(name = "poca_desig")
    private String pocaDesig;
    @JoinTable(name = "t_parcelle_poca", joinColumns = {
        @JoinColumn(name = "papo_poca_code", referencedColumnName = "poca_code")}, inverseJoinColumns = {
        @JoinColumn(name = "papo_pba_numero", referencedColumnName = "pba_numero")})
    @ManyToMany
    private List<TParcelleBafon> tParcelleBafonList;

    public TPointCardino() {
    }

    public TPointCardino(String pocaCode) {
        this.pocaCode = pocaCode;
    }

    public String getPocaCode() {
        return pocaCode;
    }

    public void setPocaCode(String pocaCode) {
        this.pocaCode = pocaCode;
    }

    public String getPocaDesig() {
        return pocaDesig;
    }

    public void setPocaDesig(String pocaDesig) {
        this.pocaDesig = pocaDesig;
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
        hash += (pocaCode != null ? pocaCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TPointCardino)) {
            return false;
        }
        TPointCardino other = (TPointCardino) object;
        if ((this.pocaCode == null && other.pocaCode != null) || (this.pocaCode != null && !this.pocaCode.equals(other.pocaCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TPointCardino[ pocaCode=" + pocaCode + " ]";
    }
    
}
