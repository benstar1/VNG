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

    public TRoleFacade() {
        super(TRole.class);
    }
    
    public  List<TRole>  findListRoleByCategorie(String categorie){
          Query query;
          query=getEntityManager().createNamedQuery("TRole.findByRolCat").setParameter("rolCat",categorie);
          return query.getResultList();
        
    }
    
    
     public List<SelectItem> getListeRoleByCategorieDEItem()
    {
        List<TRole> list= findListRoleByCategorie("DE");
        List<SelectItem> item = new ArrayList<>();
        for (TRole object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getRolDesig()));
        }
        return item;
    }
    
     
      public List<SelectItem> getListeRoleByCategorieOPItem()
    {
        List<TRole> list= findListRoleByCategorie("OP");
        List<SelectItem> item = new ArrayList<>();
        for (TRole object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getRolDesig()));
        }
        return item;
    }
     
     public List<SelectItem> getListeRoleItem()
    {
        List<TRole> list= findAll();
        List<SelectItem> item = new ArrayList<>();
        for (TRole object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getRolDesig()));
        }
        return item;
    }
}
