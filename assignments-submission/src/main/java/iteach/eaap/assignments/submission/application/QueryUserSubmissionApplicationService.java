package iteach.eaap.assignments.submission.application;

import java.util.List;

import iteach.eaap.assignments.submission.application.port.outbound.QueryUserSubmissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iteach.eaap.assignments.submission.application.port.inbound.QueryUserSubmissionUseCase;
import iteach.eaap.assignments.submission.domain.Submission;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class QueryUserSubmissionApplicationService implements QueryUserSubmissionUseCase {
    QueryUserSubmissionRepository repository;
	@Override
	public List<Submission> queryAll(String userId) {
		return repository.queryAllByUserId(userId);
	}

}
