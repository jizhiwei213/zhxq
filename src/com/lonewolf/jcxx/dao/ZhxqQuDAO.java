package com.lonewolf.jcxx.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import com.base.dao.BaseDAO;
import com.lonewolf.jcxx.model.ZhxqQuModel;
import com.lonewolf.jcxx.query.ZhxqShenShiquQuery;

public class ZhxqQuDAO extends BaseDAO {

	public List<ZhxqQuModel> getZhxqQuList(Integer start, Integer rows,ZhxqShenShiquQuery zhxqQuQuery) {
		String hql ="from ZhxqQuModel u where 1=1 ";
		if(null!=zhxqQuQuery)
		{
			if(null!=zhxqQuQuery.getQuNameQ()&&!"".equals(zhxqQuQuery.getQuNameQ()))
			{
				hql+=" and u.name like '%"+zhxqQuQuery.getQuNameQ()+"%'";
			}
		}
		hql+=" order by u.name desc";
		Query query=getSession().createQuery(hql);
		if(start!=null&&rows!=null){
			query.setFirstResult(start);
			query.setMaxResults(rows);
		}
		return query.list();
	}

	public int getZhxqQuCount(ZhxqShenShiquQuery zhxqQuQuery) {
		String hql ="select count(*) from ZhxqQuModel u where 1=1 ";
		if(null!=zhxqQuQuery)
		{
			if(null!=zhxqQuQuery.getQuNameQ()&&!"".equals(zhxqQuQuery.getQuNameQ()))
			{
				hql+=" and u.name like '%"+zhxqQuQuery.getQuNameQ()+"%'";
			}
		}
		hql+=" order by u.name desc";
		Query query=getSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult()+"");
	}

	public ZhxqQuModel getZhxqQuModelById(String id) {
		return getHibernateTemplate().get(ZhxqQuModel.class, id);
	}

	public void saveZhxqQu(ZhxqQuModel zhxqQuModel) {
		zhxqQuModel.setInsertTime(new Date());
		zhxqQuModel.setUpdateTime(new Date());
		getHibernateTemplate().save(zhxqQuModel);
	}

	public void updateZhxqQu(ZhxqQuModel zhxqQuModel) {
		zhxqQuModel.setUpdateTime(new Date());
		getHibernateTemplate().update(zhxqQuModel);
	}

	public void deleteZhxqQu(String id) {
		String hql="delete from ZhxqQuModel ddm where ddm.id=:id";
		Query query=getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	
	public List<ZhxqQuModel> getZhxqQuByShenIdList(String shenId) {
		String hql ="from ZhxqQuModel u where 1=1 ";
		if(null!=shenId&&!"".equals(shenId))
		{
			hql+=" and u.shen.id='"+shenId+"' ";
		}
		hql+=" order by u.name desc";
		Query query=getSession().createQuery(hql);
		return query.list();
	}
	
	public List<ZhxqQuModel> getZhxqQuByShiIdList(String shiId) {
		String hql ="from ZhxqQuModel u where 1=1 ";
		if(null!=shiId&&!"".equals(shiId))
		{
			hql+=" and u.shi.id='"+shiId+"' ";
		}
		hql+=" order by u.name desc";
		Query query=getSession().createQuery(hql);
		return query.list();
	}
}
