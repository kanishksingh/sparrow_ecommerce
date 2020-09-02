/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanishk.ecommerce.Beans;

/**
 *
 * @author KANISHK
 */
public class Menu {
    private int sno;
    private String menuName;
    private String subMenuName,subSubMenuName,hide_show;
    private int menuSno,subMenuSno;

    public int getSubMenuSno() {
        return subMenuSno;
    }

    public void setSubMenuSno(int subMenuSno) {
        this.subMenuSno = subMenuSno;
    }

    public String getSubSubMenuName() {
        return subSubMenuName;
    }

    public void setSubSubMenuName(String subSubMenuName) {
        this.subSubMenuName = subSubMenuName;
    }

    public String getHide_show() {
        return hide_show;
    }

    public void setHide_show(String hide_show) {
        this.hide_show = hide_show;
    }

    public int getMenuSno() {
        return menuSno;
    }

    public void setMenuSno(int menuSno) {
        this.menuSno = menuSno;
    }
    
    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getSubMenuName() {
        return subMenuName;
    }

    public void setSubMenuName(String subMenuName) {
        this.subMenuName = subMenuName;
    }
    
}
