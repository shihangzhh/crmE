package xynu.shihang.service.message;

import xynu.shihang.entity.Evaluate;
import xynu.shihang.entity.Forumpost;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ForumpostService {

    public int  addForumpost(Forumpost forumpost, HttpSession session);

    public List<Forumpost> getCurrentEmplForumposts(HttpSession session);

    public List<Forumpost> getTopSixForumposts(int flag);

    public Forumpost getForumAndEvaByForumId(int forumId);

    public int addEvaluate(Evaluate evaluate,HttpSession session);

}
