package com.study.rabbitmq.workQueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import com.study.rabbitmq.utils.RabbitmqUtils;
import java.io.IOException;
import javax.xml.transform.Source;

/**
 * FileName: Recv Description:
 *
 * @author caozhongyu
 * @create 2019/9/1
 */
public class Recv {

  //定义队列名称
  private final static String QUEUE_NAME = "test_work_queue";

  public static void main(String[] args) throws IOException {
    //连接rabbitmq
    Connection connection = RabbitmqUtils.connectRabbitmq();
    final Channel channel = connection.createChannel();
    //声明队列
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println(" [*] Waiting for messages1. To exit press CTRL+C");
    channel.basicQos(5);
    //接收消息
    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      String message = new String(delivery.getBody(), "UTF-8");
      System.out.println(" [x] Received1 '" + message + "'");
      try {
        dowork(message);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        System.out.println("[x]  Done");
        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
      }
    };
    channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> {
    });
  }

  private static void dowork(String task) throws InterruptedException {
    for (char ch : task.toCharArray()) {
      if (ch == '.') {
        Thread.sleep(1000);
      }
    }
  }
}