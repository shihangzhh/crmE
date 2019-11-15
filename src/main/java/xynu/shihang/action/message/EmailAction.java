package xynu.shihang.action.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import xynu.shihang.entity.Email;
import xynu.shihang.service.message.EmailService;
import xynu.shihang.utils.OAResult;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/email")
public class EmailAction {

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/{page}")
    public String forwardEmailPage(@PathVariable(value = "page") String page){

        return "messages/"+page;
    }
     @ResponseBody
    @RequestMapping(value = "/sendMail")
    public OAResult sendMail(Email email, String reemp, MultipartFile file, HttpServletRequest request) throws  Exception{


        OAResult   oaResult = emailService.sendMail(email, reemp, file, request);
        return oaResult;
    }


}
