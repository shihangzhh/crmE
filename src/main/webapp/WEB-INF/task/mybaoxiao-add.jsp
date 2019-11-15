<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加附件</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/theAlert.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/jquery-3.4.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/theAlert.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>

<script type="application/javascript">

	function commit() {

	    $.ajax({
			url:"${pageContext.request.contextPath}/task/baoxiao/saveBaoXiao",
            type:"post",
			dataType:"json",
			data:$("#baoxiaoForm").serialize(),
			cache:false,
			success:function (data) {
                if(data.status==200){
                    theAlert(data.msg,"alert",function(){
                        window.location="${pageContext.request.contextPath}/task/baoxiao/getBaoXiaoTables";
                    });
                }else if(data.status==400){
                    theAlert(data.msg,"alert");
                }

            }
		});

    };



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
    当前位置:报销管理>>添加新的报销
 </td>
 </tr>
</table>
</td>
</tr>
</table>
<form id="baoxiaoForm" action="${pageContext.request.contextPath}/baoxiao/add"  method="post">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="${pageContext.request.contextPath}/skin/images/tbg.gif">&nbsp;添加新的报销单&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">支出类型：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="paymode">
			<option>请选择---</option>
			<option value="出差票据">出差票据</option>
			<option value="办公采购">办公采购</option>
			<option value="其他">其他</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">总金额：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input size="26" name="totalmoney"/>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">使用时间：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input size="10" name="bxtime" id="bxtime" onfocus="WdatePicker({el:'bxtime'})"/>
	</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea rows=10 cols=130 name="bxremark"></textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:commit();" class="coolbg">保存</a>
	<a href="${pageContext.request.contextPath}/task/baoxiao/mybaoxiao-base" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>