package iteach.eaap.assignments.submission;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("作业提交服务的集成测试")
public class CreateSubmissionControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("创建提交接口")
    void test_create_submission() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String json = "{\"title\": \"t\", \"description\": \"aaaaaaaaaaaaaaaaaaaa\", \"deadline\": \"2020-05-16 15:50:00\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        String id = restTemplate.postForObject("http://localhost:39999/assignments", request, String.class);

        json = "{\"code\": \"int i = 5;\", \"assignment\": \"" + id + "\"}";
        mockMvc.perform(
                post("/submissions")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
