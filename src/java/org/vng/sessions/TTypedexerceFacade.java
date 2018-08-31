/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.vng.entities.TTypedexerce;

/**
 *
 * @author Ben
 */
@Stateless
public class TTypedexerceFacade extends AbstractFacade<TTypedexerce> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TTypedexerceFacade() {
        super(TTypedexerce.class);
    }
    
}
