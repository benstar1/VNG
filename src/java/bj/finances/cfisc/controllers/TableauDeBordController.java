/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.controllers;

import bj.finances.cfisc.entities.TArticle;
import bj.finances.cfisc.entities.TCentreImpot;
import bj.finances.cfisc.entities.TDeclarationDou;
import bj.finances.cfisc.entities.TDeclarationFiscale;
import bj.finances.cfisc.entities.TDepartement;
import bj.finances.cfisc.entities.TRepUnique;
import bj.finances.cfisc.entities.TTaxeDeclDou;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.primefaces.component.column.Column;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
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
    //formulaire de recherche
    private String ifu;
    private String dateAchoisir;
    private Date dateDebut;
    private Date dateFin;
    //fin formulaire de recherche
    
    private TreeNode treeRoot;
    private TreeNode selectedNode;
    private TRepUnique selectedTRepUnique ;
    private TArticle selectedArticle;
    private TDeclarationDou selectedTDeclarationDou;
    private boolean ficheContribuable = false;
    private UIComponent articleTabView;
    
    public TableauDeBordController() {
        System.out.println("dans le constructeur de tableau ");
    } 
    
    @EJB bj.finances.cfisc.sessions.TRepUniqueFacade tRepUniqueFacade;
    @EJB bj.finances.cfisc.sessions.TCentreImpotFacade tCentreImpotFacade;
    @EJB bj.finances.cfisc.sessions.TDeclarationDouFacade tDeclarationDouFacade;
    @EJB bj.finances.cfisc.sessions.TDeclarationFiscaleFacade tDeclarationFiscaleFacade;
    @EJB bj.finances.cfisc.sessions.TDepartementFacade tDepartementFacade;
    @EJB bj.finances.cfisc.sessions.TArticleFacade tArticleFacade;
    @EJB bj.finances.cfisc.sessions.TTaxeDeclDouFacade tTaxeDeclDouFacade;
    private List<TCentreImpot> listeCentreImpots;
    private List<TDepartement> listeDepartements;
    private List<TRepUnique> listeTRepUnique;
    private List<TDeclarationDou> listeTDeclarationDou;
    private List<TArticle> listeArticle;
    private List<TTaxeDeclDou> listeTaxeArticle;
    private List<TDeclarationFiscale> listeTDeclarationFiscale;
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
        listeTDeclarationDou = new ArrayList<>();
        listeTDeclarationFiscale = new ArrayList<>();
    }

    
    public void onRowSelect(SelectEvent event){
        ficheContribuable = true;      
        listeTDeclarationDou = tDeclarationDouFacade.findAllByContribuable(selectedTRepUnique);
        System.out.println("JE SUIS ICI " + listeTDeclarationDou.size() +"  " + selectedTRepUnique.getContNum() + "  " + selectedTRepUnique.getContImmatr() + listeTDeclarationDou.size());
        listeTDeclarationFiscale = tDeclarationFiscaleFacade.findAllByContribuable(selectedTRepUnique);
    }
    
    //code a executer sur selection dne declaration
    public void onDeclarationDouaneRowSelect(){
        
        listeArticle = tArticleFacade.findAllByDeclaration(selectedTDeclarationDou);

    }
    
    
    public void retour(){
        ficheContribuable = false;
    }

    public List<TRepUnique> getListeTRepUnique() {
        if(listeTRepUnique == null) listeTRepUnique = new ArrayList<>();
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

    public List<TDeclarationDou> getListeTDeclarationDou() {
        return listeTDeclarationDou;
    }

    public void setListeTDeclarationDou(List<TDeclarationDou> listeTDeclarationDou) {
        this.listeTDeclarationDou = listeTDeclarationDou;
    }

    public List<TDeclarationFiscale> getListeTDeclarationFiscale() {
        return listeTDeclarationFiscale;
    }

    public void setListeTDeclarationFiscale(List<TDeclarationFiscale> listeTDeclarationFiscale) {
        this.listeTDeclarationFiscale = listeTDeclarationFiscale;
    }

    public TDeclarationDou getSelectedTDeclarationDou() {
        return selectedTDeclarationDou;
    }

    public void setSelectedTDeclarationDou(TDeclarationDou selectedTDeclarationDou) {
        this.selectedTDeclarationDou = selectedTDeclarationDou;
    }

    public UIComponent getArticleTabView() {
        return articleTabView;
    }

    public void setArticleTabView(UIComponent articleTabView) {
        this.articleTabView = articleTabView;
    }

    public TArticle getSelectedArticle() {
        return selectedArticle;
    }

    public void setSelectedArticle(TArticle selectedArticle) {
        this.selectedArticle = selectedArticle;
    }

    public String getIfu() {
        return ifu;
    }

    public void setIfu(String ifu) {
        this.ifu = ifu;
    }

    public String getDateAchoisir() {
        return dateAchoisir;
    }

    public void setDateAchoisir(String dateAchoisir) {
        this.dateAchoisir = dateAchoisir;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    
    
    public List<TArticle> getListeArticle() {
        listeArticle = tArticleFacade.findAllByDeclaration(selectedTDeclarationDou);
        if(listeArticle == null) listeArticle= new ArrayList<>();
        return listeArticle;
    }

    public void setListeArticle(List<TArticle> listeArticle) {
        this.listeArticle = listeArticle;
    }
    
    public void findArticleTaxe(){
        listeTaxeArticle = selectedArticle.getTTaxeDeclDouList();
    }

    public List<TTaxeDeclDou> getListeTaxeArticle() {
        if( listeTaxeArticle ==  null) listeArticle = new ArrayList<>();
        return listeTaxeArticle;
    }

    public void setListeTaxeArticle(List<TTaxeDeclDou> listeTaxeArticle) {
        this.listeTaxeArticle = listeTaxeArticle;
    }
    
    public void recherche(){
        
    }
    
    
    
    
    
    
    
    
    

}
