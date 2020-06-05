package iteach.eaap.assignments.submission;

import org.junit.jupiter.api.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@DisplayName("作业提交系统的系统测试")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationSystemTest {
    static RestTemplate rest;
    static String id;

    @BeforeAll
    static void initAll() {
        rest = new RestTemplate();
    }

    @Test
    @DisplayName("创建合法的作业")
    @Order(1)
    void test_create_assignment() {
        String json = "{\"title\": \"t\", \"description\": \"aaaaaaaaaaaaaaaaaaaa\", \"deadline\": \"2020-05-13 15:50:00\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        id = rest.postForObject("http://localhost:39999/assignments", request, String.class);
    }

    @Test
    @DisplayName("创建非法作业 - 标题为空，对应测试用例2")
    void test_create_invalid_assignment_with_blank_title() {
        String json = "{\"title\": \"\", \"description\": \"aaaaaaaaaaaaaaaaaaaa\", \"deadline\": \"2020-05-13 15:00:00\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(json, headers);

        Assertions.assertThrows(Exception.class, () -> rest.postForObject(
                "http://localhost:39999/assignments",
                request, String.class));

        // TODO 如何获取出错状态的状态码
//        ResponseEntity<String> entity = rest.exchange("http://localhost:39999/assignments",
//                HttpMethod.POST, request, String.class);
//        Assertions.assertEquals(400, entity.getStatusCode().value());
    }

    // 其余类似，略
    // 更鼓励大家使用 ParameterizedTest

    @Test
    @DisplayName("正常发布作业，对应测试用例编号 #")
    @Order(2)
    void test_publish_assignment() {
        ResponseEntity<Object> entity = rest.exchange("http://localhost:39999/assignments/publish/" + id, HttpMethod.PUT, null, Object.class);
        Assertions.assertEquals(200, entity.getStatusCode().value());
    }
}
