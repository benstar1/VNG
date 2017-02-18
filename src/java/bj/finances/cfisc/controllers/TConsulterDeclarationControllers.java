package bj.finances.cfisc.controllers;

import bj.finances.cfisc.controllers.util.DeclarationEtendue;
import bj.finances.cfisc.controllers.util.DeclarationSynthese;
import bj.finances.cfisc.entities.TDeclarationDou;
import bj.finances.cfisc.entities.TRepUnique;
import bj.finances.cfisc.entities.TTaxeDouane;
import bj.finances.cfisc.sessions.TDeclarationDouFacade;
import bj.finances.cfisc.sessions.TRepUniqueFacade;
import bj.finances.cfisc.sessions.TTaxeDeclDouFacade;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean(name = "tConsulterDeclarationController")
@ViewScoped
public class TConsulterDeclarationControllers implements Serializable {

    //formulaire de recherche
    //parametre de recherche simple
    private String numDecl;
    private String numQuit;
    private String numLiq;
    private String annee;
    private String bureau;
    private int referenceDec = 2;
    private Date dateEnreg;
    private Date dateLiq;
    private Date dateQuit;
    private Map parametres = new HashMap();
    //fin parametre de recherche simple

    private Long ifu;
    private TRepUnique selected;
    private TDeclarationDou selectedTDeclarationDou;
    private DeclarationEtendue selectedSeDeclarationEtendue;
    private String dateAchoisir = "4";
    private String typeDeclaration = "3";
    private Date dateDebut;
    private Date dateFin;

    private List<TDeclarationDou> listeTDeclarationDou;
    private List<DeclarationEtendue> listDeclarationEtendue;
    @EJB
    private TDeclarationDouFacade tDeclarationDouFacade;
    @EJB
    private TRepUniqueFacade tRepUniqueFacade;
    @EJB
    private TTaxeDeclDouFacade tTaxeDeclDouFacade;

    public void rechercheContribuable() {
        selected = tRepUniqueFacade.find(ifu);
    }

    public void rechercheSimple() {
        listeTDeclarationDou = new ArrayList<>();
        listeTDeclarationDou = new ArrayList<>();
        selectedSeDeclarationEtendue = null;
        selectedTDeclarationDou = null;
        
        String serie = "";
        String numero = "";
        if (numDecl != null && numDecl.length() >= 2) {
            serie += numDecl.charAt(0);
            numero = numDecl.substring(1);
        }
        
        parametres = new HashMap();
        parametres.put("referenceDec", referenceDec);
        parametres.put("bureau", bureau);
        parametres.put("serie", serie);
        parametres.put("numero", null);
        parametres.put("dateEnreg", dateEnreg);
        parametres.put("dateLiq", dateLiq);
        parametres.put("dateQuit", dateQuit);

        switch (referenceDec) {
            case 2:

                listeTDeclarationDou = tDeclarationDouFacade.findAll(referenceDec, bureau, serie, numero, dateEnreg);
                break;
            case 3: 
                listeTDeclarationDou = tDeclarationDouFacade.findAll(referenceDec, bureau, serie, numero, dateLiq);
                break;
            case 4:
                listeTDeclarationDou = tDeclarationDouFacade.findAll(referenceDec, bureau, serie, numero, dateQuit);
                break;
        }

        listeTDeclarationDou = tDeclarationDouFacade.findAll(referenceDec, bureau, serie, numero, dateEnreg);

        if (listeTDeclarationDou == null) {
            listeTDeclarationDou = new ArrayList<>();
        }

        convertirADeclarationEtendu();

    }

