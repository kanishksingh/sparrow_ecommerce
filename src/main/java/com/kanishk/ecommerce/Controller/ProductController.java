/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanishk.ecommerce.Controller;

import com.kanishk.ecommerce.Beans.Menu;
import com.kanishk.ecommerce.Beans.Product;
import static com.kanishk.ecommerce.Controller.menuController.APPLICATION_JSON_UTF8_VALUE;
import com.kanishk.ecommerce.Dao.MenuDao;
import com.kanishk.ecommerce.Dao.ProductDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author KANISHK
 */
@Controller
public class ProductController {

    @Autowired
    ProductDao pdao;
    @Autowired
    MenuDao mdao;
    @Autowired
    ServletContext context;

    @RequestMapping("/newproduct")
    public String gotoSubSubMenu(Model m) {
         List<Menu> mlist = mdao.getMenuList();
        m.addAttribute("menulists", mlist);
        List<Product> list1 = pdao.getProductsList(0, 0, 0, "", "", "", "", 0, "", "", "", "", "");
        m.addAttribute("productlists", list1);
        m.addAttribute("msg", "Create / Update Master Product");
        return "NewProduct";
    }

    @RequestMapping("/uploadProductimage")
    public String uploadImage(Model m) {
//        List<Product> list1 = pdao.getProductsList(0, 0, 0, "", "", "", "", 0, "", "", "", "");
        m.addAttribute("productlists", "");
        List<Menu> mlist = mdao.getMenuList();
        m.addAttribute("menulists", mlist);
        List<Menu> smlist = mdao.getSubMenuList();
        m.addAttribute("submenulist", smlist);
        List<Menu> ssmlist = mdao.getSubSubMenuList1();
        m.addAttribute("subsubmenulist", ssmlist);
        List<Product> pitemlist1 = pdao.getDistinctProduct("itemcode");
        List<Product> pbarlist1 = pdao.getDistinctProduct("barcode");
        m.addAttribute("productitemcode", pitemlist1);
        m.addAttribute("productbarcode", pbarlist1);
        m.addAttribute("msg", "Upload Master Product Image");
        return "ProductImage";
    }

