package com.gzq.shop.user.adminaction;

import com.gzq.shop.user.service.UserService;
import com.gzq.shop.user.vo.User;
import com.gzq.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAdminAction extends ActionSupport implements ModelDriven<User>{
		User user=new User();
		private UserService userService;
		private Integer page;
		public Integer getPage() {
			return page;
		}

		public void setPage(Integer page) {
			this.page = page;
		}

		public String  findAll(){
			PageBean<User> pageBean=userService.findByPage(page);
			ActionContext.getContext().getValueStack().set("pageBean", pageBean);
			return "findAll";
		}
		
		public String edit(){
			user=userService.findByUid(user.getUid());
			
			return "editSuccess";
		}
	public String  update(){
		userService.update(user);
		return "updateSuccess";
	}
	
	public String delete(){
		
	user=userService.findByUid(user.getUid());
	userService.delete(user);
		return "deleteSuccess";
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	

}
