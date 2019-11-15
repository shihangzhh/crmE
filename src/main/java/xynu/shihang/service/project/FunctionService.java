package xynu.shihang.service.project;

import xynu.shihang.entity.Function;
import xynu.shihang.utils.OAResult;

import java.util.List;

public interface FunctionService  {

    /**
     * 保存项目的功能
     * @param function
     * @return
     */
    public OAResult saveProjectFunction(Function function);

    /**
     * 根据模块id查询功能
     * @param id 模块id
     * @return
     */

    public List<Function> getFunctionsByModuleId(int id);
}
