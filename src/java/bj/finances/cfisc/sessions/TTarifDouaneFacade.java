/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.sessions;

import bj.finances.cfisc.entities.TTarifDouane;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JOHNSON
 */
@Stateless
public class TTarifDouaneFacade extends AbstractFacade<TTarifDouane> {
    @PersistenceContext(unitName = "CFiscPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TTarifDouaneFacade() {
        super(TTarifDouane.class);
    }
    
}
