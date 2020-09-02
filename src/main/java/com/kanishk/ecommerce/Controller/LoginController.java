package com.kanishk.ecommerce.Controller;
import com.kanishk.ecommerce.Beans.Login;
import com.kanishk.ecommerce.Beans.User;
import com.kanishk.ecommerce.Dao.UserDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
 

@Controller
@SessionAttributes({"username","role"})
public class LoginController {
 private final String SQL_FIND_NAME = "SELECT * FROM employeelogin WHERE username = ? AND password = ? and block='N'";
 @Autowired
 JdbcTemplate jdbcTemplate;
 
 
@RequestMapping("/")
 public String viewLoginPage() {
 return "login";
 }
@RequestMapping("/login")
 public String viewLoginPage1() {
 return "login";
 }
 @RequestMapping("/home")
 public String Dashboard() {
 return "loginsuccess";
 }
 
 
 @RequestMapping("/loginvalidation")
 public ModelAndView validate(@RequestParam("username") String uname, @RequestParam("password") String pass) {
 ModelAndView modelAndView = null;
 try {
 Login loginBean = jdbcTemplate.queryForObject(SQL_FIND_NAME, new LoginRowMapper(),
 new Object[] { uname, pass });
 modelAndView = new ModelAndView();
 modelAndView.addObject("username", uname);
 if (loginBean != null) {
 User usdetail=UserDao.getUsersListDetail(uname, "", "", "", "");
 String role=usdetail.getRole();
 modelAndView.setViewName("loginsuccess");
  modelAndView.addObject("role", role);

 } else {
         String message="Sorry UserName Or Password Is Wrong";   
		ModelAndView model = new ModelAndView("login");
		model.addObject("msg", message);
            modelAndView=model;
 //modelAndView.setViewName("Dashboard/login");
 }
 } catch (EmptyResultDataAccessException e) {
 String message="Sorry UserName Or Password Is Wrong";   
		ModelAndView model = new ModelAndView("login");
		model.addObject("msg", message);
            modelAndView=model;
 }

 return modelAndView;
 }

 private final class LoginRowMapper implements RowMapper<Login> {

 @Override
 public Login mapRow(ResultSet rs, int arg) throws SQLException {
 return new Login(rs.getString("username"), rs.getString("password"));
 }

 }
 
}