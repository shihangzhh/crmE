package xynu.shihang.service.task.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xynu.shihang.entity.Task;
import xynu.shihang.entity.TaskExample;
import xynu.shihang.mapper.TaskMapper;
import xynu.shihang.service.task.TaskService;
import xynu.shihang.utils.OAResult;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;
    @Override
    public OAResult saveTask(Task task) {

        TaskExample taskExample = new TaskExample();
        task.setStatus(0);//设置状态码
        int row = taskMapper.insert(task);

        if (row == 1){
            return OAResult.ok(200,"分配任务成功");
        }

        return OAResult.build(400,"分配任务失败");
    }

    @Override
    public List<Task> getTaskInforByEid(int eid) {
        List<Task> taskList = taskMapper.getTaskInforByEid(eid);
        return taskList;
    }

    @Override
    public OAResult changeTaskStatus(int id, int status) {

        Task task = taskMapper.selectByPrimaryKey(id);
        //获取任务的状态
        Integer taskStatus = task.getStatus();

        if (taskStatus == 2){
        return     OAResult.ok(400,"任务已完成，不能被修改了");
        }

        if (taskStatus==0 &&status ==2){
            return  OAResult.ok(400,"任务还未开始，请不要心急哟");
        }

        //如果status不为2 那么就需要设置任务的状态
        task.setStatus(status);
        //把更新的状态写入到数据库中
        taskMapper.updateByPrimaryKey(task);

        if (status ==1){
            return OAResult.ok(200,"任务进行中...");
        }
        return OAResult.ok(200,"任务已完成");
    }


}