    public void rechercheAvancee() {
        System.out.println("TAMTAMPION");
        listeTDeclarationDou = new ArrayList<>();
        listDeclarationEtendue = new ArrayList<>();
        selectedSeDeclarationEtendue = null;
 System.out.println("TATAPORANOU " + selected.getContImmatr().toString() + "  " + typeDeclaration + "  " + bureau + "  " + dateAchoisir);
        if (selected == null) {
            return;
        }

        java.sql.Date debut = null;
        java.sql.Date fin = null;

        if (dateDebut != null && dateFin != null) {
            debut = new java.sql.Date(dateDebut.getTime());
            fin = new java.sql.Date(dateFin.getTime());
        } else if (!"4".equals(dateAchoisir)) {
            return;
        }
        
        parametres = new HashMap();
        parametres.put("contribuable", selected.getContImmatr().toString());
        parametres.put("typedeclaration", typeDeclaration);
        parametres.put("bureau", bureau);
        parametres.put("dateAchoisir", dateAchoisir);
        parametres.put("dateDeDebut", debut);
        parametres.put("dateDeFin", fin);
       
        listeTDeclarationDou = tDeclarationDouFacade.findAll(selected, typeDeclaration,bureau, dateAchoisir, debut, fin);
        System.out.println("LA TAILLE EST EST EST " + listeTDeclarationDou.size());
        convertirADeclarationEtendu();
    }

    public void convertirADeclarationEtendu() {

        if (listeTDeclarationDou == null || listeTDeclarationDou.size() == 0) {
            return;
        }

        List<Long> instanceIds = new ArrayList<>();
        for (TDeclarationDou tDeclarationDou : listeTDeclarationDou) {
            instanceIds.add(tDeclarationDou.getInstanceid());
        }

        List<Object[]> syntheseTaxe = tTaxeDeclDouFacade.findSyntheseTaxes(instanceIds);

        if (listeTDeclarationDou == null) {
            listeTDeclarationDou = new ArrayList<>();
        }

        listDeclarationEtendue = new ArrayList<>();

        for (TDeclarationDou tDeclarationDou : listeTDeclarationDou) {
            DeclarationEtendue decEtendue = new DeclarationEtendue(tDeclarationDou);
            for (Object synthese : syntheseTaxe) {
                Object[] s = (Object[]) synthese;
                Long instanceId = (Long) s[0];
                if (instanceId.equals(decEtendue.getInstanceid())) {
                    String codeTaxe = ((TTaxeDouane)s[1]).getTaxCod();
                    if (codeTaxe == null) {
                        codeTaxe = "";
                    }
                    BigDecimal montantTaxe = (BigDecimal) s[2];
                    switch (codeTaxe) {
                        case "PCS":
                            decEtendue.setPcs(montantTaxe);
                            break;
                        case "PC":
                            decEtendue.setPc(montantTaxe);
                            break;
                        case "AIB":
                            decEtendue.setAib(montantTaxe);
                            break;
                        case "DD":
                            decEtendue.setDd(montantTaxe);
                            break;
                        case "TVA":
                            decEtendue.setTva(montantTaxe);
                            break;
                        case "AFS":
                            decEtendue.setAfs(montantTaxe);
                            break;
                    }
                }
            }
            listDeclarationEtendue.add(decEtendue);
        }
    }

    public void transformToPDF() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        InputStream reportStream = facesContext.getExternalContext().getResourceAsStream("/report/listeDeclarationsjrxml.jrxml");

