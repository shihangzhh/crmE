package xynu.shihang.service.customer.impl;

import com.github.pagehelper.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import xynu.shihang.entity.*;
import xynu.shihang.mapper.*;
import xynu.shihang.service.EmployeeService;
import xynu.shihang.utils.JsonUtils;
import xynu.shihang.utils.OAResult;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private LevelMapper levelMapper;

    @Autowired
    private EmpRoleMapper empRoleMapper;

    @Autowired
    private  SourcesMapper sourcesMapper;

    @Autowired
    private JedisPool jedisPool;


    /**   需要员工表  职位表进行联合查询  分表查询也可以不过比较麻烦
     * 查询出所有的项目经理
     * @param positionName 根据公司员工职位大小
     * @return
     */

    @Override
    public List<Employee> getEmployeeByPositionName(String positionName) {

        List<Employee> emplList = employeeMapper.getEmployeeByPositionName(positionName);
        return emplList;
    }

    @Override
    public OAResult checklogin(String username, String password) {

        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();

        //设置查询条件
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);


        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);

          //根据员工id查询其位置信息
        PositionExample positionExample = new PositionExample();

        Position position = positionMapper.selectByPrimaryKey(employeeList.get(0).getEid());

        if (employeeList != null && employeeList.size() == 1){
            employeeList.get(0).setPosition(position);
          return   OAResult.build(200,"登录成功",employeeList.get(0));
        }
        return OAResult.build(400,"用户名或者密码错误");
    }

    @Override
    public List<Employee> getEmployeeByLikePositionName(String positionName) {

        return employeeMapper.getEmployeeByLikePositionName("%"+positionName+"%");
    }

    @Override
    public Employee getEmployeeArchives(int eid) {
        Employee employee = employeeMapper.getEmployeeArchivesByEid(eid);
        return employee;
    }

    @Override
    public List<Employee> showReceiveEmp(int eid) {

        EmployeeExample employeeExample = new EmployeeExample();

        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEidNotEqualTo(eid);

        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);


        return employeeList;
    }

    @Override
    public List<Position> getPositions(){
        PositionExample positionExample=new PositionExample();
        return positionMapper.selectByExample(positionExample);
    }
    @Override
    public List<Level> getLevels(){

        LevelExample levelExample=new LevelExample();
        return levelMapper.selectByExample(levelExample);
    }
    @Override
    public List<Dept> getDepts(){
        DeptExample deptExample=new DeptExample();
        return deptMapper.selectByExample(deptExample);
    }

    @Override
    public OAResult addEmployee(Employee employee, int roleFk) {
        employee.setPassword(DigestUtils.md5DigestAsHex(employee.getPassword().getBytes()));
        //保存员工
        int rows= employeeMapper.insert(employee);
        //保存员工的角色
        EmpRole empRole=new EmpRole();
        empRole.setRoleFk(roleFk);
        empRole.setEmpFk(employee.getEid());
        empRoleMapper.insert(empRole);
        if(rows==1){
            return OAResult.ok(200,"用户保存成功");
        }
        return OAResult.ok(400,"用户保存失败");
    }

    @Override
    public List<Employee> getAllEmployees() {

        EmployeeExample employeeExample = new EmployeeExample();
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        return employeeList;
    }

    @Override
    public List<Sources> getSourceByEid(int eid, int pid,String username) {

        //首先从缓存中进行查找
        Jedis jedis = jedisPool.getResource();


        try {
            String jsonData = jedis.hget("permission", username);



            if (StringUtils.isNotBlank(jsonData)) {
                System.out.println("从缓存中取出了数据");
                jedis.close();
                //将json数据的格式转化为集合
                return    JsonUtils.jsonToList(jsonData,Sources.class);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //从数据库中进行查询
        List<Sources> sourcesList = getSourceByEidAndPid(eid, pid);

        try{
            //将数据放入到缓存中  数据为json格式的
            jedis.hset("permission",username, JsonUtils.objectToJson(sourcesList));
            System.out.println("把数据放入到了缓存当中");
            jedis.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return sourcesList;
    }

    /**
     * 使用缓存进行查找
     *
     * 递归查询父菜单及其子菜单中的全部内容
     * @param eid
     * @param pid
     * @return
     */

    public List<Sources> getSourceByEidAndPid(int eid,int pid){

        List<Sources> sourcesList = sourcesMapper.getEmployeeSourceByEid(eid, pid);






        for (Sources sources:sourcesList) {

            List<Sources> childrenSourceList = getSourceByEidAndPid(eid, sources.getId());
            sources.setChildren(childrenSourceList);

        }


        return sourcesList;
    }


}
