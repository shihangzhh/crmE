package xynu.shihang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xynu.shihang.entity.Project;
import xynu.shihang.entity.ProjectExample;

public interface ProjectMapper {
    int countByExample(ProjectExample example);

    int deleteByExample(ProjectExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(Project record);

    int insertSelective(Project record);

    List<Project> selectByExample(ProjectExample example);

    Project selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    public List<Project> getAllProjectInfor();

    public List<Project> getProjectNoAnalysis();

    public List<Project> getProjectNeedAnalysis();
    public List<Project> getModuleAnalyProject();
    public List<Project> getProjectByFunctonAndEmployee(int eid);

}