package com.lonewolf.jcxx.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import com.base.dao.BaseDAO;
import com.lonewolf.jcxx.model.ZhxqWyfbzModel;

public class ZhxqWyfbzDAO extends BaseDAO {

	public List<ZhxqWyfbzModel> getZhxqWyfbzList(Integer start, Integer rows,String xiaoquId) {
		String hql ="from ZhxqWyfbzModel u where 1=1 and u.xiaoqu.id='"+xiaoquId+"' ";
		hql+=" order by u.xiaoqu.xiaoquName desc";
		Query query=getSession().createQuery(hql);
		if(start!=null&&rows!=null){
			query.setFirstResult(start);
			query.setMaxResults(rows);
		}
		return query.list();
	}

	public int getZhxqWyfbzCount(String xiaoquId) {
		String hql ="select count(*) from ZhxqWyfbzModel u where 1=1 and u.xiaoqu.id='"+xiaoquId+"' ";
		hql+=" order by u.xiaoqu.xiaoquName desc";
		Query query=getSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult()+"");
	}

	public ZhxqWyfbzModel getZhxqWyfbzModelById(String id) {
		return getHibernateTemplate().get(ZhxqWyfbzModel.class, id);
	}

	public void saveZhxqWyfbz(ZhxqWyfbzModel zhxqWyfbzModel) {
		zhxqWyfbzModel.setInsertTime(new Date());
		zhxqWyfbzModel.setUpdateTime(new Date());
		getHibernateTemplate().save(zhxqWyfbzModel);
	}

	public void updateZhxqWyfbz(ZhxqWyfbzModel zhxqWyfbzModel) {
		zhxqWyfbzModel.setUpdateTime(new Date());
		getHibernateTemplate().update(zhxqWyfbzModel);
	}

	public void deleteZhxqWyfbz(String id) {
		String hql="delete from ZhxqWyfbzModel ddm where ddm.id=:id";
		Query query=getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
	}
}
