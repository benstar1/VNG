package org.vng.controlers;

import java.io.File;
import java.io.IOException;
import org.vng.entities.TOperationParcel;
import org.vng.controlers.util.JsfUtil;
import org.vng.controlers.util.JsfUtil.PersistAction;
import org.vng.sessions.TOperationParcelFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBException;
//import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
//import javax.faces.model.SelectItem;
import org.primefaces.event.CellEditEvent;
import org.vng.controlers.util.Convert;
import org.vng.entities.Parcelle;
import org.vng.entities.TCommune;
import org.vng.entities.TConditionpaie;
import org.vng.entities.TDepotSignature;
//import org.vng.entities.TDepotSignature;
import org.vng.entities.TDroitExerce;
import org.vng.entities.TIntervenant;
import org.vng.entities.TIntervenir;
import org.vng.entities.TModePartage;
import org.vng.entities.TModeacquis;
import org.vng.entities.TParcelleBafon;
import org.vng.entities.TParcellePoca;
import org.vng.entities.TPeriodicite;
import org.vng.entities.TRole;
import org.vng.entities.TSignataire;
import org.vng.entities.TTypeOperationParcel;
import org.vng.entities.TTypedexerce;
import org.vng.entities.TUtilisateur;
//import org.vng.sessions.TTypedexerceFacade;

@Named("tOperationParcelController")

//@ManagedBean
@SessionScoped
public class TOperationParcelController implements Serializable {
    ///inection de tIntervenirControleur
    @Inject
    org.vng.controlers.TIntervenirController intervenirControleur;
    @Inject
    org.vng.controlers.TDroitExerceController droitExerceControleur;

    @EJB
    private org.vng.sessions.TOperationParcelFacade ejbFacade;
    private List<TOperationParcel> items = null;
    private TOperationParcel selected;

    @EJB
    private org.vng.sessions.TIntervenirFacade ejbFacadeIntervenir;

    @EJB
    private org.vng.sessions.TModeacquisFacade ejbFacadeModeAcquisition;

    @EJB
    private org.vng.sessions.TTypedexerceFacade ejbFacadeTypedroit;

    @EJB
    private org.vng.sessions.TDroitExerceFacade ejbFacadeDroitExerce;
    
    @EJB
    private org.vng.sessions.TParcellePocaFacade ejbFacadeParcellePoca;

    @EJB
    private org.vng.sessions.TRoleFacade ejbFacaderole;
    @EJB
    private org.vng.sessions.ParcelleFacade ejbFacadeParcelle;
    
     @EJB
    private org.vng.sessions.TDepotSignatureFacade ejbFacadeDepotSignature;

    //@Resource(mappedName = "java:app/vng", type = DataSource.class)
    //private DataSource myDB;
    @Resource(lookup = "jdbc/DSappligeo")
    private DataSource dataSource;
    JasperPrint jasperPrint;
    
    //////////////////////////////////
    Convert concertisseur;
    ////////////////////////////////////

    //////////// intervenir du bailleur
    TIntervenir intervenirbailleur;
    //////////// intervenir du preneur
    TIntervenir intervenirPreneur = new TIntervenir();

    //////declaration pour les cle etrangeres
    private String typeOperation;
    //pour operation parcelle
    private TConditionpaie conditionPaie;
    private TIntervenant intervenantBailleur;
    private TIntervenant intervenantPreneur = new TIntervenant();
    private TModeacquis modeAcquisition;
    private TPeriodicite periodicite;
    private TModePartage modePartage;
    private TUtilisateur utilisateur;
    private TTypeOperationParcel typeOperationParcelle;
    private TParcelleBafon parcelleBafonOperation;
    //pour intervenir
    private TCommune commune;
    private TOperationParcel OperationPreneurIntervenir;
    private TRole roleOperation;
    //parcelleBafonOperation
//    private TParcelleBafon parcelleBafonLimitIntervenir;
    //pour droit exerce
    private TDroitExerce droitExerce;
    private List<TDroitExerce> listDroitExerce;
    private TTypedexerce typeDroitExerce;
    List<TTypedexerce> typeDroitItem = new ArrayList<>();
    private List<TModeacquis> listmodeAcquisition;
    private List<Parcelle> parcelleLimites;
    int i = 0;

    // cat de droit exerce  DEX=droit d'administration ou DOP== droit operationel
    //////////////affichage des libelles/////////////
    private String villagelib;
    private String arrondissementlib;
    private String communelib;
    private String departementlib;
    ///////info sur vendeur
    private String collectivite;
    private String nom;
    private String prenom;
    private String domicile;
    private String age;
    ///////info sur acheteur/preneur
    private String collectivitepreneur;
    private String nompreneur;
    private String prenompreneur;
    private String domicilepreneur;
    private String agepreneur;
    String civbailsufixe = "";
    String civprensufixe = "";
    String nomme ="la nommée ";
    String pronom =" elle ";
     String article =" La ";
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    /////////////////Les temoins//////////
    private TDepotSignature signatureBail = new TDepotSignature();
    private List<TDepotSignature> listDepotSignTemBail = new ArrayList<>();
    private List<TIntervenir> listInvTemBail = new ArrayList<>();

    private List<TDepotSignature> listDepotSignTemPren = new ArrayList<>();
    private List<TIntervenir> listInvTemPren = new ArrayList<>();
    private TDepotSignature signaturePren = new TDepotSignature();
    ////////////////Tableau des limitations///////////
    //////Limitation droit administration
    private String[] listeLimiteDroitAdmin;
    private String[] listeLimiteDroitOperat;
    private String civilite, civilitesuffixe;
    private String superficie;
    ////
    private String newNumeroOpParcelle;
    /////
    private String libBailleur;
    private String libPreneur;

    private boolean estDon = false;

    private boolean estvalider = false;

    private boolean selectModeAcquis = false;

    private boolean estdroitoperationnel = false;

    //////gestion limitation
    String limitationchaine;
    String limitationchaineop;

    public List<Parcelle> getParcelleLimites() {
        return parcelleLimites;
    }

    public void setParcelleLimites(List<Parcelle> parcelleLimites) {
        this.parcelleLimites = parcelleLimites;
    }

    public boolean isEstdroitoperationnel() {
        return estdroitoperationnel;
    }

    public void setEstdroitoperationnel(boolean estdroitoperationnel) {
        this.estdroitoperationnel = estdroitoperationnel;
    }

    public boolean isSelectModeAcquis() {
        return selectModeAcquis;
    }

    public void setSelectModeAcquis(boolean selectModeAcquis) {
        this.selectModeAcquis = selectModeAcquis;
    }

    public List<TModeacquis> getListmodeAcquisition() {
        return listmodeAcquisition;
    }

    public void setListmodeAcquisition(List<TModeacquis> listmodeAcquisition) {
        this.listmodeAcquisition = listmodeAcquisition;
    }

    public boolean isEstvalider() {
        return estvalider;
    }

    public void setEstvalider(boolean estvalider) {
        this.estvalider = estvalider;
    }

    public String getLibBailleur() {
        return libBailleur;
    }

    public void setLibBailleur(String libBailleur) {
        this.libBailleur = libBailleur;
    }

    public String getLibPreneur() {
        return libPreneur;
    }

    public void setLibPreneur(String libPreneur) {
        this.libPreneur = libPreneur;
    }

    public boolean isEstDon() {
        return estDon;
    }

