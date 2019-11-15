package xynu.shihang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xynu.shihang.entity.Sources;
import xynu.shihang.entity.SourcesExample;

public interface SourcesMapper {
    int countByExample(SourcesExample example);

    int deleteByExample(SourcesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sources record);

    int insertSelective(Sources record);

    List<Sources> selectByExample(SourcesExample example);

    Sources selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sources record, @Param("example") SourcesExample example);

    int updateByExample(@Param("record") Sources record, @Param("example") SourcesExample example);

    int updateByPrimaryKeySelective(Sources record);

    int updateByPrimaryKey(Sources record);

    public List<Sources> getEmployeeSourceByEid(@Param("eid") int eid,@Param("pid") int pid);
}