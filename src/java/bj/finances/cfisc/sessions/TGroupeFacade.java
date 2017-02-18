/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.sessions;

import bj.finances.cfisc.entities.TEntDeclaration;
import bj.finances.cfisc.entities.TGroupe;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author user
 */
@Stateless
public class TGroupeFacade extends AbstractFacade<TGroupe> {

    @PersistenceContext(unitName = "CFiscPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<TGroupe> findTrueGroupe() {
        Query query;
        query = em.createNamedQuery("TGroupe.findTrueGroupe");
        //query.setParameter("anne", anne);
        return query.getResultList();
    }
    

    public TGroupeFacade() {
        super(TGroupe.class);
    }
    
}
