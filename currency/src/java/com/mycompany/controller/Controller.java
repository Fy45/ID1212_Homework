/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Moneymoney;
import java.text.DecimalFormat;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author user
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class Controller {
    public static double Finaloutputmoney=0;
     @PersistenceContext(unitName = "currency")
     private EntityManager em;
     
     public String moneyconvert(String inputcurrency, String outputcurrency, double money){
         String convertresult;
         double calculateresult;
         DecimalFormat dFormat = new DecimalFormat("#0.00");
         System.out.println("Source currency: "+inputcurrency);
        System.out.println("Destiny Currency: "+outputcurrency);
        System.out.println("Currency Amount: "+money);
        Moneymoney newmodelin=em.find(Moneymoney.class, inputcurrency);
        Moneymoney newmodelout=em.find(Moneymoney.class, outputcurrency);       
        System.out.println("Start conversion!");
        double Inputrate =  newmodelin.getmoney();//在Model里定义一下这个函数，保证在知道货币名称情况下找到相对于人民币的汇率
        double outputrate =  newmodelout.getmoney();//这里的rate返回值应该是double 类型的
        calculateresult=(outputrate/Inputrate)*money;//再转换成为String类型//已经解决了       
        convertresult=dFormat.format(calculateresult);                                    
         return convertresult;
         
     } 
    
}
