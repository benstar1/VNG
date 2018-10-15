package org.vng.controlers;

import org.vng.entities.TArrondissement;
import org.vng.controlers.util.JsfUtil;
import org.vng.controlers.util.JsfUtil.PersistAction;
import org.vng.sessions.TArrondissementFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
//import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorListener;
import javax.faces.model.SelectItem;
import org.vng.entities.TCommune;

//@Named("tArrondissementController")
@ManagedBean
@SessionScoped
public class TArrondissementController implements Serializable {

    @EJB
    private org.vng.sessions.TArrondissementFacade ejbFacade;
    private List<TArrondissement> items = null;
    private TArrondissement selected;
    private TCommune commune;
    String CodeCommune;
    List<SelectItem> ArrondItem = new ArrayList<>();       
    
    public TArrondissementController() {
         commune=new TCommune();
    }

    public TCommune getCommune() {
        return commune;
    }
  

    public void setCommune(TCommune commune) {
        this.commune = commune;
    }

    public String getCodeCommune() {
        return CodeCommune;
    }

    public void setCodeCommune(String CodeCommune) {
        this.CodeCommune = CodeCommune;
    }
    
    

    
    
    public TArrondissement getSelected() {
        return selected;
    }

    public void setSelected(TArrondissement selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TArrondissementFacade getFacade() {
        return ejbFacade;
    }

    public TArrondissement prepareCreate() {
        selected = new TArrondissement();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TArrondissementCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TArrondissementUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TArrondissementDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TArrondissement> getItems() {
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

    public TArrondissement getTArrondissement(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<TArrondissement> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TArrondissement> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    //AjaxBehaviorListener event
    public void initListeArrondissement(){
       
        System.out.println(" Liste Arrondissement");
        List<TArrondissement> listarr=null;
//        if (commune != null) {
//              System.out.println("Commune pas null");
//            listarr = commune.getTArrondissementList();
//            for (TArrondissement arr : listarr) {
//                ArrondItem.add(new SelectItem(arr, arr.getArrCode()+ " --> " + arr.getArrDesig()));
//            }
//        }
    }
    
    public List<SelectItem> getArrondissementItem() {   
//        List<TArrondissement> listarr=null;
//        if (commune != null) {
//              System.out.println("Commune pas null");
//            listarr = commune.getTArrondissementList();
//            for (TArrondissement arr : listarr) {
//                ArrondItem.add(new SelectItem(arr, arr.getArrCode()+ " --> " + arr.getArrDesig()));
//            }
//        }
        return ArrondItem;
    }

    @FacesConverter(forClass = TArrondissement.class)
    public static class TArrondissementControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TArrondissementController controller = (TArrondissementController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tArrondissementController");
            return controller.getTArrondissement(getKey(value));
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
            if (object instanceof TArrondissement) {
                TArrondissement o = (TArrondissement) object;
                return getStringKey(o.getArrCode());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TArrondissement.class.getName()});
                return null;
            }
        }

    }

}
