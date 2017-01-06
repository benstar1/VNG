/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.controllers;

//import bj.finances.cfisc.controllers.TRepUniqueController;
import bj.finances.cfisc.controllers.util.JsfUtil;
import bj.finances.cfisc.entities.TCentreImpot;
import bj.finances.cfisc.entities.THistStatut;
import bj.finances.cfisc.entities.TRepUnique;
import bj.finances.cfisc.entities.TUtilisateur;
import bj.finances.cfisc.interfaces.AgentSftpIfu;
import bj.finances.cfisc.sessions.TCentreImpotFacade;
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
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
//@Named(value = "mAJMBean")
@ManagedBean
@SessionScoped
public class MAJIndividuelleMBean extends java.lang.Object {

//    @EJB
//    private TRepUniqueFacade tRepUniqueFacade;
    @EJB
    private bj.finances.cfisc.sessions.TRepUniqueFacade tRepUniqueFacade;
//    private int selectedItemIndex;

    @Inject
    bj.finances.cfisc.controllers.LoginMBean loginBean;
    
    final static Logger logger = Logger.getLogger(AgentSftpIfu.class.getName());

    @Inject
    SendSMSMBean sms;

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

    private TRepUnique current;

    public TRepUnique getCurrent() {
        if (current == null) {
            current = new TRepUnique();
//            selectedItemIndex = -1;
        }
        return current;
    }

    public void setCurrent(TRepUnique current) {
        this.current = current;
    }
    private DataModel items = null;
    private List<TRepUnique> contrib = null;

    private TRepUnique ifu = null;

    private TRepUnique ItemsIfu;

//        public TRepUnique getSelected() {
//        if (current == null) {
//            current = new TRepUnique();
//            selectedItemIndex = -1;
//        }
//        return current;
//    }
    public TRepUnique getItemsIfu() {
        return ItemsIfu;
    }

