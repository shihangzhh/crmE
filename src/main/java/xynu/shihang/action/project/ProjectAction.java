package xynu.shihang.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xynu.shihang.entity.Employee;
import xynu.shihang.entity.Project;
import xynu.shihang.service.project.ProjectService;
import xynu.shihang.utils.OAResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/project")
public class ProjectAction {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/{path}") //具有result风格的
    public String forwardProjectPage(@PathVariable(value = "path") String path){

        return "project/"+ path;
    }

    @ResponseBody
    @RequestMapping(value = "/saveProject")
    public OAResult saveProject(Project project){

        return projectService.saveProject(project);
    }

    @ResponseBody
    @RequestMapping(value = "/selectProject")
    public List<Project> getAllProjectInfor(){


        return projectService.getAllProjectInfor();
    }

    @ResponseBody
    @RequestMapping(value = "/noAnalyis")
    public List<Project> getProjectNoAnalysis(){

        return projectService.getProjectNoAnalysis();
    }


    //带有项目需求分析的项目
    @ResponseBody
    @RequestMapping(value = "/analysisProject")
    public List<Project> getProjectNeedAnalysis()
    {
        return  projectService.getProjectNeedAnalysis();
    }


    @ResponseBody   //查询带有模块的项目
    @RequestMapping(value = "/module/project")
        public List<Project> getModuleAnalyProject(){

        return  projectService.getModuleAnalyProject();
    }

      @ResponseBody
      @RequestMapping(value = "/function/projects")
    public List<Project> getProjectByFunctonAndEmployee(HttpServletRequest request){

        //根据登录的员工获取用户id
        Employee employee = (Employee) request.getSession().getAttribute("activeUser");

        return  projectService.getProjectByFunctonAndEmployee(employee.getEid());
    }

    @RequestMapping(value = "/getProjectInfoByPid")
    public String getProjectInfoByPid(@RequestParam(value = "pid") int  pid, Model model){

        Project project = projectService.getProjectInfoByPid(pid);
        model.addAttribute("project",project);
        return "project/project-base-edit";
    }


    @ResponseBody
    @RequestMapping(value = "/updateProject")
    public OAResult   updateProject(Project project){

        OAResult oaResult = projectService.updateProject(project);

        return oaResult;
    }

     @RequestMapping(value = "/getProjectInfor")
    public String getProjectInfor(int pid ,Model model){

         Project project = projectService.getProjectInfoByPid(pid);
         model.addAttribute("project",project);
         return "project/project-base-look";
    }


    @ResponseBody
    @RequestMapping(value = "/allProject")
    public List<Project> getAllProjectInfo(){
        List<Project> projectList = projectService.getAllProjectInfo();
        return  projectList;
    }
}
