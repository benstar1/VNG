/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.sessions;

import bj.finances.cfisc.entities.TDeclarationDou;
import bj.finances.cfisc.entities.TRepUnique;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HP
 */
@Stateless
public class TDeclarationDouFacade extends AbstractFacade<TDeclarationDou> {
    @PersistenceContext(unitName = "CFiscPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TDeclarationDou findByInstanceId(Long id) {
        Query query;
        TDeclarationDou res = null;
        query = em.createNamedQuery("TDeclarationDou.findByInstanceId").setParameter("instanceid", id);
        try {
            res = (TDeclarationDou) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Aucun objet trouvé " + e.getMessage());
        }
        return res;
    }
    
    public TDeclarationDouFacade() {
        super(TDeclarationDou.class);
    }
    
}
