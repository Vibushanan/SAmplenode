package com.websterbank.hello;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

//@EnableDiscoveryClient
@SpringBootApplication
public class HelloWorldServiceApplication {

    private final AtomicInteger counter = new AtomicInteger();
    private final String instanceId     = UUID.randomUUID().toString();
    private final String startedAt      = now();

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldServiceApplication.class, args);
    }

    String now() {
        return DateTimeFormatter.ISO_INSTANT
                .withZone(ZoneId.of("UTC"))
                .format(Instant.now());
    }

    @RefreshScope
    @RestController
    class MessageRestController {

        @Autowired
        private DiscoveryClient discoveryClient;

        @Value("${message:Hello default}")
        private String message;

        @RequestMapping(value = "/message", produces = "application/json")
        public Message getMessage(@RequestParam(value = "name", defaultValue = "anonymous") String name) {
            return new Message(String.format("%s from %s", this.message, name));
        }

        @RequestMapping("/instances/{applicationName}")
        public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
            return this.discoveryClient.getInstances(applicationName);
        }
    }

    class Message {

        private int count      = counter.incrementAndGet();
        private String current = now();
        private String message;
        private String started = startedAt;
        private String uuid    = instanceId;

        public Message(String message) {
            this.message = message;
        }

        public int getCount() {
            return count;
        }

        public String getCurrent() {
            return current;
        }

        public String getMessage() {
            return message;
        }

        public String getStarted() {
            return started;
        }

        public String getUuid() {
            return uuid;
        }

    }

}
