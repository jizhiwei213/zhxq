package com.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <p>Title: 时间和日期的工具类</p>
 * <p>Description: 对时间和日期的字符串转换处理及比较等常用函数集</p>
 */
public class DateTools {
    //用来全局控制 上一周，本周，下一周的周数变化     
    private  int weeks = 0;     
    private int MaxYear;//一年最大天数  
	
    /**
     * 过滤日期时间字符串,返回纯数字型日期时间,型如"200411212311（2004年11月21日23点11分）"
     * 如果待过滤字符串为空或空字符串则返回""
     *
     * @param date String 待过滤的日期字符串
     * @return String 返回过滤后字符串
     * @throws Exception
     */
    public String trimDate(String date) throws Exception {
        StringBuffer sb = new StringBuffer("");
        char c = ' ';

        if (date != null && date.length() > 0) {
            for (int i = 0; i < date.length(); i++) {
                c = date.charAt(i);
                if (c >= '0' && c <= '9')
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 格式化时间字符串使返回YYYYMMDD格式字符型数据,使之符合标准
     * by Jack1982  (2007-02-08)
     *
     * @param str
     * @return String
     */
    public static String FormatDateToString(String str) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd"); //格式化时间
        Date tempDate = null;
        String date = "";
        try {
            if (str != null && !"".equals(str)) {
                tempDate = DateTools.stringToDate(str);
                date = format1.format(tempDate);
                return date;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
    
    /**
     * 格式化时间字符串使返回format格式字符型数据,使之符合标准
     * by Jack1982  (2007-02-08)
     *
     * @param str
     * @return String
     */
    public static String FormatDateToString(String str, String format) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd"); //格式化时间
        if(format != null && !format.equals(""))
        {
        	format1 = new SimpleDateFormat(format); //格式化时间
        }
        Date tempDate = null;
        String date = "";
        try {
            if (str != null && !"".equals(str)) {
                tempDate = DateTools.stringToDate(str);
                date = format1.format(tempDate);
                return date;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
    /**
     * 格式化时间字符串使返回YYYYMMDD格式字符型数据,使之符合标准
     * by Jack1982  (2007-02-08)
     *
     * @param str
     * @return String
     */
    public static String FormatDateToString(Date str) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd"); //格式化时间
        String date = "";
        try {
            if (str != null) {
                date = format1.format(str);
                return date;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 根据字符串返回Date型时间,对于型如"2005-11-12 11:12:21"或"2005-11-12"均有效
     * 在处理前将去除字符串头尾空格
     *
     * @param str String 待转换参数
     * @return Date 处理后日期
     * @throws Exception
     */
    public static Date stringToDate(String str) {
        Date date = null;
        String tempStr = "";

        if (str != null && !str.equals("")) {
            try {
                tempStr = str.trim();
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //精确到毫秒的时间
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                //精确到日的时间
                if (tempStr.trim().length() > 10) {
                    date = sdf1.parse(tempStr);
                } else {
                    date = sdf2.parse(tempStr);
                }
            }
            catch (Exception e) {
                return date;
            }
        }

        return date;
    }

    /**
     * 将Date型日期时间转换为字符串表示形式,型如"2006-11-22"或"2006-11-22 22:30:20"
     *
     * @param d       Date 待转换的Date型
     * @param exactly boolean 是否精确转换 true:精确转换至毫秒 false:转换至日
     * @return String 处理后的字符串表示形式
     */
    public static String dateToString(Date d, boolean exactly) {
        String str = "";

        if (d != null) {
            try {
                String pattern = "yyyy-MM-dd";
                if (exactly) pattern = "yyyy-MM-dd HH:mm:ss";
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                str = sdf.format(d);
            }
            catch (Exception e) {
                System.out.println("时间转换错误--无效的时间类型：" + d + "错误信息：" + e.getMessage());
                return str;
            }
        }

        return str;
    }

    public static String changeTimeFormat(int time){
    	String str = time + "";
        if (time < 0) {
            return "00";
        }

        if (time < 10 && time >= 0) {
            str = "0" + str;
        }

        return str;
    }

    /**
     * 用于页面取值和显示 如果时间为空 则返回空字符串
     *
     * @param d       Date
     * @param exactly boolean
     * @return String
     */
    public static String dateIfNull(Date d, boolean exactly) {
        String str = "";

        if (d != null) {
            str = dateToString(d, exactly);
        }

        return str;
    }


    public static int YEAR = Calendar.YEAR;
    public static int MONTH = Calendar.MONTH;
    public static int DATE = Calendar.DATE;
    public static int HOUR = Calendar.HOUR_OF_DAY;
    public static int MINUTE = Calendar.MINUTE;
    public static int SECOND = Calendar.SECOND;
    public static int MILL_SEC = Calendar.MILLISECOND;

    /**
     * 获取差值时间
     *
     * @param date 当前时间
     * @param n    差值,可为正负
     * @param type 类型
     * @return
     */
    public static Date getDateAfter(Date date, int n, int type) {
        if (date == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        switch (type) {
            case Calendar.YEAR : {
                int year = cal.get(Calendar.YEAR);
                year += n;
                cal.set(Calendar.YEAR, year);
                break;
            }
            case Calendar.MONTH : {
                int month = cal.get(Calendar.MONTH);
                month += n;
                cal.set(Calendar.MONTH, month);
                break;
            }
            case Calendar.DATE : {
                int nDate = cal.get(Calendar.DATE);
                nDate += n;
                cal.set(Calendar.DATE, nDate);
                break;
            }
            case Calendar.MINUTE : {
                int minute = cal.get(Calendar.MINUTE);
                minute += n;
                cal.set(Calendar.MINUTE, minute);
                break;
            }
            case Calendar.HOUR_OF_DAY : {
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                hour += n;
                cal.set(Calendar.HOUR_OF_DAY, hour);
                break;
            }
            case Calendar.SECOND : {
                int second = cal.get(Calendar.SECOND);
                second += n;
                cal.set(Calendar.SECOND, second);
                break;
            }
            case Calendar.MILLISECOND : {
                int mSecond = cal.get(Calendar.MILLISECOND);
                mSecond += n;
                cal.set(Calendar.MILLISECOND, mSecond);
                break;
            }
            default : {
                return null;
            }
        }

        date = cal.getTime();

        return date;
    }

    /**
     * 获取差值时间
     *
     * @param src  当前时间
     * @param n    差值,可为正负
     * @param type 类型
     * @return
     */
    public static String getDateStrAfter(String src, int n, int type) {
        Date date = stringToDate(src);

        date = getDateAfter(date, n, type);

        if (src.trim().length() > 10) {
            return dateToString(date, false);
        }

        return dateToString(date, false);
    }

    /**
     * 获取差值时间
     *
     * @param date 当前时间
     * @param n    差值,可为正负
     * @param type 类型
     * @return
     */
    public static String getDateStrAfter(Date date, int n, int type) {
        date = getDateAfter(date, n, type);

        if (type >= Calendar.HOUR_OF_DAY) {
            return dateToString(date, false);
        }

        return dateToString(date, false);
    }

    /**
     * 获取差值时间 - 到时分秒
     *
     * @param date 当前时间
     * @param n    差值,可为正负
     * @param type 类型
     * @return
     */
    public static String getDateExactStrAfter(Date date, int n, int type) {
        date = getDateAfter(date, n, type);

        if (type >= Calendar.HOUR_OF_DAY) {
            return dateToString(date, true);
        }

        return dateToString(date, true);
    }
    
    public static String getDateExactStrAfter(Date date, int n, int type,boolean flag) {
        date = getDateAfter(date, n, type);

        if (type >= Calendar.HOUR_OF_DAY) {
            return dateToString(date, flag);
        }

        return dateToString(date, flag);
    }

    /**
     * 获取差值时间
     *
     * @param src  当前时间
     * @param n    差值,可为正负
     * @param type 类型
     * @return
     */
    public static Date getDateAfter(String src, int n, int type) {
        Date date = stringToDate(src);

        date = getDateAfter(date, n, type);

        return date;
    }
    
    
    /**    
     * 得到二个日期间的间隔天数    
     */     
 public static String getTwoDay(String sj1, String sj2) {     
     SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");     
     long day = 0;     
     try {     
      java.util.Date date = myFormatter.parse(sj1);     
      java.util.Date mydate = myFormatter.parse(sj2);     
      day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);     
     } catch (Exception e) {     
      return "";     
     }     
     return day + "";     
 }     
  
  
 /**    
     * 根据一个日期，返回是星期几的字符串    
     *     
     * @param sdate    
     * @return    
     */     
 public static String getWeek(String sdate) {     
     // 再转换为时间     
     Date date = DateTools.strToDate(sdate);     
     Calendar c = Calendar.getInstance();     
     c.setTime(date);     
     return new SimpleDateFormat("EEEE").format(c.getTime());     
 }     
  
 /**    
     * 将短时间格式字符串转换为时间 yyyy-MM-dd     
     *     
     * @param strDate    
     * @return    
     */     
 public static Date strToDate(String strDate) {     
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");     
     ParsePosition pos = new ParsePosition(0);     
     Date strtodate = formatter.parse(strDate, pos);     
     return strtodate;     
 }     
  
 /**    
     * 两个时间之间的天数    
     *     
     * @param date1    
     * @param date2    
     * @return    
     */     
 public static long getDays(String date1, String date2) {     
     if (date1 == null || date1.equals(""))     
      return 0;     
     if (date2 == null || date2.equals(""))     
      return 0;     
     // 转换为标准时间     
     SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");     
     java.util.Date date = null;     
     java.util.Date mydate = null;     
     try {     
      date = myFormatter.parse(date1);     
      mydate = myFormatter.parse(date2);     
     } catch (Exception e) {     
     }     
     long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);     
     return day;     
 }     
  
  
  
      
 // 计算当月最后一天,返回字符串     
 public String getDefaultDay(){       
    String str = "";     
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");         
  
    Calendar lastDate = Calendar.getInstance();     
    lastDate.set(Calendar.DATE,1);//设为当前月的1号     
    lastDate.add(Calendar.MONTH,1);//加一个月，变为下月的1号     
    lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天     
         
    str=sdf.format(lastDate.getTime());     
    return str;       
 }     
      
 // 上月第一天     
 public String getPreviousMonthFirst(){       
    String str = "";     
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");         
  
    Calendar lastDate = Calendar.getInstance();     
    lastDate.set(Calendar.DATE,1);//设为当前月的1号     
    lastDate.add(Calendar.MONTH,-1);//减一个月，变为下月的1号     
     str=sdf.format(lastDate.getTime());     
    return str;       
 }     
  
//上t个月第一天     
 public String getPreviousNumMonthFirst(int t){       
    String str = "";     
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");         
  
    Calendar lastDate = Calendar.getInstance();     
    lastDate.set(Calendar.DATE,1);//设为当前月的1号     
    lastDate.add(Calendar.MONTH,-t);//减一个月，变为下月的1号     
     str=sdf.format(lastDate.getTime());     
    return str;       
 }
 
 //获取当月第一天     
 public String getFirstDayOfMonth(){       
    String str = "";     
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");         
  
    Calendar lastDate = Calendar.getInstance();     
    lastDate.set(Calendar.DATE,1);//设为当前月的1号     
    str=sdf.format(lastDate.getTime());     
    return str;       
 }     
      
 // 获得本周星期日的日期       
 public String getCurrentWeekday() {     
     weeks = 0;     
     int mondayPlus = this.getMondayPlus();     
     GregorianCalendar currentDate = new GregorianCalendar();     
     currentDate.add(GregorianCalendar.DATE, mondayPlus+6);     
     Date monday = currentDate.getTime();     
          
     DateFormat df = DateFormat.getDateInstance();     
     String preMonday = df.format(monday);     
     return preMonday;     
 }  
 
//获得相应周星期日的日期       
 public String getCurrentWeekday(int week) {     
	 int mondayPlus = 0;     
   GregorianCalendar currentDate = new GregorianCalendar(); 
   currentDate.set(currentDate.get(Calendar.YEAR), 0, 1);
   int dayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK)-1;         //因为按中国礼拜一作为第一天所以这里减1       
   if (dayOfWeek == 1) {     
  	 mondayPlus = 0;     
   } else {     
  	 mondayPlus = 1 - dayOfWeek;     
   }     
   currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * (week-1)+6);     
   Date sunday = currentDate.getTime();     
   DateFormat df = DateFormat.getDateInstance();     
   String preSunday = df.format(sunday);     
   return preSunday;    
 }  
      
      
 //获取当天时间      
 public String getNowTime(String dateformat){     
     Date   now   =   new   Date();        
     SimpleDateFormat   dateFormat   =   new   SimpleDateFormat(dateformat);//可以方便地修改日期格式        
     String  hehe  = dateFormat.format(now);        
     return hehe;     
 }     
      
 // 获得当前日期与本周日相差的天数     
 private int getMondayPlus() {     
     Calendar cd = Calendar.getInstance();     
     // 获得今天是一周的第几天，星期一是第一天，星期二是第二天......     
     int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK)-1;         //因为按中国礼拜一作为第一天所以这里减1     
     //System.out.println("相差天数:"+dayOfWeek);     
     if (dayOfWeek == 1) {     
         return 0;     
     } else {     
         return 1 - dayOfWeek;     
     }     
 }     
      
 //获得本周一的日期     
 public String getMondayOFWeek(){     
      weeks = 0;     
      int mondayPlus = this.getMondayPlus();     
      GregorianCalendar currentDate = new GregorianCalendar();     
      currentDate.add(GregorianCalendar.DATE, mondayPlus);     
      Date monday = currentDate.getTime();     
      String preMonday = new SimpleDateFormat("yyyy-MM-dd").format(monday);    
      return preMonday;     
 }  
 
//获得某周周一的日期     
 public String getMondayOFWeek(int week){     
	 int mondayPlus = 0;     
   GregorianCalendar currentDate = new GregorianCalendar(); 
   currentDate.set(currentDate.get(Calendar.YEAR), 0, 1);
   int dayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK)-1;         //因为按中国礼拜一作为第一天所以这里减1       
   if (dayOfWeek == 1) {     
  	 mondayPlus = 0;     
   } else {     
  	 mondayPlus = 1 - dayOfWeek;     
   }     
   currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * (week-1));     
   Date monday = currentDate.getTime();     
   DateFormat df = DateFormat.getDateInstance();     
   String preMonday = df.format(monday);     
   return preMonday;     
 }  
 
//获得相应月的相应周的周一的日期     
 public String getMonday(int month, int week) {     
	   int mondayPlus = 0;     
     GregorianCalendar currentDate = new GregorianCalendar(); 
     currentDate.set(currentDate.get(Calendar.YEAR), month-1, 1);
     int dayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK)-1;         //因为按中国礼拜一作为第一天所以这里减1       
     if (dayOfWeek == 1) {     
    	 mondayPlus = 0;     
     } else {     
    	 mondayPlus = 1 - dayOfWeek;     
     }     
     currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * (week-1));     
     Date monday = currentDate.getTime();     
     DateFormat df = DateFormat.getDateInstance();     
     String preMonday = df.format(monday);     
     return preMonday;     
 }  
      
//获得相应周的周六的日期     
 public String getSaturday() {     
     int mondayPlus = this.getMondayPlus();     
     GregorianCalendar currentDate = new GregorianCalendar();     
     currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);     
     Date monday = currentDate.getTime();     
     DateFormat df = DateFormat.getDateInstance();     
     String preMonday = df.format(monday);     
     return preMonday;     
 }     
      
// 获得上周星期日的日期     
 public String getPreviousWeekSunday() {     
     weeks=0;     
     weeks--;     
     int mondayPlus = this.getMondayPlus();     
     GregorianCalendar currentDate = new GregorianCalendar();     
     currentDate.add(GregorianCalendar.DATE, mondayPlus+weeks);     
     Date monday = currentDate.getTime();     
     String preMonday = new SimpleDateFormat("yyyy-MM-dd").format(monday);     
     return preMonday;     
 }     
 
  
// 获得上周星期一的日期     
 public String getPreviousWeekday() {     
     weeks--;     
     int mondayPlus = this.getMondayPlus();     
     GregorianCalendar currentDate = new GregorianCalendar();     
     currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);     
     Date monday = currentDate.getTime();     
     DateFormat df = DateFormat.getDateInstance();     
     String preMonday = df.format(monday);     
     return preMonday;     
 }     
 
