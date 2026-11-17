# springboot-demo-api

一个最小的 Spring Boot 3 示例项目，内置三个 REST 接口。

## 技术栈

- Java 17
- Spring Boot 3.3.5
- Maven

## 接口一览

| 方法 | 路径 | 说明 | 示例 |
| --- | --- | --- | --- |
| GET | `/api/hello?name=World` | 返回打招呼信息 | `{"message":"Hello, World!","timestamp":"..."}` |
| GET | `/api/status` | 返回服务运行状态 | `{"status":"UP","service":"springboot-demo-api",...}` |
| POST | `/api/echo` | 回显请求 JSON 内容 | `{"echo":{"message":"ping"},"fieldCount":1,...}` |

## 本地运行

```bash
mvn spring-boot:run
```

服务启动在 <http://localhost:8080>。

## 快速验证

```bash
curl "http://localhost:8080/api/hello?name=WorkBuddy"
curl "http://localhost:8080/api/status"
curl -X POST "http://localhost:8080/api/echo" \
  -H "Content-Type: application/json" \
  -d '{"message":"ping","from":"curl"}'
```

## 打包与测试

```bash
mvn clean package
java -jar target/springboot-demo-api-0.0.1-SNAPSHOT.jar
```
