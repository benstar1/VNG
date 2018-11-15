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
@Table(name = "t_conflit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TConflit.findAll", query = "SELECT t FROM TConflit t")
    , @NamedQuery(name = "TConflit.findByConfNum", query = "SELECT t FROM TConflit t WHERE t.confNum = :confNum")
    , @NamedQuery(name = "TConflit.findByConfNatCompromi", query = "SELECT t FROM TConflit t WHERE t.confNatCompromi = :confNatCompromi")
    , @NamedQuery(name = "TConflit.findByConfModeRegle", query = "SELECT t FROM TConflit t WHERE t.confModeRegle = :confModeRegle")
    , @NamedQuery(name = "TConflit.findByConfDateRegl", query = "SELECT t FROM TConflit t WHERE t.confDateRegl = :confDateRegl")
    , @NamedQuery(name = "TConflit.findByConfDate", query = "SELECT t FROM TConflit t WHERE t.confDate = :confDate")
    , @NamedQuery(name = "TConflit.findByConfActeurRegl", query = "SELECT t FROM TConflit t WHERE t.confActeurRegl = :confActeurRegl")
    , @NamedQuery(name = "TConflit.findByConfTemoinRegl", query = "SELECT t FROM TConflit t WHERE t.confTemoinRegl = :confTemoinRegl")
    , @NamedQuery(name = "TConflit.findByDivTyreAutre", query = "SELECT t FROM TConflit t WHERE t.divTyreAutre = :divTyreAutre")
    , @NamedQuery(name = "TConflit.findByDivOriginDroitAutre", query = "SELECT t FROM TConflit t WHERE t.divOriginDroitAutre = :divOriginDroitAutre")
    , @NamedQuery(name = "TConflit.findByDivNatureContestAutre", query = "SELECT t FROM TConflit t WHERE t.divNatureContestAutre = :divNatureContestAutre")
    , @NamedQuery(name = "TConflit.findByConfEncoursRegl", query = "SELECT t FROM TConflit t WHERE t.confEncoursRegl = :confEncoursRegl")
    , @NamedQuery(name = "TConflit.findByConfObservation", query = "SELECT t FROM TConflit t WHERE t.confObservation = :confObservation")
    , @NamedQuery(name = "TConflit.findMaxConflit", query = "SELECT Max(t.confNum) FROM TConflit t WHERE t.confNum LIKE :numeroParcel")
    , @NamedQuery(name = "TConflit.findByConfDateChargement", query = "SELECT t FROM TConflit t WHERE t.confDateChargement = :confDateChargement")})
public class TConflit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "conf_num")
    private String confNum;
    @Size(max = 5000)
    @Column(name = "conf_nat_compromi")
    private String confNatCompromi;
    @Size(max = 200)
    @Column(name = "conf_mode_regle")
    private String confModeRegle;
    @Column(name = "conf_date_regl")
    @Temporal(TemporalType.DATE)
    private Date confDateRegl;
    @Column(name = "conf_date")
    @Temporal(TemporalType.DATE)
    private Date confDate;
    @Size(max = 2147483647)
    @Column(name = "conf_acteur_regl")
    private String confActeurRegl;
    @Size(max = 2147483647)
    @Column(name = "conf_temoin_regl")
    private String confTemoinRegl;
    @Size(max = 200)
    @Column(name = "div_tyre_autre")
    private String divTyreAutre;
    @Size(max = 200)
    @Column(name = "div_origin_droit_autre")
    private String divOriginDroitAutre;
    @Size(max = 200)
    @Column(name = "div_nature_contest_autre")
    private String divNatureContestAutre;
    @Size(max = 50)
    @Column(name = "conf_encours_regl")
    private String confEncoursRegl;
    @Size(max = 4000)
    @Column(name = "conf_observation")
    private String confObservation;
    @Column(name = "conf_date_chargement")
    @Temporal(TemporalType.DATE)
    private Date confDateChargement;
    @JoinColumn(name = "conf_pba_numero", referencedColumnName = "pba_numero")
    @ManyToOne(optional = false)
    private TParcelleBafon confPbaNumero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "divConfNum")
    private List<TDivergence> tDivergenceList;

    public TConflit() {
    }

    public TConflit(String confNum) {
        this.confNum = confNum;
    }

    public String getConfNum() {
        return confNum;
    }

    public void setConfNum(String confNum) {
        this.confNum = confNum;
    }

    public String getConfNatCompromi() {
        return confNatCompromi;
    }

    public void setConfNatCompromi(String confNatCompromi) {
        this.confNatCompromi = confNatCompromi;
    }

    public String getConfModeRegle() {
        return confModeRegle;
    }

    public void setConfModeRegle(String confModeRegle) {
        this.confModeRegle = confModeRegle;
    }

    public Date getConfDateRegl() {
        return confDateRegl;
    }

    public void setConfDateRegl(Date confDateRegl) {
        this.confDateRegl = confDateRegl;
    }

    public Date getConfDate() {
        return confDate;
    }

    public void setConfDate(Date confDate) {
        this.confDate = confDate;
    }

    public String getConfActeurRegl() {
        return confActeurRegl;
    }

    public void setConfActeurRegl(String confActeurRegl) {
        this.confActeurRegl = confActeurRegl;
    }

    public String getConfTemoinRegl() {
        return confTemoinRegl;
    }

    public void setConfTemoinRegl(String confTemoinRegl) {
        this.confTemoinRegl = confTemoinRegl;
    }

    public String getDivTyreAutre() {
        return divTyreAutre;
    }

    public void setDivTyreAutre(String divTyreAutre) {
        this.divTyreAutre = divTyreAutre;
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

    public String getConfEncoursRegl() {
        return confEncoursRegl;
    }

    public void setConfEncoursRegl(String confEncoursRegl) {
        this.confEncoursRegl = confEncoursRegl;
    }

    public String getConfObservation() {
        return confObservation;
    }

    public void setConfObservation(String confObservation) {
        this.confObservation = confObservation;
    }

    public Date getConfDateChargement() {
        return confDateChargement;
    }

    public void setConfDateChargement(Date confDateChargement) {
        this.confDateChargement = confDateChargement;
    }

    public TParcelleBafon getConfPbaNumero() {
        return confPbaNumero;
    }

    public void setConfPbaNumero(TParcelleBafon confPbaNumero) {
        this.confPbaNumero = confPbaNumero;
    }

    @XmlTransient
    public List<TDivergence> getTDivergenceList() {
        return tDivergenceList;
    }

    public void setTDivergenceList(List<TDivergence> tDivergenceList) {
        this.tDivergenceList = tDivergenceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (confNum != null ? confNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TConflit)) {
            return false;
        }
        TConflit other = (TConflit) object;
        if ((this.confNum == null && other.confNum != null) || (this.confNum != null && !this.confNum.equals(other.confNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TConflit[ confNum=" + confNum + " ]";
    }
    
}
