/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanishk.ecommerce.Controller;

/**
 *
 * @author KANISHK
 */
import com.kanishk.ecommerce.Beans.Menu;
import com.kanishk.ecommerce.Dao.Authentication;
import com.kanishk.ecommerce.Dao.MenuDao;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author KANISHK
 */
@Controller
public class menuController {

    public static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";

    @Autowired
    MenuDao dao;//will inject dao from xml file  

    @RequestMapping("/newmenu")
    public String gotoMenu(Model m) {
        m.addAttribute("msg", "Create Master Category");
        List<Menu> list = dao.getMenuList();
        m.addAttribute("menulist", list);
        return "NewMenu";
    }

    @RequestMapping(value = "/savemenu", method = RequestMethod.POST)
    public ModelAndView saveMenu(@ModelAttribute("menuform") Menu emp) {
        ModelAndView model = new ModelAndView("NewMenu");
        if (emp.getMenuName().equalsIgnoreCase("")) {
            String message = "Please Enter Category Name";
            model.addObject("msgerror", message);

        } else {
            int status = dao.saveMenu(emp);
            if (status == 1) {
                String message = "Saved successfully";
                List<Menu> list = dao.getMenuList();
                model.addObject("menulist", list);
                model.addObject("msgerror", message);
            } else {
                String message = "Sorry Something Is Wrong";
                model.addObject("msgerror", message);
            }
        }
        return model;//will redirect to viewemp request mapping  
    }

    @RequestMapping(value = "/updatemenu", method = RequestMethod.POST)
    public String updateMenu(@ModelAttribute("updatemenuform") Menu emp) {
        ModelAndView model = new ModelAndView();
        model.addObject("msg", "Update Menu Master");

        if (emp.getMenuName().equalsIgnoreCase("")) {
            String message = "Please Enter Category Name";
            model.addObject("succmsg", message);

        } else {
            int status = dao.updateMenu(emp);
            if (status == 1) {
                String message = "Updated successfully";
                model.addObject("succmsg", message);
                Menu emp2 = dao.getMenuById(emp.getSno());
                model.addObject("command", emp2);
            } else {
                String message = "Sorry Something Is Wrong";
                model.addObject("succmsg", message);
                Menu emp2 = dao.getMenuById(emp.getSno());
                model.addObject("command", emp2);
            }
        }
        return "redirect:/newmenu";//will redirect to viewemp request mapping  
    }

    /* The @PathVariable puts URL data into variable.*/
    @RequestMapping(value = "/editmenu/{id}")
    public String editmenu(@PathVariable int id, Model m) {
        Menu emp = dao.getMenuById(id);
        m.addAttribute("command", emp);
        return "EditMenu";
    }

    /* The @PathVariable puts URL data into variable.*/
    @RequestMapping(value = "/deletemenu/{id}", method = RequestMethod.GET)
    public ModelAndView deleteMenu(@PathVariable int id, HttpSession session) {
        ModelAndView m = new ModelAndView("NewMenu");

        boolean usercreation = Authentication.checkPermission(session.getAttribute("username").toString(), "menu_deletion");
        if (usercreation == true) {
            int status = dao.deleteMenu(id);
            if (status == 1) {
                m.addObject("msg", "Category Deleted Successfully");

            } else {
                m.addObject("msg", "Category went Wrong");

            }
        } else {
            m.addObject("msg", "You Don't have rights to delete Category");

        }
        return m;
    }

//submenu    Controller
    @RequestMapping("/newsubmenu")
    public String gotoSubMenu(Model m) {
        List<Menu> list1 = dao.getMenuList();
        m.addAttribute("menulists", list1);
        List<Menu> list = dao.getSubMenuList();
        m.addAttribute("submenulist", list);
        m.addAttribute("msg", "Create Master Group");
        return "NewSubMenu";
    }

