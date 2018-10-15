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
 * @author Ben
 */
@Entity
@Table(name = "t_droit_exerce")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TDroitExerce.findAll", query = "SELECT t FROM TDroitExerce t")
    , @NamedQuery(name = "TDroitExerce.findByDreCode", query = "SELECT t FROM TDroitExerce t WHERE t.dreCode = :dreCode")
    , @NamedQuery(name = "TDroitExerce.findByDreDateDebut", query = "SELECT t FROM TDroitExerce t WHERE t.dreDateDebut = :dreDateDebut")
    , @NamedQuery(name = "TDroitExerce.findByDreDateFin", query = "SELECT t FROM TDroitExerce t WHERE t.dreDateFin = :dreDateFin")
    , @NamedQuery(name = "TDroitExerce.findByDreCat", query = "SELECT t FROM TDroitExerce t WHERE t.dreCat = :dreCat")
    , @NamedQuery(name = "TDroitExerce.findByDreDateEnquete", query = "SELECT t FROM TDroitExerce t WHERE t.dreDateEnquete = :dreDateEnquete")
    , @NamedQuery(name = "TDroitExerce.findByDreRolAutre", query = "SELECT t FROM TDroitExerce t WHERE t.dreRolAutre = :dreRolAutre")
    , @NamedQuery(name = "TDroitExerce.findByParcelleCat", query = "SELECT t FROM TDroitExerce t WHERE t.dreDateFin is null AND t.drePbaNumero = :parcelle AND t.dreCat = :catDroit")
    , @NamedQuery(name = "TDroitExerce.findByDreTdeAutre", query = "SELECT t FROM TDroitExerce t WHERE t.dreTdeAutre = :dreTdeAutre")
    , @NamedQuery(name = "TDroitExerce.findByDreDateChargement", query = "SELECT t FROM TDroitExerce t WHERE t.dreDateChargement = :dreDateChargement")})
@SequenceGenerator(name="tDroitExerceSequence", initialValue=1, allocationSize=1,sequenceName = "seq_id_droit_exerce")

public class TDroitExerce implements Serializable {

