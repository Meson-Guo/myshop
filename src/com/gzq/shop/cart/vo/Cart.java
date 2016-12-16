package com.gzq.shop.cart.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


public class Cart {
	//购物项集合
	private Map<Integer, CartItem> map=new LinkedHashMap<Integer, CartItem>();
	private double total;
	public Collection<CartItem> getCartItems (){
		return map.values();
	}
	public Map<Integer, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<Integer, CartItem> map) {
		this.map = map;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	//1.将购物项添加到购物车
	public void addCart(CartItem cartItem){
		//先判断是否存在购物项
		Integer pid =cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			CartItem cartItem2=map.get(pid);
			int count1=cartItem2.getCount()+cartItem.getCount();
			cartItem2.setCount(count1);
			map.put(pid, cartItem2);
			//total +=cartItem.getSubTotal();
		}else{
			map.put(pid, cartItem);
			//total +=cartItem.getSubTotal();
		}
		total +=cartItem.getSubtotal();
	}
	//2.从购物车移除购物项
	public void removeCart(Integer pid){
		CartItem cartItem=map.remove(pid);
		total -= cartItem.getSubtotal();
	}
	//3.清空购物车
	
	public void ClearCart(){
		map.clear();
		total=0;
	}
	
}