    @RequestMapping(value = "/saveSubmenu", method = RequestMethod.POST)
    public ModelAndView saveSubMenu(@RequestParam("menulists") String menu, @RequestParam("submenuName") String sub) {
        ModelAndView model = new ModelAndView("NewSubMenu");
        if (menu.equalsIgnoreCase("") || menu.equalsIgnoreCase("0")) {
            String message = "Please Enter Category Name";
            model.addObject("msgerror", message);

        } else if (sub.equalsIgnoreCase("")) {
            String message = "Please Enter Group Name";

            model.addObject("msgerror", message);

        } else {
            int status = dao.saveSubMenu(menu, sub);
            if (status == 1) {
                String message = "Saved successfully";
                List<Menu> list1 = dao.getMenuList();
                model.addObject("menulists", list1);
                List<Menu> list = dao.getSubMenuList();
                model.addObject("submenulist", list);
                model.addObject("msgerror", message);
            } else {
                String message = "Sorry Something Is Wrong";
                model.addObject("msgerror", message);
            }
        }
        return model;//will redirect to viewemp request mapping  
    }

    @RequestMapping(value = "/updatesubmenu", method = RequestMethod.POST)
    public String updateSubMenu(@RequestParam("menulists") String menu, @RequestParam("menuName") String sub, @RequestParam("sno") int sno, @RequestParam("hide_show") String hideshow) {
        ModelAndView model = new ModelAndView();
        if (menu.equalsIgnoreCase("") || menu.equalsIgnoreCase("0")) {
            String message = "Please Select Category Name";
            model.addObject("succmsg", message);

        } else {
            int status = dao.updateSubMenu(menu, sub, sno, hideshow);
            if (status == 1) {
                String message = "Updated successfully";
                model.addObject("succmsg", message);
                Menu emp2 = dao.getSubMenuById(sno);
                model.addObject("command", emp2);
                List<Menu> list1 = dao.getMenuList();
                model.addObject("menulists", list1);
            } else {
                String message = "Sorry Something Is Wrong";
                model.addObject("succmsg", message);
                List<Menu> list1 = dao.getMenuList();
                model.addObject("menulists", list1);
                Menu emp2 = dao.getSubMenuById(sno);
                model.addObject("command", emp2);
            }
        }
        return "redirect:/newsubmenu";//will redirect to viewemp request mapping  
    }

    @RequestMapping(value = "/editsubmenu/{id}")
    public String editSubmenu(@PathVariable int id, Model m) {
        Menu emp = dao.getSubMenuById(id);
        m.addAttribute("command", emp);
        List<Menu> list1 = dao.getSubMenuList();
        m.addAttribute("menulists", list1);
        m.addAttribute("msg", "Edit Master Group");

        return "EditSubMenu";
    }
//    
//      /* The @PathVariable puts URL data into variable.*/  

    @RequestMapping(value = "/deletesubmenu/{id}", method = RequestMethod.GET)
    public ModelAndView deleteSubMenu(@PathVariable int id, HttpSession session) {
        ModelAndView m = new ModelAndView("NewSubMenu");
        List<Menu> list1 = dao.getMenuList();
        m.addObject("menulists", list1);
        List<Menu> list = dao.getSubMenuList();
        m.addObject("submenulist", list);

        boolean usercreation = Authentication.checkPermission(session.getAttribute("username").toString(), "submenu_deletion");
        if (usercreation == true) {

            int status = dao.deleteSubMenu(id);
            if (status == 1) {
                m.addObject("msg", "Group Deleted Successfully");

            } else {
                m.addObject("msg", "Something went wrong");

            }
        } else {
            m.addObject("msg", "You Don't have rights to delete Group");

        }
        return m;
    }