//获得指定之前第t周星期日的日期     
 public String getPreviousNumWeekSunday(int t) {     
     int mondayPlus = this.getMondayPlus();     
     GregorianCalendar currentDate = new GregorianCalendar();     
     currentDate.add(GregorianCalendar.DATE, mondayPlus+t*7+6);     
     Date monday = currentDate.getTime();     
     String preMonday = new SimpleDateFormat("yyyy-MM-dd").format(monday);      
     return preMonday;     
 }     
 
  
// 获得指定之前第t周星期一的日期     
 public String getPreviousNumWeekday(int t) {     
     int mondayPlus = this.getMondayPlus();     
     GregorianCalendar currentDate = new GregorianCalendar();     
     currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * t);     
     Date monday = currentDate.getTime();     
     //DateFormat df = DateFormat.getDateInstance();     
     String preMonday = new SimpleDateFormat("yyyy-MM-dd").format(monday);     
     return preMonday;     
 } 
      
 // 获得下周星期一的日期     
 public String getNextMonday() {     
     weeks++;     
     int mondayPlus = this.getMondayPlus();     
     GregorianCalendar currentDate = new GregorianCalendar();     
     currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);     
     Date monday = currentDate.getTime();     
     DateFormat df = DateFormat.getDateInstance();     
     String preMonday = df.format(monday);     
     return preMonday;     
 }     
      
