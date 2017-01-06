/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.controllers;

//import bj.finances.cfisc.controllers.TRepUniqueController;
import bj.finances.cfisc.controllers.util.JsfUtil;
import bj.finances.cfisc.entities.TCentreImpot;
import bj.finances.cfisc.entities.TGroupe;
import bj.finances.cfisc.entities.THistStatut;
import bj.finances.cfisc.entities.TRepUnique;
import bj.finances.cfisc.entities.TUtilisateur;
import bj.finances.cfisc.sessions.TCentreImpotFacade;
import bj.finances.cfisc.sessions.TGroupeFacade;
import bj.finances.cfisc.sessions.THistStatutFacade;
import bj.finances.cfisc.sessions.TRepUniqueFacade;
import bj.finances.cfisc.sessions.TUtilisateurFacade;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.ejb.EJB;
//import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.inject.Inject;

/**
 *
 * @author user
 */
//@Named(value = "mAJMBean")
@ManagedBean
@SessionScoped
public class MAJUtilisateurMBean extends java.lang.Object {
    
    public TGroupeFacade gettGroupeFacade() {
        return tGroupeFacade;
    }
    
    public void settGroupeFacade(TGroupeFacade tGroupeFacade) {
        this.tGroupeFacade = tGroupeFacade;
    }
    
    @EJB
    private TGroupeFacade tGroupeFacade;
    
    private TGroupe groupe;
    
    public TGroupe getGroupe() {
        return groupe;
    }
    
    public void setGroupe(TGroupe groupe) {
        this.groupe = groupe;
    }
    
    private List<TUtilisateur> utilisateur = null;
//    @EJB
//    private TRepUniqueFacade tRepUniqueFacade;
    @EJB
    private bj.finances.cfisc.sessions.TRepUniqueFacade tRepUniqueFacade;
//    private int selectedItemIndex;

    @Inject
    bj.finances.cfisc.controllers.LoginMBean loginBean;
    
    public TRepUniqueFacade gettRepUniqueFacade() {
        return tRepUniqueFacade;
    }
    
    public void settRepUniqueFacade(TRepUniqueFacade tRepUniqueFacade) {
        this.tRepUniqueFacade = tRepUniqueFacade;
    }
    
    @EJB
    private TCentreImpotFacade tCentreImpotFacade;
    
    private TCentreImpot centreImpot;
    
    public TCentreImpotFacade gettCentreImpotFacade() {
        return tCentreImpotFacade;
    }
    
    public void settCentreImpotFacade(TCentreImpotFacade tCentreImpotFacade) {
        this.tCentreImpotFacade = tCentreImpotFacade;
    }
    
    public TCentreImpot getCentreImpot() {
        return centreImpot;
    }
    
    public void setCentreImpot(TCentreImpot centreImpot) {
        this.centreImpot = centreImpot;
    }
    
    private TUtilisateur current;
    
    public TUtilisateur getCurrent() {
        if (current == null) {
            current = new TUtilisateur();
//            selectedItemIndex = -1;
        }
        return current;
    }
    
    public void setCurrent(TUtilisateur current) {
        this.current = current;
    }
    private DataModel items = null;
    private List<TRepUnique> contrib = null;
    
    private TUtilisateur util = null;
    
    private TUtilisateur ItemsUtil;

//        public TRepUnique getSelected() {
//        if (current == null) {
//            current = new TRepUnique();
//            selectedItemIndex = -1;
//        }
//        return current;
//    }
    public TUtilisateur getItemsUtil() {
        return ItemsUtil;
    }
    
    public void setItemsUtil(TUtilisateur ItemsUtil) {
        this.ItemsUtil = ItemsUtil;
    }

//        private Long ifuChoisi;
//
//
//    public Long getIfuChoisi() {
//        return ifuChoisi;
//    }
//
//    public void setIfuChoisi(Long ifuChoisi) {
//        this.ifuChoisi = ifuChoisi;
//    }
    // ajout
    String statut = "";
    
    String afficheStatut = "";
    
