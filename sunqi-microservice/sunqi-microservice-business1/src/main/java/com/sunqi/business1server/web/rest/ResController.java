package com.sunqi.business1server.web.rest;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: 林塬
 * @date: 2018/1/16
 * @description: 资源服务器1-资源接口
 */
@RestController
@AllArgsConstructor
public class ResController {
	@Autowired
    private RestTemplate restTemplate;
	

	 @Autowired
	 private FeginClient feginClient;
	 
	   @GetMapping("/res2test")
	    public ResponseEntity<String> res2(){
	        return feginClient.res2();
	    }

    @GetMapping("/res")
    public ResponseEntity<String> res(){
        return ResponseEntity.ok("<h1>这是资源服务器1的受保护的资源</h1>");
    }

    /**
     * 访问资源服务器2-资源接口
     * @param httpReq
     * @return
     *//*
    @GetMapping("/res2/res")
    public ResponseEntity<String> remoteRes(HttpServletRequest httpReq){
        //HttpEntity
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization",httpReq.getHeader("Authorization"));
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        //请求资源服务器2的资源
       // return restTemplate.exchange("http://localhost:9006/res",HttpMethod.GET,httpEntity,String.class);
        return ResponseEntity.ok("ADMIN 资源2");
    }*/

}
