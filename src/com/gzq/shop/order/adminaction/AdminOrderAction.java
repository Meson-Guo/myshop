package com.gzq.shop.order.adminaction;

import java.util.List;

import com.gzq.shop.order.service.OrderService;
import com.gzq.shop.order.vo.Order;
import com.gzq.shop.order.vo.OrderItem;
import com.gzq.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{
	
	private Order order=new Order();
	private OrderService orderService;
	private Integer page;
	 


	public String findAll(){
		
		PageBean<Order> pageBean=orderService.findByPage(page);
		//System.out.println("pagebean:"+pageBean.getTotalPage()+" TotalCount:"+pageBean.getTotalCount());
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll"; 
	 }
	
	public String findOrderItem(){
		List<OrderItem> list=orderService.findByOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	
	public String updateState(){
		Order curorder=orderService.findByOid(order.getOid());
		curorder.setState(3);
		orderService.updateOrder(curorder);
		return "updateState";
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}

}
