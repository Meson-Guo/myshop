package com.gzq.shop.product.action;

import java.util.List;

import com.gzq.shop.category.service.CategoryService;
import com.gzq.shop.category.vo.Category;
import com.gzq.shop.product.service.ProductService;
import com.gzq.shop.product.vo.Product;
import com.gzq.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product=new Product();
	public ProductService productService;
	//接受一个一级分类的Cid
	private Integer cid;
	public CategoryService categoryService;
	private int page;
	private Integer csid;
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public CategoryService getCategoryService() {
		return categoryService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public String findByPid(){
		//调用service方法
		product =productService.findById(product.getPid());
		return "findByPid";
	}
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	public String findByCid(){
		//List<Category> cList=categoryService.findAll();
		//也可在session获得
		System.out.println("1213");
		PageBean<Product> pageBean=productService.findByPageCid(cid,page);
		System.out.println("ahhahah");
		//强PageBean存放在值站中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	public String findByCsid(){
		PageBean<Product> pageBean=productService.findByPageCsid(csid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
