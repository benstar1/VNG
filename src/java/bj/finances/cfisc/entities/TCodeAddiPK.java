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
public class TCodeAddiPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CP3_COD")
    private String cp3Cod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALID_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validFrom;

    public TCodeAddiPK() {
    }

    public TCodeAddiPK(String cp3Cod, Date validFrom) {
        this.cp3Cod = cp3Cod;
        this.validFrom = validFrom;
    }

    public String getCp3Cod() {
        return cp3Cod;
    }

    public void setCp3Cod(String cp3Cod) {
        this.cp3Cod = cp3Cod;
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
        hash += (cp3Cod != null ? cp3Cod.hashCode() : 0);
        hash += (validFrom != null ? validFrom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCodeAddiPK)) {
            return false;
        }
        TCodeAddiPK other = (TCodeAddiPK) object;
        if ((this.cp3Cod == null && other.cp3Cod != null) || (this.cp3Cod != null && !this.cp3Cod.equals(other.cp3Cod))) {
            return false;
        }
        if ((this.validFrom == null && other.validFrom != null) || (this.validFrom != null && !this.validFrom.equals(other.validFrom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TCodeAddiPK[ cp3Cod=" + cp3Cod + ", validFrom=" + validFrom + " ]";
    }
    
}
