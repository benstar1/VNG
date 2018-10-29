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
import org.vng.entities.Corridor;

/**
 *
 * @author AAKAKPO
 */
@Stateless
public class CorridorFacade extends AbstractFacade<Corridor> {

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
            System.out.println("Probleme de consolidation de corridor : insertion "+e);
        }
        return i;
    }
    
     public List<String> executeListeCommunetopo(){
        int i=0;
        List<String> listeCorridor =null;
        try{
            Query q=em.createNamedQuery("Corridor.findListCommuneCorridor");
            listeCorridor=q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme de consolidation de parcelle : insertion "+e);
        }
        return listeCorridor;
    }
    
    public List<Corridor> executeListeCorridorVillagetopo(String communeTopo){
        int i=0;
        List<Corridor> listeCorridor =null;
        try{
            Query q=em.createNamedQuery("Corridor.findBySubcountyname").setParameter("subcountyname",communeTopo);
            listeCorridor=q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme de consolidation de parcelle : insertion "+e);
        }
        return listeCorridor;
    }
    
    public CorridorFacade() {
        super(Corridor.class);
    }
    
}
