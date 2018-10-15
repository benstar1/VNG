package org.vng.controlers;

import org.vng.entities.TIntervenir;
import org.vng.controlers.util.JsfUtil;
import org.vng.controlers.util.JsfUtil.PersistAction;
import org.vng.sessions.TIntervenirFacade;

import java.io.Serializable;
import java.util.Calendar;
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

@Named("tIntervenirController")
@SessionScoped
public class TIntervenirController implements Serializable {

    @EJB
    private org.vng.sessions.TIntervenirFacade ejbFacade;
    private List<TIntervenir> items = null;
    private TIntervenir selected;
    private String newNumIntervenir;

    public TIntervenirController() {
    }

    public String getNewNumIntervenir() {
        return newNumIntervenir;
    }

    public void setNewNumIntervenir(String newNumIntervenir) {
        this.newNumIntervenir = newNumIntervenir;
    }

    public void GenererNumIntervenir() {
        //Date d = new Date();
        Calendar calendar = Calendar.getInstance();
        int ann = calendar.get(Calendar.YEAR);//
        String an = String.valueOf(ann);
        String chainesuffixe = "";
        long numsuivant;

        String numinterv = null;
        try {
            numinterv = ejbFacade.executeMaxIntervenir(an);
            if (numinterv == null) {
                setNewNumIntervenir(an + "00000000001");
            } else {
                chainesuffixe = numinterv.substring(4);
                System.out.println("chainesuffixe recuperer " + chainesuffixe);
                numsuivant = Long.valueOf(chainesuffixe) + 1;
                chainesuffixe = String.format("%020d", numsuivant);;
                setNewNumIntervenir(an + chainesuffixe);
                System.out.println("Prochaine numero intervenir " + getNewNumIntervenir());
            }
        } catch (Exception e) {

        }

    }

    public TIntervenir getSelected() {
        return selected;
    }

    public void setSelected(TIntervenir selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TIntervenirFacade getFacade() {
        return ejbFacade;
    }

    public TIntervenir prepareCreate() {
        selected = new TIntervenir();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TIntervenirCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TIntervenirUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TIntervenirDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TIntervenir> getItems() {
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

    public TIntervenir getTIntervenir(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<TIntervenir> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TIntervenir> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TIntervenir.class)
    public static class TIntervenirControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TIntervenirController controller = (TIntervenirController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tIntervenirController");
            return controller.getTIntervenir(getKey(value));
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
            if (object instanceof TIntervenir) {
                TIntervenir o = (TIntervenir) object;
                return getStringKey(o.getInvNumero());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TIntervenir.class.getName()});
                return null;
            }
        }

    }

}
