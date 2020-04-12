package com.example.Jpa.Study.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Jpa.Study.entity.order;
import com.example.Jpa.Study.entity.ordersDetail;
import com.example.Jpa.Study.param.addOrderDetailParam;
import com.example.Jpa.Study.service.JpaStudyService;

import dto.itemForm;

@Controller
public class JpaStudyController {
	@Autowired
	private JpaStudyService  jpaStudyService;
	
	@RequestMapping("/")
	public String index() {return "redirect:OrdersDetail/order";}
	
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
	
	@RequestMapping("OrdersDetail/order")
	public String findOrdersInfo(Model model , @RequestParam(value = "userId" , defaultValue = "1") Integer userId){
		Set<order> findOrders = jpaStudyService.findOrders(userId);
		model.addAttribute("Orders",findOrders);
		model.addAttribute("userId",userId);
		return "OrdersDetail/order";
	}
	
	@RequestMapping("toDetialEdit")
	public String editOrdersInfo(Model model , @RequestParam(value = "OrderId" , defaultValue = "1") Integer OrderId){
		Set<ordersDetail> editOrdersInfo = jpaStudyService.editOrdersInfo(OrderId);
		model.addAttribute("OrdersDetails",editOrdersInfo);
		return "OrdersDetail/detailList";
	}
	
	@RequestMapping("toEdit")
	public String editOrdersDetailInfo(Model model , @RequestParam(value = "OrderDetailId" , defaultValue = "1") Integer OrderDetailId){
		ordersDetail edit = jpaStudyService.toEdit(OrderDetailId).get();
		List<String> itemSelect = jpaStudyService.itemSelect();
		model.addAttribute("OrderDetailToEdit", edit);		
		model.addAttribute("itemSelect", itemSelect);
		model.addAttribute("itemForm", new itemForm());
		return "OrdersDetail/EditOrderDetail";
	}
	@RequestMapping("/SaveEdit")
	public String saveOrdersDetailInfo(@ModelAttribute(value="itemForm") itemForm item){
		System.out.println(item.getItemName());
		System.out.println(item.getItemNum());
		return "OrdersDetail/order";
	}
} 
