/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.interfaces;

import bj.finances.cfisc.entities.TCentreImpot;
import bj.finances.cfisc.entities.TRepUnique;
import bj.finances.cfisc.entities.TTypeContrib;
import bj.finances.cfisc.sessions.TCentreImpotFacade;
import bj.finances.cfisc.sessions.TRepUniqueFacade;
import bj.finances.cfisc.sessions.TTypeContribFacade;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.hibernate.annotations.Synchronize;

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
@Startup 
public class InterfaceIfuPlateforme {
    @Inject
    private TTypeContribFacade tTypeContribFacade;
    @Inject
    private TCentreImpotFacade tCentreImpotFacade;
    @Inject
    private TRepUniqueFacade tRepUniqueFacade;
    
     //private Path path = Paths.get("C:\\Users\\SANNI Emmanuel\\Documents\\cfiscal_local");
     
    private String cheminDepotLocal = "C:/cfiscal_local";  
    private String cheminDossierEchecs = "C:/cfiscal_local/echecs";
    private String cheminFichierXsd = "C:\\CONTRIBUABLE.xsd";
    
    
    
//    @Schedule(dayOfWeek = "*", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "0", persistent = false)
//    public void myTimer() {
//        //System.out.println("Timer event TOTO : " + new Date());
//        startSFTP() ;
//    }
    
    @Schedule(dayOfWeek = "*", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "*/15", persistent = false)

