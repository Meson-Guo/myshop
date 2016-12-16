package com.gzq.shop.category.adminaction;

import java.util.List;

import com.gzq.shop.category.service.CategoryService;
import com.gzq.shop.category.vo.Category;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{

	
	private Category category=new Category();
	private CategoryService categoryService;
	
	public String findAll(){
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	public String edit(){
		category=categoryService.findByCid(category.getCid());
		return "editSuccess";
	}
	
	public String update(){
		categoryService.update(category);
		
		return "updateSuccess";
	} 
	
	public String save(){
		categoryService.save(category);
		
		return "saveSuccess";
	}
	
	
	public String  delete(){
		category=categoryService.findByCid(category.getCid());
		categoryService.delete(category);
		
		return "deleteSuccess";
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}

}
