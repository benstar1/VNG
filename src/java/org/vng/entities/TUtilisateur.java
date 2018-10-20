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
@Table(name = "t_utilisateur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUtilisateur.findAll", query = "SELECT t FROM TUtilisateur t")
    , @NamedQuery(name = "TUtilisateur.findByUtiCode", query = "SELECT t FROM TUtilisateur t WHERE t.utiCode = :utiCode")
    , @NamedQuery(name = "TUtilisateur.findByUtiNom", query = "SELECT t FROM TUtilisateur t WHERE t.utiNom = :utiNom")
    , @NamedQuery(name = "TUtilisateur.findByUtiTYpe", query = "SELECT t FROM TUtilisateur t WHERE t.utiTyuCode.tyuCode = :typeUtil")
    , @NamedQuery(name = "TUtilisateur.findByUtiPrenom", query = "SELECT t FROM TUtilisateur t WHERE t.utiPrenom = :utiPrenom")
    , @NamedQuery(name = "TUtilisateur.findByUtiLogin", query = "SELECT t FROM TUtilisateur t WHERE t.utiLogin = :utiLogin")
    , @NamedQuery(name = "TUtilisateur.findByUtiPassword", query = "SELECT t FROM TUtilisateur t WHERE t.utiPassword = :utiPassword")
    , @NamedQuery(name = "TUtilisateur.findByUtiDateNais", query = "SELECT t FROM TUtilisateur t WHERE t.utiDateNais = :utiDateNais")
    , @NamedQuery(name = "TUtilisateur.findByUtiMail", query = "SELECT t FROM TUtilisateur t WHERE t.utiMail = :utiMail")
    , @NamedQuery(name = "TUtilisateur.findByUtiAdresse", query = "SELECT t FROM TUtilisateur t WHERE t.utiAdresse = :utiAdresse")
    , @NamedQuery(name = "TUtilisateur.findByUtiTelephone", query = "SELECT t FROM TUtilisateur t WHERE t.utiTelephone = :utiTelephone")
    , @NamedQuery(name = "TUtilisateur.findByUtiAdressePhoto", query = "SELECT t FROM TUtilisateur t WHERE t.utiAdressePhoto = :utiAdressePhoto")
    , @NamedQuery(name = "TUtilisateur.findByUtilNomImage", query = "SELECT t FROM TUtilisateur t WHERE t.utilNomImage = :utilNomImage")
    , @NamedQuery(name = "TUtilisateur.findByUtiDateChargement", query = "SELECT t FROM TUtilisateur t WHERE t.utiDateChargement = :utiDateChargement")})
public class TUtilisateur implements Serializable {

    @OneToMany(mappedBy = "patyUtiCode")
    private List<TParcelleTypeBf> tParcelleTypeBfList;
    @OneToMany(mappedBy = "signUtiCode")
    private List<TSignataire> tSignataireList;

