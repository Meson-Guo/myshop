package com.gzq.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gzq.shop.user.vo.User;

/****
 * 用户持久层
 * @author gzq
 *
 */
public class UserDao extends HibernateDaoSupport{
	//按名字查询是否有该用户
	public User findByUserName(String username){
		String hql="from User where username=?";
		List<User> list=this.getHibernateTemplate().find(hql,username);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	public void save(User user){
		this.getHibernateTemplate().save(user);
		
	}
	
	public User findByCode(String code){
		String hql="from User where code = ?";
		List<User> list=this.getHibernateTemplate().find(hql, code);
		if(list!=null&&list.size()!=0){
			return list.get(0);
		}
		return null;
		
	}
	public void update(User existUser){
		this.getHibernateTemplate().update(existUser);
		
	}
	public User login(User user) {
		// TODO Auto-generated method stub
		String hql="from User where username=? and password=? and state=?";
		List<User> list=this.getHibernateTemplate().find(hql, user.getUsername(),user.getPassword(),1);
		System.out.println("user:"+list.get(0).getUsername());
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
