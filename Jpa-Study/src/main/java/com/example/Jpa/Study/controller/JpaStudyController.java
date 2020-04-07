package com.example.Jpa.Study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Jpa.Study.entity.ordersDetail;
import com.example.Jpa.Study.param.addOrderDetailParam;
import com.example.Jpa.Study.service.JpaStudyService;

@Controller
public class JpaStudyController {
	@Autowired
	private JpaStudyService  jpaStudyService;
	
	@RequestMapping("/")
	public String index() {return "redirect:OrdersDetail/list";}
	
	@RequestMapping("OrdersDetail/list")
	public String findOrdersDetailInfo(Model model , @RequestParam(value = "userId" , defaultValue = "1") Integer userId){
		List<ordersDetail> findDetails = jpaStudyService.findDetails(userId);
		model.addAttribute("OrdersDetails",findDetails);
		return "OrdersDetail/list";
	}
	
	@RequestMapping("toEdit")
	public String editOrdersDetailInfo(Model model , @RequestParam(value = "userId" , defaultValue = "1") Integer userId){
		System.out.println(userId);
		return "OrdersDetail/list";
	}
	
	@RequestMapping("toAdd")
	public  String AddOrdersDetailInfo() {
		return "OrdersDetail/AddOrderList";
	}
	
	@RequestMapping("Add")
	public  String Add( addOrderDetailParam addOrderDetailParam ,BindingResult result , ModelMap model) {
		String errorMsg="";
		if(result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for(ObjectError error : list) {
				errorMsg = errorMsg + error.getCode()+":" + error.getDefaultMessage();
			}
			model.addAttribute("errorMsg", errorMsg);
		}
		return "OrdersDetail/AddOrderList";
	}
} 
