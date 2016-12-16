package com.gzq.shop.index.action;

import java.util.List;

import com.gzq.shop.category.service.CategoryService;
import com.gzq.shop.category.vo.Category;
import com.gzq.shop.product.service.ProductService;
import com.gzq.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/***
 * 
 * ������ҳindex
 * @author gzq
 *
 */
public class IndexAction  extends ActionSupport{
	/**
	 * 
	 */
	public CategoryService categoryService;
	public ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	private static final long serialVersionUID = 1L;

	public String execute(){
		System.out.println("tiaozhuan");
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getSession().put("cList", cList);
		//��ѯ������Ʒ
		List<Product> hList=productService.findHot();
		ActionContext.getContext().getValueStack().set("hList", hList);
		//��ѯ���µ���Ʒ
		List<Product> nList=productService.findNew();
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
}
