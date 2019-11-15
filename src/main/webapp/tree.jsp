
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/skin/js/jquery-3.4.1.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ztree/zTreeStyle.css">
    <script src="${pageContext.request.contextPath}/ztree/jquery.ztree.all-3.5.min.js"></script>

    <script>
         $(function(){
             var setting = {view:{showLine:false},check: { enable: true }};
             var zNodes =[
                 {name:"oa系统菜单",open:false,children:[{name:"项目管理",open:true,
                         children:[
                              {name:"项目基本信息管理"},
                              {name:"需求分析管理"}
                              ]}]}
             ];
             $.fn.zTree.init($("#mytree"), setting, zNodes);
         });
    </script>
</head>
<body>
      <ul id="mytree" class="ztree">


      </ul>
</body>
</html>
