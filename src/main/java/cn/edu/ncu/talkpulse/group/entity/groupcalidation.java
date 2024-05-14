package cn.edu.ncu.talkpulse.group.entity;


import java.time.LocalDateTime;

public class groupcalidation {
	// id
	private int groupvalidation_id;

	// 邀请者id
	private int groupvalidation_senderid;

	// 受邀者id
	private int groupvalidation_receiverid;

	// 群聊id
	private int groupvalidation_groupid;

	// 验证状态
	private boolean groupvalidation_status;

	// 是否已读
	private boolean groupvalidation_readstatus;

	private LocalDateTime groupvalidation_time;

	public int getGroupvalidation_id() {
		return groupvalidation_id;
	}

	public void setGroupvalidation_id(int groupvalidation_id) {
		this.groupvalidation_id = groupvalidation_id;
	}

	public int getGroupvalidation_senderid() {
		return groupvalidation_senderid;
	}

	public void setGroupvalidation_senderid(int groupvalidation_senderid) {
		this.groupvalidation_senderid = groupvalidation_senderid;
	}

	public int getGroupvalidation_receiverid() {
		return groupvalidation_receiverid;
	}

	public void setGroupvalidation_receiverid(int groupvalidation_receiverid) {
		this.groupvalidation_receiverid = groupvalidation_receiverid;
	}

	public int getGroupvalidation_groupid() {
		return groupvalidation_groupid;
	}

	public void setGroupvalidation_groupid(int groupvalidation_groupid) {
		this.groupvalidation_groupid = groupvalidation_groupid;
	}

	public boolean isGroupvalidation_status() {
		return groupvalidation_status;
	}

	public void setGroupvalidation_status(boolean groupvalidation_status) {
		this.groupvalidation_status = groupvalidation_status;
	}

	public boolean isGroupvalidation_readstatus() {
		return groupvalidation_readstatus;
	}

	public void setGroupvalidation_readstatus(boolean groupvalidation_readstatus) {
		this.groupvalidation_readstatus = groupvalidation_readstatus;
	}

	public LocalDateTime getGroupvalidation_time() {
		return groupvalidation_time;
	}

	public void setGroupvalidation_time(LocalDateTime groupvalidation_time) {
		this.groupvalidation_time = groupvalidation_time;
	}
}
