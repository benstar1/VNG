package org.vng.controlers;

import org.vng.entities.TDepartement;
import org.vng.controlers.util.JsfUtil;
import org.vng.controlers.util.JsfUtil.PersistAction;
import org.vng.sessions.TDepartementFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import org.vng.entities.TVillage;

@Named("tDepartementController")
@SessionScoped
public class TDepartementController implements Serializable {

    @EJB
    private org.vng.sessions.TDepartementFacade ejbFacade;
    @EJB
    private org.vng.sessions.ParcelleFacade ejbparcelleFacade;
    private List<TDepartement> items = null;
    private TDepartement selected;
    private List<String> listvillagetopo;
    List<SelectItem> VillageTopoItem = new ArrayList<>();
    public TDepartementController() {
    }
    
    @PostConstruct
    private void initDepartement(){
     initListevillagetopo();
    }
    public TDepartement getSelected() {
        return selected;
    }
    
    public void setSelected(TDepartement selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TDepartementFacade getFacade() {
        return ejbFacade;
    }

    public TDepartement prepareCreate() {
        selected = new TDepartement();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TDepartementCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TDepartementUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TDepartementDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TDepartement> getItems() {
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

    public TDepartement getTDepartement(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<TDepartement> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TDepartement> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
     public List<SelectItem> getDepartementItem() {
        List<SelectItem> DepartItem = new ArrayList<>();

        List<TDepartement> listdep = getFacade().findAll();

        for (TDepartement dep : listdep) {
            DepartItem.add(new SelectItem(dep, dep.getDepCode()+ " --> " + dep.getDepDesig()));
        }
        return DepartItem;
    }

    public void initListevillagetopo(){
        System.out.println(" Village topo");
         
        listvillagetopo= ejbparcelleFacade.executeListevillagetopo();
        if (listvillagetopo != null) {
            for (String str : listvillagetopo) {
                VillageTopoItem.add(new SelectItem(str,str));
            }
        }
    }
     public List<SelectItem> getVillageTopoItem() {
       return VillageTopoItem;
     }
     
    @FacesConverter(forClass = TDepartement.class)
    public static class TDepartementControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TDepartementController controller = (TDepartementController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tDepartementController");
            return controller.getTDepartement(getKey(value));
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
            if (object instanceof TDepartement) {
                TDepartement o = (TDepartement) object;
                return getStringKey(o.getDepCode());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TDepartement.class.getName()});
                return null;
            }
        }

    }

}
