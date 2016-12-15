/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "T_ARTICLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TArticle.findAll", query = "SELECT t FROM TArticle t"),
    @NamedQuery(name = "TArticle.findByInstanceid", query = "SELECT t FROM TArticle t WHERE t.tArticlePK.instanceid = :instanceid"),
    @NamedQuery(name = "TArticle.findByKeyItmNbr", query = "SELECT t FROM TArticle t WHERE t.tArticlePK.keyItmNbr = :keyItmNbr"),
    @NamedQuery(name = "TArticle.findByPckNbr", query = "SELECT t FROM TArticle t WHERE t.pckNbr = :pckNbr"),
    @NamedQuery(name = "TArticle.findByPckMrk1", query = "SELECT t FROM TArticle t WHERE t.pckMrk1 = :pckMrk1"),
    @NamedQuery(name = "TArticle.findByPckMrk2", query = "SELECT t FROM TArticle t WHERE t.pckMrk2 = :pckMrk2"),
    @NamedQuery(name = "TArticle.findByPckTypCod", query = "SELECT t FROM TArticle t WHERE t.pckTypCod = :pckTypCod"),
    @NamedQuery(name = "TArticle.findByPckTypNam", query = "SELECT t FROM TArticle t WHERE t.pckTypNam = :pckTypNam"),
    @NamedQuery(name = "TArticle.findByTarHscNb1", query = "SELECT t FROM TArticle t WHERE t.tarHscNb1 = :tarHscNb1"),
