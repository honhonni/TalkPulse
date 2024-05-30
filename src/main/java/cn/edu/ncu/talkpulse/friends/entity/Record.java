
package cn.edu.ncu.talkpulse.friends.entity;

import java.time.LocalDateTime;

// 私聊记录表
public class Record {
    // id
    private Integer record_id;

    // 私聊内容
    private String record_content;

    // 发送时间
    private LocalDateTime record_time;

    // 发送者id
    private Integer record_senderid;

    // 接受者id
    private Integer record_recipientid;

    // 是否已读（0未读、1已读）
    private Integer record_readstatus;

    // 消息类型（0文本、1图片、2语音）
    private Integer record_type;

    public Record(String record_content, LocalDateTime record_time, Integer record_senderid, Integer record_recipientid, Integer read_status,Integer record_type) {
        this.record_content = record_content;
        this.record_time = record_time;
        this.record_senderid = record_senderid;
        this.record_recipientid = record_recipientid;
        this.record_readstatus = read_status;
        this.record_type = record_type;
    }

    public Record(Integer record_id, String record_content, LocalDateTime record_time, Integer record_senderid, Integer record_recipientid, Integer read_status, Integer record_type) {
        this.record_id = record_id;
        this.record_content = record_content;
        this.record_time = record_time;
        this.record_senderid = record_senderid;
        this.record_recipientid = record_recipientid;
        this.record_readstatus = read_status;
        this.record_type = record_type;
    }

    public Record() {
    }

    @Override
    public String toString() {
        return "Record{" +
                "record_id=" + record_id +
                ", record_content='" + record_content + '\'' +
                ", record_time=" + record_time +
                ", record_senderid=" + record_senderid +
                ", record_recipientid=" + record_recipientid +
                ", read_status=" + record_readstatus +
                ", record_type=" + record_type +
                '}';
    }

    public Integer getRecord_type() {
        return record_type;
    }

    public void setRecord_type(Integer record_type) {
        this.record_type = record_type;
    }

    public Integer getRecord_id() {
        return record_id;
    }

    public void setRecord_id(Integer record_id) {
        this.record_id = record_id;
    }

    public String getRecord_content() {
        return record_content;
    }

    public void setRecord_content(String record_content) {
        this.record_content = record_content;
    }

    public LocalDateTime getRecord_time() {
        return record_time;
    }

    public void setRecord_time(LocalDateTime record_time) {
        this.record_time = record_time;
    }

    public Integer getRecord_senderid() {
        return record_senderid;
    }

    public void setRecord_senderid(Integer record_senderid) {
        this.record_senderid = record_senderid;
    }

    public Integer getRecord_recipientid() {
        return record_recipientid;
    }

    public void setRecord_recipientid(Integer record_recipientid) {
        this.record_recipientid = record_recipientid;
    }

    public Integer getRecord_readstatus() {
        return record_readstatus;
    }

    public void setRecord_readstatus(Integer record_readstatus) {
        this.record_readstatus = record_readstatus;
    }
}