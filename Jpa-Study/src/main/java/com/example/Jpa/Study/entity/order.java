package com.example.Jpa.Study.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
//@Data
@Table(name = "orders")
public class order {	
	@Column(name = "id" )
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public users getUser() {
		return user;
	}

	public void setUser(users user) {
		this.user = user;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Set<ordersDetail> getSetOrdersDetail() {
		return setOrdersDetail;
	}

	public void setSetOrdersDetail(Set<ordersDetail> setOrdersDetail) {
		this.setOrdersDetail = setOrdersDetail;
	}

	@ManyToOne(cascade = CascadeType.ALL , fetch =FetchType.EAGER , optional = false)
	@JoinColumn(name = "user_id" )
	private users user;
	
	@Column(name = "createTime" )
	private Date createTime;
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Column(name = "price" )
	private float price;
	
	@Column(name = "version" )
	private long version;

	@OneToMany( cascade = CascadeType.ALL  , fetch = FetchType.EAGER  ,mappedBy = "order")
	private Set<ordersDetail> setOrdersDetail = new HashSet<>();
}
