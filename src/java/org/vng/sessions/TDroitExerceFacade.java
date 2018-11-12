/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.sessions;

import java.util.Calendar;
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
      
          
    public String executeMaxDroitExerce(String an){
       
         String numintervenir =null;
        try{
            Query q=em.createNamedQuery("TDroitExerce.findMaxDroitExerce").setParameter("annee",an+"%");
            /* numintervenir = (String) em.createNativeQuery("select max(substring(inv_numero, 5)::numeric)::text lastyear  from t_intervenir where substring(inv_numero, 1,4) = ? ")
                .setParameter(1, an).getSingleResult();
           */
            numintervenir = (String) q.getSingleResult();
        }catch(Exception e){
            System.out.println("Probleme de selection de la plux recente operation "+e);
        }
        return numintervenir;
    }
       
      
          public String genererNumDroitExerce() {
        //Date d = new Date();
        String numero= "";
        Calendar calendar = Calendar.getInstance();
        int ann = calendar.get(Calendar.YEAR);//
        
        String an = String.valueOf(ann);
        String chainesuffixe = "";
        long numsuivant;

        String numinterv = null;
        try {
            numinterv = executeMaxDroitExerce(an);
            if (numinterv == null) {
                numero = an + "00000000000000000001";
            } else {
                chainesuffixe = numinterv.substring(4);
             //   System.out.println("chainesuffixe recuperer " + chainesuffixe);                
                 numsuivant = Long.valueOf(chainesuffixe) + 1;
                chainesuffixe = String.format("%020d", numsuivant);
                numero = an + chainesuffixe;
            }
        } catch (Exception e) {
                numero = an + "00000000000000000001";
        }

        return numero;
    }
    
       
}
