/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.sessions;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.vng.entities.TNatureOrigineContestation;
import org.vng.entities.TTypebf;

/**
 *
 * @author Ben
 */
@Stateless
public class TNatureOrigineContestationFacade extends AbstractFacade<TNatureOrigineContestation> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TNatureOrigineContestationFacade() {
        super(TNatureOrigineContestation.class);
    }
    
    
       public List<SelectItem> getListeNatureOriginConstItem()
    {
        List<TNatureOrigineContestation> list= findAll();
        List<SelectItem> item = new ArrayList<>();
        for (TNatureOrigineContestation object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getNaoLib()));
        }
    
        return item;
    }
    
}
