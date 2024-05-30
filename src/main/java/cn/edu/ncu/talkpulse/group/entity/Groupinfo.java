package cn.edu.ncu.talkpulse.group.entity;


public class Groupinfo {
	// 群聊id
	private Integer group_id;

	// 群聊名称
	private String group_name;

	// 群聊简介
	private String group_introduce;

	// 群主id
	private Integer group_hostid;
	private String group_photo;

	public Groupinfo(Integer group_id, String group_name, String group_introduce, Integer group_hostid) {
		this.group_id = group_id;
		this.group_name = group_name;
		this.group_introduce = group_introduce;
		this.group_hostid = group_hostid;
	}

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
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

	public Integer getGroup_hostid() {
		return group_hostid;
	}

	public void setGroup_hostid(Integer group_hostid) {
		this.group_hostid = group_hostid;
	}

	public String getGroup_photo() {
		return group_photo;
	}

	public void setGroup_photo(String group_photo) {
		this.group_photo = group_photo;
	}
}
