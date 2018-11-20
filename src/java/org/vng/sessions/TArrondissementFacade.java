/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.sessions;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
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
public class TArrondissementFacade extends AbstractFacade<TArrondissement> {

    @Inject
    private TCommuneFacade tCommuneFacade;

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TArrondissementFacade() {
        super(TArrondissement.class);
    }
    
     public List<SelectItem> getListeCompleteArrondissementItem()
    {   
        List<TArrondissement> list= findAll();
        List<SelectItem> item = new ArrayList<>();
        for (TArrondissement object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getArrDesig()));
        }
        return item;
    }
    
  
    public List<TArrondissement> getListeArrondissementByCommuneEncours(){
       
        List<TArrondissement> listeArrondissement =null;
        try{
            Query q=em.createNamedQuery("TArrondissement.findByarrComCode");
            q.setParameter("comCom",tCommuneFacade.executeCommuneEncoours());
            listeArrondissement=q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme de selection de la liste des arrondissement de la commune connectée "+e);
        }
        return listeArrondissement;
    }
    
     public List<SelectItem> getListeArrondissementCommuneEncoursItem()
    {   
        List<TArrondissement> list= getListeArrondissementByCommuneEncours();
        List<SelectItem> item = new ArrayList<>();
        for (TArrondissement object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getArrDesig()));
        }
        return item;
    }
    
     
     
    
}
