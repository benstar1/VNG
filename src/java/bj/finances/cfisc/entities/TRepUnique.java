/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "T_REP_UNIQUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRepUnique.findAll", query = "SELECT t FROM TRepUnique t"),
    @NamedQuery(name = "TRepUnique.findAllByContCentrImpCode", query = "SELECT t FROM TRepUnique t WHERE t.contCentrImpCode =:contCentrImpCode"),
    @NamedQuery(name = "TRepUnique.findByContNum", query = "SELECT t FROM TRepUnique t WHERE t.contNum = :contNum"),
    @NamedQuery(name = "TRepUnique.findByContDatenreg", query = "SELECT t FROM TRepUnique t WHERE t.contDatenreg = :contDatenreg"),
    @NamedQuery(name = "TRepUnique.findByContImmatr", query = "SELECT t FROM TRepUnique t WHERE t.contImmatr = :contImmatr"),
    @NamedQuery(name = "TRepUnique.findByContImmatlike", query = "SELECT t FROM TRepUnique t WHERE t.contImmatr like :contImmatr"),
    @NamedQuery(name = "TRepUnique.findContribByImmatPP", query = "SELECT t FROM TRepUnique t WHERE (t.contImmatr like concat(:contImmatr, '%') or t.contImmatr like concat(:contImmatr1, '%')) and (t.contRais is not null or t.contNomCourt is not null)"),
    @NamedQuery(name = "TRepUnique.findByContDateimmatr", query = "SELECT t FROM TRepUnique t WHERE t.contDateimmatr = :contDateimmatr"),
    @NamedQuery(name = "TRepUnique.findByContReimmatr", query = "SELECT t FROM TRepUnique t WHERE t.contReimmatr = :contReimmatr"),
    @NamedQuery(name = "TRepUnique.findByContNom", query = "SELECT t FROM TRepUnique t WHERE t.contNom = :contNom"),
    @NamedQuery(name = "TRepUnique.findByContPren", query = "SELECT t FROM TRepUnique t WHERE t.contPren = :contPren"),
    @NamedQuery(name = "TRepUnique.findByContNomJf", query = "SELECT t FROM TRepUnique t WHERE t.contNomJf = :contNomJf"),
    @NamedQuery(name = "TRepUnique.findByContSexe", query = "SELECT t FROM TRepUnique t WHERE t.contSexe = :contSexe"),
    @NamedQuery(name = "TRepUnique.findByContSitMat", query = "SELECT t FROM TRepUnique t WHERE t.contSitMat = :contSitMat"),
    @NamedQuery(name = "TRepUnique.findByContDatnais", query = "SELECT t FROM TRepUnique t WHERE t.contDatnais = :contDatnais"),
    @NamedQuery(name = "TRepUnique.findByContLieunais", query = "SELECT t FROM TRepUnique t WHERE t.contLieunais = :contLieunais"),
    @NamedQuery(name = "TRepUnique.findByContBp", query = "SELECT t FROM TRepUnique t WHERE t.contBp = :contBp"),
    @NamedQuery(name = "TRepUnique.findByContTel", query = "SELECT t FROM TRepUnique t WHERE t.contTel = :contTel"),
    @NamedQuery(name = "TRepUnique.findByContFax", query = "SELECT t FROM TRepUnique t WHERE t.contFax = :contFax"),
    @NamedQuery(name = "TRepUnique.findByContMail", query = "SELECT t FROM TRepUnique t WHERE t.contMail = :contMail"),
    @NamedQuery(name = "TRepUnique.findByContCarre", query = "SELECT t FROM TRepUnique t WHERE t.contCarre = :contCarre"),
    @NamedQuery(name = "TRepUnique.findByContLot", query = "SELECT t FROM TRepUnique t WHERE t.contLot = :contLot"),
    @NamedQuery(name = "TRepUnique.findByContRais", query = "SELECT t FROM TRepUnique t WHERE t.contRais = :contRais"),
    @NamedQuery(name = "TRepUnique.findByContNomCourt", query = "SELECT t FROM TRepUnique t WHERE t.contNomCourt = :contNomCourt"),
    @NamedQuery(name = "TRepUnique.findByContNomLong", query = "SELECT t FROM TRepUnique t WHERE t.contNomLong = :contNomLong"),
    @NamedQuery(name = "TRepUnique.findByContDatcreat", query = "SELECT t FROM TRepUnique t WHERE t.contDatcreat = :contDatcreat"),
    @NamedQuery(name = "TRepUnique.findByContNompropsieg", query = "SELECT t FROM TRepUnique t WHERE t.contNompropsieg = :contNompropsieg"),
    @NamedQuery(name = "TRepUnique.findByContLoyer", query = "SELECT t FROM TRepUnique t WHERE t.contLoyer = :contLoyer"),
    @NamedQuery(name = "TRepUnique.findByContCapital", query = "SELECT t FROM TRepUnique t WHERE t.contCapital = :contCapital"),
    @NamedQuery(name = "TRepUnique.findByContNuminsae", query = "SELECT t FROM TRepUnique t WHERE t.contNuminsae = :contNuminsae"),
    @NamedQuery(name = "TRepUnique.findByContDatenregInsae", query = "SELECT t FROM TRepUnique t WHERE t.contDatenregInsae = :contDatenregInsae"),
    @NamedQuery(name = "TRepUnique.findByContNbEnf", query = "SELECT t FROM TRepUnique t WHERE t.contNbEnf = :contNbEnf"),
    @NamedQuery(name = "TRepUnique.findByContCentrCode", query = "SELECT t FROM TRepUnique t WHERE t.contCentrCode = :contCentrCode"),
    @NamedQuery(name = "TRepUnique.findByContProfCode", query = "SELECT t FROM TRepUnique t WHERE t.contProfCode = :contProfCode"),
    @NamedQuery(name = "TRepUnique.findByContFonctCode", query = "SELECT t FROM TRepUnique t WHERE t.contFonctCode = :contFonctCode"),
    @NamedQuery(name = "TRepUnique.findByContNbEmpl", query = "SELECT t FROM TRepUnique t WHERE t.contNbEmpl = :contNbEmpl"),
    @NamedQuery(name = "TRepUnique.findByContQuart", query = "SELECT t FROM TRepUnique t WHERE t.contQuart = :contQuart"),
    @NamedQuery(name = "TRepUnique.findByContCatEtabCode", query = "SELECT t FROM TRepUnique t WHERE t.contCatEtabCode = :contCatEtabCode"),
    @NamedQuery(name = "TRepUnique.findByContModExpCode", query = "SELECT t FROM TRepUnique t WHERE t.contModExpCode = :contModExpCode"),
    @NamedQuery(name = "TRepUnique.findByContContNum", query = "SELECT t FROM TRepUnique t WHERE t.contContNum = :contContNum"),
    @NamedQuery(name = "TRepUnique.findByContOrdre", query = "SELECT t FROM TRepUnique t WHERE t.contOrdre = :contOrdre"),
    @NamedQuery(name = "TRepUnique.findByContMemActMere", query = "SELECT t FROM TRepUnique t WHERE t.contMemActMere = :contMemActMere"),
    @NamedQuery(name = "TRepUnique.findByContDateCessation", query = "SELECT t FROM TRepUnique t WHERE t.contDateCessation = :contDateCessation"),
    @NamedQuery(name = "TRepUnique.findByContDateDeces", query = "SELECT t FROM TRepUnique t WHERE t.contDateDeces = :contDateDeces"),
    @NamedQuery(name = "TRepUnique.findByContDateRepriseAct", query = "SELECT t FROM TRepUnique t WHERE t.contDateRepriseAct = :contDateRepriseAct"),
    @NamedQuery(name = "TRepUnique.findByContEnsCommerce", query = "SELECT t FROM TRepUnique t WHERE t.contEnsCommerce = :contEnsCommerce"),
    @NamedQuery(name = "TRepUnique.findByContNationCode", query = "SELECT t FROM TRepUnique t WHERE t.contNationCode = :contNationCode"),
    @NamedQuery(name = "TRepUnique.findByContNeVers", query = "SELECT t FROM TRepUnique t WHERE t.contNeVers = :contNeVers"),
    @NamedQuery(name = "TRepUnique.findByContVille", query = "SELECT t FROM TRepUnique t WHERE t.contVille = :contVille"),
    @NamedQuery(name = "TRepUnique.findByContRue", query = "SELECT t FROM TRepUnique t WHERE t.contRue = :contRue"),
    @NamedQuery(name = "TRepUnique.findByContPrnompropsieg", query = "SELECT t FROM TRepUnique t WHERE t.contPrnompropsieg = :contPrnompropsieg"),
    @NamedQuery(name = "TRepUnique.findByContProp", query = "SELECT t FROM TRepUnique t WHERE t.contProp = :contProp"),
    @NamedQuery(name = "TRepUnique.findByContMemBank", query = "SELECT t FROM TRepUnique t WHERE t.contMemBank = :contMemBank"),
    @NamedQuery(name = "TRepUnique.findByContNewImmatr", query = "SELECT t FROM TRepUnique t WHERE t.contNewImmatr = :contNewImmatr"),
    @NamedQuery(name = "TRepUnique.findByContNuminsae1", query = "SELECT t FROM TRepUnique t WHERE t.contNuminsae1 = :contNuminsae1"),
    @NamedQuery(name = "TRepUnique.findByContActif", query = "SELECT t FROM TRepUnique t WHERE t.contActif = :contActif"),
    @NamedQuery(name = "TRepUnique.findByContMatricule", query = "SELECT t FROM TRepUnique t WHERE t.contMatricule = :contMatricule"),
    @NamedQuery(name = "TRepUnique.findByContDateMajMatricule", query = "SELECT t FROM TRepUnique t WHERE t.contDateMajMatricule = :contDateMajMatricule"),
    @NamedQuery(name = "TRepUnique.findByContStatut", query = "SELECT t FROM TRepUnique t WHERE t.contStatut = :contStatut")})
