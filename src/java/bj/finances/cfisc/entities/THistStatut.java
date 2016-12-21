/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
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
@Table(name = "T_HIST_STATUT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "THistStatut.findAll", query = "SELECT t FROM THistStatut t")
    ,
    @NamedQuery(name = "THistStatut.findByHistStatutCode", query = "SELECT t FROM THistStatut t WHERE t.histStatutCode = :histStatutCode")
    ,
    @NamedQuery(name = "THistStatut.findByHistStatutDatedebut", query = "SELECT t FROM THistStatut t WHERE t.histStatutDatedebut = :histStatutDatedebut")
    ,
    @NamedQuery(name = "THistStatut.findByHistStatutDatefin", query = "SELECT t FROM THistStatut t WHERE t.histStatutDatefin = :histStatutDatefin")
    ,
    @NamedQuery(name = "THistStatut.findByHistStatutLast", query = "SELECT t FROM THistStatut t WHERE t.histStatutDatefin IS NULL AND t.histStatutContImmatr = :histStatutContImmatr")
    ,
    @NamedQuery(name = "THistStatut.findByHistStatutStatut", query = "SELECT t FROM THistStatut t WHERE t.histStatutStatut = :histStatutStatut")
    ,
//    @NamedQuery(name = "THistStatut.updateTHistStatu", query = "UPDATE  THistStatut t SET t.histStatutCode= :histStatutCode, t.histStatutContImmatr =:histStatutContImmatr, t.histStatutDatedebut= :histStatutDatedebut, t.histStatutDatefin= :histStatutDatefin, t.histStatutStatut= :histStatutStatut, t.histStatutUtilLogin = :histStatutUtilLogin WHERE t.histStatutContImmatr= :histStatutContImmatr"),
    @NamedQuery(name = "THistStatut.findByHistStatutContImmatr", query = "SELECT t FROM THistStatut t WHERE t.histStatutContImmatr = :histStatutContImmatr")})
@SequenceGenerator(name="thiststatutSequence", initialValue=1, allocationSize=1)
public class THistStatut implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "HIST_STATUT_CODE")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="thiststatutSequence")
    private Long histStatutCode;
    @Column(name = "HIST_STATUT_DATEDEBUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date histStatutDatedebut;
    @Column(name = "HIST_STATUT_DATEFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date histStatutDatefin;
    @Size(max = 1)
    @Column(name = "HIST_STATUT_STATUT")
    private String histStatutStatut;
    @JoinColumn(name = "HIST_CENTR_IMP_CODE", referencedColumnName = "CENTR_IMP_CODE")
    @ManyToOne(optional = false)
    private TCentreImpot histCentrImpCode;
    @JoinColumn(name = "HIST_STATUT_UTIL_LOGIN", referencedColumnName = "UTIL_LOGIN")
    @ManyToOne(optional = false)
    private TUtilisateur histStatutUtilLogin;
    @JoinColumn(name = "HIST_STATUT_CONT_IMMATR", referencedColumnName = "CONT_IMMATR")
    @ManyToOne
    private TRepUnique histStatutContImmatr;

    public THistStatut() {
    }


    public THistStatut(TRepUnique tRepUnique, TUtilisateur tUtilisateur) {
//        long randValue = randnum.nextLong();
        //this.histStatutCode = 1L;
        this.histStatutContImmatr = tRepUnique;
        this.histStatutDatedebut = new Date();
        this.histCentrImpCode = tRepUnique.getContCentrImpCode();
        this.histStatutStatut = tRepUnique.getContStatut();
        this.histStatutUtilLogin = tUtilisateur;
    }
  
    public THistStatut(Long histStatutCode) {
        this.histStatutCode = histStatutCode;
    }

    public Long getHistStatutCode() {
        return histStatutCode;
    }

    public void setHistStatutCode(Long histStatutCode) {
        this.histStatutCode = histStatutCode;
    }

    public Date getHistStatutDatedebut() {
        return histStatutDatedebut;
    }

    public void setHistStatutDatedebut(Date histStatutDatedebut) {
        this.histStatutDatedebut = histStatutDatedebut;
    }

    public Date getHistStatutDatefin() {
        return histStatutDatefin;
    }

    public void setHistStatutDatefin(Date histStatutDatefin) {
        this.histStatutDatefin = histStatutDatefin;
    }

    public String getHistStatutStatut() {
        return histStatutStatut;
    }

    public void setHistStatutStatut(String histStatutStatut) {
        this.histStatutStatut = histStatutStatut;
    }

        public TCentreImpot getHistCentrImpCode() {
        return histCentrImpCode;
    }

    public void setHistCentrImpCode(TCentreImpot histCentrImpCode) {
        this.histCentrImpCode = histCentrImpCode;
    }

    public TUtilisateur getHistStatutUtilLogin() {
        return histStatutUtilLogin;
    }

    public void setHistStatutUtilLogin(TUtilisateur histStatutUtilLogin) {
        this.histStatutUtilLogin = histStatutUtilLogin;
    }

    public TRepUnique getHistStatutContImmatr() {
        return histStatutContImmatr;
    }

    public void setHistStatutContImmatr(TRepUnique histStatutContImmatr) {
        this.histStatutContImmatr = histStatutContImmatr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (histStatutCode != null ? histStatutCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof THistStatut)) {
            return false;
        }
        THistStatut other = (THistStatut) object;
        if ((this.histStatutCode == null && other.histStatutCode != null) || (this.histStatutCode != null && !this.histStatutCode.equals(other.histStatutCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.THistStatut[ histStatutCode=" + histStatutCode + " ]";
    }

}
