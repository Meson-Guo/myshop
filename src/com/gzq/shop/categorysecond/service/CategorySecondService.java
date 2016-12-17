package com.gzq.shop.categorysecond.service;


import java.util.List;

import com.gzq.shop.categorysecond.dao.CategorySecondDao;
import com.gzq.shop.categorysecond.vo.CategorySecond;
import com.gzq.shop.utils.PageBean;

public class CategorySecondService {
	private CategorySecondDao categorySecondDao;
	
	public CategorySecondDao getCategorySecondDao() {
		return categorySecondDao;
	}

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	public PageBean<CategorySecond> findByPage(Integer page) {
		// TODO Auto-generated method stub
		PageBean<CategorySecond> pageBean=new PageBean<CategorySecond>();
		pageBean.setPage(page);
		int pageSize=8;
		pageBean.setPageSize(pageSize);
		int totalCount=0;
		totalCount=categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		int totalPage=(int) Math.ceil(totalCount / pageSize);
		pageBean.setTotalPage(totalPage);
		int startIndex=(page-1)*pageSize;
		List<CategorySecond> list=categorySecondDao.findByPage(startIndex,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	public CategorySecond findByCsid(Integer csid) {
		// TODO Auto-generated method stub
		return categorySecondDao.findByCsid(csid);
	}

	public void update(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.update(categorySecond);
	}

	public void delete(CategorySecond curcategorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.delete(curcategorySecond);
	}

	public void save(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.save(categorySecond);
	}

	public List<CategorySecond> findAll() {
		// TODO Auto-generated method stub
		return categorySecondDao.findAll();
	}

}
