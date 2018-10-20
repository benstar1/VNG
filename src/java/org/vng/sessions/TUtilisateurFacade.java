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
import org.vng.entities.TRole;
import org.vng.entities.TTypebf;
import org.vng.entities.TUtilisateur;

/**
 *
 * @author Ben
 */
@Stateless
public class TUtilisateurFacade extends AbstractFacade<TUtilisateur> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TUtilisateurFacade() {
        super(TUtilisateur.class);
    }
    
    
        public  List<TUtilisateur>  findListUtilisateurByTypeUtilisateur(String typeUtilisateur){
          Query query;
          query=getEntityManager().createNamedQuery("TUtilisateur.findByUtiTYpe").setParameter("typeUtil",typeUtilisateur);
          return query.getResultList();
    }
     
    public List<SelectItem> getListeGeometreItem()
    {
        List<TUtilisateur> list= findListUtilisateurByTypeUtilisateur("GEOME");
        List<SelectItem> item = new ArrayList<>();
        for (TUtilisateur object : list)    
        { 
            item.add(new SelectItem(object, ""+object.getUtiNom()+" "+object.getUtiPrenom()));
        }
    
        return item;
    }
     
}