// 获得下周星期日的日期     
 public String getNextSunday() {     
          
     int mondayPlus = this.getMondayPlus();     
     GregorianCalendar currentDate = new GregorianCalendar();     
     currentDate.add(GregorianCalendar.DATE, mondayPlus + 7+6);     
     Date monday = currentDate.getTime();     
     DateFormat df = DateFormat.getDateInstance();     
     String preMonday = df.format(monday);     
     return preMonday;     
 }     
      
//获得上月最后一天的日期     
 public String getPreviousMonthEnd(){     
     String str = "";     
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");         
  
    Calendar lastDate = Calendar.getInstance();     
   lastDate.add(Calendar.MONTH,-1);//减一个月     
   lastDate.set(Calendar.DATE, 1);//把日期设置为当月第一天      
   lastDate.roll(Calendar.DATE, -1);//日期回滚一天，也就是本月最后一天      
    str=sdf.format(lastDate.getTime());     
    return str;       
 } 
 
 
//获得上t个月最后一天的日期     
public String getPreviousNumMonthEnd(int t){     
    String str = "";     
   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");         
 
   Calendar lastDate = Calendar.getInstance();     
  lastDate.add(Calendar.MONTH,-t);//减一个月     
  lastDate.set(Calendar.DATE, 1);//把日期设置为当月第一天      
  lastDate.roll(Calendar.DATE, -1);//日期回滚一天，也就是本月最后一天      
   str=sdf.format(lastDate.getTime());     
   return str;       
}  
      
