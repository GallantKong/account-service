package org.gallant.account.service;

import java.util.Date;
import javax.annotation.Resource;
import org.gallant.account.controller.AccountInfoController;
import org.junit.Before;
import org.junit.Test;
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
        System.out.println(response);
    }

}
