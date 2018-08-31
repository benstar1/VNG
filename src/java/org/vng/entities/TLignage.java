/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.entities;

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
 * @author Ben
 */
@Entity
@Table(name = "t_lignage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TLignage.findAll", query = "SELECT t FROM TLignage t")
    , @NamedQuery(name = "TLignage.findByLigCode", query = "SELECT t FROM TLignage t WHERE t.ligCode = :ligCode")
    , @NamedQuery(name = "TLignage.findByLigDesig", query = "SELECT t FROM TLignage t WHERE t.ligDesig = :ligDesig")})
public class TLignage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "lig_code")
    private String ligCode;
    @Size(max = 100)
    @Column(name = "lig_desig")
    private String ligDesig;

    public TLignage() {
    }

    public TLignage(String ligCode) {
        this.ligCode = ligCode;
    }

    public String getLigCode() {
        return ligCode;
    }

    public void setLigCode(String ligCode) {
        this.ligCode = ligCode;
    }

    public String getLigDesig() {
        return ligDesig;
    }

    public void setLigDesig(String ligDesig) {
        this.ligDesig = ligDesig;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ligCode != null ? ligCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TLignage)) {
            return false;
        }
        TLignage other = (TLignage) object;
        if ((this.ligCode == null && other.ligCode != null) || (this.ligCode != null && !this.ligCode.equals(other.ligCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TLignage[ ligCode=" + ligCode + " ]";
    }
    
}
