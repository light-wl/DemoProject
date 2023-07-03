package com.light.scheduled;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author light
 * @Date 2023/7/3
 * @Desc Quartz 定时任务
 * 背景：当定时任务愈加复杂时，使用 Spring 注解 @Schedule 已经不能满足业务需要
 * 功能：在项目开发中，经常需要定时任务来帮助我们来做一些内容，如定时派息、跑批对账、将任务纳入日程或者从日程中取消，开始，停止，暂停日程进度等
 **/
@Component
public class QuartzJob implements Job {
    public void execute(JobExecutionContext context) {
        System.out.println("要去数据库扫描啦。。。");
    }

    /**
     * 1、Job 中具体的任务，包含了执行任务的具体方法。是一个接口，只定义一个方法 execute() 方法，在实现接口的 execute() 方法中编写所需要定时执行的 Job
     * 2、JobDetail 中需要执行的任务详情，包括了任务的唯一标识和具体要执行的任务，可以通过 JobDataMap 往任务中传递数据
     * 3、Trigger 中的触发器，是一个类，描述触发 Job 执行的时间触发规则，主要有 SimpleTrigger 和 CronTrigger 这两个子类。
     * （当且仅当需调度一次或者以固定时间间隔周期执行调度，SimpleTrigger 是最适合的选择；）
     * （而 CronTrigger 则可以通过 Cron 表达式定义出各种复杂时间规则的调度方案：如工作日周一到周五的 15：00 ~ 16：00 执行调度等）
     * 4、Scheduler 中的任务调度器，通过 Trigger 和 JobDetail 可以用来调度、暂停和删除任务。调度器就相当于一个容器，装载着任务和触发器，该类是一个接口，代表一个 Quartz
     * 的独立运行容器，Trigger 和 JobDetail 可以注册到 Scheduler 中，两者在 Scheduler 中拥有各自的组及名称，组及名称是 Scheduler 查找定位容器中某一对象的依据，Trigger
     * 的组及名称必须唯一，JobDetail 的组和名称也必须唯一（但可以和 Trigger 的组和名称相同，因为它们是不同类型的）
     */
    public void quartzJob() throws SchedulerException {
        // 1、JobDetail 创建任务
        JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class)
                .withIdentity("job1", "group1").build();

        // 2、Trigger 创建触发器 每3秒钟执行一次
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger1", "group3")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(3).repeatForever())
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        // 将任务及其触发器放入调度器
        scheduler.scheduleJob(jobDetail, trigger);
        // 调度器开始调度任务
        scheduler.start();
    }
}