public class TRepUnique implements Serializable {

    @OneToMany(mappedBy = "utilContImmatr")
    private List<TUtilisateur> tUtilisateurList;
    @OneToMany(mappedBy = "histContImmatr")
    private List<THistorique> tHistoriqueList;
    @OneToMany(mappedBy = "entDecContImmatr")
    private List<TEntDeclaration> tEntDeclarationList;
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "CONT_NUM")
    private String contNum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONT_DATENREG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contDatenreg;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONT_IMMATR")
    private Long contImmatr;
    @Column(name = "CONT_DATEIMMATR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contDateimmatr;
    @Size(max = 1)
    @Column(name = "CONT_REIMMATR")
    private String contReimmatr;
    @Size(max = 50)
    @Column(name = "CONT_NOM")
    private String contNom;
    @Size(max = 100)
    @Column(name = "CONT_PREN")
    private String contPren;
    @Size(max = 50)
    @Column(name = "CONT_NOM_JF")
    private String contNomJf;
    @Size(max = 1)
    @Column(name = "CONT_SEXE")
    private String contSexe;
    @Size(max = 20)
    @Column(name = "CONT_SIT_MAT")
    private String contSitMat;
    @Column(name = "CONT_DATNAIS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contDatnais;
    @Size(max = 30)
    @Column(name = "CONT_LIEUNAIS")
    private String contLieunais;
    @Size(max = 30)
    @Column(name = "CONT_BP")
    private String contBp;
    @Column(name = "CONT_TEL")
    private BigInteger contTel;
    @Column(name = "CONT_FAX")
    private BigInteger contFax;
    @Size(max = 30)
    @Column(name = "CONT_MAIL")
    private String contMail;
    @Size(max = 30)
    @Column(name = "CONT_CARRE")
    private String contCarre;
    @Size(max = 5)
    @Column(name = "CONT_LOT")
    private String contLot;
    @Size(max = 200)
    @Column(name = "CONT_RAIS")
    private String contRais;
    @Size(max = 50)
    @Column(name = "CONT_NOM_COURT")
    private String contNomCourt;
    @Size(max = 100)
    @Column(name = "CONT_NOM_LONG")
    private String contNomLong;
    @Column(name = "CONT_DATCREAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contDatcreat;
    @Size(max = 100)
    @Column(name = "CONT_NOMPROPSIEG")
    private String contNompropsieg;
    @Column(name = "CONT_LOYER")
    private Long contLoyer;
    @Column(name = "CONT_CAPITAL")
    private BigInteger contCapital;
    @Size(max = 20)
    @Column(name = "CONT_NUMINSAE")
    private String contNuminsae;
    @Column(name = "CONT_DATENREG_INSAE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contDatenregInsae;
    @Column(name = "CONT_NB_ENF")
    private Short contNbEnf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CONT_CENTR_CODE")
    private String contCentrCode;
    @Size(max = 8)
    @Column(name = "CONT_PROF_CODE")
    private String contProfCode;
    @Size(max = 5)
    @Column(name = "CONT_FONCT_CODE")
    private String contFonctCode;
    @Column(name = "CONT_NB_EMPL")
    private Short contNbEmpl;
    @Size(max = 60)
    @Column(name = "CONT_QUART")
    private String contQuart;
    @Size(max = 2)
    @Column(name = "CONT_CAT_ETAB_CODE")
    private String contCatEtabCode;
    @Size(max = 2)
    @Column(name = "CONT_MOD_EXP_CODE")
    private String contModExpCode;
    @Size(max = 15)
    @Column(name = "CONT_CONT_NUM")
    private String contContNum;
    @Column(name = "CONT_ORDRE")
    private BigInteger contOrdre;
    @Size(max = 1)
    @Column(name = "CONT_MEM_ACT_MERE")
    private String contMemActMere;
    @Column(name = "CONT_DATE_CESSATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contDateCessation;
    @Column(name = "CONT_DATE_DECES")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contDateDeces;
    @Column(name = "CONT_DATE_REPRISE_ACT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contDateRepriseAct;
    @Size(max = 100)
    @Column(name = "CONT_ENS_COMMERCE")
    private String contEnsCommerce;
    @Size(max = 5)
    @Column(name = "CONT_NATION_CODE")
    private String contNationCode;
    @Size(max = 1)
    @Column(name = "CONT_NE_VERS")
    private String contNeVers;
    @Size(max = 100)
    @Column(name = "CONT_VILLE")
    private String contVille;
    @Size(max = 100)
    @Column(name = "CONT_RUE")
    private String contRue;
    @Size(max = 100)
    @Column(name = "CONT_PRNOMPROPSIEG")
    private String contPrnompropsieg;
    @Size(max = 1)
    @Column(name = "CONT_PROP")
    private String contProp;
    @Size(max = 1)
    @Column(name = "CONT_MEM_BANK")
    private String contMemBank;
    @Column(name = "CONT_NEW_IMMATR")
    private Long contNewImmatr;
    @Size(max = 20)
    @Column(name = "CONT_NUMINSAE1")
    private String contNuminsae1;
    @Size(max = 1)
    @Column(name = "CONT_ACTIF")
    private String contActif;
    @Size(max = 6)
    @Column(name = "CONT_MATRICULE")
    private String contMatricule;
    @Column(name = "CONT_DATE_MAJ_MATRICULE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contDateMajMatricule;
    @Size(max = 10)
    @Column(name = "CONT_STATUT")
    private String contStatut;
//    @OneToOne(mappedBy = "entDecContImmatr")
//    private TEntDeclaration tEntDeclaration;
//    @OneToOne(mappedBy = "histContImmatr")
//    private THistorique tHistorique;
    @JoinColumn(name = "CONT_TYP_CONT_CODE", referencedColumnName = "TYP_CONT_CODE")
    @ManyToOne(optional = false)
    private TTypeContrib contTypContCode;
    @JoinColumn(name = "CONT_CENTR_IMP_CODE", referencedColumnName = "CENTR_IMP_CODE")
    @ManyToOne
    private TCentreImpot contCentrImpCode;
    @OneToMany(mappedBy = "histStatutContImmatr")
    private List<THistStatut> tHistStatutList;

    public TRepUnique() {
    }

    public TRepUnique(Long contImmatr) {
        this.contImmatr = contImmatr;
    }

    public TRepUnique(Long contImmatr, String contNum, Date contDatenreg, String contCentrCode) {
        this.contImmatr = contImmatr;
        this.contNum = contNum;
        this.contDatenreg = contDatenreg;
        this.contCentrCode = contCentrCode;
    }

    public String getContNum() {
        return contNum;
    }

    public void setContNum(String contNum) {
        this.contNum = contNum;
    }

    public Date getContDatenreg() {
        return contDatenreg;
    }

    public void setContDatenreg(Date contDatenreg) {
        this.contDatenreg = contDatenreg;
    }

    public Long getContImmatr() {
        return contImmatr;
    }

    public void setContImmatr(Long contImmatr) {
        this.contImmatr = contImmatr;
    }

    public Date getContDateimmatr() {
        return contDateimmatr;
    }

    public void setContDateimmatr(Date contDateimmatr) {
        this.contDateimmatr = contDateimmatr;
    }

    public String getContReimmatr() {
        return contReimmatr;
    }

    public void setContReimmatr(String contReimmatr) {
        this.contReimmatr = contReimmatr;
    }

    public String getContNom() {
        return contNom;
    }

    public void setContNom(String contNom) {
        this.contNom = contNom;
    }

    public String getContPren() {
        return contPren;
    }

    public void setContPren(String contPren) {
        this.contPren = contPren;
    }

    public String getContNomJf() {
        return contNomJf;
    }

    public void setContNomJf(String contNomJf) {
        this.contNomJf = contNomJf;
    }

    public String getContSexe() {
        return contSexe;
    }

    public void setContSexe(String contSexe) {
        this.contSexe = contSexe;
    }

    public String getContSitMat() {
        return contSitMat;
    }

    public void setContSitMat(String contSitMat) {
        this.contSitMat = contSitMat;
    }

    public Date getContDatnais() {
        return contDatnais;
    }

    public void setContDatnais(Date contDatnais) {
        this.contDatnais = contDatnais;
    }

    public String getContLieunais() {
        return contLieunais;
    }

    public void setContLieunais(String contLieunais) {
        this.contLieunais = contLieunais;
    }

    public String getContBp() {
        return contBp;
    }

    public void setContBp(String contBp) {
        this.contBp = contBp;
    }

    public BigInteger getContTel() {
        return contTel;
    }

    public void setContTel(BigInteger contTel) {
        this.contTel = contTel;
    }

    public BigInteger getContFax() {
        return contFax;
    }

    public void setContFax(BigInteger contFax) {
        this.contFax = contFax;
    }

    public String getContMail() {
        return contMail;
    }

    public void setContMail(String contMail) {
        this.contMail = contMail;
    }

    public String getContCarre() {
        return contCarre;
    }

    public void setContCarre(String contCarre) {
        this.contCarre = contCarre;
    }

    public String getContLot() {
        return contLot;
    }

    public void setContLot(String contLot) {
        this.contLot = contLot;
    }

    public String getContRais() {
        return contRais;
    }

    public void setContRais(String contRais) {
        this.contRais = contRais;
    }

    public String getContNomCourt() {
        return contNomCourt;
    }

    public void setContNomCourt(String contNomCourt) {
        this.contNomCourt = contNomCourt;
    }

    public String getContNomLong() {
        return contNomLong;
    }

    public void setContNomLong(String contNomLong) {
        this.contNomLong = contNomLong;
    }

    public Date getContDatcreat() {
        return contDatcreat;
    }

    public void setContDatcreat(Date contDatcreat) {
        this.contDatcreat = contDatcreat;
    }

    public String getContNompropsieg() {
        return contNompropsieg;
    }

    public void setContNompropsieg(String contNompropsieg) {
        this.contNompropsieg = contNompropsieg;
    }

    public Long getContLoyer() {
        return contLoyer;
    }

    public void setContLoyer(Long contLoyer) {
        this.contLoyer = contLoyer;
    }

    public BigInteger getContCapital() {
        return contCapital;
    }

    public void setContCapital(BigInteger contCapital) {
        this.contCapital = contCapital;
    }

    public String getContNuminsae() {
        return contNuminsae;
    }

    public void setContNuminsae(String contNuminsae) {
        this.contNuminsae = contNuminsae;
    }

    public Date getContDatenregInsae() {
        return contDatenregInsae;
    }

    public void setContDatenregInsae(Date contDatenregInsae) {
        this.contDatenregInsae = contDatenregInsae;
    }

    public Short getContNbEnf() {
        return contNbEnf;
    }

    public void setContNbEnf(Short contNbEnf) {
        this.contNbEnf = contNbEnf;
    }

    public String getContCentrCode() {
        return contCentrCode;
    }

    public void setContCentrCode(String contCentrCode) {
        this.contCentrCode = contCentrCode;
    }

    public String getContProfCode() {
        return contProfCode;
    }

    public void setContProfCode(String contProfCode) {
        this.contProfCode = contProfCode;
    }

    public String getContFonctCode() {
        return contFonctCode;
    }

    public void setContFonctCode(String contFonctCode) {
        this.contFonctCode = contFonctCode;
    }

    public Short getContNbEmpl() {
        return contNbEmpl;
    }

    public void setContNbEmpl(Short contNbEmpl) {
        this.contNbEmpl = contNbEmpl;
    }

    public String getContQuart() {
        return contQuart;
    }

    public void setContQuart(String contQuart) {
        this.contQuart = contQuart;
    }

    public String getContCatEtabCode() {
        return contCatEtabCode;
    }

    public void setContCatEtabCode(String contCatEtabCode) {
        this.contCatEtabCode = contCatEtabCode;
    }

    public String getContModExpCode() {
        return contModExpCode;
    }

    public void setContModExpCode(String contModExpCode) {
        this.contModExpCode = contModExpCode;
    }

    public String getContContNum() {
        return contContNum;
    }

    public void setContContNum(String contContNum) {
        this.contContNum = contContNum;
    }

    public BigInteger getContOrdre() {
        return contOrdre;
    }

    public void setContOrdre(BigInteger contOrdre) {
        this.contOrdre = contOrdre;
    }

    public String getContMemActMere() {
        return contMemActMere;
    }

    public void setContMemActMere(String contMemActMere) {
        this.contMemActMere = contMemActMere;
    }

    public Date getContDateCessation() {
        return contDateCessation;
    }

    public void setContDateCessation(Date contDateCessation) {
        this.contDateCessation = contDateCessation;
    }

    public Date getContDateDeces() {
        return contDateDeces;
    }

    public void setContDateDeces(Date contDateDeces) {
        this.contDateDeces = contDateDeces;
    }

    public Date getContDateRepriseAct() {
        return contDateRepriseAct;
    }

    public void setContDateRepriseAct(Date contDateRepriseAct) {
        this.contDateRepriseAct = contDateRepriseAct;
    }

    public String getContEnsCommerce() {
        return contEnsCommerce;
    }

    public void setContEnsCommerce(String contEnsCommerce) {
        this.contEnsCommerce = contEnsCommerce;
    }

    public String getContNationCode() {
        return contNationCode;
    }

    public void setContNationCode(String contNationCode) {
        this.contNationCode = contNationCode;
    }

    public String getContNeVers() {
        return contNeVers;
    }

    public void setContNeVers(String contNeVers) {
        this.contNeVers = contNeVers;
    }

    public String getContVille() {
        return contVille;
    }

    public void setContVille(String contVille) {
        this.contVille = contVille;
    }

    public String getContRue() {
        return contRue;
    }

    public void setContRue(String contRue) {
        this.contRue = contRue;
    }

    public String getContPrnompropsieg() {
        return contPrnompropsieg;
    }

    public void setContPrnompropsieg(String contPrnompropsieg) {
        this.contPrnompropsieg = contPrnompropsieg;
    }

    public String getContProp() {
        return contProp;
    }

    public void setContProp(String contProp) {
        this.contProp = contProp;
    }

    public String getContMemBank() {
        return contMemBank;
    }

    public void setContMemBank(String contMemBank) {
        this.contMemBank = contMemBank;
    }

    public Long getContNewImmatr() {
        return contNewImmatr;
    }

    public void setContNewImmatr(Long contNewImmatr) {
        this.contNewImmatr = contNewImmatr;
    }

    public String getContNuminsae1() {
        return contNuminsae1;
    }

    public void setContNuminsae1(String contNuminsae1) {
        this.contNuminsae1 = contNuminsae1;
    }

    public String getContActif() {
        return contActif;
    }

    public void setContActif(String contActif) {
        this.contActif = contActif;
    }

    public String getContMatricule() {
        return contMatricule;
    }

    public void setContMatricule(String contMatricule) {
        this.contMatricule = contMatricule;
    }

    public Date getContDateMajMatricule() {
        return contDateMajMatricule;
    }

    public void setContDateMajMatricule(Date contDateMajMatricule) {
        this.contDateMajMatricule = contDateMajMatricule;
    }

    public String getContStatut() {
        return contStatut;
    }

    public void setContStatut(String contStatut) {
        this.contStatut = contStatut;
    }

//    public TEntDeclaration getTEntDeclaration() {
//        return tEntDeclaration;
//    }
//
//    public void setTEntDeclaration(TEntDeclaration tEntDeclaration) {
//        this.tEntDeclaration = tEntDeclaration;
//    }
//
//    public THistorique getTHistorique() {
//        return tHistorique;
//    }
//    public void setTHistorique(THistorique tHistorique) {
//        this.tHistorique = tHistorique;
//    }
    public TTypeContrib getContTypContCode() {
        return contTypContCode;
    }

    public void setContTypContCode(TTypeContrib contTypContCode) {
        this.contTypContCode = contTypContCode;
    }

    public TCentreImpot getContCentrImpCode() {
        return contCentrImpCode;
    }

    public void setContCentrImpCode(TCentreImpot contCentrImpCode) {
        this.contCentrImpCode = contCentrImpCode;
    }

    @XmlTransient
    public List<THistStatut> getTHistStatutList() {
        return tHistStatutList;
    }

    public void setTHistStatutList(List<THistStatut> tHistStatutList) {
        this.tHistStatutList = tHistStatutList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contImmatr != null ? contImmatr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRepUnique)) {
            return false;
        }
        TRepUnique other = (TRepUnique) object;
        if ((this.contImmatr == null && other.contImmatr != null) || (this.contImmatr != null && !this.contImmatr.equals(other.contImmatr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TRepUnique[ contImmatr=" + contImmatr + " ]";
    }

    @XmlTransient
    public List<TEntDeclaration> getTEntDeclarationList() {
        return tEntDeclarationList;
    }

    public void setTEntDeclarationList(List<TEntDeclaration> tEntDeclarationList) {
        this.tEntDeclarationList = tEntDeclarationList;
    }

    @XmlTransient
    public List<THistorique> getTHistoriqueList() {
        return tHistoriqueList;
    }

    public void setTHistoriqueList(List<THistorique> tHistoriqueList) {
        this.tHistoriqueList = tHistoriqueList;
    }

    @XmlTransient
    public List<TUtilisateur> getTUtilisateurList() {
        return tUtilisateurList;
    }

    public void setTUtilisateurList(List<TUtilisateur> tUtilisateurList) {
        this.tUtilisateurList = tUtilisateurList;
    }

}
