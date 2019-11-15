<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改密码</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/theAlert.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/jquery-3.4.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/theAlert.js"></script>

	<script type="text/javascript">
      function  modifyPassword(username){

          var pw1 = $("#pw1").val();
          var pw2 = $("#pw2").val();

          if (pw1 != pw2){
              alert("两次密码不一致，请重新输入");
              return;
          }

        $.ajax({
			url:"${pageContext.request.contextPath}/modiPass",
			type:"post",
			data:{"username":username,"password":pw1},//传递参数
			dataType:"json",
			cache:false,
                success:function (data) {

			    if (data.status == 200){

			        alert(data.msg);
			        window.location.href = "${pageContext.request.contextPath}/main/index";
                }else if (data.status == 400) {
			        alert(data.msg);
                }

            }

		})

	  }


	</script>

</head>
<body leftmargin="8" topmargin="8" background='${pageContext.request.contextPath}/skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="${pageContext.request.contextPath}/skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:我的信息>>修改密码
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="${pageContext.request.contextPath}/skin/images/tbg.gif">&nbsp;修改密码&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">原密码：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="" value="${activeUser.password}" /></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">新密码：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input  name="password" id="pw1"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">重复密码：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input  name="password" id="pw2" /></td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:modifyPassword('${activeUser.username}');" class="coolbg">保存</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>