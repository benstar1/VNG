/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

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
 * @author HP
 */
@Entity
@Table(name = "T_HIST_STATUT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "THistStatut.findAll", query = "SELECT t FROM THistStatut t"),
    @NamedQuery(name = "THistStatut.findByHistStatutCode", query = "SELECT t FROM THistStatut t WHERE t.histStatutCode = :histStatutCode"),
    @NamedQuery(name = "THistStatut.findByHistStatutDatedebut", query = "SELECT t FROM THistStatut t WHERE t.histStatutDatedebut = :histStatutDatedebut"),
    @NamedQuery(name = "THistStatut.findByHistStatutDatefin", query = "SELECT t FROM THistStatut t WHERE t.histStatutDatefin = :histStatutDatefin"),
    @NamedQuery(name = "THistStatut.findByHistStatutStatut", query = "SELECT t FROM THistStatut t WHERE t.histStatutStatut = :histStatutStatut")})
public class THistStatut implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "HIST_STATUT_CODE")
    private String histStatutCode;
    @Column(name = "HIST_STATUT_DATEDEBUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date histStatutDatedebut;
    @Column(name = "HIST_STATUT_DATEFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date histStatutDatefin;
    @Size(max = 1)
    @Column(name = "HIST_STATUT_STATUT")
    private String histStatutStatut;
    @JoinColumn(name = "HIST_STATUT_UTIL_LOGIN", referencedColumnName = "UTIL_LOGIN")
    @ManyToOne(optional = false)
    private TUtilisateur histStatutUtilLogin;
    @JoinColumn(name = "HIST_STATUT_CONT_IMMATR", referencedColumnName = "CONT_IMMATR")
    @ManyToOne
    private TRepUnique histStatutContImmatr;

    public THistStatut() {
    }

    public THistStatut(String histStatutCode) {
        this.histStatutCode = histStatutCode;
    }

    public String getHistStatutCode() {
        return histStatutCode;
    }

    public void setHistStatutCode(String histStatutCode) {
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
