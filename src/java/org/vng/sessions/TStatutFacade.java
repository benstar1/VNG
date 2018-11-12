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
import org.vng.entities.TStatut;

/**
 *
 * @author Ben
 */
@Stateless
public class TStatutFacade extends AbstractFacade<TStatut> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TStatutFacade() {
        super(TStatut.class);
    }
    
     public List<SelectItem> getListeStatutItem()
    {
        List<TStatut> listStatuts= findAll();
        List<SelectItem> itemStatut = new ArrayList<>();
        for (TStatut statut : listStatuts)    
        { 
            itemStatut.add(new SelectItem(statut, ""+statut.getStaDesig()));
        }
    
        return itemStatut;
    }
    
}
