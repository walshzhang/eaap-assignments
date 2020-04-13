package iteach.eaap.assignments.submission.application;

import iteach.eaap.assignments.submission.application.port.outbound.CreateSubmissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import iteach.eaap.assignments.submission.application.port.inbound.CreateSubmissionUseCase;
import iteach.eaap.assignments.submission.domain.Submission;
import iteach.eaap.assignments.submission.domain.SubmissionException;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
class CreateSubmissionApplicationService implements CreateSubmissionUseCase {
	private final RestTemplate restTemplate;
	private final CreateSubmissionRepository repository;

	@Override
	public void createSubmission(Submission submission) {
		String url = "http://localhost:39999/assignments/" + submission.getAssignment() + "/status";
		String status = restTemplate.getForObject(url, String.class);

		assert status != null;
		if(!status.equalsIgnoreCase("published")) {
			throw new SubmissionException("提交失败");
		}

		String userId = "1";
		submission.setId(UUID.randomUUID().toString());
		submission.setUser(userId);
		submission.setDatetime(LocalDateTime.now());
		repository.addSubmission(submission);
	}
}
