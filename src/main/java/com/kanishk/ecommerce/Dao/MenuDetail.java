/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanishk.ecommerce.Dao;

import com.kanishk.ecommerce.Beans.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KANISHK
 */
public class MenuDetail {
    public List<Menu> getMenuList(String name,int sno,int limit) throws SQLException {
        List<Menu> list = new ArrayList<Menu>();
        PreparedStatement ps = null;
        Connection con = null;
        try {
            String namequery = name.equalsIgnoreCase("") ? "" : " and menuname='" + name + "'";
            String snoquery = sno == 0 ? "" : " and sno='" + sno + "' ";

            con = Utils.ConnectionUtils.getConnection();
            ps = con.prepareStatement("SELECT sno,menuname FROM `mastermenu` WHERE menuname is not null "+namequery+snoquery+" order by sno limit "+limit+"");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Menu item = new Menu();
                item.setSno(rs.getInt("sno"));
                item.setMenuName(rs.getString("menuname"));
                list.add(item);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }
  
    public List<Menu> getBreakingNewsList(String name,int sno) throws SQLException {
        List<Menu> list = new ArrayList<Menu>();
        PreparedStatement ps = null;
        Connection con = null;
        try {
            String namequery = name.equalsIgnoreCase("") ? "" : " and breaking='" + name + "'";
            String snoquery = sno == 0 ? "" : " and sno='" + sno + "' ";

            con = Utils.ConnectionUtils.getConnection();
            ps = con.prepareStatement("SELECT sno,breaking FROM `master_breaking` WHERE breaking is not null "+namequery+snoquery+" order by sno desc");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Menu item = new Menu();
                item.setSno(rs.getInt("sno"));
                item.setMenuName(rs.getString("breaking"));
                list.add(item);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }
   public List<Menu> getSubMenuList(String name,int sno,int menusno) throws SQLException {
        List<Menu> list = new ArrayList<Menu>();
        PreparedStatement ps = null;
        Connection con = null;
        try {
            String namequery = name.equalsIgnoreCase("") ? "" : " and submenuname='" + name + "'";
            String snoquery = sno == 0 ? "" : " and master_submenu.sno='" + sno + "' ";
            String menusnoquery = menusno == 0 ? "" : " and menusno='" + menusno + "' ";

            con = Utils.ConnectionUtils.getConnection();
            ps = con.prepareStatement("SELECT master_submenu.sno,master_submenu.submenuname,master_submenu.menusno,mastermenu.menuname FROM `master_submenu` INNER JOIN mastermenu on mastermenu.sno=master_submenu.menusno WHERE menuname is not null "+namequery+snoquery+menusnoquery+" order by master_submenu.sno");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Menu item = new Menu();
                item.setSno(rs.getInt("sno"));
                item.setSubMenuName(rs.getString("submenuname"));
                item.setMenuName(rs.getString("menuname"));
                item.setMenuSno(rs.getInt("menusno"));
                
                list.add(item);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }
 
    public static void main(String[] args) throws SQLException {
    //    System.out.println(getMenuList("",0).get(0).getMenuName());
    }
}
