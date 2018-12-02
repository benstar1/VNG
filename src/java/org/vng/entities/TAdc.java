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
import javax.persistence.OneToOne;
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
@Table(name = "t_adc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TAdc.findAll", query = "SELECT t FROM TAdc t")
    , @NamedQuery(name = "TAdc.findByAdcCode", query = "SELECT t FROM TAdc t WHERE t.adcCode = :adcCode")
    , @NamedQuery(name = "TAdc.findByAdcNumOrdre", query = "SELECT t FROM TAdc t WHERE t.adcNumOrdre = :adcNumOrdre")
    , @NamedQuery(name = "TAdc.findByAdcDateDemande", query = "SELECT t FROM TAdc t WHERE t.adcDateDemande = :adcDateDemande")
    , @NamedQuery(name = "TAdc.findByAdcNumEnquete", query = "SELECT t FROM TAdc t WHERE t.adcNumEnquete = :adcNumEnquete")
    , @NamedQuery(name = "TAdc.findByAdcDateEnquete", query = "SELECT t FROM TAdc t WHERE t.adcDateEnquete = :adcDateEnquete")
    , @NamedQuery(name = "TAdc.findByAdcDateRetrait", query = "SELECT t FROM TAdc t WHERE t.adcDateRetrait = :adcDateRetrait")
    , @NamedQuery(name = "TAdc.findByAdcReference", query = "SELECT t FROM TAdc t WHERE t.adcReference = :adcReference")
    , @NamedQuery(name = "TAdc.findByAdcImage1", query = "SELECT t FROM TAdc t WHERE t.adcImage1 = :adcImage1")
    , @NamedQuery(name = "TAdc.findByAdcImage2", query = "SELECT t FROM TAdc t WHERE t.adcImage2 = :adcImage2")
    , @NamedQuery(name = "TAdc.findByAdcFonction", query = "SELECT t FROM TAdc t WHERE t.adcFonction = :adcFonction")})
public class TAdc implements Serializable {

