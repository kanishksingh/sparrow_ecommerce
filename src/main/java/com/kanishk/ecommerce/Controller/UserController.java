/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanishk.ecommerce.Controller;

import com.kanishk.ecommerce.Beans.User;
import com.kanishk.ecommerce.Dao.UserDao;
import java.io.IOException;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author KANISHK
 */
@Controller
public class UserController {

    @Autowired
    UserDao udao;

    @RequestMapping("/newuser")
    public String AddUser(Model m, HttpSession session) {
        m.addAttribute("msg", "Create New  User");
        List<User> userslist = udao.getUsersList("", "", session.getAttribute("role").toString(), "", "");
        m.addAttribute("usrslist", userslist);
        return "NewUser";
    }

    @RequestMapping("/authentication")
    public String authentication(Model m, HttpSession session) {
        m.addAttribute("msg", "User Rights");
        List<User> userslist = udao.getUsersList("", "", session.getAttribute("role").toString(), "", "");
        m.addAttribute("usrslist", userslist);

        return "rights";
    }

    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String saveMenu(@RequestParam("name") String name, @RequestParam("mob") String mob, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("designation") String designation, @RequestParam("roledef") String branch, HttpSession session) {
        ModelAndView model = new ModelAndView("NewUser");
        boolean result = udao.useralreadyExist(username);
        List<User> userslist = udao.getUsersList("", "", session.getAttribute("role").toString(), "", "");
        model.addObject("usrslist", userslist);
        if (result == true) {
            model.addObject("msgerr", "User Name Already Exist !!!");
        } else {
            int status = udao.saveuser(name, mob, username, password, designation, branch);
            if (status == 1) {
                String message = "User Saved successfully";
                model.addObject("msgerr", message);
            } else {
                String message = "Sorry Something Is Wrong";
                model.addObject("msgerr", message);
            }
        }
        return "redirect:/newuser";
    }

    @RequestMapping(value = "/updateuser/{username}")
    public ModelAndView updateuser(HttpSession session, @PathVariable("username") String username) {
        ModelAndView model = new ModelAndView("EditUser");
        model.addObject("msg", "Updation User Detail  ");
        List<User> userslist = udao.getUsersList("", "", session.getAttribute("role").toString(), "", "");
        model.addObject("usrslist", userslist);

        User usd = udao.getUsersListDetail(username, "", "", "", "");
        model.addObject("usrsdetail", usd);

        return model;
    }
    
    @RequestMapping(value = "/UpdateUserDetail", method = RequestMethod.POST)
    public @ResponseBody
    String singleFileUpload(@RequestParam("photo") MultipartFile rimage, @RequestParam("name") String rname, @RequestParam("username") String username,
            @RequestParam("mob") String mob, @RequestParam("designation") String designation, @RequestParam("roledef") String roledef,
            HttpSession session)
            throws IOException {
        String value = "";
        boolean status = udao.UpdateEmpDetail(rimage, rname, username, mob, designation, roledef, session.getAttribute("username").toString());
        if (status == true) {
            value = "true";
            System.err.println("value=" + value);
        } else {
            value = "false";
        }
        return value;
    }

}
