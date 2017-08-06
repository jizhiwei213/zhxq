    <span style="font-family:Comic Sans MS;font-size:18px;"><%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>  
    <%  
        String path = request.getContextPath();  
   			String error =  request.getParameter("error");
   			if(null==error)
   			{
   				error="";
   			}
   			else if("2".equals(error))
   			{
   			error="注册失败,用户名已经存在！";
   			}
   			else
   			{
   			error="注册成功";
   			}
    %>  
      
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
    <html>  
    <head>  
    <title>后台管理系统</title>  
    <meta http-equiv=Content-Type content="text/html; charset=utf-8">  
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta name="viewport" content="width=device-width, initial-scale=1" /> 
    <meta http-equiv="expires" content="0">  
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  
    <style type="text/css">  
    .neon {  
        FILTER: glow(color = #002E60, strength  = 3)  
    }  
      
    DIV {  
        WIDTH: 70px  
    }  
      
    BODY {  
        MARGIN: 0px  
    }  
      
    BODY {  
        MARGIN-TOP: 0px;  
        SCROLLBAR-FACE-COLOR: #005fc5;  
        FONT-SIZE: 12px;  
        BACKGROUND: #ffffff;  
        SCROLLBAR-HIGHLIGHT-COLOR: #799ae1;  
        SCROLLBAR-SHADOW-COLOR: #799ae1;  
        SCROLLBAR-3DLIGHT-COLOR: #005fc5;  
        SCROLLBAR-ARROW-COLOR: #ffffff;  
        SCROLLBAR-TRACK-COLOR: #aabfec;  
        SCROLLBAR-DARKSHADOW-COLOR: #799ae1  
    }  
    </STYLE>  
    <LINK href="<%=path%>/images/duan_1.css" type=text/css rel=stylesheet>  
    <META content="MSHTML 6.00.2800.1106" name=GENERATOR>  
    <style type="text/css">  
    .style6 {  
        COLOR: #0000ff  
    }  
      
    .STYLE7 {  
        COLOR: #003366;  
        font-size: 12px;  
    }  
    </style>  
    <script type="text/javascript">  
        function dosubmit() {  
            var th = document.form1;  
            if (th.userAccount.value == "") {  
                alert("用户名不能为空");  
                th.userAccount.focus();  
                return;  
            }  
            if (th.userPassword.value == "") {  
                alert("密码不能为空");  
                th.userPassword.focus();  
                return;  
            } 
            if (th.userName.value == "") {  
                alert("姓名 不能为空");  
                th.userName.focus();  
                return;  
            }  
            th.action="<%=path%>/regeditServlet";
            th.submit();  
      
        }  
    </script>  
    </head>  
      
    <body bgColor=#ffffff  
        onload="MM_preloadImages('<%=path%>/images/ok_2.jpg', '<%=path%>/images/fh_2.jpg')">  
        <form action="<%=request.getContextPath()%>/regeditServlet" name="form1" method="post">  
            <table height="100%" cellSpacing=0 cellPadding=0 width="100%" aligen=center  
                border=0>  
                <tbody>  
                    <tr>  
                        <td colSpan=3 height=9 />  
                    </tr>  
                    <tr>  
                        <td vAlign=top width=8 background="<%=path%>/images/dhpddw.gif"  
                            rowSpan=2>  
                            <!-- DWLayoutEmptyCell -->  </td>  
                        <td background="<%=path%>/images/h-1.gif" height=9></td>  
                        <td width=9 height=9><IMG height=9  
                            src="<%=path%>/images/jiao.gif" width=9>  
                        </td>  
                    </tr>  
                    <tr>  
                        <td vAlign=top align=right width="100%" height="100%">  
                            <table cellSpacing=0 cellPadding=0 width="100%" border=0>  
                                <!-- DWLayoutTable -->  
                                <tbody>  
                                    <tr>  
                                        <td vAligh=bottom width="99%" height=27><IMG height=10  
                                            src="<%=path%>/images/jt2.gif" width=10> <span  
                                            class="1bt">如需任何帮助，请拨打公司电话：0539-8087008</span>  
                                        </td>  
                                        <td width=8 rowSpan=3> </td>  
                                    </tr>  
                                    <tr>  
                                        <td bgColor="#ffffff" height=22></td>  
                                    </tr>  
                                    <TR>  
                                        <TD height="10" align="center"> <a  
                                            href="<%=path%>/main/login.jsp"><img  
                                                src="<%=path%>/images/fh_1.jpg" name="Image9" width="60"  
                                                height="22" border="0"> </a>  
                                        </TD>  
                                        <TD></TD>  
                                    </TR>  
                                </tbody>  
                            </table>  
                        </td>  
                        <TD width=9 background="<%=path%>/images/s-1.gif"></TD>  
                    </tr>  
                </tbody>  
            </table>  
        </form>  
    </body>  
    </html>  
    </span>  