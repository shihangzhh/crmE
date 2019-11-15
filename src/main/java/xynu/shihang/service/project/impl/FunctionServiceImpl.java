package xynu.shihang.service.project.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xynu.shihang.entity.Function;
import xynu.shihang.entity.FunctionExample;
import xynu.shihang.mapper.FunctionMapper;
import xynu.shihang.service.project.FunctionService;
import xynu.shihang.utils.OAResult;

import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService {

    @Autowired
    private FunctionMapper functionMapper;

    @Override
    public OAResult saveProjectFunction(Function function) {
        int row = functionMapper.insert(function);

        if (row == 1){
            return OAResult.ok(200,"功能添加完成");
        }
        return OAResult.ok(400,"功能添加失败");


    }

    @Override
    public List<Function> getFunctionsByModuleId(int id) {

        List<Function> functionList = functionMapper.getFunctionsByModuleId(id);
        return functionList;
    }
}