    public void setEstDon(boolean estDon) {
        this.estDon = estDon;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public List<TIntervenir> getListInvTemBail() {
        return listInvTemBail;
    }

    public void setListInvTemBail(List<TIntervenir> listInvTemBail) {
        this.listInvTemBail = listInvTemBail;
    }

    public List<TIntervenir> getListInvTemPren() {
        return listInvTemPren;
    }

    public void setListInvTemPren(List<TIntervenir> listInvTemPren) {
        this.listInvTemPren = listInvTemPren;
    }

    public String getNewNumeroOpParcelle() {
        return newNumeroOpParcelle;
    }

    public String getLimitationchaine() {
        return limitationchaine;
    }

    public void setLimitationchaine(String limitationchaine) {
        this.limitationchaine = limitationchaine;
    }

    public String getLimitationchaineop() {
        return limitationchaineop;
    }

    public void setLimitationchaineop(String limitationchaineop) {
        this.limitationchaineop = limitationchaineop;
    }

    public void setNewNumeroOpParcelle(String newNumeroParcelle) {
        this.newNumeroOpParcelle = newNumeroParcelle;
    }

    public String[] getListeLimiteDroitAdmin() {
        return listeLimiteDroitAdmin;
    }

    public void setListeLimiteDroitAdmin(String[] listeLimiteDroitAdmin) {
        this.listeLimiteDroitAdmin = listeLimiteDroitAdmin;
    }

    public String[] getListeLimiteDroitOperat() {
        return listeLimiteDroitOperat;
    }

    public void setListeLimiteDroitOperat(String[] listeLimiteDroitOperat) {
        this.listeLimiteDroitOperat = listeLimiteDroitOperat;
    }

    public TDepotSignature getSignatureBail() {
        return signatureBail;
    }

    public void setSignatureBail(TDepotSignature signatureBail) {
        this.signatureBail = signatureBail;
       
    }

    public TDepotSignature getSignaturePren() {
        return signaturePren;
    }

    public void setSignaturePren(TDepotSignature signaturePren) {
        this.signaturePren = signaturePren;
        try {
            this.intervenantPreneur = signaturePren.getDesiIntNumero();
            setCollectivitepreneur(intervenantPreneur.getIntCollectivite());
            setNompreneur(intervenantPreneur.getIntNom());
            setPrenompreneur(intervenantPreneur.getIntPrenom());
            setDomicilepreneur(intervenantPreneur.getIntDomicile());
            setAgepreneur(String.valueOf(intervenantPreneur.getIntDateNais()));
        } catch (NullPointerException e) {
            System.out.println(" Signature inexistante " + e);
        }

    }

    public TIntervenir getIntervenirbailleur() {
        return intervenirbailleur;
    }

    public void setIntervenirbailleur(TIntervenir intervenirbailleur) {
        this.intervenirbailleur = intervenirbailleur;
    }

    public TIntervenir getIntervenirPreneur() {
        return intervenirPreneur;
    }

    public void setIntervenirPreneur(TIntervenir intervenirPreneur) {
        this.intervenirPreneur = intervenirPreneur;
    }

    public String getCollectivitepreneur() {
        return collectivitepreneur;
    }

    public void setCollectivitepreneur(String collectivitepreneur) {
        this.collectivitepreneur = collectivitepreneur;
    }

    public String getNompreneur() {
        return nompreneur;
    }

    public void setNompreneur(String nompreneur) {
        this.nompreneur = nompreneur;
    }

    public String getPrenompreneur() {
        return prenompreneur;
    }

    public void setPrenompreneur(String prenompreneur) {
        this.prenompreneur = prenompreneur;
    }

    public String getDomicilepreneur() {
        return domicilepreneur;
    }

    public void setDomicilepreneur(String domicilepreneur) {
        this.domicilepreneur = domicilepreneur;
    }

    public String getAgepreneur() {
        return agepreneur;
    }

    public void setAgepreneur(String agepreneur) {
        this.agepreneur = agepreneur;
    }

    public String getCollectivite() {
        return collectivite;
    }

    public void setCollectivite(String collectivite) {
        this.collectivite = collectivite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getVillagelib() {
        return villagelib;
    }

    public void setVillagelib(String villagelib) {
        this.villagelib = villagelib;
    }

    public String getArrondissementlib() {
        return arrondissementlib;
    }

    public void setArrondissementlib(String arrondissementlib) {
        this.arrondissementlib = arrondissementlib;
    }

    public String getCommunelib() {
        return communelib;
    }

    public void setCommunelib(String communelib) {
        this.communelib = communelib;
    }

    public String getDepartementlib() {
        return departementlib;
    }

    public void setDepartementlib(String departementlib) {
        this.departementlib = departementlib;
    }

    public List<TTypedexerce> getTypeDroitItem() {
        return typeDroitItem;
    }

    public void setTypeDroitItem(List<TTypedexerce> typeDroitItem) {
        this.typeDroitItem = typeDroitItem;
    }

    public TDroitExerce getDroitExerce() {
        System.out.println(" tout est la");
        return droitExerce;
    }

    public void setDroitExerce(TDroitExerce droitExerce) {
        this.droitExerce = droitExerce;
    }

    public List<TDroitExerce> getListDroitExerce() {
        return listDroitExerce;
    }

    public void setListDroitExerce(List<TDroitExerce> listDroitExerce) {
        this.listDroitExerce = listDroitExerce;
    }

    public List<TDepotSignature> getListDepotSignTemBail() {
        return listDepotSignTemBail;
    }

    public void setListDepotSignTemBail(List<TDepotSignature> listDepotSignTemBail) {
        this.listDepotSignTemBail = listDepotSignTemBail;
    }

    public List<TDepotSignature> getListDepotSignTemPren() {
        return listDepotSignTemPren;
    }

    public void setListDepotSignTemPren(List<TDepotSignature> listDepotSignTemPren) {
        this.listDepotSignTemPren = listDepotSignTemPren;
    }

    public TConditionpaie getConditionPaie() {
        return conditionPaie;
    }

    public void setConditionPaie(TConditionpaie conditionPaie) {
        this.conditionPaie = conditionPaie;
    }

    public TIntervenant getIntervenantBailleur() {
        return intervenantBailleur;
    }

    public void setIntervenantBailleur(TIntervenant intervenantBailleur) {
        this.intervenantBailleur = intervenantBailleur;
        setCollectivite(intervenantBailleur.getIntCollectivite());
        setNom(intervenantBailleur.getIntNom());
        setPrenom(intervenantBailleur.getIntPrenom());
        setDomicile(intervenantBailleur.getIntDomicile());
        setAge(String.valueOf(intervenantBailleur.getIntDateNais()));
    }

    public TIntervenant getIntervenantPreneur() {
        return intervenantPreneur;
    }

    public void setIntervenantPreneur(TIntervenant intervenantPreneur) {
        this.intervenantPreneur = intervenantPreneur;
    }

    public TModeacquis getModeAcquisition() {
        return modeAcquisition;
    }

    public void setModeAcquisition(TModeacquis modeAcquisition) {
        this.modeAcquisition = modeAcquisition;
        selected.setOpvMacCode(modeAcquisition);
    }

    public TPeriodicite getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(TPeriodicite periodicite) {
        this.periodicite = periodicite;
    }

    public TModePartage getModePartage() {
        return modePartage;
    }

    public void setModePartage(TModePartage modePartage) {
        this.modePartage = modePartage;
    }

    public TUtilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(TUtilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public TTypeOperationParcel getTypeOperationParcelle() {
        return typeOperationParcelle;
    }

    public void setTypeOperationParcelle(TTypeOperationParcel typeOperationParcelle) {
        this.typeOperationParcelle = typeOperationParcelle;
    }

    public TParcelleBafon getParcelleBafonOperation() {
        return parcelleBafonOperation;
    }

    public void setParcelleBafonOperation(TParcelleBafon parcelleBafonOperation) {
        this.parcelleBafonOperation = parcelleBafonOperation;
    }

    public TCommune getCommune() {
        return commune;
    }

    public void setCommune(TCommune commune) {
        this.commune = commune;
    }

    public TOperationParcel getOperationPreneurIntervenir() {
        return OperationPreneurIntervenir;
    }

    public void setOperationPreneurIntervenir(TOperationParcel OperationPreneurIntervenir) {
        this.OperationPreneurIntervenir = OperationPreneurIntervenir;
    }

    public TRole getRoleOperation() {
        return roleOperation;
    }

    public void setRoleOperation(TRole roleOperation) {
        this.roleOperation = roleOperation;
    }

    public TTypedexerce getTypeDroitExerce() {
        return typeDroitExerce;
    }

    public void setTypeDroitExerce(TTypedexerce typeDroitExerce) {
        this.typeDroitExerce = typeDroitExerce;
    }

    /////////////////////////////////////////
    @PostConstruct
    public void getItemsTypeDroitCat() {
        selected = new TOperationParcel();
        intervenantPreneur = new TIntervenant();
        listDroitExerce = new ArrayList<>();
        //typeDroitItem = ejbFacadeTypedroit.executeListeTypeDroit("DE");
        genererListTypeDroit("DE");
        setLimitationchaine("FALSE");
        setLimitationchaineop("FALSE");
        //intervenirPreneur.setInvLimitation(false);
    }

    private void genererListTypeDroit(String catType) {
        typeDroitItem = ejbFacadeTypedroit.executeListeTypeDroit(catType);
    }

    ////////////filtrage des mode d'acquisition suivant le type de droits
    public void ItemsModeAcquisition(String typeMode) {
        try {
            if (typeMode.equals("HERITAGE")) {
                listmodeAcquisition = ejbFacadeModeAcquisition.executeListeModeaquisHerit();
            }
            if (typeMode.equals("OPERATIONNEL")) {
                listmodeAcquisition = ejbFacadeModeAcquisition.executeListeModeOperationel();
            }
        } catch (Exception e) {
            System.out.println("Probleme selection mode acquisition");
        }
    }

    /////////////type operation vente
    public String operationParcelle(String operation) {
        nouveau();
        //setTypeOperation(operation);
        ///////////////////Fixation du mode acquisition sur achat code=MD004
        ItemsModeAcquisition(operation);
        String page = "";
        setTypeOperation(operation);
        if (operation.equals("VENTE")) {
            setLibPreneur("Acheteur");
            setLibBailleur("Vendeur");
            modeAcquisition = ejbFacadeModeAcquisition.find("MD004");
            selected.setOpvMacCode(modeAcquisition);
            setSelectModeAcquis(false);
            setEstdroitoperationnel(false);
            genererListTypeDroit("DE");
            System.out.println(operation);
            ArrayList<TModeacquis> maqs = new ArrayList<>();
            maqs.add(modeAcquisition);
            GenererItemsOperationMode(maqs);
            setEstDon(false);
            page = "/vues/tOperationParcel/operaDroitAdminparcelle.xhtml";
        }
        ///////////////////////////////////////////////////////////
        if (operation.equals("DON")) {
            setLibPreneur("Bénéficiaire");
            setLibBailleur("Donateur");
            setSelectModeAcquis(false);
            modeAcquisition = ejbFacadeModeAcquisition.find("MD003");
            selected.setOpvMacCode(modeAcquisition);
            ArrayList<TModeacquis> maqs = new ArrayList<>();
            maqs.add(modeAcquisition);
            GenererItemsOperationMode(maqs);
            genererListTypeDroit("DE");
            selected.setOpvPrix(0l);
            setEstdroitoperationnel(false);
            System.out.println(operation);
            page = "/vues/tOperationParcel/operaDroitAdminparcelle.xhtml";
            setEstDon(true);
        }

        if (operation.equals("HERITAGE")) {
            setLibPreneur("Héritier");
            setLibBailleur("Parent");
            setSelectModeAcquis(true);
            setEstdroitoperationnel(false);
            ArrayList<TModeacquis> maqs = new ArrayList<>();
            modeAcquisition = ejbFacadeModeAcquisition.find("MD002");
            maqs.add(modeAcquisition);
            modeAcquisition = ejbFacadeModeAcquisition.find("MD009");
            maqs.add(modeAcquisition);
            genererListTypeDroit("DE");
            GenererItemsOperationMode(maqs);
            System.out.println(operation);
            setEstDon(true);
            selected.setOpvPrix(0l);
            page = "/vues/tOperationParcel/operaDroitAdminparcelle.xhtml";
        }

        if (operation.equals("OPERATIONNEL")) {
            setLibPreneur("Preneur");
            setLibBailleur("Bailleur");
            setSelectModeAcquis(true);
            setEstdroitoperationnel(true);
            System.out.println(operation);
            GenererItemsOperationCatMode("OP");
            genererListTypeDroit("OP");
            setEstDon(false);
            page = "/vues/tOperationParcel/operaDroitAdminparcelle.xhtml";
        }

        return page;
    }

    /////////////type operation DON
    public void operationDon() {
        ///////////////////Fixation du mode acquisition sur achat code=MD003

        ///////////////////////////////////////////////////////////
    }

    public void validationOperation() {
        try {
            selected.setOpvStatut("VALIDEE");
            selected.setOpvDateValidation(new Date());
            ejbFacade.edit(selected);
            setEstvalider(true);
        } catch (Exception e) {
        }
    }

    /////////////////changement limitation oui/non
    public void changeLimitationOuiNon() {
        System.out.println(getLimitationchaine());
        if (getLimitationchaine().equals("FALSE")) {
            intervenirPreneur.setInvLimitation(false);
        }
        if (getLimitationchaine().equals("TRUE")) {
            intervenirPreneur.setInvLimitation(true);
        }
    }
    ////limitation pour droits operationnels

    public void changeLimitationOpOuiNon() {
        System.out.println(getLimitationchaineop());
        if (getLimitationchaineop().equals("FALSE")) {
            intervenirPreneur.setInvLimitationOp("0");
        }
        if (getLimitationchaineop().equals("TRUE")) {
            intervenirPreneur.setInvLimitationOp("1");
        }
    }
    
    private void initSignatureBailleur(TIntervenant interv){
        try {
            List<TDepotSignature> listeDepot;
          listeDepot= ejbFacadeDepotSignature.executeListeDepotIntervenant(interv);
          if(listeDepot!=null){
             setSignatureBail(listeDepot.get(0));
          }
           
        } catch (Exception e) {
            
        }
    }

    ////////////////affichage operation parcelle
    public void afficheOperationParcelle(TOperationParcel operationParcelle) {
        nouveau();
        ///////////initialisation de l'operation
        selected = operationParcelle;
        if (selected.getOpvStatut() != null) {
            if (selected.getOpvStatut().equals("VALIDEE")) {
                setEstvalider(true);
            } else {
                setEstvalider(false);
            }
        }

        initParcelleBafonAfficheOperation(selected.getOpvPbaNumero());
        setSignatureBail(selected.getOpvDesiCode());
        setModeAcquisition(selected.getOpvMacCode());
        ///////////////////recuperation de intervenir bailleur
        if (selected.getTIntervenirList1() != null) {
            try {
                setIntervenirbailleur(selected.getTIntervenirList1().get(0));
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Debordement index " + e);
            }

        }

        ////init et affichage vendeur
        setIntervenantBailleur(selected.getOpvIntNumeroBailleur());
        setCollectivite(intervenantBailleur.getIntCollectivite());
        setNom(intervenantBailleur.getIntNom());
        setPrenom(intervenantBailleur.getIntPrenom());
        setDomicile(intervenantBailleur.getIntDomicile());
        setAge(String.valueOf(intervenantBailleur.getIntDateNais()));
        System.out.println("Intervenant bailleur " + intervenantBailleur.getIntNom());

        ////init et affichage acheteur
        setIntervenantPreneur(selected.getOpvIntNumeroPreneur());
        setCollectivitepreneur(intervenantPreneur.getIntCollectivite());
        setNompreneur(intervenantPreneur.getIntNom());
        setPrenompreneur(intervenantPreneur.getIntPrenom());
        setDomicilepreneur(intervenantPreneur.getIntDomicile());
        setAgepreneur(String.valueOf(intervenantPreneur.getIntDateNais()));
        listDroitExerce = selected.getTDroitExerceList1();
        roleOperation = selected.getOpvRolCode();

        listDepotSignTemPren = new ArrayList<>();
        listDepotSignTemBail = new ArrayList<>();

        listInvTemPren = new ArrayList<>();
        listInvTemBail = new ArrayList<>();

        ////init et affichage des temoins vendeur/bailleur
        List<TIntervenir> listIntervenirTemoin;
        listIntervenirTemoin = selected.getTIntervenirList();
        for (TIntervenir interv : listIntervenirTemoin) {
            System.out.println("Categorie role : " + interv.getInvRolCode().getRolCat());
            if (interv.getInvRolCode().getRolCat().equalsIgnoreCase("TEM_ACH")) {
                nouvelSignatureTemoinPren(interv.getInvDesiCode());
                listInvTemPren.add(interv);
            }
            if (interv.getInvRolCode().getRolCat().equalsIgnoreCase("TEM_VENT")) {
                nouvelSignatureTemoinBail(interv.getInvDesiCode());
                listInvTemBail.add(interv);
            }

            ////////////signature preneur
            if (interv != null) {
                if (interv.getInvRolCode().getRolCat().equalsIgnoreCase("DE")) {
                    setSignaturePren(interv.getInvDesiCode());
                    setIntervenirPreneur(interv);
                    System.out.println("intervenir preneur " + interv.getInvNumero());
                    try {
                        if (interv.getInvLimitation() == true) {
                            setLimitationchaine("TRUE");
                        }
                        if (interv.getInvLimitation() == false) {
                            setLimitationchaine("FALSE");
                        }
                        System.out.println("Limitation " + interv.getInvLimitation());
                    } catch (NullPointerException e) {
                        System.out.println(" " + e);
                    }

                }

                if (interv.getInvRolCode().getRolCat().equalsIgnoreCase("OP")) {
                    setSignaturePren(interv.getInvDesiCode());
                    setIntervenirPreneur(interv);
                    System.out.println("intervenir preneur " + interv.getInvNumero());
                    try {
                        if (interv.getInvLimitationOp().equals("1")) {
                            setLimitationchaineop("TRUE");
                        }
                        if (interv.getInvLimitationOp().equals("0")) {
                            setLimitationchaineop("FALSE");
                        }
                        System.out.println("Limitation operationnelles " + interv.getInvLimitationOp());
                    } catch (NullPointerException e) {
                        System.out.println(" " + e);
                    }

                }

            }
        }
        ////FIN init et affichage des temoins acheteur/preneur

        /////////////////////Affichage des limitations
        if (selected.getOpvMacCode().getMacCat().equals("OP")) {
            setEstdroitoperationnel(true);
            afficheLimitDroit("OPERATIONNEL");
        } else {
            afficheLimitDroit("ADMIN");
            setEstdroitoperationnel(false);
        }

        /////////////////////FIN Affichage des limitations
    }

    public void initParcelleBafonNewOperation(TParcelleBafon pbf) {
        initParcelleBafonOperation(pbf, "NOUVELOPERATION");
    }

    public void initParcelleBafonAfficheOperation(TParcelleBafon pbf) {
        initParcelleBafonOperation(pbf, "AFFICHEOPERATION");
    }

    //////declaration pour les cle etrangeres
    private void initParcelleBafonOperation(TParcelleBafon pbf, String seloncas) {
        System.out.println("Parcelle " + pbf.getPbaNumero());
        try {
            ///////recuperation des infos sur la parcelle
            setParcelleBafonOperation(pbf);
            selected.setOpvPbaNumero(pbf);
            setVillagelib(pbf.getPbaVilaCode().getVlaDesig());
            setArrondissementlib(pbf.getPbaVilaCode().getVilaArrCode().getArrDesig());
            setCommunelib(pbf.getPbaVilaCode().getVilaArrCode().getArrComCode().getComDesig());
            setDepartementlib(pbf.getPbaVilaCode().getVilaArrCode().getArrComCode().getComDepCode().getDepDesig());
        } catch (Exception e) {
        }
        /////recuperation du detendeur de droit d'admin
        if (seloncas.equals("NOUVELOPERATION")) {
            List<TIntervenir> listIntervenir = null;
            listIntervenir = ejbFacadeIntervenir.executeListeIntervRole(pbf, "DE");
            System.out.println(" taill " + listIntervenir.size());
            boolean interdi = false;
            if (listIntervenir != null) {
                try {
                    /////////////////Verifir l'operation est opossible///////////////////
                    TIntervenir IntBail = listIntervenir.get(0);
                    System.out.println(" Numero Inv " + IntBail.getInvNumero());
                    boolean existeLimDroitDet = IntBail.getInvLimitation();
                    if (existeLimDroitDet) {
                        if ((getTypeOperation().equals("VENTE")) && (IntBail.getInvLimitVent())) {
                            /////////////Impossible de vendre la parcelle
                            interdi = true;
                            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Interdition d'operation : ", "Operation de vente interdite sur cette parcelle");
                            FacesContext.getCurrentInstance().addMessage(null, message);
                        }

                        if ((getTypeOperation().equals("DON")) && (IntBail.getInvLimitVent())) {
                            /////////////Impossible de vendre la parcelle
                            interdi = true;
                            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Interdition d'operation : ", "Operation de Don interdite sur cette parcelle");
                            FacesContext.getCurrentInstance().addMessage(null, message);
                        }

                        if ((getTypeOperation().equals("LEG")) && (IntBail.getInvLimitVent())) {
                            /////////////Impossible de vendre la parcelle
                            interdi = true;
                            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Interdition d'operation : ", "Operation de Leg interdite sur cette parcelle");
                            FacesContext.getCurrentInstance().addMessage(null, message);
                        }

                        if ((getTypeOperation().equals("PRET")) && (IntBail.getInvLimitVent())) {
                            /////////////Impossible de vendre la parcelle
                            interdi = true;
                            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Interdition d'operation : ", "Operation de pret interdite sur cette parcelle");
                            FacesContext.getCurrentInstance().addMessage(null, message);
                        }

                    }
                    /////////////////////////////////////////////////////////////////////

                    if (!interdi) {
                        intervenirbailleur = listIntervenir.get(0);
                        setIntervenantBailleur(intervenirbailleur.getInvIntNumero());
                        selected.setOpvIntNumeroBailleur(intervenirbailleur.getInvIntNumero());
                        setCollectivite(intervenirbailleur.getInvIntNumero().getIntCollectivite());
                        setNom(intervenirbailleur.getInvIntNumero().getIntNom());
                        setPrenom(intervenirbailleur.getInvIntNumero().getIntPrenom());
                        setDomicile(intervenirbailleur.getInvIntNumero().getIntDomicile());
                        setAge(String.valueOf(intervenirbailleur.getInvIntNumero().getIntDateNais()));
                        System.out.println("Intervenante " + intervenirbailleur.getInvIntNumero().getIntNom());
                        initSignatureBailleur(intervenirbailleur.getInvIntNumero());
                    } else {
                        nouveau();
                    }
                } catch (Exception e) {
                    System.out.println("Pas de detenteur de DE " + e);
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Droit de detention : ", "Default de detenteur du droit de detention");
                    FacesContext.getCurrentInstance().addMessage(null, message);

                }

            }

        }

    }

//    public void nouvelDroitExerce(TSignataire signataire) {
//         selected.setOpvSignCode(signataire);
//    }
    public void nouvelDroitExerce(TTypedexerce typeDroitExerce) {

        droitExerce = new TDroitExerce();
        droitExerce.setDreCode(String.valueOf(i));
        i++;
        setTypeDroitExerce(typeDroitExerce);
        System.out.println(" Type de droit " + getTypeDroitExerce());
        droitExerce.setDreTdeCode(typeDroitExerce);
        droitExerce.setDrePbaNumero(parcelleBafonOperation);
        droitExerce.setDreRolCode(roleOperation);
        try {
            System.out.println("La taille " + listDroitExerce.size());

            listDroitExerce.add(droitExerce);
            System.out.println("Nous sommes dans droit exerce");
        } catch (Exception e) {
            System.out.println(" Probleme " + e);
        }
    }

    public void nouvelSignatureTemoinBail(TDepotSignature signature) {
        listDepotSignTemBail.add(signature);
    }

    public void nouvelSignatureTemoinPren(TDepotSignature signature) {
        listDepotSignTemPren.add(signature);
    }

    public void onDroitExerceCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        System.out.println("Nouvelle valeur " + newValue);
        if (newValue != null && !newValue.equals(oldValue)) {

//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
//            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void supprimeDex(TDroitExerce dexerce) {
        listDroitExerce.remove(dexerce);
        //System.out.println(" date debut " + listDroitExerce.get(0).getDreDateDebut());
    }

    public void supprimeSignatureBail(TDepotSignature depo) {
        listDepotSignTemBail.remove(depo);
        //System.out.println(" date debut " + listDroitExerce.get(0).getDreDateDebut());
    }

    public void supprimeSignaturePren(TDepotSignature depo) {
        listDepotSignTemPren.remove(depo);
        //System.out.println(" date debut " + listDroitExerce.get(0).getDreDateDebut());
    }

    public TOperationParcelController() {
    }

    /////////Procedure de generation de numero operation pacelle
    private void GenererNumOperation() {
        //Date d = new Date();
        Calendar calendar = Calendar.getInstance();
        int ann = calendar.get(Calendar.YEAR);//
        String an = String.valueOf(ann);
        String chainesuffixe = "";
        long numsuivant;

        String operaParcel = null;
        try {
            operaParcel = ejbFacade.executeMaxOperation(an);
            if (operaParcel == null) {
                setNewNumeroOpParcelle(an + "00000000001");
            } else {
                chainesuffixe = operaParcel.substring(4);
                System.out.println("chainesuffixe recuperer " + chainesuffixe);
                numsuivant = Long.valueOf(chainesuffixe) + 1;
                chainesuffixe = String.format("%011d", numsuivant);;
                setNewNumeroOpParcelle(an + chainesuffixe);
                System.out.println("Prochaine numero operation parcelle " + getNewNumeroOpParcelle());
            }
        } catch (Exception e) {
        }

    }

    private void enregistreIntervenirPreneur() {  /// Etape 1

        intervenirPreneur.setInvDateDeb(new Date());
        intervenirPreneur.setInvRolCode(roleOperation);
        intervenirPreneur.setInvIntNumero(intervenantPreneur);
        intervenirPreneur.setInvPbaNumero(parcelleBafonOperation);
        intervenirPreneur.setInvPrix(selected.getOpvPrix());
        intervenirPreneur.setInvDeQui(intervenantBailleur.getIntNom() + " " + intervenantBailleur.getIntPrenom());
        ////////////affectation des cas particuliers concernant les droits operationnel
        if (getTypeOperation().equals("OPERATIONNEL")) {
            intervenirPreneur.setInvDuree(selected.getOpvDuree());
            intervenirPreneur.setInvDateDeb(selected.getOpvDateDeb());
            intervenirPreneur.setInvDateFin(selected.getOpvDateFin());
            intervenirPreneur.setInvCopCode(selected.getOpvCopCode());
            intervenirPreneur.setInvAutremodalite(selected.getOpvAutremodalite());
            intervenirPreneur.setInvCopAutre(selected.getOpvCopAutre());
            intervenirPreneur.setInvContreparti(selected.getOpvContreparti());
            if (getLimitationchaineop().equals("FALSE")) {
                intervenirPreneur.setInvLimitationOp("0");
            } else {
                intervenirPreneur.setInvLimitationOp("1");
            }
            construitLimitDroitOperationnel();
        } else {
            intervenirPreneur.setInvDuree(null);
            //intervenirPreneur.setInvDateDeb(selected.getOpvDateDeb());
            intervenirPreneur.setInvDateFin(null);
            intervenirPreneur.setInvCopCode(null);
            intervenirPreneur.setInvAutremodalite(null);
            intervenirPreneur.setInvCopAutre(null);
            intervenirPreneur.setInvContreparti(null);
            if (getLimitationchaine().equals("FALSE")) {
                intervenirPreneur.setInvLimitation(false);
            } else {
                intervenirPreneur.setInvLimitation(true);
            }
            construitLimitDroitAdmin();
        }
        intervenirPreneur.setInvOpvNumero(selected);
        intervenirPreneur.setInvDesiCode(signaturePren);
        System.err.println(" Intervenir preneur " + intervenirPreneur.getInvNumero());
        try {
            TIntervenir inv;
            if(intervenirPreneur.getInvNumero()!=null){
            inv = ejbFacadeIntervenir.find(intervenirPreneur.getInvNumero());
            try {
                ejbFacadeIntervenir.edit(intervenirPreneur);
                System.out.println(" Intervenir preneur Trouver ");
            } catch (Exception e) {
                System.out.println("Probleme modification intervenir preneur " + e);
            }
            }else{
            System.out.println(" Intervenir preneur Non Trouver ");
            intervenirControleur.GenererNumIntervenir();
            intervenirPreneur.setInvNumero(intervenirControleur.getNewNumIntervenir());
            ejbFacadeIntervenir.create(intervenirPreneur);
            }
        } catch (Exception e) {
            //jai deplacee le code d'ici vers le else; 
        }
    }

    private void finIntervenirBailleur() {  //Etape 2
        intervenirbailleur.setInvOpvNumeroPreneur(selected);
        intervenirbailleur.setInvDateFin(new Date());
        ejbFacadeIntervenir.edit(intervenirbailleur);
    }

    private void finAncienDroitExerce(String categorieDroit) {
        List<TDroitExerce> listDroitExercefin;
        droitExerceControleur.executeListDroitExerceCat(parcelleBafonOperation, categorieDroit);
        listDroitExercefin = droitExerceControleur.getListeUneCategorie();
        if (listDroitExercefin != null) {
            for (TDroitExerce de : listDroitExercefin) {
                if (!selected.getOpvNumero().equals(de.getDreOpvNumero().getOpvNumero())) {
                    de.setDreDateFin(new Date());
                    de.setDreOpvNumeroPreneur(selected);
                    ejbFacadeDroitExerce.edit(de);
                }
            }
        }
    }

    private void creationDroitsExerce() { //Etape 3
        if (listDroitExerce != null) {
            for (TDroitExerce dex : listDroitExerce) {
                dex.setDreDateDebut(new Date());
                dex.setDreCat(dex.getDreTdeCode().getTdeCat());
                dex.setDreOpvNumero(selected);
                dex.setDreIntNumero(intervenantPreneur);
                try {
                    System.out.println("Droit Exercer     ---------- "+dex.getDreCode());
                    ejbFacadeDroitExerce.find(dex.getDreCode());
                    try {
                        ejbFacadeDroitExerce.edit(dex);
                    } catch (Exception e) {
                        System.out.println("Probleme modification intervenir preneur " + e);
                    }
                } catch (EntityNotFoundException e) {
                    ejbFacadeDroitExerce.create(dex);
                }
            }
        }
    }

    private void creationTemoins() {
        ////persistance des temoins
        for (TIntervenir inv : listInvTemBail) {
            
            try {
                ejbFacadeIntervenir.remove(inv);
            } catch (Exception e) {
            }
            
        }
        for (TIntervenir inv : listInvTemPren) {
             try {
                ejbFacadeIntervenir.remove(inv);
            } catch (Exception e) {
            }
        }

        TRole roleTemoinBail = new TRole();
        TRole roleTemoinPren = new TRole();
        roleTemoinBail = ejbFacaderole.find("R0019");
        roleTemoinPren = ejbFacaderole.find("R0018");
        //temoins bailleur
        for (TDepotSignature dep : listDepotSignTemBail) {
            
            TIntervenir intervenir = new TIntervenir();
            intervenirControleur.GenererNumIntervenir();
            intervenir.setInvNumero(intervenirControleur.getNewNumIntervenir());
            intervenir.setInvDesiCode(dep);
            intervenir.setInvDateDeb(new Date());
            intervenir.setInvOpvNumero(selected);
            intervenir.setInvPbaNumero(parcelleBafonOperation);
            intervenir.setInvRolCode(roleTemoinBail);
            intervenir.setInvIntNumero(dep.getDesiIntNumero());
            ejbFacadeIntervenir.create(intervenir);
            System.out.println("Creation temoins "+intervenir.getInvNumero());
        }
        //temoins preneur
        for (TDepotSignature dep : listDepotSignTemPren) {
            TIntervenir intervenir = new TIntervenir();
            intervenirControleur.GenererNumIntervenir();
            intervenir.setInvNumero(intervenirControleur.getNewNumIntervenir());
            intervenir.setInvDesiCode(dep);
            intervenir.setInvDateDeb(new Date());
            intervenir.setInvOpvNumero(selected);
            intervenir.setInvPbaNumero(parcelleBafonOperation);
            intervenir.setInvRolCode(roleTemoinPren);
            intervenir.setInvIntNumero(dep.getDesiIntNumero());
            ejbFacadeIntervenir.create(intervenir);
        }
        //Fin persistance des temoins
    }

    public void enregistrerVenteParcelle() {
        enregistrer(getTypeOperation());
    }

    public void enregistrer(String typeOperation) {
        //////////persistance operation

        selected.setOpvDateOp(new Date());
        selected.setOpvDateDeb(new Date());
        selected.setOpvDesiCode(signatureBail);
        selected.setOpvIntNumeroBailleur(intervenantBailleur);
        selected.setOpvIntNumeroPreneur(intervenantPreneur);
        selected.setOpvMacCode(modeAcquisition);
        selected.setOpvPbaNumero(parcelleBafonOperation);
        selected.setOpvRolCode(roleOperation);
        selected.setOpvStatut("SAISIE");
        selected.setOpvDateSaisie(new Date());
        try {
            try {
                TOperationParcel top;
                if(selected.getOpvNumero()!=null){                  
                top = ejbFacade.find(selected.getOpvNumero());
                try {
                    ejbFacade.edit(selected);
                } catch (Exception e) {
                    System.out.println("Probleme de modification de operation " + e);
                }
                }else{
                 GenererNumOperation();
                 selected.setOpvNumero(getNewNumeroOpParcelle());
                 ejbFacade.create(selected);
                }
                
            } catch (EntityNotFoundException e) {
                System.out.println("TOTOTOOT");
            }
        } catch (Exception e) {
            System.out.println(" Erreur enregistrement operation " + e);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Operation parcelle", "Echec enregistrement operation parcelle");
            FacesContext.getCurrentInstance().addMessage("errorInfo", facesMsg);
        }
        //////////fin persistance operation
        try {
            enregistreIntervenirPreneur();
        } catch (Exception e) {
            System.out.println(" Erreur enregistrement intervenir preneur" + e);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Operation parcelle", "Echec enregistrement du preneur/acheteur");
            FacesContext.getCurrentInstance().addMessage("errorInfo", facesMsg);
        }

        try {
            if (getTypeOperation().equals("VENTE") || getTypeOperation().equals("LEG") || getTypeOperation().equals("DON")) {
                finIntervenirBailleur();
            }
        } catch (Exception e) {
            System.out.println(" Erreur fin intervenir bailleur" + e);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Operation parcelle", "Echec fin ancien bailleur/vendeur");
            FacesContext.getCurrentInstance().addMessage("errorInfo", facesMsg);
        }

        try {
            finAncienDroitExerce("DE");
        } catch (Exception e) {
            System.out.println(" Erreur fin droits bailleur " + e);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Operation parcelle", "Echec fin ancien droits exerces");
            FacesContext.getCurrentInstance().addMessage("errorInfo", facesMsg);
        }

        try {
            creationDroitsExerce();
        } catch (Exception e) {
            System.out.println(" Erreur enregistrement droits acheteur " + e);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Operation parcelle", "Echec enregistrement des droits exerces");
            FacesContext.getCurrentInstance().addMessage("errorInfo", facesMsg);
        }

        try {
            creationTemoins();
        } catch (Exception e) {
            System.out.println(" Erreur creation des temoins " + e);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Operation parcelle", "Echec enregistrement des temoins");
            FacesContext.getCurrentInstance().addMessage("errorInfo", facesMsg);
        }

        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Enregistrement Operation parcelle", "Succès");
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public void supprimerOperation() {
        //////////////////////enlever fin bailleur
        try {
            TIntervenir invBail;
            invBail = ejbFacadeIntervenir.find(intervenirbailleur.getInvNumero());
            intervenirbailleur.setInvDateFin(null);
            intervenirbailleur.setInvOpvNumeroPreneur(null);
            ejbFacadeIntervenir.edit(intervenirbailleur);
        } catch (Exception e) {
        }
        //////////////////////enlever fin bailleur
        /////////suppression des anciens droits exerces
        for (TDroitExerce dre : listDroitExerce) {
            try {
                ejbFacadeDroitExerce.remove(dre);
            } catch (Exception e) {
                System.out.println(" Erreur de suppression de operation parcelle " + e);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Operation parcelle", "Echec de suppression des droits exerces");
                FacesContext.getCurrentInstance().addMessage("errorInfo", facesMsg);

            }
        }
        ////////fin suppression des anciens droits exerces

        ///////////////enlever fin anciens droits exerces
        //anciendreList
        List<TDroitExerce> listanciendre = new ArrayList<>();
        listanciendre = selected.getTDroitExerceList();
        for (TDroitExerce drean : listanciendre) {
            try {
                System.out.println("Ancien droits " + drean.getDreCode());
                drean.setDreOpvNumeroPreneur(null);
                drean.setDreDateFin(null);
                ejbFacadeDroitExerce.edit(drean);
            } catch (Exception e) {
                System.out.println(" Erreur de annulation fin anciens droits exerces " + e);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Operation parcelle", "Echec de annulation fin anciens droits exerces");
                FacesContext.getCurrentInstance().addMessage("errorInfo", facesMsg);
            }
        }
        ///////////////fin enlever fin anciens droits exerces

        ////////suppression des temoins
        for (TIntervenir inv : listInvTemBail) {
            try {
                ejbFacadeIntervenir.remove(inv);
            } catch (Exception e) {
                System.out.println(" Erreur de suppression de operation parcelle " + e);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Operation parcelle", "Echec de suppression temoin du Bailleur");
                FacesContext.getCurrentInstance().addMessage("errorInfo", facesMsg);

            }
        }
        for (TIntervenir inv : listInvTemPren) {
            try {
                ejbFacadeIntervenir.remove(inv);
            } catch (Exception e) {
                System.out.println(" Erreur de suppression de operation parcelle " + e);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Operation parcelle", "Echec de suppression temoin du preneur");
                FacesContext.getCurrentInstance().addMessage("errorInfo", facesMsg);

            }
        }
        ////////fin suppression des temoins

        ////////suppression intervenir preneur
        try {
            TIntervenir interv;
            interv = ejbFacadeIntervenir.find(intervenirPreneur.getInvNumero());
            ejbFacadeIntervenir.remove(intervenirPreneur);
        } catch (Exception e) {
            System.out.println(" Erreur de suppression de operation parcelle " + e);
            //  FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Operation parcelle", "Echec de suppression du preneur");
            //  FacesContext.getCurrentInstance().addMessage("errorInfo", facesMsg);

        }
        ////////fin suppression intervenir preneur

        ////////suppression operation meme
        try {
            ejbFacade.remove(selected);
            items.remove(selected);
        } catch (Exception e) {
            System.out.println(" Erreur de suppression de operation parcelle " + e);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Operation parcelle", "Echec de suppression de l'operation");
            FacesContext.getCurrentInstance().addMessage("errorInfo", facesMsg);
        }
        ////////fin suppression operation meme

        nouveau();

        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Suppression Operation parcelle", "Succès");
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public void nouveau() {
        if(selected!=null){
        modeAcquisition=selected.getOpvMacCode();
        }      
        selected = new TOperationParcel();
        selected.setOpvMacCode(modeAcquisition);
        intervenantBailleur = new TIntervenant();
        intervenantPreneur = new TIntervenant();
        parcelleBafonOperation = new TParcelleBafon();
        intervenirPreneur = new TIntervenir();
        intervenirbailleur = new TIntervenir();
        listDroitExerce = new ArrayList<>();
        listDepotSignTemBail = new ArrayList<>();
        listDepotSignTemPren = new ArrayList<>();
        listInvTemBail = new ArrayList<>();
        listInvTemPren = new ArrayList<>();
        listeLimiteDroitAdmin = null;
        listeLimiteDroitOperat = null;
        villagelib = null;
        arrondissementlib = null;
        communelib = null;
        departementlib = null;
        ///////info sur vendeur
        collectivite = null;
        nom = null;
        prenom = null;
        domicile = null;
        age = null;
        ///////info sur acheteur/preneur
        collectivitepreneur = null;
        nompreneur = null;
        prenompreneur = null;
        domicilepreneur = null;
        agepreneur = null;
        signaturePren = new TDepotSignature();
        signatureBail = new TDepotSignature();
        setEstvalider(false);
        setEstDon(false);
        setEstdroitoperationnel(false);
        setLimitationchaine("FALSE");
        setLimitationchaineop("FALSE");
    }
//        gfgf

    private void construitLimitDroitAdmin() {
        int n = 0;
        intervenirPreneur.setInvLimitVent(false);
        intervenirPreneur.setInvLimitDon(false);
        intervenirPreneur.setInvLimitLeg(false);
        intervenirPreneur.setInvLimitPret(false);
        if (listeLimiteDroitAdmin != null) {
            n = listeLimiteDroitAdmin.length;
            String droit;
            int j;
            for (i = 0; i < n; i++) {
                System.out.println(listeLimiteDroitAdmin[i]);
                droit = listeLimiteDroitAdmin[i];
                if (droit.equals("VENTE")) {
                    intervenirPreneur.setInvLimitVent(true);
                } else {
                    //intervenirPreneur.setInvLimitVent(false);
                }

                if (droit.equals("DON")) {
                    intervenirPreneur.setInvLimitDon(true);
                } else {
                    // intervenirPreneur.setInvLimitDon(false);
                }
                if (droit.equals("LEG")) {
                    intervenirPreneur.setInvLimitLeg(true);
                } else {
                    // intervenirPreneur.setInvLimitLeg(false);
                }
                if (droit.equals("PRET")) {
                    intervenirPreneur.setInvLimitPret(true);
                } else {
                    // intervenirPreneur.setInvLimitPret(false);
                }

            }
        }

    }

    private void construitLimitDroitOperationnel() {
        int n = 0;
        intervenirPreneur.setInvLimitAliener("0");
        intervenirPreneur.setInvLimitConstruireCase("0");
        intervenirPreneur.setInvLimitHabiterTerrain("0");
        intervenirPreneur.setInvLimitPlanter("0");
        intervenirPreneur.setInvLimitRecolterFruit("0");
        intervenirPreneur.setInvLimitTransmission("0");

        if (listeLimiteDroitOperat != null) {
            n = listeLimiteDroitOperat.length;
            String droit;
            int j;
            for (i = 0; i < n; i++) {
                System.out.println(listeLimiteDroitOperat[i]);
                droit = listeLimiteDroitOperat[i];
                if (droit.equals("ALIENER")) {
                    intervenirPreneur.setInvLimitAliener("1");
                } else {
                    //intervenirPreneur.setInvLimitVent(false);
                }

                if (droit.equals("CONSTRUIRE")) {
                    intervenirPreneur.setInvLimitConstruireCase("1");
                } else {
                    // intervenirPreneur.setInvLimitDon(false);
                }
                if (droit.equals("HABITER")) {
                    intervenirPreneur.setInvLimitHabiterTerrain("1");
                } else {
                    // intervenirPreneur.setInvLimitLeg(false);
                }
                if (droit.equals("PLANTER")) {
                    intervenirPreneur.setInvLimitPlanter("1");
                } else {
                    // intervenirPreneur.setInvLimitPret(false);
                }
                if (droit.equals("RECOLTER")) {
                    intervenirPreneur.setInvLimitRecolterFruit("1");
                } else {
                    // intervenirPreneur.setInvLimitPret(false);
                }

                if (droit.equals("TRANSMISSION")) {
                    intervenirPreneur.setInvLimitTransmission("1");
                } else {
                    // intervenirPreneur.setInvLimitPret(false);
                }

            }
        }

    }

    private void afficheLimitDroit(String typeDroit) {
        if (typeDroit.equals("ADMIN")) {
            afficheLimitDroitAdmin();
        }

        if (typeDroit.equals("OPERATIONNEL")) {
            afficheLimitDroitOperationnel();
        }

    }

    private void afficheLimitDroitAdmin() {
        listeLimiteDroitAdmin = new String[4];
        if (listeLimiteDroitAdmin != null) {
            try {
                if (intervenirPreneur.getInvLimitVent() == true) {
                    listeLimiteDroitAdmin[0] = "VENTE";
                } else {
                    listeLimiteDroitAdmin[0] = "";
                }

                if (intervenirPreneur.getInvLimitDon() == true) {
                    listeLimiteDroitAdmin[1] = "DON";
                } else {
                    listeLimiteDroitAdmin[1] = "";
                }
                if (intervenirPreneur.getInvLimitLeg() == true) {
                    listeLimiteDroitAdmin[2] = "LEG";
                } else {
                    listeLimiteDroitAdmin[2] = "";
                }
                if (intervenirPreneur.getInvLimitPret() == true) {
                    listeLimiteDroitAdmin[3] = "PRET";
                } else {
                    listeLimiteDroitAdmin[3] = "";
                }
            } catch (NullPointerException e) {
                System.out.println("intervenir preneur null " + e);
            }
        }
    }

    private void afficheLimitDroitOperationnel() {
        listeLimiteDroitOperat = new String[6];
        if (listeLimiteDroitOperat != null) {
            try {
                if (intervenirPreneur.getInvLimitAliener().equals("1")) {
                    listeLimiteDroitOperat[0] = "ALIENER";
                } else {
                    listeLimiteDroitOperat[0] = "";
                }

                if (intervenirPreneur.getInvLimitConstruireCase().equals("1")) {
                    listeLimiteDroitOperat[1] = "CONSTRUIRE";
                } else {
                    listeLimiteDroitOperat[1] = "";
                }
                if (intervenirPreneur.getInvLimitHabiterTerrain().equals("1")) {
                    listeLimiteDroitOperat[2] = "HABITER";
                } else {
                    listeLimiteDroitOperat[2] = "";
                }
                if (intervenirPreneur.getInvLimitPlanter().equals("1")) {
                    listeLimiteDroitOperat[3] = "PLANTER";
                } else {
                    listeLimiteDroitOperat[3] = "";
                }

                if (intervenirPreneur.getInvLimitRecolterFruit().equals("1")) {
                    listeLimiteDroitOperat[4] = "RECOLTER";
                } else {
                    listeLimiteDroitOperat[4] = "";
                }
                if (intervenirPreneur.getInvLimitTransmission().equals("1")) {
                    listeLimiteDroitOperat[5] = "TRANSMISSION";
                } else {
                    listeLimiteDroitOperat[5] = "";
                }
            } catch (NullPointerException e) {
                System.out.println("intervenir preneur null " + e);
            }
        }
    }

    public TOperationParcel getSelected() {
        return selected;
    }

    public void setSelected(TOperationParcel selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TOperationParcelFacade getFacade() {
        return ejbFacade;
    }

    public TOperationParcel prepareCreate() {
        selected = new TOperationParcel();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TOperationParcelCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TOperationParcelUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TOperationParcelDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TOperationParcel> getItems() {
//        if (items == null) {
//            //getFacade().
//            items = getFacade().findAll();
//        }
        return items;
    }

    /////////liste des operation selon le mode acquisition
    public void GenererItemsOperationMode(ArrayList<TModeacquis> modeacquits) {
        try {
            items = getFacade().executeListeOperationMode(modeacquits);
            System.out.println(" nombre operation " + items.size());
        } catch (Exception e) {

        }
    }

    /////////liste des operation selon la categorie de mode acquisition
    public void GenererItemsOperationCatMode(String catMode) {
        try {
            items = getFacade().executeListeOperationCatMode(catMode);
            System.out.println(" nombre operation " + items.size());
        } catch (Exception e) {

        }
    }

    private void InitCivilite(TIntervenant Interv) {
        if (Interv.getIntSexe().equals("MASCULIN")) {
            civilite = "M.";
            civilitesuffixe = "eur";
        }
        if (Interv.getIntSexe().equals("FEMININ")) {
            civilite = "Mme.";
            civilitesuffixe = "euse";
        }
    }

    private void initSuperficie(TParcelleBafon pba) {
        try {
            Parcelle p = ejbFacadeParcelle.find(pba.getPbaNumero());
            superficie = p.getHectares();
        } catch (Exception e) {
        }
        
    }

    private String parcelleLimitrophes(String pba,String pointcardino){
        String limites="";
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
        try {
           List<TParcellePoca> listparcellepoca =ejbFacadeParcellePoca.executeLimite(pba, pointcardino);
            for(TParcellePoca poca:listparcellepoca){              
               limites+= df.format(poca.getPapoLongLimitroph().doubleValue())+" mètres par "+poca.getPapoPbaNumeroLimit().getPbaNumero()+" ; " ;
            }
        } catch (Exception e) {
            System.out.println("Probeleme de selection de la limite "+e);
        }
         
       return limites;
    }
    /////////liste des operation selon la categorie de mode acquisition
    public void impressionOperationVente() throws JRException, ClassNotFoundException, SQLException, ParseException {
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/vues/etat/conventionVente.jasper");
        System.out.println("Chemin :" + reportPath);
        File file = new File(reportPath);
        String civbail = "";
        String civpren = "";
        
        String Article11, Article12, Article21, Article22, Article23, Article24,Article31,Article32,Article41,Article42,Article43 = "";
        String Article51,Article52="";
        String signatureVendeur,signatureAcheteur,temoinVendeur1="",temoinVendeur2="",temoinVendeur3="";
        String affirmation,textaffirmation,signatureAffirmation="";
        String dateSignature="";
        try {
            Connection connection = dataSource.getConnection();
            System.out.println("Connexion " + connection.getCatalog());
            HashMap map = new HashMap();
            InitCivilite(intervenantBailleur);
            civbail = civilite;
            civbailsufixe=civilitesuffixe;
            InitCivilite(intervenantPreneur);
            
            civpren = civilite;
            civprensufixe=civilitesuffixe;
            
            try {
                initSuperficie(selected.getOpvPbaNumero());
            } catch (Exception e) {
            }

            Article11 = civbail + " " + getCollectivite() + " " + getNom() + " " + getPrenom() + ", dénommé(e), \n";
            Article12 = "Vend" + civbailsufixe + " cède par la présente à " + civpren + " " + getNompreneur() + " " + getPrenompreneur() + " qui accepte, un immeuble "
                    + "d’une superficie de " + superficie + ", de forme : " + selected.getOpvPbaNumero().getPbaForme() + " sise à " + getVillagelib()
                    + "     Zone lotie       Zone non lotie     [X] Village PFR       Village non PFR  "
                    + "N° d'état des lieux, ……………, Lot …………………… Numéro de l'immeuble " + selected.getOpvPbaNumero().getPbaNumero()
                    + " Village / Quartier de ville " + getVillagelib() + " Arrondissement " + selected.getOpvPbaNumero().getPbaVilaCode().getVilaArrCode().getArrDesig();
            
            //////////////////recherche des limitrophes//////////////////////////////////////
            Article21=parcelleLimitrophes(selected.getOpvPbaNumero().getPbaNumero(),"NORD");
            Article21 = "- au Nord sur "+Article21+" \n";
            Article22=parcelleLimitrophes(selected.getOpvPbaNumero().getPbaNumero(),"SUD");
            Article22 = "- au Sud sur "+Article22+" \n";
            Article23=parcelleLimitrophes(selected.getOpvPbaNumero().getPbaNumero(),"EST");
            Article23 = "- à l’Est sur "+Article23+"\n";
            Article24=parcelleLimitrophes(selected.getOpvPbaNumero().getPbaNumero(),"OUEST");
            Article24 = "- à l’ouest sur "+Article24+"\n";
            //////////////////FIN recherche des limitrophes//////////////////////////////////////
            Article31="La présente vente est consentie moyennant la somme de : "+String.valueOf(selected.getOpvPrix())+" (en chiffres)";
            
            
           // System.out.println("EN lettre "+Convert.FR(String.valueOf(selected.getOpvPrix())," FCFA",""));
           
            
            if ( civbail.equals("M.")){
                nomme ="le nommé ";
                article="Le";
            }
            
            if ( civbail.equals("M.")){
                pronom =" il ";
            }
            
            Article32=Convert.FR(String.valueOf(selected.getOpvPrix())," FCFA","")+" (en lettres)" +
                      " que "+nomme + getCollectivite() + " " + getNom() + " " + getPrenom()+
                      " déclare avoir intégralement perçue et dont"+pronom+"donne ici, bonne, valable et définitive quittance à " +
                      civpren + " " + getNompreneur() + " " + getPrenompreneur();
            
            Article41="Le vendeur déclare être le présumé propriétaire de l'immeuble objet de la présente vente pour "
                    + "l’avoir acquis auprès de "+intervenirbailleur.getInvDeQui()
                    + " à titre onéreux ou par dévolution successorale.";
             Article42=getCommunelib();
             
             Article43=sdf.format(selected.getOpvDateDeb());
             dateSignature=sdf.format(selected.getOpvDateDeb());
             if (selected.getOpvDateValidation()!=null){
                 Article43=sdf.format(selected.getOpvDateValidation());
                 dateSignature=sdf.format(selected.getOpvDateValidation());
             }
             ///////////////////////////////
             Article51=article+ " VEND"+ civbailsufixe.toUpperCase() ;
              Article52="L'ACHET"+civprensufixe.toUpperCase() ;
             /////////Les signatures/////////////////////////////
             signatureVendeur= getCollectivite() + " " + getNom() + " " + getPrenom();
             signatureVendeur+="\n "+selected.getOpvDesiCode().getDesiReference();
             signatureVendeur+=" du "+ sdf.format(selected.getOpvDesiCode().getDesiDateDeb());
             signatureAcheteur=getNompreneur() + " " + getPrenompreneur();
             signatureAcheteur+="\n "+intervenirPreneur.getInvDesiCode().getDesiReference();
             signatureAcheteur+=" du "+ sdf.format(intervenirPreneur.getInvDesiCode().getDesiDateDeb());
             //////////////////////les temoins du vendeur/////////////////////////////
             int compt=0;
             for(TDepotSignature depoSign:listDepotSignTemBail){
                 compt++;
                  if(compt==1){
                      temoinVendeur1=depoSign.getDesiIntNumero().getIntNom()+" "+ depoSign.getDesiIntNumero().getIntPrenom();
                      temoinVendeur1+="\n "+depoSign.getDesiReference();
                      temoinVendeur1+=" du "+ sdf.format(depoSign.getDesiDateDeb());
                  }   
                  
                  if(compt==2){
                      temoinVendeur2=depoSign.getDesiIntNumero().getIntNom()+" "+ depoSign.getDesiIntNumero().getIntPrenom();
                      temoinVendeur2+="\n "+depoSign.getDesiReference();
                      temoinVendeur2+=" du "+ sdf.format(depoSign.getDesiDateDeb());
                  }   
                  
                  if(compt==3){
                      temoinVendeur3=depoSign.getDesiIntNumero().getIntNom()+" "+ depoSign.getDesiIntNumero().getIntPrenom();
                      temoinVendeur3+="\n "+depoSign.getDesiReference();
                      temoinVendeur3+=" du "+ sdf.format(depoSign.getDesiDateDeb());
                  }   
             }
             
             affirmation="AFFIRMATION N° "+selected.getOpvNumeroAffirmation();
             
             textaffirmation="Devant nous "+selected.getOpvSignCode().getSignNom()+" "+selected.getOpvSignCode().getSignPrenom()+" "+selected.getOpvSignCode().getSignFonction()
                     +" de la Commune de "+getCommunelib()
                     + ", se sont présentés, le présumé propriétaire "+ getCollectivite() + " " + getNom() + " " + getPrenom()
                     + ", né le / vers ……………………………….……… à "+intervenantBailleur.getIntLieuNaiss()+", sexe "+intervenantBailleur.getIntSexe()
                     + ", profession "+intervenantBailleur.getIntActCode().getActDesig()+", lieu de résidence "+intervenantBailleur.getIntDomicile()
                     + " et ses trois (03) témoins dénommés à la présente convention.\n"
                     + "Lesquels, après lecture à eux faite et traduction au besoin en leur propre langue de la "
                     + "teneur de l’acte, en notre présence, "
                     + "par le nommé "+selected.getOpvNomInterprete() 
                     + ", interprète assermenté, ont formellement déclaré et affirmé en comprendre le sens.\n"
                     +"Le présumé propriétaire a en outre déclaré et affirmé en accepter les termes et s’oblige"
                     + " à les exécuter loyalement.\n"
                     +"Les témoins ont également déclaré et affirmé en reconnaître la parfaite régularité.\n"
                     +"Ce que nous certifions à toutes fins de droit.\n\n";
                     signatureAffirmation="Fait à "+getCommunelib()+", le "+dateSignature+"\n\n"
                     +"Le Maire, \n\n\n\n"+selected.getOpvSignCode().getSignNom()+" "+selected.getOpvSignCode().getSignPrenom();
             
            //////Initialisation des parametres///////////////
            map.put("ParamDepartement", "DEPARTEMENT DU " + getDepartementlib());
            map.put("ParamCommune", "COMMUNE DE " + getCommunelib());
            map.put("ParamOpvNum", selected.getOpvNumero());
            map.put("ParamNomPrenomVendeur", civbail + " " + getCollectivite() + " " + getNom() + " " + getPrenom());
            map.put("ParamNomPrenomAcheteur", civpren + " " + getNompreneur() + " " + getPrenompreneur());
            map.put("ParamArticle11", Article11);
            map.put("ParamArticle12", Article12);
            map.put("ParamArticle21", Article21);
            map.put("ParamArticle22", Article22);
            map.put("ParamArticle23", Article23);
            map.put("ParamArticle24", Article24);
            /////////////////////////////////////
            map.put("ParamArticle31", Article31);
            map.put("ParamArticle32", Article32);
            map.put("ParamArticle41", Article41);
            /////////////commune et date etablissement//////////
            map.put("ParamArticle42", Article42);
            map.put("ParamArticle43", Article43);
           
            /////////bloc signatqire//////////////////////////
            map.put("ParamArticle51", Article51);
            map.put("ParamArticle52", Article52);
            map.put("signatureVendeur", signatureVendeur);
            map.put("signatureAcheteur", signatureAcheteur);
            map.put("temoinVendeur1", temoinVendeur1);
            map.put("temoinVendeur2", temoinVendeur2);
            map.put("temoinVendeur3", temoinVendeur3);
            ///////////affirmation//////////////////////
            map.put("affirmation", affirmation);   
            map.put("textaffirmation", textaffirmation);  
            map.put("signatureAffirmation", signatureAffirmation);  
//            parcelleLimitrophes();

            jasperPrint = JasperFillManager.fillReport(file.getPath(), map, connection);
            //jasperPrint.setProperty("Var_NomPrenomVendeur", "AKAKPO Aurince");
            System.out.println("Taille liste classe --> " + jasperPrint.getPages().size());
            try {
                HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=ConventionVente.pdf");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                FacesContext.getCurrentInstance().responseComplete();
                System.out.println("servletOutputStream> " + servletOutputStream);
            } catch (Exception e) {

            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private String droitArbre(TIntervenir inter){
        String droitarb="";
        if (inter.getInvLimitPlanter().equals("0")){
            droitarb="Droit de planter ; ";
        }else{
            droitarb="Interdiction de planter ; ";
        }
        
        if (inter.getInvLimitRecolterFruit().equals("0")){
            droitarb+="Droit de recolter les fruits  ";
        }else{
            droitarb+="Interdiction de recolter les fruits  ";
        }
        return droitarb;
    }
    
    private String amenagementAccordes(TIntervenir inter){
        String droitamena="";
        if (inter.getInvLimitConstruireCase().equals("0")){
            droitamena="Construction ; ";
        }else{
            droitamena="";
        }
        
        if (inter.getInvLimitHabiterTerrain().equals("0")){
            droitamena+=" habitation ";
        }else{
            droitamena+=" ";
        }  
        return droitamena;
    }
    public void impressionOperationAffermage() throws JRException, ClassNotFoundException, SQLException, ParseException {
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/vues/etat/affermage.jasper");
        File file = new File(reportPath);
        String civbail = "";
        String civpren = "";
        
        String Bailleur, Preneur, Immeuble, Condition, Obligations, Resolution,Litige,temoinAcheteur2,temoinAcheteur1,Article42,Article43 = "";
        String Article51,Article52="";
        String signatureVendeur,signatureAcheteur,temoinVendeur1="",temoinVendeur2="",temoinVendeur3="";
        String affirmation,textaffirmation,signatureAffirmation="";
        String dateSignature="";
        try {
            Connection connection = dataSource.getConnection();
            System.out.println("Connexion " + connection.getCatalog());
            HashMap map = new HashMap();
            InitCivilite(intervenantBailleur);
            civbail = civilite;
            civbailsufixe=civilitesuffixe;
            InitCivilite(intervenantPreneur);
            civpren = civilite;
            civprensufixe=civilitesuffixe;
            
//            try {
//                initSuperficie(selected.getOpvPbaNumero());
//            } catch (Exception e) {
//            }
            
            
           // Article11 = civbail + " " + getCollectivite() + " " + getNom() + " " + getPrenom() + ", dénommé(e), \n";
                   
            Bailleur="Nom : "+ getCollectivite() + " " + getNom() +"\n" +
                     "Prénoms :	"+ getPrenom()+"	\n" +
                     "Sexe : "+intervenantBailleur.getIntSexe()+"\n" +
                     "Référence pièce d'identité : "+((intervenirbailleur.getInvCiPp()==null)? "":intervenirbailleur.getInvCiPp())+"\n" +
                     "Profession : "+((intervenantBailleur.getIntActCode()==null)? "":intervenantBailleur.getIntActCode().getActDesig())+"\n" +
                     "Village/Quartier d’origine :"+((intervenantBailleur.getIntVilaCode()==null)? "":intervenantBailleur.getIntVilaCode().getVlaDesig())+"\n" +
                     "Arrondissement : "+((intervenantBailleur.getIntVilaCode()==null)? "":intervenantBailleur.getIntVilaCode().getVilaArrCode().getArrDesig())+"\n" +
                     "Commune. : "+((intervenantBailleur.getIntVilaCode()==null)? "":intervenantBailleur.getIntVilaCode().getVilaArrCode().getArrComCode().getComDesig())+"\n" +
                     "Qualité   : "+((intervenirbailleur.getInvRolCode()==null)? "":intervenirbailleur.getInvRolCode().getRolDesig())+"\n" +
                     "Lieu de résidence :"+((intervenantBailleur.getIntDomicile()==null)? "":intervenantBailleur.getIntDomicile())+"\n" +
                     "Téléphone : "+((intervenantBailleur.getIntTelephone()==null)? "":intervenantBailleur.getIntTelephone());
            
            Preneur="Nom : "+ getNompreneur()+"\n" +
                     "Prénoms :	"+ getPrenompreneur()+"\n" +
                     "Sexe : "+intervenantPreneur.getIntSexe()+"\n" +
                     "Référence pièce d'identité : "+((intervenirPreneur.getInvCiPp()==null)? "":intervenirPreneur.getInvCiPp())+"\n" +
                     "Profession : "+((intervenantPreneur.getIntActCode()==null)? "":intervenantPreneur.getIntActCode().getActDesig())+"\n" +
                     "Village/Quartier d’origine : "+((intervenantPreneur.getIntVilaCode()==null)? "":intervenantPreneur.getIntVilaCode().getVlaDesig())+"\n" +
                     "Arrondissement :"+((intervenantPreneur.getIntVilaCode()==null)? "":intervenantPreneur.getIntVilaCode().getVilaArrCode().getArrDesig())+"\n" +
                     "Commune. :"+((intervenantPreneur.getIntVilaCode()==null)? "":intervenantPreneur.getIntVilaCode().getVilaArrCode().getArrComCode().getComDesig())+"\n" +
                     "Agissant au nom et pour le compte de :"+((intervenirPreneur.getInvObservation()==null)? "":intervenirPreneur.getInvObservation())+"\n" +
                     "Lieu de résidence : "+((intervenantPreneur.getIntDomicile()==null)? "":intervenantPreneur.getIntDomicile())+"\n" +
                     "Téléphone : "+((intervenantPreneur.getIntTelephone()==null)? "":intervenantPreneur.getIntTelephone());
            Immeuble="Il a été convenu et arrêté entre les soussignés ci-dessus ce qui suit : \n" +
                    "Le détenteur loue au locataire qui accepte un immeuble ayant les caractéristiques ci-après :\n" +
                    "Commune : "+getCommunelib()+" Arrondissement : "+getArrondissementlib()+" \n" +
                    "Village/quartier de ville : "+getVillagelib()+" hameau/lieu dit : "+((selected.getOpvPbaNumero()==null)? "":selected.getOpvPbaNumero().getPbaLieuDit()) +"\n" +
                    "Forme : "+((selected.getOpvPbaNumero().getPbaForme()==null)? "":selected.getOpvPbaNumero().getPbaForme())+" Superficie : " +((superficie==null)? "":superficie)  + "\n" +
                    "En cas de Plan foncier rural (PFR) du village : numéro de l'immeuble "+((selected.getOpvPbaNumero()==null)? "":selected.getOpvPbaNumero().getPbaNumero())+"\n" +
                    "En cas de lotissement, de RFU ou d’adresse de collecte des ordures : numéro de l'immeuble ";
            String duree=selected.getOpvDuree().toString();
            String d1=sdf.format(selected.getOpvDateDeb());
            String d2=sdf.format(selected.getOpvDateFin());
            Condition="3.1 - Durée du contrat : "+duree+" pour compter de : "+d1+" fin de la location : "+d2+"\n" +
                    "3.2 - Montant de la location : "+selected.getOpvPrix()+"\n" +
                    " par (spécifier la période) : "+selected.getOpvCopCode().getCopDesig()+"\n" +
                    "3.3 - Modalités de paiement : "+selected.getOpvAutremodalite()+"\n" +
                    "3.4 - Droits sur les arbres : "+droitArbre(intervenirPreneur)+"\n" +
                    "3.5 - Aménagements autorisés : "+amenagementAccordes(intervenirPreneur)+"\n" +
                    "3.6 - Autres conditions : "+selected.getOpvCopAutre()+" \n" ;
            
            Obligations="4.1 - Le preneur s’engage à :\n" +
                        "- respecter les conditions de la location ;\n" +
                        "- ne pas installer un tiers sur l'immeuble sans l’accord écrit du détenteur ;\n" +
                        "- ne pas dépasser la superficie louée sans l’accord écrit du détenteur constaté par un \n avenant ;\n" +
                        "- restituer l'immeuble loué à l’expiration du délai contractuel ou solliciter une prorogation par \n écrit ;\n" +
                        "- en cas de décès du détenteur pendant la durée contractuelle, reconnaître à ses héritiers \n les droits acquis par le de cujus aux termes du présent contrat pour la période restant à courir.\n" +
                        "\n" +
                        "4.2 - Le bailleur s’engage à :\n" +
                        "- respecter rigoureusement les clauses du contrat, notamment celles relatives à la durée et \n aux droits accordés ;\n" +
                        "- ne pas créer des troubles de jouissance au preneur ;\n" +
                        "- ne pas empêcher le preneur de tester ou d’utiliser des mesures d’amélioration de la \n productivité agricole sur la parcelle louée ;\n" +
                        "- en cas de décès du preneur pendant la durée contractuelle, reconnaître à ses héritiers les \n droits acquis par le de cujus  aux termes du présent contrat pour la période restant à courir.";
             
            Resolution="Le contrat ne peut prendre fin qu’à la date prévue. Toutefois, le non respect par le preneur de l’une \n"
                    + "quelconque de ses obligations peut entraîner la rupture du contrat par le détenteur après un préavis \n"
                    + "écrit de .......... mois resté infructueux, constaté par la Section Villageoise de Gestion Foncière (SVGF)\n"
                    + "ou le chef quartier le cas échéant.\n" +
                    "Si le preneur décide avant l’échéance de résilier le contrat pour des raisons de convenance \n"
                    + "personnelle, il ne pourrait prétendre à la récupération des loyers versés, ni à des dommages-intérêts, \n"
                    + "sauf clause contraire expressément définie à l’avance.\n";

            Litige="Pour tous litiges pouvant naître de l’interprétation ou de l’exécution du présent contrat, le bailleur et le \n"
                    + "preneur déclarent privilégier une tentative de règlement amiable par la médiation d’une instance locale \n"
                    + "ou d’une personne choisie d’un commun accord ou une conciliation par le tribunal de conciliation situé \n"
                    + "dans la même commune que la parcelle. \n" +
                       "\n" +
                    "Dans tous les cas, le recours au témoignage ou à  la Section villageoise de gestion foncière (SVGF) ou, \n"
                    + "à défaut, aux anciens du village/quartier est obligatoire.\n\n";         
            if ( civbail.equals("M.")){
                nomme ="le nommé ";
                article="Le";
            }
            
            if ( civbail.equals("M.")){
                pronom =" il ";
            }
                Article42=getCommunelib();
//             
                Article43=sdf.format(selected.getOpvDateDeb());
                dateSignature=sdf.format(selected.getOpvDateDeb());
             if (selected.getOpvDateValidation()!=null){
                 Article43=sdf.format(selected.getOpvDateValidation());
                 dateSignature=sdf.format(selected.getOpvDateValidation());
             }
//             ///////////////////////////////
//             /////////Les signatures/////////////////////////////
             signatureVendeur= getCollectivite() + " " + getNom() + " " + getPrenom();
             signatureVendeur+="\n\n "+selected.getOpvDesiCode().getDesiReference();
             signatureVendeur+=" du "+ sdf.format(selected.getOpvDesiCode().getDesiDateDeb());
             
             signatureAcheteur=getNompreneur() + " " + getPrenompreneur();
             signatureAcheteur+="\n\n "+intervenirPreneur.getInvDesiCode().getDesiReference();
             signatureAcheteur+=" du "+ sdf.format(intervenirPreneur.getInvDesiCode().getDesiDateDeb());
             
             
             
             //////////////////////les temoins du vendeur/////////////////////////////
             int compt=0;
             for(TDepotSignature depoSign:listDepotSignTemBail){
                 compt++;
                  if(compt==1){
                      temoinVendeur1=depoSign.getDesiIntNumero().getIntNom()+" "+ depoSign.getDesiIntNumero().getIntPrenom();
                      temoinVendeur1+="\n\n "+depoSign.getDesiReference();
                      temoinVendeur1+=" du "+ sdf.format(depoSign.getDesiDateDeb());
                  }   
                  
                  if(compt==2){
                      temoinVendeur2=depoSign.getDesiIntNumero().getIntNom()+" "+ depoSign.getDesiIntNumero().getIntPrenom();
                      temoinVendeur2+="\n\n "+depoSign.getDesiReference();
                      temoinVendeur2+=" du "+ sdf.format(depoSign.getDesiDateDeb());
                  }   
                    
             }
             temoinAcheteur1="";
             temoinAcheteur2="";
              compt=0;
             for(TDepotSignature depoSign:listDepotSignTemPren){
                 compt++;
                  if(compt==1){
                      temoinAcheteur1=depoSign.getDesiIntNumero().getIntNom()+" "+ depoSign.getDesiIntNumero().getIntPrenom();
                      temoinAcheteur1+="\n\n "+depoSign.getDesiReference();
                      temoinAcheteur1+=" du "+ sdf.format(depoSign.getDesiDateDeb());
                  }   
                  
                  if(compt==2){
                      temoinAcheteur2=depoSign.getDesiIntNumero().getIntNom()+" "+ depoSign.getDesiIntNumero().getIntPrenom();
                      temoinAcheteur2+="\n\n "+depoSign.getDesiReference();
                      temoinAcheteur2+=" du "+ sdf.format(depoSign.getDesiDateDeb());
                  }   
                     
             }
           
             affirmation="Enregistré sous le numéro : "+selected.getOpvNumeroAffirmation();
             affirmation+="\n Le Président de la SVGF/Chef quartier de ville";
           
            //////Initialisation des parametres///////////////
            map.put("ParamOpvNum", selected.getOpvNumero());
            map.put("Village",getVillagelib());
            map.put("Commune",getCommunelib());
            map.put("Arrondissement",getArrondissementlib());
            map.put("Bailleur",Bailleur);
            map.put("Preneur",Preneur);
            map.put("Immeuble",Immeuble);
            map.put("Condition",Condition);
            map.put("Obligations",Obligations);
            map.put("Litige",Litige);
            map.put("Resolution",Resolution);

            /////////////commune et date etablissement//////////
            map.put("ParamArticle42", Article42);
            map.put("ParamArticle43", Article43);
//           
            /////////bloc signatqire//////////////////////////
            map.put("signatureVendeur", signatureVendeur);
            map.put("signatureAcheteur", signatureAcheteur);
            map.put("temoinVendeur1", temoinVendeur1);
            map.put("temoinVendeur2", temoinVendeur2);
            map.put("temoinAcheteur1", temoinAcheteur1);
            map.put("temoinAcheteur2", temoinAcheteur2);
//            ///////////affirmation//////////////////////
            map.put("signatureAffirmation", affirmation);   

            jasperPrint = JasperFillManager.fillReport(file.getPath(), map, connection);
            System.out.println("Taille liste classe --> " + jasperPrint.getPages().size());
            try {
                HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=Affermage.pdf");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                FacesContext.getCurrentInstance().responseComplete();
                System.out.println("servletOutputStream> " + servletOutputStream);
            } catch (Exception e) {

            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void impressionOperationMetayage() throws JRException, ClassNotFoundException, SQLException, ParseException {
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/vues/etat/metayage.jasper");
        File file = new File(reportPath);
        String civbail = "";
        String civpren = "";
        
        String Bailleur, Preneur, Immeuble, Condition, Obligations, Resolution,Litige,temoinAcheteur2,temoinAcheteur1,Article42,Article43 = "";
        String Article51,Article52="";
        String signatureVendeur,signatureAcheteur,temoinVendeur1="",temoinVendeur2="",temoinVendeur3="";
        String affirmation,textaffirmation,signatureAffirmation="";
        String dateSignature="";
        try {
            Connection connection = dataSource.getConnection();
            System.out.println("Connexion " + connection.getCatalog());
            HashMap map = new HashMap();
            InitCivilite(intervenantBailleur);
            civbail = civilite;
            civbailsufixe=civilitesuffixe;
            InitCivilite(intervenantPreneur);
            civpren = civilite;
            civprensufixe=civilitesuffixe;
              
            Bailleur="Nom : "+ getCollectivite() + " " + getNom() +"\n" +
                     "Prénoms :	"+ getPrenom()+"	\n" +
                     "Sexe : "+intervenantBailleur.getIntSexe()+"\n" +
                     "Référence pièce d'identité : "+((intervenirbailleur.getInvCiPp()==null)? "":intervenirbailleur.getInvCiPp())+"\n" +
                     "Profession : "+((intervenantBailleur.getIntActCode()==null)? "":intervenantBailleur.getIntActCode().getActDesig())+"\n" +
                     "Village/Quartier d’origine :"+((intervenantBailleur.getIntVilaCode()==null)? "":intervenantBailleur.getIntVilaCode().getVlaDesig())+"\n" +
                     "Arrondissement : "+((intervenantBailleur.getIntVilaCode()==null)? "":intervenantBailleur.getIntVilaCode().getVilaArrCode().getArrDesig())+"\n" +
                     "Commune. : "+((intervenantBailleur.getIntVilaCode()==null)? "":intervenantBailleur.getIntVilaCode().getVilaArrCode().getArrComCode().getComDesig())+"\n" +
                     "Qualité   : "+((intervenirbailleur.getInvRolCode()==null)? "":intervenirbailleur.getInvRolCode().getRolDesig())+"\n" +
                     "Lieu de résidence :"+((intervenantBailleur.getIntDomicile()==null)? "":intervenantBailleur.getIntDomicile())+"\n" +
                     "Téléphone : "+((intervenantBailleur.getIntTelephone()==null)? "":intervenantBailleur.getIntTelephone());
            
            Preneur="Nom : "+ getNompreneur()+"\n" +
                     "Prénoms :	"+ getPrenompreneur()+"\n" +
                     "Sexe : "+intervenantPreneur.getIntSexe()+"\n" +
                     "Référence pièce d'identité : "+((intervenirPreneur.getInvCiPp()==null)? "":intervenirPreneur.getInvCiPp())+"\n" +
                     "Profession : "+((intervenantPreneur.getIntActCode()==null)? "":intervenantPreneur.getIntActCode().getActDesig())+"\n" +
                     "Village/Quartier d’origine : "+((intervenantPreneur.getIntVilaCode()==null)? "":intervenantPreneur.getIntVilaCode().getVlaDesig())+"\n" +
                     "Arrondissement :"+((intervenantPreneur.getIntVilaCode()==null)? "":intervenantPreneur.getIntVilaCode().getVilaArrCode().getArrDesig())+"\n" +
                     "Commune. :"+((intervenantPreneur.getIntVilaCode()==null)? "":intervenantPreneur.getIntVilaCode().getVilaArrCode().getArrComCode().getComDesig())+"\n" +
                     "Agissant au nom et pour le compte de :"+((intervenirPreneur.getInvObservation()==null)? "":intervenirPreneur.getInvObservation())+"\n" +
                     "Lieu de résidence : "+((intervenantPreneur.getIntDomicile()==null)? "":intervenantPreneur.getIntDomicile())+"\n" +
                     "Téléphone : "+((intervenantPreneur.getIntTelephone()==null)? "":intervenantPreneur.getIntTelephone());
            
            Immeuble="Le Bailleur met à la disposition du preneur qui accepte un immeuble ayant les caracteristiques ci-après : \n"+      
                    "Situation de la parcelle : "+((selected.getOpvPbaNumero()==null)? "":selected.getOpvPbaNumero().getPbaLieuDit()) +"\n" +
                    "Forme : "+((selected.getOpvPbaNumero().getPbaForme()==null)? "":selected.getOpvPbaNumero().getPbaForme())+" Superficie : " +((superficie==null)? "":superficie)  + "\n" +
                    "Enregistré au plan de terroir de :.......................\n"+
                    "Arrondissement : "+getArrondissementlib()+" Commune : "+getCommunelib()+ " \n" +
                //    "Village/quartier de ville : "+getVillagelib()+" hameau/lieu dit : "+((selected.getOpvPbaNumero()==null)? "":selected.getOpvPbaNumero().getPbaLieuDit()) +"\n" +
                    "Sous le numéro : "+((selected.getOpvPbaNumero()==null)? "":selected.getOpvPbaNumero().getPbaNumero())+"\n";
               //     "En cas de lotissement, de RFU ou d’adresse de collecte des ordures : numéro de l'immeuble ";
            
            String duree=selected.getOpvDuree().toString();
            String d1=sdf.format(selected.getOpvDateDeb());
            String d2=sdf.format(selected.getOpvDateFin());
            Condition="3.1 - Durée du contrat : "+duree+"\n"
                    + "      - pour compter de : "+d1+"\n"
                    + "      - fin du métayage : "+d2+"\n" +
                    "3.2 - Nature de la culture: "+selected.getOpvNatureCulture()+"\n" +
                   // " Contrepartie : "+selected.getOpvContreparti()+"\n" +
                    "3.3 - Contrepartie : "+selected.getOpvContreparti()+"\n" +
                    "3.4 - Mode de partage des produits : "+selected.getOpvMopCode().getMopLib()+"\n" +
                    "3.5 - Lieu de partage : "+selected.getOpvLieuPartage()+"\n" +
                    "3.6 - Aménagements autorisés : "+amenagementAccordes(intervenirPreneur)+"\n" +
                    "3.7 - Autres conditions : "+selected.getOpvCopAutre()+" \n" ;
            
            Obligations="4.1 - Le métayer s’engage à :\n" +
                        "    - respecter les conditions du contrat ;\n" +
                        "    - ne pas installer un tiers sur la parcelle sans l’accord écrit du propriétaire ;\n" +
                        "    - ne pas dépasser la superficie louée sans l’accord du propriétaire constaté par un avenant ;\n" +
                        "    - restituer la parcelle occupée à l’expiration du délai contractuel ou solliciter une prorogation.\n" +
                        "\n" +
                        "4.2 - Le cédant s’engage à :\n" +
                        "    - respecter rigoureusement les clauses du contrat, notamment celles relatives à la durée\net aux droits accordés ;\n" +
                        "    - ne pas créer des troubles de jouissance au métayer ;\n" +
                        "    - ne pas empêcher le métayer de tester ou d’utiliser des mesures d’amélioration de la fertilité\ndu sol ;\n" +
                        "    - en cas de décès du métayer pendant la durée contractuelle, reconnaître à ses héritiers\nles droits \n"
                    + "acquis par leur père aux termes du présent contrat pour la\npériode restant à courir.";
                    
            Resolution="Le contrat ne peut être résilié qu’à la fin du bail. Toutefois, le non respect par le métayer\nde l’une quelconque "
                    + "de ses obligations peut entraîner la rupture du contrat après un préavis\nécrit.......mois resté infructueux.\n" +
                  
                    "Si le métayer décide avant l’échéance de résilier le contrat pour des raisons de convenance\npersonnelle, il ne "
                    + "pourrait prétendre à la récupération des loyers versés, ni à des dommages-\nintérêts, sauf clause contraire "
                    + "expressément définie à l’avance.";

            Litige="Pour tous litiges pouvant naître de l’interprétation ou de l’exécution du présent contrat,\nle cédant et le métayer "
                    + "déclarent privilégier le règlement à l’amiable par la médiation du Comité\nVillageois de Gestion du Plan Foncier "
                    + "Rural ou du chef de village.\n" +
                
                    "Dans tous les cas, le recours au témoignage ou à l’éclairage de la Commission Foncière du\nVillage est "
                    + "obligatoire.\n\n";
   
            if ( civbail.equals("M.")){
                nomme ="le nommé ";
                article="Le";
            }
            
            if ( civbail.equals("M.")){
                pronom =" il ";
            }
                Article42=getCommunelib();
//             
                Article43=sdf.format(selected.getOpvDateDeb());
                dateSignature=sdf.format(selected.getOpvDateDeb());
             if (selected.getOpvDateValidation()!=null){
                 Article43=sdf.format(selected.getOpvDateValidation());
                 dateSignature=sdf.format(selected.getOpvDateValidation());
             }
//             /////////Les signatures/////////////////////////////
             signatureVendeur= getCollectivite() + " " + getNom() + " " + getPrenom();
             signatureVendeur+="\n\n "+selected.getOpvDesiCode().getDesiReference();
             signatureVendeur+=" du "+ sdf.format(selected.getOpvDesiCode().getDesiDateDeb());
             
             signatureAcheteur=getNompreneur() + " " + getPrenompreneur();
             signatureAcheteur+="\n\n "+intervenirPreneur.getInvDesiCode().getDesiReference();
             signatureAcheteur+=" du "+ sdf.format(intervenirPreneur.getInvDesiCode().getDesiDateDeb());
             
             
             
             //////////////////////les temoins du vendeur/////////////////////////////
             int compt=0;
             for(TDepotSignature depoSign:listDepotSignTemBail){
                 compt++;
                  if(compt==1){
                      temoinVendeur1=depoSign.getDesiIntNumero().getIntNom()+" "+ depoSign.getDesiIntNumero().getIntPrenom();
                      temoinVendeur1+="\n\n "+depoSign.getDesiReference();
                      temoinVendeur1+=" du "+ sdf.format(depoSign.getDesiDateDeb());
                  }   
                  
                  if(compt==2){
                      temoinVendeur2=depoSign.getDesiIntNumero().getIntNom()+" "+ depoSign.getDesiIntNumero().getIntPrenom();
                      temoinVendeur2+="\n\n "+depoSign.getDesiReference();
                      temoinVendeur2+=" du "+ sdf.format(depoSign.getDesiDateDeb());
                  }     
             }
             temoinAcheteur1="";
             temoinAcheteur2="";
              compt=0;
             for(TDepotSignature depoSign:listDepotSignTemPren){
                 compt++;
                  if(compt==1){
                      temoinAcheteur1=depoSign.getDesiIntNumero().getIntNom()+" "+ depoSign.getDesiIntNumero().getIntPrenom();
                      temoinAcheteur1+="\n\n "+depoSign.getDesiReference();
                      temoinAcheteur1+=" du "+ sdf.format(depoSign.getDesiDateDeb());
                  }   
                  
                  if(compt==2){
                      temoinAcheteur2=depoSign.getDesiIntNumero().getIntNom()+" "+ depoSign.getDesiIntNumero().getIntPrenom();
                      temoinAcheteur2+="\n\n "+depoSign.getDesiReference();
                      temoinAcheteur2+=" du "+ sdf.format(depoSign.getDesiDateDeb());
                  }     
             }
           
             affirmation="Enregistré sous le numéro : "+selected.getOpvNumeroAffirmation();
             affirmation+="\n Le Président de la SVGF/Chef quartier de ville";           
            //////Initialisation des parametres///////////////
            map.put("ParamOpvNum", selected.getOpvNumero());
            map.put("Village",getVillagelib());
            map.put("Commune",getCommunelib());
            map.put("Arrondissement",getArrondissementlib());
            map.put("Bailleur",Bailleur);
            map.put("Preneur",Preneur);
            map.put("Immeuble",Immeuble);
            map.put("Condition",Condition);
            map.put("Obligations",Obligations);
            map.put("Litige",Litige);
            map.put("Resolution",Resolution);
            /////////////commune et date etablissement//////////
            map.put("ParamArticle42", Article42);
            map.put("ParamArticle43", Article43);
//           
            /////////bloc signatqire//////////////////////////
            map.put("signatureVendeur", signatureVendeur);
            map.put("signatureAcheteur", signatureAcheteur);
            map.put("temoinVendeur1", temoinVendeur1);
            map.put("temoinVendeur2", temoinVendeur2);
            map.put("temoinAcheteur1", temoinAcheteur1);
            map.put("temoinAcheteur2", temoinAcheteur2);
//            ///////////affirmation//////////////////////
            map.put("signatureAffirmation", affirmation);   

            jasperPrint = JasperFillManager.fillReport(file.getPath(), map, connection);
            System.out.println("Taille liste classe --> " + jasperPrint.getPages().size());
            try {
                HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=metayage.pdf");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                FacesContext.getCurrentInstance().responseComplete();
                System.out.println("servletOutputStream> " + servletOutputStream);
            } catch (Exception e) {

            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void imprimeDoc() throws JRException, IOException, ClassNotFoundException, SQLException, ParseException {
        if (typeOperation.equals("VENTE")) {
            impressionOperationVente();
        }
         if (typeOperation.equals("OPERATIONNEL")) {
             if(selected.getOpvMacCode().getMacCode().equals("MD013")||selected.getOpvMacCode().getMacCode().equals("MD014")){
             impressionOperationAffermage();
             }
             if(selected.getOpvMacCode().getMacCode().equals("MD015")||selected.getOpvMacCode().getMacCode().equals("MD016")){
             impressionOperationMetayage();
             }
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

    public TOperationParcel getTOperationParcel(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<TOperationParcel> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TOperationParcel> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TOperationParcel.class)
    public static class TOperationParcelControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TOperationParcelController controller = (TOperationParcelController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tOperationParcelController");
            return controller.getTOperationParcel(getKey(value));
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
            if (object instanceof TOperationParcel) {
                TOperationParcel o = (TOperationParcel) object;
                return getStringKey(o.getOpvNumero());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TOperationParcel.class.getName()});
                return null;
            }
        }

    }

}
