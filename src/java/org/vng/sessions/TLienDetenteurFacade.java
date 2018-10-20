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
import org.vng.entities.TLienDetenteur;
import org.vng.entities.TLignage;

/**
 *
 * @author Ben
 */
@Stateless
public class TLienDetenteurFacade extends AbstractFacade<TLienDetenteur> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TLienDetenteurFacade() {
        super(TLienDetenteur.class);
    }
    
    
     public List<SelectItem> getListeLienAvecDetenteurItem()
    {
        List<TLienDetenteur> list= findAll();
        List<SelectItem> item = new ArrayList<>();
        for (TLienDetenteur object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getLienDesig()));
        }
    
        return item;
    }
    
}
