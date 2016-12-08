/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.interfaces;

import bj.finances.cfisc.entities.TCentreImpot;
import bj.finances.cfisc.entities.TParticiper;
import bj.finances.cfisc.entities.TRepUnique;
import bj.finances.cfisc.entities.TTypeContrib;
import bj.finances.cfisc.entities.TDeclarationDou;
import bj.finances.cfisc.entities.TArticle;
import bj.finances.cfisc.entities.TArticlePK;
import bj.finances.cfisc.entities.TTaxeDeclaration;
import bj.finances.cfisc.entities.TTaxesDou;
import bj.finances.cfisc.entities.TTaxesDouPK;
import bj.finances.cfisc.sessions.TCentreImpotFacade;
import bj.finances.cfisc.sessions.TDeclarationDouFacade;
import bj.finances.cfisc.sessions.TParticiperFacade;
import bj.finances.cfisc.sessions.TRepUniqueFacade;
import bj.finances.cfisc.sessions.TTypeContribFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderSchemaFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author SANNI Emmanuel
 */
@Stateless
public class InterfaceIfuPlateforme {
    @EJB
    private TDeclarationDouFacade tDeclarationDouaneFacade;

    @Inject
    private TTypeContribFacade tTypeContribFacade;
    @Inject
    private TCentreImpotFacade tCentreImpotFacade;
    @Inject
    private TRepUniqueFacade tRepUniqueFacade;   
       
    @Inject
    private TParticiperFacade tParticiperFacade; 
    
    @Inject
    private TDeclarationDouFacade tDeclarationDouane; 

    

