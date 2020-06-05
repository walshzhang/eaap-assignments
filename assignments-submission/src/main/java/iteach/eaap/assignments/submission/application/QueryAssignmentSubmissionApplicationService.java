package iteach.eaap.assignments.submission.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iteach.eaap.assignments.submission.application.port.outbound.QueryAssignmentSubmissionRepository;
import iteach.eaap.assignments.submission.application.port.inbound.QueryAssignmentSubmissionUseCase;
import iteach.eaap.assignments.submission.domain.Submission;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
class QueryAssignmentSubmissionApplicationService implements QueryAssignmentSubmissionUseCase{
	private final QueryAssignmentSubmissionRepository repository;
	
	@Override
	public List<Submission> querySubmissions(String assignmentId) {
		return repository.queryAllByAssignmentId(assignmentId);
	}

}
