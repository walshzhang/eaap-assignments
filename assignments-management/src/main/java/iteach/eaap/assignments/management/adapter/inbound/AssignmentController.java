package iteach.eaap.assignments.management.adapter.inbound;

import iteach.eaap.assignments.management.application.port.inbound.AssignmentUseCase;
import iteach.eaap.assignments.management.domain.AssignmentException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
class AssignmentController {
    private final AssignmentUseCase usecase;

    // DTO(Data Transfer Object) 模式
    @PostMapping("/assignments")
    public ResponseEntity<Object> newAssignment(
            @Valid @RequestBody AssignmentDTO assignment,
            Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usecase.createAssignment(assignment));
    }

    @GetMapping("/assignments")
    List<AssignmentDTO> getAllAssignments() {
        return usecase.getAllAssignments();
    }

    @GetMapping("/assignments/{id}")
    AssignmentDTO getAssignment(@PathVariable String id) {
        try {
            return usecase.getAssignment(id);
        } catch (AssignmentException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/assignments/publish/{id}")
    void publishAssignment(@PathVariable String id) {
        usecase.publishAssignments(id);
    }

    @PutMapping("/assignments/close/{id}")
    void closeAssignment(@PathVariable String id) {
        usecase.closeAssignment(id);
    }

    @PutMapping("/assignments/remove/{id}")
    void removeAssignment(@PathVariable String id) {
        usecase.removeAssignment(id);
    }

    @GetMapping("/assignments/{id}/status")
    String getAssignmentStatus(@PathVariable String id) {
        return usecase.statusOf(id);
    }
}
