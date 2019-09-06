package com.study.rabbitmq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * FileName: SendExchange Description:
 *
 * @author caozhongyu
 * @create 19-9-6
 */
public interface SendExchange {

  String SEND_MSG = "send_msg";

  @Output(SendExchange.SEND_MSG)
  MessageChannel sendMg();


}