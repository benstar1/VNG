/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.controllers;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/*
 *
 * @author DELL
 */
@ManagedBean
@SessionScoped
public class LoginMBean implements Serializable{
String uname;
String password;
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
        if ("true".equals((String)request.getParameter("Failed to log in {0}"))) {
            /* GET parameter "failed" has been sent in theillez revoir votre login et mot de passe!"));
        } HTTP request... */
            context.addMessage(null, new FacesMessage("Veuillez revoir votre login et mot de passe!"));
        }
        else if (request.getRequestedSessionId()!=null && !request.isRequestedSessionIdValid()
                    & request.getParameter("logout")==null) {
            /* The user session has timed out (not caused by a logout action)... */
            context.addMessage(null, new FacesMessage("Votre session est expiré!"));
        }
        else if (request.getParameter("logout")!=null && request.getParameter("logout").equalsIgnoreCase("true")) {
            context.addMessage(null, new FacesMessage("Déconnexion reussie."));
        }
    }
 
    public String logout() {
       // String page="/login?logout=true&faces-redirect=true";
        String page="/login?logout=true&faces-redirect=true";
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
          request.logout();
          connecte=false;
          
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed!"));
           // page="/login?logout=false&faces-redirect=true";
            page="/login?logout=true&faces-redirect=true";
        }
        return  "/login?logout=true&faces-redirect=true";
    }
    
    
    public boolean isContribuable() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        return externalContext.isUserInRole("CONTRIBUABLE");
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
    
    public  boolean isConnected(){
     ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
   //  if (externalContext.getContext()==null)
      // ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        if (request.getUserPrincipal()==null)
     { System.out.println("non");
         return false;
     }
     
     else
     {System.out.println("oui");
             return true;}
}
    public String login() {
       
       
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
       
        try {
            request.login(uname, password);
            connecte=true;
            
            
           return  "index.xhtml";
        } catch (ServletException ex) {
            Logger.getLogger(LoginMBean.class.getName()).log(Level.INFO, "Failed to log in {0}", uname);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Veuillez revoir votre login et votre mot de passe ");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(null, facesMessage);
            return "login.xhtml";
        }
    }  
}