package xynu.shihang.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xynu.shihang.service.other.LoginService;
import xynu.shihang.utils.OAResult;

@Controller
public class LoginAction {

    @Autowired
    private LoginService loginService;

    //跳转到首页
    @RequestMapping(value ={"/","/login","/index"} )
    public String forwardIndexPage(){

        return "login/login";
    }


    @RequestMapping(value = "/forwardModiPass")
    public String forwardModifyPassword(){

        return "login/modpassword";
    }

    @ResponseBody
    @RequestMapping(value = "/modiPass")
    public OAResult modifyPassword(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password){


         OAResult oaResult = loginService.modifyPassword(username, password);

        return oaResult;
    }

    @RequestMapping(value = "/personInforPage")
    public String forwardPersonInforPage(){

        return  "login/info";
    }
}
