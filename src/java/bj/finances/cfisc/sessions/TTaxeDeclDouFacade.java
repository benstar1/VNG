/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.sessions;

import bj.finances.cfisc.entities.TArticle;
import bj.finances.cfisc.entities.TTaxeDeclDou;
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
public class TTaxeDeclDouFacade extends AbstractFacade<TTaxeDeclDou> {
    @PersistenceContext(unitName = "CFiscPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TTaxeDeclDouFacade() {
        super(TTaxeDeclDou.class);
    }
    
    public List<TTaxeDeclDou> findAllByArticle(TArticle tArticle) {
        List<TTaxeDeclDou> listTTaxeDou = em.createNamedQuery("TTaxeDeclDou.findAllByArticle")
                                                .setParameter("tArticle", tArticle)
                                                .getResultList();
        return listTTaxeDou;
    }
    
    
    public List findSyntheseTaxes(List<Long> listeInstanceId){
        
        Query query = em.createNamedQuery("TTaxeDeclDou.findSumTax")
                        .setParameter("listeInstanceId", listeInstanceId);
        
        List resultat = query.getResultList();
        
        return resultat;
    }
}
