/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.sessions;

import bj.finances.cfisc.entities.TDeclarationDou;
import bj.finances.cfisc.entities.TRepUnique;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HP
 */
@Stateless
public class TDeclarationDouFacade extends AbstractFacade<TDeclarationDou> {

    @PersistenceContext(unitName = "CFiscPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TDeclarationDou findByInstanceId(Long id) {
        Query query;
        TDeclarationDou res = null;
        query = em.createNamedQuery("TDeclarationDou.findByInstanceId").setParameter("instanceid", id);
        try {
            res = (TDeclarationDou) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Aucun objet trouvé " + e.getMessage());
        }
        return res;
    }
    
    public void supprimer(Long instanceid){
        
       Query query = em.createNamedQuery("TDeclarationDou.deleteDeclaration");
       query.setParameter("instanceid", instanceid);
       query.executeUpdate();
       
    }
        
    public TDeclarationDouFacade() {
        super(TDeclarationDou.class);
    }

    public List<TDeclarationDou> findAllByContribuable(TRepUnique tRepUnique) {
        List<TDeclarationDou> listTDeclarationDous = em.createNamedQuery("TDeclarationDou.findAllByContribuable")
                .setParameter("cmpCod", tRepUnique.getContNum())
                .getResultList();
        return listTDeclarationDous;
    }

    public List<TDeclarationDou> findAll(TRepUnique tRepUnique, String typeDeclaration, String typeDate, java.sql.Date dateDebut, java.sql.Date dateFin) {
        if( tRepUnique == null ) return new ArrayList<>();
        String queryString = "SELECT t FROM TDeclarationDou t WHERE (t.cmpConCod =:cmpCod OR t.cmpExpCod =:cmpCod) ";

        if ("1".equals(typeDeclaration)) {
            queryString += " AND t.ideTypSad = 'IM' ";
        } else if ("2".equals(typeDeclaration)) {
            queryString += " AND t.ideTypSad = 'EX' ";
        }

        switch (typeDate) {
            case "1": {
                queryString += " AND t.ideRegDat >= :dateMin AND t.ideRegDat <= :dateMax ";
                break;
            }
            case "2": {
                queryString += " AND t.ideAstDat >= :dateMin AND t.ideAstDat <= :dateMax ";
                break;
            }
            case "3": {
                queryString += " AND t.ideRcpDat >= :dateMin AND t.ideRcpDat <= :dateMax ";
                break;
            }
        }

        System.out.println(queryString);

        Query query = em.createQuery(queryString);
        query.setParameter("cmpCod", tRepUnique.getContNum());

        if ((dateDebut != null && dateFin != null) && ("1".equals(typeDate) || "2".equals(typeDate) || "3".equals(typeDate))) {
            query.setParameter("dateMin", dateDebut)
                    .setParameter("dateMax", dateFin);
        }
        
        List<TDeclarationDou> listeDeclaration = query.getResultList();

        if( listeDeclaration != null ) System.out.println(listeDeclaration.size());else System.out.println("il est null");
        return listeDeclaration;
    }

}
