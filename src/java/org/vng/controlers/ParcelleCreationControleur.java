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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
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
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.primefaces.model.DualListModel;
import org.primefaces.harmony.domain.Theme;
import org.primefaces.harmony.service.ThemeService;
import org.vng.entities.TArrondissement;
import org.vng.entities.TDroitExerce;
import org.vng.entities.TParcelleTypeBf;
import org.vng.entities.TPvParcelle;
import org.vng.entities.TTypebf;
import org.vng.entities.TVillage;
import org.vng.sessions.TArrondissementFacade;
import org.vng.sessions.TCommuneFacade;
import org.vng.sessions.TDroitExerceFacade;
import org.vng.sessions.TIntervenantFacade;
import org.vng.sessions.TParcelleTypeBfFacade;
import org.vng.sessions.TPvParcelleFacade;
import org.vng.sessions.TUtilisateurFacade;
import org.vng.sessions.TVillageFacade;
//import org.apache.commons.lang.SerializationUtils; 



/**
 *
 * @author TOFFODJI Charles
 */
//@Named(value = "parcelleBafonControleur")
@Named("parcelleCreation")
//@Dependent
@SessionScoped

public class ParcelleCreationControleur implements Serializable {

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
    TIntervenantFacade intervenantFacade ;
    
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
     
     @Inject
     TDroitExerceFacade droitExerceFacade;
     
     
     @Inject
     TCommuneFacade communeFacade;
          
     @Inject
     TArrondissementFacade arrondissementFacade;
               
     @Inject
     TVillageFacade villageFacade;
     
     /*
     @Resource 
     TIntervenirController intervenirController; 
      */
    
    // Déclaration des objet
          
   private TParcelleBafon selectedParcelle;
   private TIntervenir intervenirSelect, intervenirSelectItem;
   private TIntervenant intervenantSelect ;    
   private TActivite activiteSelect ;
   private TTypeDomaineParcel typeDomaineParcel;
   private TParcelleTypeBf parcelleTypeBf;
   private TStatut statutParcelle;
   private TTypebf typeParcelle ;
   private TPvParcelle pvParcelle ;
   
   private TVillage villageSelect ;
   private TArrondissement arrondissmeentSelect ;
   
   private TTypedexerce typeDroitExerce;   
   private Boolean autreActivite = false;
   private Boolean autreNationalite = false;
   private Boolean autreEthnie = false;
   private Boolean parcelleDPP = false;
   
   private Boolean clotureActif = false;
   private Boolean autreTravauxActif = false;
   private Boolean batiActif = false;
   private BigDecimal superficieDeduite;
   private Boolean represente = false;
           
            
   
   private List<SelectItem> listeItemFilter;
   
   private List<SelectItem> listeContrePartie, listeAutreModalite;
   
   private List<SelectItem> listeVillage, listeArrondissement;
   
   private List<TDroitExerce> listeTypedexercesAdminUneParcel, listeTypedexercesOPUneParcel ;
   
    private List<TIntervenir> listeDroitOperationnel,  listeLimitrophe, listeTemp;
   
    //private List<TTypedexerce> listeTypeDroitExerce;
   
    
      
   // Déclaration de type primitif
   private String cheminAbsolutDossierImage;// = parametreFacade.getCheminDossierImage("CHEMINDOSS").getParamValeur() ;
   private String cheminPhotoComplet , cheminPhotoIdentite ;
    

