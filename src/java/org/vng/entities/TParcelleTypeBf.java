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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AAKAKPO
 */
@Entity
@Table(name = "t_parcelle_type_bf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TParcelleTypeBf.findAll", query = "SELECT t FROM TParcelleTypeBf t")
    , @NamedQuery(name = "TParcelleTypeBf.findByPatyCode", query = "SELECT t FROM TParcelleTypeBf t WHERE t.patyCode = :patyCode")
    , @NamedQuery(name = "TParcelleTypeBf.findByPatyDateDeb", query = "SELECT t FROM TParcelleTypeBf t WHERE t.patyDateDeb = :patyDateDeb")
    , @NamedQuery(name = "TParcelleTypeBf.findByPatyDateFin", query = "SELECT t FROM TParcelleTypeBf t WHERE t.patyDateFin = :patyDateFin")})
public class TParcelleTypeBf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "paty_code")
    private String patyCode;
    @Column(name = "paty_date_deb")
    @Temporal(TemporalType.DATE)
    private Date patyDateDeb;
    @Column(name = "paty_date_fin")
    @Temporal(TemporalType.DATE)
    private Date patyDateFin;
    @JoinColumn(name = "paty_pba_numero", referencedColumnName = "pba_numero")
    @ManyToOne
    private TParcelleBafon patyPbaNumero;
    @JoinColumn(name = "paty_tbf_code", referencedColumnName = "tbf_code")
    @ManyToOne
    private TTypebf patyTbfCode;
    @JoinColumn(name = "paty_uti_code", referencedColumnName = "uti_code")
    @ManyToOne
    private TUtilisateur patyUtiCode;

    public TParcelleTypeBf() {
    }

    public TParcelleTypeBf(String patyCode) {
        this.patyCode = patyCode;
    }

    public String getPatyCode() {
        return patyCode;
    }

    public void setPatyCode(String patyCode) {
        this.patyCode = patyCode;
    }

    public Date getPatyDateDeb() {
        return patyDateDeb;
    }

    public void setPatyDateDeb(Date patyDateDeb) {
        this.patyDateDeb = patyDateDeb;
    }

    public Date getPatyDateFin() {
        return patyDateFin;
    }

    public void setPatyDateFin(Date patyDateFin) {
        this.patyDateFin = patyDateFin;
    }

    public TParcelleBafon getPatyPbaNumero() {
        return patyPbaNumero;
    }

    public void setPatyPbaNumero(TParcelleBafon patyPbaNumero) {
        this.patyPbaNumero = patyPbaNumero;
    }

    public TTypebf getPatyTbfCode() {
        return patyTbfCode;
    }

    public void setPatyTbfCode(TTypebf patyTbfCode) {
        this.patyTbfCode = patyTbfCode;
    }

    public TUtilisateur getPatyUtiCode() {
        return patyUtiCode;
    }

    public void setPatyUtiCode(TUtilisateur patyUtiCode) {
        this.patyUtiCode = patyUtiCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patyCode != null ? patyCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TParcelleTypeBf)) {
            return false;
        }
        TParcelleTypeBf other = (TParcelleTypeBf) object;
        if ((this.patyCode == null && other.patyCode != null) || (this.patyCode != null && !this.patyCode.equals(other.patyCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TParcelleTypeBf[ patyCode=" + patyCode + " ]";
    }
    
}
