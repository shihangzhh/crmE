<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>对标管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
    <script type="application/javascript" src="${pageContext.request.contextPath}/skin/js/jquery-3.4.1.js"></script>
    <script type="application/javascript" src="${pageContext.request.contextPath}/eacharjs/echarts.common.min.js"></script>
</head>
<body leftmargin="8" topmargin="8" background='${pageContext.request.contextPath}/skin/images/allbg.gif'>
<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="${pageContext.request.contextPath}/skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:对标管理>>对标分析
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<div id="box" style="width:800px;height:600px;"></div>

<script type="application/javascript">
    var mychart=echarts.init($("#box")[0]);

    var option={
        title:{
            text:'营业额分析图'
        },
        legend:{
            data:['营业额']
        },
        xAxis:{
            data:[]
        },
        yAxis:{
            type:'value',
            axisLabel:{
                 formatter:'{value}(亿)',
            },
        },

    };
    mychart.setOption(option);
    //1、显示数据加载提示动画

    //准备存储x轴下标
    var names=[];
    //准备成绩数组
    var scores=[];
    //2、启动ajax，请求数据
    $.ajax({
        type:'post',
        url:'${pageContext.request.contextPath}/duibiao/showPng',
        dataType:'json',
        success:function(data){
            //判断返回值是否存在
            if(data){
                //json，遍历
                for(var i=0;i<data.length;i++){
                    var date = new Date(data[i].datime);
                    names.push(date.getFullYear()+"年");
                    scores.push(data[i].daturnover);
                }
                //隐藏加载动画
                mychart.hideLoading();
                //设置图表属性，把相关数据设置进去
                mychart.setOption({
                    xAxis:{
                        data:names
                    },
                    series:[{
                        name:'营业额',
                        type:'bar',
                        data:scores
                    }]
                });
            }
        }
    });
</script>
</body>
</html>