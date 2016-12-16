package com.gzq.shop.categorysecond.adminaction;

import java.util.List;

import com.gzq.shop.category.service.CategoryService;
import com.gzq.shop.category.vo.Category;
import com.gzq.shop.categorysecond.service.CategorySecondService;
import com.gzq.shop.categorysecond.vo.CategorySecond;
import com.gzq.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	private CategorySecond categorySecond=new CategorySecond();
	
	
	private CategorySecondService categorySecondService;
	private Integer page;
	private CategoryService categoryService;
	
	public String  findAll(){
		PageBean<CategorySecond> pageBean=categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllSuccess";
	}
	
	public String edit(){
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "edit";
	}
	
	public String update(){
		categorySecondService.update(categorySecond);
		return "update";
	}
	
	public String  delete(){
		CategorySecond curcategorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(curcategorySecond);
		
		return "delete";
	}
	
	public String addPage(){
		categorySecondService.save(categorySecond);
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPage";
	}
	
	public String save(){
		categorySecondService.save(categorySecond);
		return "save";
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}
	
	

}
