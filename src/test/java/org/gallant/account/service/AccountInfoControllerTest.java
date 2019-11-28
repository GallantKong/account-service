package org.gallant.account.service;

import com.alibaba.fastjson.JSONObject;
import java.util.Date;
import javax.annotation.Resource;
import org.gallant.account.controller.AccountInfoController;
import org.gallant.account.domain.dto.AccountInfoSaveDTO;
import org.gallant.account.domain.dto.AccountInfoUpdateDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author kongyong
 * @date 2019/11/30
 */
@AutoConfigureMockMvc
public class AccountInfoControllerTest extends UnitTestBase {

    private MockMvc mockMvc;
    @Resource
    private AccountInfoController accountInfoController;
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountInfoController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void queryByPage() throws Exception {
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf8")
                .accept(MediaType.APPLICATION_JSON)
                .param("size", "10")
                .param("page", "2")
                .param("startDate", new Date().toString())
                .param("endDate", new Date().toString());
        // 发送请求，获取请求结果
        ResultActions perform = mockMvc.perform(request);
        // 请求结果校验
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println("response:"+response);
    }

    @Test
    public void query() throws Exception {
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf8")
                .accept(MediaType.APPLICATION_JSON)
                .param("id", "1");
        // 发送请求，获取请求结果
        ResultActions perform = mockMvc.perform(request);
        // 请求结果校验
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println("response:"+response);
    }

    @Test
    public void save() throws Exception {
        AccountInfoSaveDTO accountInfoSaveDTO = new AccountInfoSaveDTO();
        accountInfoSaveDTO.setAccountBankName("我的银行");
        accountInfoSaveDTO.setAccountCardCode("就不告诉你");
        accountInfoSaveDTO.setAccountTypeName("我就是类型");
        accountInfoSaveDTO.setOwner("会灰翔的灰机");
        accountInfoSaveDTO.setRepaymentDate(new Date());
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf8")
                .accept(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(accountInfoSaveDTO));
        // 发送请求，获取请求结果
        ResultActions perform = mockMvc.perform(request);
        // 请求结果校验
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println("response:"+response);
    }

    @Test
    public void update() throws Exception {
        AccountInfoUpdateDTO accountInfoUpdateDTO = new AccountInfoUpdateDTO();
        accountInfoUpdateDTO.setId(2);
        accountInfoUpdateDTO.setAccountTypeName("我就是类型22222");
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf8")
                .accept(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(accountInfoUpdateDTO));
        // 发送请求，获取请求结果
        ResultActions perform = mockMvc.perform(request);
        // 请求结果校验
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println("response:"+response);
    }

    @Test
    public void delete() throws Exception {
        // 构建请求
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf8")
                .accept(MediaType.APPLICATION_JSON)
                .param("id", "1");
        // 发送请求，获取请求结果
        ResultActions perform = mockMvc.perform(request);
        // 请求结果校验
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println("response:"+response);
    }

}
