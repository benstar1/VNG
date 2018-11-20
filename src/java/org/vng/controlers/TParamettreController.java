package org.vng.controlers;

import org.vng.entities.TParamettre;
import org.vng.controlers.util.JsfUtil;
import org.vng.controlers.util.JsfUtil.PersistAction;
import org.vng.sessions.TParamettreFacade;

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

@Named("tParamettreController")
@SessionScoped
public class TParamettreController implements Serializable {

    @EJB
    private org.vng.sessions.TParamettreFacade ejbFacade;
    private List<TParamettre> items = null;
    private TParamettre selected;

    public TParamettreController() {
    }

    public TParamettre getSelected() {
        return selected;
    }

    public void setSelected(TParamettre selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        selected.setTParamettrePK(new org.vng.entities.TParamettrePK());
    }

    private TParamettreFacade getFacade() {
        return ejbFacade;
    }

    public TParamettre prepareCreate() {
        selected = new TParamettre();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TParamettreCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TParamettreUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TParamettreDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TParamettre> getItems() {
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

    public TParamettre getTParamettre(org.vng.entities.TParamettrePK id) {
        return getFacade().find(id);
    }

    public List<TParamettre> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TParamettre> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TParamettre.class)
    public static class TParamettreControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TParamettreController controller = (TParamettreController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tParamettreController");
            return controller.getTParamettre(getKey(value));
        }

        org.vng.entities.TParamettrePK getKey(String value) {
            org.vng.entities.TParamettrePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new org.vng.entities.TParamettrePK();
            key.setParamCode(values[0]);
            key.setParamDateeffet(java.sql.Date.valueOf(values[1]));
            return key;
        }

        String getStringKey(org.vng.entities.TParamettrePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getParamCode());
            sb.append(SEPARATOR);
            sb.append(value.getParamDateeffet());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TParamettre) {
                TParamettre o = (TParamettre) object;
                return getStringKey(o.getTParamettrePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TParamettre.class.getName()});
                return null;
            }
        }

    }

}
