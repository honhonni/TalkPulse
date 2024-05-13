package cn.edu.ncu.talkpulse.group.entity;


import java.io.Serializable;
import java.time.LocalDateTime;



public class Grouprecord  {
	// id
	private Integer grouprecordId;

	// 群聊内容
	private String grouprecordContent;

	// 发送时间
	private LocalDateTime grouprecordTime;

	// 发送用户id
	private Integer grouprecordSenderid;

	// 发送群聊号
	private Integer grouprecordGroupid;

	public Integer getGrouprecordId() {
		return grouprecordId;
	}

	public void setGrouprecordId(Integer grouprecordId) {
		this.grouprecordId = grouprecordId;
	}

	public String getGrouprecordContent() {
		return grouprecordContent;
	}

	public void setGrouprecordContent(String grouprecordContent) {
		this.grouprecordContent = grouprecordContent;
	}

	public LocalDateTime getGrouprecordTime() {
		return grouprecordTime;
	}

	public void setGrouprecordTime(LocalDateTime grouprecordTime) {
		this.grouprecordTime = grouprecordTime;
	}

	public Integer getGrouprecordSenderid() {
		return grouprecordSenderid;
	}

	public void setGrouprecordSenderid(Integer grouprecordSenderid) {
		this.grouprecordSenderid = grouprecordSenderid;
	}

	public Integer getGrouprecordGroupid() {
		return grouprecordGroupid;
	}

	public void setGrouprecordGroupid(Integer grouprecordGroupid) {
		this.grouprecordGroupid = grouprecordGroupid;
	}
}
