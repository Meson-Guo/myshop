package com.gzq.shop.categorysecond.vo;

import java.util.HashSet;
import java.util.Set;

import com.gzq.shop.category.vo.Category;
import com.gzq.shop.product.vo.Product;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class CategorySecond {
	private Integer csid;
	private String  csname;
	public Category category;
	public Set<Product> products=new HashSet<Product>();
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
}
