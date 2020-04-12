package com.example.Jpa.Study.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="ordersDetail")
public class ordersDetail {
	@Column(name = "id" )
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  id;
	
	@OneToOne(targetEntity = item.class)
	@JoinColumn(name = "item_id")
	private item item;
	
	@Column(name="item_num")
	private int itemNum;
	
	@ManyToOne(targetEntity = order.class , cascade = CascadeType.ALL , fetch = FetchType.LAZY ,optional = true)
	@JoinColumn(name = "order_id" )
	private order order;
}
