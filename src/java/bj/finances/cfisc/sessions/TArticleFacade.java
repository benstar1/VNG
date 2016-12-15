/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.sessions;

import bj.finances.cfisc.entities.TArticle;
import bj.finances.cfisc.entities.TDeclarationDou;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class TArticleFacade extends AbstractFacade<TArticle> {
    @PersistenceContext(unitName = "CFiscPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TArticleFacade() {
        super(TArticle.class);
    }
    
    public List<TArticle> findAllByDeclaration(TDeclarationDou tDeclarationDou){
        List<TArticle> listTArticles = em.createNamedQuery("TArticle.findAllByDeclaration")
                                        .setParameter("tDeclarationDou", tDeclarationDou)
                                        .getResultList();
        return listTArticles;
    }
}
