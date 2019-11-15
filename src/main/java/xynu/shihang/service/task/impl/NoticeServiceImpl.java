package xynu.shihang.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xynu.shihang.entity.Notice;
import xynu.shihang.entity.NoticeExample;
import xynu.shihang.mapper.NoticeMapper;
import xynu.shihang.service.task.NoticeService;
import xynu.shihang.utils.OAResult;
import xynu.shihang.utils.PageView;

import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public OAResult addNotice(Notice notice) {

        notice.setNdate(new Date());
        int row = noticeMapper.insert(notice);

        if (row == 1){
            return  OAResult.ok(200,"通告添加完成");
        }
        return OAResult.build(400,"通告添加失败");
    }

    @Override
    public PageView<Notice> getNotices(int currentPage, int pageSize) {

        //创建pageView对象并传入当前页和一页显示多少数据
        PageView<Notice> pageView = new PageView<Notice>(currentPage,pageSize);
        //设置分页
        PageHelper.startPage(currentPage,pageSize);

        NoticeExample  noticeExample = new NoticeExample();
        //设置数据排序方式
           noticeExample.setOrderByClause("ndate desc");
        List<Notice> noticeList = noticeMapper.selectByExample(noticeExample);

        PageInfo<Notice> pageInfo = new PageInfo<Notice>(noticeList);

        pageView.setTotalRecoreds(pageInfo.getTotal());

        pageView.setDataList(pageInfo.getList());
        return pageView;
    }

    @Override
    public OAResult deleteOneOrMore(int id) {

        int row = noticeMapper.deleteByPrimaryKey(id);

        if (row >=1){
            return OAResult.ok(200,"删除通告成功");
        }

        return OAResult.build(400,"删除通告失败");
    }

    @Override
    public Notice getOneNotice(String nid) {

        int id = Integer.parseInt(nid);

        return noticeMapper.selectByPrimaryKey(id);
    }


}
