package bj.finances.cfisc.controllers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//import bj.mefpd.gesexo.entities.Adjudication;
//import bj.mefpd.gesexo.entities.Contrib;
//import bj.mefpd.gesexo.entities.Mp2;
//import bj.mefpd.gesexo.sessionBeans.ContribFacade;
//import bj.mefpd.gesexo.sessionBeans.Mp2Facade;
import bj.finances.cfisc.entities.TCentreImpot;
import bj.finances.cfisc.entities.TExercice;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import static org.apache.jasper.compiler.ELFunctionMapper.map;

/**
 *
 * @author ramki
 */
@ManagedBean
@SessionScoped
public class Etat {
 //   @EJB
//    private Mp2Facade mp2Facade;
    @Resource(mappedName ="jdbc/cfiscDS",type = DataSource.class )
    private DataSource myDB ;
    public Date dateD;
    public Date dateF;
    public String nummp2;
    public JasperReport jasperreport;
    private  Long nument ;
    private TCentreImpot centreimpot;
    private TExercice exercice;

    public TExercice getExercice() {
        return exercice;
    }

    public void setExercice(TExercice exercice) {
        this.exercice = exercice;
    }
    

    public TCentreImpot getCentreimpot() {
        return centreimpot;
    }

    public void setCentreimpot(TCentreImpot centreimpot) {
        this.centreimpot = centreimpot;
    }

    public Date getDateD() {
        return dateD;
    }

    public void setDateD(Date dateD) {
        this.dateD = dateD;
    }

    public Date getDateF() {
        return dateF;
    }

    public void setDateF(Date dateF) {
        this.dateF = dateF;
    }
  
 public void trouveNumDeclar (){
          ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                    Map<String, Object> sessionMap  = externalContext.getSessionMap();
                    nument= (Long) sessionMap.get("NumEntSession");               
    }
 
    JasperPrint jasperPrint;

    public String getNummp2() {
        return nummp2;
    }

    public void setNummp2(String nummp2) {
        this.nummp2 = nummp2;
    }

    public void initcumulexercice() throws JRException, ClassNotFoundException, SQLException, ParseException {
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/vues/etat/cumulimportexport/cumulimportexport.jasper");
        Connection connection = myDB.getConnection();
        HashMap map = new HashMap();
        
        map.put("datedebut", dateD);
        map.put("datefin", dateF);
        jasperPrint = JasperFillManager.fillReport(reportPath, map, connection);   
        System.out.println("Taille liste classe --> " + jasperPrint.getPages().size());
        connection.close();
    }
    
     public void initdetaildeclar() throws JRException, ClassNotFoundException, SQLException, ParseException {
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/vues/etat/listedeclaration/listedeclaration.jasper");
        Connection connection = myDB.getConnection();
        System.out.println("chaine de connection "+(connection.getMetaData().getURL()));
        HashMap map = new HashMap();
        trouveNumDeclar();
        String st = nument.toString();
        Integer num = Integer.parseInt(st);
        map.put("numdeclar",num);   
        jasperPrint = JasperFillManager.fillReport(reportPath, map, connection);   
        System.out.println("Taille liste classe --> " + jasperPrint.getPages().size());
        connection.close();
    }
     
      public void PDFDETAILDECL(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException, ParseException {

            initdetaildeclar();
            System.out.println("Taille liste classe apres init --> " + jasperPrint.getPages().size());
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=CumulImportExportDeclar.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            //JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);   
            FacesContext.getCurrentInstance().responseComplete();
            System.out.println("servletOutputStream> " + servletOutputStream);
    }

        public void PDFCUMULEXERCICE(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException, ParseException {

            initcumulexercice();
            System.out.println("Taille liste classe apres init --> " + jasperPrint.getPages().size());
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=CumulImportExportDeclar.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            //JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);   
            FacesContext.getCurrentInstance().responseComplete();
            System.out.println("servletOutputStream> " + servletOutputStream);
    }
    

    public void DOCXCUMUL(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException, ParseException {
        initcumulexercice();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=CumulImportExportDeclar.docx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRDocxExporter docxExporter = new JRDocxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }

    public void XLSXCUMUL(ActionEvent actionEvent) throws JRException, IOException, SQLException, ClassNotFoundException, ParseException {
        initcumulexercice();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=CumulImportExportDeclar.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter docxExporter = new JRXlsxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
    }
    
}
