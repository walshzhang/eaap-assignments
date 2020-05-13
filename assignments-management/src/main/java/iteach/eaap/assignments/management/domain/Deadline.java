package iteach.eaap.assignments.management.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 值对象
 *
 */
@Embeddable
public class Deadline {
	@Column(name = "deadline")
	private LocalDateTime datetime;

	// JPA
	public Deadline() {}
	
	public Deadline(LocalDateTime datetime) {
		this.datetime(datetime);
	}
	
	/**
	 * 此处没有使用 setXxx 方法
	 */
	private void datetime(LocalDateTime datetime) {
		if(datetime.isBefore(LocalDateTime.now())) {
			throw new AssignmentDeadlineException("作业截止日期不能早于当前日期");
		}
		
		// 作业不能超过一周
		LocalDateTime aWeekLater = LocalDateTime.now().plus(1, ChronoUnit.WEEKS);
		if(datetime.isAfter(aWeekLater)) {
			throw new AssignmentDeadlineException("作业截止日期不能超过一周");
		}

		this.datetime = datetime;
	}
	
	public boolean isExpired() {
		return this.datetime.isBefore(LocalDateTime.now());
	}
	
	public LocalDateTime value() {
		return this.datetime;
	}

}
