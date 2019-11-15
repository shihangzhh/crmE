package xynu.shihang.service.task;

import xynu.shihang.entity.Baoxiao;
import xynu.shihang.utils.OAResult;
import xynu.shihang.utils.PageView;

import java.util.List;

public interface BaoXiaoService {

    /**
     * 保存报销
     * @param baoxiao
     * @return
     */
    public OAResult saveBaoXiao(Baoxiao baoxiao);

    /**
     * 根据员工的id获取报销单
     * 并进行分页
     * @param eid
     * @return
     */
      public PageView<Baoxiao> getBaoXiaoTalbeByEid(int eid, int currentPage, int pageSize,int bxstatus);



    /**
     * 获取单个的报销
     * @param bxid   报销的id
     * @return
     */
    public Baoxiao getOneBaoXiao(String bxid);

    /**
     *
     * @param bxid  所需审核报销单子的id
     * @param bxstatus   报销单子的状态
     * @param result    审核报销单子的结果
     * @param caiwuId    财务人员的id
     */

    public void updataBaoXiaoStatus(String bxid,int bxstatus,String result,int caiwuId);

    /**
     * 修改员工的报销信息
     * @param baoxiao
     */

    public void  upBaoXiaoBoHui(Baoxiao baoxiao);

    /**
     * 根据审核单子的不同的状态，查询不同的单子内容  默认是未审核的
     * @param bxstatus 所需审核单子的状态
     * @return
     */

    public List<Baoxiao> getBaoXiaoTalbesByStatus(int bxstatus);

    /**
     * 删除报销
     * @param id
     * @return
     */
    public int deleteOneOrMore(String id);

}
