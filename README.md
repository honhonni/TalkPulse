# TalkPulse
- 聊出心动（项目名称取自ai）
#### 项目整体文件说明
- `pom.xml` maven依赖配置文件
- `src` 项目的资源文件
  - `main` 提供的公共访问数据库的方法
    - 'java' 存放后端代码
      - 'cn.edu.ncu.talkpulse' 包
        - 'account' 存放 account 相关的 service，dao, entity
        - 'friends' 存放 friends 相关的 service，dao, entity
        - 'group' 存放 group 相关的 service，dao, entity
        - 'config' 项目配置文件，包含 MyConfig、OpenBroswer
        - 'dto' 数据传输类型
        - 'controller' 存放api接口文件目录
        - 'TalkPulseApplication.java' 项目的启动入口
    - 'resources' 主要存放项目的静态资源文件
      - 'static' 主要存放css、js、images、voice、fonts、db
      - 'template' 存放html静态页面
  - 'test' 测试目录
- `target` 存放maven项目构建生成后的文件
