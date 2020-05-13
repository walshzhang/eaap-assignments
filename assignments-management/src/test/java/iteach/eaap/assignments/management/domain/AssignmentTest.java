package iteach.eaap.assignments.management.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AssignmentTest {
    @Test
    @DisplayName("测试获取状态")
    void test_get_status() {
        Deadline deadline = Mockito.mock(Deadline.class); // dummy, stub, mock, fake
        Assignment assignment = new Assignment("title", "description", deadline);
        Assertions.assertEquals(Status.CREATED, assignment.status());

        Mockito.when(deadline.isExpired()).thenReturn(true);
        Assertions.assertEquals(Status.EXPIRED, assignment.status());
    }

    @Test
    @DisplayName("测试删除作业")
    void test_remove_assignment() {
        Deadline deadline = Mockito.mock(Deadline.class); // dummy, stub, mock, fake
        Assignment assignment = new Assignment("title", "description", deadline);
        assignment.remove();
        Assertions.assertEquals(Status.REMOVED, assignment.status());
    }

    @Test
    @DisplayName("测试关闭作业")
    void test_close_assignment() {
        Deadline deadline = Mockito.mock(Deadline.class); // dummy, stub, mock, fake
        Assignment assignment = new Assignment("title", "description", deadline);
        assignment.close();
        Assertions.assertEquals(Status.CLOSED, assignment.status());
    }

    @Test
    @DisplayName("测试发布作业")
    void test_publish_assignment() {
        Deadline deadline = Mockito.mock(Deadline.class); // dummy, stub, mock, fake
        Assignment assignment = new Assignment("title", "description", deadline);
        assignment.publish();
        Assertions.assertEquals(Status.PUBLISHED, assignment.status());
    }
}
