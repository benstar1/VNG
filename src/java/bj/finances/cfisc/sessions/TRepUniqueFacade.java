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
import javax.persistence.NoResultException;
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
        TRepUnique res = null;
        query = em.createNamedQuery("TRepUnique.findByContImmatr").setParameter("contImmatr", ifu);
        try {
            res = (TRepUnique) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Aucun objet trouvé " + e.getMessage());
        }
        return res;
    }

    public List<TRepUnique> findContribByImmatLike(String immat) {
        return em.createNamedQuery("TRepUnique.findByContImmatlike").setParameter("contImmatr", immat + "%").getResultList();

    }
    
    public List<TRepUnique> findAllByCentreImpot(TCentreImpot tCentreImpot) {
        List<TRepUnique> listeContribuables = em.createNamedQuery("TRepUnique.findAllByContCentrImpCode").setParameter("contCentrImpCode", tCentreImpot).getResultList();
        System.out.println("recensement " + listeContribuables.size());
        return listeContribuables;
    }    
    
    public List<TRepUnique> findContribByImmatPP() {

        List<TRepUnique> res = null;
//            Long param1 = Long.parseLong("1");
//            Long param2 = Long.parseLong("2");
        try {

               res = em.createNamedQuery("TRepUnique.findContribByImmatPP").setParameter("contImmatr", "1").setParameter("contImmatr1", "2").getResultList();
//            res = em.createNamedQuery("TRepUnique.findContribByImmatPPBis").getResultList();
            //res = em.createNativeQuery("select * from prop_rep_unique.t_rep_unique").getResultList();
        } catch (java.lang.NumberFormatException e) {
            System.out.println("FindPP" + e.getMessage());
        }
        return res;
    }
}
