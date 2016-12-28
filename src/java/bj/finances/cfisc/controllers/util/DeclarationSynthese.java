/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.controllers.util;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author SANNI Emmanuel
 */

//bj.finances.cfisc.controllers.util.DeclarationSynthese
public class DeclarationSynthese {
    private String bureau;
    private String numEnreg;
    private Date dateEnreg;
    private String numLiq;
    private Date dateLiq;
    private String numQuit;
    private Date dateQuit;
    private BigDecimal pcs;
    private BigDecimal pc;
    private BigDecimal aib;
    private BigDecimal dd;
    private BigDecimal tva;
    private BigDecimal afs;
    
    public DeclarationSynthese(){
        
    }

    public DeclarationSynthese(String bureau, String numEnreg, Date dateEnreg, String numLiq, Date dateLiq, String numQuit, Date dateQuit, BigDecimal pcs, BigDecimal pc, BigDecimal aib, BigDecimal dd, BigDecimal tva, BigDecimal afs) {
        this.bureau = bureau;
        this.numEnreg = numEnreg;
        this.dateEnreg = dateEnreg;
        this.numLiq = numLiq;
        this.dateLiq = dateLiq;
        this.numQuit = numQuit;
        this.dateQuit = dateQuit;
        this.pcs = pcs;
        this.pc = pc;
        this.aib = aib;
        this.dd = dd;
        this.tva = tva;
        this.afs = afs;
    }

    public String getBureau() {
        return bureau;
    }

    public void setBureau(String bureau) {
        this.bureau = bureau;
    }

    public String getNumEnreg() {
        return numEnreg;
    }

    public void setNumEnreg(String numEnreg) {
        this.numEnreg = numEnreg;
    }

    public Date getDateEnreg() {
        return dateEnreg;
    }

    public void setDateEnreg(Date dateEnreg) {
        this.dateEnreg = dateEnreg;
    }

    public String getNumLiq() {
        return numLiq;
    }

    public void setNumLiq(String numLiq) {
        this.numLiq = numLiq;
    }

    public Date getDateLiq() {
        return dateLiq;
    }

    public void setDateLiq(Date dateLiq) {
        this.dateLiq = dateLiq;
    }

    public String getNumQuit() {
        return numQuit;
    }

    public void setNumQuit(String numQuit) {
        this.numQuit = numQuit;
    }

    public Date getDateQuit() {
        return dateQuit;
    }

    public void setDateQuit(Date dateQuit) {
        this.dateQuit = dateQuit;
    }

    public BigDecimal getPcs() {
        return pcs;
    }

    public void setPcs(BigDecimal pcs) {
        this.pcs = pcs;
    }

    public BigDecimal getPc() {
        return pc;
    }

    public void setPc(BigDecimal pc) {
        this.pc = pc;
    }

    public BigDecimal getAib() {
        return aib;
    }

    public void setAib(BigDecimal aib) {
        this.aib = aib;
    }

    public BigDecimal getDd() {
        return dd;
    }

    public void setDd(BigDecimal dd) {
        this.dd = dd;
    }

    public BigDecimal getTva() {
        return tva;
    }

    public void setTva(BigDecimal tva) {
        this.tva = tva;
    }

    public BigDecimal getAfs() {
        return afs;
    }

    public void setAfs(BigDecimal afs) {
        this.afs = afs;
    }
    
    
    
}
