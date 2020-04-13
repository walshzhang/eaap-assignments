package iteach.eaap.assignments.submission.adapter.outbound;

import iteach.eaap.assignments.submission.application.port.outbound.QueryAssignmentSubmissionRepository;
import iteach.eaap.assignments.submission.application.port.outbound.QueryUserSubmissionRepository;
import iteach.eaap.assignments.submission.domain.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaQueryUserSubmissionRepository extends QueryUserSubmissionRepository, JpaRepository<Submission, String> {
    @Override
    default List<Submission> queryAllByUserId(String assignmentId) {
        return findAllByUser(assignmentId);
    }

    List<Submission> findAllByUser(String assignmentId);
}
