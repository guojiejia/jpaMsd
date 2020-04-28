package com.example.Jpa.Study.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Jpa.Study.entity.item;
@Repository
public interface itemDao extends JpaRepository<item,Integer>  , JpaSpecificationExecutor<item>{

	item findOneByName(String name);

	// TODO
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update goods set price = :price, version = version + 1 where goods_id = :goodsId and version = :version", nativeQuery = true)
	int updateItemsPriceByItemId(@Param("price") String price, @Param("goodsId") Long goodsId, @Param("version") Long version);
}
