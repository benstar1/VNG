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
import org.vng.entities.TDepotSignature;
import org.vng.entities.TIntervenant;

/**
 *
 * @author Ben
 */
@Stateless
public class TDepotSignatureFacade extends AbstractFacade<TDepotSignature> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
 public List<TDepotSignature> executeListeDepotIntervenant(TIntervenant intervenant){
        int i=0;
        List<TDepotSignature> listeDepot =null;
        try{
            Query q=em.createNamedQuery("TDepotSignature.findByIntervenant").setParameter("intervenant",intervenant)
                  ;
            listeDepot=q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme select de droit exerce par categorie : "+e);
        }
        return listeDepot;
    }
    public TDepotSignatureFacade() {
        super(TDepotSignature.class);
    }
    
}
