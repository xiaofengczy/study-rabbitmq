package com.study.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * FileName: RabbitmqReceive Description:
 *
 * @author caozhongyu
 * @create 19-9-6
 */
@EnableDiscoveryClient
@SpringBootApplication
public class RabbitmqReceive {

  public static void main(String[] args) {
    SpringApplication.run(RabbitmqReceive.class);
  }

}