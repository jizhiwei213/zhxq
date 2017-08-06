package com.lonewolf.jcxx.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Query;
import com.base.dao.BaseDAO;
import com.common.util.StringUtil;
import com.lonewolf.jcxx.model.UserRoomRelModel;
import com.lonewolf.jcxx.model.XiaoquModel;
import com.lonewolf.jcxx.model.ZhxqRoomModel;
import com.lonewolf.jcxx.query.ZhxqRoomQuery;
import com.system.define.SysParaDefine;
import com.system.model.DataDictionaryModel;
import com.system.model.DepartmentModel;
import com.system.model.UserModel;

public class ZhxqRoomDAO extends BaseDAO
{

	public List<ZhxqRoomModel> getZhxqRoomList(Integer start, Integer rows, ZhxqRoomQuery zhxqRoomQuery)
	{
		String hql = "from ZhxqRoomModel u where 1=1 ";
		if (null != zhxqRoomQuery)
		{
			if (null != zhxqRoomQuery.getNameQ() && !"".equals(zhxqRoomQuery.getNameQ()))
			{
				hql += " and u.name like '%" + zhxqRoomQuery.getNameQ() + "%'";
			}
			if (null != zhxqRoomQuery.getMenpaihaoQ() && !"".equals(zhxqRoomQuery.getMenpaihaoQ()))
			{
				hql += " and u.menpaihao like '%" + zhxqRoomQuery.getMenpaihaoQ() + "%'";
			}
			if (null != zhxqRoomQuery.getHuzhunameQ() && !"".equals(zhxqRoomQuery.getHuzhunameQ()))
			{
				hql += " and u.huzhuname like '%" + zhxqRoomQuery.getHuzhunameQ() + "%'";
			}
			if (null != zhxqRoomQuery.getXiaoquId() && !"".equals(zhxqRoomQuery.getXiaoquId()))
			{
				hql += " and u.xiaoqu.id = '" + zhxqRoomQuery.getXiaoquId() + "'";
			}
			if (null != zhxqRoomQuery.getYezhuId() && !"".equals(zhxqRoomQuery.getYezhuId()))
			{
				hql += " and u.id in (select rm.room.id from UserRoomRelModel rm where rm.user.id ='" + zhxqRoomQuery.getYezhuId() + "') ";
			}
			if (null != zhxqRoomQuery.getStatus() && "1".equals(zhxqRoomQuery.getStatus()))
			{
				hql += " and u.yezhu  is null ";
			}
		}
		hql += " order by u.name desc";
		Query query = getSession().createQuery(hql);
		if (start != null && rows != null)
		{
			query.setFirstResult(start);
			query.setMaxResults(rows);
		}
		return query.list();
	}

