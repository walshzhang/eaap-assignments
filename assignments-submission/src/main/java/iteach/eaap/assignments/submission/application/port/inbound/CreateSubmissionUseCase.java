package iteach.eaap.assignments.submission.application.port.inbound;

import iteach.eaap.assignments.submission.domain.Submission;

public interface CreateSubmissionUseCase {
	void createSubmission(Submission submission);
}
