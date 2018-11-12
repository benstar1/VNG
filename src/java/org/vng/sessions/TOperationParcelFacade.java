/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.sessions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.vng.controlers.util.StatOperation;
import org.vng.entities.TModeacquis;
import org.vng.entities.TOperationParcel;

/**
 *
 * @author Ben
 */
@Stateless
public class TOperationParcelFacade extends AbstractFacade<TOperationParcel> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    
     public String executeMaxOperation(String an){
        
         System.out.println("Annee "+an);
         String numoperation =null;
        try{
            Query q=em.createNamedQuery("TOperationParcel.findMaxOperation").setParameter("annee",an+"%");
            numoperation = (String) q.getSingleResult();
        }catch(Exception e){
            System.out.println("Probleme de selection de la plux recente operation "+e);
        }
        return numoperation;
    }
     
    public List<StatOperation> executeNombreOperation(){
        
         //System.out.println("Annee "+an);
         List<StatOperation> statOp=new ArrayList<>();
        try{
            Query q=em.createNamedQuery("TOperationParcel.nombreOperation");
            statOp = (List<StatOperation>) q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme de selection de la plux recente operation "+e);
        }
        return statOp;
    }
     
     
     public List<TOperationParcel> executeListeOperationMode(ArrayList<TModeacquis> mode){
        int i=0;
        List<TOperationParcel> listeOperation =null;
        try{
            Query q=em.createNamedQuery("TOperationParcel.findByMode").setParameter("mode",mode);
            listeOperation=q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme select operation parcelle par mode acquisition : "+e);
        }
        return listeOperation;
    }
     
     public List<TOperationParcel> executeListeOperationCatMode(String CatMode){
        int i=0;
        List<TOperationParcel> listeOperation =null;
        try{
            Query q=em.createNamedQuery("TOperationParcel.findByCategorieMode").setParameter("categorieMode",CatMode);
            listeOperation=q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme select operation parcelle par categorie mode acquisition : "+e);
        }
        return listeOperation;
    }

    

    public TOperationParcelFacade() {
        super(TOperationParcel.class);
    }
    
}
