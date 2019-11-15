package xynu.shihang.service.task;

import xynu.shihang.entity.Task;
import xynu.shihang.utils.OAResult;

import java.util.List;

public interface TaskService {

    /**
     * 保存任务
     * @param task
     * @return
     */
    public OAResult saveTask(Task task);

    /**
     *  查询任务信息
     * @param eid  项目经理的id
     * @return
     */

    public List<Task> getTaskInforByEid(int eid);

    /**
       更改任务的状态
     * @param id 任务的id
     * @param status  任务的状态
     * @return
     */
    public OAResult changeTaskStatus(int id,int status);
}
