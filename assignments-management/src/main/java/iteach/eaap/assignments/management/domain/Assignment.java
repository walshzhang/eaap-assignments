package iteach.eaap.assignments.management.domain;

import iteach.eaap.assignments.management.adapter.inbound.AssignmentDTO;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "assignments")
public class Assignment {
	@EmbeddedId
	private AssignmentId id;
	private String title;
	private String description;
	@Embedded
	private Deadline deadline;
	private Status status;
	
	// JPA 
	public Assignment() {
		
	}
	
	// Builder， Factory 创建型模式
	public Assignment(String title, String description, Deadline deadline) {
		this.id = new AssignmentId();
		this.title = title;
		this.deadline = deadline;
		this.description = description;
		this.status = Status.CREATED;
	}
	
	// 迎合显示用的
	public AssignmentDTO makeAssignmentDTO() {
		AssignmentDTO dto = new AssignmentDTO();
		dto.setTitle(this.title);
		dto.setDeadline(deadline.value());
		dto.setDescription(this.description);
		dto.setId(id.value());
		dto.setStatus(status.name());
		return dto;
	}
	
	public void close() {
		status = status.changeTo(Status.CLOSED);
	}
	
	public void publish() {
		status = status.changeTo(Status.PUBLISHED);
	}
	
	public void remove() {
		status = status.changeTo(Status.REMOVED);
	}
	
	public Status status() {
		if(deadline.isExpired()) {
			status = status.changeTo(Status.EXPIRED);
		}
		
		return status;
	}
}
