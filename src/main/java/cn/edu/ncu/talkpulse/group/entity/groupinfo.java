package cn.edu.ncu.talkpulse.group.entity;


public class groupinfo {
	// 群聊id
	private int group_id;

	// 群聊名称
	private String group_name;

	// 群聊简介
	private String group_introduce;

	// 群主id
	private int group_hostid;

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getGroup_introduce() {
		return group_introduce;
	}

	public void setGroup_introduce(String group_introduce) {
		this.group_introduce = group_introduce;
	}

	public int getGroup_hostid() {
		return group_hostid;
	}

	public void setGroup_hostid(Integer group_hostid) {
		this.group_hostid = group_hostid;
	}
}
