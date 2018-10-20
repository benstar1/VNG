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
import org.vng.entities.TTypedexerce;

/**
 *
 * @author Ben
 */
@Stateless
public class TTypedexerceFacade extends AbstractFacade<TTypedexerce> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
     public List<TTypedexerce> executeListeTypeDroit(String Cat){
        int i=0;
        List<TTypedexerce> listedroitExerce =null;
        try{
            Query q=em.createNamedQuery("TTypedexerce.findByTdeCat").setParameter("tdeCat",Cat+"%");
            listedroitExerce=q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme de selection type de droit exerce : type droit Exerce "+e);
        }
        return listedroitExerce;
    }

    public TTypedexerceFacade() {
        super(TTypedexerce.class);
    }
    
    
    public  List<TTypedexerce>  findListTypeDroitExerceByCategorie(String categorie){
          Query query;
          query=getEntityManager().createNamedQuery("TTypedexerce.findByTdeCat").setParameter("tdeCat",categorie);
          return query.getResultList();
            }
    
    
    public List<SelectItem> getListeTypeDroitExerceItem()
    {
        List<TTypedexerce> list= findAll();
        List<SelectItem> item = new ArrayList<>();
        for (TTypedexerce object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getTdeDesig()));
        }
        return item;
    }
    
    
    public List<SelectItem> getListeTypeDroitExerceCategItem(String categorie)
    {
        List<TTypedexerce> list= findListTypeDroitExerceByCategorie(categorie);
        List<SelectItem> item = new ArrayList<>();
        for (TTypedexerce object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getTdeDesig()));
        }
        return item;
    }
    
    public List<SelectItem> getListeTypeDroitExerceDEItem()
    {
        List<TTypedexerce> list= findListTypeDroitExerceByCategorie("DE");
        List<SelectItem> item = new ArrayList<>();
        for (TTypedexerce object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getTdeDesig()));
        }
        return item;
    }
    
    public List<SelectItem> getListeTypeDroitExerceOPItem()
    {
        List<TTypedexerce> list= findListTypeDroitExerceByCategorie("OP");
        List<SelectItem> item = new ArrayList<>();
        for (TTypedexerce object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getTdeDesig()));
        }
        return item;
    }
    
}
