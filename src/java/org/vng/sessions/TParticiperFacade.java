/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.vng.entities.TParticiper;

/**
 *
 * @author Ben
 */
@Stateless
public class TParticiperFacade extends AbstractFacade<TParticiper> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TParticiperFacade() {
        super(TParticiper.class);
    }
    
}
