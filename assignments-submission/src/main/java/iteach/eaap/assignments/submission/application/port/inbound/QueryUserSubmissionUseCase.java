package iteach.eaap.assignments.submission.application.port.inbound;

import java.util.List;

import iteach.eaap.assignments.submission.domain.Submission;

public interface QueryUserSubmissionUseCase {
	List<Submission> queryAll(String userId);
}
