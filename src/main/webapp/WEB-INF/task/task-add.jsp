<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>创建任务</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/theAlert.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/jquery-3.4.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/theAlert.js"></script>
<script type="text/javascript">

	$(function () {

        $.ajax({  //查询带有功能的项目
            url:"${pageContext.request.contextPath}/project/function/projects",
            type:"get",
            dataType:"json",
            cache:false,
            success:function (data) {

                $.each(data,function (index,element) {

                    var option = "<option pid='"+element.pid+"' value='"+element.pname+"'>"+element.pname+"</option>"
                    $("#proname").append(option);
                });

            }

        });

          //保存任务
        $("#btn1").click(function () {

            $.ajax({
                url:"${pageContext.request.contextPath}/task/saveTask",
                type:"post",
                dataType:"json",
                data:$("#taskForm").serialize(),
                cache:false,
                success:function (data) {

                    if(data.status==200){
                        theAlert(data.msg,"alert",function(){
                            window.location="${pageContext.request.contextPath}/task/taskInfor";
                        });
                    }else if(data.status==400){
                        theAlert(data.msg,"alert");
                    }

                }

            });

        });




    });

	
	function getAnalysis() {  //根据项目id查询需求分析
	  var  pid=  $("#proname option:selected").attr("pid");

	  $.ajax({
          url:"${pageContext.request.contextPath}/analysis/one",
          type:"get",
		  data:{"id":pid},
          dataType:"json",
          cache:false,
		  success:function (data) {

              $("#analysisname").val(data.title);
              //查询模块信息

              $.ajax({       //根据需求分析的id查询模块
                  url:"${pageContext.request.contextPath}/module/analysis",
                  type:"post",
                  dataType:"json",
                  data:{"id":pid},
                  cache:false,
                  success:function (data) {

                      //清除所有的模块
                      $("#modeleFk option:not(:eq(0))").remove();
                      $.each(data,function (index,element) {

                          var  option = "<option   value='"+element.id+"' id='"+element.id+"'>"+element.modname+"</option>";
                          $("#modeleFk").append(option);
                      });


                  }

              });
          }

	  });

    };

    function getFunctions(){

        var moduleId =   $("#modeleFk option:selected").attr("value");
        
        $.ajax({       //根据模块id查询功能
            url:"${pageContext.request.contextPath}/function/module/functions",
            type:"post",
            dataType:"json",
            data:{"id":moduleId},
            cache:false,
            success:function (data) {

                if (data.length == 0){
                    alert("该模块下没有任何的功能");
                    $('#btn1').attr("disabled",true);

                }else {
                    $('#btn1').attr("disabled",false);

                }

                //清除所有的功能
                $("#funFk option:not(:eq(0))").remove();
                $.each(data,function (index,element) {
				var option = "<option  value='"+element.id+"'>"+element.functionname+"<option>";
				$("#funFk").append(option);
                })
                
            }
        });

    };

    //查询所有的开发工程师
    $.ajax({
		url:"${pageContext.request.contextPath}/employees/position/empl",
		type:"get",
		dataType:"json",
		data:{"positionName":"开发工程师"},
		cache:false,
		success:function (data) {

		    $.each(data,function (index,element) {

		        var option = "<option   value='"+element.eid+"' >"+element.ename+"--"+element.position.name+"</option>";
               $("#empFk2").append(option);
            });

        }

	});






</script>
</head>
<body leftmargin="8" topmargin="8" background='${pageContext.request.contextPath}/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="${pageContext.request.contextPath}/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:任务管理>>创建任务
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form id="taskForm">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="${pageContext.request.contextPath}/images/tbg.gif">&nbsp;创建任务&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">参考位置：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="proname" name="proname" onchange="getAnalysis();">
			<option >请选择项目----</option>
		</select>-
		<input type="text" name="analysisname" id="analysisname"/>-
		<select id="modeleFk" name="modeleFk" onchange="getFunctions();">
			<option >请选择模块----</option>

		</select>-
		<select id="funFk" name="funFk">
			<option >请选择功能---</option>

		</select>
		</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">任务标题：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="tasktitle"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">开始时间：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="starttime"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">结束时间：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="endtime"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">执行者：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="empFk2" id="empFk2">
			<option >请选择-----</option>

		</select></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">优先级：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="level">
			<option>高</option>
			<option>中</option>
			<option>低</option>
			<option>暂缓</option>
		</select></td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >详细说明：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea rows=10 cols=130 name="remark"></textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<%--<a href="javascript:saveTask();" class="coolbg">保存</a>--%>
	<input type="button" id="btn1" value="保存"  />

</td>
</tr>
</table>

</form>
  

</body>
</html>