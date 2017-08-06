package com.system.query;


/**
 * 用户表
 */
public class UserQuery {
	private String userName;// 用户姓名(昵称)
	private String studyCode;//学号 
	private String userType;//用户类型
	private String userTel;//电话
	private String userCode;//身份证
	private String notUserId;//自身ID
	private String status;
	private String userstatus;
	private String userno;
	private String userceng;
	public UserQuery() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStudyCode() {
		return studyCode;
	}

	public void setStudyCode(String studyCode) {
		this.studyCode = studyCode;
	}

	public String getUserType()
	{
		return userType;
	}

	public void setUserType(String userType)
	{
		this.userType = userType;
	}

	public String getUserTel()
	{
		return userTel;
	}

	public void setUserTel(String userTel)
	{
		this.userTel = userTel;
	}

	public String getUserCode()
	{
		return userCode;
	}

	public void setUserCode(String userCode)
	{
		this.userCode = userCode;
	}

	public String getNotUserId()
	{
		return notUserId;
	}

	public void setNotUserId(String notUserId)
	{
		this.notUserId = notUserId;
	}

	public String getUserno()
	{
		return userno;
	}

	public void setUserno(String userno)
	{
		this.userno = userno;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getUserstatus()
	{
		return userstatus;
	}

	public void setUserstatus(String userstatus)
	{
		this.userstatus = userstatus;
	}

	public String getUserceng()
	{
		return userceng;
	}

	public void setUserceng(String userceng)
	{
		this.userceng = userceng;
	}
	
}