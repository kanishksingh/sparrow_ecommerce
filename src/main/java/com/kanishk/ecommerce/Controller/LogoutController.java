/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanishk.ecommerce.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author KANISHK
 */
@Controller
public class LogoutController {
    
    
 
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
   public ModelAndView page4(HttpSession httpsession, SessionStatus status) {

/*Mark the current handler's session processing as complete, allowing for cleanup of 
  session attributes.*/
status.setComplete();

/* Invalidates this session then unbinds any objects boundto it. */
httpsession.invalidate();
 String message="Log Out Successfully";   
		ModelAndView model = new ModelAndView("Dashboard/login");
		model.addObject("msg", message);
return model;
}

}
