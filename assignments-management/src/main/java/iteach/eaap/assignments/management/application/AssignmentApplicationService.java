package iteach.eaap.assignments.management.application;

import iteach.eaap.assignments.management.adapter.inbound.AssignmentDTO;
import iteach.eaap.assignments.management.application.port.inbound.AssignmentUseCase;
import iteach.eaap.assignments.management.application.port.outbound.AssignmentRepository;
import iteach.eaap.assignments.management.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
class AssignmentApplicationService implements AssignmentUseCase {
	private final AssignmentRepository repository;
	
	@Override
	public String createAssignment(AssignmentDTO assignment) {
		String title = assignment.getTitle();
		String description = assignment.getDescription();
		Deadline deadline = new Deadline(assignment.getDeadline());
		
		Assignment entity = new Assignment(title, description, deadline);
		repository.add(entity);
		return entity.makeAssignmentDTO().getId();
	}

	@Override
	public List<AssignmentDTO> getAllAssignments() {
		return repository.queryAll().stream()
				.map(Assignment::makeAssignmentDTO)
				.collect(Collectors.toList());
	}

	private Assignment assignmentFor(String id) {
		Assignment assignment = repository.queryById(AssignmentId.of(id));

		if(assignment == null) {
			throw new AssignmentException("没有找到指定 id 的作业：" + id);
		}

		return assignment;
	}

	@Override
	public void publishAssignments(String id) {
		Assignment assignment = assignmentFor(id);
		assignment.changeStatus(Status.PUBLISHED);
		repository.update(assignment);
	}

	@Override
	public void closeAssignment(String id) {
		Assignment assignment = assignmentFor(id);
		assignment.changeStatus(Status.CLOSED);
		repository.update(assignment);
	}

	@Override
	public void removeAssignment(String id) {
		Assignment assignment = assignmentFor(id);
		assignment.changeStatus(Status.REMOVED);
		repository.update(assignment);
	}

	@Override
	public String statusOf(String id) {
		return assignmentFor(id).status().toString();
	}

	@Override
	public AssignmentDTO getAssignment(String id) {
		Assignment assignment = assignmentFor(id);
		return assignment.makeAssignmentDTO();
	}
}
