package iteach.eaap.assignments.submission.adapter.outbound;

import iteach.eaap.assignments.submission.application.port.inbound.QueryAssignmentSubmissionUseCase;
import iteach.eaap.assignments.submission.application.port.outbound.QueryAssignmentSubmissionRepository;
import iteach.eaap.assignments.submission.domain.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaQueryAssignmentSubmissionRepository extends QueryAssignmentSubmissionRepository, JpaRepository<Submission, String> {
    @Override
    default List<Submission> queryAllByAssignmentId(String assignmentId) {
        return findAllByAssignment(assignmentId);
    }

    List<Submission> findAllByAssignment(String assignment);
}