    @RequestMapping(value = "/Menubysubmenu/{menu}", method = RequestMethod.POST, produces = APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    List<Menu> categorykhabarrepo(@PathVariable int menu) throws JSONException {
        List<Menu> list = dao.getSubMenuByMenu(menu);
        return list;
    }
     @RequestMapping(value = "/SubMenubysubsubmenu/{smenu}", method = RequestMethod.POST, produces = APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    List<Menu> SubMenubysubsubmenu(@PathVariable int smenu) throws JSONException {
        List<Menu> list = dao.getSubSubMenuList(smenu);
        return list;
    }

    @RequestMapping("/newsubsubmenu")
    public String gotoSubSubMenu(Model m) {
        List<Menu> list1 = dao.getMenuList();
        m.addAttribute("menulists", list1);
        List<Menu> list = dao.getSubSubMenuList();
        //     List<Menu> list = dao.getSubSubMenuList();
        m.addAttribute("subsubmenulist", list);
        m.addAttribute("msg", "Create Master Sub-Group");

        return "NewSubSubMenu";
    }

    @RequestMapping(value = "/editsubsubmenu/{id}")
    public String editSubSubmenu(@PathVariable int id, Model m) {
        Menu emp = dao.getSubSubMenuById(id);
        m.addAttribute("command", emp);
        List<Menu> list1 = dao.getSubSubMenuList();
        m.addAttribute("subsubmenulist", list1);
        List<Menu> list = dao.getMenuList();
        m.addAttribute("menulists", list);
        m.addAttribute("msg", "Edit Master Sub-Group");

        return "EditSubSubMenu";
    }

    @RequestMapping(value = "/saveSubSubmenu", method = RequestMethod.POST)
    public ModelAndView saveSubSubMenu(@RequestParam("menulists") String menu, @RequestParam("submenu") String sub, @RequestParam("subsubmenuName") String submenu) {
        ModelAndView model = new ModelAndView("NewSubSubMenu");
        model.addObject("msg", "Create Master Sub-Group");
        List<Menu> list1 = dao.getMenuList();
        model.addObject("menulists", list1);
        List<Menu> list = dao.getSubSubMenuList();
        model.addObject("subsubmenulist", list);
        if (menu.equalsIgnoreCase("") || menu.equalsIgnoreCase("0")) {
            String message = "Please Enter Category Name";
            model.addObject("msgerror", message);

        } else if (sub.equalsIgnoreCase("") || sub.equalsIgnoreCase("0")) {
            String message = "Please Enter Group Name";

            model.addObject("msgerror", message);

        } else if (submenu.equalsIgnoreCase("")) {
            String message = "Please Enter Sub-Group Name";

            model.addObject("msgerror", message);

        } else {
            int status = dao.saveSubSubMenu(menu, sub, submenu);
            if (status == 1) {
                String message = "Saved successfully";

                model.addObject("msgerror", message);
            } else {
                String message = "Sorry Something Is Wrong";
                model.addObject("msgerror", message);
            }
        }
        return model;//will redirect to viewemp request mapping  
    }

    @RequestMapping(value = "/updatesubsubmenu", method = RequestMethod.POST)
    public String updateSubSubMenu(@RequestParam("menulists") String menu, @RequestParam("submenu") String sub, @RequestParam("subsubmenuName") String subsubmenu, @RequestParam("sno") int sno, @RequestParam("hide_show") String hideshow) {
        ModelAndView model = new ModelAndView();
        Menu emp2 = dao.getSubSubMenuById(sno);
        model.addObject("command", emp2);
        List<Menu> list1 = dao.getMenuList();
        model.addObject("menulists", list1);

        if (menu.equalsIgnoreCase("") || menu.equalsIgnoreCase("0")) {
            String message = "Please Select Category Name";
            model.addObject("succmsg", message);

        } else if (sub.equalsIgnoreCase("") || sub.equalsIgnoreCase("0")) {
            String message = "Please Select Group Name";
            model.addObject("succmsg", message);

        } else if (subsubmenu.equalsIgnoreCase("") || subsubmenu.equalsIgnoreCase("0")) {
            String message = "Please Select Sub-Group Name";
            model.addObject("succmsg", message);

        } else {
            int status = dao.updateSubSubMenu(menu, sub, subsubmenu, sno, hideshow);
            if (status == 1) {
                String message = "Updated successfully";
                model.addObject("succmsg", message);

            } else {
                String message = "Sorry Something Is Wrong";
                model.addObject("succmsg", message);

            }
        }
        return "redirect:/newsubsubmenu";//will redirect to viewemp request mapping  
    }

    @RequestMapping(value = "/deletesubsubmenu/{id}", method = RequestMethod.GET)
    public ModelAndView deleteSubSubMenu(@PathVariable int id, HttpSession session) {
        ModelAndView m = new ModelAndView("NewSubSubMenu");
        List<Menu> list1 = dao.getMenuList();
        m.addObject("menulists", list1);
        List<Menu> list = dao.getSubSubMenuList();
        m.addObject("subsubmenulist", list);

        boolean usercreation = Authentication.checkPermission(session.getAttribute("username").toString(), "subsubmenu_deletion");
        if (usercreation == true) {

            int status = dao.deleteSubSubMenu(id);
            if (status == 1) {
                m.addObject("msg", "Sub-Group Deleted Successfully");

            } else {
                m.addObject("msg", "Something went wrong");

            }
        } else {
            m.addObject("msg", "You Don't have rights to delete Sub-Group");

        }
        return m;
    }
}
