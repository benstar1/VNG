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
 * @author Ben
 */
@Embeddable
public class TEnqueterPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "eq_enq_code")
    private String eqEnqCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "eq_uti_code")
    private String eqUtiCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eq_date_debut")
    @Temporal(TemporalType.DATE)
    private Date eqDateDebut;

    public TEnqueterPK() {
    }

    public TEnqueterPK(String eqEnqCode, String eqUtiCode, Date eqDateDebut) {
        this.eqEnqCode = eqEnqCode;
        this.eqUtiCode = eqUtiCode;
        this.eqDateDebut = eqDateDebut;
    }

    public String getEqEnqCode() {
        return eqEnqCode;
    }

    public void setEqEnqCode(String eqEnqCode) {
        this.eqEnqCode = eqEnqCode;
    }

    public String getEqUtiCode() {
        return eqUtiCode;
    }

    public void setEqUtiCode(String eqUtiCode) {
        this.eqUtiCode = eqUtiCode;
    }

    public Date getEqDateDebut() {
        return eqDateDebut;
    }

    public void setEqDateDebut(Date eqDateDebut) {
        this.eqDateDebut = eqDateDebut;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eqEnqCode != null ? eqEnqCode.hashCode() : 0);
        hash += (eqUtiCode != null ? eqUtiCode.hashCode() : 0);
        hash += (eqDateDebut != null ? eqDateDebut.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TEnqueterPK)) {
            return false;
        }
        TEnqueterPK other = (TEnqueterPK) object;
        if ((this.eqEnqCode == null && other.eqEnqCode != null) || (this.eqEnqCode != null && !this.eqEnqCode.equals(other.eqEnqCode))) {
            return false;
        }
        if ((this.eqUtiCode == null && other.eqUtiCode != null) || (this.eqUtiCode != null && !this.eqUtiCode.equals(other.eqUtiCode))) {
            return false;
        }
        if ((this.eqDateDebut == null && other.eqDateDebut != null) || (this.eqDateDebut != null && !this.eqDateDebut.equals(other.eqDateDebut))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TEnqueterPK[ eqEnqCode=" + eqEnqCode + ", eqUtiCode=" + eqUtiCode + ", eqDateDebut=" + eqDateDebut + " ]";
    }
    
}
