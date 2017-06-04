/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Matth
 */
import java.security.*;
import java.math.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class MD5 {
    private static MD5 esto;
    
    private MD5(){
    
    }
    
    public static MD5 getMD5(){
        if(esto == null){
            esto = new MD5();
        }
        return esto;
    }
    
    public String md5(String s){
       MessageDigest m;
       String hashtext;
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(),0,s.length());
            hashtext = new BigInteger(1,m.digest()).toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            //System.out.println(hashtext);
            return hashtext;
            
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error al convertir MD5 - "+ ex);
            Logger.getLogger(MD5.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
    }
 }
