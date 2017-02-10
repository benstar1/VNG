package bj.finances.cfisc.controllers;

import bj.finances.cfisc.entities.TUtilisateur;
import bj.finances.cfisc.controllers.util.JsfUtil;
import bj.finances.cfisc.controllers.util.PaginationHelper;
import bj.finances.cfisc.entities.TGroupe;
import bj.finances.cfisc.entities.TRepUnique;
import bj.finances.cfisc.sessions.TGroupeFacade;
import bj.finances.cfisc.sessions.TRepUniqueFacade;
import bj.finances.cfisc.sessions.TUtilisateurFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import sun.security.provider.MD5;

@ManagedBean(name = "tUtilisateurController")
@SessionScoped
public class TUtilisateurController implements Serializable {

    @EJB
    private TGroupeFacade tGroupeFacade;

    @EJB
    private TRepUniqueFacade tRepUniqueFacade;

    private TUtilisateur current;
    private DataModel items = null;
    @EJB
    private bj.finances.cfisc.sessions.TUtilisateurFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    TRepUnique contrib;

    public TRepUnique getContrib() {
        return contrib;
    }

    public void setContrib(TRepUnique contrib) {
        this.contrib = contrib;
    }
    private Long ifu;

    public Long getIfu() {
        return ifu;
    }

    public void setIfu(Long ifu) {
        this.ifu = ifu;
    }

    public void rechercheContribuable() throws NullPointerException {
        System.out.println("IFU est " + ifu);

        contrib = tRepUniqueFacade.find(ifu);
//
//        if (contrib == null) {
//            FacesMessage message = new FacesMessage("Ce numéro IFU est incorrect !");
//            throw new NullPointerException(message.toString());
//        }

        System.out.println("Le contrib est " + contrib);
    }

    public TUtilisateurController() {
    }

    public TUtilisateur getSelected() {
        if (current == null) {
            current = new TUtilisateur();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TUtilisateurFacade getFacade() {
        return ejbFacade;
    }

    public void validateEmail(FacesContext context, UIComponent toValidate,
            Object value) throws ValidatorException {
        String eMail = (String) value;
        if (eMail.indexOf("@") < 0) {
            FacesMessage message = new FacesMessage("Adresse Email invalide !");
            throw new ValidatorException(message);
        }
    }

    public void validateIfu(FacesContext context, UIComponent toValidate,
            Object value) throws ValidatorException {
        Long ifusaisi = (Long) value;

        if (tRepUniqueFacade.find(ifusaisi) == null) {
            FacesMessage message = new FacesMessage("Ce numéro IFU est incorrect !");
            throw new ValidatorException(message);

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
        current = (TUtilisateur) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new TUtilisateur();
        selectedItemIndex = -1;
        return "Create";
    }

    private TRepUnique utilContImmatr;

    public String prepareCreateContrib() {
        //current = new TUtilisateur();
        System.out.println("Objet courant " + current);
//        utilContImmatr = se
        selectedItemIndex = -1;
        return "Confirm?faces-redirect=true&includeViewParams=true";
    }

    // Modif MD du mot de passe
    @Inject
    ConvertirMD5 md5;

    @Inject
    SendSMSMBean sms;

    public String create() {

        String pwdmd5 = md5.generateMD5(current.getUtilPassword());
        current.setUtilPassword(pwdmd5);
        try {

//            
            String login = null;
            try {

//                login = current.getUtilContImmatr().getContImmatr().toString();
                login = ifu.toString();

                //ejbFacade.find(login);
//                if ((tRepUniqueFacade.findByContImmatr(current.getUtilContImmatr().getContImmatr()) != null) && (ejbFacade.find(login) != null)) {
                if ((tRepUniqueFacade.findByContImmatr(ifu) != null) && (ejbFacade.find(login) != null)) {
                    JsfUtil.addSuccessMessage("Ce numéro IFU est déjà enregistré");
                }

            } catch (Exception e) {
                JsfUtil.addErrorMessage(e, "Le numéro IFU est incorrect.");
            }

            current.setGroupe(tGroupeFacade.find("CONTRIBUABLE"));
            System.out.println("Le groupe du contribuable " + tGroupeFacade.find("CONTRIBUABLE"));
            System.out.println("Login " + login);
            current.setUtilLogin(login);
            current.setUtilContImmatr(tRepUniqueFacade.findByContImmatr(ifu));

            Long pin = sms.aleatoire();
            System.out.println("Pin = " + pin);

            // Récupération du nom et prénom du 
            if (contrib.getContNom() == null) {
                current.setUtilNom(contrib.getContRais());
                current.setUtilPrenoms(contrib.getContNomLong());
            } else {
                current.setUtilNom(contrib.getContNom());
                current.setUtilPrenoms(contrib.getContPren());
            }

            current.setUtilActif("N");

////            SendSMSMBean http = new SendSMSMBean();
////
////            System.out.println("Testing 1 - Send Http GET request");

            System.out.println("Ici 1" + current);

            getFacade().create(current);

            // Récupération du numéro de phone
            String dest = "229" + current.getUtilTel();
            System.out.println("Le numéro du destinataire est : " + dest);

//            http.sendGet("22997217745", dest, "Bienvenue+sur+la+plateforme+IFU.+Votre+code+d'activation+est+:+", pin);
//            http.sendGet("22997217745", "22997217744", "Bienvenue+sur+la+plateforme+IFU.+Votre+code+d'activation+est+:+", pin);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TUtilisateurCreated"));
            return prepareCreateContrib();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (TUtilisateur) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TUtilisateurUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    String codeSecret;

    public String getCodeSecret() {
        return codeSecret;
    }

    public void setCodeSecret(String codeSecret) {
        this.codeSecret = codeSecret;
    }

    public String updatePin() {

        System.out.println("Code secret " + codeSecret + " Objet courant update " + current.getUtilActif());
        try {
////            
            if (codeSecret.equals(current.getUtilActif())) {
                current.setUtilActif("1");
                getFacade().edit(current);

                // Assignation de l'utilisateur au groupe contribuable
                current = new TUtilisateur();

                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TUtilisateurUpdated"));

                return "Confirm2";
            } else {
                JsfUtil.addSuccessMessage("Le code secret est incorrect. Si le problème persiste ou que vous ne recevez pas le code secret, veuillez contacter l'administration fiscale.");
                return null;
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (TUtilisateur) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TUtilisateurDeleted"));
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

    public List<SelectItem> getGroupeItem() {
        List<SelectItem> GroupeItem = new ArrayList<>();

        List<TGroupe> listGr = tGroupeFacade.findAll();

        for (TGroupe gr : listGr) {
            GroupeItem.add(new SelectItem(gr, gr.getGroupId() + " --> " + gr.getGroupName()));
        }

        return GroupeItem;
    }

    @FacesConverter(forClass = TUtilisateur.class)
    public static class TUtilisateurControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TUtilisateurController controller = (TUtilisateurController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tUtilisateurController");
            return controller.ejbFacade.find(getKey(value));
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
            if (object instanceof TUtilisateur) {
                TUtilisateur o = (TUtilisateur) object;
                return getStringKey(o.getUtilLogin());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TUtilisateur.class.getName());
            }
        }

    }

}
