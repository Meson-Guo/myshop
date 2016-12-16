package com.gzq.shop.category.vo;

import java.util.HashSet;
import java.util.Set;

import com.gzq.shop.categorysecond.vo.CategorySecond;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class Category {
	private Integer cid;
	private String cname;
	private Set<CategorySecond> categorySeconds =new HashSet<CategorySecond>();
	public Integer getCid() {
		return cid;
	}
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
}
