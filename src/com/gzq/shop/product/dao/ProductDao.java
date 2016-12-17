package com.gzq.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gzq.shop.product.vo.Product;
import com.gzq.shop.utils.PageHibernateCallback;
import com.opensymphony.xwork2.ActionContext;

public class ProductDao extends HibernateDaoSupport{

	public List<Product> findHot() {
		// TODO Auto-generated method stub
		//使用离线条件查询
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("is_hot", 1));
		//倒叙排序输出
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list=this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	public List<Product> findNew() {
		// TODO Auto-generated method stub
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list=this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	public Product findById(Integer pid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	public int findCountCid(Integer cid) {
		// TODO Auto-generated method stub
		String hql=" select count(*) from Product  p where p.categorySecond.category.cid = ?";
		System.out.println("list:begin "+cid);
		List<Long> list=this.getHibernateTemplate().find(hql,cid);
		System.out.println("list:"+list);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPageCid(Integer cid, int startIndex, int pageSize) {
		// select p.* from category c, product p, categorysecond cs where p.csid = cs.csid and cs.cid = c.cid and c.cid=1;
		//String hqlString="select p from Category c,Product p,CategorySecond cs where c.cid=cs.category.cid and cs.csid=categorySecond.csid and c.cid=?";
		String hql="select p from Product p join p.categorySecond cs join cs.category c where c.cid =?";
		//分页的第二种方式，
		 List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, startIndex, pageSize));
		 if(list!=null&&list.size()>0){
			 return list;
		 }
		return null;
	}

	public int findCountCsid(Integer csid) {
		// TODO Auto-generated method stub
		String  hql="select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list=this.getHibernateTemplate().find(hql, csid);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPageCsid(Integer csid, int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		String hql="select p from Product p join p.categorySecond cs where cs.csid=? ";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, startIndex, pageSize));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

	public int findCount() {
		// TODO Auto-generated method stub
		String hql="select count(*) from Product";
		List<Long>  list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPage(int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		String hql="from Product order by pid desc";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, startIndex, pageSize));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

	public void update(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(product);
	}

	public void delete(Product curProduct) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(curProduct);
	}

	public void save(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(product);
	}
	
	

}
