/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.sessions;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.vng.entities.TRole;
import org.vng.entities.TTypebf;
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
 
   public int insertUtilisateurGroupe(String login,String groupe){
        int i=0;
         String sql="INSERT INTO t_utilisateurgroupe (ugr_login,ugr_groupe) VALUES ('"+login+"','"+groupe+"') ";
     try{
            Query q=em.createNativeQuery(sql);
            i= q.executeUpdate();
        }catch(Exception e){
            System.out.println("probleme insertion groupe utilisateur : "+e);
        }  
            return i;
     }
 
     
        public List<TUtilisateur> findByGroupe(String groupe) {
        List<TUtilisateur> listeUtilisateurs = em.createNamedQuery("TUtilisateur.findByGroupe").setParameter("groupe", groupe).getResultList();
                return listeUtilisateurs;
    }
    public TUtilisateurFacade() {
        super(TUtilisateur.class);
    }
    
    
        public  List<TUtilisateur>  findListUtilisateurByTypeUtilisateur(String typeUtilisateur){
          Query query;
          query=getEntityManager().createNamedQuery("TUtilisateur.findByUtiTYpe").setParameter("typeUtil",typeUtilisateur);
          return query.getResultList();
    }
     
    public List<SelectItem> getListeGeometreItem()
    {
        List<TUtilisateur> list= findListUtilisateurByTypeUtilisateur("GEOME");
        List<SelectItem> item = new ArrayList<>();
        for (TUtilisateur object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getUtiNom()+" "+object.getUtiPrenom()));
        }
    
        return item;
    }
     
}
