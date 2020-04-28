package com.example.Jpa.Study.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Jpa.Study.dao.itemDao;
import com.example.Jpa.Study.dao.orderDao;
import com.example.Jpa.Study.dao.orderDetailDao;
import com.example.Jpa.Study.dao.userDao;
import com.example.Jpa.Study.entity.item;
import com.example.Jpa.Study.entity.order;
import com.example.Jpa.Study.entity.ordersDetail;
import com.example.Jpa.Study.entity.users;

import dto.OrderAndOrderDetailDto;
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
	
	/**
	 * 查user
	 */
	public List<users> findUsers() {
		List<users> userList = userDao.findAll();
		return userList;
	}
	
	/**
	 * 一个user对应的所有订单
	 */
	public List<order> findOrdersByUserId(Integer userId) {
		List<order> orderList = orderDao.findAll(OrdersSpecification.getOrderSpecification(userId));
		if(orderList.isEmpty()) {
			System.out.println("orderList with userId " + userId.toString() + "not found !");
		}
		return orderList;
	}
	
	/**
	 * 查询所有订单（不要订单明细）
	 */
	public List<order> findAllOrder() {
		List<order> orderList = orderDao.findAll();
//		List<order> orderList = orderDao.findAll(OrdersSpecification.getAllOrderWithoudOrderDetailSpecification());
//		if(orderList.isEmpty()) {
//			System.out.println("orderList is empty !");
//		}
		return orderList;
	}
	
	/**
	 * 所有订单（不要订单明细） 原生sql查询
	 */
	public List<order> findAllOrdersByNativeQuery() {
		List<order> orderList = orderDao.findAllOrdersByNativeQuery();
		if(orderList.isEmpty()) {
			System.out.println("orderList is empty !");
		}
		return orderList;
	}
	
	/**
	 * 一个user对应的所有订单与订单明细
	 */
	public List<OrderAndOrderDetailDto> findAllOrdersWithOrderDetailsWithUserId(Integer userId) {
		List<Object> objectList = orderDao.findAllOrdersWithOrderDetailsWithUserId(userId);
		if(objectList.isEmpty()) {
			System.out.println("objectList is empty !");
		}
		List<OrderAndOrderDetailDto> orderAndOrderDetailDto = new ArrayList<OrderAndOrderDetailDto>();
		for (int i = 0; i < objectList.size(); i++) {
			OrderAndOrderDetailDto dtoList = new OrderAndOrderDetailDto();
			Object[] obj = (Object[])objectList.get(i);
			dtoList.setOId((Integer)obj[0]);
			dtoList.setOCreateTime((Timestamp)obj[1]);
			dtoList.setOPrice((Float)obj[2]);
			dtoList.setOUserId((Integer)obj[3]);
			dtoList.setOdId((Integer)obj[4]);
			dtoList.setOdItemNum((Integer)obj[5]);
			dtoList.setOdVersion((BigInteger)obj[6]);
			dtoList.setOdItemId((Integer)obj[7]);
			dtoList.setOdOrderId((Integer)obj[8]);
			orderAndOrderDetailDto.add(dtoList);
		}
		
		return orderAndOrderDetailDto;
	}
	
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
