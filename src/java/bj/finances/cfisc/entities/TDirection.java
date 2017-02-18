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
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author JOHNSON
 */
@Entity
@Table(name = "T_DIRECTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TDirection.findAll", query = "SELECT t FROM TDirection t"),
    @NamedQuery(name = "TDirection.findByCode", query = "SELECT t FROM TDirection t WHERE t.code = :code"),
    @NamedQuery(name = "TDirection.findByLibelle", query = "SELECT t FROM TDirection t WHERE t.libelle = :libelle")})
public class TDirection implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODE")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LIBELLE")
    private String libelle;
    @OneToMany(mappedBy = "direction")
    private List<TService> tServiceList;

    public TDirection() {
    }

    public TDirection(String code) {
        this.code = code;
    }

    public TDirection(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @XmlTransient
    @JsonIgnore
    public List<TService> getTServiceList() {
        return tServiceList;
    }

    public void setTServiceList(List<TService> tServiceList) {
        this.tServiceList = tServiceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TDirection)) {
            return false;
        }
        TDirection other = (TDirection) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TDirection[ code=" + code + " ]";
    }
    
}
