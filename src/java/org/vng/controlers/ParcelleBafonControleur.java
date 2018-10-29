

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.controlers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Named;
//import javax.enterprise.context.Dependent;
import org.vng.sessions.TParcelleBafonFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;
import org.primefaces.harmony.domain.Theme;
import org.primefaces.harmony.service.ThemeService;
import org.primefaces.model.DualListModel;
import org.vng.controlers.util.JsfUtil;
import org.vng.entities.TActivite;
import org.vng.entities.TIntervenant;
import org.vng.entities.TIntervenir;
import org.vng.entities.TParcelleBafon;
import org.vng.entities.TStatut;
import org.vng.entities.TTypeDomaineParcel;
import org.vng.entities.TTypedexerce;
//import org.vng.entities.TTypebf;
import org.vng.sessions.TActiviteFacade;
import org.vng.sessions.TConditionpaieFacade;
import org.vng.sessions.TEthnieFacade;
import org.vng.sessions.TIntervenirFacade;
import org.vng.sessions.TModeacquisFacade;
import org.vng.sessions.TParamettreFacade;
import org.vng.sessions.TRoleFacade;
import org.vng.sessions.TStatutFacade;
import org.vng.sessions.TTypeDomaineParcelFacade;
import org.vng.sessions.TTypebfFacade;
import org.vng.sessions.TTypedexerceFacade;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DualListModel;
import org.primefaces.harmony.domain.Theme;
import org.primefaces.harmony.service.ThemeService;
import org.vng.entities.TDroitExerce;
import org.vng.entities.TParcelleTypeBf;
import org.vng.entities.TPvParcelle;
import org.vng.entities.TTypebf;
import org.vng.sessions.TDroitExerceFacade;
import org.vng.sessions.TParcelleTypeBfFacade;
import org.vng.sessions.TPvParcelleFacade;
import org.vng.sessions.TUtilisateurFacade;
//import org.apache.commons.lang.SerializationUtils; 



/**
 *
 * @author TOFFODJI Charles
 */
//@Named(value = "parcelleBafonControleur")
@Named("parcelleBaf")
//@Dependent
@SessionScoped

public class ParcelleBafonControleur implements Serializable {

    @Inject
    private TDroitExerceFacade tDroitExerceFacade;

    @Inject
    TParcelleBafonFacade parcelleBafonFacade;
    @Inject
    TStatutFacade statutFacade;
    
     @Inject
    TTypebfFacade typebfFacade;
     
    @Inject
    TTypeDomaineParcelFacade typeDomaineFacade;
    
    @Inject
    TIntervenirFacade intervenirFacadeFacade;
    
    @Inject
    TEthnieFacade ethnieFacade;
    
    @Inject
    TActiviteFacade activiteFacade;
  
    @Inject
      TParamettreFacade parametreFacade;
    
   @Inject
    TRoleFacade roleFacade;
   
    @Inject
      TModeacquisFacade modeacquisFacade;
      
     @Inject
       TConditionpaieFacade conditionpaieFacade;
     
     @Inject
       TTypedexerceFacade typeDroitExerceFace;  
     @Inject
     TParcelleTypeBfFacade parcelleTypeBfFacade;
       
     @Inject
     TUtilisateurFacade utilisateurFacade;
     
        @Inject
       TPvParcelleFacade pvParcelleFacade;
     
     /*
     @Resource 
     TIntervenirController intervenirController; 
      */
    
    // Déclaration des objet
          
   private TParcelleBafon selectedParcelle;
   private TParcelleBafon selectedParcelleFusionne; // C'est l'objet pour enregistrer une parcelle fusionnée
   private TParcelleBafon selectedParcelleFusionneTemp; // C'est l'objet pour enregistrer une parcelle fusionnée 
   private TParcelleBafon selectedParcelleFusionneCaracteristik;
   private TIntervenir intervenirSelect, intervenirSelectItem;
   private TIntervenir intervenirSelectFusion ;
   private TIntervenir intervenirSelectFusionTemp ;
   private TIntervenant intervenantSelectFusion ;
   private TIntervenant intervenantSelect ;    
   private TActivite activiteSelect ;
   private TTypeDomaineParcel typeDomaineParcel;
   private TParcelleTypeBf parcelleTypeBf;
   private TStatut statutParcelle;
   private TTypebf typeParcelle ;
   private TPvParcelle pvParcelle ;
   
   private TTypedexerce typeDroitExerce;   
   private Boolean autreActivite = false;
   private Boolean autreNationalite = false;
   private Boolean autreEthnie = false;
   private Boolean fusionEncours = false;
   
   private Boolean clotureActif = false;
   private Boolean autreTravauxActif = false;
   private Boolean batiActif = false;
   private BigDecimal superficieDeduite;
           
            
   
   private List<SelectItem> listeItemFilter;
   
   private List<SelectItem> listeContrePartie, listeAutreModalite;
   
