/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanishk.ecommerce.Dao;

import com.kanishk.ecommerce.Beans.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author KANISHK
 */
public class LoginDao {
    JdbcTemplate template;  
  
public void setTemplate(JdbcTemplate template) {  
    this.template = template;  
}  
public List<Login> getUserList(Login p){ 
     return template.query("select * from login where usernameis not null",new RowMapper<Login>(){  
        public Login mapRow(ResultSet rs, int row) throws SQLException {  
            Login e=new Login();  
            e.setUsername(rs.getString(1));  
            e.setPassword(rs.getString(2));  
            e.setEmail(rs.getString(3));  
            e.setDesignation(rs.getString(4));  
            e.setUsertype(rs.getString(5));  
            return e;  
        }  
    });  
}

public boolean getCheckUserExist(String username,String password){
            boolean status=false;
 try {
            			Connection con = Utils.ConnectionUtils.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM `login` where username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              status=true;
               

            }
            Utils.ConnectionUtils.closeConn(con);
            ps.close();
            rs.close();;
          
        } catch (Exception e) {
            System.out.println("POJO.UserDetail.getUsersList()" + e);
        }
return status;
}
}
