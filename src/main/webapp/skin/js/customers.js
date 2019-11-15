//全选
function selectAll(){

   var oInputs =  $("input[type=checkbox][name=id]");
  // alert(oInputs.length);
    $.each(oInputs,function (index,element) {
        element.checked = true;
    });

};

//全不选
function  unselectAll() {
    var oInputs =  $("input[type=checkbox][name=id]");
    // alert(oInputs.length);
    $.each(oInputs,function (index,element) {
        element.checked = false;
    });

};

//反选
function resSelect() {
    var oInputs =  $("input[type=checkbox][name=id]");
    // alert(oInputs.length);
    $.each(oInputs,function (index,element) {

        if (element.checked){
            element.checked = false;
        }else{
            element.checked = true;

        }

    });

}



//将日期进行格式化
function formatDate(value) {

if (value < 10){
    return "0"+value;
}else {
    return value;
}

};

//将日期进行格式化
function getdate(source) {

    var date = new Date(source);
    var year = date.getFullYear();
    var month =  formatDate(date.getMonth()+1) ;
    var day = formatDate(date.getDate()) ;
    return year+"-"+month+"-"+day;

};

//首页
function  homePage(context,currentPage) {
    request_customer(context,1)
};
//上一页
function upPage(context ,currentPage) {
    if (currentPage>1){
        currentPage = currentPage-1;
        request_customer(context,currentPage);
    }else{
        request_customer(context,1);
    }

};

function downPage(context,currentPage,totalPage) {

    if (currentPage < totalPage) {
        currentPage = currentPage+1;
        request_customer(context,currentPage);
    }else{
        request_customer(context,totalPage);
    }


};

function  lastPage(context,totalPage) {
    request_customer(context,totalPage);
}



function request_customer(context,currentPage){

    $.ajax({
        url:context+"/customer/show?currentPage="+currentPage,
        type:"post",
        // data:"",
        dataType:"json",
        cache:false,
        success:function(data) {
            //清除数据

            $("#content tr:not(:lt(2)):not([id=showData]) ").remove();
            $.each(data.dataList,function (index,element) {

                var tr = "<tr align='center' bgcolor=\"#FFFFFF\" onMouseMove=\"javascript:this.bgColor='#FCFDEE';\" onMouseOut=\"javascript:this.bgColor='#FFFFFF';\" height=\"22\" >\n" +
                    "\t<td><input name=\"id\" type=\"checkbox\" id=\"id\" value=\""+element.id+"\" class=\"np\"></td>\n" +
                    "\t<td>"+((data.currentPage-1)*(data.pageSize)+index+1)+"</td>\n" +
                    "\t<td>"+element.companyperson+"</td>\n" +
                    "\t<td align=\"center\">"+element.comname+"</td>\n" +
                    "\t<td>"+getdate(element.addtime)+"</td>\n" +
                    "\t<td>"+element.comphone+"</td>\n" +
                    "\t<td><a href=\""+context+"/customer/findCustomer?id="+element.id+"\">编辑</a> | <a href=\""+context+"customer/forward/customer-look\">查看详情</a></td>\n" +
                    "</tr>";
                $("#showData").before(tr);

            });

            //设置分页数据
            var cutPage="<tr align=\"right\" bgcolor=\"#EEF4EA\">\n" +
                "<td height=\"36\" colspan=\"12\" align=\"center\">\n" +
                "   <a href=\"javascript:homePage('"+context+"',"+data.currentPage+");\">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "   <a href=\"javascript:upPage('"+context+"',"+data.currentPage+")\">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "   <a href=\"javascript:downPage('"+context+"',"+data.currentPage+","+data.totalPages+")\">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "   <a href=\"javascript:lastPage('"+context+"',"+data.totalPages+")\">尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                "</td>\n" +
                "</tr>";
            $("#showData").after(cutPage);


        }
    });
}

function searchCustomer() {

  //  alert("aaaaa");
    var searchContextType = $("#searchContextType").val();
    var searchContext = $("#searchContext").val();
    var orderby = $("#orderby").val();
//alert(orderby);
    $.ajax({
        url:  "/oa/customer/searchCustomer",
        type: "get",
         data:{"searchContextType":searchContextType,"searchContext":searchContext,"orderby":orderby},
        dataType: "json",
        cache: false,
        success: function (data) {
           //  alert(data.length);
            $("#content tr:not(:lt(2)):not([id=showData]) ").remove();
            $.each(data,function (index,element) {

                var tr = "<tr align='center' bgcolor=\"#FFFFFF\" onMouseMove=\"javascript:this.bgColor='#FCFDEE';\" onMouseOut=\"javascript:this.bgColor='#FFFFFF';\" height=\"22\" >\n" +
                    "\t<td><input name=\"id\" type=\"checkbox\" id=\"id\" value=\""+element.id+"\" class=\"np\"></td>\n" +
                    "\t<td>"+element.id+"</td>\n" +
                    "\t<td>"+element.companyperson+"</td>\n" +
                    "\t<td align=\"center\">"+element.comname+"</td>\n" +
                    "\t<td>"+getdate(element.addtime)+"</td>\n" +
                    "\t<td>"+element.comphone+"</td>\n" +
                    "\t<td><a href=\""+context+"/customer/findCustomer?id="+element.id+"\">编辑</a> | <a href=\""+context+"/customer/forward/customer-look\">查看详情</a></td>\n" +
                    "</tr>";
                $("#showData").before(tr);

            });

        }
    });

}





