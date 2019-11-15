<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>main</title>
<base target="_self">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/main.css" />
	<script type="application/javascript" src="${pageContext.request.contextPath}/skin/js/jquery-3.4.1.js"></script>
<script type="text/javascript">

	function get_date(source){

	    var date = new Date(source);
	    var year = date.getFullYear();
	    var month = formatDate(date.getMonth()+1);
	    var day = formatDate(date.getDate()) ;
        return year+"-"+month+"-"+day;
	};

    function formatDate(value){
        if(value<10)
        {
            return "0"+value;
        }else{
            return value;
        }
    };



	$(function () {

	    //查看通告
		$.ajax({
			url:"${pageContext.request.contextPath}/main/getTopThreeNotice",
			type:"get",
			dataType:"json",
			cache:false,
			success:function (data) {
			    //alert(data)

				$.each(data,function (index,element) {

                    var li="\t<li class=\"ue-clear\">\n" +
                        "\t\t\t\t\t\t<span>"+get_date(element.ndate)+"</span>&nbsp;&nbsp;&nbsp;<a href=\"javascript:showWindow("+element.nid+");\" class=\"notice-title\">"+element.ntitle+"</a>\n" +
                        "\t\t\t\t\t</li>";
                    $("#notice-list").append(li);
                })

				
            }
		
			
		});

		//查询还有几个未完成的任务

            $.ajax({
               url:"${pageContext.request.contextPath}/main/selectUnfishedTask",
                type:"get",
                data:{"status":3},
                dataType:"json",
                cache:false,
                success:function (data) {
                   var td = "<td>您有"+data+"个任务未完成……&nbsp;\n" +
                       "\t\t\t</td>"
                    $("#taskTr").append(td);
                }
                
            });
            

            //查询帖子
        $.ajax({
            type:'GET',
            url:'${pageContext.request.contextPath}/forum/allEmployee/forumposts',
            data:{"flag":6},
            dataType:'json',
            success:function(data) {

                var tr="";
                var td="";
                $(data).each(function(index,elem){

                    td=td+"<td>\n" +
                        "\t\t\t\t<ul class=\"notice-list\">\n" +
                        "\t\t\t\t\t<li class=\"ue-clear\">\n" +
                        "\t\t\t\t\t\t<span><img src=\"${pageContext.request.contextPath}/images/tx.png\" height=\"50px\" width=\"50px\"/></span> 发布时间："+get_date(elem.createtime)+"\n" +
                        "\t\t\t\t\t\t<a href=\"${pageContext.request.contextPath}/forum/forumId/forumEval?forumid="+elem.forumid+"\"class=\"notice-title\">"+elem.forumtitle+"</a>\n" +
                        "\t\t\t\t\t</li>\n" +
                        "\t\t\t\t</ul>\n" +
                        "\t\t\t</td>";

                    if((index+1)%3==0 || (index+1)==$(data).length){
                        tr="<tr bgcolor=\"#FFFFFF\">";
                        tr=tr+td+"</tr>";
                        $("#forum").before(tr);
                        tr="";
                        td="";
                    }
                });
            }
        });



    });

    // 弹窗
    function showWindow(obj) {

        $('#showdiv').show();  //显示弹窗
        $('#cover').css('display','block'); //显示遮罩层
        $('#cover').css('height',document.body.clientHeight+'px'); //设置遮罩层的高度为当前页面高度

        $.ajax({
            type:'GET',
            url:'${pageContext.request.contextPath}/notice/getOneNotice',
            data:{"nid":obj},
            dataType:'json',
            success:function(resultData) {
                $("#ntitle").text(resultData.ntitle);
                $("#content").text(resultData.remark);
            }
        });
    }
    // 关闭弹窗
    function closeWindow() {
        $('#showdiv').hide();  //隐藏弹窗
        $('#cover').css('display','none');   //显示遮罩层
    }


</script>

</head>
<body leftmargin="8" topmargin='8'>

<!-- 遮罩层 -->
<div id="cover" style="background: #000; position: absolute; left: 0px; top: 0px; width: 100%; filter: alpha(opacity=30); opacity: 0.3; display: none; z-index: 2 ">

