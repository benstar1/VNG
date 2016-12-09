/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "T_DECLARATION_DOU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TDeclarationDou.findAll", query = "SELECT t FROM TDeclarationDou t"),
    @NamedQuery(name = "TDeclarationDou.findByInstanceid", query = "SELECT t FROM TDeclarationDou t WHERE t.instanceid = :instanceid"),
    @NamedQuery(name = "TDeclarationDou.findByRlsCuoCod", query = "SELECT t FROM TDeclarationDou t WHERE t.rlsCuoCod = :rlsCuoCod"),
    @NamedQuery(name = "TDeclarationDou.findByRlsCuoNam", query = "SELECT t FROM TDeclarationDou t WHERE t.rlsCuoNam = :rlsCuoNam"),
    @NamedQuery(name = "TDeclarationDou.findByRlsRef", query = "SELECT t FROM TDeclarationDou t WHERE t.rlsRef = :rlsRef"),
    @NamedQuery(name = "TDeclarationDou.findByRlsInf", query = "SELECT t FROM TDeclarationDou t WHERE t.rlsInf = :rlsInf"),
    @NamedQuery(name = "TDeclarationDou.findByRlsDat", query = "SELECT t FROM TDeclarationDou t WHERE t.rlsDat = :rlsDat"),
    @NamedQuery(name = "TDeclarationDou.findByRlsTim", query = "SELECT t FROM TDeclarationDou t WHERE t.rlsTim = :rlsTim"),
    @NamedQuery(name = "TDeclarationDou.findByPrvDty", query = "SELECT t FROM TDeclarationDou t WHERE t.prvDty = :prvDty"),
    @NamedQuery(name = "TDeclarationDou.findByPtyColDsc", query = "SELECT t FROM TDeclarationDou t WHERE t.ptyColDsc = :ptyColDsc"),
    @NamedQuery(name = "TDeclarationDou.findByPtyColInd", query = "SELECT t FROM TDeclarationDou t WHERE t.ptyColInd = :ptyColInd"),
    @NamedQuery(name = "TDeclarationDou.findByPtyBlu", query = "SELECT t FROM TDeclarationDou t WHERE t.ptyBlu = :ptyBlu"),
    @NamedQuery(name = "TDeclarationDou.findByPtyRed", query = "SELECT t FROM TDeclarationDou t WHERE t.ptyRed = :ptyRed"),
    @NamedQuery(name = "TDeclarationDou.findByPtyYel", query = "SELECT t FROM TDeclarationDou t WHERE t.ptyYel = :ptyYel"),
    @NamedQuery(name = "TDeclarationDou.findByPtyGre", query = "SELECT t FROM TDeclarationDou t WHERE t.ptyGre = :ptyGre"),
    @NamedQuery(name = "TDeclarationDou.findByPtyQue", query = "SELECT t FROM TDeclarationDou t WHERE t.ptyQue = :ptyQue"),
    @NamedQuery(name = "TDeclarationDou.findByPtyWde", query = "SELECT t FROM TDeclarationDou t WHERE t.ptyWde = :ptyWde"),
    @NamedQuery(name = "TDeclarationDou.findByPtyFrmNbr", query = "SELECT t FROM TDeclarationDou t WHERE t.ptyFrmNbr = :ptyFrmNbr"),
    @NamedQuery(name = "TDeclarationDou.findByPtyFrmTot", query = "SELECT t FROM TDeclarationDou t WHERE t.ptyFrmTot = :ptyFrmTot"),
    @NamedQuery(name = "TDeclarationDou.findByPtyNbrLdl", query = "SELECT t FROM TDeclarationDou t WHERE t.ptyNbrLdl = :ptyNbrLdl"),
    @NamedQuery(name = "TDeclarationDou.findByPtyNbrItm", query = "SELECT t FROM TDeclarationDou t WHERE t.ptyNbrItm = :ptyNbrItm"),
    @NamedQuery(name = "TDeclarationDou.findByPtyNbrPck", query = "SELECT t FROM TDeclarationDou t WHERE t.ptyNbrPck = :ptyNbrPck"),
    @NamedQuery(name = "TDeclarationDou.findByPtyPlc", query = "SELECT t FROM TDeclarationDou t WHERE t.ptyPlc = :ptyPlc"),
    @NamedQuery(name = "TDeclarationDou.findByPtyCde", query = "SELECT t FROM TDeclarationDou t WHERE t.ptyCde = :ptyCde"),
    @NamedQuery(name = "TDeclarationDou.findByIdeCuoCod", query = "SELECT t FROM TDeclarationDou t WHERE t.ideCuoCod = :ideCuoCod"),
    @NamedQuery(name = "TDeclarationDou.findByIdeCuoNam", query = "SELECT t FROM TDeclarationDou t WHERE t.ideCuoNam = :ideCuoNam"),
    @NamedQuery(name = "TDeclarationDou.findByIdeTypSad", query = "SELECT t FROM TDeclarationDou t WHERE t.ideTypSad = :ideTypSad"),
    @NamedQuery(name = "TDeclarationDou.findByIdeTypPrc", query = "SELECT t FROM TDeclarationDou t WHERE t.ideTypPrc = :ideTypPrc"),
    @NamedQuery(name = "TDeclarationDou.findByIdeTypTyp", query = "SELECT t FROM TDeclarationDou t WHERE t.ideTypTyp = :ideTypTyp"),
    @NamedQuery(name = "TDeclarationDou.findByIdeTypTrs", query = "SELECT t FROM TDeclarationDou t WHERE t.ideTypTrs = :ideTypTrs"),
    @NamedQuery(name = "TDeclarationDou.findByIdeMan", query = "SELECT t FROM TDeclarationDou t WHERE t.ideMan = :ideMan"),
    @NamedQuery(name = "TDeclarationDou.findByIdeBar", query = "SELECT t FROM TDeclarationDou t WHERE t.ideBar = :ideBar"),
    @NamedQuery(name = "TDeclarationDou.findByIdeRegSer", query = "SELECT t FROM TDeclarationDou t WHERE t.ideRegSer = :ideRegSer"),
    @NamedQuery(name = "TDeclarationDou.findByIdeRegNbr", query = "SELECT t FROM TDeclarationDou t WHERE t.ideRegNbr = :ideRegNbr"),
    @NamedQuery(name = "TDeclarationDou.findByIdeRegDat", query = "SELECT t FROM TDeclarationDou t WHERE t.ideRegDat = :ideRegDat"),
    @NamedQuery(name = "TDeclarationDou.findByIdeAstSer", query = "SELECT t FROM TDeclarationDou t WHERE t.ideAstSer = :ideAstSer"),
    @NamedQuery(name = "TDeclarationDou.findByIdeAstNbr", query = "SELECT t FROM TDeclarationDou t WHERE t.ideAstNbr = :ideAstNbr"),
    @NamedQuery(name = "TDeclarationDou.findByIdeAstDat", query = "SELECT t FROM TDeclarationDou t WHERE t.ideAstDat = :ideAstDat"),
    @NamedQuery(name = "TDeclarationDou.findByIdeRcpSer", query = "SELECT t FROM TDeclarationDou t WHERE t.ideRcpSer = :ideRcpSer"),
    @NamedQuery(name = "TDeclarationDou.findByIdeRcpNbr", query = "SELECT t FROM TDeclarationDou t WHERE t.ideRcpNbr = :ideRcpNbr"),
    @NamedQuery(name = "TDeclarationDou.findByIdeRcpDat", query = "SELECT t FROM TDeclarationDou t WHERE t.ideRcpDat = :ideRcpDat"),
    @NamedQuery(name = "TDeclarationDou.findByIdeRcpTyp", query = "SELECT t FROM TDeclarationDou t WHERE t.ideRcpTyp = :ideRcpTyp"),
    @NamedQuery(name = "TDeclarationDou.findByIdeRcpCuo", query = "SELECT t FROM TDeclarationDou t WHERE t.ideRcpCuo = :ideRcpCuo"),
    @NamedQuery(name = "TDeclarationDou.findByIdePstNbr", query = "SELECT t FROM TDeclarationDou t WHERE t.idePstNbr = :idePstNbr"),
    @NamedQuery(name = "TDeclarationDou.findByIdePstDat", query = "SELECT t FROM TDeclarationDou t WHERE t.idePstDat = :idePstDat"),
    @NamedQuery(name = "TDeclarationDou.findByIdePstTmpPre", query = "SELECT t FROM TDeclarationDou t WHERE t.idePstTmpPre = :idePstTmpPre"),
    @NamedQuery(name = "TDeclarationDou.findByIdePstTmpTy1", query = "SELECT t FROM TDeclarationDou t WHERE t.idePstTmpTy1 = :idePstTmpTy1"),
    @NamedQuery(name = "TDeclarationDou.findByIdePstTmpTy2", query = "SELECT t FROM TDeclarationDou t WHERE t.idePstTmpTy2 = :idePstTmpTy2"),
    @NamedQuery(name = "TDeclarationDou.findByCmpExpCod", query = "SELECT t FROM TDeclarationDou t WHERE t.cmpExpCod = :cmpExpCod"),
    @NamedQuery(name = "TDeclarationDou.findByCmpConCod", query = "SELECT t FROM TDeclarationDou t WHERE t.cmpConCod = :cmpConCod"),
    @NamedQuery(name = "TDeclarationDou.findByCmpFisCod", query = "SELECT t FROM TDeclarationDou t WHERE t.cmpFisCod = :cmpFisCod"),
    @NamedQuery(name = "TDeclarationDou.findByDecFlg", query = "SELECT t FROM TDeclarationDou t WHERE t.decFlg = :decFlg"),
    @NamedQuery(name = "TDeclarationDou.findByDecCod", query = "SELECT t FROM TDeclarationDou t WHERE t.decCod = :decCod"),
    @NamedQuery(name = "TDeclarationDou.findByDecRefYer", query = "SELECT t FROM TDeclarationDou t WHERE t.decRefYer = :decRefYer"),
    @NamedQuery(name = "TDeclarationDou.findByDecRefNbr", query = "SELECT t FROM TDeclarationDou t WHERE t.decRefNbr = :decRefNbr"),
    @NamedQuery(name = "TDeclarationDou.findByDecRep", query = "SELECT t FROM TDeclarationDou t WHERE t.decRep = :decRep"),
    @NamedQuery(name = "TDeclarationDou.findByGenCtyFlt", query = "SELECT t FROM TDeclarationDou t WHERE t.genCtyFlt = :genCtyFlt"),
    @NamedQuery(name = "TDeclarationDou.findByGenCtyTrd", query = "SELECT t FROM TDeclarationDou t WHERE t.genCtyTrd = :genCtyTrd"),
    @NamedQuery(name = "TDeclarationDou.findByGenCtyEptCod", query = "SELECT t FROM TDeclarationDou t WHERE t.genCtyEptCod = :genCtyEptCod"),
    @NamedQuery(name = "TDeclarationDou.findByGenCtyEptNam", query = "SELECT t FROM TDeclarationDou t WHERE t.genCtyEptNam = :genCtyEptNam"),
    @NamedQuery(name = "TDeclarationDou.findByGenCtyEptCrg", query = "SELECT t FROM TDeclarationDou t WHERE t.genCtyEptCrg = :genCtyEptCrg"),
    @NamedQuery(name = "TDeclarationDou.findByGenCtyDesCod", query = "SELECT t FROM TDeclarationDou t WHERE t.genCtyDesCod = :genCtyDesCod"),
    @NamedQuery(name = "TDeclarationDou.findByGenCtyDesNam", query = "SELECT t FROM TDeclarationDou t WHERE t.genCtyDesNam = :genCtyDesNam"),
    @NamedQuery(name = "TDeclarationDou.findByGenCtyDesCrg", query = "SELECT t FROM TDeclarationDou t WHERE t.genCtyDesCrg = :genCtyDesCrg"),
    @NamedQuery(name = "TDeclarationDou.findByGenCtyOrg", query = "SELECT t FROM TDeclarationDou t WHERE t.genCtyOrg = :genCtyOrg"),
    @NamedQuery(name = "TDeclarationDou.findByGenVdt", query = "SELECT t FROM TDeclarationDou t WHERE t.genVdt = :genVdt"),
    @NamedQuery(name = "TDeclarationDou.findByGenCap", query = "SELECT t FROM TDeclarationDou t WHERE t.genCap = :genCap"),
    @NamedQuery(name = "TDeclarationDou.findByTptMotDpaNam", query = "SELECT t FROM TDeclarationDou t WHERE t.tptMotDpaNam = :tptMotDpaNam"),
    @NamedQuery(name = "TDeclarationDou.findByTptMotDpaCty", query = "SELECT t FROM TDeclarationDou t WHERE t.tptMotDpaCty = :tptMotDpaCty"),
    @NamedQuery(name = "TDeclarationDou.findByTptMotBrdNam", query = "SELECT t FROM TDeclarationDou t WHERE t.tptMotBrdNam = :tptMotBrdNam"),
    @NamedQuery(name = "TDeclarationDou.findByTptMotBrdCty", query = "SELECT t FROM TDeclarationDou t WHERE t.tptMotBrdCty = :tptMotBrdCty"),
    @NamedQuery(name = "TDeclarationDou.findByTptMotBrdCod", query = "SELECT t FROM TDeclarationDou t WHERE t.tptMotBrdCod = :tptMotBrdCod"),
    @NamedQuery(name = "TDeclarationDou.findByTptMotInl", query = "SELECT t FROM TDeclarationDou t WHERE t.tptMotInl = :tptMotInl"),
    @NamedQuery(name = "TDeclarationDou.findByTptCtf", query = "SELECT t FROM TDeclarationDou t WHERE t.tptCtf = :tptCtf"),
    @NamedQuery(name = "TDeclarationDou.findByTptTodCod", query = "SELECT t FROM TDeclarationDou t WHERE t.tptTodCod = :tptTodCod"),
    @NamedQuery(name = "TDeclarationDou.findByTptTodPlc", query = "SELECT t FROM TDeclarationDou t WHERE t.tptTodPlc = :tptTodPlc"),
    @NamedQuery(name = "TDeclarationDou.findByTptTodSit", query = "SELECT t FROM TDeclarationDou t WHERE t.tptTodSit = :tptTodSit"),
    @NamedQuery(name = "TDeclarationDou.findByTptCuoCod", query = "SELECT t FROM TDeclarationDou t WHERE t.tptCuoCod = :tptCuoCod"),
    @NamedQuery(name = "TDeclarationDou.findByTptCuoNam", query = "SELECT t FROM TDeclarationDou t WHERE t.tptCuoNam = :tptCuoNam"),
    @NamedQuery(name = "TDeclarationDou.findByTptLopCod", query = "SELECT t FROM TDeclarationDou t WHERE t.tptLopCod = :tptLopCod"),
    @NamedQuery(name = "TDeclarationDou.findByTptLopNam", query = "SELECT t FROM TDeclarationDou t WHERE t.tptLopNam = :tptLopNam"),
    @NamedQuery(name = "TDeclarationDou.findByTptLopCty", query = "SELECT t FROM TDeclarationDou t WHERE t.tptLopCty = :tptLopCty"),
    @NamedQuery(name = "TDeclarationDou.findByTptLoc", query = "SELECT t FROM TDeclarationDou t WHERE t.tptLoc = :tptLoc"),
    @NamedQuery(name = "TDeclarationDou.findByTptT1", query = "SELECT t FROM TDeclarationDou t WHERE t.tptT1 = :tptT1"),
    @NamedQuery(name = "TDeclarationDou.findByFinTraNa1", query = "SELECT t FROM TDeclarationDou t WHERE t.finTraNa1 = :finTraNa1"),
    @NamedQuery(name = "TDeclarationDou.findByFinTraNa2", query = "SELECT t FROM TDeclarationDou t WHERE t.finTraNa2 = :finTraNa2"),
    @NamedQuery(name = "TDeclarationDou.findByFinBnkCod", query = "SELECT t FROM TDeclarationDou t WHERE t.finBnkCod = :finBnkCod"),
    @NamedQuery(name = "TDeclarationDou.findByFinBnkBra", query = "SELECT t FROM TDeclarationDou t WHERE t.finBnkBra = :finBnkBra"),
    @NamedQuery(name = "TDeclarationDou.findByFinBnkFre", query = "SELECT t FROM TDeclarationDou t WHERE t.finBnkFre = :finBnkFre"),
    @NamedQuery(name = "TDeclarationDou.findByFinTopCod", query = "SELECT t FROM TDeclarationDou t WHERE t.finTopCod = :finTopCod"),
    @NamedQuery(name = "TDeclarationDou.findByFinTopNam", query = "SELECT t FROM TDeclarationDou t WHERE t.finTopNam = :finTopNam"),
    @NamedQuery(name = "TDeclarationDou.findByFinAcc", query = "SELECT t FROM TDeclarationDou t WHERE t.finAcc = :finAcc"),
    @NamedQuery(name = "TDeclarationDou.findByFinMpn", query = "SELECT t FROM TDeclarationDou t WHERE t.finMpn = :finMpn"),
    @NamedQuery(name = "TDeclarationDou.findByFinAmtMnl", query = "SELECT t FROM TDeclarationDou t WHERE t.finAmtMnl = :finAmtMnl"),
    @NamedQuery(name = "TDeclarationDou.findByFinAmtFee", query = "SELECT t FROM TDeclarationDou t WHERE t.finAmtFee = :finAmtFee"),
    @NamedQuery(name = "TDeclarationDou.findByFinAmtDty", query = "SELECT t FROM TDeclarationDou t WHERE t.finAmtDty = :finAmtDty"),
    @NamedQuery(name = "TDeclarationDou.findByFinAmtTbp", query = "SELECT t FROM TDeclarationDou t WHERE t.finAmtTbp = :finAmtTbp"),
    @NamedQuery(name = "TDeclarationDou.findByFinGtyNam", query = "SELECT t FROM TDeclarationDou t WHERE t.finGtyNam = :finGtyNam"),
    @NamedQuery(name = "TDeclarationDou.findByFinGtyAmt", query = "SELECT t FROM TDeclarationDou t WHERE t.finGtyAmt = :finGtyAmt"),
    @NamedQuery(name = "TDeclarationDou.findByFinGtyDat", query = "SELECT t FROM TDeclarationDou t WHERE t.finGtyDat = :finGtyDat"),
    @NamedQuery(name = "TDeclarationDou.findByFinGtyCtyCod", query = "SELECT t FROM TDeclarationDou t WHERE t.finGtyCtyCod = :finGtyCtyCod"),
    @NamedQuery(name = "TDeclarationDou.findByFinGtyCtyNam", query = "SELECT t FROM TDeclarationDou t WHERE t.finGtyCtyNam = :finGtyCtyNam"),
    @NamedQuery(name = "TDeclarationDou.findByVgsWrk", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsWrk = :vgsWrk"),
    @NamedQuery(name = "TDeclarationDou.findByVgsWgtGrs", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsWgtGrs = :vgsWgtGrs"),
    @NamedQuery(name = "TDeclarationDou.findByVgsCst", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsCst = :vgsCst"),
    @NamedQuery(name = "TDeclarationDou.findByVgsCif", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsCif = :vgsCif"),
    @NamedQuery(name = "TDeclarationDou.findByVgsTotNmu", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsTotNmu = :vgsTotNmu"),
    @NamedQuery(name = "TDeclarationDou.findByVgsTotFcx", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsTotFcx = :vgsTotFcx"),
    @NamedQuery(name = "TDeclarationDou.findByVgsTotGrs", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsTotGrs = :vgsTotGrs"),
    @NamedQuery(name = "TDeclarationDou.findByVgsInvAmtNmu", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsInvAmtNmu = :vgsInvAmtNmu"),
    @NamedQuery(name = "TDeclarationDou.findByVgsInvAmtFcx", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsInvAmtFcx = :vgsInvAmtFcx"),
    @NamedQuery(name = "TDeclarationDou.findByVgsInvCurCod", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsInvCurCod = :vgsInvCurCod"),
    @NamedQuery(name = "TDeclarationDou.findByVgsInvCurNam", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsInvCurNam = :vgsInvCurNam"),
    @NamedQuery(name = "TDeclarationDou.findByVgsInvCurRat", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsInvCurRat = :vgsInvCurRat"),
    @NamedQuery(name = "TDeclarationDou.findByVgsInvCurRef", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsInvCurRef = :vgsInvCurRef"),
    @NamedQuery(name = "TDeclarationDou.findByVgsEfrAmtNmu", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsEfrAmtNmu = :vgsEfrAmtNmu"),
    @NamedQuery(name = "TDeclarationDou.findByVgsEfrAmtFcx", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsEfrAmtFcx = :vgsEfrAmtFcx"),
    @NamedQuery(name = "TDeclarationDou.findByVgsEfrCurCod", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsEfrCurCod = :vgsEfrCurCod"),
    @NamedQuery(name = "TDeclarationDou.findByVgsEfrCurNam", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsEfrCurNam = :vgsEfrCurNam"),
    @NamedQuery(name = "TDeclarationDou.findByVgsEfrCurRat", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsEfrCurRat = :vgsEfrCurRat"),
    @NamedQuery(name = "TDeclarationDou.findByVgsEfrCurRef", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsEfrCurRef = :vgsEfrCurRef"),
    @NamedQuery(name = "TDeclarationDou.findByVgsIfrAmtNmu", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsIfrAmtNmu = :vgsIfrAmtNmu"),
    @NamedQuery(name = "TDeclarationDou.findByVgsIfrAmtFcx", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsIfrAmtFcx = :vgsIfrAmtFcx"),
    @NamedQuery(name = "TDeclarationDou.findByVgsIfrCurCod", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsIfrCurCod = :vgsIfrCurCod"),
    @NamedQuery(name = "TDeclarationDou.findByVgsIfrCurNam", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsIfrCurNam = :vgsIfrCurNam"),
    @NamedQuery(name = "TDeclarationDou.findByVgsIfrCurRat", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsIfrCurRat = :vgsIfrCurRat"),
    @NamedQuery(name = "TDeclarationDou.findByVgsIfrCurRef", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsIfrCurRef = :vgsIfrCurRef"),
    @NamedQuery(name = "TDeclarationDou.findByVgsInsAmtNmu", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsInsAmtNmu = :vgsInsAmtNmu"),
    @NamedQuery(name = "TDeclarationDou.findByVgsInsAmtFcx", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsInsAmtFcx = :vgsInsAmtFcx"),
    @NamedQuery(name = "TDeclarationDou.findByVgsInsCurCod", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsInsCurCod = :vgsInsCurCod"),
    @NamedQuery(name = "TDeclarationDou.findByVgsInsCurNam", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsInsCurNam = :vgsInsCurNam"),
    @NamedQuery(name = "TDeclarationDou.findByVgsInsCurRat", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsInsCurRat = :vgsInsCurRat"),
    @NamedQuery(name = "TDeclarationDou.findByVgsInsCurRef", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsInsCurRef = :vgsInsCurRef"),
    @NamedQuery(name = "TDeclarationDou.findByVgsOtcAmtNmu", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsOtcAmtNmu = :vgsOtcAmtNmu"),
    @NamedQuery(name = "TDeclarationDou.findByVgsOtcAmtFcx", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsOtcAmtFcx = :vgsOtcAmtFcx"),
    @NamedQuery(name = "TDeclarationDou.findByVgsOtcCurCod", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsOtcCurCod = :vgsOtcCurCod"),
    @NamedQuery(name = "TDeclarationDou.findByVgsOtcCurNam", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsOtcCurNam = :vgsOtcCurNam"),
    @NamedQuery(name = "TDeclarationDou.findByVgsOtcCurRat", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsOtcCurRat = :vgsOtcCurRat"),
    @NamedQuery(name = "TDeclarationDou.findByVgsOtcCurRef", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsOtcCurRef = :vgsOtcCurRef"),
    @NamedQuery(name = "TDeclarationDou.findByVgsDedAmtNmu", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsDedAmtNmu = :vgsDedAmtNmu"),
    @NamedQuery(name = "TDeclarationDou.findByVgsDedAmtFcx", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsDedAmtFcx = :vgsDedAmtFcx"),
    @NamedQuery(name = "TDeclarationDou.findByVgsDedCurCod", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsDedCurCod = :vgsDedCurCod"),
    @NamedQuery(name = "TDeclarationDou.findByVgsDedCurNam", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsDedCurNam = :vgsDedCurNam"),
    @NamedQuery(name = "TDeclarationDou.findByVgsDedCurRat", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsDedCurRat = :vgsDedCurRat"),
    @NamedQuery(name = "TDeclarationDou.findByVgsDedCurRef", query = "SELECT t FROM TDeclarationDou t WHERE t.vgsDedCurRef = :vgsDedCurRef"),
    @NamedQuery(name = "TDeclarationDou.findByWhsCod", query = "SELECT t FROM TDeclarationDou t WHERE t.whsCod = :whsCod"),
    @NamedQuery(name = "TDeclarationDou.findByWhsTim", query = "SELECT t FROM TDeclarationDou t WHERE t.whsTim = :whsTim"),
    @NamedQuery(name = "TDeclarationDou.findByTrsRspCod", query = "SELECT t FROM TDeclarationDou t WHERE t.trsRspCod = :trsRspCod"),
    @NamedQuery(name = "TDeclarationDou.findByTrsRspRep", query = "SELECT t FROM TDeclarationDou t WHERE t.trsRspRep = :trsRspRep"),
    @NamedQuery(name = "TDeclarationDou.findByTrsSgtPlc", query = "SELECT t FROM TDeclarationDou t WHERE t.trsSgtPlc = :trsSgtPlc"),
    @NamedQuery(name = "TDeclarationDou.findByTrsSgtDat", query = "SELECT t FROM TDeclarationDou t WHERE t.trsSgtDat = :trsSgtDat"),
    @NamedQuery(name = "TDeclarationDou.findByTrsDesCuo", query = "SELECT t FROM TDeclarationDou t WHERE t.trsDesCuo = :trsDesCuo"),
    @NamedQuery(name = "TDeclarationDou.findByTrsDesCod", query = "SELECT t FROM TDeclarationDou t WHERE t.trsDesCod = :trsDesCod"),
    @NamedQuery(name = "TDeclarationDou.findByTrsDesCty", query = "SELECT t FROM TDeclarationDou t WHERE t.trsDesCty = :trsDesCty"),
    @NamedQuery(name = "TDeclarationDou.findByTrsSlsNbr", query = "SELECT t FROM TDeclarationDou t WHERE t.trsSlsNbr = :trsSlsNbr"),
    @NamedQuery(name = "TDeclarationDou.findByTrsSlsIde", query = "SELECT t FROM TDeclarationDou t WHERE t.trsSlsIde = :trsSlsIde"),
    @NamedQuery(name = "TDeclarationDou.findByTrsCtl", query = "SELECT t FROM TDeclarationDou t WHERE t.trsCtl = :trsCtl"),
    @NamedQuery(name = "TDeclarationDou.findByTrsLim", query = "SELECT t FROM TDeclarationDou t WHERE t.trsLim = :trsLim"),
    @NamedQuery(name = "TDeclarationDou.findByTrsCof", query = "SELECT t FROM TDeclarationDou t WHERE t.trsCof = :trsCof"),
    @NamedQuery(name = "TDeclarationDou.findByAstRyr", query = "SELECT t FROM TDeclarationDou t WHERE t.astRyr = :astRyr"),
    @NamedQuery(name = "TDeclarationDou.findByAstAyr", query = "SELECT t FROM TDeclarationDou t WHERE t.astAyr = :astAyr"),
    @NamedQuery(name = "TDeclarationDou.findByAstTot", query = "SELECT t FROM TDeclarationDou t WHERE t.astTot = :astTot"),
    @NamedQuery(name = "TDeclarationDou.findByAstAmt", query = "SELECT t FROM TDeclarationDou t WHERE t.astAmt = :astAmt"),
    @NamedQuery(name = "TDeclarationDou.findByAstStn", query = "SELECT t FROM TDeclarationDou t WHERE t.astStn = :astStn"),
    @NamedQuery(name = "TDeclarationDou.findByAstStd", query = "SELECT t FROM TDeclarationDou t WHERE t.astStd = :astStd"),
    @NamedQuery(name = "TDeclarationDou.findByAstSts", query = "SELECT t FROM TDeclarationDou t WHERE t.astSts = :astSts"),
    @NamedQuery(name = "TDeclarationDou.findByRloNbr", query = "SELECT t FROM TDeclarationDou t WHERE t.rloNbr = :rloNbr"),
    @NamedQuery(name = "TDeclarationDou.findByExaSec", query = "SELECT t FROM TDeclarationDou t WHERE t.exaSec = :exaSec"),
    @NamedQuery(name = "TDeclarationDou.findByExaExa", query = "SELECT t FROM TDeclarationDou t WHERE t.exaExa = :exaExa"),
    @NamedQuery(name = "TDeclarationDou.findByExaCex", query = "SELECT t FROM TDeclarationDou t WHERE t.exaCex = :exaCex"),
    @NamedQuery(name = "TDeclarationDou.findByExaWgt", query = "SELECT t FROM TDeclarationDou t WHERE t.exaWgt = :exaWgt"),
    @NamedQuery(name = "TDeclarationDou.findByLstRcpAmt", query = "SELECT t FROM TDeclarationDou t WHERE t.lstRcpAmt = :lstRcpAmt"),
    @NamedQuery(name = "TDeclarationDou.findByLstRcpSer", query = "SELECT t FROM TDeclarationDou t WHERE t.lstRcpSer = :lstRcpSer"),
    @NamedQuery(name = "TDeclarationDou.findByLstRcpNbr", query = "SELECT t FROM TDeclarationDou t WHERE t.lstRcpNbr = :lstRcpNbr"),
    @NamedQuery(name = "TDeclarationDou.findByLstRcpDat", query = "SELECT t FROM TDeclarationDou t WHERE t.lstRcpDat = :lstRcpDat"),
    @NamedQuery(name = "TDeclarationDou.findByLstRcpTyp", query = "SELECT t FROM TDeclarationDou t WHERE t.lstRcpTyp = :lstRcpTyp"),
    @NamedQuery(name = "TDeclarationDou.findByLstRcpCuo", query = "SELECT t FROM TDeclarationDou t WHERE t.lstRcpCuo = :lstRcpCuo")})
public class TDeclarationDou implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "INSTANCEID")
    private Long instanceid;
    @Size(max = 5)
    @Column(name = "RLS_CUO_COD")
    private String rlsCuoCod;
    @Size(max = 35)
    @Column(name = "RLS_CUO_NAM")
    private String rlsCuoNam;
    @Size(max = 35)
    @Column(name = "RLS_REF")
    private String rlsRef;
    @Size(max = 35)
    @Column(name = "RLS_INF")
    private String rlsInf;
    @Column(name = "RLS_DAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rlsDat;
    @Column(name = "RLS_TIM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rlsTim;
    @Column(name = "PRV_DTY")
    private BigInteger prvDty;
    @Size(max = 35)
    @Column(name = "PTY_COL_DSC")
    private String ptyColDsc;
    @Size(max = 1)
    @Column(name = "PTY_COL_IND")
    private String ptyColInd;
    @Column(name = "PTY_BLU")
    private Long ptyBlu;
    @Column(name = "PTY_RED")
    private Long ptyRed;
    @Column(name = "PTY_YEL")
    private Long ptyYel;
    @Column(name = "PTY_GRE")
    private Long ptyGre;
    @Lob
    @Column(name = "PTY_OAS")
    private String ptyOas;
    @Column(name = "PTY_QUE")
    private Long ptyQue;
    @Column(name = "PTY_WDE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ptyWde;
    @Column(name = "PTY_FRM_NBR")
    private Long ptyFrmNbr;
    @Column(name = "PTY_FRM_TOT")
    private Long ptyFrmTot;
    @Column(name = "PTY_NBR_LDL")
    private Long ptyNbrLdl;
    @Column(name = "PTY_NBR_ITM")
    private Long ptyNbrItm;
    @Column(name = "PTY_NBR_PCK")
    private Long ptyNbrPck;
    @Size(max = 35)
    @Column(name = "PTY_PLC")
    private String ptyPlc;
    @Column(name = "PTY_CDE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ptyCde;
    @Size(max = 5)
    @Column(name = "IDE_CUO_COD")
    private String ideCuoCod;
    @Size(max = 35)
    @Column(name = "IDE_CUO_NAM")
    private String ideCuoNam;
    @Size(max = 3)
    @Column(name = "IDE_TYP_SAD")
    private String ideTypSad;
    @Size(max = 1)
    @Column(name = "IDE_TYP_PRC")
    private String ideTypPrc;
    @Size(max = 1)
    @Column(name = "IDE_TYP_TYP")
    private String ideTypTyp;
    @Size(max = 5)
    @Column(name = "IDE_TYP_TRS")
    private String ideTypTrs;
    @Size(max = 28)
    @Column(name = "IDE_MAN")
    private String ideMan;
    @Size(max = 60)
    @Column(name = "IDE_BAR")
    private String ideBar;
    @Size(max = 1)
    @Column(name = "IDE_REG_SER")
    private String ideRegSer;
    @Size(max = 50)
    @Column(name = "IDE_REG_NBR")
    private String ideRegNbr;
    @Column(name = "IDE_REG_DAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ideRegDat;
    @Size(max = 1)
    @Column(name = "IDE_AST_SER")
    private String ideAstSer;
    @Size(max = 50)
    @Column(name = "IDE_AST_NBR")
    private String ideAstNbr;
    @Column(name = "IDE_AST_DAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ideAstDat;
    @Size(max = 1)
    @Column(name = "IDE_RCP_SER")
    private String ideRcpSer;
    @Size(max = 50)
    @Column(name = "IDE_RCP_NBR")
    private String ideRcpNbr;
    @Column(name = "IDE_RCP_DAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ideRcpDat;
    @Size(max = 1)
    @Column(name = "IDE_RCP_TYP")
    private String ideRcpTyp;
    @Size(max = 5)
    @Column(name = "IDE_RCP_CUO")
    private String ideRcpCuo;
    @Size(max = 50)
    @Column(name = "IDE_PST_NBR")
    private String idePstNbr;
    @Column(name = "IDE_PST_DAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date idePstDat;
    @Size(max = 50)
    @Column(name = "IDE_PST_TMP_PRE")
    private String idePstTmpPre;
    @Size(max = 1)
    @Column(name = "IDE_PST_TMP_TY1")
    private String idePstTmpTy1;
    @Size(max = 1)
    @Column(name = "IDE_PST_TMP_TY2")
    private String idePstTmpTy2;
    @Size(max = 17)
    @Column(name = "CMP_EXP_COD")
    private String cmpExpCod;
    @Lob
    @Column(name = "CMP_EXP_NAM")
    private String cmpExpNam;
    @Size(max = 17)
    @Column(name = "CMP_CON_COD")
    private String cmpConCod;
    @Lob
    @Column(name = "CMP_CON_NAM")
    private String cmpConNam;
    @Size(max = 17)
    @Column(name = "CMP_FIS_COD")
    private String cmpFisCod;
    @Lob
    @Column(name = "CMP_FIS_NAM")
    private String cmpFisNam;
    @Size(max = 1)
    @Column(name = "DEC_FLG")
    private String decFlg;
    @Size(max = 17)
    @Column(name = "DEC_COD")
    private String decCod;
    @Lob
    @Column(name = "DEC_NAM")
    private String decNam;
    @Column(name = "DEC_REF_YER")
    private Long decRefYer;
    @Size(max = 50)
    @Column(name = "DEC_REF_NBR")
    private String decRefNbr;
    @Size(max = 80)
    @Column(name = "DEC_REP")
    private String decRep;
    @Size(max = 3)
    @Column(name = "GEN_CTY_FLT")
    private String genCtyFlt;
    @Size(max = 3)
    @Column(name = "GEN_CTY_TRD")
    private String genCtyTrd;
    @Size(max = 3)
    @Column(name = "GEN_CTY_EPT_COD")
    private String genCtyEptCod;
    @Size(max = 35)
    @Column(name = "GEN_CTY_EPT_NAM")
    private String genCtyEptNam;
    @Size(max = 4)
    @Column(name = "GEN_CTY_EPT_CRG")
    private String genCtyEptCrg;
    @Size(max = 3)
    @Column(name = "GEN_CTY_DES_COD")
    private String genCtyDesCod;
    @Size(max = 35)
    @Column(name = "GEN_CTY_DES_NAM")
    private String genCtyDesNam;
    @Size(max = 4)
    @Column(name = "GEN_CTY_DES_CRG")
    private String genCtyDesCrg;
    @Size(max = 35)
    @Column(name = "GEN_CTY_ORG")
    private String genCtyOrg;
    @Column(name = "GEN_VDT")
    private BigInteger genVdt;
    @Size(max = 20)
    @Column(name = "GEN_CAP")
    private String genCap;
    @Lob
    @Column(name = "GEN_FRE")
    private String genFre;
    @Size(max = 35)
    @Column(name = "TPT_MOT_DPA_NAM")
    private String tptMotDpaNam;
    @Size(max = 3)
    @Column(name = "TPT_MOT_DPA_CTY")
    private String tptMotDpaCty;
    @Size(max = 35)
    @Column(name = "TPT_MOT_BRD_NAM")
    private String tptMotBrdNam;
    @Size(max = 3)
    @Column(name = "TPT_MOT_BRD_CTY")
    private String tptMotBrdCty;
    @Size(max = 2)
    @Column(name = "TPT_MOT_BRD_COD")
    private String tptMotBrdCod;
    @Size(max = 2)
    @Column(name = "TPT_MOT_INL")
    private String tptMotInl;
    @Column(name = "TPT_CTF")
    private Short tptCtf;
    @Size(max = 3)
    @Column(name = "TPT_TOD_COD")
    private String tptTodCod;
    @Size(max = 35)
    @Column(name = "TPT_TOD_PLC")
    private String tptTodPlc;
    @Size(max = 2)
    @Column(name = "TPT_TOD_SIT")
    private String tptTodSit;
    @Size(max = 5)
    @Column(name = "TPT_CUO_COD")
    private String tptCuoCod;
    @Size(max = 35)
    @Column(name = "TPT_CUO_NAM")
    private String tptCuoNam;
    @Size(max = 5)
    @Column(name = "TPT_LOP_COD")
    private String tptLopCod;
    @Size(max = 35)
    @Column(name = "TPT_LOP_NAM")
    private String tptLopNam;
    @Size(max = 3)
    @Column(name = "TPT_LOP_CTY")
    private String tptLopCty;
    @Size(max = 17)
    @Column(name = "TPT_LOC")
    private String tptLoc;
    @Column(name = "TPT_T1")
    private Long tptT1;
    @Size(max = 1)
    @Column(name = "FIN_TRA_NA1")
    private String finTraNa1;
    @Size(max = 1)
    @Column(name = "FIN_TRA_NA2")
    private String finTraNa2;
    @Size(max = 17)
    @Column(name = "FIN_BNK_COD")
    private String finBnkCod;
    @Lob
    @Column(name = "FIN_BNK_NAM")
    private String finBnkNam;
    @Size(max = 17)
    @Column(name = "FIN_BNK_BRA")
    private String finBnkBra;
    @Size(max = 35)
    @Column(name = "FIN_BNK_FRE")
    private String finBnkFre;
    @Size(max = 3)
    @Column(name = "FIN_TOP_COD")
    private String finTopCod;
    @Size(max = 35)
    @Column(name = "FIN_TOP_NAM")
    private String finTopNam;
    @Size(max = 17)
    @Column(name = "FIN_ACC")
    private String finAcc;
    @Size(max = 35)
    @Column(name = "FIN_MPN")
    private String finMpn;
    @Column(name = "FIN_AMT_MNL")
    private BigInteger finAmtMnl;
    @Column(name = "FIN_AMT_FEE")
    private BigInteger finAmtFee;
    @Column(name = "FIN_AMT_DTY")
    private BigInteger finAmtDty;
    @Column(name = "FIN_AMT_TBP")
    private BigInteger finAmtTbp;
    @Size(max = 35)
    @Column(name = "FIN_GTY_NAM")
    private String finGtyNam;
    @Column(name = "FIN_GTY_AMT")
    private BigInteger finGtyAmt;
    @Column(name = "FIN_GTY_DAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finGtyDat;
    @Size(max = 3)
    @Column(name = "FIN_GTY_CTY_COD")
    private String finGtyCtyCod;
    @Size(max = 35)
    @Column(name = "FIN_GTY_CTY_NAM")
    private String finGtyCtyNam;
    @Size(max = 1)
    @Column(name = "VGS_WRK")
    private String vgsWrk;
    @Column(name = "VGS_WGT_GRS")
    private BigInteger vgsWgtGrs;
    @Column(name = "VGS_CST")
    private BigInteger vgsCst;
    @Column(name = "VGS_CIF")
    private BigInteger vgsCif;
    @Column(name = "VGS_TOT_NMU")
    private BigInteger vgsTotNmu;
    @Column(name = "VGS_TOT_FCX")
    private BigInteger vgsTotFcx;
    @Column(name = "VGS_TOT_GRS")
    private BigInteger vgsTotGrs;
    @Column(name = "VGS_INV_AMT_NMU")
    private BigInteger vgsInvAmtNmu;
    @Column(name = "VGS_INV_AMT_FCX")
    private BigInteger vgsInvAmtFcx;
    @Size(max = 3)
    @Column(name = "VGS_INV_CUR_COD")
    private String vgsInvCurCod;
    @Size(max = 35)
    @Column(name = "VGS_INV_CUR_NAM")
    private String vgsInvCurNam;
    @Column(name = "VGS_INV_CUR_RAT")
    private BigInteger vgsInvCurRat;
    @Column(name = "VGS_INV_CUR_REF")
    private BigInteger vgsInvCurRef;
    @Column(name = "VGS_EFR_AMT_NMU")
    private BigInteger vgsEfrAmtNmu;
    @Column(name = "VGS_EFR_AMT_FCX")
    private BigInteger vgsEfrAmtFcx;
    @Size(max = 3)
    @Column(name = "VGS_EFR_CUR_COD")
    private String vgsEfrCurCod;
    @Size(max = 35)
    @Column(name = "VGS_EFR_CUR_NAM")
    private String vgsEfrCurNam;
    @Column(name = "VGS_EFR_CUR_RAT")
    private BigInteger vgsEfrCurRat;
    @Column(name = "VGS_EFR_CUR_REF")
    private BigInteger vgsEfrCurRef;
    @Column(name = "VGS_IFR_AMT_NMU")
    private BigInteger vgsIfrAmtNmu;
    @Column(name = "VGS_IFR_AMT_FCX")
    private BigInteger vgsIfrAmtFcx;
    @Size(max = 3)
    @Column(name = "VGS_IFR_CUR_COD")
    private String vgsIfrCurCod;
    @Size(max = 35)
    @Column(name = "VGS_IFR_CUR_NAM")
    private String vgsIfrCurNam;
    @Column(name = "VGS_IFR_CUR_RAT")
    private BigInteger vgsIfrCurRat;
    @Column(name = "VGS_IFR_CUR_REF")
    private BigInteger vgsIfrCurRef;
    @Column(name = "VGS_INS_AMT_NMU")
    private BigInteger vgsInsAmtNmu;
    @Column(name = "VGS_INS_AMT_FCX")
    private BigInteger vgsInsAmtFcx;
    @Size(max = 3)
    @Column(name = "VGS_INS_CUR_COD")
    private String vgsInsCurCod;
    @Size(max = 35)
    @Column(name = "VGS_INS_CUR_NAM")
    private String vgsInsCurNam;
    @Column(name = "VGS_INS_CUR_RAT")
    private BigInteger vgsInsCurRat;
    @Column(name = "VGS_INS_CUR_REF")
    private BigInteger vgsInsCurRef;
    @Column(name = "VGS_OTC_AMT_NMU")
    private BigInteger vgsOtcAmtNmu;
    @Column(name = "VGS_OTC_AMT_FCX")
    private BigInteger vgsOtcAmtFcx;
    @Size(max = 3)
    @Column(name = "VGS_OTC_CUR_COD")
    private String vgsOtcCurCod;
    @Size(max = 35)
    @Column(name = "VGS_OTC_CUR_NAM")
    private String vgsOtcCurNam;
    @Column(name = "VGS_OTC_CUR_RAT")
    private BigInteger vgsOtcCurRat;
    @Column(name = "VGS_OTC_CUR_REF")
    private BigInteger vgsOtcCurRef;
    @Column(name = "VGS_DED_AMT_NMU")
    private BigInteger vgsDedAmtNmu;
    @Column(name = "VGS_DED_AMT_FCX")
    private BigInteger vgsDedAmtFcx;
    @Size(max = 3)
    @Column(name = "VGS_DED_CUR_COD")
    private String vgsDedCurCod;
    @Size(max = 35)
    @Column(name = "VGS_DED_CUR_NAM")
    private String vgsDedCurNam;
    @Column(name = "VGS_DED_CUR_RAT")
    private BigInteger vgsDedCurRat;
    @Column(name = "VGS_DED_CUR_REF")
    private BigInteger vgsDedCurRef;
    @Size(max = 17)
    @Column(name = "WHS_COD")
    private String whsCod;
    @Column(name = "WHS_TIM")
    private Long whsTim;
    @Size(max = 17)
    @Column(name = "TRS_RSP_COD")
    private String trsRspCod;
    @Lob
    @Column(name = "TRS_RSP_NAM")
    private String trsRspNam;
    @Size(max = 35)
    @Column(name = "TRS_RSP_REP")
    private String trsRspRep;
    @Size(max = 17)
    @Column(name = "TRS_SGT_PLC")
    private String trsSgtPlc;
    @Column(name = "TRS_SGT_DAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trsSgtDat;
    @Size(max = 35)
    @Column(name = "TRS_DES_CUO")
    private String trsDesCuo;
    @Size(max = 5)
    @Column(name = "TRS_DES_COD")
    private String trsDesCod;
    @Size(max = 3)
    @Column(name = "TRS_DES_CTY")
    private String trsDesCty;
    @Column(name = "TRS_SLS_NBR")
    private Long trsSlsNbr;
    @Size(max = 17)
    @Column(name = "TRS_SLS_IDE")
    private String trsSlsIde;
    @Size(max = 11)
    @Column(name = "TRS_CTL")
    private String trsCtl;
    @Column(name = "TRS_LIM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trsLim;
    @Size(max = 35)
    @Column(name = "TRS_COF")
    private String trsCof;
    @Size(max = 4)
    @Column(name = "AST_RYR")
    private String astRyr;
    @Size(max = 4)
    @Column(name = "AST_AYR")
    private String astAyr;
    @Column(name = "AST_TOT")
    private BigInteger astTot;
    @Column(name = "AST_AMT")
    private BigInteger astAmt;
    @Size(max = 11)
    @Column(name = "AST_STN")
    private String astStn;
    @Column(name = "AST_STD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date astStd;
    @Size(max = 1)
    @Column(name = "AST_STS")
    private String astSts;
    @Column(name = "RLO_NBR")
    private Long rloNbr;
    @Size(max = 17)
    @Column(name = "EXA_SEC")
    private String exaSec;
    @Size(max = 17)
    @Column(name = "EXA_EXA")
    private String exaExa;
    @Size(max = 17)
    @Column(name = "EXA_CEX")
    private String exaCex;
    @Column(name = "EXA_WGT")
    private Long exaWgt;
    @Column(name = "LST_RCP_AMT")
    private BigInteger lstRcpAmt;
    @Size(max = 1)
    @Column(name = "LST_RCP_SER")
    private String lstRcpSer;
    @Size(max = 50)
    @Column(name = "LST_RCP_NBR")
    private String lstRcpNbr;
    @Column(name = "LST_RCP_DAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lstRcpDat;
    @Size(max = 1)
    @Column(name = "LST_RCP_TYP")
    private String lstRcpTyp;
    @Size(max = 5)
    @Column(name = "LST_RCP_CUO")
    private String lstRcpCuo;
    @Lob
    @Column(name = "BROKER_STAMP")
    private Serializable brokerStamp;
    @Lob
    @Column(name = "CUSTOMS_STAMP")
    private Serializable customsStamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDeclarationDou")
    private List<TArticle> tArticleList;

    public TDeclarationDou() {
    }

    public TDeclarationDou(Long instanceid) {
        this.instanceid = instanceid;
    }

    public Long getInstanceid() {
        return instanceid;
    }

    public void setInstanceid(Long instanceid) {
        this.instanceid = instanceid;
    }

    public String getRlsCuoCod() {
        return rlsCuoCod;
    }

    public void setRlsCuoCod(String rlsCuoCod) {
        this.rlsCuoCod = rlsCuoCod;
    }

    public String getRlsCuoNam() {
        return rlsCuoNam;
    }

    public void setRlsCuoNam(String rlsCuoNam) {
        this.rlsCuoNam = rlsCuoNam;
    }

    public String getRlsRef() {
        return rlsRef;
    }

    public void setRlsRef(String rlsRef) {
        this.rlsRef = rlsRef;
    }

    public String getRlsInf() {
        return rlsInf;
    }

    public void setRlsInf(String rlsInf) {
        this.rlsInf = rlsInf;
    }

    public Date getRlsDat() {
        return rlsDat;
    }

    public void setRlsDat(Date rlsDat) {
        this.rlsDat = rlsDat;
    }

    public Date getRlsTim() {
        return rlsTim;
    }

    public void setRlsTim(Date rlsTim) {
        this.rlsTim = rlsTim;
    }

    public BigInteger getPrvDty() {
        return prvDty;
    }

    public void setPrvDty(BigInteger prvDty) {
        this.prvDty = prvDty;
    }

    public String getPtyColDsc() {
        return ptyColDsc;
    }

    public void setPtyColDsc(String ptyColDsc) {
        this.ptyColDsc = ptyColDsc;
    }

    public String getPtyColInd() {
        return ptyColInd;
    }

    public void setPtyColInd(String ptyColInd) {
        this.ptyColInd = ptyColInd;
    }

    public Long getPtyBlu() {
        return ptyBlu;
    }

    public void setPtyBlu(Long ptyBlu) {
        this.ptyBlu = ptyBlu;
    }

    public Long getPtyRed() {
        return ptyRed;
    }

    public void setPtyRed(Long ptyRed) {
        this.ptyRed = ptyRed;
    }

    public Long getPtyYel() {
        return ptyYel;
    }

    public void setPtyYel(Long ptyYel) {
        this.ptyYel = ptyYel;
    }

    public Long getPtyGre() {
        return ptyGre;
    }

    public void setPtyGre(Long ptyGre) {
        this.ptyGre = ptyGre;
    }

    public String getPtyOas() {
        return ptyOas;
    }

    public void setPtyOas(String ptyOas) {
        this.ptyOas = ptyOas;
    }

    public Long getPtyQue() {
        return ptyQue;
    }

    public void setPtyQue(Long ptyQue) {
        this.ptyQue = ptyQue;
    }

    public Date getPtyWde() {
        return ptyWde;
    }

    public void setPtyWde(Date ptyWde) {
        this.ptyWde = ptyWde;
    }

    public Long getPtyFrmNbr() {
        return ptyFrmNbr;
    }

    public void setPtyFrmNbr(Long ptyFrmNbr) {
        this.ptyFrmNbr = ptyFrmNbr;
    }

    public Long getPtyFrmTot() {
        return ptyFrmTot;
    }

    public void setPtyFrmTot(Long ptyFrmTot) {
        this.ptyFrmTot = ptyFrmTot;
    }

    public Long getPtyNbrLdl() {
        return ptyNbrLdl;
    }

    public void setPtyNbrLdl(Long ptyNbrLdl) {
        this.ptyNbrLdl = ptyNbrLdl;
    }

    public Long getPtyNbrItm() {
        return ptyNbrItm;
    }

    public void setPtyNbrItm(Long ptyNbrItm) {
        this.ptyNbrItm = ptyNbrItm;
    }

    public Long getPtyNbrPck() {
        return ptyNbrPck;
    }

    public void setPtyNbrPck(Long ptyNbrPck) {
        this.ptyNbrPck = ptyNbrPck;
    }

    public String getPtyPlc() {
        return ptyPlc;
    }

    public void setPtyPlc(String ptyPlc) {
        this.ptyPlc = ptyPlc;
    }

    public Date getPtyCde() {
        return ptyCde;
    }

    public void setPtyCde(Date ptyCde) {
        this.ptyCde = ptyCde;
    }

    public String getIdeCuoCod() {
        return ideCuoCod;
    }

    public void setIdeCuoCod(String ideCuoCod) {
        this.ideCuoCod = ideCuoCod;
    }

    public String getIdeCuoNam() {
        return ideCuoNam;
    }

    public void setIdeCuoNam(String ideCuoNam) {
        this.ideCuoNam = ideCuoNam;
    }

    public String getIdeTypSad() {
        return ideTypSad;
    }

    public void setIdeTypSad(String ideTypSad) {
        this.ideTypSad = ideTypSad;
    }

    public String getIdeTypPrc() {
        return ideTypPrc;
    }

    public void setIdeTypPrc(String ideTypPrc) {
        this.ideTypPrc = ideTypPrc;
    }

    public String getIdeTypTyp() {
        return ideTypTyp;
    }

    public void setIdeTypTyp(String ideTypTyp) {
        this.ideTypTyp = ideTypTyp;
    }

    public String getIdeTypTrs() {
        return ideTypTrs;
    }

    public void setIdeTypTrs(String ideTypTrs) {
        this.ideTypTrs = ideTypTrs;
    }

    public String getIdeMan() {
        return ideMan;
    }

    public void setIdeMan(String ideMan) {
        this.ideMan = ideMan;
    }

    public String getIdeBar() {
        return ideBar;
    }

    public void setIdeBar(String ideBar) {
        this.ideBar = ideBar;
    }

    public String getIdeRegSer() {
        return ideRegSer;
    }

    public void setIdeRegSer(String ideRegSer) {
        this.ideRegSer = ideRegSer;
    }

    public String getIdeRegNbr() {
        return ideRegNbr;
    }

    public void setIdeRegNbr(String ideRegNbr) {
        this.ideRegNbr = ideRegNbr;
    }

    public Date getIdeRegDat() {
        return ideRegDat;
    }

    public void setIdeRegDat(Date ideRegDat) {
        this.ideRegDat = ideRegDat;
    }

    public String getIdeAstSer() {
        return ideAstSer;
    }

    public void setIdeAstSer(String ideAstSer) {
        this.ideAstSer = ideAstSer;
    }

    public String getIdeAstNbr() {
        return ideAstNbr;
    }

    public void setIdeAstNbr(String ideAstNbr) {
        this.ideAstNbr = ideAstNbr;
    }

    public Date getIdeAstDat() {
        return ideAstDat;
    }

    public void setIdeAstDat(Date ideAstDat) {
        this.ideAstDat = ideAstDat;
    }

    public String getIdeRcpSer() {
        return ideRcpSer;
    }

    public void setIdeRcpSer(String ideRcpSer) {
        this.ideRcpSer = ideRcpSer;
    }

    public String getIdeRcpNbr() {
        return ideRcpNbr;
    }

    public void setIdeRcpNbr(String ideRcpNbr) {
        this.ideRcpNbr = ideRcpNbr;
    }

    public Date getIdeRcpDat() {
        return ideRcpDat;
    }

    public void setIdeRcpDat(Date ideRcpDat) {
        this.ideRcpDat = ideRcpDat;
    }

    public String getIdeRcpTyp() {
        return ideRcpTyp;
    }

    public void setIdeRcpTyp(String ideRcpTyp) {
        this.ideRcpTyp = ideRcpTyp;
    }

    public String getIdeRcpCuo() {
        return ideRcpCuo;
    }

    public void setIdeRcpCuo(String ideRcpCuo) {
        this.ideRcpCuo = ideRcpCuo;
    }

    public String getIdePstNbr() {
        return idePstNbr;
    }

    public void setIdePstNbr(String idePstNbr) {
        this.idePstNbr = idePstNbr;
    }

    public Date getIdePstDat() {
        return idePstDat;
    }

    public void setIdePstDat(Date idePstDat) {
        this.idePstDat = idePstDat;
    }

    public String getIdePstTmpPre() {
        return idePstTmpPre;
    }

    public void setIdePstTmpPre(String idePstTmpPre) {
        this.idePstTmpPre = idePstTmpPre;
    }

    public String getIdePstTmpTy1() {
        return idePstTmpTy1;
    }

    public void setIdePstTmpTy1(String idePstTmpTy1) {
        this.idePstTmpTy1 = idePstTmpTy1;
    }

    public String getIdePstTmpTy2() {
        return idePstTmpTy2;
    }

    public void setIdePstTmpTy2(String idePstTmpTy2) {
        this.idePstTmpTy2 = idePstTmpTy2;
    }

    public String getCmpExpCod() {
        return cmpExpCod;
    }

    public void setCmpExpCod(String cmpExpCod) {
        this.cmpExpCod = cmpExpCod;
    }

    public String getCmpExpNam() {
        return cmpExpNam;
    }

    public void setCmpExpNam(String cmpExpNam) {
        this.cmpExpNam = cmpExpNam;
    }

    public String getCmpConCod() {
        return cmpConCod;
    }

    public void setCmpConCod(String cmpConCod) {
        this.cmpConCod = cmpConCod;
    }

    public String getCmpConNam() {
        return cmpConNam;
    }

    public void setCmpConNam(String cmpConNam) {
        this.cmpConNam = cmpConNam;
    }

    public String getCmpFisCod() {
        return cmpFisCod;
    }

    public void setCmpFisCod(String cmpFisCod) {
        this.cmpFisCod = cmpFisCod;
    }

    public String getCmpFisNam() {
        return cmpFisNam;
    }

    public void setCmpFisNam(String cmpFisNam) {
        this.cmpFisNam = cmpFisNam;
    }

    public String getDecFlg() {
        return decFlg;
    }

    public void setDecFlg(String decFlg) {
        this.decFlg = decFlg;
    }

    public String getDecCod() {
        return decCod;
    }

    public void setDecCod(String decCod) {
        this.decCod = decCod;
    }

    public String getDecNam() {
        return decNam;
    }

    public void setDecNam(String decNam) {
        this.decNam = decNam;
    }

    public Long getDecRefYer() {
        return decRefYer;
    }

    public void setDecRefYer(Long decRefYer) {
        this.decRefYer = decRefYer;
    }

    public String getDecRefNbr() {
        return decRefNbr;
    }

    public void setDecRefNbr(String decRefNbr) {
        this.decRefNbr = decRefNbr;
    }

    public String getDecRep() {
        return decRep;
    }

    public void setDecRep(String decRep) {
        this.decRep = decRep;
    }

    public String getGenCtyFlt() {
        return genCtyFlt;
    }

    public void setGenCtyFlt(String genCtyFlt) {
        this.genCtyFlt = genCtyFlt;
    }

    public String getGenCtyTrd() {
        return genCtyTrd;
    }

    public void setGenCtyTrd(String genCtyTrd) {
        this.genCtyTrd = genCtyTrd;
    }

    public String getGenCtyEptCod() {
        return genCtyEptCod;
    }

    public void setGenCtyEptCod(String genCtyEptCod) {
        this.genCtyEptCod = genCtyEptCod;
    }

    public String getGenCtyEptNam() {
        return genCtyEptNam;
    }

    public void setGenCtyEptNam(String genCtyEptNam) {
        this.genCtyEptNam = genCtyEptNam;
    }

    public String getGenCtyEptCrg() {
        return genCtyEptCrg;
    }

    public void setGenCtyEptCrg(String genCtyEptCrg) {
        this.genCtyEptCrg = genCtyEptCrg;
    }

    public String getGenCtyDesCod() {
        return genCtyDesCod;
    }

    public void setGenCtyDesCod(String genCtyDesCod) {
        this.genCtyDesCod = genCtyDesCod;
    }

    public String getGenCtyDesNam() {
        return genCtyDesNam;
    }

    public void setGenCtyDesNam(String genCtyDesNam) {
        this.genCtyDesNam = genCtyDesNam;
    }

    public String getGenCtyDesCrg() {
        return genCtyDesCrg;
    }

    public void setGenCtyDesCrg(String genCtyDesCrg) {
        this.genCtyDesCrg = genCtyDesCrg;
    }

    public String getGenCtyOrg() {
        return genCtyOrg;
    }

    public void setGenCtyOrg(String genCtyOrg) {
        this.genCtyOrg = genCtyOrg;
    }

    public BigInteger getGenVdt() {
        return genVdt;
    }

    public void setGenVdt(BigInteger genVdt) {
        this.genVdt = genVdt;
    }

    public String getGenCap() {
        return genCap;
    }

    public void setGenCap(String genCap) {
        this.genCap = genCap;
    }

    public String getGenFre() {
        return genFre;
    }

    public void setGenFre(String genFre) {
        this.genFre = genFre;
    }

    public String getTptMotDpaNam() {
        return tptMotDpaNam;
    }

    public void setTptMotDpaNam(String tptMotDpaNam) {
        this.tptMotDpaNam = tptMotDpaNam;
    }

    public String getTptMotDpaCty() {
        return tptMotDpaCty;
    }

    public void setTptMotDpaCty(String tptMotDpaCty) {
        this.tptMotDpaCty = tptMotDpaCty;
    }

    public String getTptMotBrdNam() {
        return tptMotBrdNam;
    }

    public void setTptMotBrdNam(String tptMotBrdNam) {
        this.tptMotBrdNam = tptMotBrdNam;
    }

    public String getTptMotBrdCty() {
        return tptMotBrdCty;
    }

    public void setTptMotBrdCty(String tptMotBrdCty) {
        this.tptMotBrdCty = tptMotBrdCty;
    }

    public String getTptMotBrdCod() {
        return tptMotBrdCod;
    }

    public void setTptMotBrdCod(String tptMotBrdCod) {
        this.tptMotBrdCod = tptMotBrdCod;
    }

    public String getTptMotInl() {
        return tptMotInl;
    }

    public void setTptMotInl(String tptMotInl) {
        this.tptMotInl = tptMotInl;
    }

    public Short getTptCtf() {
        return tptCtf;
    }

    public void setTptCtf(Short tptCtf) {
        this.tptCtf = tptCtf;
    }

    public String getTptTodCod() {
        return tptTodCod;
    }

    public void setTptTodCod(String tptTodCod) {
        this.tptTodCod = tptTodCod;
    }

    public String getTptTodPlc() {
        return tptTodPlc;
    }

    public void setTptTodPlc(String tptTodPlc) {
        this.tptTodPlc = tptTodPlc;
    }

    public String getTptTodSit() {
        return tptTodSit;
    }

    public void setTptTodSit(String tptTodSit) {
        this.tptTodSit = tptTodSit;
    }

    public String getTptCuoCod() {
        return tptCuoCod;
    }

    public void setTptCuoCod(String tptCuoCod) {
        this.tptCuoCod = tptCuoCod;
    }

    public String getTptCuoNam() {
        return tptCuoNam;
    }

    public void setTptCuoNam(String tptCuoNam) {
        this.tptCuoNam = tptCuoNam;
    }

    public String getTptLopCod() {
        return tptLopCod;
    }

    public void setTptLopCod(String tptLopCod) {
        this.tptLopCod = tptLopCod;
    }

    public String getTptLopNam() {
        return tptLopNam;
    }

    public void setTptLopNam(String tptLopNam) {
        this.tptLopNam = tptLopNam;
    }

    public String getTptLopCty() {
        return tptLopCty;
    }

    public void setTptLopCty(String tptLopCty) {
        this.tptLopCty = tptLopCty;
    }

    public String getTptLoc() {
        return tptLoc;
    }

    public void setTptLoc(String tptLoc) {
        this.tptLoc = tptLoc;
    }

    public Long getTptT1() {
        return tptT1;
    }

    public void setTptT1(Long tptT1) {
        this.tptT1 = tptT1;
    }

    public String getFinTraNa1() {
        return finTraNa1;
    }

    public void setFinTraNa1(String finTraNa1) {
        this.finTraNa1 = finTraNa1;
    }

    public String getFinTraNa2() {
        return finTraNa2;
    }

    public void setFinTraNa2(String finTraNa2) {
        this.finTraNa2 = finTraNa2;
    }

    public String getFinBnkCod() {
        return finBnkCod;
    }

    public void setFinBnkCod(String finBnkCod) {
        this.finBnkCod = finBnkCod;
    }

    public String getFinBnkNam() {
        return finBnkNam;
    }

    public void setFinBnkNam(String finBnkNam) {
        this.finBnkNam = finBnkNam;
    }

    public String getFinBnkBra() {
        return finBnkBra;
    }

    public void setFinBnkBra(String finBnkBra) {
        this.finBnkBra = finBnkBra;
    }

    public String getFinBnkFre() {
        return finBnkFre;
    }

    public void setFinBnkFre(String finBnkFre) {
        this.finBnkFre = finBnkFre;
    }

    public String getFinTopCod() {
        return finTopCod;
    }

    public void setFinTopCod(String finTopCod) {
        this.finTopCod = finTopCod;
    }

    public String getFinTopNam() {
        return finTopNam;
    }

    public void setFinTopNam(String finTopNam) {
        this.finTopNam = finTopNam;
    }

    public String getFinAcc() {
        return finAcc;
    }

    public void setFinAcc(String finAcc) {
        this.finAcc = finAcc;
    }

    public String getFinMpn() {
        return finMpn;
    }

    public void setFinMpn(String finMpn) {
        this.finMpn = finMpn;
    }

    public BigInteger getFinAmtMnl() {
        return finAmtMnl;
    }

    public void setFinAmtMnl(BigInteger finAmtMnl) {
        this.finAmtMnl = finAmtMnl;
    }

    public BigInteger getFinAmtFee() {
        return finAmtFee;
    }

    public void setFinAmtFee(BigInteger finAmtFee) {
        this.finAmtFee = finAmtFee;
    }

    public BigInteger getFinAmtDty() {
        return finAmtDty;
    }

    public void setFinAmtDty(BigInteger finAmtDty) {
        this.finAmtDty = finAmtDty;
    }

    public BigInteger getFinAmtTbp() {
        return finAmtTbp;
    }

    public void setFinAmtTbp(BigInteger finAmtTbp) {
        this.finAmtTbp = finAmtTbp;
    }

    public String getFinGtyNam() {
        return finGtyNam;
    }

    public void setFinGtyNam(String finGtyNam) {
        this.finGtyNam = finGtyNam;
    }

    public BigInteger getFinGtyAmt() {
        return finGtyAmt;
    }

    public void setFinGtyAmt(BigInteger finGtyAmt) {
        this.finGtyAmt = finGtyAmt;
    }

    public Date getFinGtyDat() {
        return finGtyDat;
    }

    public void setFinGtyDat(Date finGtyDat) {
        this.finGtyDat = finGtyDat;
    }

    public String getFinGtyCtyCod() {
        return finGtyCtyCod;
    }

    public void setFinGtyCtyCod(String finGtyCtyCod) {
        this.finGtyCtyCod = finGtyCtyCod;
    }

    public String getFinGtyCtyNam() {
        return finGtyCtyNam;
    }

    public void setFinGtyCtyNam(String finGtyCtyNam) {
        this.finGtyCtyNam = finGtyCtyNam;
    }

    public String getVgsWrk() {
        return vgsWrk;
    }

    public void setVgsWrk(String vgsWrk) {
        this.vgsWrk = vgsWrk;
    }

    public BigInteger getVgsWgtGrs() {
        return vgsWgtGrs;
    }

    public void setVgsWgtGrs(BigInteger vgsWgtGrs) {
        this.vgsWgtGrs = vgsWgtGrs;
    }

    public BigInteger getVgsCst() {
        return vgsCst;
    }

    public void setVgsCst(BigInteger vgsCst) {
        this.vgsCst = vgsCst;
    }

    public BigInteger getVgsCif() {
        return vgsCif;
    }

    public void setVgsCif(BigInteger vgsCif) {
        this.vgsCif = vgsCif;
    }

    public BigInteger getVgsTotNmu() {
        return vgsTotNmu;
    }

    public void setVgsTotNmu(BigInteger vgsTotNmu) {
        this.vgsTotNmu = vgsTotNmu;
    }

    public BigInteger getVgsTotFcx() {
        return vgsTotFcx;
    }

    public void setVgsTotFcx(BigInteger vgsTotFcx) {
        this.vgsTotFcx = vgsTotFcx;
    }

    public BigInteger getVgsTotGrs() {
        return vgsTotGrs;
    }

    public void setVgsTotGrs(BigInteger vgsTotGrs) {
        this.vgsTotGrs = vgsTotGrs;
    }

    public BigInteger getVgsInvAmtNmu() {
        return vgsInvAmtNmu;
    }

    public void setVgsInvAmtNmu(BigInteger vgsInvAmtNmu) {
        this.vgsInvAmtNmu = vgsInvAmtNmu;
    }

    public BigInteger getVgsInvAmtFcx() {
        return vgsInvAmtFcx;
    }

    public void setVgsInvAmtFcx(BigInteger vgsInvAmtFcx) {
        this.vgsInvAmtFcx = vgsInvAmtFcx;
    }

    public String getVgsInvCurCod() {
        return vgsInvCurCod;
    }

    public void setVgsInvCurCod(String vgsInvCurCod) {
        this.vgsInvCurCod = vgsInvCurCod;
    }

    public String getVgsInvCurNam() {
        return vgsInvCurNam;
    }

    public void setVgsInvCurNam(String vgsInvCurNam) {
        this.vgsInvCurNam = vgsInvCurNam;
    }

    public BigInteger getVgsInvCurRat() {
        return vgsInvCurRat;
    }

    public void setVgsInvCurRat(BigInteger vgsInvCurRat) {
        this.vgsInvCurRat = vgsInvCurRat;
    }

    public BigInteger getVgsInvCurRef() {
        return vgsInvCurRef;
    }

    public void setVgsInvCurRef(BigInteger vgsInvCurRef) {
        this.vgsInvCurRef = vgsInvCurRef;
    }

    public BigInteger getVgsEfrAmtNmu() {
        return vgsEfrAmtNmu;
    }

    public void setVgsEfrAmtNmu(BigInteger vgsEfrAmtNmu) {
        this.vgsEfrAmtNmu = vgsEfrAmtNmu;
    }

    public BigInteger getVgsEfrAmtFcx() {
        return vgsEfrAmtFcx;
    }

    public void setVgsEfrAmtFcx(BigInteger vgsEfrAmtFcx) {
        this.vgsEfrAmtFcx = vgsEfrAmtFcx;
    }

    public String getVgsEfrCurCod() {
        return vgsEfrCurCod;
    }

    public void setVgsEfrCurCod(String vgsEfrCurCod) {
        this.vgsEfrCurCod = vgsEfrCurCod;
    }

    public String getVgsEfrCurNam() {
        return vgsEfrCurNam;
    }

    public void setVgsEfrCurNam(String vgsEfrCurNam) {
        this.vgsEfrCurNam = vgsEfrCurNam;
    }

    public BigInteger getVgsEfrCurRat() {
        return vgsEfrCurRat;
    }

    public void setVgsEfrCurRat(BigInteger vgsEfrCurRat) {
        this.vgsEfrCurRat = vgsEfrCurRat;
    }

    public BigInteger getVgsEfrCurRef() {
        return vgsEfrCurRef;
    }

    public void setVgsEfrCurRef(BigInteger vgsEfrCurRef) {
        this.vgsEfrCurRef = vgsEfrCurRef;
    }

    public BigInteger getVgsIfrAmtNmu() {
        return vgsIfrAmtNmu;
    }

    public void setVgsIfrAmtNmu(BigInteger vgsIfrAmtNmu) {
        this.vgsIfrAmtNmu = vgsIfrAmtNmu;
    }

    public BigInteger getVgsIfrAmtFcx() {
        return vgsIfrAmtFcx;
    }

    public void setVgsIfrAmtFcx(BigInteger vgsIfrAmtFcx) {
        this.vgsIfrAmtFcx = vgsIfrAmtFcx;
    }

    public String getVgsIfrCurCod() {
        return vgsIfrCurCod;
    }

    public void setVgsIfrCurCod(String vgsIfrCurCod) {
        this.vgsIfrCurCod = vgsIfrCurCod;
    }

    public String getVgsIfrCurNam() {
        return vgsIfrCurNam;
    }

    public void setVgsIfrCurNam(String vgsIfrCurNam) {
        this.vgsIfrCurNam = vgsIfrCurNam;
    }

    public BigInteger getVgsIfrCurRat() {
        return vgsIfrCurRat;
    }

    public void setVgsIfrCurRat(BigInteger vgsIfrCurRat) {
        this.vgsIfrCurRat = vgsIfrCurRat;
    }

    public BigInteger getVgsIfrCurRef() {
        return vgsIfrCurRef;
    }

    public void setVgsIfrCurRef(BigInteger vgsIfrCurRef) {
        this.vgsIfrCurRef = vgsIfrCurRef;
    }

    public BigInteger getVgsInsAmtNmu() {
        return vgsInsAmtNmu;
    }

    public void setVgsInsAmtNmu(BigInteger vgsInsAmtNmu) {
        this.vgsInsAmtNmu = vgsInsAmtNmu;
    }

    public BigInteger getVgsInsAmtFcx() {
        return vgsInsAmtFcx;
    }

    public void setVgsInsAmtFcx(BigInteger vgsInsAmtFcx) {
        this.vgsInsAmtFcx = vgsInsAmtFcx;
    }

    public String getVgsInsCurCod() {
        return vgsInsCurCod;
    }

    public void setVgsInsCurCod(String vgsInsCurCod) {
        this.vgsInsCurCod = vgsInsCurCod;
    }

    public String getVgsInsCurNam() {
        return vgsInsCurNam;
    }

    public void setVgsInsCurNam(String vgsInsCurNam) {
        this.vgsInsCurNam = vgsInsCurNam;
    }

    public BigInteger getVgsInsCurRat() {
        return vgsInsCurRat;
    }

    public void setVgsInsCurRat(BigInteger vgsInsCurRat) {
        this.vgsInsCurRat = vgsInsCurRat;
    }

    public BigInteger getVgsInsCurRef() {
        return vgsInsCurRef;
    }

    public void setVgsInsCurRef(BigInteger vgsInsCurRef) {
        this.vgsInsCurRef = vgsInsCurRef;
    }

    public BigInteger getVgsOtcAmtNmu() {
        return vgsOtcAmtNmu;
    }

    public void setVgsOtcAmtNmu(BigInteger vgsOtcAmtNmu) {
        this.vgsOtcAmtNmu = vgsOtcAmtNmu;
    }

    public BigInteger getVgsOtcAmtFcx() {
        return vgsOtcAmtFcx;
    }

    public void setVgsOtcAmtFcx(BigInteger vgsOtcAmtFcx) {
        this.vgsOtcAmtFcx = vgsOtcAmtFcx;
    }

    public String getVgsOtcCurCod() {
        return vgsOtcCurCod;
    }

    public void setVgsOtcCurCod(String vgsOtcCurCod) {
        this.vgsOtcCurCod = vgsOtcCurCod;
    }

    public String getVgsOtcCurNam() {
        return vgsOtcCurNam;
    }

    public void setVgsOtcCurNam(String vgsOtcCurNam) {
        this.vgsOtcCurNam = vgsOtcCurNam;
    }

    public BigInteger getVgsOtcCurRat() {
        return vgsOtcCurRat;
    }

    public void setVgsOtcCurRat(BigInteger vgsOtcCurRat) {
        this.vgsOtcCurRat = vgsOtcCurRat;
    }

    public BigInteger getVgsOtcCurRef() {
        return vgsOtcCurRef;
    }

    public void setVgsOtcCurRef(BigInteger vgsOtcCurRef) {
        this.vgsOtcCurRef = vgsOtcCurRef;
    }

    public BigInteger getVgsDedAmtNmu() {
        return vgsDedAmtNmu;
    }

    public void setVgsDedAmtNmu(BigInteger vgsDedAmtNmu) {
        this.vgsDedAmtNmu = vgsDedAmtNmu;
    }

    public BigInteger getVgsDedAmtFcx() {
        return vgsDedAmtFcx;
    }

    public void setVgsDedAmtFcx(BigInteger vgsDedAmtFcx) {
        this.vgsDedAmtFcx = vgsDedAmtFcx;
    }

    public String getVgsDedCurCod() {
        return vgsDedCurCod;
    }

    public void setVgsDedCurCod(String vgsDedCurCod) {
        this.vgsDedCurCod = vgsDedCurCod;
    }

    public String getVgsDedCurNam() {
        return vgsDedCurNam;
    }

    public void setVgsDedCurNam(String vgsDedCurNam) {
        this.vgsDedCurNam = vgsDedCurNam;
    }

    public BigInteger getVgsDedCurRat() {
        return vgsDedCurRat;
    }

    public void setVgsDedCurRat(BigInteger vgsDedCurRat) {
        this.vgsDedCurRat = vgsDedCurRat;
    }

    public BigInteger getVgsDedCurRef() {
        return vgsDedCurRef;
    }

    public void setVgsDedCurRef(BigInteger vgsDedCurRef) {
        this.vgsDedCurRef = vgsDedCurRef;
    }

    public String getWhsCod() {
        return whsCod;
    }

    public void setWhsCod(String whsCod) {
        this.whsCod = whsCod;
    }

    public Long getWhsTim() {
        return whsTim;
    }

    public void setWhsTim(Long whsTim) {
        this.whsTim = whsTim;
    }

    public String getTrsRspCod() {
        return trsRspCod;
    }

    public void setTrsRspCod(String trsRspCod) {
        this.trsRspCod = trsRspCod;
    }

    public String getTrsRspNam() {
        return trsRspNam;
    }

    public void setTrsRspNam(String trsRspNam) {
        this.trsRspNam = trsRspNam;
    }

    public String getTrsRspRep() {
        return trsRspRep;
    }

    public void setTrsRspRep(String trsRspRep) {
        this.trsRspRep = trsRspRep;
    }

    public String getTrsSgtPlc() {
        return trsSgtPlc;
    }

    public void setTrsSgtPlc(String trsSgtPlc) {
        this.trsSgtPlc = trsSgtPlc;
    }

    public Date getTrsSgtDat() {
        return trsSgtDat;
    }

    public void setTrsSgtDat(Date trsSgtDat) {
        this.trsSgtDat = trsSgtDat;
    }

    public String getTrsDesCuo() {
        return trsDesCuo;
    }

    public void setTrsDesCuo(String trsDesCuo) {
        this.trsDesCuo = trsDesCuo;
    }

    public String getTrsDesCod() {
        return trsDesCod;
    }

    public void setTrsDesCod(String trsDesCod) {
        this.trsDesCod = trsDesCod;
    }

    public String getTrsDesCty() {
        return trsDesCty;
    }

    public void setTrsDesCty(String trsDesCty) {
        this.trsDesCty = trsDesCty;
    }

    public Long getTrsSlsNbr() {
        return trsSlsNbr;
    }

    public void setTrsSlsNbr(Long trsSlsNbr) {
        this.trsSlsNbr = trsSlsNbr;
    }

    public String getTrsSlsIde() {
        return trsSlsIde;
    }

    public void setTrsSlsIde(String trsSlsIde) {
        this.trsSlsIde = trsSlsIde;
    }

    public String getTrsCtl() {
        return trsCtl;
    }

    public void setTrsCtl(String trsCtl) {
        this.trsCtl = trsCtl;
    }

    public Date getTrsLim() {
        return trsLim;
    }

    public void setTrsLim(Date trsLim) {
        this.trsLim = trsLim;
    }

    public String getTrsCof() {
        return trsCof;
    }

    public void setTrsCof(String trsCof) {
        this.trsCof = trsCof;
    }

    public String getAstRyr() {
        return astRyr;
    }

    public void setAstRyr(String astRyr) {
        this.astRyr = astRyr;
    }

    public String getAstAyr() {
        return astAyr;
    }

    public void setAstAyr(String astAyr) {
        this.astAyr = astAyr;
    }

    public BigInteger getAstTot() {
        return astTot;
    }

    public void setAstTot(BigInteger astTot) {
        this.astTot = astTot;
    }

    public BigInteger getAstAmt() {
        return astAmt;
    }

    public void setAstAmt(BigInteger astAmt) {
        this.astAmt = astAmt;
    }

    public String getAstStn() {
        return astStn;
    }

    public void setAstStn(String astStn) {
        this.astStn = astStn;
    }

    public Date getAstStd() {
        return astStd;
    }

    public void setAstStd(Date astStd) {
        this.astStd = astStd;
    }

    public String getAstSts() {
        return astSts;
    }

    public void setAstSts(String astSts) {
        this.astSts = astSts;
    }

    public Long getRloNbr() {
        return rloNbr;
    }

    public void setRloNbr(Long rloNbr) {
        this.rloNbr = rloNbr;
    }

    public String getExaSec() {
        return exaSec;
    }

    public void setExaSec(String exaSec) {
        this.exaSec = exaSec;
    }

    public String getExaExa() {
        return exaExa;
    }

    public void setExaExa(String exaExa) {
        this.exaExa = exaExa;
    }

    public String getExaCex() {
        return exaCex;
    }

    public void setExaCex(String exaCex) {
        this.exaCex = exaCex;
    }

    public Long getExaWgt() {
        return exaWgt;
    }

    public void setExaWgt(Long exaWgt) {
        this.exaWgt = exaWgt;
    }

    public BigInteger getLstRcpAmt() {
        return lstRcpAmt;
    }

    public void setLstRcpAmt(BigInteger lstRcpAmt) {
        this.lstRcpAmt = lstRcpAmt;
    }

    public String getLstRcpSer() {
        return lstRcpSer;
    }

    public void setLstRcpSer(String lstRcpSer) {
        this.lstRcpSer = lstRcpSer;
    }

    public String getLstRcpNbr() {
        return lstRcpNbr;
    }

    public void setLstRcpNbr(String lstRcpNbr) {
        this.lstRcpNbr = lstRcpNbr;
    }

    public Date getLstRcpDat() {
        return lstRcpDat;
    }

    public void setLstRcpDat(Date lstRcpDat) {
        this.lstRcpDat = lstRcpDat;
    }

    public String getLstRcpTyp() {
        return lstRcpTyp;
    }

    public void setLstRcpTyp(String lstRcpTyp) {
        this.lstRcpTyp = lstRcpTyp;
    }

    public String getLstRcpCuo() {
        return lstRcpCuo;
    }

    public void setLstRcpCuo(String lstRcpCuo) {
        this.lstRcpCuo = lstRcpCuo;
    }

    public Serializable getBrokerStamp() {
        return brokerStamp;
    }

    public void setBrokerStamp(Serializable brokerStamp) {
        this.brokerStamp = brokerStamp;
    }

    public Serializable getCustomsStamp() {
        return customsStamp;
    }

    public void setCustomsStamp(Serializable customsStamp) {
        this.customsStamp = customsStamp;
    }

    @XmlTransient
    public List<TArticle> getTArticleList() {
        return tArticleList;
    }

    public void setTArticleList(List<TArticle> tArticleList) {
        this.tArticleList = tArticleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (instanceid != null ? instanceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TDeclarationDou)) {
            return false;
        }
        TDeclarationDou other = (TDeclarationDou) object;
        if ((this.instanceid == null && other.instanceid != null) || (this.instanceid != null && !this.instanceid.equals(other.instanceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bj.finances.cfisc.entities.TDeclarationDou[ instanceid=" + instanceid + " ]";
    }
    
}
