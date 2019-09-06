package com.study.rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.study.rabbitmq.utils.RabbitmqUtils;
import java.io.IOException;

/**
 * FileName: Recv Description:
 *
 * @author caozhongyu
 * @create 19-9-2
 */
public class Recv {

  private static final String EXCHANE_NAME = "logs";

  public static void main(String[] args) throws IOException {
    //连接至rabbitmq
    Connection connection = RabbitmqUtils.connectRabbitmq();
    //创建通道
    Channel channel = connection.createChannel();
    //声明交换机
    channel.exchangeDeclare(EXCHANE_NAME, "fanout");
    //获取队列名
    String queue = channel.queueDeclare().getQueue();
    //队列与交换机绑定
    channel.queueBind(queue, EXCHANE_NAME, "");
    //记录日志
    System.out.println("接收方开始接收消息1");
    //接收消息
    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      String message = new String(delivery.getBody(), "UTF-8");
      System.out.println("接收到消息1:" + message);
    };
    channel.basicConsume(queue, true, deliverCallback, consumerTag -> {
    });
  }
}