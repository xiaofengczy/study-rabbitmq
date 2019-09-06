package com.study.rabbitmq.topic;

import com.rabbitmq.client.AMQP.Tx;
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

  private static final String EXCHANGE_NAME = "exchange_topic";
  private static final String ROUTE_KEY_BLACK = "test.color.black";

  public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
    //连接至rabbitmq
    Connection connection = RabbitmqUtils.connectRabbitmq();
    //获取连接通道
    Channel channel = connection.createChannel();
    //声明交换机
    channel.exchangeDeclare(EXCHANGE_NAME, "topic");
    //发送消息
    String msg = "hello world ,this is topic";

//    开启事务
//    try{
//      channel.txSelect();
//      channel.basicPublish(EXCHANGE_NAME, ROUTE_KEY_BLACK, null, msg.getBytes());
//      channel.txRollback();
//    }catch (Exception e){
//      e.printStackTrace();
//      channel.txRollback();
//    }

    // 普通确认机制
//    channel.confirmSelect();//开启确认
//    channel.basicPublish(EXCHANGE_NAME, ROUTE_KEY_BLACK, null, msg.getBytes()); //发送消息
//    if(channel.waitForConfirms()){
//      System.out.println("消费发送成功");
//    }

    //批量确认
    channel.confirmSelect();
    for (int i = 0; i < 10; i++) {
      channel.basicPublish(EXCHANGE_NAME, ROUTE_KEY_BLACK, null, msg.getBytes()); //发送消息
    }
    channel.waitForConfirmsOrDie(1000);//只要有一个没确认，则报io异常
    System.out.println(msg);
    channel.close();
    connection.close();
  }

}