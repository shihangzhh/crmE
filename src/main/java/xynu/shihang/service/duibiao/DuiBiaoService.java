package xynu.shihang.service.duibiao;

import xynu.shihang.entity.Datacollect;

import java.util.List;

public interface DuiBiaoService {
    public List<Datacollect> getInfoGroup();

    public  Datacollect getOneBydaid(int daid);
}
