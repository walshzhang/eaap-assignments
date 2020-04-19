package iteach.eaap.assignments.management.application.port.inbound;

import iteach.eaap.assignments.management.adapter.inbound.AssignmentDTO;

import java.util.List;

public interface AssignmentUseCase {
	String createAssignment(AssignmentDTO assignment);

	List<AssignmentDTO> getAllAssignments();

	void publishAssignments(String id);

	void closeAssignment(String id);

	void removeAssignment(String id);

	String statusOf(String id);

	AssignmentDTO getAssignment(String id);
}
