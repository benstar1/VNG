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
public class TBureauPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "CUO_COD")
    private String cuoCod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALID_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validFrom;

    public TBureauPK() {
    }

    public TBureauPK(String cuoCod, Date validFrom) {
        this.cuoCod = cuoCod;
        this.validFrom = validFrom;
    }

    public String getCuoCod() {
        return cuoCod;
    }

    public void setCuoCod(String cuoCod) {
        this.cuoCod = cuoCod;
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
        hash += (cuoCod != null ? cuoCod.hashCode() : 0);
        hash += (validFrom != null ? validFrom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TBureauPK)) {
            return false;
        }
        TBureauPK other = (TBureauPK) object;
        if ((this.cuoCod == null && other.cuoCod != null) || (this.cuoCod != null && !this.cuoCod.equals(other.cuoCod))) {
            return false;
        }
        if ((this.validFrom == null && other.validFrom != null) || (this.validFrom != null && !this.validFrom.equals(other.validFrom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TBureauPK[ cuoCod=" + cuoCod + ", validFrom=" + validFrom + " ]";
    }
    
}
