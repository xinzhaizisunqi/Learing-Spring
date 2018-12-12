package com.linyuan.resource1server.web.rest;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("resource2-server")
public interface FeginClient {
	
	 @RequestMapping(value = "/res2", method = RequestMethod.GET)
	  public ResponseEntity<String> res2(); // 两个坑：1. @GetMapping不支持   2. @PathVariable得设置value

}
