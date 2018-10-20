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
 * @author Dev_DI
 */
@Embeddable
public class TParcellePocaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "papo_poca_code")
    private String papoPocaCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "papo_pba_numero")
    private String papoPbaNumero;

    public TParcellePocaPK() {
    }

    public TParcellePocaPK(String papoPocaCode, String papoPbaNumero) {
        this.papoPocaCode = papoPocaCode;
        this.papoPbaNumero = papoPbaNumero;
    }

    public String getPapoPocaCode() {
        return papoPocaCode;
    }

    public void setPapoPocaCode(String papoPocaCode) {
        this.papoPocaCode = papoPocaCode;
    }

    public String getPapoPbaNumero() {
        return papoPbaNumero;
    }

    public void setPapoPbaNumero(String papoPbaNumero) {
        this.papoPbaNumero = papoPbaNumero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (papoPocaCode != null ? papoPocaCode.hashCode() : 0);
        hash += (papoPbaNumero != null ? papoPbaNumero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TParcellePocaPK)) {
            return false;
        }
        TParcellePocaPK other = (TParcellePocaPK) object;
        if ((this.papoPocaCode == null && other.papoPocaCode != null) || (this.papoPocaCode != null && !this.papoPocaCode.equals(other.papoPocaCode))) {
            return false;
        }
        if ((this.papoPbaNumero == null && other.papoPbaNumero != null) || (this.papoPbaNumero != null && !this.papoPbaNumero.equals(other.papoPbaNumero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TParcellePocaPK[ papoPocaCode=" + papoPocaCode + ", papoPbaNumero=" + papoPbaNumero + " ]";
    }
    
}
