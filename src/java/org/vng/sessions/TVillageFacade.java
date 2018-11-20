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
import org.vng.entities.TArrondissement;
import org.vng.entities.TCommune;
import org.vng.entities.TVillage;

/**
 *
 * @author Ben
 */
@Stateless
public class TVillageFacade extends AbstractFacade<TVillage> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TVillageFacade() {
        super(TVillage.class);
    }
    
    
    public List<TVillage> listeVillageByArrondissement(String codeArrondissement){
        List<TVillage> listeVillage =null;
        try{
            Query q=em.createNamedQuery("TVillage.findByVilaArro");
            q.setParameter("arroCode",codeArrondissement);
            listeVillage=q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme de selection de la liste des village de l'arrondissement selectionnée"+e);
        }
        return listeVillage;
    }
    
    
    public List<SelectItem> getListeVillageByArrondissementItem(String codeArrondissement)
    {   
        List<TVillage> list= listeVillageByArrondissement(codeArrondissement);
        List<SelectItem> item = new ArrayList<>();
        for (TVillage object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getVlaDesig()));
        }
        return item;
    }
    
    
    public List<SelectItem> getListeCompleteVillageItem()
    {   
        List<TVillage> list= findAll();
        List<SelectItem> item = new ArrayList<>();
        for (TVillage object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getVlaDesig()));
        }
        return item;
    }
    
    
}
