package com.gzq.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gzq.shop.categorysecond.vo.CategorySecond;
import com.gzq.shop.utils.PageHibernateCallback;

public class CategorySecondDao extends HibernateDaoSupport {

	public int findCount() {
		// TODO Auto-generated method stub
		String hql="select count(*) from CategorySecond";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<CategorySecond> findByPage(int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		String hql="from CategorySecond  cs order by cs.csid desc";
		List<CategorySecond> list=this.getHibernateTemplate().
				execute(new PageHibernateCallback<CategorySecond>(hql, new Object[]{}, startIndex, pageSize));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

	public CategorySecond findByCsid(Integer csid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	public void update(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(categorySecond);
	}

	public void delete(CategorySecond curcategorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(curcategorySecond);
	}

	public void save(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(categorySecond);
	}

	public List<CategorySecond> findAll() {
		// TODO Auto-generated method stub
		String  hql="from CategorySecond ";
		List<CategorySecond> list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

}
