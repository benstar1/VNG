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
@Table(name = "t_mutation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMutation.findAll", query = "SELECT t FROM TMutation t")
    , @NamedQuery(name = "TMutation.findByMutNumero", query = "SELECT t FROM TMutation t WHERE t.mutNumero = :mutNumero")
    , @NamedQuery(name = "TMutation.findByMutObjet", query = "SELECT t FROM TMutation t WHERE t.mutObjet = :mutObjet")
    , @NamedQuery(name = "TMutation.findByMutDateMut", query = "SELECT t FROM TMutation t WHERE t.mutDateMut = :mutDateMut")})
public class TMutation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "mut_numero")
    private String mutNumero;
    @Size(max = 500)
    @Column(name = "mut_objet")
    private String mutObjet;
    @Column(name = "mut_date_mut")
    @Temporal(TemporalType.DATE)
    private Date mutDateMut;
    @JoinColumn(name = "mut_opv_numero", referencedColumnName = "opv_numero")
    @ManyToOne(optional = false)
    private TOperationParcel mutOpvNumero;
    @OneToMany(mappedBy = "invMutNumero")
    private List<TIntervenir> tIntervenirList;

    public TMutation() {
    }

    public TMutation(String mutNumero) {
        this.mutNumero = mutNumero;
    }

    public String getMutNumero() {
        return mutNumero;
    }

    public void setMutNumero(String mutNumero) {
        this.mutNumero = mutNumero;
    }

    public String getMutObjet() {
        return mutObjet;
    }

    public void setMutObjet(String mutObjet) {
        this.mutObjet = mutObjet;
    }

    public Date getMutDateMut() {
        return mutDateMut;
    }

    public void setMutDateMut(Date mutDateMut) {
        this.mutDateMut = mutDateMut;
    }

    public TOperationParcel getMutOpvNumero() {
        return mutOpvNumero;
    }

    public void setMutOpvNumero(TOperationParcel mutOpvNumero) {
        this.mutOpvNumero = mutOpvNumero;
        this.setMutObjet(mutOpvNumero.getOpvMacCode().getMacDesig());
    }

    @XmlTransient
    public List<TIntervenir> getTIntervenirList() {
        return tIntervenirList;
    }

    public void setTIntervenirList(List<TIntervenir> tIntervenirList) {
        this.tIntervenirList = tIntervenirList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mutNumero != null ? mutNumero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TMutation)) {
            return false;
        }
        TMutation other = (TMutation) object;
        if ((this.mutNumero == null && other.mutNumero != null) || (this.mutNumero != null && !this.mutNumero.equals(other.mutNumero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TMutation[ mutNumero=" + mutNumero + " ]";
    }
    
}
