package xynu.shihang.service.project;

import xynu.shihang.entity.Project;
import xynu.shihang.utils.OAResult;

import java.util.List;

public interface ProjectService {

    OAResult saveProject(Project project);

    /**
     * 查询所有的项目信息 包括负责人信息 客户信息 以便在页面中进行展示
     * @return
     */
    List<Project> getAllProjectInfor();

    /**
     * 查看没有需求分析的项目
     * @return
     */
       public List<Project>  getProjectNoAnalysis();

    /**
     * 查看含有需求分析的项目
     * @return
     */
    public List<Project> getProjectNeedAnalysis();

    /**
     * 查询带有模块的项目   带有模块肯定带有需求分析
     * @return
     */
    public List<Project> getModuleAnalyProject();

    /**
     *查询带有功能的项目
     * @param eid  员工id    准确的说是项目负责人的id
     * @return
     */
    public List<Project> getProjectByFunctonAndEmployee(int eid);

    /**
     * 根据项目pid查询项目信息
     * @param pid  项目id
     * @return
     */

     public Project  getProjectInfoByPid(int pid);

    /**
     * 修改项目信息
     * @param project
     * @return
     */

   public OAResult  updateProject(Project project);


    /**
     *查询项目的信息  只是单表查询
     * @return
     */
    public List<Project> getAllProjectInfo();

}
