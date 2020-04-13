package iteach.eaap.assignments.submission.application.port.outbound;

import iteach.eaap.assignments.submission.domain.Submission;

import java.util.List;

public interface QueryUserSubmissionRepository {
    List<Submission> queryAllByUserId(String userId);
}
