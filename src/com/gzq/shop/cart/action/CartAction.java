package com.gzq.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.gzq.shop.cart.vo.Cart;
import com.gzq.shop.cart.vo.CartItem;
import com.gzq.shop.product.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport {
	public Integer pid;
	public Integer count;
	public ProductService productService;
	public ProductService getProductService() {
		return productService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String  addCart(){
		CartItem cartItem=new CartItem();
		cartItem.setCount(count);
		cartItem.setProduct(productService.findById(pid));
		//购物车应该存在session中,不能直接new 如果直接new的话获得的永远是最后一个cart
		
		Cart cart=getSessionCart();
		cart.addCart(cartItem);
		return "addCart";
	}
	public String  clearCart(){
		Cart cart=getSessionCart();
		cart.ClearCart();
		return "clearCart";
	}
	
	public String removeCart(){
		Cart cart=getSessionCart();
		cart.removeCart(pid);
		return "removeCart";
	}
	
	public String myCart(){
		
		return "myCart";
	}
	private Cart getSessionCart() {
		// TODO Auto-generated method stub
		Cart cart =(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart ==null){
			 cart= new Cart();
			 ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
}
