package com.study.rqbbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * FileName: RabbitmqTopConfig Description:
 *
 * @author caozhongyu
 * @create 19-9-3
 */
@Component
public class RabbitmqTopConfig {

  public final static String EXCHANGE_TOP="top_excange";

  @Bean
  public TopicExchange topicExchange(){
    return new TopicExchange(EXCHANGE_TOP);
  }

  @Bean
  public Queue xiaomi(){
    return new Queue("xiaomi");
  }

  @Bean
  public Queue huawei(){
    return new Queue("huawei");
  }

  @Bean
  public Queue phone(){
    return new Queue("phone");
  }

  @Bean
  public Binding xiaomiBinding(){
    return BindingBuilder.bind(xiaomi()).to(topicExchange()).with("xiaomi.#");
  }

  @Bean
  public Binding huaweiBinding(){
    return BindingBuilder.bind(huawei()).to(topicExchange()).with("huawei.#");
  }

  @Bean
  public Binding phoneBinding(){
    return BindingBuilder.bind(phone()).to(topicExchange()).with("#.phone.#");
  }

}