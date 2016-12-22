/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bj.finances.cfisc.interfaces;



import bj.finances.cfisc.sessions.TCentreImpotFacade;
import bj.finances.cfisc.sessions.TRepUniqueFacade;
import bj.finances.cfisc.sessions.TTypeContribFacade;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.File;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;


/**
 *
 * @author SANNI Emmanuel
 */
@Stateless
public class AgentSftpSydo {
    @Inject
    private TTypeContribFacade tTypeContribFacade;
    @Inject
    private TCentreImpotFacade tCentreImpotFacade;
    @Inject
    private TRepUniqueFacade tRepUniqueFacade;  
 
    private String cheminDepotLocal = ResourceBundle.getBundle("/parametres").getString("cheminDepotLocalSydo");
    private String cheminDepotLocalActif = ResourceBundle.getBundle("/parametres").getString("cheminDepotLocalActif");
    
    private String SYDO_SFTPUSER = ResourceBundle.getBundle("/parametres").getString("SYDO_SFTPUSER");
    private String SYDO_SFTPHOST = ResourceBundle.getBundle("/parametres").getString("SYDO_SFTPHOST");
    private String SYDO_SFTPPASS = ResourceBundle.getBundle("/parametres").getString("SYDO_SFTPPASS");
    private int SYDO_SFTPPORT = Integer.parseInt(ResourceBundle.getBundle("/parametres").getString("SYDO_SFTPPORT"));
    
    private String REP_UNI_SFTPUSER = ResourceBundle.getBundle("/parametres").getString("REP_UNI_SFTPUSER");
    private String REP_UNI_SFTPHOST = ResourceBundle.getBundle("/parametres").getString("REP_UNI_SFTPHOST");
    private String REP_UNI_SFTPPASS = ResourceBundle.getBundle("/parametres").getString("REP_UNI_SFTPPASS");
    private int REP_UNI_SFTPPORT = Integer.parseInt(ResourceBundle.getBundle("/parametres").getString("REP_UNI_SFTPPORT"));
    
    @Inject
    private InterfaceIfuPlateforme iifu;
    
    final static org.apache.log4j.Logger logger = Logger.getLogger(AgentSftpSydo.class.getName());

   // @Schedule(dayOfWeek = "*", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "*/20", persistent = false)
    public void telechargerEntreprise() {
        JSch jsch = new JSch();
        Session session = null;
        Channel channel = null;
        logger.info("Scan du dossier Sydo");
        try {
            session = jsch.getSession(SYDO_SFTPUSER, SYDO_SFTPHOST, SYDO_SFTPPORT);
            session.setPassword(SYDO_SFTPPASS);
            
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");            
            session.setConfig(config);            
            try{
            session.connect();
            }
            catch(Exception ex){
                logger.error("Problème de connexion de session" + ex.getMessage());
            }
            
            channel = session.openChannel("sftp");            
            try{
            channel.connect();
            }catch(Exception ex){
                logger.error("Problème de connexion au canal "+ ex.getMessage());
            }
            
            ChannelSftp channelSftp = (ChannelSftp) channel;
            channelSftp.lcd(cheminDepotLocal);

            Vector<ChannelSftp.LsEntry> list = channelSftp.ls("*.xml");
            logger.info("connexion a sydo reussi .................. ");
            for (ChannelSftp.LsEntry entry : list) {                
                channelSftp.get(entry.getFilename(), entry.getFilename());
                System.out.println("nom fichier : " + entry.getFilename());
                channelSftp.rename(entry.getFilename(), "fichier_traite/" + entry.getFilename());
                logger.info("Fichier traité .... " + entry.getFilename());
            }     
        } catch (Exception e) {
           logger.error("Erreur lors du telechargement d'un fichier entreprise par sftp (Sydo)( " + e.getMessage() + ")");
        }finally{
            channel.disconnect();
            session.disconnect();
        }
        
        iifu.scrutelocalSydo(); 

    }
    
    
   // @Schedule(dayOfWeek = "*", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "*/25", persistent = false)
    public void uploadXmlActif() {
        JSch jsch = new JSch();
        Session session = null;
        Channel channel = null;
        
        try {
            session = jsch.getSession(REP_UNI_SFTPUSER, REP_UNI_SFTPHOST, REP_UNI_SFTPPORT);
            session.setPassword(REP_UNI_SFTPPASS);
            
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");            
            session.setConfig(config);            
            try{
            session.connect();
            }
            catch(Exception ex){
                logger.error("Problème de connexion de session" + ex.getMessage());
            }
            
            channel = session.openChannel("sftp");            
            try{
            channel.connect();
            }catch(Exception ex){
                logger.error("Problème de connexion au canal "+ ex.getMessage());
            }
            
            ChannelSftp channelSftp = (ChannelSftp) channel;
            channelSftp.lcd(cheminDepotLocalActif);
            System.out.println("JE SUIS DANS  " + channelSftp.getHome());
                    
            Vector<ChannelSftp.LsEntry> list = channelSftp.ls("*.xml");
            System.out.println("TAILLE DE LA LISTE : " + list.size() );
            logger.info("CONNEXION A REP UNIQUE OK .................. ");
            for (ChannelSftp.LsEntry entry : list) {                
                channelSftp.get(entry.getFilename(), entry.getFilename());
                System.out.println("NOM FICHIER : " + entry.getFilename());
                channelSftp.rename(entry.getFilename(), "fichier_traite/" + entry.getFilename());
                logger.info("Fichier traité .... " + entry.getFilename());
            }     
        } catch (Exception e) {
           logger.error("Erreur lors du deplacement d'un fichier par sftp (Actif)( " + e.getMessage() + ")");
        }finally{
            channel.disconnect();
            session.disconnect();
            envoiActifVersSydo();
        }
        
        
    }
    
