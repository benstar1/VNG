/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.controllers.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author SANNI Emmanuel
 */
public class KPaxData {

    private List<Object[]> data;
    private String[] titles;
    private int[] keys;
    private int[] indexes;
    private List<Object[]> source1;
    private List<Object[]> source2;
    private int srcFlag;
    

    public static List<Object[]> merge(final List<Object[]> list1, final List<Object[]> list2, int[] keys, int[] indexes ) {

        List<Object[]> outputList = new ArrayList<>();
        Object[] mergedLine;
        
        for (Object[] line1 : list1) {
            mergedLine = new Object[line1.length + 2];
            boolean egal = false;
            Object[] sameLine = null;
            compare:
            for (Object[] line2 : list2) {
                if( line1.length != line2.length){
                    throw new IllegalArgumentException("Illegal Argument:  les tableaux manipulés doivent etre de meme taille ");
                }
                for (int indice : keys) {
                    Object cellule1 = line1[indice];
                    Object cellule2 = line2[indice];
                    if (!compareTwoFields(cellule1, cellule2)) {
                        continue compare;
                    }
                }
                sameLine = line2;
                egal = true;
                break;
            }
            if (egal == true) {
                //verifier que les autres donnees correspondent;
                boolean exactlyEqual = compareLines(line1, sameLine, indexes);
                
                System.arraycopy(line1, 0, mergedLine, 0, line1.length);
                if( exactlyEqual){
                    mergedLine[line1.length] = 0; // il y a egalite parfaite
                } 
                else{
                    mergedLine[line1.length] = 1; // il n'y a pas d'égalité
                    mergedLine[line1.length + 1 ] = sameLine;
                }
                outputList.add(mergedLine);
                list2.remove(sameLine);
            } else {
                System.arraycopy(line1, 0, mergedLine, 0, line1.length);
                mergedLine[line1.length] = 2; // n existe pas dans la liste deux     
                outputList.add(mergedLine);
            }

        }

        for (Object[] ligne : list2) {
            mergedLine = new Object[ligne.length + 2];
            System.arraycopy(ligne, 0, mergedLine, 0, ligne.length);
            mergedLine[ligne.length] = 3; // n existe pas dans la liste une     
            outputList.add(mergedLine);
        }
        return outputList;
    }

    public static boolean compareLines(Object[] line1, Object[] line2, int[] indexes ){
            for(int index : indexes){
                boolean compResult = compareTwoFields( line1[ index], line2[ index]);
                if( !compResult ) return false;
            }
        return true;
    }
    public static boolean compareTwoFields(Object field1, Object field2) {
        if (field1 == null && field2 == null) {
            return true;
        }
        if ((field1 instanceof Date) && (field2 instanceof Date)) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String date1 = dateFormat.format((Date) field1);
            String date2 = dateFormat.format((Date) field2);
            return date1.equals(date2);
        }

        if (field1 != null) {
            return field1.equals(field2);
        }
        return false;
    }

    public KPaxData(){
        
    }
    public KPaxData(List<Object[]> source1, List<Object[]> source2, String[] titles, int[] keys, int[] indexes) {
        
        this.keys = keys;
        this.titles = titles;
        this.source1 = source1;
        this.source2 = source2;
        this.srcFlag = titles.length;
        this.indexes = indexes;
        this.data = merge(source1, source2, keys, indexes);
    }

    public List<Object[]> getData() {
        return data;
    }

    public int[] getKeys() {
        return keys;
    }


    public String[] getTitles() {
        return titles;
    }

    public List<Object[]> getSource1() {
        return source1;
    }

    public List<Object[]> getSource2() {
        return source2;
    }
    
    
    public List<Object[]> getDatabyStatus(int status) {
        if (status == 0) {
            return data;
        }
        List<Object[]> filtredListe = new ArrayList<>();
        for (Object[] o : data) {
            if (((int) o[this.getSrcFlag()]) == status) {
                filtredListe.add(o);
            }
        }
        return filtredListe;
    }

    public int getSrcFlag() {
        return srcFlag;
    }

    public int[] getIndexes() {
        return indexes;
    }

    public void setIndexes(int[] indexes) {
        this.indexes = indexes;
    }

    
    
}

class IncoherentIndexes extends Exception{
    
}