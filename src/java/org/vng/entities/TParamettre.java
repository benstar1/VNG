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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author Dev_DI
 */
@Entity
@Table(name = "t_paramettre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TParamettre.findAll", query = "SELECT t FROM TParamettre t")
    , @NamedQuery(name = "TParamettre.findByParamCode", query = "SELECT t FROM TParamettre t WHERE t.tParamettrePK.paramCode = :paramCode")
    , @NamedQuery(name = "TParamettre.findParamCourant", query = "SELECT t FROM TParamettre t WHERE t.tParamettrePK.paramCode = :paramCode AND t.paramDateFin IS NULL")     
    , @NamedQuery(name = "TParamettre.findByParamLibelle", query = "SELECT t FROM TParamettre t WHERE t.paramLibelle = :paramLibelle")
    , @NamedQuery(name = "TParamettre.findByParamDateeffet", query = "SELECT t FROM TParamettre t WHERE t.tParamettrePK.paramDateeffet = :paramDateeffet")
    , @NamedQuery(name = "TParamettre.findByParamValeur", query = "SELECT t FROM TParamettre t WHERE t.paramValeur = :paramValeur")
    , @NamedQuery(name = "TParamettre.findByParamDateFin", query = "SELECT t FROM TParamettre t WHERE t.paramDateFin = :paramDateFin")
    , @NamedQuery(name = "TParamettre.findByParamDateDebut", query = "SELECT t FROM TParamettre t WHERE t.paramDateDebut = :paramDateDebut")
    , @NamedQuery(name = "TParamettre.findByAdresseImage", query = "SELECT t FROM TParamettre t WHERE t.adresseImage = :adresseImage")})
public class TParamettre implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TParamettrePK tParamettrePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "param_libelle")
    private String paramLibelle;
    @Size(max = 500)
    @Column(name = "param_valeur")
    private String paramValeur;
    @Column(name = "param_date_fin")
    @Temporal(TemporalType.DATE)
    private Date paramDateFin;
    @Column(name = "param_date_debut")
    @Temporal(TemporalType.DATE)
    private Date paramDateDebut;
    @Size(max = 2147483647)
    @Column(name = "adresse_image")
    private String adresseImage;

    public TParamettre() {
    }

    public TParamettre(TParamettrePK tParamettrePK) {
        this.tParamettrePK = tParamettrePK;
    }

    public TParamettre(TParamettrePK tParamettrePK, String paramLibelle) {
        this.tParamettrePK = tParamettrePK;
        this.paramLibelle = paramLibelle;
    }

    public TParamettre(String paramCode, Date paramDateeffet) {
        this.tParamettrePK = new TParamettrePK(paramCode, paramDateeffet);
    }

    public TParamettrePK getTParamettrePK() {
        return tParamettrePK;
    }

    public void setTParamettrePK(TParamettrePK tParamettrePK) {
        this.tParamettrePK = tParamettrePK;
    }

    public String getParamLibelle() {
        return paramLibelle;
    }

    public void setParamLibelle(String paramLibelle) {
        this.paramLibelle = paramLibelle;
    }

    public String getParamValeur() {
        return paramValeur;
    }

    public void setParamValeur(String paramValeur) {
        this.paramValeur = paramValeur;
    }

    public Date getParamDateFin() {
        return paramDateFin;
    }

    public void setParamDateFin(Date paramDateFin) {
        this.paramDateFin = paramDateFin;
    }

    public Date getParamDateDebut() {
        return paramDateDebut;
    }

    public void setParamDateDebut(Date paramDateDebut) {
        this.paramDateDebut = paramDateDebut;
    }

    public String getAdresseImage() {
        return adresseImage;
    }

    public void setAdresseImage(String adresseImage) {
        this.adresseImage = adresseImage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tParamettrePK != null ? tParamettrePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TParamettre)) {
            return false;
        }
        TParamettre other = (TParamettre) object;
        if ((this.tParamettrePK == null && other.tParamettrePK != null) || (this.tParamettrePK != null && !this.tParamettrePK.equals(other.tParamettrePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TParamettre[ tParamettrePK=" + tParamettrePK + " ]";
    }
    
}
