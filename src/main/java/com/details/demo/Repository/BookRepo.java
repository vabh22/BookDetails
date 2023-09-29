package com.details.demo.Repository;

import java.util.Optional;
import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.details.demo.Entity.Book;
@Repository
public interface BookRepo extends JpaRepository<Book, Integer>{
	@Query("select s from Book s where name Like %?1%")
	List<Book>findByname(String name);
	
//	@Query(value="{ $or: [ { 'name' : {$regex:?0,$options:'i'} } ],$and: [ { 'author' : ?1 }],$and: [ { 'price' : ?2 }]}",nativeQuery = true)
	@Query("SELECT bookObj FROM Book bookObj WHERE CONCAT(bookObj.name,bookObj.author,bookObj.price) LIKE %?1%")
	public Page<Book>findAlls(String key,Pageable pageable);
	
	@Query("SELECT bookObj FROM Book bookObj WHERE CONCAT(bookObj.name,bookObj.author,bookObj.price) LIKE %?1%")
	 public Page<Book> findValues(String string, Pageable pageable);
}
