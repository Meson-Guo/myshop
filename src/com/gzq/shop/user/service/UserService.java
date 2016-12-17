package com.gzq.shop.user.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gzq.shop.user.dao.UserDao;
import com.gzq.shop.user.vo.User;
import com.gzq.shop.utils.MailUtils;
import com.gzq.shop.utils.PageBean;
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

	

	public PageBean<User> findByPage(Integer page) {
		// TODO Auto-generated method stub
		PageBean<User> pageBean =new PageBean<User>();
		pageBean.setPage(page);
		int pageSize=8;
		pageBean.setPageSize(pageSize);
		int totalCount=0;
		totalCount=userDao.findCount();
		System.out.println("totalCount:"+totalCount);
		pageBean.setTotalCount(totalCount);
		int totalPage=0;
		if((totalCount % pageSize)==0){
			totalPage=totalCount / pageSize;
		}else{
			totalPage=totalCount / pageSize +1;
		}
		pageBean.setTotalPage(totalPage);
		int startIndex=0;
		startIndex=(page-1)*pageSize;
		List<User> list=userDao.findByPage(startIndex,pageSize);
		System.out.println("lst:::"+list.size());
		pageBean.setList(list);
		return pageBean;
	}

	public User findByUid(Integer uid) {
		// TODO Auto-generated method stub
		return userDao.findByUid(uid);
	}

	public void delete(User user) {
		// TODO Auto-generated method stub
		userDao.delete(user);
	}
}
