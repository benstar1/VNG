/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.sessions;

import bj.finances.cfisc.entities.THistStatut;
import bj.finances.cfisc.entities.THistorique;
import bj.finances.cfisc.entities.TMotif;
import bj.finances.cfisc.entities.TRepUnique;
import bj.finances.cfisc.entities.TUtilisateur;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HP
 */
@Stateless
public class THistStatutFacade extends AbstractFacade<THistStatut> {

    @PersistenceContext(unitName = "CFiscPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public THistStatut findByHistStatutLast(TRepUnique ifu) {
        Query query;
        THistStatut res = null;
        //query = em.createNamedQuery("TRepUnique.findByContImmatr").setParameter("contImmatr", ifu);
        query = em.createNamedQuery("THistStatut.findByHistStatutLast")
                //                .setParameter("histStatutDatefin", null)
                .setParameter("histStatutContImmatr", ifu);
        try {
            res = (THistStatut) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Aucun objet trouvé " + e.getMessage());
        }
        return res;
    }

    public void historiserStatut(THistStatut statutNouveau) {
        THistStatut statutEnCours = null;
        // Si le contribuable est actif dans le même centre d'impôt
//        THistStatut tHistStatut;
        System.out.println("Je suis dans historiser");
        Query query = null;

        try {

            statutEnCours = findByHistStatutLast(statutNouveau.getHistStatutContImmatr());
        } catch (Exception nue) {
            System.out.println("Rien trouvé" + nue);
        }
        // Contrôler l'action à faire

        if (statutEnCours != null) {
            System.out.println("existait");
            statutEnCours.setHistStatutDatefin(new Date()); // Mise à jour de la date de fin
            edit(statutEnCours);
            create(statutNouveau);
        } else {
            System.out.println("n'existait pas");
            create(statutNouveau);
        }

    }

    /*
        if ((tHistStatut.getHistStatutContImmatr().getContCentrImpCode().getCentrImpCode().equalsIgnoreCase(tRepUnique.getContCentrCode()))) // Même centre d'impôt
        {
            if (tHistStatut.getHistStatutContImmatr().getContStatut().equalsIgnoreCase("A"))// Contribuable précédemment actif
            {
                // Insérer une désactivation du contribuable
                // Puis Insérer une activation du contribuable dans un autre centre
            }
        }
        if (tHistStatut.getHistStatutContImmatr().getContStatut().equalsIgnoreCase("D"))// Contribuable précédemment non actif
        {

        } // Insérer une désactivation du contribuable
        else if (tHistStatut.getHistStatutContImmatr().getContStatut().equalsIgnoreCase("A"))// Contribuable précédemment actif
        {
                // Insérer une désactivation du contribuable
            // Puis Insérer une activation du contribuable dans un autre centre
        }
        if (tHistStatut.getHistStatutContImmatr().getContStatut().equalsIgnoreCase("D"))// Contribuable précédemment non actif
        {

        }
    }

//         }catch(NoResultException nre){
//            System.out.println("C EST PAS BON " + nre.getMessage());
//        }
     */
    public THistStatutFacade() {
        super(THistStatut.class);
    }

}