       public void onTBFSelect(SelectEvent event)
    {
         typeParcelle = selectedParcelle.getPbaTbfCode();
    }
   
   
    public ParcelleCreationControleur() {
        
      initialiser();
     
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
            
            setListeArrondissement(arrondissementFacade.getListeArrondissementCommuneEncoursItem());
            
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

    public Boolean getRepresente() {
        return represente;
    }

    public void setRepresente(Boolean represente) {
        this.represente = represente;
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

    public List<TDroitExerce> getListeTypedexercesOPUneParcel() {
        return listeTypedexercesOPUneParcel;
    }

    public void setListeTypedexercesOPUneParcel(List<TDroitExerce> listeTypedexercesOPUneParcel) {
        this.listeTypedexercesOPUneParcel = listeTypedexercesOPUneParcel;
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

    public TCommuneFacade getCommuneFacade() {
        return communeFacade;
    }

    public void setCommuneFacade(TCommuneFacade communeFacade) {
        this.communeFacade = communeFacade;
    }

    public TArrondissementFacade getArrondissementFacade() {
        return arrondissementFacade;
    }

    public void setArrondissementFacade(TArrondissementFacade arrondissementFacade) {
        this.arrondissementFacade = arrondissementFacade;
    }

    public TVillageFacade getVillageFacade() {
        return villageFacade;
    }

    public void setVillageFacade(TVillageFacade villageFacade) {
        this.villageFacade = villageFacade;
    }

    public List<SelectItem> getListeVillage() {
        return listeVillage;
    }

    public void setListeVillage(List<SelectItem> listeVillage) {
        this.listeVillage = listeVillage;
    }

    public List<SelectItem> getListeArrondissement() {
        return listeArrondissement;
    }

    public void setListeArrondissement(List<SelectItem> listeArrondissement) {
        this.listeArrondissement = listeArrondissement;
    }
    
    
    

    public TPvParcelle getPvParcelle() {
        return pvParcelle;
    }

    public void setPvParcelle(TPvParcelle pvParcelle) {
        this.pvParcelle = pvParcelle;
    }

    public TVillage getVillageSelect() {
        return villageSelect;
    }

    public void setVillageSelect(TVillage villageSelect) {
        this.villageSelect = villageSelect;
    }

    public TArrondissement getArrondissmeentSelect() {
        return arrondissmeentSelect;
    }

    public void setArrondissmeentSelect(TArrondissement arrondissmeentSelect) {
        this.arrondissmeentSelect = arrondissmeentSelect;
    }

    public TParcelleTypeBf getParcelleTypeBf() {
        return parcelleTypeBf;
    }

    public void setParcelleTypeBf(TParcelleTypeBf parcelleTypeBf) {
        this.parcelleTypeBf = parcelleTypeBf;
    }
    
    
    

 public void onArrondissementSelect(SelectEvent event)
    {
        if(arrondissmeentSelect != null)
        {
            setListeVillage(villageFacade.getListeVillageByArrondissementItem(arrondissmeentSelect.getArrCode()));
        }  
    }
    
 
  public void onVillageSelect(SelectEvent event)
    {
     
        if(selectedParcelle != null && (selectedParcelle.getPbaNumero() == null || ( selectedParcelle.getPbaNumero() != null && selectedParcelle.getPbaNumero().isEmpty()  ) ) )
        {
            selectedParcelle.setPbaNumero(selectedParcelle.getPbaVilaCode().getVilaCode());
        }else
        {
            if(selectedParcelle.getPbaNumero().length() == 7)
            {
                selectedParcelle.setPbaNumero(selectedParcelle.getPbaVilaCode().getVilaCode());
            }else
            {
                if(!selectedParcelle.getPbaNumero().subSequence(0, 7).toString().equalsIgnoreCase(selectedParcelle.getPbaVilaCode().getVilaCode()))
                {
                    selectedParcelle.setPbaNumero(selectedParcelle.getPbaVilaCode().getVilaCode());
                }
            }
        }
    }
    
   public void onIntervenantRowSelect(SelectEvent event)
    {
        
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
           
           setListeTypedexercesOPUneParcel(tDroitExerceFacade.findListDroitExerceParcelleByCategorie(selectedParcelle.getPbaNumero(), "OP"));
           setListeTypedexercesOPUneParcel(tDroitExerceFacade.findListDroitExerceParcelleByCategorie(selectedParcelle.getPbaNumero(), "OP1"));
           
           setListeDroitOperationnel(intervenirFacadeFacade.findListParcelleByCategorieParcelle(selectedParcelle.getPbaNumero(), "OP"));
        
           setListeLimitrophe(intervenirFacadeFacade.findListParcelleByCategorieParcelle(selectedParcelle.getPbaNumero(), "LIM"));
        }
        /*
        if(selectedParcelle != null && selectedParcelle.getPbaNumero() != null && intervenantSelect != null && intervenantSelect.getIntNumero() != null ){
            pvParcelle= getPvParcelleFacade().findPVParcelleIntervenant(selectedParcelle.getPbaNumero(), intervenantSelect.getIntNumero().toString());
            pvParcelle.setPvNumero(null);            
        } */
            
        
    }
    
    
    
    
  public void retirerTypeDroitListener(TDroitExerce droitExerceItem)
    { 
        
        int i = 0;
           for (TDroitExerce droitExerce : listeTypedexercesAdminUneParcel)
            {
               if(droitExerce.getDreTdeCode().getTdeCode().equalsIgnoreCase(droitExerceItem.getDreTdeCode().getTdeCode()))
               {
                  listeTypedexercesAdminUneParcel.remove(i);
                  droitExerceItem = new TDroitExerce();
                  break;
               } 
                i++;
            }
    }
    
    
    public void ajouterDroitExerceListener()
    {
       
       if(typeDroitExerce != null && !typeDroitExerce.getTdeCode().equalsIgnoreCase(""))
       {
           boolean trouver = false;
           if(listeTypedexercesAdminUneParcel.size()>0) 
           {
                if(typeDroitExerce.getTdeCode().equalsIgnoreCase("TD009"))
                     trouver = true;
                
                System.err.println("listeTypedexercesAdminUneParcel.get(0)  "+listeTypedexercesAdminUneParcel.get(0));
                if(!trouver && listeTypedexercesAdminUneParcel.size() == 1 )
                          if(listeTypedexercesAdminUneParcel.get(0).getDreTdeCode().getTdeCode().equalsIgnoreCase("TD009"))
                              trouver = true;
                     
           }
                
          
                     
           if(!trouver)
                for( TDroitExerce tde: listeTypedexercesAdminUneParcel)
                {
                    if(typeDroitExerce.getTdeCode().equalsIgnoreCase(tde.getDreTdeCode().getTdeCode()))
                    {
                        trouver = true;
                        break;
                    }
                }
           
           if(!trouver)
           {
                TDroitExerce tde = new TDroitExerce();
                tde.setDreCat("DE");
                tde.setDreDateDebut(new Date());
                tde.setDreTdeCode(typeDroitExerce);
                listeTypedexercesAdminUneParcel.add(tde);
           }
       }
      
    }
     
    /**
     * Cette methode permet de lancer l'enregistrement du changement de parcelle agricole en noyau villageois.
     * @return 
     */
    
    public String creationParcelle()
    {  
        try
        {  
            
            
              if(controleNulliteNoyeauVillageois()) // si c'est vrai ca veut dire que tout les élements sont en place
              {
            
               selectedParcelle.setPbaPfr(false);
              // selectedParcelle.setPbaTbfCode(typebfFacade.find("TBF02")); 
               parcelleBafonFacade.edit(selectedParcelle);
               
               intervenirSelect.setInvNumero(intervenirFacadeFacade.genererNumIntervenir());
               intervenirSelect.setInvIntNumero(intervenantSelect);
               intervenirSelect.setInvPbaNumero(selectedParcelle);
               intervenirFacadeFacade.edit(intervenirSelect);
               
               for( TDroitExerce tde: listeTypedexercesAdminUneParcel)
                {
                    tde.setDreCat("DE");
                    tde.setDreCode(droitExerceFacade.genererNumDroitExerce());
                    tde.setDreDateDebut(new Date());
                    tde.setDreIntNumero(intervenantSelect);
                    tde.setDrePbaNumero(selectedParcelle);
                    tde.setDreRolCode(intervenirSelect.getInvRolCode());
                    
                    droitExerceFacade.create(tde);
                }
               
                  /*
               parcelleTypeBf = new TParcelleTypeBf();
               parcelleTypeBf.setPatyCode("111");
               parcelleTypeBf.setPatyDateDeb(new Date());
               parcelleTypeBf.setPatyPbaNumero(selectedParcelle);
               parcelleTypeBf.setPatyTbfCode(selectedParcelle.getPbaTbfCode());
               parcelleTypeBfFacade.create(parcelleTypeBf);
               */
               
               JsfUtil.addSuccessMessage(ResourceBundle.getBundle("org.vng.ressources.messages_fr").getString("ConfirmationOperation"));
                 
               initialiser();
               
              }
        }catch(Exception ex)
         {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("msg").getString("msg_erreur_enregistrement") );
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
        if(etat)
        {
            if(represente)
                intervenirSelect.setInvRepresente("1");
            else intervenirSelect.setInvRepresente("0");
        }
        
        return etat;
    }
    
    
    
    public void initialiser()
    {
        
        villageSelect = new TVillage();
        arrondissmeentSelect = new TArrondissement();
        typeParcelle = new TTypebf();
       
        setListeVillage(null);                
         selectedParcelle = new TParcelleBafon();        
         intervenantSelect = new TIntervenant();         
         intervenirSelect = new TIntervenir();  
         pvParcelle = new TPvParcelle();
         listeTypedexercesAdminUneParcel = new ArrayList<>();
         listeTypedexercesOPUneParcel = new ArrayList<>();
         listeDroitOperationnel = new ArrayList<>();
         listeLimitrophe = new ArrayList<>();
//         listeParcelAFusionner = new ArrayList<>();
 //        listeParcelScionnee = new ArrayList<>();
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

    

    public TIntervenir getIntervenirSelectItem() {
        return intervenirSelectItem;
    }

    public void setIntervenirSelectItem(TIntervenir intervenirSelectItem) {
        this.intervenirSelectItem = intervenirSelectItem;
    }

    
    
    

    
    
    
    
     
}
