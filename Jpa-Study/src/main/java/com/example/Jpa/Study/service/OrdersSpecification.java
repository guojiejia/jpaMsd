package com.example.Jpa.Study.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.example.Jpa.Study.entity.order;

public class OrdersSpecification {
	static public Specification<order> getOrderSpecification(Integer userId) {
		Specification<order> specification = new Specification<order>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> pridicateList = new ArrayList<>();
				pridicateList.add(cb.equal(root.get("id").as(Integer.class), userId));
//		            Join<order, ordersDetail> join = root.join("order_id", JoinType.LEFT);
//		            list.add(criteriaBuilder.like(join.get("name").as(String.class), "%" + appleName + "%"));
				Predicate[] pre = new Predicate[pridicateList.size()];
				pre = pridicateList.toArray(pre);
				return query.where(pre).getRestriction();
			}
		};
		return specification;
	}
}
