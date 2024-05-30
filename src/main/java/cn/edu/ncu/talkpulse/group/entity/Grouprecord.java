package cn.edu.ncu.talkpulse.group.entity;


import java.time.LocalDateTime;



public class Grouprecord {
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

	// 群聊消息类型（0文本、1图片、2语音）
	private int grouprecord_type;

	public Grouprecord() {
	}

	public Grouprecord(int grouprecord_id, String grouprecord_content, LocalDateTime grouprecord_time, int grouprecord_senderid, int grouprecord_groupid, int grouprecord_type) {
		this.grouprecord_id = grouprecord_id;
		this.grouprecord_content = grouprecord_content;
		this.grouprecord_time = grouprecord_time;
		this.grouprecord_senderid = grouprecord_senderid;
		this.grouprecord_groupid = grouprecord_groupid;
		this.grouprecord_type = grouprecord_type;
	}

	public Grouprecord(String grouprecord_content, LocalDateTime grouprecord_time, int grouprecord_senderid, int grouprecord_groupid, int grouprecord_type) {
		this.grouprecord_content = grouprecord_content;
		this.grouprecord_time = grouprecord_time;
		this.grouprecord_senderid = grouprecord_senderid;
		this.grouprecord_groupid = grouprecord_groupid;
		this.grouprecord_type = grouprecord_type;
	}

	@Override
	public String toString() {
		return "grouprecord{" +
				"grouprecord_id=" + grouprecord_id +
				", grouprecord_content='" + grouprecord_content + '\'' +
				", grouprecord_time=" + grouprecord_time +
				", grouprecord_senderid=" + grouprecord_senderid +
				", grouprecord_groupid=" + grouprecord_groupid +
				", grouprecord_type=" + grouprecord_type +
				'}';
	}

	public int getGrouprecord_type() {
		return grouprecord_type;
	}

	public void setGrouprecord_type(int grouprecord_type) {
		this.grouprecord_type = grouprecord_type;
	}

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
