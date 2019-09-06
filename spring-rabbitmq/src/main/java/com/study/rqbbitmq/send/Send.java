package com.study.rqbbitmq.send;

import com.study.rqbbitmq.config.RabbitmqFanoutConfig;
import com.study.rqbbitmq.config.RabbitmqTopConfig;
import javax.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * FileName: DirectSend Description:
 *
 * @author caozhongyu
 * @create 19-9-3
 */
@Service
public class Send {


  @Resource
  private RabbitTemplate rabbitTemplate;

  public void sendDirect(String queue, String msg) {
    rabbitTemplate.convertAndSend(queue, msg);
  }

  public void sendFanout(String msg) {
    rabbitTemplate.convertAndSend(RabbitmqFanoutConfig.EXCHANGE_FANOUT,null,msg);
  }

  public void sendhuawei(String msg) {
    rabbitTemplate.convertAndSend(RabbitmqTopConfig.EXCHANGE_TOP,"huawei.news",msg);
  }

  public void sendxiaomi(String msg) {
    rabbitTemplate.convertAndSend(RabbitmqTopConfig.EXCHANGE_TOP,"xiaomi.news",msg);
  }

  public void sendPhone(String msg) {
    rabbitTemplate.convertAndSend(RabbitmqTopConfig.EXCHANGE_TOP,"xiaomi.phone",msg);
  }
}