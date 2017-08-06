package com.lonewolf.jcxx.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import com.base.dao.BaseDAO;
import com.lonewolf.jcxx.model.ZhxqWygsModel;
import com.lonewolf.jcxx.query.ZhxqWygsQuery;

public class ZhxqWygsDAO extends BaseDAO {

	public List<ZhxqWygsModel> getZhxqWygsList(Integer start, Integer rows,ZhxqWygsQuery zhxqWygsQuery) {
		String hql ="from ZhxqWygsModel u where 1=1 ";
		if(null!=zhxqWygsQuery)
		{
			if(null!=zhxqWygsQuery.getNameQ()&&!"".equals(zhxqWygsQuery.getNameQ()))
			{
				hql+=" and u.name like '%"+zhxqWygsQuery.getNameQ()+"%'";
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

	public int getZhxqWygsCount(ZhxqWygsQuery zhxqWygsQuery) {
		String hql ="select count(*) from ZhxqWygsModel u where 1=1 ";
		if(null!=zhxqWygsQuery)
		{
			if(null!=zhxqWygsQuery.getNameQ()&&!"".equals(zhxqWygsQuery.getNameQ()))
			{
				hql+=" and u.name like '%"+zhxqWygsQuery.getNameQ()+"%'";
			}
		}
		hql+=" order by u.name desc";
		Query query=getSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult()+"");
	}

	public ZhxqWygsModel getZhxqWygsModelById(String id) {
		return getHibernateTemplate().get(ZhxqWygsModel.class, id);
	}

	public void saveZhxqWygs(ZhxqWygsModel zhxqWygsModel) {
		zhxqWygsModel.setInsertTime(new Date());
		zhxqWygsModel.setUpdateTime(new Date());
		getHibernateTemplate().save(zhxqWygsModel);
	}

	public void updateZhxqWygs(ZhxqWygsModel zhxqWygsModel) {
		zhxqWygsModel.setUpdateTime(new Date());
		getHibernateTemplate().update(zhxqWygsModel);
	}

	public void deleteZhxqWygs(String id) {
		String hql="delete from ZhxqWygsModel ddm where ddm.id=:id";
		Query query=getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
	}
}
