/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
 * @author HP
 */
@Entity
@Table(name = "T_HISTORIQUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "THistorique.findAll", query = "SELECT t FROM THistorique t"),
    @NamedQuery(name = "THistorique.findByHistContImmatr", query = "SELECT t FROM THistorique t WHERE t.histContImmatr = :histContImmatr"),
    @NamedQuery(name = "THistorique.findByHistNum", query = "SELECT t FROM THistorique t WHERE t.histNum = :histNum"),
    @NamedQuery(name = "THistorique.findByHistContNum", query = "SELECT t FROM THistorique t WHERE t.histContNum = :histContNum"),
    @NamedQuery(name = "THistorique.findByHistContDatenreg", query = "SELECT t FROM THistorique t WHERE t.histContDatenreg = :histContDatenreg"),
    @NamedQuery(name = "THistorique.findByHistContDateimmatr", query = "SELECT t FROM THistorique t WHERE t.histContDateimmatr = :histContDateimmatr"),
    @NamedQuery(name = "THistorique.findByHistContReimmatr", query = "SELECT t FROM THistorique t WHERE t.histContReimmatr = :histContReimmatr"),
    @NamedQuery(name = "THistorique.findByHistContNom", query = "SELECT t FROM THistorique t WHERE t.histContNom = :histContNom"),
    @NamedQuery(name = "THistorique.findByHistContPren", query = "SELECT t FROM THistorique t WHERE t.histContPren = :histContPren"),
    @NamedQuery(name = "THistorique.findByHistContNomJf", query = "SELECT t FROM THistorique t WHERE t.histContNomJf = :histContNomJf"),
    @NamedQuery(name = "THistorique.findByHistContSexe", query = "SELECT t FROM THistorique t WHERE t.histContSexe = :histContSexe"),
    @NamedQuery(name = "THistorique.findByHistContSitMat", query = "SELECT t FROM THistorique t WHERE t.histContSitMat = :histContSitMat"),
    @NamedQuery(name = "THistorique.findByHistContDatnais", query = "SELECT t FROM THistorique t WHERE t.histContDatnais = :histContDatnais"),
    @NamedQuery(name = "THistorique.findByHistContLieunais", query = "SELECT t FROM THistorique t WHERE t.histContLieunais = :histContLieunais"),
    @NamedQuery(name = "THistorique.findByHistContBp", query = "SELECT t FROM THistorique t WHERE t.histContBp = :histContBp"),
    @NamedQuery(name = "THistorique.findByHistContTel", query = "SELECT t FROM THistorique t WHERE t.histContTel = :histContTel"),
    @NamedQuery(name = "THistorique.findByHistContFax", query = "SELECT t FROM THistorique t WHERE t.histContFax = :histContFax"),
    @NamedQuery(name = "THistorique.findByHistContMail", query = "SELECT t FROM THistorique t WHERE t.histContMail = :histContMail"),
    @NamedQuery(name = "THistorique.findByHistContCarre", query = "SELECT t FROM THistorique t WHERE t.histContCarre = :histContCarre"),
    @NamedQuery(name = "THistorique.findByHistContLot", query = "SELECT t FROM THistorique t WHERE t.histContLot = :histContLot"),
    @NamedQuery(name = "THistorique.findByHistContRais", query = "SELECT t FROM THistorique t WHERE t.histContRais = :histContRais"),
    @NamedQuery(name = "THistorique.findByHistContNomCourt", query = "SELECT t FROM THistorique t WHERE t.histContNomCourt = :histContNomCourt"),
    @NamedQuery(name = "THistorique.findByHistContNomLong", query = "SELECT t FROM THistorique t WHERE t.histContNomLong = :histContNomLong"),
    @NamedQuery(name = "THistorique.findByHistContDatcreat", query = "SELECT t FROM THistorique t WHERE t.histContDatcreat = :histContDatcreat"),
    @NamedQuery(name = "THistorique.findByHistContNompropsieg", query = "SELECT t FROM THistorique t WHERE t.histContNompropsieg = :histContNompropsieg"),
    @NamedQuery(name = "THistorique.findByHistContLoyer", query = "SELECT t FROM THistorique t WHERE t.histContLoyer = :histContLoyer"),
    @NamedQuery(name = "THistorique.findByHistContCapital", query = "SELECT t FROM THistorique t WHERE t.histContCapital = :histContCapital"),
    @NamedQuery(name = "THistorique.findByHistContNuminsae", query = "SELECT t FROM THistorique t WHERE t.histContNuminsae = :histContNuminsae"),
    @NamedQuery(name = "THistorique.findByHistContDatenregInsae", query = "SELECT t FROM THistorique t WHERE t.histContDatenregInsae = :histContDatenregInsae"),
    @NamedQuery(name = "THistorique.findByHistContNbEnf", query = "SELECT t FROM THistorique t WHERE t.histContNbEnf = :histContNbEnf"),
    @NamedQuery(name = "THistorique.findByHistContCentrCode", query = "SELECT t FROM THistorique t WHERE t.histContCentrCode = :histContCentrCode"),
    @NamedQuery(name = "THistorique.findByHistContTypContCode", query = "SELECT t FROM THistorique t WHERE t.histContTypContCode = :histContTypContCode"),
    @NamedQuery(name = "THistorique.findByHistContProfCode", query = "SELECT t FROM THistorique t WHERE t.histContProfCode = :histContProfCode"),
    @NamedQuery(name = "THistorique.findByHistContFonctCode", query = "SELECT t FROM THistorique t WHERE t.histContFonctCode = :histContFonctCode"),
    @NamedQuery(name = "THistorique.findByHistContNbEmpl", query = "SELECT t FROM THistorique t WHERE t.histContNbEmpl = :histContNbEmpl"),
    @NamedQuery(name = "THistorique.findByHistContQuart", query = "SELECT t FROM THistorique t WHERE t.histContQuart = :histContQuart"),
    @NamedQuery(name = "THistorique.findByHistContCatEtabCode", query = "SELECT t FROM THistorique t WHERE t.histContCatEtabCode = :histContCatEtabCode"),
    @NamedQuery(name = "THistorique.findByHistContModExpCode", query = "SELECT t FROM THistorique t WHERE t.histContModExpCode = :histContModExpCode"),
    @NamedQuery(name = "THistorique.findByHistContContNum", query = "SELECT t FROM THistorique t WHERE t.histContContNum = :histContContNum"),
    @NamedQuery(name = "THistorique.findByHistContOrdre", query = "SELECT t FROM THistorique t WHERE t.histContOrdre = :histContOrdre"),
    @NamedQuery(name = "THistorique.findByHistContMemActMere", query = "SELECT t FROM THistorique t WHERE t.histContMemActMere = :histContMemActMere"),
    @NamedQuery(name = "THistorique.findByHistContDateCessation", query = "SELECT t FROM THistorique t WHERE t.histContDateCessation = :histContDateCessation"),
    @NamedQuery(name = "THistorique.findByHistContDateDeces", query = "SELECT t FROM THistorique t WHERE t.histContDateDeces = :histContDateDeces"),
    @NamedQuery(name = "THistorique.findByHistContDateRepriseAct", query = "SELECT t FROM THistorique t WHERE t.histContDateRepriseAct = :histContDateRepriseAct"),
    @NamedQuery(name = "THistorique.findByHistContEnsCommerce", query = "SELECT t FROM THistorique t WHERE t.histContEnsCommerce = :histContEnsCommerce"),
    @NamedQuery(name = "THistorique.findByHistContNationCode", query = "SELECT t FROM THistorique t WHERE t.histContNationCode = :histContNationCode"),
    @NamedQuery(name = "THistorique.findByHistContNeVers", query = "SELECT t FROM THistorique t WHERE t.histContNeVers = :histContNeVers"),
    @NamedQuery(name = "THistorique.findByHistContVille", query = "SELECT t FROM THistorique t WHERE t.histContVille = :histContVille"),
    @NamedQuery(name = "THistorique.findByHistContRue", query = "SELECT t FROM THistorique t WHERE t.histContRue = :histContRue"),
    @NamedQuery(name = "THistorique.findByHistContPrnompropsieg", query = "SELECT t FROM THistorique t WHERE t.histContPrnompropsieg = :histContPrnompropsieg"),
    @NamedQuery(name = "THistorique.findByHistContProp", query = "SELECT t FROM THistorique t WHERE t.histContProp = :histContProp"),
    @NamedQuery(name = "THistorique.findByHistContMemBank", query = "SELECT t FROM THistorique t WHERE t.histContMemBank = :histContMemBank"),
    @NamedQuery(name = "THistorique.findByHistContNewImmatr", query = "SELECT t FROM THistorique t WHERE t.histContNewImmatr = :histContNewImmatr"),
    @NamedQuery(name = "THistorique.findByHistContNuminsae1", query = "SELECT t FROM THistorique t WHERE t.histContNuminsae1 = :histContNuminsae1"),
    @NamedQuery(name = "THistorique.findByHistContActif", query = "SELECT t FROM THistorique t WHERE t.histContActif = :histContActif"),
    @NamedQuery(name = "THistorique.findByHistContMatricule", query = "SELECT t FROM THistorique t WHERE t.histContMatricule = :histContMatricule"),
    @NamedQuery(name = "THistorique.findByHistContDateMajMatricule", query = "SELECT t FROM THistorique t WHERE t.histContDateMajMatricule = :histContDateMajMatricule"),
    @NamedQuery(name = "THistorique.findByHistContStatut", query = "SELECT t FROM THistorique t WHERE t.histContStatut = :histContStatut")})
