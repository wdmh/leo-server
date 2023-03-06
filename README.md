# leo-server

Leo System Server

## 项目架构

- leo-common: 基础配置
- leo-system: 系统服务

## 版本

- Java version: 17.0.6 (From Liberica)
- springboot version: 3.0.2
- MySQL version: 8.0.31
- MyBatis-Plus version: 3.5.3

## 注意

- 参数校验方法有默认显示，如无特殊需求，无需重写message
- 继承LeoEnum的类型，如果需要在swagger接口文档页面进行完全信息展示，需要重写toString方法