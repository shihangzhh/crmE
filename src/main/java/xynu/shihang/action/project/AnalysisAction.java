package xynu.shihang.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xynu.shihang.entity.Analysis;
import xynu.shihang.service.project.AnalysisService;
import xynu.shihang.utils.OAResult;

import java.util.List;

@Controller
@RequestMapping(value = "/analysis")
public class AnalysisAction {

    @Autowired
    private AnalysisService analysisService;

    @ResponseBody
    @RequestMapping(value = "/saveAnalysis")
     public OAResult saveProjectAnalysis(Analysis analysis){

        return analysisService.saveProjectAnalysis(analysis);

    }

    @ResponseBody
    @RequestMapping(value = "/show")
    public List<Analysis> getAllProjectAnalisis(){

        List<Analysis> analysisList = analysisService.getAllProjectAbalisis();
        return  analysisList;
    }
    @ResponseBody
    @RequestMapping(value = "/one")
    public Analysis queryOneAnalysis(Integer id){
        Analysis analysis = analysisService.queryOneAnalysis(id);

        return analysis;
    }
}
