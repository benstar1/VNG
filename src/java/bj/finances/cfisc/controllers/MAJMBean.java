/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.controllers;

//import bj.finances.cfisc.controllers.TRepUniqueController;
import bj.finances.cfisc.entities.TCentreImpot;
import bj.finances.cfisc.entities.THistStatut;
import bj.finances.cfisc.entities.TRepUnique;
import bj.finances.cfisc.entities.TUtilisateur;
import bj.finances.cfisc.sessions.THistStatutFacade;
import bj.finances.cfisc.sessions.TUtilisateurFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
//import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;

/**
 *
 * @author user
 */
//@Named(value = "mAJMBean")
@ManagedBean
@SessionScoped
public class MAJMBean extends java.lang.Object {

//    @EJB
//    private TRepUniqueFacade tRepUniqueFacade;
    @EJB
    private bj.finances.cfisc.sessions.TRepUniqueFacade tRepUniqueFacade;

    @EJB
    private TUtilisateurFacade tUtilisateurFacade;

    private TUtilisateur user;

    private THistStatut tHistStatut;

    public TUtilisateurFacade gettUtilisateurFacade() {
        return tUtilisateurFacade;
    }

    public void settUtilisateurFacade(TUtilisateurFacade tUtilisateurFacade) {
        this.tUtilisateurFacade = tUtilisateurFacade;
    }

    public TUtilisateur getUser() {
        return user;
    }

    public void setUser(TUtilisateur user) {
        this.user = user;
    }

    @EJB
    private THistStatutFacade tHistStatutFacade;

    // @Inject
    TRepUniqueController tRepUniqueController;

    private TRepUnique tRepUnique;
    private List<TRepUnique> chargement = null;

    public List<TRepUnique> getChargement() {
        return chargement;
    }

    public void setChargement(List<TRepUnique> chargement) {
        this.chargement = chargement;
    }

    private Long ifu;
    private String centre;
    private String statut;

    public Long getIfu() {
        return ifu;
    }

    public void setIfu(Long ifu) {
        this.ifu = ifu;
    }

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public TRepUniqueController gettRepUniqueController() {
        return tRepUniqueController;
    }

    public void settRepUniqueController(TRepUniqueController tRepUniqueController) {
        this.tRepUniqueController = tRepUniqueController;

    }

    private Part file;
//    private String fileContent;

