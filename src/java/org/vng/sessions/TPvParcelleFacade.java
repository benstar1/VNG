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
import org.vng.entities.TPvParcelle;

/**
 *
 * @author Ben
 */
@Stateless
public class TPvParcelleFacade extends AbstractFacade<TPvParcelle> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
            
      public  TPvParcelle  findPVParcelleIntervenant(String numeroParcelle, String NumeroIntervenant){
          Query query;
          query=getEntityManager().createNamedQuery("TPvParcelle.findByPvParcelleIntervenant")
                  .setParameter("parcelle",numeroParcelle)
                  .setParameter("intervenant",NumeroIntervenant)
                  ;
          return (TPvParcelle)query.getSingleResult();
        
    }
            
  

    public TPvParcelleFacade() {
        super(TPvParcelle.class);
    }
    
}
