# leo-server

Leo System Server

## 项目架构

- leo-common: 基础配置
- leo-system: 系统服务

## 版本

- Java: `17.0.6`
- SpringBoot: `3.0.2`
- MySQL: `8.0.31`

## 依赖

- [lombok](https://projectlombok.org): `1.18.24`
- [mybatis-plus](https://baomidou.com): `3.5.3`
- [mapstruct](https://mapstruct.org): `1.5.3.Final`
- [knife4j](https://doc.xiaominfo.com): `4.0.0`

## 注意

- 参数校验方法如@NotNull有默认错误提示信息，如无特殊需求，无需重写message
- ~~继承LeoEnum的枚举类型，如果需要在swagger接口文档页面进行完全内容展示，需要重写toString方法~~
  - (通过重写io.swagger.v3.core.converter.ModelConverter实现类，避免每个继承LeoEnum枚举都需要重写toString方法)
- knife4j现版本存在bug，无法自动提供配置提示