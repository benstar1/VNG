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
import org.vng.entities.TTypeDomaineParcel;

/**
 *
 * @author Ben
 */
@Stateless
public class TTypeDomaineParcelFacade extends AbstractFacade<TTypeDomaineParcel> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TTypeDomaineParcelFacade() {
        super(TTypeDomaineParcel.class);
    }
    
    public List<SelectItem> getListeTypeDomaineItem()
    {
        List<TTypeDomaineParcel> list= findAll();
        List<SelectItem> item = new ArrayList<>();
        for (TTypeDomaineParcel object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getTydoLib()));
        }
    
        return item;
    }
    
}