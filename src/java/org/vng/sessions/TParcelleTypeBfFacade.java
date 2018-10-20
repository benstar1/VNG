/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.vng.entities.TParcelleTypeBf;

/**
 *
 * @author Dev_DI
 */
@Stateless
public class TParcelleTypeBfFacade extends AbstractFacade<TParcelleTypeBf> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TParcelleTypeBfFacade() {
        super(TParcelleTypeBf.class);
    }
    
}
