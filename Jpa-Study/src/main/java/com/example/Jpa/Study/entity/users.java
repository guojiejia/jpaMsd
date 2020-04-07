package com.example.Jpa.Study.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class users {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "birthday")
	private String birthday;

	@Column(name = "sex")
	private String sex;

	@Column(name = "adress")
	private String adress;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Set<order> listOrders ;
}
