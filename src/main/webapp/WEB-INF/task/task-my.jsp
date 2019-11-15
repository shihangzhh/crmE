<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的任务信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/theAlert.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/theAlert.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">

    <script type="text/javascript">

        function changeTaskStatus(row,status) {

            $.ajax({
                url:"${pageContext.request.contextPath}/task/changeTaskStatus",
                type:"post",
                data:{"id":row,"status":status},
                dataType:"json",
                cache:false,
                success:function (data) {

                    if (data.status == 200){
                        $('#'+row+' td[id=status]').html(data.msg);
                    }else if(data.status == 400){
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
    当前位置:任务管理>>我的任务信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form name='form3' action='' method='get'>
<input type='hidden' name='dopost' value='' />
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='${pageContext.request.contextPath}/skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='cid' style='width:150px'>
          <option value='0'>选择类型...</option>
          	<option value='1'>任务标题</option>
          </select>
        </td>
        <td width='70'>
          关键字：
        </td>
        <td width='160'>
          	<input type='text' name='keyword' value='' style='width:120px' />
        </td>
        <td width='110'>
    		<select name='orderby' style='width:120px'>
            <option value='id'>排序...</option>
            <option value='pubdate'>开始时间</option>
            <option value='pubdate'>结束时间</option>
      	</select>
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<input name="imageField" type="image" src="${pageContext.request.contextPath}/skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<!--  内容列表   -->
<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="${pageContext.request.contextPath}/skin/images/tbg.gif">&nbsp;我的任务信息&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">任务标题</td>
	<td width="10%">执行者</td>
	<td width="8%">状态</td>
	<td width="5%">优先级</td>
	<td width="8%">开始时间</td>
	<td width="8%">结束时间</td>
	<td width="18%">操作</td>
</tr>

    <c:forEach var="task" items="${taskList}" varStatus="st">
        <tr id="${task.id}" align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
            <td><input name="id" type="checkbox" id="id" value="101" class="np"></td>
            <td>${task.id}</td>
            <td>${task.tasktitle}</td>
            <td align="center">${task.employee.ename}</td>
            <td align="center" id="status">
                <c:if test="${task.status ==0}">
                    未开始
                </c:if>
                <c:if test="${task.status ==1}">
                    进行中
                </c:if>
                <c:if test="${task.status ==2}">
                    结束了
                </c:if>
            </td>
            <td align="center">${task.level}</td>
            <td><fmt:formatDate value="${task.starttime}" pattern="yyyy-MM-dd"></fmt:formatDate> </td>
            <td><fmt:formatDate value="${task.endtime}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
            <td><a href="javascript:changeTaskStatus(${task.id},1);">开始任务</a> | <a href="javascript:changeTaskStatus(${task.id},2);">完成任务</a> |
                <a href="task-edit.jsp">编辑</a> | <a href="task-look.jsp">查看详情</a></td>
        </tr>
    </c:forEach>

<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="" class="coolbg">全选</a>
	<a href="" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
  

</body>
</html>