//    @NamedQuery(name = "TArticle.findByTarHscNb2", query = "SELECT t FROM TArticle t WHERE t.tarHscNb2 = :tarHscNb2"),
//    @NamedQuery(name = "TArticle.findByTarHscNb3", query = "SELECT t FROM TArticle t WHERE t.tarHscNb3 = :tarHscNb3"),
//    @NamedQuery(name = "TArticle.findByTarHscNb4", query = "SELECT t FROM TArticle t WHERE t.tarHscNb4 = :tarHscNb4"),
//    @NamedQuery(name = "TArticle.findByTarHscNb5", query = "SELECT t FROM TArticle t WHERE t.tarHscNb5 = :tarHscNb5"),
    @NamedQuery(name = "TArticle.findByTarHscTscDat", query = "SELECT t FROM TArticle t WHERE t.tarHscTscDat = :tarHscTscDat"),
    @NamedQuery(name = "TArticle.findByTarHscTscSta", query = "SELECT t FROM TArticle t WHERE t.tarHscTscSta = :tarHscTscSta"),
    @NamedQuery(name = "TArticle.findByTarPrf", query = "SELECT t FROM TArticle t WHERE t.tarPrf = :tarPrf"),
    @NamedQuery(name = "TArticle.findByTarPrcExt", query = "SELECT t FROM TArticle t WHERE t.tarPrcExt = :tarPrcExt"),
    @NamedQuery(name = "TArticle.findByTarPrcNat", query = "SELECT t FROM TArticle t WHERE t.tarPrcNat = :tarPrcNat"),
    @NamedQuery(name = "TArticle.findByTarQuo", query = "SELECT t FROM TArticle t WHERE t.tarQuo = :tarQuo"),
    @NamedQuery(name = "TArticle.findByTarPri", query = "SELECT t FROM TArticle t WHERE t.tarPri = :tarPri"),
    @NamedQuery(name = "TArticle.findByTarVmt", query = "SELECT t FROM TArticle t WHERE t.tarVmt = :tarVmt"),
    @NamedQuery(name = "TArticle.findByTarVdt", query = "SELECT t FROM TArticle t WHERE t.tarVdt = :tarVdt"),
    @NamedQuery(name = "TArticle.findByTarAtt", query = "SELECT t FROM TArticle t WHERE t.tarAtt = :tarAtt"),
    @NamedQuery(name = "TArticle.findByTarAic", query = "SELECT t FROM TArticle t WHERE t.tarAic = :tarAic"),
    @NamedQuery(name = "TArticle.findByGdsOrgCty", query = "SELECT t FROM TArticle t WHERE t.gdsOrgCty = :gdsOrgCty"),
    @NamedQuery(name = "TArticle.findByGdsOrgCrg", query = "SELECT t FROM TArticle t WHERE t.gdsOrgCrg = :gdsOrgCrg"),
    @NamedQuery(name = "TArticle.findByGdsCtnCt1", query = "SELECT t FROM TArticle t WHERE t.gdsCtnCt1 = :gdsCtnCt1"),
    @NamedQuery(name = "TArticle.findByGdsCtnCt2", query = "SELECT t FROM TArticle t WHERE t.gdsCtnCt2 = :gdsCtnCt2"),
    @NamedQuery(name = "TArticle.findByGdsCtnCt3", query = "SELECT t FROM TArticle t WHERE t.gdsCtnCt3 = :gdsCtnCt3"),
    @NamedQuery(name = "TArticle.findByGdsCtnCt4", query = "SELECT t FROM TArticle t WHERE t.gdsCtnCt4 = :gdsCtnCt4"),
    @NamedQuery(name = "TArticle.findByGdsDs3", query = "SELECT t FROM TArticle t WHERE t.gdsDs3 = :gdsDs3"),
    @NamedQuery(name = "TArticle.findByLnkTpt", query = "SELECT t FROM TArticle t WHERE t.lnkTpt = :lnkTpt"),
    @NamedQuery(name = "TArticle.findByLnkTptSln", query = "SELECT t FROM TArticle t WHERE t.lnkTptSln = :lnkTptSln"),
    @NamedQuery(name = "TArticle.findByLnkPrvDoc", query = "SELECT t FROM TArticle t WHERE t.lnkPrvDoc = :lnkPrvDoc"),
    @NamedQuery(name = "TArticle.findByLnkPrvWhs", query = "SELECT t FROM TArticle t WHERE t.lnkPrvWhs = :lnkPrvWhs"),
    @NamedQuery(name = "TArticle.findByLicCod", query = "SELECT t FROM TArticle t WHERE t.licCod = :licCod"),
    @NamedQuery(name = "TArticle.findByLicAmtVal", query = "SELECT t FROM TArticle t WHERE t.licAmtVal = :licAmtVal"),
    @NamedQuery(name = "TArticle.findByLicAmtQty", query = "SELECT t FROM TArticle t WHERE t.licAmtQty = :licAmtQty"),
    @NamedQuery(name = "TArticle.findByTxtFre", query = "SELECT t FROM TArticle t WHERE t.txtFre = :txtFre"),
    @NamedQuery(name = "TArticle.findByTaxAmt", query = "SELECT t FROM TArticle t WHERE t.taxAmt = :taxAmt"),
    @NamedQuery(name = "TArticle.findByTaxGty", query = "SELECT t FROM TArticle t WHERE t.taxGty = :taxGty"),
    @NamedQuery(name = "TArticle.findByTaxMop", query = "SELECT t FROM TArticle t WHERE t.taxMop = :taxMop"),
    @NamedQuery(name = "TArticle.findByTaxCtr", query = "SELECT t FROM TArticle t WHERE t.taxCtr = :taxCtr"),
    @NamedQuery(name = "TArticle.findByTaxDty", query = "SELECT t FROM TArticle t WHERE t.taxDty = :taxDty"),
    @NamedQuery(name = "TArticle.findByVitWgtGrs", query = "SELECT t FROM TArticle t WHERE t.vitWgtGrs = :vitWgtGrs"),
    @NamedQuery(name = "TArticle.findByVitWgtNet", query = "SELECT t FROM TArticle t WHERE t.vitWgtNet = :vitWgtNet"),
    @NamedQuery(name = "TArticle.findByVitCst", query = "SELECT t FROM TArticle t WHERE t.vitCst = :vitCst"),
    @NamedQuery(name = "TArticle.findByVitCif", query = "SELECT t FROM TArticle t WHERE t.vitCif = :vitCif"),
    @NamedQuery(name = "TArticle.findByVitAdj", query = "SELECT t FROM TArticle t WHERE t.vitAdj = :vitAdj"),
    @NamedQuery(name = "TArticle.findByVitStv", query = "SELECT t FROM TArticle t WHERE t.vitStv = :vitStv"),
    @NamedQuery(name = "TArticle.findByVitAlp", query = "SELECT t FROM TArticle t WHERE t.vitAlp = :vitAlp"),
    @NamedQuery(name = "TArticle.findByVitInvAmtNmu", query = "SELECT t FROM TArticle t WHERE t.vitInvAmtNmu = :vitInvAmtNmu"),
    @NamedQuery(name = "TArticle.findByVitInvAmtFcx", query = "SELECT t FROM TArticle t WHERE t.vitInvAmtFcx = :vitInvAmtFcx"),
    @NamedQuery(name = "TArticle.findByVitInvCurCod", query = "SELECT t FROM TArticle t WHERE t.vitInvCurCod = :vitInvCurCod"),
    @NamedQuery(name = "TArticle.findByVitInvCurNam", query = "SELECT t FROM TArticle t WHERE t.vitInvCurNam = :vitInvCurNam"),
    @NamedQuery(name = "TArticle.findByVitInvCurRat", query = "SELECT t FROM TArticle t WHERE t.vitInvCurRat = :vitInvCurRat"),
    @NamedQuery(name = "TArticle.findByVitInvCurRef", query = "SELECT t FROM TArticle t WHERE t.vitInvCurRef = :vitInvCurRef"),
    @NamedQuery(name = "TArticle.findByVitEfrAmtNmu", query = "SELECT t FROM TArticle t WHERE t.vitEfrAmtNmu = :vitEfrAmtNmu"),
    @NamedQuery(name = "TArticle.findByVitEfrAmtFcx", query = "SELECT t FROM TArticle t WHERE t.vitEfrAmtFcx = :vitEfrAmtFcx"),
    @NamedQuery(name = "TArticle.findByVitEfrCurCod", query = "SELECT t FROM TArticle t WHERE t.vitEfrCurCod = :vitEfrCurCod"),
    @NamedQuery(name = "TArticle.findByVitEfrCurNam", query = "SELECT t FROM TArticle t WHERE t.vitEfrCurNam = :vitEfrCurNam"),
    @NamedQuery(name = "TArticle.findByVitEfrCurRat", query = "SELECT t FROM TArticle t WHERE t.vitEfrCurRat = :vitEfrCurRat"),
    @NamedQuery(name = "TArticle.findByVitEfrCurRef", query = "SELECT t FROM TArticle t WHERE t.vitEfrCurRef = :vitEfrCurRef"),
    @NamedQuery(name = "TArticle.findByVitIfrAmtNmu", query = "SELECT t FROM TArticle t WHERE t.vitIfrAmtNmu = :vitIfrAmtNmu"),
    @NamedQuery(name = "TArticle.findByVitIfrAmtFcx", query = "SELECT t FROM TArticle t WHERE t.vitIfrAmtFcx = :vitIfrAmtFcx"),
    @NamedQuery(name = "TArticle.findByVitIfrCurCod", query = "SELECT t FROM TArticle t WHERE t.vitIfrCurCod = :vitIfrCurCod"),
    @NamedQuery(name = "TArticle.findByVitIfrCurNam", query = "SELECT t FROM TArticle t WHERE t.vitIfrCurNam = :vitIfrCurNam"),
    @NamedQuery(name = "TArticle.findByVitIfrCurRat", query = "SELECT t FROM TArticle t WHERE t.vitIfrCurRat = :vitIfrCurRat"),
    @NamedQuery(name = "TArticle.findByVitIfrCurRef", query = "SELECT t FROM TArticle t WHERE t.vitIfrCurRef = :vitIfrCurRef"),
    @NamedQuery(name = "TArticle.findByVitInsAmtNmu", query = "SELECT t FROM TArticle t WHERE t.vitInsAmtNmu = :vitInsAmtNmu"),
    @NamedQuery(name = "TArticle.findByVitInsAmtFcx", query = "SELECT t FROM TArticle t WHERE t.vitInsAmtFcx = :vitInsAmtFcx"),
    @NamedQuery(name = "TArticle.findByVitInsCurCod", query = "SELECT t FROM TArticle t WHERE t.vitInsCurCod = :vitInsCurCod"),
    @NamedQuery(name = "TArticle.findByVitInsCurNam", query = "SELECT t FROM TArticle t WHERE t.vitInsCurNam = :vitInsCurNam"),
    @NamedQuery(name = "TArticle.findByVitInsCurRat", query = "SELECT t FROM TArticle t WHERE t.vitInsCurRat = :vitInsCurRat"),
    @NamedQuery(name = "TArticle.findByVitInsCurRef", query = "SELECT t FROM TArticle t WHERE t.vitInsCurRef = :vitInsCurRef"),
    @NamedQuery(name = "TArticle.findByVitOtcAmtNmu", query = "SELECT t FROM TArticle t WHERE t.vitOtcAmtNmu = :vitOtcAmtNmu"),
    @NamedQuery(name = "TArticle.findByVitOtcAmtFcx", query = "SELECT t FROM TArticle t WHERE t.vitOtcAmtFcx = :vitOtcAmtFcx"),
    @NamedQuery(name = "TArticle.findByVitOtcCurCod", query = "SELECT t FROM TArticle t WHERE t.vitOtcCurCod = :vitOtcCurCod"),
    @NamedQuery(name = "TArticle.findByVitOtcCurNam", query = "SELECT t FROM TArticle t WHERE t.vitOtcCurNam = :vitOtcCurNam"),
    @NamedQuery(name = "TArticle.findByVitOtcCurRat", query = "SELECT t FROM TArticle t WHERE t.vitOtcCurRat = :vitOtcCurRat"),
    @NamedQuery(name = "TArticle.findByVitOtcCurRef", query = "SELECT t FROM TArticle t WHERE t.vitOtcCurRef = :vitOtcCurRef"),
    @NamedQuery(name = "TArticle.findByVitDedAmtNmu", query = "SELECT t FROM TArticle t WHERE t.vitDedAmtNmu = :vitDedAmtNmu"),
    @NamedQuery(name = "TArticle.findByVitDedAmtFcx", query = "SELECT t FROM TArticle t WHERE t.vitDedAmtFcx = :vitDedAmtFcx"),
    @NamedQuery(name = "TArticle.findByVitDedCurCod", query = "SELECT t FROM TArticle t WHERE t.vitDedCurCod = :vitDedCurCod"),
    @NamedQuery(name = "TArticle.findByVitDedCurNam", query = "SELECT t FROM TArticle t WHERE t.vitDedCurNam = :vitDedCurNam"),
    @NamedQuery(name = "TArticle.findByVitDedCurRat", query = "SELECT t FROM TArticle t WHERE t.vitDedCurRat = :vitDedCurRat"),
    @NamedQuery(name = "TArticle.findByVitDedCurRef", query = "SELECT t FROM TArticle t WHERE t.vitDedCurRef = :vitDedCurRef"),
    @NamedQuery(name = "TArticle.findByVitMktRat", query = "SELECT t FROM TArticle t WHERE t.vitMktRat = :vitMktRat"),
    @NamedQuery(name = "TArticle.findByVitMktCur", query = "SELECT t FROM TArticle t WHERE t.vitMktCur = :vitMktCur"),
    @NamedQuery(name = "TArticle.findByVitMktAmt", query = "SELECT t FROM TArticle t WHERE t.vitMktAmt = :vitMktAmt"),
    @NamedQuery(name = "TArticle.findByVitMktBseDsc", query = "SELECT t FROM TArticle t WHERE t.vitMktBseDsc = :vitMktBseDsc"),
    @NamedQuery(name = "TArticle.findByVitMktBseAmt", query = "SELECT t FROM TArticle t WHERE t.vitMktBseAmt = :vitMktBseAmt"),
    @NamedQuery(name = "TArticle.findByBlkVin", query = "SELECT t FROM TArticle t WHERE t.blkVin = :blkVin"),
    @NamedQuery(name = "TArticle.findByBlkSrp", query = "SELECT t FROM TArticle t WHERE t.blkSrp = :blkSrp"),
    @NamedQuery(name = "TArticle.findByBlkFob", query = "SELECT t FROM TArticle t WHERE t.blkFob = :blkFob"),
    @NamedQuery(name = "TArticle.findByQuoId", query = "SELECT t FROM TArticle t WHERE t.quoId = :quoId"),
    @NamedQuery(name = "TArticle.findByQuoItmNbr", query = "SELECT t FROM TArticle t WHERE t.quoItmNbr = :quoItmNbr")})
