/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.sessions;

import bj.finances.cfisc.entities.TTypeContrib;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class TTypeContribFacade extends AbstractFacade<TTypeContrib> {
    @PersistenceContext(unitName = "CFiscPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public TTypeContrib find(String id) {
        TTypeContrib tTypeContrib = (TTypeContrib) em.createNamedQuery("TTypeContrib.findByTypContCode").setParameter("typContCode", id).getSingleResult();
        
        if( tTypeContrib == null ) return null;
        return tTypeContrib;
    }

    public TTypeContribFacade() {
        super(TTypeContrib.class);
    }
    
}