    @RequestMapping(value = "/UploadProductDetail", method = RequestMethod.POST)
    public @ResponseBody
    String singleFileUpload(@RequestParam("file") MultipartFile rimage, HttpSession session)
            throws IOException {
        String value = "";
        String prodictid, category, group, subgroup, product, style, brand, colour, applicablemodelno, modelno, applicablemfd, mfgdate, expdateapplicable, expdate,
                placeoforigin, barcode, defdate, hsnno, itemcode;
        int minqtyalert = 0;
        int size = 0;
        double rate = 0;
        //if show in table use list data
        //                 List<Product> tempStudentList = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(rimage.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        String username = session.getAttribute("username").toString();

//for db use below
        Product[] poitemlist = new Product[worksheet.getPhysicalNumberOfRows()];

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Product tempStudent = new Product();

            XSSFRow row = worksheet.getRow(i);
            try {
                category = (row.getCell(1).toString()).trim();
            } catch (Exception e) {
                category = "";
            }
            try {
                group = (row.getCell(2).toString()).trim();
            } catch (Exception e) {
                group = "";
            }

            try {
                subgroup = (row.getCell(3).toString()).trim();
            } catch (Exception e) {
                subgroup = "";
            }

            try {
                product = (row.getCell(4).toString()).trim();
            } catch (Exception e) {
                product = "";
            }

            try {
                style = (row.getCell(5).toString()).trim();
            } catch (Exception e) {
                style = "";
            }

            try {
                brand = (row.getCell(6).toString()).trim();
            } catch (Exception e) {
                brand = "";
            }

            try {
                colour = (row.getCell(7).toString()).trim();
            } catch (Exception e) {
                colour = "";
            }

            try {
                applicablemodelno = (row.getCell(8).toString()).trim();
            } catch (Exception e) {
                applicablemodelno = "";
            }

            try {
                modelno = (row.getCell(9).toString()).trim();
            } catch (Exception e) {
                modelno = "";
            }

            try {
                applicablemfd = (row.getCell(10).toString()).trim();
            } catch (Exception e) {
                applicablemfd = "";
            }

            try {
                mfgdate = (row.getCell(11).toString()).trim();
            } catch (Exception e) {
                mfgdate = "";
            }

            try {
                expdateapplicable = (row.getCell(12).toString()).trim();
            } catch (Exception e) {
                expdateapplicable = "";
            }
            try {
                expdate = (row.getCell(13).toString()).trim();
            } catch (Exception e) {
                expdate = "";
            }
            try {
                placeoforigin = (row.getCell(14).toString()).trim();
            } catch (Exception e) {
                placeoforigin = "";
            }
            try {
                hsnno = ((String.valueOf((int) row.getCell(15).getNumericCellValue())).trim());
            } catch (Exception e) {
                hsnno = "";
            }
            try {
                itemcode = ((String.valueOf((int) row.getCell(16).getNumericCellValue())).trim());
            } catch (Exception e) {
                itemcode = "";
            }
            try {
                barcode = (row.getCell(17).toString()).trim();
            } catch (Exception e) {
                barcode = "";
            }
            try {
                double minqtya = row.getCell(18).getNumericCellValue();
                minqtyalert = (int) minqtya;
            } catch (Exception e) {
                minqtyalert = 0;
            }
            try {
                double sizea = row.getCell(19).getNumericCellValue();
                size = (int) sizea;;
            } catch (Exception e) {
                size = 0;
            }
            try {
                rate = row.getCell(20).getNumericCellValue();
            } catch (Exception e) {
                rate = 0;
            }
            try {
                defdate = (row.getCell(21).getStringCellValue()).toString();
            } catch (Exception e) {
                defdate = "";
            }
            tempStudent.setCategory(category);
            tempStudent.setGroup(group);
            tempStudent.setSubGroup(subgroup);
            tempStudent.setProductName(product);
            tempStudent.setStyle(style);
            tempStudent.setBrand(brand);
            tempStudent.setColour(colour);
            tempStudent.setApplicableModelNo(applicablemodelno);
            tempStudent.setModelNo(modelno);
            tempStudent.setApplicableMFD(applicablemfd);
            tempStudent.setManufd(mfgdate);
            tempStudent.setExpdateApplicable(expdateapplicable);
            tempStudent.setExpDate(expdate);
            tempStudent.setPlaceofOrigin(placeoforigin);
            tempStudent.setHsnNo(hsnno);
            tempStudent.setItemCode(itemcode);
            tempStudent.setBarCode(barcode);
            tempStudent.setMinqtyalert(minqtyalert);
            tempStudent.setSize(size);
            tempStudent.setRate(rate);
            tempStudent.setDefDate(defdate);
            tempStudent.setUserName(username);
            //    tempStudentList.add(tempStudent);

//                     int saveExcelrow= pdao.saveExcelProductUploaded(category,group,subgroup,product,style,brand,colour,applicablemodelno,modelno,applicablemfd,mfgdate,expdateapplicable,expdate,placeoforigin,hsnno,itemcode,barcode,minqtyalert,size,rate,defdate,username);
            poitemlist[i] = tempStudent;

        }
        boolean checksave = pdao.saveProductDate(poitemlist);
        return value;
    }

    @RequestMapping(value = "/AjaxPageCall/{filter}/{Category}/{Group}/{subGroup}/{itemcode}/{BarCode}", method = RequestMethod.POST, produces = APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    List<Product> relatednewsDetail(@PathVariable String filter, @PathVariable int Category, @PathVariable int Group, @PathVariable int subGroup, @PathVariable String itemcode, @PathVariable String BarCode) {
        int menu = 0, submenu = 0, subsubmenu = 0;
        try {
            menu = Category;
        } catch (Exception e) {
            menu = 0;
        }
        try {
            submenu = Group;
        } catch (Exception e) {
            submenu = 0;
        }
        try {
            subsubmenu = subGroup;
        } catch (Exception e) {
            subsubmenu = 0;
        }
        List<Product> list12 = pdao.getProductsList(menu, submenu, subsubmenu, "", "", "", "", 0, "", "", itemcode, BarCode, filter);
        return list12;
    }

         @RequestMapping(value = "/UploadSingleProductDetail", method = RequestMethod.POST)
    public @ResponseBody 
    String UploadSingleProductDetail(@RequestParam("img1") MultipartFile image1, @RequestParam("img2") MultipartFile image2, @RequestParam("img3") MultipartFile image3,
            @RequestParam("img4") MultipartFile image4, @RequestParam("img5") MultipartFile image5, @RequestParam("Category") int Category,
            @RequestParam("submenu") int submenu,@RequestParam("subsubmenu") int subsubmenu,@RequestParam("product") String product,
            @RequestParam("colour") String colour,@RequestParam("style") String style,@RequestParam("brand") String brand,@RequestParam("modalno") String modalno,
            @RequestParam("applicationmodal") String applicationmodal,@RequestParam("applicablemfd") String applicablemfd,@RequestParam("mfd") String mfd,@RequestParam("expdateapplicable") String expdateapplicable,
                    @RequestParam("expdate") String expdate,@RequestParam("place") String place,@RequestParam("hsn") String hsn
            ,@RequestParam("itemcode") String itemcode,@RequestParam("barcode") String barcode,@RequestParam("minqty") int minqty,@RequestParam("size") int size,
            @RequestParam("Rate") double Rate,@RequestParam("DEFdate") String DEFdate,@RequestParam("content") String content, HttpSession session)
            throws IOException {
        String value = "";
        boolean status = pdao.saveSingleProductDetail(image1, image2, image3, image4, image5, Category,submenu,subsubmenu,product,style,brand,colour,modalno,applicationmodal,applicablemfd,mfd,expdateapplicable,expdate,place,hsn,itemcode,barcode,minqty,size,Rate,DEFdate,content,session.getAttribute("username").toString());

        if (status == true) {
            value = "true";
            System.err.println("value=" + value);
        } else {
            value = "false";
        }
        return value;
    }

    @RequestMapping(value = "/UploadProductImagesMultiple", method = RequestMethod.POST)
    public @ResponseBody
    String productMultipleUpload(@RequestParam("image1") MultipartFile image1, @RequestParam("image2") MultipartFile image2, @RequestParam("image3") MultipartFile image3,
            @RequestParam("image4") MultipartFile image4, @RequestParam("video") MultipartFile image5, @RequestParam("masteritems") String items[],@RequestParam("content") String content, HttpSession session)
            throws IOException {
        String value = "";
        if(items.length>0){
        boolean status = pdao.UploadProductImagesMultiple(image1, image2, image3, image4, image5, items, session.getAttribute("username").toString(),content);

        if (status == true) {
            value = "true";
            System.err.println("value=" + value);
        } else {
            value = "false";
        }
        }else{value="No Product Selected";}
        return value;
    }

    
     @RequestMapping(value = "/UpdateSingleProductDetail", method = RequestMethod.POST)
    public @ResponseBody
    String UpdateSingleProductDetail(@RequestParam("product") int product, @RequestParam("content") String content, HttpSession session)
            throws IOException {
        String value = "";
        boolean status = pdao.UpdateSingleProduct(product, content, session.getAttribute("username").toString());

        if (status == true) {
            value = "true";
            System.err.println("value=" + value);
        } else {
            value = "false";
        }
        return value;
    }
    @RequestMapping(value = "/UpdateProductImages", method = RequestMethod.POST)
    public @ResponseBody
    String productUpdateUpload(@RequestParam("img1") MultipartFile image1, @RequestParam("img2") MultipartFile image2, @RequestParam("img3") MultipartFile image3,
            @RequestParam("img4") MultipartFile image4, @RequestParam("vid") MultipartFile image5, @RequestParam("modelproductsno") int items, HttpSession session)
            throws IOException {
        String value = "";
        boolean status = pdao.UpdateProductImages(image1, image2, image3, image4, image5, items, session.getAttribute("username").toString());

        if (status == true) {
            value = "true";
            System.err.println("value=" + value);
        } else {
            value = "false";
        }
        return value;
    }

    @RequestMapping("/newSingleproduct")
    public String newSingleproductPage(Model m) {
        List<Menu> mlist = mdao.getMenuList();
        m.addAttribute("menulists", mlist);
        List<Menu> smlist = mdao.getSubMenuList();
        m.addAttribute("submenulist", smlist);
        List<Menu> ssmlist = mdao.getSubSubMenuList1();
        m.addAttribute("subsubmenulist", ssmlist);
        List<Product> pitemlist1 = pdao.getDistinctProduct("itemcode");
        List<Product> pbarlist1 = pdao.getDistinctProduct("barcode");
        m.addAttribute("productitemcode", pitemlist1);
        m.addAttribute("productbarcode", pbarlist1);
        m.addAttribute("msg", "Create Single Master Product");
        return "NewSingleProduct";
    }
    
  @RequestMapping(value = "/SubSubMenubyProduct/{ssmenu}", method = RequestMethod.POST, produces = APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    List<Product> SubMenubysubsubmenu(@PathVariable int ssmenu) throws JSONException {
        List<Product> list = pdao.getDistinctProductsList(0, 0, ssmenu, "", "", "", "", 0, "", "", "", "", "");
        return list;
    }
     @RequestMapping(value = "/ProductDetail/{psno}", method = RequestMethod.POST, produces = APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Product ProductDetail(@PathVariable int psno) throws JSONException {
        Product list = pdao.getProductsDetail(psno);
//                m.addAttribute("msg", "Create Single Master Product");

        return list;
    }
}
