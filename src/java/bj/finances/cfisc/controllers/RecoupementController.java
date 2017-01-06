/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.controllers;

import bj.finances.cfisc.controllers.util.KPaxData;
import bj.finances.cfisc.controllers.util.KPaxDataExporter;
import bj.finances.cfisc.entities.TDeclarationDou;
import bj.finances.cfisc.entities.TDeclarationFiscale;
import bj.finances.cfisc.entities.TEntDeclaration;
import bj.finances.cfisc.entities.TRepUnique;
import bj.finances.cfisc.sessions.TEntDeclarationFacade;
import bj.finances.cfisc.sessions.TRecoupement;
import bj.finances.cfisc.sessions.TRepUniqueFacade;
import java.io.IOException;
import java.io.OutputStream;

import java.io.Serializable;
import java.rmi.Naming;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author SANNI Emmanuel
 */
@ManagedBean(name = "recoupementController")
@SessionScoped
public class RecoupementController implements Serializable {
    
    @Resource(mappedName = "jdbc/cfiscDS", type = DataSource.class)
    private DataSource ds;
    @EJB
    private TRepUniqueFacade tRepUniqueFacade;
    @EJB
    private TRecoupement tRecoupement;
    @EJB
    private TEntDeclarationFacade tentenTEntDeclarationFacade;

    
    private KPaxData crossCheckData;
    
    private List<TDeclarationDou> listTDeclarationDou;
    private List<TDeclarationFiscale> listTDeclarationFiscale;
    private TRepUnique selected = new TRepUnique();
    private TEntDeclaration selectedEntDeclaration;
    private List<TEntDeclaration> listeEntDeclaration;
    private List<TRepUnique> listContribuable;
    private Date dateDebut;
    private Date dateFin;
    private String typeRecoupement = "1";
    private int status = 0;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private int[] selectedFields;
    
    private List<Object[]> listeDeclarationDouanesGlobales = new ArrayList<>();
    private List<Object[]> listeDeclarationFiscalesGlobales = new ArrayList<>();
    private List<String> titles = new ArrayList();
    private List<Object[]> listeArticleDouanes = new ArrayList<>();
    private List<Object[]> listeArticleFiscales = new ArrayList<>();

    private boolean statusBoutonExecuter = true;
    /**
     * Creates a new instance of RecoupementController
     */
    public RecoupementController() {
    }

