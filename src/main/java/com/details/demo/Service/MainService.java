package com.details.demo.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.details.demo.Entity.Book;
import com.details.demo.Repository.BookRepo;

@Service
public class MainService {

	@Autowired
	private BookRepo br;
	public void add(Book book)
	{
		br.save(book);
	}
	public List<Book> getAllBook()
	{
		List<Book> ob=new ArrayList<Book>();
		br.findAll().forEach(book1->ob.add(book1));
		return ob;
	}
	public String update(Book book, int id)
	{
		br.save(book);
		return "updated";
	}
	public String delete( int id)
	{
		br.deleteById(id);
		return"Deleted";
	}
	
	// Pageable
	public Page<Book> getAllValues(int pageNumber , int pageSize)
	{
		Pageable pageable=PageRequest.of(pageNumber, pageSize);
		Page<Book> pageBook=this.br.findAll(pageable);
		return pageBook;
	}
	
	//sorting 
	public List<Book>findBookwithSorting (String field)
	{
		//return br.findAll(Sort.by(field));
		return br.findAll(Sort.by(Sort.Direction.ASC,field));
	}
	//sorting with pagination 
	public Page<Book> getAllValuesSorting(int pageNumber , int pageSize , String sortBy)
	{
		Pageable pageable=PageRequest.of(pageNumber, pageSize , Sort.by(sortBy).descending());
		Page<Book> pageBook=this.br.findAll(pageable);
		return pageBook;
	}
	//searching 
	public Page<Book>findAll(Optional<String> key)
	{
		Pageable pageable = PageRequest.of(0, 5);
		Page<Book> findAll = br.findAlls(key.get(),pageable);
		return findAll;
				
	}
	public Page<Book>sortingsearchingpagination(int pageNumber, int pageSize  ,String sortBy ,Optional<String> key)
	{
		
		Pageable pageable =PageRequest.of(pageNumber, pageSize , Sort.by(sortBy).ascending());
		Page<Book> findAllValue = br.findValues(key.get(), pageable);
		return  findAllValue;
	}
}
