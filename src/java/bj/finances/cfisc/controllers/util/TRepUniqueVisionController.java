/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.controllers.util;

import bj.finances.cfisc.entities.TRepUnique;
import bj.finances.cfisc.sessions.TRepUniqueFacade;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


/**
 *
 * @author SANNI Emmanuel
 */
@Named(value = "TRUVCtlr")
@SessionScoped
public class TRepUniqueVisionController {
    
    
    @Inject
    TRepUniqueFacade tRepUniqueFacade;
    //champ de recherche
    private String ifu;
    private String raison;
    private String nom;
    private String prenom;
    private String numDossier;
    private String telephone;
        
    //critere de recherche 
    private String critereIfu;
    private String critereRaison;
    private String critereNom;
    private String criterePrenom;
    private String critereDossier;
    private String critereTelephone;    
    
    private List<TRepUnique> items;
    
    public TRepUniqueVisionController() {
    }
    
    public void effacer(){
        
    }
    
    public void rechercher(){
        System.out.println("JE SUIS LA LES GARS"); 
        String  whereClause = " WHERE 1=1 ";
        System.out.println("PAPAPAPAPAP " + ifu);
        if( ifu != null){
            whereClause += " AND t.contImmatr " + setCritere(critereIfu,  ":contImmatr");
        }
        if(raison != null){
            whereClause += " AND t.contRais " + setCritere(critereIfu, ":contRais");
        }
        if(nom != null){
            whereClause += " AND t.contNom " + setCritere(critereIfu, ":contNom");
        }
        if(prenom != null){
            whereClause += " AND t.contPren " + setCritere(critereIfu, ":contPren");
        }
        if(numDossier != null){
            whereClause += " AND t.contContNum " + setCritere(critereIfu, ":contContNum");
        }
        if(telephone != null){
            whereClause += " AND t.contTel " + setCritere(critereIfu, ":contTel");
        }
        
        tRepUniqueFacade.find(whereClause);
    }
    
    public String setCritere(String critere, String valeur){
        switch(critere){
            case "1" : return " = " + valeur;
            case "2" : return " like %" + valeur + "%";
            case "3" : return " < " + valeur;   
            case "4" : return " > " + valeur;
        }
        return null;
    }

    public String getIfu() {
        return ifu;
    }

    public void setIfu(String ifu) {
        this.ifu = ifu;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumDossier() {
        return numDossier;
    }

    public void setNumDossier(String numDossier) {
        this.numDossier = numDossier;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCritereIfu() {
        return critereIfu;
    }

    public void setCritereIfu(String critereIfu) {
        this.critereIfu = critereIfu;
    }

    public String getCritereRaison() {
        return critereRaison;
    }

    public void setCritereRaison(String critereRaison) {
        this.critereRaison = critereRaison;
    }

    public String getCritereNom() {
        return critereNom;
    }

    public void setCritereNom(String critereNom) {
        this.critereNom = critereNom;
    }

    public String getCriterePrenom() {
        return criterePrenom;
    }

    public void setCriterePrenom(String criterePrenom) {
        this.criterePrenom = criterePrenom;
    }

    public String getCritereDossier() {
        return critereDossier;
    }

    public void setCritereDossier(String critereDossier) {
        this.critereDossier = critereDossier;
    }

    public String getCritereTelephone() {
        return critereTelephone;
    }

    public void setCritereTelephone(String critereTelephone) {
        this.critereTelephone = critereTelephone;
    }

    public List<TRepUnique> getItems() {
        return items;
    }

    public void setItems(List<TRepUnique> items) {
        this.items = items;
    }
    
    
    
}
