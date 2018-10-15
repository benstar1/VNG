/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AAKAKPO
 */
@Entity
@Table(name = "corridor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Corridor.findAll", query = "SELECT c FROM Corridor c")
    , @NamedQuery(name = "Corridor.findByGid", query = "SELECT c FROM Corridor c WHERE c.gid = :gid")
    , @NamedQuery(name = "Corridor.findByGuid", query = "SELECT c FROM Corridor c WHERE c.guid = :guid")
    , @NamedQuery(name = "Corridor.findByDistrictname", query = "SELECT c FROM Corridor c WHERE c.districtname = :districtname")
    , @NamedQuery(name = "Corridor.findByDistrictnumber", query = "SELECT c FROM Corridor c WHERE c.districtnumber = :districtnumber")
    , @NamedQuery(name = "Corridor.findByCountyname", query = "SELECT c FROM Corridor c WHERE c.countyname = :countyname")
    , @NamedQuery(name = "Corridor.findByCountynumber", query = "SELECT c FROM Corridor c WHERE c.countynumber = :countynumber")
    , @NamedQuery(name = "Corridor.findBySubcountyname", query = "SELECT c FROM Corridor c WHERE c.subcountyname = :subcountyname")
    , @NamedQuery(name = "Corridor.findBySubcountynumber", query = "SELECT c FROM Corridor c WHERE c.subcountynumber = :subcountynumber")
    , @NamedQuery(name = "Corridor.findByParishname", query = "SELECT c FROM Corridor c WHERE c.parishname = :parishname")
    , @NamedQuery(name = "Corridor.findByParishnumber", query = "SELECT c FROM Corridor c WHERE c.parishnumber = :parishnumber")
    , @NamedQuery(name = "Corridor.findByVillagename", query = "SELECT c FROM Corridor c WHERE c.villagename = :villagename")
    , @NamedQuery(name = "Corridor.findByVillagenumber", query = "SELECT c FROM Corridor c WHERE c.villagenumber = :villagenumber")
    , @NamedQuery(name = "Corridor.findByClerk", query = "SELECT c FROM Corridor c WHERE c.clerk = :clerk")
    , @NamedQuery(name = "Corridor.findByDate", query = "SELECT c FROM Corridor c WHERE c.date = :date")
    , @NamedQuery(name = "Corridor.findByType", query = "SELECT c FROM Corridor c WHERE c.type = :type")
    , @NamedQuery(name = "Corridor.findByHoffset", query = "SELECT c FROM Corridor c WHERE c.hoffset = :hoffset")
    , @NamedQuery(name = "Corridor.findByCorPbaNumero", query = "SELECT c FROM Corridor c WHERE c.corPbaNumero = :corPbaNumero")})
public class Corridor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "gid")
    private Integer gid;
    @Size(max = 50)
    @Column(name = "guid")
    private String guid;
    @Size(max = 50)
    @Column(name = "districtname")
    private String districtname;
    @Column(name = "districtnumber")
    private Integer districtnumber;
    @Size(max = 50)
    @Column(name = "countyname")
    private String countyname;
    @Column(name = "countynumber")
    private Integer countynumber;
    @Size(max = 50)
    @Column(name = "subcountyname")
    private String subcountyname;
    @Column(name = "subcountynumber")
    private Integer subcountynumber;
    @Size(max = 50)
    @Column(name = "parishname")
    private String parishname;
    @Column(name = "parishnumber")
    private Integer parishnumber;
    @Size(max = 50)
    @Column(name = "villagename")
    private String villagename;
    @Column(name = "villagenumber")
    private Integer villagenumber;
    @Size(max = 50)
    @Column(name = "clerk")
    private String clerk;
    @Size(max = 50)
    @Column(name = "date")
    private String date;
    @Size(max = 100)
    @Column(name = "type")
    private String type;
    @Size(max = 100)
    @Column(name = "hoffset")
    private String hoffset;
    //@Lob
    @Column(name = "geom")
    private String geom;
    @Size(max = 50)
    @Column(name = "cor_pba_numero")
    private String corPbaNumero;

    public Corridor() {
    }

    public Corridor(Integer gid) {
        this.gid = gid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    public Integer getDistrictnumber() {
        return districtnumber;
    }

    public void setDistrictnumber(Integer districtnumber) {
        this.districtnumber = districtnumber;
    }

    public String getCountyname() {
        return countyname;
    }

    public void setCountyname(String countyname) {
        this.countyname = countyname;
    }

    public Integer getCountynumber() {
        return countynumber;
    }

    public void setCountynumber(Integer countynumber) {
        this.countynumber = countynumber;
    }

    public String getSubcountyname() {
        return subcountyname;
    }

    public void setSubcountyname(String subcountyname) {
        this.subcountyname = subcountyname;
    }

    public Integer getSubcountynumber() {
        return subcountynumber;
    }

    public void setSubcountynumber(Integer subcountynumber) {
        this.subcountynumber = subcountynumber;
    }

    public String getParishname() {
        return parishname;
    }

    public void setParishname(String parishname) {
        this.parishname = parishname;
    }

    public Integer getParishnumber() {
        return parishnumber;
    }

    public void setParishnumber(Integer parishnumber) {
        this.parishnumber = parishnumber;
    }

    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }

    public Integer getVillagenumber() {
        return villagenumber;
    }

    public void setVillagenumber(Integer villagenumber) {
        this.villagenumber = villagenumber;
    }

    public String getClerk() {
        return clerk;
    }

    public void setClerk(String clerk) {
        this.clerk = clerk;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHoffset() {
        return hoffset;
    }

    public void setHoffset(String hoffset) {
        this.hoffset = hoffset;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public String getCorPbaNumero() {
        return corPbaNumero;
    }

    public void setCorPbaNumero(String corPbaNumero) {
        this.corPbaNumero = corPbaNumero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gid != null ? gid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Corridor)) {
            return false;
        }
        Corridor other = (Corridor) object;
        if ((this.gid == null && other.gid != null) || (this.gid != null && !this.gid.equals(other.gid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.Corridor[ gid=" + gid + " ]";
    }
    
}
