package com.example.Jpa.Study;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Jpa.Study.dao.itemDao;
import com.example.Jpa.Study.dao.orderDao;
import com.example.Jpa.Study.dao.orderDetailDao;
import com.example.Jpa.Study.dao.userDao;
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
