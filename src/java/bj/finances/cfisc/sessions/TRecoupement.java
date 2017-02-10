/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.sessions;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author SANNI Emmanuel
 */
@Stateless
public class TRecoupement {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "CFiscPU")
    private EntityManager em;

    @Inject
    private TDeclarationDouFacade tDDF;
    @Inject
    private TDeclarationFiscaleFacade tDFF;

    //Niveau declaration
    public List<Object[]> obtenirDeclarationDou(Long contImmatr, Date debut, Date fin) {
        String queryString = "  SELECT "
                + "  td.IDE_CUO_COD AS BUREAU, "
                + "  td.IDE_REG_SER || td.IDE_REG_NBR AS NUMENREG, "
                + "  td.IDE_REG_DAT  AS DATEENREG,"
                + "  NVL(td.IDE_RCP_SER || td.IDE_RCP_NBR,'') AS NUMQUIT, "
                + "  NVL(td.IDE_RCP_DAT,'') AS DATEQUIT, "
                + "  0 AS VAL_CAF, "
                + "  NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'DD' AND tt.INSTANCEID = td.INSTANCEID ),0) AS DD, "                
                + "  NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'PC' AND tt.INSTANCEID = td.INSTANCEID ),0) AS PC  , "
                + "  NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'PCS' AND tt.INSTANCEID = td.INSTANCEID ),0) AS PCS, "
                + "  NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'RS' AND tt.INSTANCEID = td.INSTANCEID ),0) AS RS  , "                
                + "  NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'AIB' AND tt.INSTANCEID = td.INSTANCEID ),0) AS AIB, "
                + "  NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'TVA' AND tt.INSTANCEID = td.INSTANCEID ),0) AS TVA, "
                + "  0 AS BASE_TAXABLE "
                + "  FROM T_DECLARATION_DOU td WHERE (td.CMP_CON_COD like ? OR td.CMP_EXP_COD like ?) AND td.IDE_RCP_DAT BETWEEN ? AND ? "
                + "  ORDER BY td.IDE_CUO_COD, td.IDE_REG_DAT, td.IDE_REG_SER || td.IDE_REG_NBR ";
        Query query = em.createNativeQuery(queryString);
        query.setParameter(1, contImmatr)
                .setParameter(2, contImmatr)
                .setParameter(3, new java.sql.Date(debut.getTime()))
                .setParameter(4, new java.sql.Date(fin.getTime()));

        List<Object[]> result = query.getResultList();
        System.out.println("CA MARCHE " + result.size());
        return result;
    }

    //niveau declaration
    public List<Object[]> obtenirDeclarationFiscal(Long EntDecNum) {
        String queryString =  " SELECT "
                            + " TD.DECLAR_BUREAU AS BUREAU, "
                            + " TD.DECLAR_REFERENCE AS NUMDEC, "
                            + " TD.DECLAR_DATE as DATEENREG, "    
                            + " '' as NUMQUIT, "   
                            + " '' as DATEQUIT, "
                            + " TD.TOTAL_VCAF as VAL_CAF, "  
                            + " TD.TOTAL_DD AS DD, "    
                            + " TD.TOTAL_PC AS PC, "                
                            + " TD.TOTAL_PCS AS PCS, "
                            + " TD.TOTAL_RS AS RS, "
                            + " TD.TOTAL_AIB AS AIB, "
                            + " TD.TOTAL_TVA AS TVA, "
                            + " TD.TOTAL_BASE AS BASE_TAXABLE "
                            + " FROM T_DECLARATION_FISCALE TD "
                            + " WHERE TD.DECLAR_ENT_DEC_NUM = ? "
                + " ORDER BY TD.DECLAR_BUREAU, TD.DECLAR_DATE, TD.DECLAR_REFERENCE ";
        Query query = em.createNativeQuery(queryString);
        query.setParameter(1, EntDecNum);
        List<Object[]> result = query.getResultList();
        System.out.println("CA MARCHE aussi " + result.size());
        return result;
    }

    //niveau global
    public List<Object[]> obtenirDeclarationDouaneGlobale(Long contImmatr, Date debut, Date fin) {
        String queryString = "   SELECT  "
                + "  'DOUANE' AS ADM,   "
                + "   SUM(0) AS VAL_CAF, "
                + "   SUM(NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'DD' AND tt.INSTANCEID = td.INSTANCEID ),0)) AS DD,    "                
                + "   SUM(NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'PC' AND tt.INSTANCEID = td.INSTANCEID ),0)) AS PC  ,    "
                + "   SUM(NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'PCS' AND tt.INSTANCEID = td.INSTANCEID ),0)) AS PCS,    "
                + "   SUM(NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'RS' AND tt.INSTANCEID = td.INSTANCEID ),0)) AS RS,    "                
                + "   SUM(NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'AIB' AND tt.INSTANCEID = td.INSTANCEID ),0)) AS AIB,    "
                + "   SUM(NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'TVA' AND tt.INSTANCEID = td.INSTANCEID ),0)) AS TVA,    "
                + "   SUM(NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'AFS' AND tt.INSTANCEID = td.INSTANCEID ),0)) AS AFS    "
                + "   FROM T_DECLARATION_DOU td WHERE (td.CMP_CON_COD like ? OR td.CMP_EXP_COD like ?) AND td.IDE_RCP_DAT BETWEEN ? AND ? GROUP BY 1";
        Query query = em.createNativeQuery(queryString)
                        .setParameter(2, contImmatr)
                        .setParameter(3, new java.sql.Date(debut.getTime()))
                        .setParameter(4, new java.sql.Date(fin.getTime()));
        List<Object[]> result = query.getResultList();
        System.out.println("CA MARCHE aussi " + result.size());
        return result;
    }

    //niveau globale
    public List<Object[]> obtenirDeclarationFiscaleGlobale(Long EntDecNum) {
        String queryString = "  SELECT "
                + "  'IMPOT' AS ADM,   "
                + "  SUM(TT.TAX_DEC_VAL_CAF) AS VAL_CAF,    "  
                + "  SUM(TT.TAX_DEC_DD) AS DD,    "    
                + "  SUM(TT.TAX_DEC_PC) AS PC,    "                
                + "  SUM(TT.TAX_DEC_PCS) AS PCS,   "
                + "  SUM(TT.TAX_DEC_RS) AS RS,    "
                + "  SUM(TT.TAX_DEC_AIB) AS AIB,    "
                + "  SUM(TT.TAX_DEC_TVA) AS TVA,    "
                + "  0 AS AFS    "                
                + "  FROM T_ENT_DECLARATION TD, T_DECLARATION_FISCALE TF,  T_TAXE_DECLARATION TT   "
                + "  WHERE TD.ENT_DEC_NUM = TF.DECLAR_ENT_DEC_NUM    "
                + "  AND TF.DECLAR_NUM = TT.TAX_DECLAR_NUM     "
                + "  AND TD.ENT_DEC_NUM = ? GROUP BY 1";
        Query query = em.createNativeQuery(queryString);
        query.setParameter(1, EntDecNum);
        List<Object[]> result = query.getResultList();
        System.out.println("CA MARCHE aussi " + result.size());
        return result;
    }

    public List<Object[]> obtenirArticleDouanes(Long contImmatr, Date debut, Date fin) {
        String queryString =    "SELECT " +
                                "	td.IDE_CUO_COD AS BUREAU, " +
                                "	td.IDE_REG_SER || td.IDE_REG_NBR AS NUMENREG, " +
                                "	td.IDE_REG_DAT  AS DATEENREG," +
                                "       ta.KEY_ITM_NBR AS NUMART," +
                                "       ta.TAR_HSC_NB1 AS TAR_HSC_NB1," +
                                "       '' AS VAL_CAF," +        
                                "	NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'DD' AND tt.INSTANCEID = td.INSTANCEID ),0) AS DD, " +                
                                "	NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'PC' AND tt.INSTANCEID = td.INSTANCEID ),0) AS PC, " +
                                "	NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'PCS' AND tt.INSTANCEID = td.INSTANCEID ),0) AS PCS, " +
                                "	NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'RS' AND tt.INSTANCEID = td.INSTANCEID ),0) AS RS, " + 
                                "	NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'AIB' AND tt.INSTANCEID = td.INSTANCEID ),0) AS AIB, " +                  
                                "	NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'TVA' AND tt.INSTANCEID = td.INSTANCEID ),0) AS TVA, " +
                                " 0 AS BASE_TAXABLE " +
                                "  FROM T_DECLARATION_DOU td, T_ARTICLE ta" +
                                "  WHERE td.INSTANCEID = ta.INSTANCEID" +
                                "  AND (td.CMP_CON_COD like ? OR td.CMP_EXP_COD like ?) AND td.IDE_RCP_DAT BETWEEN ? AND ?";
        Query query = em.createNativeQuery(queryString);
        query.setParameter(1, contImmatr)
                .setParameter(2, contImmatr)
                .setParameter(3, new java.sql.Date(debut.getTime()))
                .setParameter(4, new java.sql.Date(fin.getTime()));

        List<Object[]> result = query.getResultList();
        System.out.println("CA MARCHE " + result.size());
        return result;
    }

    public List<Object[]> obtenirArticleFiscales(Long EntDecNum) {
        String queryString =    " SELECT " + 
                                " TF.DECLAR_BUREAU AS BUREAU,  " +                 
                                " TF.DECLAR_REFERENCE,  " + 
                                " TF.DECLAR_DATE,  " +                 
                                " TT.TAX_DEC_NUM, " + 
                                " TT.TAX_DEC_TARIF_CODE," + 
                                " TT.TAX_DEC_VAL_CAF AS VAL_CAF,  " +                 
                                " TT.TAX_DEC_PCS AS PCS,  " + 
                                " TT.TAX_DEC_PC AS PC,  " + 
                                " TT.TAX_DEC_AIB AS AIB,  " + 
                                " TT.TAX_DEC_DD AS DD,  " + 
                                " TT.TAX_DEC_RS AS RS,  " + 
                                " TT.TAX_DEC_TVA AS TVA,  " + 
                                " TT.TAX_DEC_BASE_TAXABLE AS BASE_TAXABLE  " + 
                                " FROM T_ENT_DECLARATION TD, T_DECLARATION_FISCALE TF,  T_TAXE_DECLARATION TT  " + 
                                " WHERE TD.ENT_DEC_NUM = TF.DECLAR_ENT_DEC_NUM  " + 
                                " AND TF.DECLAR_NUM = TT.TAX_DECLAR_NUM  " + 
                                " AND TD.ENT_DEC_NUM = ? " ;
        Query query = em.createNativeQuery(queryString);
        query.setParameter(1, EntDecNum);
        List<Object[]> result = query.getResultList();
        System.out.println("CA MARCHE " + result.size());
        return result;
    
    }

    public List<Object[]> obtenirTaxesDouanes(Long contImmatr, Date debut, Date fin) {
        String queryString = "   SELECT     "
                + "   NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'PC' AND tt.INSTANCEID = td.INSTANCEID ),0) AS PC  ,    "
                + "   NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'PCS' AND tt.INSTANCEID = td.INSTANCEID ),0) AS PCS,    "
                + "   NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'AIB' AND tt.INSTANCEID = td.INSTANCEID ),0) AS AIB,    "
                + "   NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'DD' AND tt.INSTANCEID = td.INSTANCEID ),0) AS DD,    "
                + "   NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'TVA' AND tt.INSTANCEID = td.INSTANCEID ),0) AS TVA,    "
                + "   NVL((SELECT SUM(tt.TAX_LIN_AMT) FROM T_TAXE_DECL_DOU tt WHERE tt.TAX_LIN_COD = 'AFS' AND tt.INSTANCEID = td.INSTANCEID ),0) AS AFS    "
                + "   FROM T_DECLARATION_DOU td WHERE (td.CMP_CON_COD like ? OR td.CMP_EXP_COD like ?) AND td.IDE_RCP_DAT BETWEEN ? AND ? ";
        Query query = em.createNativeQuery(queryString)
                        .setParameter(2, contImmatr)
                        .setParameter(3, new java.sql.Date(debut.getTime()))
                        .setParameter(4, new java.sql.Date(fin.getTime()));
        List<Object[]> result = query.getResultList();
        System.out.println("CA MARCHE aussi " + result.size());
        return result;
    }

    public List<Object[]> obtenirTaxesFiscales(Long EntDecNum) {
        String queryString = "  SELECT   "
                + "  SUM(TT.TAX_DEC_PCS) AS PCS,   "
                + "  SUM(TT.TAX_DEC_PC) AS PC,    "
                + "  SUM(TT.TAX_DEC_AIB) AS AIB,    "
                + "  SUM(TT.TAX_DEC_DD) AS DD,    "
                + "  SUM(TT.TAX_DEC_RS) AS RS,    "
                + "  SUM(TT.TAX_DEC_TVA) AS TVA,    "
                + "  SUM(TT.TAX_DEC_VAL_CAF) AS VAL_CAF,    "
                + "  SUM(TT.TAX_DEC_BASE_TAXABLE) AS BASE_TAXABLE   "
                + "  FROM T_ENT_DECLARATION TD, T_DECLARATION_FISCALE TF,  T_TAXE_DECLARATION TT   "
                + "  WHERE TD.ENT_DEC_NUM = TF.DECLAR_ENT_DEC_NUM    "
                + "  AND TF.DECLAR_NUM = TT.TAX_DECLAR_NUM     "
                + "  AND TD.ENT_DEC_NUM = ?";
        Query query = em.createNativeQuery(queryString);
        query.setParameter(1, EntDecNum);
        List<Object[]> result = query.getResultList();
        System.out.println("CA MARCHE aussi " + result.size());
        return result;
    }

}
