/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "t_intervenant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TIntervenant.findAll", query = "SELECT t FROM TIntervenant t")
    , @NamedQuery(name = "TIntervenant.findByIntNumero", query = "SELECT t FROM TIntervenant t WHERE t.intNumero = :intNumero")
    , @NamedQuery(name = "TIntervenant.findByIntNom", query = "SELECT t FROM TIntervenant t WHERE t.intNom = :intNom")
    , @NamedQuery(name = "TIntervenant.findByIntPrenom", query = "SELECT t FROM TIntervenant t WHERE t.intPrenom = :intPrenom")
    , @NamedQuery(name = "TIntervenant.findByIntCollectivite", query = "SELECT t FROM TIntervenant t WHERE t.intCollectivite = :intCollectivite")
    , @NamedQuery(name = "TIntervenant.findByIntLepi", query = "SELECT t FROM TIntervenant t WHERE t.intLepi = :intLepi")
    , @NamedQuery(name = "TIntervenant.findByIntCiPp", query = "SELECT t FROM TIntervenant t WHERE t.intCiPp = :intCiPp")
    , @NamedQuery(name = "TIntervenant.findByIntDateExpCi", query = "SELECT t FROM TIntervenant t WHERE t.intDateExpCi = :intDateExpCi")
    , @NamedQuery(name = "TIntervenant.findByIntIfu", query = "SELECT t FROM TIntervenant t WHERE t.intIfu = :intIfu")
    , @NamedQuery(name = "TIntervenant.findByIntDomicile", query = "SELECT t FROM TIntervenant t WHERE t.intDomicile = :intDomicile")
    , @NamedQuery(name = "TIntervenant.findByIntNomInsti", query = "SELECT t FROM TIntervenant t WHERE t.intNomInsti = :intNomInsti")
    , @NamedQuery(name = "TIntervenant.findByIntCheflieu", query = "SELECT t FROM TIntervenant t WHERE t.intCheflieu = :intCheflieu")
    , @NamedQuery(name = "TIntervenant.findByIntSexe", query = "SELECT t FROM TIntervenant t WHERE t.intSexe = :intSexe")
    , @NamedQuery(name = "TIntervenant.findByIntPreuveAdm", query = "SELECT t FROM TIntervenant t WHERE t.intPreuveAdm = :intPreuveAdm")
    , @NamedQuery(name = "TIntervenant.findByIntOrigine", query = "SELECT t FROM TIntervenant t WHERE t.intOrigine = :intOrigine")
    , @NamedQuery(name = "TIntervenant.findByIntAnneArriv", query = "SELECT t FROM TIntervenant t WHERE t.intAnneArriv = :intAnneArriv")
    , @NamedQuery(name = "TIntervenant.findByIntProfession", query = "SELECT t FROM TIntervenant t WHERE t.intProfession = :intProfession")
    , @NamedQuery(name = "TIntervenant.findByIntNomPhoto", query = "SELECT t FROM TIntervenant t WHERE t.intNomPhoto = :intNomPhoto")
    , @NamedQuery(name = "TIntervenant.findByIntNumeroAssigne", query = "SELECT t FROM TIntervenant t WHERE t.intNumeroAssigne = :intNumeroAssigne")
    , @NamedQuery(name = "TIntervenant.findByIntNomEmprunt", query = "SELECT t FROM TIntervenant t WHERE t.intNomEmprunt = :intNomEmprunt")
    , @NamedQuery(name = "TIntervenant.findByIntTelephone", query = "SELECT t FROM TIntervenant t WHERE t.intTelephone = :intTelephone")
    , @NamedQuery(name = "TIntervenant.findByIntActAutre", query = "SELECT t FROM TIntervenant t WHERE t.intActAutre = :intActAutre")
    , @NamedQuery(name = "TIntervenant.findByIntEthAutre", query = "SELECT t FROM TIntervenant t WHERE t.intEthAutre = :intEthAutre")
    , @NamedQuery(name = "TIntervenant.findByIntNationalite", query = "SELECT t FROM TIntervenant t WHERE t.intNationalite = :intNationalite")
    , @NamedQuery(name = "TIntervenant.findByIntNationaliteAutre", query = "SELECT t FROM TIntervenant t WHERE t.intNationaliteAutre = :intNationaliteAutre")
    , @NamedQuery(name = "TIntervenant.findByIntNumeroRavip", query = "SELECT t FROM TIntervenant t WHERE t.intNumeroRavip = :intNumeroRavip")
    , @NamedQuery(name = "TIntervenant.findByIntStatutSocial", query = "SELECT t FROM TIntervenant t WHERE t.intStatutSocial = :intStatutSocial")
    , @NamedQuery(name = "TIntervenant.findByIntStructure", query = "SELECT t FROM TIntervenant t WHERE t.intStructure = :intStructure")
    , @NamedQuery(name = "TIntervenant.findByIntFonction", query = "SELECT t FROM TIntervenant t WHERE t.intFonction = :intFonction")
    , @NamedQuery(name = "TIntervenant.findByIntLigCode", query = "SELECT t FROM TIntervenant t WHERE t.intLigCode = :intLigCode")
    , @NamedQuery(name = "TIntervenant.findByIntPhotoIdentit", query = "SELECT t FROM TIntervenant t WHERE t.intPhotoIdentit = :intPhotoIdentit")
    , @NamedQuery(name = "TIntervenant.findByIntPhotoComplet", query = "SELECT t FROM TIntervenant t WHERE t.intPhotoComplet = :intPhotoComplet")
    , @NamedQuery(name = "TIntervenant.findByIntImage3", query = "SELECT t FROM TIntervenant t WHERE t.intImage3 = :intImage3")
    , @NamedQuery(name = "TIntervenant.findByIntImage4", query = "SELECT t FROM TIntervenant t WHERE t.intImage4 = :intImage4")
    , @NamedQuery(name = "TIntervenant.findByIntLigAutre", query = "SELECT t FROM TIntervenant t WHERE t.intLigAutre = :intLigAutre")
    , @NamedQuery(name = "TIntervenant.findByIntDateNais", query = "SELECT t FROM TIntervenant t WHERE t.intDateNais = :intDateNais")
    , @NamedQuery(name = "TIntervenant.findByIntDateChargement", query = "SELECT t FROM TIntervenant t WHERE t.intDateChargement = :intDateChargement")
    , @NamedQuery(name = "TIntervenant.findByIntDateNaissCalcul", query = "SELECT t FROM TIntervenant t WHERE t.intDateNaissCalcul = :intDateNaissCalcul")
    , @NamedQuery(name = "TIntervenant.findByIntLieuNaiss", query = "SELECT t FROM TIntervenant t WHERE t.intLieuNaiss = :intLieuNaiss")})
