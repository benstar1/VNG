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
import org.vng.entities.TDroitExerce;
import org.vng.entities.TIntervenant;
import org.vng.entities.TParcelleBafon;

/**
 *
 * @author Ben
 */
@Stateless
public class TDroitExerceFacade extends AbstractFacade<TDroitExerce> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<TDroitExerce> executeListeDroitExerceCat(TParcelleBafon paramparcelle,String catDroit){
        int i=0;
        List<TDroitExerce> listeDroitExerce =null;
        try{
            Query q=em.createNamedQuery("TDroitExerce.findByParcelleCat").setParameter("parcelle",paramparcelle)
                    .setParameter("catDroit", catDroit);
            listeDroitExerce=q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme select de droit exerce par categorie : "+e);
        }
        return listeDroitExerce;
    }
    
    public List<TDroitExerce> executeListeDroitExerceDetenteur(TParcelleBafon paramparcelle,TIntervenant intervenant,String catDroit){
        int i=0;
        List<TDroitExerce> listeDroitExerce =null;
        try{
            Query q=em.createNamedQuery("TDroitExerce.findByParcelleIntCat").setParameter("parcelle",paramparcelle)
                    .setParameter("catDroit", catDroit)
                    .setParameter("detenteur", intervenant);
            listeDroitExerce=q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme select de droit exerce par categorie : "+e);
        }
        return listeDroitExerce;
    }

    public TDroitExerceFacade() {
        super(TDroitExerce.class);
    }
    
}
