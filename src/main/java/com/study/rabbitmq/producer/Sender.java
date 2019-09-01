package com.study.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.study.rabbitmq.utils.RabbitmqUtils;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * FileName: Sender Description:
 *
 * @author caozhongyu
 * @create 2019/9/1
 */
public class Sender {

  //定义队列名称
  private final static String QUEUE_NAME = "hello";

  public static void main(String[] args) throws IOException, TimeoutException {
    //连接rabbitmq
    Connection connection = RabbitmqUtils.connectRabbitmq();
    Channel channel = connection.createChannel();
    //声明队列
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    // 定义消息内容
    String message = "Hello World!";
    //发送消息
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    System.out.println("[x] Sent'" + message + "'");
    channel.close();
    connection.close();
  }

}