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
import org.vng.entities.TConditionpaie;
import org.vng.entities.TModeacquis;

/**
 *
 * @author Ben
 */
@Stateless
public class TConditionpaieFacade extends AbstractFacade<TConditionpaie> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TConditionpaieFacade() {
        super(TConditionpaie.class);
    }

    public List<SelectItem> getListeConditionPaiementItem() {
        List<TConditionpaie> list = findAll();
        List<SelectItem> item = new ArrayList<>();
        for (TConditionpaie object : list) {
            item.add(new SelectItem(object, "" + object.getCopDesig()));
        }

        return item;
    }
}
