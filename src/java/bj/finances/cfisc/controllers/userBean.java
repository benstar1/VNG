/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.controllers;

//import bj.finances.cfisc.controllers.TRepUniqueController;
import bj.finances.cfisc.entities.TCentreImpot;
import bj.finances.cfisc.entities.TDirection;
import bj.finances.cfisc.entities.TService;
import bj.finances.cfisc.entities.TUtilisateur;
import bj.finances.cfisc.sessions.TDirectionFacade;
import bj.finances.cfisc.sessions.TServiceFacade;
import bj.finances.cfisc.sessions.TUtilisateurFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
//import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author user
 */
//@Named(value = "mAJMBean")
@ManagedBean
@SessionScoped
public class userBean extends java.lang.Object {

    @EJB
    private TDirectionFacade tDirectionFacade;

    @EJB
    private TServiceFacade tServiceFacade;   
    
    @EJB
    private TUtilisateurFacade tUtilisateurFacade; 

//    private String cheminActivationSucces = ResourceBundle.getBundle("/parametres").getString("cheminActiveSucces");
//    private String cheminActivationEchecs = ResourceBundle.getBundle("/parametres").getString("cheminActiveEchecs");
//    private String cheminActivationFichier = ResourceBundle.getBundle("/parametres").getString("cheminActiveFichier");

    public TDirectionFacade gettDirectionFacade() {
        return tDirectionFacade;
    }

    public void settDirectionFacade(TDirectionFacade tDirectionFacade) {
        this.tDirectionFacade = tDirectionFacade;
    }

    public TServiceFacade gettServiceFacade() {
        return tServiceFacade;
    }

    public void settServiceFacade(TServiceFacade tServiceFacade) {
        this.tServiceFacade = tServiceFacade;
    }

    public TUtilisateurFacade gettUtilisateurFacade() {
        return tUtilisateurFacade;
    }

    public void settUtilisateurFacade(TUtilisateurFacade tUtilisateurFacade) {
        this.tUtilisateurFacade = tUtilisateurFacade;
    }

    
    private List<TDirection> listDirection;

    private List<TService> listService;
    
    private TUtilisateur monUtilisateur;
    
    private TService monService;
    
    private TDirection maDirection;
            
   
    
    public List<TService> list_Service(){
        listService = tServiceFacade.findAll();
        return listService;
    }

    public List<SelectItem> getListDirection(){
        List<SelectItem> listDir = new ArrayList();
        listDirection = tDirectionFacade.findAll();
        
        for (TDirection madir : listDirection){
            listDir.add(new SelectItem(madir, madir.getCode()+ " --> "  + madir.getLibelle() ));
        }
        return listDir;
    }
    

    public List<SelectItem> getListService(){
        List<SelectItem> listServ = new ArrayList();
        listService = tServiceFacade.findAll();
        
        for (TService monser : listService){
            listServ.add(new SelectItem(monser, monser.getCode() + " --> "  + monser.getLibelle() ));
        }
        return listServ;
    }

    public void setListService(List<TService> listService) {
        this.listService = listService;
    }

    public TUtilisateur getMonUtilisateur() {
        return monUtilisateur;
    }

    public void setMonUtilisateur(TUtilisateur monUtilisateur) {
        this.monUtilisateur = monUtilisateur;
    }

    public TService getMonService() {
        return monService;
    }

    public void setMonService(TService monService) {
        this.monService = monService;
    }

    public TDirection getMaDirection() {
        return maDirection;
    }

    public void setMaDirection(TDirection maDirection) {
        this.maDirection = maDirection;
    }
    
    public void createUser(){      
        monUtilisateur.setUtilCod(null);
        tUtilisateurFacade.create(monUtilisateur);
        System.out.println("test create"); 
        
               
    }

   
    
}
