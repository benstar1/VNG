package org.vng.controlers;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import javax.annotation.Resource;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import org.vng.entities.TDepotSignature;
import org.vng.entities.TVillage;

@Named("imprimerControleur")
@SessionScoped
public class ImprimerControleur implements Serializable {

    @Inject
    org.vng.controlers.ParcelleController parcellecontroleur;
    @Resource(lookup = "jdbc/DSappligeo")
    private DataSource dataSource;
    JasperPrint jasperPrint;
    Date datedebut, datefin;
    TVillage codeVillage;

    public ImprimerControleur() {
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public TVillage getCodeVillage() {
        return codeVillage;
    }

    public void setCodeVillage(TVillage codeVillage) {
        this.codeVillage = codeVillage;
    }

    public void impressionRegistreDroitUsageXLS() throws JRException, ClassNotFoundException, SQLException, ParseException {
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/vues/etat/registreDroitUsage.jasper");
        //System.out.println("Chemin :" + reportPath);
        File file = new File(reportPath);
        String codevillage, commune, arrond, libvil = "";
        datedebut = getDatedebut();
        datefin = getDatefin();

        System.out.println("Le village encours " + parcellecontroleur.getVillageNormal().getVlaDesig());

        try {
            Connection connection = dataSource.getConnection();
            System.out.println("Connexion " + connection.getCatalog());
            HashMap map = new HashMap();

            codevillage = parcellecontroleur.getVillageNormal().getVilaCode();
            try {
                commune = parcellecontroleur.getVillageNormal().getVilaArrCode().getArrComCode().getComDesig();
                arrond = parcellecontroleur.getVillageNormal().getVilaArrCode().getArrDesig();
                libvil = parcellecontroleur.getVillageNormal().getVlaDesig();
                System.out.println(" OUi  "+commune);
                map.put("libcommune", commune);
                map.put("libarrondissement", arrond);
                map.put("libvillage", libvil);
            } catch (Exception e) {

            }
            map.put("village", codevillage);
            map.put("datedebut", datedebut);
            map.put("datefin", datefin);
            

            jasperPrint = JasperFillManager.fillReport(file.getPath(), map, connection);
            try {
                HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=RegistreDU.xlsx");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JRXlsxExporter docxExporter = new JRXlsxExporter();
                docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
                docxExporter.exportReport();
                System.out.println("servletOutputStream> " + servletOutputStream);
            } catch (Exception e) {

            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void impressionRegistreDroitUsagePDF() throws JRException, ClassNotFoundException, SQLException, ParseException {
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/vues/etat/registreDroitUsage.jasper");
        //System.out.println("Chemin :" + reportPath);
        File file = new File(reportPath);
        String codevillage, commune, arrond, libvil = "";
        datedebut = getDatedebut();
        datefin = getDatefin();

        System.out.println("Le village encours " + parcellecontroleur.getVillageNormal().getVlaDesig());

        try {
            Connection connection = dataSource.getConnection();
            System.out.println("Connexion " + connection.getCatalog());
            HashMap map = new HashMap();

            codevillage = parcellecontroleur.getVillageNormal().getVilaCode();
            try {
                commune = parcellecontroleur.getVillageNormal().getVilaArrCode().getArrComCode().getComDesig();
                arrond = parcellecontroleur.getVillageNormal().getVilaArrCode().getArrDesig();
                libvil = parcellecontroleur.getVillageNormal().getVlaDesig();
                System.out.println(" OUi  "+commune);
                map.put("libcommune", commune);
                map.put("libarrondissement", arrond);
                map.put("libvillage", libvil);
            } catch (Exception e) {

            }
            map.put("village", codevillage);
            map.put("datedebut", datedebut);
            map.put("datefin", datefin);
            

            jasperPrint = JasperFillManager.fillReport(file.getPath(), map, connection);
            try {
                HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=RegistreDU.pdf");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                FacesContext.getCurrentInstance().responseComplete();
                System.out.println("servletOutputStream> " + servletOutputStream);
            } catch (Exception e) {

            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
