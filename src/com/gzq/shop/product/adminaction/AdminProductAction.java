package com.gzq.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.gzq.shop.categorysecond.service.CategorySecondService;
import com.gzq.shop.categorysecond.vo.CategorySecond;
import com.gzq.shop.order.vo.Order;
import com.gzq.shop.product.service.ProductService;
import com.gzq.shop.product.vo.Product;
import com.gzq.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product= new Product();
	private ProductService productService;
	private Integer page;
	private CategorySecondService categorySecondService;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String findAll(){
		PageBean<Product> pageBean=productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	public String edit(){
		product=productService.findById(product.getPid());
		List<CategorySecond> csList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "edit";
	}
	
	public String  update() throws IOException{
		
		product.setPdate(new Date());
		if(upload !=null){
			String uppath=ServletActionContext.getServletContext().getRealPath("/"+product.getImage());
			File file=new File(uppath);
			file.delete();
			// 获得上传图片的服务器端路径.
			String path=ServletActionContext.getServletContext().getRealPath("/products");
			// 创建文件类型对象:
			File dFile = new File(path + "//" + uploadFileName);
			
			// 文件上传:
			FileUtils.copyFile(upload, dFile);
			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		return "update";
	}
	
	
	public String  delete(){
		product=productService.findById(product.getPid());
		String path = ServletActionContext.getServletContext().getRealPath(
				"/" + product.getImage());
		File file = new File(path);
		file.delete();
		productService.delete(product);
		return "deleteSuccess";
	}
	
	
	public String save() throws IOException{
		
		product.setPdate(new Date());
		if(upload !=null){
			String uppath=ServletActionContext.getServletContext().getRealPath("/"+product.getImage());
			File file=new File(uppath);
			file.delete();
			// 获得上传图片的服务器端路径.
			String path=ServletActionContext.getServletContext().getRealPath("/products");
			// 创建文件类型对象:
			File dFile = new File(path + "//" + uploadFileName);
			
			// 文件上传:
			FileUtils.copyFile(upload, dFile);
			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}
	
	public String  addPage(){
		List<CategorySecond> csList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPage";
	}
	public CategorySecondService getCategorySecondService() {
		return categorySecondService;
	}
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}

}
