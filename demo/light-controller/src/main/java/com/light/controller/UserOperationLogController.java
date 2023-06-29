package com.light.controller;

import com.light.model.Response;
import com.light.model.UserOperationLog;
import com.light.service.UserOperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author light
 * @Date 2023/6/28
 * @Desc
 * Q：千万级的数据量，在没有索引的情况下，查询会达到一分钟甚至100秒；
 * A1：最简单的情况就是添加索引，尽管是千万级，增加索引效果也可以；
 * A2：用Redis，加缓存；
 * A3：读写分离；
 * A4：不要想着去做切分，mysql自带分区表，先试试这个，对你的应用是透明的，无需更改代码,但是sql语句是需要针对分区表做优化的；
 * A5：如果以上都做了，那就先做垂直拆分，其实就是根据你模块的耦合度，将一个大的系统分为多个小的系统，也就是分布式系统；把一个数据库，根据业务的耦合度、功能的划分，拆成若干个小的库。
 * A6：水平切分，针对数据量大的表，这一步最麻烦，最能考验技术水平，要选择一个合理的sharding key,为了有好的查询效率，表结构也要改动，做一定的冗余，应用也要改，sql中尽量带sharding
 * key，将数据定位到限定的表上去查，而不是扫描全部的表；
 * 从上到下，成本依次增加。
 **/
@Slf4j
@RestController
@RequestMapping("/userOperationLog")
public class UserOperationLogController {
    @Autowired
    private UserOperationLogService logService;


    @GetMapping("/getUserDataById")
    public Response getUserDataById(int id) {
        UserOperationLog operationLog = logService.getUserDataById(id);
        return Response.success(operationLog);
    }
}
