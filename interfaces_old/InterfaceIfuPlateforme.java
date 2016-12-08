/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.interfaces_old;

import bj.finances.cfisc.entities.TCentreImpot;
import bj.finances.cfisc.entities.TParticiper;
import bj.finances.cfisc.entities.TRepUnique;
import bj.finances.cfisc.entities.TTypeContrib;
import bj.finances.cfisc.sessions.TCentreImpotFacade;
import bj.finances.cfisc.sessions.TRepUniqueFacade;
import bj.finances.cfisc.sessions.TTypeContribFacade;
import bj.finances.cfisc.sessions.TParticiperFacade;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
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

    @Inject
    private TTypeContribFacade tTypeContribFacade;
    @Inject
    private TCentreImpotFacade tCentreImpotFacade;
    @Inject
    private TRepUniqueFacade tRepUniqueFacade; 
    
    @Inject
    private TParticiperFacade tParticiperFacade; 
    

     //private Path path = Paths.get("C:\\Users\\SANNI Emmanuel\\Documents\\cfiscal_local");
    private String cheminDepotLocal = "C:/cfiscal_local";
    private String cheminDossierEchecs = "C:/cfiscal_local/echecs";
    private String cheminDossierSucces = "C:/cfiscal_local/succes";
    private String cheminFichierXsd = "C:\\CONTRIBUABLE.xsd";

    //@Schedule(dayOfWeek = "*", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "0", persistent = false)
    public void consommerFichierEntreprise() {
        try {
                System.out.println(" Début mise à jour de la table ifu : " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()) );
                scrutelocal();
            
        } catch (SAXException | JDOMException  ex) {
            Logger.getLogger(InterfaceIfuPlateforme.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(IOException io){
            
        }
    }

    public void scrutelocal() throws JDOMException, SAXException, IOException {

        File depotLocal = new File(cheminDepotLocal);
        File dossierEchecs = new File(cheminDossierEchecs);
        SchemaFactory schemafac = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemafac.newSchema(new File(cheminFichierXsd));
        XMLReaderJDOMFactory factory = new XMLReaderSchemaFactory(schema);
        SAXBuilder builder = new SAXBuilder(factory);
        File[] listeFichier = depotLocal.listFiles();
        for (File f : listeFichier) {
            if (f.isDirectory()) {
                continue;
            }
            Document document = null;
            InputStream in = null;
            try {
                in = new FileInputStream(f);
                document = (Document) builder.build(in);
                traitementDeDonnesCont(document,f);
//                traitementDeDonnesPart(document,f);
            } catch (Exception e) {
                System.err.println("FICHIER NON VALIDE " + e.getMessage());
                e.printStackTrace();
                try {in.close();} catch (IOException ex) {}
                f.renameTo(new File(dossierEchecs, f.getName()));
            }

        }
    }

    public void traitementDeDonnesCont(Document document, File fichier) {
        //System.err.println("QUE SE PASSE T IL LES GARS!!!!!");
        Element racine = document.getRootElement();
        //System.err.println("CA DONNE = " + racine.getName());
        Element operation = racine.getChild("OPERATION");
        Element typeOperation = operation.getChild("TYPEOP");
        Element contribuable = racine.getChild("CONT");
        Element centreImpot = racine.getChild("CENTRE_IMPOT");
        Element contCode = racine.getChild("CONT_CODE");
        
        TCentreImpot tt = tCentreImpotFacade.find(centreImpot.getChild("CENTR_IMP_CODE").getValue());
        
       if(tt == null){
           tt = new TCentreImpot();           
           tt.setCentrImpCode(centreImpot.getChild("CENTR_IMP_CODE").getValue());
           tt.setCentrImpLibelle(centreImpot.getChild("CENTR_IMP_LIBELLE").getValue());           
           tCentreImpotFacade.create(tt);
       }
        
        if (operation != null && "A".equals(typeOperation.getValue())) {
            System.out.println("c'est un ajout");
            InsertIFU(contribuable, fichier);
        }
        if (operation != null && "M".equals(typeOperation.getValue())) {
            majIFU(contribuable, fichier);
            System.out.println("c'est une modification");
            
            
             //majIFU(contribuable, fichier);
             
        }
    }

    public void setAttribut(Object object, String champ) {

    }
   
////////////////////////méthode d'insertion dans la table t_participer à partir des fichiers xml///////
   public void InsertParticiper(Element participer, File fichier){
   
       File depotLocal = new File(cheminDepotLocal);
        File dossierEchecs = new File(cheminDossierEchecs);
        System.out.println("Je suis dans l'insertion de participation");
       // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        TParticiper tParticiper = new TParticiper();
        TRepUnique tRepUnique = new TRepUnique();
        
        try {tParticiper.setPartContImmatr(participer.getChild("CONT_IFU_ENTREPRISE").getValue());} catch (Exception e) {};
        try {tParticiper.setPartContImmatrCont(participer.getChild("CONT_IFU_ASSOCIER").getValue());} catch (Exception e) {};
        try {tParticiper.setPartNum(Long.parseLong(participer.getChild("PART_NUM").getValue()));} catch (Exception e) {};
    
        // gestion ifu Etablissement 
        
        String ifuEt = participer.getChild("CONT_IFU_ENTREPRISE").getValue();
        
        if(ifuEt.substring(1).equals("1") || ifuEt.substring(1).equals("2")){
            
            String raisSoc = participer.getChild("RAIS_SOCIALE").getValue();
            String nomLong = participer.getChild("NOM_LONG").getValue();
            
            
            tRepUnique = tRepUniqueFacade.find(ifuEt);
            
            if (!(tRepUnique == null)){
                tRepUnique.setContRais(raisSoc);
                tRepUnique.setContNomLong(nomLong);
                
                tRepUniqueFacade.edit(tRepUnique);
            }else {
                
                fichier.renameTo(new File(dossierEchecs, fichier.getName()));
            }
      
        }
        
        //fin gestion Etablissement
        
        tParticiperFacade.create(tParticiper);
        
        fichier.renameTo(new File(cheminDossierSucces, fichier.getName()));
        System.out.println("------------ FIN AJOUT----------");

   } 
  
////////////////////////méthode fin d'insertion dans la table t_participer à partir des fichiers xml///////
   
////////////////////////méthode d'insertion dans la table t_participer à partir des fichiers xml///////
   public void majParticiper(Element participer, File fichier){
   
        System.out.println("Je suis dans la maj de participation");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        TParticiper tParticiper = new TParticiper();
        TRepUnique tRepUnique = new TRepUnique();
        
        try {tParticiper.setPartContImmatr(participer.getChild("CONT_IFU_ENTREPRISE").getValue());} catch (Exception e) {};
        try {tParticiper.setPartContImmatrCont(participer.getChild("CONT_IFU_ASSOCIER").getValue());} catch (Exception e) {};
        try {tParticiper.setPartNum(Long.parseLong(participer.getChild("PART_NUM").getValue()));} catch (Exception e) {};
    
        // gestion ifu Etablissement 
        
        String ifuEt = participer.getChild("CONT_IFU_ENTREPRISE").getValue();
        
        if(ifuEt.substring(1).equals("1") || ifuEt.substring(1).equals("2")){
            
            String raisSoc = participer.getChild("RAIS_SOCIALE").getValue();
            String nomLong = participer.getChild("NOM_LONG").getValue();
            
            
            tRepUnique = tRepUniqueFacade.find(ifuEt);
            
            if (!(tRepUnique == null)){
                tRepUnique.setContRais(raisSoc);
                tRepUnique.setContNomLong(nomLong);
                
                tRepUniqueFacade.edit(tRepUnique);
            }
        }
        
        //MAJ ancien ifu promoteur
            String old_ifu = participer.getChild("OLD_IFU_ASSOCIER").getValue();
            tRepUnique = tRepUniqueFacade.find(ifuEt);
            if (!(tRepUnique == null)){
                tRepUnique.setContRais("");
                tRepUnique.setContNomLong("");
                
                tRepUniqueFacade.edit(tRepUnique);
            }
            
            // fin MAJ ancien ifu
        
        //fin gestion Etablissement
        
        tParticiperFacade.create(tParticiper);
        
        fichier.renameTo(new File(cheminDossierSucces, fichier.getName()));
        System.out.println("------------ FIN MAJ ----------");

   } 
    
   
   
   
////////////////////////méthode fin de maj de la table t_participer à partir des fichiers xml///////
   
   
    
    ////////////////////////méthode d'insertion dans la table t_rep_unique à partir des fichiers xml///////
    public void InsertIFU(Element contribuable,File fichier) {
        System.out.println("Je suis dans l'insertion");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        TRepUnique tRepUnique = new TRepUnique();
        
        try {tRepUnique.setContDatenreg(dateFormat.parse(contribuable.getChild("CONT_DATENREG").getValue()));} catch (Exception e) {};
        try {tRepUnique.setContImmatr(Long.parseLong(contribuable.getChild("CONT_IMMATR").getValue()));} catch (Exception e) {};
        tRepUnique.setContNum(contribuable.getChild("CONT_NUM").getValue());
    
        tRepUnique.setContCentrCode(contribuable.getChild("CONT_CENTR_CODE").getValue());
        TTypeContrib tTypeContrib = tTypeContribFacade.find(contribuable.getChild("CONT_TYP_CONT_CODE").getValue());
        tRepUnique.setContTypContCode(tTypeContrib);
        tRepUniqueFacade.create(tRepUnique);
        fichier.renameTo(new File(cheminDossierSucces, fichier.getName()));
        System.out.println("FIN DU PROGRAMME" + tRepUnique.getContDatenreg());

    }

    public void majIFU(Element contribuable,File fichier) { 
        System.out.println("Je suis dans la modif");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        TRepUnique tRepUnique = new TRepUnique();
        
        
        
        try {tRepUnique.setContDatenreg(dateFormat.parse(contribuable.getChild("CONT_DATENREG").getValue()));} catch (Exception e) {};
    }
}
