package com.gzq.shop.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import com.gzq.shop.adminuser.dao.AdminUserDao;
import com.gzq.shop.adminuser.vo.AdminUser;
@Transactional
public class AdminUserService {
	private AdminUserDao adminUserDao;
	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
	public AdminUser login(AdminUser adminUser) {
		// TODO Auto-generated method stub
		return adminUserDao.login(adminUser);
	}
}
