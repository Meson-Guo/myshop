package com.gzq.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gzq.shop.category.dao.CategoryDao;
import com.gzq.shop.category.vo.Category;
@Transactional
public class CategoryService {
	public CategoryDao categoryDao;

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public List<Category> findAll() {
		// TODO Auto-generated method stub
		
		return categoryDao.findAll();
	}

	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return categoryDao.findByCid(cid);
	}

	public void update(Category category) {
		// TODO Auto-generated method stub
		categoryDao.update(category);
	}

	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryDao.save(category);
	}

	public void delete(Category category) {
		// TODO Auto-generated method stub
		categoryDao.delete(category);
	}
	
}
