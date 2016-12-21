package bj.finances.cfisc.controllers;

import bj.finances.cfisc.entities.TEntDeclaration;
import bj.finances.cfisc.controllers.util.JsfUtil;
import bj.finances.cfisc.controllers.util.PaginationHelper;
import bj.finances.cfisc.entities.TDeclarationFiscale;
import bj.finances.cfisc.entities.TUtilisateur;
import bj.finances.cfisc.sessions.TEntDeclarationFacade;
import bj.finances.cfisc.sessions.TUtilisateurFacade;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@ManagedBean(name = "tEntDeclarationController")
//@Named("tEntDeclarationController")
@SessionScoped
public class TEntDeclarationController implements Serializable {
    @EJB
    private TUtilisateurFacade tUtilisateurFacade;
    private TEntDeclaration entdecl;
    private TEntDeclaration current;
    private DataModel items = null;
    @EJB
    private bj.finances.cfisc.sessions.TEntDeclarationFacade ejbFacade;
    @EJB
    private bj.finances.cfisc.sessions.TRepUniqueFacade ejbFacaderepuniq;
    
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private boolean valider =false;
    
    private String loginutilisateur;
    
    public TEntDeclarationController() {
        //System.out.println("PAPA MAMAN MIMI JORDI DIVINIA.......................");
    }

    public String getLoginutilisateur() {
        return loginutilisateur;
    }

    public void setLoginutilisateur(String loginutilisateur) {
        this.loginutilisateur = loginutilisateur;
    }
    
    
    public void trouvelogin (){
          ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                    Map<String, Object> sessionMap  = externalContext.getSessionMap();
                    String le_login = (String) sessionMap.get("loginUser");
                    System.out.println("LE LOGIN " + le_login);
                    setLoginutilisateur(le_login);
    }

    public boolean isValider() {
        return valider;
    }

    public void setValider(boolean valider) {
        this.valider = valider;
    }



    public TEntDeclaration getSelected() {
        if (current == null) {
            current = new TEntDeclaration();
            selectedItemIndex = -1;
        }
        return current;
    }
    
    public void validation(){
      current.setEntDecValidation("O");
      ejbFacade.edit(current);
        setValider(true);
    }

    private TEntDeclarationFacade getFacade() {
        return ejbFacade;
    }

    public TEntDeclaration getEntdecl() {
        return entdecl;
    }

    public void setEntdecl(TEntDeclaration entdecl) {
        this.entdecl = entdecl;
        current=entdecl;
        if (current.getEntDecValidation().equals("O")){
             setValider(true);
         }
          if (current.getEntDecValidation().equals("N")){
             setValider(false);
         }
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
        current = (TEntDeclaration) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new TEntDeclaration();
        selectedItemIndex = -1;
        //return "Create";
        return null;
    }

    public String create() {
        try {
            System.out.println("Nous somme là");
           
            TUtilisateur Utilis = null;  
            Utilis=tUtilisateurFacade.rechercheUtilconnecte(getLoginutilisateur());
            System.out.println("Utilisateur connecté  ###########################   "+Utilis.getUtilNom());
            System.out.println("IFU Utilisateur connecté  ###########################   "+Utilis.getUtilContImmatr().getContImmatr());
     
            System.out.println("Nous somme après");
            current.setEntDecContImmatr(Utilis.getUtilContImmatr());
            current.setEntDecValidation("N");
            //current.setEntDecNum(2l);
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TEntDeclarationCreated"));
            //return prepareCreate();
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (TEntDeclaration) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TEntDeclarationUpdated"));
           // return "View";
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
     public String destroybis(TEntDeclaration entdecl) {
         ejbFacade.remove(entdecl);
         return null;
     }

    public String destroy() {
        current = (TEntDeclaration) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        //return "List";
        return null;
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TEntDeclarationDeleted"));
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
     
        public List<TEntDeclaration> getFindListEntdeclfisc(){        
            TUtilisateur Utilis = null;  
            Utilis=tUtilisateurFacade.rechercheUtilconnecte(getLoginutilisateur());
            System.out.println("Utilisateur connecté  ###########################   "+Utilis.getUtilNom());
            System.out.println("IFU Utilisateur connecté  ###########################   "+Utilis.getUtilContImmatr().getContImmatr());
            return ejbFacade.findListentdeclarcontrib(Utilis.getUtilContImmatr().getContImmatr());  
        }
        
    public List<TEntDeclaration> getFindAll(){        
          return ejbFacade.findAll();    
    }
    
      public String entetedeclarselectionne(TEntDeclaration entdecl) {
         current=entdecl; 
         if (current.getEntDecValidation().equals("O")){
             setValider(true);
         }
          if (current.getEntDecValidation().equals("N")){
             setValider(false);
         }
        return "";
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

    @FacesConverter(forClass = TEntDeclaration.class)
    public static class TEntDeclarationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TEntDeclarationController controller = (TEntDeclarationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tEntDeclarationController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TEntDeclaration) {
                TEntDeclaration o = (TEntDeclaration) object;
                return getStringKey(o.getEntDecNum());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TEntDeclaration.class.getName());
            }
        }

    }

}
