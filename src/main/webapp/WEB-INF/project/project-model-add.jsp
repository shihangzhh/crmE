<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>添加模块信息</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/theAlert.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/theAlert.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/jquery-3.4.1.js"></script>
	<script type="text/javascript">

		$(function () {

            $.ajax({
                url:"${pageContext.request.contextPath}/project/analysisProject",
                type:"get",
                dataType:"json",
                cache:false,
                success:function(data){
                    $.each(data,function (index,element) {

                        var  option = "<option  value='"+element.pid+"'>"+element.pname+"</option>"
                        $("#proname").append(option);
                    });
                }

            });


        });

      //查询需求
		function queryAnalysis() {
		var id =    $("#proname option:selected").attr("value");
		//alert(id);
            $.ajax({
                url:"${pageContext.request.contextPath}/analysis/one",
                type:"get",
				data:{"id":id},
                dataType:"json",
                cache:false,
                success:function(data){
                $("#analysisName").val(data.title);
                $("#analysisFk").val(data.id);
                }

            });


        };
		
		function savaProjectModule() {

            $.ajax({
                url:"${pageContext.request.contextPath}/module/saveProjectModule",
                type:"post",
                data:$("#moduleForm").serialize(),
                dataType:"json",
                cache:false,
                success: function(data){

                    if(data.status==200){
                        theAlert(data.msg,"alert",function(){
                            window.location="${pageContext.request.contextPath}/project/project-model";
                        });
                    }else if(data.status==400){
                        theAlert(data.msg,"alert");
                    }
                }
            });



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
    当前位置:项目管理>>添加模块信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form id="moduleForm">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="${pageContext.request.contextPath}/skin/images/tbg.gif">&nbsp;添加新模块&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">选择项目：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="proname" name="proname" onchange="queryAnalysis();">
			<option >请选择项目----</option>
		</select></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">选择需求：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="hidden" name="analysisFk" id="analysisFk" >
		<input type="text" id="analysisName">
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">模块名称：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="modname"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">优先级：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="level">
			<option>高</option>
			<option>中</option>
			<option>低</option>
			<option>暂缓</option></select></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">简单描述：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<textarea rows=10 cols=130 name="simpledis"></textarea></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">详细描述：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<textarea rows=15 cols=130 name="detaileddis"></textarea></td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea rows=10 cols=130 name="remark"></textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:savaProjectModule();" class="coolbg">保存</a>
	<a href="project-model.jsp" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>