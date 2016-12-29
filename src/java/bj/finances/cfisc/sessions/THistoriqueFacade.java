/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.sessions;

import bj.finances.cfisc.entities.THistorique;
import bj.finances.cfisc.entities.TMotif;
import bj.finances.cfisc.entities.TRepUnique;
import bj.finances.cfisc.entities.TUtilisateur;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class THistoriqueFacade extends AbstractFacade<THistorique> {

    @PersistenceContext(unitName = "CFiscPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public THistoriqueFacade() {
        super(THistorique.class);
    }

    public void historiser(TRepUnique tRepUnique, TMotif tMotif, TUtilisateur tUtilisateur) {
        Object object = null;
        try {
            object = em.createNamedQuery("THistorique.findLastVersion")
                    .setParameter("histContImmatr", tRepUnique.getContImmatr()).getSingleResult();
        } catch (NoResultException nre) {
            System.out.println("C EST PAS BON " + nre.getMessage());
        }
        THistorique tHistorique;
        
            if (object != null) {
                System.out.println("JAI VU");
                tHistorique = (THistorique) object;
                tHistorique.setHistDateFin(new Date());
                edit(tHistorique);
            } else {
                System.out.println("cest egal a null");
            }
            tHistorique = new THistorique(tRepUnique, tMotif, tUtilisateur);
            tHistorique.setHistDateDebut(new Date());
            create(tHistorique);
            System.out.println();
        

    }

}
