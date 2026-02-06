# 快手广告数据定时任务 - 使用说明

## 功能概述

本项目实现了一个定时任务，每小时自动调用快手广告数据查询 API，并将获取到的数据发送到钉钉机器人。

## 实现的功能

### 1. 定时任务 (KuaiSchedule.java)
- **执行频率**: 每小时执行一次（在每小时的第0分钟）
- **Cron 表达式**: `0 0 * * * ?`
- **功能**:
  - 调用快手广告数据查询 API
  - 解析返回的 JSON 数据
  - 将数据发送到钉钉机器人
  - 异常处理和错误通知

### 2. 钉钉工具类 (DingTalkUtil.java)
提供两种消息发送方式：
- `sendMessage()`: 发送 Markdown 格式的消息（支持标题和格式化内容）
- `sendTextMessage()`: 发送纯文本消息

### 3. 配置文件 (application.properties)
```properties
# 钉钉机器人 webhook URL
dingtalk.webhook.url=https://oapi.dingtalk.com/robot/send?access_token=YOUR_ACCESS_TOKEN

# 快手广告数据查询 API 配置
kwai.api.url=https://ads.kwai.com/rest/i18n/adDsp/effectReport/tableCustomQuery/v1
kwai.account.id=75123452
kwai.client.id=0
```

## 使用步骤

### 第一步：获取钉钉机器人 Webhook URL

1. 打开钉钉应用
2. 进入需要接收消息的群组
3. 点击群设置 → 群机器人 → 添加机器人
4. 选择"自定义"机器人
5. 设置机器人名称和权限
6. 获取 Webhook URL，格式如下：
   ```
   https://oapi.dingtalk.com/robot/send?access_token=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
   ```

### 第二步：配置 Webhook URL

编辑 `application.properties` 文件，将获取到的 Webhook URL 替换为实际值：

```properties
dingtalk.webhook.url=https://oapi.dingtalk.com/robot/send?access_token=YOUR_ACTUAL_ACCESS_TOKEN
```

### 第三步：配置快手 API 参数（可选）

如果需要修改快手 API 的账户 ID 或其他参数，编辑 `application.properties`：

```properties
kwai.account.id=YOUR_ACCOUNT_ID
kwai.client.id=YOUR_CLIENT_ID
```

### 第四步：启动应用

```bash
mvn spring-boot:run
```

或者构建后运行：

```bash
mvn clean package
java -jar target/adput-0.0.1-SNAPSHOT.jar
```

## 消息格式

### 成功消息
当定时任务成功执行时，钉钉会收到以下格式的消息：

```
### 快手广告数据查询结果

**查询时间**: 2026-02-07 10:00:00

**响应状态**: 200

**数据详情**:
[API 返回的数据]
```

### 错误消息
当定时任务执行失败时，钉钉会收到以下格式的消息：

```
### 快手广告数据查询失败

**查询时间**: 2026-02-07 10:00:00

**错误信息**: [具体错误信息]

**请及时检查系统日志**
```

## 文件说明

| 文件 | 说明 |
|------|------|
| `KuaiSchedule.java` | 定时任务主类，包含定时任务逻辑 |
| `DingTalkUtil.java` | 钉钉机器人工具类，提供消息发送功能 |
| `application.properties` | 应用配置文件，包含 Webhook URL 和 API 配置 |

## 依赖

项目已包含以下依赖：
- `fastjson`: JSON 处理
- `okhttp3`: HTTP 请求
- `spring-boot-starter-web`: Spring Boot Web 支持
- `spring-boot`: 定时任务支持（@Scheduled）

## 日志输出

定时任务执行时会输出以下日志：

```
========== 快手广告数据查询定时任务开始 [2026-02-07 10:00:00] ==========
快手 API 响应: {...}
钉钉消息发送成功: {...}
========== 快手广告数据查询定时任务结束 ==========
```

## 常见问题

### Q: 如何修改执行频率？
A: 修改 `KuaiSchedule.java` 中的 `@Scheduled` 注解的 cron 表达式。例如：
- `0 */5 * * * ?` - 每5分钟执行一次
- `0 0 0 * * ?` - 每天午夜执行一次
- `0 0 * * * ?` - 每小时执行一次（当前配置）

### Q: 如何测试定时任务？
A: 可以临时修改 cron 表达式为 `0 * * * * ?`（每分钟执行一次），测试完成后改回原值。

### Q: 钉钉消息发送失败怎么办？
A: 
1. 检查 Webhook URL 是否正确
2. 检查网络连接
3. 查看应用日志中的错误信息
4. 确保钉钉机器人有发送消息的权限

### Q: 如何自定义消息内容？
A: 修改 `KuaiSchedule.java` 中的 `sendToDingTalk()` 和 `sendErrorToDingTalk()` 方法。

## 扩展建议

1. **数据持久化**: 将查询结果保存到数据库
2. **数据分析**: 对获取的数据进行统计分析
3. **告警机制**: 根据数据阈值发送告警消息
4. **多账户支持**: 支持查询多个快手账户的数据
5. **定时调整**: 根据业务需求调整执行频率

## 技术栈

- Java 8
- Spring Boot 2.6.13
- OkHttp 4.9.3
- FastJSON 1.2.78
- Maven

## 联系方式

如有问题或建议，请联系项目维护者。