    @JoinColumn(name = "dre_opv_numero_preneur", referencedColumnName = "opv_numero")
    @ManyToOne
    private TOperationParcel dreOpvNumeroPreneur;
    @JoinColumn(name = "dre_opv_numero", referencedColumnName = "opv_numero")
    @ManyToOne
    private TOperationParcel dreOpvNumero;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY,generator = "tDroitExerceSequence")
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "dre_code")
    private String dreCode;
    @Column(name = "dre_date_debut")
    @Temporal(TemporalType.DATE)
    private Date dreDateDebut;
    @Column(name = "dre_date_fin")
    @Temporal(TemporalType.DATE)
    private Date dreDateFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "dre_cat")
    private String dreCat;
    @Column(name = "dre_date_enquete")
    @Temporal(TemporalType.DATE)
    private Date dreDateEnquete;
    @Size(max = 500)
    @Column(name = "dre_rol_autre")
    private String dreRolAutre;
    @Size(max = 500)
    @Column(name = "dre_tde_autre")
    private String dreTdeAutre;
    @Column(name = "dre_date_chargement")
    @Temporal(TemporalType.DATE)
    private Date dreDateChargement;
    @JoinColumn(name = "dre_enq_code", referencedColumnName = "enq_code")
    @ManyToOne
    private TEnquete dreEnqCode;
    @JoinColumn(name = "dre_int_numero", referencedColumnName = "int_numero")
    @ManyToOne(optional = false)
    private TIntervenant dreIntNumero;
    @JoinColumn(name = "dre_pba_numero", referencedColumnName = "pba_numero")
    @ManyToOne
    private TParcelleBafon drePbaNumero;
    @JoinColumn(name = "dre_rol_code", referencedColumnName = "rol_code")
    @ManyToOne(optional = false)
    private TRole dreRolCode;
    @JoinColumn(name = "dre_tde_code", referencedColumnName = "tde_code")
    @ManyToOne(optional = false)
    private TTypedexerce dreTdeCode;
    @JoinColumn(name = "dre_uti_code", referencedColumnName = "uti_code")
    @ManyToOne
    private TUtilisateur dreUtiCode;

    public TDroitExerce() {
    }

    public TDroitExerce(String dreCode) {
        this.dreCode = dreCode;
    }

    public TDroitExerce(String dreCode, String dreCat) {
        this.dreCode = dreCode;
        this.dreCat = dreCat;
    }

    public String getDreCode() {
        return dreCode;
    }

    public void setDreCode(String dreCode) {
        this.dreCode = dreCode;
    }

    public Date getDreDateDebut() {
        return dreDateDebut;
    }

    public void setDreDateDebut(Date dreDateDebut) {
        this.dreDateDebut = dreDateDebut;
    }

    public Date getDreDateFin() {
        return dreDateFin;
    }

    public void setDreDateFin(Date dreDateFin) {
        this.dreDateFin = dreDateFin;
    }

    public String getDreCat() {
        return dreCat;
    }

    public void setDreCat(String dreCat) {
        this.dreCat = dreCat;
    }

    public Date getDreDateEnquete() {
        return dreDateEnquete;
    }

    public void setDreDateEnquete(Date dreDateEnquete) {
        this.dreDateEnquete = dreDateEnquete;
    }

    public String getDreRolAutre() {
        return dreRolAutre;
    }

    public void setDreRolAutre(String dreRolAutre) {
        this.dreRolAutre = dreRolAutre;
    }

    public String getDreTdeAutre() {
        return dreTdeAutre;
    }

    public void setDreTdeAutre(String dreTdeAutre) {
        this.dreTdeAutre = dreTdeAutre;
    }

    public Date getDreDateChargement() {
        return dreDateChargement;
    }

    public void setDreDateChargement(Date dreDateChargement) {
        this.dreDateChargement = dreDateChargement;
    }

    public TEnquete getDreEnqCode() {
        return dreEnqCode;
    }

    public void setDreEnqCode(TEnquete dreEnqCode) {
        this.dreEnqCode = dreEnqCode;
    }

    public TIntervenant getDreIntNumero() {
        return dreIntNumero;
    }

    public void setDreIntNumero(TIntervenant dreIntNumero) {
        this.dreIntNumero = dreIntNumero;
    }

    public TParcelleBafon getDrePbaNumero() {
        return drePbaNumero;
    }

    public void setDrePbaNumero(TParcelleBafon drePbaNumero) {
        this.drePbaNumero = drePbaNumero;
    }

    public TRole getDreRolCode() {
        return dreRolCode;
    }

    public void setDreRolCode(TRole dreRolCode) {
        this.dreRolCode = dreRolCode;
    }

    public TTypedexerce getDreTdeCode() {
        return dreTdeCode;
    }

    public void setDreTdeCode(TTypedexerce dreTdeCode) {
        this.dreTdeCode = dreTdeCode;
    }

    public TUtilisateur getDreUtiCode() {
        return dreUtiCode;
    }

    public void setDreUtiCode(TUtilisateur dreUtiCode) {
        this.dreUtiCode = dreUtiCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dreCode != null ? dreCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TDroitExerce)) {
            return false;
        }
        TDroitExerce other = (TDroitExerce) object;
        if ((this.dreCode == null && other.dreCode != null) || (this.dreCode != null && !this.dreCode.equals(other.dreCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TDroitExerce[ dreCode=" + dreCode + " ]";
    }

    public TOperationParcel getDreOpvNumeroPreneur() {
        return dreOpvNumeroPreneur;
    }

    public void setDreOpvNumeroPreneur(TOperationParcel dreOpvNumeroPreneur) {
        this.dreOpvNumeroPreneur = dreOpvNumeroPreneur;
    }

    public TOperationParcel getDreOpvNumero() {
        return dreOpvNumero;
    }

    public void setDreOpvNumero(TOperationParcel dreOpvNumero) {
        this.dreOpvNumero = dreOpvNumero;
    }
    
}
