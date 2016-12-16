package com.gzq.shop.cart.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


public class Cart {
	//�������
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
	
	//1.����������ӵ����ﳵ
	public void addCart(CartItem cartItem){
		//���ж��Ƿ���ڹ�����
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
	//2.�ӹ��ﳵ�Ƴ�������
	public void removeCart(Integer pid){
		CartItem cartItem=map.remove(pid);
		total -= cartItem.getSubtotal();
	}
	//3.��չ��ﳵ
	
	public void ClearCart(){
		map.clear();
		total=0;
	}
	
}
