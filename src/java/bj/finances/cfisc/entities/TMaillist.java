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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JOHNSON
 */
@Entity
@Table(name = "T_MAILLIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMaillist.findAll", query = "SELECT t FROM TMaillist t"),
    @NamedQuery(name = "TMaillist.findByEmail", query = "SELECT t FROM TMaillist t WHERE t.email = :email"),
    @NamedQuery(name = "TMaillist.findByNom", query = "SELECT t FROM TMaillist t WHERE t.nom = :nom"),
    @NamedQuery(name = "TMaillist.findByPrenoms", query = "SELECT t FROM TMaillist t WHERE t.prenoms = :prenoms"),    
    @NamedQuery(name = "TMaillist.findByGroupMail", query = "SELECT t FROM TMaillist t WHERE t.groupmail = :groupmail")
})
public class TMaillist implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 20)
    @Column(name = "NOM")
    private String nom;
    @Size(max = 50)
    @Column(name = "PRENOMS")
    private String prenoms;
    @JoinColumn(name = "GROUPMAIL", referencedColumnName = "GROUPID")
    @ManyToOne
    private TMailgroup groupmail;

    public TMaillist() {
    }

    public TMaillist(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public TMailgroup getGroupmail() {
        return groupmail;
    }

    public void setGroupmail(TMailgroup groupmail) {
        this.groupmail = groupmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TMaillist)) {
            return false;
        }
        TMaillist other = (TMaillist) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TMaillist[ email=" + email + " ]";
    }
    
}