        List<DeclarationSynthese> listDeclarationSynthese = new ArrayList<>();
        for (DeclarationEtendue declarationEtendue : listDeclarationEtendue) {
            DeclarationSynthese declarationSynthese = new DeclarationSynthese();
            declarationSynthese.setBureau(declarationEtendue.getIdeCuoCod());
            if( declarationEtendue.getIdeRegSer() != null ) declarationSynthese.setNumEnreg(declarationEtendue.getIdeRegSer() + declarationEtendue.getIdeRegNbr());
            else declarationSynthese.setNumEnreg("");
            declarationSynthese.setDateEnreg(declarationEtendue.getIdeRegDat());
            if( declarationEtendue.getIdeAstSer() != null) declarationSynthese.setNumLiq(declarationEtendue.getIdeAstSer() + declarationEtendue.getIdeAstNbr());
            else declarationSynthese.setNumLiq("");
            declarationSynthese.setDateLiq(declarationEtendue.getIdeAstDat());
            if( declarationEtendue.getIdeRcpSer() != null ) declarationSynthese.setNumQuit(declarationEtendue.getIdeRcpSer() + declarationEtendue.getIdeRcpNbr());
            else declarationSynthese.setNumQuit("");
            declarationSynthese.setDateQuit(declarationEtendue.getIdeRcpDat());
            declarationSynthese.setPc(declarationEtendue.getPc());
            declarationSynthese.setPcs(declarationEtendue.getPcs());
            declarationSynthese.setDd(declarationEtendue.getDd());
            declarationSynthese.setAib(declarationEtendue.getAib());
            declarationSynthese.setAfs(declarationEtendue.getAfs());
            declarationSynthese.setTva(declarationEtendue.getTva());
            listDeclarationSynthese.add(declarationSynthese);
        }
        JRBeanCollectionDataSource jRBeanArrayDataSource = new JRBeanCollectionDataSource((List<DeclarationSynthese>) listDeclarationSynthese);
        JasperReport jasperReport;
        try {
            jasperReport = JasperCompileManager.compileReport(reportStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametres, jRBeanArrayDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=listeDeclaration.pdf");

            FacesContext.getCurrentInstance().responseComplete();

            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

            servletOutputStream.flush();
            servletOutputStream.close();
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException ex) {
            Logger.getLogger(TConsulterDeclarationControllers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException io) {

        }

    }

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

    public int getReferenceDec() {
        return referenceDec;
    }

    public void setReferenceDec(int referenceDec) {
        this.referenceDec = referenceDec;
    }

    public String getNumDecl() {
        return numDecl;
    }

    public void setNumDecl(String numDecl) {
        this.numDecl = numDecl;
    }

    public String getNumQuit() {
        return numQuit;
    }

    public void setNumQuit(String numQuit) {
        this.numQuit = numQuit;
    }

    public String getNumLiq() {
        return numLiq;
    }

    public void setNumLiq(String numLiq) {
        this.numLiq = numLiq;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getBureau() {
        return bureau;
    }

    public void setBureau(String bureau) {
        this.bureau = bureau;
    }

    public Date getDateEnreg() {
        return dateEnreg;
    }

    public void setDateEnreg(Date dateEnreg) {
        this.dateEnreg = dateEnreg;
    }

    public Date getDateLiq() {
        return dateLiq;
    }

    public void setDateLiq(Date dateLiq) {
        this.dateLiq = dateLiq;
    }

    public Date getDateQuit() {
        return dateQuit;
    }

    public void setDateQuit(Date dateQuit) {
        this.dateQuit = dateQuit;
    }

    public TTaxeDeclDouFacade gettTaxeDeclDouFacade() {
        return tTaxeDeclDouFacade;
    }

    public void settTaxeDeclDouFacade(TTaxeDeclDouFacade tTaxeDeclDouFacade) {
        this.tTaxeDeclDouFacade = tTaxeDeclDouFacade;
    }

    public List<DeclarationEtendue> getListDeclarationEtendue() {
        return listDeclarationEtendue;
    }

    public void setListDeclarationEtendue(List<DeclarationEtendue> listDeclarationEtendue) {

        this.listDeclarationEtendue = listDeclarationEtendue;
    }

    public DeclarationEtendue getSelectedSeDeclarationEtendue() {
        return selectedSeDeclarationEtendue;
    }

    public void setSelectedSeDeclarationEtendue(DeclarationEtendue selectedSeDeclarationEtendue) {
        this.selectedSeDeclarationEtendue = selectedSeDeclarationEtendue;
    }

    public String titreArticle(Long itemNber) {
        return "Article N°" + Long.toString(itemNber);
    }
}
