/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.controlers;

//import org.vng.controlers.*;
//import javax.inject.Named;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author AAKAKPO
 */
//@Named(value = "consolidation")
@ManagedBean
@SessionScoped
public class Consolidation implements Serializable {

    @EJB
    org.vng.sessions.ParcelleFacade ejbparcelle;
    @EJB
    org.vng.sessions.CorridorFacade ejbcorridor;

    /**
     * Creates a new instance of Consolidation
     */
    public Consolidation() {
    }
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        System.out.println("ICICICI");
        this.file = file;
    }

    public void upload(FileUploadEvent event) {
         int  p=0,c=0,i = 0;
        file= event.getFile();
        if (file != null) {
            //System.out.println("OKOKOKOK "+file.getFileName());      
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputstream(), "UTF-8"))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {

                    line = line.replace("public.", "");

                    if (line.toUpperCase().startsWith("INSERT INTO CORRIDOR")) {
                        //System.out.println(line);
                        i=0;
                         i = ejbcorridor.executeInsertSQL(line);
                        if (i != 0) {
                            c++;
                        }
                    }
                   
                    if (line.toUpperCase().startsWith("INSERT INTO PARCELLE")) {
                        //System.out.println(line);
                        i=0;
                         i = ejbparcelle.executeInsertSQL(line);
                        if (i != 0) {
                            p++;
                        }
                    }
                   
                }

            } catch (Exception ex) {
                //  LOG.error("Error uploading the file", ex);
                System.err.println("Probleme de lecture");
            }
             System.out.println("Nombre de parcelle Consolidees : " + p);
             System.out.println("Nombre de corridors Consolidees : " + c);
            
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " est bien charge. "+p+" Parcelles, "+c+" Corridor");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            System.out.println("NONONON ");

        }
    }
    
    public void handleFileUpload(FileUploadEvent event) {
               upload( event);
		//FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		//FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
