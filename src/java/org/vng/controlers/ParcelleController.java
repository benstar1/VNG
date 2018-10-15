package org.vng.controlers;

import org.vng.entities.Parcelle;
import org.vng.controlers.util.JsfUtil;
import org.vng.controlers.util.JsfUtil.PersistAction;
import org.vng.sessions.ParcelleFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.vng.entities.TArrondissement;
import org.vng.entities.TCommune;
import org.vng.entities.TDepartement;
import org.vng.entities.TParcelleBafon;
import org.vng.entities.TVillage;

@Named("parcelleController")
@SessionScoped
public class ParcelleController implements Serializable {

   // String urlcarte = "http://localhost:8090/geoserver/appligeo/wms?service=WMS&version=1.1.0&request=GetMap&layers=appligeo:parcelleParam&styles=&bbox=1.798,7.015,1.809,7.028&width=649&height=768&srs=EPSG:4326&format=application/openlayers";
   String urlcarte ="http://localhost:8090/geoserver/appligeo/wms?service=WMS&version=1.1.0&request=GetMap&layers=appligeo:parcelleparamcorridor&styles=&bbox=1.77521627,7.00107533,1.83385575,7.07049001&width=648&height=768&srs=EPSG:4326&format=application/openlayers";
    @EJB
    private org.vng.sessions.ParcelleFacade ejbFacade;
    @EJB
    private org.vng.sessions.TDepartementFacade ejbFacadeDepartement;
    
    @EJB
    private org.vng.sessions.TVillageFacade ejbFacadeVillage;
    
    
    private List<Parcelle> items = null;
    List<TCommune> listcom;
    List<TVillage> listVillage;
    List<TArrondissement> listArr;
    List<TParcelleBafon> listParcelleNormal;
    private List<TDepartement> listDepartement;
    private Parcelle selected;
    private TVillage villageNormal;
    private String villageTopo;
    private int nombreTraitement;
    private TDepartement departement;
    private TCommune commune;
    private TArrondissement arrondissement;
    private int nombreParcelle;
    ////////Declaration des noeuds de l'arbre
    private TreeNode root;
    private TreeNode noeudDepartement;
    private TreeNode noeudCommune;
    private TreeNode noeudArrondissement;
    private TreeNode noeudVillage;
    private TreeNode noeudParcelle;
    private String libDepart;
    private String libCommune;
    private String libArr;
    private String libVillage;
    

    private TreeNode noeudSelectionner;

    public ParcelleController() {
    }

    private void initurlcarte() {
       // urlcarte = "http://localhost:8090/geoserver/appligeo/wms?service=WMS&version=1.1.0&request=GetMap&layers=appligeo:parcelleParam&styles=&bbox=1.798,7.015,1.809,7.028&width=600&height=600&srs=EPSG:4326&format=application/openlayers&viewparams=paramvillage";
    urlcarte ="http://localhost:8090/geoserver/appligeo/wms?service=WMS&version=1.1.0&request=GetMap&layers=appligeo:parcelleparamcorridor&styles=&bbox=1.77521627,7.00107533,1.83385575,7.07049001&width=648&height=768&srs=EPSG:4326&format=application/openlayers&viewparams=paramvillage";
    }

    public String getLibDepart() {
        return libDepart;
    }

    public void setLibDepart(String libDepart) {
        this.libDepart = libDepart;
    }

    public String getLibCommune() {
        return libCommune;
    }

    public void setLibCommune(String libCommune) {
        this.libCommune = libCommune;
    }

    public String getLibArr() {
        return libArr;
    }

    public void setLibArr(String libArr) {
        this.libArr = libArr;
    }

    public String getLibVillage() {
        return libVillage;
    }

    public void setLibVillage(String libVillage) {
        this.libVillage = libVillage;
    }

    public int getNombreParcelle() {
        return nombreParcelle;
    }

    public void setNombreParcelle(int nombreParcelle) {
        this.nombreParcelle = nombreParcelle;
    }
    

    public Parcelle getSelected() {
        return selected;
    }

    public void setSelected(Parcelle selected) {
        this.selected = selected;
    }

    public int getNombreTraitement() {
        return nombreTraitement;
    }

    public void setNombreTraitement(int nombreTraitement) {
        this.nombreTraitement = nombreTraitement;
    }

    public ParcelleFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ParcelleFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public TDepartement getDepartement() {
        return departement;
    }

