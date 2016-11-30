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
public class TTaxesDouPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "INSTANCE_ID")
    private long instanceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RNK")
    private long rnk;

    public TTaxesDouPK() {
    }

    public TTaxesDouPK(long instanceId, long rnk) {
        this.instanceId = instanceId;
        this.rnk = rnk;
    }

    public long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(long instanceId) {
        this.instanceId = instanceId;
    }

    public long getRnk() {
        return rnk;
    }

    public void setRnk(long rnk) {
        this.rnk = rnk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) instanceId;
        hash += (int) rnk;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTaxesDouPK)) {
            return false;
        }
        TTaxesDouPK other = (TTaxesDouPK) object;
        if (this.instanceId != other.instanceId) {
            return false;
        }
        if (this.rnk != other.rnk) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TTaxesDouPK[ instanceId=" + instanceId + ", rnk=" + rnk + " ]";
    }
    
}
