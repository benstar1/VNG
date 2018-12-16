/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.controlers;

//import bj.finances.cfisc.entities.TUtilisateur;
//import bj.finances.cfisc.sessions.TUtilisateurFacade;
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
import javax.inject.Named;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.vng.entities.TUtilisateur;
import org.vng.sessions.TUtilisateurFacade;

/*
 *
 * @author DELL
 */
//@Named("loginMBean")
//@SessionScoped
@ManagedBean
@SessionScoped
public class LoginMBean implements Serializable {

    @EJB
    private TUtilisateurFacade tUtilisateurFacade;
    private TUtilisateur utilisateurconnecte = new TUtilisateur();
    private boolean contribuable;
    private TUtilisateur utilisateur = new TUtilisateur();
    private String nomPrenom = "Utilisateur Anonyme";
    private boolean connecter = false;
    //@Inject
    ConvertirMD5 md5;

    public TUtilisateur getUtilisateurconnecte() {
        return utilisateurconnecte;
    }

    public boolean isConnecter() {
        return connecter;
    }

    public void setConnecter(boolean connecter) {
        this.connecter = connecter;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
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
        if (connectedUser.getUtiPassword().equals(md5.generateMD5(currentPassWord)) && newPassword.equals(confirmPassword)) {
            connectedUser.setUtiPassword(md5.generateMD5(newPassword));
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
        String page = "/login?logout=true&faces-redirect=true";
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            request.logout();
            setUtilisateur(null);
            setUtilisateurconnecte(null);
            setNomPrenom("Utilisateur Anonyme");
            //connecte = false;
            setConnecter(false);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed!"));
            page = "/login?logout=true&faces-redirect=true";
        }
        return "/login?logout=true&faces-redirect=true";
    }

    public boolean isSaisie() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        contribuable = externalContext.isUserInRole("SAISIE");
        return contribuable;
    }

    public boolean isConsultation() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        return externalContext.isUserInRole("CONSULTATION");

    }

    public boolean isAdmin() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        return externalContext.isUserInRole("ADMINISTRATEUR");
    }

    public boolean isChefService() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        return externalContext.isUserInRole("CHEFSERVICE");
    }

    public boolean isConnected() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        if (request.getUserPrincipal() == null) {
            return false;
        } else {
            System.out.println("Nous somme dans du vrai");
            try {
                setUtilisateurconnecte(tUtilisateurFacade.rechercheUtilconnecte(uname));
               nom = utilisateurconnecte.getUtiLogin();
            } catch (Exception e) {
                System.err.println("Probleme : "+e);
            }
            
            return true;
        }
    }

    public boolean verifiUtilisateurConnexter(String login, String motDePasse) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            request.login(login, motDePasse);
        } catch (Exception e) {
            return false;
        }
        return isConnected();
    }

    public String login() {
        String chemin = "index.xhtml";
        boolean actif = false;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("loginUser", uname);
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            request.login(uname, password);
             System.out.println(isConnected()+" "+uname);       
            if (isConnected()) {
                try {
                    //////////Verifions si l'utilisateure est actif//////////////////////
                    if (!(tUtilisateurFacade.rechercheUtilconnecte(uname) == null)) {
                        setUtilisateur(tUtilisateurFacade.rechercheUtilconnecte(uname));
                        System.out.println("+++++++++++ UTILISATEUR CONNECTE ============ " + utilisateur.getUtiLogin());
                        sessionMap.put("utilisateurConnecte", utilisateurconnecte);
                        actif = utilisateur.getUtiActif();
                        System.out.println("Actif " + actif);
                    }
                    if (actif == false) {
                        Logger.getLogger(LoginMBean.class.getName()).log(Level.INFO, "Utilisateur non actif", uname);
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Connexion : ", "Utilisateur " + uname + " non actif");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        System.out.println(" Inactif");
                        return "login.xhtml";
                    } else {

                        //request.login(uname, password);
                        setUtilisateurconnecte(getUtilisateur());
                        setNomPrenom(getUtilisateurconnecte().getUtiNom() + " " + getUtilisateurconnecte().getUtiPrenom());
                        setConnecter(true);
                        System.out.println("Actif");
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Connexion : ", "Connexion reussie. Bienvenue " + uname);
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        return "dashboard.xhtml";
                    }
                } catch (Exception ex) {
                    Logger.getLogger(LoginMBean.class.getName()).log(Level.INFO, "Impossible de se connecter ", uname);
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("Login ou mot de passe incorrect. ");
                    facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
                    facesContext.addMessage(null, facesMessage);
                    return "login.xhtml";
                }

            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Connexion : ", "Echec de Connexion; login ou mot de passe incorrecte");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "login.xhtml";
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Connexion : ", "Echec de Connexion; login ou mot de passe incorrecte");
            FacesContext.getCurrentInstance().addMessage(null, message);
            System.out.println("Erreur login " + e);
            return "login.xhtml";

        }
    }

}
