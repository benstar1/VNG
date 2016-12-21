/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.interfaces;

import bj.finances.cfisc.controllers.util.JsfUtil;
import bj.finances.cfisc.entities.TCentreImpot;
import bj.finances.cfisc.entities.TParticiper;
import bj.finances.cfisc.entities.TRepUnique;
import bj.finances.cfisc.entities.TTypeContrib;
import bj.finances.cfisc.entities.TDeclarationDou;
import bj.finances.cfisc.entities.TArticle;
import bj.finances.cfisc.entities.TArticlePK;
import bj.finances.cfisc.entities.TMotif;
import bj.finances.cfisc.entities.TTaxeDeclDou;
import bj.finances.cfisc.entities.TTaxeDeclDouPK;
import bj.finances.cfisc.entities.TUtilisateur;
import bj.finances.cfisc.sessions.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.log4j.*;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderSchemaFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author SANNI Emmanuel
 */
@Stateless
public class InterfaceIfuPlateforme {

    @EJB
    private TDeclarationDouFacade tDeclarationDouaneFacade;

    @Inject
    private TTypeContribFacade tTypeContribFacade;
    @Inject
    private TCentreImpotFacade tCentreImpotFacade;
    @Inject
    private TRepUniqueFacade tRepUniqueFacade;

    @Inject
    private TParticiperFacade tParticiperFacade;

    @Inject
    private TTaxeDeclDouFacade tTaxeDecDouFacade;

    @Inject
    private TArticleFacade tArticleFacade;

    @Inject
    private TUtilisateurFacade tUtilisateurFacade;

    @Inject
    private TMotifFacade tMotifFacade;

    @Inject
    private THistoriqueFacade tHistoriqueFacade;

    final static org.apache.log4j.Logger logger = Logger.getLogger(InterfaceIfuPlateforme.class.getName());

    private String cheminDepotLocal = ResourceBundle.getBundle("/parametres").getString("cheminDepotLocal");
    private String cheminDossierEchecs = ResourceBundle.getBundle("/parametres").getString("cheminDossierEchecs");
    private String cheminDossierSucces = ResourceBundle.getBundle("/parametres").getString("cheminDossierSucces");
    private String cheminFichierXsdCont = ResourceBundle.getBundle("/parametres").getString("cheminFichierXsdCont");
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //@Schedule(dayOfWeek = "*", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "*/20", persistent = false)
    public void consommerFichierEntreprise() {
        try {
            scrutelocal();

        } catch (Exception ex) {
            //Logger.getLogger(InterfaceIfuPlateforme.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("methode : consommerFichierEntreprise" + ex.getMessage());
        }

    }

    public void scrutelocal() {
        System.out.println(" Scan du dossier source ........ " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()));            
         
        File depotLocal = new File(cheminDepotLocal);
        File dossierEchecs = new File(cheminDossierEchecs);
        File[] listeFichier = depotLocal.listFiles();
        List<File> liste = Arrays.asList(listeFichier);
        Collections.sort(liste);

        for (File f : liste) {

            if (f.isDirectory()) {
                continue;
            }
            Document document = null;
            InputStream in = null;
            Schema schema = null;

            try {
                System.out.println(f.getName().substring(0, 4));
                //////////////////////////traitement des xml de t_contribuables/////////////
                if (f.getName().substring(0, 4).equals("CONT")) {
                    logger.info("JE SUIS DANS CONT");
                    SchemaFactory schemafac = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                    schema = schemafac.newSchema(new File(cheminFichierXsdCont));
                    XMLReaderJDOMFactory factory = new XMLReaderSchemaFactory(schema);
                    logger.info("APRES VALIDATION");
                    SAXBuilder builder = new SAXBuilder(factory);
                    logger.info("APRES BUILDER");
                    in = new FileInputStream(f);
                    document = (Document) builder.build(in);
                    
                    traitementDeDonnesCont(document, f, in);

                } //////////////////////////traitement des xml de t_participer/////////////
                else if (f.getName().substring(0, 4).equals("PART")) {

                    in = new FileInputStream(f);
                    SAXBuilder builder = new SAXBuilder();
                    document = (Document) builder.build(in);
                    traitementDeDonnesPart(document, f, in);
                } //////////////////////////traitement des xml de declaration douanières/////////////
                else if (f.getName().substring(0, 4).equals("DECL")) {
                    in = new FileInputStream(f);
                    SAXBuilder builder = new SAXBuilder();
                    document = (Document) builder.build(in);
                    TraitementDonneesDouane(document, f, in, f.getName().toString());
                }

            } catch (JDOMException | SAXException j) {
                logger.error("Echec de validation du fichier : (" + j.getMessage() + ")");
                //j.printStackTrace();
                try {
                    in.close();
                } catch (IOException ex) {
                }
                f.renameTo(new File(dossierEchecs, f.getName()));
            } catch (IOException i) {
                logger.error("Problème de lecture du fichier : (" + i.getMessage() + ")");
                //i.printStackTrace(); 
                try {
                    in.close();
                } catch (IOException ex) {
                }
                f.renameTo(new File(dossierEchecs, f.getName()));
            } catch (Exception ex) {
                logger.error("Une exception inconnue a été générée : (" + ex.getMessage() + ")");
                //ex.printStackTrace();      
                try {
                    in.close();
                } catch (IOException ioe) {
                }
                f.renameTo(new File(dossierEchecs, f.getName()));
            } 

        }
    }

    public void traitementDeDonnesPart(Document document, File fichier, InputStream in) throws JDOMException, SAXException, IOException {

        Element racine = document.getRootElement();

        Element operation = racine.getChild("OPERATION");

        Element typeOperation = operation.getChild("TYPEOP");

        if (typeOperation.getValue().equals("A")) {
            InsertParticiper(racine, fichier);
        } else if (typeOperation.getValue().equals("M")) {
            majParticiper(racine, fichier);
        }

    }

    ////////////////////////méthode d'insertion dans la table t_participer à partir des fichiers xml///////
    public void InsertParticiper(Element participer, File fichier) {

        // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        TParticiper tParticiper = new TParticiper();
        TRepUnique tRepUnique = new TRepUnique();

        try {
            tParticiper.setPartContImmatr(participer.getChild("PART").getChild("CONT_IFU_ENTREPRISE").getValue());
        } catch (Exception e) {
        };
        try {
            tParticiper.setPartContImmatrCont(participer.getChild("PART").getChild("CONT_IFU_ASSOCIER").getValue());
        } catch (Exception e) {
        };
        try {
            tParticiper.setPartNum(Long.parseLong(participer.getChild("PART").getChild("PART_NUM").getValue()));
        } catch (Exception e) {
        };

        // gestion ifu Etablissement 
        String ifuEt = participer.getChild("PART").getChild("CONT_IFU_ENTREPRISE").getValue();
        String ifuPP = participer.getChild("PART").getChild("CONT_IFU_ASSOCIER").getValue();

        if (ifuEt.substring(0, 1).equals("1") || ifuEt.substring(0, 1).equals("2")) {

            String raisSoc = participer.getChild("EI").getChild("RAIS_SOCIALE").getValue();
            String nomLong = participer.getChild("EI").getChild("NOM_LONG").getValue();

            tRepUnique = tRepUniqueFacade.find(Long.parseLong(ifuPP));

            if (tRepUnique != null) {
                System.out.println("Associer trouvé ");
                tRepUnique.setContRais(raisSoc);
                tRepUnique.setContNomLong(nomLong);

                tRepUniqueFacade.edit(tRepUnique);
            } else {
                fichier.renameTo(new File(cheminDossierEchecs, fichier.getName()));
            }

        }

        tParticiperFacade.create(tParticiper);
        fichier.renameTo(new File(cheminDossierSucces, fichier.getName()));
        fichier.delete();
    }