   private List<TDroitExerce> listeTypedexercesAdminUneParcel;
   
    private List<TIntervenir> listeDroitOperationnel,  listeLimitrophe, listeTemp;
   
    //private List<TTypedexerce> listeTypeDroitExerce;
   
    
      
   // Déclaration de type primitif
   private String cheminAbsolutDossierImage;// = parametreFacade.getCheminDossierImage("CHEMINDOSS").getParamValeur() ;
   private String cheminPhotoComplet , cheminPhotoIdentite ;
    
   
   //private static ResourceBundle RES = ResourceBundle.getBundle(ParcelleBafonControleur.class.getCanonicalName());
   
   
   // Fusion
    private List<TIntervenir> listeParcelAFusionner;
   
   
    /**
     * Creates a new instance of ParcelleBafonControleur
     */
    public ParcelleBafonControleur() {
        
      //initialiser();
         
         //listeTypeDroitExerce = new ArrayList<TTypedexerce>();
        
       /*
       
        */    
    }

  
    
    @PostConstruct
    public void init() {
        initialiser();
        //typeParcelle = new TTypebf();
        
        
     //   setCheminPhotoComplet("demo/images/nature/nature1.jpg");
       // setCheminPhotoIdentite("demo/images/nature/nature1.jpg");
       
            listeContrePartie= new ArrayList<SelectItem>();
            
            //listeContrePartie.add(new SelectItem(" ------- " ) );
            listeContrePartie.add(new SelectItem("CADEAUX SYMBOLIQUES", "CADEAUX SYMBOLIQUES" ) );
            listeContrePartie.add(new SelectItem("REDEVANCES", "REDEVANCES" ) );            
            listeContrePartie.add(new SelectItem("REDEVANCES", "REDEVANCES" ) );
            listeContrePartie.add(new SelectItem("RECOLTE", "ARGENT" ) );
            listeContrePartie.add(new SelectItem("REDEVANCES EN PARTS DE RECOLTES ET PARFOIS EN ARGENT", "REDEVANCES EN PARTS DE RECOLTES ET PARFOIS EN ARGENT" ) );
            listeContrePartie.add(new SelectItem("PRESTATIONS EN JOURS DE TRAVAIL", "PRESTATIONS EN JOURS DE TRAVAIL" ) );
            listeContrePartie.add(new SelectItem("TROC", "TROC" ) );
            listeContrePartie.add(new SelectItem("AUTRES( A PRECISER)", "AUTRES( A PRECISER)" ) );
           
           
          
            listeAutreModalite= new ArrayList<SelectItem>();
            listeAutreModalite.add(new SelectItem("SANS PAPIER(S)", "SANS PAPIER(S)" ) );
            listeAutreModalite.add(new SelectItem("CONVENTION DE VENTE", "CONVENTION DE VENTE" ) );            
            listeAutreModalite.add(new SelectItem("TITRE FONCTIER", "TITRE FONCTIER" ) );
            listeAutreModalite.add(new SelectItem("ACTE SOUS SEING PRIVE", "ACTE SOUS SEING PRIVE" ) );
            listeAutreModalite.add(new SelectItem("EN PRESENCE DE TEMOIN", "EN PRESENCE DE TEMOIN" ) );
            listeAutreModalite.add(new SelectItem("SANS TEMOIN(S)", "SANS TEMOIN(S)" ) );
            listeAutreModalite.add(new SelectItem("AUTRES( A PRECISER)", "AUTRES( A PRECISER)" ) );
            
            
            /*
             listeTypeDroitExerce = typeDroitExerceFace.findListTypeDroitExerceByCategorie("DE");
             
              lesTypeDroitExerce = new DualListModel<TTypedexerce>(listeTypeDroitExerce, listeDroitExerceAdmin);
        //Themes
        List<Theme> themesSource = service.getThemes().subList(0, 6);
        List<Theme> themesTarget = new ArrayList<Theme>();
        
        themes = new DualListModel<Theme>(themesSource, themesTarget);
             */
            
        
           
    
  
        
            
    }
    
    public void rechercheParcelle() {
       /* persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TParcelleBafonCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }*/
    }
    
    
    
    
    
  
    
    
    
    
     public List<SelectItem> getListeStatutItem()
    {
        List<TStatut> listStatuts= statutFacade.findAll();
        List<SelectItem> itemStatut = new ArrayList<>();
        for (TStatut statut : listStatuts)    
        { 
            itemStatut.add(new SelectItem(statut, ""+statut.getStaDesig()));
        }
    
        return itemStatut;
    }
    
     
       public List<SelectItem> getListeParcelleAFusionnerItem()
    {
        //List<TStatut> listStatuts= ;  
        List<SelectItem> itemParcelle = new ArrayList<>();
        for (TIntervenir object : listeParcelAFusionner)     
        { 
            itemParcelle.add(new SelectItem(object.getInvPbaNumero(), ""+object.getInvPbaNumero().getPbaNumero()+"  --> "+object.getInvIntNumero().getIntNom()+" "+object.getInvIntNumero().getIntPrenom()));
        }
    
        return itemParcelle;
    }
     
     
     
     
    
