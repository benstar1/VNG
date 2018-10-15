/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.sessions;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.vng.entities.Parcelle;

/**
 *
 * @author AAKAKPO
 */
@Stateless
public class ParcelleFacade extends AbstractFacade<Parcelle> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
public int executeInsertSQL(String sql){
        int i=0;
        try{
            Query q=em.createNativeQuery(sql);
            i=q.executeUpdate();
        }catch(Exception e){
            System.out.println("Probleme de consolidation de parcelle : insertion "+e);
        }
        return i;
    }
   
     public List<String> executeListevillagetopo(){
        int i=0;
        List<String> listeparcelles =null;
        try{
            Query q=em.createNamedQuery("Parcelle.findListVillage");
            listeparcelles=q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme de consolidation de parcelle : insertion "+e);
        }
        return listeparcelles;
    }
     
     public List<Parcelle> executeListeParcelleVillagetopo(String villageTopo){
        int i=0;
        List<Parcelle> listeparcelles =null;
        try{
            Query q=em.createNamedQuery("Parcelle.findByVillagename").setParameter("villagename",villageTopo);
            listeparcelles=q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme de consolidation de parcelle : insertion "+e);
        }
        return listeparcelles;
    }
    
    public ParcelleFacade() {
        super(Parcelle.class);
    }
    
}
