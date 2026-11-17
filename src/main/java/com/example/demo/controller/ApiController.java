package com.example.demo.controller;

import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演示用的三个接口：
 * 1. GET  /api/hello   打招呼
 * 2. GET  /api/status  服务运行状态
 * 3. POST /api/echo    回显请求内容
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    private final Instant startedAt = Instant.now();

    /** 接口 1：打招呼 */
    @GetMapping("/hello")
    public Map<String, Object> hello(@RequestParam(name = "name", defaultValue = "World") String name) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "Hello, " + name + "!");
        body.put("timestamp", OffsetDateTime.now(ZoneId.of("Asia/Shanghai")).toString());
        return body;
    }

    /** 接口 2：服务状态 */
    @GetMapping("/status")
    public Map<String, Object> status() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", "UP");
        body.put("service", "springboot-demo-api");
        body.put("javaVersion", System.getProperty("java.version"));
        body.put("os", System.getProperty("os.name"));
        body.put("uptimeSeconds", Duration.between(startedAt, Instant.now()).getSeconds());
        return body;
    }

    /** 接口 3：回显 */
    @PostMapping("/echo")
    public Map<String, Object> echo(@RequestBody(required = false) Map<String, Object> payload) {
        Map<String, Object> data = payload == null ? Map.of() : payload;
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("echo", data);
        body.put("fieldCount", data.size());
        body.put("receivedAt", OffsetDateTime.now(ZoneId.of("Asia/Shanghai")).toString());
        return body;
    }
}
