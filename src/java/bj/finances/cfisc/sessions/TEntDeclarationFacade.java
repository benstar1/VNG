/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.sessions;

import bj.finances.cfisc.entities.TEntDeclaration;
import bj.finances.cfisc.entities.TRepUnique;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HP
 */
@Stateless
public class TEntDeclarationFacade extends AbstractFacade<TEntDeclaration> {
    @PersistenceContext(unitName = "CFiscPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TEntDeclarationFacade() {
        super(TEntDeclaration.class);
    }
    public List<TEntDeclaration> findListentdeclarcontrib(Long contrib) {
        Query query;
        query = em.createNamedQuery("TEntDeclaration.findByContrib").setParameter("contrib", contrib);
        return query.getResultList();
    }

    
}
