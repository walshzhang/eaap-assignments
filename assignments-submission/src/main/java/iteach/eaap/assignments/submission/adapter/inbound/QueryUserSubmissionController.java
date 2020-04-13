package iteach.eaap.assignments.submission.adapter.inbound;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import iteach.eaap.assignments.submission.application.port.inbound.QueryUserSubmissionUseCase;
import iteach.eaap.assignments.submission.domain.Submission;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
class QueryUserSubmissionController {
	QueryUserSubmissionUseCase usecase;
	
	@GetMapping("/submissions")
	List<Submission> submissions() {
		String userId = "1"; // TODO hard code
		return usecase.queryAll(userId);
	}
}
