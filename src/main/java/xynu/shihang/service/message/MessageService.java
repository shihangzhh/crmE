package xynu.shihang.service.message;

import xynu.shihang.entity.Msg;
import xynu.shihang.utils.OAResult;

import javax.servlet.http.HttpSession;

public interface MessageService {

    public OAResult sendMsg(Msg msg,HttpSession session) throws Exception;
}