    public void myTimer2() {
System.out.println("remplir la base de donnees : " + new Date());
         try {
             try {
                 System.out.println("remplir la base de donnees : " + new Date());
                 scrutelocal();
             } catch (SAXException ex) {
                 Logger.getLogger(InterfaceIfuPlateforme.class.getName()).log(Level.SEVERE, null, ex);
             }
         } catch (JDOMException ex) {
             Logger.getLogger(InterfaceIfuPlateforme.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
        
    public void scrutelocal() throws JDOMException, SAXException{

      File depotLocal = new File(cheminDepotLocal);
      File dossierEchecs = new File(cheminDossierEchecs);
      SchemaFactory schemafac =  SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Schema schema = schemafac.newSchema(new File(cheminFichierXsd));
      XMLReaderJDOMFactory factory = new XMLReaderSchemaFactory(schema);
      SAXBuilder builder = new SAXBuilder(factory);
      File[] listeFichier = depotLocal.listFiles();
      //System.err.println("nobre de fichier " + listeFichier.length);
      for(File f : listeFichier){
          if(f.isDirectory()) continue;
          Document document =  null;
          InputStream in = null;
          try{
               in = new FileInputStream(f);
              document = (Document) builder.build(in);
              traitementDeDonnes(document);
          }
          catch(Exception e){
              System.err.println("FICHIER NON VALIDE " + e.getMessage());
              e.printStackTrace();
              System.out.println("tttt");
              try {in.close();} catch (IOException ex) { }
              f.renameTo(new File(dossierEchecs, f.getName()));
          }         

      }
    }
    
   public void traitementDeDonnes(Document document){
       //System.err.println("QUE SE PASSE T IL LES GARS!!!!!");
       Element racine = document.getRootElement();
       //System.err.println("CA DONNE = " + racine.getName());
       Element operation = racine.getChild("OPERATION");
       Element typeOperation = operation.getChild("TYPEOP");
       Element contribuable = racine.getChild("CONT");
       if(operation != null && "A".equals(typeOperation.getValue()) ){
           System.out.println("c'est un ajout");
           InsertIFU(contribuable);
       }
       if(operation != null && "M".equals(typeOperation.getValue()) ){
           System.out.println("c'est une modification");
       }
   } 
   public void setAttribut(Object object, String champ){
       
   }
   public void InsertIFU(Element contribuable){
       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      
       TRepUnique tRepUnique = new TRepUnique();
       
       // affectation des champs de dates
       try{ tRepUnique.setContDatenreg(dateFormat.parse(contribuable.getChild("CONT_DATENREG").getValue())) ;} catch(Exception e){};
       try{ tRepUnique.setContDateimmatr(dateFormat.parse(contribuable.getChild("CONT_DATEIMMATR").getValue())) ;} catch(Exception e){};
       try{ tRepUnique.setContDatenregInsae(dateFormat.parse(contribuable.getChild("CONT_DATENREG_INSAE").getValue())) ;} catch(Exception e){};
       try{ tRepUnique.setContDateCessation(dateFormat.parse(contribuable.getChild("CONT_DATE_CESSATION").getValue())) ;} catch(Exception e){};
       try{ tRepUnique.setContDateDeces(dateFormat.parse(contribuable.getChild("CONT_DATE_DECES").getValue())) ;} catch(Exception e){};
       try{ tRepUnique.setContDateRepriseAct(dateFormat.parse(contribuable.getChild("CONT_DATE_REPRISE_ACT").getValue())) ;} catch(Exception e){};
       try{ tRepUnique.setContDateMajMatricule(dateFormat.parse(contribuable.getChild("CONT_DATE_MAJ_MATRICULE").getValue())) ;} catch(Exception e){};
       
       //affaction des champs de type Long;
       try{ tRepUnique.setContImmatr(Long.parseLong(contribuable.getChild("CONT_IMMATR").getValue()));}catch(Exception e){};       
       try{ tRepUnique.setContLoyer(Long.parseLong(contribuable.getChild("CONT_LOYER").getValue()));}catch(Exception e){}; 
       try{ tRepUnique.setContNewImmatr(Long.parseLong(contribuable.getChild("CONT_NEW_IMMATR").getValue()));}catch(Exception e){};     
      
       tRepUnique.setContNum(contribuable.getChild("CONT_NUM").getValue());
       tRepUnique.setContMatricule(contribuable.getChild("CONT_MATRICULE").getValue());
       tRepUnique.setContNuminsae(contribuable.getChild("CONT_NUMINSAE").getValue());
       tRepUnique.setContNuminsae1(contribuable.getChild("CONT_NUMINSAE1").getValue());
        try{

       
       TTypeContrib tTypeContrib = tTypeContribFacade.find(contribuable.getChild("CONT_TYP_CONT_CODE").getValue());
       if(tTypeContrib != null){ 
           System.out.println("j ai trouve 1 " + tTypeContrib.getTypContLib());
       }
            System.out.println("GGGGGGGGGGGGGGGGGGGGGGG  " + contribuable.getChild("CONT_CENTR_CODE").getValue());
       TCentreImpot tCentreImpot = tCentreImpotFacade.find(contribuable.getChild("CONT_CENTR_CODE").getValue());
       if(tCentreImpot != null){
           System.out.println("j ai trouve 2" + tCentreImpot.getCentrImpLibelle());
       }
       
       
       tRepUnique.setContCentrImpCode( tCentreImpot);
       tRepUnique.setContTypContCode(tTypeContrib);
            System.out.println("AZERTY              " + tRepUnique.getContTypContCode().getTypContLib());
       
            tRepUniqueFacade.create(tRepUnique);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
       }catch(Exception e){
           System.out.println("TETETETETET " + e.getMessage());
           e.printStackTrace();
       }
       System.out.println("MANOU " + tRepUnique.getContDatenreg());

   }
   
   public void majIFU(){
   
   }
   
   

    public void startSFTP() {
    String SFTPUSER = "cfisc";
    String SFTPHOST = "10.3.37.219";
    String SFTPPASS = "123";
    int SFTPPORT = 22;
    JSch jsch = new JSch();
    try {
        Session session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
        session.setPassword(SFTPPASS);

        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();

        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp channelSftp = (ChannelSftp) channel;
        channelSftp.lcd(cheminDepotLocal);

        Vector<ChannelSftp.LsEntry> list = channelSftp.ls("*.xml");
       
        for (ChannelSftp.LsEntry entry : list) {
            channelSftp.get(entry.getFilename(), entry.getFilename());
            channelSftp.rename(entry.getFilename(), "/fichier_traite/" + entry.getFilename());
            System.out.println(entry.getFilename());
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

}
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
