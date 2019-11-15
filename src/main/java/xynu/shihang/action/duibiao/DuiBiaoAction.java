package xynu.shihang.action.duibiao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xynu.shihang.entity.Datacollect;
import xynu.shihang.service.duibiao.DuiBiaoService;

import java.util.List;

@Controller
@RequestMapping(value = "/duibiao")
public class DuiBiaoAction {

    @Autowired
    private DuiBiaoService duiBiaoService;

    @RequestMapping(value = "/{page}")
    public String forwardDuiBiaoPage(@PathVariable(value = "page") String page){

        return "duibiao/"+page;
    }

    @ResponseBody
    @RequestMapping(value = "/getInfoGroup")
    public List<Datacollect> getInfoGroup(){

        List<Datacollect> datacollectList = duiBiaoService.getInfoGroup();
        return datacollectList;
    }

    @ResponseBody
@RequestMapping(value = "/getOneBydaid")
    public  Datacollect getOneBydaid(int daid){

        Datacollect datacollect = duiBiaoService.getOneBydaid(daid);

        return datacollect;
    }
}