     //private Path path = Paths.get("C:\\Users\\SANNI Emmanuel\\Documents\\cfiscal_local");
    private String cheminDepotLocal = "C:/cfiscal_local";
    private String cheminDossierEchecs = "C:/cfiscal_local/echecs";
    private String cheminDossierSucces = "C:/cfiscal_local/succes";
    private String cheminFichierXsd = "C:\\CONTRIBUABLE.xsd";
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    //@Schedule(dayOfWeek = "*", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "0", persistent = false)
    public void consommerFichierEntreprise() {
        try {
                System.out.println(" Début mise à jour de la table ifu : " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()) );
                scrutelocal();
            
        } catch (Exception ex) {
            Logger.getLogger(InterfaceIfuPlateforme.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void scrutelocal(){
        File depotLocal = new File(cheminDepotLocal);
        File dossierEchecs = new File(cheminDossierEchecs);
        File[] listeFichier = depotLocal.listFiles();
        
        for (File f : listeFichier) {

            if (f.isDirectory()) {
                continue;
            }
            Document document = null;
            InputStream in = null;
            Schema schema = null;
            
            try {
                System.out.println(f.getName().substring(0,4));
                if (f.getName().substring(0,4).equals("CONT")){
                SchemaFactory schemafac = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                schema = schemafac.newSchema(new File(cheminFichierXsd));
                XMLReaderJDOMFactory factory = new XMLReaderSchemaFactory(schema);
                SAXBuilder builder = new SAXBuilder(factory);
                in = new FileInputStream(f);
                document = (Document) builder.build(in);
                traitementDeDonnesCont(document,f, in);
                }
                else if (f.getName().substring(0,4).equals("PART")){
                    
                    in = new FileInputStream(f);
                    SAXBuilder builder = new SAXBuilder();
                    document = (Document) builder.build(in);
                    traitementDeDonnesPart(document,f, in);
                }    else if (f.getName().substring(0,4).equals("DECL")){
                    in = new FileInputStream(f);
                    SAXBuilder builder = new SAXBuilder();
                    document = (Document) builder.build(in);
                    TraitementDonneesDouane(document,f, in);
                }
                
                
            }
            catch(JDOMException|SAXException j){
                System.err.println("echec de validation du fichier : (" + j.getMessage() + ")");
                //j.printStackTrace();
                try {in.close();} catch (IOException ex) {}
                f.renameTo(new File(dossierEchecs, f.getName()));
            }
            catch(IOException i){
                System.err.println("Problème de lecture du fichier : (" + i.getMessage() + ")");
                //i.printStackTrace(); 
                try {in.close();} catch (IOException ex) {}
                f.renameTo(new File(dossierEchecs, f.getName()));
            }
            catch(Exception ex){
                System.err.println("Une excception inconnue a été générée : (" + ex.getMessage() + ")");
                //ex.printStackTrace();      
                try {in.close();} catch (IOException ioe) {}
                f.renameTo(new File(dossierEchecs, f.getName()));
            }
            finally{
                
            }

        }
    }

    public void traitementDeDonnesCont(Document document, File fichier, InputStream in) throws JDOMException, SAXException, IOException  {
        Element racine = document.getRootElement();
        //System.err.println("CA DONNE = " + racine.getName());
        Element operation = racine.getChild("OPERATION");

        Element typeOperation = operation.getChild("TYPEOP");
        System.out.println("sanni 1");
        Element contribuable = racine.getChild("CONT");
        System.out.println("sanni 2");
        Element centreImpot = racine.getChild("CENTRE_IMPOT");
        System.out.println("sanni 3");
        Element contCode = racine.getChild("CONT_CODE");
        System.out.println("sanni 4");
        TCentreImpot tCentreImpot = tCentreImpotFacade.find(centreImpot.getChild("CENTR_IMP_CODE").getValue());
        
        if(tCentreImpot == null){
            tCentreImpot = new TCentreImpot();           
            tCentreImpot.setCentrImpCode(centreImpot.getChild("CENTR_IMP_CODE").getValue());
            tCentreImpot.setCentrImpLibelle(centreImpot.getChild("CENTR_IMP_LIBELLE").getValue());           
            tCentreImpotFacade.create(tCentreImpot);
        }
        
        TTypeContrib ttrContrib = tTypeContribFacade.find(contCode.getChild("TYP_CONT_CODE").getValue());
        if(ttrContrib == null){
            ttrContrib = new TTypeContrib(contCode.getChild("TYP_CONT_CODE").getValue(), contCode.getChild("TYP_CONT_LIB").getValue());
            tTypeContribFacade.create(ttrContrib);
        }
        
        TRepUnique tRepUnique = tRepUniqueFacade.find(contribuable.getChild("CONT_IMMATR").getValue());
        if( tRepUnique ==  null ){
            if( "A".equals(typeOperation.getValue())){
                System.out.println("c'est un ajout");
                tRepUnique = new TRepUnique();
                try {tRepUnique.setContDatenreg(dateFormat.parse(contribuable.getChild("CONT_DATENREG").getValue()));} catch (Exception e) {};
                try {tRepUnique.setContImmatr(Long.parseLong(contribuable.getChild("CONT_IMMATR").getValue()));} catch (Exception e) {};
                tRepUnique.setContNum(contribuable.getChild("CONT_NUM").getValue());
                tRepUnique.setContCentrCode(contribuable.getChild("CONT_CENTR_CODE").getValue());
               // TTypeContrib tTypeContrib = tTypeContribFacade.find(contribuable.getChild("CONT_TYP_CONT_CODE").getValue());
                tRepUnique.setContTypContCode(ttrContrib);
                tRepUniqueFacade.create(tRepUnique);
                in.close();
                fichier.renameTo(new File(cheminDossierSucces, fichier.getName()));
            }
            else{  
                /////// code ajouter par aurince ce 06/12/2016///////////////
                /////////////////////Contribuable inexistant dans t_rep_unique mais opération M, on ajoute alors///////
                if( "M".equals(typeOperation.getValue())){
                System.out.println("c'est un ajout");
                tRepUnique = new TRepUnique();
                try {tRepUnique.setContDatenreg(dateFormat.parse(contribuable.getChild("CONT_DATENREG").getValue()));} catch (Exception e) {};
                try {tRepUnique.setContImmatr(Long.parseLong(contribuable.getChild("CONT_IMMATR").getValue()));} catch (Exception e) {};
                tRepUnique.setContNum(contribuable.getChild("CONT_NUM").getValue());
                tRepUnique.setContCentrCode(contribuable.getChild("CONT_CENTR_CODE").getValue());
               // TTypeContrib tTypeContrib = tTypeContribFacade.find(contribuable.getChild("CONT_TYP_CONT_CODE").getValue());
                tRepUnique.setContTypContCode(ttrContrib);
                tRepUniqueFacade.create(tRepUnique);
                in.close();
                fichier.renameTo(new File(cheminDossierSucces, fichier.getName()));
                }
                //////////////////////
               // in.close();
                //fichier.renameTo(new File(cheminDossierEchecs, fichier.getName()));
            }          
            return;
        }
        else{
            if( "M".equals(typeOperation.getValue())){
                System.out.println("Je suis dans lA MODIF");
                try {tRepUnique.setContDatenreg(dateFormat.parse(contribuable.getChild("CONT_DATENREG").getValue()));} catch (Exception e) {};
                try {tRepUnique.setContImmatr(Long.parseLong(contribuable.getChild("CONT_IMMATR").getValue()));} catch (Exception e) {};
                tRepUnique.setContNum(contribuable.getChild("CONT_NUM").getValue());

                tRepUnique.setContCentrCode(contribuable.getChild("CONT_CENTR_CODE").getValue());
                TTypeContrib tTypeContrib = tTypeContribFacade.find(contribuable.getChild("CONT_TYP_CONT_CODE").getValue());
                tRepUnique.setContTypContCode(tTypeContrib);
                tRepUniqueFacade.edit(tRepUnique);
                in.close();
                fichier.renameTo(new File(cheminDossierSucces, fichier.getName()));            
            }
            else{
                ////////////////////////////////////
                
                
                in.close();
                fichier.renameTo(new File(cheminDossierEchecs, fichier.getName()));
            }
            return;
        }
    }
    
    public void traitementDeDonnesPart(Document document, File fichier, InputStream in) throws JDOMException, SAXException, IOException  {
        
        Element racine = document.getRootElement();
        
        Element operation = racine.getChild("OPERATION");

        Element typeOperation = operation.getChild("TYPEOP");    
        
        if (typeOperation.getValue().equals("A")){
            InsertParticiper(racine, fichier);
        }
        else if (typeOperation.getValue().equals("M")) {
            majParticiper(racine, fichier);
        }   
        
    }
    
    ////////////////////////méthode d'insertion dans la table t_participer à partir des fichiers xml///////
   public void InsertParticiper(Element participer, File fichier){
   
        System.out.println("Je suis dans l'insertion de participation");
       // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        TParticiper tParticiper = new TParticiper();
        TRepUnique tRepUnique = new TRepUnique();
        
        try {tParticiper.setPartContImmatr(participer.getChild("PART").getChild("CONT_IFU_ENTREPRISE").getValue());} catch (Exception e) {};
        try {tParticiper.setPartContImmatrCont(participer.getChild("PART").getChild("CONT_IFU_ASSOCIER").getValue());} catch (Exception e) {};
        try {tParticiper.setPartNum(Long.parseLong(participer.getChild("PART").getChild("PART_NUM").getValue()));} catch (Exception e) {};
    
        // gestion ifu Etablissement 
        
        String ifuEt = participer.getChild("PART").getChild("CONT_IFU_ENTREPRISE").getValue();
        String ifuPP = participer.getChild("PART").getChild("CONT_IFU_ASSOCIER").getValue();
        
        if(ifuEt.substring(0,1).equals("1") || ifuEt.substring(0,1).equals("2")){
            
            String raisSoc = participer.getChild("EI").getChild("RAIS_SOCIALE").getValue();
            String nomLong = participer.getChild("EI").getChild("NOM_LONG").getValue();
            
            System.out.println("IFU DE L'ASSOCIER à METTRE AJ "+ifuPP);
            tRepUnique = tRepUniqueFacade.find(Long.parseLong(ifuPP));
            
            if (tRepUnique != null){
                System.out.println("Associer trouvé ");
                tRepUnique.setContRais(raisSoc);
                tRepUnique.setContNomLong(nomLong);
                
                tRepUniqueFacade.edit(tRepUnique);
            }else {
                fichier.renameTo(new File(cheminDossierEchecs, fichier.getName()));
            }
      
        }
        
        tParticiperFacade.create(tParticiper);
        
        fichier.renameTo(new File(cheminDossierSucces, fichier.getName()));
        fichier.delete();
        System.out.println("------------ FIN AJOUT----------");

   } 
  
//////////////////////// fin méthode d'insertion dans la table t_participer à partir des fichiers xml///////
   
////////////////////////méthode de maj dans la table t_participer à partir des fichiers xml///////
   public void majParticiper(Element participer, File fichier){
   
        System.out.println("Je suis dans la maj de participation");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        TParticiper tParticiper = new TParticiper();
        TRepUnique tRepUnique = new TRepUnique();
        
        tParticiper= tParticiperFacade.find(Long.parseLong(participer.getChild("PART").getChild("PART_NUM").getValue()));
        if (tParticiper !=null) {
        try {tParticiper.setPartContImmatr(participer.getChild("PART").getChild("CONT_IFU_ENTREPRISE").getValue());} catch (Exception e) {};
        try {tParticiper.setPartContImmatrCont(participer.getChild("PART").getChild("CONT_IFU_ASSOCIER").getValue());} catch (Exception e) {};
        try {tParticiper.setPartNum(Long.parseLong(participer.getChild("PART").getChild("PART_NUM").getValue()));} catch (Exception e) {};
    
        // gestion ifu Etablissement 
        
        String ifuEt = participer.getChild("PART").getChild("CONT_IFU_ENTREPRISE").getValue();
         String ifuPP = participer.getChild("PART").getChild("CONT_IFU_ASSOCIER").getValue();
       
        if(ifuEt.substring(0,1).equals("1") || ifuEt.substring(0,1).equals("2")){
            
            String raisSoc = participer.getChild("EI").getChild("RAIS_SOCIALE").getValue();
            String nomLong = participer.getChild("EI").getChild("NOM_LONG").getValue();
            
            
               System.out.println("IFU DE L'ASSOCIER à METTRE AJ "+ifuPP);
               tRepUnique = tRepUniqueFacade.find(Long.parseLong(ifuPP));
         
            
            if (tRepUnique != null){
                tRepUnique.setContRais(raisSoc);
                tRepUnique.setContNomLong(nomLong);
                
                tRepUniqueFacade.edit(tRepUnique);
            }
        }
        
        //MAJ ancien ifu promoteur
            String old_ifu = participer.getChild("OLD_IFU").getChild("OLD_IFU_ASSOCIER").getValue();
            tRepUnique = tRepUniqueFacade.find(Long.parseLong(old_ifu));
            System.out.println("IFU de l'Ancien associer "+old_ifu);
            if (tRepUnique != null){
                tRepUnique.setContRais("");
                tRepUnique.setContNomLong("");               
                tRepUniqueFacade.edit(tRepUnique);
            }
            
            // fin MAJ ancien ifu
        
        //fin gestion Etablissement
        
        tParticiperFacade.edit(tParticiper);
        
        fichier.renameTo(new File(cheminDossierSucces, fichier.getName()));
           fichier.delete();
        System.out.println("------------ FIN MAJ ----------");
        }
   } 
   //////////////////////// méthode fin de maj de la table t_participer à partir des fichiers xml///////

   /////////////////////// METHODE INSERTION DECLARATION SYDONIA /////////////////////////
   public void TraitementDonneesDouane(Document document, File fichier, InputStream in) throws JDOMException, SAXException, IOException {
   
        System.out.println("Je suis dans l'insertion de déclaration article taxe");
        
        Element declaration = document.getRootElement();
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        TDeclarationDou Tdeclaration = new TDeclarationDou();
        TArticle tarcticle = new TArticle();   
        TTaxesDou taxes_mtant = new TTaxesDou();
        List<TArticle> listeArticles = null;
        List<TTaxesDou> listeTaxesArticles = null;
        
         Long instance_id = Long.parseLong(declaration.getChild("segment_general").getAttribute("INSTANCE_ID").getValue());
         
         Tdeclaration = tDeclarationDouaneFacade.find(instance_id);
         String tyope = declaration.getChild("typeope").getAttribute("type").getValue();
         
         if (Tdeclaration == null) {
             if ("A".equals(tyope) || "M".equals(tyope)){
         
        //PARTIE DECLARATION
        try {Tdeclaration.setInstanceid(Long.parseLong(declaration.getChild("segment_general").getAttribute("INSTANCE_ID").getValue()));} catch (Exception e) {};
        try {Tdeclaration.setDecRefYer(Long.parseLong(declaration.getChild("segment_general").getAttribute("annee_dec").getValue()));} catch (Exception e) {};
        try {Tdeclaration.setIdeCuoCod(declaration.getChild("segment_general").getAttribute("bureau_dec").getValue());} catch (Exception e) {};
        try {Tdeclaration.setDecCod(declaration.getChild("segment_general").getAttribute("dec_code").getValue());} catch (Exception e) {};
        try {Tdeclaration.setDecRefNbr(declaration.getChild("segment_general").getAttribute("repertoire_dec").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdeRegSer(declaration.getChild("segment_general").getAttribute("reg_serial").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdeRegNbr(declaration.getChild("segment_general").getAttribute("reg_nber").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdeRegDat(dateFormat.parse(declaration.getChild("segment_general").getAttribute("date_enreg").getValue()));} catch (Exception e) {};
        try {Tdeclaration.setIdeAstSer(declaration.getChild("segment_general").getAttribute("ast_serial").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdeAstNbr(declaration.getChild("segment_general").getAttribute("ast_nbr").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdeTypSad(declaration.getChild("segment_general").getAttribute("ide_typ_sad").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdeTypPrc(declaration.getChild("segment_general").getAttribute("ide_typ_proc").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdeMan(declaration.getChild("segment_general").getAttribute("manifest").getValue());} catch (Exception e) {};
        try {Tdeclaration.setTptLoc(declaration.getChild("segment_general").getAttribute("localisation").getValue());} catch (Exception e) {};
        try {Tdeclaration.setPtyNbrItm(Long.parseLong(declaration.getChild("segment_general").getAttribute("nbr_total_art").getValue()));} catch (Exception e) {};
        try {Tdeclaration.setPtyNbrPck(Long.parseLong(declaration.getChild("segment_general").getAttribute("nbr_total_colis").getValue()));} catch (Exception e) {};
        try {Tdeclaration.setCmpExpCod(declaration.getChild("segment_general").getAttribute("ifu_exportateur").getValue());} catch (Exception e) {};
        try {Tdeclaration.setCmpConCod(declaration.getChild("segment_general").getAttribute("ifu_importateur").getValue());} catch (Exception e) {};
        try {Tdeclaration.setDecCod(declaration.getChild("segment_general").getAttribute("dec_code").getValue());} catch (Exception e) {};
        try {Tdeclaration.setDecNam(declaration.getChild("segment_general").getAttribute("nom_declarant").getValue());} catch (Exception e) {};
        try {Tdeclaration.setFinAmtDty(BigInteger.valueOf(Long.parseLong(declaration.getChild("segment_general").getAttribute("total_liq").getValue())));} catch (Exception e) {};
        try {Tdeclaration.setFinAmtTbp(BigInteger.valueOf(Long.parseLong(declaration.getChild("segment_general").getAttribute("total_a_payer").getValue())));} catch (Exception e) {};
        try {Tdeclaration.setIdeRcpSer(declaration.getChild("segment_general").getAttribute("ser_quittance").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdeRcpNbr(declaration.getChild("segment_general").getAttribute("num_quittance").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdePstNbr(declaration.getChild("segment_general").getAttribute("num_version").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdePstDat(dateFormat.parse(declaration.getChild("segment_general").getAttribute("ifu_importateur").getValue()));} catch (Exception e) {};
        try {Tdeclaration.setGenCtyDesCod(declaration.getChild("segment_general").getAttribute("cty_destcod").getValue());} catch (Exception e) {};
        try {Tdeclaration.setTptMotBrdCod(declaration.getChild("segment_general").getAttribute("mot_bord").getValue());} catch (Exception e) {};
        try {Tdeclaration.setTptMotInl(declaration.getChild("segment_general").getAttribute("mot_inland").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdeCuoCod(declaration.getChild("segment_general").getAttribute("cuo_bord").getValue());} catch (Exception e) {};
        try {Tdeclaration.setFinAmtDty(BigInteger.valueOf(Long.parseLong(declaration.getChild("segment_general").getAttribute("Valeur_en_douane").getValue())));} catch (Exception e) {};
        try {Tdeclaration.setTptCtf(Short.parseShort(declaration.getChild("segment_general").getAttribute("Indicateur_Conteneur").getValue()));} catch (Exception e) {};
        //FIN PARTIE DECLARATION
        
        //PARTIE ARTICLE
            //construction cle primaire TaxeDeclaration
            
            Long itm_nbr = Long.parseLong(declaration.getChild("segment_general").getChild("article").getAttribute("KEY_ITM_NBR").getValue());
            //fin
        
            
            
        try {tarcticle.setTArticlePK(new TArticlePK(instance_id,itm_nbr));} catch (Exception e) {};        
        try {tarcticle.setLnkTpt(declaration.getChild("segment_general").getChild("article").getAttribute("LNK_TPT").getValue());} catch (Exception e) {};
        try {tarcticle.setTarPrcExt(declaration.getChild("segment_general").getChild("article").getAttribute("TAR_PRC_EXT").getValue());} catch (Exception e) {};
        try {tarcticle.setTarPrcNat(declaration.getChild("segment_general").getChild("article").getAttribute("TAR_PRC_NAT").getValue());} catch (Exception e) {};
        try {tarcticle.setTarHscNb1(declaration.getChild("segment_general").getChild("article").getAttribute("nomenclature").getValue());} catch (Exception e) {};
        try {tarcticle.setGdsDsc(declaration.getChild("segment_general").getChild("article").getAttribute("GDS_DSC").getValue());} catch (Exception e) {};
        try {tarcticle.setPckMrk1(declaration.getChild("segment_general").getChild("article").getAttribute("PCK_MRK1").getValue());} catch (Exception e) {};
        try {tarcticle.setPckMrk2(declaration.getChild("segment_general").getChild("article").getAttribute("PCK_MRK2").getValue());} catch (Exception e) {};
        try {tarcticle.setPckTypCod(declaration.getChild("segment_general").getChild("article").getAttribute("PCK_TYP_COD").getValue());} catch (Exception e) {};
        try {tarcticle.setVitStv(BigInteger.valueOf(Long.parseLong(declaration.getChild("segment_general").getChild("article").getAttribute("PCK_MRK2").getValue())));} catch (Exception e) {};
        try {tarcticle.setGdsOrgCty(declaration.getChild("segment_general").getChild("article").getAttribute("GDS_ORG_CTY").getValue());} catch (Exception e) {};
        try {tarcticle.setVitWgtGrs(BigInteger.valueOf(Long.parseLong(declaration.getChild("segment_general").getChild("article").getAttribute("VIT_WGT_GRS").getValue())));} catch (Exception e) {};
        try {tarcticle.setVitWgtNet(BigInteger.valueOf(Long.parseLong(declaration.getChild("segment_general").getChild("article").getAttribute("VIT_WGT_NET").getValue())));} catch (Exception e) {};
        try {tarcticle.setTaxAmt(BigInteger.valueOf(Long.parseLong(declaration.getChild("segment_general").getChild("article").getAttribute("TAX_AMT").getValue())));} catch (Exception e) {};
        //FIN PARTIE ARTICLE
        
        //PARTIE TAXE
            //construction cle primaire TaxeDeclaration        
             Long tax_rnk = Long.parseLong(declaration.getChild("segment_general").getChild("article").getChild("taxe").getAttribute("KEY_TAX_RNK").getValue());
             
            //fin
        
        try {taxes_mtant.setTTaxesDouPK(new TTaxesDouPK(instance_id, tax_rnk));} catch (Exception e) {};
        try {taxes_mtant.setTaxCod(declaration.getChild("segment_general").getChild("article").getChild("taxe").getAttribute("TAX_LIN_COD").getValue());} catch (Exception e) {};
        try {taxes_mtant.setTaxAmt(BigInteger.valueOf(Long.parseLong(declaration.getChild("segment_general").getChild("article").getChild("taxe").getAttribute("TAX_LIN_COD").getValue())));} catch (Exception e) {};
        //FIN PARTIE TAXE
        
        
//        //DEBUT TRANSACTION
//        
//        EntityTransaction myTransaction = tParticiperFacade.em.getTransaction();
//        
//        try{
//        myTransaction.begin();
//        
//        tDeclarationDouaneFacade.create(Tdeclaration);
//        tArticleFacade.create(tarcticle);
//        tTaxesdouaneFacade.create(taxes_mtant);
//        
//        myTransaction.commit();     
//        
//        }catch(IllegalStateException ex){System.out.println("Insert - Une transaction est déjà active ou aucune transaction active." + ex.getMessage());}
//     //FIN TRANSACTION
        
        //ARCHIVAGE FICHIER
        in.close();
        fichier.renameTo(new File(cheminDossierSucces, fichier.getName()));
        //FIN ARCHIVAGE FICHIER
         System.out.println("------------ FIN AJOUT----------");
     
        }
     
       }
         else{
             if("M".equals(tyope)){
                 //CAS DE MODIF NORMAL
                 
                 //PARTIE DECLARATION
        try {Tdeclaration.setInstanceid(Long.parseLong(declaration.getChild("segment_general").getAttribute("INSTANCE_ID").getValue()));} catch (Exception e) {};
        try {Tdeclaration.setDecRefYer(Long.parseLong(declaration.getChild("segment_general").getAttribute("annee_dec").getValue()));} catch (Exception e) {};
        try {Tdeclaration.setIdeCuoCod(declaration.getChild("segment_general").getAttribute("bureau_dec").getValue());} catch (Exception e) {};
        try {Tdeclaration.setDecCod(declaration.getChild("segment_general").getAttribute("dec_code").getValue());} catch (Exception e) {};
        try {Tdeclaration.setDecRefNbr(declaration.getChild("segment_general").getAttribute("repertoire_dec").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdeRegSer(declaration.getChild("segment_general").getAttribute("reg_serial").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdeRegNbr(declaration.getChild("segment_general").getAttribute("reg_nber").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdeRegDat(dateFormat.parse(declaration.getChild("segment_general").getAttribute("date_enreg").getValue()));} catch (Exception e) {};
        try {Tdeclaration.setIdeAstSer(declaration.getChild("segment_general").getAttribute("ast_serial").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdeAstNbr(declaration.getChild("segment_general").getAttribute("ast_nbr").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdeTypSad(declaration.getChild("segment_general").getAttribute("ide_typ_sad").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdeTypPrc(declaration.getChild("segment_general").getAttribute("ide_typ_proc").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdeMan(declaration.getChild("segment_general").getAttribute("manifest").getValue());} catch (Exception e) {};
        try {Tdeclaration.setTptLoc(declaration.getChild("segment_general").getAttribute("localisation").getValue());} catch (Exception e) {};
        try {Tdeclaration.setPtyNbrItm(Long.parseLong(declaration.getChild("segment_general").getAttribute("nbr_total_art").getValue()));} catch (Exception e) {};
        try {Tdeclaration.setPtyNbrPck(Long.parseLong(declaration.getChild("segment_general").getAttribute("nbr_total_colis").getValue()));} catch (Exception e) {};
        try {Tdeclaration.setCmpExpCod(declaration.getChild("segment_general").getAttribute("ifu_exportateur").getValue());} catch (Exception e) {};
        try {Tdeclaration.setCmpConCod(declaration.getChild("segment_general").getAttribute("ifu_importateur").getValue());} catch (Exception e) {};
        try {Tdeclaration.setDecCod(declaration.getChild("segment_general").getAttribute("dec_code").getValue());} catch (Exception e) {};
        try {Tdeclaration.setDecNam(declaration.getChild("segment_general").getAttribute("nom_declarant").getValue());} catch (Exception e) {};
        try {Tdeclaration.setFinAmtDty(BigInteger.valueOf(Long.parseLong(declaration.getChild("segment_general").getAttribute("total_liq").getValue())));} catch (Exception e) {};
        try {Tdeclaration.setFinAmtTbp(BigInteger.valueOf(Long.parseLong(declaration.getChild("segment_general").getAttribute("total_a_payer").getValue())));} catch (Exception e) {};
        try {Tdeclaration.setIdeRcpSer(declaration.getChild("segment_general").getAttribute("ser_quittance").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdeRcpNbr(declaration.getChild("segment_general").getAttribute("num_quittance").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdePstNbr(declaration.getChild("segment_general").getAttribute("num_version").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdePstDat(dateFormat.parse(declaration.getChild("segment_general").getAttribute("ifu_importateur").getValue()));} catch (Exception e) {};
        try {Tdeclaration.setGenCtyDesCod(declaration.getChild("segment_general").getAttribute("cty_destcod").getValue());} catch (Exception e) {};
        try {Tdeclaration.setTptMotBrdCod(declaration.getChild("segment_general").getAttribute("mot_bord").getValue());} catch (Exception e) {};
        try {Tdeclaration.setTptMotInl(declaration.getChild("segment_general").getAttribute("mot_inland").getValue());} catch (Exception e) {};
        try {Tdeclaration.setIdeCuoCod(declaration.getChild("segment_general").getAttribute("cuo_bord").getValue());} catch (Exception e) {};
        try {Tdeclaration.setFinAmtDty(BigInteger.valueOf(Long.parseLong(declaration.getChild("segment_general").getAttribute("Valeur_en_douane").getValue())));} catch (Exception e) {};
        try {Tdeclaration.setTptCtf(Short.parseShort(declaration.getChild("segment_general").getAttribute("Indicateur_Conteneur").getValue()));} catch (Exception e) {};
        //FIN PARTIE DECLARATION
        
        //PARTIE ARTICLE
            //construction cle primaire TaxeDeclaration
            
            Long itm_nbr = Long.parseLong(declaration.getChild("segment_general").getChild("article").getAttribute("KEY_ITM_NBR").getValue());
            //fin
        int nbre_articles = declaration.getContentSize();        
        
        
        for (int i = 0; i <= nbre_articles; i++) {
            
        try {tarcticle.setTArticlePK(new TArticlePK(instance_id,itm_nbr));} catch (Exception e) {};        
        try {tarcticle.setLnkTpt(declaration.getChild("segment_general").getChild("article").getAttribute("LNK_TPT").getValue());} catch (Exception e) {};
        try {tarcticle.setTarPrcExt(declaration.getChild("segment_general").getChild("article").getAttribute("TAR_PRC_EXT").getValue());} catch (Exception e) {};
        try {tarcticle.setTarPrcNat(declaration.getChild("segment_general").getChild("article").getAttribute("TAR_PRC_NAT").getValue());} catch (Exception e) {};
        try {tarcticle.setTarHscNb1(declaration.getChild("segment_general").getChild("article").getAttribute("nomenclature").getValue());} catch (Exception e) {};
        try {tarcticle.setGdsDsc(declaration.getChild("segment_general").getChild("article").getAttribute("GDS_DSC").getValue());} catch (Exception e) {};
        try {tarcticle.setPckMrk1(declaration.getChild("segment_general").getChild("article").getAttribute("PCK_MRK1").getValue());} catch (Exception e) {};
        try {tarcticle.setPckMrk2(declaration.getChild("segment_general").getChild("article").getAttribute("PCK_MRK2").getValue());} catch (Exception e) {};
        try {tarcticle.setPckTypCod(declaration.getChild("segment_general").getChild("article").getAttribute("PCK_TYP_COD").getValue());} catch (Exception e) {};
        try {tarcticle.setVitStv(BigInteger.valueOf(Long.parseLong(declaration.getChild("segment_general").getChild("article").getAttribute("PCK_MRK2").getValue())));} catch (Exception e) {};
        try {tarcticle.setGdsOrgCty(declaration.getChild("segment_general").getChild("article").getAttribute("GDS_ORG_CTY").getValue());} catch (Exception e) {};
        try {tarcticle.setVitWgtGrs(BigInteger.valueOf(Long.parseLong(declaration.getChild("segment_general").getChild("article").getAttribute("VIT_WGT_GRS").getValue())));} catch (Exception e) {};
        try {tarcticle.setVitWgtNet(BigInteger.valueOf(Long.parseLong(declaration.getChild("segment_general").getChild("article").getAttribute("VIT_WGT_NET").getValue())));} catch (Exception e) {};
        try {tarcticle.setTaxAmt(BigInteger.valueOf(Long.parseLong(declaration.getChild("segment_general").getChild("article").getAttribute("TAX_AMT").getValue())));} catch (Exception e) {};
        
        //ICI AJOUT LISTE ARTICLES
        
        //FIN PARTIE ARTICLE
        
        //PARTIE TAXE
            //construction cle primaire TaxeDeclaration        
             Long tax_rnk = Long.parseLong(declaration.getChild("segment_general").getChild("article").getChild("taxe").getAttribute("KEY_TAX_RNK").getValue());
             
            //fin
        
        try {taxes_mtant.setTTaxesDouPK(new TTaxesDouPK(instance_id, tax_rnk));} catch (Exception e) {};
        try {taxes_mtant.setTaxCod(declaration.getChild("segment_general").getChild("article").getChild("taxe").getAttribute("TAX_LIN_COD").getValue());} catch (Exception e) {};
        try {taxes_mtant.setTaxAmt(BigInteger.valueOf(Long.parseLong(declaration.getChild("segment_general").getChild("article").getChild("taxe").getAttribute("TAX_LIN_COD").getValue())));} catch (Exception e) {};
        //FIN PARTIE TAXE
        
        }
        
//        //DEBUT TRANSACTION
//        
//        EntityTransaction myTransaction = tParticiperFacade.em.getTransaction();
//        
//        try{
//        myTransaction.begin();
//        
//        tDeclarationDouaneFacade.edit(Tdeclaration);
//        tArticleFacade.edit(tarcticle);
//        tTaxesdouaneFacade.edit(taxes_mtant);
//        
//        myTransaction.commit();     
//        
//        }catch(IllegalStateException ex){System.out.println("Modif - Une transaction est déjà active ou aucune transaction active." + ex.getMessage());}
//     //FIN TRANSACTION
        
        // ARCHIVAGE FICHIER
        in.close();
        fichier.renameTo(new File(cheminDossierSucces, fichier.getName()));
        // ARCHIVAGE FICHIER

         System.out.println("------------ FIN AJOUT----------");
        }
             else if ("A".equals(tyope)){
                 //DEMANDE D'AJOUTER UN ENREGISTREMENT DEJA EXISTANT
                 //ON NE FAIT RIEN                 
                 in.close();
                fichier.renameTo(new File(cheminDossierEchecs, fichier.getName()));
             }
             
    }
         
   } 
   
   ////////////////////////// FIN INSERTION DECLARATION ARTICLE TAXE
   
   
}
