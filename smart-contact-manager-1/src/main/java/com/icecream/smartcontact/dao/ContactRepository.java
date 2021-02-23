package com.icecream.smartcontact.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.icecream.smartcontact.entities.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {
	
	@Query("from Contact as c where c.user.userId =:userId ")
	public Page<Contact> findContactsByUserId(@Param("userId")int userId, Pageable pageable);
}
