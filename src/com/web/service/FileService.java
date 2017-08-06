package com.web.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import com.common.util.DateUtil;
import com.system.dao.UserDAO;

public class FileService {
	private JSONObject message;
	public JSONObject getMessage() {
		return message;
	}
	public void setMessage(JSONObject message) {
		this.message = message;
	}
	
//	//上传附件
//	public void UploadAnnexFile(File file,String uploadname){
//		try{
//			String root=ServletActionContext.getServletContext().getRealPath("/upload");
//			InputStream is = new FileInputStream(file);
//			OutputStream os = new FileOutputStream(new File(root,"\\"+uploadname));
//			byte[] buffer = new byte[500];
//			int length=0;
//			while(-1!=(length=is.read(buffer,0,buffer.length))){
//				os.write(buffer);
//			}
//			os.close();
//			is.close();
//		}catch(Exception e){
//			message=new JSONObject();
//			message.put("error", e.getMessage());
//		}
//	}
//	
//	//删除附件
//	public void DeleteAnnexFile(String name){
//		try{
//			String root=ServletActionContext.getServletContext().getRealPath("/upload");
//			File file=new File(root,"\\"+name);
//			if(file.exists()){
//				file.delete();
//			}
//		}catch(Exception e){
//			message=new JSONObject();
//			message.put("error", e.getMessage());
//		}
//	}
	
	//导出Excel
	//fieldlist：各列对应的JSON列
	//namelist：标题栏，可以有多行表头，需要合并的列，请设置名称一致
	public Workbook ExportExcel(Workbook workbook,String title,JSONArray datarows,String[] fieldlist,String[][] namelist,Boolean shownumcol){	
		Workbook wb = workbook;
		if(datarows==null)
			return null;
		if(fieldlist==null||fieldlist.length<=0)
			return null;
		if(namelist==null||namelist.length<=0||fieldlist.length!=namelist[0].length)
			return null;	
		
		try {
			if(wb==null)
				wb = new HSSFWorkbook();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int numcol=0;
		if(shownumcol)
			numcol=1;
		Sheet sheet=null;
		CellStyle titlestyle =null;
		int titlerowcount=0;
		if(title!=null&&!title.equals("")){
			sheet=wb.createSheet(title+"");		
			//标题格式
			titlestyle = wb.createCellStyle();
			titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右居中
			titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
			org.apache.poi.ss.usermodel.Font f = wb.createFont();//字体
			f.setFontName("黑体");
			f.setFontHeightInPoints((short)12);
			titlestyle.setFont(f);
		}else{
			sheet=wb.createSheet();
		}
		
		//单元格格式
		CellStyle cellstyle = wb.createCellStyle();
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右居中
		cellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
		cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
		cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);		
		org.apache.poi.ss.usermodel.Font f2 = wb.createFont();//字体
		f2.setFontName("黑体");
		f2.setFontHeightInPoints((short)10);
		cellstyle.setFont(f2);
		
		int[] widths = new int[fieldlist.length+numcol];//列宽
		for(int i=0;i<widths.length;i++)
			widths[i]=0;
		
		if(title!=null&&!title.equals("")){
			//大标题第1行合并单元格
			CellRangeAddress resionaddr = new CellRangeAddress(0,0,0,fieldlist.length-1+numcol);
			sheet.addMergedRegion(resionaddr);
			Row titlerow=sheet.createRow(0);
			titlerow.setHeight((short)595);
			Cell titlecell = titlerow.createCell(0);
			titlecell.setCellValue(title);
			titlecell.setCellStyle(titlestyle);
			titlerowcount=1;
		}
	

	
		//标题行
		for(int i=0;i<namelist.length;i++){
			Row firstrow=sheet.createRow(i+titlerowcount);
			firstrow.setHeight((short)525);
			if(shownumcol){
				Cell firstcell = firstrow.createCell(0);
				firstcell.setCellValue("序号");//第1列序号
				widths[0]=4;//列宽			
				firstcell.setCellStyle(cellstyle);
			}
			for(int j=0;j<namelist[i].length;j++){
				Cell namecell = firstrow.createCell(j+numcol);
				String name=namelist[i][j];
				if(name==null) {
					namelist[i][j]=name="";
				}
				widths[j+numcol]=calcalateLen(name,widths[j+numcol]);//列宽			
				namecell.setCellValue(name);
				namecell.setCellStyle(cellstyle);
			}
		}
		if(namelist.length>1&&shownumcol)
			sheet.addMergedRegion(new CellRangeAddress(1,namelist.length,0,0));//序号		
		//合并多行表头
		List<CellRangeAddress> existsrange = new ArrayList<CellRangeAddress>();
		for(int i=0;i<namelist.length;i++){
			for(int j=0;j<namelist[i].length;j++){
				Boolean containsinrange = false;
				for(int k=0;k<existsrange.size();k++){
					CellRangeAddress r = existsrange.get(k);
					if(i>=r.getFirstRow() && i<=r.getLastRow() && j>=r.getFirstColumn()&& j <=r.getLastColumn()){
						containsinrange = true;
						break;
					}
				}
				if(containsinrange) continue;//在合并区域内，取下一个
				CellRangeAddress range = getRangeAddr(namelist,i,j);
				if(range!=null){
					existsrange.add(range);
					CellRangeAddress realrange = new CellRangeAddress(range.getFirstRow()+1,range.getLastRow()+1,range.getFirstColumn()+numcol,range.getLastColumn()+numcol);
					sheet.addMergedRegion(realrange);//加上标题行和序号列
				}
			}
		}
		
		titlerowcount+=namelist.length;
		//内容行
		for(int i=0;i<datarows.size();i++){
			JSONObject obj = datarows.getJSONObject(i);
			Row row=sheet.createRow(i+titlerowcount);
			row.setHeight((short)375);
			if(shownumcol){
				Cell cell0 = row.createCell(0);
				cell0.setCellValue(i+1);
				cell0.setCellStyle(cellstyle);
				widths[0]=calcalateLen(""+(i+1),widths[0]);//列宽			
			}
			for(int j=0;j<fieldlist.length;j++){
				String field = fieldlist[j];
				Cell cell = row.createCell(j+numcol);
				cell.setCellStyle(cellstyle);
				Object value=obj.get(field);
				if(value==null){
					cell.setCellValue("");
				}
				else{
					String vstr = value.toString();
					cell.setCellValue(vstr);
					widths[j+numcol]=calcalateLen(vstr,widths[j+numcol]);//列宽		
				}
			}
		}

		//调整列宽
		for(int j=0;j<fieldlist.length+numcol;j++){
			if(widths[j]>50) widths[j]=50;
			sheet.setColumnWidth((short)j, widths[j]*300);
		}
		return wb;
	}
	
	
	private CellRangeAddress getRangeAddr(String[][] namelist,int row,int col){
		CellRangeAddress range=null;		
		if(namelist==null||namelist.length<=row||namelist[row].length<col)
			return null;
		String name = namelist[row][col];
		int endrow=row;
		int endcol=col;
		//先从行搜索
		for(int j=col+1;j<namelist[row].length;j++){
			String name1=namelist[row][j];
			if(!name.equals(name1)){//找到名称不一样的格式，或一行搜索完毕			
				if(j>col+1){//存在相同名称，合并
					endcol=j-1;
				}				
				break;
			}
			if(j==namelist[row].length-1){
				endcol=j;
			}
		}
		//再从列搜索
		for(int i=row+1;i<namelist.length;i++){
			int j=0;
			for(j=col;j<=endcol;j++){
				String name2=namelist[i][j];
				if(!name.equals(name2)){
					break;
				}
			}
			if((j-1)==endcol){//存在相同名称，合并
				endrow=i;					
			}
			else{
				break;
			}
		}
		if(endrow>row||endcol>col){
			range=new CellRangeAddress(row,endrow,col,endcol);
		}
		return range;
	}
	
