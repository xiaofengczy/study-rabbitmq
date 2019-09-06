package com.study.rabbitmq;

import javax.annotation.Resource;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: SendController Description:
 *
 * @author caozhongyu
 * @create 19-9-6
 */
@RestController
@EnableBinding(SendExchange.class)
public class SendController {

  @Resource
  private SendExchange sendExchange;

  @GetMapping("/sendMsg")
  public String sendMsg(@RequestParam("msg") String msg) {
    sendExchange.sendMg().send(MessageBuilder.withPayload(msg).build());
    return "success";
  }


}