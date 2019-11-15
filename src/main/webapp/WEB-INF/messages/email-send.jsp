<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>发信息</title>
<script type="application/javascript" src="${pageContext.request.contextPath}/skin/js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}\skin\js\jquery.form.js"></script>
<LINK href="${pageContext.request.contextPath}/skin/css/theAlert.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/theAlert.js"></script>
<script type="text/javascript">

    $(function(){
        $.ajax({
			type:'GET',
            url:"${pageContext.request.contextPath}/employees/showReceiveEmp",
            dataType:"json",
			cache:false,
            success:function(data){
                $(data).each(function(index,elem){
					  var option="<option value="+elem.eid+">"+elem.ename+"</option>"
                      $("#empFk2").append(option);
				});
            },
        });

    });
     function commit(){

         $("#form14").ajaxForm();
         $("#form14").ajaxSubmit(function(data){
             if(data.status==200) {
                     theAlert(data.msg,"alert",function(){
                         window.location="${pageContext.request.contextPath}/email/email";
					 });
             }else{
                 theAlert(data.msg,"alert");
             }

         });


	 }
	 //根据员工id获取其邮箱信息
    function getEmail(){

        $("#reemp").empty();
         var id = $("#empFk2").val();
         //alert(id);
        
        $.ajax({
           url:"${pageContext.request.contextPath}/archives/getArchivesByEid",
            type:"get",
            dataType:"json",
            data:{"id":id},
            cache:false,
            success:function (archives) {

               if (archives == null){
                   alert(archives);
                   $("#reemp").val("");
               }else{
                   $("#reemp").val(archives.email);
               }
            }

        });

     };
	 

</script>
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
    当前位置:信息箱>>发信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form id="form14" name="form2" action="${pageContext.request.contextPath}/email/sendMail" method="post" enctype="multipart/form-data">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="${pageContext.request.contextPath}/skin/images/tbg.gif">&nbsp;发信息&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">收件人：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="empFk2" id="empFk2" onchange="getEmail();">
			 <option>选取收件人</option>
		</select>

	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">收件人邮箱：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input  name="reemp" size="50" id="reemp" readonly />
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">标题：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="title" size="50"/>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">内容：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<textarea rows=15 cols=130 name="emailcontent"></textarea>
	</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >附件：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<input type="file" name="file"/>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:commit();" class="coolbg">发送</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>