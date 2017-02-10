/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

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
 * @author HP
 */
@Entity
@Table(name = "T_UTILISATEUR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUtilisateur.findAll", query = "SELECT t FROM TUtilisateur t"),
    @NamedQuery(name = "TUtilisateur.findByUtilLogin", query = "SELECT t FROM TUtilisateur t WHERE t.utilLogin = :utilLogin"),
    @NamedQuery(name = "TUtilisateur.findByGroupe", query = "SELECT t FROM TUtilisateur t WHERE t.groupe = :groupe"),
    @NamedQuery(name = "TUtilisateur.findByUtilActif", query = "SELECT t FROM TUtilisateur t WHERE t.utilActif = :utilActif")})
public class TUtilisateur implements Serializable {
    @JoinColumn(name = "UTIL_CONT_IMMATR", referencedColumnName = "CONT_IMMATR")
    @ManyToOne
    private TRepUnique utilContImmatr;
    @Size(max = 30)
    @Column(name = "UTIL_COD")
    private String utilCod;
    @Size(max = 30)
    @Column(name = "FONCT_COD")
    private String fonctCod;
    @Size(max = 50)
    @Column(name = "UTIL_NOM")
    private String utilNom;
    @Size(max = 50)
    @Column(name = "UTIL_PRENOMS")
    private String utilPrenoms;
    @Column(name = "UTIL_DATE_CREAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date utilDateCreat;
    @Column(name = "UTIL_DATE_SUSPENS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date utilDateSuspens;
    @Size(max = 1)
    @Column(name = "SUPPR_UTILI")
    private String supprUtili;
    @Size(max = 1000)
    @Column(name = "UTIL_PASSWORD")
    private String utilPassword;
    @Size(max = 20)
    @Column(name = "UTIL_ADMINISTRATION")
    private String utilAdministration;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "UTIL_LOGIN")
    private String utilLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "UTIL_ACTIF")
    private String utilActif;
    @OneToMany(mappedBy = "histUtilLogin")
    private List<THistorique> tHistoriqueList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tUtilisateur")
    private List<TAppartenir> tAppartenirList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "histStatutUtilLogin")
    private List<THistStatut> tHistStatutList;

    
    @JoinColumn(name = "GROUPE", referencedColumnName = "GROUP_ID")
    @ManyToOne(optional = false)
    private TGroupe groupe;
        @Size(max = 15)
    @Column(name = "UTIL_TEL")
    private String utilTel;
