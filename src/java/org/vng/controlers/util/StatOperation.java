/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.controlers.util;

import java.io.Serializable;

/**
 *
 * @author AAKAKPO
 */
public class StatOperation implements Serializable{
    private String typeOper;
    private int nbreOperation;

    public StatOperation() {
    }

    public String getTypeOper() {
        return typeOper;
    }

    public void setTypeOper(String typeOper) {
        this.typeOper = typeOper;
    }

    public int getNbreOperation() {
        return nbreOperation;
    }

    public void setNbreOperation(int nbreOperation) {
        this.nbreOperation = nbreOperation;
    }
    
}
