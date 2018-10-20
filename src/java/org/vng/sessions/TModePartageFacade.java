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
import org.vng.entities.TModePartage;

/**
 *
 * @author Ben
 */
@Stateless
public class TModePartageFacade extends AbstractFacade<TModePartage> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TModePartageFacade() {
        super(TModePartage.class);
    }
    
     public List<SelectItem> getListeModePartageItem()
    {
        List<TModePartage> list= findAll();
        List<SelectItem> item = new ArrayList<>();
        for (TModePartage object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getMopLib()));
        }
    
        return item;
    }
    
    
}
