/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ben
 */
@Embeddable
public class TParticiperPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "par_opv_numero")
    private String parOpvNumero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "par_rov_code")
    private String parRovCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "par_int_numero")
    private String parIntNumero;

    public TParticiperPK() {
    }

    public TParticiperPK(String parOpvNumero, String parRovCode, String parIntNumero) {
        this.parOpvNumero = parOpvNumero;
        this.parRovCode = parRovCode;
        this.parIntNumero = parIntNumero;
    }

    public String getParOpvNumero() {
        return parOpvNumero;
    }

    public void setParOpvNumero(String parOpvNumero) {
        this.parOpvNumero = parOpvNumero;
    }

    public String getParRovCode() {
        return parRovCode;
    }

    public void setParRovCode(String parRovCode) {
        this.parRovCode = parRovCode;
    }

    public String getParIntNumero() {
        return parIntNumero;
    }

    public void setParIntNumero(String parIntNumero) {
        this.parIntNumero = parIntNumero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parOpvNumero != null ? parOpvNumero.hashCode() : 0);
        hash += (parRovCode != null ? parRovCode.hashCode() : 0);
        hash += (parIntNumero != null ? parIntNumero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TParticiperPK)) {
            return false;
        }
        TParticiperPK other = (TParticiperPK) object;
        if ((this.parOpvNumero == null && other.parOpvNumero != null) || (this.parOpvNumero != null && !this.parOpvNumero.equals(other.parOpvNumero))) {
            return false;
        }
        if ((this.parRovCode == null && other.parRovCode != null) || (this.parRovCode != null && !this.parRovCode.equals(other.parRovCode))) {
            return false;
        }
        if ((this.parIntNumero == null && other.parIntNumero != null) || (this.parIntNumero != null && !this.parIntNumero.equals(other.parIntNumero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TParticiperPK[ parOpvNumero=" + parOpvNumero + ", parRovCode=" + parRovCode + ", parIntNumero=" + parIntNumero + " ]";
    }
    
}
