/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.vng.entities.TParcelleBafon;

/**
 *
 * @author Ben
 */
@Stateless
public class TParcelleBafonFacade extends AbstractFacade<TParcelleBafon> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TParcelleBafonFacade() {
        super(TParcelleBafon.class);
    }
    
}