public class THistorique implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "HIST_NUM")
    private Long histNum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "HIST_CONT_NUM")
    private String histContNum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HIST_CONT_DATENREG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date histContDatenreg;
    @Column(name = "HIST_CONT_DATEIMMATR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date histContDateimmatr;
    @Size(max = 1)
    @Column(name = "HIST_CONT_REIMMATR")
    private String histContReimmatr;
    @Size(max = 50)
    @Column(name = "HIST_CONT_NOM")
    private String histContNom;
    @Size(max = 100)
    @Column(name = "HIST_CONT_PREN")
    private String histContPren;
    @Size(max = 50)
    @Column(name = "HIST_CONT_NOM_JF")
    private String histContNomJf;
    @Size(max = 1)
    @Column(name = "HIST_CONT_SEXE")
    private String histContSexe;
    @Size(max = 20)
    @Column(name = "HIST_CONT_SIT_MAT")
    private String histContSitMat;
    @Column(name = "HIST_CONT_DATNAIS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date histContDatnais;
    @Size(max = 30)
    @Column(name = "HIST_CONT_LIEUNAIS")
    private String histContLieunais;
    @Size(max = 30)
    @Column(name = "HIST_CONT_BP")
    private String histContBp;
    @Column(name = "HIST_CONT_TEL")
    private BigInteger histContTel;
    @Column(name = "HIST_CONT_FAX")
    private BigInteger histContFax;
    @Size(max = 30)
    @Column(name = "HIST_CONT_MAIL")
    private String histContMail;
    @Size(max = 30)
    @Column(name = "HIST_CONT_CARRE")
    private String histContCarre;
    @Size(max = 5)
    @Column(name = "HIST_CONT_LOT")
    private String histContLot;
    @Size(max = 200)
    @Column(name = "HIST_CONT_RAIS")
    private String histContRais;
    @Size(max = 50)
    @Column(name = "HIST_CONT_NOM_COURT")
    private String histContNomCourt;
    @Size(max = 100)
    @Column(name = "HIST_CONT_NOM_LONG")
    private String histContNomLong;
    @Column(name = "HIST_CONT_DATCREAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date histContDatcreat;
    @Size(max = 100)
    @Column(name = "HIST_CONT_NOMPROPSIEG")
    private String histContNompropsieg;
    @Column(name = "HIST_CONT_LOYER")
    private Long histContLoyer;
    @Column(name = "HIST_CONT_CAPITAL")
    private BigInteger histContCapital;
    @Size(max = 20)
    @Column(name = "HIST_CONT_NUMINSAE")
    private String histContNuminsae;
    @Column(name = "HIST_CONT_DATENREG_INSAE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date histContDatenregInsae;
    @Column(name = "HIST_CONT_NB_ENF")
    private Short histContNbEnf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "HIST_CONT_CENTR_CODE")
    private String histContCentrCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "HIST_CONT_TYP_CONT_CODE")
    private String histContTypContCode;
    @Size(max = 8)
    @Column(name = "HIST_CONT_PROF_CODE")
    private String histContProfCode;
    @Size(max = 5)
    @Column(name = "HIST_CONT_FONCT_CODE")
    private String histContFonctCode;
    @Column(name = "HIST_CONT_NB_EMPL")
    private Short histContNbEmpl;
    @Size(max = 60)
    @Column(name = "HIST_CONT_QUART")
    private String histContQuart;
    @Size(max = 2)
    @Column(name = "HIST_CONT_CAT_ETAB_CODE")
    private String histContCatEtabCode;
    @Size(max = 2)
    @Column(name = "HIST_CONT_MOD_EXP_CODE")
    private String histContModExpCode;
    @Size(max = 15)
    @Column(name = "HIST_CONT_CONT_NUM")
    private String histContContNum;
    @Column(name = "HIST_CONT_ORDRE")
    private BigInteger histContOrdre;
    @Size(max = 1)
    @Column(name = "HIST_CONT_MEM_ACT_MERE")
    private String histContMemActMere;
    @Column(name = "HIST_CONT_DATE_CESSATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date histContDateCessation;
    @Column(name = "HIST_CONT_DATE_DECES")
    @Temporal(TemporalType.TIMESTAMP)
    private Date histContDateDeces;
    @Column(name = "HIST_CONT_DATE_REPRISE_ACT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date histContDateRepriseAct;
    @Size(max = 100)
    @Column(name = "HIST_CONT_ENS_COMMERCE")
    private String histContEnsCommerce;
    @Size(max = 5)
    @Column(name = "HIST_CONT_NATION_CODE")
    private String histContNationCode;
    @Size(max = 1)
    @Column(name = "HIST_CONT_NE_VERS")
    private String histContNeVers;
    @Size(max = 100)
    @Column(name = "HIST_CONT_VILLE")
    private String histContVille;
    @Size(max = 100)
    @Column(name = "HIST_CONT_RUE")
    private String histContRue;
    @Size(max = 100)
    @Column(name = "HIST_CONT_PRNOMPROPSIEG")
    private String histContPrnompropsieg;
    @Size(max = 1)
    @Column(name = "HIST_CONT_PROP")
    private String histContProp;
    @Size(max = 1)
    @Column(name = "HIST_CONT_MEM_BANK")
    private String histContMemBank;
    @Column(name = "HIST_CONT_NEW_IMMATR")
    private Long histContNewImmatr;
    @Size(max = 20)
    @Column(name = "HIST_CONT_NUMINSAE1")
    private String histContNuminsae1;
    @Size(max = 1)
    @Column(name = "HIST_CONT_ACTIF")
    private String histContActif;
    @Size(max = 6)
    @Column(name = "HIST_CONT_MATRICULE")
    private String histContMatricule;
    @Column(name = "HIST_CONT_DATE_MAJ_MATRICULE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date histContDateMajMatricule;
    @Size(max = 1)
    @Column(name = "HIST_CONT_STATUT")
    private String histContStatut;
    @JoinColumn(name = "HIST_UTIL_LOGIN", referencedColumnName = "UTIL_LOGIN")
    @ManyToOne
    private TUtilisateur histUtilLogin;
    @JoinColumn(name = "HIST_CONT_IMMATR", referencedColumnName = "CONT_IMMATR")
    @OneToOne
    private TRepUnique histContImmatr;
    @JoinColumn(name = "HIST_MOTIF_CODE", referencedColumnName = "MOTIF_CODE")
    @ManyToOne
    private TMotif histMotifCode;
    
    @Column(name = "HIST_DATE_DEBUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date histDateDebut;
    
    @Column(name = "HIST_DATE_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date histDateFin;
    
    

    public THistorique() {
    }

    public THistorique(Long histNum) {
        this.histNum = histNum;
    }

    public THistorique(Long histNum, String histContNum, Date histContDatenreg, String histContCentrCode, String histContTypContCode) {
        this.histNum = histNum;
        this.histContNum = histContNum;
        this.histContDatenreg = histContDatenreg;
        this.histContCentrCode = histContCentrCode;
        this.histContTypContCode = histContTypContCode;
    }
    
    public THistorique(TRepUnique tRepUnique, TMotif tMotif, TUtilisateur tUtilisateur){        
       this.setHistContCapital(tRepUnique.getContCapital());
       this.setHistContFax(tRepUnique.getContFax());
       this.setHistContOrdre(tRepUnique.getContOrdre());
       this.setHistContTel(tRepUnique.getContTel());
       this.setHistContDatcreat(tRepUnique.getContDatcreat());
       this.setHistContDateCessation(tRepUnique.getContDateCessation());
       this.setHistContDateDeces(tRepUnique.getContDateDeces());
       this.setHistContDateimmatr(tRepUnique.getContDateimmatr());
       this.setHistContDateMajMatricule(tRepUnique.getContDateMajMatricule());
       this.setHistContDatenreg(tRepUnique.getContDatenreg());
       this.setHistContDatenregInsae(tRepUnique.getContDatenregInsae());
       this.setHistContDateRepriseAct(tRepUnique.getContDateRepriseAct());
       this.setHistContDatnais(tRepUnique.getContDatnais());
       this.setHistContLoyer(tRepUnique.getContLoyer());
       this.setHistContNewImmatr(tRepUnique.getContNewImmatr());
      // this.setHistNum(longId);  // a revoir
       this.setHistContNbEmpl(tRepUnique.getContNbEmpl());
       this.setHistContNbEnf(tRepUnique.getContNbEnf());
       this.setHistContActif(tRepUnique.getContActif());
       this.setHistContBp(tRepUnique.getContBp());
       this.setHistContCarre(tRepUnique.getContCarre());
       this.setHistContCatEtabCode(tRepUnique.getContCatEtabCode());
       this.setHistContCentrCode(tRepUnique.getContCentrCode());
       this.setHistContContNum(tRepUnique.getContContNum());
       this.setHistContEnsCommerce(tRepUnique.getContEnsCommerce());
       this.setHistContFonctCode(tRepUnique.getContFonctCode());
       this.setHistContLieunais(tRepUnique.getContLieunais());
       this.setHistContLot(tRepUnique.getContLot());
       this.setHistContMail(tRepUnique.getContMail());
       this.setHistContMatricule(tRepUnique.getContMatricule());
       this.setHistContMemActMere(tRepUnique.getContMemActMere());
       this.setHistContMemBank(tRepUnique.getContMemBank());
       this.setHistContModExpCode(tRepUnique.getContModExpCode());
       this.setHistContNationCode(tRepUnique.getContNationCode());
       this.setHistContNeVers(tRepUnique.getContNeVers());
       this.setHistContNom(tRepUnique.getContNom());
       this.setHistContNomCourt(tRepUnique.getContNomCourt());
       this.setHistContNomJf(tRepUnique.getContNomJf());
       this.setHistContNomLong(tRepUnique.getContNomLong());
       this.setHistContNompropsieg(tRepUnique.getContNompropsieg());
       this.setHistContNum(tRepUnique.getContNum());
       this.setHistContNuminsae(tRepUnique.getContNuminsae());
       this.setHistContNuminsae1(tRepUnique.getContNuminsae1());
       this.setHistContPren(tRepUnique.getContPren());
       this.setHistContPrnompropsieg(tRepUnique.getContPrnompropsieg());
       this.setHistContProfCode(tRepUnique.getContProfCode());
       this.setHistContProp(tRepUnique.getContProp());
       this.setHistContQuart(tRepUnique.getContQuart());
       this.setHistContRais(tRepUnique.getContRais());
       this.setHistContReimmatr(tRepUnique.getContReimmatr());
       this.setHistContRue(tRepUnique.getContRue());
       this.setHistContSexe(tRepUnique.getContSexe());
       this.setHistContSitMat(tRepUnique.getContSitMat());
       this.setHistContStatut(tRepUnique.getContStatut());
       this.setHistContTypContCode(tRepUnique.getContTypContCode().getTypContCode());
       this.setHistContVille(tRepUnique.getContVille());
       this.setHistMotifCode(tMotif);   // a revoir
       this.setHistContImmatr( tRepUnique);  // a revoir
       this.setHistUtilLogin(tUtilisateur); // a revoir

    }

    public Long getHistNum() {
        return histNum;
    }

    public void setHistNum(Long histNum) {
        this.histNum = histNum;
    }

    public String getHistContNum() {
        return histContNum;
    }

    public void setHistContNum(String histContNum) {
        this.histContNum = histContNum;
    }

    public Date getHistContDatenreg() {
        return histContDatenreg;
    }

    public void setHistContDatenreg(Date histContDatenreg) {
        this.histContDatenreg = histContDatenreg;
    }

    public Date getHistContDateimmatr() {
        return histContDateimmatr;
    }

    public void setHistContDateimmatr(Date histContDateimmatr) {
        this.histContDateimmatr = histContDateimmatr;
    }

    public String getHistContReimmatr() {
        return histContReimmatr;
    }

    public void setHistContReimmatr(String histContReimmatr) {
        this.histContReimmatr = histContReimmatr;
    }

    public String getHistContNom() {
        return histContNom;
    }

    public void setHistContNom(String histContNom) {
        this.histContNom = histContNom;
    }

    public String getHistContPren() {
        return histContPren;
    }

    public void setHistContPren(String histContPren) {
        this.histContPren = histContPren;
    }

    public String getHistContNomJf() {
        return histContNomJf;
    }

    public void setHistContNomJf(String histContNomJf) {
        this.histContNomJf = histContNomJf;
    }

    public String getHistContSexe() {
        return histContSexe;
    }

    public void setHistContSexe(String histContSexe) {
        this.histContSexe = histContSexe;
    }

    public String getHistContSitMat() {
        return histContSitMat;
    }

    public void setHistContSitMat(String histContSitMat) {
        this.histContSitMat = histContSitMat;
    }

    public Date getHistContDatnais() {
        return histContDatnais;
    }

    public void setHistContDatnais(Date histContDatnais) {
        this.histContDatnais = histContDatnais;
    }

    public String getHistContLieunais() {
        return histContLieunais;
    }

    public void setHistContLieunais(String histContLieunais) {
        this.histContLieunais = histContLieunais;
    }

    public String getHistContBp() {
        return histContBp;
    }

    public void setHistContBp(String histContBp) {
        this.histContBp = histContBp;
    }

    public BigInteger getHistContTel() {
        return histContTel;
    }

    public void setHistContTel(BigInteger histContTel) {
        this.histContTel = histContTel;
    }

    public BigInteger getHistContFax() {
        return histContFax;
    }

    public void setHistContFax(BigInteger histContFax) {
        this.histContFax = histContFax;
    }

    public String getHistContMail() {
        return histContMail;
    }

    public void setHistContMail(String histContMail) {
        this.histContMail = histContMail;
    }

    public String getHistContCarre() {
        return histContCarre;
    }

    public void setHistContCarre(String histContCarre) {
        this.histContCarre = histContCarre;
    }

    public String getHistContLot() {
        return histContLot;
    }

    public void setHistContLot(String histContLot) {
        this.histContLot = histContLot;
    }

    public String getHistContRais() {
        return histContRais;
    }

    public void setHistContRais(String histContRais) {
        this.histContRais = histContRais;
    }

    public String getHistContNomCourt() {
        return histContNomCourt;
    }

    public void setHistContNomCourt(String histContNomCourt) {
        this.histContNomCourt = histContNomCourt;
    }

    public String getHistContNomLong() {
        return histContNomLong;
    }

    public void setHistContNomLong(String histContNomLong) {
        this.histContNomLong = histContNomLong;
    }

    public Date getHistContDatcreat() {
        return histContDatcreat;
    }

    public void setHistContDatcreat(Date histContDatcreat) {
        this.histContDatcreat = histContDatcreat;
    }

    public String getHistContNompropsieg() {
        return histContNompropsieg;
    }

    public void setHistContNompropsieg(String histContNompropsieg) {
        this.histContNompropsieg = histContNompropsieg;
    }

    public Long getHistContLoyer() {
        return histContLoyer;
    }

    public void setHistContLoyer(Long histContLoyer) {
        this.histContLoyer = histContLoyer;
    }

    public BigInteger getHistContCapital() {
        return histContCapital;
    }

    public void setHistContCapital(BigInteger histContCapital) {
        this.histContCapital = histContCapital;
    }

    public String getHistContNuminsae() {
        return histContNuminsae;
    }

    public void setHistContNuminsae(String histContNuminsae) {
        this.histContNuminsae = histContNuminsae;
    }

    public Date getHistContDatenregInsae() {
        return histContDatenregInsae;
    }

    public void setHistContDatenregInsae(Date histContDatenregInsae) {
        this.histContDatenregInsae = histContDatenregInsae;
    }

    public Short getHistContNbEnf() {
        return histContNbEnf;
    }

    public void setHistContNbEnf(Short histContNbEnf) {
        this.histContNbEnf = histContNbEnf;
    }

    public String getHistContCentrCode() {
        return histContCentrCode;
    }

    public void setHistContCentrCode(String histContCentrCode) {
        this.histContCentrCode = histContCentrCode;
    }

    public String getHistContTypContCode() {
        return histContTypContCode;
    }

    public void setHistContTypContCode(String histContTypContCode) {
        this.histContTypContCode = histContTypContCode;
    }

    public String getHistContProfCode() {
        return histContProfCode;
    }

    public void setHistContProfCode(String histContProfCode) {
        this.histContProfCode = histContProfCode;
    }

    public String getHistContFonctCode() {
        return histContFonctCode;
    }

    public void setHistContFonctCode(String histContFonctCode) {
        this.histContFonctCode = histContFonctCode;
    }

    public Short getHistContNbEmpl() {
        return histContNbEmpl;
    }

    public void setHistContNbEmpl(Short histContNbEmpl) {
        this.histContNbEmpl = histContNbEmpl;
    }

    public String getHistContQuart() {
        return histContQuart;
    }

    public void setHistContQuart(String histContQuart) {
        this.histContQuart = histContQuart;
    }

    public String getHistContCatEtabCode() {
        return histContCatEtabCode;
    }

    public void setHistContCatEtabCode(String histContCatEtabCode) {
        this.histContCatEtabCode = histContCatEtabCode;
    }

    public String getHistContModExpCode() {
        return histContModExpCode;
    }

    public void setHistContModExpCode(String histContModExpCode) {
        this.histContModExpCode = histContModExpCode;
    }

    public String getHistContContNum() {
        return histContContNum;
    }

    public void setHistContContNum(String histContContNum) {
        this.histContContNum = histContContNum;
    }

    public BigInteger getHistContOrdre() {
        return histContOrdre;
    }

    public void setHistContOrdre(BigInteger histContOrdre) {
        this.histContOrdre = histContOrdre;
    }

    public String getHistContMemActMere() {
        return histContMemActMere;
    }

    public void setHistContMemActMere(String histContMemActMere) {
        this.histContMemActMere = histContMemActMere;
    }

    public Date getHistContDateCessation() {
        return histContDateCessation;
    }

    public void setHistContDateCessation(Date histContDateCessation) {
        this.histContDateCessation = histContDateCessation;
    }

    public Date getHistContDateDeces() {
        return histContDateDeces;
    }

    public void setHistContDateDeces(Date histContDateDeces) {
        this.histContDateDeces = histContDateDeces;
    }

    public Date getHistContDateRepriseAct() {
        return histContDateRepriseAct;
    }

    public void setHistContDateRepriseAct(Date histContDateRepriseAct) {
        this.histContDateRepriseAct = histContDateRepriseAct;
    }

    public String getHistContEnsCommerce() {
        return histContEnsCommerce;
    }

    public void setHistContEnsCommerce(String histContEnsCommerce) {
        this.histContEnsCommerce = histContEnsCommerce;
    }

    public String getHistContNationCode() {
        return histContNationCode;
    }

    public void setHistContNationCode(String histContNationCode) {
        this.histContNationCode = histContNationCode;
    }

    public String getHistContNeVers() {
        return histContNeVers;
    }

    public void setHistContNeVers(String histContNeVers) {
        this.histContNeVers = histContNeVers;
    }

    public String getHistContVille() {
        return histContVille;
    }

    public void setHistContVille(String histContVille) {
        this.histContVille = histContVille;
    }

    public String getHistContRue() {
        return histContRue;
    }

    public void setHistContRue(String histContRue) {
        this.histContRue = histContRue;
    }

    public String getHistContPrnompropsieg() {
        return histContPrnompropsieg;
    }

    public void setHistContPrnompropsieg(String histContPrnompropsieg) {
        this.histContPrnompropsieg = histContPrnompropsieg;
    }

    public String getHistContProp() {
        return histContProp;
    }

    public void setHistContProp(String histContProp) {
        this.histContProp = histContProp;
    }

    public String getHistContMemBank() {
        return histContMemBank;
    }

    public void setHistContMemBank(String histContMemBank) {
        this.histContMemBank = histContMemBank;
    }

    public Long getHistContNewImmatr() {
        return histContNewImmatr;
    }

    public void setHistContNewImmatr(Long histContNewImmatr) {
        this.histContNewImmatr = histContNewImmatr;
    }

    public String getHistContNuminsae1() {
        return histContNuminsae1;
    }

    public void setHistContNuminsae1(String histContNuminsae1) {
        this.histContNuminsae1 = histContNuminsae1;
    }

    public String getHistContActif() {
        return histContActif;
    }

    public void setHistContActif(String histContActif) {
        this.histContActif = histContActif;
    }

    public String getHistContMatricule() {
        return histContMatricule;
    }

    public void setHistContMatricule(String histContMatricule) {
        this.histContMatricule = histContMatricule;
    }

    public Date getHistContDateMajMatricule() {
        return histContDateMajMatricule;
    }

    public void setHistContDateMajMatricule(Date histContDateMajMatricule) {
        this.histContDateMajMatricule = histContDateMajMatricule;
    }

    public String getHistContStatut() {
        return histContStatut;
    }

    public void setHistContStatut(String histContStatut) {
        this.histContStatut = histContStatut;
    }

    public TUtilisateur getHistUtilLogin() {
        return histUtilLogin;
    }

    public void setHistUtilLogin(TUtilisateur histUtilLogin) {
        this.histUtilLogin = histUtilLogin;
    }

    public TRepUnique getHistContImmatr() {
        return histContImmatr;
    }

    public void setHistContImmatr(TRepUnique histContImmatr) {
        this.histContImmatr = histContImmatr;
    }

    public TMotif getHistMotifCode() {
        return histMotifCode;
    }

    public void setHistMotifCode(TMotif histMotifCode) {
        this.histMotifCode = histMotifCode;
    }

    public Date getHistDateDebut() {
        return histDateDebut;
    }

    public void setHistDateDebut(Date histDateDebut) {
        this.histDateDebut = histDateDebut;
    }

    public Date getHistDateFin() {
        return histDateFin;
    }

    public void setHistDateFin(Date histDateFin) {
        this.histDateFin = histDateFin;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (histNum != null ? histNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof THistorique)) {
            return false;
        }
        THistorique other = (THistorique) object;
        if ((this.histNum == null && other.histNum != null) || (this.histNum != null && !this.histNum.equals(other.histNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.THistorique[ histNum=" + histNum + " ]";
    }
    
}
