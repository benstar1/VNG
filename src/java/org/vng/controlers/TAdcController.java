package org.vng.controlers;

import java.io.File;
import org.vng.entities.TAdc;
import org.vng.controlers.util.JsfUtil;
import org.vng.controlers.util.JsfUtil.PersistAction;
import org.vng.sessions.TAdcFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.vng.entities.TDepotSignature;
import org.vng.entities.TIntervenant;
import org.vng.entities.TParcelleBafon;
import org.vng.entities.TParcellePoca;

@Named("tAdcController")
@SessionScoped
public class TAdcController implements Serializable {

    @EJB
    private org.vng.sessions.TAdcFacade ejbFacade;
    @EJB
    private org.vng.sessions.TDepotSignatureFacade ejbFacadeDepotSignature;
    
     @EJB
    private org.vng.sessions.TParcellePocaFacade ejbFacadeParcellePoca;

    /////////////////
    @Resource(lookup = "jdbc/DSappligeo") ///////faut modifier
    private DataSource dataSource;
    JasperPrint jasperPrint;
    private List<TAdc> items = null;
    private TAdc selected;
    private TDepotSignature depotSignature;

    public TAdcController() {
    }

    public TDepotSignature getDepotSignature() {
        return depotSignature;
    }

    public void setDepotSignature(TDepotSignature depotSignature) {
        this.depotSignature = depotSignature;
    }

    public TAdc getSelected() {
        return selected;
    }

    public void setSelected(TAdc selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TAdcFacade getFacade() {
        return ejbFacade;
    }

    public TAdc prepareCreate() {
        selected = new TAdc();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TAdcCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TAdcUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TAdcDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TAdc> getItems() {
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

    public TAdc getTAdc(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<TAdc> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TAdc> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TAdc.class)
    public static class TAdcControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TAdcController controller = (TAdcController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tAdcController");
            return controller.getTAdc(getKey(value));
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
            if (object instanceof TAdc) {
                TAdc o = (TAdc) object;
                return getStringKey(o.getAdcCode());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TAdc.class.getName()});
                return null;
            }
        }
    }
    
    /////////parcelle limitrophes
       private String parcelleLimitrophes(String pba, String pointcardino) {
        String limites = "";
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
        try {
            
            List<TParcellePoca> listparcellepoca = ejbFacadeParcellePoca.executeLimite(pba, pointcardino);
            for (TParcellePoca poca : listparcellepoca) {
                limites += df.format(poca.getPapoLongLimitroph().doubleValue()) + " mètres par " + poca.getPapoPbaNumeroLimit().getPbaNumero() + " ; ";
            }
        } catch (Exception e) {
            System.out.println("Probeleme de selection de la limite " + e);
        }

        return limites;
    }

    //////////////permet de recuperer la signature d'un intervenant///////////
    private void initSignatureIntervenant(TIntervenant interv) {
        try {
            List<TDepotSignature> listeDepot;
            listeDepot = ejbFacadeDepotSignature.executeListeDepotIntervenant(interv);
            if (listeDepot != null) {
                setDepotSignature(listeDepot.get(0));
            }

        } catch (Exception e) {

        }
    }

