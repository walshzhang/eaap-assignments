package iteach.eaap.assignments.submission.application.port.inbound;

import java.util.List;

import iteach.eaap.assignments.submission.domain.Submission;

public interface QueryAssignmentSubmissionUseCase {
	List<Submission> querySubmissions(String assignmentId);
}
