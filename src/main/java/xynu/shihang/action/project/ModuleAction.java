package xynu.shihang.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xynu.shihang.entity.Module;
import xynu.shihang.service.project.ModuleService;
import xynu.shihang.utils.OAResult;

import java.util.List;

@Controller
@RequestMapping(value = "/module")
public class ModuleAction {

    @Autowired
    private ModuleService moduleService;

    @ResponseBody
    @RequestMapping(value = "/saveProjectModule")
    public OAResult saveProjectModule(Module module){

        return moduleService.saveProjectModule(module);
    }
    @ResponseBody
    @RequestMapping(value = "/analysis")
    public List<Module> getModuleByAnalysisId(int id){
        return moduleService.getModuleByAnalysisId(id);
    }

}
