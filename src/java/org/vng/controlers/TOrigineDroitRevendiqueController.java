package org.vng.controlers;

import org.vng.entities.TOrigineDroitRevendique;
import org.vng.controlers.util.JsfUtil;
import org.vng.controlers.util.JsfUtil.PersistAction;
import org.vng.sessions.TOrigineDroitRevendiqueFacade;

import java.io.Serializable;
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

@Named("tOrigineDroitRevendiqueController")
@SessionScoped
public class TOrigineDroitRevendiqueController implements Serializable {

    @EJB
    private org.vng.sessions.TOrigineDroitRevendiqueFacade ejbFacade;
    private List<TOrigineDroitRevendique> items = null;
    private TOrigineDroitRevendique selected;

    public TOrigineDroitRevendiqueController() {
    }

    public TOrigineDroitRevendique getSelected() {
        return selected;
    }

    public void setSelected(TOrigineDroitRevendique selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TOrigineDroitRevendiqueFacade getFacade() {
        return ejbFacade;
    }

    public TOrigineDroitRevendique prepareCreate() {
        selected = new TOrigineDroitRevendique();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TOrigineDroitRevendiqueCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TOrigineDroitRevendiqueUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TOrigineDroitRevendiqueDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TOrigineDroitRevendique> getItems() {
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

    public TOrigineDroitRevendique getTOrigineDroitRevendique(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<TOrigineDroitRevendique> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TOrigineDroitRevendique> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TOrigineDroitRevendique.class)
    public static class TOrigineDroitRevendiqueControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TOrigineDroitRevendiqueController controller = (TOrigineDroitRevendiqueController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tOrigineDroitRevendiqueController");
            return controller.getTOrigineDroitRevendique(getKey(value));
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
            if (object instanceof TOrigineDroitRevendique) {
                TOrigineDroitRevendique o = (TOrigineDroitRevendique) object;
                return getStringKey(o.getOriCode());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TOrigineDroitRevendique.class.getName()});
                return null;
            }
        }

    }

}
