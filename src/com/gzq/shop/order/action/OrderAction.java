package com.gzq.shop.order.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;



import com.gzq.shop.cart.vo.Cart;
import com.gzq.shop.cart.vo.CartItem;
import com.gzq.shop.order.service.OrderService;
import com.gzq.shop.order.vo.Order;
import com.gzq.shop.order.vo.OrderItem;
import com.gzq.shop.user.vo.User;
import com.gzq.shop.utils.PageBean;
import com.gzq.shop.utils.PaymentUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	Order order =new Order();
	 
	private OrderService orderService;
	private Integer page;
	private String pd_FrpId;
	private String r3_Amt;
	private String r6_Order;
	private Integer oid;
	

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getPage() {
		return page;
	}

	public String getPd_FrpId() {
		return pd_FrpId;
	}

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	public String getR3_Amt() {
		return r3_Amt;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public String getR6_Order() {
		return r6_Order;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	//提交订单
	public String  saveOrder(){
		Cart cart= (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			this.addActionMessage("a亲！你还没有购物哦");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		order.setOrdertime(new Date());
		order.setState(1);//1, 未付款  2，已经付款 ，未发货 3， 已经发货 未确认收货 4， 完成交易
		User existUser =(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser == null){
			this.addActionMessage("请先登录");
			return "login";
		}
		order.setUser(existUser);
		for(CartItem cartItem : cart.getCartItems()){
			OrderItem  orderItem=new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setOrder(order);
			order.getOrderItems().add(orderItem);
		}
		orderService.save(order);
		cart.ClearCart();
		//显示订单 通过值栈的方式
		return "saveSuccess";
	}
	
	public String  findByUid(){
		User existuser=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		Integer uid=existuser.getUid();
		PageBean<Order> pageBean= orderService.findByUid(uid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUid";
	}
	public String findByOid(){
		order=orderService.findByOid(order.getOid());
		return "findByOid";
	}
	
	public String payOrder() throws IOException{
		Order currentOrder=orderService.findByOid(order.getOid());
		currentOrder.setAddr(order.getAddr());
		currentOrder.setName(order.getName());
		currentOrder.setPhone(order.getPhone());
		orderService.updateOrder(currentOrder);
		this.addActionMessage("付款成功");
		String  p0_Cmd="Buy";
		String  p1_MerId="10001126856";
		String  p2_Order=order.getOid().toString();
		String  p3_Amt="0.01";
		String  p4_Cur="CNY";
		String  p5_Pid="";
		String  p6_Pcat="";
		String  p7_Pdesc="";
		String  p8_Url="http://localhost:8080/myshop/order_callBack.action";
		String  p9_SAF="";
		String pa_MP = ""; // 商户扩展信息:
		String pd_FrpId = this.pd_FrpId;// 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// 向易宝发送请求:
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return NONE;
	}
	
	public String callBack(){
		// 修改订单的状态:
		Order currentOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		// 修改订单状态为2:已经付款:
		currentOrder.setState(2);
		orderService.updateOrder(currentOrder);
		this.addActionMessage("支付成功!订单编号为: "+r6_Order +" 付款金额为: "+r3_Amt);
		return "msg";
	}
	
	// 修改订单的状态:
	public String updateState(){
		Order currentOrder = orderService.findByOid(order.getOid());
		currentOrder.setState(4);
		orderService.updateOrder(currentOrder);
		return "updateStateSuccess";
	}
	
	public String  removeOrder(){
		System.out.println("removeOrder");
		Integer uid=order.getUser().getUid();
		Order curOrder=orderService.findByOid(oid);
		System.out.println("caur:"+curOrder.getName());
		orderService.delete(curOrder);
		PageBean<Order> pageBean= orderService.findByUid(uid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		System.out.println("oid:"+order.getOid());
		return "removeOrder";
	}
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	public OrderService getOrderService() {
		return orderService;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	private Order getSessionOrder() {
		// TODO Auto-generated method stub
		Order exorder =(Order) ServletActionContext.getRequest().getSession().getAttribute("order");
		if(exorder ==null){
			exorder= new Order();
			 ServletActionContext.getRequest().getSession().setAttribute("order", exorder);
		}
		return exorder;
	}
}
