package light.quant.adput.schedule;

import org.springframework.stereotype.Component;

/**
 * @Author light
 * @Date 2024/12/6
 * @Desc
 **/
@Component
public class AdputSchedule {

//    @Scheduled(cron = "0 */1 * * * ?")
    public void startEntrance(){
        // 1、生成链接

        // 1、获取参数
        System.out.println("定时任务执行开始");
        System.out.println("定时任务执行结束");
    }
}
