/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author HP
 */
@Entity
@Table(name = "T_MOTIF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMotif.findAll", query = "SELECT t FROM TMotif t"),
    @NamedQuery(name = "TMotif.findByMotifCode", query = "SELECT t FROM TMotif t WHERE t.motifCode = :motifCode"),
    @NamedQuery(name = "TMotif.findByMotifLibelle", query = "SELECT t FROM TMotif t WHERE t.motifLibelle = :motifLibelle")})
public class TMotif implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "MOTIF_CODE")
    private String motifCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "MOTIF_LIBELLE")
    private String motifLibelle;
    @OneToMany(mappedBy = "histMotifCode")
    private List<THistorique> tHistoriqueList;

    public TMotif() {
    }

    public TMotif(String motifCode) {
        this.motifCode = motifCode;
    }

    public TMotif(String motifCode, String motifLibelle) {
        this.motifCode = motifCode;
        this.motifLibelle = motifLibelle;
    }

    public String getMotifCode() {
        return motifCode;
    }

    public void setMotifCode(String motifCode) {
        this.motifCode = motifCode;
    }

    public String getMotifLibelle() {
        return motifLibelle;
    }

    public void setMotifLibelle(String motifLibelle) {
        this.motifLibelle = motifLibelle;
    }

    @XmlTransient
    public List<THistorique> getTHistoriqueList() {
        return tHistoriqueList;
    }

    public void setTHistoriqueList(List<THistorique> tHistoriqueList) {
        this.tHistoriqueList = tHistoriqueList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (motifCode != null ? motifCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TMotif)) {
            return false;
        }
        TMotif other = (TMotif) object;
        if ((this.motifCode == null && other.motifCode != null) || (this.motifCode != null && !this.motifCode.equals(other.motifCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TMotif[ motifCode=" + motifCode + " ]";
    }
    
}
