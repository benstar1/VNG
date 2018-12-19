/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.entities;

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
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author AAKAKPO
 */
@Entity
@Table(name = "t_interprete")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TInterprete.findAll", query = "SELECT t FROM TInterprete t")
    , @NamedQuery(name = "TInterprete.findByIntpCode", query = "SELECT t FROM TInterprete t WHERE t.intpCode = :intpCode")
    , @NamedQuery(name = "TInterprete.findByIntpNom", query = "SELECT t FROM TInterprete t WHERE t.intpNom = :intpNom")
    , @NamedQuery(name = "TInterprete.findByIntpPrenom", query = "SELECT t FROM TInterprete t WHERE t.intpPrenom = :intpPrenom")
    , @NamedQuery(name = "TInterprete.findByIntpActif", query = "SELECT t FROM TInterprete t WHERE t.intpActif = :intpActif")})
public class TInterprete implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "intp_code")
    private String intpCode;
    @Size(max = 100)
    @Column(name = "intp_nom")
    private String intpNom;
    @Size(max = 100)
    @Column(name = "intp_prenom")
    private String intpPrenom;
    @Column(name = "intp_actif")
    private Boolean intpActif;
    @OneToMany(mappedBy = "opvIntpCode")
    private List<TOperationParcel> tOperationParcelList;

    public TInterprete() {
    }

    public TInterprete(String intpCode) {
        this.intpCode = intpCode;
    }

    public String getIntpCode() {
        return intpCode;
    }

    public void setIntpCode(String intpCode) {
        this.intpCode = intpCode;
    }

    public String getIntpNom() {
        return intpNom;
    }

    public void setIntpNom(String intpNom) {
        this.intpNom = intpNom;
    }

    public String getIntpPrenom() {
        return intpPrenom;
    }

    public void setIntpPrenom(String intpPrenom) {
        this.intpPrenom = intpPrenom;
    }

    public Boolean getIntpActif() {
        return intpActif;
    }

    public void setIntpActif(Boolean intpActif) {
        this.intpActif = intpActif;
    }

    @XmlTransient
    @JsonIgnore
    public List<TOperationParcel> getTOperationParcelList() {
        return tOperationParcelList;
    }

    public void setTOperationParcelList(List<TOperationParcel> tOperationParcelList) {
        this.tOperationParcelList = tOperationParcelList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (intpCode != null ? intpCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TInterprete)) {
            return false;
        }
        TInterprete other = (TInterprete) object;
        if ((this.intpCode == null && other.intpCode != null) || (this.intpCode != null && !this.intpCode.equals(other.intpCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TInterprete[ intpCode=" + intpCode + " ]";
    }
    
}
