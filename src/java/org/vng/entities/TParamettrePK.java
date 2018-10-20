/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.entities;

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
 * @author Dev_DI
 */
@Embeddable
public class TParamettrePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "param_code")
    private String paramCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "param_dateeffet")
    @Temporal(TemporalType.DATE)
    private Date paramDateeffet;

    public TParamettrePK() {
    }

    public TParamettrePK(String paramCode, Date paramDateeffet) {
        this.paramCode = paramCode;
        this.paramDateeffet = paramDateeffet;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public Date getParamDateeffet() {
        return paramDateeffet;
    }

    public void setParamDateeffet(Date paramDateeffet) {
        this.paramDateeffet = paramDateeffet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paramCode != null ? paramCode.hashCode() : 0);
        hash += (paramDateeffet != null ? paramDateeffet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TParamettrePK)) {
            return false;
        }
        TParamettrePK other = (TParamettrePK) object;
        if ((this.paramCode == null && other.paramCode != null) || (this.paramCode != null && !this.paramCode.equals(other.paramCode))) {
            return false;
        }
        if ((this.paramDateeffet == null && other.paramDateeffet != null) || (this.paramDateeffet != null && !this.paramDateeffet.equals(other.paramDateeffet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TParamettrePK[ paramCode=" + paramCode + ", paramDateeffet=" + paramDateeffet + " ]";
    }
    
}
