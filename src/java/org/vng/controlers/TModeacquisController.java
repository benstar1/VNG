package org.vng.controlers;

import org.vng.entities.TModeacquis;
import org.vng.controlers.util.JsfUtil;
import org.vng.controlers.util.JsfUtil.PersistAction;
import org.vng.sessions.TModeacquisFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("tModeacquisController")
@SessionScoped
public class TModeacquisController implements Serializable {

    @EJB
    private org.vng.sessions.TModeacquisFacade ejbFacade;
    private List<TModeacquis> items = null;
    private TModeacquis selected;
    //private List<TModeacquis> ItemsModeAcquisition
    public TModeacquisController() {
    }

    public TModeacquis getSelected() {
        return selected;
    }

    public void setSelected(TModeacquis selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TModeacquisFacade getFacade() {
        return ejbFacade;
    }

    public TModeacquis prepareCreate() {
        selected = new TModeacquis();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TModeacquisCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TModeacquisUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TModeacquisDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TModeacquis> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public void ItemsModeAcquisition(String typeMode) {
           // List<TModeacquis> listModeacquis=new ArrayList<>();
            try {
             if (typeMode.equals("HERITAGE")){
                 items = getFacade().executeListeModeaquisHerit();
             }    
        } catch (Exception e) {
                System.out.println("Probleme selection mode acquisition");
        }
          
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

    public TModeacquis getTModeacquis(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<TModeacquis> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TModeacquis> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TModeacquis.class)
    public static class TModeacquisControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TModeacquisController controller = (TModeacquisController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tModeacquisController");
            return controller.getTModeacquis(getKey(value));
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
            if (object instanceof TModeacquis) {
                TModeacquis o = (TModeacquis) object;
                return getStringKey(o.getMacCode());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TModeacquis.class.getName()});
                return null;
            }
        }

    }

}
