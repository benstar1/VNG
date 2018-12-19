/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author Ben
 */
@Entity
@Table(name = "t_intervenir")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TIntervenir.findAll", query = "SELECT t FROM TIntervenir t")
    , @NamedQuery(name = "TIntervenir.findByInvNumero", query = "SELECT t FROM TIntervenir t WHERE t.invNumero = :invNumero")
    , @NamedQuery(name = "TIntervenir.findByInvNumProp", query = "SELECT t FROM TIntervenir t WHERE t.invNumProp = :invNumProp")
    , @NamedQuery(name = "TIntervenir.findByInvDateDeb", query = "SELECT t FROM TIntervenir t WHERE t.invDateDeb = :invDateDeb")
    , @NamedQuery(name = "TIntervenir.findByInvDateFin", query = "SELECT t FROM TIntervenir t WHERE t.invDateFin = :invDateFin")
    , @NamedQuery(name = "TIntervenir.findByInvContreparti", query = "SELECT t FROM TIntervenir t WHERE t.invContreparti = :invContreparti")
    , @NamedQuery(name = "TIntervenir.findByInvAutremodalite", query = "SELECT t FROM TIntervenir t WHERE t.invAutremodalite = :invAutremodalite")
    , @NamedQuery(name = "TIntervenir.findByInvRattachement", query = "SELECT t FROM TIntervenir t WHERE t.invRattachement = :invRattachement")
    , @NamedQuery(name = "TIntervenir.findByInvAutochtone", query = "SELECT t FROM TIntervenir t WHERE t.invAutochtone = :invAutochtone")
    , @NamedQuery(name = "TIntervenir.findByInvObservation", query = "SELECT t FROM TIntervenir t WHERE t.invObservation = :invObservation")
    , @NamedQuery(name = "TIntervenir.findByInvPrix", query = "SELECT t FROM TIntervenir t WHERE t.invPrix = :invPrix")
    , @NamedQuery(name = "TIntervenir.findByInvAutreModPaie", query = "SELECT t FROM TIntervenir t WHERE t.invAutreModPaie = :invAutreModPaie")
    , @NamedQuery(name = "TIntervenir.findByInvLimitation", query = "SELECT t FROM TIntervenir t WHERE t.invLimitation = :invLimitation")
    , @NamedQuery(name = "TIntervenir.findByInvLimitLeg", query = "SELECT t FROM TIntervenir t WHERE t.invLimitLeg = :invLimitLeg")
    , @NamedQuery(name = "TIntervenir.findByInvLimitVent", query = "SELECT t FROM TIntervenir t WHERE t.invLimitVent = :invLimitVent")
    , @NamedQuery(name = "TIntervenir.findByInvLimitDon", query = "SELECT t FROM TIntervenir t WHERE t.invLimitDon = :invLimitDon")
    , @NamedQuery(name = "TIntervenir.findByInvLimitPret", query = "SELECT t FROM TIntervenir t WHERE t.invLimitPret = :invLimitPret")
    , @NamedQuery(name = "TIntervenir.findByInvLimiteAutre", query = "SELECT t FROM TIntervenir t WHERE t.invLimiteAutre = :invLimiteAutre")
    , @NamedQuery(name = "TIntervenir.findByInvRefPapier", query = "SELECT t FROM TIntervenir t WHERE t.invRefPapier = :invRefPapier")
    , @NamedQuery(name = "TIntervenir.findByIntvDateEnquete", query = "SELECT t FROM TIntervenir t WHERE t.intvDateEnquete = :intvDateEnquete")
    , @NamedQuery(name = "TIntervenir.findByInvAnneeArrive", query = "SELECT t FROM TIntervenir t WHERE t.invAnneeArrive = :invAnneeArrive")
    , @NamedQuery(name = "TIntervenir.findByInvCollectivite", query = "SELECT t FROM TIntervenir t WHERE t.invCollectivite = :invCollectivite")
    , @NamedQuery(name = "TIntervenir.findByInvPv", query = "SELECT t FROM TIntervenir t WHERE t.invPv = :invPv")
    , @NamedQuery(name = "TIntervenir.findByInvNomImg", query = "SELECT t FROM TIntervenir t WHERE t.invNomImg = :invNomImg")
    , @NamedQuery(name = "TIntervenir.findByInvNomPhoto", query = "SELECT t FROM TIntervenir t WHERE t.invNomPhoto = :invNomPhoto")
    , @NamedQuery(name = "TIntervenir.findByInvMacAutre", query = "SELECT t FROM TIntervenir t WHERE t.invMacAutre = :invMacAutre")
    , @NamedQuery(name = "TIntervenir.findByInvCopAutre", query = "SELECT t FROM TIntervenir t WHERE t.invCopAutre = :invCopAutre")
    , @NamedQuery(name = "TIntervenir.findByInvRolAutre", query = "SELECT t FROM TIntervenir t WHERE t.invRolAutre = :invRolAutre")
    , @NamedQuery(name = "TIntervenir.findByInvObservationRegistre", query = "SELECT t FROM TIntervenir t WHERE t.invObservationRegistre = :invObservationRegistre")
    , @NamedQuery(name = "TIntervenir.findByInvEtatLieu", query = "SELECT t FROM TIntervenir t WHERE t.invEtatLieu = :invEtatLieu")
    , @NamedQuery(name = "TIntervenir.findByInvRattachementAutre", query = "SELECT t FROM TIntervenir t WHERE t.invRattachementAutre = :invRattachementAutre")
    , @NamedQuery(name = "TIntervenir.findByInvFormalise", query = "SELECT t FROM TIntervenir t WHERE t.invFormalise = :invFormalise")
    , @NamedQuery(name = "TIntervenir.findByInvCodeCommune", query = "SELECT t FROM TIntervenir t WHERE t.invCodeCommune = :invCodeCommune")
    , @NamedQuery(name = "TIntervenir.findByIntLigCode", query = "SELECT t FROM TIntervenir t WHERE t.intLigCode = :intLigCode")
    , @NamedQuery(name = "TIntervenir.findByIntImageTemoin", query = "SELECT t FROM TIntervenir t WHERE t.intImageTemoin = :intImageTemoin")
    , @NamedQuery(name = "TIntervenir.findByIntImagePapier1", query = "SELECT t FROM TIntervenir t WHERE t.intImagePapier1 = :intImagePapier1")
    , @NamedQuery(name = "TIntervenir.findByIntImagePapier2", query = "SELECT t FROM TIntervenir t WHERE t.intImagePapier2 = :intImagePapier2")
    , @NamedQuery(name = "TIntervenir.findByIntImagePapier3", query = "SELECT t FROM TIntervenir t WHERE t.intImagePapier3 = :intImagePapier3")
    , @NamedQuery(name = "TIntervenir.findByIntImagePapier4", query = "SELECT t FROM TIntervenir t WHERE t.intImagePapier4 = :intImagePapier4")
    , @NamedQuery(name = "TIntervenir.findByInvIndetermine", query = "SELECT t FROM TIntervenir t WHERE t.invIndetermine = :invIndetermine")
    , @NamedQuery(name = "TIntervenir.findByInvDuree", query = "SELECT t FROM TIntervenir t WHERE t.invDuree = :invDuree")
    , @NamedQuery(name = "TIntervenir.findByInvLimitationOp", query = "SELECT t FROM TIntervenir t WHERE t.invLimitationOp = :invLimitationOp")
    , @NamedQuery(name = "TIntervenir.findByInvLimitAliener", query = "SELECT t FROM TIntervenir t WHERE t.invLimitAliener = :invLimitAliener")
    , @NamedQuery(name = "TIntervenir.findByInvLimitTransmission", query = "SELECT t FROM TIntervenir t WHERE t.invLimitTransmission = :invLimitTransmission")
    , @NamedQuery(name = "TIntervenir.findByInvLimitPlanter", query = "SELECT t FROM TIntervenir t WHERE t.invLimitPlanter = :invLimitPlanter")
    , @NamedQuery(name = "TIntervenir.findByInvLimitRecolterFruit", query = "SELECT t FROM TIntervenir t WHERE t.invLimitRecolterFruit = :invLimitRecolterFruit")
    , @NamedQuery(name = "TIntervenir.findByInvLimitConstruireCase", query = "SELECT t FROM TIntervenir t WHERE t.invLimitConstruireCase = :invLimitConstruireCase")
    , @NamedQuery(name = "TIntervenir.findByInvLimitHabiterTerrain", query = "SELECT t FROM TIntervenir t WHERE t.invLimitHabiterTerrain = :invLimitHabiterTerrain")
    , @NamedQuery(name = "TIntervenir.findByInvLimitAutreOp", query = "SELECT t FROM TIntervenir t WHERE t.invLimitAutreOp = :invLimitAutreOp")
    , @NamedQuery(name = "TIntervenir.findByInvRepresente", query = "SELECT t FROM TIntervenir t WHERE t.invRepresente = :invRepresente")
    , @NamedQuery(name = "TIntervenir.findByInvDateChargement", query = "SELECT t FROM TIntervenir t WHERE t.invDateChargement = :invDateChargement")
    , @NamedQuery(name = "TIntervenir.findByInvNomAudio", query = "SELECT t FROM TIntervenir t WHERE t.invNomAudio = :invNomAudio")
    , @NamedQuery(name = "TIntervenir.findByInvIntervenantAudio", query = "SELECT t FROM TIntervenir t WHERE t.invIntervenantAudio = :invIntervenantAudio")
    , @NamedQuery(name = "TIntervenir.findByInvNomVideo", query = "SELECT t FROM TIntervenir t WHERE t.invNomVideo = :invNomVideo")
    , @NamedQuery(name = "TIntervenir.findByInvIntervenantVideo", query = "SELECT t FROM TIntervenir t WHERE t.invIntervenantVideo = :invIntervenantVideo")
    , @NamedQuery(name = "TIntervenir.findByInvLimitAutreOpChaine", query = "SELECT t FROM TIntervenir t WHERE t.invLimitAutreOpChaine = :invLimitAutreOpChaine")
    , @NamedQuery(name = "TIntervenir.findByInvType", query = "SELECT t FROM TIntervenir t WHERE t.invType = :invType")
    , @NamedQuery(name = "TIntervenir.findByInvDeQui", query = "SELECT t FROM TIntervenir t WHERE t.invDeQui = :invDeQui")
    , @NamedQuery(name = "TIntervenir.findByInvStatut", query = "SELECT t FROM TIntervenir t WHERE t.invStatut = :invStatut")
    , @NamedQuery(name = "TIntervenir.findByInvStructure", query = "SELECT t FROM TIntervenir t WHERE t.invStructure = :invStructure")
    , @NamedQuery(name = "TIntervenir.findByInvFonction", query = "SELECT t FROM TIntervenir t WHERE t.invFonction = :invFonction")
    , @NamedQuery(name = "TIntervenir.findByInvDopRepresenter", query = "SELECT t FROM TIntervenir t WHERE t.invDopRepresenter = :invDopRepresenter")
    , @NamedQuery(name = "TIntervenir.findByInvDopDetenteur", query = "SELECT t FROM TIntervenir t WHERE t.invDopDetenteur = :invDopDetenteur")
    , @NamedQuery(name = "TIntervenir.findByInvCiPp", query = "SELECT t FROM TIntervenir t WHERE t.invCiPp = :invCiPp")
    , @NamedQuery(name = "TIntervenir.findDetentDroitAdmin", query = "SELECT t FROM TIntervenir t WHERE t.invDateFin is null AND t.invPbaNumero = :parcelle AND t.invRolCode.rolCat= :categorierole ORDER BY t.invDateDeb DESC") 
    , @NamedQuery(name = "TIntervenir.findMaxIntervenir", query = "SELECT Max(t.invNumero) FROM TIntervenir t WHERE t.invNumero LIKE :annee")
    , @NamedQuery(name = "TIntervenir.findByInvDateExpCiPp", query = "SELECT t FROM TIntervenir t WHERE t.invDateExpCiPp = :invDateExpCiPp")})
