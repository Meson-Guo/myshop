package com.gzq.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.gzq.shop.adminuser.service.AdminUserService;
import com.gzq.shop.adminuser.vo.AdminUser;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{
	AdminUser adminUser=new AdminUser();
	
	private AdminUserService adminUserService;
	
	
	
	public String login(){
		
		AdminUser existAdminUser=adminUserService.login(adminUser);
		if(existAdminUser == null){
			this.addActionError("请还没有注册。或者密码，用户名错误");
			return "loginFail";
		}else{
			ServletActionContext.getRequest().getSession().
							setAttribute("existAdminUser", existAdminUser);
			
			return "loginSuccess";
		}
		
	}
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return adminUser;
	}
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

}
