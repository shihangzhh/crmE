package xynu.shihang.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xynu.shihang.entity.Employee;
import xynu.shihang.entity.Notice;
import xynu.shihang.service.other.MainService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/main")
public class MainAction {


    @Autowired
    private MainService mainService;

@RequestMapping(value = "/{path}")
    public String mainForward(@PathVariable(value = "path") String path){

        return "main/"+path;
    }


    /**
     * 查看前三条通告
     *
     */
    @ResponseBody
    @RequestMapping(value = "/getTopThreeNotice")
    public List<Notice> getTopThreeNotice(){
        return mainService.getTopThreeNotice();
    }


    /**
     * 查看未完成任务的个数
     * @param status
     * @return
     */
    @ResponseBody
@RequestMapping(value = "/selectUnfishedTask")
      public int   selectUnfishedTask(int status, HttpSession session){
        Employee empl = (Employee) session.getAttribute("activeUser");
        int num = mainService.selectUnfishedTask(status, empl.getEid());
        return num;
      }
}
