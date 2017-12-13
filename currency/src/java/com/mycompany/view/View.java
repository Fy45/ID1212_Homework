package com.mycompany.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.controller.Controller;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
/**
 *
 * @author user
 */
@Named(value = "View")
@ConversationScoped
public class View implements Serializable{
     private static final long serialVersionUID = 16247164405L;
    @EJB
    private Controller CurrencyCon;
    private String srcCurrency;
    private String toCurrency;
    private double Amount;
    private String conversionResult = null;
    private String totalConversionResult = null;
    @Inject
    private Conversation conversation;
    
    
    
     private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    
     public String getconversionResult(){
         return conversionResult;
     }
     
     public String gettotalConversionResult(){
         return totalConversionResult;
     }
    
    public void convertCurr(){
      
        try {
            startConversation();
            conversionResult = CurrencyCon.moneyconvert(srcCurrency,toCurrency,Amount);
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
    
    
    
     public void setAmount(String input){
        this.Amount = Double.parseDouble(input);
    }
    public String getAmount(){
        return String.valueOf(Amount);
    }
    
    
    public void settoCurrency(String input){
        this.toCurrency = input;
    }
    public String gettoCurrency(){
        return toCurrency;
    }
    
    public void setsrcCurrency(String input){
        this.srcCurrency = input;
    }
    public String getsrcCurrency(){
        return srcCurrency;
    }
    
    
}
    

