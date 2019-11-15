<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>档案信息管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/theAlert.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/jquery-3.4.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/theAlert.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	
	function selectAll() {

        var values = $("input[type=checkbox][name=id]");
        //alert(values.length);
        $.each(values,function(index,element) {
            element.checked = true;
        })

    };
	
	function resSelect() {

        var values = $("input[type=checkbox][name=id]");
        //alert(values.length);
        $.each(values,function(index,element) {

            if (element.checked){
                element.checked = false;
            } else{
                element.checked = true;
            }
        })

    };




    function deleteOneOrMore() {

        var   ids = [ ];//声明一个存储选中元素的id

        $("input:checked").each(function () {
            var id=  $(this).val();
            ids.push(id);

        });
        //alert(ids.length);
        //判断元素是否为空
        if (ids.length==0){
            alert("您没有选中任何的元素哦");
            return ;
        }

        var msg = confirm("确定要删除吗");
        if (msg){

            $.ajax({
                url:"${pageContext.request.contextPath}/archives/deleteOneOrMoreArchives",
                type:"post",
                dataType:"json",
                data:"ids="+ids,
                cache:false,
                success:function (data) {

                    if (data.status ==200){

                        theAlert(data.msg,"alert",function () {

                            window.location.href = "${pageContext.request.contextPath}/archives/getAllArchives?currentPage=1";
                        })
                    }else if(data.status == 400){
                        theAlert(data.msg,"alert");
                    }

                }

            });
        }

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
    当前位置:项目管理>>档案信息管理
 </td>

 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->

<!--  内容列表   -->
<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="${pageContext.request.contextPath}/skin/images/tbg.gif">&nbsp;员工档案信息列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="8%">姓名</td>
	<td width="5%">年龄</td>
	<td width="4%">民族</td>

	<td width="8%">貌政治面</td>

	<td width="10%">毕业院校</td>
	<td width="8%">入职时间</td>
	<td width="10%">联系方式</td>
	<td width="8%">学历</td>
	<td width="12%">邮箱</td>

	<%--<td width="10%">操作</td>--%>
</tr>
<c:forEach var="archives" items="${pageView.dataList}">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="${archives.dnum}" class="np"></td>
	<td>${archives.dnum}</td>
	<td>${archives.employee.ename}</td>
	<td>${archives.employee.eage}</td>
	<td>${archives.minzu}</td>
	<td>${archives.zzmm}</td>
	<td>${archives.school}</td>
	<td><fmt:formatDate value="${archives.employee.hiredate}" pattern="yyyy-MM-dd"></fmt:formatDate> </td>
	<td>${archives.employee.telephone}</td>
	<td>${archives.xueli}</td>
	<td>${archives.email}</td>
	<%--<td><a href="project-base-edit.jsp">编辑</a> | <a href="project-base-look.jsp">查看详情</a></td>--%>
</tr>

</c:forEach>
<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="javascript:selectAll();" class="coolbg">全选</a>
	<a href="javascript:resSelect();" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:deleteOneOrMore();" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
	<a href="${pageContext.request.contextPath}/archives/archives-add" class="coolbg">&nbsp;添加档案信息&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 -->
		<a href="${pageContext.request.contextPath}/archives/getAllArchives?currentPage=1">首页</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<c:if test="${pageView.currentPage!=1}">
			<a href="${pageContext.request.contextPath}/archives/getAllArchives?currentPage=${pageView.currentPage-1}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</c:if>
		<c:if test="${pageView.currentPage != pageView.totalPages}">
		<a href="${pageContext.request.contextPath}/archives/getAllArchives?currentPage=${pageView.currentPage+1}">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</c:if>

		<a href="${pageContext.request.contextPath}/archives/getAllArchives?currentPage=${pageView.totalPages}">尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;

	</td>
</tr>
</table>

</form>
  

</body>
</html>