    public void imprimeAdc(String codeAdc) {
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/vues/etat/adc.jasper");
        //System.out.println("Chemin :" + reportPath);
        File file = new File(reportPath);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        TAdc adc = new TAdc();
        Connection connection;
        //  System.out.println("Connexion " + connection.getCatalog());
        HashMap map = new HashMap();
        String description = "", soussignee = "";
        String departement = "", commune = "", arrondissement = "", village = "",
                nomPrenomIntervenant = "", piece = "", numEnqete = "", reference = "", superficie = "",
                modeAcquis = "", nomPrenomBailleur = "", depot = "";
        String dateEnquete = "", dateAcquis = "", datedemande = "";
        String Article21="", Article22="", Article23="", Article24="";
        ///////////ou on prend directement le selected
        /////on selection l'adc a partir du code///////////
        adc = ejbFacade.find(codeAdc);
        //////////////////////////////////////////////////
        if (adc != null) {
            ////////////recup parcelle et localisation et limitrophe///////////
            TParcelleBafon parcelle = adc.getAdcPbaNumero();
            if (parcelle != null) {
                if (parcelle.getPbaVilaCode() != null) {
                    departement = parcelle.getPbaVilaCode().getVilaArrCode().getArrComCode().getComDepCode().getDepDesig();
                    commune = parcelle.getPbaVilaCode().getVilaArrCode().getArrComCode().getComDesig();
                    arrondissement = parcelle.getPbaVilaCode().getVilaArrCode().getArrDesig();
                    village = parcelle.getPbaVilaCode().getVlaDesig();
                    try {
                       
                        superficie = parcelle.getPbaSuperficie().toString().replace(".",",") ;
                        
                    } catch (Exception e) {
                        System.out.println("Problement de la recuperficie :" + e);
                    }

                }
                 ////////////////recuperation des limitrophes//////////////
            //////////////////recherche des limitrophes//////////////////////////////////////
            Article21 = parcelleLimitrophes(parcelle.getPbaNumero(), "NORD");
            Article21 = "sur " + Article21 + " \n";
            Article22 = parcelleLimitrophes(parcelle.getPbaNumero(), "SUD");
            Article22 = "sur " + Article22 + " \n";
            Article23 = parcelleLimitrophes(parcelle.getPbaNumero(), "EST");
            Article23 = "sur " + Article23 + "\n";
            Article24 = parcelleLimitrophes(parcelle.getPbaNumero(), "OUEST");
            Article24 = "sur " + Article24 + "\n";
            //////////////////FIN recherche des limitrophes//////////////////////////////////////
            
            }
            /////////////////////////////////////////////////////
            //////////////recuperation des infos sur intervenant detenteur////////////
            TIntervenant intervenant = adc.getAdcIntNumero();
            if (intervenant != null) {
                nomPrenomIntervenant = intervenant.getIntNom() + " " + intervenant.getIntPrenom();
                initSignatureIntervenant(intervenant);
                if (depotSignature != null) {
                    depot = depotSignature.getDesiReference();
                }
            }
            /////////////////////Fin recuperation des infos sur intervenant detenteur/////////////////
            numEnqete = adc.getAdcNumEnquete();
            reference = adc.getAdcReference();
            if (adc.getAdcMacCode() != null) {
                modeAcquis = adc.getAdcMacCode().getMacDesig();
            }
            nomPrenomBailleur = adc.getAdcDeQui();
            if (adc.getAdcDateEnquete() != null) {
                dateEnquete = sdf.format(adc.getAdcDateEnquete());
            }
            if (adc.getAdcDequisQuand() != null) {
                dateAcquis = sdf.format(adc.getAdcDequisQuand());
            }

            if (adc.getAdcDateDemande() != null) {
                datedemande = sdf.format(adc.getAdcDateDemande());
            }

            //////////////////je soussigneee//////////
            soussignee = "Nous, soussigné(e), Maire de la Commune de " + commune + " ,\n"
                    + "Vu la demande en date du " + datedemande + " pour le compte de " + nomPrenomIntervenant + " \n"
                    + "n° RAVIP/CNI/LEPI/IFU " + piece + " \n"
                    + "et enregistrée sous le numéro " + depot + " aux fins d’obtenir une Attestation de Détention Coutumière (ADC) ; \n"
                    + "Vu les pièces fournies par le requérant ; \n"
                    + "Vu le procès-verbal de l’enquête publique n°. " + numEnqete + " en date du " + dateEnquete + " ; \n"
                    + "Attestons que l’immeuble sis à : Département de " + departement + " Commune " + commune + " Arrondissement de " + arrondissement + " Village/Quartier de Ville de " + village + " \n"
                    + "Référence Description (Lot-Parcelle, QIP, REP, ZIP, EL) "+reference+" d’une contenance/Superficie estimée de " + superficie +" Mètre carrée.";
            ////////////////////description
            description = " Limité par \n\n" +
            "- Au Nord "+Article21+"\n" +
            "- Au sud "+Article22+"\n" +
            "- A l’Est "+Article23+"\n" +
            "- A l’Ouest "+Article24+"\n" + 
            "Est détenu par "+nomPrenomIntervenant+" qui l’a acquis par voie de "+modeAcquis+" de "+nomPrenomBailleur+" depuis "+dateAcquis;
            
            String dateSignateur="",signature="";
            
            dateSignateur=commune+" Le, "+sdf.format(adc.getAdcDateRetrait());
            if(adc.getAdcSignCode()!=null){
                signature=adc.getAdcSignCode().getSignFonction()+"\n\n\n"+adc.getAdcSignCode().getSignNom()+" "+adc.getAdcSignCode().getSignPrenom();
            }
            
                    
           
            ///////////////////////////////////////////////////////////
            ////////////recuperation de la connexion
            try {
                connection = dataSource.getConnection();
                ////////////////ajout des parametres
                map.put("ParamDepartement", "DEPARTEMENT DU " + departement);
                map.put("ParamCommune", "COMMUNE DE " + commune);
                System.out.println("Le numero de ADC : "+codeAdc);
                map.put("ParamNumAdc", codeAdc);
                map.put("ParamDescription", description);
                map.put("dateLieuSignature", dateSignateur);
                map.put("signatureAffirmation", signature);
                  
                // soussignee="toto";
                map.put("ParamSoussigne", soussignee);
                jasperPrint = JasperFillManager.fillReport(file.getPath(), map, connection);
                System.out.println("Taille liste classe --> " + jasperPrint.getPages().size());
                HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=adc.pdf");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                FacesContext.getCurrentInstance().responseComplete();
                System.out.println("servletOutputStream> " + servletOutputStream);
                connection.close();
            } catch (Exception e) {
                System.out.println(""+e);
            }

        } //////adc != null

    }

}
