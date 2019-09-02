package com.study.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import java.io.IOException;
import com.study.rabbitmq.utils.RabbitmqUtils;

/**
 * FileName: Recv Description:
 *
 * @author caozhongyu
 * @create 2019/9/1
 */
public class Recv {

  //定义队列名称
  private final static String QUEUE_NAME = "hello";

  public static void main(String[] args) throws IOException {
    //连接rabbitmq
    Connection connection = RabbitmqUtils.connectRabbitmq();
    final Channel channel = connection.createChannel();
    //声明队列
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
    //接收消息
    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      String message = new String(delivery.getBody(), "UTF-8");
      System.out.println(" [x] Received '" + message + "'");
    };
    channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
    });
  }
}