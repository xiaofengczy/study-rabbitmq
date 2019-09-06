package com.study.rqbbitmq.recv;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * FileName: DirectReceiver Description:
 *
 * @author caozhongyu
 * @create 19-9-2
 */
@Component
public class Receiver {

  @RabbitListener(queues = "direct-queue")
  public void directReceiver(String msg){
    System.out.println("DirectReceiver:"+msg);
  }

  @RabbitListener(queues = "fanout-one")
  public void fanoutReceiver1(String msg){
    System.out.println("fanout-one:"+msg);
  }

  @RabbitListener(queues = "fanout-two")
  public void fanoutReceiver2(String msg){
    System.out.println("fanout-two:"+msg);
  }

  @RabbitListener(queues = "phone")
  public void topicPhone(String msg){
    System.out.println("phone-receiver:"+msg);
  }

  @RabbitListener(queues = "xiaomi")
  public void topicxiaomi(String msg){
    System.out.println("xiaomi-receiver:"+msg);
  }

  @RabbitListener(queues = "huawei")
  public void topicHuawei(String msg){
    System.out.println("huawei-receiver:"+msg);
  }


}