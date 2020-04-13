package iteach.eaap.assignments.management.adapter.inbound;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class AssignmentDTO {
	private String id;
	@NotBlank
	private String title;
	@NotBlank
	@Size(min = 100)
	private String description;
	@Future
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime deadline;
}
