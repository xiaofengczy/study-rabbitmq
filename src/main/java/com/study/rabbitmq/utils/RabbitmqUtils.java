package com.study.rabbitmq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * FileName: RabbitmqUtils Description:
 *
 * @author caozhongyu
 * @create 2019/9/1
 */
public class RabbitmqUtils {

  public static Connection connectRabbitmq() {
    //创建连接工厂
    ConnectionFactory connectionFactory = new ConnectionFactory();
    //设置连接信息
    connectionFactory.setHost("148.70.30.245");
    connectionFactory.setVirtualHost("/study-rabbitmq");
    connectionFactory.setUsername("admin");
    connectionFactory.setPassword("admin");
    try {
      return connectionFactory.newConnection();
    } catch (TimeoutException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

}