public class TIntervenant implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "int_numero")
    private String intNumero;
    @Size(max = 150)
    @Column(name = "int_nom")
    private String intNom;
    @Size(max = 150)
    @Column(name = "int_prenom")
    private String intPrenom;
    @Size(max = 150)
    @Column(name = "int_collectivite")
    private String intCollectivite;
    @Size(max = 50)
    @Column(name = "int_lepi")
    private String intLepi;
    @Size(max = 50)
    @Column(name = "int_ci_pp")
    private String intCiPp;
    @Column(name = "int_date_exp_ci")
    @Temporal(TemporalType.DATE)
    private Date intDateExpCi;
    @Size(max = 50)
    @Column(name = "int_ifu")
    private String intIfu;
    @Size(max = 200)
    @Column(name = "int_domicile")
    private String intDomicile;
    @Size(max = 300)
    @Column(name = "int_nom_insti")
    private String intNomInsti;
    @Size(max = 300)
    @Column(name = "int_cheflieu")
    private String intCheflieu;
    @Size(max = 20)
    @Column(name = "int_sexe")
    private String intSexe;
    @Size(max = 500)
    @Column(name = "int_preuve_adm")
    private String intPreuveAdm;
    @Size(max = 300)
    @Column(name = "int_origine")
    private String intOrigine;
    @Size(max = 10)
    @Column(name = "int_anne_arriv")
    private String intAnneArriv;
    @Size(max = 300)
    @Column(name = "int_profession")
    private String intProfession;
    @Size(max = 500)
    @Column(name = "int_nom_photo")
    private String intNomPhoto;
    @Size(max = 50)
    @Column(name = "int_numero_assigne")
    private String intNumeroAssigne;
    @Size(max = 500)
    @Column(name = "int_nom_emprunt")
    private String intNomEmprunt;
    @Size(max = 50)
    @Column(name = "int_telephone")
    private String intTelephone;
    @Size(max = 100)
    @Column(name = "int_act_autre")
    private String intActAutre;
    @Size(max = 100)
    @Column(name = "int_eth_autre")
    private String intEthAutre;
    @Size(max = 100)
    @Column(name = "int_nationalite")
    private String intNationalite;
    @Size(max = 100)
    @Column(name = "int_nationalite_autre")
    private String intNationaliteAutre;
    @Size(max = 100)
    @Column(name = "int_numero_ravip")
    private String intNumeroRavip;
    @Size(max = 100)
    @Column(name = "int_statut_social")
    private String intStatutSocial;
    @Size(max = 200)
    @Column(name = "int_structure")
    private String intStructure;
    @Size(max = 200)
    @Column(name = "int_fonction")
    private String intFonction;
    @Size(max = 5)
    @Column(name = "int_lig_code")
    private String intLigCode;
    @Size(max = 500)
    @Column(name = "int_photo_identit")
    private String intPhotoIdentit;
    @Size(max = 500)
    @Column(name = "int_photo_complet")
    private String intPhotoComplet;
    @Size(max = 500)
    @Column(name = "int_image3")
    private String intImage3;
    @Size(max = 500)
    @Column(name = "int_image4")
    private String intImage4;
    @Size(max = 200)
    @Column(name = "int_lig_autre")
    private String intLigAutre;
    @Column(name = "int_date_nais")
    private Integer intDateNais;
    @Column(name = "int_date_chargement")
    @Temporal(TemporalType.DATE)
    private Date intDateChargement;
    @Column(name = "int_date_naiss_calcul")
    @Temporal(TemporalType.DATE)
    private Date intDateNaissCalcul;
    @Size(max = 50)
    @Column(name = "int_lieu_naiss")
    private String intLieuNaiss;
    @OneToMany(mappedBy = "pbaProp")
    private List<TParcelleBafon> tParcelleBafonList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pvIntNumero")
    private List<TPvParcelle> tPvParcelleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tIntervenant")
    private List<TParticiper> tParticiperList;
    @JoinColumn(name = "int_act_code", referencedColumnName = "act_code")
    @ManyToOne
    private TActivite intActCode;
    @JoinColumn(name = "int_eth_code", referencedColumnName = "eth_code")
    @ManyToOne
    private TEthnie intEthCode;
    @JoinColumn(name = "int_vila_code", referencedColumnName = "vila_code")
    @ManyToOne
    private TVillage intVilaCode;
    @OneToMany(mappedBy = "opvIntNumeroBailleur")
    private List<TOperationParcel> tOperationParcelList;
    @OneToMany(mappedBy = "opvIntNumeroPreneur")
    private List<TOperationParcel> tOperationParcelList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dreIntNumero")
    private List<TDroitExerce> tDroitExerceList;
    @OneToMany(mappedBy = "desiIntNumero")
    private List<TDepotSignature> tDepotSignatureList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invIntNumero")
    private List<TIntervenir> tIntervenirList;
    @OneToMany(mappedBy = "divIntNumero")
    private List<TDivergence> tDivergenceList;

    public TIntervenant() {
    }

    public TIntervenant(String intNumero) {
        this.intNumero = intNumero;
    }

    public String getIntNumero() {
        return intNumero;
    }

    public void setIntNumero(String intNumero) {
        this.intNumero = intNumero;
    }

    public String getIntNom() {
        return intNom;
    }

    public void setIntNom(String intNom) {
        this.intNom = intNom;
    }

    public String getIntPrenom() {
        return intPrenom;
    }

    public void setIntPrenom(String intPrenom) {
        this.intPrenom = intPrenom;
    }

    public String getIntCollectivite() {
        return intCollectivite;
    }

    public void setIntCollectivite(String intCollectivite) {
        this.intCollectivite = intCollectivite;
    }

    public String getIntLepi() {
        return intLepi;
    }

    public void setIntLepi(String intLepi) {
        this.intLepi = intLepi;
    }

    public String getIntCiPp() {
        return intCiPp;
    }

    public void setIntCiPp(String intCiPp) {
        this.intCiPp = intCiPp;
    }

    public Date getIntDateExpCi() {
        return intDateExpCi;
    }

    public void setIntDateExpCi(Date intDateExpCi) {
        this.intDateExpCi = intDateExpCi;
    }

    public String getIntIfu() {
        return intIfu;
    }

    public void setIntIfu(String intIfu) {
        this.intIfu = intIfu;
    }

    public String getIntDomicile() {
        return intDomicile;
    }

    public void setIntDomicile(String intDomicile) {
        this.intDomicile = intDomicile;
    }

    public String getIntNomInsti() {
        return intNomInsti;
    }

    public void setIntNomInsti(String intNomInsti) {
        this.intNomInsti = intNomInsti;
    }

    public String getIntCheflieu() {
        return intCheflieu;
    }

    public void setIntCheflieu(String intCheflieu) {
        this.intCheflieu = intCheflieu;
    }

    public String getIntSexe() {
        return intSexe;
    }

    public void setIntSexe(String intSexe) {
        this.intSexe = intSexe;
    }

    public String getIntPreuveAdm() {
        return intPreuveAdm;
    }

    public void setIntPreuveAdm(String intPreuveAdm) {
        this.intPreuveAdm = intPreuveAdm;
    }

    public String getIntOrigine() {
        return intOrigine;
    }

    public void setIntOrigine(String intOrigine) {
        this.intOrigine = intOrigine;
    }

    public String getIntAnneArriv() {
        return intAnneArriv;
    }

    public void setIntAnneArriv(String intAnneArriv) {
        this.intAnneArriv = intAnneArriv;
    }

    public String getIntProfession() {
        return intProfession;
    }

    public void setIntProfession(String intProfession) {
        this.intProfession = intProfession;
    }

    public String getIntNomPhoto() {
        return intNomPhoto;
    }

    public void setIntNomPhoto(String intNomPhoto) {
        this.intNomPhoto = intNomPhoto;
    }

    public String getIntNumeroAssigne() {
        return intNumeroAssigne;
    }

    public void setIntNumeroAssigne(String intNumeroAssigne) {
        this.intNumeroAssigne = intNumeroAssigne;
    }

    public String getIntNomEmprunt() {
        return intNomEmprunt;
    }

    public void setIntNomEmprunt(String intNomEmprunt) {
        this.intNomEmprunt = intNomEmprunt;
    }

    public String getIntTelephone() {
        return intTelephone;
    }

    public void setIntTelephone(String intTelephone) {
        this.intTelephone = intTelephone;
    }

    public String getIntActAutre() {
        return intActAutre;
    }

    public void setIntActAutre(String intActAutre) {
        this.intActAutre = intActAutre;
    }

    public String getIntEthAutre() {
        return intEthAutre;
    }

    public void setIntEthAutre(String intEthAutre) {
        this.intEthAutre = intEthAutre;
    }

    public String getIntNationalite() {
        return intNationalite;
    }

    public void setIntNationalite(String intNationalite) {
        this.intNationalite = intNationalite;
    }

    public String getIntNationaliteAutre() {
        return intNationaliteAutre;
    }

    public void setIntNationaliteAutre(String intNationaliteAutre) {
        this.intNationaliteAutre = intNationaliteAutre;
    }

    public String getIntNumeroRavip() {
        return intNumeroRavip;
    }

    public void setIntNumeroRavip(String intNumeroRavip) {
        this.intNumeroRavip = intNumeroRavip;
    }

    public String getIntStatutSocial() {
        return intStatutSocial;
    }

    public void setIntStatutSocial(String intStatutSocial) {
        this.intStatutSocial = intStatutSocial;
    }

    public String getIntStructure() {
        return intStructure;
    }

    public void setIntStructure(String intStructure) {
        this.intStructure = intStructure;
    }

    public String getIntFonction() {
        return intFonction;
    }

    public void setIntFonction(String intFonction) {
        this.intFonction = intFonction;
    }

    public String getIntLigCode() {
        return intLigCode;
    }

    public void setIntLigCode(String intLigCode) {
        this.intLigCode = intLigCode;
    }

    public String getIntPhotoIdentit() {
        return intPhotoIdentit;
    }

    public void setIntPhotoIdentit(String intPhotoIdentit) {
        this.intPhotoIdentit = intPhotoIdentit;
    }

    public String getIntPhotoComplet() {
        return intPhotoComplet;
    }

    public void setIntPhotoComplet(String intPhotoComplet) {
        this.intPhotoComplet = intPhotoComplet;
    }

    public String getIntImage3() {
        return intImage3;
    }

    public void setIntImage3(String intImage3) {
        this.intImage3 = intImage3;
    }

    public String getIntImage4() {
        return intImage4;
    }

    public void setIntImage4(String intImage4) {
        this.intImage4 = intImage4;
    }

    public String getIntLigAutre() {
        return intLigAutre;
    }

    public void setIntLigAutre(String intLigAutre) {
        this.intLigAutre = intLigAutre;
    }

    public Integer getIntDateNais() {
        return intDateNais;
    }

    public void setIntDateNais(Integer intDateNais) {
        this.intDateNais = intDateNais;
    }

    public Date getIntDateChargement() {
        return intDateChargement;
    }

    public void setIntDateChargement(Date intDateChargement) {
        this.intDateChargement = intDateChargement;
    }

    public Date getIntDateNaissCalcul() {
        return intDateNaissCalcul;
    }

    public void setIntDateNaissCalcul(Date intDateNaissCalcul) {
        this.intDateNaissCalcul = intDateNaissCalcul;
    }

    public String getIntLieuNaiss() {
        return intLieuNaiss;
    }

    public void setIntLieuNaiss(String intLieuNaiss) {
        this.intLieuNaiss = intLieuNaiss;
    }

    @XmlTransient
    public List<TParcelleBafon> getTParcelleBafonList() {
        return tParcelleBafonList;
    }

    public void setTParcelleBafonList(List<TParcelleBafon> tParcelleBafonList) {
        this.tParcelleBafonList = tParcelleBafonList;
    }

    @XmlTransient
    public List<TPvParcelle> getTPvParcelleList() {
        return tPvParcelleList;
    }

    public void setTPvParcelleList(List<TPvParcelle> tPvParcelleList) {
        this.tPvParcelleList = tPvParcelleList;
    }

    @XmlTransient
    public List<TParticiper> getTParticiperList() {
        return tParticiperList;
    }

    public void setTParticiperList(List<TParticiper> tParticiperList) {
        this.tParticiperList = tParticiperList;
    }

    public TActivite getIntActCode() {
        return intActCode;
    }

    public void setIntActCode(TActivite intActCode) {
        this.intActCode = intActCode;
    }

    public TEthnie getIntEthCode() {
        return intEthCode;
    }

    public void setIntEthCode(TEthnie intEthCode) {
        this.intEthCode = intEthCode;
    }

    public TVillage getIntVilaCode() {
        return intVilaCode;
    }

    public void setIntVilaCode(TVillage intVilaCode) {
        this.intVilaCode = intVilaCode;
    }

    @XmlTransient
    public List<TOperationParcel> getTOperationParcelList() {
        return tOperationParcelList;
    }

    public void setTOperationParcelList(List<TOperationParcel> tOperationParcelList) {
        this.tOperationParcelList = tOperationParcelList;
    }

    @XmlTransient
    public List<TOperationParcel> getTOperationParcelList1() {
        return tOperationParcelList1;
    }

    public void setTOperationParcelList1(List<TOperationParcel> tOperationParcelList1) {
        this.tOperationParcelList1 = tOperationParcelList1;
    }

    @XmlTransient
    public List<TDroitExerce> getTDroitExerceList() {
        return tDroitExerceList;
    }

    public void setTDroitExerceList(List<TDroitExerce> tDroitExerceList) {
        this.tDroitExerceList = tDroitExerceList;
    }

    @XmlTransient
    public List<TDepotSignature> getTDepotSignatureList() {
        return tDepotSignatureList;
    }

    public void setTDepotSignatureList(List<TDepotSignature> tDepotSignatureList) {
        this.tDepotSignatureList = tDepotSignatureList;
    }

    @XmlTransient
    public List<TIntervenir> getTIntervenirList() {
        return tIntervenirList;
    }

    public void setTIntervenirList(List<TIntervenir> tIntervenirList) {
        this.tIntervenirList = tIntervenirList;
    }

    @XmlTransient
    public List<TDivergence> getTDivergenceList() {
        return tDivergenceList;
    }

    public void setTDivergenceList(List<TDivergence> tDivergenceList) {
        this.tDivergenceList = tDivergenceList;
    }

    
      @Override
    public TIntervenant clone() {
        try {
 
            TIntervenant intervenant = (TIntervenant) super.clone();           
            return intervenant;
 
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
           // log.error(e);
        }
        return null;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (intNumero != null ? intNumero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TIntervenant)) {
            return false;
        }
        TIntervenant other = (TIntervenant) object;
        if ((this.intNumero == null && other.intNumero != null) || (this.intNumero != null && !this.intNumero.equals(other.intNumero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TIntervenant[ intNumero=" + intNumero + " ]";
    }
    
}
