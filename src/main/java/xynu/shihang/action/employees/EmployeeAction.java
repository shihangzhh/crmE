package xynu.shihang.action.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xynu.shihang.entity.*;
import xynu.shihang.service.EmployeeService;
import xynu.shihang.utils.OAResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/employees")
public class EmployeeAction {

    @Autowired
  private EmployeeService employeeService;


    @RequestMapping(value = "/{page}")
    public String forwardUserPage(@PathVariable(value = "page") String page){

        return "manager/"+page;
    }

    @ResponseBody
    @RequestMapping(value = "/position")
    public List<Employee> getEmployeeByPositionName(String positionName){

        return  employeeService.getEmployeeByPositionName(positionName);
    }



    @ResponseBody
    @RequestMapping(value ="/checklogin" )
        public OAResult checklogin(String username, String password, HttpServletRequest request ){
        OAResult oaResult = employeeService.checklogin(username, password);

        //登录成功之后把employee放入到session域中
        if (oaResult.getStatus() == 200){
            Employee employee = (Employee) oaResult.getData();
            request.getSession().setAttribute("activeUser",employee);
        }
        return oaResult;
    }
@ResponseBody
@RequestMapping(value = "/exitSystem")
    public OAResult exitSystem(HttpSession session){

        Employee employee  = (Employee) session.getAttribute("activeUser");
      if (employee != null){
          //清除session
          session.invalidate();
        return   OAResult.build(200,"退出成功");
      }

       return OAResult.ok(400,"退出失败");
    }

     @ResponseBody
    @RequestMapping(value = "/position/empl")
    public List<Employee> getEmployeeAndPosition(String positionName){

        return employeeService.getEmployeeByLikePositionName(positionName);
    }

    @ResponseBody
    @RequestMapping(value = "/person/archives")
    public Employee getEmployeeArchives(HttpSession session){

        Employee empl = (Employee) session.getAttribute("activeUser");
        Employee employee = employeeService.getEmployeeArchives(empl.getEid());

        return  employee;
    }


    /**
     * 查询收件人的信息
     * @param session
     * @return
     */

    @ResponseBody
@RequestMapping(value = "/showReceiveEmp")
     public List<Employee>  showReceiveEmp(HttpSession session){

        Employee empl = (Employee) session.getAttribute("activeUser");

        List<Employee> employeeList = employeeService.showReceiveEmp(empl.getEid());

        return  employeeList;
     }


    @ResponseBody
    @RequestMapping("/getPosition")
    public List<Position> getPositions(){

        return employeeService.getPositions();
    }
    @ResponseBody
    @RequestMapping("/getLevel")
    public List<Level> getLevels(){

        return employeeService.getLevels();
    }

    @ResponseBody
    @RequestMapping("/getDept")
    public List<Dept> getDepts(){

        return employeeService.getDepts();
    }

    @ResponseBody
    @RequestMapping("/addEmp")
    public OAResult addEmployee(Employee employee,int roleFk){

        return employeeService.addEmployee(employee,roleFk);
    }


    @RequestMapping(value = "/getAllEmployees")
    public String getAllEmployees(Model model){

        List<Employee> employeeList = employeeService.getAllEmployees();

        model.addAttribute("employeeList",employeeList);
        return "manager/user";
    }

    @ResponseBody
    @RequestMapping(value = "/permission")
   public List<Sources>  getSourceByEid(HttpSession session,@RequestParam(value = "pid",defaultValue = "1") int pid){

        Employee employee = (Employee) session.getAttribute("activeUser");
        List<Sources> sourcesList = employeeService.getSourceByEid(employee.getEid(), pid,employee.getUsername());

        return sourcesList;
    }


}
