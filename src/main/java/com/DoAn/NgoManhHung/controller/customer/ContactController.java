package com.devpro.NgoManhHungFECuoiKhoa.controller.customer;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.devpro.NgoManhHungFECuoiKhoa.model.Categories;
import com.devpro.NgoManhHungFECuoiKhoa.model.Contact;
import com.devpro.NgoManhHungFECuoiKhoa.model.Products;
import com.devpro.NgoManhHungFECuoiKhoa.controller.Base_Controller;
import com.devpro.NgoManhHungFECuoiKhoa.services.ContactService;
import com.devpro.NgoManhHungFECuoiKhoa.services.categoriesService;

@Controller
public class ContactController extends Base_Controller {

	// Sử dụng @Autowired để Inject(Tiêm) một Bean vào trong Bean khác.	
	@Autowired
	private ContactService contactService;
	@Autowired
	private categoriesService CategoriesService;
	
	@RequestMapping(value = { "/contact-us" }, method = RequestMethod.GET)
	public String contactUs(final Model model, 
			   				  final HttpServletRequest request, 
			   				  final HttpServletResponse response) {
		
		// lấy contact với id = 7 và hiển thị lên.
//		Contact contact = contactService.getEntityByNativeSQL("select * from tbl_contact where id = 7");
		
		model.addAttribute("contact", new Contact());
		return "customer/contact";
	}
	/**
	 * cách 1: cách đơn giản
	 * Khi 2 Requests trùng nhau nhưng khác Method thì vẫn OK.
	 */
//	@RequestMapping(value = { "/contact-us" }, method = RequestMethod.POST)
//	public String contactUsPost(final Model model, 
//			   				  final HttpServletRequest request, 
//			   				  final HttpServletResponse response) {
//		// Chú ý: đối với cách 1 đọc dữ liệu trong thẻ form qua HttpServletRequest
//		// cần sử dụng Name của html tag
//		String fullName = request.getParameter("fullName");
//		String email = request.getParameter("email");
//		System.out.println(fullName + " - " + email);
		
//		return "customer/contact";		
//	}
	
	/**
	 * cách 2: sử dụng spring-form
	 */
	@RequestMapping(value = { "/contact-us" }, method = RequestMethod.POST)
	public String post_spring_form(final Model model, 
								   final HttpServletRequest request,
							   	   final HttpServletResponse response, 
							   	   final @ModelAttribute("contact") Contact contact,
							   	   final @RequestParam("attachment") MultipartFile attachmentFile) throws IOException {
		contactService.saveOrUpdate(contact);
		// lưu ảnh lên server nếu có upload file.
		if(!attachmentFile.isEmpty()) {
			attachmentFile.transferTo(new File("C:/upload/" + attachmentFile.getOriginalFilename()));
		}
		model.addAttribute("message", "Cảm ơn bạn đã liên hệ");
		model.addAttribute("contact", new Contact());
		return "customer/contact";
	}
	
	/**
	 * cách 3: sử dụng ajax
	 */
//	@RequestMapping(value = { "/ajax/contact-us" }, method = RequestMethod.POST)
//	public ResponseEntity<Map<String, Object>> ajax_contact(final Model model, 
//															final HttpServletRequest request,
//															final HttpServletResponse response, 
//															final @RequestBody Contact contact) {
//		System.out.println(contact.getEmail());
//		System.out.println(contact.getMessage());
		
//		Map<String, Object> jsonResult = new HashMap<String, Object>();
//		jsonResult.put("statusCode", 200); // status code ví dụ: 200: Success, 500: Error, 404: NotFound
//		jsonResult.put("statusMessage", "Cảm ơn bạn " + contact.getEmail() + ", Chúng tôi sẽ liên hệ sớm nhất!");
//		return ResponseEntity.ok(jsonResult);
//	}
	
}
