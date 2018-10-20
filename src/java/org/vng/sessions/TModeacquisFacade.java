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
import javax.persistence.Query;
import org.vng.entities.TModePartage;
import org.vng.entities.TModeacquis;

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

    public TModeacquisFacade() {
        super(TModeacquis.class);
    }
    
    
    
    public  List<TModeacquis>  findListModeAcquisByCategorie(String categorie){
          Query query;
          query=getEntityManager().createNamedQuery("TModeacquis.findByMacCat").setParameter("macCat",categorie);
          return query.getResultList();
        
    }
    
    public List<SelectItem> getListeModeAcquisItem(String categorie)
    {
        List<TModeacquis> list= findListModeAcquisByCategorie(categorie);
        List<SelectItem> item = new ArrayList<>();
        for (TModeacquis object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getMacDesig()));
        }
    
        return item;
    }
    
    
    public List<SelectItem> getListeModeAcquisDEItem()
    {
        List<TModeacquis> list= findListModeAcquisByCategorie("DE");
        List<SelectItem> item = new ArrayList<>();
        for (TModeacquis object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getMacDesig()));
        }
    
        return item;
    }
    
     
    public List<SelectItem> getListeModeAcquisOPItem()
    {
        List<TModeacquis> list= findListModeAcquisByCategorie("OP");
        List<SelectItem> item = new ArrayList<>();
        for (TModeacquis object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getMacDesig()));
        }
    
        return item;
    }
    
    
}
