package com.example.Jpa.Study.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.Jpa.Study.entity.order;
import com.example.Jpa.Study.entity.ordersDetail;

@Repository
public interface orderDetailDao extends JpaRepository<ordersDetail,Integer>  , JpaSpecificationExecutor<ordersDetail> {
	public List<ordersDetail> findAllByOrder (Optional<order> order );
}
