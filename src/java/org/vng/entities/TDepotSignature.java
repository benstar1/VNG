/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ben
 */
@Entity
@Table(name = "t_depot_signature")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TDepotSignature.findAll", query = "SELECT t FROM TDepotSignature t")
    , @NamedQuery(name = "TDepotSignature.findByDesiCode", query = "SELECT t FROM TDepotSignature t WHERE t.desiCode = :desiCode")
    , @NamedQuery(name = "TDepotSignature.findByDesiReference", query = "SELECT t FROM TDepotSignature t WHERE t.desiReference = :desiReference")
    , @NamedQuery(name = "TDepotSignature.findByDesiDateDeb", query = "SELECT t FROM TDepotSignature t WHERE t.desiDateDeb = :desiDateDeb")
    , @NamedQuery(name = "TDepotSignature.findByDesiDateFin", query = "SELECT t FROM TDepotSignature t WHERE t.desiDateFin = :desiDateFin")})
public class TDepotSignature implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "desi_code")
    private String desiCode;
    @Size(max = 100)
    @Column(name = "desi_reference")
    private String desiReference;
    @Column(name = "desi_date_deb")
    @Temporal(TemporalType.DATE)
    private Date desiDateDeb;
    @Column(name = "desi_date_fin")
    @Temporal(TemporalType.DATE)
    private Date desiDateFin;
    @OneToMany(mappedBy = "opvDesiCode")
    private List<TOperationParcel> tOperationParcelList;
    @JoinColumn(name = "desi_int_numero", referencedColumnName = "int_numero")
    @ManyToOne
    private TIntervenant desiIntNumero;
    @JoinColumn(name = "desi_uti_code", referencedColumnName = "uti_code")
    @ManyToOne
    private TUtilisateur desiUtiCode;

    public TDepotSignature() {
    }

    public TDepotSignature(String desiCode) {
        this.desiCode = desiCode;
    }

    public String getDesiCode() {
        return desiCode;
    }

    public void setDesiCode(String desiCode) {
        this.desiCode = desiCode;
    }

    public String getDesiReference() {
        return desiReference;
    }

    public void setDesiReference(String desiReference) {
        this.desiReference = desiReference;
    }

    public Date getDesiDateDeb() {
        return desiDateDeb;
    }

    public void setDesiDateDeb(Date desiDateDeb) {
        this.desiDateDeb = desiDateDeb;
    }

    public Date getDesiDateFin() {
        return desiDateFin;
    }

    public void setDesiDateFin(Date desiDateFin) {
        this.desiDateFin = desiDateFin;
    }

    @XmlTransient
    public List<TOperationParcel> getTOperationParcelList() {
        return tOperationParcelList;
    }

    public void setTOperationParcelList(List<TOperationParcel> tOperationParcelList) {
        this.tOperationParcelList = tOperationParcelList;
    }

    public TIntervenant getDesiIntNumero() {
        return desiIntNumero;
    }

    public void setDesiIntNumero(TIntervenant desiIntNumero) {
        this.desiIntNumero = desiIntNumero;
    }

    public TUtilisateur getDesiUtiCode() {
        return desiUtiCode;
    }

    public void setDesiUtiCode(TUtilisateur desiUtiCode) {
        this.desiUtiCode = desiUtiCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (desiCode != null ? desiCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TDepotSignature)) {
            return false;
        }
        TDepotSignature other = (TDepotSignature) object;
        if ((this.desiCode == null && other.desiCode != null) || (this.desiCode != null && !this.desiCode.equals(other.desiCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TDepotSignature[ desiCode=" + desiCode + " ]";
    }
    
}
