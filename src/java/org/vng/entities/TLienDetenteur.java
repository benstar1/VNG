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
@Table(name = "t_lien_detenteur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TLienDetenteur.findAll", query = "SELECT t FROM TLienDetenteur t")
    , @NamedQuery(name = "TLienDetenteur.findByLienCode", query = "SELECT t FROM TLienDetenteur t WHERE t.lienCode = :lienCode")
    , @NamedQuery(name = "TLienDetenteur.findByLienDesig", query = "SELECT t FROM TLienDetenteur t WHERE t.lienDesig = :lienDesig")})
public class TLienDetenteur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "lien_code")
    private String lienCode;
    @Size(max = 100)
    @Column(name = "lien_desig")
    private String lienDesig;

    public TLienDetenteur() {
    }

    public TLienDetenteur(String lienCode) {
        this.lienCode = lienCode;
    }

    public String getLienCode() {
        return lienCode;
    }

    public void setLienCode(String lienCode) {
        this.lienCode = lienCode;
    }

    public String getLienDesig() {
        return lienDesig;
    }

    public void setLienDesig(String lienDesig) {
        this.lienDesig = lienDesig;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lienCode != null ? lienCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TLienDetenteur)) {
            return false;
        }
        TLienDetenteur other = (TLienDetenteur) object;
        if ((this.lienCode == null && other.lienCode != null) || (this.lienCode != null && !this.lienCode.equals(other.lienCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TLienDetenteur[ lienCode=" + lienCode + " ]";
    }
    
}
