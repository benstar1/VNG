/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.vng.entities.TAdc;

/**
 *
 * @author AAKAKPO
 */
@Stateless
public class TAdcFacade extends AbstractFacade<TAdc> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TAdcFacade() {
        super(TAdc.class);
    }
    
}
