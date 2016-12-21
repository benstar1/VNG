package bj.finances.cfisc.controllers;

import bj.finances.cfisc.entities.TRepUnique;
import bj.finances.cfisc.controllers.util.JsfUtil;
import bj.finances.cfisc.controllers.util.PaginationHelper;
import bj.finances.cfisc.entities.TCentreImpot;
import bj.finances.cfisc.entities.THistStatut;
import bj.finances.cfisc.entities.TUtilisateur;
import bj.finances.cfisc.sessions.TCentreImpotFacade;
import bj.finances.cfisc.sessions.THistStatutFacade;
import bj.finances.cfisc.sessions.TRepUniqueFacade;
import bj.finances.cfisc.sessions.TUtilisateurFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

//@ManagedBean(name = "tRepUniqueController")
@Named(value = "tRepUniqueController")
@SessionScoped
public class TRepUniqueController implements Serializable {

    @EJB
    private TCentreImpotFacade tCentreImpotFacade;

    private TCentreImpot centreImpot;

    @EJB
    private TUtilisateurFacade tUtilisateurFacade;

    private TUtilisateur user;

    public TUtilisateurFacade gettUtilisateurFacade() {
        return tUtilisateurFacade;
    }

    public void settUtilisateurFacade(TUtilisateurFacade tUtilisateurFacade) {
        this.tUtilisateurFacade = tUtilisateurFacade;
    }

    public TUtilisateur getUser() {
        return user;
    }

    public void setUser(TUtilisateur user) {
        this.user = user;
    }

    @EJB
    private THistStatutFacade tHistStatutFacade;

    // ajout
    String statut = "";

    String afficheStatut = "";

    public String getAfficheStatut() {
        return afficheStatut;
    }