    public void setItemsIfu(TRepUnique ItemsIfu) {
        this.ItemsIfu = ItemsIfu;
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

    public TRepUnique getIfu(TRepUnique ifumaj) {

        ifu = tRepUniqueFacade.find(ifumaj);
        return ifu;
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

//    private String centre;
//     
//
//    public String getCentre() {
//        return centre;
//    }
//
//    public void setCentre(String centre) {
//        this.centre = centre;
//    }
    public TRepUniqueController gettRepUniqueController() {
        return tRepUniqueController;
    }

    public void settRepUniqueController(TRepUniqueController tRepUniqueController) {
        this.tRepUniqueController = tRepUniqueController;

    }

    public void prepareAfficheMAJIndividuelle(TRepUnique trepunique) {
//        recreateModel();
//        return "MAJIndividuelle";
        if ("A".equals(trepunique.getContStatut())) {
            setStatut("Désactiver");
        } else {
            setStatut("Activer");
        }

    }

    public List<TRepUnique> getContrib() {
        try {
            if (contrib == null) {
//            contrib = tRepUniqueFacade.findAll();
                contrib = tRepUniqueFacade.findContribByImmatPP();
            }
            contrib = tRepUniqueFacade.findContribByImmatPP();
        } catch (NumberFormatException e) {
            System.out.println("FindPP" + e.getMessage());
        }

        return contrib;

    }

    public String contribSelectionne(TRepUnique contrib) {
        System.out.println(" = choisi " + current + "pris " + contrib);
        current.setContImmatr(contrib.getContImmatr());
        ifu = contrib;
        return "";
    }

    public void UpdateTableContrib() {

        System.out.println("current" + current.getContImmatr());
        TRepUnique trepunique = null;
        try {
            trepunique = tRepUniqueFacade.findByContImmatr(current.getContImmatr());
            if (trepunique != null) {
                ifu = trepunique;
                ItemsIfu = trepunique;
                prepareAfficheMAJIndividuelle(trepunique);

                if ("A".equals(ItemsIfu.getContStatut())) {
                    setAfficheStatut("ACTIVE");
                } else {
                    setAfficheStatut("DESACTIVE");
                }
            }
            else
            {
                JsfUtil.addErrorMessage("IFU Incorrect");
            }
        } catch (Exception e) {
            System.out.println("Find IFU " + e.getMessage());
        }

        //return "";
    }

    public void MAJStatut() {

        System.out.println("Je suis dans MAJStatut() " + current + " Et ifu contient quoi " + ifu);
        System.out.println("Je suis avec le statut " + current.getContStatut());

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        String le_login = (String) sessionMap.get("loginUser");
        System.out.println("LE LOGIN " + le_login);

        current = ifu;

        try {
            //System.out.println(centreImpot + " Centre impot choisi -- Test " + current.getContCentrImpCode().getCentrImpCode());
            //System.out.println(" Centre impot ancien " + ifu.getContCentrImpCode().getCentrImpCode());

            TCentreImpot centre = ifu.getContCentrImpCode();

            if ("A".equals(current.getContStatut())) {

                //System.out.println(current.getContStatut() + " Ancien " + ifu.getContStatut());
//         current.setContCentrImpCode(centre);
                current.setContStatut("D");
                System.out.println(" Statut " + current.getContStatut());

                //      current.setContCentrImpCode(centre);
                gettRepUniqueFacade().edit(current);
                setItemsIfu(current);
                setAfficheStatut("DESACTIVE");
                System.out.println(afficheStatut + "-------------");
                //ajout
                setStatut("Activer");

                // Envoi de SMS
                
                Runnable myRunnable = new Runnable(){
                public void run(){
                   System.out.println("Runnable running");
                   SendSMSMBean http = new SendSMSMBean();
                    System.out.println("Testing 1 - Send Http GET request");

                    // Récupération du numéro de phone
                    String dest = "229" + current.getContTel();
                    System.out.println("Le numéro du destinataire est : " + dest);

                    System.out.println("Le numéro IFU est : " + current.getContImmatr());
                    if (current.getContTel() == null) {
                        try {
                            TUtilisateur user = tUtilisateurFacade.find(current.getContImmatr().toString());
                        } catch (Exception e) {
                            System.out.println("Find utilisateur " + e.getMessage());
                        }

                        dest = user.getUtilTel();
                        System.out.println("Le numéro du destinataire trouvé dans Tutilisateur est : " + dest);
                    }
                    
                    try{
                    http.sendGet("22997217745", dest, "Plateforme+IFU+:+Votre+compte+vient+d'etre+desactivé.+Veuillez+vous+rapprocher+de+votre+centre+d'impot");
                    }
                    catch(Exception ex){logger.error("EXCEPTION LORS DE L'ENVOI DU SMS");}
                }
              };

            Thread thread = new Thread(myRunnable);
            thread.start();
            
                System.out.println("THREAD EXECUTE CORRECTEMENT ---------------------------------------------------");
               //historisation
                System.out.println("Début historisation -- Désactivation");

                try {
                    user = tUtilisateurFacade.rechercheUtilconnecte(le_login); 
                    System.out.println("RECHERCHE DE LOGIN LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL" + user.getUtilNom());
                } catch (Exception e) {
                    System.out.println("Erreur " + e.getMessage());
                }

                tHistStatut = new THistStatut(current, user);
                try {
                    tHistStatutFacade.historiserStatut(tHistStatut);
                    System.out.println("JE VIENS D'HISTORISER --------------------------------------------------");
                } catch (Exception e) {
                    System.out.println("Erreur appel historiser" + e);
                }

                System.out.println("Fin historisation -- Désactivation");

                //fin
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TRepUniqueUpdated"));
                //return "View";
            } else {
                current.setContStatut("A");
                System.out.println(" Statut " + current.getContStatut());
                // current.setContCentrImpCode(centre);
                gettRepUniqueFacade().edit(current);
                setItemsIfu(current);
                setAfficheStatut("ACTIVE");
                System.out.println(afficheStatut + "-------------");

                //ajout
                setStatut("Désactiver");
                //fin

                // Envoi de SMS  
                Runnable myRunnable = new Runnable(){
                public void run(){
                   System.out.println("Runnable running");
                   SendSMSMBean http = new SendSMSMBean();
                    System.out.println("Testing 1 - Send Http GET request");

                    // Récupération du numéro de phone
                    String dest = "229" + current.getContTel();
                    System.out.println("Le numéro du destinataire est : " + dest);

                    System.out.println("Le numéro IFU est : " + current.getContImmatr());
                    if (current.getContTel() == null) {
                        try {
                            TUtilisateur user = tUtilisateurFacade.find(current.getContImmatr().toString());
                        } catch (Exception e) {
                            System.out.println("Find utilisateur " + e.getMessage());
                        }

                        dest = user.getUtilTel();
                        System.out.println("Le numéro du destinataire trouvé dans Tutilisateur est : " + dest);
                    }
                    
                    try{
                    http.sendGet("22997217745", dest, "Plateforme+IFU+:+Votre+compte+vient+d'etre+desactivé.+Veuillez+vous+rapprocher+de+votre+centre+d'impot");
                    }
                    catch(Exception ex){logger.error("EXCEPTION LORS DE L'ENVOI DU SMS");}
                }
              };

            Thread thread = new Thread(myRunnable);
            thread.start();

                //historisation
                System.out.println("Début historisation -- Activation");

                try {
                    user = tUtilisateurFacade.rechercheUtilconnecte(le_login); //.findAll().get(0);

                    System.out.println("User " + user);
                } catch (Exception e) {
                    System.out.println("LEO " + e.getMessage());
                }

                tHistStatut = new THistStatut(current, user);
                try {
                    System.out.println("LEO CONN " + user);
                    tHistStatutFacade.historiserStatut(tHistStatut);
                } catch (Exception e) {
                    System.out.println("Erreur appel historiser" + e);
                }

                System.out.println("Fin historisation -- Activation");

                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TRepUniqueUpdated"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            System.out.println("ERRREEEUUUUUUUUUUUUUUUUUR " + e.getMessage());
            e.printStackTrace();
            // return null;
        }
    }

    /**
     * Creates a new instance of MAJMBean
     */
    public MAJIndividuelleMBean() {
        this.ItemsIfu = null;
    }

    public String active;
    public String filename;

    public TCentreImpot cimpot;

    public TCentreImpot getCimpot() {
        return cimpot;
    }

    public void setCimpot(TCentreImpot cimpot) {
        this.cimpot = cimpot;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
