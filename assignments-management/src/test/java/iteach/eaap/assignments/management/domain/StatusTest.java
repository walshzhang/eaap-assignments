package iteach.eaap.assignments.management.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Status 单元测试")
public class StatusTest {

    @Test
    @DisplayName("只有已创建状态才能转换为已发布状态")
    void test_change_to_published() {
        // 如果状态是 CREATED，那么可以正常转换为已发布状态
        Assertions.assertEquals(Status.PUBLISHED,
                Status.CREATED.changeTo(Status.PUBLISHED));

        // 如果不是 CREATED，那么不可以转换为已发布状态
        Stream.of(Status.CLOSED, Status.EXPIRED, Status.REMOVED).forEach(
                status -> assertThrows(AssignmentStatusException.class,
                        () -> status.changeTo(Status.PUBLISHED))
        );

        // 如果是 REMOVED，那么转换为 REMOVED 不会报错
        assertDoesNotThrow(() -> Status.REMOVED.changeTo(Status.REMOVED));
    }
}
