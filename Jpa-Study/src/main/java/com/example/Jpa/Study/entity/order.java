package com.example.Jpa.Study.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class order {	
	@Column(name = "id" )
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	

	@ManyToOne(cascade = CascadeType.ALL , fetch =FetchType.EAGER , optional = false)
	@JoinColumn(name = "user_id" )
	private users user;
	
	@Column(name = "createTime" )
	private Date createTime;

//	@OneToMany(targetEntity = ordersDetail.class)
//	private Set<ordersDetail> setOrdersDetail = new HashSet<ordersDetail>();
}
