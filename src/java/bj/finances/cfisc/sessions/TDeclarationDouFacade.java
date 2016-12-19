/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.sessions;

import bj.finances.cfisc.entities.TDeclarationDou;
import bj.finances.cfisc.entities.TRepUnique;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
    
    public TDeclarationDouFacade() {
        super(TDeclarationDou.class);
    }

    public List<TDeclarationDou> findAllByContribuable(TRepUnique tRepUnique) {
        List<TDeclarationDou> listTDeclarationDous = em.createNamedQuery("TDeclarationDou.findAllByContribuable")
                .setParameter("cmpCod", tRepUnique.getContNum())
                .getResultList();
        return listTDeclarationDous;
    }

    public BigDecimal sumTax( String codeTax, Long instanceId){
        
        Query query = em.createNamedQuery("TTaxeDeclDou.findSumTax");
        query.setParameter("codTaxe", codeTax)
             .setParameter("instanceId", instanceId);
        BigDecimal sum = (BigDecimal)query.getSingleResult();
        return sum;
    }
    public List<TDeclarationDou> findAll(int rechercherPar, String bureau, String serie, String numero, Date date){

        String queryString = "SELECT t FROM TDeclarationDou t WHERE t.ideCuoCod =:ideCuoCod ";
        switch(rechercherPar){
            case 2 : // recherche par numero d'enregistrement
                queryString += " AND t.ideRegSer=:ideSer AND t.ideRegNbr=:ideNbr AND t.ideRegDat=:ideDat ";
                break;
            case 3 :
                queryString += " AND t.ideAstSer=:ideSer AND t.ideAstNbr=:ideNbr AND t.ideAstDat=:ideDat ";
                break;
            case 4 :
                queryString += " AND t.ideRcpSer=:ideSer AND t.ideRcpNbr=:ideNbr AND t.ideRcpDat=:ideDat ";
                break;
        }
        
        System.out.println(queryString);

        Query query = em.createQuery(queryString);
        
        query.setParameter("ideCuoCod", bureau)
             .setParameter("ideSer", serie)
             .setParameter("ideNbr", numero)
             .setParameter("ideDat", date);
        
        List<TDeclarationDou> listeDeclaration = query.getResultList();
        System.out.println("AZERTYUIOP  " + listeDeclaration.size());
        if( listeDeclaration != null ) System.out.println(listeDeclaration.size());else System.out.println("il est null");
        return listeDeclaration;
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
