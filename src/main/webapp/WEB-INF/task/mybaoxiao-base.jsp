<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/jquery-3.4.1.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>附件管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
<script type="text/javascript">

	function selectAll() {
        var values = $("input[type=checkbox][name=bxid]");
	   //alert(values.length);
        $.each(values,function(index,element) {
            element.checked = true;
        })
    };

	function unselectAll() {
        var values = $("input[type=checkbox][name=bxid]");
        //alert(values.length);
        $.each(values,function(index,element) {

            if (element.checked){
                element.checked = false;
            } else{
                element.checked = true;
            }
        })

    };
	
	function deleteOneOrAll() {

        var values = $("input[type=checkbox][name=bxid]");
        if (values == null||values==""){

            alert("您没有选中哦");
            return;
		}
        var flag = confirm("确定删除吗");

        if (flag){
            $("#myBaoXiaoForm").submit();
        }

    }
    
    
    


</script>

</head>
<body leftmargin="8" topmargin="8" background='${pageContext.request.contextPath}/skin/images/allbg.gif'>
<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
<tr>
 <td height="26" background="${pageContext.request.contextPath}/skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:个人报销管理>>报销列表
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='${pageContext.request.contextPath}/task/baoxiao/mybaoxiao-add';" value='添加报销' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>


<!--  内容列表   -->
<form id="myBaoXiaoForm" action="${pageContext.request.contextPath}/task/baoxiao/deleteOneOrMoreBaoXiao" method="post">

<table width="98%"  cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="${pageContext.request.contextPath}/skin/images/tbg.gif">&nbsp;附件列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="30%">编号</td>
	<td width="6%">总金额</td>
	<td width="10%">使用时间</td>
	<td width="30%">备注信息</td>
	<td width="10%">审批状态</td>
	<td width="10%">操作</td>
</tr>

	<c:forEach var="baoxiao" items="${pageView.dataList}">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="bxid" type="checkbox" id="bxid" value="${baoxiao.bxid}" class="np"></td>
	<td>${baoxiao.bxid}</td>
	<td>${baoxiao.totalmoney}</td>
	<td align="center"><u><fmt:formatDate value="${baoxiao.bxtime}" pattern="yyyy-MM-dd"></fmt:formatDate></u></td>
	<td>${baoxiao.bxremark}</td>
   <td>
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
   <td>
	   <c:if test="${baoxiao.bxstatus==2}">
		   <a href="${pageContext.request.contextPath}/task/baoxiao/onlyBaoXiao/mybaoxiao-edit?bxid=${baoxiao.bxid}">查看详情</a></td>
		   </c:if>
	    <c:if test="${baoxiao.bxstatus==1 ||baoxiao.bxstatus==3}">
			<a href="${pageContext.request.contextPath}/task/baoxiao/onlyBaoXiao/mybaoxiao-edit?bxid=${baoxiao.bxid}">编辑</a></td>
		</c:if>


</tr>
	</c:forEach>

	<tr bgcolor="#FAFAF1">
		<td height="28" colspan="12">
			&nbsp;
			<a href="javascript:selectAll();" class="coolbg">全选</a>
			<a href="javascript:unselectAll();" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;
			<a href="javascript:deleteOneOrAll();" class="coolbg">&nbsp;删除&nbsp;</a>
			<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
			<a href="${pageContext.request.contextPath}/task/baoxiao/getBaoXiaoTables?bxstatus=1" class="coolbg">&nbsp;未审批&nbsp;</a>
			<a href="${pageContext.request.contextPath}/task/baoxiao/getBaoXiaoTables?bxstatus=2" class="coolbg">&nbsp;审批通过&nbsp;</a>
			<a href="${pageContext.request.contextPath}/task/baoxiao/getBaoXiaoTables?bxstatus=3" class="coolbg">&nbsp;驳回&nbsp;</a>
		</td>
	</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center">
		<!--翻页代码 -->
    	<a href="${pageContext.request.contextPath}/task/baoxiao/getBaoXiaoTables?currentPage=1">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<c:if test="${pageView.currentPage != 1}">
		<a href="${pageContext.request.contextPath}/task/baoxiao/getBaoXiaoTables?currentPage=${pageView.currentPage-1}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</c:if>
		<c:if test="${pageView.currentPage != pageView.totalPages}">
		<a href="${pageContext.request.contextPath}/task/baoxiao/getBaoXiaoTables?currentPage=${pageView.currentPage+1}">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</c:if>
		<a href="${pageContext.request.contextPath}/task/baoxiao/getBaoXiaoTables?currentPage=${pageView.totalPages}">尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;
	</td>
</tr>
</table>

</form>
  

</body>
</html>