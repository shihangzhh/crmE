package xynu.shihang.service.project.impl;

import javafx.scene.web.PromptData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xynu.shihang.entity.Customer;
import xynu.shihang.entity.Employee;
import xynu.shihang.entity.Project;
import xynu.shihang.entity.ProjectExample;
import xynu.shihang.mapper.CustomerMapper;
import xynu.shihang.mapper.EmployeeMapper;
import xynu.shihang.mapper.ProjectMapper;
import xynu.shihang.service.project.ProjectService;
import xynu.shihang.utils.OAResult;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ProjectServiceImpl  implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private CustomerMapper customerMapper;


    ProjectExample projectExample =  new ProjectExample();
    @Override
    public OAResult saveProject(Project project) {
        int row = projectMapper.insert(project);
        if (row == 1){
          return   OAResult.ok(200,"添加项目成功");
        }
        return OAResult.ok(400,"添加项目失败");
    }

    @Override
    public List<Project> getAllProjectInfor() {

        List<Project> projectList = projectMapper.getAllProjectInfor();
        return projectList;
    }

    @Override
    public List<Project> getProjectNoAnalysis() {
        List<Project> projectList = projectMapper.getProjectNoAnalysis();
        return projectList;
    }

    @Override
    public List<Project> getProjectNeedAnalysis() {
        return projectMapper.getProjectNeedAnalysis();
    }

    @Override
    public List<Project> getModuleAnalyProject() {
        return projectMapper.getModuleAnalyProject();
    }

    @Override
    public List<Project> getProjectByFunctonAndEmployee(int eid) {
        return projectMapper.getProjectByFunctonAndEmployee(eid);
    }

    @Override
    public Project getProjectInfoByPid(int pid) {

        Project project = projectMapper.selectByPrimaryKey(pid);
        Employee employee = employeeMapper.selectByPrimaryKey(project.getEmpFk());
        project.setEmployee(employee);
        Customer customer = customerMapper.selectByPrimaryKey(project.getComname());
         project.setCustomer(customer);

        return project;
    }

    @Override
    public OAResult updateProject(Project project) {

       int row = projectMapper.updateByPrimaryKey(project);
        if (row == 1){
            return  OAResult.build(200,"更改项目信息成功");
        }
        return OAResult.build(400,"更改项目信息失败");
    }

    @Override
    public List<Project> getAllProjectInfo() {
        ProjectExample projectExample = new ProjectExample();
        List<Project> projectList = projectMapper.selectByExample(projectExample);
        return projectList;
    }
}