    public void envoiActifVersSydo(){
        
        JSch jsch = new JSch();
        Session session = null;
        Channel channel = null;
        
        try {
             session = jsch.getSession(SYDO_SFTPUSER, SYDO_SFTPHOST, SYDO_SFTPPORT);
            session.setPassword(SYDO_SFTPPASS);
            
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");            
            session.setConfig(config);            
            try{
            session.connect();
            }
            catch(Exception ex){
                logger.error("Problème de connexion de session" + ex.getMessage());
            }
            
            channel = session.openChannel("sftp");            
            try{
            channel.connect();
            }catch(Exception ex){
                logger.error("Problème de connexion au canal "+ ex.getMessage());
            }
            
            ChannelSftp channelSftp = (ChannelSftp) channel;
            channelSftp.lcd(cheminDepotLocalActif);
            //channelSftp.put("SYDO_A_3200700098616_2011_01_18_06_37_07.xml");
            File f = new File(cheminDepotLocalActif);
           
            if (f.isDirectory()){
                File[] listeFiles = f.listFiles();
                System.out.println(" TAILLE LISTE ######################## " + listeFiles.length);
                for (File fichier : listeFiles){
                     if (fichier.getName().endsWith(".xml")){
                    System.out.println( " NOM FILLLLLLLLLLLLLLLLLEEEEEEEEEEEEEEEEEEEEEE " + fichier.getName());                   
                    channelSftp.put(fichier.getName());
                    System.out.println(" YYYOOOOOOOOOOOOOOOOOOOOOOOOOOO-----------------6OOOOOOOOOOOOOOO");
                    if(fichier.renameTo(new File(cheminDepotLocalActif + "/fichier_traite/" + fichier.getName()))){
                        System.out.println(" YYYYYYYYYYYYEEEEEEEEEEEEEEEEEEESSSSSSSSSSSSSSSSSSS");
                    }else{
                        System.out.println("PROBLEME 666666666666666666666666666666");
                                
                    }
                    
                    }
                }
            }
            
//            Vector<ChannelSftp.LsEntry> list = channelSftp.ls("*.xml");
//            logger.info("CONNEXION A SYDO ###################### .................. " + list.size() + " CHEMIN " + channelSftp.getHome());
//            for (ChannelSftp.LsEntry entry : list) {
//                
//                //channelSftp.get(entry.getFilename(), entry.getFilename());
//                System.out.println("YEAHHHHHHHHHHHHHH : " + entry.getFilename());
//
//                channelSftp.put(entry.getFilename());
//                
//                channelSftp.rename(entry.getFilename(), "fichier_traite/" + entry.getFilename());
//                logger.info("FICHIER DEPLACE ############## " + entry.getFilename());
//            } 
//                       
//            
        } catch (Exception e) {
           logger.error("Erreur lors du telechargement d'un fichier entreprise par sftp (Actif)( " + e.getMessage() + ")");
        }finally{
            channel.disconnect();
            session.disconnect();
        }               
        
    }
    
}
