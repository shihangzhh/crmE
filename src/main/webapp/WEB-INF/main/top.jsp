<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>top</title>
<link href="${pageContext.request.contextPath}/skin/css/base.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript"
	src="${pageContext.request.contextPath}/skin/js/frame/menu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/jquery-3.4.1.js"></script>
    <script type="text/javascript">

        function exitSystem() {

           $.ajax({
               url:"${pageContext.request.contextPath}/employees/exitSystem",
              type:"get",
               dataType:"json",
               cache:false,
               success:function (data) {
           if (data.status == 200){
               window.top.location = "${pageContext.request.contextPath}/login";

           }

               }
           });
        };


        function updatePwd() {
            window.top.location = "${pageContext.request.contextPath}/forwardModiPass"
        }

</script>
<style>
body { padding:0px; margin:0px; }
#tpa {
	color: #009933;
	margin:0px;
	padding:0px;
	float:right;
	padding-right:10px;
}

#tpa dd {
	margin:0px;
	padding:0px;
	float:left;
	margin-right:2px;
}

#tpa dd.ditem {
	margin-right:8px;
}

#tpa dd.img {
  padding-top:6px;
}

div.item
{
  text-align:center;
	background:url(${pageContext.request.contextPath}/skin/images/frame/topitembg.gif) 0px 3px no-repeat;
	width:82px;
	height:26px;
	line-height:28px;
}

.itemsel {
  width:80px;
  text-align:center;
  background:#226411;
	border-left:1px solid #c5f097;
	border-right:1px solid #c5f097;
	border-top:1px solid #c5f097;
	height:26px;
	line-height:28px;
}

*html .itemsel {
	height:26px;
	line-height:26px;
}

a:link,a:visited {
 text-decoration: underline;
}

.item a:link, .item a:visited {
	font-size: 12px;
	color: #ffffff;
	text-decoration: none;
	font-weight: bold;
}

.itemsel a:hover {
	color: #ffffff;
	font-weight: bold;
	border-bottom:2px solid #E9FC65;
}

.itemsel a:link, .itemsel a:visited {
	font-size: 12px;
	color: #ffffff;
	text-decoration: none;
	font-weight: bold;
}

.itemsel a:hover {
	color: #ffffff;
	border-bottom:2px solid #E9FC65;
}

.rmain {
  padding-left:10px;
  /* background:url(skin/images/frame/toprightbg.gif) no-repeat; */
}
</style>
</head>
<body bgColor='#ffffff'>
<table width="100%" border="0" cellpadding="0" cellspacing="0" background="${pageContext.request.contextPath}/skin/images/frame/topbg.gif">
  <tr>
    <td width='20%' height="60"><img src="${pageContext.request.contextPath}/skin/images/frame/logo.gif" /></td>
    <td width='80%' align="right" valign="bottom">
    	<table width="750" border="0" cellspacing="0" cellpadding="0">
      <tr>
      <td align="right" height="26" style="padding-right:10px;line-height:26px;">


          <c:if test="${!empty activeUser}">
          欢迎您的登录${activeUser.ename}
              [<a href="index.html" target="_top">主页</a>]
              [<a href="javascript:updatePwd();">修改密码</a>]
              [<a href="javascript:exitSystem();">注销退出</a>]&nbsp;
          </c:if>

      </td>
      </tr>
      <tr>
        <td align="right" height="34" class="rmain">
		
		</td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>