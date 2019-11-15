<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>main</title>
    <base target="_self">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/main.css" />
    <style type="text/css">
        li:hover{background:lightgray}

    </style>
    <script type="application/javascript" src="${pageContext.request.contextPath}/skin/js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        function get_date(source){
            var date=new Date(parseInt(source));
            var year= date.getFullYear();//获取完整的年份(4位,1970)
            var month= get_month(date.getMonth()+1);//获取月份(0-11,0代表1月,用的时候记得加上1)
            var day=get_month(date.getDate());//获取日(1-31)
            return year+"-"+month+"-"+day;
        }
        function get_month(month){
            if(month<10){
                return "0"+month;
            }else{
                return month;
            }
        }
             $(function(){
                 $.ajax({
                     type:'GET',
                     url:'${pageContext.request.contextPath}/forum/currentEmployee/forumposts',
                     dataType:'json',
                         success:function(resultData){

                          $(resultData).each(function(index,item){

                              var tr="<tr bgcolor=\"#FFFFFF\">\n" +
                                  "       <td>\n" +
                                  "            <ul class='notice-list'>\n" +
                                  "                <li class='ue-clear'>\n" +
                                  "                    <span id='sp'>\n" +
                                  "                        <img src='${pageContext.request.contextPath}/images/tx.png' height='50px' width='50px'/>\n" +
                                  "                    </span>\n" +
                                  "                    <font>发布时间:"+get_date(item.createtime)+"</font>\n" +
                                  "                    <a href='role.jsp 'class='notice-title'>"+item.forumcontent+"</a>\n" +
                                  "                </li>\n" +
                                  "            </ul>\n" +
                                  "        </td>\n" +
                                  "    </tr>";
                              $("#ff").before(tr);

                           });
                     }

                 });
             })


    </script>

</head>
<body leftmargin="8" topmargin='8'>

<table width="98%" align="center" border="0" cellpadding="3"
       cellspacing="1" bgcolor="#CBD8AC"
       style="margin-bottom: 8px; margin-top: 8px; background: white" id="tb">
    <tr id="sp">
        <td colspan="3" background="${pageContext.request.contextPath}/skin/images/frame/wbg.gif" bgcolor="#EEF4EA" class='title'>
            <span>员工论坛</span> |<span><a href='${pageContext.request.contextPath}/forum/forum-add'>发帖</a></span>
        </td>
    </tr>

  <!--
  <tr bgcolor="#FFFFFF" id="ttr">
       <td>
            <ul class='notice-list'>
                <li class='ue-clear'>
                    <span id='sp'>
                        <img src='${pageContext.request.contextPath}/images/tx.png' height='50px' width='50px'/>
                    </span>
                    <font>发布时间：2018-12-25</font>
                    <a href='role.jsp 'class='notice-title'>招租信息</a>
                </li>
            </ul>
        </td>
    </tr>


-->

    <tr id="ff"></tr>
</table>

</body>
</html>