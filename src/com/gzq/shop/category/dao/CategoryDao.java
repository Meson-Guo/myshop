package com.gzq.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gzq.shop.category.vo.Category;

public class CategoryDao extends HibernateDaoSupport{
	public Category findByCname(String  cname){
		String hql="from Category where cname = ?";
		List<Category> list=this.getHibernateTemplate().find(hql, cname);
		if(list!=null&&list.size()!=0){
			return list.get(0);
		}
		return null;
	}
	public void save(Category category){
		this.getHibernateTemplate().save(category);
		
	}
	public void delete(Category category){
		this.getHibernateTemplate().delete(category);
	}
	public void update(Category category){
		this.getHibernateTemplate().update(category);
	}
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		String  hql="from Category";
		List<Category> list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()!=0){
			return list;
		}
		return null;
	}
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Category.class,cid);
	}
}
