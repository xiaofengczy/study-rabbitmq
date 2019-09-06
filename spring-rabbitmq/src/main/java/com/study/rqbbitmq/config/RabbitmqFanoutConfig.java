package com.study.rqbbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FileName: RabbitmqFanoutConfig Description:
 *
 * @author caozhongyu
 * @create 19-9-3
 */
@Configuration
public class RabbitmqFanoutConfig {

  public final static String EXCHANGE_FANOUT = "exchange_fanout";

  @Bean
  public FanoutExchange fanoutExchange(){
    return new FanoutExchange(EXCHANGE_FANOUT,true,false);
  }

  @Bean
  public Queue queueOne(){
    return new Queue("fanout-one");
  }

  @Bean
  public Queue queueTwo(){
    return new Queue("fanout-two");
  }

  @Bean
  public Binding bindingOne(){
    return BindingBuilder.bind(queueOne()).to(fanoutExchange());
  }

  @Bean
  public Binding bindingTwo(){
    return BindingBuilder.bind(queueTwo()).to(fanoutExchange());
  }
}