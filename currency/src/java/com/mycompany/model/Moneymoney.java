/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author user
 */
@Entity
@Table(name="MONEYMONEY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Moneymoney.findAll", query = "SELECT c FROM Moneymoney c"), 
    @NamedQuery(name = "Moneymoney.findBycurrencyname", query = "SELECT c FROM Moneymoney c WHERE c.currencyname = :currencyname"),
    @NamedQuery(name = "Moneymoney.findBymoneyrate", query = "SELECT c FROM Moneymoney c WHERE c.moneyrate = :moneyrate")})
public class Moneymoney implements Serializable {
    private static final long serialVersionUID =1L;
    @Id
    @Basic(optional=false)
    @NotNull
    @Size(min=1,max=64)
    @Column(name="currencyname")
    private String currencyname;   
    @Basic(optional=false)
    @NotNull
    @Size(min=1,max=64)
    @Column(name="moneyrate")
    private double moneyrate;   
    
    public Moneymoney(){
    }
    
public Moneymoney(String currencyname){
    this.currencyname=currencyname;
}
public Moneymoney(String currencyname, double moneyrate){
    this.currencyname=currencyname;
    this.moneyrate=moneyrate;
}   
public double getmoney(){ 
    double currencyrate=moneyrate;
    return currencyrate;
          
}
public String getSrc() {
        return currencyname;
    }

    public void setSrc(String currencyname) {
        this.currencyname = currencyname;
    }

    public double getrate() {
        return moneyrate;
    }

    public void setrate(double moneyrate) {
        this.moneyrate = moneyrate;
    }


    
}
