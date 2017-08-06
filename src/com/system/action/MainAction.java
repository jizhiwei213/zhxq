package com.system.action;

import java.io.ByteArrayInputStream;
import java.net.URLEncoder;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import com.base.action.BaseAction;
import com.common.util.EncryptKey;
import com.common.util.VerificationCodeUtil;
import com.opensymphony.xwork2.ActionContext;
import com.system.model.FunctionModel;
import com.system.model.UserModel;
import com.system.remind.model.MessageModel;
import com.system.remind.service.MessageService;
import com.system.service.FunctionService;
import com.system.service.OperationService;
import com.system.service.UserService;
import com.web.service.FileService;

public class MainAction extends BaseAction
{
	private static final long serialVersionUID = 1444752453974495856L;
	private FunctionService functionService;
	private UserService userService;
	private OperationService operationService;
	private MessageService messageService;
	private FileService fileSerivce;

	private List<FunctionModel> list;
	private String parentId;

	private String userAccount;
	private String userPassword;
	private String verificationCode;
	private String oldPwd;
	private String newPwd;
	private JSONObject jsonObj = new JSONObject();
	private String href;

	public String main()
	{
		list = functionService.findAuthTopFunctionModelList(getSessionUserId());

		List<String> operationCodeList = operationService.findOperationCodeListByUser(getSessionUserId());
		getSession().setAttribute("operationCodeList", operationCodeList);

		List<MessageModel> messsageList = messageService.getMessageList();
		getRequest().setAttribute("messsageList", messsageList);

		return SUCCESS;
	}

	public String left()
	{
		list = functionService.findAuthChildrenFunctionModelList(getSessionUserId(), parentId);
		return SUCCESS;
	}

	public String leftChildren()
	{
		list = functionService.findAuthChildrenFunctionModelList(getSessionUserId(), parentId);
		return SUCCESS;
	}

	public String registIndex()
	{
		return SUCCESS;
	}

	public String login()
	{
		// String
		// vcode=getSession().getAttribute(VerificationCodeUtil.SESSION_VERIFICATION_CODE_NAME)+"";
		// if(vcode.equals(verificationCode)){

		userPassword = EncryptKey.encrypt(userPassword);
		UserModel user = userService.findUserModelByLogin(userAccount, userPassword);
		if (user != null)
		{
			getSession().setAttribute("user", user);
			return SUCCESS;
		}
		else
		{
			ActionContext.getContext().put("errorType", "accountError");
			return ERROR;
		}
		// }else{
		// if(verificationCode!=null&&!"".equals(verificationCode)){
		// ActionContext.getContext().put("errorType", "vcodeError");
		// }
		// return ERROR;
		// }

	}

	public String logOut()
	{
		getSession().invalidate();
		return SUCCESS;
	}

	public String changePwd()
	{
		if (getSessionUser().getUserPassword().equals(EncryptKey.encrypt(oldPwd)))
		{
			getSessionUser().setUserPassword(EncryptKey.encrypt(newPwd));// 更新session中保留的用户密码
			userService.updateUserPasswordById(getSessionUserId(), EncryptKey.encrypt(newPwd));
			jsonObj.put("code", "success");
		}
		else
		{
			jsonObj.put("code", "oldPwdError");// 旧密码错误
		}
		return SUCCESS;
	}

	public String tabs()
	{
		return SUCCESS;
	}

