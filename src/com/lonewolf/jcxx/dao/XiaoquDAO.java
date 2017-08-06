package com.lonewolf.jcxx.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import com.base.dao.BaseDAO;
import com.lonewolf.jcxx.model.XiaoquModel;
import com.lonewolf.jcxx.query.XiaoquQuery;

public class XiaoquDAO extends BaseDAO {

	public List<XiaoquModel> getXiaoquList(Integer start, Integer rows,XiaoquQuery xiaoquQuery) {
		String hql ="from XiaoquModel u where 1=1 ";
		if(null!=xiaoquQuery)
		{
			if(null!=xiaoquQuery.getXiaoquNameQ()&&!"".equals(xiaoquQuery.getXiaoquNameQ()))
			{
				hql+=" and u.xiaoquName like '%"+xiaoquQuery.getXiaoquNameQ()+"%'";
			}
			if(null!=xiaoquQuery.getXiaoquNoQ()&&!"".equals(xiaoquQuery.getXiaoquNoQ()))
			{
				hql+=" and u.xiaoquNo like '%"+xiaoquQuery.getXiaoquNoQ()+"%'";
			}
			if(null!=xiaoquQuery.getXiaoquAddressQ()&&!"".equals(xiaoquQuery.getXiaoquAddressQ()))
			{
				hql+=" and u.xiaoquAddress like '%"+xiaoquQuery.getXiaoquAddressQ()+"%'";
			}
		}
		hql+=" order by u.xiaoquNo desc";
		Query query=getSession().createQuery(hql);
		if(start!=null&&rows!=null){
			query.setFirstResult(start);
			query.setMaxResults(rows);
		}
		return query.list();
	}

	public int getXiaoquCount(XiaoquQuery xiaoquQuery) {
		String hql ="select count(*) from XiaoquModel u where 1=1 ";
		if(null!=xiaoquQuery)
		{
			if(null!=xiaoquQuery.getXiaoquNameQ()&&!"".equals(xiaoquQuery.getXiaoquNameQ()))
			{
				hql+=" and u.xiaoquName like '%"+xiaoquQuery.getXiaoquNameQ()+"%'";
			}
			if(null!=xiaoquQuery.getXiaoquNoQ()&&!"".equals(xiaoquQuery.getXiaoquNoQ()))
			{
				hql+=" and u.xiaoquNo like '%"+xiaoquQuery.getXiaoquNoQ()+"%'";
			}
			if(null!=xiaoquQuery.getXiaoquAddressQ()&&!"".equals(xiaoquQuery.getXiaoquAddressQ()))
			{
				hql+=" and u.xiaoquAddress like '%"+xiaoquQuery.getXiaoquAddressQ()+"%'";
			}
		}
		hql+=" order by u.xiaoquNo desc";
		Query query=getSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult()+"");
	}

	public XiaoquModel getXiaoquModelById(String id) {
		return getHibernateTemplate().get(XiaoquModel.class, id);
	}

	public void saveXiaoqu(XiaoquModel xiaoquModel) {
		xiaoquModel.setInsertTime(new Date());
		xiaoquModel.setUpdateTime(new Date());
		getHibernateTemplate().save(xiaoquModel);
	}

	public void updateXiaoqu(XiaoquModel xiaoquModel) {
		xiaoquModel.setUpdateTime(new Date());
		getHibernateTemplate().update(xiaoquModel);
	}

	public void deleteXiaoqu(String id) {
		String hql="delete from XiaoquModel ddm where ddm.id=:id";
		Query query=getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
	}
}
