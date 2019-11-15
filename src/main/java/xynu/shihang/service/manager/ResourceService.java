package xynu.shihang.service.manager;

import xynu.shihang.entity.Sources;
import xynu.shihang.utils.OAResult;

import java.util.List;

public interface ResourceService {

    public List<Sources> getRootSources(int pid);

    public OAResult addSources(Sources sources);

    public Sources getOneSourceByid(int id);

    public OAResult updateSource(Sources sources);

    public OAResult deleteSourcesById(int id);
}
