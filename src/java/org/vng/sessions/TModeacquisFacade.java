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
import org.vng.entities.TModeacquis;
import org.vng.entities.TParcelleBafon;

/**
 *
 * @author Ben
 */
@Stateless
public class TModeacquisFacade extends AbstractFacade<TModeacquis> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<TModeacquis> executeListeModeaquisHerit(){  
        List<TModeacquis> listeModeAcquis =null;
        try{
            Query q=em.createNamedQuery("TModeacquis.findByHeritage");
            listeModeAcquis=q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme select de droit exerce par categorie : "+e);
        }
        return listeModeAcquis;
    }
    
    public List<TModeacquis> executeListeModeOperationel(){  
        List<TModeacquis> listeModeAcquis =null;
        try{
            Query q=em.createNamedQuery("TModeacquis.findByOperationnel");
            listeModeAcquis=q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme select de droit exerce par categorie : "+e);
        }
        return listeModeAcquis;
    }
    
    public TModeacquisFacade() {
        super(TModeacquis.class);
    }
    
}