    public void setDepartement(TDepartement departement) {
        this.departement = departement;
    }

    public TCommune getCommune() {
        return commune;
    }

    public void setCommune(TCommune commune) {
        this.commune = commune;
        initListeArrondissement();
    }

    public TArrondissement getArrondissement() {
        return arrondissement;
    }

    public void setArrondissement(TArrondissement arrondissement) {
        this.arrondissement = arrondissement;
        initListeVillage();
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getNoeudParcelle() {
        return noeudParcelle;
    }

    public void setNoeudParcelle(TreeNode noeudParcelle) {
        this.noeudParcelle = noeudParcelle;
    }

    public TreeNode getNoeudDepartement() {
        return noeudDepartement;
    }

    public void setNoeudDepartement(TreeNode noeudDepartement) {
        this.noeudDepartement = noeudDepartement;
    }

    public TreeNode getNoeudCommune() {
        return noeudCommune;
    }

    public void setNoeudCommune(TreeNode noeudCommune) {
        this.noeudCommune = noeudCommune;
    }

    public TreeNode getNoeudArrondissement() {
        return noeudArrondissement;
    }

    public void setNoeudArrondissement(TreeNode noeudArrondissement) {
        this.noeudArrondissement = noeudArrondissement;
    }

    public TreeNode getNoeudVillage() {
        return noeudVillage;
    }

    public void setNoeudVillage(TreeNode noeudVillage) {
        this.noeudVillage = noeudVillage;
    }

    public TreeNode getNoeudSelectionner() {
        return noeudSelectionner;
    }

    public void setNoeudSelectionner(TreeNode noeudSelectionner) {
        this.noeudSelectionner = noeudSelectionner;
    }

    public List<TCommune> getListcom() {
        return listcom;
    }

    public void setListcom(List<TCommune> listcom) {
        this.listcom = listcom;
    }

    public List<TArrondissement> getListArr() {
        return listArr;
    }

    public void setListArr(List<TArrondissement> listArr) {
        this.listArr = listArr;
    }

    public List<TVillage> getListVillage() {
        return listVillage;
    }

    public void setListVillage(List<TVillage> listVillage) {
        this.listVillage = listVillage;
    }

    public TVillage getVillageNormal() {
        return villageNormal;
    }

    public void setVillageNormal(TVillage villageNormal) {
        this.villageNormal = villageNormal;
    }

    public String getVillageTopo() {
        return villageTopo;
    }

    public void setVillageTopo(String villageTopo) {
        this.villageTopo = villageTopo;
    }

    public String getUrlcarte() {
        // urlcarte=urlcarte.replace("&","&amp;");
        System.out.println(urlcarte);
        return urlcarte;
    }

    public void setUrlcarte(String urlcarte) {
        this.urlcarte = urlcarte;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ParcelleFacade getFacade() {
        return ejbFacade;
    }

    public Parcelle prepareCreate() {
        selected = new Parcelle();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ParcelleCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ParcelleUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ParcelleDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Parcelle> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    @PostConstruct
    public void genererArbre() {
        listDepartement = ejbFacadeDepartement.findAll();
        root = new DefaultTreeNode("Root", null);
        for (TDepartement dep : listDepartement) {
            noeudDepartement = new DefaultTreeNode(dep.getDepCode() + "-" + dep.getDepDesig(), root);
            listcom = dep.getTCommuneList();
            for (TCommune com : listcom) {
                noeudCommune = new DefaultTreeNode(com.getComCode() + "-" + com.getComDesig(), noeudDepartement);
                listArr = com.getTArrondissementList();
                for (TArrondissement arr : listArr) {
                    noeudArrondissement = new DefaultTreeNode(arr.getArrCode() + "-" + arr.getArrDesig(), noeudCommune);
                    listVillage = arr.getTVillageList();
                    for (TVillage vil : listVillage) {
                        noeudVillage = new DefaultTreeNode(vil.getVilaCode() + "-" + vil.getVlaDesig(), noeudArrondissement);

                        listParcelleNormal = vil.getTParcelleBafonList();
                        for (TParcelleBafon pba : listParcelleNormal) {
                            noeudParcelle = new DefaultTreeNode(pba.getPbaNumero(), noeudVillage);
                        }

                    }
                }
            }

        }

    }

    public void affiche() {
        onSelected();
        //return urlcarte;
    }

    public void onSelected() {
        String index = noeudSelectionner.getRowKey();
        String donnerecupee = "";
        donnerecupee = (String) noeudSelectionner.getData();
        donnerecupee = donnerecupee.split("-")[0];
        System.out.println("Numero objet : " + donnerecupee);
        int ln = index.length();
        if (ln == 1) {//Selection de departement
            //recuperation du numero de departement

        }
        if (ln == 3) {//Selection de commune
            //recuperation du numero de commune

        }
        if (ln == 5) {//Selection de arrondissement
            //recuperation du numero de arrondissement

        }
        if (ln == 7) {//Selection de village
            initurlcarte();
            urlcarte = urlcarte.replace("paramvillage", "paramvillage:" + donnerecupee);
            try{
                villageNormal= ejbFacadeVillage.find(donnerecupee);
                if(villageNormal!=null){
                    setLibVillage(villageNormal.getVlaDesig());
                arrondissement=villageNormal.getVilaArrCode();
                    setLibArr(arrondissement.getArrDesig());
                commune=arrondissement.getArrComCode();
                    setLibCommune(commune.getComDesig());
                departement=commune.getComDepCode();
                    setLibDepart(departement.getDepDesig());
                setNombreParcelle( villageNormal.getTParcelleBafonList().size());
                }
            }catch(Exception e){
                    System.out.println("Village affiche non trouve"+e);
            }
            
            System.out.println(urlcarte);
        }
        if (ln == 9) {//Selection de parcelle
            //recuperation du numero de parcelle

        }

        // System.out.println("  TRTTRTRTRTRT " + noeudSelectionner.getData() + " Index " + noeudSelectionner.getRowKey());
    }

    public void onDragDrop(TreeDragDropEvent event) {
        TreeNode dragNode = event.getDragNode();
        TreeNode dropNode = event.getDropNode();
        int dropIndex = event.getDropIndex();
        System.out.println(" " + dragNode.getData());
        //      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dragged " + dragNode.getData(), "Dropped on " + dropNode.getData() + " at " + dropIndex);
        //      FacesContext.getCurrentInstance().addMessage(null, message);
//        
//         FacesMessage message = new FacesMessage("Succesful","Dragged " + dragNode.getData(), "Dropped on " + dropNode.getData() + " at " + dropIndex);
//            FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void initlisteCommune() {
        System.out.println(" Liste commune ");
        if (departement != null) {
            System.out.println("  Nombre de commune " + departement.getTCommuneList().size());
            setListcom(departement.getTCommuneList());
        }
    }

    public void initListeArrondissement() {
        System.out.println(" Liste Arrondissement ");
        if (commune != null) {
            System.out.println("  Nombre d'arrondissement " + commune.getTArrondissementList().size());
            setListArr(commune.getTArrondissementList());
        }
    }

    public void initListeVillage() {
        System.out.println(" Liste Village ");
        if (arrondissement != null) {
            System.out.println("  Nombre de village " + arrondissement.getTVillageList().size());
            setListVillage(arrondissement.getTVillageList());
        }
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

    public void traitNumeroParcelleTopo() {
        List<Parcelle> listParcelle = null;
        String seqparcelle;
        nombreTraitement = 0;
        System.out.println("Nous somme dans traitement de parcelles topo");
        try {
            listParcelle = ejbFacade.executeListeParcelleVillagetopo(getVillageTopo());
            for (Parcelle p : listParcelle) {
                System.out.println(" Parcelle " + p.getParcelleno());
                seqparcelle = p.getParcelleno();
                seqparcelle = String.format("%04d", Integer.parseInt(seqparcelle));

                p.setParPbaNumero(getVillageNormal().getVilaCode() + seqparcelle);
                ejbFacade.edit(p);
                System.out.println(" Parcelle " + seqparcelle + " Numero Normal " + getVillageNormal().getVilaCode() + seqparcelle);
                nombreTraitement++;
            }
            FacesMessage message = new FacesMessage("Succesful", nombreTraitement + " Parcelles traites avec succes  ");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
        }

    }

    public Parcelle getParcelle(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Parcelle> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Parcelle> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Parcelle.class)
    public static class ParcelleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ParcelleController controller = (ParcelleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "parcelleController");
            return controller.getParcelle(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Parcelle) {
                Parcelle o = (Parcelle) object;
                return getStringKey(o.getGid());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Parcelle.class.getName()});
                return null;
            }
        }

    }

}
