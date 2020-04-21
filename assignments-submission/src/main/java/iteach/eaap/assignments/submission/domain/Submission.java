package iteach.eaap.assignments.submission.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "SUBMISSIONS")
public class Submission {
	@Id
	private String id;
	private String code;
	private String user;
	private LocalDateTime datetime;
	private String assignment;

	public String getAssignmentId() {
	    return assignment;
    }
}
