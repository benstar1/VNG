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
import bj.finances.cfisc.entities.TDirection;
import bj.finances.cfisc.entities.TExercice;
import bj.finances.cfisc.entities.TService;
import bj.finances.cfisc.entities.TUtilisateur;
import bj.finances.cfisc.sessions.TDirectionFacade;
import bj.finances.cfisc.sessions.TRepUniqueFacade;
import bj.finances.cfisc.sessions.TServiceFacade;
import java.io.File;
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
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
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

    @Resource(mappedName = "jdbc/cfiscDS", type = DataSource.class)
    private DataSource myDB;
    
    @EJB
    TRepUniqueFacade tRepuniqueFacade;
    
    @EJB
    TServiceFacade tServiceFacade;
    
    
    public Date dateD;
    public Date dateF;
    public String nummp2;
    public JasperReport jasperreport;
    private Long nument;
    private TCentreImpot centreimpot;
    private TExercice exercice;

    //Etat Ben
    private String ifu;
    private Date datedeb;
    private Date datefin;
    private int type;
    private String sortie;
    private Integer test_ifu;
    private Integer formEtat = 1;

    public String getSortie() {
        return sortie;
    }

    public void setSortie(String sortie) {
        this.sortie = sortie;
    }

    
    
    public Integer getTest_ifu() {
        return test_ifu;
    }

    public void setTest_ifu(Integer test_ifu) {
        this.test_ifu = test_ifu;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIfu() {
        return ifu;
    }

    public void setIfu(String ifu) {
        this.ifu = ifu;
    }

    public Date getDatedeb() {
        return datedeb;
    }

    public void setDatedeb(Date datedeb) {
        this.datedeb = datedeb;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    @PostConstruct
    public void Etat() {
        setType(0);
        setSortie("excel");
    }

    // fin Etat Ben
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

    public Integer getFormEtat() {
        return formEtat;
    }

    public void setFormEtat(Integer formEtat) {
        this.formEtat = formEtat;
    }

    public void trouveNumDeclar() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        nument = (Long) sessionMap.get("NumEntSession");
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
        System.out.println("EEEEEE Exercicie " + getExercice().getExoAnne());
        System.out.println("CCCCCC Centre " + getCentreimpot().getCentrImpCode());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", getExercice().getExoAnne() + "        et CENTRE " + getCentreimpot().getCentrImpCode()));

        map.put("paramexo", getExercice().getExoAnne());
        map.put("paramcentre", getCentreimpot().getCentrImpCode());
        jasperPrint = JasperFillManager.fillReport(reportPath, map, connection);
        System.out.println("Taille liste classe --> " + jasperPrint.getPages().size());
        connection.close();
    }

    public void initdetaildeclar() throws JRException, ClassNotFoundException, SQLException, ParseException {
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/vues/etat/listedeclaration/listedeclaration.jasper");
        Connection connection = myDB.getConnection();
        System.out.println("chaine de connection " + (connection.getMetaData().getURL()));
        HashMap map = new HashMap();
        trouveNumDeclar();
        String st = nument.toString();
        Integer num = Integer.parseInt(st);
        map.put("numdeclar", num);
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

    public void listeDeclDou(ActionEvent actionEvent) throws JRException, IOException, ClassNotFoundException, SQLException, ParseException {

        //System.out.println(" +++++++++++++++ ############################### " + new LoginMBean().getUtilisateur().getFonctCod().getTDirection().getCode());
        
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    
    Map<String, Object> sessionMap = externalContext.getSessionMap();
    TUtilisateur connectedUser = (TUtilisateur) sessionMap.get("utilisateurConnecte");
        System.out.println("SESSION UUUUUUUSEEEEERRRRRRRR 1" + sessionMap.get("loginUser"));
        System.out.println("SESSION UUUUUUUSEEEEERRRRRRRR 2" + ((TUtilisateur) sessionMap.get("utilisateurConnecte")).getFonctCod().getCode());       
        TService serv = (TService) ((TUtilisateur) sessionMap.get("utilisateurConnecte")).getFonctCod();
    TDirection connectedDirection = (TDirection) tServiceFacade.find(serv.getCode()).getDirection();    
    
    String ifuDirection = "";
    try{
        ifuDirection = tRepuniqueFacade.find(Long.parseLong(ifu)).getContCentrImpCode().getCentrImpCode();
    //System.out.println("+++++++++++++++++++++++++++++ " + ifuDirection + " " + connectedDirection.getCode());
    }
    catch(Exception e){
        e.printStackTrace();
        FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "VEUILLEZ CONTACTER LA BEF."));
    }
    
    if((connectedDirection.getCode()).equals(ifuDirection) || ((TUtilisateur) sessionMap.get("utilisateurConnecte")).getFonctCod().getCode().equals("BEF") || sessionMap.get("loginUser").equals("admin")){
        HashMap map = new HashMap();
        
        if (formEtat == 2) {
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/vues/etat/detailDeclarations/detailDeclarations.jasper");
            Connection connection = myDB.getConnection();
            map.put("entreprise", Long.parseLong(ifu));
            map.put("debut", datedeb);
            map.put("fin", datefin);
            map.put("type", type);
            jasperPrint = JasperFillManager.fillReport(reportPath, map, connection);
            connection.close();
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            
            if(sortie.equals("pdf")){
            httpServletResponse.addHeader("Content-disposition", (type == 0) ? "attachment; filename=listeRecapContr.pdf" : (type == 1) ? "attachment; filename=listeRecapImp.pdf" : "attachment; filename=listeRecapExp.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            }
            else if (sortie.equals("excel")){
                httpServletResponse.addHeader("Content-disposition", (type == 0) ? "attachment; filename=listeDetailContr.xlsx" : (type == 1) ? "attachment; filename=listeDetailImp.xlsx" : "attachment; filename=listeDetailExp.xlsx");          
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JRXlsxExporter docxExporter = new JRXlsxExporter();
                docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
                docxExporter.exportReport();
            }
            
            FacesContext.getCurrentInstance().responseComplete();
            return;
        }

        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/vues/etat/droits_par_importateur/droits_par_importateur.jasper");
        Connection connection = myDB.getConnection();

        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", getExercice().getExoAnne()+"        et CENTRE "+getCentreimpot().getCentrImpCode()));    
        map.put("ifu",Long.parseLong(ifu));
        map.put("dateDeb", datedeb);
        map.put("dateFin", datefin);
        map.put("type", type);
        jasperPrint = JasperFillManager.fillReport(reportPath, map, connection);      
        connection.close();
        
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        
        if(sortie.equals("pdf")){
            httpServletResponse.addHeader("Content-disposition", (type == 0) ? "attachment; filename=listeRecapContr.pdf" : (type == 1) ? "attachment; filename=listeRecapImp.pdf" : "attachment; filename=listeRecapExp.pdf");        
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        }
        else if(sortie.equals("excel")){

          httpServletResponse.addHeader("Content-disposition", (type == 0) ? "attachment; filename=listeRecapContr.xlsx" : (type == 1) ? "attachment; filename=listeRecapImp.xlsx" : "attachment; filename=listeRecapExp.xlsx");          
          ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
          JRXlsxExporter docxExporter = new JRXlsxExporter();
          docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
          docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
          docxExporter.exportReport();            
          //FacesContext.getCurrentInstance().responseComplete();
            
        }
        FacesContext.getCurrentInstance().responseComplete();
    } else{
        FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "VOUS N'ETES PAS AUTORISE A CONSULTER LES DECL. DE CE CONTRIBUABLE"));
            return;
    }   
    
    
//    if(!connectedUserDirection.equals(ifuDirection)){            
//            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "VOUS N'ETES PAS AUTORISE A CONSULTER LES DECL. DE CE CONTRIBUABLE"));
//            return; 
//        }
//        else{        
//        HashMap map = new HashMap();
//        if (formEtat == 2) {
//            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/vues/etat/detailDeclarations/detailDeclarations.jasper");
//            Connection connection = myDB.getConnection();
//            map.put("entreprise", test_ifu);
//            map.put("debut", datedeb);
//            map.put("fin", datefin);
//            jasperPrint = JasperFillManager.fillReport(reportPath, map, connection);
//            connection.close();
//            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//            httpServletResponse.addHeader("Content-disposition", (type == 0) ? "attachment; filename=listeRecapContr.pdf" : (type == 1) ? "attachment; filename=listeRecapImp.pdf" : "attachment; filename=listeRecapExp.pdf");
//            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
//            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
//            FacesContext.getCurrentInstance().responseComplete();
//            return;
//        }
//
//        if (ifu.equals("")) {
//            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "LE NUMERO IFU NE PEUT ETRE VIDE"));
//            System.out.println("VVVVVVVEEE");
//            //return;
//            map.put("ifu", null);
//            test_ifu = 0;
//            map.put("test_ifu", test_ifu);
//
//        } else {
//            map.put("ifu", Long.parseLong(ifu));
//            test_ifu = 1;
//            map.put("test_ifu", test_ifu);
//        }
//
//        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/vues/etat/droits_par_importateur/droits_par_importateur.jasper");
//        Connection connection = myDB.getConnection();
//
//        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", getExercice().getExoAnne()+"        et CENTRE "+getCentreimpot().getCentrImpCode()));    
//        map.put("dateDeb", datedeb);
//        map.put("dateFin", datefin);
//        map.put("type", type);
//        jasperPrint = JasperFillManager.fillReport(reportPath, map, connection);
//        //System.out.println("Taille liste classe --> " + jasperPrint.getPages().size());
//        connection.close();
//
//        //System.out.println("Taille liste classe apres init --> " + jasperPrint.getPages().size());
//        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        httpServletResponse.addHeader("Content-disposition", (type == 0) ? "attachment; filename=listeRecapContr.pdf" : (type == 1) ? "attachment; filename=listeRecapImp.pdf" : "attachment; filename=listeRecapExp.pdf");
//        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
//        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
//        //JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);   
//        FacesContext.getCurrentInstance().responseComplete();
//        //System.out.println("servletOutputStream> " + servletOutputStream);
//        }
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
