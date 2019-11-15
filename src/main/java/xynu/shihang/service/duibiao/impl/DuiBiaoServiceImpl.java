package xynu.shihang.service.duibiao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xynu.shihang.entity.Datacollect;
import xynu.shihang.entity.DatacollectExample;
import xynu.shihang.mapper.DatacollectMapper;
import xynu.shihang.service.duibiao.DuiBiaoService;

import java.util.List;

@Service
public class DuiBiaoServiceImpl implements DuiBiaoService {

    @Autowired
    private DatacollectMapper datacollectMapper;

    @Override
    public List<Datacollect> getInfoGroup() {

        DatacollectExample datacollectExample = new DatacollectExample();

        List<Datacollect> datacollectList = datacollectMapper.selectByExample(datacollectExample);
        return datacollectList;
    }

    @Override
    public Datacollect getOneBydaid(int daid) {

        Datacollect datacollect = datacollectMapper.selectByPrimaryKey(daid);


        return datacollect;
    }
}
