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
public class TRegimesPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CP4_COD")
    private String cp4Cod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALID_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validFrom;

    public TRegimesPK() {
    }

    public TRegimesPK(String cp4Cod, Date validFrom) {
        this.cp4Cod = cp4Cod;
        this.validFrom = validFrom;
    }

    public String getCp4Cod() {
        return cp4Cod;
    }

    public void setCp4Cod(String cp4Cod) {
        this.cp4Cod = cp4Cod;
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
        hash += (cp4Cod != null ? cp4Cod.hashCode() : 0);
        hash += (validFrom != null ? validFrom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRegimesPK)) {
            return false;
        }
        TRegimesPK other = (TRegimesPK) object;
        if ((this.cp4Cod == null && other.cp4Cod != null) || (this.cp4Cod != null && !this.cp4Cod.equals(other.cp4Cod))) {
            return false;
        }
        if ((this.validFrom == null && other.validFrom != null) || (this.validFrom != null && !this.validFrom.equals(other.validFrom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TRegimesPK[ cp4Cod=" + cp4Cod + ", validFrom=" + validFrom + " ]";
    }
    
}
