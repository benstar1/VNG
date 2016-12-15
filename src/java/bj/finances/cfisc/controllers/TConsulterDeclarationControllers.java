package bj.finances.cfisc.controllers;



import bj.finances.cfisc.entities.TDeclarationDou;
import bj.finances.cfisc.entities.TRepUnique;
import bj.finances.cfisc.sessions.TDeclarationDouFacade;
import bj.finances.cfisc.sessions.TRepUniqueFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "tConsulterDeclarationController")
@SessionScoped
public class TConsulterDeclarationControllers implements Serializable {

        //formulaire de recherche
    private Long ifu;
    private TRepUnique selected;
    private TDeclarationDou selectedTDeclarationDou;
    private String dateAchoisir = "4";
    private String typeDeclaration = "3";
    private Date dateDebut;
    private Date dateFin;
    
    private List<TDeclarationDou> listeTDeclarationDou;

    @EJB private TDeclarationDouFacade tDeclarationDouFacade;
    @EJB private TRepUniqueFacade tRepUniqueFacade;

    public Long getIfu() {
        return ifu;
    }

    public void setIfu(Long ifu) {
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

    public List getListeTDeclarationDou() {
        return listeTDeclarationDou;
    }

    public void setListeTDeclarationDou(List listeTDeclarationDou) {
        this.listeTDeclarationDou = listeTDeclarationDou;
    }

    public void rechercheContribuable(){
        selected = tRepUniqueFacade.find(ifu);
    }
    public void recherche(){
        if(ifu == null){
            return;
        }
        if( selected == null ){
            selected = tRepUniqueFacade.find(ifu);
        }
        java.sql.Date debut = null;
        java.sql.Date fin = null;
        if(dateDebut != null && dateFin != null){
            debut = new java.sql.Date(dateDebut.getTime());
            fin = new java.sql.Date(dateFin.getTime());            
        }else if(!"4".equals(dateAchoisir)){
            return;
        }

        listeTDeclarationDou = tDeclarationDouFacade.findAll(selected, typeDeclaration, dateAchoisir, debut, fin);
        
        if(listeTDeclarationDou == null) listeTDeclarationDou = new ArrayList<>();
    }

    public TRepUnique getSelected() {
        return selected;
    }

    public void setSelected(TRepUnique selected) {
        this.selected = selected;
    }

    public String getTypeDeclaration() {
        return typeDeclaration;
    }

    public void setTypeDeclaration(String typeDeclaration) {
        this.typeDeclaration = typeDeclaration;
    }

    public TDeclarationDou getSelectedTDeclarationDou() {
        return selectedTDeclarationDou;
    }

    public void setSelectedTDeclarationDou(TDeclarationDou selectedTDeclarationDou) {
        this.selectedTDeclarationDou = selectedTDeclarationDou;
    }
    
    public String titreArticle(Long itemNber){
        return "Article N°" + Long.toString(itemNber);
    }
}
