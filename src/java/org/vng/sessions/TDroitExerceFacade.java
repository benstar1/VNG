/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.sessions;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.vng.entities.TDroitExerce;
import org.vng.entities.TTypedexerce;
import org.vng.entities.TParcelleBafon;

/**
 *
 * @author Ben
 */
@Stateless
public class TDroitExerceFacade extends AbstractFacade<TDroitExerce> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<TDroitExerce> executeListeDroitExerceCat(TParcelleBafon paramparcelle,String catDroit){
        int i=0;
        List<TDroitExerce> listeDroitExerce =null;
        try{
            Query q=em.createNamedQuery("TDroitExerce.findByParcelleCat").setParameter("parcelle",paramparcelle)
                    .setParameter("catDroit", catDroit);
            listeDroitExerce=q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme select de droit exerce par categorie : "+e);
        }
        return listeDroitExerce;
    }

    public TDroitExerceFacade() {
        super(TDroitExerce.class);
    }
    
       public  List<TDroitExerce>  findListTypeDroitExerceByCategorie(String categorie){
          Query query;
          query=getEntityManager().createNamedQuery("TTypedexerce.findByTdeCat").setParameter("tdeCat",categorie);
          return query.getResultList();
        
    }
    
          public  List<TDroitExerce>  findListDroitExerceParcelleByCategorie(String numroParcelle, String categorie){
          Query query;
          query=getEntityManager().createNamedQuery("TDroitExerce.findByDreCatParcelle")
                  .setParameter("numParcelle",numroParcelle)
                  .setParameter("dreCat",categorie);
          return query.getResultList();
        
    }
      
       
       
       
}