    public void recouper() {
        try {
            System.out.println("DIEU EST GRAND        " + ds.getConnection().getMetaData().getURL());
        } catch (SQLException ex) {
            Logger.getLogger(RecoupementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch (typeRecoupement) {
            case "1": {
                List<Object[]> globalDouanes = tRecoupement.obtenirDeclarationDouaneGlobale(selected.getContImmatr(), dateDebut, dateFin);
                List<Object[]> globalImpot   = tRecoupement.obtenirDeclarationFiscaleGlobale(selectedEntDeclaration.getEntDecNum());
                int[] keys = {0};
                int[] indexes = selectedFields;
                String[] t = {"ADM","VAL CAL","DD","PC","PCS","RS","AIB", "TVA" , "AFS"};
                crossCheckData = new KPaxData(globalImpot, globalDouanes, t,keys, indexes);
                break;
            }
            case "2": {
                List<Object[]> ldd = tRecoupement.obtenirDeclarationDou(selected.getContImmatr(), dateDebut, dateFin);
                List<Object[]> ldf = tRecoupement.obtenirDeclarationFiscal(selectedEntDeclaration.getEntDecNum());
                int[] keys = {0, 1, 3};
                int[] indexes = selectedFields;
                
                String[] t = {"BUREAU","NUM DEC","DATE DEC","NUM QUIT","DATE QUIT","VAL CAF", "DD" , "PC","PCS","RS","AIB","TVA", "BASE TAXABLE"};
                crossCheckData = new KPaxData(ldf, ldd, t,keys, indexes);
                break;
            }
            case "3": {
                List<Object[]> articleDouanes = tRecoupement.obtenirArticleDouanes(selected.getContImmatr(), dateDebut, dateFin);
                List<Object[]> articleImpot = tRecoupement.obtenirArticleFiscales(selectedEntDeclaration.getEntDecNum());
                int[] keys = {0, 1, 3,4};
                int[] indexes = {0,1,2,3};                
                String[] t = {"BUREAU", "NUM DEC", "DATE DEC", "NUM ART","NOMENCLATURE","VAL CAF","DD","PC","PCS","RS","AIB","TVA"};
                crossCheckData = new KPaxData(articleImpot, articleDouanes, t,keys, indexes);
                break;
            }
            case "4": {
                break;
            }

        }

        System.out.println("AZERTY");
    }
    
    public void exportXlsx(){

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        ec.responseReset(); 
        ec.setResponseContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); 
        ec.setResponseHeader("Content-Disposition", "attachment;filename=recoupement.xlsx"); 
        try {
            OutputStream output = ec.getResponseOutputStream();
            XSSFWorkbook wb = KPaxDataExporter.exportXlsx(crossCheckData);
            wb.write(output);
        } catch (IOException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        fc.responseComplete();     
    }

    public void rechercher() {
        //if(selected.getContImmatr() != null){
        selected = tRepUniqueFacade.find(selected.getContImmatr());
        //}
        
        crossCheckData = new KPaxData();
        
    }

    public void rechercheContribuables() {
        listContribuable = tRepUniqueFacade.findAll();
    }

    public void initLisleContribuable() {
        listContribuable = new ArrayList<>();
    }

    public void handleClose() {

    }

    public List<TDeclarationDou> getListTDeclarationDou() {
        return listTDeclarationDou;
    }

    public void setListTDeclarationDou(List<TDeclarationDou> listTDeclarationDou) {
        this.listTDeclarationDou = listTDeclarationDou;
    }

    public List<TDeclarationFiscale> getListTDeclarationFiscale() {
        return listTDeclarationFiscale;
    }

    public void setListTDeclarationFiscale(List<TDeclarationFiscale> listTDeclarationFiscale) {
        this.listTDeclarationFiscale = listTDeclarationFiscale;
    }

    public TRepUnique getSelected() {
        if (selected == null) {
            selected = new TRepUnique();
        }
        return selected;
    }

    public void setSelected(TRepUnique selected) {
        this.selected = selected;
    }

    public List<TRepUnique> getListContribuable() {
        return listContribuable;
    }

    public void setListContribuable(List<TRepUnique> listContribuable) {
        this.listContribuable = listContribuable;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date deteDebut) {
        this.dateDebut = deteDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getTypeRecoupement() {
        return typeRecoupement;
    }

    public void setTypeRecoupement(String typeRecoupement) {
        this.typeRecoupement = typeRecoupement;
    }
    
    public void initDates(){
        if(selectedEntDeclaration != null){
            dateDebut = selectedEntDeclaration.getEntDecDatedebut();
            dateFin = selectedEntDeclaration.getEntDecDatefin();
        }
    }
    public TEntDeclaration getSelectedEntDeclaration() {
        return selectedEntDeclaration;
    }

    public void setSelectedEntDeclaration(TEntDeclaration selectedDeclaration) {
        this.selectedEntDeclaration = selectedDeclaration;
    }

    public List<TEntDeclaration> getListeEntDeclaration() {
        listeEntDeclaration = tentenTEntDeclarationFacade.findListentdeclarcontrib(selected.getContImmatr());
        return listeEntDeclaration;
    }

    public void setListeEntDeclaration(List<TEntDeclaration> listeEntDeclaration) {
        this.listeEntDeclaration = listeEntDeclaration;
    }

    public List<Object[]> getListeDeclarationDouanesGlobales() {
        return listeDeclarationDouanesGlobales;
    }

    public void setListeDeclarationDouanesGlobales(List<Object[]> listeDeclarationDouanesGlobales) {
        this.listeDeclarationDouanesGlobales = listeDeclarationDouanesGlobales;
    }

    public List<Object[]> getListeDeclarationFiscalesGlobales() {
        return listeDeclarationFiscalesGlobales;
    }

    public void setListeDeclarationFiscalesGlobales(List<Object[]> listeDeclarationFiscalesGlobales) {
        this.listeDeclarationFiscalesGlobales = listeDeclarationFiscalesGlobales;
    }

    public List<Object[]> getListeArticleDouanes() {
        return listeArticleDouanes;
    }

    public void setListeArticleDouanes(List<Object[]> listeArticleDouanes) {
        this.listeArticleDouanes = listeArticleDouanes;
    }

    public List<Object[]> getListeArticleFiscales() {
        return listeArticleFiscales;
    }

    public void setListeArticleFiscales(List<Object[]> listeArticleFiscales) {
        this.listeArticleFiscales = listeArticleFiscales;
    }

    public List<Object[]> getListByStatus() {
        if( crossCheckData == null ) return null;
        return crossCheckData.getDatabyStatus(status);
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public KPaxData getCrossCheckData() {
        return crossCheckData;
    }

    public void setCrossCheckData(KPaxData crossCheckData) {
        this.crossCheckData = crossCheckData;
    }

    public int[] getSelectedFields() {
        return selectedFields;
    }

    public void setSelectedFields(int[] selectedFields) {
        this.selectedFields = selectedFields;
    }

    public boolean isStatusBoutonExecuter() {
        return statusBoutonExecuter;
    }

    public void setStatusBoutonExecuter(boolean statusBoutonExecuter) {
        this.statusBoutonExecuter = statusBoutonExecuter;
    }
    
    
    

}
