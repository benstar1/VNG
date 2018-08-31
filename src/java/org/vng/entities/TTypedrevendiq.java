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
@Table(name = "t_typedrevendiq")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTypedrevendiq.findAll", query = "SELECT t FROM TTypedrevendiq t")
    , @NamedQuery(name = "TTypedrevendiq.findByTyreCode", query = "SELECT t FROM TTypedrevendiq t WHERE t.tyreCode = :tyreCode")
    , @NamedQuery(name = "TTypedrevendiq.findByTyreDesig", query = "SELECT t FROM TTypedrevendiq t WHERE t.tyreDesig = :tyreDesig")})
public class TTypedrevendiq implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "tyre_code")
    private String tyreCode;
    @Size(max = 100)
    @Column(name = "tyre_desig")
    private String tyreDesig;
    @OneToMany(mappedBy = "divTyreCode")
    private List<TDivergence> tDivergenceList;

    public TTypedrevendiq() {
    }

    public TTypedrevendiq(String tyreCode) {
        this.tyreCode = tyreCode;
    }

    public String getTyreCode() {
        return tyreCode;
    }

    public void setTyreCode(String tyreCode) {
        this.tyreCode = tyreCode;
    }

    public String getTyreDesig() {
        return tyreDesig;
    }

    public void setTyreDesig(String tyreDesig) {
        this.tyreDesig = tyreDesig;
    }

    @XmlTransient
    public List<TDivergence> getTDivergenceList() {
        return tDivergenceList;
    }

    public void setTDivergenceList(List<TDivergence> tDivergenceList) {
        this.tDivergenceList = tDivergenceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tyreCode != null ? tyreCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTypedrevendiq)) {
            return false;
        }
        TTypedrevendiq other = (TTypedrevendiq) object;
        if ((this.tyreCode == null && other.tyreCode != null) || (this.tyreCode != null && !this.tyreCode.equals(other.tyreCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TTypedrevendiq[ tyreCode=" + tyreCode + " ]";
    }
    
}
