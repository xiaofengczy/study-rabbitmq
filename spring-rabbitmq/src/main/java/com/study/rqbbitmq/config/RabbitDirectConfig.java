package com.study.rqbbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FileName: RabbitDirectConfig Description:
 *
 * @author caozhongyu
 * @create 19-9-2
 */
@Configuration
public class RabbitDirectConfig {

  public final static String DIRECT_EXCHANEGE_NAME = "test-direct";

  @Bean
  public Queue queue() {
    return new Queue("direct-queue");
  }

  @Bean
  public DirectExchange directExchange() {
    return new DirectExchange(DIRECT_EXCHANEGE_NAME, true, false);
  }

  @Bean
  public Binding binding() {
    return BindingBuilder.bind(queue()).to(directExchange()).with("direct");
  }
}