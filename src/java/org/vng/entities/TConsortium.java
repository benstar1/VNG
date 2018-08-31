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
@Table(name = "t_consortium")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TConsortium.findAll", query = "SELECT t FROM TConsortium t")
    , @NamedQuery(name = "TConsortium.findByConCode", query = "SELECT t FROM TConsortium t WHERE t.conCode = :conCode")
    , @NamedQuery(name = "TConsortium.findByConNom", query = "SELECT t FROM TConsortium t WHERE t.conNom = :conNom")
    , @NamedQuery(name = "TConsortium.findByConAdresse", query = "SELECT t FROM TConsortium t WHERE t.conAdresse = :conAdresse")
    , @NamedQuery(name = "TConsortium.findByConTel", query = "SELECT t FROM TConsortium t WHERE t.conTel = :conTel")
    , @NamedQuery(name = "TConsortium.findByConIfu", query = "SELECT t FROM TConsortium t WHERE t.conIfu = :conIfu")
    , @NamedQuery(name = "TConsortium.findByConDateChargement", query = "SELECT t FROM TConsortium t WHERE t.conDateChargement = :conDateChargement")})
public class TConsortium implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "con_code")
    private String conCode;
    @Size(max = 200)
    @Column(name = "con_nom")
    private String conNom;
    @Size(max = 200)
    @Column(name = "con_adresse")
    private String conAdresse;
    @Size(max = 50)
    @Column(name = "con_tel")
    private String conTel;
    @Size(max = 15)
    @Column(name = "con_ifu")
    private String conIfu;
    @Column(name = "con_date_chargement")
    @Temporal(TemporalType.DATE)
    private Date conDateChargement;

    public TConsortium() {
    }

    public TConsortium(String conCode) {
        this.conCode = conCode;
    }

    public String getConCode() {
        return conCode;
    }

    public void setConCode(String conCode) {
        this.conCode = conCode;
    }

    public String getConNom() {
        return conNom;
    }

    public void setConNom(String conNom) {
        this.conNom = conNom;
    }

    public String getConAdresse() {
        return conAdresse;
    }

    public void setConAdresse(String conAdresse) {
        this.conAdresse = conAdresse;
    }

    public String getConTel() {
        return conTel;
    }

    public void setConTel(String conTel) {
        this.conTel = conTel;
    }

    public String getConIfu() {
        return conIfu;
    }

    public void setConIfu(String conIfu) {
        this.conIfu = conIfu;
    }

    public Date getConDateChargement() {
        return conDateChargement;
    }

    public void setConDateChargement(Date conDateChargement) {
        this.conDateChargement = conDateChargement;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conCode != null ? conCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TConsortium)) {
            return false;
        }
        TConsortium other = (TConsortium) object;
        if ((this.conCode == null && other.conCode != null) || (this.conCode != null && !this.conCode.equals(other.conCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TConsortium[ conCode=" + conCode + " ]";
    }
    
}
