/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.controlers.util;

/**
 *
 * @author AAKAKPO
 */
import java.math.BigDecimal;

public abstract class Convert {
    private static String result, frac, tranche, dec, cent, un, dix;
    private static int rang, virgule, lg;
    private static String traiter(String nombre) {
        nombre= nombre.replace(',', '.');
        nombre= nombre.replace(" ", "");
        if (nombre.charAt(0)== '-')
            nombre= nombre.substring(1);
        virgule= nombre.indexOf('.');
        frac= "";
        if (virgule> -1) {
            frac= nombre.substring(virgule+ 1, ((nombre.length()- virgule)< 3? nombre.length(): virgule+ 3));
            nombre= nombre.substring(0, virgule);
        }
        while (frac.length()< 2)
            frac+= "0";
        if (nombre.length()> 18)
            nombre= "#Trop grand";
        else 
            try {
                new BigDecimal(nombre+ "."+ frac);
                rang= ((nombre.length()+ 2)/ 3);
            }
            catch (Exception e) {
                nombre= "#Erreur";
            }
        return nombre;
    }
    public static String AR(String nombre, String devise, String sou) {
        String[] n1= {"", "\u0648\u0627\u062d\u062f", "\u0625\u062b\u0646\u0627\u0646", "\u062b\u0644\u0627\u062b", "\u0623\u0631\u0628\u0639", "\u062e\u0645\u0633", "\u0633\u062a", "\u0633\u0628\u0639", "\u062b\u0645\u0627\u0646", "\u062a\u0633\u0639", "\u0639\u0634\u0631", "\u0623\u062d\u062f", "\u0625\u062b\u0646\u0627"},
                 //æÇÍÏ¡ ÅËäÇä¡ ËáÇË¡ .... ÚÔÑ¡ ÃÍÏ¡ ÅËäÇ,""
                 n10= {"", "\u0639\u0634\u0631", "\u0639\u0634\u0631\u0648\u0646", "\u062b\u0644\u0627\u062b\u0648\u0646", "\u0623\u0631\u0628\u0639\u0648\u0646", "\u062e\u0645\u0633\u0648\u0646", "\u0633\u062a\u0648\u0646", "\u0633\u0628\u0639\u0648\u0646", "\u062b\u0645\u0627\u0646\u0648\u0646", "\u062a\u0633\u0639\u0648\u0646"},
                 //ÚÔÑ¡ÚÔÑæä¡ ËáÇËæä......... ÊÓÚæä,""
                 n1000= {"", "", "", "\u0623\u0644\u0641", "\u0623\u0644\u0641\u0627\u0646", "\u0622\u0644\u0627\u0641", "\u0645\u0644\u064a\u0648\u0646", "\u0645\u0644\u064a\u0648\u0646\u0627\u0646", "\u0645\u0644\u0627\u064a\u064a\u0646", "\u0645\u0644\u064a\u0627\u0631", "\u0645\u0644\u064a\u0627\u0631\u0627\u0646", "\u0645\u0644\u0627\u064a\u064a\u0631", "\u0628\u0644\u064a\u0648\u0646", "\u0628\u0644\u064a\u0648\u0646\u0627\u0646", "\u0628\u0644\u0627\u064a\u064a\u0646", "\u0628\u0644\u064a\u0627\u0631", "\u0628\u0644\u064a\u0627\u0631\u0627\u0646", "\u0628\u0644\u0627\u064a\u064a\u0631"};
                 //ÃáÝ¡ÃáÝÇä¡ÂáÇÝ¡ãáíæä¡ãáíæäÇä¡ãáÇííä¡ãáíÇÑ¡ãáíÇÑÇä¡ãáÇííÑ¡Èáíæä¡ÈáíæäÇä¡ÈáÇííä¡ÈáíÇÑ¡ÈáíÇÑÇä¡ÈáÇííÑ,"","",""
        nombre= traiter(nombre);
        if (nombre.equals("#Erreur"))
            result= "\u062e\u0637\u0623#";
        else
            if (nombre.equals("#Trop grand"))
                result= "\u0643\u0628\u064a\u0631 \u062c\u062F\u0627#";
            else {
                result= "";
                while (rang> 0)    {
                    if (result== "") {
                        tranche= nombre.substring(0, ((nombre.length()+ 2)% 3)+ 1);
                        while (tranche.length()< 3)
                        tranche= "0"+ tranche;
                    }
                    else
                        tranche= nombre.substring(nombre.length()- (rang* 3), nombre.length()- ((rang- 1)* 3));
                    dec= tranche;
                    dec= dec.substring(1, 3);
                    if (dec.length()== 1) {
                        un= dec;
                        dix= "0";
                    }
                    else {
                        un= dec.substring(1);
                        dix= dec.substring(0, 1);
                    }
                    cent= tranche;
                    cent= cent.substring(0, 1);
                    switch (Integer.parseInt(cent)) {
                        case 0:
                        break;
                        case 1: result+= " \u0648\u0645\u0627\u0626\u0629";    //æãÇÆÉ
                        break;
                        case 2: result+= " \u0648\u0645\u0627\u0626\u062a\u0627\u0646";    //æãÇÆÊÇä
                        break;
                        default: result+= " \u0648"+ n1[Integer.parseInt(cent)]+ " \u0645\u0627\u0626\u0629";    //æ× ãÇÆÉ
                    }
                    switch (Integer.parseInt(dec)) {
                        case 0:
                            if (!cent.equals("0"))
                                result+= " "+ n1000[(rang- 1)* 3];
                        break;
                        case 1:
                            if (rang== 1)
                                result+= " \u0648"+ n1[Integer.parseInt(dec)];
                            else
                                result+= " \u0648"+ n1000[(rang- 1)* 3];
                        break;
                        case 2:
                            if (rang== 1)
                                result+= " \u0648"+ n1[Integer.parseInt(dec)];
                            else
                                result+= " \u0648"+ n1000[((rang- 1)* 3)+ 1];
                        break;
                        case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10:
                            result+= " \u0648"+ n1[Integer.parseInt(dec)]+ " "+ n1000[((rang- 1)* 3)+ 2];
                        break;
                        case 11: case 12:
                            result+= " \u0648"+ n1[Integer.parseInt(dec)]+ " "+ n10[1]+ " "+ n1000[(rang- 1)* 3];
                        break;
                        case 18:
                            result+= " \u0648"+ "\u062b\u0645\u0627\u0646\u064a"+ " "+ n10[1]+ " "+ n1000[(rang- 1)* 3];
                        break;
                        case 13: case 14:  case 15: case 16: case 17: case 19:
                            result+= " \u0648"+ n1[Integer.parseInt(un)]+ " "+ n10[1]+ " "+ n1000[(rang- 1)* 3];
                        break;
                        default:
                            result+= (un.equals("0")? " \u0648"+ n10[Integer.parseInt(dix)]+ " "+ n1000[(rang- 1)* 3]: " \u0648"+ n1[Integer.parseInt(un)]+ " \u0648"+ n10[Integer.parseInt(dix)]+ " "+ n1000[(rang- 1)* 3]);
                        break;
                    }
                    rang--;
                }
                if (result.equals(""))
                    result= "  \u0635\u0641\u0631 "+ devise;
                else
                    result+= " "+ devise;
                un= Character.toString(frac.charAt(1));
                dix= Character.toString(frac.charAt(0));
                switch (Integer.parseInt(frac)) {
                    case 0:
                        result+= "  \u0648\u0635\u0641\u0631 "+ sou;
                    break;
                    case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10:
                        result+= " \u0648"+ n1[Integer.parseInt(frac)]+ " "+ sou;
                    break;
                    case 11: case 12:
                        result+= " \u0648"+ n1[Integer.parseInt(frac)]+ " "+ n10[1]+ " "+ sou;
                    break;
                    case 18:
                        result+= " \u0648"+ "\u062b\u0645\u0627\u0646\u064a"+ " "+ n10[1]+ " "+ sou;
                    break;
                    case 13: case 14: case 15: case 16: case 17: case 19:
                        result+= " \u0648"+ n1[Integer.parseInt(un)]+ " "+ n10[1]+ " "+ sou;
                    break;
                    default:
                        result+= (un.equals("0")? " \u0648"+ n10[Integer.parseInt(dix)]: " \u0648"+ n1[Integer.parseInt(un)]+ " \u0648"+ n10[Integer.parseInt(dix)])+ " "+ sou;
                }
                result= result.substring(2);
            }
        return result;
    }
    public static String AR(String nombre) {
        return AR(nombre, "\u062f\u064a\u0646\u0627\u0631 \u062c\u0632\u0627\u0626\u0631\u064a", "\u0633\u0646\u062a\u064a\u0645");
    }
    
