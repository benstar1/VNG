package org.vng.controlers;

import org.vng.entities.TEnqueter;
import org.vng.controlers.util.JsfUtil;
import org.vng.controlers.util.JsfUtil.PersistAction;
import org.vng.sessions.TEnqueterFacade;

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

@Named("tEnqueterController")
@SessionScoped
public class TEnqueterController implements Serializable {

    @EJB
    private org.vng.sessions.TEnqueterFacade ejbFacade;
    private List<TEnqueter> items = null;
    private TEnqueter selected;

    public TEnqueterController() {
    }

    public TEnqueter getSelected() {
        return selected;
    }

    public void setSelected(TEnqueter selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getTEnqueterPK().setEqUtiCode(selected.getTUtilisateur().getUtiCode());
        selected.getTEnqueterPK().setEqEnqCode(selected.getTEnquete().getEnqCode());
    }

    protected void initializeEmbeddableKey() {
        selected.setTEnqueterPK(new org.vng.entities.TEnqueterPK());
    }

    private TEnqueterFacade getFacade() {
        return ejbFacade;
    }

    public TEnqueter prepareCreate() {
        selected = new TEnqueter();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TEnqueterCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TEnqueterUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TEnqueterDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TEnqueter> getItems() {
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

    public TEnqueter getTEnqueter(org.vng.entities.TEnqueterPK id) {
        return getFacade().find(id);
    }

    public List<TEnqueter> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TEnqueter> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TEnqueter.class)
    public static class TEnqueterControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TEnqueterController controller = (TEnqueterController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tEnqueterController");
            return controller.getTEnqueter(getKey(value));
        }

        org.vng.entities.TEnqueterPK getKey(String value) {
            org.vng.entities.TEnqueterPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new org.vng.entities.TEnqueterPK();
            key.setEqEnqCode(values[0]);
            key.setEqUtiCode(values[1]);
            key.setEqDateDebut(java.sql.Date.valueOf(values[2]));
            return key;
        }

        String getStringKey(org.vng.entities.TEnqueterPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getEqEnqCode());
            sb.append(SEPARATOR);
            sb.append(value.getEqUtiCode());
            sb.append(SEPARATOR);
            sb.append(value.getEqDateDebut());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TEnqueter) {
                TEnqueter o = (TEnqueter) object;
                return getStringKey(o.getTEnqueterPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TEnqueter.class.getName()});
                return null;
            }
        }

    }

}
