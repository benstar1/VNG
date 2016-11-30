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
@Table(name = "T_TYPE_PARTICIPATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTypeParticipation.findAll", query = "SELECT t FROM TTypeParticipation t"),
    @NamedQuery(name = "TTypeParticipation.findByTypPartCode", query = "SELECT t FROM TTypeParticipation t WHERE t.typPartCode = :typPartCode"),
    @NamedQuery(name = "TTypeParticipation.findByTypPartLib", query = "SELECT t FROM TTypeParticipation t WHERE t.typPartLib = :typPartLib")})
public class TTypeParticipation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "TYP_PART_CODE")
    private String typPartCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TYP_PART_LIB")
    private String typPartLib;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partTypPartCode")
    private List<TParticiper> tParticiperList;

    public TTypeParticipation() {
    }

    public TTypeParticipation(String typPartCode) {
        this.typPartCode = typPartCode;
    }

    public TTypeParticipation(String typPartCode, String typPartLib) {
        this.typPartCode = typPartCode;
        this.typPartLib = typPartLib;
    }

    public String getTypPartCode() {
        return typPartCode;
    }

    public void setTypPartCode(String typPartCode) {
        this.typPartCode = typPartCode;
    }

    public String getTypPartLib() {
        return typPartLib;
    }

    public void setTypPartLib(String typPartLib) {
        this.typPartLib = typPartLib;
    }

    @XmlTransient
    public List<TParticiper> getTParticiperList() {
        return tParticiperList;
    }

    public void setTParticiperList(List<TParticiper> tParticiperList) {
        this.tParticiperList = tParticiperList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typPartCode != null ? typPartCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTypeParticipation)) {
            return false;
        }
        TTypeParticipation other = (TTypeParticipation) object;
        if ((this.typPartCode == null && other.typPartCode != null) || (this.typPartCode != null && !this.typPartCode.equals(other.typPartCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TTypeParticipation[ typPartCode=" + typPartCode + " ]";
    }
    
}
