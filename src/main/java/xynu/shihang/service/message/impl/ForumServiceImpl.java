package xynu.shihang.service.message.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xynu.shihang.entity.*;
import xynu.shihang.mapper.EmployeeMapper;
import xynu.shihang.mapper.EvaluateMapper;
import xynu.shihang.mapper.ForumpostMapper;
import xynu.shihang.service.message.ForumpostService;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ForumServiceImpl implements ForumpostService {

    @Autowired
    private ForumpostMapper forumpostMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EvaluateMapper evaluateMapper;

    @Override
    public int  addForumpost(Forumpost forumpost, HttpSession session) {

        Employee empl = (Employee) session.getAttribute("activeUser");

        //设置发帖人的id
        forumpost.setEmpfk3(empl.getEid());
        //设置帖子的状态
        forumpost.setStats(1);
        //设置发帖的时间
        forumpost.setCreatetime(new Date());

        int row = forumpostMapper.insert(forumpost);

        return row;
    }

    @Override
    public List<Forumpost> getCurrentEmplForumposts(HttpSession session) {

        ForumpostExample forumpostExample = new ForumpostExample();
        ForumpostExample.Criteria criteria = forumpostExample.createCriteria();

        Employee empl = (Employee) session.getAttribute("activeUser");
        forumpostExample.setOrderByClause("createtime desc");
        criteria.andEmpfk3EqualTo(empl.getEid());

        List<Forumpost> forumpostList = forumpostMapper.selectByExample(forumpostExample);
        return forumpostList;
    }

    @Override
    public List<Forumpost> getTopSixForumposts(int flag) {

        //分页
        PageHelper.startPage(1,flag);

        ForumpostExample forumpostExample = new ForumpostExample();
      forumpostExample.setOrderByClause("createtime desc");

        List<Forumpost> forumpostList = forumpostMapper.selectByExample(forumpostExample);

        return forumpostList;
    }

    @Override
    public Forumpost getForumAndEvaByForumId(int forumId) {

        //查询帖子
        Forumpost forumpost = forumpostMapper.selectByPrimaryKey(forumId);

        Employee employee = employeeMapper.selectByPrimaryKey(forumpost.getEmpfk3());
        forumpost.setEmployee(employee);

        //查询一级评论

        List<Evaluate> evaluateList = getEvaluateByForumIdAndEvaId(forumId, null);
        forumpost.setEvaluateList(evaluateList);

        return forumpost;
    }


    public List<Evaluate> getEvaluateByForumIdAndEvaId(int forumId,Integer evaidFk){

        EvaluateExample evaluateExample=new EvaluateExample();
        EvaluateExample.Criteria criteria = evaluateExample.createCriteria();
        criteria.andForumfkEqualTo(forumId);
        if(evaidFk==null){
            criteria.andEvaidfkIsNull();
        }else{
            criteria.andEvaidfkEqualTo(evaidFk);
        }
        List<Evaluate> evaluateList = evaluateMapper.selectByExampleWithBLOBs(evaluateExample);
        //找每个一级评论的子评论
        for(Evaluate evaluate : evaluateList){
            //评论人
            Employee employee= employeeMapper.selectByPrimaryKey(evaluate.getEmpfk());
            evaluate.setEmployee(employee);
            List<Evaluate> subEvaluateList=getEvaluateByForumIdAndEvaId(evaluate.getForumfk(),evaluate.getEvaid());
            evaluate.setEvaluateList(subEvaluateList);
        }
        return evaluateList;
    }

    @Override
    public int addEvaluate(Evaluate evaluate,HttpSession session) {
        evaluate.setEvatime(new Date());
        evaluate.setUpdatetime(new Date());
        Employee  employee = (Employee) session.getAttribute("activeUser");
        evaluate.setEmpfk(employee.getEid());
        int result = evaluateMapper.insert(evaluate);

        return result;
    }
}
