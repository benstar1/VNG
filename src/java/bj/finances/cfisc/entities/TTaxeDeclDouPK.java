/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author HP
 */
@Embeddable
public class TTaxeDeclDouPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "INSTANCEID")
    private long instanceid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "KEY_ITM_NBR")
    private long keyItmNbr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "KEY_TAX_RNK")
    private long keyTaxRnk;

    public TTaxeDeclDouPK() {
    }

    public TTaxeDeclDouPK(long instanceid, long keyItmNbr, long keyTaxRnk) {
        this.instanceid = instanceid;
        this.keyItmNbr = keyItmNbr;
        this.keyTaxRnk = keyTaxRnk;
    }

    public long getInstanceid() {
        return instanceid;
    }

    public void setInstanceid(long instanceid) {
        this.instanceid = instanceid;
    }

    public long getKeyItmNbr() {
        return keyItmNbr;
    }

    public void setKeyItmNbr(long keyItmNbr) {
        this.keyItmNbr = keyItmNbr;
    }

    public long getKeyTaxRnk() {
        return keyTaxRnk;
    }

    public void setKeyTaxRnk(long keyTaxRnk) {
        this.keyTaxRnk = keyTaxRnk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) instanceid;
        hash += (int) keyItmNbr;
        hash += (int) keyTaxRnk;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTaxeDeclDouPK)) {
            return false;
        }
        TTaxeDeclDouPK other = (TTaxeDeclDouPK) object;
        if (this.instanceid != other.instanceid) {
            return false;
        }
        if (this.keyItmNbr != other.keyItmNbr) {
            return false;
        }
        if (this.keyTaxRnk != other.keyTaxRnk) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TTaxeDeclDouPK[ instanceid=" + instanceid + ", keyItmNbr=" + keyItmNbr + ", keyTaxRnk=" + keyTaxRnk + " ]";
    }
    
}