//获得下个月第一天的日期     
 public String getNextMonthFirst(){     
     String str = "";     
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");         
  
    Calendar lastDate = Calendar.getInstance();     
   lastDate.add(Calendar.MONTH,1);//减一个月     
   lastDate.set(Calendar.DATE, 1);//把日期设置为当月第一天      
    str=sdf.format(lastDate.getTime());     
    return str;       
 }     
      
//获得下个月最后一天的日期     
 public String getNextMonthEnd(){     
     String str = "";     
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");         
  
    Calendar lastDate = Calendar.getInstance();     
   lastDate.add(Calendar.MONTH,1);//加一个月     
   lastDate.set(Calendar.DATE, 1);//把日期设置为当月第一天      
   lastDate.roll(Calendar.DATE, -1);//日期回滚一天，也就是本月最后一天      
    str=sdf.format(lastDate.getTime());     
    return str;       
 }     
      
 //获得明年最后一天的日期     
 public String getNextYearEnd(){     
     String str = "";     
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");         
  
   Calendar lastDate = Calendar.getInstance();     
   lastDate.add(Calendar.YEAR,1);//加一个月     
  lastDate.set(Calendar.DATE,1);//把日期设置为当月第一天      
   lastDate.roll(Calendar.DATE, -1);//日期回滚一天，也就是本月最后一天      
    str=sdf.format(lastDate.getTime());     
    return str;       
 }     
      
