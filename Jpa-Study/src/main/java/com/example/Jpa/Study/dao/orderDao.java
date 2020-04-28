package com.example.Jpa.Study.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Jpa.Study.entity.order;
import com.example.Jpa.Study.entity.users;

@Repository
public interface orderDao extends JpaRepository<order,Integer>  , JpaSpecificationExecutor<order> {
	public List<Optional<order>> findAllByUser (Optional<users> user);
	public List<order> findOrdersByUserId(Integer userId);
	
	/**
	 * 所有订单（不要订单明细） 原生sql查询
	 */
	@Query(value="select * from orders", nativeQuery = true)
	public List<order> findAllOrdersByNativeQuery();
	
	/**
	 * 一个user对应的所有订单与订单明细
	 */
	@Query(value="SELECT orders.id oId, orders.create_time oCreateTime, orders.price oPrice, orders.user_id oUserId, orders_detail.id odId, orders_detail.item_num odItemNum, orders_detail.version odVersion, orders_detail.item_id odItemId, orders_detail.order_id odOrderId\r\n" + 
			"FROM jpa_db.orders \r\n" + 
			"inner join jpa_db.orders_detail\r\n" + 
			"on orders.id = orders_detail.order_id\r\n" + 
			"where orders.user_id =:userId", nativeQuery = true)
	public List<Object> findAllOrdersWithOrderDetailsWithUserId(@Param("userId") Integer userId);
}
