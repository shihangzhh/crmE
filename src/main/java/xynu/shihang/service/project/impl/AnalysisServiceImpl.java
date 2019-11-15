package xynu.shihang.service.project.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xynu.shihang.entity.Analysis;
import xynu.shihang.entity.AnalysisExample;
import xynu.shihang.mapper.AnalysisMapper;
import xynu.shihang.service.project.AnalysisService;
import xynu.shihang.utils.OAResult;

import java.util.Date;
import java.util.List;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private AnalysisMapper analysisMapper;
    @Override
    public OAResult saveProjectAnalysis(Analysis analysis) {

        analysis.setAddtime(new Date());
     analysis.setUpdatetime(new Date());
        int row = analysisMapper.insert(analysis);
        if (row == 1){
            return OAResult.ok(200,"需求分析添加完成");
        }
        return OAResult.ok(400,"需求分析添加失败");
    }

    @Override
    public List<Analysis> getAllProjectAbalisis() {
        return analysisMapper.getAllProjectAbalisis();
    }

    @Override
    public Analysis queryOneAnalysis(int id) {
        AnalysisExample analysisExample = new AnalysisExample();
        Analysis analysis = analysisMapper.selectByPrimaryKey(id);
        return analysis;
    }
}