</div>
<!-- 弹窗 -->
<div id="showdiv" style="width: 60%; margin: 0 auto; height:500px; border: 1px solid #999; display: none; position: absolute; top: 20%; left: 20%; z-index: 3; background: #fff">
    <!-- 标题 -->
    <div id="ntitle" style="background: #F8F7F7; width: 100%; height: 2rem; font-size: 0.65rem; line-height: 2rem; border: 1px solid #999; text-align: center;" >

    </div>
    <!-- 内容 -->
    <div id="content" style="text-indent: 50px; height: 4rem; font-size: 0.5rem; padding: 0.5rem; line-height: 1rem; ">

    </div>
    <!-- 按钮 -->
    <div style="background: green; width: 10%; margin: 0 auto; height: 1.5rem; line-height: 1.5rem; text-align: center;color: #fff;margin-top: 28rem; -moz-border-radius: .128rem; -webkit-border-radius: .128rem; border-radius: .128rem;font-size: .59733rem;cursor: pointer" onclick="closeWindow()">
        关闭
    </div>
</div>

	<table width="98%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td><div style='float: left'>
					<img height="14" src="${pageContext.request.contextPath}/skin/images/frame/book1.gif" width="20" />&nbsp;欢迎使用项目平台管理系统
				</div>
				<div style='float: right; padding-right: 8px;'>
					<!--  //保留接口  -->
				</div></td>
		</tr>
		<tr>
			<td height="1" background="${pageContext.request.contextPath}/skin/images/frame/sp_bg.gif"
				style='padding: 0px'></td>
		</tr>
	</table>

    <table width="98%" align="center" border="0" cellpadding="4"
		cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom: 8px">
		<tr>
			<td colspan="2" background="${pageContext.request.contextPath}/skin/images/frame/wbg.gif"
				bgcolor="#EEF4EA" class='title'>
				<div style='float: left'>
					<span>快捷操作</span>
				</div>
				<div style='float: right; padding-right: 10px;'></div>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td height="30" colspan="2" align="center" valign="bottom"><table
					width="100%" border="0" cellspacing="1" cellpadding="1">
					<tr>
						<td width="15%" height="31" align="center"><img
                                src="${pageContext.request.contextPath}/skin/images/frame/qc.gif" width="90" height="30" /></td>
						<td width="85%" valign="bottom"><div class='icoitem'>
								<div class='ico'>
									<img src='${pageContext.request.contextPath}/skin/images/frame/addnews.gif' width='16' height='16' />
								</div>
								<div class='txt'>
									<a href='${pageContext.request.contextPath}/project/project-base'><u>查看项目信息</u></a>
								</div>
							</div>
							<div class='icoitem'>
								<div class='ico'>
									<img src='${pageContext.request.contextPath}/skin/images/frame/menuarrow.gif' width='16'
                                         height='16' />
								</div>
								<div class='txt'>
									<a href='${pageContext.request.contextPath}/task/taskInfor/task-my'><u>查看任务</u></a>
								</div>
							</div>
							<div class='icoitem'>
								<div class='ico'>
									<img src='${pageContext.request.contextPath}/skin/images/frame/manage1.gif' width='16' height='16' />
								</div>
								<div class='txt'>
									<a href='${pageContext.request.contextPath}/task/task-add'><u>发布任务</u></a>
								</div>
							</div>
							<div class='icoitem'>
								<div class='ico'>
									<img src='${pageContext.request.contextPath}/skin/images/frame/file_dir.gif' width='16'
                                         height='16' />
								</div>
								<div class='txt'>
									<a href='message-give.html'><u>收件箱</u></a>
								</div>
							</div>
							<div class='icoitem'>
								<div class='ico'>
									<img src='${pageContext.request.contextPath}/skin/images/frame/part-index.gif' width='16'
                                         height='16' />
								</div>
								<div class='txt'>
									<a href='${pageContext.request.contextPath}/personInforPage'><u>个人信息</u></a>
								</div>
							</div>
							<div class='icoitem'>
								<div class='ico'>
									<img src='${pageContext.request.contextPath}/skin/images/frame/manage1.gif' width='16' height='16' />
								</div>
								<div class='txt'>
									<a href='${pageContext.request.contextPath}/forwardModiPass'><u>修改密码</u></a>
								</div>
							</div></td>
					</tr>
				</table></td>
		</tr>
	</table>



	<table width="98%" align="center" border="0" cellpadding="3"
		cellspacing="1" bgcolor="#CBD8AC"
		style="margin-bottom: 8px; margin-top: 8px;">
		<tr>
			<td background="${pageContext.request.contextPath}/skin/images/frame/wbg.gif" bgcolor="#EEF4EA"
                class='title'><span>待完成任务</span></td>
		</tr>
		<tr bgcolor="#FFFFFF" id="taskTr">
		<%--	<td>您有&lt;%&ndash;<a href="javascript:selectUnfishedTask(2);"><font color="red">2</font></a>&ndash;%&gt;个任务未完成……&nbsp;
			</td>--%>
		</tr>
	</table>



	<table width="98%" align="center" border="0" cellpadding="3"
		cellspacing="1" bgcolor="#CBD8AC"
		style="margin-bottom: 8px; margin-top: 8px;">
		<tr>
			<td background="${pageContext.request.contextPath}/skin/images/frame/wbg.gif" bgcolor="#EEF4EA"
                class='title'><span>未读消息</span></td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td>您有<a href=""><font color="red">10</font></a>条未读消息……&nbsp;
			</td>
		</tr>
	</table>


	

	<table width="98%" align="center" border="0" cellpadding="3"
		cellspacing="1" bgcolor="#CBD8AC"
		style="margin-bottom: 8px; margin-top: 8px;">
		<tr>
			<td background="${pageContext.request.contextPath}/skin/images/frame/wbg.gif" bgcolor="#EEF4EA"
                class='title'><span>通知公告</span></td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td>
				<ul class="notice-list" id="notice-list">
					<%--<li class="ue-clear">
					<span>12-15</span>&nbsp;&nbsp;&nbsp;<a href="#"class="notice-title">中国移动关于设立作风建设监督举报电话的公告</a>	
					</li>
					<p>
					<li class="ue-clear">
					<span>12-15</span>&nbsp;&nbsp;&nbsp;<a href="#"class="notice-title">中国移动关于设立作风建设监督举报电话的公告</a>	
					</li>
					<p>
					<li class="ue-clear">
					<span>12-15</span>&nbsp;&nbsp;&nbsp;<a href="#"class="notice-title">中国移动关于设立作风建设监督举报电话的公告</a>	
					</li>--%>
				</ul>
			</td>
		</tr>
	</table>

    <table width="98%" align="center" border="0" cellpadding="3"
		cellspacing="1" bgcolor="#CBD8AC"
		style="margin-bottom: 8px; margin-top: 8px;">
		<tr>
			<td background="${pageContext.request.contextPath}/skin/images/frame/wbg.gif" bgcolor="#EEF4EA"
                class='title'><span>员工论坛</span></td>
		</tr>
		<tr bgcolor="#FFFFFF" id="forum">
			<%--<td>
				<ul class="notice-list">
					<li class="ue-clear">
					     <span><img src="${pageContext.request.contextPath}/images/tx.png" height="50px" width="50px"/></span> 发布时间：2018-12-25
					     <a href="../../role.jsp" class="notice-title">招租信息</a>
					</li>
					<p>
					<li class="ue-clear">
					     <span><img src="${pageContext.request.contextPath}/images/tx.png" height="50px" width="50px"/></span> 发布时间：2018-12-25
					     <a href="../../role.jsp" class="notice-title">招租信息</a>
					</li>
					<p>
					<li class="ue-clear">
					     <span><img src="${pageContext.request.contextPath}/images/tx.png" height="50px" width="50px"/></span> 发布时间：2018-12-25
					     <a href="../../role.jsp" class="notice-title">招租信息</a>
					</li>
					<p>
					<li class="ue-clear">
					     <span><img src="${pageContext.request.contextPath}/images/tx.png" height="50px" width="50px"/></span> 发布时间：2018-12-25
					     <a href="../../role.jsp" class="notice-title">招租信息</a>
					</li>
				</ul>
			</td>--%>
		</tr>
	</table>

</body>
</html>