    @Column(name = "adc_dequis_quand")
    @Temporal(TemporalType.DATE)
    private Date adcDequisQuand;
    @Size(max = 50)
    @Column(name = "adc_de_qui")
    private String adcDeQui;
    @JoinColumn(name = "adc_int_bailleur", referencedColumnName = "int_numero")
    @ManyToOne
    private TIntervenant adcIntBailleur;
    @JoinColumn(name = "adc_mac_code", referencedColumnName = "mac_code")
    @ManyToOne
    private TModeacquis adcMacCode;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "adc_code")
    private String adcCode;
    @Size(max = 10)
    @Column(name = "adc_num_ordre")
    private String adcNumOrdre;
    @Column(name = "adc_date_demande")
    @Temporal(TemporalType.DATE)
    private Date adcDateDemande;
    @Size(max = 30)
    @Column(name = "adc_num_enquete")
    private String adcNumEnquete;
    @Column(name = "adc_date_enquete")
    @Temporal(TemporalType.DATE)
    private Date adcDateEnquete;
    @Column(name = "adc_date_retrait")
    @Temporal(TemporalType.DATE)
    private Date adcDateRetrait;
    @Size(max = 50)
    @Column(name = "adc_reference")
    private String adcReference;
    @Size(max = 300)
    @Column(name = "adc_image1")
    private String adcImage1;
    @Size(max = 300)
    @Column(name = "adc_image2")
    private String adcImage2;
    @Size(max = 300)
    @Column(name = "adc_fonction")
    private String adcFonction;
    @JoinColumn(name = "adc_int_numero", referencedColumnName = "int_numero")
    @OneToOne
    private TIntervenant adcIntNumero;
    @JoinColumn(name = "adc_pba_numero", referencedColumnName = "pba_numero")
    @OneToOne
    private TParcelleBafon adcPbaNumero;
    @JoinColumn(name = "adc_sign_code", referencedColumnName = "sign_code")
    @OneToOne
    private TSignataire adcSignCode;
    @JoinColumn(name = "adc_uti_code", referencedColumnName = "uti_code")
    @ManyToOne
    private TUtilisateur adcUtiCode;

    public TAdc() {
    }

    public TAdc(String adcCode) {
        this.adcCode = adcCode;
    }

    public String getAdcCode() {
        return adcCode;
    }

    public void setAdcCode(String adcCode) {
        this.adcCode = adcCode;
    }

    public String getAdcNumOrdre() {
        return adcNumOrdre;
    }

    public void setAdcNumOrdre(String adcNumOrdre) {
        this.adcNumOrdre = adcNumOrdre;
    }

    public Date getAdcDateDemande() {
        return adcDateDemande;
    }

    public void setAdcDateDemande(Date adcDateDemande) {
        this.adcDateDemande = adcDateDemande;
    }

    public String getAdcNumEnquete() {
        return adcNumEnquete;
    }

    public void setAdcNumEnquete(String adcNumEnquete) {
        this.adcNumEnquete = adcNumEnquete;
    }

    public Date getAdcDateEnquete() {
        return adcDateEnquete;
    }

    public void setAdcDateEnquete(Date adcDateEnquete) {
        this.adcDateEnquete = adcDateEnquete;
    }

    public Date getAdcDateRetrait() {
        return adcDateRetrait;
    }

    public void setAdcDateRetrait(Date adcDateRetrait) {
        this.adcDateRetrait = adcDateRetrait;
    }

    public String getAdcReference() {
        return adcReference;
    }

    public void setAdcReference(String adcReference) {
        this.adcReference = adcReference;
    }

    public String getAdcImage1() {
        return adcImage1;
    }

    public void setAdcImage1(String adcImage1) {
        this.adcImage1 = adcImage1;
    }

    public String getAdcImage2() {
        return adcImage2;
    }

    public void setAdcImage2(String adcImage2) {
        this.adcImage2 = adcImage2;
    }

    public String getAdcFonction() {
        return adcFonction;
    }

    public void setAdcFonction(String adcFonction) {
        this.adcFonction = adcFonction;
    }

    public TIntervenant getAdcIntNumero() {
        return adcIntNumero;
    }

    public void setAdcIntNumero(TIntervenant adcIntNumero) {
        this.adcIntNumero = adcIntNumero;
    }

    public TParcelleBafon getAdcPbaNumero() {
        return adcPbaNumero;
    }

    public void setAdcPbaNumero(TParcelleBafon adcPbaNumero) {
        this.adcPbaNumero = adcPbaNumero;
    }

    public TSignataire getAdcSignCode() {
        return adcSignCode;
    }

    public void setAdcSignCode(TSignataire adcSignCode) {
        this.adcSignCode = adcSignCode;
    }

    public TUtilisateur getAdcUtiCode() {
        return adcUtiCode;
    }

    public void setAdcUtiCode(TUtilisateur adcUtiCode) {
        this.adcUtiCode = adcUtiCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adcCode != null ? adcCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TAdc)) {
            return false;
        }
        TAdc other = (TAdc) object;
        if ((this.adcCode == null && other.adcCode != null) || (this.adcCode != null && !this.adcCode.equals(other.adcCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TAdc[ adcCode=" + adcCode + " ]";
    }

    public Date getAdcDequisQuand() {
        return adcDequisQuand;
    }

    public void setAdcDequisQuand(Date adcDequisQuand) {
        this.adcDequisQuand = adcDequisQuand;
    }

    public String getAdcDeQui() {
        return adcDeQui;
    }

    public void setAdcDeQui(String adcDeQui) {
        this.adcDeQui = adcDeQui;
    }

    public TIntervenant getAdcIntBailleur() {
        return adcIntBailleur;
    }

    public void setAdcIntBailleur(TIntervenant adcIntBailleur) {
        this.adcIntBailleur = adcIntBailleur;
    }

    public TModeacquis getAdcMacCode() {
        return adcMacCode;
    }

    public void setAdcMacCode(TModeacquis adcMacCode) {
        this.adcMacCode = adcMacCode;
    }
    
}
