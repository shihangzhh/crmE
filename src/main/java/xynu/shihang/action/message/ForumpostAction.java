package xynu.shihang.action.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xynu.shihang.entity.Evaluate;
import xynu.shihang.entity.Forumpost;
import xynu.shihang.entity.Msg;
import xynu.shihang.service.message.ForumpostService;
import xynu.shihang.utils.OAResult;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping(value = "/forum")
@Controller
public class ForumpostAction {

    @Autowired
    private ForumpostService forumpostService;



    @RequestMapping(value = "/{page}")
    public String forwardEmailPage(@PathVariable(value = "page") String page){
        return "messages/"+page;

    }

   @RequestMapping(value = "/add")
    public String addForumpost(Forumpost forumpost, HttpSession session){

       forumpostService.addForumpost(forumpost,session);
        return  "messages/show";
    }

    /**
     * 获取登录系统用户所发的帖子
     * @param session
     * @return
     */

    @ResponseBody
@RequestMapping(value = "/currentEmployee/forumposts")
    public List<Forumpost> getCurrentEmplForumposts(HttpSession session){

        List<Forumpost> forumpostList = forumpostService.getCurrentEmplForumposts(session);
        return forumpostList;
    }

    /**
     * 获取所有帖子的前6个
     * @param flag
     * @return
     */

@ResponseBody
    @RequestMapping(value = "/allEmployee/forumposts")
    public List<Forumpost> getTopSixForumposts(int flag){

    List<Forumpost> forumposts = forumpostService.getTopSixForumposts(flag);

    return  forumposts;
    }

    /**
     * 根据帖子的id查询帖子的详细内容包含评价
     * @return
     */

    @RequestMapping(value = "/forumId/forumEval")
    public String getForumAndEvaByForumId(int forumid, Model model){
        Forumpost forum = forumpostService.getForumAndEvaByForumId(forumid);
        model.addAttribute("forum",forum);
        return "messages/forum-reply";
    }

    /**
     * 保存评价并且调转到评价页面
     * @param evaluate
     * @return
     */

    @RequestMapping(value = "/evaluate/add")
    public String addEvaluate(Evaluate evaluate,HttpSession session){

         forumpostService.addEvaluate(evaluate,session);

        return "redirect:/forum/forumId/forumEval?forumid="+evaluate.getForumfk();
    }

}
