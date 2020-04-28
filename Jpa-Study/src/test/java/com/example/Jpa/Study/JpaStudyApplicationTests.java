package com.example.Jpa.Study;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Jpa.Study.controller.JpaStudyController;
import com.example.Jpa.Study.dao.itemDao;
import com.example.Jpa.Study.dao.orderDao;
import com.example.Jpa.Study.dao.orderDetailDao;
import com.example.Jpa.Study.dao.userDao;
import com.example.Jpa.Study.entity.item;
import com.example.Jpa.Study.entity.order;
import com.example.Jpa.Study.entity.ordersDetail;
import com.example.Jpa.Study.entity.users;
import com.example.Jpa.Study.service.JpaStudyService;

import dto.OrderAndOrderDetailDto;


@SpringBootTest(classes = JpaStudyApplication.class)
class JpaStudyApplicationTests {

	@Autowired
	private userDao userdao;
	@Autowired
	private itemDao itemDao;
	@Autowired
	private orderDetailDao orderDetailDao;
	@Autowired
	private orderDao ordersDao;
	@Autowired
	private JpaStudyService jpaStudyServiceTest;
	@Autowired
	private JpaStudyController jpaStudyController;

	/**
	 * 查user
	 */
	@Test void findUserInfo() {
		List<users> us = jpaStudyController.getUsers();
		System.out.println("us: " + us.toString());
	}

	/**
	 * 一个user对应的所有订单
	 */
	@Test void findOrdersByUserId() {
		List<order> orderlist = jpaStudyController.findOrdersByUserId(1);
		System.out.println("orderlist: " + orderlist.toString());
	}

	/**
	 * 所有订单（不要订单明细） 
	 */
	@Test void findAllOrders() {
		List<order> orderlist = jpaStudyController.findAllOrder();
		System.out.println("orderlist: " + orderlist.toString());
	}

	/**
	 * 所有订单（不要订单明细） 原生sql查询
	 */
	@Test void findAllOrdersByNativeQuery() {
		List<order> orderlist = jpaStudyController.findAllOrdersByNativeQuery();
		System.out.println("orderlist: " + orderlist.toString());
	}
	
	/**
	 * 一个user对应的所有订单与订单明细
	 */
	@Test void findAllOrdersWithOrderDetailsWithUserId() {
		List<OrderAndOrderDetailDto> orderlist = jpaStudyController.findAllOrdersWithOrderDetailsWithUserId(1);
		System.out.println("orderlist: " + orderlist.toString());
	}
	
	@BeforeEach
	public void setup() {
//		this.ordersDao.deleteAll();
//		this.userdao.deleteAll();
//		this.orderDetailDao.deleteAll();
//		this.itemDao.deleteAll();
	}

	@Test
	public void storeUserData() {
		item i = new item();
		i.setDetail("pc比较笨重，但是可以diy");
		i.setId(1);
		i.setPrice("2666");
		i.setName("PC");
		i.setCreateTime(new Date());
		itemDao.save(i);

		item i1 = new item();
		i1.setDetail("book比较贵，但是很轻便");
		i1.setId(2);
		i1.setPrice("4666");
		i1.setName("BOOK");
		i1.setCreateTime(new Date());
		itemDao.save(i1);

		ordersDetail od = new ordersDetail();
		od.setId(1);
		od.setItemNum(2);
		od.setItem(i);
		orderDetailDao.save(od);

		ordersDetail od1 = new ordersDetail();
		od1.setId(2);
		od1.setItemNum(1);
		od1.setItem(i1);
		orderDetailDao.save(od1);

		users u = new users();
		u.setAdress("辽宁省 大连市");
		u.setBirthday("2020/04/19");
		u.setId(1);
		u.setSex("男");
		u.setUsername("Kevin");
		userdao.save(u);

		order o = new order();
		o.setId(1);
		o.setPrice(2666*2);
		o.setCreateTime(new Date());
		Set<ordersDetail> odObj = new HashSet<ordersDetail>();
		odObj.add(od);
		odObj.add(od1);
		o.setSetOrdersDetail(odObj);
		o.setUser(u);
		ordersDao.save(o);
	}

	@Test
	public void findAllOrderDetail() {
		Optional<order> newOrder = ordersDao.findById(1);		
		List<ordersDetail> findAllByOrder = orderDetailDao.findAllByOrder(newOrder);
		for(ordersDetail item : findAllByOrder) {
			System.out.println(item.getItem());
		}

	}

	@Test
	public void testService () {
		List<ordersDetail> findDetails = jpaStudyServiceTest.findDetails(1);
		for(ordersDetail item : findDetails) {
			System.out.println(item.getItem());
		}
	}

	@Test
	public void findOrder() {
		Optional<users> findById = userdao.findById(1);
		List<Optional<order>> findAllByUser = ordersDao.findAllByUser(findById);
		for (Optional<order> optional : findAllByUser) {
			Set<ordersDetail> setOrdersDetail = optional.get().getSetOrdersDetail();
			System.out.println(setOrdersDetail.size());
		}
		System.out.println(findAllByUser.size());
	}

	//	@Test
	//	public void findItemInfo() {
	//		List<item> findAll = itemDao.findAll();		
	//		System.out.println(findAll.toArray());
	//	}
}
