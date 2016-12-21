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
public class AgentSftpIfu {
    @Inject
    private TTypeContribFacade tTypeContribFacade;
    @Inject
    private TCentreImpotFacade tCentreImpotFacade;
    @Inject
    private TRepUniqueFacade tRepUniqueFacade;  
 
    private String cheminDepotLocal = ResourceBundle.getBundle("/parametres").getString("cheminDepotLocalIfu");
    private String SFTPUSER = ResourceBundle.getBundle("/parametres").getString("SFTPUSER");
   
    private String SFTPHOST = ResourceBundle.getBundle("/parametres").getString("SFTPHOST");
    private String SFTPPASS = ResourceBundle.getBundle("/parametres").getString("SFTPPASS");
    private int SFTPPORT = Integer.parseInt(ResourceBundle.getBundle("/parametres").getString("SFTPPORT"));
    
    @Inject
    private InterfaceIfuPlateforme iifu;
    
    final static org.apache.log4j.Logger logger = Logger.getLogger(AgentSftpIfu.class.getName());
//    
//    public void telechargerDeclarationEndouane(){
//        JSch jsch = new JSch();
//        Session session = null;
//        Channel channel = null;
//        try {
//            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
//            session.setPassword(SFTPPASS);
//
//            java.util.Properties config = new java.util.Properties();
//            config.put("StrictHostKeyChecking", "no");
//            session.setConfig(config);
//            try{
//            session.connect();
//            }
//            catch(Exception ex){
//                logger.error("Problème de connexion de session" + ex.getMessage());
//            }
//
//            channel = session.openChannel("sftp");
//            try{
//            channel.connect();
//            }catch(Exception ex){
//                logger.error("Problème de connexion au canal "+ ex.getMessage());
//            }
//            ChannelSftp channelSftp = (ChannelSftp) channel;
//            channelSftp.lcd(cheminDepotLocal);
//
//            Vector<ChannelSftp.LsEntry> list = channelSftp.ls("*.xml");
//            
//            for (ChannelSftp.LsEntry entry : list) {
//                channelSftp.get(entry.getFilename(), entry.getFilename());
//                channelSftp.rename(entry.getFilename(), "/fichier_traite/" + entry.getFilename());
//                logger.info("Fichier traité .... " + entry.getFilename());            }     
//        } catch (Exception e) {
//           logger.error("Erreur lors du téléchargement d'un fichier entreprise par sftp ( " + e.getMessage() + ")");
//        }finally{
//            channel.disconnect();
//            session.disconnect();
//        }
//    }
    
    
    //@Schedule(dayOfWeek = "*", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "*/20", persistent = false)
    public void telechargerEntrepriseIfu() {
        JSch jsch = new JSch();
        Session session = null;
        Channel channel = null;
        logger.info("Scan du dossier Ifu");
        try {
            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
            session.setPassword(SFTPPASS);
            
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
            
            for (ChannelSftp.LsEntry entry : list) {
                
                channelSftp.get(entry.getFilename(), entry.getFilename());
                System.out.println("nom fichier : " + entry.getFilename());
                channelSftp.rename(entry.getFilename(), "fichier_traite/" + entry.getFilename());
                logger.info("Fichier traité .... " + entry.getFilename());
            }     
        } catch (Exception e) {
           logger.error("Erreur lors du téléchargement d'un fichier entreprise par sftp ( " + e.getMessage() + ")");
        }finally{
            channel.disconnect();
            session.disconnect();
        }
        
        iifu.scrutelocalIfu(); 

    }
}
