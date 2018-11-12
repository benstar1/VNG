/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.sessions;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.sessions.CopyGroup;
import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.config.ResultType; 
import org.jboss.weld.logging.Category;
import org.vng.entities.TParcelleBafon;




/**
 *
 * @author Ben
 */
@Stateless
public class TParcelleBafonFacade extends AbstractFacade<TParcelleBafon> {

    @PersistenceContext(unitName = "vngPU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    public List<TParcelleBafon> executeListeParcelleEncours(){
        int i=0;
        List<TParcelleBafon> listeparcelles =null;
        try{
            Query q=em.createNamedQuery("TParcelleBafon.findByCommuneEncours");
            listeparcelles=q.getResultList();
        }catch(Exception e){
            System.out.println("Probleme de consolidation de parcelle : insertion "+e);
        }
        return listeparcelles;
    }

    
    public TParcelleBafonFacade() {
        
        super(TParcelleBafon.class);
    }
    
      public void persister(TParcelleBafon entity) {
        getEntityManager().persist(entity);
        
    }
      

      
     public void detacher(TParcelleBafon entity) {
        getEntityManager().detach(entity);        
    }
     
        public void merger(TParcelleBafon entity) {
        getEntityManager().merge(entity);        
    }
        
       public TParcelleBafon copierParcelle() {
        return copierParcelle();
    }
    
    /*
        public  TParcelleBafon  findParcelleByNumeroBafon(String categorie){
          Query query;
          query=getEntityManager().createNamedQuery("TIntervenir.findAllByCategorieRole").setParameter("categorieRole",categorie);
          return query.getResultList();
        
    }*/
  
        
        
