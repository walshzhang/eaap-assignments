package iteach.eaap.assignments.submission.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iteach.eaap.assignments.submission.application.port.inbound.QueryUserSubmissionUseCase;
import iteach.eaap.assignments.submission.domain.Submission;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class QueryUserSubmissionApplicationService implements QueryUserSubmissionUseCase {

	@Override
	public List<Submission> queryAll(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
