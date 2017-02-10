/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.finances.cfisc.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



/**
 *
 * @author SANNI Emmanuel
 */
@SessionScoped
@Named(value = "bean")
public class Bean implements Serializable{

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;        
    }
    
    
    /**
     * Creates a new instance of Bean
     */
    public Bean() {
    }
    
}
