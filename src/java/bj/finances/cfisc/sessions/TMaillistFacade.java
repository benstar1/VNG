/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.sessions;


import bj.finances.cfisc.entities.TMailgroup;
import bj.finances.cfisc.entities.TMaillist;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JOHNSON
 */
@Stateless
public class TMaillistFacade extends AbstractFacade<TMaillist> {
    @PersistenceContext(unitName = "CFiscPU")
    private EntityManager em;
    
    public List<TMaillist> findAllByGroup(TMailgroup tMailgroup) {
        List<TMaillist> listeMail = em.createNamedQuery("TMaillist.findByGroupMail").setParameter("groupmail", tMailgroup).getResultList();        
        return listeMail;
    } 

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TMaillistFacade() {
        super(TMaillist.class);
    }
    
}