     public List<Map> EnqPBA1(String pba_numero){
             
        
            String queryString = "SELECT dep_desig, com_desig, arr_desig, vla_desig,  pba_numero, pba_otho_img, pba_hameau, pba_lieu_dit, pba_superficie,  pba_appartenance,\n" +
        "          pba_code_geo, rol_cat, rol_desig, inv_date_fin,pba_histoire, pba_tbf_code, pba_bati, pba_cloture, pba_clo_macon, pba_clo_banco, pba_clo_claie, pba_clo_haie, pba_clo_autre, pba_nbe_bat, \n" +
        "	   pba_toit_dalle, pba_toit_tuile, pba_toit_tole, pba_toit_paille, pba_toit_claie, pba_mais_ordinare, pba_mais_rez, pba_mais_etage, pba_garage, pba_puits, \n" +
        "	   pba_citerne, pba_cuisine, pba_puisard, pba_wc, pba_prop, pba_vila_code, vla_desig, pba_nb_etage, pba_nom_photo, pba_nom_photo1, pba_pba_numero, pba_sta_autre, \n" +
        "	   pba_titre_foncier, pba_observation, pba_nom_local, pba_ancien_numero, pba_date_finalisation, pba_date_chargement, pba_forme, pba_etat_lieu, pba_tranche, \n" +
        "	   pba_lot, pba_adressage, pba_pfr, pba_adc_reference, pba_adc_date, pba_adc_signataire, pba_adc_image, pba_foncier_reference, pba_foncier_date, pba_foncier_signataire, \n" +
        "	   pba_foncier_image,act_desig, inv_numero, inv_pba_numero, inv_int_numero, inv_num_prop, inv_rol_code, rol_desig, inv_date_deb, inv_date_fin, inv_contreparti, inv_autremodalite, \n" +
        "	   inv_rattachement, inv_autochtone, inv_observation, inv_prix, inv_autre_mod_paie, inv_limitation, inv_limit_leg, inv_limit_vent, inv_limit_don, inv_limit_pret, \n" +
        "	   inv_limite_autre, inv_ref_papier, inv_nat_papier, inv_mac_code, mac_desig,inv_cop_code, cop_desig,intv_date_enquete, inv_enq_code, inv_uti_code, inv_mut_numero, inv_annee_arrive, \n" +
        "	   inv_pv, inv_nom_img, inv_nom_photo, inv_mac_autre, inv_cop_autre, inv_rol_autre, inv_observation_registre, inv_etat_lieu, inv_rattachement_autre, \n" +
        "	   inv_formalise, inv_code_commune, int_image_temoin, int_nom, int_prenom, int_date_nais, int_date_naiss_calcul,eth_desig, int_sexe, int_domicile, int_image_papier1, int_image_papier2, int_image_papier3, int_image_papier4, inv_indetermine, \n" +
        "	   inv_duree, inv_limitation_op, inv_limit_aliener, inv_limit_transmission, inv_limit_planter, inv_limit_recolter_fruit, inv_limit_construire_case, inv_limit_habiter_terrain, \n" +
        "	   inv_limit_autre_op, inv_represente, inv_date_chargement, inv_nom_audio, inv_intervenant_audio, inv_nom_video, inv_intervenant_video, inv_limit_autre_op_chaine, inv_type, \n" +
        "	   inv_de_qui, inv_statut, inv_structure, inv_fonction, inv_dop_representer, inv_dop_detenteur, inv_pba_numero_lim, inv_ci_pp, inv_date_exp_ci_pp, inv_opv_numero, \n" +
        "	   inv_opv_numero_preneur,inv_collectivite, inv_desi_code, uti1.uti_nom as geoNom, uti1.uti_prenom as geoPren, uti2.uti_nom as socioNom, uti2.uti_prenom as socioPren,\n" +
        "	   pv1.pv_date_chargement, pv1.pv_pv, pv1.pv_pv_entete, inv_observation_registre\n" +
        "	FROM \n" +
        "	t_parcelle_bafon, \n" +
        "	t_village, \n" +
        "	t_arrondissement, \n" +
        "	t_commune, \n" +
        "	t_departement, \n" +
        "	t_intervenir, \n" +
        "	t_intervenant, \n" +
        "	t_role, \n" +
        "	t_activite,\n" +
        "	t_ethnie,\n" +
        "	t_pv_parcelle pv1,\n" +
        "	t_pv_parcelle pv2,\n" +
        "	t_utilisateur uti1,\n" +
        "	t_utilisateur uti2,\n" +
        "	t_modeacquis,\n" +
        "	t_conditionpaie\n" +
        "	where \n" +
        "	pba_vila_code = vila_code\n" +
        "	and vila_arr_code = arr_code \n" +
        "	and arr_com_code = com_code \n" +
        "	and com_dep_code = dep_code \n" +
        "	and inv_int_numero = int_numero\n" +
        "	and inv_rol_code = rol_code \n" +
        "	and  rol_cat = 'DE'\n" +
        "	and pba_numero = inv_pba_numero\n" +
        "	and inv_date_fin is null\n" +
        "	and int_act_code = act_code\n" +
        "	and int_eth_code = eth_code\n" +
        "	and pba_code_geo = uti1.uti_code\n" +
        "	and inv_uti_code = pv2.pv_uti_code_enq\n" +
        "	and pv2.pv_uti_code_enq = uti2.uti_code\n" +
        "	and pba_numero = pv1.pv_pba_numero\n" +
        "	and pba_numero = pv2.pv_pba_numero\n" +
        "	and mac_code = inv_mac_code\n" +
        "	and cop_code = inv_cop_code\n" +
        "       and pba_tbf_code = 'TBF01'   "+
        "	and pba_numero = #pba_num  ";
                 
        
        
        //System.out.println(queryString);

        Query query = em.createNativeQuery(queryString);        
        query.setParameter("pba_num", pba_numero);
        query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        
        List<Map> listeDeclaration = query.getResultList();        
        if( listeDeclaration != null ) System.out.println(listeDeclaration.size());else System.out.println("EnqParcBaf est null");
        return listeDeclaration;
    }
         