//获得明年第一天的日期     
 public String getNextYearFirst(){     
     String str = "";     
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");         
  
    Calendar lastDate = Calendar.getInstance();     
   lastDate.add(Calendar.YEAR,1);//加一年     
   lastDate.set(Calendar.MONTH,1);//把日期设置为当年第一月      
  lastDate.set(Calendar.DATE,1);//把日期设置为当月第一天      
    str=sdf.format(lastDate.getTime());     
    return str;       
 }     
      
 private int getYearPlus(){     
     Calendar cd = Calendar.getInstance();     
     int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);//获得当天是一年中的第几天     
     cd.set(Calendar.DAY_OF_YEAR,1);//把日期设为当年第一天     
     cd.roll(Calendar.DAY_OF_YEAR,-1);//把日期回滚一天。     
     int MaxYear = cd.get(Calendar.DAY_OF_YEAR);     
     if(yearOfNumber == 1){     
         return -MaxYear;     
     }else{     
         return 1-yearOfNumber;     
     }     
 }     
//获得本年第一天的日期     
 public String getCurrentYearFirst(){     
     int yearPlus = this.getYearPlus();     
     GregorianCalendar currentDate = new GregorianCalendar();     
     currentDate.add(GregorianCalendar.DATE,yearPlus);     
     Date yearDay = currentDate.getTime();     
     DateFormat df = DateFormat.getDateInstance();     
     String preYearDay = df.format(yearDay);     
     return preYearDay;     
 }     
      
      