//////////////////////// fin méthode d'insertion dans la table t_participer à partir des fichiers xml///////

////////////////////////méthode de maj dans la table t_participer à partir des fichiers xml///////
    public void majParticiper(Element participer, File fichier) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        TParticiper tParticiper = new TParticiper();
        TRepUnique tRepUnique = new TRepUnique();

        tParticiper = tParticiperFacade.find(Long.parseLong(participer.getChild("PART").getChild("PART_NUM").getValue()));
        if (tParticiper != null) {
            try {
                tParticiper.setPartContImmatr(participer.getChild("PART").getChild("CONT_IFU_ENTREPRISE").getValue());
            } catch (Exception e) {
            };
            try {
                tParticiper.setPartContImmatrCont(participer.getChild("PART").getChild("CONT_IFU_ASSOCIER").getValue());
            } catch (Exception e) {
            };
            try {
                tParticiper.setPartNum(Long.parseLong(participer.getChild("PART").getChild("PART_NUM").getValue()));
            } catch (Exception e) {
            };

        // gestion ifu Etablissement 
            String ifuEt = participer.getChild("PART").getChild("CONT_IFU_ENTREPRISE").getValue();
            String ifuPP = participer.getChild("PART").getChild("CONT_IFU_ASSOCIER").getValue();

            if (ifuEt.substring(0, 1).equals("1") || ifuEt.substring(0, 1).equals("2")) {

                String raisSoc = participer.getChild("EI").getChild("RAIS_SOCIALE").getValue();
                String nomLong = participer.getChild("EI").getChild("NOM_LONG").getValue();

                tRepUnique = tRepUniqueFacade.find(Long.parseLong(ifuPP));

                if (tRepUnique != null) {
                    tRepUnique.setContRais(raisSoc);
                    tRepUnique.setContNomLong(nomLong);

                    tRepUniqueFacade.edit(tRepUnique);
                }
            }

            //MAJ ancien ifu promoteur
            String old_ifu = participer.getChild("OLD_IFU").getChild("OLD_IFU_ASSOCIER").getValue();
            tRepUnique = tRepUniqueFacade.find(Long.parseLong(old_ifu));
            if (tRepUnique != null) {
                tRepUnique.setContRais("");
                tRepUnique.setContNomLong("");
                tRepUniqueFacade.edit(tRepUnique);
            }

            // fin MAJ ancien ifu
        //fin gestion Etablissement
            tParticiperFacade.edit(tParticiper);

            fichier.renameTo(new File(cheminDossierSucces, fichier.getName()));
            fichier.delete();
           logger.info("Mise à jour Partciper effectuée ");
        }
    }
   //////////////////////// méthode fin de maj de la table t_participer à partir des fichiers xml///////

    /////////////////////// METHODE INSERTION DECLARATION SYDONIA /////////////////////////
    public void TraitementDonneesDouane(Document document, File fichier, InputStream in, String decl) throws JDOMException, SAXException, IOException {

        logger.info("Traitement de la déclaration ..... " + decl);

        Element declaration = document.getRootElement();
        TDeclarationDou Tdeclaration = new TDeclarationDou();

        Long instance_id = Long.parseLong(declaration.getChild("segment_general").getAttribute("INSTANCE_ID").getValue());

        Tdeclaration = tDeclarationDouaneFacade.find(instance_id);

        String typeope = declaration.getChild("typeope").getAttribute("type").getValue();

        if (Tdeclaration == null) {
            Tdeclaration = new TDeclarationDou(instance_id);
            if ("A".equals(typeope) || "M".equals(typeope)) {

                //PARTIE DECLARATION
                try {
                    Tdeclaration.setInstanceid(Long.parseLong(declaration.getChild("segment_general").getAttribute("INSTANCE_ID").getValue()));
                } catch (Exception e) {
                };

               // System.out.println(declaration.getChild("segment_general").getAttribute("bureau_dec").getValue());

                try {
                    Tdeclaration.setDecRefYer(Long.parseLong(declaration.getChild("segment_general").getAttribute("annee_dec").getValue()));
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeCuoCod(declaration.getChild("segment_general").getAttribute("bureau_dec").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setDecCod(declaration.getChild("segment_general").getAttribute("dec_code").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setDecRefNbr(declaration.getChild("segment_general").getAttribute("repertoire_dec").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeRegSer(declaration.getChild("segment_general").getAttribute("reg_serial").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeRegNbr(declaration.getChild("segment_general").getAttribute("reg_nber").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeRegDat(dateFormat.parse(declaration.getChild("segment_general").getAttribute("date_enreg").getValue()));
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeAstSer(declaration.getChild("segment_general").getAttribute("ast_serial").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeAstNbr(declaration.getChild("segment_general").getAttribute("ast_nbr").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeTypSad(declaration.getChild("segment_general").getAttribute("ide_typ_sad").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeTypPrc(declaration.getChild("segment_general").getAttribute("ide_typ_proc").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeMan(declaration.getChild("segment_general").getAttribute("manifest").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setTptLoc(declaration.getChild("segment_general").getAttribute("localisation").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setPtyNbrItm(Long.parseLong(declaration.getChild("segment_general").getAttribute("nbr_total_art").getValue()));
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setPtyNbrPck(Long.parseLong(declaration.getChild("segment_general").getAttribute("nbr_total_colis").getValue()));
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setCmpExpCod(declaration.getChild("segment_general").getAttribute("ifu_exportateur").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setCmpConCod(declaration.getChild("segment_general").getAttribute("ifu_importateur").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setDecCod(declaration.getChild("segment_general").getAttribute("dec_code").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setDecNam(declaration.getChild("segment_general").getAttribute("nom_declarant").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setFinAmtDty(new BigDecimal(declaration.getChild("segment_general").getAttribute("total_liq").getValue()));
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setFinAmtTbp(new BigDecimal(declaration.getChild("segment_general").getAttribute("total_a_payer").getValue()));
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeRcpSer(declaration.getChild("segment_general").getAttribute("ser_quittance").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeRcpNbr(declaration.getChild("segment_general").getAttribute("num_quittance").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdePstNbr(declaration.getChild("segment_general").getAttribute("num_version").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdePstDat(dateFormat.parse(declaration.getChild("segment_general").getAttribute("ifu_importateur").getValue()));
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setGenCtyDesCod(declaration.getChild("segment_general").getAttribute("cty_destcod").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setTptMotBrdCod(declaration.getChild("segment_general").getAttribute("mot_bord").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setTptMotInl(declaration.getChild("segment_general").getAttribute("mot_inland").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeCuoCod(declaration.getChild("segment_general").getAttribute("cuo_bord").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setFinAmtDty(new BigDecimal(declaration.getChild("segment_general").getAttribute("Valeur_en_douane").getValue()));
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setTptCtf(Short.parseShort(declaration.getChild("segment_general").getAttribute("Indicateur_Conteneur").getValue()));
                } catch (Exception e) {
                };

                try {
                    tDeclarationDouaneFacade.create(Tdeclaration);
                } catch (Exception ex) {
                    logger.error("Probleme lors de la création de la declaration - " + ex.getMessage());
                    //ARCHIVAGE FICHIER
                    in.close();
                    fichier.renameTo(new File(cheminDossierEchecs, fichier.getName()));
                    return;
                    //FIN ARCHIVAGE FICHIER
                }
//FIN PARTIE DECLARATION        
                //PARTIE ARTICLE        

                List<Element> malistedArticles = declaration.getChildren("article");
                List<TArticle> mla = new ArrayList<>();
                //fin
                for (Element ArtElt : malistedArticles) {
                    //construction cle primaire TaxeDeclaration
                    TArticle tarcticle = new TArticle();
                    Long itm_nbr = Long.parseLong(ArtElt.getAttribute("KEY_ITM_NBR").getValue());
                    
                    TArticlePK articlePK = new TArticlePK(instance_id, itm_nbr);
                    tarcticle.setTArticlePK(articlePK);
        //fin construction cle primaire Article       

                    try {
                        tarcticle.setLnkTpt(ArtElt.getAttribute("LNK_TPT").getValue());
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setTarPrcExt(ArtElt.getAttribute("TAR_PRC_EXT").getValue());
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setTarPrcNat(ArtElt.getAttribute("TAR_PRC_NAT").getValue());
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setTarHscNb1(ArtElt.getAttribute("nomenclature").getValue());
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setGdsDsc(ArtElt.getAttribute("GDS_DSC").getValue());
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setPckMrk1(ArtElt.getAttribute("PCK_MRK1").getValue());
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setPckMrk2(ArtElt.getAttribute("PCK_MRK2").getValue());
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setPckTypCod(ArtElt.getAttribute("PCK_TYP_COD").getValue());
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setVitStv((new BigDecimal(ArtElt.getAttribute("PCK_MRK2").getValue())));
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setGdsOrgCty(ArtElt.getAttribute("GDS_ORG_CTY").getValue());
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setVitWgtGrs((new BigDecimal(ArtElt.getAttribute("VIT_WGT_GRS").getValue())));
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setVitWgtNet((new BigDecimal(ArtElt.getAttribute("VIT_WGT_NET").getValue())));
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setTaxAmt((new BigDecimal(ArtElt.getAttribute("TAX_AMT").getValue())));
                    } catch (Exception e) {
                    };
                    tarcticle.setTDeclarationDou(Tdeclaration);

                    try {
                        tArticleFacade.create(tarcticle);
                    } catch (Exception ex) {
                        //tDeclarationDouaneFacade.remove(Tdeclaration);       
                        logger.error("Probleme lors de la création d article - " + ex.getMessage());
                        //ARCHIVAGE FICHIER
                        in.close();
                        fichier.renameTo(new File(cheminDossierEchecs, fichier.getName()));
                        return;
                        //FIN ARCHIVAGE FICHIER
                    }

//PARTIE TAXE
                    List<Element> malistedeTaxes = ArtElt.getChildren("taxe");
                    for (Element TaxeElt : malistedeTaxes) {
                        //construction cle primaire TaxeDeclaration  
                        TTaxeDeclDou taxe = new TTaxeDeclDou();
                        Long tax_rnk = Long.parseLong(TaxeElt.getAttribute("KEY_TAX_RNK").getValue());
                        TTaxeDeclDouPK cleTaxe = new TTaxeDeclDouPK(instance_id, itm_nbr, tax_rnk);
                        taxe.setTTaxeDeclDouPK(cleTaxe);

        //fin construction cle primaire taxe
                        try {
                            taxe.setTaxLinAmt((new BigDecimal(TaxeElt.getAttribute("TAX_LIN_AMT").getValue())));
                        } catch (Exception e) {
                        };
                        try {
                            taxe.setTaxLinBse((new BigDecimal((TaxeElt.getAttribute("TAX_LIN_BSE").getValue()))));
                        } catch (Exception e) {
                        };
                        try {
                            taxe.setTaxLinCod(TaxeElt.getAttribute("TAX_LIN_COD").getValue());
                        } catch (Exception e) {
                        };
                        try {
                            taxe.setTaxLinMop(TaxeElt.getAttribute("TAX_LIN_MOP").getValue());
                        } catch (Exception e) {
                        };
                        try {
                            taxe.setTaxLinRat((new BigDecimal(TaxeElt.getAttribute("TAX_LIN_RAT").getValue())));
                        } catch (Exception e) {
                        };
                        try {
                            taxe.setTaxLinTyp(TaxeElt.getAttribute("TAX_LIN_TYP").getValue());
                        } catch (Exception e) {
                        };
                        taxe.setTArticle(tarcticle);

                        try {
                            tTaxeDecDouFacade.create(taxe);
                        } catch (Exception ex) {                            
                            tArticleFacade.remove(tarcticle);
                            logger.error("Probleme lors de la création de taxe - " + ex.getMessage());
                            //ARCHIVAGE FICHIER
                            in.close();
                            fichier.renameTo(new File(cheminDossierEchecs, fichier.getName()));
                            return;
                            //FIN ARCHIVAGE FICHIER
                        }

                    }                   
                }
                logger.info("Declaration créée ...... " + decl);
                    //ARCHIVAGE FICHIER
                    in.close();
                    fichier.renameTo(new File(cheminDossierSucces, fichier.getName()));
                    //FIN ARCHIVAGE FICHIER
            }
        } else {
            if ("M".equals(typeope)) {
                 //PARTIE DECLARATION               
                Tdeclaration = new TDeclarationDou();        
                try {
                     tDeclarationDouaneFacade.supprimer(instance_id);                     
                } catch (Exception e) {
                    logger.error("Problème lors de la suppression de la declaration - " + e.getMessage());
                }
                
                Tdeclaration.setInstanceid(instance_id);
                try {
                    Tdeclaration.setDecRefYer(Long.parseLong(declaration.getChild("segment_general").getAttribute("annee_dec").getValue()));
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeCuoCod(declaration.getChild("segment_general").getAttribute("bureau_dec").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setDecCod(declaration.getChild("segment_general").getAttribute("dec_code").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setDecRefNbr(declaration.getChild("segment_general").getAttribute("repertoire_dec").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeRegSer(declaration.getChild("segment_general").getAttribute("reg_serial").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeRegNbr(declaration.getChild("segment_general").getAttribute("reg_nber").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeRegDat(dateFormat.parse(declaration.getChild("segment_general").getAttribute("date_enreg").getValue()));
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeAstSer(declaration.getChild("segment_general").getAttribute("ast_serial").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeAstNbr(declaration.getChild("segment_general").getAttribute("ast_nbr").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeTypSad(declaration.getChild("segment_general").getAttribute("ide_typ_sad").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeTypPrc(declaration.getChild("segment_general").getAttribute("ide_typ_proc").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeMan(declaration.getChild("segment_general").getAttribute("manifest").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setTptLoc(declaration.getChild("segment_general").getAttribute("localisation").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setPtyNbrItm(Long.parseLong(declaration.getChild("segment_general").getAttribute("nbr_total_art").getValue()));
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setPtyNbrPck(Long.parseLong(declaration.getChild("segment_general").getAttribute("nbr_total_colis").getValue()));
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setCmpExpCod(declaration.getChild("segment_general").getAttribute("ifu_exportateur").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setCmpConCod(declaration.getChild("segment_general").getAttribute("ifu_importateur").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setDecCod(declaration.getChild("segment_general").getAttribute("dec_code").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setDecNam(declaration.getChild("segment_general").getAttribute("nom_declarant").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setFinAmtDty(new BigDecimal(declaration.getChild("segment_general").getAttribute("total_liq").getValue()));
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setFinAmtTbp(new BigDecimal(declaration.getChild("segment_general").getAttribute("total_a_payer").getValue()));
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeRcpSer(declaration.getChild("segment_general").getAttribute("ser_quittance").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeRcpNbr(declaration.getChild("segment_general").getAttribute("num_quittance").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdePstNbr(declaration.getChild("segment_general").getAttribute("num_version").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdePstDat(dateFormat.parse(declaration.getChild("segment_general").getAttribute("ifu_importateur").getValue()));
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setGenCtyDesCod(declaration.getChild("segment_general").getAttribute("cty_destcod").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setTptMotBrdCod(declaration.getChild("segment_general").getAttribute("mot_bord").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setTptMotInl(declaration.getChild("segment_general").getAttribute("mot_inland").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setIdeCuoCod(declaration.getChild("segment_general").getAttribute("cuo_bord").getValue());
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setFinAmtDty(new BigDecimal(declaration.getChild("segment_general").getAttribute("Valeur_en_douane").getValue()));
                } catch (Exception e) {
                };
                try {
                    Tdeclaration.setTptCtf(Short.parseShort(declaration.getChild("segment_general").getAttribute("Indicateur_Conteneur").getValue()));
                } catch (Exception e) {
                };

                try {
                    tDeclarationDouaneFacade.create(Tdeclaration);
                } catch (Exception ex) {
                    logger.error("Probleme lors de la création de la declaration - " + ex.getMessage());
                    //ARCHIVAGE FICHIER
                    in.close();
                    fichier.renameTo(new File(cheminDossierEchecs, fichier.getName()));
                    return;
                    //FIN ARCHIVAGE FICHIER
                }
//FIN PARTIE DECLARATION        
                //PARTIE ARTICLE        

                List<Element> malistedArticles = declaration.getChildren("article");
                List<TArticle> mla = new ArrayList<>();
                //fin
                for (Element ArtElt : malistedArticles) {
                    //construction cle primaire TaxeDeclaration
                    TArticle tarcticle = new TArticle();
                    Long itm_nbr = Long.parseLong(ArtElt.getAttribute("KEY_ITM_NBR").getValue());                    
                    TArticlePK articlePK = new TArticlePK(instance_id, itm_nbr);
                    tarcticle.setTArticlePK(articlePK);
        //fin construction cle primaire Article       

                    try {
                        tarcticle.setLnkTpt(ArtElt.getAttribute("LNK_TPT").getValue());
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setTarPrcExt(ArtElt.getAttribute("TAR_PRC_EXT").getValue());
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setTarPrcNat(ArtElt.getAttribute("TAR_PRC_NAT").getValue());
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setTarHscNb1(ArtElt.getAttribute("nomenclature").getValue());
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setGdsDsc(ArtElt.getAttribute("GDS_DSC").getValue());
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setPckMrk1(ArtElt.getAttribute("PCK_MRK1").getValue());
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setPckMrk2(ArtElt.getAttribute("PCK_MRK2").getValue());
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setPckTypCod(ArtElt.getAttribute("PCK_TYP_COD").getValue());
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setVitStv((new BigDecimal(ArtElt.getAttribute("PCK_MRK2").getValue())));
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setGdsOrgCty(ArtElt.getAttribute("GDS_ORG_CTY").getValue());
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setVitWgtGrs((new BigDecimal(ArtElt.getAttribute("VIT_WGT_GRS").getValue())));
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setVitWgtNet((new BigDecimal(ArtElt.getAttribute("VIT_WGT_NET").getValue())));
                    } catch (Exception e) {
                    };
                    try {
                        tarcticle.setTaxAmt((new BigDecimal(ArtElt.getAttribute("TAX_AMT").getValue())));
                    } catch (Exception e) {
                    };
                    tarcticle.setTDeclarationDou(Tdeclaration);

                    try {
                        tArticleFacade.create(tarcticle);
                    } catch (Exception ex) {
                        //tDeclarationDouaneFacade.remove(Tdeclaration);       
                        logger.error("Probleme lors de la création d article - " + ex.getMessage());
                        //ARCHIVAGE FICHIER
                        in.close();
                        fichier.renameTo(new File(cheminDossierEchecs, fichier.getName()));
                        return;
                        //FIN ARCHIVAGE FICHIER
                    }

//PARTIE TAXE
                    List<Element> malistedeTaxes = ArtElt.getChildren("taxe");
        //System.out.println(nbre_taxes + " nbre taxes ----------------");

                    for (Element TaxeElt : malistedeTaxes) {
                        //construction cle primaire TaxeDeclaration  
                        TTaxeDeclDou taxe = new TTaxeDeclDou();
                        Long tax_rnk = Long.parseLong(TaxeElt.getAttribute("KEY_TAX_RNK").getValue());
                        TTaxeDeclDouPK cleTaxe = new TTaxeDeclDouPK(instance_id, itm_nbr, tax_rnk);
                        taxe.setTTaxeDeclDouPK(cleTaxe);

        //fin construction cle primaire taxe
                        try {
                            taxe.setTaxLinAmt((new BigDecimal(TaxeElt.getAttribute("TAX_LIN_AMT").getValue())));
                        } catch (Exception e) {
                        };
                        try {
                            taxe.setTaxLinBse((new BigDecimal((TaxeElt.getAttribute("TAX_LIN_BSE").getValue()))));
                        } catch (Exception e) {
                        };
                        try {
                            taxe.setTaxLinCod(TaxeElt.getAttribute("TAX_LIN_COD").getValue());
                        } catch (Exception e) {
                        };
                        try {
                            taxe.setTaxLinMop(TaxeElt.getAttribute("TAX_LIN_MOP").getValue());
                        } catch (Exception e) {
                        };
                        try {
                            taxe.setTaxLinRat((new BigDecimal(TaxeElt.getAttribute("TAX_LIN_RAT").getValue())));
                        } catch (Exception e) {
                        };
                        try {
                            taxe.setTaxLinTyp(TaxeElt.getAttribute("TAX_LIN_TYP").getValue());
                        } catch (Exception e) {
                        };
                        taxe.setTArticle(tarcticle);

                        try {
                            tTaxeDecDouFacade.create(taxe);
                        } catch (Exception ex) {
                            //tDeclarationDouaneFacade.remove(Tdeclaration);
                            tArticleFacade.remove(tarcticle);
                            logger.error("Probleme lors de la création de taxe - " + ex.getMessage());
                            //ARCHIVAGE FICHIER
                            in.close();
                            fichier.renameTo(new File(cheminDossierEchecs, fichier.getName()));
                            return;
                            //FIN ARCHIVAGE FICHIER
                        }
                    }

                    
                    //ARCHIVAGE FICHIER
//                    in.close();
//                    fichier.renameTo(new File(cheminDossierSucces, fichier.getName()));
                    //FIN ARCHIVAGE FICHIER
                    

                }
}        logger.info("Declaration Modifiée ..... " + decl);
        //ARCHIVAGE FICHIER
        in.close();
        fichier.renameTo(new File(cheminDossierSucces, fichier.getName()));
        //FIN ARCHIVAGE FICHIER
        }
    }

   ////////////////////////// FIN INSERTION DECLARATION ARTICLE TAXE
    //traitement table contribuable 
   
     public void traitementDeDonnesCont(Document document, File fichier, InputStream in) throws JDOMException, SAXException, IOException, UserOrMotifUndefined {
        Element racine = document.getRootElement();
        System.err.println("CA DONNE = " + racine.getName());
        Element operation = racine.getChild("OPERATION");
        TTypeContrib ttrContrib = new TTypeContrib();
        TRepUnique tRepUnique = new TRepUnique();
        Element typeOperation = operation.getChild("TYPEOP");
        System.out.println(typeOperation.getValue());
        Element contribuable = racine.getChild("CONT");
        Element centreImpot = racine.getChild("CENTRE_IMPOT");
        Element contCode = racine.getChild("CONT_CODE");
        
        ttrContrib = tTypeContribFacade.find(contCode.getChild("TYP_CONT_CODE").getValue());
        if (ttrContrib == null) {
            ttrContrib = new TTypeContrib(contCode.getChild("TYP_CONT_CODE").getValue(), contCode.getChild("TYP_CONT_LIB").getValue());
            tTypeContribFacade.create(ttrContrib);
        }

        tRepUnique = tRepUniqueFacade.find(Long.valueOf(contribuable.getChild("CONT_IMMATR").getValue()));
        System.out.println("-----***********----------------");
        if (tRepUnique == null) {
            if ("A".equals(typeOperation.getValue())) {
                System.out.println("ESSAIIIII     IIIIIIIIIII");
                tRepUnique = new TRepUnique();
                try {
                    tRepUnique.setContDatenreg(dateFormat.parse(contribuable.getChild("CONT_DATENREG").getValue()));
                } catch (Exception e) {
                    System.out.println("Erreur date enreg" + e);
                };
                try {
                    tRepUnique.setContImmatr(Long.parseLong(contribuable.getChild("CONT_IMMATR").getValue()));
                } catch (Exception e) {
                    System.out.println("Erreur immatr" + e);
                };
                try {
                    tRepUnique.setContNum(contribuable.getChild("CONT_NUM").getValue());
                } catch (Exception e) {
                    System.out.println("Erreur cont_num" + e);
                }
                try {
                    tRepUnique.setContNom(contribuable.getChild("CONT_NOM").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContPren(contribuable.getChild("CONT_PREN").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContDatnais(dateFormat.parse(contribuable.getChild("CONT_DATNAIS").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContActif(contribuable.getChild("CONT_ACTIF").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContBp(contribuable.getChild("CONT_BP").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContCapital(BigDecimal.valueOf(Long.parseLong(contribuable.getChild("CONT_CAPITAL").getValue())).toBigInteger());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContCatEtabCode(contribuable.getChild("CONT_CAT_ETAB_CODE").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContCentrCode(contribuable.getChild("CONT_CENTR_CODE").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContDatcreat(dateFormat.parse(contribuable.getChild("CONT_DATCREAT").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContDateCessation(dateFormat.parse(contribuable.getChild("CONT_DATE_CESSATION").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContDateDeces(dateFormat.parse(contribuable.getChild("CONT_DATE_DECES").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContDateMajMatricule(dateFormat.parse(contribuable.getChild("CONT_DATE_MAJ_MATRICULE").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContDateRepriseAct(dateFormat.parse(contribuable.getChild("CONT_DATE_REPRISE_ACT").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContDateimmatr(dateFormat.parse(contribuable.getChild("CONT_DATE_IMMATR").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContDatenregInsae(dateFormat.parse(contribuable.getChild("CONT_DATENREG_INSAE").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContEnsCommerce(contribuable.getChild("CONT_ENS_COMMERCE").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContFax(BigDecimal.valueOf(Long.parseLong(contribuable.getChild("CONT_FAX").getValue())).toBigInteger());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContFonctCode(contribuable.getChild("CONT_FONCT_CODE").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContLieunais(contribuable.getChild("CONT_LIEUNAIS").getValue());
                } catch (Exception e) {
                }

                try {
                    tRepUnique.setContLot(contribuable.getChild("CONT_LOT").getValue());
                } catch (Exception e) {
                }

                try {
                    tRepUnique.setContLoyer(Long.parseLong(contribuable.getChild("CONT_LOYER").getValue()));
                } catch (Exception e) {
                }

                try {
                    tRepUnique.setContMail(contribuable.getChild("CONT_MAIL").getValue());
                } catch (Exception e) {
                }

                try {
                    tRepUnique.setContMatricule(contribuable.getChild("CONT_MATRICULE").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContMemActMere(contribuable.getChild("CONT_MEM_ACT_MERE").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContMemBank(contribuable.getChild("CONT_MEM_BANK").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContModExpCode(contribuable.getChild("CONT_MOD_EXP_CODE").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContNationCode(contribuable.getChild("CONT_NATION_CODE").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContNbEmpl(Short.parseShort(contribuable.getChild("CONT_NB_EMPL").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContNbEnf(Short.parseShort(contribuable.getChild("CONT_NB_ENF").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContNeVers(contribuable.getChild("CONT_NE_VERS").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContNewImmatr(Long.parseLong(contribuable.getChild("CONT_NEW_IMMATR").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContNomCourt(contribuable.getChild("CONT_NOM_COURT").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContNomJf(contribuable.getChild("CONT_Nom_JF").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContNomLong(contribuable.getChild("CONT_Nom_LONG").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContNompropsieg(contribuable.getChild("CONT_NOMPROPSIEG").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContNuminsae(contribuable.getChild("CONT_NUMINSAE").getValue());
                } catch (Exception e) {
                }
                 try {
                    tRepUnique.setContNuminsae1(contribuable.getChild("CONT_NUMINSAE1").getValue());
                } catch (Exception e) {
                }
                  try {
                    tRepUnique.setContOrdre(BigDecimal.valueOf(Long.parseLong(contribuable.getChild("CONT_NUMINSAE").getValue())).toBigInteger());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContPrnompropsieg(contribuable.getChild("CONT_PRNOMPROPSIEG").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContProfCode(contribuable.getChild("CONT_PROF_CODE").getValue());
                } catch (Exception e) {
                }
                 try {
                    tRepUnique.setContProp(contribuable.getChild("CONT_PROP").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContQuart(contribuable.getChild("CONT_QUART").getValue());
                } catch (Exception e) {
                }
                 try { 
                    tRepUnique.setContRais(contribuable.getChild("CONT_RAIS").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContReimmatr(contribuable.getChild("CONT_REIMMATR").getValue());
                } catch (Exception e) {
                }
                
                try {
                    tRepUnique.setContRue(contribuable.getChild("CONT_RUE").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContSexe(contribuable.getChild("CONT_SEXE").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContSitMat(contribuable.getChild("CONT_SIT_MAT").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContStatut(contribuable.getChild("CONT_STATUT").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContTel(BigDecimal.valueOf(Long.parseLong(contribuable.getChild("CONT_TEL").getValue())).toBigInteger());
                } catch (Exception e) {
                }
                
                try {
                    tRepUnique.setContVille(contribuable.getChild("CONT_VILLE").getValue());
                } catch (Exception e) {
                }  

                tRepUnique.setContCentrCode(contribuable.getChild("CONT_CENTR_CODE").getValue());
                tRepUnique.setContTypContCode(ttrContrib);

                TMotif tMotif = tMotifFacade.find("A");
                TUtilisateur tUtilisateur = tUtilisateurFacade.find("daemon");
                if (tMotif == null || tUtilisateur == null) {
                    throw new UserOrMotifUndefined("");
                }
                
                tRepUniqueFacade.create(tRepUnique);
                tHistoriqueFacade.historiser(tRepUnique, tMotif, tUtilisateur);
                
                in.close();
                fichier.renameTo(new File(cheminDossierSucces, fichier.getName()));
            } else {
                System.out.println("Ajout impossible pour cause de fichier existant " + fichier.getName());
                in.close();
                fichier.renameTo(new File(cheminDossierEchecs, fichier.getName()));
            }
            return;
        } else {
            if ("M".equals(typeOperation.getValue())) {
                System.out.println("Je suis dans la modification");
                try {
                    tRepUnique.setContDatenreg(dateFormat.parse(contribuable.getChild("CONT_DATENREG").getValue()));
                } catch (Exception e) {
                    System.out.println("Erreur date enreg" + e);
                };
                try {
                    tRepUnique.setContImmatr(Long.parseLong(contribuable.getChild("CONT_IMMATR").getValue()));
                } catch (Exception e) {
                    System.out.println("Erreur immatr" + e);
                };
                try {
                    tRepUnique.setContNum(contribuable.getChild("CONT_NUM").getValue());
                } catch (Exception e) {
                    System.out.println("Erreur cont_num" + e);
                }
                try {
                    tRepUnique.setContNom(contribuable.getChild("CONT_NOM").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContPren(contribuable.getChild("CONT_PREN").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContDatnais(dateFormat.parse(contribuable.getChild("CONT_DATNAIS").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContActif(contribuable.getChild("CONT_ACTIF").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContBp(contribuable.getChild("CONT_BP").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContCapital(BigDecimal.valueOf(Long.parseLong(contribuable.getChild("CONT_CAPITAL").getValue())).toBigInteger());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContCatEtabCode(contribuable.getChild("CONT_CAT_ETAB_CODE").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContCentrCode(contribuable.getChild("CONT_CENTR_CODE").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContDatcreat(dateFormat.parse(contribuable.getChild("CONT_DATCREAT").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContDateCessation(dateFormat.parse(contribuable.getChild("CONT_DATE_CESSATION").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContDateDeces(dateFormat.parse(contribuable.getChild("CONT_DATE_DECES").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContDateMajMatricule(dateFormat.parse(contribuable.getChild("CONT_DATE_MAJ_MATRICULE").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContDateRepriseAct(dateFormat.parse(contribuable.getChild("CONT_DATE_REPRISE_ACT").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContDateimmatr(dateFormat.parse(contribuable.getChild("CONT_DATE_IMMATR").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContDatenregInsae(dateFormat.parse(contribuable.getChild("CONT_DATENREG_INSAE").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContEnsCommerce(contribuable.getChild("CONT_ENS_COMMERCE").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContFax(BigDecimal.valueOf(Long.parseLong(contribuable.getChild("CONT_FAX").getValue())).toBigInteger());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContFonctCode(contribuable.getChild("CONT_FONCT_CODE").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContLieunais(contribuable.getChild("CONT_LIEUNAIS").getValue());
                } catch (Exception e) {
                }

                try {
                    tRepUnique.setContLot(contribuable.getChild("CONT_LOT").getValue());
                } catch (Exception e) {
                }

                try {
                    tRepUnique.setContLoyer(Long.parseLong(contribuable.getChild("CONT_LOYER").getValue()));
                } catch (Exception e) {
                }

                try {
                    tRepUnique.setContMail(contribuable.getChild("CONT_MAIL").getValue());
                } catch (Exception e) {
                }

                try {
                    tRepUnique.setContMatricule(contribuable.getChild("CONT_MATRICULE").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContMemActMere(contribuable.getChild("CONT_MEM_ACT_MERE").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContMemBank(contribuable.getChild("CONT_MEM_BANK").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContModExpCode(contribuable.getChild("CONT_MOD_EXP_CODE").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContNationCode(contribuable.getChild("CONT_NATION_CODE").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContNbEmpl(Short.parseShort(contribuable.getChild("CONT_NB_EMPL").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContNbEnf(Short.parseShort(contribuable.getChild("CONT_NB_ENF").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContNeVers(contribuable.getChild("CONT_NE_VERS").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContNewImmatr(Long.parseLong(contribuable.getChild("CONT_NEW_IMMATR").getValue()));
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContNomCourt(contribuable.getChild("CONT_NOM_COURT").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContNomJf(contribuable.getChild("CONT_Nom_JF").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContNomLong(contribuable.getChild("CONT_Nom_LONG").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContNompropsieg(contribuable.getChild("CONT_NOMPROPSIEG").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContNuminsae(contribuable.getChild("CONT_NUMINSAE").getValue());
                } catch (Exception e) {
                }
                 try {
                    tRepUnique.setContNuminsae1(contribuable.getChild("CONT_NUMINSAE1").getValue());
                } catch (Exception e) {
                }
                  try {
                    tRepUnique.setContOrdre(BigDecimal.valueOf(Long.parseLong(contribuable.getChild("CONT_NUMINSAE").getValue())).toBigInteger());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContPrnompropsieg(contribuable.getChild("CONT_PRNOMPROPSIEG").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContProfCode(contribuable.getChild("CONT_PROF_CODE").getValue());
                } catch (Exception e) {
                }
                 try {
                    tRepUnique.setContProp(contribuable.getChild("CONT_PROP").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContQuart(contribuable.getChild("CONT_QUART").getValue());
                } catch (Exception e) {
                }
                 try { 
                    tRepUnique.setContRais(contribuable.getChild("CONT_RAIS").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContReimmatr(contribuable.getChild("CONT_REIMMATR").getValue());
                } catch (Exception e) {
                }
                
                try {
                    tRepUnique.setContRue(contribuable.getChild("CONT_RUE").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContSexe(contribuable.getChild("CONT_SEXE").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContSitMat(contribuable.getChild("CONT_SIT_MAT").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContStatut(contribuable.getChild("CONT_STATUT").getValue());
                } catch (Exception e) {
                }
                try {
                    tRepUnique.setContTel(BigDecimal.valueOf(Long.parseLong(contribuable.getChild("CONT_TEL").getValue())).toBigInteger());
                } catch (Exception e) {
                }
                
                try {
                    tRepUnique.setContVille(contribuable.getChild("CONT_VILLE").getValue());
                } catch (Exception e) {
                }
                
                tRepUnique.setContCentrCode(contribuable.getChild("CONT_CENTR_CODE").getValue());

                tRepUnique.setContTypContCode(ttrContrib);
                
                TMotif tMotif = tMotifFacade.find("M");
                TUtilisateur tUtilisateur = tUtilisateurFacade.find("daemon");
                if (tMotif == null || tUtilisateur == null) {
                    throw new UserOrMotifUndefined("");
                }
                
                tRepUniqueFacade.edit(tRepUnique);
                tHistoriqueFacade.historiser(tRepUnique, tMotif, tUtilisateur);

                System.out.println("cest ici");
                in.close();
                System.out.println("passe");
                fichier.renameTo(new File(cheminDossierSucces, fichier.getName()));
            } else {
                System.out.println("inpossible de modifier un contribuable inexistant " + fichier.getName());
                in.close();
                fichier.renameTo(new File(cheminDossierEchecs, fichier.getName()));
            }
            return;
        }
    }


    private class UserOrMotifUndefined extends Exception {

        UserOrMotifUndefined(String s) {
            super(s);
        }

        public String toString() {
            return ("Exception Number ");
        }
    };
   //fin traitement table contribuable
}
