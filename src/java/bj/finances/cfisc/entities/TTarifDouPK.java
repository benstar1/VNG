/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author HP
 */
@Embeddable
public class TTarifDouPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "HS6_COD")
    private String hs6Cod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALID_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validFrom;

    public TTarifDouPK() {
    }

    public TTarifDouPK(String hs6Cod, Date validFrom) {
        this.hs6Cod = hs6Cod;
        this.validFrom = validFrom;
    }

    public String getHs6Cod() {
        return hs6Cod;
    }

    public void setHs6Cod(String hs6Cod) {
        this.hs6Cod = hs6Cod;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hs6Cod != null ? hs6Cod.hashCode() : 0);
        hash += (validFrom != null ? validFrom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTarifDouPK)) {
            return false;
        }
        TTarifDouPK other = (TTarifDouPK) object;
        if ((this.hs6Cod == null && other.hs6Cod != null) || (this.hs6Cod != null && !this.hs6Cod.equals(other.hs6Cod))) {
            return false;
        }
        if ((this.validFrom == null && other.validFrom != null) || (this.validFrom != null && !this.validFrom.equals(other.validFrom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TTarifDouPK[ hs6Cod=" + hs6Cod + ", validFrom=" + validFrom + " ]";
    }
    
}
