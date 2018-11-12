/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.sessions;

import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.vng.entities.TIntervenir;
import org.vng.entities.TParcelleBafon;

/**
 *
 * @author Ben
 */
@Stateless
public class TIntervenirFacade extends AbstractFacade<TIntervenir> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<TIntervenir> executeListeIntervRole(TParcelleBafon parcelle,String categorieDroit){
        int i=0;
        List<TIntervenir> listeintervenir =null;
        try{
            Query q=em.createNamedQuery("TIntervenir.findDetentDroitAdmin");
            q.setParameter("categorierole",categorieDroit);
            q.setParameter("parcelle",parcelle);
            listeintervenir=q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme de selection des intervenir selon role : selection "+e);
        }
        return listeintervenir;
    }
    
    public String executeMaxIntervenir(String an){
        
         System.out.println("Annee "+an);
         String numintervenir =null;
        try{
            Query q=em.createNamedQuery("TIntervenir.findMaxIntervenir").setParameter("annee",an+"%");
            /* numintervenir = (String) em.createNativeQuery("select max(substring(inv_numero, 5)::numeric)::text lastyear  from t_intervenir where substring(inv_numero, 1,4) = ? ")
                .setParameter(1, an).getSingleResult();
           */
            numintervenir = (String) q.getSingleResult();
        }catch(Exception e){
            System.out.println("Probleme de selection de la plux recente operation "+e);
        }
        return numintervenir;
    }

    public TIntervenirFacade() {
        super(TIntervenir.class);
    }
    
    
    public  List<TIntervenir>  findListParcelleByCategorie(String categorie){
          Query query;
          query=getEntityManager().createNamedQuery("TIntervenir.findAllByCategorieRole").setParameter("categorieRole",categorie);
          return query.getResultList();
        
    }
    
    
        public  List<TIntervenir>  findListParcelleByTypeParcelleCategorie(String categorie, String typeParcelle ){
          Query query;
          query=getEntityManager().createNamedQuery("TIntervenir.findAllByTypeParcelleRole")
                  .setParameter("categorieRole",categorie)
                  .setParameter("typeParcelle",typeParcelle);
          return query.getResultList();
        
    }
    
     public  List<TIntervenir>  findListParcelleByCategorieParcelle(String numParcelle, String categorie){
          Query query;
          query=getEntityManager().createNamedQuery("TIntervenir.findAllByCategorieRoleParcelle")
                  .setParameter("numParcelle",numParcelle)
                  .setParameter("categorieRole",categorie);
          return query.getResultList();
        
    }
     
     public String genererNumIntervenir() {
        //Date d = new Date();
        String numero= "";
        Calendar calendar = Calendar.getInstance();
        int ann = calendar.get(Calendar.YEAR);//
        String an = String.valueOf(ann);
        String chainesuffixe = "";
        long numsuivant;

        String numinterv = null;
        try {
            numinterv = executeMaxIntervenir(an);
            if (numinterv == null) {
                numero = an + "00000000000000000001";
            } else {
                chainesuffixe = numinterv.substring(4);
             //   System.out.println("chainesuffixe recuperer " + chainesuffixe);                
                 numsuivant = Long.valueOf(chainesuffixe) + 1;
                chainesuffixe = String.format("%020d", numsuivant);
                numero = an + chainesuffixe;
            }
        } catch (Exception e) {

        }

        return numero;
    }
    
    
    
}
