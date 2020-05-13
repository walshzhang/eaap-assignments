package iteach.eaap.assignments.management;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@DisplayName("作业管理服务集成测试")
class AssignmentControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    private static String id = "";

    @Test
    @Order(1)
    void test_get_all_assignments() throws Exception {
        mockMvc.perform(get("/assignments"))
                .andExpect(handler().methodName("getAllAssignments"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(2)
    void test_create_assignment() throws Exception {
        String json = "{\"title\": \"title from test\", \"description\": \"description from test\", \"deadline\": \"2020-05-15 00:00:00\"}";
        MvcResult result = mockMvc.perform(
                post("/assignments")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        id = result.getResponse().getContentAsString();
        System.out.println("created id = " + id);
        assertNotNull(id);
    }

    @Test
    @Order(3)
    void test_get_assignment_status() throws Exception {
        String response = mockMvc.perform(get("/assignments/" + id + "/status"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("CREATED", response);
    }

    @Test
    @Order(4)
    void test_publish_assignment() throws Exception {
        mockMvc.perform(put("/assignments/publish/" + id))
                .andDo(print())
                .andExpect(status().isOk());
        String response = mockMvc.perform(get("/assignments/" + id + "/status"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("PUBLISHED", response);
    }

    @Test
    @Order(5)
    void test_get_assignment_details() throws Exception {
        mockMvc.perform(get("/assignments/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("title from test"))
                .andExpect(jsonPath("$.status").value("PUBLISHED"));
    }

    @Test
    @Order(6)
    void test_close_assignment() throws Exception {
        mockMvc.perform(put("/assignments/close/" + id))
                .andDo(print())
                .andExpect(status().isOk());
        String response = mockMvc.perform(get("/assignments/" + id + "/status"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("CLOSED", response);
    }

    @Test
    @Order(7)
    void test_remove_assignment() throws Exception {
        mockMvc.perform(put("/assignments/remove/" + id))
                .andDo(print())
                .andExpect(status().isOk());
        String response = mockMvc.perform(get("/assignments/" + id + "/status"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("REMOVED", response);
    }

    @Test
    @DisplayName("测试非法的创建作业请求")
    void test_create_invalid_assignment() throws Exception {
        String json = "{\"title\": \"title from test\", \"description\": \"description from test\", \"deadline\": \"2020-05-10 00:00:00\"}";
        mockMvc.perform(
                post("/assignments")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("获取一个不存在的作业")
    void test_get_not_exist_assignment_details() throws Exception {
        mockMvc.perform(get("/assignments/2f5e530d-bff9-425c-8b7e-49cccb0c6eff"))
                .andDo(print())
                .andExpect(status().is5xxServerError());
    }
}
