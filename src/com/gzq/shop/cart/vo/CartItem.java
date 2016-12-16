package com.gzq.shop.cart.vo;

import com.gzq.shop.product.vo.Product;

/****
 * ¹ºÎïÏî
 * @author gzq
 *
 */
public class CartItem {
	private Product product;
	private int count;
	private double subtotal;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public double getSubtotal() {
		return count*product.getShop_price();
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
