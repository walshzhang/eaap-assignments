package iteach.eaap.assignments.submission.adapter.inbound;

import iteach.eaap.assignments.submission.domain.Submission;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import iteach.eaap.assignments.submission.application.port.inbound.CreateSubmissionUseCase;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
class CreateSubmissionController {
	private CreateSubmissionUseCase usecase;
	
	@PostMapping("/submissions")
	void createSubmission(@RequestBody Submission submission) {
		usecase.createSubmission(submission);
	}
}
