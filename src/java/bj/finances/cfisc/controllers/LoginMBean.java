/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.controllers;

import bj.finances.cfisc.entities.TUtilisateur;
import bj.finances.cfisc.sessions.TUtilisateurFacade;
import com.sun.faces.context.SessionMap;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
//import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
 *
 * @author DELL
 */
@ManagedBean(name = "loginMBean")
@SessionScoped
public class LoginMBean implements Serializable {

    @EJB
    private TUtilisateurFacade tUtilisateurFacade;
    private TUtilisateur utilisateurconnecte = new TUtilisateur();
    private boolean contribuable;
    private TUtilisateur utilisateur = new TUtilisateur();

    @Inject
    ConvertirMD5 md5;

    public TUtilisateur getUtilisateurconnecte() {
        return utilisateurconnecte;
    }

    public void setUtilisateurconnecte(TUtilisateur utilisateurconnecte) {
        this.utilisateurconnecte = utilisateurconnecte;
    }
    String uname;
    String password;
    String nom = null;
    String prenom;

    //champs pour la fenetre de changement du mot de passe
    private String currentPassWord;
    private String newPassword;
    private String confirmPassword;

    public String MD5(String md5) throws NoSuchAlgorithmException {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        byte[] array = md.digest(md5.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    public void changePassword() {
        // if( currentPassWord )
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

            System.out.println("TOTORINO         : " + md5.generateMD5(currentPassWord));
            System.out.println("TOTORINO est un frere        : " + md5.generateMD5(currentPassWord));
            Map<String, Object> sessionMap = externalContext.getSessionMap();
            TUtilisateur connectedUser = (TUtilisateur) sessionMap.get("utilisateurConnecte");
            if (connectedUser.getUtilPassword().equals(md5.generateMD5(currentPassWord)) && newPassword.equals(confirmPassword)) {
                connectedUser.setUtilPassword(md5.generateMD5(newPassword));
                tUtilisateurFacade.edit(connectedUser);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mot de passe modifié"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mot de passe incorect"));
            }


    }

    public void beforePasswordChange() {
        currentPassWord = null;
        newPassword = null;
        confirmPassword = null;
    }

    public String getNom() {
        return nom;
    }

    public String getCurrentPassWord() {
        return currentPassWord;
    }

    public void setCurrentPassWord(String currentPassWord) {
        this.currentPassWord = currentPassWord;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    String raisonsocial;
    Long ifu;

    public Long getIfu() {
        return ifu;
    }

    public void setIfu(Long ifu) {
        this.ifu = ifu;
    }
    private Boolean connecte;

    public Boolean getConnecte() {
        return connecte;
    }

    public void setConnecte(Boolean connecte) {
        this.connecte = connecte;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Creates a new instance of AuthentificationBean
     */
    public LoginMBean() {
    }

    public void checkErrors(ComponentSystemEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        if ("true".equals((String) request.getParameter("Failed to log in {0}"))) {
            /* GET parameter "failed" has been sent in theillez revoir votre login et mot de passe!"));
             } HTTP request... */
            context.addMessage(null, new FacesMessage("Login ou mot de passe incorrect!"));
        } else if (request.getRequestedSessionId() != null && !request.isRequestedSessionIdValid()
                & request.getParameter("logout") == null) {
            /* The user session has timed out (not caused by a logout action)... */
            context.addMessage(null, new FacesMessage("Votre session a expiré!"));
        } else if (request.getParameter("logout") != null && request.getParameter("logout").equalsIgnoreCase("true")) {
            context.addMessage(null, new FacesMessage("Vous êtes déconnecté."));

        }
    }

    public TUtilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(TUtilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String logout() {
        // String page="/login?logout=true&faces-redirect=true";
        String page = "/login?logout=true&faces-redirect=true";
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        //HttpSession session = (HttpSession) context.getExternalContext().getSession(false);

        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            request.logout();
            connecte = false;

        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed!"));
            // page="/login?logout=false&faces-redirect=true";
            page = "/login?logout=true&faces-redirect=true";
        }
        return "/login?logout=true&faces-redirect=true";
    }

    public boolean isContribuable() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        contribuable = externalContext.isUserInRole("CONTRIBUABLE");
        return contribuable;
    }

    public boolean isActivecc() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        return externalContext.isUserInRole("ACTIVECC");

    }

