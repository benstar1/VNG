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
public class TMonnaiePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALID_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validFrom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CUR_COD")
    private String curCod;

    public TMonnaiePK() {
    }

    public TMonnaiePK(Date validFrom, String curCod) {
        this.validFrom = validFrom;
        this.curCod = curCod;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public String getCurCod() {
        return curCod;
    }

    public void setCurCod(String curCod) {
        this.curCod = curCod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (validFrom != null ? validFrom.hashCode() : 0);
        hash += (curCod != null ? curCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TMonnaiePK)) {
            return false;
        }
        TMonnaiePK other = (TMonnaiePK) object;
        if ((this.validFrom == null && other.validFrom != null) || (this.validFrom != null && !this.validFrom.equals(other.validFrom))) {
            return false;
        }
        if ((this.curCod == null && other.curCod != null) || (this.curCod != null && !this.curCod.equals(other.curCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TMonnaiePK[ validFrom=" + validFrom + ", curCod=" + curCod + " ]";
    }
    
}
