package com.lonewolf.jcxx.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import com.base.dao.BaseDAO;
import com.lonewolf.jcxx.model.ZhxqShiModel;
import com.lonewolf.jcxx.query.ZhxqShenShiquQuery;

public class ZhxqShiDAO extends BaseDAO {

	public List<ZhxqShiModel> getZhxqShiList(Integer start, Integer rows,ZhxqShenShiquQuery zhxqShiQuery) {
		String hql ="from ZhxqShiModel u where 1=1 ";
		if(null!=zhxqShiQuery)
		{
			if(null!=zhxqShiQuery.getShiNameQ()&&!"".equals(zhxqShiQuery.getShiNameQ()))
			{
				hql+=" and u.name like '%"+zhxqShiQuery.getShiNameQ()+"%'";
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

	public int getZhxqShiCount(ZhxqShenShiquQuery zhxqShiQuery) {
		String hql ="select count(*) from ZhxqShiModel u where 1=1 ";
		if(null!=zhxqShiQuery)
		{
			if(null!=zhxqShiQuery.getShiNameQ()&&!"".equals(zhxqShiQuery.getShiNameQ()))
			{
				hql+=" and u.name like '%"+zhxqShiQuery.getShiNameQ()+"%'";
			}
		}
		hql+=" order by u.name desc";
		Query query=getSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult()+"");
	}

	public ZhxqShiModel getZhxqShiModelById(String id) {
		return getHibernateTemplate().get(ZhxqShiModel.class, id);
	}

	public void saveZhxqShi(ZhxqShiModel zhxqShiModel) {
		zhxqShiModel.setInsertTime(new Date());
		zhxqShiModel.setUpdateTime(new Date());
		getHibernateTemplate().save(zhxqShiModel);
	}

	public void updateZhxqShi(ZhxqShiModel zhxqShiModel) {
		zhxqShiModel.setUpdateTime(new Date());
		getHibernateTemplate().update(zhxqShiModel);
	}

	public void deleteZhxqShi(String id) {
		String hql="delete from ZhxqShiModel ddm where ddm.id=:id";
		Query query=getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	
	public List<ZhxqShiModel> getZhxqShiByShenIdList(String shenId) {
		String hql ="from ZhxqShiModel u where 1=1 ";
		if(null!=shenId&&!"".equals(shenId))
		{
			hql+=" and u.shen.id='"+shenId+"' ";
		}
		hql+=" order by u.name desc";
		Query query=getSession().createQuery(hql);
		return query.list();
	}
}
