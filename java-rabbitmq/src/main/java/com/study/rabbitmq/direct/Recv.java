package com.study.rabbitmq.direct;

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

  private static final String EXCHANE_NAME = "direct_logs";
  private static final String ROUTING_KEY_BLACK = "black";
  private static final String ROUTING_KEY_RED = "red";
  private static final String QUEUE_NAME="direct_queue";

  public static void main(String[] args) throws IOException {
    //连接至rabbitmq
    Connection connection = RabbitmqUtils.connectRabbitmq();
    //创建通道
    Channel channel = connection.createChannel();
    //声明交换机
    channel.exchangeDeclare(EXCHANE_NAME, "direct");
    channel.queueDeclare(QUEUE_NAME,false,false,false,null);
    //获取队列名
    String queue = channel.queueDeclare().getQueue();
    System.out.println(queue);
    //队列与交换机绑定
    channel.queueBind(QUEUE_NAME, EXCHANE_NAME, ROUTING_KEY_BLACK);
    channel.queueBind(QUEUE_NAME, EXCHANE_NAME, ROUTING_KEY_RED);
    //记录日志
    System.out.println("接收方开始接收消息2");
    //接收消息
    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      String message = new String(delivery.getBody(), "UTF-8");
      System.out.println("接收到消息2:" + message);
    };
    channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
    });
  }
}