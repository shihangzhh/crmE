package xynu.shihang.service;

import org.springframework.web.bind.annotation.RequestParam;
import xynu.shihang.entity.*;
import xynu.shihang.utils.OAResult;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface EmployeeService {

    public  List<Employee> getEmployeeByPositionName(String positionName);

    /**
     * 校验员工登录
     * @param username 员工姓名
     * @param password  员工密码
     * @return
     */
    public OAResult checklogin(String username,String password);

    /**
     * 查询职位为开发工程师的员工的详细信息
     * @return
     */

    public List<Employee> getEmployeeByLikePositionName(String positionName);

    public Employee getEmployeeArchives(int eid);


    public  List<Employee> showReceiveEmp(int eid);

    public List<Position> getPositions();

    public List<Level> getLevels();

    public List<Dept> getDepts();

    public OAResult addEmployee(Employee employee,int roleFk);

    public List<Employee> getAllEmployees();

    public List<Sources>  getSourceByEid(int eid,  int pid,String username);


    }



