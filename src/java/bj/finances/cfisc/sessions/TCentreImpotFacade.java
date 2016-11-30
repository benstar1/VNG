/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.sessions;

import bj.finances.cfisc.entities.TCentreImpot;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class TCentreImpotFacade extends AbstractFacade<TCentreImpot> {
    @PersistenceContext(unitName = "CFiscPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    public TCentreImpot find(String id) {

        TCentreImpot tCentreImpot = (TCentreImpot) em.createNamedQuery("TCentreImpot.findByCentrImpCode").setParameter("centrImpCode", id).getSingleResult();

        if( tCentreImpot == null ) return null;
        return tCentreImpot;
    }
        
        
    public TCentreImpotFacade() {
        super(TCentreImpot.class);
    }
    
}