	private int calcalateLen(String str,int len){
		int m=0;
		char arr[] = str.toCharArray();
		for(int i=0;i<arr.length;i++){
			char c = arr[i];
			if(c>=0x0000 && c<0x00FF)//英文
				m=m+1;
			else
				m=m+2;			
		}
		if(m>len)
			return m;
		else
			return len;
	}

	
	//获得单元格字符串
	public Object getCellValue(Cell cell){
		Object cellvalue=null;
		if(cell==null) return null;
		if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
			if(HSSFDateUtil.isCellDateFormatted(cell)){
				cellvalue=cell.getDateCellValue();
			}else{
				cellvalue= cell.getNumericCellValue();		
			}
        }else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
        	cellvalue= cell.getStringCellValue();			
        }else if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
        	cellvalue= cell.getCellFormula();			
        }else if(cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
        	cellvalue= String.valueOf(cell.getBooleanCellValue());			
        }else if(cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
        	cellvalue= null;			
        }else if(cell.getCellType() == HSSFCell.CELL_TYPE_ERROR) {
        	cellvalue= String.valueOf(cell.getErrorCellValue());			
        }else{
        	cellvalue=cell+"";
        }		
		return cellvalue;
	}
	
	//导入Excel
	public JSONArray ImportExcel(File excelfile) {
		JSONArray results=new JSONArray();
		message=new JSONObject();	
		StringBuffer error=new StringBuffer("");
		int rowindex=0;
		int j=0;
		try {
			DateUtil dateUtil = new DateUtil();
			InputStream ins = new FileInputStream(excelfile);
			Workbook wb = new HSSFWorkbook(ins);
			ins.close();

			Sheet sheet = wb.getSheetAt(0);
			Row firstrow = sheet.getRow(0);//第一行
			List<String> namelist=new LinkedList();
			if(firstrow==null||firstrow.getCell(0)==null){
				message.put("error", "表格数据为空！");
				return results;
			}
			JSONObject rowdata=new JSONObject();
			Cell cell=firstrow.getCell(j++);
			while(cell!=null){
				String colname=cell+"";
				namelist.add(colname);
				rowdata.put(j, colname);
				cell=firstrow.getCell(j++);	
			}
			results.add(rowdata);
			//第二行开始读数据
			for (rowindex=1;rowindex<=sheet.getLastRowNum();rowindex++) {
				Row row = sheet.getRow(rowindex);
				if(row == null)
					break;
				rowdata=new JSONObject();
				for(j=0;j<namelist.size();j++){
					Object cellval=getCellValue(row.getCell(j));
					rowdata.put(j,cellval);
				}
				results.add(rowdata);
			}
		} catch (Exception e) {			 
			error.append("读第" + rowindex + "行第"+(j+1)+"列数据有误\r\n");			
			e.printStackTrace();
		}
		message.put("error", error.toString());
		return results;
	}
	
}
