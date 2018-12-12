package com.linyuan.resource1server.web.rest;

import com.linyuan.resource1server.Resource1ServerApplication;
import com.linyuan.resource1server.web.api.dto.LoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author: 林塬
 * @date: 2018/1/19
 * @description: 资源获取单元测试
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Resource1ServerApplication.class)
@AutoConfigureMockMvc
public class ResControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private OAuth2AccessToken oAuth2AccessToken;

    /**
     * 获取令牌
     * @throws Exception
     */
    @Before
    public void getToken() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("linyuan");
        loginDTO.setPassword("123456");

        byte[] body = this.mockMvc.perform(
                post("/login")
                        .content(objectMapper.writeValueAsBytes(loginDTO))
                        .contentType(MediaType.APPLICATION_JSON)    //请求数据的格式
                        .accept(MediaType.APPLICATION_JSON)         //接收返回数据的格式
        ).andExpect(status().isOk())
         .andReturn().getResponse().getContentAsByteArray();
        oAuth2AccessToken = objectMapper.readValue(body,OAuth2AccessToken.class);
        System.out.println("------------------------------token----------------------------------" +oAuth2AccessToken);
        
        String fileName="D:\\kuka.txt";
        String txt = oAuth2AccessToken.getValue();
                     try
                       {
                            //使用这个构造函数时，如果存在kuka.txt文件，
                            //则先把这个文件给删除掉，然后创建新的kuka.txt
                             FileWriter writer=new FileWriter(fileName);
                              writer.write(txt);
                              writer.close();
                       } catch (IOException e)
                       {
                                 e.printStackTrace();
                       }
    }

    /**
     * 测试访问本地受保护资源
     * @throws Exception
     */
    @Test
    public void testGetLocalRes() throws Exception{
  
        int status = this.mockMvc.perform(
                get("/res")
                        //.header("Authorization",OAuth2AccessToken.BEARER_TYPE+" "+oAuth2AccessToken.getValue())
                .header("Authorization",oAuth2AccessToken.getValue())
                        .accept(MediaType.APPLICATION_JSON)

        ).andDo(print()).andReturn().getResponse().getStatus();
        printStatus(status);
        Assert.assertEquals(status,HttpStatus.SC_OK);
    }

    /**
     * 测试访问资源服务器2受保护资源
     * @throws Exception
     */
/*    @Test
    public void testGetRes2lRes() throws Exception{
        int status = this.mockMvc.perform(
                get("/res2/res")
                        .header("Authorization",OAuth2AccessToken.BEARER_TYPE+" "+"7a98cc93-922e-431f-9474-36ae45b1461c")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andReturn().getResponse().getStatus();
        printStatus(status);
        Assert.assertEquals(status,HttpStatus.SC_OK);
    }*/

    private void printStatus(int status){
        switch (status) {
            case HttpStatus.SC_OK:
            	 System.out.println("测试访问受保护资源---------------->成功（200）");
                break;
            case HttpStatus.SC_UNAUTHORIZED:
            	 System.out.println("测试访问受保护资源---------------->失败（401---没有权限，请确认令牌无误，角色权限无误，请注意是否 Authorization 请求头部 Basic 打成了 Bearer）");
                break;
            case HttpStatus.SC_BAD_REQUEST:
            	 System.out.println("测试访问受保护资源---------------->失败（400---请求失败，请检查用户密码是否正确）");
                break;
            default:
            	 System.out.println("测试访问本地受保护资源---------------->失败（{}---未知结果）"+status);
                break;
        }
    }
}