	public int getZhxqRoomCount(ZhxqRoomQuery zhxqRoomQuery)
	{
		String hql = "select count(*) from ZhxqRoomModel u where 1=1 ";
		if (null != zhxqRoomQuery)
		{
			if (null != zhxqRoomQuery.getNameQ() && !"".equals(zhxqRoomQuery.getNameQ()))
			{
				hql += " and u.name like '%" + zhxqRoomQuery.getNameQ() + "%'";
			}
			if (null != zhxqRoomQuery.getMenpaihaoQ() && !"".equals(zhxqRoomQuery.getMenpaihaoQ()))
			{
				hql += " and u.menpaihao like '%" + zhxqRoomQuery.getMenpaihaoQ() + "%'";
			}
			if (null != zhxqRoomQuery.getHuzhunameQ() && !"".equals(zhxqRoomQuery.getHuzhunameQ()))
			{
				hql += " and u.huzhuname like '%" + zhxqRoomQuery.getHuzhunameQ() + "%'";
			}
			if (null != zhxqRoomQuery.getXiaoquId() && !"".equals(zhxqRoomQuery.getXiaoquId()))
			{
				hql += " and u.xiaoqu.id = '" + zhxqRoomQuery.getXiaoquId() + "'";
			}
			if (null != zhxqRoomQuery.getYezhuId() && !"".equals(zhxqRoomQuery.getYezhuId()))
			{
				hql += " and u.id in (select rm.room.id from UserRoomRelModel rm where rm.user.id ='" + zhxqRoomQuery.getYezhuId() + "') ";
			}
			if (null != zhxqRoomQuery.getStatus() && "1".equals(zhxqRoomQuery.getStatus()))
			{
				hql += " and u.yezhu  is null ";
			}
		}
		hql += " order by u.name desc";
		Query query = getSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult() + "");
	}

	public ZhxqRoomModel getZhxqRoomModelById(String id)
	{
		return getHibernateTemplate().get(ZhxqRoomModel.class, id);
	}

	public void saveZhxqRoom(ZhxqRoomModel ZhxqRoomModel)
	{
		ZhxqRoomModel.setInsertTime(new Date());
		ZhxqRoomModel.setUpdateTime(new Date());
		getHibernateTemplate().save(ZhxqRoomModel);
	}

	public void updateZhxqRoom(ZhxqRoomModel zhxqRoomModel)
	{
		zhxqRoomModel.setUpdateTime(new Date());
		getHibernateTemplate().update(zhxqRoomModel);
	}

	public void deleteUserRoomByRoomAndUser(ZhxqRoomModel zhxqRoomModel, UserModel userModel)
	{
		String hql = "delete from UserRoomRelModel u where 1=1 ";
		hql += " and u.user = :userModel ";
		hql += " and u.room = :zhxqRoomModel";
		Query query = getSession().createQuery(hql);
		query.setParameter("userModel", userModel);
		query.setParameter("zhxqRoomModel", zhxqRoomModel);
		query.executeUpdate();
	}

	public List<UserRoomRelModel> getUserRoomByRoomAndUser(ZhxqRoomModel zhxqRoomModel, UserModel userModel)
	{
		String hql = "from UserRoomRelModel u where 1=1 ";
		
		if (null != userModel)
		{
			hql += " and u.user = :userModel ";
		}
		if (null != zhxqRoomModel)
		{
			hql += " and u.room = :zhxqRoomModel";
		}
		Query query = getSession().createQuery(hql);
		if (null != userModel)
		{
			query.setParameter("userModel", userModel);
		}
		if (null != zhxqRoomModel)
		{
			query.setParameter("zhxqRoomModel", zhxqRoomModel);
		}
		return query.list();
	}
	
	public List<ZhxqRoomModel> getUserRoomByNoUser(UserModel userModel)
	{
		String hql = "from ZhxqRoomModel u where 1=1 ";
		
		if (null != userModel)
		{
			hql += " and u.id not in (select ur.room.id  from UserRoomRelModel ur where ur.user.id = '"+userModel.getId()+"') ";
		}
		Query query = getSession().createQuery(hql);
		return query.list();
	}
	public void saveUserRoomByRoomAndUser(UserRoomRelModel userRoom)
	{
		getHibernateTemplate().save(userRoom);
	}

	public void deleteZhxqRoom(String id)
	{
		String hql = "delete from ZhxqRoomModel ddm where ddm.id=:id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	public String uploadFile(File inputExcel, XiaoquModel xiaoqu)
	{
		String msg = "导入成功";
		System.out.println(":::::::::::");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HashMap<String, Integer> roomNoMap = new HashMap<String, Integer>();
		int count = 0;
		String column = "";
		Workbook wb = null;
		try
		{
			InputStream is = new FileInputStream(inputExcel);
			wb = new HSSFWorkbook(is);
			is.close();

			// Workbook wb = null;
			// excel文件的前8个字节含有版本信息
			// InputStream is = new FileInputStream(standardExcel);
			// InputStream key = new PushbackInputStream(is,8);
			// if(POIFSFileSystem.hasPOIFSHeader(key)){
			// //03版本
			// wb = new HSSFWorkbook(is);
			// }else if(POIXMLDocument.hasOOXMLHeader(key)){
			// //07版本
			// wb = new XSSFWorkbook(is);
			// }
			// wb = new XSSFWorkbook(is);
			// is.close();
			Sheet sheet = wb.getSheetAt(0);
			Row rowTitle = sheet.getRow(0);
			List<ZhxqRoomModel> equipList = new ArrayList<ZhxqRoomModel>();
			for (int i = 1; i <= sheet.getLastRowNum(); i++)
			{
				count = i;
				Row row = sheet.getRow(i);
				if (row == null) break;
				Cell cellll = row.getCell(0);
				if(null==cellll)break;
				String xuhao = row.getCell(0).getStringCellValue();
				if (xuhao == null || "".equals(xuhao))
				{
					break;
				}
				ZhxqRoomModel zhxqRoomModel = new ZhxqRoomModel();// new一个新对象
				column = rowTitle.getCell(1).getStringCellValue();
				String mendong = row.getCell(1).getStringCellValue();
				if(null==mendong||"".equals(mendong))
				{
					break;
				}
				column = rowTitle.getCell(2).getStringCellValue();
				String danyuan = row.getCell(2).getStringCellValue();
				column = rowTitle.getCell(3).getStringCellValue();
				String menpaihao = row.getCell(3).getStringCellValue();
				column = rowTitle.getCell(4).getStringCellValue();
				String huzhuname = row.getCell(4).getStringCellValue();
				column = rowTitle.getCell(5).getStringCellValue();
				String mianji = row.getCell(5).getNumericCellValue()+"";
				column = rowTitle.getCell(6).getStringCellValue();
				String name = row.getCell(6).getStringCellValue();
				zhxqRoomModel.setHuzhuname(huzhuname);
				zhxqRoomModel.setId("");
				zhxqRoomModel.setMenpaihao(menpaihao);
				zhxqRoomModel.setName(name);
				zhxqRoomModel.setXiaoqu(xiaoqu);
				try
				{
					zhxqRoomModel.setMendong(Integer.valueOf(mendong)+"");
					zhxqRoomModel.setDanyuan(Integer.valueOf(danyuan)+"");
					zhxqRoomModel.setMianji(Double.valueOf(mianji));
					getHibernateTemplate().save(zhxqRoomModel);
				}
				catch (Exception e)
				{
					//e.printStackTrace();
					System.out.println(e.getMessage());
					continue;
				}
			}
		}
		catch (Exception e)
		{

			msg = "导入失败第" + count + "行" + column + "数据有误";
			e.printStackTrace();
		}// 03版本
		return msg;

	}

	private static HSSFCellStyle getCellStyle(HSSFWorkbook wb)
	{
		HSSFCellStyle cellstyle = wb.createCellStyle();
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		cellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellstyle.setWrapText(true);
		HSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeight((short) 250);// 对应字体10
		cellstyle.setFont(font);
		return cellstyle;
	}
}
