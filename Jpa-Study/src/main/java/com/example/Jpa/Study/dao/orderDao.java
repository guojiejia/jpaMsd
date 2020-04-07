package com.example.Jpa.Study.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.Jpa.Study.entity.order;
import com.example.Jpa.Study.entity.users;

@Repository
public interface orderDao extends JpaRepository<order,Integer>  , JpaSpecificationExecutor<order> {
	public List<Optional<order>> findAllByUser (Optional<users> user);
}
