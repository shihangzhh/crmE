package xynu.shihang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xynu.shihang.entity.Employee;
import xynu.shihang.entity.EmployeeExample;

public interface EmployeeMapper {
    int countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer eid);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer eid);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
  public  List<Employee> getEmployeeByPositionName(String positionName);
    public List<Employee> getEmployeeByLikePositionName(String positionName);

    public Employee  getEmployeeArchivesByEid(int eid);
}