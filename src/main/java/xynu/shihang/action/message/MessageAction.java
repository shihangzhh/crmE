package xynu.shihang.action.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xynu.shihang.entity.Msg;
import xynu.shihang.service.message.MessageService;
import xynu.shihang.utils.OAResult;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/message")
public class MessageAction {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/{page}")
    public String forwardEmailPage(@PathVariable(value = "page") String page){
        return "messages/"+page;

    }

    /**
     * 定时发送消息  这个东西比较重要
     * @param msg

     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sendMsg")
    public OAResult sendMsg(Msg msg,HttpSession session) throws Exception{

        OAResult oaResult = messageService.sendMsg(msg,session);

        return oaResult;
    }

}
