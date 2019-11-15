<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>报销管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
</head>
<body leftmargin="8" topmargin="8" background='${pageContext.request.contextPath}/skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="${pageContext.request.contextPath}/skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:报销管理>>报销列表
 </td>

 </tr>
</table>
</td>
</tr>
</table>


<!--  内容列表   -->
<form name="form2">

<table width="98%"  cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="${pageContext.request.contextPath}/skin/images/tbg.gif">&nbsp;报销单列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="22%">编号</td>
	<td width="8%">类型</td>
	<td width="8%">报销人</td>
	<td width="6%">总金额</td>
	<td width="10%">使用时间</td>
	<td width="20%">备注信息</td>
	<td width="7%">审批状态</td>
	<td width="5%">操作</td>
</tr>

   <c:forEach items="${baoxiaoList}" var="baoxiao">
	<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
		<td><input name="id" type="checkbox" id="id" value="101" class="np"></td>
		<td>${baoxiao.bxid}</td>
		<td>${baoxiao.paymode}</td>
		<td>${baoxiao.employee.ename}</td>
		<td>${baoxiao.totalmoney}</td>
		<td align="center"><u><fmt:formatDate value="${baoxiao.bxtime}" pattern="yyyy-MM-dd"></fmt:formatDate></u></td>
		<td>${baoxiao.bxremark}</td>
		<td>
		<%--	未审批--%>
			<c:if test="${baoxiao.bxstatus==1}">
				<font color="#6495ed">未审批</font>
			</c:if>
			<c:if test="${baoxiao.bxstatus==2}">
				<font color="green">审批通过</font>
			</c:if>
			<c:if test="${baoxiao.bxstatus==3}">
				<font color="red">驳回</font>
			</c:if>

		</td>
		<td><a href="${pageContext.request.contextPath}/task/baoxiao/onlyBaoXiao/baoxiao-task-edit?bxid=${baoxiao.bxid}">审批</a> </td>
	</tr>
	</c:forEach>
<tr bgcolor="#FAFAF1">
		<td height="28" colspan="12">
			<a href="${pageContext.request.contextPath}/task/baoxiao/showDealBaoXiao?bxstatus=2" class="coolbg">&nbsp;已审批的报销&nbsp;</a>
			<a href="${pageContext.request.contextPath}/task/baoxiao/showDealBaoXiao?bxstatus=3" class="coolbg">&nbsp;已驳回的报销&nbsp;</a>
		</td>
	</tr>



<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
  

</body>
</html>