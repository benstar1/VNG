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
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.vng.entities.TCommune;

/**
 *
 * @author Ben
 */
@Stateless
public class TCommuneFacade extends AbstractFacade<TCommune> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

     public TCommune executeCommuneEncoours(){
        int i=0;
         TCommune commune =null;
        try{
            Query q=em.createNamedQuery("TCommune.findByComEncours");
            commune = (TCommune) q.getSingleResult();
        }catch(Exception e){
            System.out.println("Probleme de selection de la commune de deploiement "+e);
        }
        return commune;
    }
    
    public TCommuneFacade() {
        super(TCommune.class);
    }
    
    
    
    public List<SelectItem> getListeCompletCommuneItem()
    {
        List<TCommune> list= findAll();
        List<SelectItem> item = new ArrayList<>();
        for (TCommune object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getComDesig()));
        }
        return item;
    }
    

    
    
    public List<SelectItem> getListeCommuneByDepartementItem(String departementCode)
    {   
        List<TCommune> list= findAll();
        List<SelectItem> item = new ArrayList<>();
        for (TCommune object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getComDesig()));
        }
        return item;
    }
        
        
        
        
    public List<TCommune> listeCommuneByDepartement(String departementCode){
       
        List<TCommune> listeCommune =null;
        try{
            Query q=em.createNamedQuery("TCommune.findByDepartement");
            q.setParameter("departCode",departementCode);
            listeCommune=q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme de selection des communes par département "+e);
        }
        return listeCommune;
    }
    
    
}
