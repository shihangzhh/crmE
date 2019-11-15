package xynu.shihang.service.other;

import xynu.shihang.entity.Notice;

import java.util.List;

public interface MainService {

    public List<Notice> getTopThreeNotice();

       public int   selectUnfishedTask(int status,int eid);
}
