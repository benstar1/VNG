package bj.finances.cfisc.controllers;

import bj.finances.cfisc.entities.TTaxeDeclDou;
import bj.finances.cfisc.controllers.util.JsfUtil;
import bj.finances.cfisc.controllers.util.PaginationHelper;
import bj.finances.cfisc.sessions.TTaxeDeclDouFacade;

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

@ManagedBean(name = "tTaxeDeclDouController")
@SessionScoped
public class TTaxeDeclDouController implements Serializable {

    private TTaxeDeclDou current;
    private DataModel items = null;
    @EJB
    private bj.finances.cfisc.sessions.TTaxeDeclDouFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public TTaxeDeclDouController() {
    }

    public TTaxeDeclDou getSelected() {
        if (current == null) {
            current = new TTaxeDeclDou();
            current.setTTaxeDeclDouPK(new bj.finances.cfisc.entities.TTaxeDeclDouPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private TTaxeDeclDouFacade getFacade() {
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
        current = (TTaxeDeclDou) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new TTaxeDeclDou();
        current.setTTaxeDeclDouPK(new bj.finances.cfisc.entities.TTaxeDeclDouPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getTTaxeDeclDouPK().setInstanceid(current.getTArticle().getTArticlePK().getInstanceid());
            current.getTTaxeDeclDouPK().setKeyItmNbr(current.getTArticle().getTArticlePK().getKeyItmNbr());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TTaxeDeclDouCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (TTaxeDeclDou) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getTTaxeDeclDouPK().setInstanceid(current.getTArticle().getTArticlePK().getInstanceid());
            current.getTTaxeDeclDouPK().setKeyItmNbr(current.getTArticle().getTArticlePK().getKeyItmNbr());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TTaxeDeclDouUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (TTaxeDeclDou) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TTaxeDeclDouDeleted"));
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

    @FacesConverter(forClass = TTaxeDeclDou.class)
    public static class TTaxeDeclDouControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TTaxeDeclDouController controller = (TTaxeDeclDouController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tTaxeDeclDouController");
            return controller.ejbFacade.find(getKey(value));
        }

        bj.finances.cfisc.entities.TTaxeDeclDouPK getKey(String value) {
            bj.finances.cfisc.entities.TTaxeDeclDouPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new bj.finances.cfisc.entities.TTaxeDeclDouPK();
            key.setInstanceid(Long.parseLong(values[0]));
            key.setKeyItmNbr(Long.parseLong(values[1]));
            key.setKeyTaxRnk(Long.parseLong(values[2]));
            return key;
        }

        String getStringKey(bj.finances.cfisc.entities.TTaxeDeclDouPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getInstanceid());
            sb.append(SEPARATOR);
            sb.append(value.getKeyItmNbr());
            sb.append(SEPARATOR);
            sb.append(value.getKeyTaxRnk());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TTaxeDeclDou) {
                TTaxeDeclDou o = (TTaxeDeclDou) object;
                return getStringKey(o.getTTaxeDeclDouPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TTaxeDeclDou.class.getName());
            }
        }

    }

}
