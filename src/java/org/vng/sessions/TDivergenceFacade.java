/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.sessions;

import java.util.Calendar;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.vng.entities.TDivergence;

/**
 *
 * @author Ben
 */
@Stateless
public class TDivergenceFacade extends AbstractFacade<TDivergence> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TDivergenceFacade() {
        super(TDivergence.class);
    }
    
    
    
    public String executeMaxDivergence(String an){
       
         String numintervenir =null;
        try{
            Query q=em.createNamedQuery("TDivergence.findMaxDivergence").setParameter("annee",an+"%");
            /* numintervenir = (String) em.createNativeQuery("select max(substring(inv_numero, 5)::numeric)::text lastyear  from t_intervenir where substring(inv_numero, 1,4) = ? ")
                .setParameter(1, an).getSingleResult();
           */
            numintervenir = (String) q.getSingleResult();
        }catch(Exception e){
            System.out.println("Probleme de selection de la plux recente operation "+e);
        }
        return numintervenir;
    }
          
          public String genererNumDivergence() {
        //Date d = new Date();
        String numero= "";
        Calendar calendar = Calendar.getInstance();
        int ann = calendar.get(Calendar.YEAR);//
        
        String an = String.valueOf(ann);
        String chainesuffixe = "";
        long numsuivant;

        String numinterv = null;
        try {
            numinterv = executeMaxDivergence(an);
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
