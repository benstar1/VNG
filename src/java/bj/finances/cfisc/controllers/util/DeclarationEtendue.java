/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.controllers.util;

import bj.finances.cfisc.entities.TDeclarationDou;
import java.math.BigDecimal;

/**
 *
 * @author SANNI Emmanuel
 */
public class DeclarationEtendue extends TDeclarationDou {

    private BigDecimal pcs;
    private BigDecimal pc;
    private BigDecimal tva;
    private BigDecimal aib;
    private BigDecimal afs;
    private BigDecimal dd;

    public DeclarationEtendue() {

    }

    public DeclarationEtendue(TDeclarationDou tDeclarationDou) {
        this.setAstAmt(tDeclarationDou.getAstAmt());
        this.setAstTot(tDeclarationDou.getAstTot());
        this.setFinAmtDty(tDeclarationDou.getFinAmtDty());
        this.setFinAmtFee(tDeclarationDou.getFinAmtFee());
        this.setFinAmtMnl(tDeclarationDou.getFinAmtMnl());
        this.setFinAmtTbp(tDeclarationDou.getFinAmtTbp());
        this.setFinGtyAmt(tDeclarationDou.getFinGtyAmt());
        this.setGenVdt(tDeclarationDou.getGenVdt());
        this.setLstRcpAmt(tDeclarationDou.getLstRcpAmt());
        this.setPrvDty(tDeclarationDou.getPrvDty());
        this.setVgsCif(tDeclarationDou.getVgsCif());
        this.setVgsCst(tDeclarationDou.getVgsCst());
        this.setVgsDedAmtFcx(tDeclarationDou.getVgsDedAmtFcx());
        this.setVgsDedAmtNmu(tDeclarationDou.getVgsDedAmtNmu());
        this.setVgsDedCurRat(tDeclarationDou.getVgsDedCurRat());
        this.setVgsDedCurRef(tDeclarationDou.getVgsDedCurRef());
        this.setVgsEfrAmtFcx(tDeclarationDou.getVgsEfrAmtFcx());
        this.setVgsEfrAmtNmu(tDeclarationDou.getVgsEfrAmtNmu());
        this.setVgsEfrCurRat(tDeclarationDou.getVgsEfrCurRat());
        this.setVgsEfrCurRef(tDeclarationDou.getVgsEfrCurRef());
        this.setVgsIfrAmtFcx(tDeclarationDou.getVgsIfrAmtFcx());
        this.setVgsIfrAmtNmu(tDeclarationDou.getVgsIfrAmtNmu());
        this.setVgsIfrCurRat(tDeclarationDou.getVgsIfrCurRat());
        this.setVgsIfrCurRef(tDeclarationDou.getVgsIfrCurRef());
        this.setVgsInsAmtFcx(tDeclarationDou.getVgsInsAmtFcx());
        this.setVgsInsAmtNmu(tDeclarationDou.getVgsInsAmtNmu());
        this.setVgsInsCurRat(tDeclarationDou.getVgsInsCurRat());
        this.setVgsInsCurRef(tDeclarationDou.getVgsInsCurRef());
        this.setVgsInvAmtFcx(tDeclarationDou.getVgsInvAmtFcx());
        this.setVgsInvAmtNmu(tDeclarationDou.getVgsInvAmtNmu());
        this.setVgsInvCurRat(tDeclarationDou.getVgsInvCurRat());
        this.setVgsInvCurRef(tDeclarationDou.getVgsInvCurRef());
        this.setVgsOtcAmtFcx(tDeclarationDou.getVgsOtcAmtFcx());
        this.setVgsOtcAmtNmu(tDeclarationDou.getVgsOtcAmtNmu());
        this.setVgsOtcCurRat(tDeclarationDou.getVgsOtcCurRat());
        this.setVgsOtcCurRef(tDeclarationDou.getVgsOtcCurRef());
        this.setVgsTotFcx(tDeclarationDou.getVgsTotFcx());
        this.setVgsTotGrs(tDeclarationDou.getVgsTotGrs());
        this.setVgsTotNmu(tDeclarationDou.getVgsTotNmu());
        this.setVgsWgtGrs(tDeclarationDou.getVgsWgtGrs());
        this.setAstStd(tDeclarationDou.getAstStd());
        this.setFinGtyDat(tDeclarationDou.getFinGtyDat());
        this.setIdeAstDat(tDeclarationDou.getIdeAstDat());
        this.setIdePstDat(tDeclarationDou.getIdePstDat());
        this.setIdeRcpDat(tDeclarationDou.getIdeRcpDat());
        this.setIdeRegDat(tDeclarationDou.getIdeRegDat());
        this.setLstRcpDat(tDeclarationDou.getLstRcpDat());
        this.setPtyCde(tDeclarationDou.getPtyCde());
        this.setPtyWde(tDeclarationDou.getPtyWde());
        this.setRlsDat(tDeclarationDou.getRlsDat());
        this.setRlsTim(tDeclarationDou.getRlsTim());
        this.setTrsLim(tDeclarationDou.getTrsLim());
        this.setTrsSgtDat(tDeclarationDou.getTrsSgtDat());
        this.setTArticleList(tDeclarationDou.getTArticleList());
        this.setDecRefYer(tDeclarationDou.getDecRefYer());
        this.setExaWgt(tDeclarationDou.getExaWgt());
        this.setInstanceid(tDeclarationDou.getInstanceid());
        this.setPtyBlu(tDeclarationDou.getPtyBlu());
        this.setPtyFrmNbr(tDeclarationDou.getPtyFrmNbr());
        this.setPtyFrmTot(tDeclarationDou.getPtyFrmTot());
        this.setPtyGre(tDeclarationDou.getPtyGre());
        this.setPtyNbrItm(tDeclarationDou.getPtyNbrItm());
        this.setPtyNbrLdl(tDeclarationDou.getPtyNbrLdl());
        this.setPtyNbrPck(tDeclarationDou.getPtyNbrPck());
        this.setPtyQue(tDeclarationDou.getPtyQue());
        this.setPtyRed(tDeclarationDou.getPtyRed());
        this.setPtyYel(tDeclarationDou.getPtyYel());
        this.setRloNbr(tDeclarationDou.getRloNbr());
        this.setTptT1(tDeclarationDou.getTptT1());
        this.setTrsSlsNbr(tDeclarationDou.getTrsSlsNbr());
        this.setWhsTim(tDeclarationDou.getWhsTim());
        this.setBrokerStamp(tDeclarationDou.getBrokerStamp());
        this.setCustomsStamp(tDeclarationDou.getCustomsStamp());
        this.setTptCtf(tDeclarationDou.getTptCtf());
        this.setAstAyr(tDeclarationDou.getAstAyr());
        this.setAstRyr(tDeclarationDou.getAstRyr());
        this.setAstStn(tDeclarationDou.getAstStn());
        this.setAstSts(tDeclarationDou.getAstSts());
        this.setCmpConCod(tDeclarationDou.getCmpConCod());
        this.setCmpConNam(tDeclarationDou.getCmpConNam());
        this.setCmpExpCod(tDeclarationDou.getCmpExpCod());
        this.setCmpExpNam(tDeclarationDou.getCmpExpNam());
        this.setCmpFisCod(tDeclarationDou.getCmpFisCod());
        this.setCmpFisNam(tDeclarationDou.getCmpFisNam());
        this.setDecCod(tDeclarationDou.getDecCod());
        this.setDecFlg(tDeclarationDou.getDecFlg());
        this.setDecNam(tDeclarationDou.getDecNam());
        this.setDecRefNbr(tDeclarationDou.getDecRefNbr());
        this.setDecRep(tDeclarationDou.getDecRep());
        this.setExaCex(tDeclarationDou.getExaCex());
        this.setExaExa(tDeclarationDou.getExaExa());
        this.setExaSec(tDeclarationDou.getExaSec());
        this.setFinAcc(tDeclarationDou.getFinAcc());
        this.setFinBnkBra(tDeclarationDou.getFinBnkBra());
        this.setFinBnkCod(tDeclarationDou.getFinBnkCod());
        this.setFinBnkFre(tDeclarationDou.getFinBnkFre());
        this.setFinBnkNam(tDeclarationDou.getFinBnkNam());
        this.setFinGtyCtyCod(tDeclarationDou.getFinGtyCtyCod());
        this.setFinGtyCtyNam(tDeclarationDou.getFinGtyCtyNam());
        this.setFinGtyNam(tDeclarationDou.getFinGtyNam());
        this.setFinMpn(tDeclarationDou.getFinMpn());
        this.setFinTopCod(tDeclarationDou.getFinTopCod());
        this.setFinTopNam(tDeclarationDou.getFinTopNam());
        this.setFinTraNa1(tDeclarationDou.getFinTraNa1());
        this.setFinTraNa2(tDeclarationDou.getFinTraNa2());
        this.setGenCap(tDeclarationDou.getGenCap());
        this.setGenCtyDesCod(tDeclarationDou.getGenCtyDesCod());
        this.setGenCtyDesCrg(tDeclarationDou.getGenCtyDesCrg());
        this.setGenCtyDesNam(tDeclarationDou.getGenCtyDesNam());
        this.setGenCtyEptCod(tDeclarationDou.getGenCtyEptCod());
        this.setGenCtyEptCrg(tDeclarationDou.getGenCtyEptCrg());
        this.setGenCtyEptNam(tDeclarationDou.getGenCtyEptNam());
        this.setGenCtyFlt(tDeclarationDou.getGenCtyFlt());
        this.setGenCtyOrg(tDeclarationDou.getGenCtyOrg());
        this.setGenCtyTrd(tDeclarationDou.getGenCtyTrd());
        this.setGenFre(tDeclarationDou.getGenFre());
        this.setIdeAstNbr(tDeclarationDou.getIdeAstNbr());
        this.setIdeAstSer(tDeclarationDou.getIdeAstSer());
        this.setIdeBar(tDeclarationDou.getIdeBar());
        this.setIdeCuoCod(tDeclarationDou.getIdeCuoCod());
        this.setIdeCuoNam(tDeclarationDou.getIdeCuoNam());
        this.setIdeMan(tDeclarationDou.getIdeMan());
        this.setIdePstNbr(tDeclarationDou.getIdePstNbr());
        this.setIdePstTmpPre(tDeclarationDou.getIdePstTmpPre());
        this.setIdePstTmpTy1(tDeclarationDou.getIdePstTmpTy1());
        this.setIdePstTmpTy2(tDeclarationDou.getIdePstTmpTy2());
        this.setIdeRcpCuo(tDeclarationDou.getIdeRcpCuo());
        this.setIdeRcpNbr(tDeclarationDou.getIdeRcpNbr());
        this.setIdeRcpSer(tDeclarationDou.getIdeRcpSer());
        this.setIdeRcpTyp(tDeclarationDou.getIdeRcpTyp());
        this.setIdeRegNbr(tDeclarationDou.getIdeRegNbr());
        this.setIdeRegSer(tDeclarationDou.getIdeRegSer());
        this.setIdeTypPrc(tDeclarationDou.getIdeTypPrc());
        this.setIdeTypSad(tDeclarationDou.getIdeTypSad());
        this.setIdeTypTrs(tDeclarationDou.getIdeTypTrs());
        this.setIdeTypTyp(tDeclarationDou.getIdeTypTyp());
        this.setLstRcpCuo(tDeclarationDou.getLstRcpCuo());
        this.setLstRcpNbr(tDeclarationDou.getLstRcpNbr());
        this.setLstRcpSer(tDeclarationDou.getLstRcpSer());
        this.setLstRcpTyp(tDeclarationDou.getLstRcpTyp());
        this.setPtyColDsc(tDeclarationDou.getPtyColDsc());
        this.setPtyColInd(tDeclarationDou.getPtyColInd());
        this.setPtyOas(tDeclarationDou.getPtyOas());
        this.setPtyPlc(tDeclarationDou.getPtyPlc());
        this.setRlsCuoCod(tDeclarationDou.getRlsCuoCod());
        this.setRlsCuoNam(tDeclarationDou.getRlsCuoNam());
        this.setRlsInf(tDeclarationDou.getRlsInf());
        this.setRlsRef(tDeclarationDou.getRlsRef());
        this.setTptCuoCod(tDeclarationDou.getTptCuoCod());
        this.setTptCuoNam(tDeclarationDou.getTptCuoNam());
        this.setTptLoc(tDeclarationDou.getTptLoc());
        this.setTptLopCod(tDeclarationDou.getTptLopCod());
        this.setTptLopCty(tDeclarationDou.getTptLopCty());
        this.setTptLopNam(tDeclarationDou.getTptLopNam());
        this.setTptMotBrdCod(tDeclarationDou.getTptMotBrdCod());
        this.setTptMotBrdCty(tDeclarationDou.getTptMotBrdCty());
        this.setTptMotBrdNam(tDeclarationDou.getTptMotBrdNam());
        this.setTptMotDpaCty(tDeclarationDou.getTptMotDpaCty());
        this.setTptMotDpaNam(tDeclarationDou.getTptMotDpaNam());
        this.setTptMotInl(tDeclarationDou.getTptMotInl());
        this.setTptTodCod(tDeclarationDou.getTptTodCod());
        this.setTptTodPlc(tDeclarationDou.getTptTodPlc());
        this.setTptTodSit(tDeclarationDou.getTptTodSit());
        this.setTrsCof(tDeclarationDou.getTrsCof());
        this.setTrsCtl(tDeclarationDou.getTrsCtl());
        this.setTrsDesCod(tDeclarationDou.getTrsDesCod());
        this.setTrsDesCty(tDeclarationDou.getTrsDesCty());
        this.setTrsDesCuo(tDeclarationDou.getTrsDesCuo());
        this.setTrsRspCod(tDeclarationDou.getTrsRspCod());
        this.setTrsRspNam(tDeclarationDou.getTrsRspNam());
        this.setTrsRspRep(tDeclarationDou.getTrsRspRep());
        this.setTrsSgtPlc(tDeclarationDou.getTrsSgtPlc());
        this.setTrsSlsIde(tDeclarationDou.getTrsSlsIde());
        this.setVgsDedCurCod(tDeclarationDou.getVgsDedCurCod());
        this.setVgsDedCurNam(tDeclarationDou.getVgsDedCurNam());
        this.setVgsEfrCurCod(tDeclarationDou.getVgsEfrCurCod());
        this.setVgsEfrCurNam(tDeclarationDou.getVgsEfrCurNam());
        this.setVgsIfrCurCod(tDeclarationDou.getVgsIfrCurCod());
        this.setVgsIfrCurNam(tDeclarationDou.getVgsIfrCurNam());
        this.setVgsInsCurCod(tDeclarationDou.getVgsInsCurCod());
        this.setVgsInsCurNam(tDeclarationDou.getVgsInsCurNam());
        this.setVgsInvCurCod(tDeclarationDou.getVgsInvCurCod());
        this.setVgsInvCurNam(tDeclarationDou.getVgsInvCurNam());
        this.setVgsOtcCurCod(tDeclarationDou.getVgsOtcCurCod());
        this.setVgsOtcCurNam(tDeclarationDou.getVgsOtcCurNam());
        this.setVgsWrk(tDeclarationDou.getVgsWrk());
        this.setWhsCod(tDeclarationDou.getWhsCod());

    }

    public BigDecimal getPcs() {
        return pcs;
    }

    public void setPcs(BigDecimal pcs) {
        this.pcs = pcs;
    }

    public BigDecimal getPc() {
        return pc;
    }

    public void setPc(BigDecimal pc) {
        this.pc = pc;
    }

    public BigDecimal getTva() {
        return tva;
    }

    public void setTva(BigDecimal tva) {
        this.tva = tva;
    }

    public BigDecimal getAib() {
        return aib;
    }

    public void setAib(BigDecimal aib) {
        this.aib = aib;
    }

    public BigDecimal getAfs() {
        return afs;
    }

    public void setAfs(BigDecimal afs) {
        this.afs = afs;
    }

    public BigDecimal getDd() {
        return dd;
    }

    public void setDd(BigDecimal dd) {
        this.dd = dd;
    }

}
