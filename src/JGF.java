package jgf;

import javax.swing.JOptionPane;
import jgf.util.Version;

/******************************************************************************
*
* COPYRIGHT Vin�cius G. Mendon�a ALL RIGHTS RESERVED.
*
* This software cannot be copied, stored, distributed without  
* Vin�cius G.Mendon�a prior authorization.
*
* This file was made available on http://www.pontov.com.br and it is free 
* to be restributed or used under Creative Commons license 2.5 br: 
* http://creativecommons.org/licenses/by-sa/2.5/br/
*
*******************************************************************************
* Este software nao pode ser copiado, armazenado, distribuido sem autoriza��o 
* a priori de Vin�cius G. Mendon�a
*
* Este arquivo foi disponibilizado no site http://www.pontov.com.br e esta 
* livre para distribui��o seguindo a licen�a Creative Commons 2.5 br:
* http://creativecommons.org/licenses/by-sa/2.5/br/
*
******************************************************************************/


public class JGF {

    public static final Version VERSION = new Version(1,1,0);
    
    public static void main(String[] args) {
        String text = "<html>Java Game Framework " + (VERSION.isBeta() ? "<font color=\"red\">" : "") + "v." + VERSION;
                
        JOptionPane.showMessageDialog(null, text);        
    }

}