    public String getAfficheStatut() {
        return afficheStatut;
    }
    
    public void setAfficheStatut(String afficheStatut) {
        this.afficheStatut = afficheStatut;
    }
    
    public String getStatut() {
        return statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }
// fin 

    private THistStatut tHistStatut;
    
    @EJB
    private TUtilisateurFacade tUtilisateurFacade;
    
    private TUtilisateur user;
    
    public TUtilisateurFacade gettUtilisateurFacade() {
        return tUtilisateurFacade;
    }
    
    public void settUtilisateurFacade(TUtilisateurFacade tUtilisateurFacade) {
        this.tUtilisateurFacade = tUtilisateurFacade;
    }
    
    public TUtilisateur getUser() {
        return user;
    }
    
    public void setUser(TUtilisateur user) {
        this.user = user;
    }
    
    public TUtilisateur getUtil(TUtilisateur utilmaj) {
        
        util = tUtilisateurFacade.find(utilmaj);
        return util;
    }
    
    @EJB
    private THistStatutFacade tHistStatutFacade;

    // @Inject
    TRepUniqueController tRepUniqueController;
    
    private TRepUnique tRepUnique;
    private List<TRepUnique> chargement = null;
    
    public List<TRepUnique> getChargement() {
        return chargement;
    }
    
    public void setChargement(List<TRepUnique> chargement) {
        this.chargement = chargement;
    }
    
    private String centre;
    
    public String getCentre() {
        return centre;
    }
    
    public void setCentre(String centre) {
        this.centre = centre;
    }
    
    public TRepUniqueController gettRepUniqueController() {
        return tRepUniqueController;
    }
    
    public void settRepUniqueController(TRepUniqueController tRepUniqueController) {
        this.tRepUniqueController = tRepUniqueController;
        
    }
    
    public void prepareAfficheMAJIndividuelle(TUtilisateur tutilisateur) {
//        recreateModel();
//        return "MAJIndividuelle";
        if ("A".equals(tutilisateur.getUtilActif())) {
            setStatut("Désactiver");
        } else {
            setStatut("Activer");
        }
        
    }
    
    public String UpdateTableUtil() {
        TUtilisateur tutilisateur = tUtilisateurFacade.find(util.getUtilLogin());
        ItemsUtil = tutilisateur;
        prepareAfficheMAJIndividuelle(tutilisateur);
        
        if ("A".equals(ItemsUtil.getUtilActif())) {
            setAfficheStatut("ACTIVE");
        } else {
            setAfficheStatut("DESACTIVE");
        }
        
        return "";
    }
    
