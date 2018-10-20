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
import org.vng.entities.TTypedrevendiq;

/**
 *
 * @author Ben
 */
@Stateless
public class TTypedrevendiqFacade extends AbstractFacade<TTypedrevendiq> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TTypedrevendiqFacade() {
        super(TTypedrevendiq.class);
    }
    
     public List<SelectItem> getListeTypeDroitRevendiqueItem()
    {
        List<TTypedrevendiq> list= findAll();
        List<SelectItem> item = new ArrayList<>();
        for (TTypedrevendiq object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getTyreDesig()));
        }
        return item;
    }
    
}
