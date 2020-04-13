package iteach.eaap.assignments.management.domain;

/**
 * 如果状态管理过于复杂，可以使用状态模式进行重构
 *
 */
public enum Status {
	CREATED,
	PUBLISHED,
	EXPIRED,
	CLOSED,
	REMOVED;
	
	public Status changeTo(Status newStatus) {
		// 如果当前状态处于 REMOVED，那么它不可以变成其他状态
		if(this == Status.REMOVED) {
			if(newStatus != Status.REMOVED) {
				throw new AssignmentStatusException("不能修改已删除作业的状态");
			}
		}
		
		// 如果当前状态不是 CREATED， 那么不允许发布
		if(this != Status.CREATED) {
			if(newStatus == Status.PUBLISHED) {
				throw new AssignmentStatusException("不能发布一个不是已创建状态的作业");
			}
		}
		
		// .....
		
		return newStatus;
	}
}
