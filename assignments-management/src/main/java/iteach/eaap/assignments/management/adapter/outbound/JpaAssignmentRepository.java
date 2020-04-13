package iteach.eaap.assignments.management.adapter.outbound;

import iteach.eaap.assignments.management.application.port.outbound.AssignmentRepository;
import iteach.eaap.assignments.management.domain.Assignment;
import iteach.eaap.assignments.management.domain.AssignmentId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface JpaAssignmentRepository extends AssignmentRepository, JpaRepository<Assignment, AssignmentId> {
    @Override
    default void add(Assignment assignment) {
        this.save(assignment);
    }

    @Override
    default void update(Assignment assignment) {
        this.save(assignment);
    }

    @Override
    default List<Assignment> queryAll() {
        return this.findAll();
    }

    @Override
    default Assignment queryById(AssignmentId id) {
        return this.findById(id).orElse(null);
    }

}