public class TArticle implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TArticlePK tArticlePK;
    @Column(name = "PCK_NBR")
    //private BigDecimal pckNbr;
    private BigDecimal pckNbr;
    @Size(max = 50)
    @Column(name = "PCK_MRK1")
    private String pckMrk1;
    @Size(max = 50)
    @Column(name = "PCK_MRK2")
    private String pckMrk2;
    @Size(max = 17)
    @Column(name = "PCK_TYP_COD")
    private String pckTypCod;
    @Size(max = 35)
    @Column(name = "PCK_TYP_NAM")
    private String pckTypNam;
    @Size(max = 10)
    @Column(name = "TAR_HSC_NB1")
    private String tarHscNb1;
//    @Size(max = 3)
//    @Column(name = "TAR_HSC_NB2")
//    private String tarHscNb2;
//    @Size(max = 4)
//    @Column(name = "TAR_HSC_NB3")
//    private String tarHscNb3;
//    @Size(max = 4)
//    @Column(name = "TAR_HSC_NB4")
//    private String tarHscNb4;
//    @Size(max = 4)
//    @Column(name = "TAR_HSC_NB5")
//    private String tarHscNb5;
    @Column(name = "TAR_HSC_TSC_DAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tarHscTscDat;
    @Size(max = 1)
    @Column(name = "TAR_HSC_TSC_STA")
    private String tarHscTscSta;
    @Size(max = 8)
    @Column(name = "TAR_PRF")
    private String tarPrf;
    @Size(max = 4)
    @Column(name = "TAR_PRC_EXT")
    private String tarPrcExt;
    @Size(max = 3)
    @Column(name = "TAR_PRC_NAT")
    private String tarPrcNat;
    @Size(max = 17)
    @Column(name = "TAR_QUO")
    private String tarQuo;
    @Column(name = "TAR_PRI")
    //private BigDecimal tarPri;
    private BigDecimal tarPri;
    @Size(max = 1)
    @Column(name = "TAR_VMT")
    private String tarVmt;
    @Size(max = 55)
    @Column(name = "TAR_VDT")
    private String tarVdt;
    @Size(max = 55)
    @Column(name = "TAR_ATT")
    private String tarAtt;
    @Size(max = 3)
    @Column(name = "TAR_AIC")
    private String tarAic;
    @Size(max = 3)
    @Column(name = "GDS_ORG_CTY")
    private String gdsOrgCty;
    @Size(max = 4)
    @Column(name = "GDS_ORG_CRG")
    private String gdsOrgCrg;
    @Size(max = 17)
    @Column(name = "GDS_CTN_CT1")
    private String gdsCtnCt1;
    @Size(max = 17)
    @Column(name = "GDS_CTN_CT2")
    private String gdsCtnCt2;
    @Size(max = 17)
    @Column(name = "GDS_CTN_CT3")
    private String gdsCtnCt3;
    @Size(max = 17)
    @Column(name = "GDS_CTN_CT4")
    private String gdsCtnCt4;
    @Lob
    @Column(name = "GDS_DSC")
    private String gdsDsc;
    @Size(max = 50)
    @Column(name = "GDS_DS3")
    private String gdsDs3;
    @Size(max = 40)
    @Column(name = "LNK_TPT")
    private String lnkTpt;
    @Size(max = 4)
    @Column(name = "LNK_TPT_SLN")
    private String lnkTptSln;
    @Size(max = 35)
    @Column(name = "LNK_PRV_DOC")
    private String lnkPrvDoc;
    @Size(max = 17)
    @Column(name = "LNK_PRV_WHS")
    private String lnkPrvWhs;
    @Size(max = 17)
    @Column(name = "LIC_COD")
    private String licCod;
    @Column(name = "LIC_AMT_VAL")
    //private BigDecimal licAmtVal;
    private BigDecimal licAmtVal;
    @Column(name = "LIC_AMT_QTY")
    //private BigDecimal licAmtQty;
    private BigDecimal licAmtQty;
    @Size(max = 35)
    @Column(name = "TXT_FRE")
    private String txtFre;
    @Lob
    @Column(name = "TXT_RSV")
    private String txtRsv;
    @Column(name = "TAX_AMT")
    //private BigDecimal taxAmt;
    private BigDecimal taxAmt;
    @Column(name = "TAX_GTY")
    //private BigDecimal taxGty;
    private BigDecimal taxGty;
    @Size(max = 1)
    @Column(name = "TAX_MOP")
    private String taxMop;
    @Column(name = "TAX_CTR")
    private Long taxCtr;
    @Column(name = "TAX_DTY")
    private BigDecimal taxDty;
    @Column(name = "VIT_WGT_GRS")
    private BigDecimal vitWgtGrs;
    @Column(name = "VIT_WGT_NET")
    private BigDecimal vitWgtNet;
    @Column(name = "VIT_CST")
    private BigDecimal vitCst;
    @Column(name = "VIT_CIF")
    private BigDecimal vitCif;
    @Column(name = "VIT_ADJ")
    private BigDecimal vitAdj;
    @Column(name = "VIT_STV")
    private BigDecimal vitStv;
    @Column(name = "VIT_ALP")
    private BigDecimal vitAlp;
    @Column(name = "VIT_INV_AMT_NMU")
    private BigDecimal vitInvAmtNmu;
    @Column(name = "VIT_INV_AMT_FCX")
    private BigDecimal vitInvAmtFcx;
    @Size(max = 3)
    @Column(name = "VIT_INV_CUR_COD")
    private String vitInvCurCod;
    @Size(max = 35)
    @Column(name = "VIT_INV_CUR_NAM")
    private String vitInvCurNam;
    @Column(name = "VIT_INV_CUR_RAT")
    private BigDecimal vitInvCurRat;
    @Column(name = "VIT_INV_CUR_REF")
    private BigDecimal vitInvCurRef;
    @Column(name = "VIT_EFR_AMT_NMU")
    private BigDecimal vitEfrAmtNmu;
    @Column(name = "VIT_EFR_AMT_FCX")
    private BigDecimal vitEfrAmtFcx;
    @Size(max = 3)
    @Column(name = "VIT_EFR_CUR_COD")
    private String vitEfrCurCod;
    @Size(max = 35)
    @Column(name = "VIT_EFR_CUR_NAM")
    private String vitEfrCurNam;
    @Column(name = "VIT_EFR_CUR_RAT")
    private BigDecimal vitEfrCurRat;
    @Column(name = "VIT_EFR_CUR_REF")
    private BigDecimal vitEfrCurRef;
    @Column(name = "VIT_IFR_AMT_NMU")
    private BigDecimal vitIfrAmtNmu;
    @Column(name = "VIT_IFR_AMT_FCX")
    private BigDecimal vitIfrAmtFcx;
    @Size(max = 3)
    @Column(name = "VIT_IFR_CUR_COD")
    private String vitIfrCurCod;
    @Size(max = 35)
    @Column(name = "VIT_IFR_CUR_NAM")
    private String vitIfrCurNam;
    @Column(name = "VIT_IFR_CUR_RAT")
    private BigDecimal vitIfrCurRat;
    @Column(name = "VIT_IFR_CUR_REF")
    private BigDecimal vitIfrCurRef;
    @Column(name = "VIT_INS_AMT_NMU")
    private BigDecimal vitInsAmtNmu;
    @Column(name = "VIT_INS_AMT_FCX")
    private BigDecimal vitInsAmtFcx;
    @Size(max = 3)
    @Column(name = "VIT_INS_CUR_COD")
    private String vitInsCurCod;
    @Size(max = 35)
    @Column(name = "VIT_INS_CUR_NAM")
    private String vitInsCurNam;
    @Column(name = "VIT_INS_CUR_RAT")
    private BigDecimal vitInsCurRat;
    @Column(name = "VIT_INS_CUR_REF")
    private BigDecimal vitInsCurRef;
    @Column(name = "VIT_OTC_AMT_NMU")
    private BigDecimal vitOtcAmtNmu;
    @Column(name = "VIT_OTC_AMT_FCX")
    private BigDecimal vitOtcAmtFcx;
    @Size(max = 3)
    @Column(name = "VIT_OTC_CUR_COD")
    private String vitOtcCurCod;
    @Size(max = 35)
    @Column(name = "VIT_OTC_CUR_NAM")
    private String vitOtcCurNam;
    @Column(name = "VIT_OTC_CUR_RAT")
    private BigDecimal vitOtcCurRat;
    @Column(name = "VIT_OTC_CUR_REF")
    private BigDecimal vitOtcCurRef;
    @Column(name = "VIT_DED_AMT_NMU")
    private BigDecimal vitDedAmtNmu;
    @Column(name = "VIT_DED_AMT_FCX")
    private BigDecimal vitDedAmtFcx;
    @Size(max = 3)
    @Column(name = "VIT_DED_CUR_COD")
    private String vitDedCurCod;
    @Size(max = 35)
    @Column(name = "VIT_DED_CUR_NAM")
    private String vitDedCurNam;
    @Column(name = "VIT_DED_CUR_RAT")
    private BigDecimal vitDedCurRat;
    @Column(name = "VIT_DED_CUR_REF")
    private BigDecimal vitDedCurRef;
    @Column(name = "VIT_MKT_RAT")
    private BigDecimal vitMktRat;
    @Size(max = 3)
    @Column(name = "VIT_MKT_CUR")
    private String vitMktCur;
    @Column(name = "VIT_MKT_AMT")
    private BigDecimal vitMktAmt;
    @Size(max = 35)
    @Column(name = "VIT_MKT_BSE_DSC")
    private String vitMktBseDsc;
    @Column(name = "VIT_MKT_BSE_AMT")
    private BigDecimal vitMktBseAmt;
    @Size(max = 17)
    @Column(name = "BLK_VIN")
    private String blkVin;
    @Column(name = "BLK_SRP")
    private BigDecimal blkSrp;
    @Column(name = "BLK_FOB")
    private BigDecimal blkFob;
    @Lob
    @Column(name = "FLP1")
    private Serializable flp1;
    @Column(name = "QUO_ID")
    private Long quoId;
    @Column(name = "QUO_ITM_NBR")
    private Long quoItmNbr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tArticle")
    private List<TTaxeDeclDou> tTaxeDeclDouList;
    @JoinColumn(name = "INSTANCEID", referencedColumnName = "INSTANCEID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TDeclarationDou tDeclarationDou;

    public TArticle() {
    }

    public TArticle(TArticlePK tArticlePK) {
        this.tArticlePK = tArticlePK;
    }

    public TArticle(long instanceid, long keyItmNbr) {
        this.tArticlePK = new TArticlePK(instanceid, keyItmNbr);
    }

    public TArticlePK getTArticlePK() {
        return tArticlePK;
    }

    public void setTArticlePK(TArticlePK tArticlePK) {
        this.tArticlePK = tArticlePK;
    }

    public BigDecimal getPckNbr() {
        return pckNbr;
    }

    public void setPckNbr(BigDecimal pckNbr) {
        this.pckNbr = pckNbr;
    }

    public String getPckMrk1() {
        return pckMrk1;
    }

    public void setPckMrk1(String pckMrk1) {
        this.pckMrk1 = pckMrk1;
    }

    public String getPckMrk2() {
        return pckMrk2;
    }

    public void setPckMrk2(String pckMrk2) {
        this.pckMrk2 = pckMrk2;
    }

    public String getPckTypCod() {
        return pckTypCod;
    }

    public void setPckTypCod(String pckTypCod) {
        this.pckTypCod = pckTypCod;
    }

    public String getPckTypNam() {
        return pckTypNam;
    }

    public void setPckTypNam(String pckTypNam) {
        this.pckTypNam = pckTypNam;
    }

    public String getTarHscNb1() {
        return tarHscNb1;
    }

    public void setTarHscNb1(String tarHscNb1) {
        this.tarHscNb1 = tarHscNb1;
    }

//    public String getTarHscNb2() {
//        return tarHscNb2;
//    }
//
//    public void setTarHscNb2(String tarHscNb2) {
//        this.tarHscNb2 = tarHscNb2;
//    }
//
//    public String getTarHscNb3() {
//        return tarHscNb3;
//    }
//
//    public void setTarHscNb3(String tarHscNb3) {
//        this.tarHscNb3 = tarHscNb3;
//    }
//
//    public String getTarHscNb4() {
//        return tarHscNb4;
//    }
//
//    public void setTarHscNb4(String tarHscNb4) {
//        this.tarHscNb4 = tarHscNb4;
//    }
//
//    public String getTarHscNb5() {
//        return tarHscNb5;
//    }
//
//    public void setTarHscNb5(String tarHscNb5) {
//        this.tarHscNb5 = tarHscNb5;
//    }

    public Date getTarHscTscDat() {
        return tarHscTscDat;
    }

    public void setTarHscTscDat(Date tarHscTscDat) {
        this.tarHscTscDat = tarHscTscDat;
    }

    public String getTarHscTscSta() {
        return tarHscTscSta;
    }

    public void setTarHscTscSta(String tarHscTscSta) {
        this.tarHscTscSta = tarHscTscSta;
    }

    public String getTarPrf() {
        return tarPrf;
    }

    public void setTarPrf(String tarPrf) {
        this.tarPrf = tarPrf;
    }

    public String getTarPrcExt() {
        return tarPrcExt;
    }

    public void setTarPrcExt(String tarPrcExt) {
        this.tarPrcExt = tarPrcExt;
    }

    public String getTarPrcNat() {
        return tarPrcNat;
    }

    public void setTarPrcNat(String tarPrcNat) {
        this.tarPrcNat = tarPrcNat;
    }

    public String getTarQuo() {
        return tarQuo;
    }

    public void setTarQuo(String tarQuo) {
        this.tarQuo = tarQuo;
    }

    public BigDecimal getTarPri() {
        return tarPri;
    }

    public void setTarPri(BigDecimal tarPri) {
        this.tarPri = tarPri;
    }

    public String getTarVmt() {
        return tarVmt;
    }

    public void setTarVmt(String tarVmt) {
        this.tarVmt = tarVmt;
    }

    public String getTarVdt() {
        return tarVdt;
    }

    public void setTarVdt(String tarVdt) {
        this.tarVdt = tarVdt;
    }

    public String getTarAtt() {
        return tarAtt;
    }

    public void setTarAtt(String tarAtt) {
        this.tarAtt = tarAtt;
    }

    public String getTarAic() {
        return tarAic;
    }

    public void setTarAic(String tarAic) {
        this.tarAic = tarAic;
    }

    public String getGdsOrgCty() {
        return gdsOrgCty;
    }

    public void setGdsOrgCty(String gdsOrgCty) {
        this.gdsOrgCty = gdsOrgCty;
    }

    public String getGdsOrgCrg() {
        return gdsOrgCrg;
    }

    public void setGdsOrgCrg(String gdsOrgCrg) {
        this.gdsOrgCrg = gdsOrgCrg;
    }

    public String getGdsCtnCt1() {
        return gdsCtnCt1;
    }

    public void setGdsCtnCt1(String gdsCtnCt1) {
        this.gdsCtnCt1 = gdsCtnCt1;
    }

    public String getGdsCtnCt2() {
        return gdsCtnCt2;
    }

    public void setGdsCtnCt2(String gdsCtnCt2) {
        this.gdsCtnCt2 = gdsCtnCt2;
    }

    public String getGdsCtnCt3() {
        return gdsCtnCt3;
    }

    public void setGdsCtnCt3(String gdsCtnCt3) {
        this.gdsCtnCt3 = gdsCtnCt3;
    }

    public String getGdsCtnCt4() {
        return gdsCtnCt4;
    }

    public void setGdsCtnCt4(String gdsCtnCt4) {
        this.gdsCtnCt4 = gdsCtnCt4;
    }

    public String getGdsDsc() {
        return gdsDsc;
    }

    public void setGdsDsc(String gdsDsc) {
        this.gdsDsc = gdsDsc;
    }

    public String getGdsDs3() {
        return gdsDs3;
    }

    public void setGdsDs3(String gdsDs3) {
        this.gdsDs3 = gdsDs3;
    }

    public String getLnkTpt() {
        return lnkTpt;
    }

    public void setLnkTpt(String lnkTpt) {
        this.lnkTpt = lnkTpt;
    }

    public String getLnkTptSln() {
        return lnkTptSln;
    }

    public void setLnkTptSln(String lnkTptSln) {
        this.lnkTptSln = lnkTptSln;
    }

    public String getLnkPrvDoc() {
        return lnkPrvDoc;
    }

    public void setLnkPrvDoc(String lnkPrvDoc) {
        this.lnkPrvDoc = lnkPrvDoc;
    }

    public String getLnkPrvWhs() {
        return lnkPrvWhs;
    }

    public void setLnkPrvWhs(String lnkPrvWhs) {
        this.lnkPrvWhs = lnkPrvWhs;
    }

    public String getLicCod() {
        return licCod;
    }

    public void setLicCod(String licCod) {
        this.licCod = licCod;
    }

    public BigDecimal getLicAmtVal() {
        return licAmtVal;
    }

    public void setLicAmtVal(BigDecimal licAmtVal) {
        this.licAmtVal = licAmtVal;
    }

    public BigDecimal getLicAmtQty() {
        return licAmtQty;
    }

    public void setLicAmtQty(BigDecimal licAmtQty) {
        this.licAmtQty = licAmtQty;
    }

    public String getTxtFre() {
        return txtFre;
    }

    public void setTxtFre(String txtFre) {
        this.txtFre = txtFre;
    }

    public String getTxtRsv() {
        return txtRsv;
    }

    public void setTxtRsv(String txtRsv) {
        this.txtRsv = txtRsv;
    }

    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    public BigDecimal getTaxGty() {
        return taxGty;
    }

    public void setTaxGty(BigDecimal taxGty) {
        this.taxGty = taxGty;
    }

    public String getTaxMop() {
        return taxMop;
    }

    public void setTaxMop(String taxMop) {
        this.taxMop = taxMop;
    }

    public Long getTaxCtr() {
        return taxCtr;
    }

    public void setTaxCtr(Long taxCtr) {
        this.taxCtr = taxCtr;
    }

    public BigDecimal getTaxDty() {
        return taxDty;
    }

    public void setTaxDty(BigDecimal taxDty) {
        this.taxDty = taxDty;
    }

    public BigDecimal getVitWgtGrs() {
        return vitWgtGrs;
    }

    public void setVitWgtGrs(BigDecimal vitWgtGrs) {
        this.vitWgtGrs = vitWgtGrs;
    }

    public BigDecimal getVitWgtNet() {
        return vitWgtNet;
    }

    public void setVitWgtNet(BigDecimal vitWgtNet) {
        this.vitWgtNet = vitWgtNet;
    }

    public BigDecimal getVitCst() {
        return vitCst;
    }

    public void setVitCst(BigDecimal vitCst) {
        this.vitCst = vitCst;
    }

    public BigDecimal getVitCif() {
        return vitCif;
    }

    public void setVitCif(BigDecimal vitCif) {
        this.vitCif = vitCif;
    }

    public BigDecimal getVitAdj() {
        return vitAdj;
    }

    public void setVitAdj(BigDecimal vitAdj) {
        this.vitAdj = vitAdj;
    }

    public BigDecimal getVitStv() {
        return vitStv;
    }

    public void setVitStv(BigDecimal vitStv) {
        this.vitStv = vitStv;
    }

    public BigDecimal getVitAlp() {
        return vitAlp;
    }

    public void setVitAlp(BigDecimal vitAlp) {
        this.vitAlp = vitAlp;
    }

    public BigDecimal getVitInvAmtNmu() {
        return vitInvAmtNmu;
    }

    public void setVitInvAmtNmu(BigDecimal vitInvAmtNmu) {
        this.vitInvAmtNmu = vitInvAmtNmu;
    }

    public BigDecimal getVitInvAmtFcx() {
        return vitInvAmtFcx;
    }

    public void setVitInvAmtFcx(BigDecimal vitInvAmtFcx) {
        this.vitInvAmtFcx = vitInvAmtFcx;
    }

    public String getVitInvCurCod() {
        return vitInvCurCod;
    }

    public void setVitInvCurCod(String vitInvCurCod) {
        this.vitInvCurCod = vitInvCurCod;
    }

    public String getVitInvCurNam() {
        return vitInvCurNam;
    }

    public void setVitInvCurNam(String vitInvCurNam) {
        this.vitInvCurNam = vitInvCurNam;
    }

    public BigDecimal getVitInvCurRat() {
        return vitInvCurRat;
    }

    public void setVitInvCurRat(BigDecimal vitInvCurRat) {
        this.vitInvCurRat = vitInvCurRat;
    }

    public BigDecimal getVitInvCurRef() {
        return vitInvCurRef;
    }

    public void setVitInvCurRef(BigDecimal vitInvCurRef) {
        this.vitInvCurRef = vitInvCurRef;
    }

    public BigDecimal getVitEfrAmtNmu() {
        return vitEfrAmtNmu;
    }

    public void setVitEfrAmtNmu(BigDecimal vitEfrAmtNmu) {
        this.vitEfrAmtNmu = vitEfrAmtNmu;
    }

    public BigDecimal getVitEfrAmtFcx() {
        return vitEfrAmtFcx;
    }

    public void setVitEfrAmtFcx(BigDecimal vitEfrAmtFcx) {
        this.vitEfrAmtFcx = vitEfrAmtFcx;
    }

    public String getVitEfrCurCod() {
        return vitEfrCurCod;
    }

    public void setVitEfrCurCod(String vitEfrCurCod) {
        this.vitEfrCurCod = vitEfrCurCod;
    }

    public String getVitEfrCurNam() {
        return vitEfrCurNam;
    }

    public void setVitEfrCurNam(String vitEfrCurNam) {
        this.vitEfrCurNam = vitEfrCurNam;
    }

    public BigDecimal getVitEfrCurRat() {
        return vitEfrCurRat;
    }

    public void setVitEfrCurRat(BigDecimal vitEfrCurRat) {
        this.vitEfrCurRat = vitEfrCurRat;
    }

    public BigDecimal getVitEfrCurRef() {
        return vitEfrCurRef;
    }

    public void setVitEfrCurRef(BigDecimal vitEfrCurRef) {
        this.vitEfrCurRef = vitEfrCurRef;
    }

    public BigDecimal getVitIfrAmtNmu() {
        return vitIfrAmtNmu;
    }

    public void setVitIfrAmtNmu(BigDecimal vitIfrAmtNmu) {
        this.vitIfrAmtNmu = vitIfrAmtNmu;
    }

    public BigDecimal getVitIfrAmtFcx() {
        return vitIfrAmtFcx;
    }

    public void setVitIfrAmtFcx(BigDecimal vitIfrAmtFcx) {
        this.vitIfrAmtFcx = vitIfrAmtFcx;
    }

    public String getVitIfrCurCod() {
        return vitIfrCurCod;
    }

    public void setVitIfrCurCod(String vitIfrCurCod) {
        this.vitIfrCurCod = vitIfrCurCod;
    }

    public String getVitIfrCurNam() {
        return vitIfrCurNam;
    }

    public void setVitIfrCurNam(String vitIfrCurNam) {
        this.vitIfrCurNam = vitIfrCurNam;
    }

    public BigDecimal getVitIfrCurRat() {
        return vitIfrCurRat;
    }

    public void setVitIfrCurRat(BigDecimal vitIfrCurRat) {
        this.vitIfrCurRat = vitIfrCurRat;
    }

    public BigDecimal getVitIfrCurRef() {
        return vitIfrCurRef;
    }

    public void setVitIfrCurRef(BigDecimal vitIfrCurRef) {
        this.vitIfrCurRef = vitIfrCurRef;
    }

    public BigDecimal getVitInsAmtNmu() {
        return vitInsAmtNmu;
    }

    public void setVitInsAmtNmu(BigDecimal vitInsAmtNmu) {
        this.vitInsAmtNmu = vitInsAmtNmu;
    }

    public BigDecimal getVitInsAmtFcx() {
        return vitInsAmtFcx;
    }

    public void setVitInsAmtFcx(BigDecimal vitInsAmtFcx) {
        this.vitInsAmtFcx = vitInsAmtFcx;
    }

    public String getVitInsCurCod() {
        return vitInsCurCod;
    }

    public void setVitInsCurCod(String vitInsCurCod) {
        this.vitInsCurCod = vitInsCurCod;
    }

    public String getVitInsCurNam() {
        return vitInsCurNam;
    }

    public void setVitInsCurNam(String vitInsCurNam) {
        this.vitInsCurNam = vitInsCurNam;
    }

    public BigDecimal getVitInsCurRat() {
        return vitInsCurRat;
    }

    public void setVitInsCurRat(BigDecimal vitInsCurRat) {
        this.vitInsCurRat = vitInsCurRat;
    }

    public BigDecimal getVitInsCurRef() {
        return vitInsCurRef;
    }

    public void setVitInsCurRef(BigDecimal vitInsCurRef) {
        this.vitInsCurRef = vitInsCurRef;
    }

    public BigDecimal getVitOtcAmtNmu() {
        return vitOtcAmtNmu;
    }

    public void setVitOtcAmtNmu(BigDecimal vitOtcAmtNmu) {
        this.vitOtcAmtNmu = vitOtcAmtNmu;
    }

    public BigDecimal getVitOtcAmtFcx() {
        return vitOtcAmtFcx;
    }

    public void setVitOtcAmtFcx(BigDecimal vitOtcAmtFcx) {
        this.vitOtcAmtFcx = vitOtcAmtFcx;
    }

    public String getVitOtcCurCod() {
        return vitOtcCurCod;
    }

    public void setVitOtcCurCod(String vitOtcCurCod) {
        this.vitOtcCurCod = vitOtcCurCod;
    }

    public String getVitOtcCurNam() {
        return vitOtcCurNam;
    }

    public void setVitOtcCurNam(String vitOtcCurNam) {
        this.vitOtcCurNam = vitOtcCurNam;
    }

    public BigDecimal getVitOtcCurRat() {
        return vitOtcCurRat;
    }

    public void setVitOtcCurRat(BigDecimal vitOtcCurRat) {
        this.vitOtcCurRat = vitOtcCurRat;
    }

    public BigDecimal getVitOtcCurRef() {
        return vitOtcCurRef;
    }

    public void setVitOtcCurRef(BigDecimal vitOtcCurRef) {
        this.vitOtcCurRef = vitOtcCurRef;
    }

    public BigDecimal getVitDedAmtNmu() {
        return vitDedAmtNmu;
    }

    public void setVitDedAmtNmu(BigDecimal vitDedAmtNmu) {
        this.vitDedAmtNmu = vitDedAmtNmu;
    }

    public BigDecimal getVitDedAmtFcx() {
        return vitDedAmtFcx;
    }

    public void setVitDedAmtFcx(BigDecimal vitDedAmtFcx) {
        this.vitDedAmtFcx = vitDedAmtFcx;
    }

    public String getVitDedCurCod() {
        return vitDedCurCod;
    }

    public void setVitDedCurCod(String vitDedCurCod) {
        this.vitDedCurCod = vitDedCurCod;
    }

    public String getVitDedCurNam() {
        return vitDedCurNam;
    }

    public void setVitDedCurNam(String vitDedCurNam) {
        this.vitDedCurNam = vitDedCurNam;
    }

    public BigDecimal getVitDedCurRat() {
        return vitDedCurRat;
    }

    public void setVitDedCurRat(BigDecimal vitDedCurRat) {
        this.vitDedCurRat = vitDedCurRat;
    }

    public BigDecimal getVitDedCurRef() {
        return vitDedCurRef;
    }

    public void setVitDedCurRef(BigDecimal vitDedCurRef) {
        this.vitDedCurRef = vitDedCurRef;
    }

    public BigDecimal getVitMktRat() {
        return vitMktRat;
    }

    public void setVitMktRat(BigDecimal vitMktRat) {
        this.vitMktRat = vitMktRat;
    }

    public String getVitMktCur() {
        return vitMktCur;
    }

    public void setVitMktCur(String vitMktCur) {
        this.vitMktCur = vitMktCur;
    }

    public BigDecimal getVitMktAmt() {
        return vitMktAmt;
    }

    public void setVitMktAmt(BigDecimal vitMktAmt) {
        this.vitMktAmt = vitMktAmt;
    }

    public String getVitMktBseDsc() {
        return vitMktBseDsc;
    }

    public void setVitMktBseDsc(String vitMktBseDsc) {
        this.vitMktBseDsc = vitMktBseDsc;
    }

    public BigDecimal getVitMktBseAmt() {
        return vitMktBseAmt;
    }

    public void setVitMktBseAmt(BigDecimal vitMktBseAmt) {
        this.vitMktBseAmt = vitMktBseAmt;
    }

    public String getBlkVin() {
        return blkVin;
    }

    public void setBlkVin(String blkVin) {
        this.blkVin = blkVin;
    }

    public BigDecimal getBlkSrp() {
        return blkSrp;
    }

    public void setBlkSrp(BigDecimal blkSrp) {
        this.blkSrp = blkSrp;
    }

    public BigDecimal getBlkFob() {
        return blkFob;
    }

    public void setBlkFob(BigDecimal blkFob) {
        this.blkFob = blkFob;
    }

    public Serializable getFlp1() {
        return flp1;
    }

    public void setFlp1(Serializable flp1) {
        this.flp1 = flp1;
    }

    public Long getQuoId() {
        return quoId;
    }

    public void setQuoId(Long quoId) {
        this.quoId = quoId;
    }

    public Long getQuoItmNbr() {
        return quoItmNbr;
    }

    public void setQuoItmNbr(Long quoItmNbr) {
        this.quoItmNbr = quoItmNbr;
    }

    @XmlTransient
    public List<TTaxeDeclDou> getTTaxeDeclDouList() {
        return tTaxeDeclDouList;
    }

    public void setTTaxeDeclDouList(List<TTaxeDeclDou> tTaxeDeclDouList) {
        this.tTaxeDeclDouList = tTaxeDeclDouList;
    }

    public TDeclarationDou getTDeclarationDou() {
        return tDeclarationDou;
    }

    public void setTDeclarationDou(TDeclarationDou tDeclarationDou) {
        this.tDeclarationDou = tDeclarationDou;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tArticlePK != null ? tArticlePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TArticle)) {
            return false;
        }
        TArticle other = (TArticle) object;
        if ((this.tArticlePK == null && other.tArticlePK != null) || (this.tArticlePK != null && !this.tArticlePK.equals(other.tArticlePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TArticle[ tArticlePK=" + tArticlePK + " ]";
    }
    
}
