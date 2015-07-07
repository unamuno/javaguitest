/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitest;

/**
 *
 * @author Merlin
 */
public class GUItest {

    public double MwsRechner(double pNetoBetrag, double pMwSSatz){
        double varBrutoBetrag=0;
        System.out.println(String.valueOf(pNetoBetrag));
        System.out.println(String.valueOf(pMwSSatz));
        varBrutoBetrag=pNetoBetrag*(1+(pMwSSatz/100));
        //varBrutoBetrag=Math.rint(varBrutoBetrag*100)/100;
        System.out.println(String.valueOf(varBrutoBetrag));
        return varBrutoBetrag;
    } 
    
    
    
}
