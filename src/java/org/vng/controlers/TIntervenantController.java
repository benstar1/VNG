package org.vng.controlers;

import org.vng.entities.TIntervenant;
import org.vng.controlers.util.JsfUtil;
import org.vng.controlers.util.JsfUtil.PersistAction;
import org.vng.sessions.TIntervenantFacade;

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
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import org.vng.sessions.TActiviteFacade;
import org.vng.sessions.TEthnieFacade;

@Named("intervenantController")
@SessionScoped
public class TIntervenantController implements Serializable {

    @EJB
    private org.vng.sessions.TIntervenantFacade ejbFacade;
    
     @Inject
    TEthnieFacade ethnieFacade;
    
    @Inject
    TActiviteFacade activiteFacade;
    
    private List<TIntervenant> items = null;
    private List<TIntervenant> itemsFilter = null;
    private TIntervenant selected;

    public TIntervenantController() {
        selected = new TIntervenant();
       // selected.setIntNumero("00001");
        //System.out.println("selected  ----  "+selected);
    }

    public TIntervenant getSelected() {
        return selected;
    }

    public void setSelected(TIntervenant selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TIntervenantFacade getFacade() {
        return ejbFacade;
    }

    public TEthnieFacade getEthnieFacade() {
        return ethnieFacade;
    }

    public void setEthnieFacade(TEthnieFacade ethnieFacade) {
        this.ethnieFacade = ethnieFacade;
    }

    public TActiviteFacade getActiviteFacade() {
        return activiteFacade;
    }

    public void setActiviteFacade(TActiviteFacade activiteFacade) {
        this.activiteFacade = activiteFacade;
    }
    
    
    public String ajouterIntervenant()
    {
       // System.out.println(" selected iocicicic "+selected);
        if(selected != null)
        {
            selected.setIntNumero("000011");
            ejbFacade.create(selected);
            //JsfUtil.addSuccessMessage(ResourceBundle.getBundle("msg").getString("confirmation_operation"));
          //   JsfUtil.addSuccessMessage("Opération effectuée avec succès.");
            selected = new TIntervenant();
            
             FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Opération effectuée avec succès.");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(null, facesMessage);
           /* System.out.println("selected ---1 "+selected.getIntNumero());
            
             PersistenceUtil util = Persistence.getPersistenceUtil();
             boolean isObjectLoaded = util.isLoaded(selected);
             boolean isFieldLoaded = util.isLoaded(selected, "int_numero");
             
             System.out.println("selected ---2 "+selected.getIntNumero());
        */
        // JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/").getString("MessageConfirmationMarche"));
             
        }System.out.println("oui on passe ");
        return "parcelleagricolenoyau.xhtml";    
    }
    
      public void initialiser()
    {
        selected = new TIntervenant();
    }

    public TIntervenant prepareCreate() {
        selected = new TIntervenant();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TIntervenantCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TIntervenantUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TIntervenantDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TIntervenant> getItems() {
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

    public TIntervenant getTIntervenant(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<TIntervenant> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TIntervenant> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<TIntervenant> getItemsFilter() {
        return itemsFilter;
    }

    public void setItemsFilter(List<TIntervenant> itemsFilter) {
        this.itemsFilter = itemsFilter;
    }

    @FacesConverter(forClass = TIntervenant.class)
    public static class TIntervenantControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TIntervenantController controller = (TIntervenantController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "intervenantController");
            return controller.getTIntervenant(getKey(value));
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
            if (object instanceof TIntervenant) {
                TIntervenant o = (TIntervenant) object;
                return getStringKey(o.getIntNumero());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TIntervenant.class.getName()});
                return null;
            }
        }

    }

}
