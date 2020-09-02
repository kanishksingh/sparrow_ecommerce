/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanishk.ecommerce.Controller;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author KANISHK
 */
@Controller
public class FileUploadController {
	
	private String saveDirectory = "C:/Test/Upload/";
	
	@RequestMapping(value="/uploadFile",method = RequestMethod.POST)
	public String handleFileUpload(HttpServletRequest request, 
			@RequestParam CommonsMultipartFile[] fileUpload) throws Exception {
		
		System.out.println("description: " + request.getParameter("description"));
		
		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload){
				
				System.out.println("Saving file: " + aFile.getOriginalFilename());
				
				if (!aFile.getOriginalFilename().equals("")) {
					aFile.transferTo(new File(saveDirectory + aFile.getOriginalFilename()));
				}
			}
		}

		// returns to the view "Result"
		return "Result";
	}
}