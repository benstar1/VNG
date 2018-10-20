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
import org.vng.entities.TIntervenir;

/**
 *
 * @author Ben
 */
@Stateless
public class TIntervenirFacade extends AbstractFacade<TIntervenir> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TIntervenirFacade() {
        super(TIntervenir.class);
    }
    
    
    public  List<TIntervenir>  findListParcelleByCategorie(String categorie){
          Query query;
          query=getEntityManager().createNamedQuery("TIntervenir.findAllByCategorieRole").setParameter("categorieRole",categorie);
          return query.getResultList();
        
    }
    
    
        public  List<TIntervenir>  findListParcelleByTypeParcelleCategorie(String categorie, String typeParcelle ){
          Query query;
          query=getEntityManager().createNamedQuery("TIntervenir.findAllByTypeParcelleRole")
                  .setParameter("categorieRole",categorie)
                  .setParameter("typeParcelle",typeParcelle);
          return query.getResultList();
        
    }
    
     public  List<TIntervenir>  findListParcelleByCategorieParcelle(String numParcelle, String categorie){
          Query query;
          query=getEntityManager().createNamedQuery("TIntervenir.findAllByCategorieRoleParcelle")
                  .setParameter("numParcelle",numParcelle)
                  .setParameter("categorieRole",categorie);
          return query.getResultList();
        
    }
    
    
    
}
