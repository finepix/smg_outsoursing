package com.smg.base;

import java.io.Serializable;
import java.util.List;
/**
 * 基础服务类。向数据库进行操作
 * 抽象类，不能被实例化
 */
public abstract class BaseServiceImpl implements BaseService{

	@Override
	public List getResult(String hql, Object[] parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object findById(Class clazz, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getResultByPage(String hql, Object[] parameters, int pageNow,
			int PageSize) {
		return null;
	}

	@Override
	public int queryPageCount(String hql, Object[] parameters, int pageSize) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delById(Class clazz, Serializable id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object uniqueQuery(String hql, Object[] parameters) {
		// TODO Auto-generated method stub
		return null;
	}

}
