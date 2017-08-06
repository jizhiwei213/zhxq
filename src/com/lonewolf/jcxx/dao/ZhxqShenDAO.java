package com.lonewolf.jcxx.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import com.base.dao.BaseDAO;
import com.lonewolf.jcxx.model.ZhxqShenModel;
import com.lonewolf.jcxx.query.ZhxqShenShiquQuery;

public class ZhxqShenDAO extends BaseDAO {

	public List<ZhxqShenModel> getZhxqShenList(Integer start, Integer rows,ZhxqShenShiquQuery zhxqShenQuery) {
		String hql ="from ZhxqShenModel u where 1=1 ";
		if(null!=zhxqShenQuery)
		{
			if(null!=zhxqShenQuery.getShenNameQ()&&!"".equals(zhxqShenQuery.getShenNameQ()))
			{
				hql+=" and u.name like '%"+zhxqShenQuery.getShenNameQ()+"%'";
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

	public int getZhxqShenCount(ZhxqShenShiquQuery zhxqShenQuery) {
		String hql ="select count(*) from ZhxqShenModel u where 1=1 ";
		if(null!=zhxqShenQuery)
		{
			if(null!=zhxqShenQuery.getShenNameQ()&&!"".equals(zhxqShenQuery.getShenNameQ()))
			{
				hql+=" and u.name like '%"+zhxqShenQuery.getShenNameQ()+"%'";
			}
		}
		hql+=" order by u.name desc";
		Query query=getSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult()+"");
	}

	public ZhxqShenModel getZhxqShenModelById(String id) {
		return getHibernateTemplate().get(ZhxqShenModel.class, id);
	}

	public void saveZhxqShen(ZhxqShenModel zhxqShenModel) {
		zhxqShenModel.setInsertTime(new Date());
		zhxqShenModel.setUpdateTime(new Date());
		getHibernateTemplate().save(zhxqShenModel);
	}

	public void updateZhxqShen(ZhxqShenModel zhxqShenModel) {
		zhxqShenModel.setUpdateTime(new Date());
		getHibernateTemplate().update(zhxqShenModel);
	}

	public void deleteZhxqShen(String id) {
		String hql="delete from ZhxqShenModel ddm where ddm.id=:id";
		Query query=getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
	}
}
