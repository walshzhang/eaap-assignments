package iteach.eaap.assignments.management.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

/**
 * 值对象
 *
 */
@Embeddable
public class AssignmentId implements Serializable {
	@Column(name = "id")
	private String value;
	
	public AssignmentId() {
		this.value = UUID.randomUUID().toString();
	}

	public static AssignmentId of(String id) {
		AssignmentId assignmentId = new AssignmentId();
		assignmentId.value = UUID.fromString(id).toString();
		return assignmentId;
	}
	
	/**
	 * 此处没有使用 getValue
	 * 也没有使用 Lombok
	 * 
	 */
	public String value() {
		return this.value;
	}
}
