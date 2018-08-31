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
 * @author Ben
 */
@Entity
@Table(name = "t_quartier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TQuartier.findAll", query = "SELECT t FROM TQuartier t")
    , @NamedQuery(name = "TQuartier.findByQuartCode", query = "SELECT t FROM TQuartier t WHERE t.quartCode = :quartCode")
    , @NamedQuery(name = "TQuartier.findByQuartLibelle", query = "SELECT t FROM TQuartier t WHERE t.quartLibelle = :quartLibelle")
    , @NamedQuery(name = "TQuartier.findByQuartArroCode", query = "SELECT t FROM TQuartier t WHERE t.quartArroCode = :quartArroCode")
    , @NamedQuery(name = "TQuartier.findByQuartActrCode", query = "SELECT t FROM TQuartier t WHERE t.quartActrCode = :quartActrCode")
    , @NamedQuery(name = "TQuartier.findByQuartDateModif", query = "SELECT t FROM TQuartier t WHERE t.quartDateModif = :quartDateModif")})
public class TQuartier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "quart_code")
    private String quartCode;
    @Size(max = 200)
    @Column(name = "quart_libelle")
    private String quartLibelle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "quart_arro_code")
    private String quartArroCode;
    @Size(max = 7)
    @Column(name = "quart_actr_code")
    private String quartActrCode;
    @Column(name = "quart_date_modif")
    @Temporal(TemporalType.TIMESTAMP)
    private Date quartDateModif;

    public TQuartier() {
    }

    public TQuartier(String quartCode) {
        this.quartCode = quartCode;
    }

    public TQuartier(String quartCode, String quartArroCode) {
        this.quartCode = quartCode;
        this.quartArroCode = quartArroCode;
    }

    public String getQuartCode() {
        return quartCode;
    }

    public void setQuartCode(String quartCode) {
        this.quartCode = quartCode;
    }

    public String getQuartLibelle() {
        return quartLibelle;
    }

    public void setQuartLibelle(String quartLibelle) {
        this.quartLibelle = quartLibelle;
    }

    public String getQuartArroCode() {
        return quartArroCode;
    }

    public void setQuartArroCode(String quartArroCode) {
        this.quartArroCode = quartArroCode;
    }

    public String getQuartActrCode() {
        return quartActrCode;
    }

    public void setQuartActrCode(String quartActrCode) {
        this.quartActrCode = quartActrCode;
    }

    public Date getQuartDateModif() {
        return quartDateModif;
    }

    public void setQuartDateModif(Date quartDateModif) {
        this.quartDateModif = quartDateModif;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (quartCode != null ? quartCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TQuartier)) {
            return false;
        }
        TQuartier other = (TQuartier) object;
        if ((this.quartCode == null && other.quartCode != null) || (this.quartCode != null && !this.quartCode.equals(other.quartCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TQuartier[ quartCode=" + quartCode + " ]";
    }
    
}
