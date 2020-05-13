package iteach.eaap.assignments.management;

import iteach.eaap.assignments.management.adapter.inbound.AssignmentDTO;
import iteach.eaap.assignments.management.application.port.outbound.AssignmentRepository;
import iteach.eaap.assignments.management.domain.Assignment;
import iteach.eaap.assignments.management.domain.Deadline;
import iteach.eaap.assignments.management.domain.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DisplayName("作业管理服务数据库集成测试")
public class AssignmentRepositoryIntegrationTest {
    @Autowired
    AssignmentRepository repository;

    // fake object
    @Test
    void test_query_all() {
        List<Assignment> list = repository.queryAll();
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    void test_add_assignment() {
        Assignment assignment = new Assignment("title", "description", new Deadline(LocalDateTime.now().plusDays(2)));
        repository.add(assignment);

        List<Assignment> list = repository.queryAll();
        assertEquals(1, list.size());
        assertEquals(assignment.makeAssignmentDTO(), list.get(0).makeAssignmentDTO());
    }
}
