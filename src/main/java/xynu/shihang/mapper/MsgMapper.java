package xynu.shihang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xynu.shihang.entity.Msg;
import xynu.shihang.entity.MsgExample;

public interface MsgMapper {
    int countByExample(MsgExample example);

    int deleteByExample(MsgExample example);

    int deleteByPrimaryKey(Integer msgid);

    int insert(Msg record);

    int insertSelective(Msg record);

    List<Msg> selectByExample(MsgExample example);

    Msg selectByPrimaryKey(Integer msgid);

    int updateByExampleSelective(@Param("record") Msg record, @Param("example") MsgExample example);

    int updateByExample(@Param("record") Msg record, @Param("example") MsgExample example);

    int updateByPrimaryKeySelective(Msg record);

    int updateByPrimaryKey(Msg record);
}