public class TIntervenir implements Serializable {

    @JoinColumn(name = "inv_roc_code", referencedColumnName = "roc_code")
    @ManyToOne
    private TRolec invRocCode;

    @JoinColumn(name = "inv_desi_code", referencedColumnName = "desi_code")
    @ManyToOne
    private TDepotSignature invDesiCode;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "inv_numero")
    private String invNumero;
    @Size(max = 50)
    @Column(name = "inv_num_prop")
    private String invNumProp;
    @Column(name = "inv_date_deb")
    @Temporal(TemporalType.DATE)
    private Date invDateDeb;
    @Column(name = "inv_date_fin")
    @Temporal(TemporalType.DATE)
    private Date invDateFin;
    @Size(max = 200)
    @Column(name = "inv_contreparti")
    private String invContreparti;
    @Size(max = 200)
    @Column(name = "inv_autremodalite")
    private String invAutremodalite;
    @Size(max = 200)
    @Column(name = "inv_rattachement")
    private String invRattachement;
    @Column(name = "inv_autochtone")
    private Boolean invAutochtone;
    @Size(max = 500)
    @Column(name = "inv_observation")
    private String invObservation;
    @Column(name = "inv_prix")
    private Long invPrix;
    @Size(max = 300)
    @Column(name = "inv_autre_mod_paie")
    private String invAutreModPaie;
    @Column(name = "inv_limitation")
    private Boolean invLimitation;
    @Column(name = "inv_limit_leg")
    private Boolean invLimitLeg;
    @Column(name = "inv_limit_vent")
    private Boolean invLimitVent;
    @Column(name = "inv_limit_don")
    private Boolean invLimitDon;
    @Column(name = "inv_limit_pret")
    private Boolean invLimitPret;
    @Size(max = 300)
    @Column(name = "inv_limite_autre")
    private String invLimiteAutre;
    @Size(max = 50)
    @Column(name = "inv_ref_papier")
    private String invRefPapier;
    @Column(name = "intv_date_enquete")
    @Temporal(TemporalType.DATE)
    private Date intvDateEnquete;
    @Size(max = 2147483647)
    @Column(name = "inv_annee_arrive")
    private String invAnneeArrive;
    @Size(max = 150)
    @Column(name = "inv_collectivite")
    private String invCollectivite;
    @Size(max = 40000)
    @Column(name = "inv_pv")
    private String invPv;
    @Size(max = 500)
    @Column(name = "inv_nom_img")
    private String invNomImg;
    @Size(max = 500)
    @Column(name = "inv_nom_photo")
    private String invNomPhoto;
    @Size(max = 100)
    @Column(name = "inv_mac_autre")
    private String invMacAutre;
    @Size(max = 100)
    @Column(name = "inv_cop_autre")
    private String invCopAutre;
    @Size(max = 100)
    @Column(name = "inv_rol_autre")
    private String invRolAutre;
    @Size(max = 500)
    @Column(name = "inv_observation_registre")
    private String invObservationRegistre;
    @Size(max = 500)
    @Column(name = "inv_etat_lieu")
    private String invEtatLieu;
    @Size(max = 100)
    @Column(name = "inv_rattachement_autre")
    private String invRattachementAutre;
    @Size(max = 100)
    @Column(name = "inv_formalise")
    private String invFormalise;
    @Size(max = 50)
    @Column(name = "inv_code_commune")
    private String invCodeCommune;
    @Size(max = 5)
    @Column(name = "int_lig_code")
    private String intLigCode;
    @Size(max = 500)
    @Column(name = "int_image_temoin")
    private String intImageTemoin;
    @Size(max = 500)
    @Column(name = "int_image_papier1")
    private String intImagePapier1;
    @Size(max = 500)
    @Column(name = "int_image_papier2")
    private String intImagePapier2;
    @Size(max = 500)
    @Column(name = "int_image_papier3")
    private String intImagePapier3;
    @Size(max = 500)
    @Column(name = "int_image_papier4")
    private String intImagePapier4;
    @Size(max = 5)
    @Column(name = "inv_indetermine")
    private String invIndetermine;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "inv_duree")
    private BigDecimal invDuree;
    @Size(max = 5)
    @Column(name = "inv_limitation_op")
    private String invLimitationOp;
    @Size(max = 5)
    @Column(name = "inv_limit_aliener")
    private String invLimitAliener;
    @Size(max = 5)
    @Column(name = "inv_limit_transmission")
    private String invLimitTransmission;
    @Size(max = 5)
    @Column(name = "inv_limit_planter")
    private String invLimitPlanter;
    @Size(max = 5)
    @Column(name = "inv_limit_recolter_fruit")
    private String invLimitRecolterFruit;
    @Size(max = 5)
    @Column(name = "inv_limit_construire_case")
    private String invLimitConstruireCase;
    @Size(max = 5)
    @Column(name = "inv_limit_habiter_terrain")
    private String invLimitHabiterTerrain;
    @Size(max = 5)
    @Column(name = "inv_limit_autre_op")
    private String invLimitAutreOp;
    @Size(max = 2)
    @Column(name = "inv_represente")
    private String invRepresente;
    @Column(name = "inv_date_chargement")
    @Temporal(TemporalType.DATE)
    private Date invDateChargement;
    @Size(max = 500)
    @Column(name = "inv_nom_audio")
    private String invNomAudio;
    @Size(max = 500)
    @Column(name = "inv_intervenant_audio")
    private String invIntervenantAudio;
    @Size(max = 500)
    @Column(name = "inv_nom_video")
    private String invNomVideo;
    @Size(max = 500)
    @Column(name = "inv_intervenant_video")
    private String invIntervenantVideo;
    @Size(max = 50)
    @Column(name = "inv_limit_autre_op_chaine")
    private String invLimitAutreOpChaine;
    @Size(max = 10)
    @Column(name = "inv_type")
    private String invType;
    @Size(max = 150)
    @Column(name = "inv_de_qui")
    private String invDeQui;
    @Size(max = 10)
    @Column(name = "inv_statut")
    private String invStatut;
    @Size(max = 200)
    @Column(name = "inv_structure")
    private String invStructure;
    @Size(max = 200)
    @Column(name = "inv_fonction")
    private String invFonction;
    @Column(name = "inv_dop_representer")
    private Boolean invDopRepresenter;
    @Size(max = 300)
    @Column(name = "inv_dop_detenteur")
    private String invDopDetenteur;
    @Size(max = 50)
    @Column(name = "inv_ci_pp")
    private String invCiPp;
    @Column(name = "inv_date_exp_ci_pp")
    @Temporal(TemporalType.DATE)
    private Date invDateExpCiPp;
    @JoinColumn(name = "inv_enq_code", referencedColumnName = "enq_code")
    @ManyToOne
    private TEnquete invEnqCode;
    @JoinColumn(name = "inv_cop_code", referencedColumnName = "cop_code")
    @ManyToOne
    private TConditionpaie invCopCode;
    @JoinColumn(name = "inv_int_numero", referencedColumnName = "int_numero")
    @ManyToOne(optional = false)
    private TIntervenant invIntNumero;
    @JoinColumn(name = "inv_mac_code", referencedColumnName = "mac_code")
    @ManyToOne
    private TModeacquis invMacCode;
    @JoinColumn(name = "inv_mut_numero", referencedColumnName = "mut_numero")
    @ManyToOne
    private TMutation invMutNumero;
    @JoinColumn(name = "inv_nat_papier", referencedColumnName = "nap_code")
    @ManyToOne
    private TNaturepapier invNatPapier;
    @JoinColumn(name = "inv_opv_numero", referencedColumnName = "opv_numero")
    @ManyToOne
    private TOperationParcel invOpvNumero;
    @JoinColumn(name = "inv_opv_numero_preneur", referencedColumnName = "opv_numero")
    @ManyToOne
    private TOperationParcel invOpvNumeroPreneur;
    @JoinColumn(name = "inv_uti_code", referencedColumnName = "uti_code")
    @ManyToOne
    private TUtilisateur invUtiCode;
    @JoinColumn(name = "inv_pba_numero_lim", referencedColumnName = "pba_numero")
    @ManyToOne
    private TParcelleBafon invPbaNumeroLim;
    @JoinColumn(name = "inv_pba_numero", referencedColumnName = "pba_numero")
    @ManyToOne(optional = false)
    private TParcelleBafon invPbaNumero;
    @JoinColumn(name = "inv_rol_code", referencedColumnName = "rol_code")
    @ManyToOne(optional = false)
    private TRole invRolCode;

    public TIntervenir() {
    }

    public TIntervenir(String invNumero) {
        this.invNumero = invNumero;
    }

    public String getInvNumero() {
        return invNumero;
    }

    public void setInvNumero(String invNumero) {
        this.invNumero = invNumero;
    }

    public String getInvNumProp() {
        return invNumProp;
    }

    public void setInvNumProp(String invNumProp) {
        this.invNumProp = invNumProp;
    }

    public Date getInvDateDeb() {
        return invDateDeb;
    }

    public void setInvDateDeb(Date invDateDeb) {
        this.invDateDeb = invDateDeb;
    }

    public Date getInvDateFin() {
        return invDateFin;
    }

    public void setInvDateFin(Date invDateFin) {
        this.invDateFin = invDateFin;
    }

    public String getInvContreparti() {
        return invContreparti;
    }

    public void setInvContreparti(String invContreparti) {
        this.invContreparti = invContreparti;
    }

    public String getInvAutremodalite() {
        return invAutremodalite;
    }

    public void setInvAutremodalite(String invAutremodalite) {
        this.invAutremodalite = invAutremodalite;
    }

    public String getInvRattachement() {
        return invRattachement;
    }

    public void setInvRattachement(String invRattachement) {
        this.invRattachement = invRattachement;
    }

    public Boolean getInvAutochtone() {
        return invAutochtone;
    }

    public void setInvAutochtone(Boolean invAutochtone) {
        this.invAutochtone = invAutochtone;
    }

    public String getInvObservation() {
        return invObservation;
    }

    public void setInvObservation(String invObservation) {
        this.invObservation = invObservation;
    }

    public Long getInvPrix() {
        return invPrix;
    }

    public void setInvPrix(Long invPrix) {
        this.invPrix = invPrix;
    }

    public String getInvAutreModPaie() {
        return invAutreModPaie;
    }

    public void setInvAutreModPaie(String invAutreModPaie) {
        this.invAutreModPaie = invAutreModPaie;
    }

    public Boolean getInvLimitation() {
        return invLimitation;
    }

    public void setInvLimitation(Boolean invLimitation) {
        this.invLimitation = invLimitation;
    }

    public Boolean getInvLimitLeg() {
        return invLimitLeg;
    }

    public void setInvLimitLeg(Boolean invLimitLeg) {
        this.invLimitLeg = invLimitLeg;
    }

    public Boolean getInvLimitVent() {
        return invLimitVent;
    }

    public void setInvLimitVent(Boolean invLimitVent) {
        this.invLimitVent = invLimitVent;
    }

    public Boolean getInvLimitDon() {
        return invLimitDon;
    }

    public void setInvLimitDon(Boolean invLimitDon) {
        this.invLimitDon = invLimitDon;
    }

    public Boolean getInvLimitPret() {
        return invLimitPret;
    }

    public void setInvLimitPret(Boolean invLimitPret) {
        this.invLimitPret = invLimitPret;
    }

    public String getInvLimiteAutre() {
        return invLimiteAutre;
    }

    public void setInvLimiteAutre(String invLimiteAutre) {
        this.invLimiteAutre = invLimiteAutre;
    }

    public String getInvRefPapier() {
        return invRefPapier;
    }

    public void setInvRefPapier(String invRefPapier) {
        this.invRefPapier = invRefPapier;
    }

    public Date getIntvDateEnquete() {
        return intvDateEnquete;
    }

    public void setIntvDateEnquete(Date intvDateEnquete) {
        this.intvDateEnquete = intvDateEnquete;
    }

    public String getInvAnneeArrive() {
        return invAnneeArrive;
    }

    public void setInvAnneeArrive(String invAnneeArrive) {
        this.invAnneeArrive = invAnneeArrive;
    }

    public String getInvCollectivite() {
        return invCollectivite;
    }

    public void setInvCollectivite(String invCollectivite) {
        this.invCollectivite = invCollectivite;
    }

    public String getInvPv() {
        return invPv;
    }

    public void setInvPv(String invPv) {
        this.invPv = invPv;
    }

    public String getInvNomImg() {
        return invNomImg;
    }

    public void setInvNomImg(String invNomImg) {
        this.invNomImg = invNomImg;
    }

    public String getInvNomPhoto() {
        return invNomPhoto;
    }

    public void setInvNomPhoto(String invNomPhoto) {
        this.invNomPhoto = invNomPhoto;
    }

    public String getInvMacAutre() {
        return invMacAutre;
    }

    public void setInvMacAutre(String invMacAutre) {
        this.invMacAutre = invMacAutre;
    }

    public String getInvCopAutre() {
        return invCopAutre;
    }

    public void setInvCopAutre(String invCopAutre) {
        this.invCopAutre = invCopAutre;
    }

    public String getInvRolAutre() {
        return invRolAutre;
    }

    public void setInvRolAutre(String invRolAutre) {
        this.invRolAutre = invRolAutre;
    }

    public String getInvObservationRegistre() {
        return invObservationRegistre;
    }

    public void setInvObservationRegistre(String invObservationRegistre) {
        this.invObservationRegistre = invObservationRegistre;
    }

    public String getInvEtatLieu() {
        return invEtatLieu;
    }

    public void setInvEtatLieu(String invEtatLieu) {
        this.invEtatLieu = invEtatLieu;
    }

    public String getInvRattachementAutre() {
        return invRattachementAutre;
    }

    public void setInvRattachementAutre(String invRattachementAutre) {
        this.invRattachementAutre = invRattachementAutre;
    }

    public String getInvFormalise() {
        return invFormalise;
    }

    public void setInvFormalise(String invFormalise) {
        this.invFormalise = invFormalise;
    }

    public String getInvCodeCommune() {
        return invCodeCommune;
    }

    public void setInvCodeCommune(String invCodeCommune) {
        this.invCodeCommune = invCodeCommune;
    }

    public String getIntLigCode() {
        return intLigCode;
    }

    public void setIntLigCode(String intLigCode) {
        this.intLigCode = intLigCode;
    }

    public String getIntImageTemoin() {
        return intImageTemoin;
    }

    public void setIntImageTemoin(String intImageTemoin) {
        this.intImageTemoin = intImageTemoin;
    }

    public String getIntImagePapier1() {
        return intImagePapier1;
    }

    public void setIntImagePapier1(String intImagePapier1) {
        this.intImagePapier1 = intImagePapier1;
    }

    public String getIntImagePapier2() {
        return intImagePapier2;
    }

    public void setIntImagePapier2(String intImagePapier2) {
        this.intImagePapier2 = intImagePapier2;
    }

    public String getIntImagePapier3() {
        return intImagePapier3;
    }

    public void setIntImagePapier3(String intImagePapier3) {
        this.intImagePapier3 = intImagePapier3;
    }

    public String getIntImagePapier4() {
        return intImagePapier4;
    }

    public void setIntImagePapier4(String intImagePapier4) {
        this.intImagePapier4 = intImagePapier4;
    }

    public String getInvIndetermine() {
        return invIndetermine;
    }

    public void setInvIndetermine(String invIndetermine) {
        this.invIndetermine = invIndetermine;
    }

    public BigDecimal getInvDuree() {
        return invDuree;
    }

    public void setInvDuree(BigDecimal invDuree) {
        this.invDuree = invDuree;
    }

    public String getInvLimitationOp() {
        return invLimitationOp;
    }

    public void setInvLimitationOp(String invLimitationOp) {
        this.invLimitationOp = invLimitationOp;
    }

    public String getInvLimitAliener() {
        return invLimitAliener;
    }

    public void setInvLimitAliener(String invLimitAliener) {
        this.invLimitAliener = invLimitAliener;
    }

    public String getInvLimitTransmission() {
        return invLimitTransmission;
    }

    public void setInvLimitTransmission(String invLimitTransmission) {
        this.invLimitTransmission = invLimitTransmission;
    }

    public String getInvLimitPlanter() {
        return invLimitPlanter;
    }

    public void setInvLimitPlanter(String invLimitPlanter) {
        this.invLimitPlanter = invLimitPlanter;
    }

    public String getInvLimitRecolterFruit() {
        return invLimitRecolterFruit;
    }

    public void setInvLimitRecolterFruit(String invLimitRecolterFruit) {
        this.invLimitRecolterFruit = invLimitRecolterFruit;
    }

    public String getInvLimitConstruireCase() {
        return invLimitConstruireCase;
    }

    public void setInvLimitConstruireCase(String invLimitConstruireCase) {
        this.invLimitConstruireCase = invLimitConstruireCase;
    }

    public String getInvLimitHabiterTerrain() {
        return invLimitHabiterTerrain;
    }

    public void setInvLimitHabiterTerrain(String invLimitHabiterTerrain) {
        this.invLimitHabiterTerrain = invLimitHabiterTerrain;
    }

    public String getInvLimitAutreOp() {
        return invLimitAutreOp;
    }

    public void setInvLimitAutreOp(String invLimitAutreOp) {
        this.invLimitAutreOp = invLimitAutreOp;
    }

    public String getInvRepresente() {
        return invRepresente;
    }

    public void setInvRepresente(String invRepresente) {
        this.invRepresente = invRepresente;
    }

    public Date getInvDateChargement() {
        return invDateChargement;
    }

    public void setInvDateChargement(Date invDateChargement) {
        this.invDateChargement = invDateChargement;
    }

    public String getInvNomAudio() {
        return invNomAudio;
    }

    public void setInvNomAudio(String invNomAudio) {
        this.invNomAudio = invNomAudio;
    }

    public String getInvIntervenantAudio() {
        return invIntervenantAudio;
    }

    public void setInvIntervenantAudio(String invIntervenantAudio) {
        this.invIntervenantAudio = invIntervenantAudio;
    }

    public String getInvNomVideo() {
        return invNomVideo;
    }

    public void setInvNomVideo(String invNomVideo) {
        this.invNomVideo = invNomVideo;
    }

    public String getInvIntervenantVideo() {
        return invIntervenantVideo;
    }

    public void setInvIntervenantVideo(String invIntervenantVideo) {
        this.invIntervenantVideo = invIntervenantVideo;
    }

    public String getInvLimitAutreOpChaine() {
        return invLimitAutreOpChaine;
    }

    public void setInvLimitAutreOpChaine(String invLimitAutreOpChaine) {
        this.invLimitAutreOpChaine = invLimitAutreOpChaine;
    }

    public String getInvType() {
        return invType;
    }

    public void setInvType(String invType) {
        this.invType = invType;
    }

    public String getInvDeQui() {
        return invDeQui;
    }

    public void setInvDeQui(String invDeQui) {
        this.invDeQui = invDeQui;
    }

    public String getInvStatut() {
        return invStatut;
    }

    public void setInvStatut(String invStatut) {
        this.invStatut = invStatut;
    }

    public String getInvStructure() {
        return invStructure;
    }

    public void setInvStructure(String invStructure) {
        this.invStructure = invStructure;
    }

    public String getInvFonction() {
        return invFonction;
    }

    public void setInvFonction(String invFonction) {
        this.invFonction = invFonction;
    }

    public Boolean getInvDopRepresenter() {
        return invDopRepresenter;
    }

    public void setInvDopRepresenter(Boolean invDopRepresenter) {
        this.invDopRepresenter = invDopRepresenter;
    }

    public String getInvDopDetenteur() {
        return invDopDetenteur;
    }

    public void setInvDopDetenteur(String invDopDetenteur) {
        this.invDopDetenteur = invDopDetenteur;
    }

    public String getInvCiPp() {
        return invCiPp;
    }

    public void setInvCiPp(String invCiPp) {
        this.invCiPp = invCiPp;
    }

    public Date getInvDateExpCiPp() {
        return invDateExpCiPp;
    }

    public void setInvDateExpCiPp(Date invDateExpCiPp) {
        this.invDateExpCiPp = invDateExpCiPp;
    }

    public TEnquete getInvEnqCode() {
        return invEnqCode;
    }

    public void setInvEnqCode(TEnquete invEnqCode) {
        this.invEnqCode = invEnqCode;
    }

    public TConditionpaie getInvCopCode() {
        return invCopCode;
    }

    public void setInvCopCode(TConditionpaie invCopCode) {
        this.invCopCode = invCopCode;
    }

    public TIntervenant getInvIntNumero() {
        return invIntNumero;
    }

    public void setInvIntNumero(TIntervenant invIntNumero) {
        this.invIntNumero = invIntNumero;
    }

    public TModeacquis getInvMacCode() {
        return invMacCode;
    }

    public void setInvMacCode(TModeacquis invMacCode) {
        this.invMacCode = invMacCode;
    }

    public TMutation getInvMutNumero() {
        return invMutNumero;
    }

    public void setInvMutNumero(TMutation invMutNumero) {
        this.invMutNumero = invMutNumero;
    }

    public TNaturepapier getInvNatPapier() {
        return invNatPapier;
    }

    public void setInvNatPapier(TNaturepapier invNatPapier) {
        this.invNatPapier = invNatPapier;
    }

    public TOperationParcel getInvOpvNumero() {
        return invOpvNumero;
    }

    public void setInvOpvNumero(TOperationParcel invOpvNumero) {
        this.invOpvNumero = invOpvNumero;
    }

    public TOperationParcel getInvOpvNumeroPreneur() {
        return invOpvNumeroPreneur;
    }

    public void setInvOpvNumeroPreneur(TOperationParcel invOpvNumeroPreneur) {
        this.invOpvNumeroPreneur = invOpvNumeroPreneur;
    }

    public TUtilisateur getInvUtiCode() {
        return invUtiCode;
    }

    public void setInvUtiCode(TUtilisateur invUtiCode) {
        this.invUtiCode = invUtiCode;
    }

    public TParcelleBafon getInvPbaNumeroLim() {
        return invPbaNumeroLim;
    }

    public void setInvPbaNumeroLim(TParcelleBafon invPbaNumeroLim) {
        this.invPbaNumeroLim = invPbaNumeroLim;
    }

    public TParcelleBafon getInvPbaNumero() {
        return invPbaNumero;
    }

    public void setInvPbaNumero(TParcelleBafon invPbaNumero) {
        this.invPbaNumero = invPbaNumero;
    }

    public TRole getInvRolCode() {
        return invRolCode;
    }

    public void setInvRolCode(TRole invRolCode) {
        this.invRolCode = invRolCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invNumero != null ? invNumero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TIntervenir)) {
            return false;
        }
        TIntervenir other = (TIntervenir) object;
        if ((this.invNumero == null && other.invNumero != null) || (this.invNumero != null && !this.invNumero.equals(other.invNumero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TIntervenir[ invNumero=" + invNumero + " ]";
    }

    public TDepotSignature getInvDesiCode() {
        return invDesiCode;
    }

    public void setInvDesiCode(TDepotSignature invDesiCode) {
        this.invDesiCode = invDesiCode;
    }

    public TRolec getInvRocCode() {
        return invRocCode;
    }

    public void setInvRocCode(TRolec invRocCode) {
        this.invRocCode = invRocCode;
    }
    
}
