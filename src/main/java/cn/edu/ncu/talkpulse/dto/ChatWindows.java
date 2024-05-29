package cn.edu.ncu.talkpulse.dto;

import java.time.LocalDateTime;

public class ChatWindows {
    private String messagetype;// "friends" (or "groups")
    private LocalDateTime compare_time;

    private Integer user_id;// 好友的id
    private String user_photo;// 好友的头像
    private String user_name;// 好友的名称
    private LocalDateTime record_time;//最新的
    private String record_content;//最新的
    private Integer record_type;//最新的类型
    private Integer no_read;// 自己为接收方的未读条数

    private Integer group_id;//群id
    private String group_photo;//群头像
    private String group_name;// 群名称
    private LocalDateTime grouprecord_time;//最新的
    private String grouprecord_content;//最新的
    private Integer  grouprecord_type;//最新的消息类型
    private Boolean group_no_read;// 查询corre表判断是否有新消息，有的话，值设为1

  /*  public ChatWindows(String messagetype,Integer user_id,String user_photo,String user_name,LocalDateTime record_time,String record_content,Integer record_type,Integer no_read,Integer group_id,String group_photo,String group_name,LocalDateTime grouprecord_time,String grouprecord_content,Integer grouprecord_type,Integer group_no_read)
    {
        this.messagetype=messagetype;
        if (messagetype=="friends") {
            this.user_id = user_id;
            this.user_photo = user_photo;
            this.user_name = user_name;
            this.record_time = record_time;
            this.record_content = record_content;
            this.record_type = record_type;
            this.no_read = no_read;
        }
        else if (messagetype=="groups") {
            this.group_id = group_id;
            this.group_photo = group_photo;
            this.group_name = group_name;
            this.grouprecord_time = grouprecord_time;
            this.grouprecord_content = grouprecord_content;
            this.grouprecord_type = grouprecord_type;
            this.group_no_read = group_no_read;
        }
    }*/
  public ChatWindows(String messagetype,Integer id,String photo,String name,LocalDateTime time,
                     String content,Integer type,Boolean noread)
  {
      this.messagetype=messagetype;
          this.group_id = id;
          this.group_photo = photo;
          this.group_name = name;
          this.grouprecord_time = time;
          this.grouprecord_content = content;
          this.grouprecord_type = type;
          this.group_no_read = noread;

  }
    public ChatWindows(String messagetype,Integer id,String photo,String name,LocalDateTime time,
                       String content,Integer type,Integer noread)
    {
        this.messagetype=messagetype;
        this.user_id = id;
        this.user_photo = photo;
        this.user_name = name;
        this.record_time = time;
        this.record_content = content;
        this.record_type = type;
        this.no_read = noread;
    }
    public ChatWindows()
    {

    }

    public String getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public LocalDateTime getRecord_time() {
        return record_time;
    }

    public void setRecord_time(LocalDateTime record_time) {
        this.record_time = record_time;
    }

    public String getRecord_content() {
        return record_content;
    }

    public void setRecord_content(String record_content) {
        this.record_content = record_content;
    }

    public Integer getRecord_type() {
        return record_type;
    }

    public void setRecord_type(Integer record_type) {
        this.record_type = record_type;
    }

    public Integer getNo_read() {
        return no_read;
    }

    public void setNo_read(Integer no_read) {
        this.no_read = no_read;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getGroup_photo() {
        return group_photo;
    }

    public void setGroup_photo(String group_photo) {
        this.group_photo = group_photo;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public LocalDateTime getGrouprecord_time() {
        return grouprecord_time;
    }

    public void setGrouprecord_time(LocalDateTime grouprecord_time) {
        this.grouprecord_time = grouprecord_time;
    }

    public String getGrouprecord_content() {
        return grouprecord_content;
    }

    public void setGrouprecord_content(String grouprecord_content) {
        this.grouprecord_content = grouprecord_content;
    }

    public Integer getGrouprecord_type() {
        return grouprecord_type;
    }

    public void setGrouprecord_type(Integer grouprecord_type) {
        this.grouprecord_type = grouprecord_type;
    }

    public Boolean getGroup_no_read() {
        return group_no_read;
    }

    public void setGroup_no_read(Boolean group_no_read) {
        this.group_no_read = group_no_read;
    }

    public LocalDateTime getCompare_time() {
        return compare_time;
    }

    public void setCompare_time(LocalDateTime compare_time) {
        this.compare_time = compare_time;
    }
}