//获得本年最后一天的日期 *     
 public String getCurrentYearEnd(){     
     Date date = new Date();     
     SimpleDateFormat   dateFormat   =   new   SimpleDateFormat("yyyy");//可以方便地修改日期格式        
     String  years  = dateFormat.format(date);        
     return years+"-12-31";     
 }     
      
      
//获得上年第一天的日期 *     
 public String getPreviousYearFirst(){     
     Date date = new Date();     
     SimpleDateFormat   dateFormat   =   new   SimpleDateFormat("yyyy");//可以方便地修改日期格式        
     String  years  = dateFormat.format(date); int years_value = Integer.parseInt(years);       
     years_value--;     
     return years_value+"-1-1";     
 }     
      
//获得上年最后一天的日期     
 public String getPreviousYearEnd(){     
     weeks--;     
     int yearPlus = this.getYearPlus();     
     GregorianCalendar currentDate = new GregorianCalendar();     
     currentDate.add(GregorianCalendar.DATE,yearPlus+MaxYear*weeks+(MaxYear-1));     
     Date yearDay = currentDate.getTime();     
     DateFormat df = DateFormat.getDateInstance();     
     String preYearDay = df.format(yearDay);     
     getThisSeasonTime(11);     
     return preYearDay;     
 }     
      
//获得本季度     
 public String getThisSeasonTime(int month){     
     int array[][] = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};     
     int season = 1;     
     if(month>=1&&month<=3){     
         season = 1;     
     }     
     if(month>=4&&month<=6){     
         season = 2;     
     }     
     if(month>=7&&month<=9){     
         season = 3;     
     }     
     if(month>=10&&month<=12){     
         season = 4;     
     }     
     int start_month = array[season-1][0];     
     int end_month = array[season-1][2];     
          
     Date date = new Date();     
     SimpleDateFormat   dateFormat   =   new   SimpleDateFormat("yyyy");//可以方便地修改日期格式        
     String  years  = dateFormat.format(date);        
     int years_value = Integer.parseInt(years);     
          
     int start_days =1;//years+"-"+String.valueOf(start_month)+"-1";//getLastDayOfMonth(years_value,start_month);     
     int end_days = getLastDayOfMonth(years_value,end_month);     
     String seasonDate = years_value+"-"+start_month+"-"+start_days+";"+years_value+"-"+end_month+"-"+end_days;     
     return seasonDate;     
          
 }     
      
 /**    
  * 获取某年某月的最后一天    
  * @param year 年    
  * @param month 月    
  * @return 最后一天    
  */     
private int getLastDayOfMonth(int year, int month) {     
      if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8     
                || month == 10 || month == 12) {     
            return 31;     
      }     
      if (month == 4 || month == 6 || month == 9 || month == 11) {     
            return 30;     
      }     
      if (month == 2) {     
            if (isLeapYear(year)) {     
                return 29;     
            } else {     
                return 28;     
            }     
      }     
      return 0;     
}     
/**    
 * 是否闰年    
 * @param year 年    
 * @return     
 */     
