/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "T_TYPE_CONTRIB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTypeContrib.findAll", query = "SELECT t FROM TTypeContrib t"),
    @NamedQuery(name = "TTypeContrib.findByTypContCode", query = "SELECT t FROM TTypeContrib t WHERE t.typContCode = 'PME'"),
    @NamedQuery(name = "TTypeContrib.findByTypContLib", query = "SELECT t FROM TTypeContrib t WHERE t.typContLib = :typContLib")})
public class TTypeContrib implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "TYP_CONT_CODE")
    private String typContCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TYP_CONT_LIB")
    private String typContLib;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contTypContCode")
    private List<TRepUnique> tRepUniqueList;

    public TTypeContrib() {
    }

    public TTypeContrib(String typContCode) {
        this.typContCode = typContCode;
    }

    public TTypeContrib(String typContCode, String typContLib) {
        this.typContCode = typContCode;
        this.typContLib = typContLib;
    }

    public String getTypContCode() {
        return typContCode;
    }

    public void setTypContCode(String typContCode) {
        this.typContCode = typContCode;
    }

    public String getTypContLib() {
        return typContLib;
    }

    public void setTypContLib(String typContLib) {
        this.typContLib = typContLib;
    }

    @XmlTransient
    public List<TRepUnique> getTRepUniqueList() {
        return tRepUniqueList;
    }

    public void setTRepUniqueList(List<TRepUnique> tRepUniqueList) {
        this.tRepUniqueList = tRepUniqueList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typContCode != null ? typContCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTypeContrib)) {
            return false;
        }
        TTypeContrib other = (TTypeContrib) object;
        if ((this.typContCode == null && other.typContCode != null) || (this.typContCode != null && !this.typContCode.equals(other.typContCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TTypeContrib[ typContCode=" + typContCode + " ]";
    }
    
}