    //=============================================================================================================================

    public static String FR(String nombre, String devise, String sou) {
        String[] n1= {"", " Un", " Deux", " Trois", " Quatre", " Cinq", " Six", " Sept", " Huit", " Neuf", " Dix", " Onze", " Douze", " Treize", " Quatorze", " Quinze", " Seize", " Dix-sept", " Dix-huit", " Dix-neuf"},
                 n70= {" Soixante et Onze", " Soixante-Douze", " Soixante-Treize", " Soixante-Quatorze", " Soixante-Quinze", " Soixante-Seize", " Soixante-Dix-sept", " Soixante-Dix-huit", " Soixante-Dix-neuf"},
                 n90= {" Quatre-vingt-Onze", " Quatre-vingt-Douze", " Quatre-vingt-Treize", " Quatre-vingt-Quatorze", " Quatre-vingt-Quinze", " Quatre-vingt-Seize", " Quatre-vingt-Dix-sept", " Quatre-vingt-Dix-huit", " Quatre-vingt-Dix-neuf"},
                 n10= {"", " Dix", " Vingt", " Trente", " Quarante", " Cinquante", " Soixante", " Soixante-Dix", " Quatre-vingt", " Quatre-vingt-Dix"},
                 n1000= {"", " Mille", " Million", " Milliard", " Billion", " Billiard"};
        nombre= traiter(nombre);
        if ((nombre.equals("#Erreur"))||(nombre.equals("#Trop grand")))
            result= nombre;
        else {
            result= "";
            while (rang> 0)    {
                if (result== "") {
                    tranche= nombre.substring(0, ((nombre.length()+ 2)% 3)+ 1);
                    while (tranche.length()< 3)
                    tranche= "0"+ tranche;
                }
                else
                    tranche= nombre.substring(nombre.length()- (rang* 3), nombre.length()- ((rang- 1)* 3));
                dec= tranche;
                dec= dec.substring(1, 3);
                if (dec.length()== 1) {
                    un= dec;
                    dix= "0";
                }
                else {
                    un= dec.substring(1);
                    dix= dec.substring(0, 1);
                }
                cent= tranche;
                cent= cent.substring(0, 1);
                switch (Integer.parseInt(cent)) {
                    case 0:
                    break;
                    case 1: result+= " Cent";
                    break;
                    default: result+= n1[Integer.parseInt(cent)]+ " Cent";
                }
                switch (Integer.parseInt(dec)) {
                    case 0:
                        if (!cent.equals("0"))
                            result+= n1000[rang- 1];
                    break;
                    case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19:
                        result+= n1[Integer.parseInt(dec)]+ n1000[rang- 1];
                    break;
                    case 71: case 72: case 73: case 74: case 75: case 76: case 77: case 78: case 79:
                        result+= n70[Integer.parseInt(un)- 1]+ n1000[rang- 1];
                    break;
                    case 91: case 92: case 93: case 94: case 95: case 96: case 97: case 98: case 99:
                        result+= n90[Integer.parseInt(un)- 1]+ n1000[rang- 1];
                    break;
                    default:
                        if ((!dix.equals("0"))&&(un.equals("1")))
                            result+= n10[Integer.parseInt(dix)]+ " et Un"+ n1000[rang- 1];
                        else
                            result+= n10[Integer.parseInt(dix)]+ n1[Integer.parseInt(un)]+ n1000[rang- 1];
                }
                rang--;
            }
            if (result.equals(""))
                result= " Zero "+ devise;//+ " et";
            else
                result+= " "+ devise;//+ " et";
//            un= Character.toString(frac.charAt(1));
//            dix= Character.toString(frac.charAt(0));
//            switch (Integer.parseInt(frac)) {
//                case 0:
//                    result+= " Zero "+ sou;
//                break;
//                case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19:
//                    result+= n1[Integer.parseInt(frac)]+ " "+ sou;
//                break;
//                case 71: case 72: case 73: case 74: case 75: case 76: case 77: case 78: case 79:
//                    result+= n70[Integer.parseInt(un)- 1]+ " "+ sou;
//                break;
//                case 91: case 92: case 93: case 94: case 95: case 96: case 97: case 98: case 99:
//                    result+= n90[Integer.parseInt(un)- 1]+ " "+ sou;
//                break;
//                default:
//                    if ((!dix.equals("0"))&&(un.equals("1")))
//                        result+= n10[Integer.parseInt(dix)]+ " et Un "+ sou;
//                    else
//                        result+= n10[Integer.parseInt(dix)]+ n1[Integer.parseInt(un)]+ " "+ sou;
//            }
            result= result.substring(1);
        }
        return result;
    }
    public static String FR(String nombre) {
        return FR(nombre, "Dinars Algériens", "Centimes");
    }
    
//    public static String FR(String nombre) {
//        return FR(nombre, "Dinars Algériens", "Centimes");
//    }
}