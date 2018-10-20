/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dev_DI
 */
@Entity
@Table(name = "t_signataire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TSignataire.findAll", query = "SELECT t FROM TSignataire t")
    , @NamedQuery(name = "TSignataire.findBySignCode", query = "SELECT t FROM TSignataire t WHERE t.signCode = :signCode")
    , @NamedQuery(name = "TSignataire.findBySignNom", query = "SELECT t FROM TSignataire t WHERE t.signNom = :signNom")
    , @NamedQuery(name = "TSignataire.findBySignPrenom", query = "SELECT t FROM TSignataire t WHERE t.signPrenom = :signPrenom")
    , @NamedQuery(name = "TSignataire.findBySignActif", query = "SELECT t FROM TSignataire t WHERE t.signActif = :signActif")
    , @NamedQuery(name = "TSignataire.findBySignFonction", query = "SELECT t FROM TSignataire t WHERE t.signFonction = :signFonction")})
public class TSignataire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "sign_code")
    private String signCode;
    @Size(max = 100)
    @Column(name = "sign_nom")
    private String signNom;
    @Size(max = 100)
    @Column(name = "sign_prenom")
    private String signPrenom;
    @Column(name = "sign_actif")
    private Boolean signActif;
    @Size(max = 300)
    @Column(name = "sign_fonction")
    private String signFonction;
    @OneToMany(mappedBy = "opvSignCode")
    private List<TOperationParcel> tOperationParcelList;
    @JoinColumn(name = "sign_uti_code", referencedColumnName = "uti_code")
    @ManyToOne
    private TUtilisateur signUtiCode;

    public TSignataire() {
    }

    public TSignataire(String signCode) {
        this.signCode = signCode;
    }

    public String getSignCode() {
        return signCode;
    }

    public void setSignCode(String signCode) {
        this.signCode = signCode;
    }

    public String getSignNom() {
        return signNom;
    }

    public void setSignNom(String signNom) {
        this.signNom = signNom;
    }

    public String getSignPrenom() {
        return signPrenom;
    }

    public void setSignPrenom(String signPrenom) {
        this.signPrenom = signPrenom;
    }

    public Boolean getSignActif() {
        return signActif;
    }

    public void setSignActif(Boolean signActif) {
        this.signActif = signActif;
    }

    public String getSignFonction() {
        return signFonction;
    }

    public void setSignFonction(String signFonction) {
        this.signFonction = signFonction;
    }

    @XmlTransient
    public List<TOperationParcel> getTOperationParcelList() {
        return tOperationParcelList;
    }

    public void setTOperationParcelList(List<TOperationParcel> tOperationParcelList) {
        this.tOperationParcelList = tOperationParcelList;
    }

    public TUtilisateur getSignUtiCode() {
        return signUtiCode;
    }

    public void setSignUtiCode(TUtilisateur signUtiCode) {
        this.signUtiCode = signUtiCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (signCode != null ? signCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TSignataire)) {
            return false;
        }
        TSignataire other = (TSignataire) object;
        if ((this.signCode == null && other.signCode != null) || (this.signCode != null && !this.signCode.equals(other.signCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TSignataire[ signCode=" + signCode + " ]";
    }
    
}
