/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.controllers;

import bj.finances.cfisc.entities.TCentreImpot;
import bj.finances.cfisc.entities.TDepartement;
import bj.finances.cfisc.entities.TRepUnique;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;



/**
 *
 * @author SANNI Emmanuel
 */
@ViewScoped
@ManagedBean(name = "tableauDeBordController")
public class TableauDeBordController  implements Serializable {

    /**
     * Creates a snew instance of TableauDeBordController
     */
    
    private TreeNode treeRoot;
    private TreeNode selectedNode;
    private TRepUnique selectedTRepUnique ;
    private boolean ficheContribuable = false;
    
    public TableauDeBordController() {
        System.out.println("dans le constructeur de tableau ");
    } 
    
    @EJB bj.finances.cfisc.sessions.TRepUniqueFacade tRepUniqueFacade;
    @EJB bj.finances.cfisc.sessions.TCentreImpotFacade tCentreImpotFacade;
    @EJB bj.finances.cfisc.sessions.TDeclarationDouFacade tDeclarationDouFacade;
    @EJB bj.finances.cfisc.sessions.TDeclarationFiscaleFacade tDeclarationFiscaleFacade;
    @EJB bj.finances.cfisc.sessions.TDepartementFacade tDepartementFacade;
    private List<TCentreImpot> listeCentreImpots;
    private List<TDepartement> listeDepartements;
    private List<TRepUnique> listeTRepUnique;

    public List<TDepartement> getListeDepartements() {
        return listeDepartements;
    }

    public void setListeDepartements(List<TDepartement> listeDepartements) {
        this.listeDepartements = listeDepartements;
    }    
   
    public List<TCentreImpot> getListeCentreImpot() {
        return listeCentreImpots;
    }

    public void setListeCentreImpots(List<TCentreImpot> listeCentreImpots) {
        this.listeCentreImpots = listeCentreImpots;
    }
    
    public TreeNode getTreeRoot() {
        return treeRoot;
    }
    
    public void setTreeRoot( TreeNode treeroot) {
        this.treeRoot = treeRoot;
    }    

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }   
    
    
    @PostConstruct
    public void initMenu(){
        listeDepartements = tDepartementFacade.findAll();
        treeRoot = new DefaultTreeNode("Root", null);
        for(TDepartement tDepartement : listeDepartements){
            TreeNode noeudDepartement = new DefaultTreeNode(tDepartement.getDepCode() + " - " + tDepartement.getDepLibelle(), treeRoot);
            List<TCentreImpot> centres = tDepartement.getTCentreImpotList();
            for(TCentreImpot tCentreImpot : centres){
                TreeNode noeudCentre = new DefaultTreeNode(tCentreImpot.getCentrImpCode() + " - " + tCentreImpot.getCentrImpLibelle(), noeudDepartement);
            }
        }
    }
    
    public void onNodeSelect(NodeSelectEvent event) {
        String entreprise = (String) event.getTreeNode().getData();
        String code_entreprise = null;
        try{
            code_entreprise = entreprise.split("-")[0].trim();
        }catch(NullPointerException npe){}
        TCentreImpot tCentreImpot = tCentreImpotFacade.find(code_entreprise);
        if( tCentreImpot != null){
            listeTRepUnique = tRepUniqueFacade.findAllByCentreImpot(tCentreImpot);
        }
        else{
            listeTRepUnique = new ArrayList<>();
        }
        ficheContribuable = false;
    }

    
    public void onRowSelect(SelectEvent event){
        ficheContribuable = true;
        System.out.println("MAMAN " );
        System.out.println("TO TO TO " + selectedTRepUnique.getContNum());
        System.out.println("MAMAN " + ((TRepUnique) event.getObject()).getContNum() );
        FacesMessage msg = new FacesMessage("TRepunique Selected", ((TRepUnique) event.getObject()).getContNum());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }
    
    
    public void retour(){
        ficheContribuable = false;
    }

    public List<TRepUnique> getListeTRepUnique() {
        if(listeTRepUnique == null) listeTRepUnique = new ArrayList<TRepUnique>();
        return listeTRepUnique;
    }   

    public void setListeTRepUnique(List<TRepUnique> listeTRepUnique) {
        this.listeTRepUnique = listeTRepUnique;
    }

    public TRepUnique getSelectedTRepUnique() {
        if(selectedTRepUnique == null) selectedTRepUnique = new TRepUnique();
        return selectedTRepUnique;
    }

    public void setSelectedTRepUnique(TRepUnique selectedTRepUnique) {
        this.selectedTRepUnique = selectedTRepUnique;
    }

    public boolean isFicheContribuable() {
        return ficheContribuable;
    }

    public void setFicheContribuable(boolean ficheContribuable) {
        this.ficheContribuable = ficheContribuable;
    }

}
