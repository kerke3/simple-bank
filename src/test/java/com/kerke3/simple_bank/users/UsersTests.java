package com.kerke3.simple_bank;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kerke3.simple_bank.common.dto.response.StandardResponse;
import com.kerke3.simple_bank.common.dto.request.UserIdRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;

@SpringBootTest
@AutoConfigureMockMvc
class UsersTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @Order(1)
    void ShouldFailUserCreate() throws Exception {
        HashMap<String,String> badRequestBody = new HashMap<>();
        badRequestBody.put("user","anas");
        String userRequestJSON = objectMapper.writeValueAsString(badRequestBody);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/create").contentType(MediaType.APPLICATION_JSON).content(userRequestJSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
        StandardResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>(){});
        Assertions.assertFalse(response.success());
    }
    @Test
    @Order(2)
    void ShouldCreateUser() throws Exception {
        UserIdRequest userIdRequest = getUserIdRequest();
        String userRequestJSON = objectMapper.writeValueAsString(userIdRequest);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/create").contentType(MediaType.APPLICATION_JSON).content(userRequestJSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        StandardResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>(){});
        Assertions.assertTrue(response.success());
    }

    private UserIdRequest getUserIdRequest() {
        return new UserIdRequest("anas");
    }

}
