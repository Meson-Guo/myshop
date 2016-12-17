package com.gzq.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gzq.shop.user.vo.User;
import com.gzq.shop.utils.PageHibernateCallback;

/****
 * �û��־ò�
 * @author gzq
 *
 */
public class UserDao extends HibernateDaoSupport{
	//�����ֲ�ѯ�Ƿ��и��û�
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
	public int findCount() {
		// TODO Auto-generated method stub
		String  hql="select count(*) from User";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	public List<User> findByPage(int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		String hql="from User";
		List<User> list=this.getHibernateTemplate().execute(new PageHibernateCallback<User>(hql, null, startIndex, pageSize));
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}
	public User findByUid(Integer uid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(User.class, uid);
	}
	public void delete(User user) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(user);
	}
}
