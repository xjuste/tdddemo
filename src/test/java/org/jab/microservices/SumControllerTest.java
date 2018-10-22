package org.jab.microservices;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jab.microservices.SumRequest;
import org.jab.microservices.SumResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenSumWithPostAndFormData_whenMockMVC_thenResponseOKResult() throws Exception  {

        Integer param1 = 1;
        Integer param2 = 3;

        final SumRequest request = new SumRequest(param1, param2);
        final ObjectMapper mapper = new ObjectMapper();

        final String jsonBodyRequest = mapper.writeValueAsString(request);
        final String expectedJsonContent = mapper.writeValueAsString(new SumResponse(param1 + param2));
        this.mockMvc.perform(post("/sum")
                .contentType(MediaType.APPLICATION_JSON)
        .content(jsonBodyRequest))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJsonContent));
    }
}