    public TParcelleBafon getSelectedParcelle() {
        return selectedParcelle;
    }

    public void setSelectedParcelle(TParcelleBafon selectedParcelle) {
        this.selectedParcelle = selectedParcelle;
    }
   
    

    public TTypebfFacade getTypebfFacade() {
        return typebfFacade;
    }

    public void setTypebfFacade(TTypebfFacade typebfFacade) {
        this.typebfFacade = typebfFacade;
    }

    public TEthnieFacade getEthnieFacade() {
        return ethnieFacade;
    }

    public void setEthnieFacade(TEthnieFacade ethnieFacade) {
        this.ethnieFacade = ethnieFacade;
    }
    
    
    

    public TTypeDomaineParcelFacade getTypeDomaineFacade() {
        return typeDomaineFacade;
    }

    public void setTypeDomaineFacade(TTypeDomaineParcelFacade typeDomaineFacade) {
        this.typeDomaineFacade = typeDomaineFacade;
    }

    public TIntervenir getIntervenirSelect() {
        return intervenirSelect;
    }

    public void setIntervenirSelect(TIntervenir intervenirSelect) {
        this.intervenirSelect = intervenirSelect;
    }

    public TIntervenant getIntervenantSelect() {
        return intervenantSelect;
    }

    public void setIntervenantSelect(TIntervenant intervenantSelect) {
        this.intervenantSelect = intervenantSelect;
    }

    public TTypeDomaineParcel getTypeDomaineParcel() {
        return typeDomaineParcel;
    }

    public void setTypeDomaineParcel(TTypeDomaineParcel typeDomaineParcel) {
        this.typeDomaineParcel = typeDomaineParcel;
    }

    public TActivite getActiviteSelect() {
        return activiteSelect;
    }

    public void setActiviteSelect(TActivite activiteSelect) {
        this.activiteSelect = activiteSelect;
    }

    public Boolean getAutreActivite() {
        return autreActivite;
    }

    public void setAutreActivite(Boolean autreActivite) {
        this.autreActivite = autreActivite;
    }

    public Boolean getAutreNationalite() {
        return autreNationalite;
    }

    public void setAutreNationalite(Boolean autreNationalite) {
        this.autreNationalite = autreNationalite;
    }

    public Boolean getAutreEthnie() {
        return autreEthnie;
    }

    public void setAutreEthnie(Boolean autreEthnie) {
        this.autreEthnie = autreEthnie;
    }

    public List<SelectItem> getListeItemFilter() {
        return listeItemFilter;
    }

    public void setListeItemFilter(List<SelectItem> listeItemFilter) {
        this.listeItemFilter = listeItemFilter;
    }

    public String getCheminPhotoComplet() {
        return cheminPhotoComplet;
    }

    public void setCheminPhotoComplet(String cheminPhotoComplet) {
        this.cheminPhotoComplet = cheminPhotoComplet;
    }

    public String getCheminPhotoIdentite() {
        return cheminPhotoIdentite;
    }

    public void setCheminPhotoIdentite(String cheminPhotoIdentite) {
        this.cheminPhotoIdentite = cheminPhotoIdentite;
    }

    public TActiviteFacade getActiviteFacade() {
        return activiteFacade;
    }

    public void setActiviteFacade(TActiviteFacade activiteFacade) {
        this.activiteFacade = activiteFacade;
    }

    public Boolean getClotureActif() {
        return clotureActif;
    }

    public void setClotureActif(Boolean clotureActif) {
        this.clotureActif = clotureActif;
    }

    public Boolean getAutreTravauxActif() {
        return autreTravauxActif;
    }

    public void setAutreTravauxActif(Boolean autreTravauxActif) {
        this.autreTravauxActif = autreTravauxActif;
    }

    public Boolean getBatiActif() {
        return batiActif;
    }

    public void setBatiActif(Boolean batiActif) {
        this.batiActif = batiActif;
    }

    public TRoleFacade getRoleFacade() {
        return roleFacade;
    }

    public void setRoleFacade(TRoleFacade roleFacade) {
        this.roleFacade = roleFacade;
    }

    public TModeacquisFacade getModeacquisFacade() {
        return modeacquisFacade;
    }

    public void setModeacquisFacade(TModeacquisFacade modeacquisFacade) {
        this.modeacquisFacade = modeacquisFacade;
    }

    public TConditionpaieFacade getConditionpaieFacade() {
        return conditionpaieFacade;
    }

    public void setConditionpaieFacade(TConditionpaieFacade conditionpaieFacade) {
        this.conditionpaieFacade = conditionpaieFacade;
    }

    public List<SelectItem> getListeContrePartie() {
        return listeContrePartie;
    }

