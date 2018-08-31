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
@Table(name = "t_pv_parcelle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TPvParcelle.findAll", query = "SELECT t FROM TPvParcelle t")
    , @NamedQuery(name = "TPvParcelle.findByPvNumero", query = "SELECT t FROM TPvParcelle t WHERE t.pvNumero = :pvNumero")
    , @NamedQuery(name = "TPvParcelle.findByPvNomImageDet", query = "SELECT t FROM TPvParcelle t WHERE t.pvNomImageDet = :pvNomImageDet")
    , @NamedQuery(name = "TPvParcelle.findByPvDateDeb", query = "SELECT t FROM TPvParcelle t WHERE t.pvDateDeb = :pvDateDeb")
    , @NamedQuery(name = "TPvParcelle.findByPvDateFin", query = "SELECT t FROM TPvParcelle t WHERE t.pvDateFin = :pvDateFin")
    , @NamedQuery(name = "TPvParcelle.findByPvPv", query = "SELECT t FROM TPvParcelle t WHERE t.pvPv = :pvPv")
    , @NamedQuery(name = "TPvParcelle.findByPvNomImageEnq", query = "SELECT t FROM TPvParcelle t WHERE t.pvNomImageEnq = :pvNomImageEnq")
    , @NamedQuery(name = "TPvParcelle.findByPvNomImageGeo", query = "SELECT t FROM TPvParcelle t WHERE t.pvNomImageGeo = :pvNomImageGeo")
    , @NamedQuery(name = "TPvParcelle.findByPvPvEntete", query = "SELECT t FROM TPvParcelle t WHERE t.pvPvEntete = :pvPvEntete")
    , @NamedQuery(name = "TPvParcelle.findByPvEnqCode", query = "SELECT t FROM TPvParcelle t WHERE t.pvEnqCode = :pvEnqCode")
    , @NamedQuery(name = "TPvParcelle.findByPvDateChargement", query = "SELECT t FROM TPvParcelle t WHERE t.pvDateChargement = :pvDateChargement")})
public class TPvParcelle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "pv_numero")
    private String pvNumero;
    @Size(max = 200)
    @Column(name = "pv_nom_image_det")
    private String pvNomImageDet;
    @Column(name = "pv_date_deb")
    @Temporal(TemporalType.DATE)
    private Date pvDateDeb;
    @Column(name = "pv_date_fin")
    @Temporal(TemporalType.DATE)
    private Date pvDateFin;
    @Size(max = 40000)
    @Column(name = "pv_pv")
    private String pvPv;
    @Size(max = 200)
    @Column(name = "pv_nom_image_enq")
    private String pvNomImageEnq;
    @Size(max = 200)
    @Column(name = "pv_nom_image_geo")
    private String pvNomImageGeo;
    @Size(max = 1000)
    @Column(name = "pv_pv_entete")
    private String pvPvEntete;
    @Size(max = 50)
    @Column(name = "pv_enq_code")
    private String pvEnqCode;
    @Column(name = "pv_date_chargement")
    @Temporal(TemporalType.DATE)
    private Date pvDateChargement;
    @JoinColumn(name = "pv_int_numero", referencedColumnName = "int_numero")
    @ManyToOne(optional = false)
    private TIntervenant pvIntNumero;
    @JoinColumn(name = "pv_pba_numero", referencedColumnName = "pba_numero")
    @ManyToOne(optional = false)
    private TParcelleBafon pvPbaNumero;
    @JoinColumn(name = "pv_uti_code_enq", referencedColumnName = "uti_code")
    @ManyToOne
    private TUtilisateur pvUtiCodeEnq;
    @JoinColumn(name = "pv_uti_code_geo", referencedColumnName = "uti_code")
    @ManyToOne
    private TUtilisateur pvUtiCodeGeo;

    public TPvParcelle() {
    }

    public TPvParcelle(String pvNumero) {
        this.pvNumero = pvNumero;
    }

    public String getPvNumero() {
        return pvNumero;
    }

    public void setPvNumero(String pvNumero) {
        this.pvNumero = pvNumero;
    }

    public String getPvNomImageDet() {
        return pvNomImageDet;
    }

    public void setPvNomImageDet(String pvNomImageDet) {
        this.pvNomImageDet = pvNomImageDet;
    }

    public Date getPvDateDeb() {
        return pvDateDeb;
    }

    public void setPvDateDeb(Date pvDateDeb) {
        this.pvDateDeb = pvDateDeb;
    }

    public Date getPvDateFin() {
        return pvDateFin;
    }

    public void setPvDateFin(Date pvDateFin) {
        this.pvDateFin = pvDateFin;
    }

    public String getPvPv() {
        return pvPv;
    }

    public void setPvPv(String pvPv) {
        this.pvPv = pvPv;
    }

    public String getPvNomImageEnq() {
        return pvNomImageEnq;
    }

    public void setPvNomImageEnq(String pvNomImageEnq) {
        this.pvNomImageEnq = pvNomImageEnq;
    }

    public String getPvNomImageGeo() {
        return pvNomImageGeo;
    }

    public void setPvNomImageGeo(String pvNomImageGeo) {
        this.pvNomImageGeo = pvNomImageGeo;
    }

    public String getPvPvEntete() {
        return pvPvEntete;
    }

    public void setPvPvEntete(String pvPvEntete) {
        this.pvPvEntete = pvPvEntete;
    }

    public String getPvEnqCode() {
        return pvEnqCode;
    }

    public void setPvEnqCode(String pvEnqCode) {
        this.pvEnqCode = pvEnqCode;
    }

    public Date getPvDateChargement() {
        return pvDateChargement;
    }

    public void setPvDateChargement(Date pvDateChargement) {
        this.pvDateChargement = pvDateChargement;
    }

    public TIntervenant getPvIntNumero() {
        return pvIntNumero;
    }

    public void setPvIntNumero(TIntervenant pvIntNumero) {
        this.pvIntNumero = pvIntNumero;
    }

    public TParcelleBafon getPvPbaNumero() {
        return pvPbaNumero;
    }

    public void setPvPbaNumero(TParcelleBafon pvPbaNumero) {
        this.pvPbaNumero = pvPbaNumero;
    }

    public TUtilisateur getPvUtiCodeEnq() {
        return pvUtiCodeEnq;
    }

    public void setPvUtiCodeEnq(TUtilisateur pvUtiCodeEnq) {
        this.pvUtiCodeEnq = pvUtiCodeEnq;
    }

    public TUtilisateur getPvUtiCodeGeo() {
        return pvUtiCodeGeo;
    }

    public void setPvUtiCodeGeo(TUtilisateur pvUtiCodeGeo) {
        this.pvUtiCodeGeo = pvUtiCodeGeo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pvNumero != null ? pvNumero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TPvParcelle)) {
            return false;
        }
        TPvParcelle other = (TPvParcelle) object;
        if ((this.pvNumero == null && other.pvNumero != null) || (this.pvNumero != null && !this.pvNumero.equals(other.pvNumero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TPvParcelle[ pvNumero=" + pvNumero + " ]";
    }
    
}
