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
import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 *
 * @author AAKAKPO
 */
@Entity
@Table(name = "parcelle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parcelle.findAll", query = "SELECT p FROM Parcelle p")
    , @NamedQuery(name = "Parcelle.findByGid", query = "SELECT p FROM Parcelle p WHERE p.gid = :gid")
    , @NamedQuery(name = "Parcelle.findByGuid", query = "SELECT p FROM Parcelle p WHERE p.guid = :guid")
    , @NamedQuery(name = "Parcelle.findByDistrictname", query = "SELECT p FROM Parcelle p WHERE p.districtname = :districtname")
    , @NamedQuery(name = "Parcelle.findByDistrictnumber", query = "SELECT p FROM Parcelle p WHERE p.districtnumber = :districtnumber")
    , @NamedQuery(name = "Parcelle.findByCountyname", query = "SELECT p FROM Parcelle p WHERE p.countyname = :countyname")
    , @NamedQuery(name = "Parcelle.findByCountynumber", query = "SELECT p FROM Parcelle p WHERE p.countynumber = :countynumber")
    , @NamedQuery(name = "Parcelle.findBySubcountyname", query = "SELECT p FROM Parcelle p WHERE p.subcountyname = :subcountyname")
    , @NamedQuery(name = "Parcelle.findBySubcountynumber", query = "SELECT p FROM Parcelle p WHERE p.subcountynumber = :subcountynumber")
    , @NamedQuery(name = "Parcelle.findByParishname", query = "SELECT p FROM Parcelle p WHERE p.parishname = :parishname")
    , @NamedQuery(name = "Parcelle.findByParishnumber", query = "SELECT p FROM Parcelle p WHERE p.parishnumber = :parishnumber")
    , @NamedQuery(name = "Parcelle.findByVillagename", query = "SELECT p FROM Parcelle p WHERE p.villagename = :villagename")
    , @NamedQuery(name = "Parcelle.findByVillagenumber", query = "SELECT p FROM Parcelle p WHERE p.villagenumber = :villagenumber")
    , @NamedQuery(name = "Parcelle.findByClerk", query = "SELECT p FROM Parcelle p WHERE p.clerk = :clerk")
    , @NamedQuery(name = "Parcelle.findByDate", query = "SELECT p FROM Parcelle p WHERE p.date = :date")
    , @NamedQuery(name = "Parcelle.findByLat", query = "SELECT p FROM Parcelle p WHERE p.lat = :lat")
    , @NamedQuery(name = "Parcelle.findByLon", query = "SELECT p FROM Parcelle p WHERE p.lon = :lon")
    , @NamedQuery(name = "Parcelle.findByEast", query = "SELECT p FROM Parcelle p WHERE p.east = :east")
    , @NamedQuery(name = "Parcelle.findByNorth", query = "SELECT p FROM Parcelle p WHERE p.north = :north")
    , @NamedQuery(name = "Parcelle.findByDatecreate", query = "SELECT p FROM Parcelle p WHERE p.datecreate = :datecreate")
    , @NamedQuery(name = "Parcelle.findByDatelasted", query = "SELECT p FROM Parcelle p WHERE p.datelasted = :datelasted")
    , @NamedQuery(name = "Parcelle.findByParcelleno", query = "SELECT p FROM Parcelle p WHERE p.parcelleno = :parcelleno")
    , @NamedQuery(name = "Parcelle.findByGeoid", query = "SELECT p FROM Parcelle p WHERE p.geoid = :geoid")
    , @NamedQuery(name = "Parcelle.findByHectares", query = "SELECT p FROM Parcelle p WHERE p.hectares = :hectares")
    , @NamedQuery(name = "Parcelle.findByUtilizatio", query = "SELECT p FROM Parcelle p WHERE p.utilizatio = :utilizatio")
    , @NamedQuery(name = "Parcelle.findByUsers", query = "SELECT p FROM Parcelle p WHERE p.users = :users")
    , @NamedQuery(name = "Parcelle.findByNeighbours", query = "SELECT p FROM Parcelle p WHERE p.neighbours = :neighbours")
    , @NamedQuery(name = "Parcelle.findByWitnesses", query = "SELECT p FROM Parcelle p WHERE p.witnesses = :witnesses")
    , @NamedQuery(name = "Parcelle.findByUse", query = "SELECT p FROM Parcelle p WHERE p.use = :use")
    , @NamedQuery(name = "Parcelle.findByLandtype", query = "SELECT p FROM Parcelle p WHERE p.landtype = :landtype")
    , @NamedQuery(name = "Parcelle.findByPhoto1", query = "SELECT p FROM Parcelle p WHERE p.photo1 = :photo1")
    , @NamedQuery(name = "Parcelle.findByPhoto2", query = "SELECT p FROM Parcelle p WHERE p.photo2 = :photo2")
    , @NamedQuery(name = "Parcelle.findByPhoto3", query = "SELECT p FROM Parcelle p WHERE p.photo3 = :photo3")
    , @NamedQuery(name = "Parcelle.findBySketch", query = "SELECT p FROM Parcelle p WHERE p.sketch = :sketch")
    , @NamedQuery(name = "Parcelle.findByGrouppic", query = "SELECT p FROM Parcelle p WHERE p.grouppic = :grouppic")
    , @NamedQuery(name = "Parcelle.findListVillage", query = "SELECT DISTINCT p.villagename FROM Parcelle p")
    , @NamedQuery(name = "Parcelle.findByParPbaNumero", query = "SELECT p FROM Parcelle p WHERE p.parPbaNumero = :parPbaNumero")})
