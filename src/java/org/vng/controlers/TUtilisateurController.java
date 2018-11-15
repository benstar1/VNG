package org.vng.controlers;

import org.vng.entities.TUtilisateur;
import org.vng.controlers.util.JsfUtil;
import org.vng.controlers.util.JsfUtil.PersistAction;
import org.vng.sessions.TUtilisateurFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;


@Named("tUtilisateurController")
@SessionScoped
public class TUtilisateurController implements Serializable {
    //@Inject
    ConvertirMD5 md5=new ConvertirMD5();
    
    @EJB
    private org.vng.sessions.TUtilisateurFacade ejbFacade;
    private List<TUtilisateur> items = null;
    private TUtilisateur selected;
    int i=0;
    String limitationchaine,confutiPassword;
    public TUtilisateurController() {
    }

    public String getConfutiPassword() {
        return confutiPassword;
    }

    public void setConfutiPassword(String confutiPassword) {
        this.confutiPassword = confutiPassword;
    }

    public String getLimitationchaine() {
        return limitationchaine;
    }

    public void setLimitationchaine(String limitationchaine) {
        this.limitationchaine = limitationchaine;
    }
    
    

    public TUtilisateur getSelected() {
        return selected;
    }

    public void setSelected(TUtilisateur selected) {
        this.selected = selected;
        initlimitation();
       
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TUtilisateurFacade getFacade() {
        return ejbFacade;
    }

    public TUtilisateur prepareCreate() {
        selected = new TUtilisateur();
        initializeEmbeddableKey();
        return selected;
    }
     private void initActif(){
         if(getLimitationchaine().equals("OUI")){
             selected.setUtiActif(Boolean.TRUE);
         }
         if(getLimitationchaine().equals("NON")){
         selected.setUtiActif(Boolean.FALSE);
         }
     }
     
      private void initlimitation(){
        if(selected!=null){
        if(selected.getUtiActif()){
            setLimitationchaine("OUI");
        }else{
           setLimitationchaine("NON");
        }  
              System.out.println(limitationchaine);
          }
     }
     
     
    public void create() {
        if(!selected.getUtiPassword().equals(getConfutiPassword())){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Utilisateur : ", "Mot de passe non confirme");
            FacesContext.getCurrentInstance().addMessage(null, message);                
        }else{
        
        String pwdmd5 = md5.generateMD5(selected.getUtiPassword());
        selected.setUtiPassword(pwdmd5);
        initActif();
        i++;
        selected.setUtiCode(String.valueOf(i));
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TUtilisateurCreated"));
        int n;
        n=ejbFacade.insertUtilisateurGroupe(selected.getUtiLogin(),selected.getUtiTyuCode().getTyuDesig());
            System.out.println("Groupe insere "+n);
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        
        }
    }

    public void update() {
        initActif();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TUtilisateurUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TUtilisateurDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TUtilisateur> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public TUtilisateur getTUtilisateur(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<TUtilisateur> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TUtilisateur> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TUtilisateur.class)
    public static class TUtilisateurControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TUtilisateurController controller = (TUtilisateurController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tUtilisateurController");
            return controller.getTUtilisateur(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TUtilisateur) {
                TUtilisateur o = (TUtilisateur) object;
                return getStringKey(o.getUtiCode());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TUtilisateur.class.getName()});
                return null;
            }
        }

    }

}
