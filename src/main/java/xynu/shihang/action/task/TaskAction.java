package xynu.shihang.action.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xynu.shihang.entity.Employee;
import xynu.shihang.entity.Task;
import xynu.shihang.service.task.TaskService;
import xynu.shihang.utils.OAResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/task")
public class TaskAction {

    @Autowired
    private TaskService taskService;


@RequestMapping(value = "/{page}")
    public String forwardTaskPage(@PathVariable(value = "page") String page){

        return "task/"+page;
    }


    @ResponseBody
    @RequestMapping(value = "/saveTask")
    public OAResult saveTask(Task task, HttpServletRequest request){

        Employee employee = (Employee) request.getSession().getAttribute("activeUser");
        //设置员工id
        task.setEmpFk(employee.getEid());
        return taskService.saveTask(task);
    }


    @RequestMapping(value = "/taskInfor/{page}")
    public String getTaskInfor(Model model, HttpServletRequest request,@PathVariable(value = "page") String page){

        Employee employee = (Employee) request.getSession().getAttribute("activeUser");
        List<Task> taskList = taskService.getTaskInforByEid(employee.getEid());
        //将查询出来的任务信息保存到共享域里面
      model.addAttribute("taskList",taskList);
        return "task/"+page;
    }

    @ResponseBody
    @RequestMapping(value = "/changeTaskStatus")
    public OAResult changeTaskStatus(int id,int status){

    return taskService.changeTaskStatus(id,status);
    }

}
