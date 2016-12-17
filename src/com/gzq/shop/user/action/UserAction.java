package com.gzq.shop.user.action;



import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.gzq.shop.user.service.UserService;
import com.gzq.shop.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	// 模型驱动使用的对象
		private User user = new User();
		private UserService userService;
		public String checkCode;
		
	public String registPage(){
		System.out.println("zhuce");
		return "registPage";
	}
	
	public String findByName() throws IOException{
		// 调用userservice进行查询
		User exisUser=userService.findByUserName(user.getUsername());
		//获得response对象
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if(exisUser!=null){
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		}else{
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return null;
	}
	
	
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	public String regist(){
		String  checkCode1=(String) ServletActionContext.getRequest().
				getSession().getAttribute("checkCode");
				System.out.println("checkCode1:"+checkCode1);
		/*if(checkCode.equalsIgnoreCase(checkCode1)){
			this.addActionError("验证码错误");
			return "checkcodeFail";
		}*/
		userService.save(user);
	this.addActionMessage("注册成功，请去邮箱激活");
		return "msg";
	}
	
	public String active(){
		User existUser=userService.findByCode(user.getCode());
		if(existUser==null){
			this.addActionMessage("激活失败");
		}
		existUser.setState(1);
		existUser.setCode(null);
		userService.update(existUser);
		this.addActionMessage("激活成功");
		return "msg";
	}
	
	public String loginPage(){
		return "loginPage";
	}
	
	public String login(){
		User exitUser =userService.login(user);
		
		if(exitUser == null){
			this.addActionError("登录失败：用户名或密码不正确或者未激活");
			return LOGIN;
		}
		//将用户信息存入session
		ServletActionContext.getRequest().getSession().
			setAttribute("existUser", exitUser);
		System.out.println("login success");
		return "loginSuccess";
	}
	
	public String quit(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}