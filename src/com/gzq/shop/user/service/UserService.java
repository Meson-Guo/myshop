package com.gzq.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import com.gzq.shop.user.dao.UserDao;
import com.gzq.shop.user.vo.User;
import com.gzq.shop.utils.MailUtils;
import com.gzq.shop.utils.UUIDUtils;

/****
 * 用户模块业务层代码
 * @author gzq
 *
 */
@Transactional
public class UserService {
	//注入userDao
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//按用户查询
	public User findByUserName(String username){
		return userDao.findByUserName(username);
	}
	public void save(User user){
		user.setState(0);
		String  code=UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		MailUtils.sendMail(user.getEmail(), code);
	}
	public User findByCode(String code){
		return userDao.findByCode(code);
	}
	public void update(User existUser){
		userDao.update(existUser);
	}

	public User login(User user) {
		// TODO Auto-generated method stub
		System.out.println("userservice:"+user.getUsername());
		return userDao.login(user);
	}
}
