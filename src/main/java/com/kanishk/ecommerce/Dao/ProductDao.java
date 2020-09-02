/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanishk.ecommerce.Dao;

import Utils.RandomCaptcha;
import com.kanishk.ecommerce.Beans.Product;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.swing.JOptionPane;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author KANISHK
 */
public class ProductDao {

    JdbcTemplate template;

    @Autowired
    ServletContext context;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public List<Product> getDistinctProduct(String column) {
        return template.query("select sno," + column + " from product_master where  " + column + "!='' group by " + column + "", new RowMapper<Product>() {
            public Product mapRow(ResultSet rs, int row) throws SQLException {
                Product e = new Product();
                e.setSno(rs.getInt(1));

                e.setItemCode(rs.getString(2));
                return e;
            }
        });
    }

    public List<Product> getProductsList(int menusno, int submenu, int subsubmenu, String product, String style, String brand, String colour, int size, String modelno, String hsn, String itemcode, String barcode, String filterimage) {
        List<Product> list = new ArrayList();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            String menusnoquery = menusno > 0 ? " and product_master.category='" + menusno + "'" : "";
            String submenuquery = submenu > 0 ? " and product_master.group='" + submenu + "'" : "";
            String subsubmenuquery = subsubmenu > 0 ? " and product_master.subgroup='" + subsubmenu + "'" : "";
            String productquery = product.equalsIgnoreCase("") ? "" : " and product_master.product='" + product + "'";
            String stylequery = style.equalsIgnoreCase("") ? "" : " and product_master.style='" + style + "'";
            String brandquery = brand.equalsIgnoreCase("") ? "" : " and product_master.brand='" + brand + "'";
            String colourquery = colour.equalsIgnoreCase("") ? "" : " and product_master.colour='" + colour + "'";
            String modelnoquery = modelno.equalsIgnoreCase("") ? "" : " and product_master.modelno='" + modelno + "'";
            String hsnquery = hsn.equalsIgnoreCase("") ? "" : " and product_master.hsnno='" + hsn + "'";
            String itemcodequery = itemcode.equalsIgnoreCase("") || itemcode.equalsIgnoreCase("Select") ? "" : " and product_master.itemcode='" + itemcode + "'";
            String barcodequery = barcode.equalsIgnoreCase("") || barcode.equalsIgnoreCase("Select") ? "" : " and product_master.barcode='" + barcode + "'";
            String quef = "";
            if (filterimage.equalsIgnoreCase("Y")) {
                quef = " and product_master.foldername!=''";
            } else if (filterimage.equalsIgnoreCase("N")) {
                quef = " and product_master.foldername=''";
            }
            String filterimagequery = filterimage.equalsIgnoreCase("") || filterimage.equalsIgnoreCase("Select") ? "" : quef;

            con = Utils.ConnectionUtils.getConnection();

            ps = con.prepareStatement("SELECT product_master.sno,product_master.category,product_master.`group`,product_master.subgroup,product_master.product,product_master.style,product_master.brand,product_master.colour,product_master.applicationmodel,product_master.modelno,product_master.applicablemfd,product_master.mfgdate,product_master.expdateapplicable,product_master.expdate,product_master.placeoforigin,product_master.hsnno,product_master.itemcode,product_master.barcode,product_master.miniqtyalert,product_master.size,product_master.rate,product_master.defdate,product_master.enteredby,DATE_FORMAT(product_master.enteredon,'%M %d,%Y %p'),mastermenu.menuname,master_submenu.submenuname,master_subsubmenu.subsubmenuname,product_master.foldername,product_master.image1_filename,product_master.image1_filepath,product_master.image2_filename,product_master.image2_filepath,product_master.image3_filename,product_master.image3_filepath,product_master.image4_filename,product_master.image4_filepath,product_master.video_filename,product_master.video_filepath from product_master INNER JOIN mastermenu on mastermenu.sno=product_master.category INNER JOIN  master_submenu on master_submenu.sno=product_master.`group` INNER JOIN master_subsubmenu on master_subsubmenu.sno=product_master.subgroup where product_master.sno is not null " + menusnoquery + submenuquery + subsubmenuquery + productquery + stylequery + brandquery + colourquery + modelnoquery + hsnquery + itemcodequery + barcodequery + filterimagequery + "");
            rs = ps.executeQuery();
            while (rs.next()) {
                Product party1 = new Product();
                party1.setSno(rs.getInt(1));
                party1.setCategory(rs.getString(2));
                party1.setGroup(rs.getString(3));
                party1.setSubGroup(rs.getString(4));
                party1.setProductName(rs.getString(5));
                party1.setStyle(rs.getString(6));
                party1.setBrand(rs.getString(7));
                party1.setColour(rs.getString(8));
                party1.setApplicableModelNo(rs.getString(9));
                party1.setModelNo(rs.getString(10));
                party1.setApplicableMFD(rs.getString(11));
                party1.setManufd(rs.getString(12));
                party1.setExpdateApplicable(rs.getString(13));
                party1.setExpDate(rs.getString(14));
                party1.setPlaceofOrigin(rs.getString(15));
                party1.setHsnNo(rs.getString(16));
                party1.setItemCode(rs.getString(17));
                party1.setBarCode(rs.getString(18));
                party1.setMinqtyalert(rs.getInt(19));
                party1.setSize(rs.getInt(20));
                party1.setRate(rs.getDouble(21));
                party1.setDefDate(rs.getString(22));
                party1.setUserName(rs.getString(23));
                party1.setEnteredDateTime(rs.getString(24));
                party1.setMenuName(rs.getString(25));
                party1.setSubMenuName(rs.getString(26));
                party1.setSubSubMenuName(rs.getString(27));
                party1.setFolderName(rs.getString(28));
                party1.setImage1_Filename(rs.getString(29));
                party1.setImage1_FilePath(rs.getString(30));
                party1.setImage2_Filename(rs.getString(31));
                party1.setImage2_FilePath(rs.getString(32));
                party1.setImage3_Filename(rs.getString(33));
                party1.setImage3_FilePath(rs.getString(34));
                party1.setImage4_Filename(rs.getString(35));
                party1.setImage4_FilePath(rs.getString(36));
                party1.setImage5_Filename(rs.getString(37));
                party1.setImage5_FilePath(rs.getString(38));

                list.add(party1);
            }

        } catch (Exception e) {
            System.err.println("Failed at getProductsList() " + e.toString());
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

    public Product getProductsDetail(int sno) {
        Product party1 = new Product();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = Utils.ConnectionUtils.getConnection();

            ps = con.prepareStatement("SELECT product_master.sno,product_master.category,product_master.`group`,product_master.subgroup,product_master.product,product_master.style,product_master.brand,product_master.colour,product_master.applicationmodel,product_master.modelno,product_master.applicablemfd,product_master.mfgdate,product_master.expdateapplicable,product_master.expdate,product_master.placeoforigin,product_master.hsnno,product_master.itemcode,product_master.barcode,product_master.miniqtyalert,product_master.size,product_master.rate,product_master.defdate,product_master.enteredby,DATE_FORMAT(product_master.enteredon,'%M %d,%Y %p'),mastermenu.menuname,master_submenu.submenuname,master_subsubmenu.subsubmenuname,product_master.foldername,product_master.image1_filename,product_master.image1_filepath,product_master.image2_filename,product_master.image2_filepath,product_master.image3_filename,product_master.image3_filepath,product_master.image4_filename,product_master.image4_filepath,product_master.video_filename,product_master.video_filepath,product_master.product_description from product_master INNER JOIN mastermenu on mastermenu.sno=product_master.category INNER JOIN  master_submenu on master_submenu.sno=product_master.`group` INNER JOIN master_subsubmenu on master_subsubmenu.sno=product_master.subgroup where product_master.sno=? ");
            ps.setInt(1, sno);
            rs = ps.executeQuery();
            while (rs.next()) {
                party1.setSno(rs.getInt(1));
                party1.setCategory(rs.getString(2));
                party1.setGroup(rs.getString(3));
                party1.setSubGroup(rs.getString(4));
                party1.setProductName(rs.getString(5));
                party1.setStyle(rs.getString(6));
                party1.setBrand(rs.getString(7));
                party1.setColour(rs.getString(8));
                party1.setApplicableModelNo(rs.getString(9));
                party1.setModelNo(rs.getString(10));
                party1.setApplicableMFD(rs.getString(11));
                party1.setManufd(rs.getString(12));
                party1.setExpdateApplicable(rs.getString(13));
                party1.setExpDate(rs.getString(14));
                party1.setPlaceofOrigin(rs.getString(15));
                party1.setHsnNo(rs.getString(16));
                party1.setItemCode(rs.getString(17));
                party1.setBarCode(rs.getString(18));
                party1.setMinqtyalert(rs.getInt(19));
                party1.setSize(rs.getInt(20));
                party1.setRate(rs.getDouble(21));
                party1.setDefDate(rs.getString(22));
                party1.setUserName(rs.getString(23));
                party1.setEnteredDateTime(rs.getString(24));
                party1.setMenuName(rs.getString(25));
                party1.setSubMenuName(rs.getString(26));
                party1.setSubSubMenuName(rs.getString(27));
                party1.setFolderName(rs.getString(28));
                party1.setImage1_Filename(rs.getString(29));
                party1.setImage1_FilePath(rs.getString(30));
                party1.setImage2_Filename(rs.getString(31));
                party1.setImage2_FilePath(rs.getString(32));
                party1.setImage3_Filename(rs.getString(33));
                party1.setImage3_FilePath(rs.getString(34));
                party1.setImage4_Filename(rs.getString(35));
                party1.setImage4_FilePath(rs.getString(36));
                party1.setImage5_Filename(rs.getString(37));
                party1.setImage5_FilePath(rs.getString(38));
                party1.setProductDescription(rs.getString("product_description"));

            }

        } catch (Exception e) {
            System.err.println("Failed at getProductsList() " + e.toString());
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

    public boolean saveSingleProductDetail(MultipartFile image1, MultipartFile image2, MultipartFile image3, MultipartFile image4, MultipartFile image5, int Category, int submenu, int subsubmenu, String product, String style, String brand, String colour,
            String modalno, String applicationmodal, String applicablemfd, String mfd, String expdateapplicable, String expdate, String place, String hsn, String itemcode, String barcode, int minqty, int size, double Rate, String DEFdate, String content, String username) {
        boolean status = false;
        Connection con = null;
        PreparedStatement ps = null;
        int rows = 0;
        boolean result = false;
        JOptionPane.showMessageDialog(null, "1");
        String random = RandomCaptcha.fastestRandomStringWithMixedCase(15);
        String currentdate = String.valueOf(java.time.LocalDate.now());
        String image1filename = "", image1filepath = "", image2filename = "", image2filepath = "", image3filename = "", image3filepath = "", image4filename = "", image4filepath = "",
                image5filename = "", image5filepath = "", image5samplename = "", img1path, img2path, img3path, img4path, img5path;
        long rimagesize = 0;
        int isno = getMaxSno("product_master");
        String relativeWebPath = "/resources/Dynamic_FileAttachment/ProductImages/";
        String absoluteFilePath = context.getRealPath("") + File.separator + relativeWebPath;

        String savePath = absoluteFilePath;
        System.err.println(savePath);
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        if (!image1.isEmpty()) {   //product image 1
            try {
                byte[] bytes = image1.getBytes();
                String basename = FilenameUtils.getBaseName(image1.getOriginalFilename());
                String extension = FilenameUtils.getExtension(image1.getOriginalFilename());
                image1filename = "Product1" + random + "." + extension;
                rimagesize = image1.getSize();
                image1filepath = savePath + File.separator + image1filename;
                File serverFile = new File(savePath + File.separator + image1filename);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

                //DELETE ELEMENT FROM FOLDER
            } catch (Exception e) {
                image1filename = "";
                image1filepath = "";
                rimagesize = 0;
            }
        }
        JOptionPane.showMessageDialog(null, "file name=" + image1filename);

        if (!image2.isEmpty()) {   //product image 2
            try {
                byte[] bytes = image1.getBytes();
                String basename = FilenameUtils.getBaseName(image2.getOriginalFilename());
                String extension = FilenameUtils.getExtension(image2.getOriginalFilename());
                image2filename = "Product2" + random + "." + extension;
//                rimagesize = image2.getSize();
                image2filepath = savePath + File.separator + image2filename;
                File serverFile = new File(savePath + File.separator + image2filename);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                //DELETE ELEMENT FROM FOLDER

            } catch (Exception e) {
                image2filename = "";
                image2filepath = "";
//                rimagesize = 0;
            }
        }
        if (!image3.isEmpty()) {   //product image 3
            try {
                byte[] bytes = image3.getBytes();
                String basename = FilenameUtils.getBaseName(image3.getOriginalFilename());
                String extension = FilenameUtils.getExtension(image3.getOriginalFilename());
                image3filename = "Product3" + random + "." + extension;
//                rimagesize = image2.getSize();
                image3filepath = savePath + File.separator + image3filename;
                File serverFile = new File(savePath + File.separator + image3filename);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                //DELETE ELEMENT FROM FOLDER

            } catch (Exception e) {
                image3filename = "";
                image3filepath = "";
//                rimagesize = 0;
            }
        }
        if (!image4.isEmpty()) {   //product image 4
            try {
                byte[] bytes = image4.getBytes();
                String basename = FilenameUtils.getBaseName(image4.getOriginalFilename());
                String extension = FilenameUtils.getExtension(image4.getOriginalFilename());
                image4filename = "Product4" + random + "." + extension;
//                rimagesize = image2.getSize();
                image4filepath = savePath + File.separator + image4filename;
                File serverFile = new File(savePath + File.separator + image4filename);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                //DELETE ELEMENT FROM FOLDER

            } catch (Exception e) {
                image4filename = "";
                image4filepath = "";
//                rimagesize = 0;
            }
        }
        if (!image5.isEmpty()) {   //product video 5
            try {
                byte[] bytes = image5.getBytes();
                String basename = FilenameUtils.getBaseName(image5.getOriginalFilename());
                String extension = FilenameUtils.getExtension(image5.getOriginalFilename());
                image5filename = "video" + random + "." + extension;
//                rimagesize = image2.getSize();
                image5filepath = savePath + File.separator + image5filename;
                File serverFile = new File(savePath + File.separator + image5filename);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                //DELETE ELEMENT FROM FOLDER

            } catch (Exception e) {
                image5filename = "";
                image5filepath = "";
//                rimagesize = 0;
            }
        }
        JOptionPane.showMessageDialog(null, "1");

        try {
            con = Utils.ConnectionUtils.getConnection();
            ps = con.prepareStatement("insert into `product_master` (sno,category,`group`,subgroup,product,style,brand,colour,applicationmodel,modelno,applicablemfd,mfgdate,expdateapplicable,expdate,placeoforigin,hsnno,itemcode,barcode,miniqtyalert,size,rate,defdate,enteredby,enteredon,product_description,product_desc_enteredby,foldername,image1_filename,image1_filepath,image2_filename,image2_filepath,image3_filename,image3_filepath,image4_filename,image4_filepath,video_filename,video_filepath,video_samplename) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, isno);
            ps.setInt(2, Category);
            ps.setInt(3, submenu);
            ps.setInt(4, subsubmenu);
            ps.setString(5, product);
            ps.setString(6, style);
            ps.setString(7, brand);
            ps.setString(8, colour);
            ps.setString(9, applicationmodal);
            ps.setString(10, modalno);
            ps.setString(11, applicablemfd);
            ps.setString(12, mfd);
            ps.setString(13, expdateapplicable);
            ps.setString(14, expdate);
            ps.setString(15, place);
            ps.setString(16, hsn);
            ps.setString(17, itemcode);
            ps.setString(18, barcode);
            ps.setInt(19, minqty);
            ps.setInt(20, size);
            ps.setDouble(21, Rate);
            ps.setString(22, DEFdate);
            ps.setString(23, username);
            ps.setString(24, content);
            ps.setString(25, username);
            ps.setString(26, relativeWebPath);
            ps.setString(27, image1filename);
            ps.setString(28, image1filepath);
            ps.setString(29, image2filename);
            ps.setString(30, image2filepath);
            ps.setString(31, image3filename);
            ps.setString(32, image3filepath);
            ps.setString(33, image4filename);
            ps.setString(34, image4filepath);
            ps.setString(35, image5filename);
            ps.setString(36, image5filepath);
            ps.setString(37, image5samplename);
            rows = ps.executeUpdate();
            if (rows == 1) {
                status = true;
            }
        } catch (Exception e) {
            System.err.print("Error saveMenuext() " + e.toString());
            JOptionPane.showMessageDialog(null, e.toString());

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
        return status;
    }

    public boolean saveMenuext(int sno, String menuname) {
        boolean status = false;
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = Utils.ConnectionUtils.getConnection();
            ps = con.prepareStatement("insert into mastermenu (sno,menuname,hide_show) values(?,?,'Y')");
            ps.setInt(1, sno);
            ps.setString(2, menuname);
            int rows = ps.executeUpdate();
            if (rows == 1) {
                status = true;
            }
        } catch (Exception e) {
            System.err.print("Error saveMenuext() " + e.toString());
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
        return status;
    }

    public boolean saveSubMenuext(int sno, int msno, String menuname) {
        boolean status = false;
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = Utils.ConnectionUtils.getConnection();
            ps = con.prepareStatement("insert into master_submenu (sno,menusno,submenuname,hide_show) values(?,?,?,'Y')");
            ps.setInt(1, sno);
            ps.setInt(2, msno);
            ps.setString(3, menuname);
            int rows = ps.executeUpdate();
            if (rows == 1) {
                status = true;
            }
        } catch (Exception e) {
            System.err.print("Error saveMenuext() " + e.toString());
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
        return status;
    }

    public boolean saveSubSubMenuext(int sno, int msno, int mssno, String menuname) {
        boolean status = false;
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = Utils.ConnectionUtils.getConnection();
            ps = con.prepareStatement("insert into master_subsubmenu (sno,menusno,submenusno,subsubmenuname,hide_show) values(?,?,?,?,'Y')");
            ps.setInt(1, sno);
            ps.setInt(2, msno);
            ps.setInt(3, mssno);
            ps.setString(4, menuname);
            int rows = ps.executeUpdate();
            if (rows == 1) {
                status = true;
            }
        } catch (Exception e) {
            System.err.print("Error saveMenuext() " + e.toString());
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
        return status;
    }

    public boolean saveProduct(int sno, int category, int group, int subgroup, String product, String style, String brand, String colour, String applicablemodelno, String modelno, String applicablemfd, String mfgdate, String expdateapplicable, String expdate, String placeoforigin, String hsnno, String itemcode, String barcode, int minqtyalert, int size, double rate, String defdate, String enteredby) {
        boolean status = false;
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = Utils.ConnectionUtils.getConnection();
            ps = con.prepareStatement("insert into `product_master` (sno,category,`group`,subgroup,product,style,brand,colour,applicationmodel,modelno,applicablemfd,mfgdate,expdateapplicable,expdate,placeoforigin,hsnno,itemcode,barcode,miniqtyalert,size,rate,defdate,enteredby,enteredon) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW())");
            ps.setInt(1, sno);
            ps.setInt(2, category);
            ps.setInt(3, group);
            ps.setInt(4, subgroup);
            ps.setString(5, product);
            ps.setString(6, style);
            ps.setString(7, brand);
            ps.setString(8, colour);
            ps.setString(9, applicablemodelno);
            ps.setString(10, modelno);
            ps.setString(11, applicablemfd);
            ps.setString(12, mfgdate);
            ps.setString(13, expdateapplicable);
            ps.setString(14, expdate);
            ps.setString(15, placeoforigin);
            ps.setString(16, hsnno);
            ps.setString(17, itemcode);
            ps.setString(18, barcode);
            ps.setInt(19, minqtyalert);
            ps.setInt(20, size);
            ps.setDouble(21, rate);
            ps.setString(22, defdate);
            ps.setString(23, enteredby);

            int rows = ps.executeUpdate();
            if (rows == 1) {
                status = true;
            }
        } catch (Exception e) {
            System.err.print("Error saveMenuext() " + e.toString());
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
        return status;
    }

    public boolean updateProduct(int sno, int category, int group, int subgroup, String product, String style, String brand, String colour, String applicablemodelno, String modelno, String applicablemfd, String mfgdate, String expdateapplicable, String expdate, String placeoforigin, String hsnno, String itemcode, String barcode, int minqtyalert, int size, double rate, String defdate, String enteredby) {
        boolean status = false;
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = Utils.ConnectionUtils.getConnection();
            ps = con.prepareStatement("update `product_master` set applicationmodel=?,applicablemfd=?,mfgdate=?,expdateapplicable=?,expdate=?,placeoforigin=?,miniqtyalert=?,size=?,rate=?,defdate=?,updatedby=?,updatedon=NOW() WHERE  category=? and `group`=? and subgroup=? and product=? and style=? and brand=? and colour=? and modelno=? and hsnno=? and itemcode=? and barcode=?");

            ps.setString(1, applicablemodelno);
            ps.setString(2, applicablemfd);
            ps.setString(3, mfgdate);
            ps.setString(4, expdateapplicable);
            ps.setString(5, expdate);
            ps.setString(6, placeoforigin);

            ps.setInt(7, minqtyalert);
            ps.setInt(8, size);
            ps.setDouble(9, rate);
            ps.setString(10, defdate);
            ps.setString(11, enteredby);
            ps.setInt(12, category);
            ps.setInt(13, group);
            ps.setInt(14, subgroup);
            ps.setString(15, product);
            ps.setString(16, style);
            ps.setString(17, brand);
            ps.setString(18, colour);
            ps.setString(19, modelno);
            ps.setString(20, hsnno);
            ps.setString(21, itemcode);
            ps.setString(22, barcode);
            int rows = ps.executeUpdate();
            if (rows == 1) {
                status = true;
            }
        } catch (Exception e) {
            System.err.print("Error updateProduct() " + e.toString());
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
        return status;
    }

    public int saveExcelProductUploaded(String category, String group, String subgroup, String product, String style, String brand, String colour, String applicablemodelno, String modelno, String applicablemfd, String mfgdate, String expdateapplicable, String expdate, String placeoforigin, String hsnno, String itemcode, String barcode, int minqtyalert, int size, double rate, String defdate, String username) {
        int sn = 0;
        boolean checkmenuExist = CategoryalreadyExist(category);

        int maxmenusno = 0;
        int maxsubmenusno = 0;
        int maxsubsubmenusno = 0;
        int maxproductsno = 0;
        if (checkmenuExist == false) {
            maxmenusno = getMaxSno("mastermenu");
            saveMenuext(maxmenusno, category);

        } else {
            maxmenusno = MenuDao.getMenus(category).getSno();
        }
        boolean checkgroupExist = GroupalreadyExist(maxmenusno, group);
        if (checkgroupExist == false) {
            maxsubmenusno = getMaxSno("master_submenu");
            saveSubMenuext(maxsubmenusno, maxmenusno, group);
        } else {
            maxsubmenusno = MenuDao.getSubMenus(maxmenusno, group).getSno();
        }

        boolean checksubgroupExist = SubGroupalreadyExist(maxmenusno, maxsubmenusno, subgroup);
        if (checksubgroupExist == false) {
            maxsubsubmenusno = getMaxSno("master_subsubmenu");
            saveSubSubMenuext(maxsubsubmenusno, maxmenusno, maxsubmenusno, subgroup);
        } else {
            maxsubsubmenusno = MenuDao.getSubSubMenus(maxmenusno, maxsubmenusno, subgroup).getSno();
        }

        boolean checkProductExist = SubProductalreadyExist(maxmenusno, maxsubmenusno, maxsubsubmenusno, product, style, brand, colour, modelno, hsnno, itemcode, barcode);
        if (checkProductExist == false) {
            maxproductsno = getMaxSno("product_master");

            saveProduct(maxproductsno, maxmenusno, maxsubmenusno, maxsubsubmenusno, product, style, brand, colour, applicablemodelno, modelno, applicablemfd, mfgdate, expdateapplicable, expdate, placeoforigin, hsnno, itemcode, barcode, minqtyalert, size, rate, defdate, username);
        } else {
            updateProduct(maxproductsno, maxmenusno, maxsubmenusno, maxsubsubmenusno, product, style, brand, colour, applicablemodelno, modelno, applicablemfd, mfgdate, expdateapplicable, expdate, placeoforigin, hsnno, itemcode, barcode, minqtyalert, size, rate, defdate, username);

        }

        return sn;
    }

    public static boolean CategoryalreadyExist(String category) {
        boolean result = false;
        try {

            Connection con = Utils.ConnectionUtils.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT menuname FROM `mastermenu` WHERE  menuname=?");
            ps.setString(1, category);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = true;
            }
            con.close();
            rs.close();
            ps.close();

        } catch (Exception e) {
            System.err.println("Failed at CategoryalreadyExist():" + e.toString());
        }
        return result;
    }

    public static boolean GroupalreadyExist(int menusno, String category) {
        boolean result = false;
        try {

            Connection con = Utils.ConnectionUtils.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT submenuname FROM `master_submenu` WHERE  submenuname=? and menusno=?");
            ps.setString(1, category);
            ps.setInt(2, menusno);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = true;
            }
            con.close();
            rs.close();
            ps.close();

        } catch (Exception e) {
            System.err.println("Failed at CategoryalreadyExist():" + e.toString());
        }
        return result;
    }

    public static boolean SubGroupalreadyExist(int menusno, int submenusno, String category) {
        boolean result = false;
        try {

            Connection con = Utils.ConnectionUtils.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT subsubmenuname FROM `master_subsubmenu` WHERE  subsubmenuname=? and menusno=? and submenusno=?");
            ps.setString(1, category);
            ps.setInt(2, menusno);
            ps.setInt(3, submenusno);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = true;
            }
            con.close();
            rs.close();
            ps.close();

        } catch (Exception e) {
            System.err.println("Failed at CategoryalreadyExist():" + e.toString());
        }
        return result;
    }

    public static boolean SubProductalreadyExist(int menusno, int submenusno, int subsubmenusno, String product, String style, String brand, String colour, String modelno, String hsnno, String itemcode, String barcode) {
        boolean result = false;
        try {

            Connection con = Utils.ConnectionUtils.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT product FROM `product_master` WHERE  category=? and `group`=? and subgroup=? and product=? and style=? and brand=? and colour=? and modelno=? and hsnno=? and itemcode=? and barcode=?");
            ps.setInt(1, menusno);
            ps.setInt(2, submenusno);
            ps.setInt(3, subsubmenusno);
            ps.setString(4, product);
            ps.setString(5, style);
            ps.setString(6, brand);
            ps.setString(7, colour);
            ps.setString(8, modelno);
            ps.setString(9, hsnno);
            ps.setString(10, itemcode);
            ps.setString(11, barcode);
            System.err.println(ps);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = true;
            }
            con.close();
            rs.close();
            ps.close();

        } catch (Exception e) {
            System.err.println("Failed at CategoryalreadyExist():" + e.toString());
        }
        return result;
    }

