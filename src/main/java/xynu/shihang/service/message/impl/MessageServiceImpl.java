package xynu.shihang.service.message.impl;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;
import xynu.shihang.entity.Employee;
import xynu.shihang.entity.Msg;
import xynu.shihang.service.message.MessageService;
import xynu.shihang.utils.OAResult;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MessageServiceImpl implements MessageService  {
    @Override
    public OAResult sendMsg(Msg msg,HttpSession session) throws Exception {


        Employee employee = (Employee) session.getAttribute("activeUser");
        msg.setSendp(employee.getEid());
        msg.setMark(1);

        //1.创建调用Scheduler的工厂
        SchedulerFactory sf = new StdSchedulerFactory();
        //2.从工厂中获取调度器实例
        Scheduler scheduler = sf.getScheduler();

        //3.创建JobDetail
        JobDetail jb = JobBuilder.newJob(MsgJob.class)
                .withIdentity("ramJob", "ramGroup") //job 的name和group(名称自己定义)
                .build();

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(msg.getMsgtime()));
        long time1 = msg.getMsgtime().getTime() - System.currentTimeMillis();
        System.out.println(time1+"\t"+msg.getMsgtime().getTime());
        long time = System.currentTimeMillis() + time1;
        System.out.println(time+"*****************************");
        Date statTime = new Date(time);
        //4.创建Trigger
        //使用SimpleScheduleBuilder或者CronScheduleBuilder
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("ramTrigger", "ramGroup")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                .startAt(statTime)//设定任务的启动时间
                .build();
        //给我们的job方法传递参数
        jb.getJobDataMap().put("msg",msg);
        //使用调度器将我们的job和trigger绑定到一起
        scheduler.scheduleJob(jb,trigger);

        if(!scheduler.isShutdown()){
            scheduler.start();
            return OAResult.ok(200,"定时消息已启动");
        }
        return OAResult.ok(400,"定时消息启动失败");


    }



}
