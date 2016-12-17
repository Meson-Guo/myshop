package com.gzq.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gzq.shop.order.vo.Order;
import com.gzq.shop.order.vo.OrderItem;
import com.gzq.shop.product.vo.Product;
import com.gzq.shop.utils.PageHibernateCallback;

public class OrderDao extends HibernateDaoSupport{

	public void save(Order order) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(order);
	}

	public int findCountUid(Integer uid) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Order o where o.user.uid=?";
		List<Long> list=this.getHibernateTemplate().find(hql, uid);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Order> findByPageUid(Integer uid, int begin, int limit) {
		// TODO Auto-generated method stub
		String hql="from Order order where order.user.uid=? order by order.ordertime desc"; 
		List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, begin, limit));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

	public Order findByOid(Integer oid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	public void updateOrder(Order currentOrder) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(currentOrder);
	}

	

	public void delete(Order order) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(order);
	}

	public OrderItem findByItemId(Integer itemid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(OrderItem.class, itemid);
	}

	public int findCount() {
		// TODO Auto-generated method stub
		String hql="select count(*) from Order";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Order> findByPage(int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		String  hql="from Order order by oid desc";
		List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null, startIndex, pageSize));
		return list;
	}

	public List<OrderItem> findByOrderItem(Integer oid) {
		// TODO Auto-generated method stub
		String hql="from OrderItem o where o.order.oid=? order by o.itemid desc ";
		List<OrderItem> list=this.getHibernateTemplate().find(hql, oid);
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

	

}
