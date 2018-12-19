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
import org.vng.entities.TRole;

/**
 *
 * @author Ben
 */
@Stateless
public class TRoleFacade extends AbstractFacade<TRole> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
        public List<TRole> executeListRoleCat(String categorie){
        int i=0;
         List<TRole> listRole =null;
        try{
            Query q=em.createNamedQuery("TRole.findByRolCat").setParameter("rolCat",categorie);
            listRole =  q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme de selection des roles "+e);
        }
        return listRole;
    }

    public TRoleFacade() {
        super(TRole.class);
    }
    
}
