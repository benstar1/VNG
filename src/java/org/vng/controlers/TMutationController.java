package org.vng.controlers;

import org.vng.entities.TMutation;
import org.vng.controlers.util.JsfUtil;
import org.vng.controlers.util.JsfUtil.PersistAction;
import org.vng.sessions.TMutationFacade;

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
import org.vng.entities.TOperationParcel;

@Named("tMutationController")
@SessionScoped
public class TMutationController implements Serializable {
    int i=0;
    @EJB
    private org.vng.sessions.TMutationFacade ejbFacade;
    
    @EJB
    private org.vng.sessions.TOperationParcelFacade ejbFacadeOperation;
    
    private List<TMutation> items = null;
     private List<TOperationParcel> itemsOperation = null;
    private TMutation selected;

    public TMutationController() {
    }

    public TMutation getSelected() {
        return selected;
    }

    public void setSelected(TMutation selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TMutationFacade getFacade() {
        return ejbFacade;
    }

    public TMutation prepareCreate() {
        selected = new TMutation();
        initializeEmbeddableKey();
        return selected;
    }
    
//     public void GenererItemsOperationCatMode() {
//        try {
//            itemsOperation = ejbFacadeOperation.executeListeOperationCatMode("OP");
//            System.out.println(" nombre operation " + items.size());
//        } catch (Exception e) {
//        }
//    }

    public List<TOperationParcel> getItemsOperation() {
        try {
            itemsOperation = ejbFacadeOperation.executeListeOperationCatMode("DE");
            System.out.println(" nombre operation " + items.size());
        } catch (Exception e) {
        }
        return itemsOperation;
    }

    public void setItemsOperation(List<TOperationParcel> itemsOperation) {
        this.itemsOperation = itemsOperation;
    }
     
     

    public void create() {
         i++;
        selected.setMutNumero(String.valueOf(i));
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TMutationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TMutationUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TMutationDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TMutation> getItems() {
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

    public TMutation getTMutation(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<TMutation> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TMutation> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TMutation.class)
    public static class TMutationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TMutationController controller = (TMutationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tMutationController");
            return controller.getTMutation(getKey(value));
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
            if (object instanceof TMutation) {
                TMutation o = (TMutation) object;
                return getStringKey(o.getMutNumero());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TMutation.class.getName()});
                return null;
            }
        }

    }

}
