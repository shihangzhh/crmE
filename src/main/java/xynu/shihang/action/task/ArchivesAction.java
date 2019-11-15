package xynu.shihang.action.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import xynu.shihang.entity.Archives;
import xynu.shihang.service.task.ArchivesService;
import xynu.shihang.utils.OAResult;
import xynu.shihang.utils.PageView;

@Controller
@RequestMapping(value = "/archives")
public class ArchivesAction {

    @Value("${pageSize}")
    private int pageSize;

    @Autowired
    private ArchivesService archivesService;

    @RequestMapping(value = "/{page}")
    public String fowardArchivesPage(@PathVariable(value = "page") String page){

        return "task/"+page;
    }


    @RequestMapping(value = "/getAllArchives")
    public String getAllArchives(@RequestParam(value = "currentPage",defaultValue = "1") int currentPage, Model model){

        PageView<Archives> pageView = archivesService.getAllArchives(currentPage, pageSize);
        model.addAttribute("pageView",pageView);

        return "task/archives";
    }


/**
 * 导入员工档案
  */
@ResponseBody
@RequestMapping(value = "/import/archives/data")
    public OAResult importBatchEmployeeArchives(MultipartFile files){

    OAResult oaResult = archivesService.importBatchEmployeeArchives(files);
    return  oaResult;
    }
@ResponseBody
@RequestMapping(value = "/deleteOneOrMoreArchives")
    public OAResult deleteOneOrMoreArchives(String ids[]){

    OAResult oaResult = null;
    for(String id:ids){
         oaResult = archivesService.deleteOneOrMoreArchives(id);

    }

    return oaResult;
    }

    /**
     * 根据员工id获取其档案信息
     * @param id
     * @return
     */

   @ResponseBody
    @RequestMapping(value = "/getArchivesByEid")
    public Archives getArchivesByEid(int id){

       Archives archives = archivesService.getArchivesByEid(id);

       //如果不做判断的话，如果archives为空，则什么也不会返回
       if (archives == null){
           archives = new Archives();
           archives.setEmail("");
       }
       return archives;
    }

}
