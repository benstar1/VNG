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

/**
 *
 * @author JOHNSON
 */
@Entity
@Table(name = "T_TARIF_DOUANE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTarifDouane.findAll", query = "SELECT t FROM TTarifDouane t"),
    @NamedQuery(name = "TTarifDouane.findByCodeTarif", query = "SELECT t FROM TTarifDouane t WHERE t.codeTarif = :codeTarif"),
    @NamedQuery(name = "TTarifDouane.findByDescription", query = "SELECT t FROM TTarifDouane t WHERE t.description = :description")})
public class TTarifDouane implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "CODE_TARIF")
    private String codeTarif;
    @Size(max = 4000)
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(mappedBy = "taxDecTarifCode")
    private List<TTaxeDeclaration> tTaxeDeclarationList;

    public TTarifDouane() {
    }

    public TTarifDouane(String codeTarif) {
        this.codeTarif = codeTarif;
    }

    public String getCodeTarif() {
        return codeTarif;
    }

    public void setCodeTarif(String codeTarif) {
        this.codeTarif = codeTarif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<TTaxeDeclaration> getTTaxeDeclarationList() {
        return tTaxeDeclarationList;
    }

    public void setTTaxeDeclarationList(List<TTaxeDeclaration> tTaxeDeclarationList) {
        this.tTaxeDeclarationList = tTaxeDeclarationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeTarif != null ? codeTarif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTarifDouane)) {
            return false;
        }
        TTarifDouane other = (TTarifDouane) object;
        if ((this.codeTarif == null && other.codeTarif != null) || (this.codeTarif != null && !this.codeTarif.equals(other.codeTarif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TTarifDouane[ codeTarif=" + codeTarif + " ]";
    }
    
}
