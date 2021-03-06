# 1. 字段列表

Order status 四种状态 unpaid, paided, accepted, rejected

# 2. 业务规则

* 为了方便客户使用，减小装机成本，应当使用容易安装使用的客户端软件，例如微信小程序
* 。。。

# 3. 质量需求
## 3.1 可用性
* 对于APP新用户来说，学习使用该软件，并完成成功下单付款（忽略挑选菜式的过程）的所有流程要在3分钟内。
* 对于APP老用户，从挑选好菜式到下单付款不能超过4个页面，操作和页面响应时间之和不超过1分钟。

## 3.2 可靠性
* 系统一天内的错误发生率要保证在 0.1% 之内
* <span data-type="color" style="color:rgb(0, 0, 0)">平均无故障时间（MTBF，Mean Time Between Failures）保证在30天以上</span>
* 系统自动恢复时间：5min 内
* <span data-type="color" style="color:rgb(0, 0, 0)">平均修复时间（MTTR，Mean Time To Repair）保证在 1h 内</span>

## 3.3 性能
* 要求能在 5 台 8GB，8Cores CPU 的机器中运行部署该软件
* 要求日平均系统CPU资源利用率不超过50%
* 要求承载10000人同时在线
* 在10000人同时在线的情况下，保证每个请求的响应保证在3秒内
* 系统可支持最小的吞吐量： 300 TPS

## 3.4 可支持性
* 用户升级程序能保留自身原来的历史设置信息和个人信息
* 95%的紧急错误能在 2h 内解决修复
* 在修复故障时，未修复的缺陷应该小于 0.5

# 4. 设计约束
* 采用 JAVA或者Golang 进行编码
* 采用 MySQL 作为数据库
* 同时具有安卓客户端和微信小程序客户端

# 5. 许可需求
* 本软件开源
* 允许他人修改该程序源代码
* 被修改的文件可以不放置版权说明
* 综上，本软件使用 MIT 许可证开源 [https://opensource.org/licenses/MIT](https://opensource.org/licenses/MIT)

# 6. 适用的标准
* 服务之间的接口设计应该符合 Restful API 的设计标准，参见 github api 设计规范
* 微信小程序界面设计应该符合微信官方的 WeUI 设计标准

