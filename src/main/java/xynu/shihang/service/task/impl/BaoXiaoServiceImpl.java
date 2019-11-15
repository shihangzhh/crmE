package xynu.shihang.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xynu.shihang.entity.Baoxiao;
import xynu.shihang.entity.BaoxiaoExample;
import xynu.shihang.entity.Employee;
import xynu.shihang.mapper.BaoxiaoMapper;
import xynu.shihang.mapper.EmployeeMapper;
import xynu.shihang.service.task.BaoXiaoService;
import xynu.shihang.utils.OAResult;
import xynu.shihang.utils.PageView;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Service
public class BaoXiaoServiceImpl implements BaoXiaoService {

    @Autowired
    private BaoxiaoMapper baoxiaoMapper;

    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    public OAResult saveBaoXiao(Baoxiao baoxiao) {

        //设置报销主键
        baoxiao.setBxid(UUID.randomUUID().toString().replace('-','1'));
       //设置报销状态  未审批
        baoxiao.setBxstatus(1);

        int row = baoxiaoMapper.insert(baoxiao);

        if (row == 1){
            return OAResult.build(200,"保存报销成功");
        }
        return OAResult.build(400,"保存报销失败");
    }

    @Override
    public PageView<Baoxiao> getBaoXiaoTalbeByEid(int eid,int currentPage,int pageSize,int bxstatus) {

        //创建分页对象 并传入一个页面显示多少数据
        PageView<Baoxiao> pageView = new PageView<Baoxiao>(currentPage,pageSize);
        //开始分页
        PageHelper.startPage(currentPage,pageSize);
        BaoxiaoExample baoxiaoExample = new BaoxiaoExample();
        BaoxiaoExample.Criteria criteria = baoxiaoExample.createCriteria();
        criteria.andEmpFkEqualTo(eid);
        criteria.andBxstatusEqualTo(bxstatus);
        List<Baoxiao> baoxiaoList = baoxiaoMapper.selectByExample(baoxiaoExample);
        PageInfo<Baoxiao> pageInfo = new PageInfo<>(baoxiaoList);
        pageView.setTotalRecoreds(pageInfo.getTotal());
        pageView.setDataList(pageInfo.getList());//保存查询出来的数据
        return pageView;
    }

    @Override
    public Baoxiao getOneBaoXiao(String bxid) {

        Baoxiao baoxiao = baoxiaoMapper.selectByPrimaryKey(bxid);
        //获取审核人
        Employee employee = employeeMapper.selectByPrimaryKey(baoxiao.getCaiwuFk());
        baoxiao.setEmployee(employee);
        return baoxiao;
    }

    @Override
    public void updataBaoXiaoStatus(String bxid, int bxstatus, String result,int caiwuId) {

        Baoxiao baoxiao = baoxiaoMapper.selectByPrimaryKey(bxid);
        //更改审批状态
        baoxiao.setBxstatus(bxstatus);
        //设置审批结果
        baoxiao.setResult(result);
        //设置财务id
        baoxiao.setCaiwuFk(caiwuId);
        //设置审批时间
        baoxiao.setShenpidate(new Date());

         baoxiaoMapper.updateByPrimaryKey(baoxiao);

    }

    @Override
    public void upBaoXiaoBoHui(Baoxiao baoxiao) {

        Baoxiao baoxiao1 = baoxiaoMapper.selectByPrimaryKey(baoxiao.getBxid());

        //以下情况都是有可能发生的，所以最好都要设置一下
        //更改支出类型
        baoxiao1.setPaymode(baoxiao.getPaymode());
        //更该支出金额
        baoxiao1.setTotalmoney(baoxiao.getTotalmoney());
        //更改报销订单的使用时间
        baoxiao1.setBxtime(baoxiao.getBxtime());
        //更改备注
        baoxiao1.setBxremark(baoxiao.getBxremark());
         //更改审批状态
        baoxiao1.setBxstatus(baoxiao.getBxstatus());

      baoxiaoMapper.updateByPrimaryKey(baoxiao);

    }

    @Override
    public List<Baoxiao> getBaoXiaoTalbesByStatus(int bxstatus) {

        BaoxiaoExample baoxiaoExample = new BaoxiaoExample();
        BaoxiaoExample.Criteria criteria = baoxiaoExample.createCriteria();
        //设置查询条件
        criteria.andBxstatusEqualTo(bxstatus);

        List<Baoxiao> baoxiaoList = baoxiaoMapper.selectByExample(baoxiaoExample);

        for (Baoxiao bx:baoxiaoList) {

            //设置报销人
            bx.setEmployee(employeeMapper.selectByPrimaryKey(bx.getEmpFk()));

        }

        return baoxiaoList;
    }

    @Override
    public int deleteOneOrMore(String id) {

        BaoxiaoExample baoxiaoExample = new BaoxiaoExample();

        BaoxiaoExample.Criteria criteria = baoxiaoExample.createCriteria();
        criteria.andBxidEqualTo(id);

        int row = baoxiaoMapper.deleteByExample(baoxiaoExample);

        return row;
    }


}
