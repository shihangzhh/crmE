package xynu.shihang.service.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xynu.shihang.entity.RoleSourcesExample;
import xynu.shihang.entity.Sources;
import xynu.shihang.entity.SourcesExample;
import xynu.shihang.mapper.RoleSourcesMapper;
import xynu.shihang.mapper.SourcesMapper;
import xynu.shihang.service.manager.ResourceService;
import xynu.shihang.utils.OAResult;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private SourcesMapper sourcesMapper;

    @Autowired
    private RoleSourcesMapper roleSourcesMapper;

    @Override
    public List<Sources> getRootSources(int pid) {
        SourcesExample sourcesExample = new SourcesExample();

        SourcesExample.Criteria criteria = sourcesExample.createCriteria();
        criteria.andPidEqualTo(pid);
        List<Sources> sourcesList = sourcesMapper.selectByExample(sourcesExample);

        for (Sources sources:sourcesList) {
            sources.setChildren(getRootSources(sources.getId()));
        }

        return sourcesList;
    }

    @Override
    public OAResult addSources(Sources sources) {
        int row = sourcesMapper.insert(sources);

        if (row ==1){

            return  OAResult.ok(200,"资源添加成功");
        }

        return OAResult.ok(200,"资源添加成功");
    }

    @Override
    public Sources getOneSourceByid(int id) {

        Sources sources = sourcesMapper.selectByPrimaryKey(id);

        return sources;
    }

    @Override
    public OAResult updateSource(Sources sources) {

        int row = sourcesMapper.updateByPrimaryKey(sources);

        if (row == 1){
         return    OAResult.ok(200,"更新资源成功");
        }
        return OAResult.ok(200,"更新资源成功");
    }

    @Override
    public OAResult deleteSourcesById(int id) {

        //先删除中间表中的资源
        RoleSourcesExample roleSourcesExample=new RoleSourcesExample();
        RoleSourcesExample.Criteria criteria = roleSourcesExample.createCriteria();
        criteria.andSidEqualTo(id);
        roleSourcesMapper.deleteByExample(roleSourcesExample);

        //在删除资源
        int row = sourcesMapper.deleteByPrimaryKey(id);

        if (row == 1){

            return  OAResult.ok(200,"删除资源成功");
        }
        return OAResult.ok(400,"删除资源失败");
    }
}
