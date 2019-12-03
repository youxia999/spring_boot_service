基于https://github.com/liangxiaobo/test-security-oauth2/tree/master-jdbc改造了
- authorizationserver、resourceserver的持久化层，mybatis + druid + redis store
- authorizationserver改造token store为redis。
- resourceserver删除注册功能
- login server增加注册、登录功能，并用jetty作为容器，并指定根目录