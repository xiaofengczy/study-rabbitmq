package com.study.rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.study.rabbitmq.utils.RabbitmqUtils;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * FileName: Send Description:
 *
 * @author caozhongyu
 * @create 19-9-2
 */
public class Send {

  private static final String EXCHANE_NAME = "logs";

  public static void main(String[] args) throws IOException, TimeoutException {
    //连接至rabbitmq
    Connection connection = RabbitmqUtils.connectRabbitmq();
    //创建通道
    Channel channel = connection.createChannel();
    //声明交换机
    channel.exchangeDeclare(EXCHANE_NAME, "fanout");
    //定义消息
    String message = "hello rabbitmq , fanout";
    //发送消息
    channel.basicPublish(EXCHANE_NAME, "", null, message.getBytes());
    //记录发送消息
    System.out.println(message);
    channel.close();
    connection.close();
  }

}