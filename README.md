# PetAdopt - 宠物领养平台

一个宠物领养平台Web应用，支持用户浏览宠物、收藏、申请领养、消息咨询等功能。

## 技术栈

### 后端
- **框架**: Spring Boot 3.2.0
- **语言**: Java 17
- **数据库**: MySQL
- **认证**: JWT (jjwt 0.12.3)
- **安全**: Spring Security

### 前端
- **技术**: HTML/CSS/JavaScript (静态页面)
- **依赖**: pptxgenjs (仅用于生成报告)

## 项目结构

```
Pet2.0/
├── backend/                 # Spring Boot 后端
│   ├── src/main/java/com/petadopt/
│   │   ├── controller/      # 控制器层
│   │   ├── service/         # 业务逻辑层
│   │   ├── repository/      # 数据访问层
│   │   ├── entity/          # 实体类
│   │   ├── dto/              # 数据传输对象
│   │   ├── security/        # 安全配置 (JWT)
│   │   ├── config/          # 配置类
│   │   └── exception/       # 异常处理
│   └── pom.xml
├── index.html               # 前端页面
└── package.json             # npm 配置
```

## 主要功能

### 用户模块
- 用户注册/登录
- JWT token 认证

### 宠物模块
- 浏览宠物列表
- 查看宠物详情
- 搜索/筛选宠物

### 收藏模块
- 收藏宠物
- 查看收藏列表
- 取消收藏

### 领养申请模块
- 提交领养申请
- 查看申请状态
- 管理员审核

### 消息模块
- 与救助站/发布者私信沟通
- 消息收发

### 订阅模块
- 订阅宠物更新通知

## 数据库表

- `user` - 用户表
- `pet` - 宠物表
- `shelter` - 救助站表
- `favorite` - 收藏表
- `application` - 领养申请表
- `conversation` - 对话表
- `message` - 消息表
- `user_preference` - 用户偏好设置

## 运行项目

### 后端

1. 确保已安装 MySQL 并创建数据库
2. 配置数据库连接 (application.properties)
3. 运行项目:

```bash
cd backend
mvn spring-boot:run
```

后端默认运行在 `http://localhost:8080`

### 前端

直接用浏览器打开 `index.html` 即可，或使用静态服务器：

```bash
# 使用 Python
python -m http.server 8081

# 或使用 Node.js
npx serve .
```

前端默认运行在 `http://localhost:8081`

## API 端点

| 模块 | 端点 | 方法 | 说明 |
|------|------|------|------|
| 认证 | `/api/auth/register` | POST | 用户注册 |
| 认证 | `/api/auth/login` | POST | 用户登录 |
| 宠物 | `/api/pets` | GET | 获取宠物列表 |
| 宠物 | `/api/pets/{id}` | GET | 获取宠物详情 |
| 收藏 | `/api/favorites` | GET | 获取收藏列表 |
| 收藏 | `/api/favorites` | POST | 添加收藏 |
| 收藏 | `/api/favorites/{id}` | DELETE | 删除收藏 |
| 申请 | `/api/applications` | POST | 提交申请 |
| 申请 | `/api/applications` | GET | 查看申请 |
| 消息 | `/api/conversations` | GET | 获取对话列表 |
| 消息 | `/api/conversations` | POST | 创建对话 |
| 消息 | `/api/conversations/{id}/messages` | GET | 获取消息 |
| 消息 | `/api/conversations/{id}/messages` | POST | 发送消息 |
| 订阅 | `/api/subscribe` | POST | 订阅宠物 |

## 配置

后端配置文件: `backend/src/main/resources/application.properties`

主要配置项:
- `spring.datasource.url` - 数据库连接
- `spring.datasource.username` - 数据库用户名
- `spring.datasource.password` - 数据库密码
- `jwt.secret` - JWT 密钥
- `jwt.expiration` - Token 过期时间
