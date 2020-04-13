package iteach.eaap.assignments.submission.adapter.outbound;

import iteach.eaap.assignments.submission.application.port.outbound.CreateSubmissionRepository;
import iteach.eaap.assignments.submission.domain.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCreateSubmissionRepository extends CreateSubmissionRepository, JpaRepository<Submission, String> {
    @Override
    default void addSubmission(Submission submission) {
        this.save(submission);
    }
}
