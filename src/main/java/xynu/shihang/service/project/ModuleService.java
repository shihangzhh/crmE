package xynu.shihang.service.project;

import xynu.shihang.entity.Module;
import xynu.shihang.utils.OAResult;

import java.util.List;

public interface ModuleService  {

    /**
     * 保存模块
     * @param module
     * @return
     */
   public OAResult saveProjectModule(Module module);

    /**
     * 根据需求分析的id查询模块
     * @param id
     * @return
     */
    public List<Module> getModuleByAnalysisId(int id);

}
