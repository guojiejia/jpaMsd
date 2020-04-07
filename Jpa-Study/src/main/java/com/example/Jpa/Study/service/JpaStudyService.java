package com.example.Jpa.Study.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Jpa.Study.dao.orderDao;
import com.example.Jpa.Study.dao.orderDetailDao;
import com.example.Jpa.Study.dao.userDao;
import com.example.Jpa.Study.entity.order;
import com.example.Jpa.Study.entity.ordersDetail;
import com.example.Jpa.Study.entity.users;

@Service
public class JpaStudyService {

	@Autowired
	private orderDetailDao orderDetailDao;
	
	@Autowired
	private userDao userDao;
	
	@Autowired
	private orderDao orderDao;
	
	public List<ordersDetail> findDetails (Integer userId){
		List<ordersDetail> listOrdersDetail = new ArrayList<ordersDetail>();
		Optional<users> user = userDao.findById(userId);
		List<Optional<order>> listOrder = orderDao.findAllByUser(user);
		for (Optional<order> item : listOrder) {
			listOrdersDetail.addAll(orderDetailDao.findAllByOrder(item));
		}
		return listOrdersDetail;		
	}
	
//	public List<ordersDetail> editoOrderDetails(Integer userId){
//		
//		return null;
//	}
	

}
