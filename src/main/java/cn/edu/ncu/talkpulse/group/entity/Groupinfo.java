package cn.edu.ncu.talkpulse.group.entity;


import java.io.Serializable;

public class Groupinfo {
	// 群聊id
	private Integer groupId;

	// 群聊名称
	private String groupName;

	// 群聊简介
	private String groupIntroduce;

	// 群主id
	private Integer groupHostid;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupIntroduce() {
		return groupIntroduce;
	}

	public void setGroupIntroduce(String groupIntroduce) {
		this.groupIntroduce = groupIntroduce;
	}

	public Integer getGroupHostid() {
		return groupHostid;
	}

	public void setGroupHostid(Integer groupHostid) {
		this.groupHostid = groupHostid;
	}
}
