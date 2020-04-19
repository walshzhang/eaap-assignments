package iteach.eaap.assignments.management.adapter.inbound;

import iteach.eaap.assignments.management.application.port.inbound.AssignmentUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
class AssignmentController {
	private final AssignmentUseCase usecase;
	
	// DTO(Data Transfer Object) 模式
	@PostMapping("/assignments")
	public ResponseEntity<Object> newAssignment(
			@RequestBody AssignmentDTO assignment,
			@Valid Errors errors) {
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest().body(errors);
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
		return usecase.getAssignment(id);
	}

	@PutMapping("/assignments/publish/{id}")
	void publishAssignment(@PathVariable String id) {
		usecase.publishAssignments(id);
	}

	@PutMapping("/assignments/close/{id}")
	void closeAssignment(@PathVariable String id) {
		usecase.closeAssignment(id);
	}

	@DeleteMapping("/assignments/close/{id}")
	void removeAssignment(@PathVariable String id) {
		usecase.removeAssignment(id);
	}

	@GetMapping("/assignments/{id}/status")
	String getAssignmentStatus(@PathVariable String id) {
		return usecase.statusOf(id);
	}
}
