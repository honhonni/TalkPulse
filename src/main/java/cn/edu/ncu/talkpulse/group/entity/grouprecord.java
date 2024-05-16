package cn.edu.ncu.talkpulse.group.entity;


import java.time.LocalDateTime;



public class grouprecord {
	// id
	private int grouprecord_id;

	// 群聊内容
	private String grouprecord_content;

	// 发送时间
	private LocalDateTime grouprecord_time;

	// 发送用户id
	private int grouprecord_senderid;

	// 发送群聊号
	private int grouprecord_groupid;

	public int getGrouprecord_id() {
		return grouprecord_id;
	}

	public void setGrouprecord_id(int grouprecord_id) {
		this.grouprecord_id = grouprecord_id;
	}

	public String getGrouprecord_content() {
		return grouprecord_content;
	}

	public void setGrouprecord_content(String grouprecord_content) {
		this.grouprecord_content = grouprecord_content;
	}

	public LocalDateTime getGrouprecord_time() {
		return grouprecord_time;
	}

	public void setGrouprecord_time(LocalDateTime grouprecord_time) {
		this.grouprecord_time = grouprecord_time;
	}

	public int getGrouprecord_senderid() {
		return grouprecord_senderid;
	}

	public void setGrouprecord_senderid(int grouprecord_senderid) {
		this.grouprecord_senderid = grouprecord_senderid;
	}

	public int getGrouprecord_groupid() {
		return grouprecord_groupid;
	}

	public void setGrouprecord_groupid(int grouprecord_groupid) {
		this.grouprecord_groupid = grouprecord_groupid;
	}
}
