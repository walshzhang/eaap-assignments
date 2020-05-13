package iteach.eaap.assignments.management.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

@DisplayName("值对象 Deadline 的单元测试")
class DeadlineTest {
    @Test
    @DisplayName("datetime 必须晚于当前时间，否则报错")
    void test_too_early_deadline() {
        Assertions.assertThrows(AssignmentDeadlineException.class,
                () ->new Deadline(LocalDateTime.now().minusDays(3)));
    }

    @Test
    @DisplayName("datetime 不能晚于一周之后")
    void test_7_days_later_deadline() {
        Assertions.assertThrows(AssignmentDeadlineException.class,
                () ->new Deadline(LocalDateTime.now().plusDays(8)));
    }

    @Test
    @DisplayName("测试是否过期")
    void test_is_expired() {
        Deadline deadline = new Deadline(LocalDateTime.now().plusDays(3));
        Assertions.assertFalse(deadline.isExpired());
    }

}
