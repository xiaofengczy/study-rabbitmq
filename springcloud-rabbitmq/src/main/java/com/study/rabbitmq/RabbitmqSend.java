package com.study.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * FileName: RabbitmqSend Description:
 *
 * @author caozhongyu
 * @create 19-9-6
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RabbitmqSend {

  public static void main(String[] args) {
    SpringApplication.run(RabbitmqSend.class);
  }

}