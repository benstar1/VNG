/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ben
 */
@Entity
@Table(name = "t_participer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TParticiper.findAll", query = "SELECT t FROM TParticiper t")
    , @NamedQuery(name = "TParticiper.findByParOpvNumero", query = "SELECT t FROM TParticiper t WHERE t.tParticiperPK.parOpvNumero = :parOpvNumero")
    , @NamedQuery(name = "TParticiper.findByParRovCode", query = "SELECT t FROM TParticiper t WHERE t.tParticiperPK.parRovCode = :parRovCode")
    , @NamedQuery(name = "TParticiper.findByParIntNumero", query = "SELECT t FROM TParticiper t WHERE t.tParticiperPK.parIntNumero = :parIntNumero")
    , @NamedQuery(name = "TParticiper.findByParNumsignature", query = "SELECT t FROM TParticiper t WHERE t.parNumsignature = :parNumsignature")
    , @NamedQuery(name = "TParticiper.findByParDatesignature", query = "SELECT t FROM TParticiper t WHERE t.parDatesignature = :parDatesignature")})
public class TParticiper implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TParticiperPK tParticiperPK;
    @Size(max = 12)
    @Column(name = "par_numsignature")
    private String parNumsignature;
    @Column(name = "par_datesignature")
    @Temporal(TemporalType.DATE)
    private Date parDatesignature;
    @JoinColumn(name = "par_int_numero", referencedColumnName = "int_numero", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TIntervenant tIntervenant;
    @JoinColumn(name = "par_opv_numero", referencedColumnName = "opv_numero", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TOperationParcel tOperationParcel;
    @JoinColumn(name = "par_rov_code", referencedColumnName = "rov_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TRolepart tRolepart;

    public TParticiper() {
    }

    public TParticiper(TParticiperPK tParticiperPK) {
        this.tParticiperPK = tParticiperPK;
    }

    public TParticiper(String parOpvNumero, String parRovCode, String parIntNumero) {
        this.tParticiperPK = new TParticiperPK(parOpvNumero, parRovCode, parIntNumero);
    }

    public TParticiperPK getTParticiperPK() {
        return tParticiperPK;
    }

    public void setTParticiperPK(TParticiperPK tParticiperPK) {
        this.tParticiperPK = tParticiperPK;
    }

    public String getParNumsignature() {
        return parNumsignature;
    }

    public void setParNumsignature(String parNumsignature) {
        this.parNumsignature = parNumsignature;
    }

    public Date getParDatesignature() {
        return parDatesignature;
    }

    public void setParDatesignature(Date parDatesignature) {
        this.parDatesignature = parDatesignature;
    }

    public TIntervenant getTIntervenant() {
        return tIntervenant;
    }

    public void setTIntervenant(TIntervenant tIntervenant) {
        this.tIntervenant = tIntervenant;
    }

    public TOperationParcel getTOperationParcel() {
        return tOperationParcel;
    }

    public void setTOperationParcel(TOperationParcel tOperationParcel) {
        this.tOperationParcel = tOperationParcel;
    }

    public TRolepart getTRolepart() {
        return tRolepart;
    }

    public void setTRolepart(TRolepart tRolepart) {
        this.tRolepart = tRolepart;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tParticiperPK != null ? tParticiperPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TParticiper)) {
            return false;
        }
        TParticiper other = (TParticiper) object;
        if ((this.tParticiperPK == null && other.tParticiperPK != null) || (this.tParticiperPK != null && !this.tParticiperPK.equals(other.tParticiperPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TParticiper[ tParticiperPK=" + tParticiperPK + " ]";
    }
    
}
