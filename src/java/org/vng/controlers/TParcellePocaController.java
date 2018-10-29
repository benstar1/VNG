package org.vng.controlers;

import org.vng.entities.TParcellePoca;
import org.vng.controlers.util.JsfUtil;
import org.vng.controlers.util.JsfUtil.PersistAction;
import org.vng.sessions.TParcellePocaFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import javax.faces.model.SelectItem;
import javax.persistence.EntityNotFoundException;
import org.vng.entities.Parcelle;
import org.vng.entities.TParcelleBafon;

@Named("tParcellePocaController")
@SessionScoped
public class TParcellePocaController implements Serializable {

    @EJB
    private org.vng.sessions.TParcellePocaFacade ejbFacade;
    @EJB
    private org.vng.sessions.ParcelleFacade ejbFacadeParcelle;

    @EJB
    private org.vng.sessions.TParcelleBafonFacade ejbFacadeParcelleBafon;

    private List<TParcellePoca> items = null;
    private TParcellePoca selected;
    List<SelectItem> pointCardiItem = new ArrayList<>();
    List<SelectItem> parcelleLimitropheItem = new ArrayList<>();
    TParcelleBafon parcelbafond;

    public TParcellePocaController() {
        selected = new TParcellePoca();
    }

    public List<SelectItem> getParcelleLimitropheItem() {
        return parcelleLimitropheItem;
    }

    public void setParcelleLimitropheItem(List<SelectItem> parcelleLimitropheItem) {
        this.parcelleLimitropheItem = parcelleLimitropheItem;
    }

    public TParcelleBafon getParcelbafond() {
        return parcelbafond;
    }

    public void setParcelbafond(TParcelleBafon parcelbafond) {
        this.parcelbafond = parcelbafond;
    }

    public TParcellePoca getSelected() {
       if (selected!=null){
        setParcelbafond(selected.getTParcelleBafon());
      //  System.out.println(getParcelbafond());
    }
        
        return selected;
    }

    public void setSelected(TParcellePoca selected) {
        this.selected = selected;
    }

    public void initParcelle(TParcelleBafon parcelle) {
        // prepareCreate();
        setParcelbafond(parcelle);

    }

    public void calculeLongueurLimite() {
        BigDecimal bd = BigDecimal.ZERO;
        try {
            System.out.println("TOTOTOT   "+getParcelbafond().getPbaNumero()+" "+ selected.getPapoPbaNumeroLimit().getPbaNumero());
            String pb=getParcelbafond().getPbaNumero();
            String pblim=selected.getPapoPbaNumeroLimit().getPbaNumero();
            Object obj = ejbFacadeParcelle.executCalculDistanceLimite(pb,pblim);
            Double l = (Double) obj;
            bd = BigDecimal.valueOf(l);
            selected.setPapoLongLimitroph(bd);
            System.out.println("Longueur " + bd);
        } catch (Exception e) {
            System.out.println("Probleme de calcul longueur limite "+e);
        }
    }

    public void initparcelleLimitrophe() {
        ////////////////initialisation des parcelles limitrophes
        TParcelleBafon pba;
        String numparc;
        parcelleLimitropheItem = new ArrayList<>();
        List<Object[]> listParcelletopo = ejbFacadeParcelle.listeParcelleLimit(getParcelbafond().getPbaNumero());
        System.out.println(" taille " + listParcelletopo.size());

        if (listParcelletopo != null) {
            for (Object str : listParcelletopo) {
                numparc = (String) str;
                try {
                    pba = ejbFacadeParcelleBafon.find(numparc);
                    parcelleLimitropheItem.add(new SelectItem(pba, pba.getPbaNumero()));
                    pba = null;
                } catch (EntityNotFoundException e) {
                }
            }
        }
    }

    protected void setEmbeddableKeys() {
        selected.getTParcellePocaPK().setPapoPocaCode(selected.getTPointCardino().getPocaCode());
        selected.getTParcellePocaPK().setPapoPbaNumero(selected.getTParcelleBafon().getPbaNumero());
    }

    protected void initializeEmbeddableKey() {
        selected.setTParcellePocaPK(new org.vng.entities.TParcellePocaPK());
    }

    private TParcellePocaFacade getFacade() {
        return ejbFacade;
    }

    public TParcellePoca prepareCreate() {
        // TParcelleBafon pba=selected.getTParcelleBafon();
        selected = new TParcellePoca();
        initializeEmbeddableKey();
        initparcelleLimitrophe();
        // selected.setTParcelleBafon(pba);
        return selected;
    }

    public void create() {
        selected.setTParcelleBafon(parcelbafond);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TParcellePocaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        //selected.setTParcelleBafon(getParcelbafond());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TParcellePocaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TParcellePocaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TParcellePoca> getItems() {
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

    public TParcellePoca getTParcellePoca(org.vng.entities.TParcellePocaPK id) {
        return getFacade().find(id);
    }

    public List<TParcellePoca> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TParcellePoca> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TParcellePoca.class)
    public static class TParcellePocaControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TParcellePocaController controller = (TParcellePocaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tParcellePocaController");
            return controller.getTParcellePoca(getKey(value));
        }

        org.vng.entities.TParcellePocaPK getKey(String value) {
            org.vng.entities.TParcellePocaPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new org.vng.entities.TParcellePocaPK();
            key.setPapoPocaCode(values[0]);
            key.setPapoPbaNumero(values[1]);
            return key;
        }

        String getStringKey(org.vng.entities.TParcellePocaPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getPapoPocaCode());
            sb.append(SEPARATOR);
            sb.append(value.getPapoPbaNumero());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TParcellePoca) {
                TParcellePoca o = (TParcellePoca) object;
                return getStringKey(o.getTParcellePocaPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TParcellePoca.class.getName()});
                return null;
            }
        }

    }

}
