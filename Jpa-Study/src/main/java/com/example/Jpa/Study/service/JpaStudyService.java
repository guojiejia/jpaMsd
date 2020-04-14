package com.example.Jpa.Study.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Jpa.Study.dao.itemDao;
import com.example.Jpa.Study.dao.orderDao;
import com.example.Jpa.Study.dao.orderDetailDao;
import com.example.Jpa.Study.dao.userDao;
import com.example.Jpa.Study.entity.item;
import com.example.Jpa.Study.entity.order;
import com.example.Jpa.Study.entity.ordersDetail;
import com.example.Jpa.Study.entity.users;

import dto.itemForm;

@Service
public class JpaStudyService {

	@Autowired
	private orderDetailDao orderDetailDao;
	
	@Autowired
	private userDao userDao;
	
	@Autowired
	private orderDao orderDao;
	
	@Autowired
	private itemDao itemDao;
	
	public List<ordersDetail> findDetails (Integer userId){
		List<ordersDetail> listOrdersDetail = new ArrayList<ordersDetail>();
		Optional<users> user = userDao.findById(userId);
		Set<order> listOrder = user.get().getListOrders();
		for (order item : listOrder) {
			listOrdersDetail.addAll(item.getSetOrdersDetail());
		}
		return listOrdersDetail;		
	}
	
	public Set<order> findOrders (Integer userId){
		Set<order> listOrdersDetail = new HashSet<order>();
		Optional<users> user = userDao.findById(userId);
		listOrdersDetail = user.get().getListOrders();
		return listOrdersDetail;		
	}
	
	public Set<ordersDetail> editOrdersInfo (Integer OrderId){		
		Optional<order> Order = orderDao.findById(OrderId);
		if(Order == null) {
			return  null ;
		}
		return Order.get().getSetOrdersDetail();		
	}
	
	public Optional<ordersDetail> toEdit (Integer OrderDetailId){
		return orderDetailDao.findById(OrderDetailId);
	}
	
	public List<String> itemSelect (){
		List<String> itemSelectList = new ArrayList<String>();
		List<item> itemList = itemDao.findAll();
		for (item item : itemList) {
			itemSelectList.add(item.getName());
		}
		return itemSelectList;
	}
	
	public Set<ordersDetail> updateOrderList (itemForm itemForm , Integer OrderDetailId , Integer OrderId){
		Optional<order> order = orderDao.findById(OrderId);
		for (ordersDetail ordersDetail : order.get().getSetOrdersDetail()) {
			if(ordersDetail.getId() == OrderDetailId) {
				item item = itemDao.findOneByName(itemForm.getItemName());
				ordersDetail.setItem(item);
				ordersDetail.setItemNum(itemForm.getItemNum());
			}
		}
		orderDao.saveAndFlush(order.get());
		return  order.get().getSetOrdersDetail();
	}
	

}
