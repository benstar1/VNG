package bj.finances.cfisc.controllers;

import bj.finances.cfisc.entities.TAppartenir;
import bj.finances.cfisc.controllers.util.JsfUtil;
import bj.finances.cfisc.controllers.util.PaginationHelper;
import bj.finances.cfisc.sessions.TAppartenirFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "tAppartenirController")
@SessionScoped
public class TAppartenirController implements Serializable {

    private TAppartenir current;
    private DataModel items = null;
    @EJB
    private bj.finances.cfisc.sessions.TAppartenirFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public TAppartenirController() {
    }

    public TAppartenir getSelected() {
        if (current == null) {
            current = new TAppartenir();
            current.setTAppartenirPK(new bj.finances.cfisc.entities.TAppartenirPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private TAppartenirFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (TAppartenir) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new TAppartenir();
        current.setTAppartenirPK(new bj.finances.cfisc.entities.TAppartenirPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getTAppartenirPK().setAppartenirCentrImpCode(current.getTCentreImpot().getCentrImpCode());
            current.getTAppartenirPK().setAppartenirUtilLogin(current.getTUtilisateur().getUtilLogin());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TAppartenirCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (TAppartenir) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getTAppartenirPK().setAppartenirCentrImpCode(current.getTCentreImpot().getCentrImpCode());
            current.getTAppartenirPK().setAppartenirUtilLogin(current.getTUtilisateur().getUtilLogin());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TAppartenirUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (TAppartenir) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TAppartenirDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = TAppartenir.class)
    public static class TAppartenirControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TAppartenirController controller = (TAppartenirController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tAppartenirController");
            return controller.ejbFacade.find(getKey(value));
        }

        bj.finances.cfisc.entities.TAppartenirPK getKey(String value) {
            bj.finances.cfisc.entities.TAppartenirPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new bj.finances.cfisc.entities.TAppartenirPK();
            key.setAppartenirCentrImpCode(values[0]);
            key.setAppartenirUtilLogin(values[1]);
            key.setAppartenirDatedebut(java.sql.Date.valueOf(values[2]));
            return key;
        }

        String getStringKey(bj.finances.cfisc.entities.TAppartenirPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getAppartenirCentrImpCode());
            sb.append(SEPARATOR);
            sb.append(value.getAppartenirUtilLogin());
            sb.append(SEPARATOR);
            sb.append(value.getAppartenirDatedebut());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TAppartenir) {
                TAppartenir o = (TAppartenir) object;
                return getStringKey(o.getTAppartenirPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TAppartenir.class.getName());
            }
        }

    }

}
