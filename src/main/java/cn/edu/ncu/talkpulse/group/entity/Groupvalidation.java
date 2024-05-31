package cn.edu.ncu.talkpulse.group.entity;


import java.time.LocalDateTime;

public class Groupvalidation {
	// id
	private Integer groupvalidation_id;

	// 邀请者id
	private Integer groupvalidation_senderid;

	// 受邀者id
	private Integer groupvalidation_receiverid;

	// 群聊id
	private Integer groupvalidation_groupid;

	// 验证状态
	private Byte groupvalidation_status;

	// 是否已读
	private Byte groupvalidation_readstatus;

	private LocalDateTime groupvalidation_time;

	public Integer getGroupvalidation_id() {
		return groupvalidation_id;
	}

	public void setGroupvalidation_id(Integer groupvalidation_id) {
		this.groupvalidation_id = groupvalidation_id;
	}

	public Integer getGroupvalidation_senderid() {
		return groupvalidation_senderid;
	}

	public void setGroupvalidation_senderid(Integer groupvalidation_senderid) {
		this.groupvalidation_senderid = groupvalidation_senderid;
	}

	public Integer getGroupvalidation_receiverid() {
		return groupvalidation_receiverid;
	}

	public void setGroupvalidation_receiverid(Integer groupvalidation_receiverid) {
		this.groupvalidation_receiverid = groupvalidation_receiverid;
	}

	public Integer getGroupvalidation_groupid() {
		return groupvalidation_groupid;
	}

	public void setGroupvalidation_groupid(Integer groupvalidation_groupid) {
		this.groupvalidation_groupid = groupvalidation_groupid;
	}

	public Byte isGroupvalidation_status() {
		return groupvalidation_status;
	}

	public void setGroupvalidation_status(Byte groupvalidation_status) {
		this.groupvalidation_status = groupvalidation_status;
	}

	public Byte isGroupvalidation_readstatus() {
		return groupvalidation_readstatus;
	}

	public void setGroupvalidation_readstatus(Byte groupvalidation_readstatus) {
		this.groupvalidation_readstatus = groupvalidation_readstatus;
	}

	public LocalDateTime getGroupvalidation_time() {
		return groupvalidation_time;
	}

	public void setGroupvalidation_time(LocalDateTime groupvalidation_time) {
		this.groupvalidation_time = groupvalidation_time;
	}
}
