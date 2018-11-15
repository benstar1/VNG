/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.vng.entities.TConflit;

/**
 *
 * @author Ben
 */
@Stateless
public class TConflitFacade extends AbstractFacade<TConflit> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TConflitFacade() {
        super(TConflit.class);
    }
    
    
    
    public String executeMaxConflit(String numParcelle){
       
         String numintervenir =null;
        try{
            Query q=em.createNamedQuery("TConflit.findMaxConflit").setParameter("numeroParcel",numParcelle+"%");
            /* numintervenir = (String) em.createNativeQuery("select max(substring(inv_numero, 5)::numeric)::text lastyear  from t_intervenir where substring(inv_numero, 1,4) = ? ")
                .setParameter(1, an).getSingleResult();
           */
            numintervenir = (String) q.getSingleResult();
        }catch(Exception e){
            System.out.println("Probleme de selection du plus récent litige "+e);
        }
        return numintervenir;
    }
     

      
          public String genererNumConflit(String numParcelle) {
        //Date d = new Date();
        String numero= "";
       
        String chainesuffixe = "";
        long numsuivant;

        String numinterv = null;
        try {
            numinterv = executeMaxConflit(numParcelle);
            if (numinterv == null) {
                numero = numParcelle + "0";
            } else {
                chainesuffixe = numinterv.substring(numParcelle.length());
             //   System.out.println("chainesuffixe recuperer " + chainesuffixe);                
                 numsuivant = Long.valueOf(chainesuffixe) + 1;                
                numero =  numParcelle + numsuivant;
            }
        } catch (Exception e) {
                numero = numParcelle + "0";
        }
            //  System.err.println(" ************************* numero "+numero+" numparcelle "+numParcelle);
        return numero;
    }
    
    
    
    
}
