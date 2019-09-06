package com.study.rabbitmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

/**
 * FileName: ReceiveExchange Description:
 *
 * @author caozhongyu
 * @create 19-9-6
 */
public interface ReceiveExchange {

  String SEND_MSG = "send_msg";

  @Input(ReceiveExchange.SEND_MSG)
  MessageChannel sendMg();
}