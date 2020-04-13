package iteach.eaap.assignments.submission.application.port.outbound;

import iteach.eaap.assignments.submission.domain.Submission;

public interface CreateSubmissionRepository {
	void addSubmission(Submission submission);
}
