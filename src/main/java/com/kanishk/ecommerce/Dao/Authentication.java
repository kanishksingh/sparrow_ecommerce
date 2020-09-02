/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanishk.ecommerce.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author KANISHK
 */
public class Authentication {
    
    
    public static boolean checkPermission(String username,String table)
    {
    boolean status=false;
     try {
            Connection con = Utils.ConnectionUtils.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT "+table+"  FROM employeelogin where username=? and "+table+"='Y' ");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
             status=true;
            }else{status=false;}
            con.close();
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("Authentication.checkPermission()" + e);
        }
    
    return status ;
    }
}