    public void setAfficheStatut(String afficheStatut) {
        this.afficheStatut = afficheStatut;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
// fin 
    @Inject
    private THistStatutController tHistStatutController;

    private TRepUnique current;
    private DataModel items = null;
    private List<TRepUnique> contrib = null;

    private TRepUnique ifu = null;

    private TRepUnique ItemsIfu;

    THistStatut THistStatut;

    @EJB
    private bj.finances.cfisc.sessions.TRepUniqueFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public TRepUniqueController() {
        this.ItemsIfu = null;
    }

    public TRepUnique getSelected() {
        if (current == null) {
            current = new TRepUnique();
            selectedItemIndex = -1;
        }
        return current;
    }

    public TCentreImpot getCentreImpot() {
        return centreImpot;
    }

    public void setCentreImpot(TCentreImpot centreImpot) {
        this.centreImpot = centreImpot;
    }

    private TRepUniqueFacade getFacade() {
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

    public void prepareAfficheMAJIndividuelle(TRepUnique trepunique) {
//        recreateModel();
//        return "MAJIndividuelle";
        if ("A".equals(trepunique.getContStatut())) {
            setStatut("Désactiver");
        } else {
            setStatut("Activer");
        }

    }

    public String prepareView() {
        current = (TRepUnique) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new TRepUnique();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TRepUniqueCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
// Instanciation d'un objet tHistStatut
    THistStatut tHistStatut = new THistStatut();

    public String prepareEdit() {

        current = (TRepUnique) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TRepUniqueUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String updateRunique(TRepUnique enreg) {
        try {

            current.setContStatut(enreg.getContStatut());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TRepUniqueUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (TRepUnique) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TRepUniqueDeleted"));
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

    public TRepUnique getIfu(TRepUnique ifumaj) {

        ifu = ejbFacade.find(ifumaj);
        return ifu;
    }

    public List<TRepUnique> getContrib() {

        if (contrib == null) {
            contrib = getFacade().findAll();
        }
        return getFacade().findAll();

    }

    public TRepUnique getItemsIfu() {
        return ItemsIfu;
    }

    public void setItemsIfu(TRepUnique ItemsIfu) {
        this.ItemsIfu = ItemsIfu;
    }

    public void MAJActive() {

        try {

            TCentreImpot centre = current.getContCentrImpCode();

            current = ifu;
            System.out.println(" Ancien " + ifu.getContStatut());
            current.setContStatut("A");
            System.out.println(" activé " + current.getContStatut());
            current.setContCentrImpCode(centre);
            getFacade().edit(current);
            System.out.println(current);
            prepareAfficheMAJIndividuelle(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TRepUniqueUpdated"));
            // return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
//            return null;
        }

    }

    public void MAJStatut() {
        current = ifu;
        try {
            System.out.println(centreImpot + " Centre impot choisi -- Test " + current.getContCentrImpCode().getCentrImpCode());
            System.out.println(" Centre impot ancien " + ifu.getContCentrImpCode().getCentrImpCode());

            TCentreImpot centre = ifu.getContCentrImpCode();

            if ("A".equals(current.getContStatut())) {

                //System.out.println(current.getContStatut() + " Ancien " + ifu.getContStatut());
//         current.setContCentrImpCode(centreImpot);
                current.setContStatut("D");
                System.out.println(" Statut " + current.getContStatut());

                // current.setContCentrImpCode(centre);
//            getFacade().edit(current);
                try {
                    getFacade().edit(current);
                } catch (Exception e) {
                    System.out.println("Erreur d'édition " + e.getMessage());
                }

                setItemsIfu(current);
                setAfficheStatut("DESACTIVE");
                System.out.println(afficheStatut + "-------------");
                //ajout
                setStatut("Activer");
            //fin
                //historisation
                System.out.println("Début historisation -- Désactivation");

                user = tUtilisateurFacade.findAll().get(0);
                System.out.println("User " + user);

                tHistStatut = new THistStatut(current, user);
                try {
                    tHistStatutFacade.historiserStatut(tHistStatut);
                } catch (Exception e) {
                    System.out.println("Erreur appel historiser" + e);
                }

                System.out.println("Fin historisation -- Désactivation");

                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TRepUniqueUpdated"));
                //return "View";
            } else {
                current.setContStatut("A");
                System.out.println(" Statut " + current.getContStatut());
                // current.setContCentrImpCode(centre);
                try {
                    getFacade().edit(current);
                } catch (Exception e) {
                    System.out.println("Erreur d'édition " + e.getMessage());
                }

                setItemsIfu(current);
                setAfficheStatut("ACTIVE");
                System.out.println(afficheStatut + "-------------");

                //ajout
                setStatut("Désactiver");
            //fin
                //historisation
                System.out.println("Début historisation -- Activation");

                user = tUtilisateurFacade.findAll().get(0);
                System.out.println("User " + user);

                tHistStatut = new THistStatut(current, user);
                try {
                    tHistStatutFacade.historiserStatut(tHistStatut);
                } catch (Exception e) {
                    System.out.println("Erreur appel historiser" + e);
                }

                System.out.println("Fin historisation -- Activation");
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TRepUniqueUpdated"));
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            // return null;
        }
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

    public List<SelectItem> getCimpotItem() {
        List<SelectItem> CimpotItem = new ArrayList<>();

        List<TCentreImpot> listCi = tCentreImpotFacade.findAll();

        for (TCentreImpot ci : listCi) {
            CimpotItem.add(new SelectItem(ci, ci.getCentrImpCode() + " --> " + ci.getCentrImpLibelle()));
        }

        return CimpotItem;
    }

    public TRepUnique getTRepUnique(java.lang.Long id) {
        return ejbFacade.find(id);
    }

    public void ifuValueChangeListener(ValueChangeEvent ev) {
        String ru = (ev.getNewValue().toString());
        Long lg = new Long(ru);

        System.out.println(" num voy " + " " + ev.getNewValue());
        TRepUnique list = ejbFacade.findByContImmatr(ifu.getContImmatr());
        System.out.println(" num voy " + " " + list);
        ItemsIfu = list;
//        
//        dispo = list.getContImmatr();
//        
//        System.out.println(" dispo " + " " + dispo);

    }

    public List<String> completeText(String query) {

        List<String> results = new ArrayList<String>();
        List<TRepUnique> listC = ejbFacade.findContribByImmatLike(query);
        for (TRepUnique c : listC) {
            results.add(c.getContImmatr().toString());
        }

        return results;
    }

    public String contribSelectionne(TRepUnique contrib) {
        System.out.println(current + "pris " + items);
        System.out.println("je suis l'ifu sélectionné " + contrib);
        current.setContImmatr(contrib.getContImmatr());

        ifu = contrib;
        return "";
    }

    public String UpdateTableContrib() {

        TRepUnique trepunique = ejbFacade.findByContImmatr(ifu.getContImmatr());

        ItemsIfu = trepunique;
        prepareAfficheMAJIndividuelle(trepunique);

        if ("A".equals(ItemsIfu.getContStatut())) {
            setAfficheStatut("ACTIVE");
        } else {
            setAfficheStatut("DESACTIVE");
        }

        return "";
    }

    @FacesConverter(forClass = TRepUnique.class)
    public static class TRepUniqueControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TRepUniqueController controller = (TRepUniqueController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tRepUniqueController");
            return controller.getTRepUnique(getKey(value));
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
            if (object instanceof TRepUnique) {
                TRepUnique o = (TRepUnique) object;
                return getStringKey(o.getContImmatr());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TRepUnique.class.getName());
            }
        }

    }
}
