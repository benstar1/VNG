/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.sessions;

import bj.finances.cfisc.entities.TGroupe;
import bj.finances.cfisc.entities.TUtilisateur;
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
public class TUtilisateurFacade extends AbstractFacade<TUtilisateur> {
    @PersistenceContext(unitName = "CFiscPU")
    private EntityManager em;
 private TUtilisateur Util = null; 
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

     public TUtilisateur rechercheUtilconnecte(String login){
   
        Query query;
       
      query= em.createNamedQuery("TUtilisateur.findByUtilLogin").setParameter("utilLogin",login);
         try {
           Util = (TUtilisateur) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Aucun objet trouvé " + e.getMessage());
        }
        return Util;
      
      
    }
     
        public List<TUtilisateur> findByGroupe(TGroupe groupe) {
        List<TUtilisateur> listeUtilisateurs = em.createNamedQuery("TUtilisateur.findByGroupe").setParameter("groupe", groupe).getResultList();
                return listeUtilisateurs;
    }
        
    public TUtilisateurFacade() {
        super(TUtilisateur.class);
    }
    
}
