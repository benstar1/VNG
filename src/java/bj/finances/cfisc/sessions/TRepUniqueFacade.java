/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.sessions;

import bj.finances.cfisc.entities.TCentreImpot;
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
public class TRepUniqueFacade extends AbstractFacade<TRepUnique> {
    @PersistenceContext(unitName = "CFiscPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TRepUniqueFacade() {
        super(TRepUnique.class);
    }
    public TRepUnique findByContImmatr(Long ifu) {
        Query query;
        query = em.createNamedQuery("TRepUnique.findByContImmatr").setParameter("contImmatr", ifu);
        return (TRepUnique) query.getSingleResult();

    }

    public List<TRepUnique> findContribByImmatLike(String immat) {
        return em.createNamedQuery("TRepUnique.findByContImmatlike").setParameter("contImmatr", immat + "%").getResultList();

    }
    
    public List<TRepUnique> findAllByCentreImpot(TCentreImpot tCentreImpot) {
        List<TRepUnique> listeContribuables = em.createNamedQuery("TRepUnique.findAllByContCentrImpCode").setParameter("contCentrImpCode", tCentreImpot).getResultList();
        return listeContribuables;
    }    
}
