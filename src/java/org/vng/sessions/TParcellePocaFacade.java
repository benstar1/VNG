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
import org.vng.entities.TParcellePoca;

/**
 *
 * @author AAKAKPO
 */
@Stateless
public class TParcellePocaFacade extends AbstractFacade<TParcellePoca> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<TParcellePoca> executeLimite(String pba,String pointcard){
        int i=0;
         List<TParcellePoca> limites =null;
        try{
            Query q=em.createNamedQuery("TParcellePoca.findLimite")
                    .setParameter("pba",pba)
                    .setParameter("pointCardinaux", pointcard);
            limites = q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme de selection de la limite "+e);
        }
        return limites;
    }

    public TParcellePocaFacade() {
        super(TParcellePoca.class);
    }
    
}
