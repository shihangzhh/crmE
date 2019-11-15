<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加附件</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
<LINK href="${pageContext.request.contextPath}/skin/css/theAlert.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/theAlert.js"></script>


	<script type="application/javascript">

		function commit(status) {
             $("#bxstatus").val(status);
             $("#baoxiaoForm").submit();

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
    当前位置:报销管理>>更新报销
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form id="baoxiaoForm" action="${pageContext.request.contextPath}/task/baoxiao/upBaoXiaoBoHui" method="post">
     <input type="hidden" value="${baoxiao.bxid}" id="bxid" name="bxid">
	<input type="hidden" name="bxstatus" id="bxstatus" value="">
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="${pageContext.request.contextPath}/skin/images/tbg.gif">&nbsp;添加新的报销单&nbsp;</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">支出类型：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">

		<select name="paymode" id="paymode">
            <c:if test="${baoxiao.paymode eq '出差票据'}">
				<option value="出差票据" selected>出差票据</option>
				<option value="办公采购">办公采购</option>
				<option value="其他">其他</option>
			</c:if>
			<c:if test="${baoxiao.paymode eq '办公采购'}">
				<option value="出差票据">出差票据</option>
				<option value="办公采购" selected>办公采购</option>
				<option value="其他">其他</option>
			</c:if>
			<c:if test="${baoxiao.paymode eq '其他'}">
				<option value="出差票据" >出差票据</option>
				<option value="办公采购">办公采购</option>
				<option value="其他" selected>其他</option>
			</c:if>

		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">总金额：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input size="26"  id="totalmoney" name="totalmoney" value="${baoxiao.totalmoney}"/>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">使用时间：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input size="52" name="bxtime" id="bxtime" value='	<fmt:formatDate value="${baoxiao.bxtime}" pattern="yyyy-MM-dd"></fmt:formatDate>'/>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea rows=10 cols=130  id="bxremark" name="bxremark">${baoxiao.bxremark}</textarea>
	</td>
</tr>
    <tr >
		<td align="right" bgcolor="#FAFAF1" >批注：</td>
		<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
			${baoxiao.employee.ename}
				于<fmt:formatDate value="${baoxiao.shenpidate}" pattern="yyyy-MM-dd hh-mm-ss"></fmt:formatDate>
			审批结果为：<c:if test="${baoxiao.bxstatus==3}">驳回</c:if>
			原因：${baoxiao.result}
			<!--王翠花于2019-10-10 11:09:30 审批结为：驳回  原因：xxxxx-->
		</td>
	</tr>

<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:commit(1)" class="coolbg">更新</a>
	<a href="${pageContext.request.contextPath}/task/baoxiao/getBaoXiaoTables" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>

</body>
</html>