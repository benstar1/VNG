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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "t_operation_parcel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TOperationParcel.findAll", query = "SELECT t FROM TOperationParcel t")
    , @NamedQuery(name = "TOperationParcel.findByOpvNumero", query = "SELECT t FROM TOperationParcel t WHERE t.opvNumero = :opvNumero")
    , @NamedQuery(name = "TOperationParcel.findByOpvDateOp", query = "SELECT t FROM TOperationParcel t WHERE t.opvDateOp = :opvDateOp")
    , @NamedQuery(name = "TOperationParcel.findByOpvPrix", query = "SELECT t FROM TOperationParcel t WHERE t.opvPrix = :opvPrix")
    , @NamedQuery(name = "TOperationParcel.findByOpvNumeroPv", query = "SELECT t FROM TOperationParcel t WHERE t.opvNumeroPv = :opvNumeroPv")
    , @NamedQuery(name = "TOperationParcel.findByOpvLimitPlanter", query = "SELECT t FROM TOperationParcel t WHERE t.opvLimitPlanter = :opvLimitPlanter")
    , @NamedQuery(name = "TOperationParcel.findByOpvLimitRecolterFruit", query = "SELECT t FROM TOperationParcel t WHERE t.opvLimitRecolterFruit = :opvLimitRecolterFruit")
    , @NamedQuery(name = "TOperationParcel.findByOpvLimitConstruireCase", query = "SELECT t FROM TOperationParcel t WHERE t.opvLimitConstruireCase = :opvLimitConstruireCase")
    , @NamedQuery(name = "TOperationParcel.findByOpvLimitHabiterTerrain", query = "SELECT t FROM TOperationParcel t WHERE t.opvLimitHabiterTerrain = :opvLimitHabiterTerrain")
    , @NamedQuery(name = "TOperationParcel.findByOpvLimitAutreOp", query = "SELECT t FROM TOperationParcel t WHERE t.opvLimitAutreOp = :opvLimitAutreOp")
    , @NamedQuery(name = "TOperationParcel.findByOpvLimitationOp", query = "SELECT t FROM TOperationParcel t WHERE t.opvLimitationOp = :opvLimitationOp")
    , @NamedQuery(name = "TOperationParcel.findByOpvLimitAliener", query = "SELECT t FROM TOperationParcel t WHERE t.opvLimitAliener = :opvLimitAliener")
    , @NamedQuery(name = "TOperationParcel.findByOpvLimitTransmission", query = "SELECT t FROM TOperationParcel t WHERE t.opvLimitTransmission = :opvLimitTransmission")
    , @NamedQuery(name = "TOperationParcel.findByOpvLimitation", query = "SELECT t FROM TOperationParcel t WHERE t.opvLimitation = :opvLimitation")
    , @NamedQuery(name = "TOperationParcel.findByOpvLimitLeg", query = "SELECT t FROM TOperationParcel t WHERE t.opvLimitLeg = :opvLimitLeg")
    , @NamedQuery(name = "TOperationParcel.findByOpvLimitVent", query = "SELECT t FROM TOperationParcel t WHERE t.opvLimitVent = :opvLimitVent")
    , @NamedQuery(name = "TOperationParcel.findByOpvLimitDon", query = "SELECT t FROM TOperationParcel t WHERE t.opvLimitDon = :opvLimitDon")
    , @NamedQuery(name = "TOperationParcel.findByOpvLimitPret", query = "SELECT t FROM TOperationParcel t WHERE t.opvLimitPret = :opvLimitPret")
    , @NamedQuery(name = "TOperationParcel.findByOpvLimiteAutre", query = "SELECT t FROM TOperationParcel t WHERE t.opvLimiteAutre = :opvLimiteAutre")
    , @NamedQuery(name = "TOperationParcel.findByOpvDuree", query = "SELECT t FROM TOperationParcel t WHERE t.opvDuree = :opvDuree")
    , @NamedQuery(name = "TOperationParcel.findByOpvDateDeb", query = "SELECT t FROM TOperationParcel t WHERE t.opvDateDeb = :opvDateDeb")
    , @NamedQuery(name = "TOperationParcel.findByOpvDateFin", query = "SELECT t FROM TOperationParcel t WHERE t.opvDateFin = :opvDateFin")
    , @NamedQuery(name = "TOperationParcel.findByOpvMutObjet", query = "SELECT t FROM TOperationParcel t WHERE t.opvMutObjet = :opvMutObjet")
    , @NamedQuery(name = "TOperationParcel.findByOpvMutDateMut", query = "SELECT t FROM TOperationParcel t WHERE t.opvMutDateMut = :opvMutDateMut")
    , @NamedQuery(name = "TOperationParcel.findByOpvNatureCulture", query = "SELECT t FROM TOperationParcel t WHERE t.opvNatureCulture = :opvNatureCulture")
    , @NamedQuery(name = "TOperationParcel.findByOpvLieuPartage", query = "SELECT t FROM TOperationParcel t WHERE t.opvLieuPartage = :opvLieuPartage")
    , @NamedQuery(name = "TOperationParcel.findByOpvDesiReference", query = "SELECT t FROM TOperationParcel t WHERE t.opvDesiReference = :opvDesiReference")
    , @NamedQuery(name = "TOperationParcel.findByOpvDesiDate", query = "SELECT t FROM TOperationParcel t WHERE t.opvDesiDate = :opvDesiDate")
    , @NamedQuery(name = "TOperationParcel.findByOpvNumeroAffirmation", query = "SELECT t FROM TOperationParcel t WHERE t.opvNumeroAffirmation = :opvNumeroAffirmation")
    , @NamedQuery(name = "TOperationParcel.findByOpvNomMaire", query = "SELECT t FROM TOperationParcel t WHERE t.opvNomMaire = :opvNomMaire")
    , @NamedQuery(name = "TOperationParcel.findByOpvNomInterprete", query = "SELECT t FROM TOperationParcel t WHERE t.opvNomInterprete = :opvNomInterprete")
    , @NamedQuery(name = "TOperationParcel.findByOpvRattachement", query = "SELECT t FROM TOperationParcel t WHERE t.opvRattachement = :opvRattachement")
    , @NamedQuery(name = "TOperationParcel.findByOpvContreparti", query = "SELECT t FROM TOperationParcel t WHERE t.opvContreparti = :opvContreparti")
    , @NamedQuery(name = "TOperationParcel.findByOpvAutremodalite", query = "SELECT t FROM TOperationParcel t WHERE t.opvAutremodalite = :opvAutremodalite")
    , @NamedQuery(name = "TOperationParcel.findByOpvCopAutre", query = "SELECT t FROM TOperationParcel t WHERE t.opvCopAutre = :opvCopAutre")
    , @NamedQuery(name = "TOperationParcel.findByOpvRolAutre", query = "SELECT t FROM TOperationParcel t WHERE t.opvRolAutre = :opvRolAutre")
    , @NamedQuery(name = "TOperationParcel.findByOpvMacAutre", query = "SELECT t FROM TOperationParcel t WHERE t.opvMacAutre = :opvMacAutre")
    , @NamedQuery(name = "TOperationParcel.findByOpvLigCode", query = "SELECT t FROM TOperationParcel t WHERE t.opvLigCode = :opvLigCode")
    , @NamedQuery(name = "TOperationParcel.findByOpvCiPp", query = "SELECT t FROM TOperationParcel t WHERE t.opvCiPp = :opvCiPp")
    , @NamedQuery(name = "TOperationParcel.findMaxOperation", query = "SELECT Max(t.opvNumero) FROM TOperationParcel t WHERE t.opvNumero LIKE :annee")
    , @NamedQuery(name = "TOperationParcel.findByMode", query = "SELECT t FROM TOperationParcel t WHERE t.opvMacCode in :mode")
    , @NamedQuery(name = "TOperationParcel.findByCategorieMode", query = "SELECT t FROM TOperationParcel t WHERE t.opvMacCode.macCat = :categorieMode")
    , @NamedQuery(name = "TOperationParcel.nombreOperation", query = "SELECT DISTINCT t.opvMacCode.macDesig,COUNT(t.opvNumero) FROM TOperationParcel t GROUP BY t.opvMacCode.macDesig")
    , @NamedQuery(name = "TOperationParcel.findByOpvDateExpCiPp", query = "SELECT t FROM TOperationParcel t WHERE t.opvDateExpCiPp = :opvDateExpCiPp")})
