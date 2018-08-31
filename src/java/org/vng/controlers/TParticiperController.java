package org.vng.controlers;

import org.vng.entities.TParticiper;
import org.vng.controlers.util.JsfUtil;
import org.vng.controlers.util.JsfUtil.PersistAction;
import org.vng.sessions.TParticiperFacade;

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

@Named("tParticiperController")
@SessionScoped
public class TParticiperController implements Serializable {

    @EJB
    private org.vng.sessions.TParticiperFacade ejbFacade;
    private List<TParticiper> items = null;
    private TParticiper selected;

    public TParticiperController() {
    }

    public TParticiper getSelected() {
        return selected;
    }

    public void setSelected(TParticiper selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getTParticiperPK().setParOpvNumero(selected.getTOperationParcel().getOpvNumero());
        selected.getTParticiperPK().setParIntNumero(selected.getTIntervenant().getIntNumero());
        selected.getTParticiperPK().setParRovCode(selected.getTRolepart().getRovCode());
    }

    protected void initializeEmbeddableKey() {
        selected.setTParticiperPK(new org.vng.entities.TParticiperPK());
    }

    private TParticiperFacade getFacade() {
        return ejbFacade;
    }

    public TParticiper prepareCreate() {
        selected = new TParticiper();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TParticiperCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TParticiperUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TParticiperDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TParticiper> getItems() {
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

    public TParticiper getTParticiper(org.vng.entities.TParticiperPK id) {
        return getFacade().find(id);
    }

    public List<TParticiper> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TParticiper> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TParticiper.class)
    public static class TParticiperControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TParticiperController controller = (TParticiperController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tParticiperController");
            return controller.getTParticiper(getKey(value));
        }

        org.vng.entities.TParticiperPK getKey(String value) {
            org.vng.entities.TParticiperPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new org.vng.entities.TParticiperPK();
            key.setParOpvNumero(values[0]);
            key.setParRovCode(values[1]);
            key.setParIntNumero(values[2]);
            return key;
        }

        String getStringKey(org.vng.entities.TParticiperPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getParOpvNumero());
            sb.append(SEPARATOR);
            sb.append(value.getParRovCode());
            sb.append(SEPARATOR);
            sb.append(value.getParIntNumero());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TParticiper) {
                TParticiper o = (TParticiper) object;
                return getStringKey(o.getTParticiperPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TParticiper.class.getName()});
                return null;
            }
        }

    }

}