    public List<Map> EnqPBA2(String pba_numero){
             
        String queryString = "select \n" +
                            "int_nom,\n" +
                            "int_prenom,\n" +
                            "int_sexe,\n" +
                            "int_domicile,\n" +
                            "inv_rattachement,\n" +
                            "rol_desig\n" +
                            "from \n" +
                            "t_intervenir, \n" +
                            "t_role, \n" +
                            "t_intervenant,\n" +
                            "t_activite,\n" +
                            "t_ethnie\n" +
                            "where \n" +
                            "inv_pba_numero = #pba_num \n" +
                            "and inv_int_numero = int_numero\n" +
                            "and inv_rol_code = rol_code \n" +
                            "and  rol_cat = 'OP'\n" +
                            "and inv_date_fin is null\n" +
                            "and int_act_code = act_code\n" +
                            "and int_eth_code = eth_code";
               
        //System.out.println(queryString);

        Query query = em.createNativeQuery(queryString);        
        query.setParameter("pba_num", pba_numero);
        query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        
        List<Map> listeDeclaration = query.getResultList();        
        if( listeDeclaration != null ) System.out.println(listeDeclaration.size());else System.out.println("DroitsOP est null");
        return listeDeclaration;
    }
    
   
    public List<Map> EnqPBA3(String pba_numero){
             
        String queryString = "select \n" +
                            "int_nom,\n" +
                            "int_prenom,\n" +
                            "tde_desig,\n" +
                            "mac_desig,\n" +
                            "inv_num_prop,\n" +
                            "inv_date_deb,\n" +
                            "inv_duree,\n" +
                            "inv_limit_aliener\n" +
                            "from \n" +
                            "t_intervenir,\n" +
                            "t_modeacquis,\n" +
                            "t_intervenant,\n" +
                            "t_role,\n" +
                            "t_droit_exerce,\n" +
                            "t_typedexerce\n" +
                            "where \n" +
                            "rol_cat='OP'\n" +
                            "and mac_code = inv_mac_code\n" +
                            "and inv_int_numero = int_numero\n" +
                            "and rol_code = inv_rol_code\n" +
                            "and inv_date_fin is null\n" +
                            "and inv_pba_numero = dre_pba_numero\n" +
                            "and inv_int_numero = dre_int_numero\n" +
                            "and dre_tde_code = tde_code\n" +
                            "and tde_cat = 'OP1'\n" +
                            "and inv_pba_numero = #pba_num \n" +
                            "order by inv_pba_numero, int_nom, int_prenom";
               
       // System.out.println(queryString);

        Query query = em.createNativeQuery(queryString);        
        query.setParameter("pba_num", pba_numero);
        query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        
        List<Map> listeDeclaration = query.getResultList();        
        if( listeDeclaration != null ) System.out.println("Nre enr Caract DOP : " + listeDeclaration.size());else System.out.println("CaractOP est null");
        return listeDeclaration;
    }
         
    
    public List<Map> EnqPBA4(String pba_numero){
             
        String queryString = "select \n" +
                            "int_nom,\n" +
                            "int_prenom,\n" +
                            "rol_cat,\n" +
                            "inv_pba_numero\n" +
                            "from \n" +
                            "t_intervenir, \n" +
                            "t_role, \n" +
                            "t_intervenant\n" +
                            "where \n" +
                            "inv_pba_numero = #pba_num \n" +
                            "and \n" +
                            "inv_int_numero = int_numero\n" +
                            "and inv_rol_code = rol_code \n" +
                            "and  rol_cat in ('LIM','TEM_ACH','TEM_VENT')\n" +
                            "and inv_date_fin is null\n" +
                            "order by inv_pba_numero";
               
        //System.out.println(queryString);

        Query query = em.createNativeQuery(queryString);        
        query.setParameter("pba_num", pba_numero);
        query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
        
        List<Map> listeDeclaration = query.getResultList();        
        if( listeDeclaration != null ) System.out.println("Nbre enr LIM : " + listeDeclaration.size());else System.out.println("LIM est null");
        return listeDeclaration;
    }
    
    
    
}
