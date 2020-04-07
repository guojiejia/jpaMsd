package com.example.Jpa.Study;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Jpa.Study.dao.itemDao;
import com.example.Jpa.Study.dao.orderDao;
import com.example.Jpa.Study.dao.orderDetailDao;
import com.example.Jpa.Study.dao.userDao;
import com.example.Jpa.Study.entity.item;
import com.example.Jpa.Study.entity.order;
import com.example.Jpa.Study.entity.ordersDetail;
import com.example.Jpa.Study.entity.users;
import com.example.Jpa.Study.service.JpaStudyService;


@SpringBootTest(classes = JpaStudyApplication.class)
class JpaStudyApplicationTests {
	
	@Autowired
	private userDao userdao;
	@Autowired
	private itemDao itemDao;
	@Autowired
	private orderDetailDao orderDetailDao;
	
	@Autowired
	private JpaStudyService jpaStudyServiceTest;
	
	@Autowired
	private orderDao ordersDao;
	
	public void saveOrderDetail() {
		users x = new users();
		x.setUsername("wangwu");
		x.setSex("nan");
		x.setAdress("dalian");
		x.setBirthday("1993-02-02");
		userdao.save(x);
		
		item item1 = new item();
		item1.setName("茄子");
		item1.setDetail("非常好吃！！");
		item1.setPrice("50");
		item1.setCreateTime(new Date());
		itemDao.save(item1);
		
		item item2 = new item();
		item2.setName("西瓜");
		item2.setDetail("非常好吃！！");
		item2.setPrice("500");
		item2.setCreateTime(new Date());
		itemDao.save(item2);
		
		order newOrder = new order();
		newOrder.setCreateTime(new Date());
		newOrder.setUser(x);
		ordersDao.save(newOrder);
		
		ordersDetail newOrdersDetail1 = new ordersDetail();
		newOrdersDetail1.setItem(item1);
		newOrdersDetail1.setOrder(newOrder);
		newOrdersDetail1.setItemNum(5);
		ordersDetail newOrdersDetail2 = new ordersDetail();
		newOrdersDetail2.setItem(item2);
		newOrdersDetail2.setOrder(newOrder);
		newOrdersDetail2.setItemNum(5);
		
		orderDetailDao.save(newOrdersDetail1);
		orderDetailDao.save(newOrdersDetail2);
	}
	
	@Test
	public void findAllOrderDetail() {
		Optional<order> newOrder = ordersDao.findById(1);		
		List<ordersDetail> findAllByOrder = orderDetailDao.findAllByOrder(newOrder);
		for(ordersDetail item : findAllByOrder) {
			System.out.println(item.getItem());
		}
		
	}
	
	
	public void testService () {
		List<ordersDetail> findDetails = jpaStudyServiceTest.findDetails(1);
		for(ordersDetail item : findDetails) {
			System.out.println(item.getItem());
		}
	}

//	@Test
//	public void findItemInfo() {
//		List<item> findAll = itemDao.findAll();		
//		System.out.println(findAll.toArray());
//	}
}
