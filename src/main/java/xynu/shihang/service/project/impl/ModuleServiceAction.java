package xynu.shihang.service.project.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xynu.shihang.entity.Module;
import xynu.shihang.entity.ModuleExample;
import xynu.shihang.mapper.ModuleMapper;
import xynu.shihang.service.project.ModuleService;
import xynu.shihang.utils.OAResult;

import java.util.List;

@Service
public class ModuleServiceAction implements ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public OAResult saveProjectModule(Module module) {

        int row = moduleMapper.insert(module);
        if (row == 1){
            return  OAResult.ok(200,"添加模块成功");
        }
        return OAResult.ok(400,"添加模块失败");
    }

    @Override
    public List<Module> getModuleByAnalysisId(int id) {

        ModuleExample moduleExample = new ModuleExample();
        ModuleExample.Criteria criteria = moduleExample.createCriteria();
        //设置查询条件
        criteria.andAnalysisFkEqualTo(id);
        List<Module> moduleList = moduleMapper.selectByExample(moduleExample);

        return moduleList;
    }
}
