package com.gzq.shop.test;

import com.gzq.shop.product.dao.ProductDao;

public class Test {
	public static void main(String[] args) {
		ProductDao productDao=new ProductDao();
		System.out.println("productDao.findCountCid(1):"+productDao.findCountCid(1));
	}
}