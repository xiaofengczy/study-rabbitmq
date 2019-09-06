package com.study.rabbitmq;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * FileName: ReceiveService Description:
 *
 * @author caozhongyu
 * @create 19-9-6
 */
@Service
@EnableBinding(ReceiveExchange.class)
public class ReceiveService {

  @StreamListener(ReceiveExchange.SEND_MSG)
  public void getMsg(Object msg){
    System.out.println(msg);
  }

}