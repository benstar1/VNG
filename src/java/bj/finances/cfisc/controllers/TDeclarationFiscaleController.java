package bj.finances.cfisc.controllers;

import bj.finances.cfisc.entities.TDeclarationFiscale;
import bj.finances.cfisc.controllers.util.JsfUtil;
import bj.finances.cfisc.controllers.util.PaginationHelper;
import bj.finances.cfisc.entities.TEntDeclaration;
import bj.finances.cfisc.entities.TTaxeDeclaration;
import bj.finances.cfisc.sessions.TDeclarationFiscaleFacade;
import bj.finances.cfisc.sessions.TEntDeclarationFacade;
import bj.finances.cfisc.sessions.TTaxeDeclarationFacade;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;


@ManagedBean(name = "tDeclarationFiscaleController")
//@Named("tDeclarationFiscaleController")
@SessionScoped
public class TDeclarationFiscaleController implements Serializable {
    @EJB
    private TTaxeDeclarationFacade tTaxeDeclarationFacade;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Date datejour = new Date();
    private TDeclarationFiscale current;
    private TDeclarationFiscale decl;
    List<TTaxeDeclaration> listtaxe;
    private DataModel items = null;
    @EJB
    private bj.finances.cfisc.sessions.TDeclarationFiscaleFacade ejbFacade;
    
    //@javax.inject.Inject
    //bj.finances.cfisc.controllers.TEntDeclarationController tentdeclarationcontrol;
    
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public TDeclarationFiscaleController() {
        System.out.println("dans le constructeur de declaration fiscal ............................");
    }

    public TDeclarationFiscale getSelected() {
        if (current == null) {
            current = new TDeclarationFiscale();
            selectedItemIndex = -1;
        }
        return current;
    }
    
    
//    public void calcultotaux(TDeclarationFiscale Decl){
//        System.out.println("Nous somme là");
//         Long  totaltav=0l;
//         Long  totaldd=0l;
//         Long  totalpc=0l;
//         Long  totalpcs=0l;
//         Long  totalrs=0l;
//         Long  totalaib=0l;
//          Long  totalbt=0l;
//         if (Decl.getTTaxeDeclarationList()!=null){
//        for (TTaxeDeclaration tt : Decl.getTTaxeDeclarationList()){
//            if (tt.getTaxDecTva()!=null)
//             totaltav+=tt.getTaxDecTva();
//            if (tt.getTaxDecPc()!=null)
//             totalpc+=tt.getTaxDecPc();
//            if (tt.getTaxDecPcs()!=null)
//             totalpcs+=tt.getTaxDecPcs();
//            if (tt.getTaxDecDd()!=null)
//             totaldd+=tt.getTaxDecDd();
//            if (tt.getTaxDecRs()!=null)
//             totalrs+=tt.getTaxDecRs();
//            if (tt.getTaxDecAib()!=null)
//             totalaib+=tt.getTaxDecAib();
//            if (tt.getTaxDecBaseTaxable()!=null)
//             totalbt+=tt.getTaxDecBaseTaxable();    
//        }
//        current.setTotalTva(totaltav);
//        current.setTotalAib(totalaib);
//        current.setTotalDd(totaldd);
//        current.setTotalPc(totalpc);
//        current.setTotalPcs(totalpcs);
//        current.setTotalRs(totalrs);
//        current.setTotalBase(totalbt);
//         }
//    }

    
    public void calcultotaux(CloseEvent event){
        System.out.println("Nous somme là dans listner");
         Long  totaltav=0l;
         Long  totaldd=0l;
         Long  totalpc=0l;
         Long  totalpcs=0l;
         Long  totalrs=0l;
         Long  totalaib=0l;
          Long  totalbt=0l;
         Long  totalvcaf=0l;
         if (current!=null){
        listtaxe=  tTaxeDeclarationFacade.findListTaxeDeclar(current);
         if (listtaxe!=null){
        for (TTaxeDeclaration tt : listtaxe){
            if (tt.getTaxDecTva()!=null)
             totaltav+=tt.getTaxDecTva();
            if (tt.getTaxDecPc()!=null)
             totalpc+=tt.getTaxDecPc();
            if (tt.getTaxDecPcs()!=null)
             totalpcs+=tt.getTaxDecPcs();
            if (tt.getTaxDecDd()!=null)
             totaldd+=tt.getTaxDecDd();
            if (tt.getTaxDecRs()!=null)
             totalrs+=tt.getTaxDecRs();
            if (tt.getTaxDecAib()!=null)
             totalaib+=tt.getTaxDecAib();
            if (tt.getTaxDecBaseTaxable()!=null)
             totalbt+=tt.getTaxDecBaseTaxable();  
            if (tt.getTaxDecBaseTaxable()!=null)
             totalvcaf+=tt.getTaxDecValCaf();
        }
        current.setTotalTva(totaltav);
        current.setTotalAib(totalaib);
        current.setTotalDd(totaldd);
        current.setTotalPc(totalpc);
        current.setTotalPcs(totalpcs);
        current.setTotalRs(totalrs);
        current.setTotalBase(totalbt);
        current.setTotalVcaf(totalvcaf);
        ejbFacade.edit(current);
         }
         }
    }

    
    public TDeclarationFiscale getDecl() {
        return decl;
    }

    public void setDecl(TDeclarationFiscale decl) {
        this.decl = decl;
        current=decl;
    }
    
  
    
    private TDeclarationFiscaleFacade getFacade() {
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
        current = (TDeclarationFiscale) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
//      public String setEntdeclare(TEntDeclaration entdeclar){
//      current.setDeclarEntDecNum(entdeclar);
//      return "";
//              }
    public void prepareCreate() {
        current = new TDeclarationFiscale();
        selectedItemIndex = -1;
        //return "Create";
    }
    public void onRowSelect(SelectEvent event) {
       // FacesMessage msg = new FacesMessage("Car Selected", ((Car) event.getObject()).getId());
       // FacesContext.getCurrentInstance().addMessage(null, msg);
       // current=getSelected();
    }
    
    public List<TDeclarationFiscale> getFindAll(){        
    return ejbFacade.findAll();    
    }

    
    public List<TDeclarationFiscale> getFindListEnt(TEntDeclaration entdeclar){        
    return ejbFacade.findListdeclar(entdeclar);    
    }
     
    public String create() {
        try {
            //current.setDeclarEntDecNum(tentdeclarationcontrol.getSelected());
            //current.set
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TDeclarationFiscaleCreated"));
           // return prepareCreate();
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (TDeclarationFiscale) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TDeclarationFiscaleUpdated"));
          //  return "View";
              return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    public String destroybis(TDeclarationFiscale declfisc) {
        ejbFacade.remove(declfisc);
        
        return null;
    }
 
    public String destroy() {
        current = (TDeclarationFiscale) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TDeclarationFiscaleDeleted"));
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

    @FacesConverter(forClass = TDeclarationFiscale.class)
    public static class TDeclarationFiscaleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TDeclarationFiscaleController controller = (TDeclarationFiscaleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tDeclarationFiscaleController");
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
            if (object instanceof TDeclarationFiscale) {
                TDeclarationFiscale o = (TDeclarationFiscale) object;
                return getStringKey(o.getDeclarNum());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TDeclarationFiscale.class.getName());
            }
        }

    }

}