    public void setListeContrePartie(List<SelectItem> listeContrePartie) {
        this.listeContrePartie = listeContrePartie;
    }

    public List<SelectItem> getListeAutreModalite() {
        return listeAutreModalite;
    }

    public void setListeAutreModalite(List<SelectItem> listeAutreModalite) {
        this.listeAutreModalite = listeAutreModalite;
    }

    public TTypedexerceFacade getTypeDroitExerceFace() {
        return typeDroitExerceFace;
    }

    public void setTypeDroitExerceFace(TTypedexerceFacade typeDroitExerceFace) {
        this.typeDroitExerceFace = typeDroitExerceFace;
    }

    public TUtilisateurFacade getUtilisateurFacade() {
        return utilisateurFacade;
    }

    public void setUtilisateurFacade(TUtilisateurFacade utilisateurFacade) {
        this.utilisateurFacade = utilisateurFacade;
    }

    
    
    public TTypedexerce getTypeDroitExerce() {
        return typeDroitExerce;
    }

    public void setTypeDroitExerce(TTypedexerce typeDroitExerce) {
        this.typeDroitExerce = typeDroitExerce;
    }

    public List<TDroitExerce> getListeTypedexercesAdminUneParcel() {
        return listeTypedexercesAdminUneParcel;
    }

    public void setListeTypedexercesAdminUneParcel(List<TDroitExerce> listeTypedexercesAdminUneParcel) {
        this.listeTypedexercesAdminUneParcel = listeTypedexercesAdminUneParcel;
    }

    public List<TIntervenir> getListeDroitOperationnel() {
        return listeDroitOperationnel;
    }

    public void setListeDroitOperationnel(List<TIntervenir> listeDroitOperationnel) {
        this.listeDroitOperationnel = listeDroitOperationnel;
    }

    public List<TIntervenir> getListeLimitrophe() {
        return listeLimitrophe;
    }

    public void setListeLimitrophe(List<TIntervenir> listeLimitrophe) {
        this.listeLimitrophe = listeLimitrophe;
    }

    public TTypebf getTypeParcelle() {
        return typeParcelle;
    }

    public void setTypeParcelle(TTypebf typeParcelle) {
        this.typeParcelle = typeParcelle;
    }

    public TStatut getStatutParcelle() {
        return statutParcelle;
    }

    public void setStatutParcelle(TStatut statutParcelle) {
        this.statutParcelle = statutParcelle;
    }

    public BigDecimal getSuperficieDeduite() {
        return superficieDeduite;
    }

    public void setSuperficieDeduite(BigDecimal superficieDeduite) {
        this.superficieDeduite = superficieDeduite;
    }

    public TPvParcelleFacade getPvParcelleFacade() {
        return pvParcelleFacade;
    }

    public void setPvParcelleFacade(TPvParcelleFacade pvParcelleFacade) {
        this.pvParcelleFacade = pvParcelleFacade;
    }

    public TPvParcelle getPvParcelle() {
        return pvParcelle;
    }

