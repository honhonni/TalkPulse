package cn.edu.ncu.talkpulse.group.entity;


import java.io.Serializable;

public class Groupvalidation implements Serializable {
	// id
	private Integer groupvalidationId;

	// 邀请者id
	private Integer groupvalidationSenderid;

	// 受邀者id
	private Integer groupvalidationReceiverid;

	// 群聊id
	private Integer groupvalidationGroupid;

	// 验证状态
	private Integer groupvalidationStatus;

	// 是否已读
	private Integer groupvalidationReadstatus;

	public Integer getGroupvalidationId() {
		return groupvalidationId;
	}

	public void setGroupvalidationId(Integer groupvalidationId) {
		this.groupvalidationId = groupvalidationId;
	}

	public Integer getGroupvalidationSenderid() {
		return groupvalidationSenderid;
	}

	public void setGroupvalidationSenderid(Integer groupvalidationSenderid) {
		this.groupvalidationSenderid = groupvalidationSenderid;
	}

	public Integer getGroupvalidationReceiverid() {
		return groupvalidationReceiverid;
	}

	public void setGroupvalidationReceiverid(Integer groupvalidationReceiverid) {
		this.groupvalidationReceiverid = groupvalidationReceiverid;
	}

	public Integer getGroupvalidationGroupid() {
		return groupvalidationGroupid;
	}

	public void setGroupvalidationGroupid(Integer groupvalidationGroupid) {
		this.groupvalidationGroupid = groupvalidationGroupid;
	}

	public Integer getGroupvalidationStatus() {
		return groupvalidationStatus;
	}

	public void setGroupvalidationStatus(Integer groupvalidationStatus) {
		this.groupvalidationStatus = groupvalidationStatus;
	}

	public Integer getGroupvalidationReadstatus() {
		return groupvalidationReadstatus;
	}

	public void setGroupvalidationReadstatus(Integer groupvalidationReadstatus) {
		this.groupvalidationReadstatus = groupvalidationReadstatus;
	}
}
