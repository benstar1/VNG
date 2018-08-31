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
@Table(name = "t_enquete")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TEnquete.findAll", query = "SELECT t FROM TEnquete t")
    , @NamedQuery(name = "TEnquete.findByEnqCode", query = "SELECT t FROM TEnquete t WHERE t.enqCode = :enqCode")
    , @NamedQuery(name = "TEnquete.findByEnqDesig", query = "SELECT t FROM TEnquete t WHERE t.enqDesig = :enqDesig")
    , @NamedQuery(name = "TEnquete.findByEnqDateDebut", query = "SELECT t FROM TEnquete t WHERE t.enqDateDebut = :enqDateDebut")
    , @NamedQuery(name = "TEnquete.findByEnqDateFin", query = "SELECT t FROM TEnquete t WHERE t.enqDateFin = :enqDateFin")})
public class TEnquete implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "enq_code")
    private String enqCode;
    @Size(max = 500)
    @Column(name = "enq_desig")
    private String enqDesig;
    @Column(name = "enq_date_debut")
    @Temporal(TemporalType.DATE)
    private Date enqDateDebut;
    @Column(name = "enq_date_fin")
    @Temporal(TemporalType.DATE)
    private Date enqDateFin;
    @OneToMany(mappedBy = "dreEnqCode")
    private List<TDroitExerce> tDroitExerceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "affEnqCode")
    private List<TAffecter> tAffecterList;
    @OneToMany(mappedBy = "invEnqCode")
    private List<TIntervenir> tIntervenirList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tEnquete")
    private List<TEnqueter> tEnqueterList;

    public TEnquete() {
    }

    public TEnquete(String enqCode) {
        this.enqCode = enqCode;
    }

    public String getEnqCode() {
        return enqCode;
    }

    public void setEnqCode(String enqCode) {
        this.enqCode = enqCode;
    }

    public String getEnqDesig() {
        return enqDesig;
    }

    public void setEnqDesig(String enqDesig) {
        this.enqDesig = enqDesig;
    }

    public Date getEnqDateDebut() {
        return enqDateDebut;
    }

    public void setEnqDateDebut(Date enqDateDebut) {
        this.enqDateDebut = enqDateDebut;
    }

    public Date getEnqDateFin() {
        return enqDateFin;
    }

    public void setEnqDateFin(Date enqDateFin) {
        this.enqDateFin = enqDateFin;
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
        hash += (enqCode != null ? enqCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TEnquete)) {
            return false;
        }
        TEnquete other = (TEnquete) object;
        if ((this.enqCode == null && other.enqCode != null) || (this.enqCode != null && !this.enqCode.equals(other.enqCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TEnquete[ enqCode=" + enqCode + " ]";
    }
    
}
