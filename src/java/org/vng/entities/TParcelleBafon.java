/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name = "t_parcelle_bafon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TParcelleBafon.findAll", query = "SELECT t FROM TParcelleBafon t")
    , @NamedQuery(name = "TParcelleBafon.findByPbaNumero", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaNumero = :pbaNumero")
    , @NamedQuery(name = "TParcelleBafon.findByPbaOthoImg", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaOthoImg = :pbaOthoImg")
    , @NamedQuery(name = "TParcelleBafon.findByPbaNature", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaNature = :pbaNature")
    , @NamedQuery(name = "TParcelleBafon.findByPbaSuperficie", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaSuperficie = :pbaSuperficie")
    , @NamedQuery(name = "TParcelleBafon.findByPbaCodeGeo", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaCodeGeo = :pbaCodeGeo")
    , @NamedQuery(name = "TParcelleBafon.findByPbaHistoire", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaHistoire = :pbaHistoire")
    , @NamedQuery(name = "TParcelleBafon.findByPbaBati", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaBati = :pbaBati")
    , @NamedQuery(name = "TParcelleBafon.findByPbaCloture", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaCloture = :pbaCloture")
    , @NamedQuery(name = "TParcelleBafon.findByPbaCloMacon", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaCloMacon = :pbaCloMacon")
    , @NamedQuery(name = "TParcelleBafon.findByPbaCloBanco", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaCloBanco = :pbaCloBanco")
    , @NamedQuery(name = "TParcelleBafon.findByPbaCloClaie", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaCloClaie = :pbaCloClaie")
    , @NamedQuery(name = "TParcelleBafon.findByPbaCloHaie", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaCloHaie = :pbaCloHaie")
    , @NamedQuery(name = "TParcelleBafon.findByPbaCloAutre", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaCloAutre = :pbaCloAutre")
    , @NamedQuery(name = "TParcelleBafon.findByPbaNbeBat", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaNbeBat = :pbaNbeBat")
    , @NamedQuery(name = "TParcelleBafon.findByPbaToitDalle", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaToitDalle = :pbaToitDalle")
    , @NamedQuery(name = "TParcelleBafon.findByPbaToitTuile", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaToitTuile = :pbaToitTuile")
    , @NamedQuery(name = "TParcelleBafon.findByPbaToitTole", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaToitTole = :pbaToitTole")
    , @NamedQuery(name = "TParcelleBafon.findByPbaToitPaille", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaToitPaille = :pbaToitPaille")
    , @NamedQuery(name = "TParcelleBafon.findByPbaToitClaie", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaToitClaie = :pbaToitClaie")
    , @NamedQuery(name = "TParcelleBafon.findByPbaMaisOrdinare", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaMaisOrdinare = :pbaMaisOrdinare")
    , @NamedQuery(name = "TParcelleBafon.findByPbaMaisRez", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaMaisRez = :pbaMaisRez")
    , @NamedQuery(name = "TParcelleBafon.findByPbaMaisEtage", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaMaisEtage = :pbaMaisEtage")
    , @NamedQuery(name = "TParcelleBafon.findByPbaGarage", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaGarage = :pbaGarage")
    , @NamedQuery(name = "TParcelleBafon.findByPbaPuits", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaPuits = :pbaPuits")
    , @NamedQuery(name = "TParcelleBafon.findByPbaCiterne", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaCiterne = :pbaCiterne")
    , @NamedQuery(name = "TParcelleBafon.findByPbaCuisine", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaCuisine = :pbaCuisine")
    , @NamedQuery(name = "TParcelleBafon.findByPbaPuisard", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaPuisard = :pbaPuisard")
    , @NamedQuery(name = "TParcelleBafon.findByPbaWc", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaWc = :pbaWc")
    , @NamedQuery(name = "TParcelleBafon.findByPbaHameau", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaHameau = :pbaHameau")
    , @NamedQuery(name = "TParcelleBafon.findByPbaNbEtage", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaNbEtage = :pbaNbEtage")
    , @NamedQuery(name = "TParcelleBafon.findByPbaLieuDit", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaLieuDit = :pbaLieuDit")
    , @NamedQuery(name = "TParcelleBafon.findByPbaNomPhoto", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaNomPhoto = :pbaNomPhoto")
    , @NamedQuery(name = "TParcelleBafon.findByPbaNomPhoto1", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaNomPhoto1 = :pbaNomPhoto1")
    , @NamedQuery(name = "TParcelleBafon.findByPbaStaAutre", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaStaAutre = :pbaStaAutre")
    , @NamedQuery(name = "TParcelleBafon.findByPbaTitreFoncier", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaTitreFoncier = :pbaTitreFoncier")
    , @NamedQuery(name = "TParcelleBafon.findByPbaObservation", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaObservation = :pbaObservation")
    , @NamedQuery(name = "TParcelleBafon.findByPbaNomLocal", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaNomLocal = :pbaNomLocal")
    , @NamedQuery(name = "TParcelleBafon.findByPbaAncienNumero", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaAncienNumero = :pbaAncienNumero")
    , @NamedQuery(name = "TParcelleBafon.findByPbaAppartenance", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaAppartenance = :pbaAppartenance")
    , @NamedQuery(name = "TParcelleBafon.findByPbaDateFinalisation", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaDateFinalisation = :pbaDateFinalisation")
    , @NamedQuery(name = "TParcelleBafon.findByPbaDateChargement", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaDateChargement = :pbaDateChargement")
    , @NamedQuery(name = "TParcelleBafon.findByPbaForme", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaForme = :pbaForme")
    , @NamedQuery(name = "TParcelleBafon.findByPbaEtatLieu", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaEtatLieu = :pbaEtatLieu")
    , @NamedQuery(name = "TParcelleBafon.findByPbaTranche", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaTranche = :pbaTranche")
    , @NamedQuery(name = "TParcelleBafon.findByPbaLot", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaLot = :pbaLot")
    , @NamedQuery(name = "TParcelleBafon.findByPbaAdressage", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaAdressage = :pbaAdressage")
    , @NamedQuery(name = "TParcelleBafon.findByCommuneEncours", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaVilaCode.vilaArrCode.arrComCode.comEncours = true")
    , @NamedQuery(name = "TParcelleBafon.findByPbaPfr", query = "SELECT t FROM TParcelleBafon t WHERE t.pbaPfr = :pbaPfr")})
public class TParcelleBafon implements Serializable {

    @OneToMany(mappedBy = "patyPbaNumero")
    private List<TParcelleTypeBf> tParcelleTypeBfList;
    @JoinColumn(name = "pba_code_geo", referencedColumnName = "uti_code")
    @ManyToOne
    private TUtilisateur pbaCodeGeo;

    @Size(max = 30)
    @Column(name = "pba_adc_reference")
    private String pbaAdcReference;
    @Column(name = "pba_adc_date")
    @Temporal(TemporalType.DATE)
    private Date pbaAdcDate;
    @Size(max = 100)
    @Column(name = "pba_adc_signataire")
    private String pbaAdcSignataire;
    @Size(max = 200)
    @Column(name = "pba_adc_image")
    private String pbaAdcImage;
    @Size(max = 30)
    @Column(name = "pba_foncier_reference")
    private String pbaFoncierReference;
    @Column(name = "pba_foncier_date")
    @Temporal(TemporalType.DATE)
    private Date pbaFoncierDate;
    @Size(max = 100)
    @Column(name = "pba_foncier_signataire")
    private String pbaFoncierSignataire;
    @Size(max = 200)
    @Column(name = "pba_foncier_image")
    private String pbaFoncierImage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tParcelleBafon")
    private List<TParcellePoca> tParcellePocaList;
    @OneToMany(mappedBy = "papoPbaNumeroLimit")
    private List<TParcellePoca> tParcellePocaList1;

    

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "pba_numero")
    private String pbaNumero;
    @Size(max = 50)
    @Column(name = "pba_otho_img")
    private String pbaOthoImg;
    @Size(max = 10)
    @Column(name = "pba_nature")
    private String pbaNature;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pba_superficie")
    private BigDecimal pbaSuperficie;
   // @Size(max = 50)
  //  @Column(name = "pba_code_geo")
//    private String pbaCodeGeo;
    @Size(max = 5000)
    @Column(name = "pba_histoire")
    private String pbaHistoire;
    @Column(name = "pba_bati")
    private Boolean pbaBati;
    @Column(name = "pba_cloture")
    private Boolean pbaCloture;
    @Column(name = "pba_clo_macon")
    private Boolean pbaCloMacon;
    @Column(name = "pba_clo_banco")
    private Boolean pbaCloBanco;
    @Column(name = "pba_clo_claie")
    private Boolean pbaCloClaie;
    @Column(name = "pba_clo_haie")
    private Boolean pbaCloHaie;
    @Size(max = 500)
    @Column(name = "pba_clo_autre")
    private String pbaCloAutre;
    @Column(name = "pba_nbe_bat")
    private Short pbaNbeBat;
    @Column(name = "pba_toit_dalle")
    private Boolean pbaToitDalle;
    @Column(name = "pba_toit_tuile")
    private Boolean pbaToitTuile;
    @Column(name = "pba_toit_tole")
    private Boolean pbaToitTole;
    @Column(name = "pba_toit_paille")
    private Boolean pbaToitPaille;
    @Column(name = "pba_toit_claie")
    private Boolean pbaToitClaie;
    @Column(name = "pba_mais_ordinare")
    private Boolean pbaMaisOrdinare;
    @Column(name = "pba_mais_rez")
    private Boolean pbaMaisRez;
    @Column(name = "pba_mais_etage")
    private Boolean pbaMaisEtage;
    @Column(name = "pba_garage")
    private Boolean pbaGarage;
    @Column(name = "pba_puits")
    private Boolean pbaPuits;
    @Column(name = "pba_citerne")
    private Boolean pbaCiterne;
    @Column(name = "pba_cuisine")
    private Boolean pbaCuisine;
    @Column(name = "pba_puisard")
    private Boolean pbaPuisard;
    @Column(name = "pba_wc")
    private Boolean pbaWc;
    @Size(max = 100)
    @Column(name = "pba_hameau")
    private String pbaHameau;
    @Column(name = "pba_nb_etage")
    private Short pbaNbEtage;
    @Size(max = 500)
    @Column(name = "pba_lieu_dit")
    private String pbaLieuDit;
    @Size(max = 500)
    @Column(name = "pba_nom_photo")
    private String pbaNomPhoto;
    @Size(max = 500)
    @Column(name = "pba_nom_photo1")
    private String pbaNomPhoto1;
    @Size(max = 100)
    @Column(name = "pba_sta_autre")
    private String pbaStaAutre;
    @Size(max = 100)
    @Column(name = "pba_titre_foncier")
    private String pbaTitreFoncier;
    @Size(max = 500)
    @Column(name = "pba_observation")
    private String pbaObservation;
    @Size(max = 100)
    @Column(name = "pba_nom_local")
    private String pbaNomLocal;
    @Size(max = 50)
    @Column(name = "pba_ancien_numero")
    private String pbaAncienNumero;
    @Size(max = 20)
    @Column(name = "pba_appartenance")
    private String pbaAppartenance;
    @Column(name = "pba_date_finalisation")
    @Temporal(TemporalType.DATE)
    private Date pbaDateFinalisation;
    @Column(name = "pba_date_chargement")
    @Temporal(TemporalType.DATE)
    private Date pbaDateChargement;
    @Size(max = 50)
    @Column(name = "pba_forme")
    private String pbaForme;
    @Size(max = 20)
    @Column(name = "pba_etat_lieu")
    private String pbaEtatLieu;
    @Size(max = 20)
    @Column(name = "pba_tranche")
    private String pbaTranche;
    @Size(max = 20)
    @Column(name = "pba_lot")
    private String pbaLot;
    @Size(max = 20)
    @Column(name = "pba_adressage")
    private String pbaAdressage;
    @Column(name = "pba_pfr")
    private Boolean pbaPfr;
    @ManyToMany(mappedBy = "tParcelleBafonList")
    private List<TPointCardino> tPointCardinoList;
    @JoinColumn(name = "pba_prop", referencedColumnName = "int_numero")
    @ManyToOne
    private TIntervenant pbaProp;
    @OneToMany(mappedBy = "pbaPbaNumero")
    private List<TParcelleBafon> tParcelleBafonList;
    @JoinColumn(name = "pba_pba_numero", referencedColumnName = "pba_numero")
    @ManyToOne
    private TParcelleBafon pbaPbaNumero;
    @JoinColumn(name = "pba_sta_code", referencedColumnName = "sta_code")
    @ManyToOne
    private TStatut pbaStaCode;
    @JoinColumn(name = "pba_tbf_code", referencedColumnName = "tbf_code")
    @ManyToOne
    private TTypebf pbaTbfCode;
    @JoinColumn(name = "pba_vila_code", referencedColumnName = "vila_code")
    @ManyToOne
    private TVillage pbaVilaCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pvPbaNumero")
    private List<TPvParcelle> tPvParcelleList;
    @OneToMany(mappedBy = "opvPbaNumero")
    private List<TOperationParcel> tOperationParcelList;
    @OneToMany(mappedBy = "drePbaNumero")
    private List<TDroitExerce> tDroitExerceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "confPbaNumero")
    private List<TConflit> tConflitList;
    @OneToMany(mappedBy = "invPbaNumeroLim")
    private List<TIntervenir> tIntervenirList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invPbaNumero")
    private List<TIntervenir> tIntervenirList1;

    public TParcelleBafon() {
    }

    public TParcelleBafon(String pbaNumero) {
        this.pbaNumero = pbaNumero;
    }

    public String getPbaNumero() {
        return pbaNumero;
    }

    public void setPbaNumero(String pbaNumero) {
        this.pbaNumero = pbaNumero;
    }

    public String getPbaOthoImg() {
        return pbaOthoImg;
    }

    public void setPbaOthoImg(String pbaOthoImg) {
        this.pbaOthoImg = pbaOthoImg;
    }

    public String getPbaNature() {
        return pbaNature;
    }

    public void setPbaNature(String pbaNature) {
        this.pbaNature = pbaNature;
    }

    public BigDecimal getPbaSuperficie() {
        return pbaSuperficie;
    }

    public void setPbaSuperficie(BigDecimal pbaSuperficie) {
        this.pbaSuperficie = pbaSuperficie;
    }

//    public String getPbaCodeGeo() {
//        return pbaCodeGeo;
//    }
//
//    public void setPbaCodeGeo(String pbaCodeGeo) {
//        this.pbaCodeGeo = pbaCodeGeo;
//    }

    public String getPbaHistoire() {
        return pbaHistoire;
    }

    public void setPbaHistoire(String pbaHistoire) {
        this.pbaHistoire = pbaHistoire;
    }

    public Boolean getPbaBati() {
        return pbaBati;
    }

    public void setPbaBati(Boolean pbaBati) {
        this.pbaBati = pbaBati;
    }

    public Boolean getPbaCloture() {
        return pbaCloture;
    }

    public void setPbaCloture(Boolean pbaCloture) {
        this.pbaCloture = pbaCloture;
    }

    public Boolean getPbaCloMacon() {
        return pbaCloMacon;
    }

    public void setPbaCloMacon(Boolean pbaCloMacon) {
        this.pbaCloMacon = pbaCloMacon;
    }

    public Boolean getPbaCloBanco() {
        return pbaCloBanco;
    }

    public void setPbaCloBanco(Boolean pbaCloBanco) {
        this.pbaCloBanco = pbaCloBanco;
    }

    public Boolean getPbaCloClaie() {
        return pbaCloClaie;
    }

    public void setPbaCloClaie(Boolean pbaCloClaie) {
        this.pbaCloClaie = pbaCloClaie;
    }

    public Boolean getPbaCloHaie() {
        return pbaCloHaie;
    }

    public void setPbaCloHaie(Boolean pbaCloHaie) {
        this.pbaCloHaie = pbaCloHaie;
    }

    public String getPbaCloAutre() {
        return pbaCloAutre;
    }

    public void setPbaCloAutre(String pbaCloAutre) {
        this.pbaCloAutre = pbaCloAutre;
    }

    public Short getPbaNbeBat() {
        return pbaNbeBat;
    }

    public void setPbaNbeBat(Short pbaNbeBat) {
        this.pbaNbeBat = pbaNbeBat;
    }

    public Boolean getPbaToitDalle() {
        return pbaToitDalle;
    }

    public void setPbaToitDalle(Boolean pbaToitDalle) {
        this.pbaToitDalle = pbaToitDalle;
    }

    public Boolean getPbaToitTuile() {
        return pbaToitTuile;
    }

    public void setPbaToitTuile(Boolean pbaToitTuile) {
        this.pbaToitTuile = pbaToitTuile;
    }

    public Boolean getPbaToitTole() {
        return pbaToitTole;
    }

    public void setPbaToitTole(Boolean pbaToitTole) {
        this.pbaToitTole = pbaToitTole;
    }

    public Boolean getPbaToitPaille() {
        return pbaToitPaille;
    }

    public void setPbaToitPaille(Boolean pbaToitPaille) {
        this.pbaToitPaille = pbaToitPaille;
    }

    public Boolean getPbaToitClaie() {
        return pbaToitClaie;
    }

    public void setPbaToitClaie(Boolean pbaToitClaie) {
        this.pbaToitClaie = pbaToitClaie;
    }

    public Boolean getPbaMaisOrdinare() {
        return pbaMaisOrdinare;
    }

    public void setPbaMaisOrdinare(Boolean pbaMaisOrdinare) {
        this.pbaMaisOrdinare = pbaMaisOrdinare;
    }

    public Boolean getPbaMaisRez() {
        return pbaMaisRez;
    }

    public void setPbaMaisRez(Boolean pbaMaisRez) {
        this.pbaMaisRez = pbaMaisRez;
    }

    public Boolean getPbaMaisEtage() {
        return pbaMaisEtage;
    }

    public void setPbaMaisEtage(Boolean pbaMaisEtage) {
        this.pbaMaisEtage = pbaMaisEtage;
    }

    public Boolean getPbaGarage() {
        return pbaGarage;
    }

    public void setPbaGarage(Boolean pbaGarage) {
        this.pbaGarage = pbaGarage;
    }

    public Boolean getPbaPuits() {
        return pbaPuits;
    }

    public void setPbaPuits(Boolean pbaPuits) {
        this.pbaPuits = pbaPuits;
    }

    public Boolean getPbaCiterne() {
        return pbaCiterne;
    }

    public void setPbaCiterne(Boolean pbaCiterne) {
        this.pbaCiterne = pbaCiterne;
    }

    public Boolean getPbaCuisine() {
        return pbaCuisine;
    }

    public void setPbaCuisine(Boolean pbaCuisine) {
        this.pbaCuisine = pbaCuisine;
    }

    public Boolean getPbaPuisard() {
        return pbaPuisard;
    }

    public void setPbaPuisard(Boolean pbaPuisard) {
        this.pbaPuisard = pbaPuisard;
    }

    public Boolean getPbaWc() {
        return pbaWc;
    }

    public void setPbaWc(Boolean pbaWc) {
        this.pbaWc = pbaWc;
    }

    public String getPbaHameau() {
        return pbaHameau;
    }

    public void setPbaHameau(String pbaHameau) {
        this.pbaHameau = pbaHameau;
    }

    public Short getPbaNbEtage() {
        return pbaNbEtage;
    }

    public void setPbaNbEtage(Short pbaNbEtage) {
        this.pbaNbEtage = pbaNbEtage;
    }

    public String getPbaLieuDit() {
        return pbaLieuDit;
    }

    public void setPbaLieuDit(String pbaLieuDit) {
        this.pbaLieuDit = pbaLieuDit;
    }

    public String getPbaNomPhoto() {
        return pbaNomPhoto;
    }

    public void setPbaNomPhoto(String pbaNomPhoto) {
        this.pbaNomPhoto = pbaNomPhoto;
    }

    public String getPbaNomPhoto1() {
        return pbaNomPhoto1;
    }

    public void setPbaNomPhoto1(String pbaNomPhoto1) {
        this.pbaNomPhoto1 = pbaNomPhoto1;
    }

    public String getPbaStaAutre() {
        return pbaStaAutre;
    }

    public void setPbaStaAutre(String pbaStaAutre) {
        this.pbaStaAutre = pbaStaAutre;
    }

    public String getPbaTitreFoncier() {
        return pbaTitreFoncier;
    }

    public void setPbaTitreFoncier(String pbaTitreFoncier) {
        this.pbaTitreFoncier = pbaTitreFoncier;
    }

    public String getPbaObservation() {
        return pbaObservation;
    }

    public void setPbaObservation(String pbaObservation) {
        this.pbaObservation = pbaObservation;
    }

    public String getPbaNomLocal() {
        return pbaNomLocal;
    }

    public void setPbaNomLocal(String pbaNomLocal) {
        this.pbaNomLocal = pbaNomLocal;
    }

    public String getPbaAncienNumero() {
        return pbaAncienNumero;
    }

    public void setPbaAncienNumero(String pbaAncienNumero) {
        this.pbaAncienNumero = pbaAncienNumero;
    }

    public String getPbaAppartenance() {
        return pbaAppartenance;
    }

    public void setPbaAppartenance(String pbaAppartenance) {
        this.pbaAppartenance = pbaAppartenance;
    }

    public Date getPbaDateFinalisation() {
        return pbaDateFinalisation;
    }

    public void setPbaDateFinalisation(Date pbaDateFinalisation) {
        this.pbaDateFinalisation = pbaDateFinalisation;
    }

    public Date getPbaDateChargement() {
        return pbaDateChargement;
    }

    public void setPbaDateChargement(Date pbaDateChargement) {
        this.pbaDateChargement = pbaDateChargement;
    }

    public String getPbaForme() {
        return pbaForme;
    }

    public void setPbaForme(String pbaForme) {
        this.pbaForme = pbaForme;
    }

    public String getPbaEtatLieu() {
        return pbaEtatLieu;
    }

    public void setPbaEtatLieu(String pbaEtatLieu) {
        this.pbaEtatLieu = pbaEtatLieu;
    }

    public String getPbaTranche() {
        return pbaTranche;
    }

    public void setPbaTranche(String pbaTranche) {
        this.pbaTranche = pbaTranche;
    }

    public String getPbaLot() {
        return pbaLot;
    }

    public void setPbaLot(String pbaLot) {
        this.pbaLot = pbaLot;
    }

    public String getPbaAdressage() {
        return pbaAdressage;
    }

    public void setPbaAdressage(String pbaAdressage) {
        this.pbaAdressage = pbaAdressage;
    }

    public Boolean getPbaPfr() {
        return pbaPfr;
    }

    public void setPbaPfr(Boolean pbaPfr) {
        this.pbaPfr = pbaPfr;
    }

    @XmlTransient
    public List<TPointCardino> getTPointCardinoList() {
        return tPointCardinoList;
    }

    public void setTPointCardinoList(List<TPointCardino> tPointCardinoList) {
        this.tPointCardinoList = tPointCardinoList;
    }

    public TIntervenant getPbaProp() {
        return pbaProp;
    }

    public void setPbaProp(TIntervenant pbaProp) {
        this.pbaProp = pbaProp;
    }

    @XmlTransient
    public List<TParcelleBafon> getTParcelleBafonList() {
        return tParcelleBafonList;
    }

    public void setTParcelleBafonList(List<TParcelleBafon> tParcelleBafonList) {
        this.tParcelleBafonList = tParcelleBafonList;
    }

    public TParcelleBafon getPbaPbaNumero() {
        return pbaPbaNumero;
    }

    public void setPbaPbaNumero(TParcelleBafon pbaPbaNumero) {
        this.pbaPbaNumero = pbaPbaNumero;
    }

    public TStatut getPbaStaCode() {
        return pbaStaCode;
    }

    public void setPbaStaCode(TStatut pbaStaCode) {
        this.pbaStaCode = pbaStaCode;
    }

    public TTypebf getPbaTbfCode() {
        return pbaTbfCode;
    }

    public void setPbaTbfCode(TTypebf pbaTbfCode) {
        this.pbaTbfCode = pbaTbfCode;
    }

    public TVillage getPbaVilaCode() {
        return pbaVilaCode;
    }

    public void setPbaVilaCode(TVillage pbaVilaCode) {
        this.pbaVilaCode = pbaVilaCode;
    }

    @XmlTransient
    public List<TPvParcelle> getTPvParcelleList() {
        return tPvParcelleList;
    }

    public void setTPvParcelleList(List<TPvParcelle> tPvParcelleList) {
        this.tPvParcelleList = tPvParcelleList;
    }

    @XmlTransient
    public List<TOperationParcel> getTOperationParcelList() {
        return tOperationParcelList;
    }

    public void setTOperationParcelList(List<TOperationParcel> tOperationParcelList) {
        this.tOperationParcelList = tOperationParcelList;
    }

    @XmlTransient
    public List<TDroitExerce> getTDroitExerceList() {
        return tDroitExerceList;
    }

    public void setTDroitExerceList(List<TDroitExerce> tDroitExerceList) {
        this.tDroitExerceList = tDroitExerceList;
    }

    @XmlTransient
    public List<TConflit> getTConflitList() {
        return tConflitList;
    }

    public void setTConflitList(List<TConflit> tConflitList) {
        this.tConflitList = tConflitList;
    }

    @XmlTransient
    public List<TIntervenir> getTIntervenirList() {
        return tIntervenirList;
    }

    public void setTIntervenirList(List<TIntervenir> tIntervenirList) {
        this.tIntervenirList = tIntervenirList;
    }

    @XmlTransient
    public List<TIntervenir> getTIntervenirList1() {
        return tIntervenirList1;
    }

    public void setTIntervenirList1(List<TIntervenir> tIntervenirList1) {
        this.tIntervenirList1 = tIntervenirList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pbaNumero != null ? pbaNumero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TParcelleBafon)) {
            return false;
        }
        TParcelleBafon other = (TParcelleBafon) object;
        if ((this.pbaNumero == null && other.pbaNumero != null) || (this.pbaNumero != null && !this.pbaNumero.equals(other.pbaNumero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TParcelleBafon[ pbaNumero=" + pbaNumero + " ]";
    }

    public String getPbaAdcReference() {
        return pbaAdcReference;
    }

    public void setPbaAdcReference(String pbaAdcReference) {
        this.pbaAdcReference = pbaAdcReference;
    }

    public Date getPbaAdcDate() {
        return pbaAdcDate;
    }

    public void setPbaAdcDate(Date pbaAdcDate) {
        this.pbaAdcDate = pbaAdcDate;
    }

    public String getPbaAdcSignataire() {
        return pbaAdcSignataire;
    }

    public void setPbaAdcSignataire(String pbaAdcSignataire) {
        this.pbaAdcSignataire = pbaAdcSignataire;
    }

    public String getPbaAdcImage() {
        return pbaAdcImage;
    }

    public void setPbaAdcImage(String pbaAdcImage) {
        this.pbaAdcImage = pbaAdcImage;
    }

    public String getPbaFoncierReference() {
        return pbaFoncierReference;
    }

    public void setPbaFoncierReference(String pbaFoncierReference) {
        this.pbaFoncierReference = pbaFoncierReference;
    }

    public Date getPbaFoncierDate() {
        return pbaFoncierDate;
    }

    public void setPbaFoncierDate(Date pbaFoncierDate) {
        this.pbaFoncierDate = pbaFoncierDate;
    }

    public String getPbaFoncierSignataire() {
        return pbaFoncierSignataire;
    }

    public void setPbaFoncierSignataire(String pbaFoncierSignataire) {
        this.pbaFoncierSignataire = pbaFoncierSignataire;
    }

    public String getPbaFoncierImage() {
        return pbaFoncierImage;
    }

    public void setPbaFoncierImage(String pbaFoncierImage) {
        this.pbaFoncierImage = pbaFoncierImage;
    }

    public TUtilisateur getPbaCodeGeo() {
        return pbaCodeGeo;
    }

    public void setPbaCodeGeo(TUtilisateur pbaCodeGeo) {
        this.pbaCodeGeo = pbaCodeGeo;
    }

    @XmlTransient
    public List<TParcelleTypeBf> getTParcelleTypeBfList() {
        return tParcelleTypeBfList;
    }

    public void setTParcelleTypeBfList(List<TParcelleTypeBf> tParcelleTypeBfList) {
        this.tParcelleTypeBfList = tParcelleTypeBfList;
    }

    @XmlTransient
    public List<TParcellePoca> getTParcellePocaList() {
        return tParcellePocaList;
    }

    public void setTParcellePocaList(List<TParcellePoca> tParcellePocaList) {
        this.tParcellePocaList = tParcellePocaList;
    }

    @XmlTransient
    public List<TParcellePoca> getTParcellePocaList1() {
        return tParcellePocaList1;
    }
        @XmlTransient
 

    public void setTParcellePocaList1(List<TParcellePoca> tParcellePocaList1) {
        this.tParcellePocaList1 = tParcellePocaList1;
    }
    
}
