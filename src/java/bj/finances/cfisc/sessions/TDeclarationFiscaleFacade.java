/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.sessions;

import bj.finances.cfisc.entities.TDeclarationFiscale;
import bj.finances.cfisc.entities.TEntDeclaration;
import bj.finances.cfisc.entities.TRepUnique;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HP
 */
@Stateless
public class TDeclarationFiscaleFacade extends AbstractFacade<TDeclarationFiscale> {
    @PersistenceContext(unitName = "CFiscPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TDeclarationFiscaleFacade() {
        super(TDeclarationFiscale.class);
    }
    
     public List<TDeclarationFiscale> findListdeclar(TEntDeclaration entdeclar) {
        Query query;
        query = em.createNamedQuery("TDeclarationFiscale.findByNumEntDecl").setParameter("entdeclar", entdeclar);
        return query.getResultList();
    }
     
    public List<TDeclarationFiscale> findAllByContribuable(TRepUnique tRepUnique) {

        List<TDeclarationFiscale> listTDeclarationDous = em.createNamedQuery("TDeclarationFiscale.findAllByContribuable")
                                                .setParameter("cmpCod", tRepUnique.getContImmatr())
                                                .getResultList();
        return listTDeclarationDous;
    }
     
     

    
}