@Size(max = 15)
    @Column(name = "UTIL_TELFIX")
    private String utilTelfix;

    @Size(max = 15)
    @Column(name = "UTIL_TEL2")
    private String utilTel2;
    @Size(max = 30)
    @Column(name = "UTIL_EMAIL")
    private String utilEmail;
    
        @Size(max = 15)
    @Column(name = "UTIL_NUMCI")
    private String utilNumci;
    @Column(name = "UTIL_DATEEXPCI")
    @Temporal(TemporalType.DATE)
    private Date utilDateexpci;
    @Size(max = 30)
    @Column(name = "UTIL_LIEUDELIVRCI")
    private String utilLieudelivrci;
    
    
    public String getUtilTel() {
        return utilTel;
    }

    public void setUtilTel(String utilTel) {
        this.utilTel = utilTel;
    }
    
        public String getUtilTel2() {
        return utilTel2;
    }

    public void setUtilTel2(String utilTel2) {
        this.utilTel2 = utilTel2;
    }

    public String getUtilEmail() {
        return utilEmail;
    }

    public void setUtilEmail(String utilEmail) {
        this.utilEmail = utilEmail;
    }


    public String getUtilTelfix() {
        return utilTelfix;
    }

    public void setUtilTelfix(String utilTelfix) {
        this.utilTelfix = utilTelfix;
    }

    
    public String getUtilNumci() {
        return utilNumci;
    }

    public void setUtilNumci(String utilNumci) {
        this.utilNumci = utilNumci;
    }

    public Date getUtilDateexpci() {
        return utilDateexpci;
    }

    public void setUtilDateexpci(Date utilDateexpci) {
        this.utilDateexpci = utilDateexpci;
    }

    public String getUtilLieudelivrci() {
        return utilLieudelivrci;
    }

    public void setUtilLieudelivrci(String utilLieudelivrci) {
        this.utilLieudelivrci = utilLieudelivrci;
    }

    public TUtilisateur() {
    }

    public TUtilisateur(String utilLogin) {
        this.utilLogin = utilLogin;
    }

    public TUtilisateur(String utilLogin, String utilActif) {
        this.utilLogin = utilLogin;
        this.utilActif = utilActif;
    }

    public String getUtilLogin() {
        return utilLogin;
    }

    public void setUtilLogin(String utilLogin) {
        this.utilLogin = utilLogin;
    }

    public String getUtilActif() {
        return utilActif;
    }

    public void setUtilActif(String utilActif) {
        this.utilActif = utilActif;
    }

    @XmlTransient
    public List<THistorique> getTHistoriqueList() {
        return tHistoriqueList;
    }

    public void setTHistoriqueList(List<THistorique> tHistoriqueList) {
        this.tHistoriqueList = tHistoriqueList;
    }

    @XmlTransient
    public List<TAppartenir> getTAppartenirList() {
        return tAppartenirList;
    }

    public void setTAppartenirList(List<TAppartenir> tAppartenirList) {
        this.tAppartenirList = tAppartenirList;
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
        hash += (utilLogin != null ? utilLogin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUtilisateur)) {
            return false;
        }
        TUtilisateur other = (TUtilisateur) object;
        if ((this.utilLogin == null && other.utilLogin != null) || (this.utilLogin != null && !this.utilLogin.equals(other.utilLogin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TUtilisateur[ utilLogin=" + utilLogin + " ]";
    }

    public String getUtilCod() {
        return utilCod;
    }

    public void setUtilCod(String utilCod) {
        this.utilCod = utilCod;
    }

    public String getFonctCod() {
        return fonctCod;
    }

    public void setFonctCod(String fonctCod) {
        this.fonctCod = fonctCod;
    }

    public String getUtilNom() {
        return utilNom;
    }

    public void setUtilNom(String utilNom) {
        this.utilNom = utilNom;
    }

    public String getUtilPrenoms() {
        return utilPrenoms;
    }

    public void setUtilPrenoms(String utilPrenoms) {
        this.utilPrenoms = utilPrenoms;
    }

    public Date getUtilDateCreat() {
        return utilDateCreat;
    }

    public void setUtilDateCreat(Date utilDateCreat) {
        this.utilDateCreat = utilDateCreat;
    }

    public Date getUtilDateSuspens() {
        return utilDateSuspens;
    }

    public void setUtilDateSuspens(Date utilDateSuspens) {
        this.utilDateSuspens = utilDateSuspens;
    }

    public String getSupprUtili() {
        return supprUtili;
    }

    public void setSupprUtili(String supprUtili) {
        this.supprUtili = supprUtili;
    }

    public String getUtilPassword() {
        return utilPassword;
    }

    public void setUtilPassword(String utilPassword) {
        this.utilPassword = utilPassword;
    }

    public String getUtilAdministration() {
        return utilAdministration;
    }

    public void setUtilAdministration(String utilAdministration) {
        this.utilAdministration = utilAdministration;
    }

    public TRepUnique getUtilContImmatr() {
        return utilContImmatr;
    }

    public void setUtilContImmatr(TRepUnique utilContImmatr) {
        this.utilContImmatr = utilContImmatr;
    }
    
            public TGroupe getGroupe() {
        return groupe;
    }

    public void setGroupe(TGroupe groupe) {
        this.groupe = groupe;
    }
}
