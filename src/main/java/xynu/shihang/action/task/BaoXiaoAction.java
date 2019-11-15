package xynu.shihang.action.task;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xynu.shihang.entity.Baoxiao;
import xynu.shihang.entity.Employee;
import xynu.shihang.service.task.BaoXiaoService;
import xynu.shihang.utils.OAResult;
import xynu.shihang.utils.PageView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;


@RequestMapping(value = "task/baoxiao")
@Controller
public class BaoXiaoAction {

    @Autowired
 private BaoXiaoService baoXiaoService;

    @Value("${pageSize}")
    private int pageSize;

    @RequestMapping(value = "/{page}")   //跳转到报销模块下的页面
    public String forwardBaoXiaoPage(@PathVariable(value = "page") String page){

        return "task/"+page;
    }

    @ResponseBody
    @RequestMapping(value = "/saveBaoXiao")
    public OAResult saveBaoXiao(Baoxiao baoxiao,HttpSession session){

        //获取要保存报销员工的id
        Employee employee = (Employee) session.getAttribute("activeUser");
        baoxiao.setEmpFk(employee.getEid());

        return baoXiaoService.saveBaoXiao(baoxiao);
    }
    /*
    默认显示第一页和未审批的情况
     */

    @RequestMapping(value = "/getBaoXiaoTables")
    public String getBaoXiaoTalbeByEid(Model model,HttpSession session,
                                       @RequestParam(value = "currentPage",defaultValue = "1") int currentPage,
                                       @RequestParam(value = "bxstatus",defaultValue = "1") int bxstatus){

        Employee employee = (Employee) session.getAttribute("activeUser");
        PageView<Baoxiao> pageView = baoXiaoService.getBaoXiaoTalbeByEid(employee.getEid(), currentPage, pageSize,bxstatus);
        model.addAttribute("pageView",pageView);
        return "task/mybaoxiao-base";
    }


    @RequestMapping(value = "/showDealBaoXiao")
    public String getDealBaoXiaoTables( @RequestParam(value = "bxstatus",defaultValue = "1") int bxstatus ,Model model){

        List<Baoxiao> baoxiaoList = baoXiaoService.getBaoXiaoTalbesByStatus(bxstatus);
        model.addAttribute("baoxiaoList",baoxiaoList);

        return  "task/baoxiao-task";
    }

    @RequestMapping(value = "/onlyBaoXiao/{page}")
    public String getOneBaoXiao(String bxid,Model model,@PathVariable(value = "page") String page,HttpSession session){

        //获取用户
        Employee employee = (Employee) session.getAttribute("activeUser");

        Baoxiao baoxiao = baoXiaoService.getOneBaoXiao(bxid);
        baoxiao.setEmployee(employee);
        model.addAttribute("baoxiao",baoxiao);
        return  "task/"+page;
    }


    @RequestMapping(value = "/updataBaoXiaoStatus")
    public String updataBaoXiaoStatus(String bxid,int bxstatus,String result,HttpSession session){

        Employee empl = (Employee) session.getAttribute("activeUser");

        baoXiaoService.updataBaoXiaoStatus(bxid,bxstatus,result,empl.getEid());
        return  "task/baoxiao-task";
    }



    @RequestMapping(value = "/upBaoXiaoBoHui")
    public String upBaoXiaoBoHui(Baoxiao baoxiao){

        baoXiaoService.upBaoXiaoBoHui(baoxiao);

        return  "task/mybaoxiao-base";

    }



    @RequestMapping(value = "/deleteOneOrMoreBaoXiao")
    public String deleteOneOrMoreBaoXiao(HttpServletRequest request){
        String[] ids = request.getParameterValues("bxid");

        if (ids != null){
            for (String id: ids) {
                baoXiaoService.deleteOneOrMore(id);
            }
        }


        return "task/mybaoxiao-base";
    }






}
