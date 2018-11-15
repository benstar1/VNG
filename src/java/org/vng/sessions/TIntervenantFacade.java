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
import org.vng.entities.TIntervenant;
import org.vng.entities.TOrigineDroitRevendique;

/**
 *
 * @author Ben
 */
@Stateless
public class TIntervenantFacade extends AbstractFacade<TIntervenant> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TIntervenantFacade() {
        super(TIntervenant.class);
    }
    
    
        public List<SelectItem> getListeIntervenantItem()
    {
        List<TIntervenant> list= findAll();
        List<SelectItem> item = new ArrayList<>();
        for (TIntervenant object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getIntNom()+" "+object.getIntPrenom()));
        }
        return item;
    }
    
    
    
}