    public static int getMaxSno(String tablename) {
        int ino = 0;
        try {

            Connection con = Utils.ConnectionUtils.getConnection();
            PreparedStatement ps = con.prepareStatement("select max(sno) from " + tablename + "");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ino = rs.getInt(1);
            }
            con.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            System.err.println("Failed at getMaxSno() " + e.toString());
        }
        return ino + 1;
    }

    public static int getSavedMaxSno(String tablename) {
        int ino = 0;
        try {

            Connection con = Utils.ConnectionUtils.getConnection();
            PreparedStatement ps = con.prepareStatement("select max(sno) from " + tablename + "");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ino = rs.getInt(1);
            }
            con.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            System.err.println("Failed at getMaxSno() " + e.toString());
        }
        return ino;
    }

    public static boolean saveProductDate(Product[] ist1) {

        boolean status = false;
        ProductDao pdao = new ProductDao();
        for (int i = 1; i < ist1.length; i++) {
            Product ist = ist1[i];
            String category = ist.getCategory();
            String group = ist.getGroup();
            String subgroup = ist.getSubGroup();
            String product = ist.getProductName();
            String style = ist.getStyle();
            String brand = ist.getBrand();
            String colour = ist.getColour();
            String applicablemodelno = ist.getApplicableModelNo();
            String modelno = ist.getModelNo();
            String applicablemfd = ist.getApplicableMFD();
            String manufd = ist.getManufd();
            String expdateapplicable = ist.getExpdateApplicable();
            String expdate = ist.getExpDate();
            String placeoforigin = ist.getPlaceofOrigin();
            String hsnno = ist.getHsnNo();
            String itemcode = ist.getItemCode();
            String barcode = ist.getBarCode();
            int miniqtyalert = ist.getMinqtyalert();
            int size = ist.getSize();
            double rate = ist.getRate();
            String defdate = ist.getDefDate();
            String username = ist.getUserName();

//       System.out.println("miniqtyalert="+miniqtyalert);
//       System.out.println("size="+size);
//       System.out.println("rate="+rate);
            int saveExcelrow = pdao.saveExcelProductUploaded(category, group, subgroup, product, style, brand, colour, applicablemodelno, modelno, applicablemfd, manufd, expdateapplicable, expdate, placeoforigin, hsnno, itemcode, barcode, miniqtyalert, size, rate, defdate, username);
//if(saveExcelrow==1){status=true;}
        }

        return status;
    }

    public boolean UploadProductImagesMultiple(MultipartFile image1, MultipartFile image2, MultipartFile image3, MultipartFile image4, MultipartFile image5, String items[], String enteredby, String content) {

        Connection con = null;
        PreparedStatement ps = null;
        int rows = 0;
        boolean result = false;

        String random = RandomCaptcha.fastestRandomStringWithMixedCase(15);
        String currentdate = String.valueOf(java.time.LocalDate.now());
        String image1filename = "", image1filepath = "", image2filename = "", image2filepath = "", image3filename = "", image3filepath = "", image4filename = "", image4filepath = "",
                image5filename = "", image5filepath = "", image5samplename = "";
        long rimagesize = 0;
        String relativeWebPath = "/resources/Dynamic_FileAttachment/ProductImages/";
        String absoluteFilePath = context.getRealPath("") + File.separator + relativeWebPath;

        String savePath = absoluteFilePath;
        System.err.println(savePath);
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        for (int index = 0; index < items.length; index++) {
            String item[] = items[index].split(">");
            int isno = Integer.parseInt(item[0].trim());
            ProductDao da = new ProductDao();
            Product dao1 = da.getProductsDetail(isno);
            String img1path = dao1.getImage1_FilePath();
            String img2path = dao1.getImage2_FilePath();
            String img3path = dao1.getImage3_FilePath();
            String img4path = dao1.getImage4_FilePath();
            String img5path = dao1.getImage5_FilePath();

            if (!image1.isEmpty()) {   //product image 1
                try {
                    byte[] bytes = image1.getBytes();
                    String basename = FilenameUtils.getBaseName(image1.getOriginalFilename());
                    String extension = FilenameUtils.getExtension(image1.getOriginalFilename());
                    image1filename = "Product1" + random + "." + extension;
                    rimagesize = image1.getSize();
                    image1filepath = savePath + File.separator + image1filename;
                    File serverFile = new File(savePath + File.separator + image1filename);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.flush();
                    stream.close();

                    //DELETE ELEMENT FROM FOLDER
                    Path imagesPath = Paths.get(img1path);

                    try {
                        Files.delete(imagesPath);
                        System.out.println("File " + imagesPath.toAbsolutePath().toString() + " successfully removed");
                    } catch (IOException e) {
                        System.err.println("Unable to delete " + imagesPath.toAbsolutePath().toString() + " due to...");
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    image1filename = "";
                    image1filepath = "";
                    rimagesize = 0;
                }
            }
            if (!image2.isEmpty()) {   //product image 2
                try {
                    byte[] bytes = image1.getBytes();
                    String basename = FilenameUtils.getBaseName(image2.getOriginalFilename());
                    String extension = FilenameUtils.getExtension(image2.getOriginalFilename());
                    image2filename = "Product2" + random + "." + extension;
//                rimagesize = image2.getSize();
                    image2filepath = savePath + File.separator + image2filename;
                    File serverFile = new File(savePath + File.separator + image2filename);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.flush();
                    stream.close();
                    //DELETE ELEMENT FROM FOLDER
                    Path imagesPath2 = Paths.get(img2path);

                    try {
                        Files.delete(imagesPath2);
                        System.out.println("File 2 " + imagesPath2.toAbsolutePath().toString() + " successfully removed");
                    } catch (IOException e) {
                        System.err.println("Unable to delete 2 " + imagesPath2.toAbsolutePath().toString() + " due to...");
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    image2filename = "";
                    image2filepath = "";
//                rimagesize = 0;
                }
            }
            if (!image3.isEmpty()) {   //product image 3
                try {
                    byte[] bytes = image3.getBytes();
                    String basename = FilenameUtils.getBaseName(image3.getOriginalFilename());
                    String extension = FilenameUtils.getExtension(image3.getOriginalFilename());
                    image3filename = "Product3" + random + "." + extension;
//                rimagesize = image2.getSize();
                    image3filepath = savePath + File.separator + image3filename;
                    File serverFile = new File(savePath + File.separator + image3filename);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.flush();
                    stream.close();
                    //DELETE ELEMENT FROM FOLDER
                    Path imagesPath3 = Paths.get(img3path);

                    try {
                        Files.delete(imagesPath3);
                        System.out.println("File 3 " + imagesPath3.toAbsolutePath().toString() + " successfully removed");
                    } catch (IOException e) {
                        System.err.println("Unable to delete 3 " + imagesPath3.toAbsolutePath().toString() + " due to...");
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    image3filename = "";
                    image3filepath = "";
//                rimagesize = 0;
                }
            }
            if (!image4.isEmpty()) {   //product image 4
                try {
                    byte[] bytes = image4.getBytes();
                    String basename = FilenameUtils.getBaseName(image4.getOriginalFilename());
                    String extension = FilenameUtils.getExtension(image4.getOriginalFilename());
                    image4filename = "Product4" + random + "." + extension;
//                rimagesize = image2.getSize();
                    image4filepath = savePath + File.separator + image4filename;
                    File serverFile = new File(savePath + File.separator + image4filename);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.flush();
                    stream.close();
                    //DELETE ELEMENT FROM FOLDER
                    Path imagesPath5 = Paths.get(img4path);

                    try {
                        Files.delete(imagesPath5);
                        System.out.println("File 5 " + imagesPath5.toAbsolutePath().toString() + " successfully removed");
                    } catch (IOException e) {
                        System.err.println("Unable to delete 5 " + imagesPath5.toAbsolutePath().toString() + " due to...");
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    image4filename = "";
                    image4filepath = "";
//                rimagesize = 0;
                }
            }
            if (!image5.isEmpty()) {   //product video 5
                try {
                    byte[] bytes = image5.getBytes();
                    String basename = FilenameUtils.getBaseName(image5.getOriginalFilename());
                    String extension = FilenameUtils.getExtension(image5.getOriginalFilename());
                    image5filename = "video" + random + "." + extension;
//                rimagesize = image2.getSize();
                    image5filepath = savePath + File.separator + image5filename;
                    File serverFile = new File(savePath + File.separator + image5filename);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.flush();
                    stream.close();
                    //DELETE ELEMENT FROM FOLDER
                    Path imagesPath6 = Paths.get(img5path);

                    try {
                        Files.delete(imagesPath6);
                        System.out.println("File 6 " + imagesPath6.toAbsolutePath().toString() + " successfully removed");
                    } catch (IOException e) {
                        System.err.println("Unable to delete 6 " + imagesPath6.toAbsolutePath().toString() + " due to...");
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    image5filename = "";
                    image5filepath = "";
//                rimagesize = 0;
                }
            }
            try {

                con = Utils.ConnectionUtils.getConnection();
                ps = con.prepareStatement("update product_master set foldername=?,image1_filename=?,image1_filepath=?,image2_filename=?,image2_filepath=?,image3_filename=?,image3_filepath=?,image4_filename=?,image4_filepath=?,video_filename=?,video_filepath=?,video_samplename=?,product_description=?,product_desc_enteredby=? where sno=?");

                ps.setString(1, relativeWebPath);
                ps.setString(2, image1filename);
                ps.setString(3, image1filepath);
                ps.setString(4, image2filename);
                ps.setString(5, image2filepath);
                ps.setString(6, image3filename);
                ps.setString(7, image3filepath);
                ps.setString(8, image4filename);
                ps.setString(9, image4filepath);
                ps.setString(10, image5filename);
                ps.setString(11, image5filepath);
                ps.setString(12, image5samplename);
                ps.setString(13, content);
                ps.setString(14, enteredby);
                ps.setInt(15, isno);
//            ps.setString(14, enteredby);

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
        }
        return result;
    }

    public boolean UpdateProductImages(MultipartFile image1, MultipartFile image2, MultipartFile image3, MultipartFile image4, MultipartFile image5, int items, String enteredby) {

        Connection con = null;
        PreparedStatement ps = null;
        int rows = 0;
        boolean result = false;

        String random = RandomCaptcha.fastestRandomStringWithMixedCase(15);
        String currentdate = String.valueOf(java.time.LocalDate.now());
        String image1filename = "", image1filepath = "", image2filename = "", image2filepath = "", image3filename = "", image3filepath = "", image4filename = "", image4filepath = "",
                image5filename = "", image5filepath = "", image5samplename = "";
        long rimagesize = 0;
        String relativeWebPath = "/resources/Dynamic_FileAttachment/ProductImages/";
        String absoluteFilePath = context.getRealPath("") + File.separator + relativeWebPath;

        String savePath = absoluteFilePath;
        System.err.println(savePath);
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        int isno = items;
        ProductDao da = new ProductDao();
        Product dao1 = da.getProductsDetail(isno);
        String img1path = dao1.getImage1_FilePath();
        String img2path = dao1.getImage2_FilePath();
        String img3path = dao1.getImage3_FilePath();
        String img4path = dao1.getImage4_FilePath();
        String img5path = dao1.getImage5_FilePath();

        if (!image1.isEmpty()) {   //product image 1
            try {
                byte[] bytes = image1.getBytes();
                String basename = FilenameUtils.getBaseName(image1.getOriginalFilename());
                String extension = FilenameUtils.getExtension(image1.getOriginalFilename());
                image1filename = "Product1" + random + "." + extension;
                rimagesize = image1.getSize();
                image1filepath = savePath + File.separator + image1filename;
                File serverFile = new File(savePath + File.separator + image1filename);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

                //DELETE ELEMENT FROM FOLDER
                Path imagesPath = Paths.get(img1path);

                try {
                    Files.delete(imagesPath);
                    System.out.println("File " + imagesPath.toAbsolutePath().toString() + " successfully removed");
                } catch (IOException e) {
                    System.err.println("Unable to delete " + imagesPath.toAbsolutePath().toString() + " due to...");
                    e.printStackTrace();
                }
            } catch (Exception e) {
                image1filename = "";
                image1filepath = "";
                rimagesize = 0;
            }
        }
        if (!image2.isEmpty()) {   //product image 2
            try {
                byte[] bytes = image1.getBytes();
                String basename = FilenameUtils.getBaseName(image2.getOriginalFilename());
                String extension = FilenameUtils.getExtension(image2.getOriginalFilename());
                image2filename = "Product2" + random + "." + extension;
//                rimagesize = image2.getSize();
                image2filepath = savePath + File.separator + image2filename;
                File serverFile = new File(savePath + File.separator + image2filename);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                //DELETE ELEMENT FROM FOLDER
                Path imagesPath2 = Paths.get(img2path);

                try {
                    Files.delete(imagesPath2);
                    System.out.println("File 2 " + imagesPath2.toAbsolutePath().toString() + " successfully removed");
                } catch (IOException e) {
                    System.err.println("Unable to delete 2 " + imagesPath2.toAbsolutePath().toString() + " due to...");
                    e.printStackTrace();
                }
            } catch (Exception e) {
                image2filename = "";
                image2filepath = "";
//                rimagesize = 0;
            }
        }
        if (!image3.isEmpty()) {   //product image 3
            try {
                byte[] bytes = image3.getBytes();
                String basename = FilenameUtils.getBaseName(image3.getOriginalFilename());
                String extension = FilenameUtils.getExtension(image3.getOriginalFilename());
                image3filename = "Product3" + random + "." + extension;
//                rimagesize = image2.getSize();
                image3filepath = savePath + File.separator + image3filename;
                File serverFile = new File(savePath + File.separator + image3filename);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                //DELETE ELEMENT FROM FOLDER
                Path imagesPath3 = Paths.get(img3path);

                try {
                    Files.delete(imagesPath3);
                    System.out.println("File 3 " + imagesPath3.toAbsolutePath().toString() + " successfully removed");
                } catch (IOException e) {
                    System.err.println("Unable to delete 3 " + imagesPath3.toAbsolutePath().toString() + " due to...");
                    e.printStackTrace();
                }
            } catch (Exception e) {
                image3filename = "";
                image3filepath = "";
//                rimagesize = 0;
            }
        }
        if (!image4.isEmpty()) {   //product image 4
            try {
                byte[] bytes = image4.getBytes();
                String basename = FilenameUtils.getBaseName(image4.getOriginalFilename());
                String extension = FilenameUtils.getExtension(image4.getOriginalFilename());
                image4filename = "Product4" + random + "." + extension;
//                rimagesize = image2.getSize();
                image4filepath = savePath + File.separator + image4filename;
                File serverFile = new File(savePath + File.separator + image4filename);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                //DELETE ELEMENT FROM FOLDER
                Path imagesPath5 = Paths.get(img4path);

                try {
                    Files.delete(imagesPath5);
                    System.out.println("File 5 " + imagesPath5.toAbsolutePath().toString() + " successfully removed");
                } catch (IOException e) {
                    System.err.println("Unable to delete 5 " + imagesPath5.toAbsolutePath().toString() + " due to...");
                    e.printStackTrace();
                }
            } catch (Exception e) {
                image4filename = "";
                image4filepath = "";
//                rimagesize = 0;
            }
        }
        if (!image5.isEmpty()) {   //product video 5
            try {
                byte[] bytes = image5.getBytes();
                String basename = FilenameUtils.getBaseName(image5.getOriginalFilename());
                String extension = FilenameUtils.getExtension(image5.getOriginalFilename());
                image5filename = "video" + random + "." + extension;
//                rimagesize = image2.getSize();
                image5filepath = savePath + File.separator + image5filename;
                File serverFile = new File(savePath + File.separator + image5filename);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                //DELETE ELEMENT FROM FOLDER
                Path imagesPath6 = Paths.get(img5path);

                try {
                    Files.delete(imagesPath6);
                    System.out.println("File 6 " + imagesPath6.toAbsolutePath().toString() + " successfully removed");
                } catch (IOException e) {
                    System.err.println("Unable to delete 6 " + imagesPath6.toAbsolutePath().toString() + " due to...");
                    e.printStackTrace();
                }
            } catch (Exception e) {
                image5filename = "";
                image5filepath = "";
//                rimagesize = 0;
            }
        }
        try {

            con = Utils.ConnectionUtils.getConnection();
            ps = con.prepareStatement("update product_master set foldername=?,image1_filename=?,image1_filepath=?,image2_filename=?,image2_filepath=?,image3_filename=?,image3_filepath=?,image4_filename=?,image4_filepath=?,video_filename=?,video_filepath=?,video_samplename=? where sno=?");

            ps.setString(1, relativeWebPath);
            ps.setString(2, image1filename);
            ps.setString(3, image1filepath);
            ps.setString(4, image2filename);
            ps.setString(5, image2filepath);
            ps.setString(6, image3filename);
            ps.setString(7, image3filepath);
            ps.setString(8, image4filename);
            ps.setString(9, image4filepath);
            ps.setString(10, image5filename);
            ps.setString(11, image5filepath);
            ps.setString(12, image5samplename);
            ps.setInt(13, isno);
//            ps.setString(14, enteredby);

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

    public List<Product> getDistinctProductsList(int menusno, int submenu, int subsubmenu, String product, String style, String brand, String colour, int size, String modelno, String hsn, String itemcode, String barcode, String filterimage) {
        List<Product> list = new ArrayList();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            String menusnoquery = menusno > 0 ? " and product_master.category='" + menusno + "'" : "";
            String submenuquery = submenu > 0 ? " and product_master.group='" + submenu + "'" : "";
            String subsubmenuquery = subsubmenu > 0 ? " and product_master.subgroup='" + subsubmenu + "'" : "";
            String productquery = product.equalsIgnoreCase("") ? "" : " and product_master.product='" + product + "'";
            String stylequery = style.equalsIgnoreCase("") ? "" : " and product_master.style='" + style + "'";
            String brandquery = brand.equalsIgnoreCase("") ? "" : " and product_master.brand='" + brand + "'";
            String colourquery = colour.equalsIgnoreCase("") ? "" : " and product_master.colour='" + colour + "'";
            String modelnoquery = modelno.equalsIgnoreCase("") ? "" : " and product_master.modelno='" + modelno + "'";
            String hsnquery = hsn.equalsIgnoreCase("") ? "" : " and product_master.hsnno='" + hsn + "'";
            String itemcodequery = itemcode.equalsIgnoreCase("") || itemcode.equalsIgnoreCase("Select") ? "" : " and product_master.itemcode='" + itemcode + "'";
            String barcodequery = barcode.equalsIgnoreCase("") || barcode.equalsIgnoreCase("Select") ? "" : " and product_master.barcode='" + barcode + "'";
            String quef = "";
            if (filterimage.equalsIgnoreCase("Y")) {
                quef = " and product_master.foldername!=''";
            } else if (filterimage.equalsIgnoreCase("N")) {
                quef = " and product_master.foldername=''";
            }
            String filterimagequery = filterimage.equalsIgnoreCase("") || filterimage.equalsIgnoreCase("Select") ? "" : quef;

            con = Utils.ConnectionUtils.getConnection();

            ps = con.prepareStatement("SELECT product_master.sno,product_master.category,product_master.`group`,product_master.subgroup,product_master.product,product_master.style,product_master.brand,product_master.colour,product_master.applicationmodel,product_master.modelno,product_master.applicablemfd,product_master.mfgdate,product_master.expdateapplicable,product_master.expdate,product_master.placeoforigin,product_master.hsnno,product_master.itemcode,product_master.barcode,product_master.miniqtyalert,product_master.size,product_master.rate,product_master.defdate,product_master.enteredby,DATE_FORMAT(product_master.enteredon,'%M %d,%Y %p'),mastermenu.menuname,master_submenu.submenuname,master_subsubmenu.subsubmenuname,product_master.foldername,product_master.image1_filename,product_master.image1_filepath,product_master.image2_filename,product_master.image2_filepath,product_master.image3_filename,product_master.image3_filepath,product_master.image4_filename,product_master.image4_filepath,product_master.video_filename,product_master.video_filepath from product_master INNER JOIN mastermenu on mastermenu.sno=product_master.category INNER JOIN  master_submenu on master_submenu.sno=product_master.`group` INNER JOIN master_subsubmenu on master_subsubmenu.sno=product_master.subgroup where product_master.sno is not null  " + menusnoquery + submenuquery + subsubmenuquery + productquery + stylequery + brandquery + colourquery + modelnoquery + hsnquery + itemcodequery + barcodequery + filterimagequery + " group by product_master.product");
            rs = ps.executeQuery();
            while (rs.next()) {
                Product party1 = new Product();
                party1.setSno(rs.getInt(1));
                party1.setCategory(rs.getString(2));
                party1.setGroup(rs.getString(3));
                party1.setSubGroup(rs.getString(4));
                party1.setProductName(rs.getString(5));
                party1.setStyle(rs.getString(6));
                party1.setBrand(rs.getString(7));
                party1.setColour(rs.getString(8));
                party1.setApplicableModelNo(rs.getString(9));
                party1.setModelNo(rs.getString(10));
                party1.setApplicableMFD(rs.getString(11));
                party1.setManufd(rs.getString(12));
                party1.setExpdateApplicable(rs.getString(13));
                party1.setExpDate(rs.getString(14));
                party1.setPlaceofOrigin(rs.getString(15));
                party1.setHsnNo(rs.getString(16));
                party1.setItemCode(rs.getString(17));
                party1.setBarCode(rs.getString(18));
                party1.setMinqtyalert(rs.getInt(19));
                party1.setSize(rs.getInt(20));
                party1.setRate(rs.getDouble(21));
                party1.setDefDate(rs.getString(22));
                party1.setUserName(rs.getString(23));
                party1.setEnteredDateTime(rs.getString(24));
                party1.setMenuName(rs.getString(25));
                party1.setSubMenuName(rs.getString(26));
                party1.setSubSubMenuName(rs.getString(27));
                party1.setFolderName(rs.getString(28));
                party1.setImage1_Filename(rs.getString(29));
                party1.setImage1_FilePath(rs.getString(30));
                party1.setImage2_Filename(rs.getString(31));
                party1.setImage2_FilePath(rs.getString(32));
                party1.setImage3_Filename(rs.getString(33));
                party1.setImage3_FilePath(rs.getString(34));
                party1.setImage4_Filename(rs.getString(35));
                party1.setImage4_FilePath(rs.getString(36));
                party1.setImage5_Filename(rs.getString(37));
                party1.setImage5_FilePath(rs.getString(38));

                list.add(party1);
            }

        } catch (Exception e) {
            System.err.println("Failed at getProductsList() " + e.toString());
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

    public boolean UpdateSingleProduct(int sno, String content, String descenteredby) {
        boolean status = false;
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = Utils.ConnectionUtils.getConnection();
            ps = con.prepareStatement("update  set product_description=?,product_desc_enteredby=? where sno=?");
            ps.setInt(1, sno);
            ps.setString(2, content);
            ps.setString(3, descenteredby);
            int rows = ps.executeUpdate();
            if (rows == 1) {
                status = true;
            }
        } catch (Exception e) {
            System.err.print("Error UpdateSingleProduct() " + e.toString());
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
        return status;
    }
//    public static void main(String[] args) {
//    //        ProductDao pdao=new ProductDao();
//
//   //     System.err.println(pdao.getDistinctProductsList(0, 0, 0, product, style, brand, colour, 0, modelno, hsn, itemcode, barcode, filterimage));
//    }
}
