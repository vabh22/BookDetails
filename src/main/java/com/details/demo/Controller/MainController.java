package com.details.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.details.demo.Entity.Book;
import com.details.demo.Repository.BookRepo;
import com.details.demo.Service.MainService;
import com.details.demo.dto.APIResponse;

//import antlr.collections.List;
import java.util.*;

@RestController
public class MainController {
	@Autowired
	private MainService ms;
	@Autowired 
	private BookRepo br;
	
	@PostMapping("/Add")
	public String addDetails(@RequestBody Book book)
	{
		ms.add(book);
		return"Add Successfully";
	}
	
	@GetMapping("/Read")
	public List<Book> getAllBook() {
		
			return ms.getAllBook();
		
	}
	@PutMapping("/update/{id}")
	public String update(@RequestBody Book book, @PathVariable int id)
	{
		try {
			Optional<Book> ob= br.findById(id);
			if(ob.isPresent()) {
				br.save(book);
				return "updated";
			}else {
				return "Not found";
			}
			
		}catch(Exception e) {
			return e.getMessage();
		}
	
	}
	
	@DeleteMapping("/Delete/{id}")
	public String delete( @PathVariable("id") int id)
	{
		return ms.delete(id);
	}
	
	
	//pagegnation
	@GetMapping("/pageshow")
	public Page<Book> getAllValue(@RequestParam(value= "pageNumber" ,defaultValue="0")Integer pageNumber,
								@RequestParam(value = "pageSize", defaultValue= "10")Integer pageSize){
		return ms.getAllValues(pageNumber, pageSize);
	}
	
	//sorting 
	@GetMapping("/{field}")
	private APIResponse<List<Book>>getBookWithSort(@PathVariable String field)
	{
		List<Book> allBook=ms.findBookwithSorting(field);
		return new APIResponse<>(allBook.size(),allBook);
	}
	
	//sorting with pagination
	@GetMapping("/pagewithsorting")
	public Page<Book> getAllValuesSorting(@RequestParam(value= "pageNumber" ,defaultValue="0")Integer pageNumber,
								@RequestParam(value = "pageSize", defaultValue= "10")Integer pageSize, 
								@RequestParam(value= "sortBy", defaultValue="name")String sortBy)
	{
		return ms.getAllValuesSorting(pageNumber, pageSize,sortBy);
	}
	/*@GetMapping("/Book")
	public Page<Book> findAll(@RequestParam Optional<String> key){
		System.out.println(key.get());
		System.out.println(key);

		Pageable pageable=PageRequest.of(0, 5);
		 Page<Book> findAll = br.findAlls(key.get(),pageable);
		 return findAll;
	}*/
	@GetMapping("/Book")
	public Page<Book> findAll(@RequestParam Optional<String> key)
	{
		return ms.findAll(key);
	}
	
	@GetMapping("/SSP")
	public Page<Book> findAllValue(@RequestParam(value = "pageNumber")Integer pageNumber,
			@RequestParam(value = "pageSize")Integer pageSize,
			@RequestParam(value= "sortBy")String sortBy,
			@RequestParam Optional<String> key)
	{
		return ms.sortingsearchingpagination(pageNumber, pageSize, sortBy, key);
	}

}
