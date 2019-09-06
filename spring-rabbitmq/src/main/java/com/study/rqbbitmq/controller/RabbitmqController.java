package com.study.rqbbitmq.controller;

import com.study.rqbbitmq.send.Send;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: RabbitmqController Description:
 *
 * @author caozhongyu
 * @create 19-9-3
 */
@RestController
public class RabbitmqController {

  @Resource
  private Send send;

  @GetMapping("/direct/{queue}")
  public void directSend(@PathVariable("queue") String queue, @RequestParam("msg") String msg) {
    send.sendDirect(queue,msg);
  }

  @GetMapping("/fanout")
  public void fanoutSend(@RequestParam("msg") String msg) {
    send.sendFanout(msg);
  }


}