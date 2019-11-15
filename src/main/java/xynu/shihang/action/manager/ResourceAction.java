package xynu.shihang.action.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xynu.shihang.entity.Sources;
import xynu.shihang.service.manager.ResourceService;
import xynu.shihang.utils.OAResult;

import java.util.List;

@Controller
@RequestMapping(value = "/resource")
public class ResourceAction {

    @Autowired
    private ResourceService resourceService;

    /**
     *
     * @param page
     * @param id   用来接收资源id，  这个有特别的用途，比如在添加资源的时候，可以作为父菜单id，即pid，
     * @param model
     * @return
     */

    @RequestMapping(value = "/{page}")
    public String forwardManagerResourcePage(@PathVariable(value = "page") String page,
                                             @RequestParam(value="id",defaultValue = "0") int id, Model model){
model.addAttribute("id",id);
        return "manager/"+page;
    }

    /**
     * 得到所有的资源  即系统中所有的功能
     *
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/getRootSources")
    public List<Sources> getRootSources(@RequestParam(value = "pid",defaultValue = "0") int pid){

        return  resourceService.getRootSources(pid);
    }

    /**
     * 添加资源
     * @param sources
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/addSources")
    public OAResult addSources(Sources sources){


        return resourceService.addSources(sources);
    }

    /**
     * 根据id查询得到资源
     * @param id
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/getOneSource")
    public Sources getOneSourceByid(int id){

        Sources sources = resourceService.getOneSourceByid(id);
        return  sources;

    }

    @ResponseBody
    @RequestMapping(value = "/updateSource")
    public OAResult updateSource(Sources sources){

        return  resourceService.updateSource(sources);
    }


    @ResponseBody
    @RequestMapping(value = "/deleteSourcesById")
    public OAResult deleteSourcesById(int id){

        OAResult oaResult = resourceService.deleteSourcesById(id);
        return oaResult;
    }
}
