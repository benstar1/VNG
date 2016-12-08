/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bj.finances.cfisc.interfaces_old;

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
import java.io.IOException;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;


/**
 *
 * @author SANNI Emmanuel
 */
@Stateless
public class AgentSftp {
    @Inject
    private TTypeContribFacade tTypeContribFacade;
    @Inject
    private TCentreImpotFacade tCentreImpotFacade;
    @Inject
    private TRepUniqueFacade tRepUniqueFacade;  
 
    private String cheminDepotLocal = "C:/cfiscal_local";  
    private String SFTPUSER = "cfisc";
   // private String SFTPHOST = "10.3.37.219";
     private String SFTPHOST = "localhost";
    private String SFTPPASS = "123";
    private int SFTPPORT = 22;
    @Inject
    private InterfaceIfuPlateforme iifu;
    
    
    
    //@Schedule(dayOfWeek = "*", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "0", persistent = false)
    public void telechargerEntreprise() {
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
            
            
            //System.out.println(list.size() + " nouvelles fichiers entreprises trouvés : " + new SimpleDateFormat("dd/MM/yyyy hh:mn:ss").format(new Date()) );
            for (ChannelSftp.LsEntry entry : list) {
                channelSftp.get(entry.getFilename(), entry.getFilename());
                
                channelSftp.rename(entry.getFilename(), "/fichier_traite/" + entry.getFilename());
                System.out.println(entry.getFilename());
            }     
                     
                       
            
        } catch (Exception e) {
            System.out.println("Erreur lors du téléchargement d'un fichier entreprise par sftp " + e.getMessage());
            e.getStackTrace();
        }
        
        try { 
           iifu.scrutelocal(); 
        } catch (JDOMException | SAXException ex) {
            Logger.getLogger(AgentSftp.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (IOException ex) {
            Logger.getLogger(AgentSftp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
