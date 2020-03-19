package com.gengjiaxin.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.gengjiaxin.domain.User;

public interface UserRepository extends ElasticsearchRepository<User, Integer>{

	List<User> findByName(String name);
	
	List<User> findByAddress(String address);
	
	List<User> findByAddressOrName(String address,String name);
	
	List<User> findByAddressAndName(String address,String name);
	
	List<User> findByIdLessThan(Integer id);
}
