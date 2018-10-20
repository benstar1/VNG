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
import org.vng.entities.TParamettre;

/**
 *
 * @author Dev_DI
 */
@Stateless
public class TParamettreFacade extends AbstractFacade<TParamettre> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TParamettreFacade() {
        super(TParamettre.class);
    }
    
    
    public  TParamettre  getCheminDossierImage(String codeParametre){
          Query query;
          query=getEntityManager().createNamedQuery("TParamettre.findParamCourant").setParameter("paramCode",codeParametre);
          return (TParamettre)query.getSingleResult();
        
    }
    
    
}
