/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanishk.ecommerce.Dao;

import Utils.RandomCaptcha;
import com.kanishk.ecommerce.Beans.User;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author KANISHK
 */
public class UserDao {

    JdbcTemplate template;

      @Autowired
    ServletContext context;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public int saveuser(String name, String mob, String username, String password, String designation, String role) {

        String sql = "insert into employeelogin (username,password,role,employeename,mobile,empdesignation,entereddatetime) values('" + username + "','" + password + "','" + role + "','" + name + "','" + mob + "','" + designation + "',NOW())";
        return template.update(sql);
    }
//      public int updateuser(String username) {
//
//        String sql = "update employeelogin set employeename=?,mobile,empdesignation,entereddatetime) values('" + username + "','" + password + "','" + role + "','" + name + "','" + mob + "','" + designation + "',NOW())";
//        return template.update(sql);
//    }

    public static boolean useralreadyExist(String username) {
        boolean result = false;
        try {

            Connection con = Utils.ConnectionUtils.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT username FROM `employeelogin` WHERE  username=?");
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = true;
            }
            con.close();
            rs.close();
            ps.close();

        } catch (Exception e) {
            System.err.println("Failed at branchCodexist():" + e.toString());
        }
        return result;
    }

    public static ArrayList<User> getUsersList(String username, String branch, String usertype, String limit, String search) {
        ArrayList<User> list = new ArrayList();
        try {
            Connection con = Utils.ConnectionUtils.getConnection();
            String snoquery = username.equalsIgnoreCase("") || username.equalsIgnoreCase("select") ? "" : " and username='" + username + "'";
            String limitquery = limit.equalsIgnoreCase("") || branch.equalsIgnoreCase("0") ? "" : " ASC limit " + limit + " ";
            String searchquery = search.equalsIgnoreCase("") || search.equalsIgnoreCase("select") ? "" : " and employeename like '%" + search + "%'";

            if (usertype.equalsIgnoreCase("A") || usertype.equalsIgnoreCase("S")) {
                snoquery = "";
            }
            PreparedStatement ps = con.prepareStatement("SELECT username,password,date_format(entereddatetime,'%M %d,%Y %p'),employeename,empdesignation,mobile,emailid,block,role,createuser,updateuser,deleteuser,menu_creation,userauthentication,menu_updation,menu_deletion,submenu_creation,submenu_updation,submenu_deletion FROM hotelmanagementsystem_kot.employeelogin where username is not null" + snoquery + snoquery + searchquery + " order by username  " + limitquery + " ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User item = new User();
                item.setName(rs.getString("employeename"));
                item.setUsername(rs.getString("username"));
                item.setPassword(rs.getString("password"));
                item.setMobile(rs.getString("mobile"));
                item.setDesignation(rs.getString("empdesignation"));
                item.setUserType(rs.getString("role"));
                String blockedStatus = rs.getString("block");
                String createUserStatus = rs.getString("createuser");
                String updateUserStatus = rs.getString("updateuser");
                String deleteUserStatus = rs.getString("deleteuser");
                String authenticationUserStatus = rs.getString("userauthentication");
                String createMenuStatus = rs.getString("menu_creation");
                String updateMenuStatus = rs.getString("menu_updation");
                String deleteMenuStatus = rs.getString("menu_deletion");
                String createSubmenuStatus = rs.getString("submenu_creation");
                String updateSubmenuStatus = rs.getString("submenu_updation");
                String deleteSubmenuStatus = rs.getString("submenu_deletion");

                item.setBlockedStatus(blockedStatus);
                item.setCreateUserStatus(createUserStatus);
                item.setUpdateUserStatus(updateUserStatus);
                item.setDeleteUserStatus(deleteUserStatus);
                item.setCreateMenuStatus(createMenuStatus);
                item.setUpdateMenuStatus(updateMenuStatus);
                item.setDeleteMenuStatus(deleteMenuStatus);
                item.setCreateSubMenuStatus(createSubmenuStatus);
                item.setUpdateSubMenuStatus(updateSubmenuStatus);
                item.setDeleteSubMenuStatus(deleteSubmenuStatus);

                item.setAuthenticationUserStatus(authenticationUserStatus);

                item.setBlockedCheckedUncheck(blockedStatus.equalsIgnoreCase("Y") ? "checked" : "");
                item.setCreateUserCheckedUncheck(createUserStatus.equalsIgnoreCase("Y") ? "checked" : "");
                item.setUpdateUserCheckedUncheck(updateUserStatus.equalsIgnoreCase("Y") ? "checked" : "");
                item.setDeleteUserCheckedUncheck(deleteUserStatus.equalsIgnoreCase("Y") ? "checked" : "");
                item.setCreateMenuCheckedUncheck(createMenuStatus.equalsIgnoreCase("Y") ? "checked" : "");
                item.setUpdateMenuCheckedUncheck(updateMenuStatus.equalsIgnoreCase("Y") ? "checked" : "");
                item.setDeleteMenuCheckedUncheck(deleteMenuStatus.equalsIgnoreCase("Y") ? "checked" : "");
                item.setCreateSubMenuCheckedUncheck(createSubmenuStatus.equalsIgnoreCase("Y") ? "checked" : "");
                item.setUpdateSubMenuCheckedUncheck(updateSubmenuStatus.equalsIgnoreCase("Y") ? "checked" : "");
                item.setDeleteSubMenuCheckedUncheck(deleteSubmenuStatus.equalsIgnoreCase("Y") ? "checked" : "");

                list.add(item);
                item = null;

            }
            con.close();
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("POJO.UserDetail.getUsersList()" + e);
        }
        return list;
    }

    public static User getUsersListDetail(String username, String branch, String usertype, String limit, String search) {
        User item = new User();
        try {
            Connection con = Utils.ConnectionUtils.getConnection();
            String snoquery = username.equalsIgnoreCase("") || username.equalsIgnoreCase("select") ? "" : " and username='" + username + "'";
            String searchquery = search.equalsIgnoreCase("") || search.equalsIgnoreCase("select") ? "" : " and employeename like '%" + search + "%'";

            if (usertype.equalsIgnoreCase("A") || usertype.equalsIgnoreCase("S")) {
                snoquery = "";
            }
            PreparedStatement ps = con.prepareStatement("SELECT username,password,date_format(entereddatetime,'%M %d,%Y %p'),employeename,empdesignation,mobile,emailid,block,role,menu_creation,menu_updation,menu_deletion,submenu_creation,submenu_updation,submenu_deletion,emailid,filename,filepath,foldername FROM hotelmanagementsystem_kot.employeelogin where username is not null" + snoquery + snoquery + searchquery + " ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                item.setName(rs.getString("employeename"));
                item.setUserName(rs.getString("username"));
                item.setPassword(rs.getString("password"));
                item.setMobile(rs.getString("mobile"));
                item.setEmailid(rs.getString("emailid"));
                item.setDesignation(rs.getString("empdesignation"));
                item.setRole(rs.getString("role"));
                item.setFilename(rs.getString("filename"));
                item.setFilepath(rs.getString("filepath"));
                item.setFoldername(rs.getString("foldername"));
            }
            con.close();
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("POJO.UserDetail.getUsersListDetail()" + e);
        }
        return item;
    }
 public boolean UpdateEmpDetail(MultipartFile rimage,String rname,String username,String mob,String designation,String roledef,String enteredby) {

        Connection con = null;
        PreparedStatement ps = null;
        int rows = 0;
        boolean result = false;

        String random = RandomCaptcha.fastestRandomStringWithMixedCase(15);
        String currentdate = String.valueOf(java.time.LocalDate.now());
        String rimagefilename = "", rimagefilepath = "";
        long rimagesize = 0;
        String relativeWebPath = "/resources/Dynamic_FileAttachment/EmployeeImages/";
        String absoluteFilePath = context.getRealPath("") + File.separator + relativeWebPath;

        String savePath = absoluteFilePath + File.separator + currentdate;
        System.err.println(savePath);
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        if (!rimage.isEmpty()) {
            try {
                byte[] bytes = rimage.getBytes();
                String basename = FilenameUtils.getBaseName(rimage.getOriginalFilename());
                String extension = FilenameUtils.getExtension(rimage.getOriginalFilename());
                rimagefilename = "Emp" + random + "." + extension;
                rimagesize = rimage.getSize();
                rimagefilepath = savePath + File.separator + rimagefilename;
                File serverFile = new File(savePath + File.separator + rimagefilename);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
            } catch (Exception e) {
                rimagefilename = "";
                rimagefilepath = "";
                rimagesize = 0;
            }
        }
        try {

            con = Utils.ConnectionUtils.getConnection();
            ps = con.prepareStatement("update employeelogin set role=?,employeename=?,mobile=?,empdesignation=?,foldername=?,filename=?,filepath=?,updatedby=?,updatedat=NOW() where username=?");

            ps.setString(1, roledef);
            ps.setString(2, rname);
            ps.setString(3, mob);
            ps.setString(4, designation);
            ps.setString(5, relativeWebPath + File.separator + currentdate);
            ps.setString(6, rimagefilename);
            ps.setString(7, rimagefilepath);
            ps.setString(8, enteredby);

            ps.setString(9, username);

            rows = ps.executeUpdate();

            if (rows > 0) {
                result = true;

            }
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.toString());
        } finally {

            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
            }
        }
        return result;
    }
 
}
