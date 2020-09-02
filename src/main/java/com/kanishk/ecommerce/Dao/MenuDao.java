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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author KANISHK
 */
public class MenuDao {

    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public int saveMenu(Menu p) {
        String sql = "insert into mastermenu (menuname) values('" + p.getMenuName() + "')";
        return template.update(sql);
    }

    public int updateMenu(Menu p) {
        String sql = "update mastermenu set menuname='" + p.getMenuName() + "',hide_show='" + p.getHide_show() + "' where sno=" + p.getSno() + "";
        return template.update(sql);
    }

    public int deleteMenu(int id) {
        String sql = "delete from mastermenu where sno=" + id + "";
        return template.update(sql);
    }

    public Menu getMenuById(int id) {
        String sql = "select * from mastermenu where sno=?";
        return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Menu>(Menu.class));
    }

    public List<Menu> getMenuList() {
        return template.query("select * from mastermenu", new RowMapper<Menu>() {
            public Menu mapRow(ResultSet rs, int row) throws SQLException {
                Menu e = new Menu();
                e.setSno(rs.getInt(1));
                e.setMenuName(rs.getString(2));
                e.setHide_show(rs.getString(3));
                return e;
            }
        });
    }

//Sub Menu
    public static List<Menu> getMenus() {
        List<Menu> list = new ArrayList();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = Utils.ConnectionUtils.getConnection();

            ps = con.prepareStatement("select * from mastermenu where sno is not null and hide_show='Y' order by sno asc limit 10");
            rs = ps.executeQuery();
            while (rs.next()) {
                Menu party1 = new Menu();
                party1.setSno(rs.getInt(1));
                party1.setMenuName(rs.getString(2));

                list.add(party1);

            }

        } catch (Exception e) {
            System.err.println("Failed at getPartyDetails() " + e.toString());
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

        return list;
    }

    public static List<Menu> getMenusPublished() {
        List<Menu> list = new ArrayList();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = Utils.ConnectionUtils.getConnection();

            ps = con.prepareStatement("select mastermenu.sno,mastermenu.menuname,menupublish.menusno,menupublish.sno as menupublishedsno,menupublish.submenus from mastermenu inner join menupublish on menupublish.menusno=mastermenu.sno where hide_show='Y' order by mastermenu.sno asc ");
            rs = ps.executeQuery();
            while (rs.next()) {
                Menu party1 = new Menu();
                party1.setSno(rs.getInt("menupublishedsno"));
                party1.setMenuName(rs.getString("menuname"));
                party1.setMenuSno(rs.getInt("menusno"));
                party1.setSubMenuName(rs.getString("submenus"));

                list.add(party1);

            }

        } catch (Exception e) {
            System.err.println("Failed at getPartyDetails() " + e.toString());
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

        return list;
    }

    public List<Menu> getSubMenuByMenu(int menusno) {
        List<Menu> list = new ArrayList();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = Utils.ConnectionUtils.getConnection();

            ps = con.prepareStatement("select master_submenu.sno,master_submenu.menusno,master_submenu.submenuname,mastermenu.menuname from master_submenu inner join mastermenu on mastermenu.sno=master_submenu.menusno where master_submenu.menusno=? and master_submenu.hide_show='Y'");
            ps.setInt(1, menusno);
            rs = ps.executeQuery();
            while (rs.next()) {
                Menu party1 = new Menu();
                party1.setSno(rs.getInt(1));
                party1.setMenuSno(rs.getInt(2));
                party1.setSubMenuName(rs.getString(3));
                party1.setMenuName(rs.getString(4));

                list.add(party1);

            }

        } catch (Exception e) {
            System.err.println("Failed at getPartyDetails() " + e.toString());
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

        return list;
    }
      public List<Menu> getSubSubMenuList(int submenusno) {
        return template.query("select master_subsubmenu.sno,master_subsubmenu.menusno,master_subsubmenu.submenusno,master_submenu.submenuname,mastermenu.menuname,master_subsubmenu.subsubmenuname,master_subsubmenu.hide_show from master_subsubmenu INNER JOIN master_submenu on master_subsubmenu.submenusno=master_submenu.sno  inner join mastermenu on mastermenu.sno=master_submenu.menusno where master_subsubmenu.sno='"+submenusno+"'", new RowMapper<Menu>() {
            public Menu mapRow(ResultSet rs, int row) throws SQLException {
                Menu e = new Menu();
                e.setSno(rs.getInt(1));
                e.setMenuSno(rs.getInt(2));
                e.setSubMenuSno(rs.getInt(3));
                e.setSubMenuName(rs.getString(4));
                e.setMenuName(rs.getString(5));
                e.setSubSubMenuName(rs.getString(6));

                e.setHide_show(rs.getString(7));

                return e;
            }
        });
    }
 
    public List<Menu> getSubMenuList() {
        return template.query("select master_submenu.sno,master_submenu.menusno,master_submenu.submenuname,mastermenu.menuname,master_submenu.hide_show from master_submenu inner join mastermenu on mastermenu.sno=master_submenu.menusno", new RowMapper<Menu>() {
            public Menu mapRow(ResultSet rs, int row) throws SQLException {
                Menu e = new Menu();
                e.setSno(rs.getInt(1));
                e.setMenuSno(rs.getInt(2));
                e.setSubMenuName(rs.getString(3));
                e.setMenuName(rs.getString(4));
                e.setHide_show(rs.getString(5));

                return e;
            }
        });
    }

    public List<Menu> getSubSubMenuList() {
        return template.query("select master_subsubmenu.sno,master_subsubmenu.menusno,master_subsubmenu.submenusno,master_submenu.submenuname,mastermenu.menuname,master_subsubmenu.subsubmenuname,master_subsubmenu.hide_show from master_subsubmenu INNER JOIN master_submenu on master_subsubmenu.submenusno=master_submenu.sno  inner join mastermenu on mastermenu.sno=master_submenu.menusno", new RowMapper<Menu>() {
            public Menu mapRow(ResultSet rs, int row) throws SQLException {
                Menu e = new Menu();
                e.setSno(rs.getInt(1));
                e.setMenuSno(rs.getInt(2));
                e.setSubMenuSno(rs.getInt(3));
                e.setSubMenuName(rs.getString(4));
                e.setMenuName(rs.getString(5));
                e.setSubSubMenuName(rs.getString(6));

                e.setHide_show(rs.getString(7));

                return e;
            }
        });
    }
    public List<Menu> getSubSubMenuList1() {
        return template.query("select master_subsubmenu.sno,master_subsubmenu.subsubmenuname from master_subsubmenu group by subsubmenuname", new RowMapper<Menu>() {
            public Menu mapRow(ResultSet rs, int row) throws SQLException {
                Menu e = new Menu();
                e.setSno(rs.getInt(1));
               
                e.setSubSubMenuName(rs.getString(2));
                return e;
            }
        });
    }

    public int saveSubMenu(String menu, String sub) {

        String sql = "insert into master_submenu (menusno,submenuname) values('" + menu + "','" + sub + "')";
        return template.update(sql);
    }

    public int saveSubSubMenu(String menu, String sub, String subsub) {

        String sql = "insert into master_subsubmenu (menusno,submenusno,subsubmenuname) values('" + menu + "','" + sub + "','" + subsub + "')";
        return template.update(sql);
    }

    public int updateSubMenu(String menu, String sub, int sno, String hideshow) {
        String sql = "update master_submenu set menusno='" + menu + "',submenuname='" + sub + "',hide_show='" + hideshow + "' where sno=" + sno + "";
        return template.update(sql);
    }

    public int updateSubSubMenu(String menu, String sub, String subsub, int sno, String hideshow) {
        String sql = "update master_subsubmenu set menusno='" + menu + "',submenusno='" + sub + "',hide_show='" + hideshow + "',subsubmenuname='" + subsub + "' where sno=" + sno + "";
        return template.update(sql);
    }

    public int deleteSubMenu(int id) {
        String sql = "delete from master_submenu where sno=" + id + "";
        return template.update(sql);
    }

    public int deleteSubSubMenu(int id) {
        String sql = "delete from master_subsubmenu where sno=" + id + "";
        return template.update(sql);
    }

    public Menu getSubMenuById(int id) {
        String sql = "select * from master_submenu where sno=?";
        return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Menu>(Menu.class));
    }

    public Menu getSubSubMenuById(int id) {
        String sql = "select * from master_subsubmenu where sno=?";
        return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Menu>(Menu.class));
    }

    public Menu getSubMenuName1(int sno) {
        Menu party1 = new Menu();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = Utils.ConnectionUtils.getConnection();

            ps = con.prepareStatement("select * from master_submenu where sno=?");
            ps.setInt(1, sno);
            rs = ps.executeQuery();
            while (rs.next()) {
                party1.setSno(rs.getInt(1));
                party1.setMenuSno(rs.getInt(2));
                party1.setSubMenuName(rs.getString(3));
            }

        } catch (Exception e) {
            System.err.println("Failed at getPartyDetails() " + e.toString());
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

        return party1;
    }
 public static Menu getMenus(String mname) {
                Menu party1 = new Menu();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = Utils.ConnectionUtils.getConnection();
            ps = con.prepareStatement("select * from mastermenu where sno is not null and menuname=?");
            ps.setString(1, mname);
            rs = ps.executeQuery();
            while (rs.next()) {
                party1.setSno(rs.getInt(1));
                party1.setMenuName(rs.getString(2));
            }

        } catch (Exception e) {
            System.err.println("Failed at getPartyDetails() " + e.toString());
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
        return party1;
    }
 public static Menu getSubMenus(int mname,String submenu) {
                Menu party1 = new Menu();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = Utils.ConnectionUtils.getConnection();
            ps = con.prepareStatement("select * from master_submenu where sno is not null and menusno=? and submenuname=?");
            ps.setInt(1, mname);
            ps.setString(2, submenu);
            rs = ps.executeQuery();
            while (rs.next()) {
                party1.setSno(rs.getInt(1));
//                party1.setMenuName(rs.getString(2));
            }

        } catch (Exception e) {
            System.err.println("Failed at getPartyDetails() " + e.toString());
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
        return party1;
    }
public static Menu getSubSubMenus(int mname,int submenu,String submenuname) {
                Menu party1 = new Menu();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = Utils.ConnectionUtils.getConnection();
            ps = con.prepareStatement("select * from master_subsubmenu where sno is not null and menusno=? and submenusno=? and subsubmenuname=?");
            ps.setInt(1, mname);
            ps.setInt(2, submenu);
            ps.setString(3, submenuname);
            rs = ps.executeQuery();
            while (rs.next()) {
                party1.setSno(rs.getInt(1));
//                party1.setMenuName(rs.getString(2));
            }

        } catch (Exception e) {
            System.err.println("Failed at getPartyDetails() " + e.toString());
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
        return party1;
    }
}
