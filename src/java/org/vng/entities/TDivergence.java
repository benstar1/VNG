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
@Table(name = "t_divergence")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TDivergence.findAll", query = "SELECT t FROM TDivergence t")
    , @NamedQuery(name = "TDivergence.findByDivCode", query = "SELECT t FROM TDivergence t WHERE t.divCode = :divCode")
    , @NamedQuery(name = "TDivergence.findByDivOriginDroit", query = "SELECT t FROM TDivergence t WHERE t.divOriginDroit = :divOriginDroit")
    , @NamedQuery(name = "TDivergence.findByDivDureeOccup", query = "SELECT t FROM TDivergence t WHERE t.divDureeOccup = :divDureeOccup")
    , @NamedQuery(name = "TDivergence.findByDivNatureContest", query = "SELECT t FROM TDivergence t WHERE t.divNatureContest = :divNatureContest")
    , @NamedQuery(name = "TDivergence.findByDivRocAutre", query = "SELECT t FROM TDivergence t WHERE t.divRocAutre = :divRocAutre")
    , @NamedQuery(name = "TDivergence.findByDivOriginDroitAutre", query = "SELECT t FROM TDivergence t WHERE t.divOriginDroitAutre = :divOriginDroitAutre")
    , @NamedQuery(name = "TDivergence.findByDivNatureContestAutre", query = "SELECT t FROM TDivergence t WHERE t.divNatureContestAutre = :divNatureContestAutre")
    , @NamedQuery(name = "TDivergence.findByDivPhotoSignature", query = "SELECT t FROM TDivergence t WHERE t.divPhotoSignature = :divPhotoSignature")
    , @NamedQuery(name = "TDivergence.findByDivNomPhoto", query = "SELECT t FROM TDivergence t WHERE t.divNomPhoto = :divNomPhoto")
    , @NamedQuery(name = "TDivergence.findByDivDateChargement", query = "SELECT t FROM TDivergence t WHERE t.divDateChargement = :divDateChargement")
    , @NamedQuery(name = "TDivergence.findByDivTyreAutre", query = "SELECT t FROM TDivergence t WHERE t.divTyreAutre = :divTyreAutre")})
public class TDivergence implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "div_code")
    private String divCode;
    @Size(max = 200)
    @Column(name = "div_origin_droit")
    private String divOriginDroit;
    @Column(name = "div_duree_occup")
    private Long divDureeOccup;
    @Size(max = 2147483647)
    @Column(name = "div_nature_contest")
    private String divNatureContest;
    @Size(max = 200)
    @Column(name = "div_roc_autre")
    private String divRocAutre;
    @Size(max = 200)
    @Column(name = "div_origin_droit_autre")
    private String divOriginDroitAutre;
    @Size(max = 200)
    @Column(name = "div_nature_contest_autre")
    private String divNatureContestAutre;
    @Size(max = 200)
    @Column(name = "div_photo_signature")
    private String divPhotoSignature;
    @Size(max = 200)
    @Column(name = "div_nom_photo")
    private String divNomPhoto;
    @Column(name = "div_date_chargement")
    @Temporal(TemporalType.DATE)
    private Date divDateChargement;
    @Size(max = 200)
    @Column(name = "div_tyre_autre")
    private String divTyreAutre;
    @JoinColumn(name = "div_conf_num", referencedColumnName = "conf_num")
    @ManyToOne(optional = false)
    private TConflit divConfNum;
    @JoinColumn(name = "div_int_numero", referencedColumnName = "int_numero")
    @ManyToOne
    private TIntervenant divIntNumero;
    @JoinColumn(name = "div_roc_code", referencedColumnName = "roc_code")
    @ManyToOne
    private TRolec divRocCode;
    @JoinColumn(name = "div_tyre_code", referencedColumnName = "tyre_code")
    @ManyToOne
    private TTypedrevendiq divTyreCode;

    public TDivergence() {
    }

    public TDivergence(String divCode) {
        this.divCode = divCode;
    }

    public String getDivCode() {
        return divCode;
    }

    public void setDivCode(String divCode) {
        this.divCode = divCode;
    }

    public String getDivOriginDroit() {
        return divOriginDroit;
    }

    public void setDivOriginDroit(String divOriginDroit) {
        this.divOriginDroit = divOriginDroit;
    }

    public Long getDivDureeOccup() {
        return divDureeOccup;
    }

    public void setDivDureeOccup(Long divDureeOccup) {
        this.divDureeOccup = divDureeOccup;
    }

    public String getDivNatureContest() {
        return divNatureContest;
    }

    public void setDivNatureContest(String divNatureContest) {
        this.divNatureContest = divNatureContest;
    }

    public String getDivRocAutre() {
        return divRocAutre;
    }

    public void setDivRocAutre(String divRocAutre) {
        this.divRocAutre = divRocAutre;
    }

    public String getDivOriginDroitAutre() {
        return divOriginDroitAutre;
    }

    public void setDivOriginDroitAutre(String divOriginDroitAutre) {
        this.divOriginDroitAutre = divOriginDroitAutre;
    }

    public String getDivNatureContestAutre() {
        return divNatureContestAutre;
    }

    public void setDivNatureContestAutre(String divNatureContestAutre) {
        this.divNatureContestAutre = divNatureContestAutre;
    }

    public String getDivPhotoSignature() {
        return divPhotoSignature;
    }

    public void setDivPhotoSignature(String divPhotoSignature) {
        this.divPhotoSignature = divPhotoSignature;
    }

    public String getDivNomPhoto() {
        return divNomPhoto;
    }

    public void setDivNomPhoto(String divNomPhoto) {
        this.divNomPhoto = divNomPhoto;
    }

    public Date getDivDateChargement() {
        return divDateChargement;
    }

    public void setDivDateChargement(Date divDateChargement) {
        this.divDateChargement = divDateChargement;
    }

    public String getDivTyreAutre() {
        return divTyreAutre;
    }

    public void setDivTyreAutre(String divTyreAutre) {
        this.divTyreAutre = divTyreAutre;
    }

    public TConflit getDivConfNum() {
        return divConfNum;
    }

    public void setDivConfNum(TConflit divConfNum) {
        this.divConfNum = divConfNum;
    }

    public TIntervenant getDivIntNumero() {
        return divIntNumero;
    }

    public void setDivIntNumero(TIntervenant divIntNumero) {
        this.divIntNumero = divIntNumero;
    }

    public TRolec getDivRocCode() {
        return divRocCode;
    }

    public void setDivRocCode(TRolec divRocCode) {
        this.divRocCode = divRocCode;
    }

    public TTypedrevendiq getDivTyreCode() {
        return divTyreCode;
    }

    public void setDivTyreCode(TTypedrevendiq divTyreCode) {
        this.divTyreCode = divTyreCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (divCode != null ? divCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TDivergence)) {
            return false;
        }
        TDivergence other = (TDivergence) object;
        if ((this.divCode == null && other.divCode != null) || (this.divCode != null && !this.divCode.equals(other.divCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TDivergence[ divCode=" + divCode + " ]";
    }
    
}
