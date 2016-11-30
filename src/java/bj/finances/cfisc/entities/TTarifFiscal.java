/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "T_TARIF_FISCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTarifFiscal.findAll", query = "SELECT t FROM TTarifFiscal t"),
    @NamedQuery(name = "TTarifFiscal.findByTarifCode", query = "SELECT t FROM TTarifFiscal t WHERE t.tarifCode = :tarifCode"),
    @NamedQuery(name = "TTarifFiscal.findByTarifLib", query = "SELECT t FROM TTarifFiscal t WHERE t.tarifLib = :tarifLib")})
public class TTarifFiscal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "TARIF_CODE")
    private String tarifCode;
    @Size(max = 100)
    @Column(name = "TARIF_LIB")
    private String tarifLib;

    public TTarifFiscal() {
    }

    public TTarifFiscal(String tarifCode) {
        this.tarifCode = tarifCode;
    }

    public String getTarifCode() {
        return tarifCode;
    }

    public void setTarifCode(String tarifCode) {
        this.tarifCode = tarifCode;
    }

    public String getTarifLib() {
        return tarifLib;
    }

    public void setTarifLib(String tarifLib) {
        this.tarifLib = tarifLib;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tarifCode != null ? tarifCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTarifFiscal)) {
            return false;
        }
        TTarifFiscal other = (TTarifFiscal) object;
        if ((this.tarifCode == null && other.tarifCode != null) || (this.tarifCode != null && !this.tarifCode.equals(other.tarifCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TTarifFiscal[ tarifCode=" + tarifCode + " ]";
    }
    
}
