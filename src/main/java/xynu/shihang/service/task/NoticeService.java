package xynu.shihang.service.task;

import xynu.shihang.entity.Notice;
import xynu.shihang.utils.OAResult;
import xynu.shihang.utils.PageView;

public interface NoticeService {

    /**
     * 添加通告
     * @param notice
     * @return
     */
    public OAResult addNotice(Notice notice);


    public PageView<Notice> getNotices(int currentPage,int pageSize);

    /*
    根据通告id删除通告
     */
    public OAResult deleteOneOrMore(int  id);

    /**
     * 根据通告id获取单个通告
     * @param nid
     * @return
     */
    public Notice getOneNotice(String nid);


}
