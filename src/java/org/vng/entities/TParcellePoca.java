/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dev_DI
 */
@Entity
@Table(name = "t_parcelle_poca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TParcellePoca.findAll", query = "SELECT t FROM TParcellePoca t")
    , @NamedQuery(name = "TParcellePoca.findByPapoPocaCode", query = "SELECT t FROM TParcellePoca t WHERE t.tParcellePocaPK.papoPocaCode = :papoPocaCode")
    , @NamedQuery(name = "TParcellePoca.findByPapoPbaNumero", query = "SELECT t FROM TParcellePoca t WHERE t.tParcellePocaPK.papoPbaNumero = :papoPbaNumero")
    , @NamedQuery(name = "TParcellePoca.findByPapoAutreLimite", query = "SELECT t FROM TParcellePoca t WHERE t.papoAutreLimite = :papoAutreLimite")
    , @NamedQuery(name = "TParcellePoca.findByPapoLongLimitroph", query = "SELECT t FROM TParcellePoca t WHERE t.papoLongLimitroph = :papoLongLimitroph")})
public class TParcellePoca implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TParcellePocaPK tParcellePocaPK;
    @Size(max = 500)
    @Column(name = "papo_autre_limite")
    private String papoAutreLimite;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "papo_long_limitroph")
    private BigDecimal papoLongLimitroph;
    @JoinColumn(name = "papo_pba_numero", referencedColumnName = "pba_numero", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TParcelleBafon tParcelleBafon;
    @JoinColumn(name = "papo_pba_numero_limit", referencedColumnName = "pba_numero")
    @ManyToOne
    private TParcelleBafon papoPbaNumeroLimit;
    @JoinColumn(name = "papo_poca_code", referencedColumnName = "poca_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TPointCardino tPointCardino;

    public TParcellePoca() {
    }

    public TParcellePoca(TParcellePocaPK tParcellePocaPK) {
        this.tParcellePocaPK = tParcellePocaPK;
    }

    public TParcellePoca(String papoPocaCode, String papoPbaNumero) {
        this.tParcellePocaPK = new TParcellePocaPK(papoPocaCode, papoPbaNumero);
    }

    public TParcellePocaPK getTParcellePocaPK() {
        return tParcellePocaPK;
    }

    public void setTParcellePocaPK(TParcellePocaPK tParcellePocaPK) {
        this.tParcellePocaPK = tParcellePocaPK;
    }

    public String getPapoAutreLimite() {
        return papoAutreLimite;
    }

    public void setPapoAutreLimite(String papoAutreLimite) {
        this.papoAutreLimite = papoAutreLimite;
    }

    public BigDecimal getPapoLongLimitroph() {
        return papoLongLimitroph;
    }

    public void setPapoLongLimitroph(BigDecimal papoLongLimitroph) {
        this.papoLongLimitroph = papoLongLimitroph;
    }

    public TParcelleBafon getTParcelleBafon() {
        return tParcelleBafon;
    }

    public void setTParcelleBafon(TParcelleBafon tParcelleBafon) {
        this.tParcelleBafon = tParcelleBafon;
    }

    public TParcelleBafon getPapoPbaNumeroLimit() {
        return papoPbaNumeroLimit;
    }

    public void setPapoPbaNumeroLimit(TParcelleBafon papoPbaNumeroLimit) {
        this.papoPbaNumeroLimit = papoPbaNumeroLimit;
    }

    public TPointCardino getTPointCardino() {
        return tPointCardino;
    }

    public void setTPointCardino(TPointCardino tPointCardino) {
        this.tPointCardino = tPointCardino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tParcellePocaPK != null ? tParcellePocaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TParcellePoca)) {
            return false;
        }
        TParcellePoca other = (TParcellePoca) object;
        if ((this.tParcellePocaPK == null && other.tParcellePocaPK != null) || (this.tParcellePocaPK != null && !this.tParcellePocaPK.equals(other.tParcellePocaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.vng.entities.TParcellePoca[ tParcellePocaPK=" + tParcellePocaPK + " ]";
    }
    
}
