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
     
     public List<Object[]> listeParcelleLimit(String numParcelle){
     List<Object[]> listparcelle=null;
         String sql="select p.par_pba_numero from parcelle p where ST_Touches((select p2.geom from parcelle p2 where p2.par_pba_numero ='"+numParcelle+"'),p.geom)=true";
     try{
            Query q=em.createNativeQuery(sql);
            listparcelle= q.getResultList();
            System.out.println("taille    "+listparcelle.size());
        }catch(Exception e){
            System.out.println("probleme de selection de limitrophes : "+e);
        }
     
     return listparcelle;
     }
     
      public Object executCalculDistanceLimite(String numParcelle,String numParcelleLimite){
            Object mesure=null;
         String sql="select ST_Length(ST_Intersection((select p.geom from parcelle p where p.par_pba_numero ='"+numParcelle+"'),(select p2.geom from parcelle p2 where p2.par_pba_numero ='"+numParcelleLimite+"'))::geography)";
     try{
            Query q=em.createNativeQuery(sql);
            mesure= q.getSingleResult();
            //System.out.println("taille    "+listparcelle.size());
        }catch(Exception e){
            System.out.println("probleme de selection de limitrophes : "+e);
        }
     
     return mesure;
     }
    
    public ParcelleFacade() {
        super(Parcelle.class);
    }
    
}
