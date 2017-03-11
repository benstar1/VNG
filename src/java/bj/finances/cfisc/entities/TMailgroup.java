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
@Table(name = "T_MAILGROUP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMailgroup.findAll", query = "SELECT t FROM TMailgroup t"),
    @NamedQuery(name = "TMailgroup.findByGroupid", query = "SELECT t FROM TMailgroup t WHERE t.groupid = :groupid"),
    @NamedQuery(name = "TMailgroup.findByGroupname", query = "SELECT t FROM TMailgroup t WHERE t.groupname = :groupname")})
public class TMailgroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "GROUPID")
    private String groupid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "GROUPNAME")
    private String groupname;
    @OneToMany(mappedBy = "groupmail")
    private List<TMaillist> tMaillistList;

    public TMailgroup() {
    }

    public TMailgroup(String groupid) {
        this.groupid = groupid;
    }

    public TMailgroup(String groupid, String groupname) {
        this.groupid = groupid;
        this.groupname = groupname;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @XmlTransient
    @JsonIgnore
    public List<TMaillist> getTMaillistList() {
        return tMaillistList;
    }

    public void setTMaillistList(List<TMaillist> tMaillistList) {
        this.tMaillistList = tMaillistList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupid != null ? groupid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TMailgroup)) {
            return false;
        }
        TMailgroup other = (TMailgroup) object;
        if ((this.groupid == null && other.groupid != null) || (this.groupid != null && !this.groupid.equals(other.groupid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TMailgroup[ groupid=" + groupid + " ]";
    }
    
}