public boolean isLeapYear(int year) {     
     return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);     
}     

    public static void main(String[] args) throws ParseException {
        Date date = new Date();

        System.out.println(getDateExactStrAfter(date, 2, DateTools.YEAR) + " Date-Src两年后");
        System.out.println(getDateExactStrAfter(date, -2, DateTools.YEAR) + " Date-Src两年前");
        System.out.println(getDateExactStrAfter(date, 2, DateTools.MONTH) + " Date-Src两月后");
        System.out.println(getDateExactStrAfter(date, -2, DateTools.MONTH) + " Date-Src两月前");
        System.out.println(getDateExactStrAfter(date, 2, DateTools.DATE) + " Date-Src两天后");
        System.out.println(getDateExactStrAfter(date, -2, DateTools.DATE) + " Date-Src两天前");
        System.out.println(getDateExactStrAfter(date, 2, DateTools.HOUR) + " Date-Src两小时后");
        System.out.println(getDateExactStrAfter(date, 2, DateTools.MINUTE) + " Date-Src两分钟后");
        System.out.println(getDateExactStrAfter(date, -2, DateTools.MINUTE) + " Date-Src两分钟前");
        System.out.println(getDateExactStrAfter(date, 2, DateTools.SECOND) + " Date-Src两秒后");
        System.out.println(getDateExactStrAfter(date, -2, DateTools.SECOND) + " Date-Src两秒前");
        
        DateTools tt = new DateTools();
        System.out.println("获取当天日期:"+tt.getNowTime("yyyy-MM-dd"));     
        System.out.println("获取本周一日期:"+tt.getMondayOFWeek());     
        System.out.println("获取本周日的日期~:"+tt.getCurrentWeekday());     
        System.out.println("获取上周一日期:"+tt.getPreviousWeekday());     
        System.out.println("获取上周日日期:"+tt.getPreviousWeekSunday());   
        System.out.println("获取上几周一日期:"+tt.getPreviousNumWeekday(-2));     
        System.out.println("获取上几周日日期:"+tt.getPreviousNumWeekSunday(-2));  
        System.out.println("获取下周一日期:"+tt.getNextMonday());     
        System.out.println("获取下周日日期:"+tt.getNextSunday());  
        System.out.println("获取本月第一天日期:"+tt.getFirstDayOfMonth());     
        System.out.println("获取本月最后一天日期:"+tt.getDefaultDay());     
        System.out.println("获取上月第一天日期:"+tt.getPreviousMonthFirst());    
        System.out.println("获取上几月第一天日期:"+tt.getPreviousNumMonthFirst(1));     
        System.out.println("获取上月最后一天的日期:"+tt.getPreviousMonthEnd());   
        System.out.println("获取上几月最后一天的日期:"+tt.getPreviousNumMonthEnd(1));  
        System.out.println("获取下月第一天日期:"+tt.getNextMonthFirst());     
        System.out.println("获取下月最后一天日期:"+tt.getNextMonthEnd());     
        System.out.println("获取本年的第一天日期:"+tt.getCurrentYearFirst());     
        System.out.println("获取本年最后一天日期:"+tt.getCurrentYearEnd());     
        System.out.println("获取去年的第一天日期:"+tt.getPreviousYearFirst());     
        System.out.println("获取去年的最后一天日期:"+tt.getPreviousYearEnd());     
        System.out.println("获取明年第一天日期:"+tt.getNextYearFirst());     
        System.out.println("获取明年最后一天日期:"+tt.getNextYearEnd());     
        System.out.println("获取本季度第一天到最后一天:"+tt.getThisSeasonTime(11));     
        System.out.println("获取两个日期之间间隔天数2008-12-1~2008-9.29:"+DateTools.getTwoDay("2008-12-1","2008-9-29"));     
        System.out.println("根据日期获得星期几:"+DateTools.getWeek("2008-12-1"));  
        
        String zydate =tt.getPreviousNumWeekday(-1);
        System.out.println(zydate);
		String zmdate =  tt.getPreviousNumWeekday(-1);
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(zmdate));
		System.out.println( cal.get(Calendar.WEEK_OF_YEAR)+"      "+zmdate);
    }
}
