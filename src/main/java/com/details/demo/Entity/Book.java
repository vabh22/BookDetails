 package com.details.demo.Entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="bookProperties")
public class Book {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	public String author;
	public String name;
	public int price;
	public Book() {
		super();
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", name=" + name + ", price=" + price + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	

}