    public void upload() {
        chargement = new ArrayList<>();
        chargeAbsent = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY_HH-mm-ss");
        String tstamp = sdf.format(new Date());
        File echecs = new File("c:\\cfisc\\echecs_" + cimpot.getCentrImpLibelle() + "_" + tstamp + ".txt");
        File succes = new File("c:\\cfisc\\succes_" + cimpot.getCentrImpLibelle() + "_" + tstamp + ".txt");
        File upload = new File("c:\\cfisc\\chargement_" + cimpot.getCentrImpLibelle() + "_" + tstamp + ".xls");

        WriteCSV(file, tstamp, upload);

        try {
//            fileContent = new Scanner(file.getInputStream())
//                    .useDelimiter("\\A").next();

            FileWriter outechec = new FileWriter(echecs);
            FileWriter outsucces = new FileWriter(succes);

            Vector dataHolder = ReadCSV(file);

            //String file = event.getFile().getFilename();
            System.out.print("size " + dataHolder.size());

            HttpServletResponse response
                    = (HttpServletResponse) FacesContext.getCurrentInstance()
                            .getExternalContext().getResponse();

            //byte[] buf = new byte[1024];
//            response.setContentType("application/text");
//            response.setHeader("Content-Disposition", "attachment;filename=test.txt");
//            PrintWriter pout = response.getWriter();//.getOutputStream().write(buf);
//            response.getOutputStream().flush();
//            response.getOutputStream().close();
//            FacesContext.getCurrentInstance().responseComplete();
            for (int i = 1; i < dataHolder.size(); i++) {
                Vector cellStoreVector = (Vector) dataHolder.elementAt(i);
                System.out.print("size " + cellStoreVector.size());
//            for (int j = 0; j < cellStoreVector.size(); j++) {
//                HSSFCell myCell = (HSSFCell) cellStoreVector.elementAt(j);
//                String stringCellValue = myCell.toString();

                //System.out.print(stringCellValue + "\t");
                //   System.out.println(active + " "+cimpot.getCentrImpCode());
                // tRepUnique.setContCentrImpCode(cimpot);
                //tRepUnique.setContStatut(active);
                // System.out.println(cimpot + " "+active + " " +   filename);
                // tRepUniqueController.updateRunique(tRepUnique);
//            }
                DataFormatter fmt = new DataFormatter();

                String ifuAsSeenInExcel = fmt.formatCellValue((Cell) cellStoreVector.elementAt(0));
                ifu = new Long(ifuAsSeenInExcel);
                centre = cellStoreVector.elementAt(4).toString();
                String nom = cellStoreVector.elementAt(1).toString();
                String pnom = cellStoreVector.elementAt(2).toString();
                statut = active;

                System.out.println("IFU centre statut " + ifu + " " + centre + " " + statut + "\n");

                tRepUnique = tRepUniqueFacade.findByContImmatr(ifu);

                if (tRepUnique != null) {

                    if ("A".equals(active)) {

                        System.out.println(tRepUnique.getContStatut());
                        System.out.println("Ancien centre " + tRepUnique.getContCentrImpCode());

                        tRepUnique.setContStatut(statut);
                        tRepUnique.setContCentrImpCode(cimpot);
                        tRepUniqueFacade.edit(tRepUnique);

                        System.out.println("Nouveau statut " + tRepUnique.getContStatut());
                        System.out.println("Nouveau centre " + tRepUnique.getContCentrImpCode());
//                
                        System.out.println("centre selectionné" + cimpot);
                        System.out.println("Action " + active);

                        System.out.println("Objet a charger " + tRepUnique);

                        // constitution de la liste des contribuables modifiés
                        chargement.add(tRepUnique);
                        charge = chargement;

                        System.out.println("Début historisation -- Activation");

                        user = tUtilisateurFacade.findAll().get(0);
                        System.out.println("User " + user);

                        tHistStatut = new THistStatut(tRepUnique, user);
                        try {
                            tHistStatutFacade.historiserStatut(tHistStatut);
                        } catch (Exception e) {
                            System.out.println("Erreur appel historiser" + e);
                        }

                        System.out.println("Fin historisation -- Activation");

                    } else {
                        tRepUnique.setContStatut(statut);
                        tRepUniqueFacade.edit(tRepUnique);

                        // constitution de la liste des contribuables modifiés
                        chargement.add(tRepUnique);
                        charge = chargement;

                        //historisation
                        System.out.println("Début historisation -- Désactivation");

                        user = tUtilisateurFacade.findAll().get(0);
                        System.out.println("User " + user);

                        tHistStatut = new THistStatut(tRepUnique, user);
                        try {
                            tHistStatutFacade.historiserStatut(tHistStatut);
                        } catch (Exception e) {
                            System.out.println("Erreur appel historiser" + e);
                        }

                        System.out.println("Fin historisation -- Désactivation");

//                        if(cimpot.equals(tRepUnique.getCont)){
//                            
//                        }
                    }
                    outsucces.write(ifu.toString() + " " + nom + " " + pnom + " \n");
                } else {

                    outechec.write(ifu.toString() + " " + nom + " " + pnom + " \n");
//                    pout.write(ifu.toString() + " " + nom + " " + pnom + " \n");

                    chargeAbsent.add(ifu.toString() + "\t           " + nom + " \t " + pnom);

                }
            }
// pout.close();
            outechec.close();
            outsucces.close();

        } catch (NumberFormatException e) {
            System.err.println("Erreur sur le format de nombre " + e.getMessage());
        } catch (IOException ex) {
            System.err.println("Erreur d'entrée/sortie " + ex.getMessage());
        }
    }

    public Vector ReadCSV(Part fichier) {
        Vector cellVectorHolder = new Vector();

        fichier = file;
        try {
            //new FileInputStream
            FileInputStream myInput = (FileInputStream) fichier.getInputStream();

            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

            HSSFSheet mySheet = myWorkBook.getSheetAt(0);

            Iterator rowIter = mySheet.rowIterator();

            while (rowIter.hasNext()) {
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator cellIter = myRow.cellIterator();
                Vector cellStoreVector = new Vector();
                while (cellIter.hasNext()) {
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    cellStoreVector.addElement(myCell);
                }
                cellVectorHolder.addElement(cellStoreVector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellVectorHolder;
    }

    public void WriteCSV(Part fichier, String timestamp, File upload) {
        fichier = file;
        try {
            //new FileInputStream
            FileInputStream myInput = (FileInputStream) fichier.getInputStream();

            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

            FileOutputStream out = new FileOutputStream(upload);
            myWorkBook.write(out);
            out.close();

            out.close();

        } catch (IOException e) {
            System.out.println("Erreur I/O" + e.getMessage());
        }
    }

    private List<TRepUnique> charge = null;
    private List<String> chargeAbsent = null;

    public List<String> getChargeAbsent() {
        return chargeAbsent;
    }

    public void setChargeAbsent(List<String> chargeAbsent) {
        this.chargeAbsent = chargeAbsent;
    }

    public List<TRepUnique> getCharge() {
        return charge;
    }

    public void setCharge(List<TRepUnique> charge) {
        this.charge = charge;
    }

    public void chargement() {

        System.out.println(" Chargement " + charge);

    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    /**
     * Creates a new instance of MAJMBean
     */
    public MAJMBean() {
    }

    public String active;
    public String filename;

    public TCentreImpot cimpot;

    public TCentreImpot getCimpot() {
        return cimpot;
    }

    public void setCimpot(TCentreImpot cimpot) {
        this.cimpot = cimpot;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