public class Parcelle implements Serializable {

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
    @Column(name = "lat")
    private String lat;
    @Size(max = 100)
    @Column(name = "lon")
    private String lon;
    @Size(max = 100)
    @Column(name = "east")
    private String east;
    @Size(max = 100)
    @Column(name = "north")
    private String north;
    @Size(max = 100)
    @Column(name = "datecreate")
    private String datecreate;
    @Size(max = 100)
    @Column(name = "datelasted")
    private String datelasted;
    @Size(max = 100)
    @Column(name = "parcelleno")
    private String parcelleno;
    @Size(max = 100)
    @Column(name = "geoid")
    private String geoid;
    @Size(max = 100)
    @Column(name = "hectares")
    private String hectares;
    @Size(max = 100)
    @Column(name = "utilizatio")
    private String utilizatio;
    @Size(max = 100)
    @Column(name = "users")
    private String users;
    @Size(max = 100)
    @Column(name = "neighbours")
    private String neighbours;
    @Size(max = 100)
    @Column(name = "witnesses")
    private String witnesses;
    @Size(max = 100)
    @Column(name = "use")
    private String use;
    @Size(max = 100)
    @Column(name = "landtype")
    private String landtype;
    @Size(max = 100)
    @Column(name = "photo1")
    private String photo1;
    @Size(max = 100)
    @Column(name = "photo2")
    private String photo2;
    @Size(max = 100)
    @Column(name = "photo3")
    private String photo3;
    @Size(max = 100)
    @Column(name = "sketch")
    private String sketch;
    @Size(max = 100)
    @Column(name = "grouppic")
    private String grouppic;
    //java.sql.Blob
    //@Blob
    @Column(name = "geom")
    private String geom;
    @Size(max = 50)
    @Column(name = "par_pba_numero")
    private String parPbaNumero;

    public Parcelle() {
    }

    public Parcelle(Integer gid) {
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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(String datecreate) {
        this.datecreate = datecreate;
    }

    public String getDatelasted() {
        return datelasted;
    }

    public void setDatelasted(String datelasted) {
        this.datelasted = datelasted;
    }

    public String getParcelleno() {
        return parcelleno;
    }

    public void setParcelleno(String parcelleno) {
        this.parcelleno = parcelleno;
    }

    public String getGeoid() {
        return geoid;
    }

    public void setGeoid(String geoid) {
        this.geoid = geoid;
    }

    public String getHectares() {
        return hectares;
    }

    public void setHectares(String hectares) {
        this.hectares = hectares;
    }

    public String getUtilizatio() {
        return utilizatio;
    }

    public void setUtilizatio(String utilizatio) {
        this.utilizatio = utilizatio;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(String neighbours) {
        this.neighbours = neighbours;
    }

    public String getWitnesses() {
        return witnesses;
    }

    public void setWitnesses(String witnesses) {
        this.witnesses = witnesses;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getLandtype() {
        return landtype;
    }

    public void setLandtype(String landtype) {
        this.landtype = landtype;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public String getSketch() {
        return sketch;
    }

    public void setSketch(String sketch) {
        this.sketch = sketch;
    }

    public String getGrouppic() {
        return grouppic;
    }

    public void setGrouppic(String grouppic) {
        this.grouppic = grouppic;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public String getParPbaNumero() {
        return parPbaNumero;
    }

    public void setParPbaNumero(String parPbaNumero) {
        this.parPbaNumero = parPbaNumero;
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
        if (!(object instanceof Parcelle)) {
            return false;
        }
        Parcelle other = (Parcelle) object;
        if ((this.gid == null && other.gid != null) || (this.gid != null && !this.gid.equals(other.gid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.Parcelle[ gid=" + gid + " ]";
    }
    
}
