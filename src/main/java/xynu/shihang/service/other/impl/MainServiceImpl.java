package xynu.shihang.service.other.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import xynu.shihang.entity.Notice;
import xynu.shihang.entity.TaskExample;
import xynu.shihang.mapper.NoticeMapper;
import xynu.shihang.mapper.TaskMapper;
import xynu.shihang.service.other.MainService;

import java.util.List;
@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<Notice> getTopThreeNotice() {
        List<Notice> noticeList = noticeMapper.getTopThreeNotice();
        return noticeList;
    }

    @Override
    public int selectUnfishedTask(int status,int eid) {

        TaskExample taskExample = new TaskExample();

        TaskExample.Criteria criteria = taskExample.createCriteria();

        criteria.andStatusNotEqualTo(status);
        int num = taskMapper.countByExample(taskExample);

        return num;
    }
}
