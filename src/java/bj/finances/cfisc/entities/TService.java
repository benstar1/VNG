/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JOHNSON
 */
@Entity
@Table(name = "T_SERVICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TService.findAll", query = "SELECT t FROM TService t"),
    @NamedQuery(name = "TService.findByCode", query = "SELECT t FROM TService t WHERE t.code = :code"),
    @NamedQuery(name = "TService.findByLibelle", query = "SELECT t FROM TService t WHERE t.libelle = :libelle"),
    @NamedQuery(name = "TService.findByDirection", query = "SELECT t FROM TService t WHERE t.direction = :direction")})
public class TService implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODE")
    private String code;
    @Size(max = 50)
    @Column(name = "LIBELLE")
    private String libelle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "DIRECTION")
    private String direction;
    @JoinColumn(name = "CODE", referencedColumnName = "CODE", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TDirection tDirection;

    public TService() {
    }

    public TService(String code) {
        this.code = code;
    }

    public TService(String code, String direction) {
        this.code = code;
        this.direction = direction;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public TDirection getTDirection() {
        return tDirection;
    }

    public void setTDirection(TDirection tDirection) {
        this.tDirection = tDirection;
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
        if (!(object instanceof TService)) {
            return false;
        }
        TService other = (TService) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TService[ code=" + code + " ]";
    }
    
}
