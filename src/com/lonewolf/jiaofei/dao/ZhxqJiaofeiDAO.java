package com.lonewolf.jiaofei.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Query;
import com.base.dao.BaseDAO;
import com.lonewolf.jcxx.model.XiaoquModel;
import com.lonewolf.jcxx.model.ZhxqRoomModel;
import com.lonewolf.jcxx.model.ZhxqWyfbzModel;
import com.lonewolf.jiaofei.model.ZhxqJiaofeiModel;
import com.lonewolf.jiaofei.query.ZhxqJiaofeiQuery;

public class ZhxqJiaofeiDAO extends BaseDAO {

	public List<ZhxqJiaofeiModel> getZhxqJiaofeiList(Integer start, Integer rows,ZhxqJiaofeiQuery zhxqJiaofeiQuery) {
		String hql ="from ZhxqJiaofeiModel u where 1=1 ";
		if(null!=zhxqJiaofeiQuery)
		{
			if(null!=zhxqJiaofeiQuery.getYearQ()&&!"".equals(zhxqJiaofeiQuery.getYearQ()))
			{
				hql+=" and u.zhxqyear = '"+zhxqJiaofeiQuery.getYearQ()+"'";
			}
			if(null!=zhxqJiaofeiQuery.getMonthQ()&&!"".equals(zhxqJiaofeiQuery.getMonthQ()))
			{
				hql+=" and u.zhxqmonth = '"+zhxqJiaofeiQuery.getMonthQ()+"'";
			}
			if(null!=zhxqJiaofeiQuery.getRoomNameQ()&&!"".equals(zhxqJiaofeiQuery.getRoomNameQ()))
			{
				hql+=" and u.room.name like '%"+zhxqJiaofeiQuery.getRoomNameQ()+"%'";
			}
			if(null!=zhxqJiaofeiQuery.getYwyId()&&!"".equals(zhxqJiaofeiQuery.getYwyId()))
			{
				hql+=" and u.ywy.id = '"+zhxqJiaofeiQuery.getYwyId()+"'";
			}
			if(null!=zhxqJiaofeiQuery.getXiaoquId()&&!"".equals(zhxqJiaofeiQuery.getXiaoquId()))
			{
				hql+=" and u.room.xiaoqu.id = '"+zhxqJiaofeiQuery.getXiaoquId()+"'";
			}
			if(null!=zhxqJiaofeiQuery.getQuyuId()&&!"".equals(zhxqJiaofeiQuery.getQuyuId()))
			{
				hql+=" and u.room.xiaoqu.qu.id = '"+zhxqJiaofeiQuery.getQuyuId()+"'";
			}
			if(null!=zhxqJiaofeiQuery.getShenId()&&!"".equals(zhxqJiaofeiQuery.getShenId()))
			{
				hql+=" and u.room.xiaoqu.shen.id = '"+zhxqJiaofeiQuery.getShenId()+"'";
			}
			if(null!=zhxqJiaofeiQuery.getShiId()&&!"".equals(zhxqJiaofeiQuery.getShiId()))
			{
				hql+=" and u.room.xiaoqu.shi.id = '"+zhxqJiaofeiQuery.getShiId()+"'";
			}
			if(null!=zhxqJiaofeiQuery.getXianId()&&!"".equals(zhxqJiaofeiQuery.getXianId()))
			{
				hql+=" and u.room.xiaoqu.qu.id = '"+zhxqJiaofeiQuery.getXianId()+"'";
			}
//			if(null!=zhxqJiaofeiQuery.getYezhuId()&&!"".equals(zhxqJiaofeiQuery.getYezhuId()))
//			{
//				hql+=" and u.room.yezhu.id = '"+zhxqJiaofeiQuery.getYezhuId()+"'";
//			}
			if(null!=zhxqJiaofeiQuery.getYezhuId()&&!"".equals(zhxqJiaofeiQuery.getYezhuId()))
			{
				hql+=" and u.room.id  in (select m.room.id from UserRoomRelModel m where m.user.id = '"+zhxqJiaofeiQuery.getYezhuId()+"') ";
			}
			if(null!=zhxqJiaofeiQuery.getIsJfcx()&&"1".equals(zhxqJiaofeiQuery.getIsJfcx()))
			{
				hql+=" and u.ywy  is not null ";
			}
			if(null!=zhxqJiaofeiQuery.getJfzt()&&!"".equals(zhxqJiaofeiQuery.getJfzt()))
			{
				hql+=" and u.jiaofeistatus in "+zhxqJiaofeiQuery.getJfzt()+" ";
			}
		}
		hql+=" order by u.jiaofeistatus desc, u.zhxqyear desc,u.zhxqmonth desc";
		Query query=getSession().createQuery(hql);
		if(start!=null&&rows!=null){
			query.setFirstResult(start);
			query.setMaxResults(rows);
		}
		return query.list();
	}

