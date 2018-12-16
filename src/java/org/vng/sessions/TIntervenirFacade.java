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
import org.vng.entities.TIntervenir;
import org.vng.entities.TParcelleBafon;

/**
 *
 * @author Ben
 */
@Stateless
public class TIntervenirFacade extends AbstractFacade<TIntervenir> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<TIntervenir> executeListeIntervRole(TParcelleBafon parcelle,String categorieDroit){
        int i=0;
        List<TIntervenir> listeintervenir =null;
        try{
            Query q=em.createNamedQuery("TIntervenir.findDetentDroitAdmin");
            q.setParameter("categorierole",categorieDroit);
            q.setParameter("parcelle",parcelle);
            listeintervenir=q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme de selection des intervenir selon role : selection "+e);
        }
        return listeintervenir;
    }
     
    public String executeMaxIntervenir(String an){
        
         System.out.println("Annee "+an);
         String numintervenir =null;
        try{
            Query q=em.createNamedQuery("TIntervenir.findMaxIntervenir").setParameter("annee",an+"%");
            numintervenir = (String) q.getSingleResult();
        }catch(Exception e){
            System.out.println("Probleme de selection de la plux recente operation "+e);
        }
        return numintervenir;
    }

    public TIntervenirFacade() {
        super(TIntervenir.class);
    }
    
}