    public void MAJStatut() {
        
        System.out.println("Je suis dans MAJStatut() " + current + " Et ifu contient quoi " + util);
        System.out.println("Je suis avec le statut " + current.getUtilActif());

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        String le_login = (String) sessionMap.get("loginUser");
        System.out.println("LE LOGIN " + le_login);

        current = util;

        try {
            System.out.println(centreImpot + " Centre impot choisi -- Test " + current.getGroupe().getGroupId());
            System.out.println(" Centre impot ancien " + util.getGroupe().getGroupId());

            TGroupe groupe = util.getGroupe();

            if ("A".equals(current.getUtilActif())) {

                //System.out.println(current.getContStatut() + " Ancien " + ifu.getContStatut());
//         current.setContCentrImpCode(centre);
//                current.setUtilNumci(util.getUtilNumci());
//                current.setUtilDateexpci(util.getUtilDateexpci());
//                current.setUtilLieudelivrci(util.getUtilLieudelivrci());

                current.setUtilActif("D");
                System.out.println(" Statut " + current.getUtilActif());

                //      current.setContCentrImpCode(centre);
                gettUtilisateurFacade().edit(current);
                setItemsUtil(current);
                setAfficheStatut("DESACTIVE");
                System.out.println(afficheStatut + "-------------");
                //ajout
                setStatut("Activer");

                //historisation
                System.out.println("Début historisation -- Désactivation");

//                try {
//                    user = tUtilisateurFacade.rechercheUtilconnecte(le_login); //.findAll().get(0);
//                 //user = loginBean.getUtilisateurconnecte();
////                    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
////                    Map<String, Object> sessionMap  = externalContext.getSessionMap();
////                    String le_login = (String) sessionMap.get("loginUser");
////                    System.out.println("LE LOGIN " + le_login);
//                    
//                System.out.println("RECHERCHE DE LOGIN " + user.getUtilNom());
//                } catch (Exception e) {
//                    System.out.println("Erreur " + e.getMessage());
//                }
//                
//                
//            tHistStatut  = new THistStatut(current, user);
//                try {
//                    tHistStatutFacade.historiserStatut(tHistStatut);     
//                } catch (Exception e) { System.out.println("Erreur appel historiser" +e);
//                }
//            
                System.out.println("Fin historisation -- Désactivation");

                //fin
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TRepUniqueUpdated"));
                //return "View";
            } else {

                // Cas Spécifique du contribuable
                if ("N".equals(current.getUtilActif())) {
                    System.out.println("Hé, il est un nouveau");
                    System.out.println(util.getUtilNumci());
                    System.out.println(util.getUtilDateexpci());
                    System.out.println(util.getUtilLieudelivrci());
                    current.setUtilNumci(util.getUtilNumci());
                    current.setUtilDateexpci(util.getUtilDateexpci());
                    current.setUtilLieudelivrci(util.getUtilLieudelivrci());
                }
                System.out.println(" Statut Nouveau" + current.getUtilActif());
                current.setUtilActif("A");
                System.out.println(" Statut " + current.getUtilActif());
                // current.setContCentrImpCode(centre);
                gettUtilisateurFacade().edit(current);
                setItemsUtil(current);
                setAfficheStatut("ACTIVE");
                System.out.println(afficheStatut + "-------------");

                //ajout
                setStatut("Désactiver");
                //fin

                //historisation
                System.out.println("Début historisation -- Activation");

//                 try {
//                    user = tUtilisateurFacade.rechercheUtilconnecte(le_login); //.findAll().get(0);
//                 
//                System.out.println("User " + user);
//                } catch (Exception e) {
//                    System.out.println("LEO " + e.getMessage());
//                }
//                
//            tHistStatut  = new THistStatut(current, user);
//                try {
//                    System.out.println("LEO CONN " + user);
//                    tHistStatutFacade.historiserStatut(tHistStatut);     
//                } catch (Exception e) { System.out.println("Erreur appel historiser" +e);
//                }
                System.out.println("Fin historisation -- Activation");

                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TRepUniqueUpdated"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            // return null;
        }
    }
    
    public List<TUtilisateur> getUtilisateur() {
        try {
//                if (utilisateur == null) {
////            contrib = tRepUniqueFacade.findAll();
//            utilisateur = tUtilisateurFacade.findAll();
//        }
            utilisateur = tUtilisateurFacade.findAll();
        } catch (NumberFormatException e) {
            System.out.println("Find" + e.getMessage());
        }
        
        return utilisateur;
        
    }
    
    public String UtilisateurSelectionne(TUtilisateur utilisateur) {
        System.out.println(" = choisi " + current + "pris " + utilisateur);
        current.setUtilLogin(utilisateur.getUtilLogin());
        util = utilisateur;
        return "";
    }

    /**
     * Creates a new instance of MAJMBean
     */
    public MAJUtilisateurMBean() {
        this.ItemsUtil = null;
    }
    
    public String active;
//    public String filename;

//    public TCentreImpot cimpot;
//
//    public TCentreImpot getCimpot() {
//        return cimpot;
//    }
//
//    public void setCimpot(TCentreImpot cimpot) {
//        this.cimpot = cimpot;
//    }
    public String getActive() {
        return active;
    }
    
    public void setActive(String active) {
        this.active = active;
    }

//    public String getFilename() {
//        return filename;
//    }
//
//    public void setFilename(String filename) {
//        this.filename = filename;
//    }
}