    @OneToMany(mappedBy = "pbaCodeGeo")
    private List<TParcelleBafon> tParcelleBafonList;


    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "uti_code")
    private String utiCode;
    @Size(max = 100)
    @Column(name = "uti_nom")
    private String utiNom;
    @Size(max = 100)
    @Column(name = "uti_prenom")
    private String utiPrenom;
    @Size(max = 100)
    @Column(name = "uti_login")
    private String utiLogin;
    @Size(max = 200)
    @Column(name = "uti_password")
    private String utiPassword;
    @Column(name = "uti_date_nais")
    @Temporal(TemporalType.DATE)
    private Date utiDateNais;
    @Size(max = 200)
    @Column(name = "uti_mail")
    private String utiMail;
    @Size(max = 500)
    @Column(name = "uti_adresse")
    private String utiAdresse;
    @Size(max = 50)
    @Column(name = "uti_telephone")
    private String utiTelephone;
    @Size(max = 2000)
    @Column(name = "uti_adresse_photo")
    private String utiAdressePhoto;
    @Size(max = 500)
    @Column(name = "util_nom_image")
    private String utilNomImage;
    @Column(name = "uti_date_chargement")
    @Temporal(TemporalType.DATE)
    private Date utiDateChargement;
    @OneToMany(mappedBy = "pvUtiCodeEnq")
    private List<TPvParcelle> tPvParcelleList;
    @OneToMany(mappedBy = "pvUtiCodeGeo")
    private List<TPvParcelle> tPvParcelleList1;
    @JoinColumn(name = "uti_tyu_code", referencedColumnName = "tyu_code")
    @ManyToOne
    private TTypeutil utiTyuCode;
    @OneToMany(mappedBy = "opvUtiCode")
    private List<TOperationParcel> tOperationParcelList;
    @OneToMany(mappedBy = "dreUtiCode")
    private List<TDroitExerce> tDroitExerceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "affUtiCode")
    private List<TAffecter> tAffecterList;
    @OneToMany(mappedBy = "desiUtiCode")
    private List<TDepotSignature> tDepotSignatureList;
    @OneToMany(mappedBy = "invUtiCode")
    private List<TIntervenir> tIntervenirList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tUtilisateur")
    private List<TEnqueter> tEnqueterList;

    public TUtilisateur() {
    }

    public TUtilisateur(String utiCode) {
        this.utiCode = utiCode;
    }

    public String getUtiCode() {
        return utiCode;
    }

    public void setUtiCode(String utiCode) {
        this.utiCode = utiCode;
    }

    public String getUtiNom() {
        return utiNom;
    }

    public void setUtiNom(String utiNom) {
        this.utiNom = utiNom;
    }

    public String getUtiPrenom() {
        return utiPrenom;
    }

    public void setUtiPrenom(String utiPrenom) {
        this.utiPrenom = utiPrenom;
    }

    public String getUtiLogin() {
        return utiLogin;
    }

    public void setUtiLogin(String utiLogin) {
        this.utiLogin = utiLogin;
    }

    public String getUtiPassword() {
        return utiPassword;
    }

    public void setUtiPassword(String utiPassword) {
        this.utiPassword = utiPassword;
    }

    public Date getUtiDateNais() {
        return utiDateNais;
    }

    public void setUtiDateNais(Date utiDateNais) {
        this.utiDateNais = utiDateNais;
    }

    public String getUtiMail() {
        return utiMail;
    }

    public void setUtiMail(String utiMail) {
        this.utiMail = utiMail;
    }

    public String getUtiAdresse() {
        return utiAdresse;
    }

    public void setUtiAdresse(String utiAdresse) {
        this.utiAdresse = utiAdresse;
    }

    public String getUtiTelephone() {
        return utiTelephone;
    }

    public void setUtiTelephone(String utiTelephone) {
        this.utiTelephone = utiTelephone;
    }

    public String getUtiAdressePhoto() {
        return utiAdressePhoto;
    }

    public void setUtiAdressePhoto(String utiAdressePhoto) {
        this.utiAdressePhoto = utiAdressePhoto;
    }

    public String getUtilNomImage() {
        return utilNomImage;
    }

    public void setUtilNomImage(String utilNomImage) {
        this.utilNomImage = utilNomImage;
    }

    public Date getUtiDateChargement() {
        return utiDateChargement;
    }

    public void setUtiDateChargement(Date utiDateChargement) {
        this.utiDateChargement = utiDateChargement;
    }

    @XmlTransient
    public List<TPvParcelle> getTPvParcelleList() {
        return tPvParcelleList;
    }

    public void setTPvParcelleList(List<TPvParcelle> tPvParcelleList) {
        this.tPvParcelleList = tPvParcelleList;
    }

    @XmlTransient
    public List<TPvParcelle> getTPvParcelleList1() {
        return tPvParcelleList1;
    }

    public void setTPvParcelleList1(List<TPvParcelle> tPvParcelleList1) {
        this.tPvParcelleList1 = tPvParcelleList1;
    }

    public TTypeutil getUtiTyuCode() {
        return utiTyuCode;
    }

    public void setUtiTyuCode(TTypeutil utiTyuCode) {
        this.utiTyuCode = utiTyuCode;
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
    public List<TAffecter> getTAffecterList() {
        return tAffecterList;
    }

    public void setTAffecterList(List<TAffecter> tAffecterList) {
        this.tAffecterList = tAffecterList;
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
    public List<TEnqueter> getTEnqueterList() {
        return tEnqueterList;
    }

    public void setTEnqueterList(List<TEnqueter> tEnqueterList) {
        this.tEnqueterList = tEnqueterList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (utiCode != null ? utiCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUtilisateur)) {
            return false;
        }
        TUtilisateur other = (TUtilisateur) object;
        if ((this.utiCode == null && other.utiCode != null) || (this.utiCode != null && !this.utiCode.equals(other.utiCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TUtilisateur[ utiCode=" + utiCode + " ]";
    }

    @XmlTransient
    public List<TParcelleBafon> getTParcelleBafonList() {
        return tParcelleBafonList;
    }

    public void setTParcelleBafonList(List<TParcelleBafon> tParcelleBafonList) {
        this.tParcelleBafonList = tParcelleBafonList;
    }

    @XmlTransient
    public List<TParcelleTypeBf> getTParcelleTypeBfList() {
        return tParcelleTypeBfList;
    }

    public void setTParcelleTypeBfList(List<TParcelleTypeBf> tParcelleTypeBfList) {
        this.tParcelleTypeBfList = tParcelleTypeBfList;
    }

    @XmlTransient
    public List<TSignataire> getTSignataireList() {
        return tSignataireList;
    }

    public void setTSignataireList(List<TSignataire> tSignataireList) {
        this.tSignataireList = tSignataireList;
    }
    
}