//@SequenceGenerator(name="tOperationParcelSequence", initialValue=1, allocationSize=1,sequenceName = "seq_id_operation_parcel")
public class TOperationParcel implements Serializable {

    @Size(max = 10)
    @Column(name = "opv_statut")
    private String opvStatut;
    @Column(name = "opv_date_saisie")
    @Temporal(TemporalType.TIMESTAMP)
    private Date opvDateSaisie;
    @Column(name = "opv_date_validation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date opvDateValidation;
    @JoinColumn(name = "opv_sign_code", referencedColumnName = "sign_code")
    @ManyToOne
    private TSignataire opvSignCode;

    @OneToMany(mappedBy = "dreOpvNumeroPreneur")
    private List<TDroitExerce> tDroitExerceList;
    @OneToMany(mappedBy = "dreOpvNumero")
    private List<TDroitExerce> tDroitExerceList1;

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue( strategy = GenerationType.IDENTITY,generator = "tOperationParcelSequence")
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "opv_numero")
    private String opvNumero;
    @Column(name = "opv_date_op")
    @Temporal(TemporalType.DATE)
    private Date opvDateOp;
    @Column(name = "opv_prix")
    private Long opvPrix;
    @Size(max = 50)
    @Column(name = "opv_numero_pv")
    private String opvNumeroPv;
    @Size(max = 5)
    @Column(name = "opv_limit_planter")
    private String opvLimitPlanter;
    @Size(max = 5)
    @Column(name = "opv_limit_recolter_fruit")
    private String opvLimitRecolterFruit;
    @Size(max = 5)
    @Column(name = "opv_limit_construire_case")
    private String opvLimitConstruireCase;
    @Size(max = 5)
    @Column(name = "opv_limit_habiter_terrain")
    private String opvLimitHabiterTerrain;
    @Size(max = 5)
    @Column(name = "opv_limit_autre_op")
    private String opvLimitAutreOp;
    @Size(max = 5)
    @Column(name = "opv_limitation_op")
    private String opvLimitationOp;
    @Size(max = 5)
    @Column(name = "opv_limit_aliener")
    private String opvLimitAliener;
    @Size(max = 5)
    @Column(name = "opv_limit_transmission")
    private String opvLimitTransmission;
    @Column(name = "opv_limitation")
    private Boolean opvLimitation;
    @Column(name = "opv_limit_leg")
    private Boolean opvLimitLeg;
    @Column(name = "opv_limit_vent")
    private Boolean opvLimitVent;
    @Column(name = "opv_limit_don")
    private Boolean opvLimitDon;
    @Column(name = "opv_limit_pret")
    private Boolean opvLimitPret;
    @Size(max = 300)
    @Column(name = "opv_limite_autre")
    private String opvLimiteAutre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "opv_duree")
    private BigDecimal opvDuree;
    @Column(name = "opv_date_deb")
    @Temporal(TemporalType.DATE)
    private Date opvDateDeb;
    @Column(name = "opv_date_fin")
    @Temporal(TemporalType.DATE)
    private Date opvDateFin;
    @Size(max = 500)
    @Column(name = "opv_mut_objet")
    private String opvMutObjet;
    @Size(max = 500)
    @Column(name = "opv_mut_date_mut")
    private String opvMutDateMut;
    @Size(max = 500)
    @Column(name = "opv_nature_culture")
    private String opvNatureCulture;
    @Size(max = 100)
    @Column(name = "opv_lieu_partage")
    private String opvLieuPartage;
    @Size(max = 50)
    @Column(name = "opv_desi_reference")
    private String opvDesiReference;
    @Column(name = "opv_desi_date")
    @Temporal(TemporalType.DATE)
    private Date opvDesiDate;
    @Size(max = 50)
    @Column(name = "opv_numero_affirmation")
    private String opvNumeroAffirmation;
    @Size(max = 150)
    @Column(name = "opv_nom_maire")
    private String opvNomMaire;
    @Size(max = 150)
    @Column(name = "opv_nom_interprete")
    private String opvNomInterprete;
    @Size(max = 200)
    @Column(name = "opv_rattachement")
    private String opvRattachement;
    @Size(max = 200)
    @Column(name = "opv_contreparti")
    private String opvContreparti;
    @Size(max = 200)
    @Column(name = "opv_autremodalite")
    private String opvAutremodalite;
    @Size(max = 100)
    @Column(name = "opv_cop_autre")
    private String opvCopAutre;
    @Size(max = 100)
    @Column(name = "opv_rol_autre")
    private String opvRolAutre;
    @Size(max = 100)
    @Column(name = "opv_mac_autre")
    private String opvMacAutre;
    @Size(max = 5)
    @Column(name = "opv_lig_code")
    private String opvLigCode;
    @Size(max = 50)
    @Column(name = "opv_ci_pp")
    private String opvCiPp;
    @Column(name = "opv_date_exp_ci_pp")
    @Temporal(TemporalType.DATE)
    private Date opvDateExpCiPp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mutOpvNumero")
    private List<TMutation> tMutationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tOperationParcel")
    private List<TParticiper> tParticiperList;
    @JoinColumn(name = "opv_mop_code", referencedColumnName = "mop_code")
    @ManyToOne
    private TModePartage opvMopCode;
    @JoinColumn(name = "opv_cop_code", referencedColumnName = "cop_code")
    @ManyToOne
    private TConditionpaie opvCopCode;
    @JoinColumn(name = "opv_desi_code", referencedColumnName = "desi_code")
    @ManyToOne
    private TDepotSignature opvDesiCode;
    @JoinColumn(name = "opv_int_numero_bailleur", referencedColumnName = "int_numero")
    @ManyToOne
    private TIntervenant opvIntNumeroBailleur;
    @JoinColumn(name = "opv_int_numero_preneur", referencedColumnName = "int_numero")
    @ManyToOne
    private TIntervenant opvIntNumeroPreneur;
    @JoinColumn(name = "opv_mac_code", referencedColumnName = "mac_code")
    @ManyToOne
    private TModeacquis opvMacCode;
    @JoinColumn(name = "opv_pba_numero", referencedColumnName = "pba_numero")
    @ManyToOne
    private TParcelleBafon opvPbaNumero;
    @JoinColumn(name = "opv_per_code", referencedColumnName = "per_code")
    @ManyToOne
    private TPeriodicite opvPerCode;
    @JoinColumn(name = "opv_rol_code", referencedColumnName = "rol_code")
    @ManyToOne
    private TRole opvRolCode;
    @JoinColumn(name = "opv_tyov_code", referencedColumnName = "tyov_code")
    @ManyToOne
    private TTypeOperationParcel opvTyovCode;
    @JoinColumn(name = "opv_uti_code", referencedColumnName = "uti_code")
    @ManyToOne
    private TUtilisateur opvUtiCode;
    @OneToMany(mappedBy = "invOpvNumero")
    private List<TIntervenir> tIntervenirList;
    @OneToMany(mappedBy = "invOpvNumeroPreneur")
    private List<TIntervenir> tIntervenirList1;

    public TOperationParcel() {
    }

    public TOperationParcel(String opvNumero) {
        this.opvNumero = opvNumero;
    }

    public String getOpvNumero() {
        return opvNumero;
    }

    public void setOpvNumero(String opvNumero) {
        this.opvNumero = opvNumero;
    }

    public Date getOpvDateOp() {
        return opvDateOp;
    }

    public void setOpvDateOp(Date opvDateOp) {
        this.opvDateOp = opvDateOp;
    }

    public Long getOpvPrix() {
        return opvPrix;
    }

    public void setOpvPrix(Long opvPrix) {
        this.opvPrix = opvPrix;
    }

    public String getOpvNumeroPv() {
        return opvNumeroPv;
    }

    public void setOpvNumeroPv(String opvNumeroPv) {
        this.opvNumeroPv = opvNumeroPv;
    }

    public String getOpvLimitPlanter() {
        return opvLimitPlanter;
    }

    public void setOpvLimitPlanter(String opvLimitPlanter) {
        this.opvLimitPlanter = opvLimitPlanter;
    }

    public String getOpvLimitRecolterFruit() {
        return opvLimitRecolterFruit;
    }

    public void setOpvLimitRecolterFruit(String opvLimitRecolterFruit) {
        this.opvLimitRecolterFruit = opvLimitRecolterFruit;
    }

    public String getOpvLimitConstruireCase() {
        return opvLimitConstruireCase;
    }

    public void setOpvLimitConstruireCase(String opvLimitConstruireCase) {
        this.opvLimitConstruireCase = opvLimitConstruireCase;
    }

    public String getOpvLimitHabiterTerrain() {
        return opvLimitHabiterTerrain;
    }

    public void setOpvLimitHabiterTerrain(String opvLimitHabiterTerrain) {
        this.opvLimitHabiterTerrain = opvLimitHabiterTerrain;
    }

    public String getOpvLimitAutreOp() {
        return opvLimitAutreOp;
    }

    public void setOpvLimitAutreOp(String opvLimitAutreOp) {
        this.opvLimitAutreOp = opvLimitAutreOp;
    }

    public String getOpvLimitationOp() {
        return opvLimitationOp;
    }

    public void setOpvLimitationOp(String opvLimitationOp) {
        this.opvLimitationOp = opvLimitationOp;
    }

    public String getOpvLimitAliener() {
        return opvLimitAliener;
    }

    public void setOpvLimitAliener(String opvLimitAliener) {
        this.opvLimitAliener = opvLimitAliener;
    }

    public String getOpvLimitTransmission() {
        return opvLimitTransmission;
    }

    public void setOpvLimitTransmission(String opvLimitTransmission) {
        this.opvLimitTransmission = opvLimitTransmission;
    }

    public Boolean getOpvLimitation() {
        return opvLimitation;
    }

    public void setOpvLimitation(Boolean opvLimitation) {
        this.opvLimitation = opvLimitation;
    }

    public Boolean getOpvLimitLeg() {
        return opvLimitLeg;
    }

    public void setOpvLimitLeg(Boolean opvLimitLeg) {
        this.opvLimitLeg = opvLimitLeg;
    }

    public Boolean getOpvLimitVent() {
        return opvLimitVent;
    }

    public void setOpvLimitVent(Boolean opvLimitVent) {
        this.opvLimitVent = opvLimitVent;
    }

    public Boolean getOpvLimitDon() {
        return opvLimitDon;
    }

    public void setOpvLimitDon(Boolean opvLimitDon) {
        this.opvLimitDon = opvLimitDon;
    }

    public Boolean getOpvLimitPret() {
        return opvLimitPret;
    }

    public void setOpvLimitPret(Boolean opvLimitPret) {
        this.opvLimitPret = opvLimitPret;
    }

    public String getOpvLimiteAutre() {
        return opvLimiteAutre;
    }

    public void setOpvLimiteAutre(String opvLimiteAutre) {
        this.opvLimiteAutre = opvLimiteAutre;
    }

    public BigDecimal getOpvDuree() {
        return opvDuree;
    }

    public void setOpvDuree(BigDecimal opvDuree) {
        this.opvDuree = opvDuree;
    }

    public Date getOpvDateDeb() {
        return opvDateDeb;
    }

    public void setOpvDateDeb(Date opvDateDeb) {
        this.opvDateDeb = opvDateDeb;
    }

    public Date getOpvDateFin() {
        return opvDateFin;
    }

    public void setOpvDateFin(Date opvDateFin) {
        this.opvDateFin = opvDateFin;
    }

    public String getOpvMutObjet() {
        return opvMutObjet;
    }

    public void setOpvMutObjet(String opvMutObjet) {
        this.opvMutObjet = opvMutObjet;
    }

    public String getOpvMutDateMut() {
        return opvMutDateMut;
    }

    public void setOpvMutDateMut(String opvMutDateMut) {
        this.opvMutDateMut = opvMutDateMut;
    }

    public String getOpvNatureCulture() {
        return opvNatureCulture;
    }

    public void setOpvNatureCulture(String opvNatureCulture) {
        this.opvNatureCulture = opvNatureCulture;
    }

    public String getOpvLieuPartage() {
        return opvLieuPartage;
    }

    public void setOpvLieuPartage(String opvLieuPartage) {
        this.opvLieuPartage = opvLieuPartage;
    }

    public String getOpvDesiReference() {
        return opvDesiReference;
    }

    public void setOpvDesiReference(String opvDesiReference) {
        this.opvDesiReference = opvDesiReference;
    }

    public Date getOpvDesiDate() {
        return opvDesiDate;
    }

    public void setOpvDesiDate(Date opvDesiDate) {
        this.opvDesiDate = opvDesiDate;
    }

    public String getOpvNumeroAffirmation() {
        return opvNumeroAffirmation;
    }

    public void setOpvNumeroAffirmation(String opvNumeroAffirmation) {
        this.opvNumeroAffirmation = opvNumeroAffirmation;
    }

    public String getOpvNomMaire() {
        return opvNomMaire;
    }

    public void setOpvNomMaire(String opvNomMaire) {
        this.opvNomMaire = opvNomMaire;
    }

    public String getOpvNomInterprete() {
        return opvNomInterprete;
    }

    public void setOpvNomInterprete(String opvNomInterprete) {
        this.opvNomInterprete = opvNomInterprete;
    }

    public String getOpvRattachement() {
        return opvRattachement;
    }

    public void setOpvRattachement(String opvRattachement) {
        this.opvRattachement = opvRattachement;
    }

    public String getOpvContreparti() {
        return opvContreparti;
    }

    public void setOpvContreparti(String opvContreparti) {
        this.opvContreparti = opvContreparti;
    }

    public String getOpvAutremodalite() {
        return opvAutremodalite;
    }

    public void setOpvAutremodalite(String opvAutremodalite) {
        this.opvAutremodalite = opvAutremodalite;
    }

    public String getOpvCopAutre() {
        return opvCopAutre;
    }

    public void setOpvCopAutre(String opvCopAutre) {
        this.opvCopAutre = opvCopAutre;
    }

    public String getOpvRolAutre() {
        return opvRolAutre;
    }

    public void setOpvRolAutre(String opvRolAutre) {
        this.opvRolAutre = opvRolAutre;
    }

    public String getOpvMacAutre() {
        return opvMacAutre;
    }

    public void setOpvMacAutre(String opvMacAutre) {
        this.opvMacAutre = opvMacAutre;
    }

    public String getOpvLigCode() {
        return opvLigCode;
    }

    public void setOpvLigCode(String opvLigCode) {
        this.opvLigCode = opvLigCode;
    }

    public String getOpvCiPp() {
        return opvCiPp;
    }

    public void setOpvCiPp(String opvCiPp) {
        this.opvCiPp = opvCiPp;
    }

    public Date getOpvDateExpCiPp() {
        return opvDateExpCiPp;
    }

    public void setOpvDateExpCiPp(Date opvDateExpCiPp) {
        this.opvDateExpCiPp = opvDateExpCiPp;
    }

    @XmlTransient
    public List<TMutation> getTMutationList() {
        return tMutationList;
    }

    public void setTMutationList(List<TMutation> tMutationList) {
        this.tMutationList = tMutationList;
    }

    @XmlTransient
    public List<TParticiper> getTParticiperList() {
        return tParticiperList;
    }

    public void setTParticiperList(List<TParticiper> tParticiperList) {
        this.tParticiperList = tParticiperList;
    }

    public TModePartage getOpvMopCode() {
        return opvMopCode;
    }

    public void setOpvMopCode(TModePartage opvMopCode) {
        this.opvMopCode = opvMopCode;
    }

    public TConditionpaie getOpvCopCode() {
        return opvCopCode;
    }

    public void setOpvCopCode(TConditionpaie opvCopCode) {
        this.opvCopCode = opvCopCode;
    }

    public TDepotSignature getOpvDesiCode() {
        return opvDesiCode;
    }

    public void setOpvDesiCode(TDepotSignature opvDesiCode) {
        this.opvDesiCode = opvDesiCode;
    }

    public TIntervenant getOpvIntNumeroBailleur() {
        return opvIntNumeroBailleur;
    }

    public void setOpvIntNumeroBailleur(TIntervenant opvIntNumeroBailleur) {
        this.opvIntNumeroBailleur = opvIntNumeroBailleur;
    }

    public TIntervenant getOpvIntNumeroPreneur() {
        return opvIntNumeroPreneur;
    }

    public void setOpvIntNumeroPreneur(TIntervenant opvIntNumeroPreneur) {
        this.opvIntNumeroPreneur = opvIntNumeroPreneur;
    }

    public TModeacquis getOpvMacCode() {
        return opvMacCode;
    }

    public void setOpvMacCode(TModeacquis opvMacCode) {
        this.opvMacCode = opvMacCode;
    }

    public TParcelleBafon getOpvPbaNumero() {
        return opvPbaNumero;
    }

    public void setOpvPbaNumero(TParcelleBafon opvPbaNumero) {
        this.opvPbaNumero = opvPbaNumero;
    }

    public TPeriodicite getOpvPerCode() {
        return opvPerCode;
    }

    public void setOpvPerCode(TPeriodicite opvPerCode) {
        this.opvPerCode = opvPerCode;
    }

    public TRole getOpvRolCode() {
        return opvRolCode;
    }

    public void setOpvRolCode(TRole opvRolCode) {
        this.opvRolCode = opvRolCode;
    }

    public TTypeOperationParcel getOpvTyovCode() {
        return opvTyovCode;
    }

    public void setOpvTyovCode(TTypeOperationParcel opvTyovCode) {
        this.opvTyovCode = opvTyovCode;
    }

    public TUtilisateur getOpvUtiCode() {
        return opvUtiCode;
    }

    public void setOpvUtiCode(TUtilisateur opvUtiCode) {
        this.opvUtiCode = opvUtiCode;
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
        hash += (opvNumero != null ? opvNumero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TOperationParcel)) {
            return false;
        }
        TOperationParcel other = (TOperationParcel) object;
        if ((this.opvNumero == null && other.opvNumero != null) || (this.opvNumero != null && !this.opvNumero.equals(other.opvNumero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TOperationParcel[ opvNumero=" + opvNumero + " ]";
    }

    @XmlTransient
    public List<TDroitExerce> getTDroitExerceList() {
        return tDroitExerceList;
    }

    public void setTDroitExerceList(List<TDroitExerce> tDroitExerceList) {
        this.tDroitExerceList = tDroitExerceList;
    }

    @XmlTransient
    public List<TDroitExerce> getTDroitExerceList1() {
        return tDroitExerceList1;
    }

    public void setTDroitExerceList1(List<TDroitExerce> tDroitExerceList1) {
        this.tDroitExerceList1 = tDroitExerceList1;
    }

    public String getOpvStatut() {
        return opvStatut;
    }

    public void setOpvStatut(String opvStatut) {
        this.opvStatut = opvStatut;
    }

    public Date getOpvDateSaisie() {
        return opvDateSaisie;
    }

    public void setOpvDateSaisie(Date opvDateSaisie) {
        this.opvDateSaisie = opvDateSaisie;
    }

    public Date getOpvDateValidation() {
        return opvDateValidation;
    }

    public void setOpvDateValidation(Date opvDateValidation) {
        this.opvDateValidation = opvDateValidation;
    }

    public TSignataire getOpvSignCode() {
        return opvSignCode;
    }

    public void setOpvSignCode(TSignataire opvSignCode) {
        this.opvSignCode = opvSignCode;
        this.setOpvNomMaire(opvSignCode.getSignNom()+" "+opvSignCode.getSignPrenom());
    }
    
}
