package com.gzq.shop.user.action;



import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.gzq.shop.user.service.UserService;
import com.gzq.shop.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	// ģ������ʹ�õĶ���
		private User user = new User();
		private UserService userService;
		public String checkCode;
		
	public String registPage(){
		System.out.println("zhuce");
		return "registPage";
	}
	
	public String findByName() throws IOException{
		// ����userservice���в�ѯ
		User exisUser=userService.findByUserName(user.getUsername());
		//���response����
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if(exisUser!=null){
			response.getWriter().println("<font color='red'>�û����Ѿ�����</font>");
		}else{
			response.getWriter().println("<font color='green'>�û�������ʹ��</font>");
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
			this.addActionError("��֤�����");
			return "checkcodeFail";
		}*/
		userService.save(user);
	this.addActionMessage("ע��ɹ�����ȥ���伤��");
		return "msg";
	}
	
	public String active(){
		User existUser=userService.findByCode(user.getCode());
		if(existUser==null){
			this.addActionMessage("����ʧ��");
		}
		existUser.setState(1);
		existUser.setCode(null);
		userService.update(existUser);
		this.addActionMessage("����ɹ�");
		return "msg";
	}
	
	public String loginPage(){
		return "loginPage";
	}
	
	public String login(){
		User exitUser =userService.login(user);
		
		if(exitUser == null){
			this.addActionError("��¼ʧ�ܣ��û��������벻��ȷ����δ����");
			return LOGIN;
		}
		//���û���Ϣ����session
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