    public void setPvParcelle(TPvParcelle pvParcelle) {
        this.pvParcelle = pvParcelle;
    }

    
    
    
    public void onAutreTypeClotureCheck()
    {
        if(getAutreTravauxActif())
        setAutreTravauxActif(true);
    }
    
   
    public void onParcelleRowSelect(SelectEvent event)
    {
        
        
       // cheminAbsolutDossierImage = parametreFacade.getCheminDossierImage("CHEMINDOSS").getParamValeur() ;
        //System.out.println(" intervenirSelect   "+intervenirSelect);
        selectedParcelle = intervenirSelect.getInvPbaNumero();
        setTypeParcelle(selectedParcelle.getPbaTbfCode());
        setStatutParcelle(selectedParcelle.getPbaStaCode());
        setTypeDomaineParcel(selectedParcelle.getPbaStaCode().getStaTydoCode());
        intervenantSelect = intervenirSelect.getInvIntNumero();
        if(selectedParcelle.getPbaNumero()!=null){
           listeTypedexercesAdminUneParcel = tDroitExerceFacade.findListDroitExerceParcelleByCategorie(selectedParcelle.getPbaNumero(), "DEX");
        
           setListeDroitOperationnel(intervenirFacadeFacade.findListParcelleByCategorieParcelle(selectedParcelle.getPbaNumero(), "OP"));
        
           setListeLimitrophe(intervenirFacadeFacade.findListParcelleByCategorieParcelle(selectedParcelle.getPbaNumero(), "LIM"));
        }
        
        if(selectedParcelle != null && intervenantSelect != null )
            pvParcelle= getPvParcelleFacade().findPVParcelleIntervenant(selectedParcelle.getPbaNumero(), intervenantSelect.getIntNumero());
        
        //listeTypedexercesAdminUneParcel.get(0).getDreTdeCode().getTdeDesig()
       // activiteSelect = intervenantSelect.getIntActCode();
        
          /*
      if(listeParcelAFusionner.isEmpty())
        {    superficieDeduite = BigDecimal.ZERO  ;                      
             setSelectedParcelleFusionneTemp(intervenirSelect.getInvPbaNumero());
             selectedParcelleFusionneTemp.setPbaSuperficie(superficieDeduite);
             selectedParcelleFusionneTemp.setPbaNumero(intervenirSelect.getInvPbaNumero().getPbaVilaCode().getVilaCode());
        }else selectedParcelleFusionneTemp = new TParcelleBafon();
        */  
    
        
    /*    String numParcelleImage = "";
        if(selectedParcelle != null && selectedParcelle.getPbaNumero() != null )
        {
           
            if(intervenantSelect.getIntPhotoComplet() != null && !intervenantSelect.getIntPhotoComplet().equalsIgnoreCase(""))
            {
                 numParcelleImage = intervenantSelect.getIntPhotoComplet().substring(0,11);
                 String numParcelle = numParcelleImage.substring(0,7)+"\\"+numParcelleImage;                 
                 setCheminPhotoComplet(cheminAbsolutDossierImage+numParcelle+"\\"+intervenantSelect.getIntPhotoComplet());
            }
            
            numParcelleImage = "";
        
         if(intervenantSelect.getIntPhotoIdentit() != null && !intervenantSelect.getIntPhotoIdentit().equalsIgnoreCase(""))
            {
                 numParcelleImage = intervenantSelect.getIntPhotoIdentit().substring(0,11);
                 String numParcelle = numParcelleImage.substring(0,7)+"\\"+numParcelleImage;                 
                 setCheminPhotoIdentite(cheminAbsolutDossierImage+numParcelle+"\\"+intervenantSelect.getIntPhotoIdentit());
            }
         
            System.out.println(" setCheminPhotoComplet "+getCheminPhotoComplet());
            
            System.out.println(" setCheminPhotoIdentite "+getCheminPhotoIdentite());
            
       //  setCheminPhotoIdentite(cheminAbsolutDossierImage+numParcelle+"\\"+intervenantSelect.getIntPhotoIdentit());
        
        } */
    
        
      //  System.out.println("  on passe ");
      // FacesMessage msg = new FacesMessage("Car Selected", ((LotMarche) event.getObject()).getRefMarche()); 
      //  String refMarche = (((LotMarche) event.getObject()).getRefMarche());        
        //detailLotMarcheByReference();

    }
    
    
    
    public void onActiviteRowSelect(SelectEvent event)
    {
        
          System.out.println(" Ici "+selectedParcelle.getPbaNumero());
       // System.out.println("event  "+activiteSelect.getActDesig());
        /*
        if(activiteSelect.getActCode().equalsIgnoreCase("AUTRE"))
            setAutreActivite(true);
        else
          setAutreActivite(false);
        
        intervenantSelect.setIntActCode(activiteSelect);
        
        */
      //  System.out.println("  on passe ");
      // FacesMessage msg = new FacesMessage("Car Selected", ((LotMarche) event.getObject()).getRefMarche()); 
      //  String refMarche = (((LotMarche) event.getObject()).getRefMarche());        
        //detailLotMarcheByReference();

    }
    
    
  
    
    
    public void ajouterDroitExerceListener()
    {
       
      
    }
     
    
    
