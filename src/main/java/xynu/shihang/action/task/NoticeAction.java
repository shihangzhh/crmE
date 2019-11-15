package xynu.shihang.action.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xynu.shihang.entity.Notice;
import xynu.shihang.service.task.NoticeService;
import xynu.shihang.utils.OAResult;
import xynu.shihang.utils.PageView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
@RequestMapping(value = "/notice")
public class NoticeAction {

    @Autowired
    private NoticeService noticeService;

    @Value("${pageSize}")
    private  int pageSize;

    @RequestMapping(value = "/{page}")
    public String fowardArchivesPage(@PathVariable(value = "page") String page){

        return "task/"+page;
    }


    @ResponseBody
    @RequestMapping(value = "/add")
    public OAResult addNotice(Notice notice){

        return noticeService.addNotice(notice);
    }


    /**
     *  查询所有通过并进行分页展示
     * @param currentPage
     * @return
     */

    @RequestMapping(value = "/showNotices")
    public String getNotices(@RequestParam(value = "currentPage" ,defaultValue = "1") int currentPage, Model model){
        PageView<Notice> pageView = noticeService.getNotices(currentPage, pageSize);
        model.addAttribute("pageView",pageView);
        return "task/notice" ;
    }


    @ResponseBody
    @RequestMapping(value = "/deleteOneOrMoreNotice")
    public OAResult deleteOneOrMoreNotices(String ids []){

        OAResult oaResult= null;
        if (ids.length != 0){

            for (String id:ids) {
                int nid = Integer.parseInt(id);
                oaResult = noticeService.deleteOneOrMore(nid);

            }
        }


        return  oaResult;
    }

    @ResponseBody
    @RequestMapping(value = "/getOneNotice")
    public Notice getOneNotice(String nid){

        return noticeService.getOneNotice(nid);
    }

}