    public boolean isAdmin() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        return externalContext.isUserInRole("ADMIN");
    }

    public boolean isInspectmaj() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        return externalContext.isUserInRole("INSPECTMAJ");
    }

    public boolean isInspecteur() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        return externalContext.isUserInRole("INSPECTEUR");
    }

    public boolean isConnected() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        //  if (externalContext.getContext()==null)
        // ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        if (request.getUserPrincipal() == null) {
            System.out.println("non");
            return false;
        } else {

            setUtilisateurconnecte(tUtilisateurFacade.rechercheUtilconnecte(uname));
            nom = utilisateurconnecte.getUtilLogin();
            //System.out.println("Utilisateur Connecté " + utilisateurconnecte + "Uname " + uname);
            //System.out.println(" SERVICE +++++++ : " + utilisateurconnecte.getFonctCod().getLibelle()+ " test");

            //rechercher le centre de l'utilisateur dans un truc public si c'est un inspecteur
            return true;
        }
    }

    public String login() {
        String chemin = "index.xhtml";
        String statut = "D";
        boolean actif = false;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("loginUser", uname);

        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        if (isConnected()) {

            sessionMap.put("utilisateurConnecte", utilisateurconnecte);
            if (externalContext.isUserInRole("CONTRIBUABLE") == true) {
                chemin = "/vues/tEntDeclaration/DeclarationFiscal.xhtml";
            }
            if (externalContext.isUserInRole("ADMIN") == true) {
                chemin = "/vues/tDeclarationDou/ConsulterDD.xhtml";
            }
            if (externalContext.isUserInRole("INSPECTEUR") == true) {
                chemin = "/vues/tDeclarationDou/ConsulterDD.xhtml";
            }
            if (externalContext.isUserInRole("INSPECTMAJ") == true) {
                chemin = "/vues/tRepUnique/MAJIndividuelle.xhtml";
            }
            if (externalContext.isUserInRole("ACTIVECC") == true) {
                chemin = "/vues/tUtilisateur/MAJUtilisateur.xhtml";
            }
        } else {

            try {
                if (!(tUtilisateurFacade.rechercheUtilconnecte(uname) == null)) {
                    setUtilisateur(tUtilisateurFacade.rechercheUtilconnecte(uname));
                    System.out.println("+++++++++++ UTILISATEUR CONNECTE ============ " + utilisateur.getUtilLogin());
                    sessionMap.put("utilisateurConnecte", utilisateur);
                    statut = utilisateur.getUtilActif();
                }
                //if (statut.equals("A") ) {
                if ("A".equals(statut)) {
                    actif = true;
                } else {
                    actif = false;
                };
                if (actif == false) {
                    Logger.getLogger(LoginMBean.class.getName()).log(Level.INFO, "Utilisateur non actif", uname);
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Votre compte n'est pas actif ");
                    facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
                    facesContext.addMessage(null, facesMessage);
                    return "login.xhtml";
                } else {
                    request.login(uname, password);
                    System.out.println("connexion");
                    if (isConnected()) {
                        if (externalContext.isUserInRole("CONTRIBUABLE") == true) {
                            chemin = "/vues/tEntDeclaration/DeclarationFiscal.xhtml";
                        }
                        if (externalContext.isUserInRole("ADMIN") == true) {
                            chemin = "/vues/tDeclarationDou/ConsulterDD.xhtml";
                        }
                        if (externalContext.isUserInRole("INSPECTEUR") == true) {
                            chemin = "/vues/tDeclarationDou/ConsulterDD.xhtml";
                        }
                        if (externalContext.isUserInRole("INSPECTMAJ") == true) {
                            chemin = "/vues/tRepUnique/MAJIndividuelle.xhtml";
                        }
                        if (externalContext.isUserInRole("ACTIVECC") == true) {
                            chemin = "/vues/tUtilisateur/MAJUtilisateur.xhtml";
                        }

//                   HttpSession session = request.getSession();
//                   session.setMaxInactiveInterval(Integer.parseInt(ResourceBundle.getBundle("/parametres").getString("timeOut"))*60);
                    }

                }

                return chemin;
            } catch (ServletException ex) {
                Logger.getLogger(LoginMBean.class.getName()).log(Level.INFO, "Failed to log in {0}", uname);
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("Login ou mot de passe incorrect. ");
                facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
                facesContext.addMessage(null, facesMessage);

                return "login.xhtml";
            }
        }
        return chemin;

    }
}
