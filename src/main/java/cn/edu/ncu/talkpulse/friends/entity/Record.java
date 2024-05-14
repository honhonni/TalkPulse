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

    // 是否已读
    private Integer read_status;

    public Record(Integer record_d, String record_content, LocalDateTime record_time, Integer record_senderid, Integer record_recipientid, Integer read_status) {
        this.record_id = record_d;
        this.record_content = record_content;
        this.record_time = record_time;
        this.record_senderid = record_senderid;
        this.record_recipientid = record_recipientid;
        this.read_status = read_status;
    }

    public Record() {
    }

    @Override
    public String toString() {
        return "Record{" +
                "record_d=" + record_id +
                ", record_content='" + record_content + '\'' +
                ", record_time=" + record_time +
                ", record_senderid=" + record_senderid +
                ", record_recipientid=" + record_recipientid +
                ", read_status=" + read_status +
                '}';
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

    public Integer getRead_status() {
        return read_status;
    }

    public void setRead_status(Integer read_status) {
        this.read_status = read_status;
    }
}
