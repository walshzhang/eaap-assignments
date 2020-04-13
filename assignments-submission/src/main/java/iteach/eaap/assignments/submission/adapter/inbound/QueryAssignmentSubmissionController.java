package iteach.eaap.assignments.submission.adapter.inbound;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import iteach.eaap.assignments.submission.application.port.inbound.QueryAssignmentSubmissionUseCase;
import iteach.eaap.assignments.submission.domain.Submission;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
class QueryAssignmentSubmissionController {
	QueryAssignmentSubmissionUseCase usecase;
	
	@GetMapping("/submissions/{assignmentId}")
	List<Submission> submissionsOf(String assignmentId) {
		return usecase.querySubmissions(assignmentId);
	}
}