	public String excelExport()
	{
		try
		{
			String multi = getRequest().getParameter("multi");
			if (multi != null && multi.equals("multi"))
			{// 导出多个工作簿
				excelExportMulti();
			}
			else
			{
				JSONArray datas = JSONArray.fromObject(getRequest().getParameter("datas"));
				String filename = getRequest().getParameter("filename");
				String title = getRequest().getParameter("title");
				String fieldpara = getRequest().getParameter("fields");
				String namepara = getRequest().getParameter("names");
				String[] fields = fieldpara.split(",");
				String[] names = namepara.split(",");
				Integer fieldlen = fields.length;
				Integer namerow = names.length / fieldlen;
				if (filename == null || filename.equals("")) filename = title;
				String[][] truenames = new String[namerow][fieldlen];
				for (int i = 0; i < namerow; i++)
				{
					for (int j = 0; j < fieldlen; j++)
					{
						truenames[i][j] = names[i * fieldlen + j];
					}
				}
				Workbook wb = fileSerivce.ExportExcel(null, title, datas, fields, truenames, true);
				if (wb != null)
				{
					getResponse().setHeader("Content-Disposition",
							"attachment;filename=" + URLEncoder.encode(filename + ".xls", "UTF-8"));
					wb.write(getResponse().getOutputStream());
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public String excelExportMulti()
	{
		try
		{
			String filename = getRequest().getParameter("filename");

			JSONArray datas = JSONArray.fromObject(getRequest().getParameter("datas"));
			String fulltitle = getRequest().getParameter("title");
			String fullfieldpara = getRequest().getParameter("fields");
			String fullnamepara = getRequest().getParameter("names");
			String[] titles = fulltitle.split(";");
			String[] fieldparas = fullfieldpara.split(";");
			String[] nameparas = fullnamepara.split(";");
			Workbook wb = null;
			for (int k = 0; k < titles.length; k++)
			{// 分工作簿写入
				String title = titles[k];
				String fieldpara = fieldparas[k];
				String namepara = nameparas[k];
				String[] fields = fieldpara.split(",");
				String[] names = namepara.split(",");
				Integer fieldlen = fields.length;
				Integer namerow = names.length / fieldlen;

				String[][] truenames = new String[namerow][fieldlen];
				for (int i = 0; i < namerow; i++)
				{
					for (int j = 0; j < fieldlen; j++)
					{
						truenames[i][j] = names[i * fieldlen + j];
					}
				}
				JSONArray data = datas.getJSONArray(k);
				wb = fileSerivce.ExportExcel(new HSSFWorkbook(), title, data, fields, truenames, true);
			}
			if (wb != null)
			{
				getResponse().setHeader("Content-Disposition",
						"attachment;filename=" + URLEncoder.encode(filename + ".xls", "UTF-8"));
				wb.write(getResponse().getOutputStream());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void setFunctionService(FunctionService functionService)
	{
		this.functionService = functionService;
	}

	public List<FunctionModel> getList()
	{
		return list;
	}

	public void setList(List<FunctionModel> list)
	{
		this.list = list;
	}

	public String getParentId()
	{
		return parentId;
	}

	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

	public ByteArrayInputStream getInputStream()
	{
		return VerificationCodeUtil.buildVerificationCodeImage(getSession());
	}

	public String getUserAccount()
	{
		return userAccount;
	}

	public void setUserAccount(String userAccount)
	{
		this.userAccount = userAccount;
	}

	public String getUserPassword()
	{
		return userPassword;
	}

	public void setUserPassword(String userPassword)
	{
		this.userPassword = userPassword;
	}

	public String getVerificationCode()
	{
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode)
	{
		this.verificationCode = verificationCode;
	}

	public String getHref()
	{
		return href;
	}

	public void setHref(String href)
	{
		this.href = href;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public void setOperationService(OperationService operationService)
	{
		this.operationService = operationService;
	}

	public void setMessageService(MessageService messageService)
	{
		this.messageService = messageService;
	}

	public String getOldPwd()
	{
		return oldPwd;
	}

	public void setOldPwd(String oldPwd)
	{
		this.oldPwd = oldPwd;
	}

	public String getNewPwd()
	{
		return newPwd;
	}

	public void setNewPwd(String newPwd)
	{
		this.newPwd = newPwd;
	}

	public JSONObject getJsonObj()
	{
		return jsonObj;
	}

	public void setJsonObj(JSONObject jsonObj)
	{
		this.jsonObj = jsonObj;
	}

	public FileService getFileSerivce()
	{
		return fileSerivce;
	}

	public void setFileSerivce(FileService fileSerivce)
	{
		this.fileSerivce = fileSerivce;
	}

}
