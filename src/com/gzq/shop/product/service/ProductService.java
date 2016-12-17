package com.gzq.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gzq.shop.product.dao.ProductDao;
import com.gzq.shop.product.vo.Product;
import com.gzq.shop.utils.PageBean;
@Transactional
public class ProductService {
	public ProductDao productDao;

	public List<Product> findHot() {
		// TODO Auto-generated method stub
		return productDao.findHot();
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Product> findNew() {
		// TODO Auto-generated method stub
		return productDao.findNew();
	}

	public Product findById(Integer pid) {
		// TODO Auto-generated method stub
		return productDao.findById(pid);
	}

	public PageBean<Product> findByPageCid(Integer cid, int page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean=new PageBean<Product>();
		pageBean.setPage(page);
		int pageSize=8;
		pageBean.setPageSize(pageSize);
		int totalCount=71;
		System.out.println("****gzq***");
		totalCount=productDao.findCountCid(cid);
		System.out.println("totalCount:"+totalCount);
		pageBean.setTotalCount(totalCount);
		int totalPage=0;
		totalPage=(int) Math.ceil(totalCount / pageSize);
		pageBean.setTotalPage(totalPage);
		int startIndex=(page-1)*pageSize;
		System.out.println("****123***");
		List<Product> list=productDao.findByPageCid(cid,startIndex,pageSize);
		System.out.println("****zsy***");
		pageBean.setList(list);
		return pageBean;
	}
//根据二级分类查询商品
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean=new PageBean<Product>();
		pageBean.setPage(page);
		int pageSize=8;
		pageBean.setPageSize(pageSize);
		int totalCount=0;
		System.out.println("****gzq***");
		totalCount=productDao.findCountCsid(csid);
		System.out.println("totalCount:"+totalCount);
		pageBean.setTotalCount(totalCount);
		int totalPage=0;
		totalPage=(int) Math.ceil(totalCount / pageSize);
		pageBean.setTotalPage(totalPage);
		int startIndex=(page-1)*pageSize;
		System.out.println("****123***");
		List<Product> list=productDao.findByPageCsid(csid,startIndex,pageSize);
		System.out.println("****zsy***");
		pageBean.setList(list);
		return pageBean;

	}

	public PageBean<Product> findByPage(Integer page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean=new PageBean<Product>();
		pageBean.setPage(page);
		int pageSize=8;
		pageBean.setPageSize(pageSize);
		int totalCount=0;
		totalCount=productDao.findCount();
		pageBean.setTotalCount(totalCount);
		int totalPage=0;
		if((totalCount%pageSize)==0){
			totalPage=totalCount / pageSize;
		}else{
			totalPage=totalCount / pageSize +1;
		}
		pageBean.setTotalPage(totalPage);
		int startIndex=0;
		startIndex=(page-1)*pageSize;
		List<Product> list=productDao.findByPage( startIndex, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	public void update(Product product) {
		// TODO Auto-generated method stub
		productDao.update(product);
	}

	public void delete(Product curProduct) {
		// TODO Auto-generated method stub
		productDao.delete(curProduct);
	}

	public void save(Product product) {
		// TODO Auto-generated method stub
		productDao.save(product);
	}
}
