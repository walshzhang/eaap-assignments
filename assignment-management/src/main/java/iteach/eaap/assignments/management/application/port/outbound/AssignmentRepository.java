package iteach.eaap.assignments.management.application.port.outbound;

import iteach.eaap.assignments.management.domain.Assignment;
import iteach.eaap.assignments.management.domain.AssignmentId;

import java.util.List;

public interface AssignmentRepository {
	List<Assignment> queryAll();
	void add(Assignment assignment);
	void update(Assignment assignment);
	Assignment queryById(AssignmentId id);
}
