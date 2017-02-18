/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.controllers;

import bj.finances.cfisc.sessions.TDeclarationDouFacade;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author SANNI Emmanuel
 */
@Named(value = "reportController")
@SessionScoped
public class ReportController implements Serializable {

    @EJB
    private TDeclarationDouFacade tDeclarationDouFacade;

    @Resource(mappedName = "jdbc/cfiscDS", type = DataSource.class)
    private DataSource dataSource;

    private Date debut;
    private Date fin;
    private Long ifu;
    private List<Object[]> resultat;
    private Object[] selected;

    private String statut = "A";
    private boolean choix_etat = true;
    private Date dateJour;
    private String centreImpot;

    /**
     * Creates a new instance of FenStatController
     */
    public ReportController() {
    }

    public void rechercher() {
        //resultat = tDeclarationDouFacade.executeStat(debut, fin, ifu);
    }

    private void preparerImpression(Map<String,Object > parametres, String fichierEtat, String nomEtat) {
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        InputStream reportStream = facesContext.getExternalContext().getResourceAsStream(fichierEtat);
        JasperReport jasperReport;
        Connection con = null;
        try {
            con = dataSource.getConnection();
            jasperReport = JasperCompileManager.compileReport(reportStream);
            parametres.put("logo", facesContext.getExternalContext().getResourceAsStream("/report/logo_mef.jpg") );
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametres, con);
    
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition","attachment; filename=" + nomEtat );
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
            //servletOutputStream.flush();
            //servletOutputStream.close();
            
        } catch (JRException | SQLException | IOException ex) {
            System.out.println("AZERTYQSDFG " + ex.getMessage());
        } finally {
            if( con != null) {
                try{
                    con.close();
                }catch(SQLException sqle){
                    
                }
            }
        }
    }

    public void obtenirRepertoireUnique() {
        preparerImpression(new HashMap(), "/report/contribuableAJour_V2.jrxml", "contribuableAJour_V2.pdf");
    }

    public void rechercherEntreprisePArStatut() {
        Map<String, Object> parametres = new HashMap<>();
        parametres.put("dateJour",dateJour);
        parametres.put("centre_impot",centreImpot);
        parametres.put("etat",statut);
        System.out.println("la date est " + dateJour + "   " + centreImpot + "      "  + statut);
        preparerImpression( parametres, "/report/contribuableAJourParDate_V2.jrxml", "contribuableAJourParDate.pdf");
    }

    //setters et getters 
    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Long getIfu() {
        return ifu;
    }

    public void setIfu(Long ifu) {
        this.ifu = ifu;
    }

    public List<Object[]> getResultat() {
        return resultat;
    }

    public void setResultat(List<Object[]> Resultat) {
        this.resultat = Resultat;
    }

    public Object[] getSelected() {
        return selected;
    }

    public void setSelected(Object[] selected) {
        this.selected = selected;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public boolean isChoix_etat() {
        return choix_etat;
    }

    public void setChoix_etat(boolean choix_etat) {
        this.choix_etat = choix_etat;
    }

    public Date getDateJour() {
        return dateJour;
    }

    public void setDateJour(Date dateJour) {
        this.dateJour = dateJour;
    }

    public String getCentreImpot() {
        return centreImpot;
    }

    public void setCentreImpot(String centreImpot) {
        this.centreImpot = centreImpot;
    }
}
