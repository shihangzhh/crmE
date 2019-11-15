package xynu.shihang.service.project;

import xynu.shihang.entity.Analysis;
import xynu.shihang.utils.OAResult;

import java.util.List;

public interface AnalysisService {

    /**
     * 保存项目需求分析
     * @param analysis
     * @return
     */
     public OAResult saveProjectAnalysis(Analysis analysis);

    /**
     * 查询所有含有项目需求分析的的项目
     * @return
     */
   public   List<Analysis> getAllProjectAbalisis();

    /**
     *查看项目的需求分析
     * @param id  为项目的id
     * @return
     */

    public Analysis queryOneAnalysis(int id);

}