    public String majParcelleAgricolNoyeauVillageois()
    {  
        try
        {  
              if(controleNulliteNoyeauVillageois()) // si c'est vrai ca veut dire que tout les élements sont en place
              {
            
               parcelleTypeBf = new TParcelleTypeBf();
               parcelleTypeBf.setPatyCode("111");
               parcelleTypeBf.setPatyDateDeb(new Date());
               parcelleTypeBf.setPatyPbaNumero(selectedParcelle);
               parcelleTypeBf.setPatyTbfCode(selectedParcelle.getPbaTbfCode());
               parcelleTypeBfFacade.create(parcelleTypeBf);
               
               selectedParcelle.setPbaTbfCode(typebfFacade.find("TBF02")); // On fiche la parcelle noyau villageois ici
               parcelleBafonFacade.edit(selectedParcelle);
               JsfUtil.addSuccessMessage(ResourceBundle.getBundle("org.vng.ressources.messages_fr").getString("ConfirmationOperation"));
                 
               initialiser();
               
              }
            /*
                  if(lotMarche.getCodTypMar().getCodTypMar().equalsIgnoreCase("CONTRAT"))  // Ceci veut dire que c'est sur un contrat on fait le traitement
                    {    
                          if(ejbFacade.majMarcheContrat(lotMarche , listeAdjudicataire , listeContrib))  // Il s'agit d'un marché normal
                           {
                               initialiser();
                           }
                   }else if(lotMarche.getCodTypMar().getCodTypMar().equalsIgnoreCase("SOUSTRAIT")) // Sous traitance
                   {
                      
                          if(ejbFacade.majMarcheSousTraitance(lotMarche ,lotMarcheMere , listeAdjudicataire, adjudicationMere))
                           {
                                initialiser();
                           }
                   }else if(lotMarche.getCodTypMar().getCodTypMar().equalsIgnoreCase("AUTORISE")) // Autorisation
                   {
                        System.out.println("  adjudicationMere    "+adjudicationMere+"  \n lotMarcheMere   "+lotMarcheMere);
                       
                          if(ejbFacade.majMarcheAutorisation(lotMarche ,lotMarcheMere , listeAdjudicataire, adjudicationMere))
                           {
                                initialiser();
                           }
                   }else if(lotMarche.getCodTypMar().getCodTypMar().equalsIgnoreCase("CONSOMP1")) // consommation MP1
                   {
                          if(ejbFacade.majMarcheAncienMp1(lotMarche , listeAdjudicataire , listeContrib))  // Il s'agi de la consommation d'un ancien MP1
                           {
                               initialiser();
                           }    
                   }*/
                    
                   // JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MessageConfirmationMarche"));
                   
              
              //}
        }catch(Exception ex)
         {
           // showErrorMessage("Une erreur s'est produite : "+ex.getMessage());
            // ex.printStackTrace();
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("msg").getString("msg_erreur_enregistrement") );
           // JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("MessageErreurMarche"));
         }
        return "parcelleagricolenoyau.xhtml";
    }
    
    
    
    /*
    Controle de nuillité pour parcelle agricole en noyau villageois
    */
    private boolean controleNulliteNoyeauVillageois()
    {
        boolean etat = true;
        
        if(selectedParcelle.getPbaNumero() == null || selectedParcelle.getPbaNumero().isEmpty() )
        {
             JsfUtil.addSuccessMessage("Veuillez sélectionner la parcelle.");
             etat = false;
        }
        
        return etat;
    }
    
    
    
    public void initialiser()
    {
         selectedParcelle = new TParcelleBafon();
         selectedParcelleFusionne  = new TParcelleBafon();
         selectedParcelleFusionneCaracteristik  = new TParcelleBafon();
         intervenantSelect = new TIntervenant();         
         intervenirSelect = new TIntervenir();  
      //  System.err.println("ttttt  "+intervenirSelect);
         intervenantSelectFusion =  new TIntervenant();
         intervenirSelectFusion = new TIntervenir();
         intervenirSelectFusionTemp = new TIntervenir();
         pvParcelle = new TPvParcelle();
        // System.err.println("tttttt  "+selectedParcelleFusionne.getPbaVilaCode().getVlaDesig());
         listeTypedexercesAdminUneParcel = new ArrayList<>();
         listeDroitOperationnel = new ArrayList<>();
         listeLimitrophe = new ArrayList<>();
         listeParcelAFusionner = new ArrayList<>();
         listeTemp = new ArrayList<>();
         
        activiteSelect = new TActivite();
        typeDomaineParcel = new TTypeDomaineParcel();
        parcelleTypeBf = new TParcelleTypeBf();
        statutParcelle = new TStatut();
        typeParcelle = new TTypebf();
        typeDroitExerce = new TTypedexerce();   
        autreActivite = false;
        autreNationalite = false;
        autreEthnie = false;
        clotureActif = false;
        autreTravauxActif = false;
        batiActif = false;
      
    }

    
    


    
    
    
    // //////////////////// Fusion de parcelle
    
        public TIntervenir getIntervenirSelectFusion() {    
        return intervenirSelectFusion;
    }
    
    public void setIntervenirSelectFusion(TIntervenir intervenirSelectFusion) {
       // System.out.println("SET intervenirSelectFusion "+intervenirSelectFusion); 
        this.intervenirSelectFusion = intervenirSelectFusion;
    }

    public TIntervenir getIntervenirSelectFusionTemp() {
        return intervenirSelectFusionTemp;
    }

    public void setIntervenirSelectFusionTemp(TIntervenir intervenirSelectFusionTemp) {
        this.intervenirSelectFusionTemp = intervenirSelectFusionTemp;
    }

    public TIntervenant getIntervenantSelectFusion() {
        return intervenantSelectFusion;
    }

    public void setIntervenantSelectFusion(TIntervenant intervenantSelectFusion) {
        this.intervenantSelectFusion = intervenantSelectFusion;
    }

    public TIntervenir getIntervenirSelectItem() {
        return intervenirSelectItem;
    }

    public void setIntervenirSelectItem(TIntervenir intervenirSelectItem) {
        this.intervenirSelectItem = intervenirSelectItem;
    }

    
    
    
    public List<TIntervenir> getListeParcelAFusionner() {
        //System.out.println("GET intervenirSelectFusion "+intervenirSelectFusion);
        return listeParcelAFusionner;
    }

    public void setListeParcelAFusionner(List<TIntervenir> listeParcelAFusionner) {
        this.listeParcelAFusionner = listeParcelAFusionner;
    }

    public TParcelleBafon getSelectedParcelleFusionne() {
        return selectedParcelleFusionne;
    }

    public void setSelectedParcelleFusionne(TParcelleBafon selectedParcelleFusionne) {
        this.selectedParcelleFusionne = selectedParcelleFusionne;
    }

    public Boolean getFusionEncours() {
        return fusionEncours;
    }

    public void setFusionEncours(Boolean fusionEncours) {
        this.fusionEncours = fusionEncours;
    }

    public TParcelleBafon getSelectedParcelleFusionneCaracteristik() {
        return selectedParcelleFusionneCaracteristik;
    }

    public void setSelectedParcelleFusionneCaracteristik(TParcelleBafon selectedParcelleFusionneCaracteristik) {
        this.selectedParcelleFusionneCaracteristik = selectedParcelleFusionneCaracteristik;
    }
    
    
    public void onParcelleFusionRowSelect(SelectEvent event)
    {
     
        selectedParcelle = intervenirSelect.getInvPbaNumero();
        setTypeParcelle(selectedParcelle.getPbaTbfCode());
        setStatutParcelle(selectedParcelle.getPbaStaCode());
        setTypeDomaineParcel(selectedParcelle.getPbaStaCode().getStaTydoCode());
        intervenantSelect = intervenirSelect.getInvIntNumero();
  
    }
    
  
    
    public void ajouterParcellePourFusionListener(ActionEvent event)
    {
        boolean trouver = false;

        if(listeParcelAFusionner.isEmpty())
        {   
            setFusionEncours(true);
            superficieDeduite = BigDecimal.ZERO  ;
             listeParcelAFusionner.add(intervenirSelect);             
             selectedParcelleFusionne = (TParcelleBafon)copyObject(intervenirSelect.getInvPbaNumero()) ;                        
             selectedParcelleFusionne.setPbaNumero(intervenirSelect.getInvPbaNumero().getPbaVilaCode().getVilaCode());
             superficieDeduite = superficieDeduite.add(intervenirSelect.getInvPbaNumero().getPbaSuperficie());
             selectedParcelleFusionne.setPbaSuperficie(superficieDeduite); 
             
             intervenirSelect = new TIntervenir();             
             selectedParcelle = new TParcelleBafon(); 
              
        
        }else
        {
           
            for (TIntervenir intervenir : listeParcelAFusionner)
            {
               if(intervenir.getInvPbaNumero().getPbaNumero().equalsIgnoreCase(intervenirSelect.getInvPbaNumero().getPbaNumero()) || 
                  !intervenir.getInvPbaNumero().getPbaVilaCode().getVilaCode().equalsIgnoreCase(intervenirSelect.getInvPbaNumero().getPbaVilaCode().getVilaCode())     ) 
               {
                  trouver = true;
                  break;
               }   
            }
            
            if(!trouver)
            {                
                 listeParcelAFusionner.add(intervenirSelect);
                 superficieDeduite = superficieDeduite.add(intervenirSelect.getInvPbaNumero().getPbaSuperficie());
                 //selectedParcelleFusionne.setPbaSuperficie(superficieDeduite);                
                 intervenirSelect = new TIntervenir();
                 selectedParcelle = new TParcelleBafon();
            }else{
               // JsfUtil.addSuccessMessage(ResourceBundle.getBundle("org.vng.ressources.messages_fr").getString("ConfirmationOperation"));
                JsfUtil.addSuccessMessage("Cette parcelle a été déjà ajouté ou n'est pas dans le même village que le premier.");
            }
             
            
        }
       
      
    }
    
    
      public void onTBFSelect(SelectEvent event)
    {
         
    }
      
      public void onParcelleFusionne(SelectEvent event)
    {
        long prix = 0 ;
        
                listeTypedexercesAdminUneParcel = new ArrayList<>();
                listeDroitOperationnel = new ArrayList<>();
                listeLimitrophe = new ArrayList<>();  
            for (TIntervenir intervenir : listeParcelAFusionner)
            {  
                if(selectedParcelleFusionneCaracteristik.getPbaNumero().equalsIgnoreCase(intervenir.getInvPbaNumero().getPbaNumero()))
                {
                    intervenirSelectFusion = (TIntervenir)copyObject(intervenir);
                    intervenantSelectFusion = (TIntervenant)copyObject(intervenirSelectFusion.getInvIntNumero());
                }
                
                prix = prix + intervenir.getInvPrix();                   
                listeTypedexercesAdminUneParcel = tDroitExerceFacade.findListDroitExerceParcelleByCategorie(intervenir.getInvPbaNumero().getPbaNumero(), "DEX");        
                setListeDroitOperationnel(intervenirFacadeFacade.findListParcelleByCategorieParcelle(intervenir.getInvPbaNumero().getPbaNumero(), "OP"));        
                setListeLimitrophe(intervenirFacadeFacade.findListParcelleByCategorieParcelle(intervenir.getInvPbaNumero().getPbaNumero(), "LIM"));
               
            } 
            
            intervenirSelectFusion.setInvPrix(prix);
    }
    
    
    public void detailParcellePourFusionListener(ActionEvent event)
    {
     
       // System.out.println(" intervenirSelectFusion "+intervenirSelectFusion.getInvPbaNumero().getPbaNumero());
        
        intervenantSelect = intervenirSelectFusionTemp.getInvIntNumero();     
        /*for (TIntervenir intervenir : listeParcelAFusionner)
            {
               
            }
         */
            
       
      
    }
    
    public void retirerParcellePourFusionListener(ActionEvent event)
    {
     
//       System.out.println(" intervenirSelectFusion "+intervenirSelectFusion.getInvPbaNumero().getPbaNumero());        
        //intervenantSelect = intervenirSelectFusion.getInvIntNumero(); 
         //System.err.println(" intervenirSelectFusion "+intervenirSelectFusion);
        int i = 0;
           for (TIntervenir intervenir : listeParcelAFusionner)
            {
                System.err.println(" intervenirSelectFusionTemp "+intervenirSelectFusionTemp);
               if(intervenirSelectFusionTemp.getInvPbaNumero().getPbaNumero().equalsIgnoreCase(intervenir.getInvPbaNumero().getPbaNumero()))
               {
                  listeParcelAFusionner.remove(i);
                  break;
               }
              
                i++;
            }
   
      
    }
    
    /*
    Controle de nuillité pour parcelle agricole en noyau villageois
    */
    private boolean controleNulliteFusion()
    {
        boolean etat = true;
        
        if(selectedParcelleFusionne.getPbaNumero() == null || selectedParcelleFusionne.getPbaNumero().isEmpty() )
        {
             JsfUtil.addSuccessMessage("Veuillez saisir le numéro de la parcelle.");
             etat = false;
        }else if(selectedParcelleFusionne.getPbaNumero().length() < 11 )
        {
             JsfUtil.addSuccessMessage("La taille minimum d'un numéro de parcelle est de 11.");
             etat = false;
        }
        
        return etat;
    }
    
    public String majFusionParcelle()
    {  
        try
        {  
              if(controleNulliteFusion()) // si c'est vrai ca veut dire que tout les élements sont en place
              {
                  
                  // On inserre la parcelle
                  String statutParcelle = "FUSION";
                  parcelleBafonFacade.create(selectedParcelleFusionne);
                  
                  for (TIntervenir intervenir : listeParcelAFusionner)
                   {
                       TParcelleBafon parcelle = new TParcelleBafon();
                       parcelle = intervenir.getInvPbaNumero();
                       parcelle.setPbaPbaNumero(selectedParcelleFusionne);
                       parcelle.setPbaStatut(statutParcelle);
                       parcelle.setPbaActif(false);
                       parcelleBafonFacade.edit(parcelle);    
                       
                       intervenir.setInvDateFin(new Date());
                       intervenirFacadeFacade.edit(intervenir);
                       
                   }
                  // On inserre la ligne du droit 
                  intervenirSelectFusion.setInvNumero("111");
                  intervenirSelectFusion.setInvPbaNumero(selectedParcelleFusionne);
                  intervenirFacadeFacade.create(intervenirSelectFusion);
                  
                  if(pvParcelle != null && pvParcelle.getPvPv() != null)
                      pvParcelleFacade.create(pvParcelle);
                  
                  
                  for (TIntervenir intervenir : listeDroitOperationnel)
                   {
                      intervenirFacadeFacade.create(intervenirSelectFusion);
//                       parcelleBafonFacade.edit(parcelle);             
                   }
                  
                  
               /*parcelleTypeBf = new TParcelleTypeBf();
               parcelleTypeBf.setPatyCode("111");
               parcelleTypeBf.setPatyDateDeb(new Date());
               parcelleTypeBf.setPatyPbaNumero(selectedParcelle);
               parcelleTypeBf.setPatyTbfCode(selectedParcelle.getPbaTbfCode());
               */
               
               
              
               JsfUtil.addSuccessMessage(ResourceBundle.getBundle("org.vng.ressources.messages_fr").getString("ConfirmationOperation"));
                 
               initialiser();
               
              }
            
              
              
        }catch(Exception ex)
         {
           // showErrorMessage("Une erreur s'est produite : "+ex.getMessage());
            // ex.printStackTrace();
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("msg").getString("msg_erreur_enregistrement") );
           // JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("MessageErreurMarche"));
         }
        return "fusionparcelle.xhtml";
    }
    
    
    private Object copyObject(Object objSource) {
        Object objDest= null;
        try {
           
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(objSource);
            oos.flush();
            oos.close();
            bos.close();
            byte[] byteData = bos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
            try {
                objDest = new ObjectInputStream(bais).readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objDest;

    }
    
    
     
}
