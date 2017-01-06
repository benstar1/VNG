/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author JOHNSON
 */
@Entity
@Table(name = "T_EXERCICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TExercice.findAll", query = "SELECT t FROM TExercice t"),
    @NamedQuery(name = "TExercice.findByExoAnne", query = "SELECT t FROM TExercice t WHERE t.exoAnne = :exoAnne"),
    @NamedQuery(name = "TExercice.findByExoDatedebut", query = "SELECT t FROM TExercice t WHERE t.exoDatedebut = :exoDatedebut"),
    @NamedQuery(name = "TExercice.findByExoDatefin", query = "SELECT t FROM TExercice t WHERE t.exoDatefin = :exoDatefin")})
public class TExercice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "EXO_ANNE")
    private String exoAnne;
    @Column(name = "EXO_DATEDEBUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exoDatedebut;
    @Column(name = "EXO_DATEFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exoDatefin;
    @OneToMany(mappedBy = "exoAnne")
    private List<TEntDeclaration> tEntDeclarationList;

    public TExercice() {
    }

    public TExercice(String exoAnne) {
        this.exoAnne = exoAnne;
    }

    public String getExoAnne() {
        return exoAnne;
    }

    public void setExoAnne(String exoAnne) {
        this.exoAnne = exoAnne;
    }

    public Date getExoDatedebut() {
        return exoDatedebut;
    }

    public void setExoDatedebut(Date exoDatedebut) {
        this.exoDatedebut = exoDatedebut;
    }

    public Date getExoDatefin() {
        return exoDatefin;
    }

    public void setExoDatefin(Date exoDatefin) {
        this.exoDatefin = exoDatefin;
    }

    @XmlTransient
    @JsonIgnore
    public List<TEntDeclaration> getTEntDeclarationList() {
        return tEntDeclarationList;
    }

    public void setTEntDeclarationList(List<TEntDeclaration> tEntDeclarationList) {
        this.tEntDeclarationList = tEntDeclarationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (exoAnne != null ? exoAnne.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TExercice)) {
            return false;
        }
        TExercice other = (TExercice) object;
        if ((this.exoAnne == null && other.exoAnne != null) || (this.exoAnne != null && !this.exoAnne.equals(other.exoAnne))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TExercice[ exoAnne=" + exoAnne + " ]";
    }
    
}
