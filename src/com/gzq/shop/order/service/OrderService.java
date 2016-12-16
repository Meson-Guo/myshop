package com.gzq.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gzq.shop.order.dao.OrderDao;
import com.gzq.shop.order.vo.Order;
import com.gzq.shop.order.vo.OrderItem;
import com.gzq.shop.product.vo.Product;
import com.gzq.shop.utils.PageBean;
@Transactional
public class OrderService {
	private OrderDao orderDao;

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void save(Order order) {
		// TODO Auto-generated method stub
		orderDao.save(order);
	}

	public PageBean<Order> findByUid(Integer uid, Integer page) {
		// TODO Auto-generated method stub
		PageBean<Order> pageBean=new PageBean<Order>();
		pageBean.setPage(page);
		int limit=8;
		pageBean.setLimit(limit);
		int totalCount=0;
	
		totalCount=orderDao.findCountUid(uid);
		System.out.println("totalCount:"+totalCount);
		pageBean.setTotalCount(totalCount);
		int totalPage=0;
		totalPage=(int) Math.ceil(totalCount / limit);
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		
		List<Order> list=orderDao.findByPageUid(uid,begin,limit);
		
		pageBean.setList(list);
		return pageBean;
	}

	public Order findByOid(Integer oid) {
		// TODO Auto-generated method stub
		
		return orderDao.findByOid(oid);
	}

	public void updateOrder(Order currentOrder) {
		// TODO Auto-generated method stub
		orderDao.updateOrder(currentOrder);
	}

	

	public void delete(Order order) {
		// TODO Auto-generated method stub
		orderDao.delete(order);
	}

	public OrderItem findByItemId(Integer itemid) {
		// TODO Auto-generated method stub
		return orderDao.findByItemId(itemid);
	}

	public PageBean<Order> findByPage(Integer page) {
		// TODO Auto-generated method stub
		PageBean<Order> pageBean=new PageBean<Order>();
		pageBean.setPage(page);
		int limit=8;
		pageBean.setLimit(limit);
		int totalCount=0;
		totalCount=orderDao.findCount();
		pageBean.setTotalCount(totalCount);
		int totalPage=0;
		if((totalCount%limit)==0){
		totalPage=(totalCount / limit)+1;
		}else{
			totalPage=(totalCount / limit);
		}
		pageBean.setTotalPage(totalPage);
		int begin=0;
		begin=(page-1)*limit;
		List<Order> list=orderDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public List<OrderItem> findByOrderItem(Integer oid) {
		// TODO Auto-generated method stub
		return orderDao.findByOrderItem(oid);
	}

	
}
