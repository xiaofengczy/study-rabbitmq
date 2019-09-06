package com.study.rabbitmq.topic;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Envelope;
import com.study.rabbitmq.utils.RabbitmqUtils;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * FileName: Recv Description:
 *
 * @author caozhongyu
 * @create 19-9-2
 */
public class Recv {

  private static final String EXCHANGE_NAME = "exchange_topic";
  private static final String ROUTE_KEY_BLACK = "test.color.black";
  private static final String QUEUE_NAME = "topic_queue";

  public static void main(String[] args) throws IOException, TimeoutException {
    //连接至rabbitmq
    Connection connection = RabbitmqUtils.connectRabbitmq();
    //创建通道
    Channel channel = connection.createChannel();
    //声明队列
    channel.queueDeclare(QUEUE_NAME, true, false, false, null);
    //声明交换机
    channel.exchangeDeclare(EXCHANGE_NAME, "topic");
    //交换机绑定队列
    channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTE_KEY_BLACK);
    //获取消息
    System.out.println("消费者开始消费消息");

    Consumer defaultConsumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope,
          AMQP.BasicProperties properties,
          byte[] body) throws IOException {
        String message = new String(body, "utf-8"); // 消息正文
        System.out.println("收到消息 => " + message);
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
//        channel.basicAck(envelope.getDeliveryTag(),
//            false); // 手动确认消息【参数说明：参数一：该消息的index；参数二：是否批量应答，true批量确认小于当前id的消息】
      }
    };
//    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//      String msg = new String(delivery.getBody(), "UTF-8");
//      System.out.println(msg);
//      channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
//    };
    channel.basicConsume(QUEUE_NAME, false, "", defaultConsumer);
  }

}