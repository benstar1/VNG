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
 * @author AAKAKPO
 */
@Entity
@Table(name = "t_forme_parcelle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TFormeParcelle.findAll", query = "SELECT t FROM TFormeParcelle t")
    , @NamedQuery(name = "TFormeParcelle.findByFopaCode", query = "SELECT t FROM TFormeParcelle t WHERE t.fopaCode = :fopaCode")
    , @NamedQuery(name = "TFormeParcelle.findByFopaDesig", query = "SELECT t FROM TFormeParcelle t WHERE t.fopaDesig = :fopaDesig")})
public class TFormeParcelle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "fopa_code")
    private String fopaCode;
    @Size(max = 100)
    @Column(name = "fopa_desig")
    private String fopaDesig;

    public TFormeParcelle() {
    }

    public TFormeParcelle(String fopaCode) {
        this.fopaCode = fopaCode;
    }

    public String getFopaCode() {
        return fopaCode;
    }

    public void setFopaCode(String fopaCode) {
        this.fopaCode = fopaCode;
    }

    public String getFopaDesig() {
        return fopaDesig;
    }

    public void setFopaDesig(String fopaDesig) {
        this.fopaDesig = fopaDesig;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fopaCode != null ? fopaCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TFormeParcelle)) {
            return false;
        }
        TFormeParcelle other = (TFormeParcelle) object;
        if ((this.fopaCode == null && other.fopaCode != null) || (this.fopaCode != null && !this.fopaCode.equals(other.fopaCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TFormeParcelle[ fopaCode=" + fopaCode + " ]";
    }
    
}