	public int getZhxqJiaofeiCount(ZhxqJiaofeiQuery zhxqJiaofeiQuery) {
		String hql ="select count(*) from ZhxqJiaofeiModel u where 1=1 ";
		if(null!=zhxqJiaofeiQuery)
		{
			if(null!=zhxqJiaofeiQuery.getYearQ()&&!"".equals(zhxqJiaofeiQuery.getYearQ()))
			{
				hql+=" and u.zhxqyear = '"+zhxqJiaofeiQuery.getYearQ()+"'";
			}
			if(null!=zhxqJiaofeiQuery.getMonthQ()&&!"".equals(zhxqJiaofeiQuery.getMonthQ()))
			{
				hql+=" and u.zhxqmonth = '"+zhxqJiaofeiQuery.getMonthQ()+"'";
			}
			if(null!=zhxqJiaofeiQuery.getRoomNameQ()&&!"".equals(zhxqJiaofeiQuery.getRoomNameQ()))
			{
				hql+=" and u.room.name like '%"+zhxqJiaofeiQuery.getRoomNameQ()+"%'";
			}
			if(null!=zhxqJiaofeiQuery.getYwyId()&&!"".equals(zhxqJiaofeiQuery.getYwyId()))
			{
				hql+=" and u.ywy.id = '"+zhxqJiaofeiQuery.getYwyId()+"'";
			}
			if(null!=zhxqJiaofeiQuery.getXiaoquId()&&!"".equals(zhxqJiaofeiQuery.getXiaoquId()))
			{
				hql+=" and u.room.xiaoqu.id = '"+zhxqJiaofeiQuery.getXiaoquId()+"'";
			}
			if(null!=zhxqJiaofeiQuery.getQuyuId()&&!"".equals(zhxqJiaofeiQuery.getQuyuId()))
			{
				hql+=" and u.room.xiaoqu.qu.id = '"+zhxqJiaofeiQuery.getQuyuId()+"'";
			}
			if(null!=zhxqJiaofeiQuery.getShenId()&&!"".equals(zhxqJiaofeiQuery.getShenId()))
			{
				hql+=" and u.room.xiaoqu.shen.id = '"+zhxqJiaofeiQuery.getShenId()+"'";
			}
			if(null!=zhxqJiaofeiQuery.getShiId()&&!"".equals(zhxqJiaofeiQuery.getShiId()))
			{
				hql+=" and u.room.xiaoqu.shi.id = '"+zhxqJiaofeiQuery.getShiId()+"'";
			}
			if(null!=zhxqJiaofeiQuery.getXianId()&&!"".equals(zhxqJiaofeiQuery.getXianId()))
			{
				hql+=" and u.room.xiaoqu.qu.id = '"+zhxqJiaofeiQuery.getXianId()+"'";
			}
//			if(null!=zhxqJiaofeiQuery.getYezhuId()&&!"".equals(zhxqJiaofeiQuery.getYezhuId()))
//			{
//				hql+=" and u.room.yezhu.id = '"+zhxqJiaofeiQuery.getYezhuId()+"'";
//			}
			if(null!=zhxqJiaofeiQuery.getYezhuId()&&!"".equals(zhxqJiaofeiQuery.getYezhuId()))
			{
				hql+=" and u.room.id  in (select m.room.id from UserRoomRelModel m where m.user.id = '"+zhxqJiaofeiQuery.getYezhuId()+"') ";
			}
			if(null!=zhxqJiaofeiQuery.getIsJfcx()&&"1".equals(zhxqJiaofeiQuery.getIsJfcx()))
			{
				hql+=" and u.ywy  is not null ";
			}
			if(null!=zhxqJiaofeiQuery.getJfzt()&&!"".equals(zhxqJiaofeiQuery.getJfzt()))
			{
				hql+=" and u.jiaofeistatus in "+zhxqJiaofeiQuery.getJfzt()+" ";
			}
		}
		hql+=" order by u.jiaofeistatus desc, u.zhxqyear desc,u.zhxqmonth desc";
		Query query=getSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult()+"");
	}

	public ZhxqJiaofeiModel getZhxqJiaofeiModelById(String id) {
		return getHibernateTemplate().get(ZhxqJiaofeiModel.class, id);
	}

	public void saveZhxqJiaofei(ZhxqJiaofeiModel ZhxqJiaofeiModel) {
		ZhxqJiaofeiModel.setInsertTime(new Date());
		ZhxqJiaofeiModel.setUpdateTime(new Date());
		getHibernateTemplate().save(ZhxqJiaofeiModel);
	}

	public void updateZhxqJiaofei(ZhxqJiaofeiModel zhxqJiaofeiModel) {
		zhxqJiaofeiModel.setUpdateTime(new Date());
		getHibernateTemplate().update(zhxqJiaofeiModel);
	}

	public void deleteZhxqJiaofei(String id) {
		String hql="delete from ZhxqJiaofeiModel ddm where ddm.id=:id";
		Query query=getSession().createQuery(hql);
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
				ZhxqJiaofeiModel jiaofei = new ZhxqJiaofeiModel();// new一个新对象
				column = rowTitle.getCell(1).getStringCellValue();
				String mendong = row.getCell(1).getStringCellValue();
				column = rowTitle.getCell(2).getStringCellValue();
				String danyuan = row.getCell(2).getStringCellValue();
				column = rowTitle.getCell(3).getStringCellValue();
				String menpaihao = row.getCell(3).getStringCellValue();
				column = rowTitle.getCell(4).getStringCellValue();
				String shuiliang = row.getCell(4).getStringCellValue();
				column = rowTitle.getCell(5).getStringCellValue();
				String dianliang = row.getCell(5).getStringCellValue();
				column = rowTitle.getCell(6).getStringCellValue();
				String ranqiliang = row.getCell(6).getStringCellValue();
				column = rowTitle.getCell(7).getStringCellValue();
				String cwf = row.getCell(7).getStringCellValue();
				column = rowTitle.getCell(8).getStringCellValue();
				String nian = row.getCell(8).getStringCellValue();
				column = rowTitle.getCell(9).getStringCellValue();
				String yue = row.getCell(9).getStringCellValue();
				Double shuifeibz =0d;Double ranqifeibz =0d;Double dianfeibz =0d;
				Double wyfbz=0d,otherfybz=0d,gnfbz=0d;
				String hql2 ="from ZhxqWyfbzModel u where 1=1 and u.xiaoqu.id='"+xiaoqu.getId()+"' ";
				hql2+=" order by u.xiaoqu.xiaoquName desc";
				Query query2=getSession().createQuery(hql2);
				List bzList = query2.list();
				if(null!= bzList&&bzList.size()>0)
				{
					ZhxqWyfbzModel bz =(ZhxqWyfbzModel)bzList.get(0);
					shuifeibz = bz.getSfbiaozhun();
					ranqifeibz = bz.getRqbiaozhun();
					dianfeibz = bz.getDfbiaozhun();
					wyfbz = bz.getBiaozhun();
					otherfybz = bz.getQitabz();
					gnfbz = bz.getNqf();
				}
				
				String hql="from ZhxqRoomModel ddm where ddm.menpaihao='"+menpaihao+"' and ddm.mendong='"+mendong+"' and ddm.danyuan='"+danyuan+"' ";
				Query query=getSession().createQuery(hql);
				List roomList = query.list();
				if(null!=roomList&&roomList.size()>0)
				{
					ZhxqRoomModel room = (ZhxqRoomModel)roomList.get(0);
					String sql =" from ZhxqJiaofeiModel j where j.room.id='"+room.getId()+"' and j.zhxqyear='"+nian+"' and j.zhxqmonth='"+yue+"'  ";
					Query query5 = getSession().createQuery(sql);
					List jiaofeiList = query5.list();
					if(null==jiaofeiList||jiaofeiList.size()==0)
					{
						jiaofei.setRoom(room);
						Double mianji = room.getMianji();
						try
						{
							jiaofei.setWyf(wyfbz*mianji);
							jiaofei.setGnf(0d);
							jiaofei.setCwf(Double.valueOf(cwf));
							jiaofei.setOtherfy(otherfybz);
							jiaofei.setDianfei(Double.valueOf(dianliang)*dianfeibz);
							jiaofei.setDianliang(Double.valueOf(dianliang));
							jiaofei.setRuanqifeiliang(Double.valueOf(ranqiliang));
							jiaofei.setRuanqifei(Double.valueOf(ranqiliang)*ranqifeibz);
							jiaofei.setShuifeiliang(Double.valueOf(shuiliang));
							jiaofei.setShuifei(Double.valueOf(shuiliang)*shuifeibz);
							jiaofei.setZhxqyear(Integer.valueOf(nian));
							jiaofei.setZhxqmonth(Integer.valueOf(yue));
							jiaofei.setJiaofeistatus("2");
							jiaofei.setDianliangstatus("2");
							jiaofei.setShuifeistatus("2");
							jiaofei.setRuanqifeistatus("2");
							jiaofei.setGnfstatus("2");
							//zhxqRoomModel.setMianji(Double.valueOf(mianji));
						}
		
						catch (Exception e)
						{
							e.printStackTrace();
						}
						getHibernateTemplate().save(jiaofei);
					}
					else
					{
						ZhxqJiaofeiModel jf =(ZhxqJiaofeiModel)jiaofeiList.get(0);
						String status = jf.getJiaofeistatus();
						if("2".equals(status))
						{
							try
							{
								Double mianji = jf.getRoom().getMianji();
								jf.setWyf(wyfbz*mianji);
								jf.setGnf(0d);
								jf.setCwf(Double.valueOf(cwf));
								jf.setOtherfy(otherfybz);
								jiaofei.setDianfei(Double.valueOf(dianliang)*dianfeibz);
								jiaofei.setDianliang(Double.valueOf(dianliang));
								jiaofei.setRuanqifeiliang(Double.valueOf(ranqiliang));
								jiaofei.setRuanqifei(Double.valueOf(ranqiliang)*ranqifeibz);
								jiaofei.setShuifeiliang(Double.valueOf(shuiliang));
								jiaofei.setShuifei(Double.valueOf(shuiliang)*shuifeibz);
								jf.setZhxqyear(Integer.valueOf(nian));
								jf.setZhxqmonth(Integer.valueOf(yue));
								jf.setJiaofeistatus("2");
								jf.setDianliangstatus("2");
								jf.setShuifeistatus("2");
								jf.setRuanqifeistatus("2");
								jf.setGnfstatus("2");
								//zhxqRoomModel.setMianji(Double.valueOf(mianji));
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}
							getHibernateTemplate().update(jf);
						}
					}
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
