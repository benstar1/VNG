/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.sessions;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.vng.entities.TUtilisateur;

/**
 *
 * @author Ben
 */
@Stateless
public class TUtilisateurFacade extends AbstractFacade<TUtilisateur> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;
    private TUtilisateur Util = null; 
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
 public TUtilisateur rechercheUtilconnecte(String login){
   
        Query query;
       
      query= em.createNamedQuery("TUtilisateur.findByUtiLogin").setParameter("utiLogin",login);
         try {
           Util = (TUtilisateur) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Aucun objet trouvé " + e.getMessage());
        }
        return Util;   
    }
     
        public List<TUtilisateur> findByGroupe(String groupe) {
        List<TUtilisateur> listeUtilisateurs = em.createNamedQuery("TUtilisateur.findByGroupe").setParameter("groupe", groupe).getResultList();
                return listeUtilisateurs;
    }
    public TUtilisateurFacade() {
        super(TUtilisateur.class);
    }
    
}
