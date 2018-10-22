package org.vng.controlers;

import org.vng.entities.TSignataire;
import org.vng.controlers.util.JsfUtil;
import org.vng.controlers.util.JsfUtil.PersistAction;
import org.vng.sessions.TSignataireFacade;

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

@Named("tSignataireController")
@SessionScoped
public class TSignataireController implements Serializable {

    @EJB
    private org.vng.sessions.TSignataireFacade ejbFacade;
    private List<TSignataire> items = null;
    private TSignataire selected;
    int i;
    public TSignataireController() {
    }

    public TSignataire getSelected() {
        return selected;
    }

    public void setSelected(TSignataire selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TSignataireFacade getFacade() {
        return ejbFacade;
    }

    public TSignataire prepareCreate() {
        selected = new TSignataire();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        i++;
        selected.setSignCode(String.valueOf(i));
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TSignataireCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TSignataireUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TSignataireDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TSignataire> getItems() {
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

    public TSignataire getTSignataire(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<TSignataire> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TSignataire> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TSignataire.class)
    public static class TSignataireControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TSignataireController controller = (TSignataireController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tSignataireController");
            return controller.getTSignataire(getKey(value));
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
            if (object instanceof TSignataire) {
                TSignataire o = (TSignataire) object;
                return getStringKey(o.getSignCode());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TSignataire.class.getName()});
                return null;
            }
        }

    }

}