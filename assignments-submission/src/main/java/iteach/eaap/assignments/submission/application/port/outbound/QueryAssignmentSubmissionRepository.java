package iteach.eaap.assignments.submission.application.port.outbound;

import java.util.List;

import iteach.eaap.assignments.submission.domain.Submission;

public interface QueryAssignmentSubmissionRepository {
	List<Submission> queryAllByAssignmentId(String assignmentId);
}
